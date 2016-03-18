package states;
import lejos.hardware.Button;
import sorter.Main;

public class DoneState extends State {
	public DoneState(){
		//TODO say done, show more stats.
		Button.LEDPattern(7);
	}

	@Override
	public State nextState(Main m) {
		if(m.isReset()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(Main m){
		super.displayUpdate(m);
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
		long t = m.totalTimer.getTimeMS();
		//TODO say total sort time, is stored in t
	}
	
}
