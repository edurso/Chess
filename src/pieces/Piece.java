package pieces;

import java.util.ArrayList;
import chess.ChessSquare;

public abstract class Piece implements Cloneable{
	
	private int color;
	
	private String id = null;
	
	private String path;//image path
	
	ArrayList<ChessSquare> possibleMoves = new ArrayList<>();
	
	public abstract ArrayList<ChessSquare> move(ChessSquare[][] pos, int x, int y);
	
	public Piece getCopy() throws CloneNotSupportedException{ return (Piece) this.clone(); }

	public int getColor() { return color; }

	public void setColor(int color) { this.color = color; }

	public String getId() { return id; }

	public void setId(String id) { this.id = id; }

	public String getPath() { return path; }

	public void setPath(String path) { this.path = path; }
	
}
