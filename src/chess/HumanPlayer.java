package chess;

public class HumanPlayer implements Player {
	
	private boolean colour;
	
	public HumanPlayer(boolean colour) {
		this.colour = colour;
	}
	
	@Override
	public boolean getColour() {
		return colour;
	}

	@Override
	public void activate() {
		
	}

}
