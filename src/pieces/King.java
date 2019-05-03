package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

public class King extends Piece {
	
	private static final int NUM_POSSIBLE_MOVES = 8;

	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] state, int x, int y) {
		possibleMoves.clear();
		int[] posX = {x  , x  , x+1, x-1, x+1, x-1, x+1, x-1};//Possible X Positions 
		int[] posY = {y+1, y-1, y  , y  , y+1, y+1, y-1, y-1};//Possible Y Positions | In Line With X Positions Above
		for(int i = 0 ; i < NUM_POSSIBLE_MOVES ; i++){
			if(((0 <= posX[i]) && (8 > posX[i])) && ((0 <= posY[i]) && (8 > posY[i]))) {//if we can move in all 8 directions
				
			}
		}
		return possibleMoves;
	}

}
