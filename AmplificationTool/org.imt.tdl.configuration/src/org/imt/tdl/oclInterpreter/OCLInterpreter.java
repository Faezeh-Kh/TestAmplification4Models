package org.imt.tdl.oclInterpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
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

	public void setUp() {
		this.ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
		this.oclHelper = ocl.createOCLHelper();
	}

	@SuppressWarnings("unchecked")
	public String runQuery(Resource MUTResource, String query) {
		// The root element of the dsl is the context for ocl
		this.oclHelper.setContext(MUTResource.getContents().get(0).eClass());
		try {
			this.expression = this.oclHelper.createQuery(query);
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAIL: Cannot create the ocl query";
		}
		this.queryEval = this.ocl.createQuery(this.expression);
		// the ocl query will be evaluated on the root element of MUT
		Object res = this.queryEval.evaluate(MUTResource.getContents().get(0));
		resultAsObject = new ArrayList<>();
		resultAsString = new ArrayList<>();
		if (res instanceof Collection<?>) {
			if (res instanceof LinkedHashSet<?>) {
				LinkedHashSet<?> queryResult =  (LinkedHashSet<?>) res;
				Iterator<?> it = queryResult.iterator();
				while (it.hasNext()) {
					EObject object = (EObject) it.next();
					this.resultAsObject.add(object);
					this.resultAsString.add(TDLTestResultUtil.getInstance().eObjectLabelProvider(object));
				}
			}else if (res instanceof ArrayList<?>) {
				ArrayList<?> queryResult =  (ArrayList<?>) res;
				for (int i = 0; i < queryResult.size(); i++) {
					if (queryResult.get(i)== null) {
						this.resultAsObject.add(null);
						this.resultAsString.add("null");
					}else {
						this.resultAsObject.add((EObject) queryResult.get(i));
						this.resultAsString.add("'" + queryResult.get(i).toString() + "'");
					}
				}
			}
		}else {
			if (res instanceof EObject) {
				EObject object = (EObject) res;
				this.resultAsObject.add(object);
				this.resultAsString.add(TDLTestResultUtil.getInstance().eObjectLabelProvider(object));
			}else {
				this.resultAsObject.add(null);
				this.resultAsString.add("'" + "null" + "'");
			}
		}
		return "PASS: The ocl query evaluated successfully";
	}

	public ArrayList<String> getResultAsString(){
		return this.resultAsString;
	}
	public ArrayList<EObject> getResultAsObject() {
		return this.resultAsObject;
	}
	public void tearDown() throws Exception {
		this.oclHelper = null;
		this.ocl = null;
		this.expression = null;
		this.queryEval = null;
		Runtime.getRuntime().gc();
	}
}
