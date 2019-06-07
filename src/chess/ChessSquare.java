package chess;

import javax.swing.*;
import java.awt.*;

import pieces.King;
import pieces.Piece;

/**
 * ChessSquares are the components of the chess board, where pieces will be placed
 * @author edurso
 */
public class ChessSquare extends JPanel implements Cloneable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * if the square is a possible destination of the piece of the selected cell
	 */
	private boolean isPossibleDestination;
	
	/**
	 * the piece image
	 */
	private JLabel content;
	
	/**
	 * the piece on the square, null if there is no piece
	 */
	private Piece piece;
	
	/**
	 * x position of the square on the board
	 */
	public int x;
	
	/**
	 * y position of the square on the board
	 */
	public int y;
	
	/**
	 * if the square is selected by a player
	 */
	private boolean isSelected = false;
	
	/**
	 * if the square contains a king in check or a piece that is putting the king in check
	 */
	private boolean isCheck = false;
	
	/**
	 * constructor for the chess square
	 * pre: none
	 * post: chess square exists with things
	 * @param x x position of the square on the board
	 * @param y y position of the square on the board
	 * @param p piece on the square
	 */
	public ChessSquare(int x, int y, Piece p) {		
		this.x = x;
		this.y = y;
		
		setLayout(new BorderLayout());
	
		if((x + y) % 2 == 0) setBackground(Color.white);
		else setBackground(Color.PINK);
	 
		if(p != null) setPiece(p);
		//else setBackground(Color.BLACK);
		
	}
	
	/**
	 * constructor to create a chess square from an existing chess square
	 * pre: chess square exists
	 * post:chess square cloned
	 * @param cs ChessSquare to base creation of new square from
	 * @throws CloneNotSupportedException can throw a clone not supported exception in some rare cases
	 */
	public ChessSquare(ChessSquare cs) throws CloneNotSupportedException {
		this.x = cs.x;
		this.y = cs.y;
		
		setLayout(new BorderLayout());
		
		if((x + y) % 2 == 0) setBackground(Color.white);
		else setBackground(Color.PINK);
		
		if(cs.getPiece() != null) setPiece(cs.getPiece().getCopy());
		else piece = null;
	}
	
	/**
	 * sets the piece on the square
	 * pre: square is not null
	 * post: piece added to square
	 * @param piece piece to add to square
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
		ImageIcon img = null;// = new ImageIcon(getClass().getResource(piece.getPath()));
		if(piece != null && piece.getPath() != null) img = new ImageIcon(getClass().getResource(piece.getPath()));
		content = new JLabel(img);
		this.add(content);
		content.paint(null);
	}
	
	/** 
	 * pre: piece should not be null
	 * post: none
	 * @return the piece on the square
	 */
	public Piece getPiece() { return this.piece; }
	
	/**
	 * removes the piece on the square
	 * pre: there is a piece on the square
	 * post: there is not a piece on the square
	 */
	public void removePiece() {
		if (piece instanceof King) {
			this.setVisible(false);
			piece = null;
			this.remove(content);
			this.setVisible(true);
		} else {
			this.setVisible(false);
			piece = null;
			this.remove(content);
			this.setVisible(true);
		}
	}
	
	/**
	 * selcets the square
	 * pre: square is not null
	 * post: borders highlighted
	 */
	public void select() {
		this.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		this.isSelected = true;
	}
	
	/**
	 * pre: none
	 * post: none
	 * @return true if the square is selected, false otherwise
	 */
	public boolean isSelected() { return this.isSelected; }
	
	/**
	 * removes the border from the square to indicate that it is no longer selected
	 * pre: chess square exists
	 * post: chess square returned to normal state
	 */
	public void deselect() {
		this.setBorder(null);
		this.isSelected = false;
	}
	
	/**
	 * sets the square as a possible destination (border color changes to blue)
	 * pre: square exists
	 * post: possibe destination highlighted and labeled
	 */
	public void setPossibleDestination() {
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
		this.isPossibleDestination = true;
	}
	
	/**
	 * removes blue border from square o indicate the square is no longer selected
	 * pre: square exists
	 * post: highlight and label removed
	 */
	public void setImpossibleDestination() {
		this.setBorder(null);
		this.isPossibleDestination = false;
	}
	
	/**
	 * pre: square exists
	 * post: square is possible destination
	 * @return true if the square is a possible destination, false otherwise
	 */
	public boolean isPossibleDestination() { return this.isPossibleDestination; }
	
	/**
	 * sets the border color of a cell to indicate check
	 * pre: square exists
	 * post: check marked and labeled
	 */
	public void setCheck() {
		this.setBackground(Color.RED);
		this.isCheck = true;
	}
	
	/**
	 * removes the red border color of a cell to indicate the cell is no longer involved in check
	 * pre: square exists
	 * post: coloration and label removed
	 */
	public void removeCheck() {
		this.setBorder(null);
		if((x + y) % 2 == 0) setBackground(Color.white);
		else setBackground(Color.PINK);
		this.isCheck = false;
	}
	
	/**
	 * pre: square exists
	 * post: none
	 * @return if the cell is in check
	 */
	public boolean isCheck(){ return isCheck; }
	
}
