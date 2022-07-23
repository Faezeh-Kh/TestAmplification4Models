BehavioralInterface ArduinoInterface
	accepted event run:
		parameters = [sketch: Sketch]
	accepted event button_pressed:
		parameters = [button: PushButton]
	accepted event button_released:
		parameters = [button: PushButton]
	accepted event IRSensor_detected:
		parameters = [sensor: InfraRedSensor]
	accepted event IRSensor_notDetected:
		parameters = [sensor: InfraRedSensor]
	accepted event soundSensor_detected:
		parameters = [sensor: SoundSensor]
	exposed event Pin_level_changed:
		parameters = [pin: Pin]
		
		
