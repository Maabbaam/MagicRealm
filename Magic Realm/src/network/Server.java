package network;
import gui.MagicRealmGUI;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import models.ChatMessage;

/*
 * The server that can be run both as a console application or a GUI
 */
public class Server {
	// a unique ID for each connection
	private static int uniqueId;
	// an ArrayList to keep the list of the Client
	private ArrayList<ClientThread> al;
	// if I am in a GUI
	MagicRealmGUI view;
	int finished = 0;
	String scores[] = new String[6];
	ArrayList<String> users = new ArrayList<String>();
	int numUsers = 0;
	int numMoves = 0;
	int numCombatMoves = 0;
	HashMap<String, Integer> combatMoves = new HashMap<String, Integer>();

	// to display time
	private SimpleDateFormat sdf;
	// the port number to listen for connection
	private int port;
	// the boolean that will be turned of to stop the server
	private boolean keepGoing;


	/*
	 *  server constructor that receive the port to listen to for connection as parameter
	 *  in console
	 */
	public Server(int port) {
		this(port, null);
	}

	public Server(int port, MagicRealmGUI view) {
		// GUI or not
		this.view = view;
		// the port
		this.port = port;
		// to display hh:mm:ss
		sdf = new SimpleDateFormat("HH:mm:ss");
		// ArrayList for the Client list
		al = new ArrayList<ClientThread>();
	}

	public void start() {
		keepGoing = true;
		/* create socket server and wait for connection requests */
		try 
		{
			// the socket used by the server
			ServerSocket serverSocket = new ServerSocket(port);

			// infinite loop to wait for connections
			while(keepGoing) 
			{
				// format message saying we are waiting
				display("Server waiting for Clients on port " + port + ".");

				Socket socket = serverSocket.accept();  	// accept connection
				// if I was asked to stop
				if(!keepGoing)
					break;
				ClientThread t = new ClientThread(socket);  // make a thread of it
				al.add(t);									// save it in the ArrayList
				t.start();
			}
			// I was asked to stop
			try {
				serverSocket.close();
				for(int i = 0; i < al.size(); ++i) {
					ClientThread tc = al.get(i);
					try {
						tc.sInput.close();
						tc.sOutput.close();
						tc.socket.close();
					}
					catch(IOException ioE) {
						// not much I can do
					}
				}
			}
			catch(Exception e) {
				display("Exception closing the server and clients: " + e);
			}
		}
		// something went bad
		catch (IOException e) {
			String mview = " Exception on new ServerSocket: " + e + "\n";
			display(mview);
		}
	}		
	/*
	 * For the GUI to stop the server
	 */
	protected void stop() {
		keepGoing = false;
		// connect to myself as Client to exit statement 
		// Socket socket = serverSocket.accept();
		try {
			new Socket("localhost", port);
		}
		catch(Exception e) {
			// nothing I can really do
		}
	}
	/*
	 * Display an event (not a message) to the console or the GUI
	 */
	private void display(String mview) {
		String time = sdf.format(new Date()) + " " + mview;
		if(view == null)
			System.out.println(time);
		else{
			//	view.appendEvent(time + "\n");
		}

	}
	/*
	 *  to broadcast a message to all Clients
	 */
	synchronized void broadcast(ChatMessage message) {

		// display message on console or GUI


		// we loop in reverse order in case we would have to remove a Client
		// because it has disconnected
		for(int i = al.size(); --i >= 0;) {
			ClientThread ct = al.get(i);
			// try to write to the Client if it fails remove it from the list
			if(!ct.writeMview(message)) {
				al.remove(i);
				display("Disconnected Client " + ct.username + " removed from list.");
			}
		}
	}


	// for a client who logoff using the LOGOUT message
	synchronized void remove(int id) {
		// scan the array list until we found the Id
		for(int i = 0; i < al.size(); ++i) {
			ClientThread ct = al.get(i);
			// found it
			if(ct.id == id) {
				al.remove(i);
				return;
			}
		}
	}

	/*
	 *  To run as a console application just open a console window and: 
	 * > java Server
	 * > java Server portNumber
	 * If the port number is not specified 28442 is used
	 */ 
	public static void main(String[] args) {
		// start server on port 28442 unless a PortNumber is specified 
		int portNumber = 28442;
		switch(args.length) {
		case 1:
			try {
				portNumber = Integer.parseInt(args[0]);
			}
			catch(Exception e) {
				System.out.println("Invalid port number.");
				System.out.println("Usage is: > java Server [portNumber]");
				return;
			}
		case 0:
			break;
		default:
			System.out.println("Usage is: > java Server [portNumber]");
			return;

		}
		// create a server object and start it
		Server server = new Server(portNumber);
		server.start();
	}

	/** One instance of this thread will run for each client */
	class ClientThread extends Thread {
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream sInput;
		ObjectOutputStream sOutput;
		// my unique id (easier for deconnection)
		int id;
		// the Username of the Client
		String username;
		// the only type of message a will receive
		ChatMessage cm;
		// the date I connect
		String date;

		// Constructore
		ClientThread(Socket socket) {
			// a unique id
			id = ++uniqueId;
			this.socket = socket;
			/* Creating both Data Stream */
			System.out.println("Thread trying to create Object Input/Output Streams");
			try
			{
				// create output first
				sOutput = new ObjectOutputStream(socket.getOutputStream());
				sInput  = new ObjectInputStream(socket.getInputStream());
				// read the username
				username = (String) sInput.readObject();
				display(username + " just connected.\n");
			}
			catch (IOException e) {
				display("Exception creating new Input/output Streams: " + e);
				return;
			}
			// have to catch ClassNotFoundException
			// but I read a String, I am sure it will work
			catch (ClassNotFoundException e) {
			}
			date = new Date().toString() + "\n";
		}

		// what will run forever
		public void run() {
			// to loop until LOGOUT
			boolean keepGoing = true;
			while(keepGoing) {
				// read a String (which is an object)
				try {
					cm = (ChatMessage) sInput.readObject();
				}
				catch (IOException e) {
					if (cm.getUserClass() != null && !cm.getUserClass().equals(""))
					{
						display(cm.getUserClass() + " Exception reading Streams: " + e);
						broadcast(new ChatMessage(ChatMessage.LOGOUT, cm.getUserClass() + ",logout", cm.getUserClass()));

						for(String user : users)
						{
							if (user.contains(cm.getUserClass()))
							{
								users.remove(user);
								numUsers--;
								break;
							}
						}
						
					}
					break;				
				}
				catch(ClassNotFoundException e2) {
					break;
				}
				catch(ClassCastException e2) {
					break;
				}
				// the message part of the ChatMessage


				// Switch on the type of message receive
				switch(cm.getType()) {
				case ChatMessage.LOGOUT:
					
					broadcast(new ChatMessage(ChatMessage.LOGOUT, cm.getUserClass() + ",logout", cm.getUserClass()));
					for(String user : users)
					{
						if (user.contains(cm.getUserClass()))
						{
							users.remove(user);
							numUsers--;
							break;
						}
					}
					break;
				case ChatMessage.WHOISIN:
					for (int i = 0; i < users.size(); i++) {
						broadcast(new ChatMessage(ChatMessage.COMBATMOVE, users.get(i) + ", is online\n", ""));
					}
					break;
				case ChatMessage.CHAT:
					broadcast(cm);
					break;
				case ChatMessage.INVENTORY:
					broadcast(cm);
					break;
				case ChatMessage.QUICKMOVE:
					broadcast(cm);
					break;
				case ChatMessage.MONSTERCOMBAT:
					broadcast(cm);
					break;
				case ChatMessage.NATIVECOMBAT:
					broadcast(cm);
					break;
				case ChatMessage.MOVE:
					broadcast(cm);
					numMoves++;
					if (numMoves == users.size())
					{
						broadcast(new ChatMessage(ChatMessage.COMBATMOVE,  "server,movesreceived", ""));
						numMoves = 0;
					}
					display(cm+"");
					break;

				case ChatMessage.COMBATMOVE:
					broadcast(cm);

					numCombatMoves++;
					if (numCombatMoves == users.size())
					{
						broadcast(new ChatMessage(ChatMessage.COMBATMOVE, "server,combatmovesreceived", ""));
						numCombatMoves = 0;
					}
					break;
				case ChatMessage.MESSAGE:
					broadcast(cm);
					break;
				case ChatMessage.SEARCH:
					broadcast(cm);
					break;
				case ChatMessage.TRADEACCEPT:
					broadcast(cm);
					break;
				case ChatMessage.TRADEOFFER:
					broadcast(cm);
					break;
				case ChatMessage.TRADESEARCH:
					broadcast(cm);
					break;
				case ChatMessage.DEATH:
					broadcast(cm);
					numUsers--;
					break;
				case ChatMessage.UPDATE:
					broadcast(cm);
					break;
				case ChatMessage.FAME:
					broadcast(cm);
					break;
				case ChatMessage.NOTORIETY:
					broadcast(cm);
					break;
				case ChatMessage.GOLD:
					broadcast(cm);
					break;
				case ChatMessage.SPELL:
					broadcast(cm);
					break;
				case ChatMessage.LOCATION:
					broadcast(cm);
					break;
				case ChatMessage.TREASURE:
					broadcast(cm);
					break;
				case ChatMessage.VICTORY:
					display(username + ", won\n");
					//broadcast(scores[0]);
					break;
				case ChatMessage.CONNECT:
					broadcast(new ChatMessage(ChatMessage.WHOISIN, cm.getMessage() + ", connected", ""));
					if (cm.getMessage().getClass().equals(String.class))
					{
						if(!(numUsers<0)){
							users.add((String)cm.getMessage());
							numUsers++;
						}else{
							numUsers=0;
							users.add((String)cm.getMessage());
							numUsers++;
						}
					}
					break;

				}
			}
			// remove myself from the arrayList containing the list of the
			// connected Clients
			remove(id);
			close();
		}

		// try to close everything
		private void close() {
			// try to close the connection
			try {
				if(sOutput != null) sOutput.close();
			}
			catch(Exception e) {}
			try {
				if(sInput != null) sInput.close();
			}
			catch(Exception e) {};
			try {
				if(socket != null) socket.close();
			}
			catch (Exception e) {}
		}

		/*
		 * Write a String to the Client output stream
		 */

		private boolean writeMview(ChatMessage mview) {
			// if Client is still connected send the message to it
			if(!socket.isConnected()) {
				close();
				return false;
			}
			// write the message to the stream
			try {
				sOutput.writeObject(mview);
			}
			// if an error occurs, do not abort just inform the user
			catch(IOException e) {
				//display("Error sending message to " + username);
				display(e.toString());
			}
			return true;
		}

	}
}
