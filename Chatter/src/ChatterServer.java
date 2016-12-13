import java.io.*;
import java.net.*;

public class ChatterServer {
	
	final static int SERVER_PORT = 3333;
	
	public static void main(String [] args) throws Exception {
		
		// server sockets wait to hear from clients, so after the socket is created, it sits and waits until a client connects
		
		ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		System.err.println("Waiting for a client");
		
		// when a client tries to connect, save a reference to the client socket
		
		Socket clientSocket = serverSocket.accept();

		System.out.println("Connection requested from: " + clientSocket.getLocalAddress());

		// just like the client, set up input and output streams, but of course in reverse: output goes out to, and input comes in from, the -client-
		
		PrintStream toClient = new PrintStream(clientSocket.getOutputStream(), true);
		BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		// and set up to read keyboard input
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		// start communication, then enter a similar loop of communication.
		
		toClient.println("Whatcha want?");
		String incoming = fromClient.readLine();
		while(incoming != null) {
			System.out.println(incoming);
			System.out.print("Your turn> ");
			String myReply;
			myReply = keyboard.readLine();
			toClient.println(myReply);
			incoming = fromClient.readLine();
		}
		
		// close the socket when communication is complete
		
		serverSocket.close();
	}

}
