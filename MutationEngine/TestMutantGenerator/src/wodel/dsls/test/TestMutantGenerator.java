package wodel.dsls.test;

import wodel.dsls.WodelUtils;

import java.io.IOException;

import wodel.utils.exceptions.MetaModelNotFoundException;
import mutatorenvironment.MutatorenvironmentPackage;

public class TestMutantGenerator {
	public static void main(String[] args) {
		
		MutatorenvironmentPackage.eINSTANCE.getClass();
		try {
			WodelUtils.generateMutationOperators("c:/GemocStudio/workspace2/wodeldfa/data/model/DFAAutomaton.ecore", "c:/GemocStudio/workspace3/TestMutantGenerator/input", "c:/GemocStudio/workspace2/wodeldfa");
		} catch (MetaModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		WodelUtils.compileWodelProject("c:/GemocStudio/workspace2/wodeldfa", "c:/GemocStudio", "GemocStudioc");
		
		String currentPath = null;
		try {
			currentPath = new java.io.File(".").getCanonicalPath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		//WodelUtils.generateMutants("d:/dfa/models", "d:/dfa/mutants", currentPath, "c:/GemocStudio/workspace2/wodeldfa", "c:/GemocStudio");
		WodelUtils.generateMutants("c:/GemocStudio/workspace3/TestMutantGenerator/input", "c:/GemocStudio/workspace3/TestMutantGenerator/output/mutants", currentPath, "c:/GemocStudio/workspace2/wodeldfa", "c:/GemocStudio");
	}
}
