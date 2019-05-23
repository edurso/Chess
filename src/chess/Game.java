package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.Menu;

/**
 * Class that runs the game
 * @author edurso
 */
public class Game {
	
	/**
	 * window used to show an error that as occured
	 */
	private static JFrame errorFrame;
	
	/**
	 * container for components involving error on {@code errorFrame}
	 */
	private static JPanel errorPanel;
	
	/**
	 * text of error message
	 */
	private static JTextField errorText;
	
	/**
	 * button to handle error - restart
	 */
	private static JButton errorButton;
	
	/**
	 * menu to restart from
	 */
	private static Menu m;
	
	/**
	 * board used for game
	 */
	private static Board mainBoard;
	
	/**
	 * runs the game of the specified {@code style}
	 * @param style style in which to run the game
	 */
	public static void run(GameStyle style) {
		
		initErrorWindow();
		
		switch(style) {
			case SINGLE_EASY:
				
				break;
			case SINGLE_MED:
				
				break;
			case SINGLE_HARD:
				
				break;
			case MULTI_LOCAL:
				mainBoard = new Board(Menu.getActiveWhitePlayer(), Menu.getActiveBlackPlayer());
				mainBoard.play();
				break;
			case MULTI_ONLINE:
				errorFrame.setVisible(true);
				break;
			default:
				errorFrame.setVisible(true);
				break;
		}
	}
	
	/**
	 * shows the error window and error
	 */
	public static void revealErrorWindow() { errorFrame.setVisible(true); }
	
	/**
	 * removes the error window
	 */
	public static void killErrorWindow() { errorFrame.dispose(); }
	
	/**
	 * initializes the error window with a default message
	 */
	private static void initErrorWindow() {
		errorFrame = new JFrame();
		errorFrame.setVisible(false);
		errorPanel = new JPanel();
		errorFrame.setTitle("Unexpected Error Encountered");
		errorFrame.setBounds(500, 500, 500, 250);
		errorText = new JTextField();
		errorText.setText("An Unexpected Error Has Occured. We Reccomend Restarting the Application");
		errorText.setEditable(false);
		m = new Menu();
		errorButton = new JButton("Restart");
	    errorPanel.add(errorButton);
	    errorButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				errorFrame.dispose();
			    m.start();
			}
		});
		errorPanel.add(errorText);
		errorFrame.add(errorPanel);
	}
	
	/**
	 * sets the text in the error window to the specified {@code msg}
	 * @param msg error message to show
	 */
	public static void setErrorText(String msg) { errorText.setText(msg); }
	
}
