package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.Menu;

public class Game {
	
	private static JFrame errorFrame;
	private static JPanel errorPanel;
	private static JTextField errorText;
	private static JButton errorButton;
	private static Menu m;
	
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
				
				break;
			case MULTI_ONLINE:
				errorFrame.setVisible(true);
				break;
			default:
				errorFrame.setVisible(true);
				break;
		}
	}
	
	public static void error() { errorFrame.setVisible(true); }
	
	public static void killErrorWindow() { errorFrame.dispose(); }
	
	private static void initErrorWindow() {
		errorFrame = new JFrame();
		errorFrame.setVisible(false);
		errorPanel = new JPanel();
		errorFrame.setTitle("Unexpected Error Encountered");
		errorFrame.setBounds(0, 0, 500, 250);
		errorText = new JTextField();
		errorText.setText("An Unexpected Error Has Occured. We Reccomend Restarting the Application");
		errorText.setEditable(false);
		m = new Menu();
		errorButton = new JButton("Restart");
		//errorButton.setBounds(400,800,200,80);
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
	
}
