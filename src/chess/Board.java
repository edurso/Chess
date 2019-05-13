package chess;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

import base.Menu;
import pieces.*;
import user.Player;

public class Board extends JFrame implements MouseListener{
	
	private ChessSquare[][] boardState;

	private static Castle wr01 = new Castle("WR01", "whiteRook.png", Piece.COLOR_WHITE); 
	private static Castle wr02 = new Castle("WR02", "whiteRook.png", Piece.COLOR_WHITE);
	private static Castle br01 = new Castle("BR01", "blackRook.png", Piece.COLOR_BLACK); 
	private static Castle br02 = new Castle("BR02", "blackRook.png", Piece.COLOR_BLACK);
	
	private static Horse wk01 = new Horse("WK01", "whiteKnight.png", Piece.COLOR_WHITE);
	private static Horse wk02 = new Horse("WK02", "whiteKnight.png", Piece.COLOR_WHITE);
	private static Horse bk01 = new Horse("BK01", "blackKnight.png", Piece.COLOR_BLACK);
	private static Horse bk02 = new Horse("BK02", "blackKnight.png", Piece.COLOR_BLACK);
	
	private static Bishop wb01 = new Bishop("WB01", "whiteBishop.png", Piece.COLOR_WHITE); 
	private static Bishop wb02 = new Bishop("WB02", "whiteBishop.png", Piece.COLOR_WHITE); 
	private static Bishop bb01 = new Bishop("BB01", "blackBishop.png", Piece.COLOR_BLACK); 
	private static Bishop bb02 = new Bishop("BB02", "blackBishop.png", Piece.COLOR_BLACK);
	
	private static Queen wq = new Queen("WQ", "whiteQueen.png", Piece.COLOR_WHITE); 
	private static Queen bq = new Queen("BQ", "blackQueen.png", Piece.COLOR_BLACK);
	
	private static King wk = new King("WK", "whiteKing.png", Piece.COLOR_WHITE, 7, 3);
	private static King bk = new King("BK", "blackKing.png", Piece.COLOR_BLACK, 0, 3);
	
	private static Pawn wp[] = new Pawn[8]; 
	private static Pawn bp[] = new Pawn[8];
	
	static {
		for(int i = 0 ; i < 8 ; i++) {
			wp[i] = new Pawn(("WP0" + (i + 1)), "whitePawn.png", Piece.COLOR_WHITE);
			bp[i] = new Pawn(("BP0" + (i + 1)), "blackPawn.png", Piece.COLOR_BLACK);
		}
	}
	
	private static final String WHITE_MOVE = "WHITE";
	private static final String BLACK_MOVE = "BLACK";
	static String Move = WHITE_MOVE;
	
	private static Board mainBoard = new Board(Menu.getActiveWhitePlayer(), Menu.getActiveBlackPlayer());
	
	private Player whitePlayer = null;
	private Player blackPlayer = null;
	
	private JPanel board;
	
	private ChessSquare cs;
	
	public Board(Player whitePlayer, Player blackPlayer) {
		
		board = new JPanel(new GridLayout(8, 8));
		
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		
		Piece P;
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0 ; j < 8 ; j++) {	
				P = null;
				if(i == 0 && j == 0)      P=br01;
				else if(i == 0 && j == 7) P=br02;
				else if(i == 7 && j == 0) P=wr01;
				else if(i == 7 && j == 7) P=wr02;
				else if(i == 0 && j == 1) P=bk01;
				else if(i == 0 && j == 6) P=bk02;
				else if(i == 7 && j == 1) P=wk01;
				else if(i == 7 && j == 6) P=wk02;
				else if(i == 0 && j == 2) P=bb01;
				else if(i == 0 && j == 5) P=bb02;
				else if(i == 7 && j == 2) P=wb01;
				else if(i == 7 && j == 5) P=wb02;
				else if(i == 0 && j == 3) P=bk;
				else if(i == 0 && j == 4) P=bq;
				else if(i == 7 && j == 3) P=wk;
				else if(i == 7 && j == 4) P=wq;
				else if(i == 1)           P=bp[j];
				else if(i == 6)           P=wp[j];
				
				cs = new ChessSquare(i, j, P);
				cs.addMouseListener(this);
				board.add(cs);
				boardState[i][j] = cs;
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
