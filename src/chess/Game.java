package chess;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import start.*;
import user.SimpleAI;

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
	 * menu to start from
	 */
	public static Menu menu = new Menu();
	
	/**
	 * board used for game
	 */
	public static Board board;

	/**
	 * ai
	 */
	public static SimpleAI aiboi = new SimpleAI();

	/**
	 * starts the game at the menu
	 */
	public static void start(){ menu.start(); }
	
	/**
	 * runs the game of the specified {@code style}
	 * @param style style in which to run the game
	 */
	public static void run(GameStyle style) {
		
		initErrorWindow();
		
		switch(style) {
			case SINGLE:
				board = new Board(Settings.getActiveWhitePlayer(), aiboi);
				board.play();
				break;
			case MULTI_LOCAL:
				board = new Board(Settings.getActiveWhitePlayer(), Settings.getActiveBlackPlayer());
				board.play();
				break;
			case MULTI_ONLINE:
				OnlineMenu.launch();
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
		errorPanel.setBackground(Color.BLACK);
		errorFrame.setTitle("Unexpected Error");
		errorFrame.setBounds(500, 500, 500, 250);
		errorText = new JTextField();
		errorText.setBackground(Color.BLACK);
		errorText.setForeground(Color.RED);
		errorText.setText("An Unexpected Error Has Occured. We Reccomend Restarting the Application");
		errorText.setEditable(false);
		menu = new Menu();
		errorButton = new JButton("Restart");
		errorButton.setBackground(Color.RED);
	    errorButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				errorFrame.dispose();
			    menu.start();
			}
		});
		errorPanel.add(errorText);
	    errorPanel.add(errorButton);
		errorFrame.add(errorPanel);
	}
	
	/**
	 * sets the text in the error window to the specified {@code msg}
	 * @param msg error message to show
	 */
	public static void setErrorText(String msg) { errorText.setText(msg); }
	
}
