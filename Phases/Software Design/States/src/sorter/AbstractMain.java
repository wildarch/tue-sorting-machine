package sorter;

import error.AbortButtonError;
import error.BatteryWarning;
import lejos.hardware.Key;
import peripherals.ColorSensor;
import peripherals.Display;
import peripherals.GyroSensor;
import peripherals.Motor;
import peripherals.TouchSensor;
import states.AbortState;
import states.ModeSelectionState;
import states.State;
import states.WarningState;
import peripherals.Battery;;

public abstract class AbstractMain {
	public static final int MOTOR_TURN_STEP = 		120;
	public static final int MOTOR_SAFE_SPEED = 		MOTOR_TURN_STEP;
	public static final int MOTOR_FAST_SPEED = 		750;
	public static final int GYRO_STABLE_TRESHOLD = 	15;
	public static final float BATTERY_TRESHOLD = 	8.5f;
	
	public final Key spButton;
	public final Key aButton;
	public final Key rButton;
	
	public final Motor motor;
	public final ColorSensor colorSensor;
	public final GyroSensor gyroSensor;
	public final TouchSensor touchSensor;
	public final Statistics stats;
	public final Battery battery;
	public final Timer timer = new Timer();
	
	private boolean paused = true;
	private boolean reset = false;
	private Mode mode;
	
	private long tavg = 100000;	//average time for disc to fall
	private long tdmax = 100000;	//maximum allowed time for disc to fall
	private long tgmax = 100000;	//maximum allowed time for gyro to stabilize
	
	protected State currentState;
	public Display display;
	
	public AbstractMain(
			Key sp,
			Key a,
			Key r,
			Motor m,
			ColorSensor c,
			GyroSensor g,
			TouchSensor t,
			Display d,
			Statistics s,
			Battery b
			){
		spButton = sp;
		aButton = a;
		rButton = r;
		motor = m;
		colorSensor = c;
		gyroSensor = g;
		touchSensor = t;
		display = d;
		stats = s;
		battery = b;
	}
	
	public void run(){
		while(true){
			cycle();
		}
	}
	
	public void cycle(){
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
		if(battery.getVoltage() < this.BATTERY_TRESHOLD){
			currentState = new WarningState(new BatteryWarning(), this, currentState);
		}
		
		currentState.displayUpdate(this);
		State newState = currentState.nextState(this);
		if(newState != currentState){
			//System.out.println("State: "+newState.getClass().getSimpleName());
		}
		currentState = newState;
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
	
}