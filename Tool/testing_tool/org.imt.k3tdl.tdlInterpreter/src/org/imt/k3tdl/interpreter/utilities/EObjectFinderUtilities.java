package org.imt.k3tdl.interpreter.utilities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class EObjectFinderUtilities {
	
	private static EObjectFinderUtilities instance = new EObjectFinderUtilities();
	public List<EObject> matchedEObjectsByContainer = new ArrayList<>();
			
	public static EObjectFinderUtilities getInstance() {
		return instance;
	}

	public EObject getInitialMatchedEObject(EObject object) {
		if (matchedEObjectsByContainer.isEmpty()) {
			return null;
		}
		if (matchedEObjectsByContainer.size()==1) {
			return matchedEObjectsByContainer.get(0);
		}
		EObject containerObject = object;
		if (object == null) {
			int lastIndex = matchedEObjectsByContainer.size()-1;
			containerObject = matchedEObjectsByContainer.get(lastIndex);
			matchedEObjectsByContainer.remove(lastIndex);
		}
		while (true) {
			List<EObject> containeeEObjects = new ArrayList<>();
			for (EObject o : matchedEObjectsByContainer) {
				EObject containeeObject = o;
				while (containeeObject.eContainer()!=null) {
					if (containeeObject.eContainer() == containerObject) {
						containeeEObjects.add(o);
						break;
					}else {
						containeeObject = containeeObject.eContainer();
					}
				}
			}
			if (containeeEObjects.isEmpty()) {
				return containerObject;
			}
			containerObject = containeeEObjects.get(0);
		}
		
	}
}
