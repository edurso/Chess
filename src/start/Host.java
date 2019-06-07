package start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chess.Board;
import chess.Game;

public class Host extends Thread{
	
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

	private static ObjectOutputStream out;

	private static ObjectInputStream in;
	
	public void run() {
		try {
			s = server.accept();
			out = new ObjectOutputStream(s.getOutputStream());
			in = new ObjectInputStream(s.getInputStream());
			Thread.currentThread().join();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void startServer() {
		try {
			s = server.accept();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    } 
	public void sendInfo(Board b) {
		try {
			if(s.isConnected()) {
				out.writeObject(b);
				out.writeObject(Settings.getActiveWhitePlayer());
			}
			
		}
		catch(IOException io) {
			io.printStackTrace();
			closeResources();
		}
		catch(NullPointerException nulle) {
			nulle.printStackTrace();
			closeResources();
		}
		catch(Exception e) {
			System.out.println("An unknown error occured");
			e.printStackTrace();
			closeResources();
		}
	}
	public ArrayList<Object> recieveBoard() {
		ArrayList<Object> obj = new <Object>ArrayList();
		try {
			if(s.isConnected()) {
				obj.add(in.readObject());
				obj.add(in.readObject());
			}
		}
		catch(IOException io) {
			io.printStackTrace();
			closeResources();
		}
		catch(NullPointerException nulle) {
			nulle.printStackTrace();
			closeResources();
		}
		catch(Exception e) {
			System.out.println("An unknown error occured");
			e.printStackTrace();
			closeResources();
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
			closeResources();
		}
	}
}

