package peripherals;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;
import sorter.Images;
import sorter.Statistics;
import states.State;
import error.FatalError;
import error.Warning;

public class RealDisplay implements Display {
	GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
    final int SW = g.getWidth();
    final int SH = g.getHeight();
    
    private long displayReadyTime;
	private final long warningWaitTime = 5000;
	
    public RealDisplay() {
    	g.setAutoRefresh(false);
    }
    
	@SuppressWarnings("static-access")
	public void drawCount(State state, Statistics stats, boolean refresh){
		if(!isReady()) return;
		Button.LEDPattern(1);
		String name = state.getClass().getSimpleName();
		
		g.clear();
		
		drawTitle(name);
		
		g.setFont(Font.getLargeFont());
		g.drawString("B", SW/3, SH/2-10, g.BASELINE|g.HCENTER);
		String b = String.valueOf(stats.black);
		g.drawString(b, SW/3, SH/2+30, g.BASELINE|g.HCENTER);
		
		g.drawString("W", 2*SW/3, SH/2-10, g.BASELINE|g.HCENTER);
		String w = String.valueOf(stats.white);
		g.drawString(w, 2*SW/3, SH/2+30, g.BASELINE|g.HCENTER);
		if(refresh) g.refresh();
	}
	
	public void drawCount(State s, Statistics st){
		drawCount(s, st, true);
	}
	
	@SuppressWarnings("static-access")
	public boolean drawModeSelect(){
		if(!isReady()) return false;
		g.clear();
		
		g.drawImage(Images.modeSelect, SW/2, 0, g.HCENTER|g.TOP);
		g.setFont(Font.getSmallFont());
		
		g.drawString("Fast", 0, SH-20, g.BASELINE);
		g.drawString("Step", SW-25, SH-20, g.BASELINE);
		g.drawString("Exit", SW/2, SH-10, g.BASELINE|g.HCENTER);
		g.drawString("Safe", SW/2, SH-20, g.BASELINE|g.HCENTER);
		g.drawString("Calibrate", SW/2, SH-30, g.BASELINE|g.HCENTER);
		
		g.refresh();
		return true;
	}
	
	@SuppressWarnings("static-access")
	public void drawSuccessChance(float chance){
		if(!isReady()) return;
		g.setFont(Font.getDefaultFont());
		g.drawString("Success: "+(chance)*100 + "%", SW/2, SH, g.BOTTOM|g.HCENTER, true);
	}
	
	@SuppressWarnings("static-access")
	public void drawTime(long t){
		if(!isReady()) return;
		int seconds = (int)t/1000;
		long miliseconds = t - seconds * 1000; 
		g.setFont(Font.getDefaultFont());
		g.drawString("Time: " + seconds + "." + miliseconds + "s", SW/2, SH-g.getFont().getHeight(), g.BOTTOM|g.HCENTER, true);
	}
	
	private boolean isReady() {
		return System.currentTimeMillis() > displayReadyTime;
	}

	@SuppressWarnings("static-access")
	private void drawTitle(String s){
		g.setFont(Font.getDefaultFont());
		g.drawString(s, SW/2, 14, g.BASELINE|g.HCENTER, true);
	}
	
	@SuppressWarnings("static-access")
	private void drawError(String title, Image img, String caption){
		g.clear();
		drawTitle(title);
		g.drawImage(img, SW/2, SH/2, g.HCENTER|g.VCENTER);
		g.setFont(Font.getSmallFont());
		g.drawString(caption, SW/2, SH-5, g.BASELINE|g.HCENTER);
		g.refresh();
		
	}
	
	public void drawFatal(FatalError fatal){
		Button.LEDPattern(8);
		drawError("Fatal error", Images.fatalImage, fatal.getMessage());
	}
	
	public void drawWarning(Warning warning){
		Button.LEDPattern(6);
		setDisplayReadyTime(System.currentTimeMillis() + warningWaitTime);
		drawError("Warning", Images.warnImage, warning.getMessage());
	}
	
	public void setDisplayReadyTime(long t){
		this.displayReadyTime = t;
	}
	
	public void refresh(){
		g.refresh();
	}
}
