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

public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String username = "User";
	
	private int gamesPlayed = 0;
	
	private int gamesWon = 0;
	
	public Player (String username) {
		this.username = username;
	}

	public String getUsername() { return username; }

	public void setUsername(String username) { this.username = username; }

	public int getGamesPlayed() { return gamesPlayed; }

	public void addGamePlayed(boolean didWin) {
		gamesPlayed++;
		gamesWon += (didWin) ? 1 : 0;
	}

	public int getGamesWon() { return gamesWon; }
	
	public Integer getWinPercent() { return new Integer((gamesWon*100)/gamesPlayed); }
	
	public void savePlayerData() {
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		Player tempPlayer;
		File inputFile = null;
		File outputFile = null;
		try {
			inputFile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
			outputFile = new File(System.getProperty("user.dir") + File.separator + "tempfile.dat");
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Read-Write Permission Denied !! Program Cannot Start");
			//System.exit(0);
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
			File newf = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			if(outputFile.renameTo(newf)==false) System.out.println("File Renameing Unsuccessful");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Unable to read/write the required Game files !! Press ok to continue");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
		} catch (Exception e) { Game.revealErrorWindow(); }
	}
	
	public static ArrayList<Player> getPlayers() {
		Player tempPlayer;
		ObjectInputStream input = null;
		ArrayList<Player> players = new ArrayList<Player>();
		try {
			File infile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
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
			e.printStackTrace();
			try { input.close(); } catch (IOException e1) {}
			JOptionPane.showMessageDialog(null, "Unable to read the required Game files !!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
		} catch (Exception e) { Game.revealErrorWindow(); }
		return players;
	}
	
}
