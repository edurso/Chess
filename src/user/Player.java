package user;

import chess.ChessSquare;

public abstract class Player {
	
	/**
	 * used by AI to move the auto=player's pieces
	 * @param boardState current state of the board
	 */
	public abstract void move(ChessSquare[][] boardState);

	/**
	 * adds a game played
	 * @param b if the player won or not
	 */
	public abstract void addGamePlayed(boolean b);

	/**
	 * @return the number of games won
	 */
	public abstract String getGamesWon();

	/**
	 * @return the number of games won over the number of games played
	 */
	public abstract String getWinPercent();

	/**
	 * @return the player's username
	 */
	public abstract String getUsername();

	/**
	 * writes player data to a file
	 */
	public abstract void savePlayerData();
}
