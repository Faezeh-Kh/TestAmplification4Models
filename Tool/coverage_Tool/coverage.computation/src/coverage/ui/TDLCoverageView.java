package coverage.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.ui.editor.XtextEditor;

import coverage.computation.ObjectCoverageStatus;
import coverage.computation.TDLCoverageUtil;
import coverage.computation.TDLTestSuiteCoverage;

public class TDLCoverageView extends ViewPart{

	public static final String ID = "rt.ui.coverageView"; //$NON-NLS-1$
	
	private TreeViewer m_treeViewer;
	
	private static final Color RED = new Color(Display.getCurrent(), 255, 102, 102);

	private static final Color GREEN = new Color(Display.getCurrent(), 102, 255, 102);
	
	private static final Color YELLOW = new Color(Display.getCurrent(), 255, 255, 102);

	private static final Color GRAY = new Color(Display.getCurrent(), 237, 237, 237);
	
	private static int coverageFilterIndex = -1;
	private static int elementFilterIndex = -1;
	private static List<String> classFilters = new ArrayList<>();
	
	@Override
	public void createPartControl(Composite parent) {
		if (TDLCoverageUtil.getInstance().getTestSuiteCoverage().getTsCoveragePercentage() == 0) {
			TDLCoverageUtil.getInstance().runCoverageComputation();
		}
		Composite contents = new Group(parent, SWT.NULL);
	    GridLayout layout = new GridLayout();
	    layout.numColumns = 2;
		contents.setLayout(layout);
	    GridData gd = new GridData();
	    contents.setLayoutData(gd);
	    
	    Group filter = new Group(contents, SWT.NULL);
	    layout = new GridLayout();
	    filter.setLayout(layout);
		filter.setText("Filters");
		gd = new GridData();
		gd.verticalAlignment = SWT.FILL;
		filter.setLayoutData(gd);
		
		Group coverageFilter = new Group(filter, SWT.NULL);
	    layout = new GridLayout();
	    coverageFilter.setLayout(layout);
	    coverageFilter.setText("Coverage Filters");
		gd = new GridData();
		gd.verticalAlignment = SWT.ON_TOP;
		gd.widthHint = 250;
		coverageFilter.setLayoutData(gd);
        final Combo coverageFilterCombo = new Combo(coverageFilter, SWT.NONE);
        coverageFilterCombo.add("All");
        coverageFilterCombo.add("Covered");
        coverageFilterCombo.add("Not-Covered");
        coverageFilterCombo.add("Covered & Not-Covered");
        coverageFilterCombo.add("Not Coverable");
        coverageFilterCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				coverageFilterIndex = coverageFilterCombo.getSelectionIndex();
				m_treeViewer.collapseAll();
				m_treeViewer.refresh();
			}
		});
		
        Group elementFilter = new Group(filter, SWT.NULL);
	    layout = new GridLayout();
	    elementFilter.setLayout(layout);
	    elementFilter.setText("Type of Model Element");
		gd = new GridData();
		gd.verticalAlignment = SWT.ON_TOP;
		gd.widthHint = 250;
		elementFilter.setLayoutData(gd);
        final Combo elementFilterCombo = new Combo(elementFilter, SWT.NONE);
        elementFilterCombo.add("All");
        //add the meta-classes included in the coverage information as filter
        List<ObjectCoverageStatus> coverageInfos = TDLCoverageUtil.getInstance().getTestSuiteCoverage().getCoverageOfModelObjects();
        Set<EClass> metaClasses = new HashSet<EClass>();
        classFilters.clear();
        for (ObjectCoverageStatus cInfo: coverageInfos) {
        	metaClasses.add(cInfo.getMetaclass());      	
        }
        metaClasses.remove(null);
        for (EClass metaClass: metaClasses) {
        	classFilters.add(metaClass.getName());
        	elementFilterCombo.add(metaClass.getName());
        }
        elementFilterCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				elementFilterIndex = elementFilterCombo.getSelectionIndex();
				m_treeViewer.collapseAll();
				m_treeViewer.refresh();
			}
		});
		
		Group testCoverage = new Group(contents, SWT.NULL);
		FillLayout fill = new FillLayout(SWT.VERTICAL);
		testCoverage.setLayout(fill);
		testCoverage.setText("Coverage");
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		testCoverage.setLayoutData(gd);
		
	    final Tree addressTree = new Tree(testCoverage, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		addressTree.setHeaderVisible(true);
		addressTree.setLinesVisible(true);
		addressTree.addListener(SWT.MouseDoubleClick, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Point point = new Point(event.x, event.y);
				TreeItem item = addressTree.getItem(point);
				if (item == null || item.getData() == null) {
					//do nothing
				}
				else if (item.getData() instanceof ObjectCoverageStatus) {
					ObjectCoverageStatus objectCoverage = (ObjectCoverageStatus) item.getData();
					EObject eobjectToOpen = objectCoverage.getModelObject();		
					IFile fileToOpen = ResourcesPlugin.getWorkspace().getRoot().getFile(
							new Path(eobjectToOpen.eResource().getURI().toPlatformString(true)));
					IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().
							getDefaultEditor(fileToOpen.getName());
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						IEditorPart editor = page.openEditor(new FileEditorInput(fileToOpen), desc.getId());
						if (editor instanceof EcoreEditor) {
							TreeViewer tviewer = (TreeViewer) ((EcoreEditor) editor).getViewer();
							ResourceSet resSet =(ResourceSet) tviewer.getInput();
							EObject eobjectToOpen2 = resSet.getResources().get(0).getEObject(
									eobjectToOpen.eResource().getURIFragment(eobjectToOpen));
							tviewer.setSelection(new StructuredSelection(eobjectToOpen2));
						}else if (editor instanceof XtextEditor) {
							//TODO: how to reveal the object in the xtext editor
						}
						
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		m_treeViewer = new TreeViewer(addressTree);
		
		TreeColumn metaclassColumn = new TreeColumn(addressTree, SWT.LEFT);
		metaclassColumn.setAlignment(SWT.LEFT);
		metaclassColumn.setText("Meta-Class");
		metaclassColumn.setWidth(130);
		
		TreeColumn modelColumn = new TreeColumn(addressTree, SWT.LEFT);
		modelColumn.setAlignment(SWT.LEFT);
		modelColumn.setText("Model Element");
		modelColumn.setWidth(150);
		
		int colNum = TDLCoverageUtil.getInstance().getTestSuiteCoverage().getTCCoverages().size();
		for (int i=0; i<colNum; i++) {
			TreeColumn column = new TreeColumn(addressTree, SWT.LEFT);
			column.setAlignment(SWT.CENTER);
			column.setText("Test " + (i+1));
			column.setWidth(60);
		}
		
		TreeColumn tsColumn = new TreeColumn(addressTree, SWT.LEFT | SWT.BOLD);
		tsColumn.setAlignment(SWT.CENTER);
		tsColumn.setText("Test Suite");
		tsColumn.setWidth(100);
		
		m_treeViewer.setContentProvider(new TDLCoverageContentProvider());
		m_treeViewer.setLabelProvider(new TableLabelProvider());
		m_treeViewer.setInput(TDLCoverageUtil.getInstance().getTestSuiteCoverage());
		m_treeViewer.setFilters(new CoverageFilter(), new ElementFilter());
		m_treeViewer.collapseAll();
	}

	private class TDLCoverageContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof List<?>) {
				return ((List<?>) parentElement).toArray();
			}
			if (parentElement instanceof TDLTestSuiteCoverage) {
				return ((TDLTestSuiteCoverage) parentElement).getCoverageOfModelObjects().toArray();
			}
			return new Object[0]; 
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof String) {
				return (String) element;
			}
			if (element instanceof TDLTestSuiteCoverage) {
				return "Test Suite Coverage";
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof List<?>) {
				return ((List<?>) element).size() > 0;
			}
			if (element instanceof TDLTestSuiteCoverage) {
				return ((TDLTestSuiteCoverage) element).getCoverageOfModelObjects().size() > 0;
			}
			return false;
		}
		
	}
	
	class TableLabelProvider implements ITableLabelProvider, ITableColorProvider {
		@Override
		public void addListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isLabelProperty(Object element, String property) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void removeListener(ILabelProviderListener listener) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			if (element instanceof ObjectCoverageStatus) {
				switch(columnIndex) {
				case 0:
					//the column containing metaclasses
					return null;
				case 1:
					//the column containing model elements
					return null;
				default:
					String colText = ((ObjectCoverageStatus) element).getCoverage().get(columnIndex-2);
					if (colText == TDLCoverageUtil.COVERED) {
						return GREEN;
					}
					else if (colText == TDLCoverageUtil.NOT_TRACED) {
						return YELLOW;
					}
					else if (colText == TDLCoverageUtil.NOT_COVERED) {
						return RED;
					}
					else return GRAY;
				}
			}
			return null;
		}

		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
		@Override
		public String getColumnText(Object element, int columnIndex) {
			String columnText = "";
			if (element instanceof String) {
				switch (columnIndex) {
				case 0:
					columnText = (String) element;
					break;
				}
			}
			if (element instanceof ObjectCoverageStatus) {
				ObjectCoverageStatus cInfo = (ObjectCoverageStatus) element;
				switch(columnIndex) {
				case 0:
					if (cInfo.getMetaclass() != null) {
						columnText = cInfo.getMetaclass().getName();
					}
					break;
				case 1:
					if (cInfo.getModelObject() != null) {
						String metaclassName = cInfo.getMetaclass().getName();
						columnText = this.eObjectLabelProvider(cInfo.getModelObject()).replaceAll("\\s", "");
						columnText = columnText.substring(metaclassName.length());
					}
					break;
				default:
					columnText = cInfo.getCoverage().get(columnIndex-2);
					if (columnText == TDLCoverageUtil.COVERED) {
						columnText = "C";
					} 
					else if (columnText == TDLCoverageUtil.NOT_COVERED) {
						columnText = "NC";
					}
					else if (columnText == TDLCoverageUtil.NOT_TRACED) {
						columnText = "-";
					}
					break;
				}
			}
			return columnText; 
		}
		
		public String eObjectLabelProvider(EObject object) {
			final Class<?> IItemLabelProviderClass = IItemLabelProvider.class;
			ArrayList<AdapterFactory> factories = new ArrayList<AdapterFactory>();
			factories.add(new ResourceItemProviderAdapterFactory());
			factories.add(new EcoreItemProviderAdapterFactory());
			factories.add(new ReflectiveItemProviderAdapterFactory());
				
			ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory(factories);
			IItemLabelProvider itemLabelProvider;
		 	AdapterFactory adapterFactory = composedAdapterFactory;
		    	
		 	itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProviderClass);
			String objectLabel = itemLabelProvider.getText(object) ;
		        
			return (objectLabel);
		}

	}
private class CoverageFilter extends ViewerFilter {
		
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (coverageFilterIndex == -1 || coverageFilterIndex == 0) {
				return true;
			}
			if (element instanceof ObjectCoverageStatus) {
				ObjectCoverageStatus cInfo = (ObjectCoverageStatus) element;
				if (coverageFilterIndex == 1) {//covered elements
					//the last element of the coverage is related to the test suite
					return cInfo.getCoverage().get(cInfo.getCoverage().size()-1) == TDLCoverageUtil.COVERED;
				}
				else if (coverageFilterIndex == 2) {//not covered elements
					return cInfo.getCoverage().get(cInfo.getCoverage().size()-1) == TDLCoverageUtil.NOT_COVERED;
				}
				else if (coverageFilterIndex == 3) {//covered and not covered elements
					String coverage = cInfo.getCoverage().get(cInfo.getCoverage().size()-1);
					return  (coverage == TDLCoverageUtil.COVERED || coverage == TDLCoverageUtil.NOT_COVERED);
				}
				else if (coverageFilterIndex == 4) {//elements that are not coverable
					return cInfo.getCoverage().get(cInfo.getCoverage().size()-1) == TDLCoverageUtil.NOT_TRACED;
				}
			}
			return false;
		}
	}

private class ElementFilter extends ViewerFilter {
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (elementFilterIndex == -1 || elementFilterIndex == 0) {
			return true;
		}else {
			if (element instanceof ObjectCoverageStatus) {
				ObjectCoverageStatus cInfo = (ObjectCoverageStatus) element;
				if (cInfo.getMetaclass() == null) {
					return false;
				}else {
					String filter = classFilters.get(elementFilterIndex-1);
					String objectType = cInfo.getMetaclass().getName();
					if (objectType.equals(filter)) {
						filter = objectType;
						//true
					}
					return cInfo.getMetaclass().getName().equals(classFilters.get(elementFilterIndex-1));
				}
			}
		}
		return false;
	}
}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
