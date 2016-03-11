package sorter;

import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.Font;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.lcd.Image;
import states.State;
import error.FatalError;
import error.Warning;

public class Display {
	GraphicsLCD g = BrickFinder.getDefault().getGraphicsLCD();
    final int SW = g.getWidth();
    final int SH = g.getHeight();
	
    public Display() {
    	g.setAutoRefresh(false);
    }
    
	@SuppressWarnings("static-access")
	public void update(State state, Statistics stats){
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
		g.refresh();
	}
	
	@SuppressWarnings("static-access")
	private void drawTitle(String s){
		g.setFont(Font.getDefaultFont());
		g.drawString(s, SW/2, 14, g.BASELINE|g.HCENTER, true);
	}
	
	@SuppressWarnings("static-access")
	private void draw(String title, Image img, String caption){
		g.clear();
		drawTitle(title);
		g.drawImage(img, SW/2, SH/2, g.HCENTER|g.VCENTER);
		g.setFont(Font.getSmallFont());
		g.drawString(caption, SW/2, SH-5, g.BASELINE|g.HCENTER);
		g.refresh();
		
	}
	
	public void drawFatal(FatalError fatal){
		Image fatalImage = new Image(64, 64, new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0xfc, (byte) 0xff, (byte) 0xff, 
				(byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x80, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0x00, 
				(byte) 0x00, (byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0xf0, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0x00, (byte) 0x00, (byte) 0xf8, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0x00, (byte) 0x00, 
				(byte) 0xfc, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x00, 
				(byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x80, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x01, (byte) 0xc0, (byte) 0xff, (byte) 0xff, (byte) 0x03, 
				(byte) 0xc0, (byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0xe0, 
				(byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0xc0, (byte) 0xff, 
				(byte) 0xff, (byte) 0x07, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0x03, (byte) 0xc0, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0xf8, (byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0xc0, 
				(byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0xfc, (byte) 0xff, 
				(byte) 0xff, (byte) 0x03, (byte) 0xc0, (byte) 0xff, (byte) 0xff, 
				(byte) 0x3f, (byte) 0xfe, (byte) 0xff, (byte) 0xff, (byte) 0x03, 
				(byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0xe0, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x07, (byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x07, (byte) 0xe0, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x07, (byte) 0xe0, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x07, 
				(byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x07, (byte) 0xe0, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x07, (byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x07, (byte) 0xe0, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x07, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x07, 
				(byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x0f, (byte) 0xf0, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x0f, (byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, (byte) 0xf0, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x0f, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x0f, (byte) 0xf0, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x0f, (byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, (byte) 0xf0, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x0f, (byte) 0xf8, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0xf8, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0xf8, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x1f, (byte) 0xf8, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x3f, (byte) 0xfc, 
				(byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0xfe, (byte) 0xff, 
				(byte) 0xff, (byte) 0x0f, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0x3f, (byte) 0xfc, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0xf0, (byte) 0xff, (byte) 0xff, (byte) 0x1f, (byte) 0xf8, 
				(byte) 0xff, (byte) 0xff, (byte) 0x07, (byte) 0xf0, (byte) 0xff, 
				(byte) 0xff, (byte) 0x0f, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0x07, (byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0x07, 
				(byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0x07, (byte) 0xf0, 
				(byte) 0xff, (byte) 0xff, (byte) 0x03, (byte) 0xc0, (byte) 0xff, 
				(byte) 0xff, (byte) 0x0f, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0x01, (byte) 0x80, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0xf8, (byte) 0xff, (byte) 0xff, (byte) 0x00, (byte) 0x00, 
				(byte) 0xff, (byte) 0xff, (byte) 0x3f, (byte) 0xfc, (byte) 0xff, 
				(byte) 0x7f, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x00, 
				(byte) 0x00, (byte) 0xfc, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x1f, (byte) 0x00, (byte) 0x00, (byte) 0xf8, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x0f, 
				(byte) 0x00, (byte) 0x00, (byte) 0xf0, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0x07, (byte) 0x00, (byte) 0x00, 
				(byte) 0xe0, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x01, (byte) 0x00, 
				(byte) 0x00, (byte) 0x80, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x7f, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xfe, (byte) 0xff, 
				(byte) 0xff, (byte) 0x3f, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0xfc, (byte) 0xff, (byte) 0xff, (byte) 0x1f, 
				(byte) 0x00, (byte) 0x00, });
		draw("Fatal error", fatalImage, fatal.getMessage());
	}
	
	public void drawWarning(Warning warning){
		Image warnImage = new Image(64, 64, new byte[] {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x80, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x03, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0xe0, (byte) 0x07, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf0, 
				(byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0xf0, (byte) 0x0f, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0xf8, (byte) 0x0f, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7c, (byte) 0x1e, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x3c, (byte) 0x3e, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x3e, 
				(byte) 0x3c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x1e, (byte) 0x7c, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x1f, (byte) 0x78, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x0f, (byte) 0xf8, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x80, (byte) 0x07, (byte) 0xf0, (byte) 0x01, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x07, 
				(byte) 0xe0, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0xc0, (byte) 0x03, (byte) 0xe0, (byte) 0x03, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, 
				(byte) 0x01, (byte) 0xc0, (byte) 0x03, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x01, (byte) 0xc0, 
				(byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0xf0, (byte) 0xc0, (byte) 0x83, (byte) 0x07, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xf8, (byte) 0xe0, 
				(byte) 0x07, (byte) 0x0f, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x78, (byte) 0xe0, (byte) 0x07, (byte) 0x1f, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x7c, 
				(byte) 0xe0, (byte) 0x07, (byte) 0x1e, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x3c, (byte) 0xe0, (byte) 0x07, 
				(byte) 0x3c, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x1e, (byte) 0xe0, (byte) 0x07, (byte) 0x7c, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x1f, (byte) 0xe0, 
				(byte) 0x07, (byte) 0x78, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x0f, (byte) 0xe0, (byte) 0x07, (byte) 0xf8, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x0f, 
				(byte) 0xe0, (byte) 0x07, (byte) 0xf0, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x80, (byte) 0x07, (byte) 0xe0, (byte) 0x07, 
				(byte) 0xf0, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0xc0, 
				(byte) 0x07, (byte) 0xe0, (byte) 0x07, (byte) 0xe0, (byte) 0x01, 
				(byte) 0x00, (byte) 0x00, (byte) 0xc0, (byte) 0x03, (byte) 0xe0, 
				(byte) 0x07, (byte) 0xc0, (byte) 0x03, (byte) 0x00, (byte) 0x00, 
				(byte) 0xe0, (byte) 0x01, (byte) 0xe0, (byte) 0x07, (byte) 0xc0, 
				(byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0xf0, (byte) 0x01, 
				(byte) 0xc0, (byte) 0x07, (byte) 0x80, (byte) 0x07, (byte) 0x00, 
				(byte) 0x00, (byte) 0xf0, (byte) 0x00, (byte) 0xc0, (byte) 0x07, 
				(byte) 0x80, (byte) 0x0f, (byte) 0x00, (byte) 0x00, (byte) 0xf8, 
				(byte) 0x00, (byte) 0xc0, (byte) 0x07, (byte) 0x00, (byte) 0x0f, 
				(byte) 0x00, (byte) 0x00, (byte) 0x78, (byte) 0x00, (byte) 0xc0, 
				(byte) 0x07, (byte) 0x00, (byte) 0x1f, (byte) 0x00, (byte) 0x00, 
				(byte) 0x7c, (byte) 0x00, (byte) 0xc0, (byte) 0x03, (byte) 0x00, 
				(byte) 0x3e, (byte) 0x00, (byte) 0x00, (byte) 0x3c, (byte) 0x00, 
				(byte) 0xc0, (byte) 0x03, (byte) 0x00, (byte) 0x3c, (byte) 0x00, 
				(byte) 0x00, (byte) 0x1e, (byte) 0x00, (byte) 0xc0, (byte) 0x03, 
				(byte) 0x00, (byte) 0x7c, (byte) 0x00, (byte) 0x00, (byte) 0x1f, 
				(byte) 0x00, (byte) 0xc0, (byte) 0x03, (byte) 0x00, (byte) 0x78, 
				(byte) 0x00, (byte) 0x00, (byte) 0x0f, (byte) 0x00, (byte) 0xc0, 
				(byte) 0x03, (byte) 0x00, (byte) 0xf0, (byte) 0x00, (byte) 0x80, 
				(byte) 0x0f, (byte) 0x00, (byte) 0x80, (byte) 0x03, (byte) 0x00, 
				(byte) 0xf0, (byte) 0x01, (byte) 0x80, (byte) 0x07, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x01, 
				(byte) 0xc0, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0xe0, (byte) 0x03, (byte) 0xc0, (byte) 0x03, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xc0, 
				(byte) 0x03, (byte) 0xe0, (byte) 0x01, (byte) 0x00, (byte) 0xe0, 
				(byte) 0x07, (byte) 0x00, (byte) 0x80, (byte) 0x07, (byte) 0xf0, 
				(byte) 0x01, (byte) 0x00, (byte) 0xe0, (byte) 0x07, (byte) 0x00, 
				(byte) 0x80, (byte) 0x0f, (byte) 0xf0, (byte) 0x00, (byte) 0x00, 
				(byte) 0xe0, (byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x0f, 
				(byte) 0xf8, (byte) 0x00, (byte) 0x00, (byte) 0xe0, (byte) 0x07, 
				(byte) 0x00, (byte) 0x00, (byte) 0x1f, (byte) 0x78, (byte) 0x00, 
				(byte) 0x00, (byte) 0xe0, (byte) 0x07, (byte) 0x00, (byte) 0x00, 
				(byte) 0x1e, (byte) 0x3c, (byte) 0x00, (byte) 0x00, (byte) 0xe0, 
				(byte) 0x07, (byte) 0x00, (byte) 0x00, (byte) 0x3e, (byte) 0x3c, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x3c, (byte) 0x1e, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x78, 
				(byte) 0x1f, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0xf8, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xfe, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0x7f, (byte) 0xfc, (byte) 0xff, (byte) 0xff, 
				(byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0xff, (byte) 0x3f, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, 
				(byte) 0x00, (byte) 0x00, });
		draw("Warning", warnImage, warning.getMessage());
	}
}
