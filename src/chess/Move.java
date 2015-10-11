package chess;

import java.awt.Point;

public class Move {
	private Point start, destination;
	
	public Move(Point start, Point destination) {
		this.start = start;
		this.destination = destination;
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getDestination() {
		return destination;
	}
}
