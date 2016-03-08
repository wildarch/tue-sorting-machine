package sorter;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import states.AbortState;
import states.InitialState;
import states.PausedState;
import states.State;
import error.AbortButtonError;

public class Main {
	public Motor motor;
	public ColorSensor colorSensor;
	public GyroSensor gyroSensor;
	public Statistics stats;
	private boolean paused = true;
	private boolean reset = false;
	private boolean abort = false;
	private Mode mode = Mode.SAFE;
	
	private State currentState;
	public Display display;
	
	public Main(){
		this.setupPeripherals();
		currentState = new InitialState();
		
		while(true){
			if(Button.ESCAPE.isDown() && currentState instanceof AbortState){
				currentState = new AbortState(new AbortButtonError(), this);
			} 
			else if(Button.ENTER.isDown()){
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
		//TODO remove
		motor = new Motor(MotorPort.A, 120, 400);
		colorSensor = new ColorSensor(SensorPort.S1);
		gyroSensor = new GyroSensor(SensorPort.S2, 20);
		display = new Display();
		stats = new Statistics();
	}
	
	public void peripheralStart(){
		motor = new Motor(MotorPort.A, 120, 400);
		colorSensor = new ColorSensor(SensorPort.S1);
		gyroSensor = new GyroSensor(SensorPort.S2, 20);
		display = new Display();
		stats = new Statistics();
	}
	
	public void peripheralReset(){
		//TODO implement
	}
	
	public void timerStart(){
		//TODO implement
	}
	
	public boolean timerLargerThanAvg(){
		//TODO implement
		return false;
	}
	
	public boolean timerLargerThanGMax(){
		//TODO implement
		return false;
	}
	
	public boolean timerLargerThanDMax(){
		//TODO implement
		return false;
	}
	
	public Mode getMode(){
		return this.mode;
	}
	
	public final boolean startPausedPressed(){
		return (Button.getButtons() & Button.ID_ENTER) == Button.ID_ENTER;
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
	
}
