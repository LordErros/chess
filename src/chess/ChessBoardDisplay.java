package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
//import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoardDisplay extends JPanel implements MouseListener {
	
	private static final long serialVersionUID = -536187845371875202L;
	
	static final String IMG_PATH = "images/";
	//static final int TOTAL_IMAGES = 1;
	ImageIcon[] images; 
	
	ChessGame game;
	
	Point selectedCell;
	
	public ChessBoardDisplay() {
		super();
		initImages();
		game = new ChessGame();
		
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
					else {
						Move mv = new Move(selectedCell, new Point(x,y));
						if(MoveValidation.isValid(mv, game.getBoard(), game.getCurrentPlayer(), false)) {
							if(mv.isCastle()) {
								g.setColor(Color.YELLOW);
							}
							else {
								g.setColor(Color.BLUE);
							}
						}
						
					}
				}
				g.fillRect(x*40, y*40, 40, 40);
				if(game.getBoard().get(x, y) != ChessPiece.EMPTY) {
					images[game.getBoard().get(x, y).ordinal()].paintIcon(this, g, x*40, y*40);
				}
			}
		}
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(320,320);
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
				game.performMove(new Move(selectedCell, dest));
				selectedCell = null;
			}
			else if(game.getBoard().get(e.getX() / 40, e.getY() / 40).getColour() == game.getCurrentPlayer().getColour()) {
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
