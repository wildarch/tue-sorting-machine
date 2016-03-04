package states;
public class BlackState extends ColorState {
	public BlackState() {
		super(new ReadColorState(), true);
	}
}
