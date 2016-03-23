package error;

import sorter.Say;

public class LongerThanAvgWarning extends Warning {
	public LongerThanAvgWarning() {
		super("The time to reach the basket deviates from average.");
	}
	
	@Override
	public void say(){
		Say.timeDev();
	}
}
