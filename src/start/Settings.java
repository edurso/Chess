package start;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.Clip;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import user.Player;

public class Settings extends JPanel implements ItemListener {

    /**
	* Checkbox that enables and disables the music
	*/
	private JCheckBox music;

	/**
	* Clip for the audio file
	*/
    private Clip clip;
    
    private JFrame selectPlayer;

    private JButton selectPlayerButton;

    HashMap<String, Player> players;

    private static Player activeWhitePlayer;

    private static Player activeBlackPlayer;

    private JComboBox<String> whiteSelect;

    private JComboBox<String> blackSelect;

    private ArrayList<String> whiteUsrNames;

    private ArrayList<String> blackUsrNames;
    
    private JPanel playerPanel;
    
    private JPanel musicPanel;
    
    private JPanel blackPlayerInfo;
    
    private JPanel whitePlayerInfo;
    
    private JTextField sbp;
    
    private JTextField swp;
    
    private JPanel whiteStats;
    
    private JPanel blackStats;
    
    private static JTextField whiteDubs;
    
    private static JTextField whiteDubPercent;
    
    private static JTextField blackDubs;
    
    private static JTextField blackDubPercent;

    public Settings() {
    	
        super(new GridLayout(3, 1));
        
        whiteDubs = new JTextField();
        whiteDubs.setEditable(false);
        whiteDubPercent = new JTextField();
        whiteDubPercent.setEditable(false);
        blackDubs = new JTextField();
        blackDubs.setEditable(false);
        blackDubPercent = new JTextField();
        blackDubPercent.setEditable(false);

        activeBlackPlayer = null;//never getting set prior to the game starting
        activeWhitePlayer = null;

        ArrayList<Player> temp = Player.getPlayers();
        players = new HashMap<>();
        whiteUsrNames = new ArrayList<>();
        blackUsrNames = new ArrayList<>();

        selectPlayerButton = new JButton("Select Players");
	    selectPlayerButton.setBounds(400,800,200,80);
        selectPlayerButton.setBackground(Color.RED);
        selectPlayerButton.setFont(Menu.f);
        selectPlayerButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { initPlayerSelect(); } });        
        
		music = new JCheckBox("Enable Music");
		music.setBounds(450,500,100,50);	
		music.setBorderPaintedFlat(false);
        music.setBackground(Color.RED);
        music.setFont(Menu.f);
        
        whiteStats = new JPanel(new GridLayout(2, 2));
        JLabel whiteDubsLabel = new JLabel("Wins: ");
        whiteDubs.setText("Unavailable");
        JLabel whiteDubPercentLabel = new JLabel("Win Percent: ");
        whiteDubPercent.setText("Unavailable");
        whiteStats.add(whiteDubsLabel);
        whiteStats.add(whiteDubs);
        whiteStats.add(whiteDubPercentLabel);
        whiteStats.add(whiteDubPercent);
        
        blackStats = new JPanel(new GridLayout(2, 2));
        JLabel blackDubsLabel = new JLabel("Wins: ");
        blackDubs.setText("Unavailable");
        JLabel blackDubPercentLabel = new JLabel("Win Percent: ");
        blackDubPercent.setText("Unavailable");
        blackStats.add(blackDubsLabel);
        blackStats.add(blackDubs);
        blackStats.add(blackDubPercentLabel);
        blackStats.add(blackDubPercent);
        
        swp = new JTextField();
        swp.setEditable(false);
        swp.setText("GuestWhitePlayer");
        swp.setBackground(Color.RED);
        
        sbp = new JTextField();
        sbp.setEditable(false);
        sbp.setText("GuestBlackPlayer");
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
        
        musicPanel = new JPanel(new FlowLayout());
        musicPanel.setBackground(Color.RED);
        musicPanel.add(music);
        
        playerPanel = new JPanel(new GridLayout(1, 3));
        playerPanel.setBackground(Color.RED);
        playerPanel.add(whitePlayerInfo);
        playerPanel.add(blackPlayerInfo);
        playerPanel.add(selectPlayerButton);
        

		this.setBackground(Color.RED);
        this.add(playerPanel);
        this.add(musicPanel);
        
    }
    
    private void updatePlayersMenu(String whiteName, String blackName) {
    	swp.setText(whiteName);
    	sbp.setText(blackName);
    }

    public static void updatePlayersMenuStats(String winsWhite, String percentWinsWhite, String winsBlack, String percentWinsBlack) {
    	whiteDubs.setText(winsWhite);
        whiteDubPercent.setText(percentWinsWhite);
        blackDubs.setText(winsBlack);
    	blackDubPercent.setText(percentWinsBlack);
    }

    public static Player getActiveWhitePlayer(){ return activeWhitePlayer; }
    
    public static Player getActiveBlackPlayer(){ return activeBlackPlayer; }

    JLabel whiteMSG, blackMSG;

    private void initPlayerSelect(){

        ArrayList<Player> temp = Player.getPlayers();
        whiteUsrNames.clear();
        blackUsrNames.clear();
        for(int i = 0 ; i < temp.size() ; i++) {
            Player p = temp.get(i);
            players.put(p.getUsername(), p);
            whiteUsrNames.add(p.getUsername());
            blackUsrNames.add(p.getUsername());
        }

        selectPlayer = new JFrame();
        selectPlayer.setTitle("Chess: Select Players");
        selectPlayer.setBounds(100, 100, 500, 200);
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
                activeWhitePlayer = players.get(w);//TODO - never happens before game starts???
                System.out.println(activeWhitePlayer.getUsername());
                if(((activeBlackPlayer != null) && (activeWhitePlayer != null)) && (!(w.equals(b)))) activeBlackPlayer = players.get(b);
                //System.out.println("white : " + activeWhitePlayer.getUsername() + "\nblack : " + activeBlackPlayer.getUsername());
                selectPlayer.dispose();
                updatePlayersMenu((String) w, (String) b);
                if(activeWhitePlayer == null && activeBlackPlayer == null) updatePlayersMenuStats("Unavailable", "Unavailable", "Unavailable", "Unavailable");
                else if(activeWhitePlayer == null) updatePlayersMenuStats("Unavailable", "Unavailable", ""+activeBlackPlayer.getGamesWon(), ""+activeBlackPlayer.getWinPercent());
                else if(activeBlackPlayer == null) updatePlayersMenuStats(""+activeWhitePlayer.getGamesWon(), ""+activeWhitePlayer.getWinPercent(), "Unavailable", "Unavailable");
                else updatePlayersMenuStats(""+activeWhitePlayer.getGamesWon(), ""+activeWhitePlayer.getWinPercent(), ""+activeBlackPlayer.getGamesWon(), ""+activeBlackPlayer.getWinPercent());
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

    public void itemStateChanged(ItemEvent e) { 
        if (e.getSource() == whiteSelect) whiteMSG.setText("Selected White Player is " + whiteSelect.getSelectedItem());
        if (e.getSource() == blackSelect) blackMSG.setText("Selected Black Player is " + blackSelect.getSelectedItem());  
    } 

    private void newPlayerMenu(){
        JFrame f = new JFrame();
        f.setTitle("Chess: Create Player");
        f.setBounds(100, 100, 500, 200);

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
                Player p = new Player(un);
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

}