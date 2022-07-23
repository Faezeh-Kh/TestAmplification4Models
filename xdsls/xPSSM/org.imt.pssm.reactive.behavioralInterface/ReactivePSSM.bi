BehavioralInterface ReactivePSSM
	accepted event run:
		parameters = [state_machine: StateMachine]
	accepted event signal_received:
		parameters = [state_machine: StateMachine, signal_occurrence: SignalEventOccurrence]
	accepted event callOperation_received:
		parameters = [state_machine: StateMachine, call_occurrence: CallEventOccurrence]
	exposed event behavior_executed:
		parameters = [behavior: Behavior]
		
		