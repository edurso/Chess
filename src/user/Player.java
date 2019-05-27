package user;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import chess.Game;

/**
 * Stored data about players and is the interface with the file to get/save player data
 * @author edurso
 */
public class Player implements Serializable{
	
	/**
	 * filename
	 */
	private static final String fname = "gnome_chess_game_data.dat";
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * holder for the active white player when the menu restarts
	 */
	private static String activeWhite = "";
	
	/**
	 * holder for the active black player when the menu restarts
	 */
	private static String activeBlack = "";

	/**
	 * username of the player
	 */
	private String username = "";
	
	/**
	 * number of games the player has played
	 */
	private int gamesPlayed = 0;
	
	/**
	 * number of games the player has won
	 */
	private int gamesWon = 0;
	
	/**
	 * created new player with {@code username}
	 * @param username
	 */
	public Player (String username) { this.username = username; }

	/**
	 * retrieves the active white player
	 * @return the active white player
	 */
	public static String getActiveWhite() { return activeWhite; }

	/**
	 * sets the active white player
	 * @param activeWhite player username of active white player
	 */
	public static void setActiveWhite(String activeWhite) { Player.activeWhite = activeWhite; }

	/**
	 * retrieves the active black player
	 * @return the active black player
	 */
	public static String getActiveBlack() { return activeBlack; }

	/**
	 * sets the active black player
	 * @param activeBlack player username of active black player
	 */
	public static void setActiveBlack(String activeBlack) { Player.activeBlack = activeBlack; }

	/**
	 * retrieves the username of the player
	 * @return player's username
	 */
	public String getUsername() { return username; }

	/**
	 * sets the players username
	 * @param username new username for player
	 */
	public void setUsername(String username) { this.username = username; }

	/**
	 * retrieves the number of games the player has played
	 * @return
	 */
	public int getGamesPlayed() { return gamesPlayed; }

	/**
	 * adds a game played, and a game won if the game was won by the player
	 * @param didWin weather or not the player won the game
	 */
	public void addGamePlayed(boolean didWin) {
		gamesPlayed++;
		gamesWon += (didWin) ? 1 : 0;
	}

	/**
	 * retrieves the number of games the player has won
	 * @return number of games won by the player
	 */
	public int getGamesWon() { return gamesWon; }
	
	/**
	 * retrieves the percent of games the player has won
	 * @return player's won games as a percent
	 */
	public Integer getWinPercent() {
		if(gamesPlayed != 0) return new Integer((gamesWon * 100) / gamesPlayed); 
		return 0;
	}
	
	/**
	 * saves all player data for current player only
	 */
	public void savePlayerData() {
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		Player tempPlayer;
		File inputFile = null;
		File outputFile = null;
		try {
			inputFile = new File(System.getProperty("user.dir") + File.separator + fname);
			outputFile = new File(System.getProperty("user.dir") + File.separator + "tempfile.dat");
		} catch (SecurityException e) {
			Game.setErrorText("Application does not have access to the specified drive");
			Game.revealErrorWindow();
		} 
		boolean playerDoesntExist;
		try {
			if(outputFile.exists() == false) outputFile.createNewFile();
			if(inputFile.exists() == false) {
				output = new ObjectOutputStream(new java.io.FileOutputStream(outputFile, true));
				output.writeObject(this);
			} else {
				input = new ObjectInputStream(new FileInputStream(inputFile));
				output = new ObjectOutputStream(new FileOutputStream(outputFile));
				playerDoesntExist = true;
				try {
					while(true) {
						tempPlayer = (Player)input.readObject();
						if (tempPlayer.getUsername().equals(getUsername())) {
							output.writeObject(this);
							playerDoesntExist = false;
						} else output.writeObject(tempPlayer);
					}
				}
				catch(EOFException e){ input.close(); }
				if(playerDoesntExist) output.writeObject(this);
			}
			inputFile.delete();
			output.close();
			File newf = new File(System.getProperty("user.dir")+ File.separator + fname);
			if(outputFile.renameTo(newf) == false) { 
				Game.setErrorText("File Renameing Unsuccessful");
				Game.revealErrorWindow();
			}
		} catch (FileNotFoundException e) {
			Game.setErrorText("Game File Does Not Exist");
			Game.revealErrorWindow();
		} catch (IOException e) {
			Game.setErrorText("unable to read/view proper files. press ok to continue");
			Game.revealErrorWindow();
		} catch (ClassNotFoundException e) {
			Game.setErrorText("Game Data File Corrupted");
			Game.revealErrorWindow();
		} catch (Exception e) { Game.revealErrorWindow(); }
	}
	
	/**
	 * retrieves a list of all the players stored in the file
	 * @return list of saved players
	 */
	public static ArrayList<Player> getPlayers() {
		Player tempPlayer;
		ObjectInputStream input = null;
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			File infile = new File(System.getProperty("user.dir")+ File.separator + fname);
			input = new ObjectInputStream(new FileInputStream(infile));
			try {
				while(true) {
					tempPlayer = (Player) input.readObject();
					players.add(tempPlayer);
				}
			} catch(EOFException e) { input.close(); }
		} catch (FileNotFoundException e) {
			players.clear();
			return players;
		} catch (IOException e) {
			try { input.close(); } catch (IOException e1) {}
			Game.setErrorText("unable to read/view proper files");
			Game.revealErrorWindow();
		} catch (ClassNotFoundException e) {
			Game.setErrorText("Game Data File Corrupted");
			Game.revealErrorWindow();
		} catch (Exception e) { Game.revealErrorWindow(); }
		return players;
	}
	
}
