package org.imt.tdl.coverage.computation;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.imt.tdl.coverage.dslSpecific.DSLSpecificCoverageHandler;
import org.imt.tdl.coverage.dslSpecific.IDSLSpecificCoverage;
import org.imt.tdl.utilities.DSLProcessor;
import DSLSpecificCoverage.DomainSpecificCoverage;

public class TDLCoverageUtil {
	
private static TDLCoverageUtil instance = new TDLCoverageUtil();
	
	private DSLProcessor dslProcessor;
	private Path DSLPath;
	private EPackage metamodelRootElement;
	private Set<String> extendedClassesWithoutStep = new HashSet<>();
	private Set<String> coverableClasses = new HashSet<>();
	
	private List<EClass> classesWithDynamicFeatures = new ArrayList<>();
	private List<EClass> dynamicClasses = new ArrayList<>();
	
	public static final String COVERED = "covered";
	public static final String NOT_COVERED = "not covered";
	public static final String PARTLY_COVERED = "partly covered";
	public static final String NOSTATUS = "no status";
	
	public static final String TRACEBASEDCOVERAGE = "basedOnTrace";
	public static final String DSLSPECIFICCOVERAGE = "dslSpecific";
	public static final String BRANCHCOVERAGE = "branchCoverage";
	
	private IDSLSpecificCoverage dslSpecificCoverageExtension;
	private List<DomainSpecificCoverage> dslSpecificCoverages;
	
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

	public Path getDSLPath() {
		return DSLPath;
	}

	public void setDSLPath(Path DSLPath) {
		instance.DSLPath = DSLPath;
	}
	
	public void runCoverageComputation() {
		extendedClassesWithoutStep.clear();
		coverableClasses.clear();
		dynamicClasses.clear();
		classesWithDynamicFeatures.clear();
		
		dslProcessor = new DSLProcessor(DSLPath);
		dslProcessor.loadDSLMetaclasses();
		metamodelRootElement = dslProcessor.getMetamodelRootElement();
		findCoverableClassesFromDSLSemantics();
		
		dslSpecificCoverageExtension = null;
		dslSpecificCoverages = null;
		
		testSuiteCoverage.calculateTSCoverage();
	}
	
	private void findCoverableClassesFromDSLSemantics(){
		dslProcessor.findDSLExtendedClasses();
		coverableClasses.addAll(dslProcessor.getExtendedClassesWithStep());
		extendedClassesWithoutStep.addAll(dslProcessor.getExtendedClassesWithoutStep());
		checkInheritanceForNotCoverableClasses();
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
				checkDynamicAspectsOfClass((EClass) clazz);	
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
				filter(a -> a.getSource().equals("dynamic") || 
						a.getSource().equals("aspect")).collect(Collectors.toList());
		return (featureDynamicAnnotations != null && featureDynamicAnnotations.size() > 0);
	}

	
	public EPackage getMetamodelRootElement() {
		return metamodelRootElement;
	}

	public Set<String> getCoverableClasses() {
		return coverableClasses;
	}

	public List<EClass> getClassesWithDynamicFeatures() {
		return classesWithDynamicFeatures;
	}

	public List<EClass> getDynamicClasses() {
		return dynamicClasses;
	}
	
	public List<DomainSpecificCoverage> getDslSpecificCoverages() {
		return dslSpecificCoverages;
	}
	
	public IDSLSpecificCoverage getDslSpecificCoverageExtension() {
		if (dslSpecificCoverageExtension == null) {
			findDSLSpecificCoverage();
		}
		return dslSpecificCoverageExtension;
	}
	
	private void findDSLSpecificCoverage() {
		dslSpecificCoverages = new ArrayList<>();
		//check if there is a DSL-Specific coverage extension
		DSLSpecificCoverageHandler dslSpecificCoverageHandler = new DSLSpecificCoverageHandler();
		dslSpecificCoverageExtension = dslSpecificCoverageHandler.getDSLSpecificCoverage();
		//1. if the rules are implemented in a java class, retrieve them using extension point
		if (dslSpecificCoverageExtension != null &&
				dslSpecificCoverageExtension.getDomainSpecificCoverage() != null) {
			dslSpecificCoverages.add(dslSpecificCoverageExtension.getDomainSpecificCoverage());
		}
		//2. else, check .dsl file for the path to the coverageRules.cov file
		else {
			List<String> coverageFilesPathes = getCoverageFilePath();
			if (coverageFilesPathes != null) {
				ResourceSet resSet = new ResourceSetImpl();
				coverageFilesPathes.forEach(path -> resSet.getResource(URI.createPlatformPluginURI(path.trim(), false), true));
				EcoreUtil.resolveAll(resSet);
				resSet.getResources().forEach(r -> dslSpecificCoverages.add((DomainSpecificCoverage) r.getContents().get(0)));
			}
		}
	}

	
	private List<String> getCoverageFilePath() {
		String path = dslProcessor.getPath2CoverageRules();
		if (path == null) {
			return null;
		}
		List<String> path2coverageFiles = new ArrayList<>();
		if (path.contains(",")) {
			//there are several coverage files
			path2coverageFiles.addAll(Arrays.asList(path.split(",")));
		}else {
			//there is only one coverage file
			path2coverageFiles.add(path);
		}
		return path2coverageFiles;
	}
}