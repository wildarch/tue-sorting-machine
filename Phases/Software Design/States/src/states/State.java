package states;
import sorter.AbstractMain;

public abstract class State {
	public abstract State nextState(AbstractMain abstractMain);
	
	public void displayUpdate(AbstractMain abstractMain){
		abstractMain.display.drawCount(this, abstractMain.stats);
	}
}
