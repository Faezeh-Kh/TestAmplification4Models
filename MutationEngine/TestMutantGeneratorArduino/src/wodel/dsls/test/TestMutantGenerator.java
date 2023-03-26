package wodel.dsls.test;

import wodel.dsls.WodelUtils;

import java.io.IOException;

import wodel.utils.exceptions.MetaModelNotFoundException;
import mutatorenvironment.MutatorEnvironment;
import mutatorenvironment.MutatorenvironmentPackage;

public class TestMutantGenerator {
	public static void main(String[] args) {

		MutatorenvironmentPackage.eINSTANCE.getClass();
		try {
			//WodelUtils.generateMutationOperators("c:/GemocStudio/workspace2/wodelarduino/data/model/arduino.ecore", "d:/arduino/models", "c:/GemocStudio/workspace2/wodelarduino");
			MutatorEnvironment wodel = WodelUtils.generateWodelProgram("c:/GemocStudio/workspace2/wodelarduino/data/model/arduino.ecore");
			WodelUtils.generateCreationMutationOperators(wodel, "d:/arduino/models", "Module");
			WodelUtils.generateCloningMutationOperators(wodel, "d:/arduino/models", "If", "While");
			WodelUtils.generateModificationMutationOperators(wodel, "d:/arduino/models", "ModuleAssignment", "Expression");
			//WodelUtils.generateRemovalMutationOperators(wodel, "d:/arduino/models");
			WodelUtils.generateRetypingMutationOperators(wodel, "d:/arduino/models", "DigitalPin");
			WodelUtils.serializeWodelProgram(wodel, "c:/GemocStudio/workspace2/wodelarduino");
		} catch (MetaModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		WodelUtils.compileWodelProject("c:/GemocStudio/workspace2/wodelarduino", "c:/GemocStudio", "GemocStudioc");

		String currentPath = null;
		try {
			currentPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		WodelUtils.generateMutants("d:/arduino/models", "d:/arduino/mutants", currentPath, "c:/GemocStudio/workspace2/wodelarduino", "c:/GemocStudio");
	}
}
