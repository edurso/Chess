package chess;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

	static String move = WHITE_MOVE;
	
	private Player whitePlayer = null;
	private Player blackPlayer = null;
	
	private JPanel board;
	
	private JPanel optionPanel;
	
	private ChessSquare cs;

	private ChessSquare c;

	private ChessSquare previous;

	private int chance;

	private ArrayList<ChessSquare> destinList;

	private boolean end;

	private Player winner;
	
	public Board(Player whitePlayer, Player blackPlayer) {

		chance = 0;

		end = false;//true if game has ended

		destinList = new ArrayList<>();
		
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
				board.add(cs);
				boardState[i][j] = cs;
			}
		}
		
		setTitle("Chess");
		setSize(1200, 825);
		setResizable(false);
		add(board);
		board.setBounds(0, 0, 800, 800);
		add(optionPanel);
		optionPanel.setBounds(800, 0, 400, 825);
		
	}
	
	public void play() {
		setVisible(true);
		//System.out.println("Piece at 0, 0 is ... " + boardState[0][0].getPiece().getPath());//just for testing
	}
	
	public King getKing(int color) {
		if (color == Piece.COLOR_WHITE) return wk;
		return bk;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		c = (ChessSquare) e.getSource();
		if (previous == null) {//if this is the first piece we are selecting . . . 
			if(c.getPiece() != null) {
				if(c.getPiece().getColor() != chance) return;
				c.select();
				previous = c;
				destinList.clear();
				destinList = c.getPiece().move(boardState, c.x, c.y);
				if(c.getPiece() instanceof King) destinList = filterdestination(destinList, c);
				else {
					if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck()) destinList = new ArrayList<ChessSquare>(filterdestination(destinList,c));
					else if(destinList.isEmpty() == false && willkingbeindanger(c, destinList.get(0))) destinList.clear();
				}
				highlightdestinations(destinList);
			}
		} else {
			if(c.x == previous.x && c.y == previous.y) {
				c.deselect();
				cleandestinations(destinList);
				destinList.clear();
				previous = null;
			} else if(c.getPiece()==null||previous.getPiece().getColor() != c.getPiece().getColor()) {//null pointer?
				if(c.isPossibleDestination()) {
					if(c.getPiece() != null) c.removePiece();
					c.setPiece(previous.getPiece());
					if (previous.isCheck()) previous.removeCheck();
					previous.removePiece();
					if(getKing(chance^1).threatExists(boardState)) {
						boardState[getKing(chance^1).getX()][getKing(chance^1).getY()].setCheck();
						if (checkmate(getKing(chance^1).getColor())) {
							previous.deselect();
							if(previous.getPiece()!=null)
								previous.removePiece();
							gameEnd();
						}
					}
					if(getKing(chance).threatExists(boardState)==false) boardState[getKing(chance).getX()][getKing(chance).getY()].removeCheck();
					if(c.getPiece() instanceof King) {
						((King)c.getPiece()).setX(c.x);
						((King)c.getPiece()).setY(c.y);
					}
					changechance();
					if(!end) {
						//restart timer
					}
				}
				if(previous != null) {
					previous.deselect();
					previous=null;
				}
				cleandestinations(destinList);
				destinList.clear();
			} else if(previous.getPiece().getColor()==c.getPiece().getColor()) {
				previous.deselect();
				cleandestinations(destinList);
				destinList.clear();
				c.select();
				previous=c;
				destinList=c.getPiece().move(boardState, c.x, c.y);
				if(c.getPiece() instanceof King)
					destinList=filterdestination(destinList,c);
				else
				{
					if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck())
						destinList = new ArrayList<ChessSquare>(filterdestination(destinList,c));
					else if(destinList.isEmpty()==false && willkingbeindanger(c,destinList.get(0)))
						destinList.clear();
				}
				highlightdestinations(destinList);
			}
		}
		if(c.getPiece() != null && c.getPiece() instanceof King) {
			((King)c.getPiece()).setX(c.x);
			((King)c.getPiece()).setY(c.y);
		}
	}

	public void changechance() {
		if (boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck()) {
			chance ^= 1;
			gameEnd();
		}
		if(destinList.isEmpty() == false) cleandestinations(destinList);
		if(previous != null) previous.deselect();
		previous = null;
		chance ^= 1;
		if(!end /*&& timer != null*/){
			//reset timer
			//showPlayer.remove(CHNC);
			if(Board.move == Board.WHITE_MOVE) Board.move = Board.BLACK_MOVE;
			else Board.move = Board.WHITE_MOVE;
			//CHNC.setText(Main.move);
			//showPlayer.add(CHNC);
		}
	}

	private ArrayList<ChessSquare> filterdestination (ArrayList<ChessSquare> destlist, ChessSquare fromSquare) {
    	ArrayList<ChessSquare> newlist = new ArrayList<ChessSquare>();
    	ChessSquare newboardstate[][] = new ChessSquare[8][8];
    	ListIterator<ChessSquare> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new ChessSquare(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		
    		ChessSquare tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getPiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromSquare.x][fromSquare.y].getPiece());
    		x=getKing(chance).getX();
    		y=getKing(chance).getY();
    		if(newboardstate[fromSquare.x][fromSquare.y].getPiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).setX(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).setY(tempc.y);
    			x = tempc.x;
    			y = tempc.y;
    		}
    		newboardstate[fromSquare.x][fromSquare.y].removePiece();
    		if ((((King)(newboardstate[x][y].getPiece())).threatExists(newboardstate)==false)) newlist.add(tempc);
    	}
    	return newlist;
    }

	private boolean willkingbeindanger(ChessSquare fromSquare, ChessSquare toSquare) {
    	ChessSquare[][] newboardstate = new ChessSquare[8][8];
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
    		{	try { newboardstate[i][j] = new ChessSquare(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning"); }}
    	
    	// if(newboardstate[fromSquare.x][fromSquare.y].getPiece()!=null)
		// 	newboardstate[fromSquare.x][fromSquare.y].removePiece();
    	
		newboardstate[toSquare.x][toSquare.y].setPiece(newboardstate[fromSquare.x][fromSquare.y].getPiece());
		if(newboardstate[toSquare.x][toSquare.y].getPiece() instanceof King)
		{
			((King)(newboardstate[toSquare.x][toSquare.y].getPiece())).setX(toSquare.x);
			((King)(newboardstate[toSquare.x][toSquare.y].getPiece())).setY(fromSquare.y);
		}
		newboardstate[fromSquare.x][fromSquare.y].removePiece();
		if (((King)(newboardstate[getKing(chance).getX()][getKing(chance).getY()].getPiece())).threatExists(newboardstate) == true)
			return true;
		else return false;
    }

	private void cleandestinations(ArrayList<ChessSquare> destlist) {
    	ListIterator<ChessSquare> it = destlist.listIterator();
    	while(it.hasNext()) it.next().setImpossibleDestination();
	} 
	
    private void highlightdestinations(ArrayList<ChessSquare> destlist) {
    	ListIterator<ChessSquare> it = destlist.listIterator();
    	while(it.hasNext()) it.next().setPossibleDestination();
    }

	private ArrayList<ChessSquare> incheckfilter (ArrayList<ChessSquare> destList, ChessSquare fromSquare, int color) {
    	ArrayList<ChessSquare> newList = new ArrayList<>();
    	ChessSquare[][] newboardstate = new ChessSquare[8][8];
    	ListIterator<ChessSquare> it = destList.listIterator();
    	int x,y;
    	while (it.hasNext()) {
    		for(int i=0;i<8;i++) for(int j=0;j<8;j++) {try { newboardstate[i][j] = new ChessSquare(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
			ChessSquare tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getPiece() != null) newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromSquare.x][fromSquare.y].getPiece());
    		x = getKing(color).getX();
    		y = getKing(color).getY();
    		if(newboardstate[tempc.x][tempc.y].getPiece() instanceof King) {
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).setX(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getPiece())).setY(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromSquare.x][fromSquare.y].removePiece();
    		if ((((King)(newboardstate[x][y].getPiece())).threatExists(newboardstate) == false)) newList.add(tempc);
    	}
    	return newList;
    }

	public boolean checkmate(int color) {
    	ArrayList<ChessSquare> dlist = new ArrayList<>();
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			if (boardState[i][j].getPiece() != null && boardState[i][j].getPiece().getColor() == color) {
    				dlist.clear();
    				dlist = boardState[i][j].getPiece().move(boardState, i, j);
    				dlist = incheckfilter(dlist,boardState[i][j],color);
    				if(dlist.size()!=0) return false;
    			}
    		}
    	}
    	return true;
    }

	private void gameEnd() {
		String winMsg = "The ";
    	cleandestinations(destinList);
    	if(previous != null) previous.removePiece();
    	if(chance == Piece.COLOR_WHITE) {	
			//update white player stats, set winner
			winner = whitePlayer;
			winMsg += "White Player Won!";
			whitePlayer.addGamePlayed(true);
			blackPlayer.addGamePlayed(false);
		} else {
			//update black player stats, set winner
			winner = blackPlayer;
			winMsg += "Black Player Won!";
			whitePlayer.addGamePlayed(false);
			blackPlayer.addGamePlayed(true);
		}
		end = true;
		//show winner
		winMsg += ("\tCongrats " + winner.getUsername());
		JPanel dubPanel = new JPanel();
		JLabel dubLabel = new JLabel();
		this.removeAll();
		dubLabel.setText(winMsg);
		dubPanel.add(dubLabel);
		this.add(dubPanel);
		//dispose this
		System.out.println("SOmeone won");
		//dispose();
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
