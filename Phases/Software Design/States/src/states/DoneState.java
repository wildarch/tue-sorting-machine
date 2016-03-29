package states;
import lejos.hardware.Button;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Say;

public class DoneState extends State {
	private long time = 0;
	
	public DoneState(){
		//Say.done();
		Say.dumpert();
		try {
			Button.LEDPattern(7);
		}
		catch(NoClassDefFoundError e){
			//Np
		}
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
		m.display.drawCount(this, m.stats, false);
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
		if(m.getMode() != Mode.INCREMENTAL) m.display.drawTime(time);
		m.display.refresh();
	}
	
}
