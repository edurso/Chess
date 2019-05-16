package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Chess Piece King Class
 * @author edurso
 */
public class King extends Piece {
	
	/**
	 * Number of possible moves in all directions that can be made by the king.
	 */
	private static final int NUM_POSSIBLE_MOVES = 8;
	
	/**
	 * Keep track of king's X position
	 */
	private int x; 
	
	/**
	 * Keep track of king's Y position
	 */
	private int y;
	
	/**
	 * King constructor
	 * @param id    kings ID
	 * @param path  path to kings graphic  
	 * @param color color of king
	 * @param x     king X position
	 * @param y     king Y position
	 */
	public King(String id, String path, int color, int x, int y) {
		//super(path);
		setX(x);
		setY(y);
		setId(id);
		setPath(path);
		setColor(color);
	}
	
	/**
	 * sets x position of king
	 * @param x new X position
	 */
	public void setX(int x) { this.x = x; }
	
	/**
	 * sets y position of king
	 * @param y new Y position
	 */
	public void setY(int y) { this.y = y; }
	
	/**
	 * @return the x position of king
	 */
	public int getX() { return x; }
	
	/**
	 * @return the y position of king
	 */
	public int getY() { return y; }

	@Override
	public ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y) {
		possibleMoves.clear();
		int[] posX = {x  , x  , x+1, x-1, x+1, x-1, x+1, x-1};//Possible X Positions 
		int[] posY = {y+1, y-1, y  , y  , y+1, y+1, y-1, y-1};//Possible Y Positions | In Line With X Positions Above
		for(int i = 0 ; i < NUM_POSSIBLE_MOVES ; i++){
			if(((0 <= posX[i]) && (8 > posX[i])) && ((0 <= posY[i]) && (8 > posY[i]))) {
				if(((currentBoardState[posX[i]][posY[i]].getPiece() == null)
						|| (currentBoardState[posX[i]][posY[i]].getPiece().getColor() != this.getColor()))) {
					possibleMoves.add(currentBoardState[posX[i]][posY[i]]);
				}
			}
		}
		return possibleMoves;
	}
	
	/**
	 * determines if the king is in check
	 * @param currentBoardState the current state of the board
	 * @return true if an opposing piece is able to capture the king, false otherwise
	 */
	public boolean threatExists (ChessSquare[][] currentBoardState){
		return (pawnAttackPossible(currentBoardState) 
				|| horizontalAttackPossible(currentBoardState)
				|| diagonalAttackPossible(currentBoardState)
				|| horseAttackPossible(currentBoardState));
	}
	
	/**
	 * determined if an attack is possible from above, below, or on either side of the king
	 * @param currentBoardState the current state of the board
	 * @return true if an opposing queen or rook is able to capture the king, false otherwise
	 */
	private boolean horizontalAttackPossible(ChessSquare[][] currentBoardState) {
		//Check Both sides (left & right)
		for(int i = x + 1 ; i < 8 ; i++) {
    		if(currentBoardState[i][y].getPiece() == null) continue;
    		else if(currentBoardState[i][y].getPiece().getColor() == this.getColor()) break;
    		else
    		{
    			if ((currentBoardState[i][y].getPiece() instanceof Castle) 
    					|| (currentBoardState[i][y].getPiece() instanceof Queen))
    				return true;
    			else break;
    		}
    	}
    	for(int i = x - 1 ; i >= 0 ; i--) {
    		if(currentBoardState[i][y].getPiece() == null) continue;
    		else if(currentBoardState[i][y].getPiece().getColor() == this.getColor()) break;
    		else
    		{
    			if ((currentBoardState[i][y].getPiece() instanceof Castle) 
    					|| (currentBoardState[i][y].getPiece() instanceof Queen)) 
    				return true;
    			else break;
    		}
    	}
    	//Check above and below
    	for(int i = y + 1 ; i < 8 ; i++) {
    		if(currentBoardState[x][i].getPiece() == null) continue;
    		else if(currentBoardState[x][i].getPiece().getColor() == this.getColor())break;
    		else
    		{
    			if ((currentBoardState[x][i].getPiece() instanceof Castle) 
    					|| (currentBoardState[x][i].getPiece() instanceof Queen))
    				return true;
    			else break;
    		}
    	}
    	for(int i = y - 1 ; i >= 0 ; i--) {
    		if(currentBoardState[x][i].getPiece()==null) continue;
    		else if(currentBoardState[x][i].getPiece().getColor()==this.getColor()) break;
    		else
    		{
    			if ((currentBoardState[x][i].getPiece() instanceof Castle) 
    					|| (currentBoardState[x][i].getPiece() instanceof Queen))
    				return true;
    			else break;
    		}
    	}
		return false;
	}
	
	/**
	 * determines if a pawn from the opposing side is in position to take the king
	 * @param currentBoardState the current state of the board
	 * @return true if an opposing pawn is in position to take the king, false otherwise
	 */
	private boolean pawnAttackPossible(ChessSquare[][] currentBoardState) {
		int posX[]={x + 1, x + 1, x + 1, x    , x    , x - 1, x - 1, x - 1};
		int posY[]={y - 1, y + 1, y    , y + 1, y - 1, y + 1, y - 1, y    };
		for(int i = 0 ; i < 8 ; i++)
			if(((posX[i] >= 0) && (posX[i] < 8)) && ((posY[i] >= 0) && (posY[i] < 8)))
				if(currentBoardState[posX[i]][posY[i]].getPiece() != null 
						&& currentBoardState[posX[i]][posY[i]].getPiece().getColor() != this.getColor() 
						&& (currentBoardState[posX[i]][posY[i]].getPiece() instanceof King))
					return true;
			
		if(this.getColor() == Piece.COLOR_WHITE) {
			if(x > 0 && y > 0 
					&& currentBoardState[x-1][y-1].getPiece() != null
					&& currentBoardState[x-1][y-1].getPiece().getColor() == Piece.COLOR_BLACK 
					&& currentBoardState[x-1][y-1].getPiece() instanceof Pawn)
				return true;
			if(x > 0 && y < 7 
					&& currentBoardState[x-1][y+1].getPiece() != null
					&& currentBoardState[x-1][y+1].getPiece().getColor() == Piece.COLOR_BLACK
					&&(currentBoardState[x-1][y+1].getPiece() instanceof Pawn))
				return true;
		} else { //this.getColor() == Piece.COLOR_BLACK
			if(x < 7 && y > 0
					&& currentBoardState[x+1][y-1].getPiece() != null
					&& currentBoardState[x+1][y-1].getPiece().getColor() == Piece.COLOR_WHITE 
					&&(currentBoardState[x+1][y-1].getPiece() instanceof Pawn))
				return true;
			if(x < 7 && y < 7
					&& currentBoardState[x+1][y+1].getPiece() != null
					&& currentBoardState[x+1][y+1].getPiece().getColor() == Piece.COLOR_WHITE
					&& currentBoardState[x+1][y+1].getPiece() instanceof Pawn)
				return true;
		}
		return false;
	}
	
	/**
	 * determines if an opposing piece that moves on a diagonal axis is able to take the king
	 * @param currentBoardState the current state of the board
	 * @return true if an opposing queen or bishop is able to take the king, false otherwise
	 */
	private boolean diagonalAttackPossible(ChessSquare[][] currentBoardState) {
		int tempX = x + 1; 
		int tempY = y - 1;
		while(tempX < 8 && tempY >= 0) {
			if(currentBoardState[tempX][tempY].getPiece() == null) {
				tempX++;
				tempY--;
			}
			else if(currentBoardState[tempX][tempY].getPiece().getColor()==this.getColor()) break;
			else
			{
				if (currentBoardState[tempX][tempY].getPiece() instanceof Bishop 
						|| currentBoardState[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else break;
			}
		}
		tempX = x - 1; 
		tempY = y + 1;
		while( tempX >= 0 && tempY < 8) {
			if(currentBoardState[tempX][tempY].getPiece() == null) {
				tempX--;
				tempY++;
			}
			else if(currentBoardState[tempX][tempY].getPiece().getColor() == this.getColor()) break;
			else
			{
				if (currentBoardState[tempX][tempY].getPiece() instanceof Bishop 
						|| currentBoardState[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else break;
			}
		}
		tempX = x - 1; 
		tempY = y - 1;
		while(tempX >= 0 && tempY >= 0) {
			if(currentBoardState[tempX][tempY].getPiece() == null) {
				tempX--;
				tempY--;
			}
			else if(currentBoardState[tempX][tempY].getPiece().getColor() == this.getColor()) break;
			else
			{
				if (currentBoardState[tempX][tempY].getPiece() instanceof Bishop 
						|| currentBoardState[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else break;
			}
		}
		tempX = x + 1;
		tempY = y + 1;
		while(tempX < 8 && tempY < 8) {
			if(currentBoardState[tempX][tempY].getPiece() == null) {
				tempX++;
				tempY++;
			}
			else if(currentBoardState[tempX][tempY].getPiece().getColor() == this.getColor()) break;
			else
			{
				if (currentBoardState[tempX][tempY].getPiece() instanceof Bishop 
						|| currentBoardState[tempX][tempY].getPiece() instanceof Queen)
    				return true;
    			else break;
			}
		}
		return false;
	}
	
	/**
	 * determines if an opposing knight is able to take the king
	 * @param currentBoardState the current state of the board
	 * @return true if an opposing knight is in position o take the king, false otherwise
	 */
	private boolean horseAttackPossible(ChessSquare[][] currentBoardState) {
		int posX[]={x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};//possible horse positions 
		int posY[]={y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};//relative to king
		for(int i = 0 ; i < 8 ; i++)
			if(((posX[i] >= 0) && (posX[i] < 8)) && ( (posY[i] >= 0) && (posY[i] < 8)))
				if((currentBoardState[posX[i]][posY[i]].getPiece() != null) 
						&& (currentBoardState[posX[i]][posY[i]].getPiece().getColor() != this.getColor()) 
						&& (currentBoardState[posX[i]][posY[i]].getPiece() instanceof Horse))
					return true;
		return false;
	}

}
