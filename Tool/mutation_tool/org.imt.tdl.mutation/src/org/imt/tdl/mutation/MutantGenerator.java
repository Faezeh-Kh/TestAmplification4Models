package org.imt.tdl.mutation;
import wodel.dsls.WodelUtils;

/**
 * @author Pablo Gomez-Abajo - Utils for serialize and deserialize Wodel programs
 * 
 */
public class MutantGenerator {
	public static void main(String[] args) {
		
		WodelUtils.generateMutants(new String[]{
				"c:/labtop/gemoc_studio/workspaces/xTDL_Amplification/mutationOperatorGenerator/models", 
				"c:/labtop/gemoc_studio/workspaces/xTDL_Amplification/mutationOperatorGenerator/mutants", 
				"c:/GemocStudio/workspace2/wodeleduapp", 
				"c:/GemocStudio"});
	}
}
