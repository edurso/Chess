package start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chess.Board;
import chess.Game;

public class Host{
	
	/**
	 * Server socket that allows the client to establish a connection
	 */
	private static ServerSocket server;
	static {
		try {
			server = new ServerSocket(6666);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Socket that allows the server to interact with the client socket
	 */
	private static Socket s;

	/**
	 * A bufferedreader that allows the server to grab information that the client sends
	 */
	private static ObjectOutputStream out;
	
	private static ObjectInputStream in;
	
	public static void startServer() {
		try {
			s = server.accept();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    } 
	public void sendBoard(Board b) {
		try {
			if(s.isConnected()) {
				out = new ObjectOutputStream(s.getOutputStream());
				out.writeObject(b);
				
			}
			
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		catch(NullPointerException nulle) {
			nulle.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("An unknown error occured");
			e.printStackTrace();
		}
	}
	public Board recieveBoard() {
		try {
			if(s.isConnected()) {
				in = new ObjectInputStream(s.getInputStream());
				return (Board)(in.readObject());
			}
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		catch(NullPointerException nulle) {
			nulle.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("An unknown error occured");
			e.printStackTrace();
		}
		return null;
	}
	public void closeResources() {
		try {
			s.close();
			server.close();
			out.close();
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

