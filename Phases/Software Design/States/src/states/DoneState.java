package states;
import lejos.hardware.Button;
import sorter.AbstractMain;
import sorter.Say;

public class DoneState extends State {
	private long time = 0;
	
	public DoneState(){
		Say.done();
		Button.LEDPattern(7);
		Say.dumpert();
	} 

	@Override
	public State nextState(AbstractMain m) {
		if (time == 0){
			time = m.totalTimer.getTimeMS();
		}
		
		if(m.isReset() || m.rButton.isDown()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(AbstractMain m){
		super.displayUpdate(m);
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
		m.display.drawTime(time);
	}
	
}
