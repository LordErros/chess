package chess;

public class Player {
	public static final boolean BLACK = false;
	public static final boolean WHITE = true;
	
	private boolean colour;
	
	private boolean castleKing;
	private boolean castleQueen;
	
	public Player(boolean colour) {
		this.colour = colour;
		this.castleKing = true;
		this.castleQueen = true;
	}
	
	public boolean getColour() {
		return colour;
	}
	
	public boolean canCastleKingSide() {
		return castleKing;
	}
	
	public boolean canCastleQueenSide() {
		return castleQueen;
	}
	
	public void setCanCastleKingSide(boolean castleKing) {
		this.castleKing = castleKing;
	}
	
	public void setCanCastleQueenSide(boolean castleQueen) {
		this.castleQueen = castleQueen;
	}
	
	//public void activate();
}
