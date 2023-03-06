package org.imt.tdl.mutation;
import java.io.IOException;

import exceptions.MetaModelNotFoundException;
import wodel.dsls.WodelUtils;

/**
 * @author Pablo Gomez-Abajo - Utils for serialize and deserialize Wodel programs
 * 
 */
public class MutantGenerator {
	public static void main(String[] args) {
		String wodelProjectPath = "c:/labtop/gemoc_studio/workspaces/xTDL_Amplification/ArduinoMutation";
		String metamodelPath = wodelProjectPath + "/data/model/arduino.ecore";
		String inputPath = "";
		String eclipseHomePath = "c:/labtop/gemoc_studio";
		String eclipseCompilerName = "GemocStudioc";
		String outputPath = wodelProjectPath + "/out/mutants";
		
		try {
			WodelUtils.generateMutationOperators(new String[] {metamodelPath, inputPath, wodelProjectPath});
			//WodelUtils.compileWodelProject(new String[] {wodelProjectPath, eclipseHomePath, eclipseCompilerName});
			//WodelUtils.generateMutants(new String[] {inputPath, outputPath, wodelProjectPath, eclipseHomePath});
		} catch (MetaModelNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
