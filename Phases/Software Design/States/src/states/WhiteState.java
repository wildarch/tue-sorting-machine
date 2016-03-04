package states;
public class WhiteState extends ColorState {
	public WhiteState(){
		super(new ReadColorState(), false);
	}
}
