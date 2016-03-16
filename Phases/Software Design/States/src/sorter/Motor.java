package sorter;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Motor {
	private EV3MediumRegulatedMotor motor;
	private int defaultAngle;
	private final float calibrationSpeed = 100;
	private float safeSpeed;
	private float fastSpeed;
	private float speed;
	
	public Motor(Port port, int angle, int safeSpeed, int fastSpeed){
		motor = new EV3MediumRegulatedMotor(port);
		reset();
		defaultAngle = angle;
		this.safeSpeed = safeSpeed;
		this.fastSpeed = fastSpeed;
	}
	
	public void setSpeed(Mode m){
		if(m == Mode.FAST){
			speed = fastSpeed;
		}
		else {
			speed = safeSpeed;
		}
	}
	
	public void turnLeft(){
		motor.rotate(-defaultAngle, true);
	}
	
	public void turnRight(){
		motor.rotate(defaultAngle, true);
	}
	
	public boolean isMoving(){
		return motor.isMoving();
	}
	
	public boolean isStalled(){
		return motor.isStalled();
	}
	
	public void stop(){
		motor.stop();
	}
	
	public void slowForward(){
		motor.setSpeed(calibrationSpeed);
		motor.forward();
	}
	
	public void reset(){
		motor.resetTachoCount();
	}
}
