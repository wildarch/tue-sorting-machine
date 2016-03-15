package sorter;

public class Timer {
	private long ms;
	
	/**
	 * Starts the timer.
	 */
	public void start(){
		ms = System.currentTimeMillis();
	}
	
	/**
	 * Reads the current time of the timer.
	 * @return the time in milliseconds passed since the last Timer.start() call.
	 */
	public long getTimeMS(){
		return System.currentTimeMillis() - ms;
	}
}