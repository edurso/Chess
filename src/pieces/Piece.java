package pieces;

import java.util.ArrayList;

import chess.ChessSquare;

/**
 * Piece class serves as super class for all game pieces
 * @author edurso
 */
public abstract class Piece implements Cloneable{
	
	/**
	 * white game piece color
	 */
	public static final int COLOR_WHITE = 0;
	
	/**
	 * black game piece number
	 */
	public static final int COLOR_BLACK = 1;
	
	/**
	 * color of game piece
	 */
	private int color;
	
	/**
	 * id of game piece
	 */
	private String id = null;
	
	/**
	 * file path to piece graphic
	 */
	private String path;
	
	/**
	 * list of possible moves the piece can make
	 */
	ArrayList<ChessSquare> possibleMoves = new ArrayList<>();
	
	/**
	 * updates the list of moves the piece can make
	 * @param currentBoardState array of ChessSquares representing the current state of the board 
	 * @param x                 current x position of the piece
	 * @param y                 current y position of the piece
	 * @return the new list of possible moves
	 */
	public abstract ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y);
	
	/**
	 * clones the piece
	 * @return a clone of the game piece
	 * @throws CloneNotSupportedException
	 */
	public Piece getCopy() throws CloneNotSupportedException{ return (Piece) this.clone(); }

	/**
	 * @return the color of the game piece
	 */
	public int getColor() { return color; }

	/**
	 * sets the color of the game piece
	 * @param color new color of the game piece
	 */
	public void setColor(int color) { this.color = color; }

	/**
	 * @return the id of the game piece
	 */
	public String getId() { return id; }

	/**
	 * set the id of the game piece
	 * @param id new id for the game piece
	 */
	public void setId(String id) { this.id = id; }

	/**
	 * @return the path the the graphic for the game piece
	 */
	public String getPath() { return path; }

	/**
	 * sets the file path to the graphic of the game piece
	 * @param path the new file path for the graphic of the game piece
	 */
	public void setPath(String path) { this.path = path; }
	
}
