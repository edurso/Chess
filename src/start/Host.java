package start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import chess.Game;

//TODO - Javadoc
public class Host{
	/**
	 * Socket that allows the server to interact with the client socket
	 */
	private static Socket s;
	
	/**
	 * Server socket that allows the client to establish a connection
	 */
	private static ServerSocket server;
	
	/**
	 * A bufferedreader that allows the server to grab information that the client sends
	 */
	private static BufferedReader in;
	
	public static void startServer() {
 		try { server = new ServerSocket(6666); } catch(IOException io) { io.printStackTrace(); }
   		try { s = server.accept();/* problem here */ } catch (IOException e1) { e1.printStackTrace(); }
    		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    		String temp;
    		try {
			while ((temp = in.readLine()) != null) {
				System.out.println(temp);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
     	try { server.close(); } catch (IOException e) { e.printStackTrace(); }
    		
     	
    } 
}

