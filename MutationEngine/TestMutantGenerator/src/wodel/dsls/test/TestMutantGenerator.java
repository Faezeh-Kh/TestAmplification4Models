package wodel.dsls.test;

import wodel.dsls.WodelUtils;

import java.io.IOException;

import exceptions.MetaModelNotFoundException;
import mutatorenvironment.MutatorenvironmentPackage;

public class TestMutantGenerator {
	public static void main(String[] args) {
		
		MutatorenvironmentPackage.eINSTANCE.getClass();
		/*
		try {
			WodelUtils.generateMutationOperators(new String[] {"c:/GemocStudio/workspace2/wodeleduapp/data/model/DFAAutomaton.ecore", "d:/dfa/models", "c:/GemocStudio/workspace2/wodeleduapp"});
		} catch (MetaModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		*/
		//WodelUtils.compileWodelProject(new String[] {"c:/GemocStudio/workspace2/wodeleduapp", "c:/GemocStudio", "GemocStudioc"});
		
		WodelUtils.generateMutants(new String[]{"d:/dfa/models", "d:/dfa/mutants", "c:/GemocStudio/workspace2/wodeleduapp", "c:/GemocStudio"});
	}
}
