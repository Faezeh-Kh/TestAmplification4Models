package org.imt.tdl.coverage.persistence;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gemoc.commons.eclipse.emf.URIHelper;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.etsi.mts.tdl.PackageableElement;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.coverage.computation.TDLCoverageUtil;
import org.imt.tdl.coverage.computation.TDLTestCaseCoverage;
import org.imt.tdl.coverage.computation.TDLTestSuiteCoverage;
import org.imt.tdl.coverage.report.TestCoverageReport;

import TestCoverage.CoverageStatus;
import TestCoverage.ModelObjectCoverageStatus;
import TestCoverage.TestCaseCoverage;
import TestCoverage.TestCoverageFactory;
import TestCoverage.TestSuiteCoverage;

public class TestCoveragePersistence implements IEngineAddon{
	
	static String coverageFolderName = "test-coverage";
	IPath path2coverageFolder;
	Resource testSuiteResource;
	Resource MUTResource;
	private List<EObject> modelObjects = new ArrayList<>();
	
	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		if (TDLCoverageUtil.getInstance().getTestSuiteCoverage() == null) {
		   System.out.println("There is no test coverage result to be saved. The test execution is interrupted due to some errors.");
	    }
		else {
			if (!TDLCoverageUtil.getInstance().getTestSuiteCoverage().isCoverageComputed()) {
				TDLCoverageUtil.getInstance().runCoverageComputation();
			}
			saveTestCoverageResult(engine.getExecutionContext());
		}
	}
		
	private void saveTestCoverageResult(IExecutionContext<?, ?, ?> _executionContext) {
		URI modelURI = _executionContext.getResourceModel().getURI();
		IPath testFilePath = new Path(URIHelper.removePlatformScheme(modelURI));
		IPath _projectPath = testFilePath.removeLastSegments(testFilePath.segmentCount() - 1);
		IPath _executionTopParentPath = _projectPath.append(coverageFolderName);		
		String folderNameWithTime = generateSpecificExecutionFolderName();
		path2coverageFolder = _executionTopParentPath.append(folderNameWithTime);

		try {
			createExecutionFolders();
			copyFileTo(testFilePath, path2coverageFolder);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		URI copiedTestSuiteURI = URI.createPlatformResourceURI(path2coverageFolder.toString(), false)
				.appendSegment(testFilePath.lastSegment());
		testSuiteResource= (new ResourceSetImpl()).getResource(copiedTestSuiteURI, true);

		TDLCoverageUtil.getInstance().getTestSuiteCoverage().getCoverageReports()
			.forEach(r -> saveCoverageReport(r));
	}

	private String generateSpecificExecutionFolderName() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return "/execution-" + timeStamp;
	}
		
	private void createExecutionFolders() throws CoreException {
		createFolder(path2coverageFolder.removeLastSegments(1));
		createFolder(path2coverageFolder);		
	}

	private void createFolder(IPath folderPath) throws CoreException {
		IFolder folder = ResourcesPlugin.getWorkspace().getRoot().getFolder(folderPath);
		if (!folder.exists()) {
			folder.create(true, true, null);
		}
	}
	
	public void copyFileTo(IPath sourceFilePath, IPath destinationFolderPath) throws CoreException 
	{
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(sourceFilePath);
		IPath destinationFilePath = destinationFolderPath.append(sourceFilePath.lastSegment());
		file.copy(destinationFilePath, true, null);
	}

	private void saveCoverageReport(TestCoverageReport report) {
		TestSuiteCoverage tsCoverage = createTestSuiteObjectCoverage(report);
		//create a resource for the test coverage
		URI tsCoverageURI = URI.createPlatformResourceURI(path2coverageFolder.toString(), false).
				appendSegment(report.getReportTitle() + ".xmi");
		Resource tsCoverageResource = (new ResourceSetImpl()).createResource(tsCoverageURI);
		tsCoverageResource.getContents().add(tsCoverage);
		//saving resources
		try {
			tsCoverageResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TestSuiteCoverage createTestSuiteObjectCoverage (TestCoverageReport tsReport) {
	   //create test coverage according to the TDLTestCoverage.ecore structure
	   org.etsi.mts.tdl.Package copiedTestSuite = (org.etsi.mts.tdl.Package) testSuiteResource.getContents().get(0);
	   TDLTestSuiteCoverage tsCoveragObject = TDLCoverageUtil.getInstance().getTestSuiteCoverage();
	   TestSuiteCoverage testSuiteCoverage = TestCoverageFactory.eINSTANCE.createTestSuiteCoverage();
	   testSuiteCoverage.setTestSuite(copiedTestSuite);
	   testSuiteCoverage.setCoveragePercentage(tsReport.getCoveragePercentage());

	   for (TDLTestCaseCoverage tcCoverageObject : tsCoveragObject.getTcCoverages()) {
		   String testCaseName = tcCoverageObject.getTestCaseName();
		   Optional<PackageableElement> optionalTC = copiedTestSuite.getPackagedElement().stream().
				   filter(p -> p instanceof TestDescription).
				   filter(t -> t.getName().equals(testCaseName)).findFirst();
		   if (optionalTC.isEmpty()) { break; }
		   TestDescription copiedTestCase = (TestDescription) optionalTC.get();
		   TestCaseCoverage testCaseCoverage = TestCoverageFactory.eINSTANCE.createTestCaseCoverage();
		   testCaseCoverage.setTestCase(copiedTestCase);
		   TestCoverageReport tcReport = tcCoverageObject.getTcCoverageRepot(tsReport.getReportTitle());
		   testCaseCoverage.setCoveragePercentage(tcReport.getCoveragePercentage());
		   
		   copyMUTResource(tcCoverageObject.getMUTResource(), copiedTestCase.getName()+"_"+tcReport.getReportTitle());
		   for (int i=0; i<tcReport.getObjects().size(); i++) {
			   ModelObjectCoverageStatus tcModelObjectCoverageStatus = TestCoverageFactory.eINSTANCE.createModelObjectCoverageStatus();
			   EObject modelObject = tcReport.getObjects().get(i);
			   tcModelObjectCoverageStatus.setModelObject(getEObjectFromCopiedMUT(modelObject));
			   String tcCoverage = "";
			   tcCoverage = tcReport.getObjectCoverageStatus().get(i);
			   tcModelObjectCoverageStatus.setCoverageStatus(getCoverageStatus(tcCoverage));
			   if (tcReport.getParent_childrenReport() != null &&
					   tcReport.getParent_childrenReport().get(i) != null) {
				   //if there are related child objects with coverage status, save them too
				   TestCoverageReport childrenReport = tcReport.getParent_childrenReport().get(i);
				   for (int j=0; j < childrenReport.getObjects().size(); j++) {
					   ModelObjectCoverageStatus childModelObjectCoverageStatus = TestCoverageFactory.eINSTANCE.createModelObjectCoverageStatus();
					   EObject childObject = childrenReport.getObjects().get(j);
					   childModelObjectCoverageStatus.setModelObject(getEObjectFromCopiedMUT(childObject));
					   String tcChildCoverage = "";
					   tcChildCoverage = childrenReport.getObjectCoverageStatus().get(j);
					   childModelObjectCoverageStatus.setCoverageStatus(getCoverageStatus(tcChildCoverage));
					   tcModelObjectCoverageStatus.getRelatedObjectCoverageStatus().add(childModelObjectCoverageStatus);
				   }
			   }
			   testCaseCoverage.getTcObjectCoverageStatus().add(tcModelObjectCoverageStatus);
		   }
		   testSuiteCoverage.getTestCaseCoverages().add(testCaseCoverage);
	   }
	   
	   for (int i=0; i<tsReport.getObjects().size(); i++) {
		   ModelObjectCoverageStatus tsModelObjectCoverageStatus = TestCoverageFactory.eINSTANCE.createModelObjectCoverageStatus();
		   EObject modelObject = tsReport.getObjects().get(i);
		   tsModelObjectCoverageStatus.setModelObject(getEObjectFromCopiedMUT(modelObject));
		   String tsCoverage = "";
		   tsCoverage = tsReport.getObjectCoverageStatus().get(i);
		   tsModelObjectCoverageStatus.setCoverageStatus(getCoverageStatus(tsCoverage));
		   testSuiteCoverage.getTsObjectCoverageStatus().add(tsModelObjectCoverageStatus);
	   }
	   return testSuiteCoverage;
	}
	
	private CoverageStatus getCoverageStatus(String coverage) {
		if (coverage == TDLCoverageUtil.COVERED) {
			return CoverageStatus.COVERED;
		}
		else if (coverage == TDLCoverageUtil.NOT_COVERED) {
			return CoverageStatus.NOTCOVERED;
		}
		else if (coverage == TDLCoverageUtil.PARTLY_COVERED) {
			return CoverageStatus.PARTLYCOVERED;
		}
		else if (coverage == TDLCoverageUtil.NOSTATUS) {
			return CoverageStatus.NOSTATUS;
		}
		return null;
	}
	
	//save the model under test if it is not saved or if it is different from the current saved file
	private void copyMUTResource (Resource resource, String testID) {
		URI modelURI = URI.createPlatformResourceURI(path2coverageFolder.toString(), false)
				.appendSegment("testedModels");
		if (MUTResource == null) {
			modelURI = modelURI.appendSegment("mut.xmi");
		}
		//the test case uses a different model under test
		else if (!EcoreUtil.equals(MUTResource.getContents().get(0), resource.getContents().get(0))) {
			modelURI = modelURI.appendSegment("mut_" + testID + ".xmi");
		}
		//the model under test is already copied, so do nothing
		else {return;}
		
		MUTResource = (new ResourceSetImpl()).createResource(modelURI);
		MUTResource.getContents().addAll(EcoreUtil.copyAll(resource.getContents()));
	    try {
	    	MUTResource.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    TreeIterator<EObject> modelContents = MUTResource.getAllContents();
		while (modelContents.hasNext()) {
			modelObjects.add(modelContents.next());
		}
	}
	
	private EObject getEObjectFromCopiedMUT (EObject eobject) {
		EcoreUtil.resolveAll(eobject);
		if (modelObjects.stream().filter(o -> EcoreUtil.equals(o, eobject)).findFirst().isEmpty()) {
			//System.out.println("cannot find eobject " + eobject);
		}
		Optional<EObject> matchedEObject = modelObjects.stream().filter(o -> EcoreUtil.equals(o, eobject)).findFirst();
		if (matchedEObject.isPresent()) {
			return matchedEObject.get();
		}
		return null;
	}
}
