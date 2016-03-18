package sorter;

public class Timer {
	private long ms;
	private long paused;
	
	/**
	 * Starts the timer, or continues it when it was paused.
	 */
	public void start(){
		if(paused > 0){
			ms = System.currentTimeMillis() - paused;
		}
		else{
			ms = System.currentTimeMillis();
		}
		
		paused = 0;
	}
	
	/**
	 * Reads the current time of the timer.
	 * @return the time in milliseconds passed since the last Timer.start() call.
	 */
	public long getTimeMS(){
		return System.currentTimeMillis() - ms;
	}
	
	/**
	 * Paused the timer.
	 */
	public void pause(){
		paused = getTimeMS();
	}
	
	/**
	 * Reset the timer to 0.
	 */
	public void reset(){
		paused = 0;
	}
}