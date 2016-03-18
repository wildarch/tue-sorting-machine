package states;
import lejos.hardware.Button;
import sorter.AbstractMain;

public class DoneState extends State {
	public DoneState(){
		//TODO say done
		Button.LEDPattern(7);
	}

	@Override
	public State nextState(AbstractMain m) {
		if(m.isReset()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(AbstractMain m){
		super.displayUpdate(m);
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
		long t = m.totalTimer.getTimeMS();
		m.display.drawTime(t);
	}
	
}
