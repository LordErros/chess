package chess;

/**
 * Class to represent a game of chess, including the board state, whose turn it is, and checking when
 * a player has won.
 * @author Jack
 *
 */
public class ChessGame {
	private ChessBoard board;
	
	private Player whitePlayer;
	private Player blackPlayer;
	private Player currentPlayer;
	
	public ChessGame() {
		board = new ChessBoard();
		board.init();
		
		whitePlayer = new HumanPlayer(Player.WHITE);
		blackPlayer = new HumanPlayer(Player.BLACK);
		currentPlayer = whitePlayer;
	}
	
	public ChessBoard getBoard() {
		return board;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void performMove(Move move) {
		Point start = move.getStart();
		
		System.out.println("Performing move...");
		if(MoveValidation.isValid(move, board, currentPlayer, false)) {
			board.doMove(move, currentPlayer);
			if(board.get(start) == ChessPiece.W_KING || board.get(start) == ChessPiece.B_KING) {
				currentPlayer.setCanCastleKingSide(false);
				currentPlayer.setCanCastleQueenSide(false);
			}
			
			if(start.getY() == 0 || start.getY() == ChessBoard.HEIGHT) {
				if(start.getX() == 0) {
					currentPlayer.setCanCastleQueenSide(false);
				}
				else if(start.getX() == ChessBoard.WIDTH) {
					currentPlayer.setCanCastleKingSide(false);
				}
			}
			if(currentPlayer == whitePlayer) {
				currentPlayer = blackPlayer;
			}
			else if(currentPlayer == blackPlayer) {
				currentPlayer = whitePlayer;
			}
		}
		else {System.out.println("Failed - move is not valid.");}
	}
}
