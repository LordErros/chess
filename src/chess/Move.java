package chess;

//import java.awt.Point;

public class Move {
	private Point start, destination;
	private boolean castle;
	
	public Move(Point start, Point destination) {
		this.start = start;
		this.destination = destination;
		this.castle = false;
	}
	
	public Move(Point start, Point destination, boolean castle) {
		this.start = start;
		this.destination = destination;
		this.castle = castle;
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getDestination() {
		return destination;
	}
	
	public boolean isCastle() {
		return castle;
	}
	
	public void setCastle(boolean castle) {
		this.castle = castle;
	}
}
