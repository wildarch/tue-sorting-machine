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
	public boolean paused = true;
	
	private State currentState;
	public Display display;
	
	public Main(){
		setupPeripherals();
		
		currentState = new InitialState();
		
		while(true){
			if(Button.ESCAPE.isDown() && !currentState.isAbort()){
				currentState = new AbortState(new AbortButtonError(), this);
			} 
			else if(Button.ENTER.isDown()){
				paused = true;
			}
			
			State newState = currentState.nextState(this);
			if(newState != currentState){
				display.update(newState, stats);
			}
			currentState = newState;
		}
	}

	private void setupPeripherals(){
		motor = new Motor(MotorPort.A, 120, 400);
		colorSensor = new ColorSensor(SensorPort.S1);
		gyroSensor = new GyroSensor(SensorPort.S2, 20);
		display = new Display();
		stats = new Statistics();
	}
	
	public static void main(String[] args){
		new Main();
	}
	
}
