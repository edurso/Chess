package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Pawn extends piece
 * @author edurso
 */
public class Pawn extends Piece {
	
	/**
	 * constructor for the Pawn class
	 * @param id    id for the Pawn
	 * @param path  path to the graphic for the Pawn
	 * @param color color for the Pawn
	 */
	public Pawn(String id, String path, int color) {
		this.setId(id);
		this.setPath(path);
		this.setColor(color);
	}

	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y) {
		possibleMoves.clear();
		if(getColor() == Piece.COLOR_WHITE) {
			if(x == 0) return possibleMoves;//no possibilities if we have already crossed
			if(currentBoardState[x-1][y].getPiece() == null) {
				possibleMoves.add(currentBoardState[x-1][y]);
				if(x==6 && currentBoardState[4][y].getPiece() == null) possibleMoves.add(currentBoardState[4][y]);
			}
			if((y > 0)
					&&(currentBoardState[x-1][y-1].getPiece() != null)
					&&(currentBoardState[x-1][y-1].getPiece().getColor() != this.getColor()))
				possibleMoves.add(currentBoardState[x-1][y-1]);
			if((y < 7)
					&&(currentBoardState[x-1][y+1].getPiece() != null)
					&&(currentBoardState[x-1][y+1].getPiece().getColor() != this.getColor()))
				possibleMoves.add(currentBoardState[x-1][y+1]);
		} else {
			if(x == 8) return possibleMoves;
			if(currentBoardState[x+1][y].getPiece() == null) {
				possibleMoves.add(currentBoardState[x+1][y]);
				if(x == 1 && currentBoardState[3][y].getPiece() == null) possibleMoves.add(currentBoardState[3][y]);
			}
			if((y > 0) 
					&&(currentBoardState[x+1][y-1].getPiece() != null)
					&&(currentBoardState[x+1][y-1].getPiece().getColor() != this.getColor()))
				possibleMoves.add(currentBoardState[x+1][y-1]);
			if((y < 7) 
					&&(currentBoardState[x+1][y+1].getPiece() != null)
					&&(currentBoardState[x+1][y+1].getPiece().getColor() != this.getColor()))
				possibleMoves.add(currentBoardState[x+1][y+1]);
		}
		return possibleMoves;
	}

}
