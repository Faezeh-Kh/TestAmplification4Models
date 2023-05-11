package org.imt.tdl.testResult.ui;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.presentation.EcoreEditor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
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
import org.imt.tdl.testResult.TDLMessageResult;
import org.imt.tdl.testResult.TDLTestCaseResult;
import org.imt.tdl.testResult.TDLTestResultUtil;
import org.imt.tdl.testResult.TDLTestSuiteResult;

public class TDLTestResultsView extends ViewPart{
	
	public static final String ID = "test.views.TDLTestResultsTestViewPart"; //$NON-NLS-1$
	
	private TreeViewer m_treeViewer;
	
	private static final Color RED = new Color(Display.getCurrent(), 255, 102, 102);

	private static final Color GREEN = new Color(Display.getCurrent(), 102, 255, 102);
	
	private static final Color YELLOW = new Color(Display.getCurrent(), 255, 255, 102);

	
	private static int filterIndex = -1;
	
	@Override
	public void createPartControl(Composite parent) {
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
		gd.verticalAlignment = SWT.ON_TOP;
		gd.widthHint = 150;
	    filter.setLayoutData(gd);
	    
        final Combo filterCombo = new Combo(filter, SWT.NONE);
        filterCombo.add("All");
        filterCombo.add("Passed");
        filterCombo.add("Failed");
        filterCombo.add("Inconclusive");
		filterCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filterIndex = filterCombo.getSelectionIndex();
				m_treeViewer.collapseAll();
				m_treeViewer.refresh();
			}
		});
		
		Group testVerdict = new Group(contents, SWT.NULL);
		FillLayout fill = new FillLayout(SWT.VERTICAL);
		testVerdict.setLayout(fill);
		testVerdict.setText("Test Execution Results");
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		gd.horizontalAlignment = SWT.FILL;
		gd.verticalAlignment = SWT.FILL;
		testVerdict.setLayoutData(gd);
		
	    final Tree addressTree = new Tree(testVerdict, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		addressTree.setHeaderVisible(true);
		addressTree.setLinesVisible(true);
		final StyledText detailTextBox = new StyledText(testVerdict, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION| SWT.WRAP);
		//show the test result description when mouse down
		addressTree.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Point point = new Point(event.x, event.y);
				TreeItem item = addressTree.getItem(point);
				if (item == null || item.getData() == null) {
					//do nothing
				}
				else if (item.getData() instanceof TDLTestCaseResult) {
					TDLTestCaseResult verdict = (TDLTestCaseResult) item.getData();
					if (verdict.getDescription() != null) {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							public void run() {
								detailTextBox.setText(verdict.getDescription());
							}
						});
					}
				}else if (item.getData() instanceof TDLMessageResult) {
					TDLMessageResult verdict = (TDLMessageResult) item.getData();
					if (verdict.getDescription() != null) {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							public void run() {
								detailTextBox.setText(verdict.getDescription());
							}
						});
					}
				}
			}
		});
		//show the test message/test case/test suite when double click
		addressTree.addListener(SWT.MouseDoubleClick, new Listener() {
			@Override
			public void handleEvent(Event event) {
				Point point = new Point(event.x, event.y);
				TreeItem item = addressTree.getItem(point);
				EObject eobjectToOpen = null;
				if (item == null || item.getData() == null) {
					//do nothing
				}
				else if (item.getData() instanceof TDLTestSuiteResult) {
					eobjectToOpen = ((TDLTestSuiteResult) item.getData()).getTestSuite();
				}
				else if (item.getData() instanceof TDLTestCaseResult) {
					eobjectToOpen = ((TDLTestCaseResult) item.getData()).getTestCase();	
					
				}else if (item.getData() instanceof TDLMessageResult) {
					eobjectToOpen = ((TDLMessageResult) item.getData()).getMessage();
				}
				if (eobjectToOpen != null) {
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

		TreeColumn column1 = new TreeColumn(addressTree, SWT.LEFT);
		column1.setAlignment(SWT.LEFT);
		column1.setText("Test case");
		column1.setWidth(200);
		
		TreeColumn column2 = new TreeColumn(addressTree, SWT.LEFT);
		column2.setAlignment(SWT.LEFT);
		column2.setText("Result");
		column2.setWidth(130);
		
		TreeColumn column3 = new TreeColumn(addressTree, SWT.LEFT);
		column3.setAlignment(SWT.LEFT);
		column3.setText("Description");
		column3.setWidth(600);
		m_treeViewer.setContentProvider(new TDLTestResultContentProvider());
		m_treeViewer.setLabelProvider(new TableLabelProvider());
		m_treeViewer.setInput(TDLTestResultUtil.getInstance().getTestSuiteResult());
		m_treeViewer.addFilter(new DataFilter());
		m_treeViewer.collapseAll();
	}
	
	private class TDLTestResultContentProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof List<?>) {
				return ((List<?>) parentElement).toArray();
			}
			if (parentElement instanceof TDLTestSuiteResult) {
				return ((TDLTestSuiteResult) parentElement).getTestCaseResults().toArray();
			}
			if (parentElement instanceof TDLTestCaseResult) {
				return  ((TDLTestCaseResult) parentElement).getTdlMessages().toArray();
			}
			return new Object[0]; 
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof String) {
				return (String) element;
			}
			if (element instanceof TDLTestSuiteResult) {
				return ((TDLTestSuiteResult) element).getTestSuiteName();
			}
			if (element instanceof TDLTestCaseResult) {
				return ((TDLTestCaseResult) element).getTestCaseName();
			}
			if (element instanceof TDLMessageResult) {
				return ((TDLMessageResult) element).getTdlMessageId();
			}
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof List<?>) {
				return ((List<?>) element).size() > 0;
			}
			if (element instanceof TDLTestSuiteResult) {
				return ((TDLTestSuiteResult) element).getTestCaseResults().size() > 0;
			}
			if (element instanceof TDLTestCaseResult) {
				return ((TDLTestCaseResult) element).getTdlMessages().size() > 0;
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
		
		private Color getBackground(Object element) {
			if (element instanceof TDLTestSuiteResult) {
				return ((TDLTestSuiteResult) element).getNumOfFailedTestCases() == 0 ? GREEN : RED;
			}
			if (element instanceof TDLTestCaseResult) {
				TDLTestCaseResult result = (TDLTestCaseResult) element;
				if (result.getValue() == TDLTestResultUtil.PASS) {
					return GREEN;
				}
				else if(result.getValue() == TDLTestResultUtil.INCONCLUSIVE) {
					return YELLOW;
				}
				else if(result.getValue() == TDLTestResultUtil.FAIL) {
					return RED;
				}
			}
			if (element instanceof TDLMessageResult) {
				TDLMessageResult result = (TDLMessageResult) element;
				if (result.getValue() == TDLTestResultUtil.PASS) {
					return GREEN;
				}
				else if (result.getFailure()) {
					return YELLOW;
				}
				else if (result.getValue() == TDLTestResultUtil.FAIL) {
					return RED;
				}
			}
			return null;
		}

		@Override
		public Color getBackground(Object element, int columnIndex) {
			return getBackground(element);
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
			if (element instanceof TDLTestSuiteResult) {
				TDLTestSuiteResult result = (TDLTestSuiteResult) element;
				switch(columnIndex) {
				case 0:
					columnText = result.getTestSuiteName();
					break;
				case 1:
					if (result.getNumOfFailedTestCases() == 0) {
						columnText = TDLTestResultUtil.PASS;
					}else if (result.getNumOfInconclusiveTestCases() > 0) {
						columnText = TDLTestResultUtil.INCONCLUSIVE;
					}else {
						columnText = TDLTestResultUtil.FAIL;
					}
					break;
				case 2:
					columnText = "";
					break;
				}
			}
			if (element instanceof TDLTestCaseResult) {
				TDLTestCaseResult result = (TDLTestCaseResult) element;
				switch (columnIndex) {
				case 0:
					columnText = result.getTestCaseName();
					break;
				case 1:
					columnText = result.getValue();
					break;
				case 2:
					columnText = result.getDescription();
					break;
				}
			}
			if (element instanceof TDLMessageResult) {
				TDLMessageResult result = (TDLMessageResult) element;
				switch (columnIndex) {
				case 0:
					columnText = result.getTdlMessageId();
					break;
				case 1:
					columnText = result.getValue();
					break;
				case 2:
					columnText = result.getDescription();
					break;
				}
			}
			return columnText; 
		}

	}
private class DataFilter extends ViewerFilter {
		
		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (filterIndex == -1 || filterIndex == 0) {
				return true;
			}
			if (filterIndex == 1) {
				if (element instanceof TDLTestSuiteResult) {
					return true;
				}
				if (element instanceof TDLTestCaseResult) {
					return ((TDLTestCaseResult) element).getValue() == TDLTestResultUtil.PASS;
				}
				if (element instanceof TDLMessageResult) {
					return ((TDLMessageResult) element).getValue() == TDLTestResultUtil.PASS;
				}
			}
			if (filterIndex == 2) {
				if (element instanceof TDLTestSuiteResult) {
					return true;
				}
				if (element instanceof TDLTestCaseResult) {
					return ((TDLTestCaseResult) element).getValue() == TDLTestResultUtil.FAIL;
				}
				if (element instanceof TDLMessageResult) {
					return ((TDLMessageResult) element).getValue() == TDLTestResultUtil.FAIL;
				}
			}
			if (filterIndex == 3) {
				if (element instanceof TDLTestSuiteResult) {
					return true;
				}
				if (element instanceof TDLTestCaseResult) {
					return ((TDLTestCaseResult) element).getValue() == TDLTestResultUtil.INCONCLUSIVE;
				}
				if (element instanceof TDLMessageResult) {
					if (parentElement instanceof TDLTestCaseResult) {
						return ((TDLTestCaseResult) parentElement).getValue() == TDLTestResultUtil.INCONCLUSIVE;
					}
					return false;
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
