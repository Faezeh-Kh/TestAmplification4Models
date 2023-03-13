package coverage.computation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecoretools.ale.BehavioredClass;
import org.eclipse.emf.ecoretools.ale.Operation;
import org.eclipse.emf.ecoretools.ale.Tag;
import org.eclipse.emf.ecoretools.ale.Unit;
import org.eclipse.gemoc.dsl.Dsl;
import org.osgi.framework.Bundle;

import DSLSpecificCoverage.DomainSpecificCoverage;
import coverage.dslSpecific.DSLSpecificCoverageHandler;
import coverage.dslSpecific.IDSLSpecificCoverage;
import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.Step;

public class TDLCoverageUtil {
	
	private static TDLCoverageUtil instance = new TDLCoverageUtil();
	
	private String DSLPath;
	private EPackage metamodelRootElement;
	private Set<String> coverableClasses = new HashSet<>();
	private Set<String> extendedClassesWithStep = new HashSet<>();
	private Set<String> extendedClassesWithoutStep = new HashSet<>();

	private List<EClass> classesWithDynamicFeatures = new ArrayList<>();
	private List<EClass> dynamicClasses = new ArrayList<>();
	
	public static final String COVERED = "Covered";
	public static final String NOT_COVERED = "Not_Covered";
	public static final String NOT_TRACED = "Not_Traced";
	
	private IDSLSpecificCoverage dslSpecificCoverageExtension;
	private DomainSpecificCoverage dslSpecificCoverage;
	
	private TDLTestSuiteCoverage testSuiteCoverage;

	//make the constructor private so that this class cannot be
	//instantiated
	private TDLCoverageUtil(){}

	//Get the only object available
	public static TDLCoverageUtil getInstance(){
		return instance;
	}
	public TDLTestSuiteCoverage getTestSuiteCoverage() {
		return testSuiteCoverage;
	}
	public void setTestSuiteCoverage(TDLTestSuiteCoverage coverage) {
		testSuiteCoverage = coverage;
	}

	public String getDSLPath() {
		return DSLPath;
	}

	public void setDSLPath(String DSLPath) {
		instance.DSLPath = DSLPath;
	}
	
	public void runCoverageComputation() {
		coverableClasses.clear();
		extendedClassesWithStep.clear();
		extendedClassesWithoutStep.clear();
		dynamicClasses.clear();
		classesWithDynamicFeatures.clear();
		dslSpecificCoverage = null;
		dslSpecificCoverageExtension = null;
		
		findCoverableClassesFromDSLSemantics();
		testSuiteCoverage.calculateTSCoverage();
	}
	
	private void findCoverableClassesFromDSLSemantics(){
		final ResourceSet resSet = new ResourceSetImpl();
		IConfigurationElement language = Arrays
				.asList(Platform.getExtensionRegistry()
						.getConfigurationElementsFor("org.eclipse.gemoc.gemoc_language_workbench.xdsml"))
				.stream().filter(l -> l.getAttribute("xdsmlFilePath").equals(DSLPath.substring(16)))
				.findFirst().orElse(null);
		
		final Resource res = resSet.getResource(URI.createURI(DSLPath), true);
		final Dsl dsl = (Dsl) res.getContents().get(0);
		final Bundle bundle = Platform.getBundle(language.getContributor().getName());
		String ecoreFilePath = dsl.getEntry("ecore").getValue().replaceFirst("resource", "plugin");
		Resource ecoreResource = (new ResourceSetImpl()).getResource(URI.createURI(ecoreFilePath), true);
		metamodelRootElement = (EPackage) ecoreResource.getContents().get(0);
		
		if (dsl.getEntry("k3") != null) {
			findK3Classes(dsl, bundle);
		}else if (dsl.getEntry("ale") != null) {
			findAleClasses(dsl, bundle);
		}
		checkInheritanceForNotCoverableClasses();
	}

	private void findK3Classes(Dsl dsl, Bundle bundle) {
		final List<Class<?>> classes = dsl.getEntries().stream().filter(e -> e.getKey().equals("k3"))
				.findFirst()
				.map(os -> Arrays.asList(os.getValue().split(",")).stream().map(cn -> loadClass(cn, bundle))
						.filter(c -> c != null).collect(Collectors.toList()))
				.orElse(Collections.emptyList()).stream().map(c -> (Class<?>) c).collect(Collectors.toList());
		
		//if a class is opened and has a stepping rule in the xDSL's interpreter, 
		//the objects of the class are coverable
		for (Class<?> clazz : classes) {
			String className = clazz.getDeclaredAnnotation(Aspect.class).className().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			Method[] methods = clazz.getDeclaredMethods();
			for (Method method: methods) {
				if (method.getAnnotationsByType(Step.class).length > 0) {
					extendedClassesWithStep.add(className);
					coverableClasses.add(className);
					break;
				}
			}
			//if the extended class had no @step rule
			if (!extendedClassesWithStep.contains(className)) {
				extendedClassesWithoutStep.add(className);
			}
		}
	}
	
	private void findAleClasses(Dsl dsl, Bundle bundle) {
		String aleFilePath = dsl.getEntry("ale").getValue().replaceFirst("resource", "plugin");
		Resource aleResource = (new ResourceSetImpl()).getResource(URI.createURI(aleFilePath), true);
		Unit interpreter = (Unit) aleResource.getContents().get(0);
		List<BehavioredClass> classes = interpreter.getXtendedClasses();
		//if a class is opened and has a stepping rule in the xDSL's interpreter, 
		//the objects of the class are coverable
		for (BehavioredClass clazz : classes) {
			EList<Operation> operations = clazz.getOperations();
			String className = clazz.eClass().getName();
			for (int i=0; i<operations.size(); i++) {
				for (Tag tag:operations.get(i).getTag()) {
					if (tag.getName().equals("step")) {
						extendedClassesWithStep.add(className);
						coverableClasses.add(className);
						i = operations.size();
						break;
					}
				}
			}
			//if the extended class had no @step rule
			if (!extendedClassesWithStep.contains(className)) {
				extendedClassesWithoutStep.add(className);
			}
		}
	}
	private Class<?> loadClass(String className, Bundle bundle) {
		Class<?> result = null;
		try {
			result = bundle.loadClass(className.replaceAll("\\s", "").trim());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//for each class that is not coverable (it is not extended in the interpreter)
	//if one of its super classes is coverable, the class must be set as coverable 
	public void checkInheritanceForNotCoverableClasses() {
		for (EClassifier clazz: metamodelRootElement.getEClassifiers()) {
			String className = clazz.getName();
			if (clazz instanceof EClass) {
				if (!coverableClasses.contains(className) && 
						!extendedClassesWithoutStep.contains(className)) {
					checkInheritance((EClass) clazz);
				}
				checkDynamicAspectsOfClass(clazz);	
			}
		}	
	}
	
	private void checkInheritance(EClass eClazz) {
		for (EClass superClass:eClazz.getEAllSuperTypes()) {
			if (coverableClasses.contains(superClass.getName())) {
				coverableClasses.add(eClazz.getName());
				break;
			}
		}
	}
	
	private void checkDynamicAspectsOfClass(EClassifier clazz) {
		List<EAnnotation> classDynamicAnnotations = clazz.getEAnnotations().stream().
				filter(a -> a.getSource().equals("dynamic") || a.getSource().equals("aspect")).collect(Collectors.toList());
		//if the type of the object is dynamic, all of its features must be set to the default values
		if (classDynamicAnnotations != null && classDynamicAnnotations.size()>0) {
			dynamicClasses.add((EClass) clazz);
		}
		else {
			List<EStructuralFeature> dynamicFeatures = ((EClass) clazz).getEAllStructuralFeatures().stream().
					filter(f -> isDynamicFeature(f)).collect(Collectors.toList());
			if (dynamicFeatures != null && dynamicFeatures.size()>0) {
				classesWithDynamicFeatures.add((EClass) clazz);
			}
		}
	}
	
	private boolean isDynamicFeature(EStructuralFeature feature) {
		List<EAnnotation> featureDynamicAnnotations = feature.getEAnnotations().stream().
				filter(a -> a.getSource().equals("dynamic") || a.getSource().equals("aspect")).collect(Collectors.toList());
		return (featureDynamicAnnotations != null && featureDynamicAnnotations.size() > 0);
	}

	public boolean isClassCoverable(EClass clazz) {
		return coverableClasses.contains(clazz.getName());
	}
	
	//add a class and all of its sub classes
	public void addCoverableClass(EClass clazz) {
		if (!coverableClasses.contains(clazz.getName())) {
			coverableClasses.add(clazz.getName());
			List<String> notCoverableSubClasses = metamodelRootElement.getEClassifiers().stream().
					filter(c -> c instanceof EClass).map(c -> (EClass) c).
					filter(c -> c.getEAllSuperTypes().stream().
							filter(sc -> sc.getName().equals(clazz.getName())).findAny().isPresent() 
							&& !coverableClasses.contains(c.getName())
							&& !extendedClassesWithoutStep.contains(c.getName())).
					map (c -> c.getName()).collect(Collectors.toList());
				coverableClasses.addAll(notCoverableSubClasses);
		}
	}
	
	public void removeCoverableClass(EClass clazz) {
		if (coverableClasses.contains(clazz.getName())) {
			coverableClasses.remove(clazz.getName());
		}
	}
	//remove a class and all of its sub classes
	public void removeCoverableClass_subClass(EClass clazz) {
		if (coverableClasses.contains(clazz.getName())) {
			coverableClasses.remove(clazz.getName());
			List<String> coverableSubClasses = metamodelRootElement.getEClassifiers().stream().
					filter(c -> c instanceof EClass).map(c -> (EClass) c).
					filter(c -> c.getEAllSuperTypes().stream().
							filter(sc -> sc.getName().equals(clazz.getName())).findAny().isPresent() 
							&& coverableClasses.contains(c.getName())).
					map (c -> c.getName()).collect(Collectors.toList());
				coverableClasses.removeAll(coverableSubClasses);
		}
	}
	
	public List<EClass> getClassesWithDynamicFeatures() {
		return classesWithDynamicFeatures;
	}

	public List<EClass> getDynamicClasses() {
		return dynamicClasses;
	}
	
	public DomainSpecificCoverage getDslSpecificCoverage() {
		if (dslSpecificCoverage == null) {
			findDSLSpecificCoverage();
		}
		return dslSpecificCoverage;
	}
	
	private void findDSLSpecificCoverage() {
		//check if there is a DSL-Specific coverage extension
		DSLSpecificCoverageHandler dslSpecificCoverageHandler = new DSLSpecificCoverageHandler();
		dslSpecificCoverageExtension = dslSpecificCoverageHandler.getDSLSpecificCoverage();
		//1. if the rules are implemented in a java class, retrieve them using extension point
		if (dslSpecificCoverageExtension != null &&
				dslSpecificCoverageExtension.getDomainSpecificCoverage() != null) {
			dslSpecificCoverage = dslSpecificCoverageExtension.getDomainSpecificCoverage();
		}
		//2. else, check .dsl file for the path to the coverageRules.xmi file
		else {
			Resource coverageFileResource = loadDSLSpecificCoverageFile();
			if (coverageFileResource != null) {
				dslSpecificCoverage = (DomainSpecificCoverage) coverageFileResource.getContents().get(0);
			}
		}
	}
	
	private Resource loadDSLSpecificCoverageFile() {
//		ResourceSet resourceSet = new ResourceSetImpl();
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("cov", new XMIResourceFactoryImpl());
//		resourceSet.getPackageRegistry().put(DSLSpecificCoveragePackage.eNS_URI, DSLSpecificCoveragePackage.eINSTANCE);
		String coverageFilePath = getCoverageFilePath();
		if (coverageFilePath != null) {
			Resource coverageFileResource = (new ResourceSetImpl()).getResource(URI.createURI(coverageFilePath), true);
			return coverageFileResource;
		}
		return null;
	}
	
	private String getCoverageFilePath() {
		final Resource res = (new ResourceSetImpl()).getResource(URI.createURI(DSLPath), true);
		final Dsl dsl = (Dsl) res.getContents().get(0);
		if (dsl.getEntry("coverageFilePath") != null) {
			return dsl.getEntry("coverageFilePath").getValue().replaceFirst("resource", "plugin");
		}
		return null;
	}
	
	public IDSLSpecificCoverage getDslSpecificCoverageExtension() {
		if (dslSpecificCoverageExtension == null) {
			findDSLSpecificCoverage();
		}
		return dslSpecificCoverageExtension;
	}
}
