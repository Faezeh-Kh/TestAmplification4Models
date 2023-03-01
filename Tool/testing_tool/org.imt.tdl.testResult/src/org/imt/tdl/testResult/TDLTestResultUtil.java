package org.imt.tdl.testResult;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

public class TDLTestResultUtil {
	
	private static TDLTestResultUtil instance = new TDLTestResultUtil();
	private TDLTestSuiteResult testSuiteResult;
	
	public static final String PASS = "PASS";
	public static final String FAIL = "FAIL";
	public static final String INCONCLUSIVE = "INCONCLUSIVE";

   //make the constructor private so that this class cannot be
   //instantiated
   private TDLTestResultUtil(){}

   //Get the only object available
   public static TDLTestResultUtil getInstance(){
      return instance;
   }
   public TDLTestSuiteResult getTestSuiteResult() {
	   return instance.testSuiteResult;
   }
   public void setTestSuiteResult(TDLTestSuiteResult result) {
	   instance.testSuiteResult = result;
   }
   
   public String eObjectLabelProvider(EObject object) {
		final Class<?> IItemLabelProviderClass = IItemLabelProvider.class;
		final Class<?> ITreeItemContentProviderClass = ITreeItemContentProvider.class;
		ArrayList<AdapterFactory> factories = new ArrayList<AdapterFactory>();
		factories.add(new ResourceItemProviderAdapterFactory());
		factories.add(new EcoreItemProviderAdapterFactory());
		factories.add(new ReflectiveItemProviderAdapterFactory());
		
		AdapterFactory adapterFactory = new ComposedAdapterFactory(factories); 
		
	    IItemLabelProvider objectLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProviderClass);
	    String objectLabel = objectLabelProvider.getText(object) ;
	    
	    ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider)adapterFactory.adapt(object, ITreeItemContentProviderClass);
	    Object container = treeItemContentProvider.getParent(object) ; 
	    IItemLabelProvider containerLabelProvider = (IItemLabelProvider)adapterFactory.adapt(container, IItemLabelProviderClass);
	    String containerLabel = "";
	    if (containerLabelProvider != null) {
	    	containerLabel = containerLabelProvider.getText(container);
	    }
	    String label = containerLabel + "::" + objectLabel + "(";
	    for (EAttribute attribute : object.eClass().getEAllAttributes()) {
	    	Object featureValue = object.eGet(attribute);
	    	label += featureValue != null ? 
	    			attribute.getName() + "=" + featureValue.toString() + ", " : "";
	    }
	    if (label.endsWith(", ")) {
	    	label = label.substring(0, label.length()-2);
	    }
	    return label + ")";
	}
   
   public String getDataAsString(List<?> list){
		String data = "[";
		for (int i=0; i<list.size(); i++){
			data += eObjectLabelProvider((EObject)list.get(i));
			if (i < list.size()-1){
				data += ", ";
			}
		}
		data += "]";
		return data;
	}
}
