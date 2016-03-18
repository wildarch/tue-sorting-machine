package sorter;
import peripherals.ColorSensor;
import peripherals.Display;
import peripherals.GyroSensor;
import peripherals.MockColorSensor;
import peripherals.MockDisplay;
import peripherals.MockGyroSensor;
import peripherals.MockMotor;
import peripherals.MockTouchSensor;
import peripherals.Motor;
import peripherals.RealColorSensor;
import peripherals.RealDisplay;
import peripherals.RealGyroSensor;
import peripherals.RealMotor;
import peripherals.RealTouchSensor;
import peripherals.TouchSensor;
import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import states.AbortState;
import states.ModeSelectionState;
import states.State;
import states.WarningState;
import error.AbortButtonError;
import error.BatteryWarning;

public class Main {
	public static final Port MOTOR_PORT = 			MotorPort.A;
	public static final Port COLOR_SENSOR_PORT = 	SensorPort.S1;
	public static final Port GYRO_SENSOR_PORT = 	SensorPort.S2;
	public static final Port TOUCH_SENSOR_PORT = 	SensorPort.S3;
	
	public static final int MOTOR_TURN_STEP = 		120;
	public static final int MOTOR_SAFE_SPEED = 		MOTOR_TURN_STEP;
	public static final int MOTOR_FAST_SPEED = 		750;
	public static final int GYRO_STABLE_TRESHOLD = 	15;
	public static final float BATTERY_TRESHOLD = 	8.5f;
	
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
	
	public Main(boolean mocked){
		//TODO say hello
		Say.wtf();
		if(!mocked){
			motor = new RealMotor(MOTOR_PORT, MOTOR_TURN_STEP, MOTOR_SAFE_SPEED, MOTOR_FAST_SPEED);
			colorSensor = new RealColorSensor(COLOR_SENSOR_PORT);
			gyroSensor = new RealGyroSensor(GYRO_SENSOR_PORT, GYRO_STABLE_TRESHOLD);
			touchSensor = new RealTouchSensor(TOUCH_SENSOR_PORT);
			display = new RealDisplay();
		}
		else {
			motor = new MockMotor();
			colorSensor = new MockColorSensor();
			gyroSensor = new MockGyroSensor();
			touchSensor = new MockTouchSensor();
			display = new MockDisplay();
		}
		
		
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
			//check buttons
			if(aButton.isDown() && !(currentState instanceof AbortState)){
				currentState = new AbortState(new AbortButtonError(), this);
			} 
			else if(spButton.isDown()){
				paused = true;
			}
			else if(rButton.isDown()){
				reset = true;
			}
			
			//check battery
			if(Battery.getVoltage() < this.BATTERY_TRESHOLD){
				currentState = new WarningState(new BatteryWarning(), this, currentState);
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
	
	public static void main(String[] args){
		Main m = new Main(false);
		m.run();
	}
}
