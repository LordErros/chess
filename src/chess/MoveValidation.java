package chess;

import java.awt.Point;

//Handles all validation of moves.
public class MoveValidation {
	
	public static boolean isValid(Move move, ChessPiece[][] board, Player player) {
		boolean result = false;
		
		int startX = (int)move.getStart().getX();
		int startY = (int)move.getStart().getY();
		int destinationX = (int)move.getDestination().getX();
		int destinationY = (int)move.getDestination().getY();
		
		if(!board[destinationY][destinationX].isColour(player.getColour()) && board[startY][startX].isColour(player.getColour())) {
			switch(board[startY][startX]) {
				case W_PAWN: case B_PAWN:
					result = isValidPawn(startX, startY, destinationX, destinationY, board, player);
					break;
			
				case W_KNIGHT: case B_KNIGHT:
					result = isValidKnight(startX, startY, destinationX, destinationY, board, player);
				break;
			
				case W_ROOK: case B_ROOK:
					result = isValidRook(startX, startY, destinationX, destinationY, board, player);
				break;
				
				case W_BISHOP: case B_BISHOP:
					result = isValidBishop(startX, startY, destinationX, destinationY, board, player);
				break;
				
				case W_QUEEN: case B_QUEEN:
					result = isValidQueen(startX, startY, destinationX, destinationY, board, player);
				break;
				
				case W_KING: case B_KING:
					result = isValidKing(startX, startY, destinationX, destinationY, board, player);
				break;
				
				default:
				break;
			}
		}
		return result;
	}
	
	//These are private as they are only to be called by isValid.
	
	private static boolean isValidPawn(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
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
		else if((Math.abs(destinationX - startX) == 1) && (startY + direction == destinationY) && (board[destinationY][destinationX].isColour(!player.getColour()))) {
			return true;
		}
		
		return result;
	}

	private static boolean isValidKnight(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		boolean result = false;
		
		if((Math.abs(startX - destinationX) == 1) && (Math.abs(startY - destinationY) == 2)) {
			result = true;
		}
		else if((Math.abs(startX - destinationX) == 2) && (Math.abs(startY - destinationY) == 1)) {
			result = true;
		}
		
		return result;
	}
	
	private static boolean isValidRook(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		boolean result = true;
		
		if(startX == destinationX) {
			int direction = (destinationY - startY) / Math.abs(destinationY - startY);
			for(int i = startY + direction; i != destinationY; i += direction) {
				if(!(board[i][startX] == ChessPiece.EMPTY)) {
					result = false;
				}
			}
		}
		else if(startY == destinationY) {
			int direction = (destinationX - startX) / Math.abs(destinationX - startX);
			for(int i = startX + direction; i != destinationX; i += direction) {
				if(!(board[startY][i] == ChessPiece.EMPTY)) {
					result = false;
				}
			}
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	private static boolean isValidBishop(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		boolean result = true;
		
		if(Math.abs(destinationX - startX) == Math.abs(destinationY - startY)) {
			int xDirection = (destinationX - startX) / Math.abs(destinationX - startX);
			int yDirection = (destinationY - startY) / Math.abs(destinationY - startY);
			for(int i = 1; i < Math.abs(destinationX - startX); i++) {
				if(board[startY + i * yDirection][startX + i * xDirection] != ChessPiece.EMPTY) {
					result = false;
				}
			}
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	private static boolean isValidQueen(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		return isValidRook(startX, startY, destinationX, destinationY, board, player) || isValidBishop(startX, startY, destinationX, destinationY, board, player);
	}
	
	private static boolean isValidKing(int startX, int startY, int destinationX, int destinationY, ChessPiece[][] board, Player player) {
		boolean result = false;
		if((Math.abs(destinationX - startX) <= 1 && Math.abs(destinationY - startY) <= 1)) {
			ChessPiece[][] tempBoard = doMove(new Move(new Point(startX, startY), new Point(destinationX,destinationY)), board);
			if(!pieceIsInCheck(new Point(destinationX, destinationY), tempBoard)) {
				result = true;
			}
		}
		return result;
	}
	
	public static boolean pieceIsInCheck(Point piece, ChessPiece[][] board) {
		boolean result = false;
		
		boolean opponentColour = !board[(int)piece.getY()][(int)piece.getX()].getColour();
		
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				if(board[y][x].isColour(opponentColour) && board[y][x] != ChessPiece.W_KING && board[y][x] != ChessPiece.B_KING) {
					if(isValid(new Move(new Point(x,y), piece), board, new HumanPlayer(opponentColour))) {
						result = true;
					}
				}
			}
		}
		
		return result;
	}
	
	public static ChessPiece[][] doMove(Move move, ChessPiece[][] board) {
		ChessPiece[][] finalBoard = cloneBoard(board);
		finalBoard[(int)move.getDestination().getY()][(int)move.getDestination().getX()] = finalBoard[(int)move.getStart().getY()][(int)move.getStart().getX()];
		finalBoard[(int)move.getStart().getY()][(int)move.getStart().getX()] = ChessPiece.EMPTY;
		return finalBoard;
	}
	
	public static ChessPiece[][] cloneBoard(ChessPiece[][] board) {
		ChessPiece[][] clone = new ChessPiece[8][8];
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				clone[y][x] = board[y][x];
			}
		}
		return clone;
	}
	
}
