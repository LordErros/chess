package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -536187845371875202L;
	
	static final String IMG_PATH = "images/";
	//static final int TOTAL_IMAGES = 1;
	ImageIcon[] images; 
	
	ChessPiece[][] board;
	
	public ChessBoard() {
		super();
		initImages();
		initBoard();
	}
	
	public void initImages() {
		images = new ImageIcon[ChessPiece.PIECE_TOTAL.ordinal()];
		ChessPiece[] chessvals = ChessPiece.values();
		for(int i = 0; i < ChessPiece.PIECE_TOTAL.ordinal(); i++) {
			images[i] = new ImageIcon(IMG_PATH + chessvals[i].toString() + ".png");
		}
	}
	
	public void initBoard() {
		board = new ChessPiece[8][8];
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				g.setColor((x%2 == y%2)?  Color.GRAY : Color.DARK_GRAY);
				g.fillRect(x*40, y*40, 40, 40);
				if(board[y][x] != ChessPiece.EMPTY) {
					images[board[y][x].ordinal()].paintIcon(this, g, x*40, y*40);
				}
			}
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(320,320);
	}
	
}
