package base;
import javax.imageio.ImageIO;
import javax.swing.*;

import chess.Game;
import chess.GameStyle;

import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Menu {
	
	private JFrame mainFrame;
	
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
		
		mainFrame = new JFrame();
		
		mainFrame.setTitle("Chess");
		
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
	    
	    mainFrame.getContentPane().add(menuPanel);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setSize(1000,1204);
	    mainFrame.setResizable(false);
	    mainFrame.setVisible(true);
	}
	
	private void initMainMenuButtons() {
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
				mainFrame.setVisible(false);
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(settingsPanel);
				initSettingsButtons();
			    mainFrame.setVisible(true);
			}
		});
	    
	    singleButton = new JButton("Single Player");
	    singleButton.setBounds(400,350,200,80);
	    menuPanel.add(singleButton);
	    singleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
			mainFrame.getContentPane().removeAll();
			mainFrame.getContentPane().add(singlePanel);
			initSingleplayerButtons();
		    mainFrame.setVisible(true);
			}
		});
	    
	    multiButton = new JButton("Multiplayer");
	    multiButton.setBounds(400,500,200,80);
	    menuPanel.add(multiButton);
	    multiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(multiPanel);
				initMultiplayerButtons();
			    mainFrame.setVisible(true);
			}
		});
	    
	}
	
	private void initSettingsButtons() {
		//TODO - Implement this soon.
		addBack(settingsPanel);
	}
	
	private void initSingleplayerButtons() {
		easy = new JButton("Easy");
		easy.setBounds(400,350,200,80);
	    singlePanel.add(easy);
	    easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				Game.run(GameStyle.SINGLE_EASY);
			}
		});
	    
	    medium = new JButton("Medium");
	    medium.setBounds(400,500,200,80);
	    singlePanel.add(medium);
	    medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				Game.run(GameStyle.SINGLE_MED); 
			}
		});
	    
	    hard = new JButton("Hard");
	    hard.setBounds(400,650,200,80);
	    singlePanel.add(hard);
	    hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				Game.run(GameStyle.SINGLE_HARD); 
			}
		});
	    
	    addBack(singlePanel);
	}
	
	private void initMultiplayerButtons() {
		local = new JButton("Local");
		local.setBounds(400,500,200,80);
	    multiPanel.add(local);
	    local.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				Game.run(GameStyle.MULTI_LOCAL);
			}
		});
	    
	    online = new JButton("Online");
	    online.setBounds(400,650,200,80);
	    multiPanel.add(online);
	    online.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
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
			    mainFrame.setVisible(false);
				mainFrame.getContentPane().removeAll();
				mainFrame.getContentPane().add(menuPanel);
				initMainMenuButtons();
			    mainFrame.setVisible(true);
			}
		});
	}

}
