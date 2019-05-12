package base;
import javax.imageio.ImageIO;
import java.awt.*;
import javax.swing.*;

import chess.Game;
import chess.GameStyle;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*
;
public class Menu extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JButton quitButton;
	private JButton singleButton;
	private JButton multiButton;
	private JButton settingsButton;
	private JButton back;
	
	private JButton easy, medium, hard;
	private JButton local, online;
	
	private JPanel menuPanel;
    private JPanel singlePanel;
    private JPanel multiPanel;
    private JPanel settingsPanel;
	
    private JScrollBar volumeScroll;
    JLabel background;
    
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
	
	private void initSettingsButtons() {
		settingsPanel.add(background);
		background.setVisible(true);
		//TODO - Implement
		addBack(settingsPanel);
	}
	
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

