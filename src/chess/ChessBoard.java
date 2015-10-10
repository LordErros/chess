package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoard extends JPanel {
	static final String IMG_PATH = "images/";
	//static final int TOTAL_IMAGES = 1;
	ImageIcon[] images; 
	
	ChessPiece[][] board;
	
	public ChessBoard() {
		super();
		images = new ImageIcon[ChessPiece.PIECE_TOTAL.ordinal()];
		images[ChessPiece.B_PAWN.ordinal()] = new ImageIcon(IMG_PATH + "b_pawn.png");
		
		board = new ChessPiece[8][8];
		for(int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				board[y][x] = (x % 2 == y % 2)? ChessPiece.B_PAWN : ChessPiece.EMPTY;
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
