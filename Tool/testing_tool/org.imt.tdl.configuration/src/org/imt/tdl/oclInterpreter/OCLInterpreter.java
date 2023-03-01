package org.imt.tdl.oclInterpreter;

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
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import org.imt.tdl.testResult.TDLTestResultUtil;

public class OCLInterpreter {

	@SuppressWarnings("rawtypes")
	protected OCL ocl = null;
	@SuppressWarnings("rawtypes")
	protected OCLHelper oclHelper = null;

	protected OCLExpression<EClassifier> expression = null;
	protected Query<EClassifier, EClass, EObject> queryEval = null;
	
	private ArrayList<EObject> resultAsObject;
	private ArrayList<String> resultAsString;

	public OCLInterpreter() {
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		oclHelper = ocl.createOCLHelper();
	}

	@SuppressWarnings("unchecked")
	public String runQuery(EObject context, String query) {
		// The root element of the dsl is the context for ocl
		oclHelper.setContext(context.eClass());
		try {
			expression = oclHelper.createQuery(query);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAIL: Cannot create the ocl query";
		}
		queryEval = ocl.createQuery(expression);
		// the ocl query will be evaluated on the context element
		Object res = queryEval.evaluate(context);
		resultAsObject = new ArrayList<>();
		resultAsString = new ArrayList<>();
		if (res instanceof Collection<?>) {
			if (res instanceof LinkedHashSet<?>) {
				LinkedHashSet<?> queryResult =  (LinkedHashSet<?>) res;
				Iterator<?> it = queryResult.iterator();
				while (it.hasNext()) {
					EObject object = (EObject) it.next();
					resultAsObject.add(object);
					resultAsString.add(TDLTestResultUtil.getInstance().eObjectLabelProvider(object));
				}
			}else if (res instanceof ArrayList<?>) {
				ArrayList<?> queryResult =  (ArrayList<?>) res;
				for (int i = 0; i < queryResult.size(); i++) {
					if (queryResult.get(i)== null) {
						resultAsObject.add(null);
						resultAsString.add("null");
					}else {
						resultAsObject.add((EObject) queryResult.get(i));
						resultAsString.add("'" + queryResult.get(i).toString() + "'");
					}
				}
			}
		}else {
			if (res instanceof EObject) {
				EObject object = (EObject) res;
				resultAsObject.add(object);
				resultAsString.add(TDLTestResultUtil.getInstance().eObjectLabelProvider(object));
			}else if (res == null){
				resultAsObject.add(null);
				resultAsString.add("'" + "null" + "'");
			}else {//result is a primitive value
				resultAsObject.add(null);
				resultAsString.add("'" + res.toString() + "'");
			}
		}
		return "PASS: The ocl query evaluated successfully";
	}

	public ArrayList<String> getResultAsString(){
		return resultAsString;
	}
	public ArrayList<EObject> getResultAsObject() {
		return resultAsObject;
	}
	public void tearDown() throws Exception {
		oclHelper = null;
		ocl = null;
		expression = null;
		queryEval = null;
		Runtime.getRuntime().gc();
	}
}
