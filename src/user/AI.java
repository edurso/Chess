package user;

import java.util.List;

import chess.ChessSquare;
import chess.Game;
import pieces.King;
import pieces.Piece;

public abstract class AI extends Player {

	protected ChessSquare from;
	
	protected ChessSquare to;

	protected List<ChessSquare> dList;

	protected abstract void setFrom(ChessSquare[][] boardState);

	protected abstract void setTo(ChessSquare[][] boardState);

	public abstract void selectMove(ChessSquare[][] boardState);

	public void move(ChessSquare[][] boardState){
		selectMove(boardState);
		updatePieces(boardState, from, to);
	}

	//TODO - check filters & logic here
	protected void updatePieces(ChessSquare[][] boardState, ChessSquare from, ChessSquare to) {
		if(to.getPiece() != null) to.removePiece();
		to.setPiece(from.getPiece());
		if (from.isCheck()) from.removeCheck();
		from.removePiece();
		if(Game.board.getKing(Piece.COLOR_WHITE).threatExists(boardState)) {
			boardState[Game.board.getKing(Piece.COLOR_WHITE).getX()][Game.board.getKing(Piece.COLOR_WHITE).getY()].setCheck();
			if (Game.board.checkmate(Game.board.getKing(Piece.COLOR_WHITE).getColor()) || Game.board.checkmate(Game.board.getKing(Piece.COLOR_BLACK).getColor())) {
				if(from.getPiece() != null) from.removePiece();
				Game.board.gameEnd();
			}
		}
		if(Game.board.getKing(Piece.COLOR_BLACK).threatExists(boardState) == false) boardState[Game.board.getKing(Piece.COLOR_BLACK).getX()][Game.board.getKing(Piece.COLOR_BLACK).getY()].removeCheck();
		if(to.getPiece() instanceof King) {
			((King)to.getPiece()).setX(to.x);
			((King)to.getPiece()).setY(to.y);
		}
	}
	
}
