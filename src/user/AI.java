package user;

import java.util.List;

import chess.ChessSquare;
import chess.Game;
import pieces.King;
import pieces.Piece;

public abstract class AI extends Player {

	/**
	 * place holder for square with piece
	 */
	protected ChessSquare from;
	
	/**
	 * place holder for destination
	 */
	protected ChessSquare to;

	/**
	 * list of possible destinations
	 */
	protected List<ChessSquare> dList;

	/**
	 * calculates piece to move
	 * @param boardState current state of the board
	 */
	protected abstract void setFrom(ChessSquare[][] boardState);

	/**
	 * calculates destination square
	 * @param boardState current state of the board
	 */
	protected abstract void setTo(ChessSquare[][] boardState);

	/**
	 * selects the move
	 * @param boardState current state of the board
	 */
	public abstract void selectMove(ChessSquare[][] boardState);

	public void move(ChessSquare[][] boardState){
		selectMove(boardState);
		updatePieces(boardState, from, to);
	}

	//TODO - check filters & logic here
	/**
	 * updates the selected pieces based on the selected move
	 * @param boardState current state of the board
	 * @param from square containing the piece to be moved
	 * @param to destination square
	 */
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
