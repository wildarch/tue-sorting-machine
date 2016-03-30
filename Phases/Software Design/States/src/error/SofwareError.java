package error;

import sorter.AbstractMain;
import sorter.Say;

public class SofwareError extends FatalError {

	public SofwareError(AbstractMain abstractMain, Exception e) {
		super("Software Exception");
		System.out.println("Traces:");
		for(StackTraceElement elem : e.getStackTrace()){
			System.out.println("In "+elem.getClassName()+", line: "+elem.getLineNumber());
		}
	}
	
	@Override
	public void say() {
		Say.softwareException();
	}

}
