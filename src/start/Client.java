package start;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

//TODO - Javadoc
public class Client extends OnlineMenu{
	/**
	 * A socket that allows client to connect and interact with server
	 */
	private static Socket client;
	
	/**
	 * A printwriter that allows client to send messages to server
	 */
	private static PrintWriter out;
	
	public static void joinServer() {
		try {
			client = new Socket(joinAddress.getText(),6666);
			if(client.isConnected()) {
				out = new PrintWriter(client.getOutputStream(),true);
				out.println("please dear god work");
				client.close();
			}
			
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		catch(NullPointerException nu) {
			nu.printStackTrace();
		}
	}
}
