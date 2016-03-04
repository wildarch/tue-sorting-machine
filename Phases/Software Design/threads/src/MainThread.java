import lejos.hardware.Button;
import lejos.hardware.motor.Motor;
public class MainThread extends Thread {
	public int oldBut;
	private CycleThread cthread = new CycleThread();
	
	public void run(){
		cthread.run();
		
		while(true){
			int but = Button.readButtons();
			
			if (butPressed(but, Button.ID_ESCAPE)) {
				Motor.A.stop();
				cthread.queue.offer(Messages.A);
			}
			else if (butPressed(but, Button.ID_ENTER)){
				cthread.queue.offer(Messages.SP);
			}
			else if (butPressed(but, Button.ID_DOWN)){
				cthread.queue.offer(Messages.R);
			}
			
		}
	}
	
	private boolean butPressed(final int but, final int id){
		return (but & id) == id && (oldBut & id) == 0;
	}
}
