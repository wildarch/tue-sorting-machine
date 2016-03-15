package states;
import sorter.Main;
import sorter.Statistics;

public class DoneState extends State {
	public DoneState(){
		//TODO say done, show more stats. Flash green LEDs
	}

	@Override
	public State nextState(Main m) {
		if(m.rButton.isDown()){
			m.stats.reset();
			return new ModeSelectionState();
		}
		return this;
	}
	
	@Override
	public void displayUpdate(Main m){
		super.displayUpdate(m);
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
	}
	
}
