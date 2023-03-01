In the current version of TDL test amplification, we consider the following assumptions:
1. a TDL test case (i.e., TestDescription element) is a sequence of TDL Message elements exchanged between the reactiveGates
2. the test data exchanged by TDL Messages are event instances conforming to the behavioral interface of the xDSL

- Message.argument (dataInstanceUse.dataInstance) = event instance
- Message.argument.parameterBinding.parameter = event parameter
- Message.argument.parameterBinding.dataUse.dataInstance = EObject (value of the event parameter)

3. assertion generation is performed by analyzing the execution trace of the mutated tests ->
	it considers the xModel under test is correct and the newly generated tests are indeed useful for regression testing