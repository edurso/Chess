package base;
import javax.swing.*;
import java.awt.event.*;
public class Menu {
	
	private JFrame mainFrame;
	
	private JButton quitButton;
	private JButton singleButton;
	private JButton multiButton;
	private JButton settingsButton;
	
	private JPanel menuPanel;
    private JPanel singlePanel;
    private JPanel multiPanel;
    private JPanel settingsPanel;
	
    private JScrollBar volumeScroll;
	public void start() {
		
		mainFrame = new JFrame();
		
		mainFrame.setTitle("Chess");
		
		menuPanel = new JPanel();
	    menuPanel.setLayout(null);
	    
	    singlePanel = new JPanel();
	    menuPanel.setLayout(null);
	    
	    multiPanel = new JPanel();
	    menuPanel.setLayout(null);
	    
	    settingsPanel = new JPanel();
	    menuPanel.setLayout(null);
	    
	    initMainMenuButtons();
	    initSettingsButtons();
	    
	    mainFrame.getContentPane().add(menuPanel);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setSize(1000,1002);
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
				mainFrame.getContentPane().remove(menuPanel);
				mainFrame.getContentPane().add(settingsPanel);
			    mainFrame.setVisible(true);
			}
		});
	    
	    singleButton = new JButton("Single Player");
	    singleButton.setBounds(400,350,200,80);
	    menuPanel.add(singleButton);
	    singleButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().remove(menuPanel);
				mainFrame.getContentPane().add(singlePanel);
			    mainFrame.setVisible(true);
			}
		});
	    
	    multiButton = new JButton("Multiplayer");
	    multiButton.setBounds(400,500,200,80);
	    menuPanel.add(multiButton);
	    multiButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				mainFrame.getContentPane().remove(menuPanel);
				mainFrame.getContentPane().add(multiPanel);
			    mainFrame.setVisible(true);
			}
		});
	    
	}
	
	private void initSettingsButtons() {
		volumeScroll = new JScrollBar();
		volumeScroll.setName("Volume");
		volumeScroll.setBounds(100, 100, 200, 200);
		settingsPanel.add(volumeScroll);
	}
	
}
