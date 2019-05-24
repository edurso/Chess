package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Queen extends piece
 * @author edurso
 */
public class Queen extends Piece {
	
	/**
	 * constructor for the Queen class
	 * @param id    id for the Queen
	 * @param path  path to the graphic for the Queen
	 * @param color color for the Queen
	 */
	public Queen(String id, String path, int color) {
		//super(path);
		this.setId(id);
		this.setPath(path);
		this.setColor(color);
	}
	
	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y) {
		//this is all just the castle's movements combined with the bishop's movements
		possibleMoves.clear();
		//vertical movements
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
		//horizontal movements
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
			else if(currentBoardState[x][tempY].getPiece().getColor() == this.getColor()) break;
			else {
				possibleMoves.add(currentBoardState[x][tempY]);
				break;
			}
			tempY++;
		}
		//diagonal movements
		tempX = x + 1;
		tempY = y - 1;
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
