package chess;

/**
 * Basic class for storing an integer point on the x,y plane. Used to indicate locations on
 * a chess board.
 * @author Jack
 *
 */
public class Point {
	private int x;
	private int y;
	
	/**
	 * Takes in the x and y coordinates of the point
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
