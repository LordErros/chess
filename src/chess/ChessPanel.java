package chess;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2080570957860465257L;
	
	private ChessBoard board;
	private JLabel title;
	
	public ChessPanel() {
		super();
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		title = new JLabel("Play Chess!");
		add(title);
		
		board = new ChessBoard();
		add(board);
		board.setSize(getPreferredSize());
		
		//Add struts
		//add(Box.createHorizontalStrut(40), BorderLayout.WEST);
		//add(Box.createHorizontalStrut(40), BorderLayout.EAST);
	}

	
	public Dimension getPreferredSize() {
		return new Dimension(400,500);
	}
	
}
