package chess;

public enum ChessPiece {
	EMPTY(Player.BLACK),
	W_PAWN(Player.WHITE),
	W_ROOK(Player.WHITE),
	W_KNIGHT(Player.WHITE),
	W_BISHOP(Player.WHITE),
	W_QUEEN(Player.WHITE),
	W_KING(Player.WHITE),
	B_PAWN(Player.BLACK),
	B_ROOK(Player.BLACK),
	B_KNIGHT(Player.BLACK),
	B_BISHOP(Player.BLACK),
	B_QUEEN(Player.BLACK),
	B_KING(Player.BLACK),
	PIECE_TOTAL(Player.BLACK);
	
	boolean player;
	
	ChessPiece(boolean player) {
		this.player = player;
	}
	
	public boolean getColour() {
		return player;
	}
	
}
