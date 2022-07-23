package org.imt.tdl.amplification.ui;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ICoreRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.imt.tdl.amplification.TDLTestAmplifier;

public class CommandHandler implements IHandler {

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage().getSelection();
		if (!selection.isEmpty()) {
			if (selection instanceof TreeSelection) {
				Object element = ((TreeSelection) selection).getFirstElement();
				if (element instanceof IFile) {
					ICoreRunnable run = new ICoreRunnable() {
						@Override
						public void run(IProgressMonitor monitor) throws CoreException {
							try {
								TDLTestAmplifier amplifier = new TDLTestAmplifier();
								amplifier.amplifyTestSuite((IFile) element);
							} catch (Throwable t) {
								throw new CoreException(Status.error("Error when running test amplifier", t));
							}

						}
					};
					Job job = Job.create("Test Amplifier", run);
					job.schedule();
				}
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
	}

}
