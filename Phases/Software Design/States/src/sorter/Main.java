package sorter;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import states.AbortState;
import states.InitialState;
import states.State;
import error.AbortButtonError;

public class Main {
	public Motor motor;
	public ColorSensor colorSensor;
	public GyroSensor gyroSensor;
	public TouchSensor touchSensor;
	public Statistics stats;
	public Key spButton;
	public Key aButton;
	public Key rButton;
	private boolean paused = true;
	private boolean reset = false;
	private boolean abort = false;
	private Mode mode;
	
	private State currentState;
	public Display display;
	
	public Main(){
		this.setupPeripherals();
		currentState = new InitialState();
		setMode(Mode.FAST);
		//Say.hello();
		
		while(true){
			if(aButton.isDown() && !currentState.isAbort()){
				currentState = new AbortState(new AbortButtonError(), this);
			} 
			else if(spButton.isDown()){
				paused = true;
			}
			
			State newState = currentState.run(this);
			if(newState != currentState){
				display.update(newState, stats);
			}
			currentState = newState;
		}
	}

	private void setupPeripherals(){
		motor = new Motor(MotorPort.A, 120, 200);
		colorSensor = new ColorSensor(SensorPort.S1);
		gyroSensor = new GyroSensor(SensorPort.S2, 20);
		touchSensor = new TouchSensor(SensorPort.S3);
		spButton = Button.ENTER;
		aButton = Button.ESCAPE;
		rButton = Button.DOWN;
		
		display = new Display();
		stats = new Statistics();
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
	
	public boolean isAbort(){
		return this.abort;
	}
	
	public static void main(String[] args){
		new Main();
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
	
}
