package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Bishop extends piece
 * @author edurso
 */
public class Bishop extends Piece {
	
	/**
	 * constructor for the Bishop class
	 * @param id    id for the Bishop
	 * @param path  path to the graphic for the Bishop
	 * @param color color for the Bishop
	 */
	public Bishop(String id, String path, int color) {
		//super(path);
		this.setId(id);
		this.setPath(path);
		this.setColor(color);
	}

	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y) {
		int tempX = x + 1;
		int tempY = y - 1;
		while(tempX < 8 && tempY >= 0) {
			if(currentBoardState[tempX][tempY].getPiece() == null) possibleMoves.add(currentBoardState[tempX][tempY]);
			else if(currentBoardState[tempX][tempY].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[tempX][tempY]);
				break;
			}
			tempX++;
			tempY--;
		}
		tempX = x - 1;
		tempY = y + 1;
		while(tempX >= 0 && tempY < 8) {
			if(currentBoardState[tempX][tempY].getPiece() == null) possibleMoves.add(currentBoardState[tempX][tempY]);
			else if(currentBoardState[tempX][tempY].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[tempX][tempY]);
				break;
			}
			tempX--;
			tempY++;
		}
		tempX = x - 1; 
		tempY = y - 1;
		while(tempX >= 0 && tempY >= 0) {
			if(currentBoardState[tempX][tempY].getPiece() == null) possibleMoves.add(currentBoardState[tempX][tempY]);
			else if(currentBoardState[tempX][tempY].getPiece().getColor()==this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[tempX][tempY]);
				break;
			}
			tempX--;
			tempY--;
		}
		tempX = x + 1;
		tempY = y + 1;
		while(tempX < 8 && tempY < 8) {
			if(currentBoardState[tempX][tempY].getPiece() == null) possibleMoves.add(currentBoardState[tempX][tempY]);
			else if(currentBoardState[tempX][tempY].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[tempX][tempY]);
				break;
			}
			tempX++;
			tempY++;
		}
		return possibleMoves;
	}

}
