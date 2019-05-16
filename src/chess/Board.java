package chess;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
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

	private Castle wr01; 
	private Castle wr02;
	private Castle br01;
	private Castle br02;
	
	private Horse wk01;
	private Horse wk02;
	private Horse bk01;
	private Horse bk02;
	
	private Bishop wb01; 
	private Bishop wb02;
	private Bishop bb01;
	private Bishop bb02;
	
	private Queen wq;
	private Queen bq;
	
	private King wk;
	private King bk;
	
	private Pawn wp[] = new Pawn[8]; 
	private Pawn bp[] = new Pawn[8];
	
	private static final String WHITE_MOVE = "WHITE";
	private static final String BLACK_MOVE = "BLACK";
	static String Move = WHITE_MOVE;
	
	private Player whitePlayer = null;
	private Player blackPlayer = null;
	
	private JPanel board;
	
	private JPanel optionPanel;
	
	private ChessSquare cs;
	
	public Board(Player whitePlayer, Player blackPlayer) {
		
		wr01 = new Castle("WR01", "whiteRook.png", Piece.COLOR_WHITE); 
		wr02 = new Castle("WR02", "whiteRook.png", Piece.COLOR_WHITE);
		br01 = new Castle("BR01", "blackRook.png", Piece.COLOR_BLACK); 
		br02 = new Castle("BR02", "blackRook.png", Piece.COLOR_BLACK);
		
		wk01 = new Horse("WK01", "whiteKnight.png", Piece.COLOR_WHITE);
		wk02 = new Horse("WK02", "whiteKnight.png", Piece.COLOR_WHITE);
		bk01 = new Horse("BK01", "blackKnight.png", Piece.COLOR_BLACK);
		bk02 = new Horse("BK02", "blackKnight.png", Piece.COLOR_BLACK);
		
		wb01 = new Bishop("WB01", "whiteBishop.png", Piece.COLOR_WHITE); 
		wb02 = new Bishop("WB02", "whiteBishop.png", Piece.COLOR_WHITE); 
		bb01 = new Bishop("BB01", "blackBishop.png", Piece.COLOR_BLACK); 
		bb02 = new Bishop("BB02", "blackBishop.png", Piece.COLOR_BLACK);
		
		wq = new Queen("WQ", "whiteQueen.png", Piece.COLOR_WHITE); 
		bq = new Queen("BQ", "blackQueen.png", Piece.COLOR_BLACK);
		
		wk = new King("WK", "whiteKing.png", Piece.COLOR_WHITE, 7, 3);
		bk = new King("BK", "blackKing.png", Piece.COLOR_BLACK, 0, 3);
		
		for(int i = 0 ; i < 8 ; i++) {
			wp[i] = new Pawn(("WP0" + (i + 1)), "whitePawn.png", Piece.COLOR_WHITE);
			bp[i] = new Pawn(("BP0" + (i + 1)), "blackPawn.png", Piece.COLOR_BLACK);
		}
		
		board = new JPanel(new GridLayout(8, 8));
		
		optionPanel = new JPanel();
		
		boardState = new ChessSquare[8][8];
		
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		
		Piece P;
		for(int i = 0 ; i < 8 ; i++) {
			for(int j = 0 ; j < 8 ; j++) {	
				P = null;
				if(i == 0 && j == 0)       P = br01;
				else if(i == 0 && j == 7)  P = br02;
				else if(i == 7 && j == 0)  P = wr01;
				else if(i == 7 && j == 7)  P = wr02;
				else if(i == 0 && j == 1)  P = bk01;
				else if(i == 0 && j == 6)  P = bk02;
				else if(i == 7 && j == 1)  P = wk01;
				else if(i == 7 && j == 6)  P = wk02;
				else if(i == 0 && j == 2)  P = bb01;
				else if(i == 0 && j == 5)  P = bb02;
				else if(i == 7 && j == 2)  P = wb01;
				else if(i == 7 && j == 5)  P = wb02;
				else if(i == 0 && j == 3)  P = bk;
				else if(i == 0 && j == 4)  P = bq;
				else if(i == 7 && j == 3)  P = wk;
				else if(i == 7 && j == 4)  P = wq;
				else if(i == 1)            P = bp[j];
				else if(i == 6)            P = wp[j];
				
				cs = new ChessSquare(i, j, P);
				cs.addMouseListener(this);
				board.add(cs);//https://stackoverflow.com/questions/47879323/how-do-i-get-coordinates-of-a-jpanel-in-an-8x8-gridlayout
				boardState[i][j] = cs;
			}
		}
		
		setTitle("Chess");
		setSize(1200, 825);
		setResizable(false);
		add(board);
		add(optionPanel);
		board.setBounds(0, 0, 800, 800);
		
		optionPanel.setBounds(800, 0, 400, 825);
		
	}
	
	public void play() {
		setVisible(true);
		//System.out.println("Piece at 0, 0 is ... " + boardState[0][0].getPiece().getPath());//just for testing
		//TODO - help
	}
	
	public King getKing(int color) {
		if (color == Piece.COLOR_WHITE) return wk;
		return bk;
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
