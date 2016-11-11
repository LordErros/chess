package chess;

//import java.awt.Point;

/**
 * A class that contains static methods to handle the validation of
 * chess moves.
 * @author Jack
 *
 */
public class MoveValidation {
	
	/**
	 * Checks whether or not the given move is valid for the given player given the board.
	 * @param move		the move that is to be checked
	 * @param board		the current state of the board
	 * @param player	the player whose turn it is
	 * @return			whether or not the move is valid
	 */
	public static boolean isValid(Move move, ChessBoard board, Player player, boolean ignoreCheck) {
		int startX = move.getStart().getX();
		int startY = move.getStart().getY();
		int destinationX = move.getDestination().getX();
		int destinationY = move.getDestination().getY();

		if(!ignoreCheck) {
			ChessBoard tempBoard = board.clone();
			tempBoard.doMove(move, player);
			Point kingLocation = new Point(0,0);
			
			for(int x=0; x<ChessBoard.WIDTH; x++) {
				for(int y=0; y<ChessBoard.HEIGHT; y++) {
					if(tempBoard.get(x, y) == ChessPiece.W_KING && player.getColour() == Player.WHITE) {
						kingLocation.setXY(x, y);
					}
					else if(tempBoard.get(x, y) == ChessPiece.B_KING && player.getColour() == Player.BLACK) {
						kingLocation.setXY(x, y);
					}
				}
			}
			
			if(pieceIsInCheck(kingLocation,tempBoard)) {
				return false;
			}
		}
		if(!board.get(destinationX, destinationY).isColour(player.getColour()) && board.get(startX, startY).isColour(player.getColour())) {
			switch(board.get(startX, startY)) {
				case W_PAWN: case B_PAWN:
					return isValidPawn(startX, startY, destinationX, destinationY, board, player);
				
				case W_KNIGHT: case B_KNIGHT:
					return isValidKnight(startX, startY, destinationX, destinationY, board, player);
				
				case W_ROOK: case B_ROOK:
					return isValidRook(startX, startY, destinationX, destinationY, board, player);
				
				case W_BISHOP: case B_BISHOP:
					return isValidBishop(startX, startY, destinationX, destinationY, board, player);
						
				case W_QUEEN: case B_QUEEN:
					return isValidQueen(startX, startY, destinationX, destinationY, board, player);
						
				case W_KING: case B_KING:
					return isValidKing(startX, startY, destinationX, destinationY, board, player, move);
					
				default:
					return false;
			}
		}
		else {
			return false;
		}
	}
	
	//These are private as they are only to be called by isValid.
	
	private static boolean isValidPawn(int startX, int startY, int destinationX, int destinationY, ChessBoard board, Player player) {
		boolean result = false;
		int direction = 0;
		int startLine = 0;
		if(player.getColour() == Player.WHITE) {
			direction = -1;
			startLine = ChessBoard.HEIGHT - 2;
		}
		else {
			direction = 1;
			startLine = 1;
		}
		
		if(startX == destinationX) {
			if(((startY + direction == destinationY) || (startY + direction*2 == destinationY && startY == startLine)) && (board.get(destinationX, destinationY) == ChessPiece.EMPTY)) {
				return true;
			}
		}
		else if((Math.abs(destinationX - startX) == 1) && (startY + direction == destinationY) && (board.get(destinationX, destinationY).isColour(!player.getColour()))) {
			return true;
		}
		
		return result;
	}

	private static boolean isValidKnight(int startX, int startY, int destinationX, int destinationY, ChessBoard board, Player player) {
		boolean result = false;
		
		if((Math.abs(startX - destinationX) == 1) && (Math.abs(startY - destinationY) == 2)) {
			result = true;
		}
		else if((Math.abs(startX - destinationX) == 2) && (Math.abs(startY - destinationY) == 1)) {
			result = true;
		}
		
		return result;
	}
	
	private static boolean isValidRook(int startX, int startY, int destinationX, int destinationY, ChessBoard board, Player player) {
		boolean result = true;
		
		if(startX == destinationX) {
			int direction = (destinationY - startY) / Math.abs(destinationY - startY);
			for(int i = startY + direction; i != destinationY; i += direction) {
				if(!(board.get(startX, i) == ChessPiece.EMPTY)) {
					result = false;
				}
			}
		}
		else if(startY == destinationY) {
			int direction = (destinationX - startX) / Math.abs(destinationX - startX);
			for(int i = startX + direction; i != destinationX; i += direction) {
				if(!(board.get(i, startY) == ChessPiece.EMPTY)) {
					result = false;
				}
			}
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	private static boolean isValidBishop(int startX, int startY, int destinationX, int destinationY, ChessBoard board, Player player) {
		boolean result = true;
		
		if(Math.abs(destinationX - startX) == Math.abs(destinationY - startY)) {
			int xDirection = (destinationX - startX) / Math.abs(destinationX - startX);
			int yDirection = (destinationY - startY) / Math.abs(destinationY - startY);
			for(int i = 1; i < Math.abs(destinationX - startX); i++) {
				if(board.get(startX + i * xDirection, startY + i * yDirection) != ChessPiece.EMPTY) {
					result = false;
				}
			}
		}
		else {
			result = false;
		}
		
		return result;
	}
	
	private static boolean isValidQueen(int startX, int startY, int destinationX, int destinationY, ChessBoard board, Player player) {
		return isValidRook(startX, startY, destinationX, destinationY, board, player) || isValidBishop(startX, startY, destinationX, destinationY, board, player);
	}
	
	private static boolean isValidKing(int startX, int startY, int destinationX, int destinationY, ChessBoard board, Player player, Move move) {
		boolean result = false;
		ChessBoard tempBoard = board.clone();
		tempBoard.doMove(new Move(new Point(startX, startY), new Point(destinationX,destinationY)), player);
		if(pieceIsInCheck(new Point(destinationX, destinationY), tempBoard)) {
			result = false;
		}
		else if((Math.abs(destinationX - startX) <= 1 && Math.abs(destinationY - startY) <= 1)) {
			result = true;
		}
		else if(destinationX - startX == 2) {
			if(player.canCastleKingSide()) {
				result = true;
				move.setCastle(true);
			}
		}
		else if(destinationX - startY == -2) {
			if(player.canCastleQueenSide()) {
				result = true;
				move.setCastle(true);
			}
		}
		return result;
	}
	
	/**
	 * Determines whether or not the piece at the specifies position on the board
	 * is in check, that is to say that it is possible for it to be taken by an enemy
	 * piece next turn.
	 * @param	piece the coordinates of the piece to be checked
	 * @param 	board	the current state of the board
	 * @return	whether or not the piece is in check
	 */
	public static boolean pieceIsInCheck(Point piece, ChessBoard board) {
		boolean result = false;
		
		boolean opponentColour = !board.get(piece.getX(), piece.getY()).getColour();
		System.out.println(opponentColour);
		for(int y=0; y<ChessBoard.HEIGHT; y++) {
			for(int x=0; x<ChessBoard.WIDTH; x++) {
				/*
				 * A king is in check if it can be taken by another piece. A special case needs to be
				 * made for kings putting other kings in check to avoid an infinite loop where the game,
				 * to check whether or not a king is in check, checks if taking the king would put the 
				 * other king in check, hence performing the same check for the first king etc. Hence
				 * the special case only judges the distance between kings and disregards check.
				 */
				if(board.get(x, y).isColour(opponentColour) && board.get(x, y) != ChessPiece.W_KING && board.get(x, y) != ChessPiece.B_KING) {
					if(isValid(new Move(new Point(x,y), piece), board, new HumanPlayer(opponentColour), true)) {
						result = true;
					}
				}
				else if((board.get(x, y) == ChessPiece.W_KING && opponentColour == Player.WHITE) || (board.get(x, y) == ChessPiece.B_KING && opponentColour == Player.BLACK)) {
					if(Math.abs(x - piece.getX()) <= 1 && Math.abs(y - piece.getY()) <= 1) {
						System.out.println("Special case");
						result = true;
					}
				}
			}
		}
		
		return result;
	}
	
}
