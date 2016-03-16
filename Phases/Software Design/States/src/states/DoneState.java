package states;
import sorter.Main;

public class DoneState extends State {
	public DoneState(){
		//TODO say done, show more stats. Flash green LEDs
	}

	@Override
	public State nextState(Main m) {
		if(m.isReset()){
			m.setReset(false);
			return new ModeSelectionState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(Main m){
		super.displayUpdate(m);
		m.display.drawSuccessChance(m.stats.getChanceSuccess());
	}
	
}
