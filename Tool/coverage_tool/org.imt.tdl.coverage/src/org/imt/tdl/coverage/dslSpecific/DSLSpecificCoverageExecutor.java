package org.imt.tdl.coverage.dslSpecific;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.imt.tdl.coverage.computation.TDLCoverageUtil;
import org.imt.tdl.coverage.computation.TDLTestCaseCoverage;
import org.imt.tdl.coverage.report.TestCoverageReport;
import org.imt.tdl.coverage.utilities.OCLInterpreter;

import DSLSpecificCoverage.BranchSpecification;
import DSLSpecificCoverage.Condition;
import DSLSpecificCoverage.Context;
import DSLSpecificCoverage.CoverageByContent;
import DSLSpecificCoverage.CoverageOfReferenced;
import DSLSpecificCoverage.CoveredContents;
import DSLSpecificCoverage.DomainSpecificCoverage;
import DSLSpecificCoverage.Ignore;
import DSLSpecificCoverage.Import;
import DSLSpecificCoverage.LimitationType;
import DSLSpecificCoverage.LimitedIgnore;
import DSLSpecificCoverage.Rule;

public class DSLSpecificCoverageExecutor {

	private TDLTestCaseCoverage testCaseCoverage;
	
	private DomainSpecificCoverage coverageRuleset;
	private HashMap<Context, List<EObject>> coverageContext_eobjects = new HashMap<>();
	
	private HashMap<EObject, LinkedHashSet<EObject>> object2find_objects2add = new HashMap<>();
	private List<EObject> objectsCapturedByTrace_modified;
	
	private TestCoverageReport tcDslSpecificCoverageReport;
	private List<TestCoverageReport> tcCoverageReports = new ArrayList<>();
	
	private HashMap<BranchSpecification, LinkedHashSet<EObject>> branchingRule_contextObjects = new HashMap<>();
	
	public DSLSpecificCoverageExecutor (TDLTestCaseCoverage testCaseCoverage, DomainSpecificCoverage coverageRuleset) {
		this.testCaseCoverage = testCaseCoverage;
		this.coverageRuleset = coverageRuleset;
		coverageContext_eobjects= new HashMap<>();
	}
	
	public void runDSLSpecificCoverage() {
		String coverageTitle = TDLCoverageUtil.DSLSPECIFICCOVERAGE + "_" + coverageRuleset.getName();
		tcDslSpecificCoverageReport = new TestCoverageReport(coverageTitle);
		tcDslSpecificCoverageReport.setCoverableClasses(TDLCoverageUtil.getInstance().getCoverableClasses());
		tcDslSpecificCoverageReport.setObjects(testCaseCoverage.getAllModelObjects());
		
		//the initial coverage status is the one computed based on the trace
		tcDslSpecificCoverageReport.setObjectCoverageStatus(testCaseCoverage.getTcObjectCoverageStatusByTrace());

		//finding eobjects of each context
		setCoverageContext_objects(coverageRuleset);
		for(Import importedRuleset:coverageRuleset.getImports()) {
			DomainSpecificCoverage crs = importedRuleset.getImportedRuleset();
			if (crs == null) {
				URI importedURI = URI.createPlatformPluginURI(importedRuleset.getImportURI(), false);
				Resource importedRes = coverageRuleset.eResource().getResourceSet().getResource(importedURI, false);
				crs = (DomainSpecificCoverage) importedRes.getContents().get(0);
			}
			setCoverageContext_objects(crs);
		}
		//apply domain specific coverage while the coverage matrix changes
		boolean isCoverageMatrixChanged = true;
		while (isCoverageMatrixChanged) {
			List<String> objectCoverageStatus_pv = new ArrayList<>(tcDslSpecificCoverageReport.getObjectCoverageStatus());
			coverageContext_eobjects.entrySet().stream()
				.filter(entry -> !entry.getValue().isEmpty())
				.forEach(entry -> applyCoverageRules(entry.getKey().getRules(), entry.getValue()));
			isCoverageMatrixChanged = !Objects.equals(objectCoverageStatus_pv, tcDslSpecificCoverageReport.getObjectCoverageStatus());
		}
		tcDslSpecificCoverageReport.findNotCoverableObjects();
		tcDslSpecificCoverageReport.computeCoveragePercentage();
		if (hasOnlyGenericRules()) {
			tcDslSpecificCoverageReport.printCoverageResult(testCaseCoverage.getTestCaseName());
			tcCoverageReports.add(tcDslSpecificCoverageReport);
		}
		
		//if there are branchSpecification rules, compute branch coverage
		if (!branchingRule_contextObjects.isEmpty() && noOverlapsExists()) {
			updateObjectsCapturedByTrace();
			TestCoverageReport tcBranchCoverageReport = (new DSLSpecificBranchCoverage(this)).runBranchCoverageComputation();
			tcCoverageReports.add(tcBranchCoverageReport);
		}
	}

	private boolean noOverlapsExists() {
		int numOfContextObjects = (int) branchingRule_contextObjects.values().stream().count();
		int numOfDistinctContextObjects = (int) branchingRule_contextObjects.values().stream().distinct().count();
		if (numOfContextObjects == numOfDistinctContextObjects) {
			return true;
		}
		System.out.println("There is an overlap between the BranchSpecification rules");
		return false;
	}

	private boolean hasOnlyGenericRules() {
		for (Context context:coverageRuleset.getContexts()) {
			if (context.getRules().stream().anyMatch(r -> r instanceof BranchSpecification)) {
				return false;
			}
		}
		return true;
	}

	private void setCoverageContext_objects(DomainSpecificCoverage coverageRuleset) {
		for (Context context : coverageRuleset.getContexts()) {
			if (coverageContext_eobjects.get(context) == null) {
				coverageContext_eobjects.put(context, new ArrayList<>());
			}
			coverageContext_eobjects.get(context).addAll(
					testCaseCoverage.getAllModelObjects().stream()
						.filter(o -> isContextInstance(context.getMetaclass().getName(), o))
						.collect(Collectors.toList())
					);
		}
	}
	
	private boolean isContextInstance(String contextName, EObject object) {
		String objectTypeName = object.eClass().getName();
		if (objectTypeName.equals(contextName) ||
				object.eClass().getEAllSuperTypes().stream().
					filter(st -> st.getName().equals(contextName)).findAny().isPresent()) {
			return true;
		}
		return false;
	}
	
	//apply all the rules on the object (NOTE: rule's context = object type)
	public void applyCoverageRules(EList<Rule> rules, List<EObject> eObjects) {
		for (Rule rule:rules) {
			List<EObject> validObjects = eObjects.stream()
					.filter(object -> isRuleConditionSatisfied(rule.getCondition(), object))
					.collect(Collectors.toList());
			if (!validObjects.isEmpty()) {
				if (rule instanceof Ignore) {
					Ignore ignoreRule = (Ignore) rule;
					updateCoverableClasses(ignoreRule);
					validObjects.forEach(object -> runIgnoreRule(ignoreRule, object));
				}
				else if (rule instanceof LimitedIgnore) {
					LimitedIgnore limitedIgnoreRule = (LimitedIgnore) rule;
					validObjects.forEach(object -> runLimitedIgnoreRule(limitedIgnoreRule, object));
				}
				else if (rule instanceof CoverageOfReferenced) {
					CoverageOfReferenced refRule = (CoverageOfReferenced) rule;
					updateCoverableClasses(refRule);
					validObjects.forEach(object -> inferReferenceCoverage(refRule, object));
				}
				else if (rule instanceof CoverageByContent) {
					CoverageByContent containmentRule = (CoverageByContent) rule;
					updateCoverableClasses(containmentRule);
					validObjects.forEach(object -> inferContainerCoverage(containmentRule, object));
				}
				else if (rule instanceof BranchSpecification) {
					BranchSpecification branchRule = (BranchSpecification) rule; 
					if (!validObjects.isEmpty()) {
						if (branchingRule_contextObjects.get(branchRule) == null) {
							branchingRule_contextObjects.put(branchRule, new LinkedHashSet<>());
						}
						branchingRule_contextObjects.get(branchRule).addAll(validObjects);
					}
				}
			}
		}
	}
	
	private void updateCoverableClasses(CoverageByContent rule) {
		addCoverableClass(((Context) rule.eContainer()).getMetaclass());
	}

	private void updateCoverableClasses(CoverageOfReferenced rule) {
		rule.getReference().stream().forEach(r -> addCoverableClass((EClass) r.getEType()));
	}
	
	private void updateCoverableClasses(Ignore rule) {
		if (rule.isIgnoreSubtypes()) {
			removeCoverableClass_subClass(((Context) rule.eContainer()).getMetaclass());
		}
		else {
			removeCoverableClass(((Context) rule.eContainer()).getMetaclass());
		}
	}

	private void inferReferenceCoverage(CoverageOfReferenced r, EObject object) {
		String coverageStatus = tcDslSpecificCoverageReport.getObjectCoverage(object);
		for (EReference reference:r.getReference()) {
			EReference ref = (EReference) getMatchedFeature(object, reference.getName());
			if (ref != null) {
				Object referencedObject = object.eGet(ref);
				if (referencedObject != null) { 
					if (referencedObject instanceof EObject) {
						EObject refObject = (EObject) referencedObject;
						tcDslSpecificCoverageReport.setObjectCoverage(refObject, coverageStatus);
						if (coverageStatus == TDLCoverageUtil.COVERED) {
							addObject2tracedObjects(object, refObject);
						}
					}
					else if (referencedObject instanceof EObjectContainmentEList<?>) {
						EObjectContainmentEList<?> refObjects = (EObjectContainmentEList<?>) referencedObject;
						refObjects.forEach(o -> tcDslSpecificCoverageReport.setObjectCoverage((EObject) o, coverageStatus));
						if (coverageStatus == TDLCoverageUtil.COVERED) {
							refObjects.forEach(refObject -> addObject2tracedObjects(object, (EObject) refObject));
						}
					}
				}
			}
		}
	}

	private void inferContainerCoverage(CoverageByContent r, EObject object) {
		if (r.getContainmentReference() != null) {
			EReference ref = (EReference) getMatchedFeature(object, r.getContainmentReference().getName());
			if (ref == null) { return;}
			
			Object containedObject = object.eGet(ref);
			if (containedObject == null) { return; }
			
			if (containedObject instanceof EObject) {
				EObject containee = (EObject) containedObject;
				String coverageStatus = tcDslSpecificCoverageReport.getObjectCoverage(containee);
				tcDslSpecificCoverageReport.setObjectCoverage(object, coverageStatus);
				if (coverageStatus == TDLCoverageUtil.COVERED) {
					addObject2tracedObjects(containee, object);
				}
			}
			else if (containedObject instanceof EObjectContainmentEList<?>) {
				EObjectContainmentEList<?> containees = (EObjectContainmentEList<?>) containedObject;
				//if several objects are contained, set coverage based on the rule's multiplicity
				if (r.getMultiplicity() == CoveredContents.ONE) {
					coverContainerIfOneContaineeCovered(containees);
				}
				else if (r.getMultiplicity() == CoveredContents.ALL){
					coverContainerIfAllContaineeCovered(containees);
				}
				else if (r.getMultiplicity() == CoveredContents.ANY) {
					coverContainerIfAnyContaineeCovered(object);
				}	
			}
		}
		else {
			coverContainerIfAnyContaineeCovered(object);
		}
	}

	private void coverContainerIfOneContaineeCovered(EObjectContainmentEList<?> containedObjects) {
		EObject container = ((EObject) containedObjects.get(0)).eContainer();
		String containeeCoverage = TDLCoverageUtil.NOT_COVERED;
		for (Object containee:containedObjects) {
			containeeCoverage = tcDslSpecificCoverageReport.getObjectCoverage((EObject) containee);
			if (containeeCoverage == TDLCoverageUtil.COVERED) {
				addObject2tracedObjects((EObject) containee, container);
				break;
			}
		}
		tcDslSpecificCoverageReport.setObjectCoverage(container, containeeCoverage);
	}
	
	private void coverContainerIfAllContaineeCovered(EObjectContainmentEList<?> containedObjects) {
		int CoveredContentsCounter = 0;
		for (Object containee:containedObjects) {
			String containeeCoverage = tcDslSpecificCoverageReport.getObjectCoverage((EObject) containee);
			if (containeeCoverage == TDLCoverageUtil.COVERED) {
				CoveredContentsCounter++;
			}
		}
		if (CoveredContentsCounter == containedObjects.size()) {
			EObject container = ((EObject) containedObjects.get(0)).eContainer();
			tcDslSpecificCoverageReport.setObjectCoverage(container, TDLCoverageUtil.COVERED);
			addObject2tracedObjects((EObject) containedObjects.get(0), container);
		}
	}
	
	private void coverContainerIfAnyContaineeCovered(EObject container) {
		String coverageStatus = "";
		TreeIterator<EObject> iterator = container.eAllContents();
		while (iterator.hasNext()) {
			EObject containee = iterator.next();
			coverageStatus = tcDslSpecificCoverageReport.getObjectCoverage(containee);
			if (coverageStatus == TDLCoverageUtil.COVERED) {
				tcDslSpecificCoverageReport.setObjectCoverage(container, coverageStatus);
				addObject2tracedObjects(containee, container);
				break;
			}
		}
	}
	
	private void runLimitedIgnoreRule(LimitedIgnore rule, EObject object) {
		if (rule.getType() == LimitationType.CONTAINED_BY) {
			//ignore EObjects contained by one of the ContainerType classes
			if (rule.getContainerMetaclass().stream().
				anyMatch(c -> c.getName().equals(object.eContainer().eClass().getName()))) {
				tcDslSpecificCoverageReport.setObjectNotCoverable(object);
			}
		}
		else if (rule.getType() == LimitationType.NOT_CONTAINED_BY) {
			//ignore EObjects that are not contained by any of the ContainerType classes
			if (!rule.getContainerMetaclass().stream().
				anyMatch(c -> c.getName().equals(object.eContainer().eClass().getName()))) {
				tcDslSpecificCoverageReport.setObjectNotCoverable(object);
			}
		}
	}
	
	private void runIgnoreRule(Ignore rule, EObject object) {
		if (!rule.isIgnoreSubtypes() && !object.eClass().getName().equals(
				((Context) rule.eContainer()).getMetaclass().getName())) {
			return;
		}
		tcDslSpecificCoverageReport.setObjectNotCoverable(object);
	}
	
	private void addObject2tracedObjects(EObject object2find, EObject object2add) {
		if (object2find_objects2add.get(object2find) == null) {
			object2find_objects2add.put(object2find, new LinkedHashSet<>());
		}
		object2find_objects2add.get(object2find).add(object2add);
	}
	
	private void updateObjectsCapturedByTrace() {
		//extending the list of objects captured by trace based on the result of generic coverage rules.
		objectsCapturedByTrace_modified = new ArrayList<>();
		for (EObject capturedObject : testCaseCoverage.getObjectsCapturedByTrace()) {
			if (object2find_objects2add.get(capturedObject) != null) {
				LinkedHashSet<EObject> objects2add = object2find_objects2add.get(capturedObject);
				//check if the container of the captured object must be added
				EObject container2add = objects2add.stream()
						.filter(object2add -> object2add == capturedObject.eContainer())
						.findFirst().orElse(null);
				if (container2add != null) {
					//if the container must be added, add it before all of its contained elements
					int index = objectsCapturedByTrace_modified.size()-1;
					while (objectsCapturedByTrace_modified.get(index).eContainer() == container2add && index >= 0) {
						index--;
					}
					objectsCapturedByTrace_modified.add(index+1, container2add);
					objects2add.remove(container2add);
				}
				
				//add the captured object
				objectsCapturedByTrace_modified.add(capturedObject);
				
				//add rest of the elements: they are the covered referenced elements
				objectsCapturedByTrace_modified.addAll(objects2add);				
			}
			else {
				objectsCapturedByTrace_modified.add(capturedObject);
			}
		}
	}
	
	private EStructuralFeature getMatchedFeature(EObject rootElement, String featureName){
		EStructuralFeature matchedFeature = rootElement.eClass().getEAllStructuralFeatures().stream().
				filter(f -> f.getName().equals(featureName)).findFirst().get();
		return matchedFeature;
	}
	
	private boolean isRuleConditionSatisfied(Condition condition, EObject object) {
		if (condition == null) {
			return true;
		}
		return (new OCLInterpreter()).isConstraintSatisfied(object, condition.getOCLConstraint());
	}
	
	public boolean isClassCoverable(EClass clazz) {
		return tcDslSpecificCoverageReport.getCoverableClasses().contains(clazz.getName());
	}
	
	//add a class and all of its sub classes
	public void addCoverableClass(EClass clazz) {
		if (!tcDslSpecificCoverageReport.getCoverableClasses().contains(clazz.getName())) {
			tcDslSpecificCoverageReport.getCoverableClasses().add(clazz.getName());
			List<String> notCoverableSubClasses = TDLCoverageUtil.getInstance()
					.getMetamodelRootElement().getEClassifiers().stream()
					.filter(c -> c instanceof EClass).map(c -> (EClass) c)
					.filter(c -> c.getEAllSuperTypes().stream().
							filter(sc -> sc.getName().equals(clazz.getName())).findAny().isPresent() 
							&& !tcDslSpecificCoverageReport.getCoverableClasses().contains(c.getName())).
					map (c -> c.getName()).collect(Collectors.toList());
			tcDslSpecificCoverageReport.getCoverableClasses().addAll(notCoverableSubClasses);
		}
	}
	
	public void removeCoverableClass(EClass clazz) {
		if (tcDslSpecificCoverageReport.getCoverableClasses().contains(clazz.getName())) {
			tcDslSpecificCoverageReport.getCoverableClasses().remove(clazz.getName());
		}
	}
	
	//remove a class and all of its sub classes
	public void removeCoverableClass_subClass(EClass clazz) {
		if (tcDslSpecificCoverageReport.getCoverableClasses().contains(clazz.getName())) {
			tcDslSpecificCoverageReport.getCoverableClasses().remove(clazz.getName());
			List<String> coverableSubClasses = TDLCoverageUtil.getInstance()
					.getMetamodelRootElement().getEClassifiers().stream()
					.filter(c -> c instanceof EClass).map(c -> (EClass) c)
					.filter(c -> c.getEAllSuperTypes().stream().
							filter(sc -> sc.getName().equals(clazz.getName())).findAny().isPresent() 
							&& tcDslSpecificCoverageReport.getCoverableClasses().contains(c.getName())).
					map (c -> c.getName()).collect(Collectors.toList());
			tcDslSpecificCoverageReport.getCoverableClasses().removeAll(coverableSubClasses);
		}
	}

	public List<EObject> getObjectsCapturedByTrace_extended() {
		return objectsCapturedByTrace_modified;
	}
	
	public TDLTestCaseCoverage getTestCaseCoverage() {
		return testCaseCoverage;
	}

	public Set<String> getCoverableClasses() {
		return tcDslSpecificCoverageReport.getCoverableClasses();
	}

	public List<EObject> getAllModelObjects() {
		return tcDslSpecificCoverageReport.getObjects();
	}

	public HashMap<Context, List<EObject>> getCoverageContext_eobjects() {
		return coverageContext_eobjects;
	}

	public HashMap<BranchSpecification, LinkedHashSet<EObject>> getBranchingRule_contextObjects() {
		return branchingRule_contextObjects;
	}

	public List<String> getTcObjectCoverageStatus() {
		return tcDslSpecificCoverageReport.getObjectCoverageStatus();
	}

	public String getObjectCoverage(EObject object) {
		return tcDslSpecificCoverageReport.getObjectCoverage(object);
	}
	
	public TestCoverageReport getTcDslSpecificCoverageReport(String coverageType) {
		try {
			return tcCoverageReports.stream()
					.filter(r -> r.getReportTitle().equals(coverageType))
					.findFirst().get();
		}catch (NoSuchElementException e) {
			return null;
		}
	}

	public List<TestCoverageReport> getTcCoverageReports() {
		return tcCoverageReports;
	}
	
	
}