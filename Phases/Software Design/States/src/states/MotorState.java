package states;
import error.AbortButtonError;
import error.FatalError;
import error.WrongBasketWarning;
import sorter.Main;
import sorter.Mode;
import sorter.Orientation;

public abstract class MotorState extends State {
	
	private Orientation direction;
	
	public MotorState(Orientation o){
		direction = o;
	}

	@Override
	public State run(Main m) {
		if(m.getMode()==Mode.FAST){//check mode
			if(!m.motor.isFinished()) {//M=F?
				return new ReadState();
			}
			return this;
		}
		else{//other modes
			//turn motor
			if (!m.motor.isMoving()){
				if(direction == Orientation.Right){
					m.motor.turnRight();
				}
				else{
					m.motor.turnLeft();
				}
			}
			
			//check gyro
			if(m.gyroSensor.getOrientation() == direction){
				m.timerStart();
				return new StabilizeState();
			}
			
			//check warning guard
			if(m.timerLargerThanAvg()){
				return new MotorWarningState();
			}
			
			if(m.timerLargerThanDMax() || 
					(!(direction == Orientation.Right) || m.gyroSensor.getOrientation() == Orientation.Left) ||
					(!(direction == Orientation.Left) || m.gyroSensor.getOrientation() == Orientation.Right) ||
					m.motor.isJammed()) {
				m.peripheralReset();
				//TODO change error the throw
				return new AbortState(new AbortButtonError(), m);
			}
			
			return this;
		}
	}

}
