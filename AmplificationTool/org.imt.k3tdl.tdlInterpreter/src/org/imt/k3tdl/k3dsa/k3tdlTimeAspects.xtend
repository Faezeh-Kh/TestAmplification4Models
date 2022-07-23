package org.imt.k3tdl.k3dsa

import fr.inria.diverse.k3.al.annotationprocessor.Aspect
import org.etsi.mts.tdl.TimeConstraint
import org.etsi.mts.tdl.TimeLabel
import org.etsi.mts.tdl.Timer

@Aspect (className = TimeLabel)
class TimeLabelAspect{
	def void op(){
		
	}
}

@Aspect (className = TimeConstraint)
class TimeConstraintAspect{
	def void op(){
		
	}
}
@Aspect (className = Timer)
class TimerAspect{
	def void op(){
		
	}
}