package chess;

public interface Player {
	public static final boolean BLACK = false;
	public static final boolean WHITE = true;
	
	public boolean getColour();
	public void activate();
}
