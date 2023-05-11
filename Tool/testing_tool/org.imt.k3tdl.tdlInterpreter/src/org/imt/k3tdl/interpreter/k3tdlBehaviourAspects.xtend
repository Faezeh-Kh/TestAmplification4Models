package org.imt.k3tdl.interpreter

import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod
import fr.inria.diverse.k3.al.annotationprocessor.Step
import org.etsi.mts.tdl.ActionBehaviour
import org.etsi.mts.tdl.ActionReference
import org.etsi.mts.tdl.AlternativeBehaviour
import org.etsi.mts.tdl.Assertion
import org.etsi.mts.tdl.Assignment
import org.etsi.mts.tdl.AtomicBehaviour
import org.etsi.mts.tdl.Behaviour
import org.etsi.mts.tdl.BehaviourDescription
import org.etsi.mts.tdl.Block
import org.etsi.mts.tdl.BoundedLoopBehaviour
import org.etsi.mts.tdl.Break
import org.etsi.mts.tdl.CombinedBehaviour
import org.etsi.mts.tdl.CompoundBehaviour
import org.etsi.mts.tdl.ConditionalBehaviour
import org.etsi.mts.tdl.DataInstanceUse
import org.etsi.mts.tdl.DefaultBehaviour
import org.etsi.mts.tdl.ExceptionalBehaviour
import org.etsi.mts.tdl.InlineAction
import org.etsi.mts.tdl.Interaction
import org.etsi.mts.tdl.InterruptBehaviour
import org.etsi.mts.tdl.LiteralValueUse
import org.etsi.mts.tdl.LocalExpression
import org.etsi.mts.tdl.Message
import org.etsi.mts.tdl.MultipleCombinedBehaviour
import org.etsi.mts.tdl.OptionalBehaviour
import org.etsi.mts.tdl.ParallelBehaviour
import org.etsi.mts.tdl.PeriodicBehaviour
import org.etsi.mts.tdl.ProcedureCall
import org.etsi.mts.tdl.Quiescence
import org.etsi.mts.tdl.SingleCombinedBehaviour
import org.etsi.mts.tdl.Stop
import org.etsi.mts.tdl.Target
import org.etsi.mts.tdl.TestDescriptionReference
import org.etsi.mts.tdl.TimeOperation
import org.etsi.mts.tdl.TimeOut
import org.etsi.mts.tdl.TimerOperation
import org.etsi.mts.tdl.TimerStart
import org.etsi.mts.tdl.TimerStop
import org.etsi.mts.tdl.UnboundedLoopBehaviour
import org.etsi.mts.tdl.VerdictAssignment
import org.etsi.mts.tdl.Wait
import org.imt.tdl.testResult.TDLMessageResult
import org.imt.tdl.testResult.TDLTestResultUtil

import static extension org.imt.k3tdl.interpreter.BehaviourAspect.*
import static extension org.imt.k3tdl.interpreter.BlockAspect.*
import static extension org.imt.k3tdl.interpreter.ExpressionAspect.*
import static extension org.imt.k3tdl.interpreter.GateInstanceAspect.*
import static extension org.imt.k3tdl.interpreter.TestDescriptionAspect.*

@Aspect (className = BehaviourDescription)
class BehaviourDescriptionAspect{

	def boolean callBehavior(){
		return _self.behaviour.performBehavior()
	}
}
@Aspect (className = Behaviour)
class BehaviourAspect{
	public Behaviour enabledBehaviour;

	def boolean performBehavior(){
		_self.enabledBehaviour = _self
		return false
	}
}
@Aspect (className = AtomicBehaviour)
class AtomicBehaviourAspect extends BehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = CombinedBehaviour)
class CombinedBehaviourAspect extends BehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = PeriodicBehaviour)
class PeriodicBehaviourAspect extends BehaviourAspect{
	@OverrideAspectMethod
	def boolean performBehavior(){
		return _self.block.traverseBlock()
	}
}
@Aspect (className = ExceptionalBehaviour)
class ExceptionalBehaviourAspect extends BehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = ActionBehaviour)
class ActionBehaviourAspect extends AtomicBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = VerdictAssignment)
class VerdictAssignmentAspect extends AtomicBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = Stop)
class StopAspect extends AtomicBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		_self.parentTestDescription.launcher.terminateExecution
		return true
	}
}
@Aspect (className = Break)
class BreakAspect extends AtomicBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = TestDescriptionReference)
class TestDescriptionReferenceAspect extends AtomicBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = Interaction)
class InteractoinAspect extends AtomicBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = TimerOperation)
class TimerOperationAspect extends AtomicBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = TimeOperation)
class TimeOperationAspect extends AtomicBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = Assertion)
class AssertionAspect extends ActionBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = Assignment)
class AssignmentAspect extends AtomicBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = InlineAction)
class InlineActionAspect extends ActionBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = ActionReference)
class ActionReferenceAspect extends ActionBehaviourAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = ProcedureCall)
class ProcedureCallAspect extends InteractoinAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = Message)
class MessageAspect extends InteractoinAspect{
	TDLMessageResult messageVerdict;
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		for (Target t: _self.target){
			if (_self.sourceGate.component.role.toString == "SUT"){
				//when the SUT component sends an argument, it is actually an assertion that have to be checked
				_self.sourceGate.gate.setLauncher(_self.parentTestDescription.launcher)
				var String verdict = _self.sourceGate.gate.assertArgument(_self.argument)
				_self.addMessageResult(verdict)
				return true //continue test case execution
			}else{//the argument has to be sent to the MUT
				t.targetGate.gate.setLauncher(_self.parentTestDescription.launcher)
				var String verdict
				verdict = t.targetGate.gate.sendArgument2sut(_self.argument)
				_self.addMessageResult(verdict)
				if (verdict.contains(TDLTestResultUtil.FAIL)){
					_self.parentTestDescription.testCaseResult.value = TDLTestResultUtil.INCONCLUSIVE//the test case should be interrupted
					return false//if the result is false, the test case execution should be interrupted
				}
				return true 
			}
		}	
	}
	def void addMessageResult(String info){
		var String result = ""
		if (info.contains(TDLTestResultUtil.FAIL)){
			result = TDLTestResultUtil.FAIL
			_self.parentTestDescription.testCaseResult.value = TDLTestResultUtil.FAIL
		}
		else if (info.contains(TDLTestResultUtil.INCONCLUSIVE)){
			result = TDLTestResultUtil.INCONCLUSIVE
			_self.parentTestDescription.testCaseResult.value = TDLTestResultUtil.INCONCLUSIVE
		}
		else if (info.contains(TDLTestResultUtil.PASS)){
			result = TDLTestResultUtil.PASS
		}
		var description = info
		if (info.contains(":")){
			description = info.substring(info.indexOf(":") + 2, info.length)
		}
		_self.messageVerdict = new TDLMessageResult(_self, result, description, null);
		_self.parentTestDescription.testCaseResult.addTdlMessage(_self.messageVerdict)
	}
}
@Aspect (className = TimerStart)
class TimerStartAspect extends TimerOperationAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = TimerStop)
class TimerStopAspect extends TimerOperationAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = TimeOut)
class TimeOutAspect extends TimerOperationAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = Wait)
class WaitAspect extends TimeOperationAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		if (_self.period instanceof LiteralValueUse){
			val delay = (_self.period as LiteralValueUse).value;
			val miliSec = Long.parseLong(delay.substring(1, delay.length-1));
			Thread.sleep(miliSec);
		}
		return true;
	}
}
@Aspect (className = Quiescence)
class QuiescenceAspect extends TimeOperationAspect{
	@Step
	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = SingleCombinedBehaviour)
class SingleCombinedBehaviourAspect extends CombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return _self.block.traverseBlock()
	}
}
@Aspect (className = MultipleCombinedBehaviour)
class MultipleCombinedBehaviourAspect extends CombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = BoundedLoopBehaviour)
class BoundedLoopBehaviourAspect extends SingleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		var boolean result = true
		for (i:0..<_self.numIteration.get(0).numIteration){
			result = _self.block.traverseBlock()
			if (!result){
				return false
			}
		}
		return true
	}
}
@Aspect (className = UnboundedLoopBehaviour)
class UnBoundedLoopBehaviourAspect extends SingleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		var boolean result = true
		if (_self.block.guard.size>0){
			val guard = _self.block.guard.get(0)
			while (guard.validateExpression){
				result = _self.block.traverseBlock()
				if (!result){
					return false
				}
			}
		}else{
			//it shall be executed an infinite number of times, unless it contains a 'Break' or a 'Stop'.
		}
		return true
	}
}
@Aspect (className = CompoundBehaviour)
class CompoundBehaviourAspect extends SingleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return _self.block.traverseBlock()
	}
}
@Aspect (className = OptionalBehaviour)
class OptionalBehaviourAspect extends SingleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return _self.block.traverseBlock()
	}
}
@Aspect (className = ConditionalBehaviour)
class ConditionalBehaviourAspect extends MultipleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = AlternativeBehaviour)
class AlternativeBehaviourAspect extends MultipleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		var result = true
		for (Block innerBlock : _self.block){
			for (Behaviour b :innerBlock.behaviour){
				_self.enabledBehaviour = b
				result = b.performBehavior()
				if (!result){
					return result
				}
			}
		}
	}
}
@Aspect (className = ParallelBehaviour)
class ParallelBehaviourAspect extends MultipleCombinedBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return false
	}
}
@Aspect (className = DefaultBehaviour)
class DefaultBehaviourAspect extends ExceptionalBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return _self.block.traverseBlock()
	}
}
@Aspect (className = InterruptBehaviour)
class InterruptBehaviourAspect extends ExceptionalBehaviourAspect{

	@OverrideAspectMethod
	def boolean performBehavior(){
		return _self.block.traverseBlock()
	}
}
@Aspect (className = Block)
class BlockAspect{

	def boolean traverseBlock(){
		var boolean canExecute = true
		if (_self.guard.size>0){
			try{
				for (i:0..<_self.guard.size){
					canExecute = _self.guard.get(i).validateExpression
					if (!canExecute){
						throw new InterruptedException()
					}
				}
			}catch (InterruptedException e) {
				//break the loop since one of the guards is not validated
				return false;
			}	
		}
		var result = true
		for (Behaviour b:_self.behaviour){
			result = b.performBehavior()
			if (!result){
				return false
			}
			if (b instanceof Stop){//don't execute the behaviors after executing Stop behavior as the test suite execution must be finished
				return true
			}
		}
		return true
	}
}
@Aspect (className = LocalExpression)
class ExpressionAspect{
	def boolean validateExpression(){
		if (_self.expression instanceof DataInstanceUse){
			if (_self.expression instanceof DataInstanceUse){
				val expression = (_self.expression as DataInstanceUse).dataInstance
				if (expression.dataType.name.equals("EBoolean")){
					return Boolean.parseBoolean(expression.name)
				}
				else{
					//TODO:Check for other types
					return false
				}
			} else if (_self.expression instanceof LiteralValueUse){
				val value = (_self.expression as LiteralValueUse).value;
				return Boolean.parseBoolean(value.substring(1, value.length-1));
			}
			else{
				//TODO:Check for other types
				return false
			}
		}
		else{
			//TODO:Check for other types
			return false
		}
	}
	def int getNumIteration(){
		if (_self.expression instanceof DataInstanceUse){
			val expression = (_self.expression as DataInstanceUse).dataInstance
			if (expression.dataType.name.equals("EInt")){
				return Integer.parseInt(expression.name)
			} else if (_self.expression instanceof LiteralValueUse){
				val value = (_self.expression as LiteralValueUse).value;
				return Integer.parseInt(value.substring(1, value.length-1));
			}else{
				//TODO:Check for other types
				return 0
			}
		}else if (_self.expression instanceof LiteralValueUse){
			val value = (_self.expression as LiteralValueUse).value;
			return Integer.parseInt(value.substring(1, value.length-1));
		}
		else{
			//TODO:Check for other types
			return 0
		}
	}
}