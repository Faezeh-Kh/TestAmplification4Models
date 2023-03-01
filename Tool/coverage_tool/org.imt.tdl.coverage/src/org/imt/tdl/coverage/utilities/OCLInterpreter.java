package org.imt.tdl.coverage.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

public class OCLInterpreter {

	@SuppressWarnings("rawtypes")
	protected OCL ocl = null;
	
	@SuppressWarnings("rawtypes")
	protected OCLHelper oclHelper = null;

	public OCLInterpreter() {
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		oclHelper = ocl.createOCLHelper();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<EObject> runQuery(EObject context, String query) {
		oclHelper.setContext(context.eClass());
		OCLExpression<EClassifier> expression = null;
		try {
			expression = oclHelper.createQuery(query);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(expression);
		// the ocl query will be evaluated on the context element
		Object res = queryEval.evaluate(context);
		ArrayList<EObject> resultAsObject = new ArrayList<>();
		if (res instanceof Collection<?>) {
			if (res instanceof LinkedHashSet<?>) {
				LinkedHashSet<?> queryResult =  (LinkedHashSet<?>) res;
				Iterator<?> it = queryResult.iterator();
				while (it.hasNext()) {
					EObject object = (EObject) it.next();
					resultAsObject.add(object);
				}
			}else if (res instanceof ArrayList<?>) {
				ArrayList<?> queryResult =  (ArrayList<?>) res;
				if (queryResult.isEmpty()) {
					resultAsObject.add(null);
				}
				else {
					queryResult.stream().forEach(object -> resultAsObject.add((EObject) object));
				}
			}
		}else {
			if (res instanceof EObject) {
				resultAsObject.add((EObject) res);
			}else {//result is a primitive value or null
				resultAsObject.add(null);
			}
		}
		return resultAsObject;
	}

	public boolean isConstraintSatisfied(EObject context, String constraint) {
		oclHelper.setContext(context.eClass());
		Constraint invariant = null;
		try {
			invariant = (Constraint) oclHelper.createInvariant(constraint);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(invariant);
		// the ocl query will be evaluated on the context element
		return queryEval.check(context);
	}
}
