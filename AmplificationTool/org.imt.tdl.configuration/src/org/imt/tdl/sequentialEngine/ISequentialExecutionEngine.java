package org.imt.tdl.sequentialEngine;

public interface ISequentialExecutionEngine extends IExecutionEngine {
	
	public String executeModelSynchronous();
	public String executeModelAsynchronous();
	public String stopAsynchronousExecution();
	public void disposeResources();
}
