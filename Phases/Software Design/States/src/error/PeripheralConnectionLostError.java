package error;

import sorter.Say;

public class PeripheralConnectionLostError extends FatalError {

	public PeripheralConnectionLostError() {
		super("Connection to a peripheral lost.");
	}
	
	@Override
	public void say(){
		Say.connectionLost();
	}
}
