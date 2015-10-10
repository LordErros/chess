package chess;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box;

public class ChessFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981708911796843769L;
	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessFrame frame = new ChessFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChessFrame() {
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 500);
		setResizable(false);
		mainPanel = new ChessPanel();
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0,0));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		contentPane.add(Box.createHorizontalStrut(35), BorderLayout.WEST);
		setContentPane(contentPane);
	}

}
