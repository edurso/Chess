package user;

import chess.ChessSquare;

public abstract class Player {
	
	/**
	 * used by AI to move the auto=player's pieces
	 * pre: board state not null and valid
	 * post: player has moved piece
	 * @param boardState current state of the board
	 */
	public abstract void move(ChessSquare[][] boardState);

	/**
	 * adds a game played
	 * pre: player not null
	 * post: game added, win if won
	 * @param b if the player won or not
	 */
	public abstract void addGamePlayed(boolean b);

	/**
	 * pre: player not null
	 * post: none
	 * @return the number of games won
	 */
	public abstract String getGamesWon();

	/**
	 * pre: player not null
	 * post: none
	 * @return the number of games won over the number of games played
	 */
	public abstract String getWinPercent();

	/**
	 * pre: player not null
	 * post: none
	 * @return the player's username
	 */
	public abstract String getUsername();

	/**
	 * pre: player not null
	 * post: data written
	 * writes player data to a file
	 */
	public abstract void savePlayerData();
}
