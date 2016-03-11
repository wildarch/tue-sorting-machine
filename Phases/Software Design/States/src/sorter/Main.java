package sorter;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import states.AbortState;
import states.ModeSelectionState;
import states.State;
import error.AbortButtonError;

public class Main {
	public final Motor motor;
	public final ColorSensor colorSensor;
	public final GyroSensor gyroSensor;
	public final TouchSensor touchSensor;
	public final Key spButton;
	public final Key aButton;
	public final Key rButton;
	public final Statistics stats;
	
	private boolean paused = true;
	private boolean reset = false;
	private Mode mode;
	
	private State currentState;
	public Display display;
	
	public Main(){
		Say.wtf();
		motor = new Motor(MotorPort.A, 120, 200);
		colorSensor = new ColorSensor(SensorPort.S1);
		gyroSensor = new GyroSensor(SensorPort.S2, 20);
		touchSensor = new TouchSensor(SensorPort.S3);
		spButton = Button.ENTER;
		aButton = Button.ESCAPE;
		rButton = Button.DOWN;
		
		display = new Display();
		stats = new Statistics();
		
		currentState = new ModeSelectionState();
		setMode(Mode.FAST);
	}
	
	private void run(){
		while(true){
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
				System.out.println("State: "+newState.getClass().getSimpleName());
			}
			currentState = newState;
		}
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
	
}
