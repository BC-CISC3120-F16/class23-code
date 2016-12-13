import java.io.*;
import java.net.*;

public class ChatterClient {
	
	final static int SERVER_PORT = 3333;
	
	public static void main(String [] args) throws Exception {
		
		// create a new socket connecting to the server specified on the command line, with the port designated for the 'chatter' protocol
		
		Socket serverSocket = new Socket(args[0], SERVER_PORT);

		// set up input and output streams at the socket (output goes out to the server; input comes in from the server)
		
		OutputStream os = serverSocket.getOutputStream();
		System.out.println(os.getClass().getName());
		
		PrintStream toServer = new PrintStream(serverSocket.getOutputStream(), true);
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		
		// set up to read input from the keyboard
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		// initialize, then enter loop to read from and write to the chatter socket
		
		String incoming = fromServer.readLine();
		while(incoming != null) {
			System.out.println(incoming);
			System.out.print("Your turn> ");
			String myReply;
			myReply = keyboard.readLine();
			toServer.println(myReply);
			incoming = fromServer.readLine();
		}
		
		// close the socket when communication is complete
		
		serverSocket.close();
	}
	
}
