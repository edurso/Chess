package pieces;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import chess.ChessSquare;

/**
 * Piece class serves as super class for all game pieces
 * @author edurso
 */
public abstract class Piece extends JLabel implements Cloneable{
	
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
	private String path = null;
	
	/**
	 * list of possible moves the piece can make
	 */
	ArrayList<ChessSquare> possibleMoves = new ArrayList<>();
	
	/**
	 * updates the list of moves the piece can make
	 * pre: piece is not null, state must be valid
	 * post: list of moves that suit the piece
	 * @param currentBoardState array of ChessSquares representing the current state of the board 
	 * @param x                 current x position of the piece
	 * @param y                 current y position of the piece
	 * @return the new list of possible moves
	 */
	public abstract ArrayList<ChessSquare> move(ChessSquare[][] currentBoardState, int x, int y);
	
	/**
	 * clones the piece
	 * pre: piece not null
	 * post: cloned
	 * @return a clone of the game piece
	 * @throws CloneNotSupportedException can not clone at times
	 */
	public Piece getCopy() throws CloneNotSupportedException{ return (Piece) this.clone(); }

	/**
	 * pre: piece not null
	 * post: none
	 * @return color of the game piece
	 */
	public int getColor() { return color; }

	/**
	 * sets the color of the game piece
	 * pre: piece is not null
	 * post: color updated
	 * @param color new color of the game piece
	 */
	public void setColor(int color) { this.color = color; }

	/**
	 * pre: piece is not null
	 * post: none
	 * @return the id of the game piece
	 */
	public String getId() { return id; }

	/**
	 * set the id of the game piece
	 * pre: piece is not null
	 * post: id set
	 * @param id new id for the game piece
	 */
	public void setId(String id) { this.id = id; }

	/**
	 * pre: piece is not null
	 * post: null
	 * @return the path the the graphic for the game piece
	 */
	public String getPath() { 
		if(path != null) return path; 
		return "whitePawn.png";
	}

	/**
	 * sets the file path to the graphic of the game piece
	 * pre: piece is not null
	 * post: none
	 * @param path the new file path for the graphic of the game piece
	 */
	public void setPath(String path) { this.path = path; }
	
}
