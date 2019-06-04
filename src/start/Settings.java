package start;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import chess.Game;
import user.User;

/**
 * Settings menu - accessed in game by clicking settings on main screen
 * contains all player functions as well as music and some other helpful things.
 * @author edurso
 */
public class Settings extends JPanel implements ItemListener {

    /**
	* Checkbox that enables and disables the music
	*/
	private JButton music;

	/**
	* Clip for the audio file
	*/
    private Clip clip;
    
    /**
     * Window for menu that selects active players
     */
    private JFrame selectPlayer;
    
    /**
     * Button that launches {@code selectPlayer}
     */
    private JButton selectPlayerButton;

    /**
     * map of all players indexed to their usernames
     */
    private HashMap<String, User> players;

    /**
     * selected white player
     */
    private static User activeWhitePlayer;

    /**
     * selected black player
     */
    private static User activeBlackPlayer;

    /**
     * drop-down menu of white players
     */
    private JComboBox<String> whiteSelect;

    /**
     * drop-down menu od black players
     */
    private JComboBox<String> blackSelect;

    /**
     * list of white user names
     */
    private ArrayList<String> whiteUsrNames;

    /**
     * list of black user names
     */
    private ArrayList<String> blackUsrNames;
    
    /**
     * container for all player data and options
     */
    private JPanel playerPanel;
    
    /**
     * container for all music-related things
     */
    private JPanel musicPanel;
    
    /**
     * container for black player information
     */
    private JPanel blackPlayerInfo;
    
    /**
     * container for white player information
     */
    private JPanel whitePlayerInfo;
    
    /**
     * graphic for the username of the selected black player
     */
    private JTextField sbp;
    
    /**
     * graphic for the username of the selected white player
     */
    private JTextField swp;
    
    /**
     * container for the selected white player's stats
     */
    private JPanel whiteStats;
    
    /**
     * container for the selected black player's stats
     */
    private JPanel blackStats;
    
    /**
     * graphic for the selected white players wins
     */
    private static JTextField whiteDubs;
    
    /**
     * graphic for the selected white players wins as a percent of their games played
     */
    private static JTextField whiteDubPercent;
    
    /**
     * graphic for the selected black players wins
     */
    private static JTextField blackDubs;
    
    /**
     * graphic for the selected black players wins as a percent of their games played
     */
    private static JTextField blackDubPercent;
    
    /**
     * graphic for the selected white player's username
     */
    private JLabel whiteMSG;
    
    /**
     * graphic for the selected black player's username
     */
    private JLabel blackMSG;
    
    /**
     * denotes {@code whiteDubs}
     */
    private JLabel whiteDubsLabel;
    
    /**
     * denotes {@code whiteDubPercent}
     */
    private JLabel whiteDubPercentLabel;
    
    /**
     * denotes {@code blackDubs}
     */
    private JLabel blackDubsLabel;
    
    /**
     * denotes {@code blackDubPercent}
     */
    private JLabel blackDubPercentLabel;
    
    /**
     * button that redirects to the applications start-up menu
     */
    private JButton back;

    /**
     * constructor
     * initializes usernames and settings menu options
     */
    public Settings() {
    	
        super(new GridLayout(3, 1)); 

        ArrayList<User> temp = User.getPlayers();
        players = new HashMap<>();
        whiteUsrNames = new ArrayList<>();
        blackUsrNames = new ArrayList<>();
        whiteUsrNames.clear();
        blackUsrNames.clear();
        for(int i = 0 ; i < temp.size() ; i++) {
            User p = temp.get(i);
            players.put(p.getUsername(), p);
            whiteUsrNames.add(p.getUsername());
            blackUsrNames.add(p.getUsername());
        }

        activeBlackPlayer = players.get(User.getActiveBlack());
        activeWhitePlayer = players.get(User.getActiveWhite());
        
        initMenuThings();
        
    }
    
    /**
     * updates the settings menu with the player names
     * @param whiteName white player name for menu
     * @param blackName black player name for menu
     */
    private void updatePlayersMenu(String whiteName, String blackName) {
    	swp.setText(whiteName);
    	sbp.setText(blackName);
    }

    /**
     * updates the main menu stats for the game
     * @param winsWhite white players number of wins
     * @param percentWinsWhite white players number of wins as a percent
     * @param winsBlack black players number of wins
     * @param percentWinsBlack black players number of wins as a percent
     */
    public static void updatePlayersMenuStats(String winsWhite, String percentWinsWhite, String winsBlack, String percentWinsBlack) {
    	whiteDubs.setText(winsWhite);
        whiteDubPercent.setText(percentWinsWhite);
        blackDubs.setText(winsBlack);
    	blackDubPercent.setText(percentWinsBlack);
    }

    /**
     * retrieves the selected white player
     * @return the active white player
     */
    public static User getActiveWhitePlayer(){ return activeWhitePlayer; }
    
    /**
     * retrieves the selected black player
     * @return the active black player
     */
    public static User getActiveBlackPlayer(){ return activeBlackPlayer; }

    /**
     * initializes and reveals the window for selecting players
     */
    private void initPlayerSelect(){

    	players.clear();
        ArrayList<User> temp = User.getPlayers();
        whiteUsrNames.clear();
        blackUsrNames.clear();
        for(int i = 0 ; i < temp.size() ; i++) {
            User p = temp.get(i);
            players.put(p.getUsername(), p);
            whiteUsrNames.add(p.getUsername());
            blackUsrNames.add(p.getUsername());
        }

        selectPlayer = new JFrame();
        selectPlayer.setTitle("Chess: Select Players");
        selectPlayer.setBounds(100, 100, 600, 200);
        selectPlayer.setBackground(Color.BLACK);

        GridLayout gl = new GridLayout();
        gl.setRows(3);
        gl.setColumns(1);

        JPanel p = new JPanel(gl);
        JPanel white = new JPanel();
        white.setBackground(Color.BLACK);
        JPanel black = new JPanel();
        black.setBackground(Color.BLACK);

        JLabel whitePrompt = new JLabel("Select White Player");
        whitePrompt.setForeground(Color.RED);
        whitePrompt.setBackground(Color.BLACK);
        JLabel blackPrompt = new JLabel("Select Black Player");
        blackPrompt.setForeground(Color.RED);
        blackPrompt.setBackground(Color.BLACK);

        whiteMSG = new JLabel("Selected White Player is GuestWhitePlayer");
        whiteMSG.setForeground(Color.RED);
        whiteMSG.setBackground(Color.BLACK);
        blackMSG = new JLabel("Selected Black Player is GuestBlackPlayer");
        blackMSG.setForeground(Color.RED);
        blackMSG.setBackground(Color.BLACK);

        whiteSelect = new JComboBox<String>();
        whiteSelect.setModel(new DefaultComboBoxModel(whiteUsrNames.toArray()));
        whiteSelect.addItemListener(this);
        whiteSelect.setBackground(Color.RED);

        blackSelect = new JComboBox<String>();
        blackSelect.setModel(new DefaultComboBoxModel(blackUsrNames.toArray()));
        blackSelect.addItemListener(this);
        blackSelect.setBackground(Color.RED);

        white.add(whitePrompt);
        white.add(whiteSelect);
        white.add(whiteMSG);

        black.add(blackPrompt);
        black.add(blackSelect);
        black.add(blackMSG);

        JButton save = new JButton("Save Selections");
        save.setBackground(Color.RED);
        save.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                Object w = whiteSelect.getSelectedItem();
                Object b = blackSelect.getSelectedItem();
                activeWhitePlayer = players.get(w);
                activeBlackPlayer = players.get(b);
                
                selectPlayer.dispose();
                
                updatePlayersMenu((String) w, (String) b);
                
                updatePlayersMenuStats(""+activeWhitePlayer.getGamesWon(), 
                		""+activeWhitePlayer.getWinPercent(), 
                		""+activeBlackPlayer.getGamesWon(), 
                		""+activeBlackPlayer.getWinPercent());
            } 
        });

        JButton newPlayer = new JButton("Create New Player");
        newPlayer.setBackground(Color.RED);
        newPlayer.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                selectPlayer.dispose();
                newPlayerMenu(); 
            } 
        });

        JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.RED);
        cancel.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { selectPlayer.dispose(); } });

        p.setBackground(Color.BLACK);
        p.add(white);
        p.add(black);
        JPanel otherOne = new JPanel(new FlowLayout());
        otherOne.setBackground(Color.BLACK);
        otherOne.add(save);
        otherOne.add(newPlayer);
        otherOne.add(cancel);
        p.add(otherOne);
        selectPlayer.add(p);
        selectPlayer.setVisible(true);

        
    }

    /**
     * updates selected player names in the player selection window after they are clicked
     */
    public void itemStateChanged(ItemEvent e) { 
        if (e.getSource() == whiteSelect) whiteMSG.setText("Selected White Player is " + whiteSelect.getSelectedItem());
        if (e.getSource() == blackSelect) blackMSG.setText("Selected Black Player is " + blackSelect.getSelectedItem());  
    } 

    /**
     * initializes and reveals the menu for creating a new player
     */
    private void newPlayerMenu(){
        JFrame f = new JFrame();
        f.setTitle("Chess: Create Player");
        f.setBounds(100, 100, 600, 200);

        GridLayout gl = new GridLayout(2, 1);
        JPanel main = new JPanel(gl);
        main.setBackground(Color.BLACK);

        JPanel inputUsrName = new JPanel();
        inputUsrName.setBackground(Color.BLACK);
        JLabel prompt = new JLabel("Username: ");
        prompt.setForeground(Color.RED);
        JTextField input = new JTextField("AngryElectonicGardenGnome09");
        input.setBackground(Color.BLACK);
        input.setForeground(Color.RED);
        input.setEditable(true);

        inputUsrName.add(prompt);
        inputUsrName.add(input);
        inputUsrName.setVisible(true);
        
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setBackground(Color.BLACK);
        JButton save = new JButton("Save");
        save.setBackground(Color.RED);
        save.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                String un = input.getText();
                User p = new User(un);
                p.savePlayerData();
                f.dispose();
                initPlayerSelect();
            } 
        });
        JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.RED);
        cancel.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                f.dispose(); 
                initPlayerSelect();
            } 
        });
        buttons.add(save);
        buttons.add(cancel);
        buttons.setVisible(true);

        main.add(inputUsrName);
        main.add(buttons);
        main.setVisible(true);

        f.add(main);

        f.setVisible(true);
    }
    
    /**
     * initializes the main, settings menu components
     */
    private void initMenuThings() {
    	JPanel selPlayerP = new JPanel(new FlowLayout());
    	selPlayerP.setBackground(Color.RED);
    	selectPlayerButton = new JButton("Select Players");
	    selectPlayerButton.setBounds(400,800,200,80);
        selectPlayerButton.setBackground(Color.BLACK);
        selectPlayerButton.setForeground(Color.RED);
        selectPlayerButton.setFont(Menu.f);
        selectPlayerButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { initPlayerSelect(); } });        
        selPlayerP.add(selectPlayerButton);
        
		music = new JButton("Enable Music");
		music.setBounds(450,500,100,50);
        music.setBackground(Color.BLACK);
        music.setForeground(Color.RED);
        music.setFont(Menu.f);
        music.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {play();}});
        
        whiteDubs = new JTextField();
        whiteDubs.setEditable(false);
        whiteDubs.setBackground(Color.RED);
        whiteDubPercent = new JTextField();
        whiteDubPercent.setEditable(false);
        whiteDubPercent.setBackground(Color.RED);
        blackDubs = new JTextField();
        blackDubs.setEditable(false);
        blackDubs.setBackground(Color.RED);
        blackDubPercent = new JTextField();
        blackDubPercent.setEditable(false);
        blackDubPercent.setBackground(Color.RED);
        
        whiteStats = new JPanel(new GridLayout(2, 2));
        whiteDubsLabel = new JLabel("Wins: ");
        whiteDubsLabel.setBackground(Color.RED);
        if(activeWhitePlayer == null) whiteDubs.setText("Unavailable");
        else whiteDubs.setText(""+activeWhitePlayer.getGamesWon());
        whiteDubPercentLabel = new JLabel("Win Percent: ");
        whiteDubPercentLabel.setBackground(Color.RED);
        if(activeWhitePlayer == null) whiteDubPercent.setText("Unavailable");
        else whiteDubPercent.setText(""+activeWhitePlayer.getWinPercent());
        whiteStats.add(whiteDubsLabel);
        whiteStats.add(whiteDubs);
        whiteStats.add(whiteDubPercentLabel);
        whiteStats.add(whiteDubPercent);
        
        blackStats = new JPanel(new GridLayout(2, 2));
        blackDubsLabel = new JLabel("Wins: ");
        blackDubsLabel.setBackground(Color.RED);
        if(activeBlackPlayer == null) blackDubs.setText("Unavailable");
        else blackDubs.setText(""+activeBlackPlayer.getGamesWon());
        blackDubPercentLabel = new JLabel("Win Percent: ");
        blackDubPercentLabel.setBackground(Color.RED);
        if(activeBlackPlayer == null) blackDubPercent.setText("Unavailable");
        else blackDubPercent.setText(""+activeBlackPlayer.getWinPercent());
        blackStats.add(blackDubsLabel);
        blackStats.add(blackDubs);
        blackStats.add(blackDubPercentLabel);
        blackStats.add(blackDubPercent);
        
        swp = new JTextField();
        swp.setEditable(false);
        if(activeWhitePlayer == null) swp.setText("GuestWhitePlayer");
        else swp.setText(activeWhitePlayer.getUsername());
        swp.setBackground(Color.RED);
        
        sbp = new JTextField();
        sbp.setEditable(false);
        if(activeBlackPlayer == null) sbp.setText("GuestBlackPlayer");
        else sbp.setText(activeBlackPlayer.getUsername());
        sbp.setBackground(Color.RED);
        
        whitePlayerInfo = new JPanel(new FlowLayout());
        whitePlayerInfo.setBackground(Color.RED);
        JLabel whiteHeader = new JLabel("Active White Player: ");
        whitePlayerInfo.add(whiteHeader);
        whitePlayerInfo.add(swp);
        whitePlayerInfo.add(whiteStats);
        
        blackPlayerInfo = new JPanel(new FlowLayout());
        blackPlayerInfo.setBackground(Color.RED);
        JLabel blackHeader = new JLabel("Active Black Player: ");
        blackPlayerInfo.add(blackHeader);
        blackPlayerInfo.add(sbp);
        blackPlayerInfo.add(blackStats);

        playerPanel = new JPanel(new GridLayout(1, 3));
        playerPanel.setBackground(Color.RED);
        playerPanel.add(whitePlayerInfo);
        playerPanel.add(blackPlayerInfo);
        playerPanel.add(selPlayerP);
        
        musicPanel = new JPanel(new FlowLayout());
        musicPanel.setBackground(Color.RED);
        musicPanel.add(music);
        
        JPanel goHome = new JPanel();
        goHome.setBackground(Color.RED);
        back = new JButton("Go To Home");
	    back.setBounds(400,800,200,80);
	    back.setBackground(Color.BLACK);
	    back.setForeground(Color.RED);
	    back.setFont(Menu.f);
	    goHome.add(back);
	    back.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) { Game.menu.goToHome(); } });
        
		this.setBackground(Color.RED);
        this.add(playerPanel);
        this.add(musicPanel);
        this.add(goHome);
    }

    /**
     * Method to play music
     */
    private void play() {
    	try {
    		clip = AudioSystem.getClip();
    		clip.open(AudioSystem.getAudioInputStream(getClass().getResource("music.wav")));
    		clip.loop(Clip.LOOP_CONTINUOUSLY);
    	} catch(Exception e) {
            Game.setErrorText("error finding music file");
            Game.revealErrorWindow();
    	}
    	if(clip.isRunning()) stopMusic(clip);
    	else startMusic(clip);    	
    }
    
    /**
     * Method that starts music
     */
    private void startMusic(Clip c) { c.start(); }
    
    /**
     * Method that stops music
     */
    private void stopMusic(Clip c) { c.stop(); }

}