package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -536187845371875202L;
	
	static final String IMG_PATH = "images/";
	//static final int TOTAL_IMAGES = 1;
	ImageIcon[] images; 
	
	ChessPiece[][] board;
	
	Player whitePlayer;
	Player blackPlayer;
	Player currentPlayer;
	
	Point selectedCell;
	
	public ChessBoard() {
		super();
		initImages();
		initBoard();
		
		whitePlayer = new HumanPlayer(Player.WHITE);
		blackPlayer = new HumanPlayer(Player.BLACK);
		currentPlayer = whitePlayer;
		currentPlayer.activate();
		
		validate();
		repaint();
		
		addMouseListener(this);
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
				if(selectedCell != null) {
					if(x == selectedCell.getX() && y == selectedCell.getY()) {
						g.setColor(Color.GREEN);
					}
				}
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

	
	public void performMove(Move move) {
		System.out.println("Performing move...");
		if(MoveValidation.isValid(move, board, currentPlayer)) {
//			board[(int)move.getDestination().getY()][(int)move.getDestination().getX()] = board[(int)move.getStart().getY()][(int)move.getStart().getX()];
//			board[(int)move.getStart().getY()][(int)move.getStart().getX()] = ChessPiece.EMPTY;
			board = MoveValidation.doMove(move, board);
			validate();
			repaint();
			if(currentPlayer == whitePlayer) {
				currentPlayer = blackPlayer;
			}
			else if(currentPlayer == blackPlayer) {
				currentPlayer = whitePlayer;
			}
		}
		else {System.out.println("Failed - move is not valid.");}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getX() < getPreferredSize().getWidth() && e.getY() < getPreferredSize().getHeight()) {
			if(selectedCell != null) {
				Point dest = new Point(e.getX() / 40, e.getY() / 40);
				performMove(new Move(selectedCell, dest));
				selectedCell = null;
			}
			else if(board[e.getY() / 40][e.getX() / 40].getColour() == currentPlayer.getColour()) {
				selectedCell = new Point(e.getX() / 40, e.getY() / 40);
			}
			validate();
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


}
