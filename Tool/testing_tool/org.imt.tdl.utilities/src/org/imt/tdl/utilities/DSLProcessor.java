package org.imt.tdl.utilities;

import java.lang.reflect.Method;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecoretools.ale.BehavioredClass;
import org.eclipse.emf.ecoretools.ale.Operation;
import org.eclipse.emf.ecoretools.ale.Tag;
import org.eclipse.emf.ecoretools.ale.Unit;
import org.eclipse.gemoc.dsl.Dsl;
import org.osgi.framework.Bundle;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.Step;

public class DSLProcessor {
	
	Path DSLPath = null;
	URI DSLURI;
	Dsl dsl;
	IConfigurationElement language;
	HashMap <String, String> dslEntries;
	
	EPackage metamodelRootElement;
	EList<EClassifier> eClassifiers;
	
	private Set<String> extendedClassesWithStep = new HashSet<>();
	private Set<String> extendedClassesWithoutStep = new HashSet<>();
	
	final static String NAME = "name";
	final static String ECORE = "ecore";
	final static String K3 = "k3";
	final static String ALE = "ale";
	final static String BEHAVIORAL_INTERFACE = "behavioralInterface";
	final static String IMPLEM_REL_ID = "implemRelId";
	final static String SUBTYPE_REL_ID = "subtypeRelId";
	final static String COVERAGE_RULES = "coverageRules";
	final static String MUTATION_OPERATORS = "mutationOperators";
	
	final static String xdsml_extension_point_id = "org.eclipse.gemoc.gemoc_language_workbench.xdsml";
	
	public DSLProcessor(String DSLName){
		DSLPath = findDSLPath(DSLName);
		DSLURI = URI.createPlatformPluginURI(DSLPath.toString(), false);
		loadDSL();
	}
	
	public DSLProcessor(Path DSLPath) {
		this.DSLPath = DSLPath;
		DSLURI = URI.createPlatformPluginURI(DSLPath.toString(), false);
		loadDSL();
	}
	
	public DSLProcessor(URI DSLURI) {
		this.DSLURI = DSLURI;
		DSLPath = (new PathHelper()).getPath(DSLURI);
		loadDSL();
	}
	private void loadDSL(){
		dslEntries = new HashMap<>();
		Resource dslRes = (new ResourceSetImpl()).getResource(DSLURI, true);
		dsl = (Dsl) dslRes.getContents().get(0);
		dsl.getEntries().forEach(e -> dslEntries.put(e.getKey(), e.getValue()));
	}
	
	public void loadDSLMetaclasses(){
		if (dslEntries.get(ECORE) != null){
			String metamodelPath = dslEntries.get(ECORE).replaceFirst("resource", "plugin");
			Resource metamodelRes = (new ResourceSetImpl()).getResource(URI.createURI(metamodelPath), true);
			metamodelRootElement = (EPackage) metamodelRes.getContents().get(0);
			eClassifiers = new BasicEList<EClassifier>();
			eClassifiers.addAll(metamodelRootElement.getEClassifiers());
		}
	}
	
	public Path findDSLPath (String DSLName){
		language = findDSLFile(DSLName);
		if (language != null) {
			return Paths.get(language.getAttribute("xdsmlFilePath"));
		}
		return null;
	}
	
	private IConfigurationElement findDSLFile(String DSLName) {
		IConfigurationElement language = Arrays
				.asList(Platform.getExtensionRegistry()
						.getConfigurationElementsFor(xdsml_extension_point_id))
				.stream().filter(l -> l.getAttribute("xdsmlFilePath").endsWith(".dsl")
						&& l.getAttribute(NAME).equals(DSLName))
				.findFirst().orElse(null);
		return language;
	}
	
	public void findDSLExtendedClasses(){
		if (language == null) {
			language = findDSLFile(getDSLEntryValue(NAME));
		}
		if (dsl.getEntry(K3) != null) {
			findK3Classes();
		}
		else if (dsl.getEntry(ALE) != null) {
			findAleClasses();
		}
	}
	
	private void findK3Classes() {
		final Bundle bundle = Platform.getBundle(language.getContributor().getName());
		final List<Class<?>> classes = dsl.getEntries().stream().filter(e -> e.getKey().equals(K3))
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
					break;
				}
			}
			//if the extended class had no @step rule
			if (!extendedClassesWithStep.contains(className)) {
				extendedClassesWithoutStep.add(className);
			}
		}
	}
	
	private void findAleClasses() {
		String aleFilePath = dsl.getEntry(ALE).getValue().replaceFirst("resource", "plugin");
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
	
	
	public boolean isConcreteEcoreType(String typeName) {
		return eClassifiers.stream().filter(c -> c.getName().equals(getValidName(typeName)) 
				&& !c.eClass().isAbstract()).count() > 0;
	}
	
	public String getValidName(String name){
		if (name.startsWith("_")){
			return name.substring(1);
		}
		return name;
	}
	
	public Path getDSLPath(){
		return DSLPath;
	}
	
	public URI getDSLURI() {
		return DSLURI;
	}
	
	public String getDSLEntryValue (String key){
		return dslEntries.get(key);
	}
	
	public String getDSLName() {
		return dslEntries.get(NAME);
	}
	
	//NOTE: currently, we only consider K3 and ALE metaprogramming approaches for the implementation of the DSL's semantics
	public String getDSLSemanticsLanguage() {
		return dslEntries.get(K3) != null ? K3 : ALE;
	}
	
	public String getPath2Ecore() {
		return dslEntries.get(ECORE);
	}
	
	public boolean dslHasBehavioralInterface() {
		return dslEntries.get(BEHAVIORAL_INTERFACE) != null;
	}
	
	public String getPath2BehavioralInterface() {
		return dslEntries.get(BEHAVIORAL_INTERFACE);
	}
	
	public boolean dslHasImplemRelId() {
		return dslEntries.get(IMPLEM_REL_ID) != null;
	}
	
	public String getImplemRelId() {
		return dslEntries.get(IMPLEM_REL_ID);
	}
	
	public boolean dslHasSubtypemRelId() {
		return dslEntries.get(SUBTYPE_REL_ID) != null;
	}
	
	public String getSubtypeRelId() {
		return dslEntries.get(SUBTYPE_REL_ID);
	}
	
	public boolean dslHasCoverageRules() {
		return dslEntries.get(COVERAGE_RULES) != null;
	}
	
	public String getPath2CoverageRules() {
		return dslEntries.get(COVERAGE_RULES);
	}
	
	public boolean dslHasMutationOperators() {
		return dslEntries.get(MUTATION_OPERATORS) != null;
	}
	
	public String getPath2MutationOperators() {
		return dslEntries.get(MUTATION_OPERATORS);
	}

	public IConfigurationElement getLanguage() {
		return language;
	}

	public EPackage getMetamodelRootElement() {
		return metamodelRootElement;
	}

	public EList<EClassifier> geteClassifiers() {
		return eClassifiers;
	}

	public Set<String> getExtendedClassesWithStep() {
		return extendedClassesWithStep;
	}

	public Set<String> getExtendedClassesWithoutStep() {
		return extendedClassesWithoutStep;
	}
}
