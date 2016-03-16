package sorter;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import states.AbortState;
import states.ModeSelectionState;
import states.State;
import error.AbortButtonError;

public class Main {
	private final Port motorPort = 			MotorPort.A;
	private final Port colorSensorPort = 	SensorPort.S1;
	private final Port gyroSensorPort = 	SensorPort.S2;
	private final Port touchSensorPort = 	SensorPort.S3;
	
	private final int motorTurnStep = 		120;
	private final int motorSafeSpeed = 		3*motorTurnStep;
	private final int motorFastSpeed = 		700;
	private final int gyroStableThreshold = 15;
	
	public final Key spButton = Button.ENTER;
	public final Key aButton = Button.ESCAPE;
	public final Key rButton = Button.DOWN;
	
	public final Motor motor;
	public final ColorSensor colorSensor;
	public final GyroSensor gyroSensor;
	public final TouchSensor touchSensor;
	public final Statistics stats;
	public final Timer timer = new Timer();
	
	private boolean paused = true;
	private boolean reset = false;
	private Mode mode;
	
	private long tavg = 100000;	//average time for disc to fall
	private long tdmax = 100000;	//maximum allowed time for disc to fall
	private long tgmax = 100000;	//maximum allowed time for gyro to stabilize
	
	private State currentState;
	public Display display;
	
	float gyroAngle = Float.MAX_VALUE;
	
	public Main(){
		//TODO say hello
		Say.wtf();
		motor = new Motor(motorPort, motorTurnStep, motorSafeSpeed, motorFastSpeed);
		colorSensor = new ColorSensor(colorSensorPort);
		gyroSensor = new GyroSensor(gyroSensorPort, gyroStableThreshold);
		touchSensor = new TouchSensor(touchSensorPort);
		
		display = new Display();
		stats = new Statistics();
		
		currentState = new ModeSelectionState(this);
		setMode(Mode.FAST);
	}
	
	private void run(){
		while(true){
			/*
			float angle = gyroSensor.getAngle();
			if(angle != gyroAngle){
				gyroAngle = angle;
				System.out.println("Angle: "+gyroAngle);
			}*/
			if(aButton.isDown() && !(currentState instanceof AbortState)){
				currentState = new AbortState(new AbortButtonError(), this);
			} 
			else if(spButton.isDown()){
				paused = true;
			}
			else if(rButton.isDown()){
				reset = true;
			}
			currentState.displayUpdate(this);
			State newState = currentState.nextState(this);
			if(newState != currentState){
				//System.out.println("State: "+newState.getClass().getSimpleName());
			}
			currentState = newState;
		}
	}
	
	public void variableReset(){
		this.reset = false;
		this.paused = false;
		this.stats.reset();
	}
	
	public boolean isPaused(){
		return this.paused;
	}
	
	public boolean isReset(){
		return this.reset;
	}
	
	public void setReset(boolean reset){
		this.reset = reset;
	}
	
	public void setPaused(boolean paused){
		this.paused = paused;
	}
	
	public static void main(String[] args){
		Main m = new Main();
		m.run();
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
	public long getTAvg(){
		return this.tavg;
	}
	
	public long getTDMax(){
		return this.tdmax;
	}
	
	public long getTGMax(){
		return this.tgmax;
	}
}
