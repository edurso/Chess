package pieces;
import java.util.ArrayList;
import chess.ChessSquare;
public abstract class Piece {
	private int color;
	private String id = null;
	private String path;
	ArrayList<ChessSquare> possibleMoves = new ArrayList<>();
	
	
}
