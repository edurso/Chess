package user;

import java.util.ArrayList;
import java.util.List;

import chess.ChessSquare;
import chess.Game;
import pieces.King;
import pieces.Piece;

//TODO - second time it does not play properly.
//TODO - it declares loss on check i think - pls fix
//TODO - need to stop it from replacing its own pieces - filtering destinations might help
public class SimpleAI extends AI {
	
	@Override
	protected void setFrom(ChessSquare[][] boardState) {
		while(true) {
			from = boardState[(int) (Math.random() * 7)][(int) (Math.random() * 7)];
			if(from.getPiece() != null && from.getPiece().getColor() == Piece.COLOR_BLACK) break;
		}
	}

	@Override
	protected void setTo(ChessSquare[][] boardState) {
		try {
			to = dList.get( (int) (Math.random() * dList.size()) );
		} catch (Exception e) {
			//System.out.println("problem");
			setFrom(boardState);
		}
	}

	@Override
	public void selectMove(ChessSquare[][] boardState) {
		dList = new ArrayList<>();
		setFrom(boardState);
		if(from != null) dList = from.getPiece().move(boardState, from.x, from.y);
		//else System.out.println("no");
		//TODO - FILTER DLIST
		//System.out.println("X: " + from.x + "\nY: " + from.y + "\n");
		setTo(boardState);
		int c = 0;
		if(to == null) {
			//System.out.println(":( thats unfortunate ):");
			selectMove(boardState);
		}
	}

	@Override
	public void addGamePlayed(boolean b) {}

	@Override
	public String getGamesWon() { return "more than you"; }

	@Override
	public String getWinPercent() { return "more than you"; }

	@Override
	public String getUsername() { return "AReallySimpleAI"; }

	@Override
	public void savePlayerData() {}

}
