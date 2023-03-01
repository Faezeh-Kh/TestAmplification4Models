package org.imt.tdl.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.etsi.mts.tdl.Annotation;
import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.tdlPackage;
import org.etsi.mts.tdl.util.tdlResourceFactoryImpl;

public class PathHelper {

	IFile testSuiteFile;
	Path testSuiteFilePath;
	String testSuiteFileName;
	
	Resource testSuiteResource;
	URI testSuiteURI;
	
	Package testSuite;

	Path workspacePath;
	Path modelsProjectPath;
	Path modelUnderTestPath;
	Resource MUTResource;
	
	String DSLName;
	Path DSLPath;
	
	final static String MUTPATH = "MUTPath";
	final static String DSLNAME = "DSLName";
	
	public PathHelper (IFile testSuiteFile) {
		this.testSuiteFile = testSuiteFile;
		String filePath = testSuiteFile.getFullPath().toString();
		testSuiteURI = URI.createPlatformResourceURI(filePath, true);
		loadTestSuite (testSuiteURI);
		findTestSuiteNameAndPath();
	}
	
	public PathHelper (Resource testSuiteResource) {
		this.testSuiteResource = testSuiteResource;
		testSuite = (Package) testSuiteResource.getContents().get(0);
		findTestSuiteNameAndPath();
	}
	
	public PathHelper (Package testSuite) {
		this.testSuite = testSuite;
		testSuiteResource = testSuite.eResource();
		findTestSuiteNameAndPath();
	}
	
	public PathHelper (Path modelUnderTestPath) {
		this.modelUnderTestPath = modelUnderTestPath;
		//when the modelUnderTestPath found, find the workspace path
		findWorkspacePath();
	}
	
	public PathHelper() {
		
	}
	
	private void findTestSuiteNameAndPath() {
		testSuiteURI = testSuiteResource.getURI();
		String path = testSuiteURI.path().replace(testSuiteURI.segment(0), "");
		testSuiteFilePath = Paths.get(path);
		testSuiteFileName = testSuiteURI.lastSegment().replace("."+testSuiteURI.fileExtension(), "");
	}

	public void loadTestSuite (URI testSuiteURI) {
		ResourceSet resSet = new ResourceSetImpl();
		if (testSuiteURI.toString().endsWith(".xmi")) {
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
			resSet.getPackageRegistry().put(tdlPackage.eNS_URI, tdlPackage.eINSTANCE);
		} 
		else if (testSuiteURI.toString().endsWith(".model")) {
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("model", new XMIResourceFactoryImpl());
			resSet.getPackageRegistry().put(tdlPackage.eNS_URI, tdlPackage.eINSTANCE);
		}
		else if (testSuiteURI.toString().endsWith(".tdl")) {
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("tdl", new XMIResourceFactoryImpl());
			resSet.getPackageRegistry().put(tdlPackage.eNS_URI, tdlPackage.eINSTANCE);
		}
		else if (testSuiteURI.toString().endsWith(".tdlan2")) {
			resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".tdlan2", new tdlResourceFactoryImpl());
			resSet.getPackageRegistry().put(tdlPackage.eNS_URI, tdlPackage.eINSTANCE);
		}
		testSuiteResource = (resSet).getResource(testSuiteURI, true);
		testSuite = (Package) testSuiteResource.getContents().get(0);
	}

	public void findModelAndDSLPathOfTestSuite() {
		TestDescription testCase = testSuite.getPackagedElement().stream().
				filter(p -> p instanceof TestDescription).
				map(p -> (TestDescription) p).
				findFirst().get();
		findModelAndDSLPathOfTestCase(testCase);
	}
	
	public void findModelAndDSLPathOfTestCase(TestDescription testCase) {
		ComponentInstance sutComponent = testCase.getTestConfiguration().getComponentInstance().stream().
				filter(ci -> ci.getRole().toString().equals("SUT")).
				findFirst().get();
		for (Annotation a:sutComponent.getAnnotation()){
			if (a.getKey().getName().equals(MUTPATH)){
				String mutpath = a.getValue().substring(1, a.getValue().length()-1);
				if (modelUnderTestPath == null || !modelUnderTestPath.toString().equals(mutpath)) {
					modelUnderTestPath = Paths.get(mutpath);
					//when the modelUnderTestPath is set, find the workspace path
					findWorkspacePath();
				}
			}
			else if (a.getKey().getName().equals(DSLNAME)) {
				String dslName = a.getValue().substring(1, a.getValue().length()-1);
				if (DSLName == null || !DSLName.equals(dslName)) {
					DSLName = dslName;
					DSLPath = findDSLPath (DSLName);
				}	
			}
		}
	}
	
	public String findDSLNameOfTestCase(TestDescription testCase) {
		ComponentInstance sutComponent = testCase.getTestConfiguration().getComponentInstance().stream().
				filter(ci -> ci.getRole().toString().equals("SUT")).
				findFirst().get();
		Optional<Annotation> DSLNameAnnotationOp = sutComponent.getAnnotation().stream().
				filter(a -> a.getKey().getName().equals(DSLNAME)).findFirst();
		if (DSLNameAnnotationOp.isPresent()) {
			String dslName = DSLNameAnnotationOp.get().getValue();
			return dslName.substring(1, dslName.length()-1);
		}
		return null;
	}
	
	public Path findDSLPath (String DSLName){
		DSLProcessor dslProcessor = new DSLProcessor(DSLName);
		return dslProcessor.findDSLPath(DSLName);
	}
	
	private void findWorkspacePath() {
		IProject mutProject = getModelUnderTestProject();
		String path = mutProject.getLocation().toString();
		path = path.replace(mutProject.getLocation().lastSegment(), "");
		workspacePath = Paths.get(path);
	}
	
	public Path getWorkspacePath(Path path) {
		return getWorkspacePath(getProject(path));
	}
	
	public Path getWorkspacePath(String projectName) {
		return getWorkspacePath(getProject(projectName));
	}
	
	public Path getWorkspacePath(IProject project) {
		String projectPath = project.getLocation().toString();
		projectPath = projectPath.substring(0, projectPath.lastIndexOf(File.separator));
		return Paths.get(projectPath);
	}
	
	public Path getWorkspacePath() {
		return workspacePath;
	}
	
	public IProject getProject (Path path) {
		String projectName = path.subpath(0, 1).toString();
		return getProject(projectName);
	}
	
	public IProject getProject (String projectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	}
	
	public IProject getTestSuiteProject () {
		return getProject(testSuiteFilePath);
	}
	
	public IProject getModelUnderTestProject () {
		return getProject(modelUnderTestPath);
	}
	
	public IFile getTestSuiteFile() {
		return testSuiteFile;
	}

	public Resource getTestSuiteResource() {
		return testSuiteResource;
	}
	
	public Path getTestSuiteFilePath() {
		return testSuiteFilePath;
	}

	public String getTestSuiteProjectName() {
		return testSuiteURI.segment(1);
	}

	public String getTestSuiteFileName() {
		return testSuiteFileName;
	}
	
	public Package getTestSuite() {
		return testSuite;
	}

	public Path getModelUnderTestPath() {
		return modelUnderTestPath;
	}

	public void setModelUnderTestPath(Path modelUnderTestPath) {
		this.modelUnderTestPath = modelUnderTestPath;
		//when the modelUnderTestPath is set, find the workspace path
		findWorkspacePath();
	}
	
	public Resource getMUTResource() {
		if (MUTResource == null && modelUnderTestPath != null) {
			MUTResource = getMUTResource(modelUnderTestPath);
		}
		return MUTResource;
	}
	
	public Resource getMUTResource(Path modelUnderTestPath) {
		String path = modelUnderTestPath.toString();
		return (new ResourceSetImpl()).getResource(URI.createPlatformResourceURI(path,false), true);
	}
	
	public Path getPath (String path) {
		if (path.startsWith("platform:")) {
			URI uri = URI.createURI(path);
			return getPath(uri);
		}
		return Paths.get(path);
	}
	
	public Path getPath (URI uri) {
		String path = File.separator;
		//segment[0] is "resource" or "plugin"
		for (int i=1; i<uri.segmentCount(); i++) {
			path += uri.segment(i);
			if (i < uri.segmentCount()-1) {
				path += File.separator;
			}
		}
		return Paths.get(path);
	}
	
	public String getDSLName() {
		return DSLName;
	}

	public Path getDSLPath() {
		return DSLPath;
	}
}
