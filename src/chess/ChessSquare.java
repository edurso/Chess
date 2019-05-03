package chess;

import javax.swing.*;

import pieces.Piece;

import java.awt.*;

public class ChessSquare extends JPanel implements Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean ispossibledestination;
	
	private JLabel content;
	
	private Piece piece;
	
	int x;
	
	int y;
	
	private boolean isSelected = false;
	
	private boolean ischeck = false;
	
	public ChessSquare(int x, int y, Piece p) {		
		this.x = x;
		this.y = y;
		
		setLayout(new BorderLayout());
	
		if((x+y)%2==0) setBackground(new Color(113,198,113));
		else setBackground(Color.white);
	 
		if(p!=null) setPiece(p);
	}
	
	
	public ChessSquare(ChessSquare cs) throws CloneNotSupportedException {
		this.x = cs.x;
		this.y = cs.y;
		
		setLayout(new BorderLayout());
		
		if((x+y)%2==0) setBackground(new Color(113,198,113));
		else setBackground(Color.white);
		
		if(cs.getPiece()!=null) setPiece(cs.getPiece().getCopy());
		else piece=null;
	}
	
	public void setPiece(Piece piece) {
		//set piece
		this.piece = piece;
		//change label
		ImageIcon img = new ImageIcon(piece.getPath());
		content = new JLabel(img);
		this.add(content);
	}
	
	public Piece getPiece() { return this.piece; }
	
}
