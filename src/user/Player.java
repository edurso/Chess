package user;

import chess.ChessSquare;

public abstract class Player {
	
	public abstract void move(ChessSquare[][] boardState);

	public abstract void addGamePlayed(boolean b);

	public abstract String getGamesWon();

	public abstract String getWinPercent();

	public abstract String getUsername();

	public abstract void savePlayerData();
}
