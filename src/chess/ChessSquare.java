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
	int x;
	
	/**
	 * y position of the square on the board
	 */
	int y;
	
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
	 * @param cs ChessSquare to base creation of new square from
	 * @throws CloneNotSupportedException
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
	 * @param piece piece to add to square
	 */
	public void setPiece(Piece piece) {
		//set piece
		this.piece = piece;
		//change label
		//ImageIcon img = new ImageIcon(getClass().getResource("whitePawn.png"));
		ImageIcon img = new ImageIcon(getClass().getResource(piece.getPath()));
		if(piece.getPath() != null) img = new ImageIcon(getClass().getResource(piece.getPath()));
		content = new JLabel(img);
		this.add(content);
		content.paint(null);
	}
	
	/** 
	 * @return the piece on the square
	 */
	public Piece getPiece() { return this.piece; }
	
	/**
	 * removes the piece on the square
	 */
	public void removePiece() {
		if (piece instanceof King) {
			piece = null;
			this.remove(content);
		} else {
			piece = null;
			this.remove(content);
		}
	}
	
	public void showContent() {
		content.setVisible(true);
		this.setVisible(true);
	}
	
	public void hideContent() {
		content.setVisible(true);
		this.setVisible(true);
	}
	
	/**
	 * selcets the square
	 */
	public void select() {
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected = true;
	}
	
	/**
	 * @return true if the square is selected, false otherwise
	 */
	public boolean isSelected() { return this.isSelected; }
	
	/**
	 * removes the border from the square to indicate that it is no longer selected
	 */
	public void deselect() {
		this.setBorder(null);
		this.isSelected = false;
	}
	
	/**
	 * sets the square as a possible destination (border color changes to blue)
	 */
	public void setPossibleDestination() {
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.isPossibleDestination = true;
	}
	
	/**
	 * removes blue border from square o indicate the square is no longer selected
	 */
	public void setImpossibleDestination() {
		this.setBorder(null);
		this.isPossibleDestination = false;
	}
	
	/**
	 * @return true if the square is a possible destination, false otherwise
	 */
	public boolean isPossibleDestination() { return this.isPossibleDestination; }
	
	/**
	 * sets the border color of a cell to indicate check
	 */
	public void setCheck() {
		this.setBackground(Color.RED);
		this.isCheck = true;
	}
	
	/**
	 * removes the red border color of a cell to indicate the cell is no longer involved in check
	 */
	public void removeCheck() {
		this.setBorder(null);
		if((x+y)%2==0) setBackground(new Color(110,200,110));
		else setBackground(Color.white);
		this.isCheck = false;
	}
	
	/**
	 * @return if the cell is in check
	 */
	public boolean isCheck(){ return isCheck; }
	
}
