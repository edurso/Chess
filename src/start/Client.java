package start;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import chess.Board;

public class Client extends OnlineMenu{
	/**
	 * A socket that allows client to connect and interact with server
	 */
	private static Socket client;
	static {
		try {
			client = new Socket(joinAddress.getText(),6666);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * An object output stream that allows client to send messages to server
	 */
	private static ObjectOutputStream out;
	
	private static ObjectInputStream in;
	
	public static void joinServer() {
	}
	public void sendBoard(Board b) {
		try {
			if(client.isConnected()) {
				out = new ObjectOutputStream(client.getOutputStream());
				out.writeObject(b);
				client.close();
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
			if(client.isConnected()) {
				in = new ObjectInputStream(client.getInputStream());
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
			client.close();
			out.close();
			in.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
