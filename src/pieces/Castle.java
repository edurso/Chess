package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Castle extends piece
 * @author edurso
 */
public class Castle extends Piece {

	/**
	 * constructor for the Castle class
	 * pre: none
	 * post: castle created
	 * @param id    id for the castle
	 * @param path  path to the graphic for the castle
	 * @param color color for the castle
	 */
	public Castle(String id, String path, int color) {
		//super(path);
		this.setId(id);
		this.setPath(path);
		this.setColor(color);
	}
	
	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y) {
		possibleMoves.clear();
		int tempX = x - 1;
		while(tempX >= 0) {
			if(currentBoardState[tempX][y].getPiece() == null) possibleMoves.add(currentBoardState[tempX][y]);
			else if(currentBoardState[tempX][y].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[tempX][y]);
				break;
			}
			tempX--;
		}
		tempX = x + 1;
		while(tempX < 8) {
			if(currentBoardState[tempX][y].getPiece() == null) possibleMoves.add(currentBoardState[tempX][y]);
			else if(currentBoardState[tempX][y].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[tempX][y]);
				break;
			}
			tempX++;
		} 
		int tempY = y - 1;
		while(tempY >= 0) {
			if(currentBoardState[x][tempY].getPiece() == null) possibleMoves.add(currentBoardState[x][tempY]);
			else if(currentBoardState[x][tempY].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[x][tempY]);
				break;
			}
			tempY--;
		}
		tempY = y + 1;
		while(tempY < 8) {
			if(currentBoardState[x][tempY].getPiece() == null) possibleMoves.add(currentBoardState[x][tempY]);
			else if(currentBoardState[x][tempY].getPiece().getColor()==this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[x][tempY]);
				break;
			}
			tempY++;
		}
		return possibleMoves;
	}
	
}
