package chess;

public class ChessBoard {
	
	private ChessPiece[][] board = new ChessPiece[8][8];
	
	public ChessPiece get(int x, int y) {
		return board[y][x];
	}
	
	public void init() {
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				board[y][x] = ChessPiece.EMPTY;
			}
		}
		for(int x = 0; x < 8; x++) {
			board[1][x] = ChessPiece.B_PAWN;
			board[6][x] = ChessPiece.W_PAWN;
			switch(x) {
				case 0: case 7:
					board[0][x] = ChessPiece.B_ROOK;
					board[7][x] = ChessPiece.W_ROOK;
				break;
				
				case 1: case 6:
					board[0][x] = ChessPiece.B_KNIGHT;
					board[7][x] = ChessPiece.W_KNIGHT;
				break;
				
				case 2: case 5:
					board[0][x] = ChessPiece.B_BISHOP;
					board[7][x] = ChessPiece.W_BISHOP;
				break;
				
				case 3:
					board[0][x] = ChessPiece.B_QUEEN;
					board[7][x] = ChessPiece.W_QUEEN;
				break;
				
				case 4:
					board[0][x] = ChessPiece.B_KING;
					board[7][x] = ChessPiece.W_KING;
				break;
			}
		}
	}
	public void set(int x, int y, ChessPiece piece) {
		board[y][x] = piece;
	}
	
	public void doMove(Move move) {
		board[(int)move.getDestination().getY()][(int)move.getDestination().getX()] = board[(int)move.getStart().getY()][(int)move.getStart().getX()];
		board[(int)move.getStart().getY()][(int)move.getStart().getX()] = ChessPiece.EMPTY;
	}
	
	public ChessBoard clone() {
		ChessBoard clone = new ChessBoard();
		for(int y=0; y<8; y++) {
			for(int x=0; x<8; x++) {
				clone.set(x, y, get(x,y));
			}
		}
		return clone;
	}
}
