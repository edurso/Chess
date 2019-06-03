package start;
import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;

import java.awt.*;
import javax.swing.*;

import chess.Game;
import chess.GameStyle;
import user.User;

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
	 * settings for menu button
	 */
	private Settings settings;
	 
    /**
     * Universal font for project
     */
    public static Font f = new Font("TimesRoman", Font.PLAIN, 20);
    
   	/**
     * Starts the menu at the main screen of the application
     */
	public void start() {

		setTitle("Chess");
		
		menuPanel = new JPanel();
	   	menuPanel.setLayout(null);
	   
	   	multiPanel = new JPanel();
	   	multiPanel.setLayout(null);
	    
	   	settingsPanel = new JPanel();
	   	settingsPanel.setLayout(null);
	    
	   	getContentPane().add(menuPanel);
	   	ImageIcon image = new ImageIcon(getClass().getResource("chess_background.png")); 
		background = new JLabel(image, JLabel.CENTER);
		background.setBounds(0,0,1000,1204);

	   	initMainMenuButtons();
	    
	   	settings = new Settings();
	   
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000,1204);
	   	setResizable(false);
	   	setVisible(true);
	}
	
	/**
	 * gets active players from the list of players
	 * @return the player who is playing on the white side
	 */
	public static User getActiveWhitePlayer() { return Settings.getActiveWhitePlayer(); }
	
	/**
	 * gets active players from the list of players
	 * @return the player who is playing on the black side
	 */
	public static User getActiveBlackPlayer() { return Settings.getActiveBlackPlayer(); }
	
	/**
	 * Configures the components for the main menu
	 */
	private void initMainMenuButtons() {

	    quitButton = new JButton("Quit");
	    quitButton.setBounds(400,800,200,80);
	    quitButton.setBackground(Color.RED);
	    quitButton.setFont(f);
	    menuPanel.add(quitButton);
	    quitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.exit(0); 
			}
		});
	    
	    settingsButton = new JButton("Settings");
	    settingsButton.setBounds(400,650,200,80);
	    settingsButton.setBackground(Color.RED);
	    settingsButton.setFont(f);
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
	    singleButton.setBackground(Color.RED);
	    singleButton.setFont(f);
	    menuPanel.add(singleButton);
	    singleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.SINGLE);
			}
		});
	    
	    multiButton = new JButton("Multiplayer");
	    multiButton.setBounds(400,500,200,80);
	    multiButton.setBackground(Color.RED);
	    multiButton.setFont(f);
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
	    
		menuPanel.add(background);
		background.setVisible(true);
		
	}
	
	/**
	 * keeps track to make sure multiple go to home buttons are not added
	 */
	int count = 0;

	/**
	 * Configures the components for the settings menu
	 */
	private void initSettingsButtons() {
		settings.setBounds(0, 301, 1000, 903);
		settings.setVisible(true);

		//if (count == 0) addBack(settings);
		settingsPanel.add(settings);
		settingsPanel.add(background);
		background.setVisible(true);
		count++;
	}
	
	/**
	 * Configures the components for the multiplayer menu
	 */
	private void initMultiplayerButtons() {
		
		local = new JButton("Local");
		local.setBounds(400,500,200,80);
	    local.setBackground(Color.RED);
	    local.setFont(f);
	    multiPanel.add(local);
	    local.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.MULTI_LOCAL);
			}
		});
	    
	    online = new JButton("Online");
	    online.setBounds(400,650,200,80);
	    online.setBackground(Color.RED);
	    online.setFont(f);
	    multiPanel.add(online);
	    online.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				dispose();
				Game.run(GameStyle.MULTI_ONLINE);
			}
		});
	    addBack(multiPanel);
	    multiPanel.add(background);
		background.setVisible(true);
	}
	
	/**
	 * Adds a "go home" button to the inputed panel.
	 * @param panel the panel the home button will be added to
	 */
	private void addBack(JPanel panel) {
		back = new JButton("Go To Home");
	    back.setBounds(400,800,200,80);
	    back.setBackground(Color.RED);
	    back.setFont(f);
	    panel.add(back);
	    back.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) { goToHome(); } });
	}
	
	/**
	 * Redirects to the main menu of the application
	 */
	protected void goToHome() {
		setVisible(false);
		getContentPane().removeAll();
		getContentPane().add(menuPanel);
		initMainMenuButtons();
	    setVisible(true);
	}

}

