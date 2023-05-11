package org.imt.tdl.testResult.persistence;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gemoc.commons.eclipse.emf.URIHelper;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionContext;
import org.eclipse.gemoc.xdsmlframework.api.core.IExecutionEngine;
import org.eclipse.gemoc.xdsmlframework.api.engine_addon.IEngineAddon;
import org.etsi.mts.tdl.Behaviour;
import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.Message;
import org.etsi.mts.tdl.Package;
import org.etsi.mts.tdl.TestDescription;
import org.imt.tdl.TDLTestReport.TDLTestReportFactory;
import org.imt.tdl.TDLTestReport.TestCaseResult;
import org.imt.tdl.TDLTestReport.TestMessageResult;
import org.imt.tdl.TDLTestReport.TestSuiteResult;
import org.imt.tdl.TDLTestReport.Verdicts;
import org.imt.tdl.testResult.TDLMessageResult;
import org.imt.tdl.testResult.TDLTestCaseResult;
import org.imt.tdl.testResult.TDLTestResultUtil;

public class TestReportPersistence implements IEngineAddon{
	
	static String reportFolderName = "test-report";
	IPath path2reportsFolder;
	
	@Override
	public void engineStopped(IExecutionEngine<?> engine) {
		if (TDLTestResultUtil.getInstance().getTestSuiteResult() == null) {
		   System.out.println("There is no test execution result to be saved. The test execution is interrupted due to some errors.");
	    }
		else {
			saveTestReport(engine.getExecutionContext());
		}
	}

	private void saveTestReport(IExecutionContext<?, ?, ?> _executionContext) {
		URI modelURI = _executionContext.getResourceModel().getURI();
		IPath testFilePath = new Path(URIHelper.removePlatformScheme(modelURI));
		IPath _projectPath = testFilePath.removeLastSegments(testFilePath.segmentCount() - 1);
		IPath _executionTopParentPath = _projectPath.append(reportFolderName);		
		String folderNameWithTime = generateSpecificExecutionFolderName();
		path2reportsFolder = _executionTopParentPath.append(folderNameWithTime);

		try {
			createExecutionFolders();
			copyFileTo(testFilePath, path2reportsFolder);
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		URI copiedTestSuiteURI = URI.createPlatformResourceURI(path2reportsFolder.toString(), false)
				.appendSegment(testFilePath.lastSegment());
		Resource testSuiteResource= (new ResourceSetImpl()).getResource(copiedTestSuiteURI, true);
		
	   //create test result according to the TDLTestReport.ecore structure
	   Package copiedTestSuite = (Package) testSuiteResource.getContents().get(0);
	   TestSuiteResult testSuiteResult = TDLTestReportFactory.eINSTANCE.createTestSuiteResult();
	   testSuiteResult.setTestSuite(copiedTestSuite);
	   for (TDLTestCaseResult tcResultObject : TDLTestResultUtil.getInstance().getTestSuiteResult().getTestCaseResults()) {
		   String testCaseName = tcResultObject.getTestCaseName();
		   Optional<TestDescription> optionalTC = copiedTestSuite.getPackagedElement().stream()
				   .filter(p -> p instanceof TestDescription)
				   .map(t -> (TestDescription) t)
				   .filter(t -> t.getName().equals(testCaseName)).findFirst();
		   if (optionalTC.isPresent()) {
			   TestDescription copiedTestCase = optionalTC.get();
			   TestCaseResult testCaseResult = TDLTestReportFactory.eINSTANCE.createTestCaseResult();
			   testCaseResult.setTestCase(copiedTestCase);
			   String testCaseVerdict = tcResultObject.getValue();
			   if (testCaseVerdict == TDLTestResultUtil.PASS) {
				   testCaseResult.setValue(Verdicts.PASS);
			   }else if (testCaseVerdict == TDLTestResultUtil.FAIL) {
				   testCaseResult.setValue(Verdicts.FAIL);
			   }else if (testCaseVerdict == TDLTestResultUtil.INCONCLUSIVE) {
				   testCaseResult.setValue(Verdicts.INCONCLUSIVE);
			   }
			   testCaseResult.setDescription(tcResultObject.getDescription());
			   for (int i=0; i<tcResultObject.getTdlMessages().size(); i++) {
				   TDLMessageResult messageResultObject = tcResultObject.getTdlMessages().get(i);
				   TestMessageResult testMessageResult = TDLTestReportFactory.eINSTANCE.createTestMessageResult();
				   testMessageResult.setMessage(findEquivalentTDLMessage(copiedTestCase, i));
				   String testMsgVerdict = messageResultObject.getValue();
				   if (testMsgVerdict == TDLTestResultUtil.PASS) {
					   testMessageResult.setValue(Verdicts.PASS);
				   }else if (testMsgVerdict == TDLTestResultUtil.FAIL) {
					   testMessageResult.setValue(Verdicts.FAIL);
				   }else if (testMsgVerdict == TDLTestResultUtil.INCONCLUSIVE) {
					   testMessageResult.setValue(Verdicts.INCONCLUSIVE);
				   }
				   testMessageResult.setDescription(messageResultObject.getDescription());
				   testMessageResult.setTdlMessageId(messageResultObject.getTdlMessageId());
				   testCaseResult.getTestMessageResults().add(testMessageResult);
			   }
			   testSuiteResult.getTestCaseResults().add(testCaseResult);
		   }
	   }
	   
	   //create a resource for the test result
	   URI testReportURI = URI.createPlatformResourceURI(path2reportsFolder.toString(), false)
				.appendSegment("testReport.xmi");
	   Resource testResultResource = (new ResourceSetImpl()).createResource(testReportURI);
	   testResultResource.getContents().add(testSuiteResult);
	   //saving resources
	   try {
			testResultResource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String generateSpecificExecutionFolderName() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return "/execution-" + timeStamp;
	}
		
	private void createExecutionFolders() throws CoreException {
		createFolder(path2reportsFolder.removeLastSegments(1));
		createFolder(path2reportsFolder);		
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

	private Message findEquivalentTDLMessage(TestDescription copiedTestCase, int index) {
		EList<Behaviour> behaviors =  ((CompoundBehaviour) copiedTestCase.getBehaviourDescription().getBehaviour()).getBlock().getBehaviour();
		Object[] messages = behaviors.stream().filter(b -> b instanceof Message).toArray();
		if (messages.length > index && messages[index] instanceof Message) {
			return (Message) messages[index];
		}
		return null;
	}
}
