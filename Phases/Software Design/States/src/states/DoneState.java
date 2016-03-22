package states;
import lejos.hardware.Button;
import sorter.AbstractMain;
import sorter.Say;

public class DoneState extends State {
	private long time = 0;
	
	public DoneState(){
		Say.done();
		Button.LEDPattern(7);
	} 

	@Override
	public State nextState(AbstractMain m) {
		long t = m.totalTimer.getTimeMS();
		if(m.isReset() || m.rButton.isDown()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(AbstractMain m){
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
		m.display.drawTime(time);
		super.displayUpdate(m);
	}
	
}
