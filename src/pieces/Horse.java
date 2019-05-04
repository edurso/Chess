package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Horse extends piece
 * @author edurso
 */
public class Horse extends Piece {
	
	/**
	 * constructor for the Horse class
	 * @param id    id for the Horse
	 * @param path  path to the graphic for the Horse
	 * @param color color for the Horse
	 */
	public Horse(String id, String path, int color) {
		this.setId(id);
		this.setPath(path);
		this.setColor(color);
	}

	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y) {
		possibleMoves.clear();
		int posX[]={x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
		int posY[]={y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};
		for(int i = 0 ; i < 8 ; i++)
			if(((posX[i] >= 0) && (posX[i] < 8)) && ((posY[i] >= 0) && (posY[i] < 8)))
				if(currentBoardState[posX[i]][posY[i]].getPiece() == null
						|| currentBoardState[posX[i]][posY[i]].getPiece().getColor() != this.getColor())
					possibleMoves.add(currentBoardState[posX[i]][posY[i]]);
		return possibleMoves;
	}

}
