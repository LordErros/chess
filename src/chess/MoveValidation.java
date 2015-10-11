package chess;

import java.awt.Point;



public class MoveValidation {
	
	public static boolean isValid(Move move, ChessPiece[][] board, Player player) {
		boolean result = false;
		
		int startX = (int)move.getStart().getX();
		int startY = (int)move.getStart().getY();
		int destinationX = (int)move.getDestination().getX();
		int destinationY = (int)move.getDestination().getY();
		
		if(!(board[destinationY][destinationX].getColour() == player.getColour() && board[destinationY][destinationX] != ChessPiece.EMPTY)) {
			switch(board[(int)move.getStart().getY()][(int)move.getStart().getX()]) {
				case W_PAWN: case B_PAWN:
					result = isValidPawn(startX, startY, destinationX, destinationY, board, player);
					break;
			
				case W_KNIGHT: case B_KNIGHT:
					result = isValidKnight(startX, startY, destinationX, destinationY, board, player);
				break;
			
				default:
				break;
			}
		}
		return result;
	}
	
	public static boolean isValidPawn(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		boolean result = false;
		int direction = 0;
		int startLine = 0;
		if(player.getColour() == Player.WHITE) {
			direction = -1;
			startLine = 6;
		}
		else {
			direction = 1;
			startLine = 1;
		}
		
		if(startX == destinationX) {
			if(((startY + direction == destinationY) || (startY + direction*2 == destinationY && startY == startLine)) && (board[destinationY][destinationX] == ChessPiece.EMPTY)) {
				return true;
			}
		}
		else if((Math.abs(destinationX - startX) == 1) && (startY + direction == destinationY) && (board[destinationY][destinationX].getColour() == !player.getColour())) {
			return true;
		}
		
		return result;
	}

	public static boolean isValidKnight(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		boolean result = false;
		
		if((Math.abs(startX - destinationX) == 1) && (Math.abs(startY - destinationY) == 2)) {
			result = true;
		}
		else if((Math.abs(startX - destinationX) == 2) && (Math.abs(startY - destinationY) == 1)) {
			result = true;
		}
		
		return result;
	}
}
