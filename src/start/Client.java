package start;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import chess.Board;

public class Client {
	/**
	 * A socket that allows client to connect and interact with server
	 */
	private static Socket client;
	
	/**
	 * An object output stream that allows client to send messages to server
	 */
	private static ObjectOutputStream out;
	
	private static ObjectInputStream in;
	
	public static void var() {
		try {
			client = new Socket(OnlineMenu.getJoinAddress().getText(),6666);
			out = new ObjectOutputStream(client.getOutputStream());
			in = new ObjectInputStream(client.getInputStream());
		}
		catch(Exception e){
			e.printStackTrace();
			closeResources();
		}
	}
	
	public static void sendBoard(Board b) {
		try {
			if(client.isConnected()) {
				out.writeObject(b);
				out.writeObject(Settings.getActiveBlackPlayer());
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
	public static ArrayList<Object> recieveBoard() {
		ArrayList<Object> obj = new <Object>ArrayList();
		try {
			if(client.isConnected()) {
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
	
	public static void closeResources() {
		try {
			client.close();
			out.close();
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
