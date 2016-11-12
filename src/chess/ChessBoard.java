package chess;

public class ChessBoard {
	
	private ChessPiece[][] board = new ChessPiece[8][8];
	
	public static final int WIDTH = 8;
	public static final int HEIGHT = 8;
	
	public ChessPiece get(int x, int y) {
		return board[y][x];
	}
	
	public ChessPiece get(Point p) {
		return board[p.getY()][p.getX()];
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
//					board[0][x] = ChessPiece.B_KNIGHT;
//					board[7][x] = ChessPiece.W_KNIGHT;
				break;
				
				case 2: case 5:
//					board[0][x] = ChessPiece.B_BISHOP;
//					board[7][x] = ChessPiece.W_BISHOP;
				break;
				
				case 3:
//					board[0][x] = ChessPiece.B_QUEEN;
//					board[7][x] = ChessPiece.W_QUEEN;
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
	
	public void doMove(Move move, Player currentPlayer) {
		
		Point start = move.getStart();
		Point dest = move.getDestination();
		
		
		
		board[dest.getY()][dest.getX()] = board[start.getY()][start.getX()];
		board[start.getY()][start.getX()] = ChessPiece.EMPTY;
		if(move.isCastle()) {
			if(board[dest.getY()][dest.getX()+1] == ChessPiece.EMPTY) {
				//If castling queen side, the rook is two spaces away.
				doMove(new Move(new Point(dest.getX() - 2, dest.getY()), new Point(dest.getX() + 1, dest.getY())), currentPlayer);
			}
			else if(board[dest.getY()][dest.getX()-1] == ChessPiece.EMPTY) {
				doMove(new Move(new Point(dest.getX() + 1, dest.getY()), new Point(dest.getX() - 1, dest.getY())), currentPlayer);
			}
		}
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
