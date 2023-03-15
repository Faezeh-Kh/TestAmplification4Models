package wodel.dsls.test;

import wodel.dsls.WodelUtils;

import java.io.IOException;

import wodel.utils.exceptions.MetaModelNotFoundException;
import mutatorenvironment.MutatorenvironmentPackage;

public class TestMutantGenerator {
	public static void main(String[] args) {

		MutatorenvironmentPackage.eINSTANCE.getClass();
		try {
			WodelUtils.generateMutationOperators(new String[] {"c:/GemocStudio/workspace2/wodelarduino/data/model/arduino.ecore", "d:/arduino/models", "c:/GemocStudio/workspace2/wodelarduino"});
		} catch (MetaModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		WodelUtils.compileWodelProject(new String[] {"c:/GemocStudio/workspace2/wodelarduino", "c:/GemocStudio", "GemocStudioc"});
		
		WodelUtils.generateMutants(new String[]{"d:/arduino/models", "d:/arduino/mutants", "c:/GemocStudio/workspace2/wodelarduino", "c:/GemocStudio"});
	}
}
