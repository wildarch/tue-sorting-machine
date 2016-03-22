package sorter;

import java.util.ArrayList;

public class Statistics {
	public int black;
	public int white;
	public int unknown;
	
	public ArrayList<Float> wrongChances = new ArrayList<Float>();
	
	public void reset(){
		this.black = this.white = this.unknown = 0;
	}
	
	public void addWrongChance(float c){
		wrongChances.add(c);
		//System.out.println("Wrong chance: "+c);
	}
	
	public float getChanceSuccess(){
		float chance = 1f;
		for(Float f : wrongChances){
			chance *= f;
		}
		chance = 1 - chance;
		return chance;
	}
}
