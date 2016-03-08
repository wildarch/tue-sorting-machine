package sorter;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Motor {
	private EV3MediumRegulatedMotor motor;
	private int defaultAngle;
	private float speed;
	
	public Motor(Port port, int angle, float speed){
		motor = new EV3MediumRegulatedMotor(port);
		motor.resetTachoCount();
		
		defaultAngle = angle;
		
		this.speed = speed;
		motor.setSpeed(speed);
	}
	
	public void turnLeft(){
		motor.rotate(defaultAngle, true);
	}
	
	public void turnRight(){
		motor.rotate(-defaultAngle, true);
	}
	
	public boolean isMoving(){
		return motor.isMoving();
	}
	
	public void stop(){
		motor.stop();
	}
}