package wodel.dsls.test;

import wodel.dsls.WodelUtils;

import java.io.IOException;

import wodel.utils.exceptions.MetaModelNotFoundException;
import mutatorenvironment.MutatorenvironmentPackage;

public class TestMutantGenerator {
	public static void main(String[] args) {
		
		MutatorenvironmentPackage.eINSTANCE.getClass();
		try {
			WodelUtils.generateMutationOperators("c:/GemocStudio/workspace2/wodelsm/data/model/statemachines.ecore", "d:/sm/empty", "c:/GemocStudio/workspace2/wodelsm");
		} catch (MetaModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		WodelUtils.compileWodelProject("c:/GemocStudio/workspace2/wodelsm", "c:/GemocStudio", "GemocStudioc");

		String currentPath = null;
		try {
			currentPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		WodelUtils.generateMutants("d:/sm/models", "d:/sm/mutants", currentPath, "c:/GemocStudio/workspace2/wodelsm", "c:/GemocStudio");
	}
}
