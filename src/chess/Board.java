package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import start.*;
import user.AI;
import user.Player;
import user.User;
import pieces.*;

/**
 * Class sets up and runs board game. Also contains methods for checking piece conditions
 * @author edurso
 */
public class Board extends JFrame implements MouseListener{
	
	/**
	 * Stores current state of the board
	 */
	private ChessSquare[][] boardState;

	/**
	 * White Rook 1
	 */
	private Castle wr01; 
	
	/**
	 * White Rook 2
	 */
	private Castle wr02;
	
	/**
	 * Black Rook 1
	 */
	private Castle br01;
	
	/**
	 * Black Rook 2
	 */
	private Castle br02;
	
	/**
	 * White Knight 1
	 */
	private Horse wk01;
	
	/**
	 * White Knight 2
	 */
	private Horse wk02;
	
	/**
	 * Black Knight 1
	 */
	private Horse bk01;
	
	/**
	 * Black Knight 2
	 */
	private Horse bk02;
	
	/**
	 * White Bishop 1
	 */
	private Bishop wb01; 
	
	/**
	 * White Bishop 2
	 */
	private Bishop wb02;
	
	/**
	 * Black Bishop 1
	 */
	private Bishop bb01;
	
	/**
	 * Black Bishop 2
	 */
	private Bishop bb02;
	
	/**
	 * White Queen
	 */
	private Queen wq;
	
	/**
	 * Black Queen
	 */
	private Queen bq;
	
	/**
	 * White King
	 */
	private King wk;
	
	/**
	 * Black King
	 */
	private King bk;
	
	/**
	 * White Pawns
	 */
	private Pawn wp[] = new Pawn[8]; 
	
	/**
	 * Black Pawns
	 */
	private Pawn bp[] = new Pawn[8];
	
	/**
	 * Indicates it is white's turn
	 */
	private static final String WHITE_MOVE = "WHITE";
	
	/**
	 * Indicates it is black's turn
	 */
	private static final String BLACK_MOVE = "BLACK";

	/**
	 * Stores the current turn as a string
	 */
	static String move = WHITE_MOVE;
	
	/**
	 * White player object
	 */
	private Player whitePlayer = null;
	
	/**
	 * Black player object
	 */
	private Player blackPlayer = null;
	
	/**
	 * graphic container for board
	 */
	private JPanel board;
	
	/**
	 * graphic container for option menu
	 */
	private JPanel optionPanel;
	
	/**
	 * temporary square used in initialization
	 */
	private ChessSquare cs;
	
	/**
	 * temporary square used in game
	 */
	private ChessSquare c;

	/**
	 * placeholder for first cell clicked - piece must not be null
	 */
	private ChessSquare previous;
	
	/**
	 * Stores players turn as int
	 */
	private int chance;

	/**
	 * list of possible destinations of selected piece
	 */
	private ArrayList<ChessSquare> destinList;

	/**
	 * true if game has ended
	 */
	private boolean end;

	/**
	 * used to indicate winning player
	 */
	private Player winner;
	
	/**
	 * window for win message
	 */
	private static JFrame winFrame;

	/**
	 * container for win message components
	 */
	private static JPanel winPanel;

	/**
	 * win message
	 */
	private static JTextField winText;
	
	/**
	 * button restarts the game
	 */
	private static JButton restartButton;

	/**
	 * button exits the application
	 */
	private static JButton quitButton;

	/**
	 * initializes board and pieces
	 * @param whitePlayer selected white player to play game
	 * @param blackPlayer selected black player to play game
	 */
	public Board(Player whitePlayer, Player blackPlayer) {

		if(whitePlayer == null) whitePlayer = new User("GuestWhitePlayer"); 
		if(blackPlayer == null) blackPlayer = new User("GuestBlackPlayer");

		chance = Piece.COLOR_WHITE;

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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 825);
		setResizable(false);
		add(board);
		board.setBounds(0, 0, 800, 800);
		add(optionPanel);
		optionPanel.setBounds(800, 0, 400, 825);
		
	}
	
	/**
	 * reveals the game board and allows the game to be played
	 */
	public void play() {
		setVisible(true);
		//System.out.println(Settings.getActiveWhitePlayer().getUsername());
		//System.out.println("Piece at 0, 0 is ... " + boardState[0][0].getPiece().getPath());//just for testing
	}

	/**
	 * retrieves the king of the designated color
	 * @param color color of the king to be returned
	 * @return the king of {@code color} 
	 */
	public King getKing(int color) {
		if (color == Piece.COLOR_WHITE) return wk;
		return bk;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		c = (ChessSquare) e.getSource();
		if (previous == null) {//if this is the first piece we are selecting . . . 
			if(c.getPiece() != null) {
				if(c.getPiece().getColor() != chance) return;
				else if(blackPlayer instanceof AI && c.getPiece().getColor() == Piece.COLOR_BLACK) return;
				c.select();
				previous = c;
				destinList.clear();
				destinList = c.getPiece().move(boardState, c.x, c.y);
				if(c.getPiece() instanceof King) destinList = destinaionFilter(destinList, c);
				else {
					if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck()) destinList = new ArrayList<ChessSquare>(destinaionFilter(destinList,c));
					else if(destinList.isEmpty() == false && endangersKing(c, destinList.get(0))) destinList.clear();
				}
				showDestins(destinList);
			}
		} else {//if we have already selected a piecee and are now selecting a destination
			if(c.x == previous.x && c.y == previous.y) {//deselect if the same cell was clicked
				c.deselect();
				clearDestinations(destinList);
				destinList.clear();
				previous = null;
			} else if(c.getPiece() == null || ( (c.getPiece() != null) && (previous.getPiece().getColor() != c.getPiece().getColor()))) {//null pointer?
				if(c.isPossibleDestination()) {
					if(c.getPiece() != null) c.removePiece();
					c.setPiece(previous.getPiece());
					if (previous.isCheck()) previous.removeCheck();
					previous.removePiece();
					if(getKing(chance^1).threatExists(boardState)) {
						boardState[getKing(chance^1).getX()][getKing(chance^1).getY()].setCheck();
						if (checkmate(getKing(chance^1).getColor())) {
							previous.deselect();
							if(previous.getPiece() != null) previous.removePiece();
							gameEnd();
						}
					}
					if(getKing(chance).threatExists(boardState) == false) boardState[getKing(chance).getX()][getKing(chance).getY()].removeCheck();
					if(c.getPiece() instanceof King) {
						((King)c.getPiece()).setX(c.x);
						((King)c.getPiece()).setY(c.y);
					}
					changeChance();
					if(!end) {
						//restart timer
					}
				}
				if(previous != null) {
					previous.deselect();
					previous = null;
				}
				clearDestinations(destinList);
				destinList.clear();
			} else if(previous.getPiece().getColor() == c.getPiece().getColor()) {
				previous.deselect();
				clearDestinations(destinList);
				destinList.clear();
				c.select();
				previous = c;
				destinList = c.getPiece().move(boardState, c.x, c.y);
				if(c.getPiece() instanceof King)
					destinList = destinaionFilter(destinList, c);
				else {
					if(boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck()) destinList = new ArrayList<ChessSquare>(destinaionFilter(destinList,c));
					else if(destinList.isEmpty()==false && endangersKing(c,destinList.get(0))) destinList.clear();
				}
				showDestins(destinList);
			}
		}
		if(c.getPiece() != null && c.getPiece() instanceof King) {
			((King)c.getPiece()).setX(c.x);
			((King)c.getPiece()).setY(c.y);
		}
	}

	/**
	 * Alternate turn between players
	 */
	public void changeChance() {
		if (boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck()) {
			chance ^= 1;
			gameEnd();
		}
		if(destinList.isEmpty() == false) clearDestinations(destinList);
		if(previous != null) previous.deselect();
		previous = null;
		chance ^= 1;
		if(!end /*&& timer != null*/){
			//reset timer
			if(Board.move == Board.WHITE_MOVE) Board.move = Board.BLACK_MOVE;
			else Board.move = Board.WHITE_MOVE;
		}
		if(Board.move == Board.BLACK_MOVE && blackPlayer instanceof AI) {
			//System.out.println("here");//?????????????????????????????
			blackPlayer.move(boardState);
			// changeChance();
			//cleanup
			if (boardState[getKing(chance).getX()][getKing(chance).getY()].isCheck()) {
				chance ^= 1;
				gameEnd();
			}
			if(destinList.isEmpty() == false) clearDestinations(destinList);
			if(previous != null) previous.deselect();
			previous = null;
			chance ^= 1;
			if(!end /*&& timer != null*/){
				//reset timer
				// if(Board.move == Board.WHITE_MOVE) Board.move = Board.BLACK_MOVE;
				// else Board.move = Board.WHITE_MOVE;
				Board.move = Board.WHITE_MOVE;//?
			}
		}
	}

	/**
	 * filters out moves that would put the king in danger
	 * @param destinList list of possible destinations
	 * @param fromSquare square from which move will be made
	 * @return new list of destinations with those that harm the king out
	 */
	private ArrayList<ChessSquare> destinaionFilter (ArrayList<ChessSquare> destinList, ChessSquare fromSquare) {
    	ArrayList<ChessSquare> newList = new ArrayList<ChessSquare>();
    	ChessSquare newBoardState[][] = new ChessSquare[8][8];
    	ListIterator<ChessSquare> it = destinList.listIterator();
    	int x,y;
    	while (it.hasNext()) {
    		for(int i = 0 ; i < 8 ; i++) for(int j = 0 ; j < 8 ; j++) { try { newBoardState[i][j] = new ChessSquare(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		ChessSquare temp = it.next();
    		if(newBoardState[temp.x][temp.y].getPiece() != null) newBoardState[temp.x][temp.y].removePiece();
    		newBoardState[temp.x][temp.y].setPiece(newBoardState[fromSquare.x][fromSquare.y].getPiece());
    		x = getKing(chance).getX();
    		y = getKing(chance).getY();
    		if(newBoardState[fromSquare.x][fromSquare.y].getPiece() instanceof King) {
    			((King)(newBoardState[temp.x][temp.y].getPiece())).setX(temp.x);
    			((King)(newBoardState[temp.x][temp.y].getPiece())).setY(temp.y);
    			x = temp.x;
    			y = temp.y;
    		}
    		newBoardState[fromSquare.x][fromSquare.y].removePiece();
    		if ((newBoardState[x][y].getPiece() instanceof King) && (((King)(newBoardState[x][y].getPiece())).threatExists(newBoardState) == false)) newList.add(temp);
    	}
    	return newList;
    }

	/**
	 * determines if moving the piece on {@code fromSquare} to {@code toSquare} will harm the king
	 * @param fromSquare initial  square
	 * @param toSquare square to which the king will be moved
	 * @return true if the move will put the king in danger, false otherwise
	 */
	private boolean endangersKing(ChessSquare fromSquare, ChessSquare toSquare) {
    	ChessSquare[][] newBoardState = new ChessSquare[8][8];
    	for(int i = 0 ; i < 8 ; i++) for(int j = 0 ; j < 8; j++) { try { newBoardState[i][j] = new ChessSquare(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("Cloning Error"); }}
    	newBoardState[toSquare.x][toSquare.y].setPiece(newBoardState[fromSquare.x][fromSquare.y].getPiece());
		if(newBoardState[toSquare.x][toSquare.y].getPiece() instanceof King) {
			((King)(newBoardState[toSquare.x][toSquare.y].getPiece())).setX(toSquare.x);
			((King)(newBoardState[toSquare.x][toSquare.y].getPiece())).setY(fromSquare.y);
		}
		newBoardState[fromSquare.x][fromSquare.y].removePiece();
		if (((newBoardState[getKing(chance).getX()][getKing(chance).getY()].getPiece() instanceof King) && ((King)(newBoardState[getKing(chance).getX()][getKing(chance).getY()].getPiece())).threatExists(newBoardState) == true)) return true;
		else return false;
    }

	/**
	 * removes all possible destinations from the list
	 * @param destinList list to reset destinations of
	 */
	private void clearDestinations(ArrayList<ChessSquare> destinList) {
    	ListIterator<ChessSquare> it = destinList.listIterator();
    	while(it.hasNext()) it.next().setImpossibleDestination();
	} 
	
	/**
	 * highlights possible destinations in list
	 * @param destinList list of possible destinations
	 */
    private void showDestins(ArrayList<ChessSquare> destinList) {
    	ListIterator<ChessSquare> it = destinList.listIterator();
    	while(it.hasNext()) it.next().setPossibleDestination();
    }

    /**
     * filters out destinations that would cause self-check
     * @param destinList list of destinations
     * @param fromSquare square from which move made
     * @param color color of mover
     * @return filtered list
     */
	private ArrayList<ChessSquare> inCheckFilter (ArrayList<ChessSquare> destinList, ChessSquare fromSquare, int color) {
    	ArrayList<ChessSquare> newList = new ArrayList<>();
    	ChessSquare[][] newBoardState = new ChessSquare[8][8];
    	ListIterator<ChessSquare> it = destinList.listIterator();
    	int x,y;
    	while (it.hasNext()) {
    		for(int i=0;i<8;i++) for(int j=0;j<8;j++) { try { newBoardState[i][j] = new ChessSquare(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
			ChessSquare tempc = it.next();
    		if(newBoardState[tempc.x][tempc.y].getPiece() != null) newBoardState[tempc.x][tempc.y].removePiece();
    		newBoardState[tempc.x][tempc.y].setPiece(newBoardState[fromSquare.x][fromSquare.y].getPiece());
    		x = getKing(color).getX();
    		y = getKing(color).getY();
    		if(newBoardState[tempc.x][tempc.y].getPiece() instanceof King) {
    			((King)(newBoardState[tempc.x][tempc.y].getPiece())).setX(tempc.x);
    			((King)(newBoardState[tempc.x][tempc.y].getPiece())).setY(tempc.y);
    			x = tempc.x;
    			y = tempc.y;
    		}
    		newBoardState[fromSquare.x][fromSquare.y].removePiece();
    		if ((newBoardState[x][y].getPiece() instanceof King) && (((King)(newBoardState[x][y].getPiece())).threatExists(newBoardState) == false)) newList.add(tempc);
    	}
    	return newList;
    }

	/**
	 * determines if chceckmate has happened
	 * @param color to check if they are in check
	 * @return true if {@code color} is in check, false otherwise
	 */
	public boolean checkmate(int color) {
    	ArrayList<ChessSquare> dlist = new ArrayList<>();
    	for(int i=0;i<8;i++) {
    		for(int j=0;j<8;j++) {
    			if (boardState[i][j].getPiece() != null && boardState[i][j].getPiece().getColor() == color) {
    				dlist.clear();
    				dlist = boardState[i][j].getPiece().move(boardState, i, j);
    				dlist = inCheckFilter(dlist,boardState[i][j],color);
    				if(dlist.size() != 0) return false;
    			}
    		}
    	}
    	return true;
    }

	/**
	 * run when game is terminated
	 */
	public void gameEnd() {
		String winMsg = "The ";
    	clearDestinations(destinList);
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
		Settings.updatePlayersMenuStats(""+whitePlayer.getGamesWon(), ""+whitePlayer.getWinPercent(), ""+blackPlayer.getGamesWon(), ""+blackPlayer.getWinPercent());
		//save player data
		User.setActiveWhite(whitePlayer.getUsername());
		User.setActiveBlack(blackPlayer.getUsername());
		whitePlayer.savePlayerData();
		blackPlayer.savePlayerData();
		//show winner
		winMsg += ("\nCongrats " + winner.getUsername());
		//dispose this
		//System.out.println("SOmeone won");
		dispose();
		initWinWindow(winMsg);
		revealWinWindow();
	}
	
	/**
	 * reveals vistory window
	 */
	public static void revealWinWindow() { winFrame.setVisible(true); }
	
	/**
	 * hides victory window
	 */
	public static void killWinWindow() { winFrame.dispose(); }
	
	/**
	 * initializes victory window with message {@code msg}
	 * @param msg message for victory window
	 */
	private static void initWinWindow(String msg) {
		winFrame = new JFrame();
		winFrame.setVisible(false);
		winPanel = new JPanel();
		winPanel.setBackground(Color.black);
		winFrame.setTitle("Chess");
		winFrame.setBounds(500, 500, 300, 250);
		winText = new JTextField();
		winText.setText(msg);
		winText.setEditable(false);
		winText.setBackground(Color.white);
		restartButton = new JButton("Restart");
	    restartButton.setBackground(Color.RED);
	    restartButton.setFont(Menu.f);
		quitButton = new JButton("Quit");
	    quitButton.setBackground(Color.RED);
	    quitButton.setFont(Menu.f);
		winPanel.add(restartButton);
		winPanel.add(quitButton);
	    restartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				winFrame.dispose();
			    Game.menu.start();
			}
		});
		quitButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) { System.exit(0); } });
		winPanel.add(winText);
		winFrame.add(winPanel);
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
