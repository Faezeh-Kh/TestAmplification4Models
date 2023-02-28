package org.imt.tdl.mutation;
import java.io.IOException;

import exceptions.MetaModelNotFoundException;

/**
 * @author Pablo Gomez-Abajo - Utils for serialize and deserialize Wodel programs
 * 
 */
public class MutantGenerator {
	public static void main(String[] args) throws MetaModelNotFoundException, IOException {
		//args[0] = domainMetamodelPath, args[1] = inputPath, args[2] = outputPath, args[3] = wodelProjectPath,  args[4] = eclipseHomePath (, args[5] = exhaustive == true, optimized == false)?
		//eclipseHomePath: the path to the binary of eclipse e.g., c:/GemocStudio
		wodel.dsls.WodelUtils.main(args);
	}
}
