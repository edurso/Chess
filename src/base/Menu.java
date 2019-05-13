package base;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;

import chess.Game;
import chess.GameStyle;
import user.Player;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

/**
 * This is the main menu that pops up when the application is launched.
 * @author edurso
 */
public class Menu extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Button that will exit the application
	 */
	private JButton quitButton;
	
	/**
	 * Button that launches the selection menu for single player options
	 */
	private JButton singleButton;
	
	/**
	 * Button that launches the selection menu for multiplayer options
	 */
	private JButton multiButton;
	
	/**
	 * Button that launches the selection menu for the player's setings
	 */
	private JButton settingsButton;
	
	/**
	 * Button that redirects to main menu
	 */
	private JButton back;
	
	/**
	 * Button that launches an easy single player game
	 */
	private JButton easy;
	
	/**
	 * Button that launches a medium single player game
	 */
	private JButton medium;
	
	/**
	 * Button that launches a hard single player game
	 */
	private JButton hard;
	
	/**
	 * Button that launches a local multiplayer game
	 */
	private JButton local;
	
	/**
	 * Button that launches an online multiplayer game
	 */
	private JButton online;
	
	/**
	 * Container for main menu buttons
	 */
	private JPanel menuPanel;
	
	/**
	 * Container for single player buttons
	 */
    private JPanel singlePanel;
    
    /**
     * Container for multiplayer buttons
     */
    private JPanel multiPanel;
    
    /**
     * Container for settings components
     */
    private JPanel settingsPanel;
	
    /**
     * Image for the background of the menu
     */
    private JLabel background;
    
    /**
     * Starts the menu at the main screen of the application
     */
	public void start() {
		ImageIcon image = new ImageIcon("/Chess/src/sprites/chess_background.png"); 
		background = new JLabel("/Chess/src/sprites/chess_background.png", image, JLabel.CENTER);
		background.setBounds(0,0,1000,1204);
		add(background);
		background.setVisible(true);
		
		setTitle("Chess");
		
		//background
		
		menuPanel = new JPanel();
	    menuPanel.setLayout(null);
	    
	    singlePanel = new JPanel();
	    singlePanel.setLayout(null);
	    
	    multiPanel = new JPanel();
	    multiPanel.setLayout(null);
	    
	    settingsPanel = new JPanel();
	    settingsPanel.setLayout(null);
	    
	    initMainMenuButtons();
	    initSettingsButtons();
	    initSingleplayerButtons();
	    initMultiplayerButtons();
	    
	    getContentPane().add(menuPanel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(1000,1204);
	    setResizable(false);
	    setVisible(true);
	}
	
	/**
	 * gets active players from the list of players
	 * @return the player who is playing on the white side
	 */
	public static Player getActiveWhitePlayer() {
		return null;
	}
	
	/**
	 * gets active players from the list of players
	 * @return the player who is playing on the black side
	 */
	public static Player getActiveBlackPlayer() {
		return null;
	}
	
	/**
	 * Configures the components for the main menu
	 */
	private void initMainMenuButtons() {
		
		menuPanel.add(background);
		background.setVisible(true);
		
		quitButton = new JButton("Quit");
	    quitButton.setBounds(400,800,200,80);
	    menuPanel.add(quitButton);
	    quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
	    
	    settingsButton = new JButton("Settings");
	    settingsButton.setBounds(400,650,200,80);
	    menuPanel.add(settingsButton);
	    settingsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getContentPane().removeAll();
				getContentPane().add(settingsPanel);
				initSettingsButtons();
			    setVisible(true);
			}
		});
	    
	    singleButton = new JButton("Single Player");
	    singleButton.setBounds(400,350,200,80);
	    menuPanel.add(singleButton);
	    singleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			getContentPane().removeAll();
			getContentPane().add(singlePanel);
			initSingleplayerButtons();
		    setVisible(true);
			}
		});
	    
	    multiButton = new JButton("Multiplayer");
	    multiButton.setBounds(400,500,200,80);
	    menuPanel.add(multiButton);
	    multiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				getContentPane().removeAll();
				getContentPane().add(multiPanel);
				initMultiplayerButtons();
			    setVisible(true);
			}
		});
	    
	}
	
	/**
	 * Configures the components for the settings menu
	 */
	private void initSettingsButtons() {
		settingsPanel.add(background);
		background.setVisible(true);
		//TODO - Implement
		addBack(settingsPanel);
	}
	
	/**
	 * Configures the components for the single player menu
	 */
	private void initSingleplayerButtons() {
		singlePanel.add(background);
		background.setVisible(true);
		easy = new JButton("Easy");
		easy.setBounds(400,350,200,80);
	    singlePanel.add(easy);
	    easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.SINGLE_EASY);
			}
		});
	    
	    medium = new JButton("Medium");
	    medium.setBounds(400,500,200,80);
	    singlePanel.add(medium);
	    medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.SINGLE_MED); 
			}
		});
	    
	    hard = new JButton("Hard");
	    hard.setBounds(400,650,200,80);
	    singlePanel.add(hard);
	    hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.SINGLE_HARD); 
			}
		});
	    
	    addBack(singlePanel);
	}
	
	/**
	 * Configures the components for the multiplayer menu
	 */
	private void initMultiplayerButtons() {
		multiPanel.add(background);
		background.setVisible(true);
		local = new JButton("Local");
		local.setBounds(400,500,200,80);
	    multiPanel.add(local);
	    local.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.MULTI_LOCAL);
			}
		});
	    
	    online = new JButton("Online");
	    online.setBounds(400,650,200,80);
	    multiPanel.add(online);
	    online.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.MULTI_ONLINE);
			}
		});
	    
	    addBack(multiPanel);
	}
	
	/**
	 * Adds a "go home" button to the inputed panel.
	 * @param panel the panel the home button will be added to
	 */
	private void addBack(JPanel panel) {
		back = new JButton("Go To Home");
	    back.setBounds(400,800,200,80);
	    panel.add(back);
	    back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			    setVisible(false);
				getContentPane().removeAll();
				getContentPane().add(menuPanel);
				initMainMenuButtons();
			    setVisible(true);
			}
		});
	}

}

