package gui;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.io.*;

import javax.swing.JOptionPane;

import models.ChatMessage;
import models.Dice;
import models.MagicRealmVariables;

/*
 * The Client has a GUI... This is the main file to run if you are a client.
 */
public class Client  {

	// for I/O	
	String scores;
	// Variables needed to maintain game
	MagicRealmVariables model;
	// GUI
	MagicRealmGUI view;
	private ObjectInputStream sInput;		// to read from the socket
	private ObjectOutputStream sOutput;		// to write on the socket
	private Socket socket;

	public enum Phase {
		BIRDSONG, DAYLIGHT, SUNSET, MIDNIGHT
	}
	Phase phase = Phase.BIRDSONG;
	int day = 1;

	// the server, the port and the username
	private String server, username;
	private int port;
	/*
	 *  Constructor called by console mode
	 *  server: the server address
	 *  port: the port number
	 *  username: the username
	 */
	Client(String server, int port, String username) {
		// which calls the common constructor with the GUI set to null
		this(server, port, username, null, null);
	}
	public static String[] removeElements(String[] input, String deleteMe) {
		List<String> result = new LinkedList<String>();

		for(String item : input)
			if(!deleteMe.equals(item))
				result.add(item);

		return result.toArray(input);
	}
	/*
	 * Constructor call when used from a GUI
	 * in console mode the ClienGUI parameter is null
	 */
	Client(String server, int port, String username, MagicRealmGUI view, MagicRealmVariables model) {
		this.server = server;
		this.port = port;
		this.username = username;
		// save if we are in GUI mode or not
		this.view = view;
		this.model = model;
	}

	/*
	 * To start the dialog
	 */
	public boolean start() {
		// try to connect to the server
		try {
			socket = new Socket(server, port);
		} 
		// if it failed not much I can so
		catch(Exception ec) {
			display("Error connectiong to server:" + ec);
			return false;
		}

		String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
		display(msg);

		/* Creating both Data Stream */
		try
		{
			sInput  = new ObjectInputStream(socket.getInputStream());
			sOutput = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException eIO) {
			display("Exception creating new Input/output Streams: " + eIO);
			return false;
		}

		// creates the Thread to listen from the server 
		new ListenFromServer().start();
		// Send our username to the server this is the only message that we
		// will send as a String. All other messages will be ChatMessage objects
		try
		{
			sOutput.writeObject(username);
		}
		catch (IOException eIO) {
			display("Exception doing login : " + eIO);
			disconnect();
			return false;
		}
		// success we inform the caller that it worked
		return true;
	}

	/*
	 * To send a message to the console or the GUI
	 */
	private void display(String msg) {
		if(view == null)
			System.out.println(msg);      // println in console mode
		else{
			//view.append(msg + "\n");		// append to the ClientGUI JTextArea (or whatever)
		}
	}

	/*
	 * To send a message to the server
	 */
	void sendMessage(ChatMessage msg) {
		try {
			sOutput.writeObject(msg);
		}
		catch(IOException e) {
			display("Exception writing to server: " + e);
		}
	}

	/*
	 * When something goes wrong
	 * Close the Input/Output streams and disconnect not much to do in the catch clause
	 */
	private void disconnect() {
		try { 
			if(sInput != null) sInput.close();
		}
		catch(Exception e) {} // not much else I can do
		try {
			if(sOutput != null) sOutput.close();
		}
		catch(Exception e) {} // not much else I can do
		try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // not much else I can do

		// inform the GUI
		if(view != null){
			//view.connectionFailed();
		}


	}
	/*
	 * To start the Client in console mode use one of the following command
	 * > java Client
	 * > java Client username
	 * > java Client username portNumber
	 * > java Client username portNumber serverAddress
	 * at the console prompt
	 * If the portNumber is not specified 28442 is used
	 * If the serverAddress is not specified "localHost" is used
	 * If the username is not specified "Anonymous" is used
	 * > java Client 
	 * is equivalent to
	 * > java Client Anonymous 28442 localhost 
	 * are eqquivalent
	 * 
	 * In console mode, if an error occurs the program simply stops
	 * when a GUI id used, the GUI is informed of the disconnection
	 */
	public static void main(String[] args) {
		// default values	
		MagicRealmVariables model  = new MagicRealmVariables();
		MagicRealmGUI view  = new MagicRealmGUI(model);


		int portNumber = 28442;
		String serverAddress = "localhost";
		String userName = "Anonymous";

		// depending of the number of arguments provided we fall through
		switch(args.length) {
		// > javac Client username portNumber serverAddr
		case 3:
			serverAddress = args[2];
			// > javac Client username portNumber
		case 2:
			try {
				portNumber = Integer.parseInt(args[1]);
			}
			catch(Exception e) {
				System.out.println("Invalid port number.");
				System.out.println("Usage is: > java Client [username] [portNumber] [serverAddress]");
				return;
			}
			// > javac Client username
		case 1: 
			userName = args[0];
			// > java Client
		case 0:
			break;
			// invalid number of arguments
		default:
			System.out.println("Usage is: > java Client [player number] [portNumber] {serverAddress]");
			return;
		}
		// create the Client object
		Client client = new Client(serverAddress, portNumber, userName, view, model);
		// test if we can start the connection to the Server
		// if it failed nothing we can do
		if(!client.start())
			return;

		// wait for messages from user
		//Scanner scan = new Scanner(System.in);
		String msg = "";
		// loop forever for message from the user
		while(true) {
			// read message from user
			// logout if message is LOGOUT
			if(msg.equalsIgnoreCase("LOGOUT")) {
				client.sendMessage(new ChatMessage(ChatMessage.LOGOUT, model.getUserClassData().get(0) + ",disconnected\n", model.getUserClassData().get(0)));
				// break to do the disconnect
				break;
			}
			// message WhoIsIn
			else if(msg.equalsIgnoreCase("WHOISIN")) {
				client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, "", model.getUserClassData().get(0)));				
			}

			else {				// default to ordinary message

				if (model.start == 1)
				{
					model.day=1;
					model.phase=0;
					client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, "", ""));
					model.start = 0;
				}
				if (model.getPlay() == 1)
				{
					model.setPlay(0);
					/*CHECK IF YOU SELECTED THE WIZARD
					 	IF WIZARD WAS SELECTED, 
					 	PLACE ALL CAVE ENTRANCES AND SECRET PATH ENTRANCES IN CAVE ENTRANCES/SECRET PATHS FOUND
					 	THIS IS FOR WIZARDS SPECIAL ABILITY : EXPERIENCE 	*/
					if (model.userClassData.get(0).equals("Wizard"))
					{
						for (Integer[] caveEntrance : model.cavesEntrances)
							model.cavesFound.add(caveEntrance);
						for (Integer[] secretPathEntrances : model.secretPathsEntrances){
							Integer[] temp = new Integer[2];
							temp[0] = secretPathEntrances[0];
							temp[1] = secretPathEntrances[1];
							model.secretPathsFound.add(temp);
						}
					}
					// TO START THE GAME, FIRST CONNECT TO THE SERVER, 
					// THEN FIND OUT WHO IS IN THE GAME ALREADY.
					client.sendMessage(new ChatMessage(ChatMessage.CONNECT, view.selectedLoc.getText() +"," + model.getUserClassData().get(0), model.getUserClassData().get(0)));
					client.sendMessage(new ChatMessage(ChatMessage.WHOISIN, view.selectedLoc.getText() + "," + model.getUserClassData().get(0), model.getUserClassData().get(0)));

				}
				//IF USER IS TRYING TO SEND A CHAT MESSAGE
				if (model.getChat() == 1)
				{
					model.setChat(0);
					client.sendMessage(new ChatMessage(ChatMessage.CHAT, view.chatMessage + ", chat", model.getUserClassData().get(0)));

				}
				//IF USER DIES
				if (model.dead == 1)
				{
					model.dead = 0;
					client.sendMessage(new ChatMessage(ChatMessage.DEATH, model.userClassData.get(0) + ", died", model.userClassData.get(0)));

				}
				if (model.moveActiveToInactive == 1)
				{
					model.moveActiveToInactive = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.inventoryList.getSelectedValue() + "," + model.userClassData.get(0) +",moveActiveToInactive", model.userClassData.get(0)));

				}
				if (model.moveInactiveToActive == 1)
				{
					model.moveInactiveToActive = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.inactiveInventoryList.getSelectedValue() + "," + model.userClassData.get(0) +",moveInactiveToActive", model.userClassData.get(0)));

				}
				if (model.addActive == 1)
				{
					model.addActive = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",addActive", model.userClassData.get(0)));

				}

				if (model.takeTWT == 1)
				{
					model.takeTWT = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + view.activeTWT +",takeTWT", model.userClassData.get(0)));

				}
				if (model.addInactive == 1)
				{
					model.addInactive = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",addInactive", model.userClassData.get(0)));

				}
				if (model.removeActive == 1)
				{
					model.removeActive = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",removeActive", model.userClassData.get(0)));

				}
				if (model.removeInactive == 1)
				{
					model.removeInactive = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",removeInactive", model.userClassData.get(0)));

				}
				if (model.itemDestroyed == 1)
				{
					model.itemDestroyed = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",itemdestroyed", model.userClassData.get(0)));

				}
				if (model.addGold == 1)
				{
					model.addGold = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",gold", model.userClassData.get(0)));

				}
				if (model.addFatigued == 1)
				{
					model.addFatigued = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",addFatigued", model.userClassData.get(0)));

				}
				if (model.removeFatigued == 1)
				{
					model.removeFatigued = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",removeFatigued", model.userClassData.get(0)));

				}
				if (model.addWounded == 1)
				{
					model.addWounded = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",addWounded", model.userClassData.get(0)));

				}
				if (model.removeWounded == 1)
				{
					model.removeWounded = 0;
					client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, view.activeItem + "," + model.userClassData.get(0) +",removeWounded", model.userClassData.get(0)));

				}

				//IF USER MOVES
				if (model.move == 1)
				{

					Integer[] playerLoc = model.playerLocationsData.get(model.getUserClassData().get(0));
					for(String str: model.soundChitNeighborhood.keySet()){
						for(Integer[] loc: model.soundChitNeighborhood.get(str)){
							if(playerLoc[0].equals(loc[0]) && playerLoc[1].equals(loc[1])){
								int u = model.soundChits.indexOf(str);
								view.soundChit.setText(model.soundChits.get(u));
								break;
							}
						}
					}
					boolean caveFound = false;
					for (Integer[] caveLoc : model.caves)
						if (caveLoc[0].equals(playerLoc[0]) && caveLoc[1].equals(playerLoc[1]))
							caveFound = true;
					if (caveFound == true)
						view.inCave = true;
					else
						view.inCave = false;
					model.move=0;
					client.sendMessage(new ChatMessage(ChatMessage.QUICKMOVE, model.getPlayerLocationsData().get(model.getUserClassData().get(0))[0]
							+ " " + model.getPlayerLocationsData().get(model.getUserClassData().get(0))[1] 
									+","+model.getUserClassData().get(0) + ",quickmove", model.getUserClassData().get(0)));

					//the mingle button is used to buy drinks for natives
					//and see which native is following you.
					boolean foundNPC = false;
					for(String natives : model.nativeLocation.keySet())
					{
						if (!model.nativesFollowing.contains(natives))
						{
							if (model.nativeLocation.get(natives) != null
									&& model.nativeLocation.get(natives)[0].equals(model.getPlayerLocationsData().get(model.userClassData.get(0))[0])
									&& model.nativeLocation.get(natives)[1].equals(model.getPlayerLocationsData().get(model.userClassData.get(0))[1]))
							{
								view.mingle.setEnabled(true);
								foundNPC = true;
							}
						}
					}
					if (!foundNPC)
						view.mingle.setEnabled(false);

					//adds site chit treasures to loot screen
					for(String s : model.siteChitLocation.keySet())
					{
						boolean found = false;
						for(Integer[] loc: model.siteChitNeighborhood.get(s)){
							if ((loc[0].equals(model.getPlayerLocationsData().get(model.getUserClassData().get(0))[0]))
									&&(loc[1].equals(model.getPlayerLocationsData().get(model.getUserClassData().get(0))[1]))
									&& (model.playerSitesFound.get(model.getUserClassData().get(0)).get(model.siteChits.indexOf(s))) == 1 )
							{
								view.siteChit.setText(s);
								found = true;
								break;
							}
							else{
								view.siteChit.setText("");
							}
						}
						if(found){
							break;
						}
					}
				}
				//IF USER LOOTS
				if (model.getSearch() == 1)
				{
					model.setSearch(0);
					view.message=view.message + ",peer";
					client.sendMessage(new ChatMessage(ChatMessage.SEARCH, view.message + "," + model.getUserClassData().get(0), model.getUserClassData().get(0)));

				}
				//IF USER SELECTS A USER TO TRADE WITH
				if (model.tradeSearch == 1)
				{
					model.tradeSearch = 0;
					client.sendMessage(new ChatMessage(ChatMessage.TRADESEARCH, view.partner + ",tradesearch," + model.getUserClassData().get(0), model.getUserClassData().get(0)));

				}	
				//IF USER ACCEPTS A TRADE
				if (model.tradeAccept == 1)
				{
					model.tradeAccept = 0;
					client.sendMessage(new ChatMessage(ChatMessage.TRADEACCEPT, view.targetPlayer+","+view.acceptedItem+","+view.offeredItem+",tradeaccept," + model.getUserClassData().get(0), model.getUserClassData().get(0)));

				}
				//IF A USER MAKES A TRADE OFFER
				if (model.tradeOffer == 1)
				{
					model.tradeOffer = 0;
					client.sendMessage(new ChatMessage(ChatMessage.TRADEOFFER, view.targetPlayer +","+view.tradeOffer+",tradeOffer," + model.getUserClassData().get(0), model.getUserClassData().get(0)));

				}
				//IF USER DISCOVERS A LOCATION
				if (model.locationEntered == 1)
				{
					model.locationEntered = 0;
					client.sendMessage(new ChatMessage(ChatMessage.LOCATION, model.getUserClassData().get(0)+",location", model.getUserClassData().get(0)));

				}
				//IF USER TRADES WITH A NATIVE
				if (model.nativeTrade == 1)
				{
					model.nativeTrade = 0;
					client.sendMessage(new ChatMessage(ChatMessage.TRADEACCEPT, model.getUserClassData().get(0)+","+view.offeredItem+","+view.acceptedItem+",tradeaccept," + view.partner, view.partner));

				}
				//IF USER ACCEPTS THEIR ACTIONS FOR THE PHASE
				if (model.getWaitingMove() == 1)
				{
					model.setWaitingMove(0);
					msg = "";
					//ADDING ALL THE ACTIONS USER ENTERED TO A MESSAGE.
					for (int i = 0; i<view.actionModel.getSize(); i++)
					{
						msg = msg + view.actionModel.getElementAt(i) + ",";
					}
					//FOR GRIPPING DUST TREASURE
					if (view.inventoryModel.contains("Gripping Dust"))
						msg = msg + "Alert,";
					msg = msg+ model.getUserClassData().get(0) + ",actions";
					//SENDING ACTIONS FOR THIS PHASE TO THE SERVER
					client.sendMessage(new ChatMessage(ChatMessage.MOVE, msg, model.getUserClassData().get(0)));

					view.actionModel.removeAllElements();
					view.numActions = 0;

				}
				//IF USER FINISHES COMBAT SETUP
				if (model.getWaitingCombatMove() == 1)
				{
					//SENDING A MESSAGE DESCRIBING YOUR ATTACK TO THE SERVER
					String targetPlayer = "";
					if (view.combatPlayerList.getSelectedValue() == null || view.running == true)
					{
						targetPlayer = "No One";
						view.running = false;
					}
					else
						targetPlayer = view.combatPlayerList.getSelectedValue();

					if (model.nativesFollowing.size() > 0)
					{
						//CREATE A LIST OF Natives THAT MAY POTENTIALLY ATTACK YOU
						HashMap<String, Integer[]> attackingNativeStats = new HashMap<String, Integer[]>();
						for (String natives : model.nativesFollowing)
						{
							//FIND NATIVE DATA AND FIX THE REST OF THIS FUNCTION
							attackingNativeStats.put(natives, model.nativeData.get(natives));
						}	
						//GIVE THE Natives IN YOUR CLEARING RANDOM ATTACK DATA.
						for(String s : attackingNativeStats.keySet())
						{
							String [] value = new String[8];
							Random rand = new Random();
							// nextInt is normally exclusive of the top value,
							// so add 1 to make it inclusive
							int randomNum = rand.nextInt((3 - 1) + 1) + 1;
							if (model.cheatMode)
							{
								Object[] attackDirSheet = {"Swing", "Smash", "Thrust"};
								String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
										"Select the direction for the native to attack in",
										"Native Attack Direction",
										JOptionPane.INFORMATION_MESSAGE,
										null,
										attackDirSheet,
										"Swing");
								if(temp!=null && temp.equals("Swing")){
									randomNum = 1;
								}else if(temp!=null && temp.equals("Smash")){
									randomNum = 2;
								}else{
									randomNum = 3;
								}
							}
							switch (randomNum) {
							case 1:
								value[0] = "Swing";
								break;
							case 2:
								value[0] = "Smash";
								break;
							case 3:
								value[0] = "Thrust";
								break;
							}
							rand = new Random();
							randomNum = rand.nextInt((3 - 1) + 1) + 1;
							if (model.cheatMode)
							{
								Object[] moveDirSheet = {"Duck", "Dodge", "Charge"};
								String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
										"Select the direction for the native to move in",
										"Native Move Direction",
										JOptionPane.INFORMATION_MESSAGE,
										null,
										moveDirSheet,
										"Duck");
								if(temp!=null && temp.equals("Duck")){
									randomNum = 1;
								}else if(temp!=null && temp.equals("Dodge")){
									randomNum = 2;
								}else{
									randomNum = 3;
								}
							}
							switch (randomNum) {
							case 1:
								value[1] = "Duck";
								break;
							case 2:
								value[1] = "Dodge";
								break;
							case 3:
								value[1] = "Charge";
								break;
							}

							switch(attackingNativeStats.get(s)[0]){
							case 0:
								value[2] = "FIGHTN"+attackingNativeStats.get(s)[4];
								break;
							case 1:
								value[2] = "FIGHTL"+attackingNativeStats.get(s)[4];
								break;
							case 2:
								value[2] = "FIGHTM"+attackingNativeStats.get(s)[4];
								break;
							case 3:
								value[2] = "FIGHTL"+attackingNativeStats.get(s)[4];
								break;
							case 4:
								value[2] = "FIGHTT"+attackingNativeStats.get(s)[4];
								break;
							case 5:
								value[2] = "FIGHTS"+attackingNativeStats.get(s)[4];
								break;
							default:
								break;
							}

							switch(attackingNativeStats.get(s)[5]){
							case 0:
								value[3] = "MOVEN"+attackingNativeStats.get(s)[5];
								break;
							case 1:
								value[3] = "MOVEL"+attackingNativeStats.get(s)[5];
								break;
							case 2:
								value[3] = "MOVEM"+attackingNativeStats.get(s)[5];
								break;
							case 3:
								value[3] = "MOVEH"+attackingNativeStats.get(s)[5];
								break;
							case 4:
								value[3] = "MOVET"+attackingNativeStats.get(s)[5];
								break;
							case 5:
								value[3] = "MOVES"+attackingNativeStats.get(s)[5];
								break;
							default:
								break;
							}

							value[4] = "Native";
							//Use the same target the player is attacking.
							value[5] = targetPlayer;
							value[6] = s;
							value[7] = "combatActions";
							client.sendMessage(new ChatMessage(ChatMessage.NATIVECOMBAT, value[0]+ "," +value[1]+ "," +value[2]+ "," +value[3]+ "," +value[4]+ "," +value[5]+ "," +value[6]+ "," +value[7], model.userClassData.get(0)));
						}

					}

					//CREATE A LIST OF MONSTERS THAT MAY ATTACK
					HashMap<String, Integer[]> attackingMonsterStats = new HashMap<String, Integer[]>();
					for (String monster : model.generatedMonsters)
					{
						attackingMonsterStats.put(monster,model.monsterData.get(monster));

					}  	

					if (!attackingMonsterStats.isEmpty())
					{
						//GIVE THE MONSTERS IN YOUR CLEARING RANDOM ATTACK DATA.
						for(String monster : model.generatedMonsters)
						{
							if (!monster.equals("") && monster!=null && model.monsterData.get(monster)!=null)
							{
								String [] value = new String[8];
								Random rand = new Random();
								// nextInt is normally exclusive of the top value,
								// so add 1 to make it inclusive
								int randomNum = rand.nextInt((3 - 1) + 1) + 1;
								if (model.cheatMode)
								{
									Object[] attackDirSheet = {"Swing", "Smash", "Thrust"};
									String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
											"Select the direction for the monster to attack in",
											"Monster Attack Direction",
											JOptionPane.INFORMATION_MESSAGE,
											null,
											attackDirSheet,
											"Swing");
									if(temp!=null && temp.equals("Swing")){
										randomNum = 1;
									}else if(temp!=null && temp.equals("Smash")){
										randomNum = 2;
									}else{
										randomNum = 3;
									}
								}
								switch (randomNum) {
								case 1:
									value[0] = "Swing";
									break;
								case 2:
									value[0] = "Smash";
									break;
								case 3:
									value[0] = "Thrust";
									break;
								}
								rand = new Random();
								randomNum = rand.nextInt((3 - 1) + 1) + 1;
								if (model.cheatMode)
								{
									Object[] moveDirSheet = {"Duck", "Dodge", "Charge"};
									String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
											"Select the direction for the monster to move in",
											"Monster Move Direction",
											JOptionPane.INFORMATION_MESSAGE,
											null,
											moveDirSheet,
											"Duck");
									if(temp!=null && temp.equals("Duck")){
										randomNum = 1;
									}else if(temp!=null && temp.equals("Dodge")){
										randomNum = 2;
									}else{
										randomNum = 3;
									}
								}
								switch (randomNum) {
								case 1:
									value[1] = "Duck";
									break;
								case 2:
									value[1] = "Dodge";
									break;
								case 3:
									value[1] = "Charge";
									break;
								}
								if (model.monsterData.get(monster)[4].equals(0))
									value[2] = "FIGHTN"+model.monsterData.get(monster)[7];
								if (model.monsterData.get(monster)[4].equals(1))
									value[2] = "FIGHTL"+model.monsterData.get(monster)[7];
								if (model.monsterData.get(monster)[4].equals(2))
									value[2] = "FIGHTM"+model.monsterData.get(monster)[7];
								if (model.monsterData.get(monster)[4].equals(3))
									value[2] = "FIGHTH"+model.monsterData.get(monster)[7];
								if (model.monsterData.get(monster)[4].equals(4))
									value[2] = "FIGHTT"+model.monsterData.get(monster)[7];
								if (model.monsterData.get(monster)[4].equals(5))
									value[2] = "FIGHTS"+model.monsterData.get(monster)[7];
								if (model.monsterData.get(monster)[0].equals(0))
									value[3] = "MOVEN"+model.monsterData.get(monster)[5];
								if (model.monsterData.get(monster)[0].equals(1))
									value[3] = "MOVEL"+model.monsterData.get(monster)[5];
								if (model.monsterData.get(monster)[0].equals(2))
									value[3] = "MOVEM"+model.monsterData.get(monster)[5];
								if (model.monsterData.get(monster)[0].equals(3))
									value[3] = "MOVEH"+model.monsterData.get(monster)[5];
								if (model.monsterData.get(monster)[0].equals(4))
									value[3] = "MOVET"+model.monsterData.get(monster)[5];
								if (model.monsterData.get(monster)[0].equals(5))
									value[3] = "MOVES"+model.monsterData.get(monster)[5];
								value[4] = "Monster";
								rand = new Random();
								//PICK A USER AT RANDOM FROM THOSE IN THE SAME CLEARING AS THE MONSTER.

								if (model.userStatusData.get(model.userClassData.get(0)).equals("Hidden"))
									value[5] = "No One";
								else 
								{
									if (model.cheatMode)
									{
										Object[] attackDirSheet = {"No", "Yes"};
										String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
												"Should the monster attack the "+ model.userClassData.get(0)+"?",
												"Monster Attack",
												JOptionPane.INFORMATION_MESSAGE,
												null,
												attackDirSheet,
												"Duck");
										if(temp!=null && temp.equals("Yes")){
											value[5] = model.userClassData.get(0);
										}else{
											value[5] = "No One";
										}
									}else{
										value[5] = model.userClassData.get(0);
									}
								}
								value[6] = monster;
								value[7] = "combatActions";
								client.sendMessage(new ChatMessage(ChatMessage.MONSTERCOMBAT, value[0]+ "," +value[1]+ "," +value[2]+ "," +value[3]+ "," +value[4]+ "," +value[5]+ "," +value[6]+ "," +value[7], model.userClassData.get(0)));
							}
						}
					}

					if (view.activeWeapon == null)
						view.activeWeapon = "Dagger";
					if (view.attackType == null)
						view.attackType = "null";
					if (view.maneuverType == null)
						view.maneuverType = "null";
					if (view.chitLabel.getText().equals("") || view.chitLabel.getText()  == null)
						view.chitLabel.setText("FIGHTZ9");
					if (view.chitLabel2.getText().equals("") || view.chitLabel2.getText()  == null)
						view.chitLabel2.setText("MOVEZ9");

					//ITEM SPECIAL ABILITY: OIL OF POISON
					if (view.inventoryModel.contains("Oil of Poison"))
					{
						if (view != null 
								&& view.sideframe.isVisible()
								&& view.inventoryModel.contains("Oil of Poison"))
						{
							if (!view.chitLabel.getText().contains("**")
									&& view.chitLabel.getText().contains("*"))
							{
								view.chitLabel.setText(view.chitLabel.getText() + "*");
								view.updateText("You Imbue Your Weapon With Oil of Poison");
							}
							else if (!view.chitLabel.getText().contains("**"))
							{
								view.chitLabel.setText(view.chitLabel.getText() + "*");
								view.updateText("You Imbue Your Weapon With Oil of Poison");
							}
						}

					}
					//ITEM SPECIAL ABILITY: BELT OF STRENGTH
					if(view.inventoryModel.contains("Belt of Strength")){
						if (view != null && view.sideframe.isVisible())	
							view.updateText("The Belt of Strength Enchants You");
						if(view.chitLabel.getText().contains("**")){
							String newChitLabel;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel = view.chitLabel.getText().substring(0,5)+'T'+view.chitLabel.getText().substring(6);
								view.chitLabel.setText(newChitLabel);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel = view.chitLabel2.getText().substring(0,4)+'T'+view.chitLabel2.getText().substring(5);
								view.chitLabel2.setText(newChitLabel);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel = view.chitLabel.getText().substring(0,6)+'T'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel = view.chitLabel2.getText().substring(0,4)+'T'+view.chitLabel2.getText().substring(5);
								view.chitLabel2.setText(newChitLabel);
								break;
							}
						}else if(view.chitLabel.getText().contains("*")){
							String newChitLabel2;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel2 = view.chitLabel.getText().substring(0,5)+'H'+view.chitLabel.getText().substring(6);
								view.chitLabel.setText(newChitLabel2);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel2 = view.chitLabel2.getText().substring(0,4)+'H'+view.chitLabel2.getText().substring(5);
								view.chitLabel2.setText(newChitLabel2);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel2 = view.chitLabel.getText().substring(0,6)+'H'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel2);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel2 = view.chitLabel2.getText().substring(0,4)+'H'+view.chitLabel2.getText().substring(5);
								view.chitLabel2.setText(newChitLabel2);
								break;
							}
						}else{
							String newChitLabel3;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel3 = view.chitLabel.getText().substring(0,5)+'M'+view.chitLabel.getText().substring(6);
								view.chitLabel.setText(newChitLabel3);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel3 = view.chitLabel2.getText().substring(0,4)+'M'+view.chitLabel2.getText().substring(5);
								view.chitLabel2.setText(newChitLabel3);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel3 = view.chitLabel.getText().substring(0,6)+'M'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel3);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel3 = view.chitLabel2.getText().substring(0,4)+'M'+view.chitLabel2.getText().substring(5);
								view.chitLabel2.setText(newChitLabel3);
								break;
							}
						}
					}

					//ITEM SPECIAL ABILITY: GARB OF SPEED
					if(view.inventoryModel.contains("Garb of Speed")){
						if (view != null && view.sideframe.isVisible())	
							view.updateText("The Garb of Speed enchants you");
						if(view.chitLabel.getText().contains("**")){
							String newChitLabel;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel = view.chitLabel.getText().substring(0,6)+'3'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel = view.chitLabel2.getText().substring(0,5)+'3'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel = view.chitLabel.getText().substring(0,7)+'3'+view.chitLabel.getText().substring(8);
								view.chitLabel.setText(newChitLabel);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel = view.chitLabel2.getText().substring(0,5)+'3'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel);
								break;
							}
						}else if(view.chitLabel.getText().contains("*")){
							String newChitLabel2;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel2 = view.chitLabel.getText().substring(0,6)+'4'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel2);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel2 = view.chitLabel2.getText().substring(0,5)+'4'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel2);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel2 = view.chitLabel.getText().substring(0,7)+'4'+view.chitLabel.getText().substring(8);
								view.chitLabel.setText(newChitLabel2);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel2 = view.chitLabel2.getText().substring(0,5)+'4'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel2);
								break;
							}
						}else{
							String newChitLabel3;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel3 = view.chitLabel.getText().substring(0,6)+'5'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel3);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel3 = view.chitLabel2.getText().substring(0,5)+'5'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel3);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel3 = view.chitLabel.getText().substring(0,7)+'5'+view.chitLabel.getText().substring(8);
								view.chitLabel.setText(newChitLabel3);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel3 = view.chitLabel2.getText().substring(0,5)+'5'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel3);
								break;
							}
						}
					}
					else if(view.inventoryModel.contains("Draught of Speed")){
						if (view != null && view.sideframe.isVisible())
							view.updateText("The Draught of Speed Has Been Consumed");
						client.sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Draught of Speed"
								+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

						if(view.chitLabel.getText().contains("**")){
							String newChitLabel;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel = view.chitLabel.getText().substring(0,6)+'2'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel = view.chitLabel2.getText().substring(0,5)+'2'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel = view.chitLabel.getText().substring(0,7)+'2'+view.chitLabel.getText().substring(8);
								view.chitLabel.setText(newChitLabel);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel = view.chitLabel2.getText().substring(0,5)+'2'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel);
								break;
							}
						}else if(view.chitLabel.getText().contains("*")){
							String newChitLabel2;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel2 = view.chitLabel.getText().substring(0,6)+'3'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel2);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel2 = view.chitLabel2.getText().substring(0,5)+'3'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel2);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel2 = view.chitLabel.getText().substring(0,7)+'3'+view.chitLabel.getText().substring(8);
								view.chitLabel.setText(newChitLabel2);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel2 = view.chitLabel2.getText().substring(0,5)+'3'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel2);
								break;
							}
						}else{
							String newChitLabel3;
							if(view.chitLabel.getText().contains("FIGHT")){
								newChitLabel3 = view.chitLabel.getText().substring(0,6)+'4'+view.chitLabel.getText().substring(7);
								view.chitLabel.setText(newChitLabel3);
								break;
							}else if(view.chitLabel2.getText().contains("MOVE")){
								newChitLabel3 = view.chitLabel2.getText().substring(0,5)+'4'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel3);
								break;
							}else if(view.chitLabel.getText().contains("BESERK")){
								newChitLabel3 = view.chitLabel.getText().substring(0,7)+'4'+view.chitLabel.getText().substring(8);
								view.chitLabel.setText(newChitLabel3);
								break;
							}else if(view.chitLabel2.getText().contains("DUCK")){
								newChitLabel3 = view.chitLabel2.getText().substring(0,5)+'4'+view.chitLabel2.getText().substring(6);
								view.chitLabel2.setText(newChitLabel3);
								break;
							}
						}
					}
					if (view.inventoryModel.contains("Penetrating Grease"))
						view.updateText("You Imbue Your Weapon With Penetrating Grease");

					if (view.inventoryModel.contains("Ointment of Bite"))
						view.updateText("You Imbue Your Weapon With Ointment of Bite");
					msg = view.attackType + ","+ view.maneuverType+ ","+ view.chitLabel.getText() + ","+ 
							view.chitLabel2.getText() + ","+ view.activeWeapon + "," + targetPlayer + ","+ model.getUserClassData().get(0) + ",combatActions";
					client.sendMessage(new ChatMessage(ChatMessage.COMBATMOVE, msg, model.getUserClassData().get(0)));
					model.setWaitingCombatMove(0);
				}

			}

		}
		// done disconnect
		client.disconnect();
	}

	/*
	 * a class that waits for the message from the server and append them to the JTextArea
	 * if we have a GUI or simply use System.out.println() if in console mode
	 */
	class ListenFromServer extends Thread {

		public void run() {
			while(true) {
				try {
					ChatMessage msg1 = (ChatMessage) sInput.readObject();
					//If you want console to print every message client receives from server, uncomment next line.
					String msg = "";
					if (msg1.getMessage().getClass().equals(String.class))
					{
						msg = (String)msg1.getMessage();
						System.out.println(msg);

					}
					else if (msg1.getMessage().getClass().equals(MagicRealmVariables.class) 
							&& model.userClassData.size() > 1 && !model.updatedPlayers.contains(model.userClassData.get(0)))
					{
						//					System.out.println(model.getUserClassData().get(0)+" Updating variables...");
						//COPYING ELEMENTS FROM THE MODEL RECEIVED FROM UPDATED USERS.

						Integer[] loc = model.playerLocationsData.get(model.getUserClassData().get(0));
						((MagicRealmVariables)msg1.getMessage()).playerLocationsData.put(model.getUserClassData().get(0),loc);

						model.phase = ((MagicRealmVariables)msg1.getMessage()).phase;
						model.event.setText(((MagicRealmVariables)msg1.getMessage()).event.getText());
						model.day = ((MagicRealmVariables)msg1.getMessage()).day;


						model.playerLocationsData.clear();
						if(!model.playerLocationsData.keySet().equals(((MagicRealmVariables)msg1.getMessage()).playerLocationsData.keySet())){
							for (String string : ((MagicRealmVariables)msg1.getMessage()).playerLocationsData.keySet()) {

								model.playerLocationsData.put(string, ((MagicRealmVariables)msg1.getMessage()).playerLocationsData.get(string));
							}
						}

						model.userAlertData.clear();
						if(!model.userAlertData.keySet().equals(((MagicRealmVariables)msg1.getMessage()).userAlertData.keySet())){
							for (String string : ((MagicRealmVariables)msg1.getMessage()).userAlertData.keySet()) {

								model.userAlertData.put(string, ((MagicRealmVariables)msg1.getMessage()).userAlertData.get(string));
							}
						}
						model.userStatusData.clear();
						if(!model.userStatusData.keySet().equals(((MagicRealmVariables)msg1.getMessage()).userStatusData.keySet())){
							for (String string : ((MagicRealmVariables)msg1.getMessage()).userStatusData.keySet()) {

								model.userStatusData.put(string, ((MagicRealmVariables)msg1.getMessage()).userStatusData.get(string));
							}
						}


						model.userVictoryPoints.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).userVictoryPoints.keySet()) {

							model.userVictoryPoints.put(string, ((MagicRealmVariables)msg1.getMessage()).userVictoryPoints.get(string));
						}


						model.userClassVulnerability.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).userClassVulnerability.keySet()) {
							model.userClassVulnerability.put(string, ((MagicRealmVariables)msg1.getMessage()).userClassVulnerability.get(string));
						}


						model.inventoryData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).inventoryData.keySet()) {

							model.inventoryData.put(string, ((MagicRealmVariables)msg1.getMessage()).inventoryData.get(string));
						}

						model.inactiveInventoryData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).inactiveInventoryData.keySet()) {

							model.inactiveInventoryData.put(string, ((MagicRealmVariables)msg1.getMessage()).inactiveInventoryData.get(string));
						}

						model.woundedInventoryData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).woundedInventoryData.keySet()) {
							model.woundedInventoryData.put(string, ((MagicRealmVariables)msg1.getMessage()).woundedInventoryData.get(string));
						}

						model.fatiguedInventoryData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).fatiguedInventoryData.keySet()) {
							model.fatiguedInventoryData.put(string, ((MagicRealmVariables)msg1.getMessage()).fatiguedInventoryData.get(string));
						}

						model.playerMoves.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).playerMoves.keySet()) {
							model.playerMoves.put(string, ((MagicRealmVariables)msg1.getMessage()).playerMoves.get(string));
						}
						model.followers.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).followers.keySet()) {
							model.followers.put(string, ((MagicRealmVariables)msg1.getMessage()).followers.get(string));
						}

						model.dwellingLocationsData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).dwellingLocationsData.keySet()) {

							model.dwellingLocationsData.put(string, ((MagicRealmVariables)msg1.getMessage()).dwellingLocationsData.get(string));
						}

						model.adjacentClearings.clear();
						for (Integer[] string : ((MagicRealmVariables)msg1.getMessage()).adjacentClearings.keySet()) {

							model.adjacentClearings.put(string, ((MagicRealmVariables)msg1.getMessage()).adjacentClearings.get(string));
						}

						model.userCombatActions.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).userCombatActions.keySet()) {

							model.userCombatActions.put(string, ((MagicRealmVariables)msg1.getMessage()).userCombatActions.get(string));
						}

						model.weaponData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).weaponData.keySet()) {

							model.weaponData.put(string, ((MagicRealmVariables)msg1.getMessage()).weaponData.get(string));
						}

						model.armourData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).armourData.keySet()) {

							model.armourData.put(string, ((MagicRealmVariables)msg1.getMessage()).armourData.get(string));
						}

						model.monsterData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).monsterData.keySet()) {

							model.monsterData.put(string, ((MagicRealmVariables)msg1.getMessage()).monsterData.get(string));
						}

						model.startingLocations.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).startingLocations.keySet()) {

							model.startingLocations.put(string, ((MagicRealmVariables)msg1.getMessage()).startingLocations.get(string));
						}

						model.siteChitLocation.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).siteChitLocation.keySet()) {

							model.siteChitLocation.put(string, ((MagicRealmVariables)msg1.getMessage()).siteChitLocation.get(string));
						}
						model.soundChitLocation.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).soundChitLocation.keySet()) {

							model.soundChitLocation.put(string, ((MagicRealmVariables)msg1.getMessage()).soundChitLocation.get(string));
						}

						model.siteChitData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).siteChitData.keySet()) {

							model.siteChitData.put(string, ((MagicRealmVariables)msg1.getMessage()).siteChitData.get(string));
						}


						model.soundChitData.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).soundChitData.keySet()) {

							model.soundChitData.put(string, ((MagicRealmVariables)msg1.getMessage()).soundChitData.get(string));
						}

						model.siteChitLocation.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).siteChitLocation.keySet()) {

							model.siteChitLocation.put(string, ((MagicRealmVariables)msg1.getMessage()).siteChitLocation.get(string));
						}

						model.soundChitLocation.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).soundChitLocation.keySet()) {

							model.soundChitLocation.put(string, ((MagicRealmVariables)msg1.getMessage()).soundChitLocation.get(string));
						}

						model.siteChitNeighborhood.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).siteChitNeighborhood.keySet()) {

							model.siteChitNeighborhood.put(string, ((MagicRealmVariables)msg1.getMessage()).siteChitNeighborhood.get(string));
						}
						model.soundChitNeighborhood.clear();
						for (String string : ((MagicRealmVariables)msg1.getMessage()).soundChitNeighborhood.keySet()) {

							model.soundChitNeighborhood.put(string, ((MagicRealmVariables)msg1.getMessage()).soundChitNeighborhood.get(string));
						}

						model.rSiteChitLocation.clear();
						for (Integer[] string : ((MagicRealmVariables)msg1.getMessage()).rSiteChitLocation.keySet()) {

							model.rSiteChitLocation.put(string, ((MagicRealmVariables)msg1.getMessage()).rSiteChitLocation.get(string));
						}

						model.siteChits.clear();
						for (int i = 0; i<((MagicRealmVariables)msg1.getMessage()).siteChits.size(); i++) {
							model.siteChits.add(i, ((MagicRealmVariables)msg1.getMessage()).siteChits.get(i));
						}

						model.soundChits.clear();
						for (int i = 0; i<((MagicRealmVariables)msg1.getMessage()).soundChits.size(); i++) {
							model.soundChits.add(i, ((MagicRealmVariables)msg1.getMessage()).soundChits.get(i));
						}

						model.clearingInventoryData.clear();
						for (Integer[] string : ((MagicRealmVariables)msg1.getMessage()).clearingInventoryData.keySet()) {
							model.clearingInventoryData.put(string, ((MagicRealmVariables)msg1.getMessage()).clearingInventoryData.get(string));
						}

						model.updatedPlayers.clear();
						for (int i = 0; i<((MagicRealmVariables)msg1.getMessage()).updatedPlayers.size(); i++) {
							model.updatedPlayers.add(i, ((MagicRealmVariables)msg1.getMessage()).updatedPlayers.get(i));
						}
						model.updatedPlayers.add(model.userClassData.get(0));

					}
					String phrase = msg;
					String delims = "[,]+";
					String[] tokens = phrase.split(delims);

					for (int i = 0; i<tokens.length-1; i++)
					{
						if (view != null ){
							//A new player has connected.
							if (tokens[i+1].contains("is online"))
							{	
								if (!view.userModel.contains(tokens[i])){
									view.userModel.addElement(tokens[i]);
								}
								//Update the model, place the new player in the dwelling they selected.
								if (!model.getUserClassData().contains(tokens[i]) && tokens[i] != "")
								{

									model.getUserClassData().add(tokens[i]);
									model.playerLocationsData.put(tokens[i], model.dwellingLocationsData.get(tokens[i-1]));
									//view.userModel.addElement(tokens[i]);
									if (view.selectedChar.getText()!=null
											&& view.selectedChar.getText() != ""
											&& model.userVictoryConditions.keySet().contains(model.getUserClassData().get(0)))
									{
										//								System.out.println(model.getUserClassData().get(0)+" sending message");
										sendMessage(new ChatMessage(ChatMessage.UPDATE, model, model.getUserClassData().get(0)));
									}

									if (view.mainframe.isVisible())
									{
										view.updateMap(model);
										//Display a message in clients GUI; 
										if (view != null && view.sideframe.isVisible())
											view.updateText(tokens[i] + " Has Connected");
									}
								}

							}
							//Received a chat message from network.
							if (tokens[i+1].contains("chat"))
							{
								if (view != null && view.sideframe.isVisible())view.updateText(tokens[i] + ": " + tokens[i-1]);
							}


							//For instamove ability
							if (tokens[i+1].contains("quickmove"))
							{
								String delims3 = "[ ]+";
								String[] tokens1 = tokens[0].split(delims3);
								Integer[] tokens2 = new Integer[2];
								for (int t = 0; t<tokens1.length; t++)
								{
									//System.out.println(tokens1[t]);
									tokens2[t] = Integer.parseInt(tokens1[t]);
								}
								//Switch User to Un-Hidden (Normal) and Un-Alerted
								if (model.follow == 1 
										&& view.followTarget != null 
										&& view.followTarget.equals(tokens[i]))
								{
									model.userAlertData.put(model.userClassData.get(0), false);
									model.getUserStatusData().put(tokens[i], "Normal");
									model.getPlayerLocationsData().put(model.userClassData.get(0), tokens2);
									model.move = 1;
								}
								model.userAlertData.put(tokens[i], false);
								model.getUserStatusData().put(tokens[i], "Normal");
								model.getPlayerLocationsData().put(tokens[i], tokens2);

								if(tokens[i].equals(model.userClassData.get(0))){
									view.setButtons();
								}

							}


							//Check to see if you tokens receive a trade request from network.
							if (tokens[i+1].contains("tradesearch"))
							{
								if (view != null && view.sideframe.isVisible())view.updateText(tokens[i+2] + " is trading with: " + tokens[i]);
								if (model.getUserClassData().get(0).equals(tokens[i]))
								{
									view.partner=tokens[i+2];
									view.MagicRealmGUITradeFrame(model);
								}
							}
							if (tokens[i+1].contains("tradeOffer"))
							{
								if (model.getUserClassData().get(0).equals(tokens[0]))	
								{
									view.theOffer.setText(tokens[i]);
								}
							}
							//view.targetPlayer+","+view.acceptedItem+","+view.offeredItem
							//Check to see if you receive a trade accepted request from network.
							if (tokens[i+1].contains("tradeaccept"))
							{
								if (tokens[i-1].equals("Rejected"))
								{
									view.tradeframe.setVisible(false);
									if (model.tradeAccepts.get(tokens[4]) != null)
										model.tradeAccepts.remove(tokens[4]);
									if (model.tradeAccepts.get(tokens[0]) != null)
										model.tradeAccepts.remove(tokens[0]);
									if (tokens[0].equals(model.userClassData.get(0)))
										JOptionPane.showMessageDialog(null, "Deal Was Rejected!", "Trade Notice",
												JOptionPane.INFORMATION_MESSAGE);	
								}
								else if (!tokens[i-1].equals("Rejected"))
								{
									int count = 0;
									model.tradeAccepts.put(tokens[i+2], tokens);
									if (view != null && view.sideframe.isVisible())view.updateText(tokens[i+2] + " has accepted an offer from: " + tokens[0]);
									for (String[] s : model.tradeAccepts.values())
									{
										if (s[4].equals(model.getUserClassData().get(0)) || s[0].equals(model.getUserClassData().get(0)))
										{
											count ++;
										}
									}
									if (count == 2)
									{

										if (!(model.tradeAccepts.get(model.userClassData.get(0))[1].equals("Rejected") 
												|| model.tradeAccepts.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[1].equals("Rejected")))
										{
											//Rebuild your inactive inventory
											boolean found2 = false;
											String[] yourInv = new String[model.inactiveInventoryData.get(model.getUserClassData().get(0)).length + 1];
											for (int s = 0; s < model.inactiveInventoryData.get(model.getUserClassData().get(0)).length; s++)
											{
												if (model.inactiveInventoryData.get(model.getUserClassData().get(0))[s]!=null
														&& model.inactiveInventoryData.get(model.getUserClassData().get(0))[s].equals(model.tradeAccepts.get(model.getUserClassData().get(0))[2]) 
														&& found2 == false)
												{
													found2 = true;
												}
												else {
													yourInv[s] = model.inactiveInventoryData.get(model.getUserClassData().get(0))[s];
												}
											}						
											if (model.tradeAccepts.get(model.userClassData.get(0))[1].contains("gold"))
											{
												String victoryGold = model.tradeAccepts.get(model.userClassData.get(0))[1];
												String victoryGoldDelims = "[ ]+";
												String[] victoryGoldTokens = victoryGold.split(victoryGoldDelims);
												sendMessage(new ChatMessage(ChatMessage.GOLD, victoryGoldTokens[0] + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

											}
											else
												yourInv[yourInv.length-1] = model.tradeAccepts.get(model.userClassData.get(0))[1];
											model.inactiveInventoryData.put(model.userClassData.get(0), yourInv);

											//Rebuild your active inventory
											String[] yourActiveInv = new String[model.inventoryData.get(model.getUserClassData().get(0)).length + 1];
											for (int s = 0; s < model.inventoryData.get(model.getUserClassData().get(0)).length; s++)
											{
												if (model.inventoryData.get(model.getUserClassData().get(0))[s] != null
														&& model.inventoryData.get(model.getUserClassData().get(0))[s].equals(model.tradeAccepts.get(model.getUserClassData().get(0))[2]) 
														&& found2 == false)
												{
													found2 = true;
												}
												else {
													yourActiveInv[s] = model.inventoryData.get(model.getUserClassData().get(0))[s];
												}
											}
											model.inventoryData.put(model.getUserClassData().get(0), yourActiveInv);

											//Rebuild GUI inactive inventory
											view.inactiveInventoryModel.removeAllElements();
											for (String s: yourInv)
											{
												view.inactiveInventoryModel.addElement(s);
											}
											view.inventoryModel.removeAllElements();
											for (String s: yourActiveInv)
											{
												view.inventoryModel.addElement(s);
											}

											//Rebuild partner's inventory, remove your accepted item, and add theirs.
											boolean found = false;
											if (model.inactiveInventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0]) != null)
											{
												String[] partnerInv = new String[model.inactiveInventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0]).length + 1];
												for (int s = 0; s < model.inactiveInventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0]).length; s++)
												{
													if (model.inactiveInventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[s] != null
															&& model.inactiveInventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[s].equals(model.tradeAccepts.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[2]) 
															&& found == false)
													{
														found = true;
													}
													else {
														partnerInv[s] = model.inactiveInventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[s];
													}
												}

												model.inactiveInventoryData.put(model.tradeAccepts.get(model.getUserClassData().get(0))[0], partnerInv);
											}
											if (model.inventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0]) != null)
											{
												String[] partnerActiveInv = new String[model.inventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0]).length + 1];
												for (int s = 0; s < model.inventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0]).length; s++)
												{
													if (model.inventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[s] != null
															&& model.inventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[s].equals(model.tradeAccepts.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[2]) 
															&& found == false)
													{
														found = true;
													}
													else {
														partnerActiveInv[s] = model.inventoryData.get(model.tradeAccepts.get(model.getUserClassData().get(0))[0])[s];
													}
												}

												model.inventoryData.put(model.tradeAccepts.get(model.getUserClassData().get(0))[0], partnerActiveInv);
											}


											model.tradeAccepts.remove(model.tradeAccepts.get(model.getUserClassData().get(0))[0]);
											model.tradeAccepts.remove(model.getUserClassData().get(0));
											view.tradeframe.setVisible(false);
										}
									}
								}
								//if you have not yet decided to accept or not 
								else {
									view.tradeList.setEnabled(false);
								}

							}

							//Handle searching
							if (tokens[i].contains("peer"))
							{
								boolean isFound = false;
								//someone searched for something, so build a new clearing inventory.
								//looking for the right clearing
								for (Integer[] intArray : model.getClearingInventoryData().keySet()) {
									if (intArray[0] == Integer.parseInt(tokens[1]) && intArray[1] == Integer.parseInt(tokens[2])) {
										//found the right clearing.
										//initializing our clearings new inventory data.
										String[] temp = new String[model.getClearingInventoryData().get(intArray).length];

										//building the new clearing's inventory.
										for (int j = 0; j < temp.length; j++) 
										{
											if (model.getClearingInventoryData().get(intArray)[j] != null)
											{
												if(model.getClearingInventoryData().get(intArray)[j].equals(tokens[0]))
												{
													if(isFound)
													{
														temp[j] = model.getClearingInventoryData().get(intArray)[j];
													}
													else if (tokens[i+1].equals(model.userClassData.get(0)) && !tokens[0].contains("gold"))
													{
														sendMessage(new ChatMessage(ChatMessage.INVENTORY, tokens[0]
																+","+model.getUserClassData().get(0)+",itemfound", model.getUserClassData().get(0)));
														if (model.treasureProp.containsKey(tokens[0]))
															sendMessage(new ChatMessage(ChatMessage.TREASURE, model.getUserClassData().get(0)+",treasure", model.getUserClassData().get(0)));

													}
													else if (tokens[i+1].equals(model.userClassData.get(0)) && tokens[0].contains("gold"))
													{
														String victoryGold = tokens[0];
														String victoryGoldDelims = "[ ]+";
														String[] victoryGoldTokens = victoryGold.split(victoryGoldDelims);
														sendMessage(new ChatMessage(ChatMessage.GOLD, Integer.parseInt(victoryGoldTokens[0])
																+","+model.getUserClassData().get(0)+",gold", model.getUserClassData().get(0)));
													}
													//if this is the item the user found, do not include it once from the list

													isFound = true;
												}
												else
												{
													//keep building the clearing inventory data with the rest of the items.
													temp[j] = model.getClearingInventoryData().get(intArray)[j];
												}
											}
										}
										//update the model with the new clearing inventory.
										model.getClearingInventoryData().put(intArray, temp);
									}

									//Next look through sites chit to see if the player tried to loot from a site.

								}
								if(model.siteChitData.containsKey(tokens[0]) && !isFound)
								{
									Random blah=new Random();
									int randomIndex = blah.nextInt(model.siteChitData.get(tokens[0]).length);
									if (model.cheatMode)
									{
										String temp2 = (String) JOptionPane.showInputDialog(view.sideframe, 
												"Select the item to be looted",
												"Cheat Mode: Loot",
												JOptionPane.INFORMATION_MESSAGE,
												null,
												model.siteChitData.get(tokens[0]),
												model.siteChitData.get(tokens[0])[0]);
										if(temp2!=null){
											for (int k = 0; k < model.siteChitData.get(tokens[0]).length; k++) {
												if(model.siteChitData.get(tokens[0])[k].equals(temp2)){
													randomIndex = k;
												}
											}
										}else{
											randomIndex = 0;
										}
									}
									//itemvalue is randomly assigned loot
									String itemValue = model.siteChitData.get(tokens[0])[randomIndex];
									if (itemValue == null)
									{
										for (int zz = 0; zz<model.siteChitData.get(tokens[0]).length; zz++)
										{
											if (model.siteChitData.get(tokens[0])[zz]!=null)
												itemValue = model.siteChitData.get(tokens[0])[zz];
										}
									}
									//copy the inactive inventory over to tmp
									if (itemValue.contains("gold") && model.userClassData.get(0).equals(tokens[i+1]))
									{
										String addDelims = "[ ]+";
										String[] goldTokens = itemValue.split(addDelims);
										sendMessage(new ChatMessage(ChatMessage.GOLD, Integer.parseInt(goldTokens[0])
												+","+model.getUserClassData().get(0)+",gold", model.getUserClassData().get(0)));
										sendMessage(new ChatMessage(ChatMessage.TREASURE, tokens[0]+","+itemValue+",rebuildSite", model.getUserClassData().get(0)));

									}
									else if(model.userClassData.get(0).equals(tokens[i+1]))
									{
										sendMessage(new ChatMessage(ChatMessage.INVENTORY, itemValue
												+","+model.getUserClassData().get(0)+",itemfound", model.getUserClassData().get(0)));

										if (model.treasureProp.containsKey(itemValue))
											sendMessage(new ChatMessage(ChatMessage.TREASURE, model.getUserClassData().get(0)+",treasure", model.getUserClassData().get(0)));
										sendMessage(new ChatMessage(ChatMessage.TREASURE, tokens[0]+","+itemValue+",rebuildSite", model.getUserClassData().get(0)));

									}
									//rebuild site chit treasure

								}
								//remove the element from items the user found in the search screen.
								if (model.getPlayerLocationsData().get(model.getUserClassData().get(0)) != null
										&& model.getPlayerLocationsData().get(tokens[4]) != null
										&& model.getPlayerLocationsData().get(tokens[4])[0].equals(model.getPlayerLocationsData().get(model.getUserClassData().get(0))[0]) 
										&& model.getPlayerLocationsData().get(tokens[4])[1].equals(model.getPlayerLocationsData().get(model.getUserClassData().get(0))[1]))
									if (model.siteChitData.get(tokens[0]) == null || model.siteChitData.get(tokens[0]).length == 0)
										view.searchModel.removeElement(tokens[0]);
							}


							if (tokens[i+1].contains("logout"))
							{

								if(tokens[i].equals(model.getUserClassData().get(0))){
									model.userClassData.remove(tokens[i]);
									view.userModel.removeElement(tokens[i]);
									view.actionModel.removeAllElements();
									view.inventoryModel.removeAllElements();
									view.inactiveInventoryModel.removeAllElements();
									view.woundedInventoryModel.removeAllElements();
									view.fatiguedInventoryModel.removeAllElements();
									view.sideframe.setVisible(false);
									view.mainframe.setVisible(false);
									view.combatframe.setVisible(false);
									view.mapPanel.setVisible(false);
									if (view.restframe != null && view.restframe.isVisible())
										view.restframe.setVisible(false);
									if (view.nativesframe != null && view.nativesframe.isVisible())
										view.nativesframe.setVisible(false);
									model.event.setText(model.event.getText());
									sendMessage(new ChatMessage(ChatMessage.WHOISIN, "", ""));
								} else{
									if (model != null)
										model.userClassData.remove(tokens[i]);
									if (view != null && view.sideframe != null && view.sideframe.isVisible())
									{
										view.userModel.removeElement(tokens[i]);
										if (view != null && view.sideframe.isVisible()) view.updateText(tokens[i] + " Has Disconnected");
										view.updateMap(model);
									}
								}
							}

							if (tokens[i+1].equals("gold"))
							{
								if (Integer.parseInt(tokens[i-1]) > 0 && model.userClassData.contains(tokens[i]))
								{
									model.userVictoryPoints.get(tokens[i])[4] = model.userVictoryPoints.get(tokens[i])[4] + Integer.parseInt(tokens[i-1]);
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())	view.updateText(tokens[i] + " Has Increased Gold");
									boolean found = false;
									for (int k = 0; k < model.getInactiveInventoryData().get(tokens[i]).length; k++) 
									{
										if(model.getInactiveInventoryData().get(tokens[i])[k] != null)
										{
											if(model.getInactiveInventoryData().get(tokens[i])[k].contains("gold"))
											{
												found = true;
												String add1 = model.getInactiveInventoryData().get(tokens[i])[k];
												String add2 = tokens[0];
												String addDelims = "[ ]+";
												String[] goldTokens = add1.split(addDelims);
												String[] silverTokens = add2.split(addDelims);
												//add the amount of gold looted to the players victory points.

												int newValue= Integer.parseInt(goldTokens[0]) + Integer.parseInt(silverTokens[0]);
												add1 = newValue + " gold";
												model.getInactiveInventoryData().get(tokens[i])[k] = add1;
												if (tokens[i].equals(model.userClassData.get(0))){
													for (int index = 0; index < view.inactiveInventoryModel.getSize(); index++)
														if (view.inactiveInventoryModel.getElementAt(index) != null 
														&& view.inactiveInventoryModel.getElementAt(index).contains("gold"))
															view.inactiveInventoryModel.removeElementAt(index);
													view.inactiveInventoryModel.addElement(add1);
												}
											}
										}
									}
									if (found == false){
										String[] tmp = new String[model.getInactiveInventoryData().get(tokens[i]).length + 1];
										for(int b = 0; b < model.getInactiveInventoryData().get(tokens[i]).length; b++)
										{
											tmp[b] = model.getInactiveInventoryData().get(tokens[i])[b];
										}
										tmp[tmp.length-1] = tokens[0] + " gold";
										model.getInactiveInventoryData().put(tokens[i], tmp); 
										view.inactiveInventoryModel.addElement(tokens[0] + " gold");
									}
								}
								else
								{
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())view.updateText(tokens[i] + " Has Gained No Gold");
								}
							}
							if (tokens[i+1].equals("fame"))
							{
								if (Integer.parseInt(tokens[i-1]) > 0)
								{
									model.userVictoryPoints.get(tokens[i])[2] = model.userVictoryPoints.get(tokens[i])[2] + Integer.parseInt(tokens[i-1]);
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())view.updateText(tokens[i] + " Has Increased Fame");
								}
								else
								{
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())	view.updateText(tokens[i] + " Has Gained No Fame");
								}
							}
							if (tokens[i+1].equals("itemfound"))
							{
								String[] tmp = new String[model.getInactiveInventoryData().get(tokens[i]).length+1];
								for(int b = 0; b < model.getInactiveInventoryData().get(tokens[i]).length; b++)
								{
									tmp[b] = model.getInactiveInventoryData().get(tokens[i])[b];
								}
								tmp[tmp.length-1]=tokens[i-1];
								if (model.userClassData.get(0).equals(tokens[i]))
								{
									view.inactiveInventoryModel.addElement(tokens[i-1]);
								}
							}
							if (tokens[i+1].equals("itemdestroyed"))
							{
								boolean found = false;
								//remove it from m.inventoryData
								String[] n = model.inventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								if (list.contains(tokens[i-1]))
									found = true;
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								if (found)
								{
									model.inventoryData.put(tokens[i], n);
									if (model.userClassData.get(0).equals(tokens[i]))
									{
										view.inventoryModel.removeElement(tokens[i-1]);
									}
								}
								else {
									//remove it from m.inactiveInventoryData
									String[] n2 = model.inactiveInventoryData.get(tokens[i]);
									final List<String> list2 =  new ArrayList<String>();
									Collections.addAll(list2, n2); 
									if (list2.contains(tokens[i-1]))
										found = true;
									list2.remove(tokens[i-1]);
									n2 = list2.toArray(new String[list2.size()]);
									model.inactiveInventoryData.put(tokens[i], n2);
									if (model.userClassData.get(0).equals(tokens[i]) && found)
									{
										view.inactiveInventoryModel.removeElement(tokens[i-1]);
									}
								}

							}
							if (tokens[i+1].equals("notoriety"))
							{
								if (Integer.parseInt(tokens[i-1]) > 0)
								{
									model.userVictoryPoints.get(tokens[i])[3] = model.userVictoryPoints.get(tokens[i])[3] + Integer.parseInt(tokens[i-1]);
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())
										view.updateText(tokens[i] + " Has Increased Notoriety");
								}
								else
								{
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())	
										view.updateText(tokens[i] + " Has Gained No Notoriety");
								}
							}
							if (tokens[i+1].equals("spell"))
							{
								if (Integer.parseInt(tokens[i-1]) > 0)
								{
									model.userVictoryPoints.get(tokens[i])[1] = model.userVictoryPoints.get(tokens[i])[1] + Integer.parseInt(tokens[i-1]);
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())
										view.updateText(tokens[i] + " Has Learned a Spell");
								}
								else
								{
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())
										view.updateText(tokens[i] + " Has Learned No Spell");
								}
							}
							if (tokens[i+1].equals("location"))
							{
								model.userVictoryPoints.get(tokens[i])[5] = model.userVictoryPoints.get(tokens[i])[5] + 1;
								if (view != null && view.sideframe !=null && view.sideframe.isVisible())
									view.updateText(tokens[i] + " Has Discovered a Path");
							}
							if (tokens[i+1].equals("treasure"))
							{
								model.userVictoryPoints.get(tokens[i])[0] = model.userVictoryPoints.get(tokens[i])[0] + 1;
								if (view != null && view.sideframe !=null && view.sideframe.isVisible())
									view.updateText(tokens[i] + " Has Found a Great Treasure");
							}
							if (tokens[i+1].equals("takeTWT"))
							{
								int startSize = 0;
								startSize = model.TWTdata.get(tokens[i]).length;
								String[] n = model.TWTdata.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.TWTdata.put(tokens[i], n);
								int endSize = model.TWTdata.get(tokens[i]).length;
								if (startSize==endSize || model.TWTdata.get(tokens[i])==null || model.TWTdata.get(tokens[i]).length==0)
								{
									model.TWTdata.remove(tokens[i]);
									if (view.inventoryModel.contains(tokens[i]))
									{
										sendMessage(new ChatMessage(ChatMessage.INVENTORY, tokens[i]
												+","+model.getUserClassData().get(0)+",removeActive", model.getUserClassData().get(0)));

									}
								}



							}
							if (tokens[i+1].equals("rebuildSite"))
							{
								int startSize = model.siteChitData.get(tokens[i-1]).length;
								String[] n = model.siteChitData.get(tokens[i-1]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i]);
								n = list.toArray(new String[list.size()]);
								model.siteChitData.put(tokens[i-1], n);
								int endSize = model.siteChitData.get(tokens[i-1]).length;
								if (startSize==endSize)
									model.siteChitData.remove(tokens[i-1]);
							}
							if (tokens[i+1].equals("removeActive"))
							{
								String[] n = model.inventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.inventoryData.put(tokens[i], n);

								if (model.userClassData.get(0).equals(tokens[i]))
									view.inventoryModel.removeElement(tokens[i-1]);

							}
							if (tokens[i+1].equals("removeInactive"))
							{
								String[] n = model.inactiveInventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.inactiveInventoryData.put(tokens[i], n);

								if (model.userClassData.get(0).equals(tokens[i]))
									view.inactiveInventoryModel.removeElement(tokens[i-1]);

							}
							if (tokens[i+1].equals("removeWounded"))
							{
								String[] n = model.woundedInventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.woundedInventoryData.put(tokens[i], n);
								if (model.userClassData.get(0).equals(tokens[i]))
									view.woundedInventoryModel.removeElement(tokens[i-1]);

							}
							if (tokens[i+1].equals("removeFatigued"))
							{
								String[] n = model.fatiguedInventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.fatiguedInventoryData.put(tokens[i], n);


								if (model.userClassData.get(0).equals(tokens[i]))
									view.fatiguedInventoryModel.removeElement(tokens[i-1]);

							}
							if (tokens[i+1].equals("addActive"))
							{
								//add it to m.inventoryData
								String[] tmpInv = new String[model.inventoryData.get(tokens[i]).length + 1];
								System.arraycopy(model.inventoryData.get(tokens[i]), 0, tmpInv, 0,
										Math.min(model.inventoryData.get(tokens[i]).length, model.inventoryData.get(tokens[i]).length + 1));
								tmpInv[tmpInv.length-1] = tokens[i-1];
								model.inventoryData.put(tokens[i], tmpInv);

								if (model.userClassData.get(0).equals(tokens[i]))
									view.inventoryModel.addElement(tokens[i-1]);
							}
							if (tokens[i+1].equals("addInactive"))
							{
								//add it to m.inactiveInventoryData
								String[] tmpInv = new String[model.inactiveInventoryData.get(tokens[i]).length + 1];
								System.arraycopy(model.inactiveInventoryData.get(tokens[i]), 0, tmpInv, 0,
										Math.min(model.inactiveInventoryData.get(tokens[i]).length, model.inactiveInventoryData.get(tokens[i]).length + 1));
								tmpInv[tmpInv.length-1] = tokens[i-1];
								model.inactiveInventoryData.put(tokens[i], tmpInv);
								if (model.userClassData.get(0).equals(tokens[i]))
									view.inactiveInventoryModel.addElement(tokens[i-1]);

							}
							if (tokens[i+1].equals("addWounded"))
							{
								//add it to m.woundedInventoryData
								String[] tmpInv = new String[model.woundedInventoryData.get(tokens[i]).length + 1];
								System.arraycopy(model.woundedInventoryData.get(tokens[i]), 0, tmpInv, 0,
										Math.min(model.woundedInventoryData.get(tokens[i]).length, model.woundedInventoryData.get(tokens[i]).length + 1));
								tmpInv[tmpInv.length-1] = tokens[i-1];
								model.woundedInventoryData.put(tokens[i], tmpInv);

								if (model.userClassData.get(0).equals(tokens[i]))
									view.woundedInventoryModel.addElement(tokens[i-1]);
							}
							if (tokens[i+1].equals("addFatigued"))
							{
								//add it to m.fatiguedInventoryData
								String[] tmpInv = new String[model.fatiguedInventoryData.get(tokens[i]).length + 1];
								System.arraycopy(model.fatiguedInventoryData.get(tokens[i]), 0, tmpInv, 0,
										Math.min(model.fatiguedInventoryData.get(tokens[i]).length, model.fatiguedInventoryData.get(tokens[i]).length + 1));
								tmpInv[tmpInv.length-1] = tokens[i-1];
								model.fatiguedInventoryData.put(tokens[i], tmpInv);

								if (model.userClassData.get(0).equals(tokens[i]))
									view.fatiguedInventoryModel.addElement(tokens[i-1]);
							}

							if (tokens[i+1].equals("moveInactiveToActive"))
							{
								if(tokens[i].equals(model.userClassData.get(0)))
								{
									//remove it from inactiveInventoryModel
									view.inactiveInventoryModel.removeElement(tokens[i-1]);
									//add it to inventoryModel
									view.inventoryModel.addElement(tokens[i-1]);
								}
								//remove it from m.inactiveInventoryData

								String[] n = model.inactiveInventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.inactiveInventoryData.put(tokens[i], n);

								//add it to m.inventoryData
								String[] tmpInv = new String[model.inventoryData.get(tokens[i]).length + 1];
								System.arraycopy(model.inventoryData.get(tokens[i]), 0, tmpInv, 0,
										Math.min(model.inventoryData.get(tokens[i]).length, model.inventoryData.get(tokens[i]).length + 1));
								tmpInv[tmpInv.length-1] = tokens[i-1];
								model.inventoryData.put(tokens[i], tmpInv);

							}
							if (tokens[i+1].equals("moveActiveToInactive"))
							{
								if(tokens[i].equals(model.userClassData.get(0)))
								{
									//remove it from inventoryModel
									view.inventoryModel.removeElement(tokens[i-1]);
									//add it to inactiveInventoryModel
									view.inactiveInventoryModel.addElement(tokens[i-1]);
								}

								//remove it from m.inventoryData
								String[] n = model.inventoryData.get(tokens[i]);
								final List<String> list =  new ArrayList<String>();
								Collections.addAll(list, n); 
								list.remove(tokens[i-1]);
								n = list.toArray(new String[list.size()]);
								model.inventoryData.put(tokens[i], n);

								//add it to m.inactiveInventoryData
								String[] tmpInv = new String[model.inactiveInventoryData.get(tokens[i]).length + 1];
								System.arraycopy(model.inactiveInventoryData.get(tokens[i]), 0, tmpInv, 0,
										Math.min(model.inactiveInventoryData.get(tokens[i]).length, model.inactiveInventoryData.get(tokens[i]).length + 1));
								tmpInv[tmpInv.length-1] = tokens[i-1];
								model.inactiveInventoryData.put(tokens[i], tmpInv);

							}


							//Handle combat moves
							if (tokens[i+1].equals("combatmovesreceived"))
							{
								view.combatframe.setVisible(false);
								view.hide.setEnabled(true);
								view.trade.setEnabled(true);
								view.alert.setEnabled(true);
								view.follow.setEnabled(true);
								view.hide.setEnabled(true);
								view.trade.setEnabled(true);
								view.alert.setEnabled(true);
								view.follow.setEnabled(true);
								view.mingle.setEnabled(false);
								for (Integer[] nativeLoc : model.nativeLocation.values()){
									if (nativeLoc[0].equals(model.getPlayerLocationsData().get(model.userClassData.get(0))[0])
											&& nativeLoc[1].equals(model.getPlayerLocationsData().get(model.userClassData.get(0))[1]))
									{
										view.mingle.setEnabled(true);
										break;
									}
								}
								view.search.setEnabled(true);
								view.rest.setEnabled(true);
								view.ok.setEnabled(true);
								view.inventoryList.setEnabled(true);
								view.inactiveInventoryList.setEnabled(true);
								view.fatiguedInventoryList.setEnabled(true);
								view.woundedInventoryList.setEnabled(true);

								//OFFENSE AGAINST MONSTERS
								for (String[] actions : model.getUserCombatActions().values()) 
								{
									for (String followingNative : model.nativesFollowing) 
									{

										if ((actions[6] != null && actions[6] != "No One" && (actions[6].equals(model.getUserClassData().get(0))
												|| actions[6].equals(followingNative))))
										{
											for (String targetMonster : model.monsterData.keySet())
											{
												if (actions[5].equals(targetMonster))
												{
													boolean hit = false;
													//ITEM SPECIAL ABILITY : OINTMENT OF BITE
													boolean hasBite = false;

													if (model.inventoryData.get(actions[6]) != null)
														for (int num = 0; num < model.inventoryData.get(actions[6]).length; num++)
														{
															if (model.inventoryData.get(actions[6])[num].contains("Ointment of Bite"))
															{
																hasBite = true;
																view.updateText("Ointment of Bite Imbued" + actions[6]+"'s "+actions[4]);
															}
														}
													//IF MONSTER MANEUVER SPEED[3] > ATTACKER's(YOU) FIGHT SPEED[2]
													//	System.out.println("Monster speed: "+model.getUserCombatActions().get(targetMonster)[3].charAt(5)+", "+model.getUserCombatActions().get(actions[6])+" speed: "+ model.getUserCombatActions().get(actions[6])[2].charAt(6));
													//	System.out.println("Monster manuever: "+model.getUserCombatActions().get(targetMonster)[1]+", "+model.getUserCombatActions().get(actions[6])+" manuever: "+ model.getUserCombatActions().get(actions[6])[0]);
													if(model.getUserCombatActions().get(targetMonster) != null 
															&& model.getUserCombatActions().get(actions[6]) != null
															&& (model.getUserCombatActions().get(targetMonster)[3].charAt(5) > model.getUserCombatActions().get(actions[6])[2].charAt(6)
																	|| (hasBite && (model.getUserCombatActions().get(targetMonster)[3].charAt(5) >= 
																	model.getUserCombatActions().get(actions[6])[2].charAt(6))))){
														hit=true;
													}
													//IF DUCK(MONSTER) AND SWING(YOU)
													else if(model.getUserCombatActions().get(targetMonster) != null 
															&& model.getUserCombatActions().get(actions[6]) != null
															&& model.getUserCombatActions().get(targetMonster)[1].equals("Duck")){
														if(model.getUserCombatActions().get(actions[6]) != null 
																&& model.getUserCombatActions().get(actions[6])[0].equals("Smash")){
															hit=true;
														}
													}
													//IF DODGE(MONSTER) AND SWING(YOU)
													else if(model.getUserCombatActions().get(targetMonster) != null 
															&& model.getUserCombatActions().get(actions[6]) != null
															&& model.getUserCombatActions().get(targetMonster)[1].equals("Dodge")){
														if(model.getUserCombatActions().get(actions[6]) != null 
																&& model.getUserCombatActions().get(actions[6])[0].equals("Swing")){
															hit=true;
														}
													}

													else if(model.getUserCombatActions().get(targetMonster) != null 
															&& model.getUserCombatActions().get(actions[6]) != null
															&& model.getUserCombatActions().get(targetMonster)[1].equals("Charge")){
														if(model.getUserCombatActions().get(actions[6]) != null 
																&& model.getUserCombatActions().get(actions[6])[0].equals("Thrust")){
															hit=true;
														}
													}
													int atkStr = 0;
													int atkSharp = 0;
													Dice missileRoll = new Dice();
													if(hit){
														//	System.out.println("Monster hit!");
														if (!actions[4].equals(null))
														{
															if(actions[4].equals("Native")){
																atkStr = model.nativeData.get(actions[6])[4];
															} else{
																atkStr = model.getWeaponData().get(actions[4])[1];
															}

															//Missile logic

															// for special ability rol -1 
															//AIM ability
															int roll = missileRoll.roll();
															int roll2 = missileRoll.roll();

															if(roll<roll2 && !actions[6].equals("Elf")
																	&& !actions[6].equals("Woods Girl")){
																roll=roll2;
															}

															if(actions[6].equals("Amazon")
																	|| actions[6].equals("Black Knight")
																	|| actions[6].equals("Captain"))
																roll = roll -1 ;
															if (model.cheatMode)
															{
																Object[] missleSheet = {"1", "2", "3", "4", "5", "6"};
																String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
																		"Select the die value to be used for the missile roll",
																		"Cheat Mode: Missile Roll",
																		JOptionPane.INFORMATION_MESSAGE,
																		null,
																		missleSheet,
																		1);
																if(temp!=null){
																	roll = Integer.parseInt(temp);
																}else{
																	roll = missileRoll.roll();
																}
															}
															//Archer ability
															if (roll < 0) roll= 0;
															if (actions[4].equals("Light Bow") || actions[4].equals("Medium Bow"))

															{

																switch (roll) {
																case 1:
																	atkStr=atkStr+2;
																	break;
																case 2:
																	atkStr++;
																	break;
																case 4:
																	atkStr--;
																	break;
																case 5:
																	atkStr=atkStr-2;
																	break;
																case 6:
																	atkStr=atkStr-3;
																	break;

																default:
																	break;
																}

															}

														}

														else
														{
															atkStr = model.getWeaponData().get("Dagger")[1];
															if (model.userAlertData.get(actions[5]))
															{
																atkSharp = model.getWeaponData().get("Dagger")[4];
															}
															else {
																atkSharp = model.getWeaponData().get("Dagger")[3];
															}
														} 
														//if the attacker's fight chit is of greater strength than the attack, the attack's strength attack increases by 1
														char c = model.getUserCombatActions().get(actions[5])[2].charAt(5);
														int chitStr;
														switch(c){
														case 'L': chitStr = 1;
														case 'M': chitStr = 2;
														case 'H': chitStr = 3;
														case 'T': chitStr = 4;
														default: chitStr = 0;
														}

														if(chitStr>atkStr){
															atkStr++;
														}

														atkStr += atkSharp;

														//		System.out.println("Monster:"+targetMonster+", Your attack strength: "+atkStr);

														//If the monster died, tell all players on the network who killed the monster.
														if (model.monsterData.get(targetMonster)[0] <= atkStr){
															sendMessage(new ChatMessage(ChatMessage.FAME, model.monsterData.get(targetMonster)[2]
																	+","+model.getUserClassData().get(0)+",fame", model.getUserClassData().get(0)));
															sendMessage(new ChatMessage(ChatMessage.NOTORIETY, model.monsterData.get(targetMonster)[1]
																	+","+model.getUserClassData().get(0)+",notoriety", model.getUserClassData().get(0)));
														}


													}
												}
											}



											for (String targetNative : model.nativeData.keySet())
											{
												if (actions[5].equals(targetNative))
												{
													boolean hit = false;
													//ITEM SPECIAL ABILITY : OINTMENT OF BITE
													boolean hasBite = false;
													if (model.inventoryData.get(actions[6]) != null)
														for (int num = 0; num < model.inventoryData.get(actions[6]).length; num++)
														{
															if (model.inventoryData.get(actions[6])[num].contains("Ointment of Bite"))
															{
																hasBite = true;
																view.updateText("Ointment of Bite Imbued" + actions[6]+"'s "+actions[4]);
															}
														}
													//IF NATIVE MANEUVER SPEED[3] > YOUR FIGHT SPEED[2]
													if((model.getUserCombatActions().get(targetNative)[3].charAt(5) > model.getUserCombatActions().get(actions[6])[2].charAt(6)) || (hasBite && (model.getUserCombatActions().get(targetNative)[3].charAt(5) >= 
															model.getUserCombatActions().get(actions[6])[2].charAt(6)))){
														hit=true;
													}
													//IF DUCK(NATIVE) AND SWING(YOU)
													else if(model.getUserCombatActions().get(targetNative)[1].equals("Duck")){
														if(model.getUserCombatActions().get(actions[6]) != null 
																&& model.getUserCombatActions().get(actions[6])[0].equals("Smash")){
															hit=true;
														}
													}
													//IF DODGE(NATIVE) AND SWING(YOU)
													else if(model.getUserCombatActions().get(targetNative)[1].equals("Dodge")){
														if(model.getUserCombatActions().get(actions[6]) != null 
																&& model.getUserCombatActions().get(actions[6])[0].equals("Swing")){
															hit=true;
														}
													}

													else if(model.getUserCombatActions().get(targetNative)[1].equals("Charge")){
														if(model.getUserCombatActions().get(actions[6]) != null 
																&& model.getUserCombatActions().get(actions[6])[0].equals("Thrust")){
															hit=true;
														}
													}
													int atkStr = 0;
													int atkSharp = 0;
													Dice[] missileRoll = new Dice[2];
													if(hit){
														if (!actions[4].equals(null))
														{
															atkStr = model.getWeaponData().get(actions[4])[1];


															//Missile logic

															// for special ability rol -1 
															//AIM ability
															int roll = missileRoll[0].roll();
															int roll2 = missileRoll[1].roll();

															if(roll<roll2 && !actions[6].equals("Elf")
																	&& !actions[6].equals("Woods Girl")){
																roll=roll2;
															}

															if(actions[6].equals("Amazon")
																	|| actions[6].equals("Black Knight")
																	|| actions[6].equals("Captain"))
																roll = roll -1 ;

															if (roll < 0) roll= 0;
															if (actions[4].equals("Light Bow") || actions[4].equals("Medium Bow"))
															{

																switch (roll) {
																case 1:
																	atkStr=atkStr+2;
																	break;
																case 2:
																	atkStr++;
																	break;
																case 4:
																	atkStr--;
																	break;
																case 5:
																	atkStr=atkStr-2;
																	break;
																case 6:
																	atkStr=atkStr-3;
																	break;

																default:
																	break;
																}

															}

														}

														else
														{
															atkStr = model.getWeaponData().get("Dagger")[1];
															if (model.userAlertData.get(actions[5]))
															{
																atkSharp = model.getWeaponData().get("Dagger")[4];
															}
															else {
																atkSharp = model.getWeaponData().get("Dagger")[3];
															}
														} 
														//if the attacker's fight chit is of greater strength than the attack, the attack's strength attack increases by 1
														char c = model.getUserCombatActions().get(actions[5])[2].charAt(5);
														int chitStr;
														switch(c){
														case 'L': chitStr = 1;
														case 'M': chitStr = 2;
														case 'H': chitStr = 3;
														case 'T': chitStr = 4;
														default: chitStr = 0;
														}

														if(chitStr>atkStr){
															atkStr++;
														}


														atkStr += atkSharp;

														//If native died, tell all players on the network that you killed the native
														if (model.nativeData.get(targetNative)[0] <= atkStr)
															sendMessage(new ChatMessage(ChatMessage.NOTORIETY, model.nativeData.get(targetNative)[1]+","+model.getUserClassData().get(0)+",notoriety", model.getUserClassData().get(0)));
													}
												}
											}
										}
									}
								}
								//DEFENCE
								//Cycle through the list of players that attack you first, later we will take care of monsters.


								ArrayList<String> combatOrder = new ArrayList<String>();
								for (String string : model.getUserCombatActions().keySet()) {
									combatOrder.add(string);
								}
								//SWORDSMAN SPECIAL ABILITY : CLEAVE
								if(model.userCombatActions.containsKey("Swordsman")){
									combatOrder.remove("Swordsman");
									combatOrder.add(0, "Swordsman");
									if (view != null && view.sideframe !=null && view.sideframe.isVisible())
										view.updateText("Swordsman Cleaves And Attacks First!");
								}

								String[] string;
								for (String str : combatOrder) {
									string = model.getUserCombatActions().get(str);
									boolean hit = false;
									//PILGRIM SPECIAL ABILITY : HEAVENLY PROTECTION
									if (!(string[5].equals(model.getUserClassData().get(0)) && string[5].equals("Pilgrim") 
											&& 	(string[6].contains("Imp") || string[6].equals("Demon") || string[6].contains("Winged Demon"))))
									{
										char weight = 'x';
										if (model.getUserCombatActions().get(string[6])!=null && model.getUserCombatActions().get(string[6])[2]!=null)
											weight = model.getUserCombatActions().get(string[6])[2].charAt(5);
										int oppChitStr;
										switch(weight){
										case 'L': oppChitStr = 1;
										case 'M': oppChitStr = 2;
										case 'H': oppChitStr = 3;
										case 'T': oppChitStr = 4;
										default: oppChitStr = 0;
										}
										if (!view.inventoryModel.contains("Ointment Of Steel"))
										{

											//make sure the target (you) string is valid.
											//IF YOU ARE THE TARGET OF ANOTHER PLAYER's ATTACK
											if (string[5] != null && string[5] != "No One" && string[5].equals(model.getUserClassData().get(0)))
											{

												//CHECK FIRST IF YOU RAN AWAY.
												if(model.getUserCombatActions().get(model.getUserClassData().get(0))[5].contains("No One"))
												{
													//IF YOU RAN AWAY, ONLY CHECK MANEUVER VS FIGHT SPEEDS.
													//IF YOUR MANEUVER SPEED[3] > ATTACKER's FIGHT SPEED[2], You're dead.
													//ITEM SPECIAL ABILITY : OINTMENT OF BITE
													boolean hasBite = false;
													if (model.inventoryData.get(string[6]) != null)
														for (int num = 0; num < model.inventoryData.get(string[6]).length; num++)
														{
															if (model.inventoryData.get(string[6])[num].contains("Ointment of Bite"))
															{
																hasBite = true;
																view.updateText("Ointment of Bite Imbued" + string[6]+"'s "+string[4]);
															}
														}
													if((model.getUserCombatActions().get(model.getUserClassData().get(0))[3].charAt(5) > 
													model.getUserCombatActions().get(string[6])[2].charAt(6)) || (hasBite && (model.getUserCombatActions().get(model.getUserClassData().get(0))[3].charAt(5) >= 
													model.getUserCombatActions().get(string[6])[2].charAt(6))))
													{
														sendMessage(new ChatMessage(ChatMessage.CHAT, "Tried To Run Away!,"+model.getUserClassData().get(0)+", chat", model.getUserClassData().get(0)));
														boolean helmet = false;
														boolean breastplate = false;
														boolean suitofarmour = false;
														int atkStr = 0;
														int atkSharp = 0;
														Dice[] missileRoll = new Dice[2];
														missileRoll[0] = new Dice();
														missileRoll[1] = new Dice();

														if (!string[4].equals(null))
														{
															if (!string[4].equals("Monster"))
																atkStr = model.getWeaponData().get(string[4])[1];
															else 
																atkStr = model.monsterData.get(string[6])[4];
															if (model.userAlertData.get(string[6]) != null 
																	&& model.userAlertData.get(string[6]))
															{
																if (!string[4].equals("Monster"))
																	atkSharp = model.getWeaponData().get(string[4])[4];
																else
																	atkSharp = model.monsterData.get(string[6])[11];

															}
															else {
																if (!string[4].equals("Monster"))
																	atkSharp = model.getWeaponData().get(string[4])[3];
																else
																	atkSharp = model.monsterData.get(string[6])[7];

															}

															//Missile logic

															// for special ability rol -1 
															//AIM ability
															int roll = missileRoll[0].roll();
															int roll2 = missileRoll[1].roll();

															if(roll<roll2 && !string[6].equals("Elf")
																	&& !string[6].equals("Woods Girl")){
																roll=roll2;
															}

															if(string[6].equals("Amazon")
																	|| string[6].equals("Black Knight")
																	|| string[6].equals("Captain"))
																roll = roll -1 ;

															if (roll < 0) roll= 0;

															if (string[4].equals("Light Bow") || string[4].equals("Medium Bow") || string[4].equals("Alchemist's Mixture"))
															{

																switch (roll) {
																case 1:
																	atkStr=atkStr+2;
																	break;
																case 2:
																	atkStr++;
																	break;
																case 4:
																	atkStr--;
																	break;
																case 5:
																	atkStr=atkStr-2;
																	break;
																case 6:
																	atkStr=atkStr-3;
																	break;

																default:
																	break;
																}

															}

														}
														else
														{
															atkStr = model.getWeaponData().get("Dagger")[1];
															if (model.userAlertData.get(string[6]))
															{
																atkSharp = model.getWeaponData().get("Dagger")[4];
															}
															else {
																atkSharp = model.getWeaponData().get("Dagger")[3];
															}
														} 

														//if the attacker's fight chit is of greater strength than the attack, the attack's strength attack increases by 1
														if(oppChitStr>atkStr){
															atkStr++;
														}

														for(int ii = 0; ii<model.getInventoryData().get(model.getUserClassData().get(0)).length; ii++)
														{
															if (model.getInventoryData().get(model.getUserClassData().get(0))[ii].equals("Helmet"))
															{
																helmet = true;
																if (model.inventoryData.get(string[6]) != null)
																{
																	for (String oppInvItem : model.inventoryData.get(string[6]))
																	{
																		if (oppInvItem.equals("Penetrating Grease"))
																		{
																			helmet = false;
																			view.updateText(string[6]+"'s Penetrating Grease Negates Your Armor!");
																		}
																	}
																}
															}
															else if (model.getInventoryData().get(model.getUserClassData().get(0))[ii].equals("Breastplate"))
															{
																breastplate = true;
																if (model.inventoryData.get(string[6]) != null)
																{
																	for (String oppInvItem : model.inventoryData.get(string[6]))
																	{
																		if (oppInvItem.equals("Penetrating Grease"))
																		{
																			breastplate = false;
																			view.updateText(string[6]+"'s Penetrating Grease Negates Your Armor!");
																		}
																	}
																}
															}
															else if (model.getInventoryData().get(model.getUserClassData().get(0))[ii].equals("Suit of Armour"))
															{
																suitofarmour = true;
																if (model.inventoryData.get(string[6]) != null)
																{
																	for (String oppInvItem : model.inventoryData.get(string[6]))
																	{
																		if (oppInvItem.equals("Penetrating Grease"))
																		{
																			suitofarmour = false;
																			view.updateText(string[6]+"'s Penetrating Grease Negates Your Armor!");
																		}
																	}
																}
															}
														}
														if (model.getUserCombatActions().get(string[6])[0].equals("Smash")){
															//IN THE CASE USER IS WEARING GOLDEN CROWN
															for (int element=0; element<view.inventoryModel.size();element++){
																if (view.inventoryModel.elementAt(element).equals("Golden Crown"))
																{
																	if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																		view.updateText("The Golden Crown Deflected The Attack");


																	if(oppChitStr>1){
																		sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Golden Crown"
																				+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		if (view != null && view.sideframe !=null && view.sideframe.isVisible())	
																			view.updateText("The Golden Crown Has Been Destroyed");
																		sendMessage(new ChatMessage(ChatMessage.GOLD, 48 + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

																	}
																	atkSharp = 0;
																	atkStr = 0;
																	break;
																}
															}
															if (helmet)
															{
																atkSharp--;

																if (model.damagedArmour.contains("Helmet"))
																{
																	if (oppChitStr<2)
																	{
																		atkStr = 0;
																		atkSharp = 0;
																	}
																	for (int element=0; element<view.inventoryModel.size();element++)
																	{
																		if (view.inventoryModel.elementAt(element).equals("Helmet"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																				view.updateText("The Helmet Has Been Destroyed");
																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Helmet"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		}
																	}
																}
																else
																	model.damagedArmour.add("Helmet");
															}
															if (suitofarmour)
															{
																atkSharp--;		
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Suit of Armour"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Suit of Armour"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																				view.updateText("The Suit of Armour Has Been Destroyed");

																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Suit of Armour"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));
																		}
																	}
																}
																else
																	model.damagedArmour.add("Suit of Armour");
															}
														}	
														else if (model.getUserCombatActions().get(string[6])[0].equals("Swing") || model.getUserCombatActions().get(string[6])[0].equals("Thrust")){

															//IN THE CASE USER IS WEARING BEJEWELED DWARF VEST AND/OR GOLDEN ARM BAND
															for (int element=0; element<view.inventoryModel.size();element++){
																if (view.inventoryModel.elementAt(element).equals("Bejeweled Dwarf Vest"))
																{
																	if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																		view.updateText("The Bejeweled Dwarf Vest Deflected The Attack");


																	if(oppChitStr>2){
																		sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Bejeweled Dwarf Vest"
																				+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		if (view != null && view.sideframe !=null && view.sideframe.isVisible())		
																			view.updateText("The Bejeweled Dwarf Vest Has Been Destroyed");
																		sendMessage(new ChatMessage(ChatMessage.GOLD, 23 + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

																	}
																	atkSharp = 0;
																	atkStr = 0;
																	break;
																}

																if (view.inventoryModel.elementAt(element).equals("Golden Arm Band"))
																{
																	if (view != null && view.sideframe !=null && view.sideframe.isVisible()) 
																		view.updateText("The Golden Arm Band Deflected The Attack");
																	if(oppChitStr>1){
																		sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Golden Arm Band"
																				+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		if (view != null && view.sideframe !=null && view.sideframe.isVisible()) 
																			view.updateText("The Golden Arm Band Has Been Destroyed");
																		sendMessage(new ChatMessage(ChatMessage.GOLD, 11 + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

																	}
																	atkSharp = 0;
																	atkStr = 0;
																	break;
																}
															}
															if (breastplate)
															{
																atkSharp--;
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Breastplate"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Breastplate"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																				view.updateText("The Breastplate Has Been Destroyed");

																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Breastplate"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		}
																	}
																}
																else
																	model.damagedArmour.add("Breastplate");
															}
															if (suitofarmour)
															{
																atkSharp--;
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Suit of Armour"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Suit of Armour"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																				view.updateText("The Suit of Armour Has Been Destroyed");
																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Suit of Armour"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		}
																	}
																}
																else
																	model.damagedArmour.add("Suit of Armour");
															}
														}
														atkStr += atkSharp;

														//If you died, tell all players on the network.
														if (model.getUserClassVulnerability().get(model.userClassData.get(0)) <= atkStr){
															view.initialzed = false;
															//Send your gold to attacking player
															for (String inv: model.inactiveInventoryData.get(model.userClassData.get(0)))
															{
																if (inv.contains("gold"))
																{
																	String victoryGold = inv;
																	String victoryGoldDelims = "[ ]+";
																	String[] victoryGoldTokens = victoryGold.split(victoryGoldDelims);
																	sendMessage(new ChatMessage(ChatMessage.GOLD, victoryGoldTokens[0] + "," + string[6] + ",gold",model.userClassData.get(0)));
																	sendMessage(new ChatMessage(ChatMessage.INVENTORY, inv + "," + model.userClassData.get(0) +",removeInactive", model.userClassData.get(0)));

																}
															}
															sendMessage(new ChatMessage(ChatMessage.DEATH, model.userClassData.get(0)+", died", model.userClassData.get(0)));
														}
														else if (hit)
														{
															sendMessage(new ChatMessage(ChatMessage.CHAT, " Somehow Survived The Attack!," + model.userClassData.get(0) + ", chat", model.getUserClassData().get(0)));
															//ITEM SPECIAL ABILITY : REFLECTING GREASE
															if (!view.inventoryModel.contains("Reflecting Grease"))
																view.MagicRealmGUIWoundingScreen(model);
															else 
															{
																if (!(breastplate || suitofarmour || helmet))
																	view.MagicRealmGUIWoundingScreen(model);
																else 
																	view.updateText("Reflecting Grease Prevented A Wound");
															}
														}

													}
													else
													{
														sendMessage(new ChatMessage(ChatMessage.CHAT, " Dodged Combat!,"+model.getUserClassData().get(0) +", chat", model.getUserClassData().get(0)));
													}
												}
												//IF YOU ARE NOT RUNNING AWAY
												else 
												{
													//ITEM SPECIAL ABILITY : OINTMENT OF BITE
													boolean hasBite = false;
													if (model.inventoryData.get(string[6]) != null)
														for (int num = 0; num < model.inventoryData.get(string[6]).length; num++)
														{
															if (model.inventoryData.get(string[6])[num] != null
																	&& !model.inventoryData.get(string[6])[num].equals("null") &&
																	model.inventoryData.get(string[6])[num].contains("Ointment of Bite"))
															{
																hasBite = true;
															}
														}
													if (hasBite && (model.getUserCombatActions().get(string[6]) != null
															&& model.getUserCombatActions().get(string[6])[2] != null
															&& !model.getUserCombatActions().get(string[6])[2].equals("null")
															&& (model.getUserCombatActions().get(model.getUserClassData().get(0))[3].equals("null") || model.getUserCombatActions().get(model.getUserClassData().get(0))[3].charAt(5) >= 
															model.getUserCombatActions().get(string[6])[2].charAt(6))))
													{
														hit=true;
														//ITEM SPECIAL ABILITY : SHIELD
														if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Smash"))
														{
															if (view.shieldDuck)
															{
																hit = false;
																if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																	view.updateText("Your Shield Got Hit");
															}
														}
														else if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Swing")){
															if (view.shieldDodge)
															{
																hit = false;
																if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																	view.updateText("Your Shield Got Hit");
															}
														}
														else if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Thrust")){
															if (view.shieldCharge)
															{
																hit = false;
																if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																	view.updateText("Your Shield Got Hit");
															}
														}
													}
													//IF YOUR MANEUVER SPEED[3] > ATTACKER's FIGHT SPEED[2]
													else if(model.getUserCombatActions().get(string[6]) != null
															&& model.getUserCombatActions().get(string[6])[2] != null
															&& !model.getUserCombatActions().get(string[6])[2].equals("null")
															&& (model.getUserCombatActions().get(model.getUserClassData().get(0))[3].equals("null") || model.getUserCombatActions().get(model.getUserClassData().get(0))[3].charAt(5) > 
															model.getUserCombatActions().get(string[6])[2].charAt(6))){
														hit=true;
														//ITEM SPECIAL ABILITY : SHIELD
														if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Smash"))
														{
															if (view.shieldDuck)
															{
																hit = false;
																if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																	view.updateText("Your Shield Got Hit");
															}
														}
														else if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Swing")){
															if (view.shieldDodge)
															{
																hit = false;
																if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																	view.updateText("Your Shield Got Hit");
															}
														}
														else if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Thrust")){
															if (view.shieldCharge)
															{
																hit = false;
																if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																	view.updateText("Your Shield Got Hit");
															}
														}
													}
													//IF DUCK(YOU) AND SWING(ATTACKER)
													else if(model.getUserCombatActions().get(model.getUserClassData().get(0))[1].equals("Duck")){
														if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Smash")){
															hit=true;


															//IN THE CASE USER IS WEARING GOLDEN CROWN
															for (int element=0; element<view.inventoryModel.size();element++){
																if (view.inventoryModel.elementAt(element).equals("Golden Crown"))
																{
																	if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																		view.updateText("The Golden Crown Deflected The Attack");


																	if(oppChitStr>1){
																		sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Golden Crown"
																				+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		if (view != null && view.sideframe !=null && view.sideframe.isVisible())	
																			view.updateText("The Golden Crown Has Been Destroyed");
																		sendMessage(new ChatMessage(ChatMessage.GOLD, 48 + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

																	}
																	hit = false;
																	break;
																}
															}
														}
													}
													//IF DODGE(YOU) AND SWING(ATTACKER)
													else if(model.getUserCombatActions().get(model.getUserClassData().get(0))[1].equals("Dodge")){
														if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Swing")){
															hit=true;
															//IN THE CASE USER IS WEARING GOLDEN ARM BAND
															for (int element=0; element<view.inventoryModel.size();element++){
																if (view.inventoryModel.elementAt(element).equals("Golden Arm Band"))
																{
																	if (view != null && view.sideframe.isVisible())view.updateText("The Golden Arm Band Deflected The Attack");

																	if(oppChitStr>1){
																		sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Golden Arm Band"
																				+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		if (view != null && view.sideframe !=null && view.sideframe.isVisible()) 
																			view.updateText("The Golden Arm Band Has Been Destroyed");
																		sendMessage(new ChatMessage(ChatMessage.GOLD, 11 + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

																	}
																	hit = false;
																	break;
																}
															}
														}
													}

													else if(model.getUserCombatActions().get(model.getUserClassData().get(0))[1].equals("Charge")){
														if(model.getUserCombatActions().get(string[6]) != null 
																&& model.getUserCombatActions().get(string[6])[0].equals("Thrust")){
															hit=true;
															//IN THE CASE USER IS WEARING BEJEWELED DWARF VEST
															for (int element=0; element<view.inventoryModel.size();element++){
																if (view.inventoryModel.elementAt(element).equals("Bejeweled Dwarf Vest"))
																{
																	if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																		view.updateText("The Bejeweled Dwarf Vest Deflected The Attack");


																	if(oppChitStr>2){

																		sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Bejeweled Dwarf Vest"
																				+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																			view.updateText("The Bejeweled Dwarf Vest Has Been Destroyed");
																		sendMessage(new ChatMessage(ChatMessage.GOLD, 23 + "," + model.getUserClassData().get(0) + ",gold",model.userClassData.get(0)));

																	}
																	hit = false;
																	break;
																}
															}

														}
													}
													boolean helmet = false;
													boolean breastplate = false;
													boolean suitofarmour = false;
													int atkStr = 0;
													int atkSharp = 0;
													Dice missileRoll = new Dice();
													if(hit){
														if (!string[4].equals(null))
														{
															if (string[4]!=null
																	&& model.getWeaponData().get(string[4])!=null
																	&& !string[4].equals("Monster"))
																atkStr = model.getWeaponData().get(string[4])[1];
															if (model.userAlertData.get(string[6])!=null
																	&& model.userAlertData.get(string[6]))
															{
																atkSharp = model.getWeaponData().get(string[4])[4];
															}
															else {
																if (model.getWeaponData().get(string[4]) != null)
																	atkSharp = model.getWeaponData().get(string[4])[3];
															}

															//Missile logic

															// for special ability rol -1 
															//AIM ability
															int roll = missileRoll.roll();
															int roll2 = missileRoll.roll();

															if(roll<roll2 && !string[6].equals("Elf")
																	&& !string[6].equals("Woods Girl")){
																roll=roll2;
															}

															if(string[6].equals("Amazon")
																	|| string[6].equals("Black Knight")
																	|| string[6].equals("Captain"))
																roll = roll -1 ;

															if (roll < 0) roll= 0;
															if (string[4].equals("Light Bow") || string[4].equals("Medium Bow"))
															{

																switch (roll) {
																case 1:
																	atkStr=atkStr+2;
																	break;
																case 2:
																	atkStr++;
																	break;
																case 4:
																	atkStr--;
																	break;
																case 5:
																	atkStr=atkStr-2;
																	break;
																case 6:
																	atkStr=atkStr-3;
																	break;

																default:
																	break;
																}

															}

														}

														else
														{
															atkStr = model.getWeaponData().get("Dagger")[1];
															if (model.userAlertData.get(string[6]))
															{
																atkSharp = model.getWeaponData().get("Dagger")[4];
															}
															else {
																atkSharp = model.getWeaponData().get("Dagger")[3];
															}
														} 
														//if the attacker's fight chit is of greater strength than the attack, the attack's strength attack increases by 1

														if(oppChitStr>atkStr){
															atkStr++;
														}

														for(int ii = 0; ii<model.getInventoryData().get(model.getUserClassData().get(0)).length; ii++)
														{
															if (model.getInventoryData().get(model.getUserClassData().get(0))[ii]!=null
																	&&model.getInventoryData().get(model.getUserClassData().get(0))[ii].equals("Helmet"))
															{
																helmet = true;
																if (model.inventoryData.get(string[6]) != null)
																{
																	for (String oppInvItem : model.inventoryData.get(string[6]))
																	{
																		if (oppInvItem.equals("Penetrating Grease"))
																		{
																			helmet = false;
																			view.updateText(string[6]+"'s Penetrating Grease Negates Your Armor!");
																		}
																	}
																}
															}
															else if (model.getInventoryData().get(model.getUserClassData().get(0))[ii] !=null
																	&& model.getInventoryData().get(model.getUserClassData().get(0))[ii].equals("Breastplate"))
															{
																breastplate = true;
																if (model.inventoryData.get(string[6]) != null)
																{
																	for (String oppInvItem : model.inventoryData.get(string[6]))
																	{
																		if (oppInvItem.equals("Penetrating Grease"))
																		{
																			breastplate = false;
																			view.updateText(string[6]+"'s Penetrating Grease Negates Your Armor!");
																		}
																	}
																}
															}
															else if (model.getInventoryData().get(model.getUserClassData().get(0))[ii]!=null
																	&&model.getInventoryData().get(model.getUserClassData().get(0))[ii].equals("Suit of Armour"))
															{
																suitofarmour = true;
																if (model.inventoryData.get(string[6]) != null)
																{
																	for (String oppInvItem : model.inventoryData.get(string[6]))
																	{
																		if (oppInvItem.equals("Penetrating Grease"))
																		{
																			suitofarmour = false;
																			view.updateText(string[6]+"'s Penetrating Grease Negates Your Armor!");
																		}
																	}
																}
															}
														}
														if (model.getUserCombatActions().get(string[6])[0].equals("Smash")){
															if (helmet)
															{
																atkSharp--;
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Helmet"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Helmet"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible()) 
																				view.updateText("The Helmet Has Been Destroyed");
																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Helmet"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		}
																	}
																}
																else
																	model.damagedArmour.add("Helmet");
															}
															if (suitofarmour)
															{
																atkSharp--;
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Suit of Armour"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Suit of Armour"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible())
																				view.updateText("The Suit of Armour Has Been Destroyed");
																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Suit of Armour"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));

																		}
																	}
																}
																else
																	model.damagedArmour.add("Suit of Armour");
															}
														}	
														else if (model.getUserCombatActions().get(string[6])[0].equals("Swing") || model.getUserCombatActions().get(string[6])[0].equals("Thrust")){
															if (breastplate)
															{
																atkSharp--;
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Breastplate"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Breastplate"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible()) 
																				view.updateText("The Breastplate Has Been Destroyed");
																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Breastplate"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));
																		}
																	}
																}
																else
																	model.damagedArmour.add("Breastplate");
															}
															if (suitofarmour)
															{
																atkSharp--;
																if (oppChitStr<2)
																{
																	atkStr = 0;
																	atkSharp = 0;
																}
																if (model.damagedArmour.contains("Suit of Armour"))
																{
																	for (int element=0; element<view.inventoryModel.size();element++){
																		if (view.inventoryModel.elementAt(element).equals("Suit of Armour"))
																		{
																			if (view != null && view.sideframe !=null && view.sideframe.isVisible()) 
																				view.updateText("The Suit of Armour Has Been Destroyed");
																			sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Suit of Armour"
																					+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));
																		}
																	}
																}
																else
																	model.damagedArmour.add("Suit of Armour");
															}
														}
														atkStr += atkSharp;

														//If you died, tell all players on the network.
														if (model.getUserClassVulnerability().get(model.userClassData.get(0)) <= atkStr)
														{
															//Send your gold to attacking player
															for (String inv: model.inactiveInventoryData.get(model.userClassData.get(0)))
															{
																if (inv.contains("gold"))
																{
																	String victoryGold = inv;
																	String victoryGoldDelims = "[ ]+";
																	String[] victoryGoldTokens = victoryGold.split(victoryGoldDelims);
																	sendMessage(new ChatMessage(ChatMessage.GOLD, victoryGoldTokens[0] + "," + string[6] + ",gold",model.userClassData.get(0)));
																	sendMessage(new ChatMessage(ChatMessage.INVENTORY, inv + "," + model.userClassData.get(0) +",removeInactive", model.userClassData.get(0)));

																}
															}
															sendMessage(new ChatMessage(ChatMessage.DEATH, model.userClassData.get(0) + ", died", model.userClassData.get(0)));
														}
														//if you did not die, but still got hit, wound screen pops up.
														else if (hit) {
															//ITEM SPECIAL ABILITY : REFLECTING GREASE
															if (!view.inventoryModel.contains("Reflecting Grease"))
																view.MagicRealmGUIWoundingScreen(model);
															else 
															{
																if (!(breastplate || suitofarmour || helmet))
																	view.MagicRealmGUIWoundingScreen(model);
																else 
																	view.updateText("Reflecting Grease Prevented A Wound");
															}
														}
													}
												}
											}	
										}
										else
										{
											//ITEM SPECIAL ABILITY : OINTMENT OF STEEL
											if (view != null && view.sideframe.isVisible())
												view.updateText("The Ointment of Steel Deflected The Attack");
											if (oppChitStr > 3)
											{
												sendMessage(new ChatMessage(ChatMessage.INVENTORY, "Ointment of Steel"
														+","+model.getUserClassData().get(0)+",itemdestroyed", model.getUserClassData().get(0)));
												if (view != null && view.sideframe !=null  && view.sideframe.isVisible())	
													view.updateText("The Ointment of Steel Has Been Destroyed");

											}
										}
									}
									else{
										if (view != null && view.sideframe.isVisible()) view.updateText("Heavenly Protection Aids You");
									}
								}
							}
							//Player tokens[i] sent combat actions
							if (tokens[i+1].contains("combatActions"))
							{
								if (view != null && view.sideframe !=null  && view.sideframe.isVisible()) 
									view.updateText(tokens[i] + " versus " + tokens[i-1]);
								model.userCombatActions.put(tokens[i], tokens);
							}
							//Player tokens[i] died
							if (tokens[i+1].contains("died"))
							{
								//put the players inventory and inactive inventory items on the ground.
								int inventorySize = 0;
								for(String string: model.getInventoryData().keySet())
								{
									//Searching for the correct player that died in our hashmap.
									if (string.equals(tokens[i]))
									{
										inventorySize += model.getInventoryData().get(string).length;
										Integer[] newLoc = null;
										for (String s : model.getPlayerLocationsData().keySet())
										{
											if (s.equals(string))
											{
												newLoc = model.getPlayerLocationsData().get(s);
												for (Integer[] one : model.getClearingInventoryData().keySet())
												{
													if (one[0].equals(newLoc[0]) && one[1].equals(newLoc[1]))
													{
														inventorySize += model.getClearingInventoryData().get(one).length;
														inventorySize += model.getInactiveInventoryData().get(string).length;
														String[] newInventory = new String[inventorySize];
														for (int j = 0; j < model.getInventoryData().get(string).length; j++) {
															newInventory[j]=model.getInventoryData().get(string)[j];
														}
														for (int j = 0; j < model.getInactiveInventoryData().get(string).length; j++) {
															if (model.getInventoryData().get(string)[j] != null);
															newInventory[j+model.getInventoryData().get(string).length]=model.getInactiveInventoryData().get(string)[j];
														}

														for (int j = model.getInventoryData().get(string).length + model.getInactiveInventoryData().get(string).length; j < inventorySize; j++) {
															if (model.getClearingInventoryData().get(one)[j-model.getInventoryData().get(string).length - model.getInactiveInventoryData().get(string).length] != null);
															newInventory[j]=model.getClearingInventoryData().get(one)[j-model.getInventoryData().get(string).length - model.getInactiveInventoryData().get(string).length];
														}
														model.getClearingInventoryData().put(one, newInventory);
													}
												}
											}	
										}
									}	
								}
								//If you die, then the game closes automatically.
								if(tokens[i].equals(view.selectedChar.getText())){

									model.event.setText("Day " + model.day +": Midnight");	
									model.followers.clear();
									model.cavesFound.clear();
									view.siteChit.setText("");
									view.soundChit.setText("");
									view.MagicRealmGUIGameOver();
									sendMessage(new ChatMessage(ChatMessage.LOGOUT, model.userClassData.get(0)+",logout", model.userClassData.get(0)));
									view.sendChatMessage("is dead");
								}
							}
							//All player's actions received
							if (tokens[i+1].equals("movesreceived") &&  view != null && !view.joinframe.isVisible() ){

								//Phase and day handling
								switch (model.phase) {
								case 0:
									model.event.setText("Day " + model.day +": Daylight");
									model.phase=1;
									break;
								case 1:
									model.event.setText("Day " + model.day +": Sunset");		
									model.phase=2;
									view.ok.setEnabled(false);
									view.hide.setEnabled(false);
									view.search.setEnabled(false);
									view.rest.setEnabled(false);
									view.trade.setEnabled(false);
									view.alert.setEnabled(false);
									view.follow.setEnabled(false);
									view.inventoryList.setEnabled(false);
									view.inactiveInventoryList.setEnabled(false);
									view.woundedInventoryList.setEnabled(false);
									view.fatiguedInventoryList.setEnabled(false);
									view.MagicRealmGUICombatScreen(model);
									break;
								case 2:
									model.event.setText("Day " + model.day +": Midnight");		
									model.phase=3;
									for (String player: model.userVictoryConditions.keySet())
									{	

										int points = 0;

										if (model.userVictoryConditions.get(player)[0] < model.userVictoryPoints.get(player)[0])
										{
											points += model.userVictoryConditions.get(player)[0];
										}


										if (model.userVictoryConditions.get(player)[1] < model.userVictoryPoints.get(player)[1])
										{
											points += (int)(model.userVictoryConditions.get(player)[1]/2);
										}


										if (model.userVictoryConditions.get(player)[2] < model.userVictoryPoints.get(player)[2])
										{
											points += (int)(model.userVictoryConditions.get(player)[2]/10);
										}


										if (model.userVictoryConditions.get(player)[3] < model.userVictoryPoints.get(player)[3])
										{
											points += (int)(model.userVictoryConditions.get(player)[3]/20);
										}


										if (model.userVictoryConditions.get(player)[4] < model.userVictoryPoints.get(player)[4])
										{
											points += (int)(model.userVictoryConditions.get(player)[4]/30);

										}


										if (model.userVictoryConditions.get(player)[5] < model.userVictoryPoints.get(player)[5])
										{
											points += model.userVictoryConditions.get(player)[5];
										}
										//	System.out.println("Points : "+points);
										if (points == 5)
										{
											if (view != null && view.sideframe.isVisible()) 
												view.updateText(player + " Won!");

											//VICTORY SCREEN
											for (String x : model.getUserClassData()) {
												if (model.userVictoryConditions.get(x) != null)
													for (Integer b : model.userVictoryConditions.get(x)) {
														if (view != null && view.sideframe.isVisible()) view.updateText(x + ": " + b);
													}	
											}	
											view.ok.setEnabled(false);
											view.hide.setEnabled(false);
											view.search.setEnabled(false);
											view.rest.setEnabled(false);
											view.trade.setEnabled(false);
											view.alert.setEnabled(false);
											view.follow.setEnabled(false);
											view.inventoryList.setEnabled(false);
											view.inactiveInventoryList.setEnabled(false);
											view.woundedInventoryList.setEnabled(false);
											view.fatiguedInventoryList.setEnabled(false);
										}
									}
									if(model.day==28){
										//VICTORY SCREEN
										if (view != null && view.sideframe.isVisible()) 
											view.updateText("Game Tied!");
										for (String x : model.getUserClassData()) {
											for (Integer b : model.getUserVictoryPoints().get(x)) {
												if (view != null && view.sideframe.isVisible())view.updateText(x + ": " + b);
											}	
										}	
										view.ok.setEnabled(false);
										view.hide.setEnabled(false);
										view.search.setEnabled(false);
										view.rest.setEnabled(false);
										view.trade.setEnabled(false);
										view.alert.setEnabled(false);
										view.follow.setEnabled(false);
										view.inventoryList.setEnabled(false);
										view.inactiveInventoryList.setEnabled(false);
										view.woundedInventoryList.setEnabled(false);
										view.fatiguedInventoryList.setEnabled(false);
									}
									model.day++;
									break;
								case 3:
									model.event.setText("Day " + model.day +": Birdsong");		
									model.phase=0;
									break;

								default:
									break;
								}

								if (!(model.phase==3))
								{
									view.ok.setEnabled(true);
									view.rest.setEnabled(true);
									view.hide.setEnabled(true);
									view.search.setEnabled(true);
									view.trade.setEnabled(true);
									view.alert.setEnabled(true);
									view.follow.setEnabled(true);

									for (Integer[] nativeLoc : model.nativeLocation.values()){
										if (nativeLoc[0].equals(model.getPlayerLocationsData().get(model.userClassData.get(0))[0])
												&& nativeLoc[1].equals(model.getPlayerLocationsData().get(model.userClassData.get(0))[1]))
										{
											view.mingle.setEnabled(true);
											break;
										}
									}
									view.inventoryList.setEnabled(true);
									view.inactiveInventoryList.setEnabled(true);
									view.woundedInventoryList.setEnabled(true);
									view.fatiguedInventoryList.setEnabled(true);
								}
								if (view != null && view.sideframe.isVisible()) view.updateText("All Moves Received");
								view.updateMap(model);	
								if(model.phase != 3&&model.phase != 2){
									System.out.println("GENERATING SHIZN???IT");
									//GENERATE NEW MONSTERS FOR PLAYER
									//this simulates aspects of the monster roll
									model.generatedMonsters.clear();
									//DRUID SPECIAL ABILITY: PEACE WITH NATURE
									boolean hasPlayersInClearing = false;
									Integer[] playerLoc = model.playerLocationsData.get(model.userClassData.get(0));
									for (String otherplayer : model.playerLocationsData.keySet())
										if (model.playerLocationsData.get(otherplayer)[0].equals(playerLoc[0]) 
												&& model.playerLocationsData.get(otherplayer)[1].equals(playerLoc[1])
												&& !model.userClassData.get(0).equals(otherplayer))
										{
											hasPlayersInClearing = true;
											break;
										}
									if (!model.userClassData.get(0).equals("Druid")
											|| (model.userClassData.get(0).equals("Druid") && hasPlayersInClearing))
									{
										for (String monster : model.soundChitData.get(view.soundChit.getText()))
										{
											model.generatedMonsters.add(monster);

										}  	
										//Determine if the player is at a site chit,
										//put that monster in combat list...
										String site = view.siteChit.getText();
										String playerType = model.userClassData.get(0);

										//DWARF SPECIAL ABILITY:CAVE KNOWLEDGE 
										//and BLACK KNIGHT SPECIAL ABILITY: FEAR 
										//and WOODS GIRL SPECIAL ABILITY: TRACKING SKILLS
										//ITEM SPECIAL ABILITY : LUCKY CHARM
										Dice die = new Dice();
										int x,z;
										if ((model.userClassData.get(0).equals("Black Knight")) 
												|| (model.userClassData.get(0).equals("Dwarf") && view.inCave) 
												|| view.inventoryModel.contains("Lucky Charm")
												|| (model.userClassData.get(0).equals("Woods Girl") && view.soundChit.getText() != null 
												&& view.soundChit.getText()!= "" && view.soundChit.getText().contains("(W)")))
										{
											if (model.userClassData.get(0).equals("Black Knight"))
												if (view != null && view.sideframe.isVisible())view.updateText("Fear Aids You");

											if ((model.userClassData.get(0).equals("Woods Girl") && view.soundChit.getText() != null 
													&& view.soundChit.getText()!= "" && view.soundChit.getText().contains("(W)")))
												if (view != null && view.sideframe.isVisible())view.updateText("Tracking Skills Aid You");

											if (model.userClassData.get(0).equals("Dwarf") && view.inCave)
												if (view != null && view.sideframe.isVisible())view.updateText("Cave Knowledge Aids You");
											if (view.inventoryModel.contains("Lucky Charm"))
												if (view != null && view.sideframe.isVisible())view.updateText("The Lucky Charm Aids You");

											x = die.roll();
											if (model.cheatMode)
											{
												Object[] diceSheet = {"1", "2", "3", "4", "5", "6"};
												String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
														"Select the die value to be used for the monster roll",
														"Cheat Mode: Monster Roll",
														JOptionPane.INFORMATION_MESSAGE,
														null,
														diceSheet,
														1);
												if(temp!=null){
													x = Integer.parseInt(temp);
												}else{
													x = die.roll();
												}
											}


										}
										else {
											z = die.roll();
											x = die.roll();
											if(i>x)
												x = z;
										}
										//WHITE KNIGHT SPECIAL ABILITY : HONOR
										if (model.userClassData.get(0).equals("White Knight"))
										{
											x--;
											if (view != null && view.sideframe.isVisible())view.updateText("Your Honor Aids You");
										}
										if (x<0)
											x=0;
										if (model.cheatMode)
										{
											Object[] meetingSheet = {"1", "2", "3", "4", "5", "6"};
											String temp = (String) JOptionPane.showInputDialog(view.sideframe, 
													"Select the die value to be used for the monster roll",
													"Cheat Mode: Monster Roll",
													JOptionPane.INFORMATION_MESSAGE,
													null,
													meetingSheet,
													1);
											if(temp!=null){
												x = Integer.parseInt(temp);
											}else{
												x = die.roll();
											}
										}
										if (view.soundChit.getText().equals("Dank (V)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Patrol") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Patrol")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Patrol")[1])
												&& playerType.equals("Swordsman"))
										{
											if (x==3){
												for (String natives: model.nativeGroup.get("Patrol"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Dank (V)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Lancers") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Lancers")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Lancers")[1])
												&& (playerType.equals("Elf") || playerType.equals("Wizard")))
										{
											if (x==4){
												for (String natives: model.nativeGroup.get("Lancers"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Ruins (V)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Patrol") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Patrol")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Patrol")[1])
												&& playerType.equals("Swordsman"))
										{
											if (x==3){
												for (String natives: model.nativeGroup.get("Patrol"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Smoke (W)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Woodfolk") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Woodfolk")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Woodfolk")[1])
												&& playerType.equals("Druid"))
										{
											if (x==2){
												for (String natives: model.nativeGroup.get("Woodfolk"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Smoke (W)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Lancers") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Lancers")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Lancers")[1])
												&& (playerType.equals("Elf") || playerType.equals("Wizard")))
										{
											if (x==4){
												for (String natives: model.nativeGroup.get("Lancers"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Smoke (V)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Woodfolk") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Woodfolk")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Woodfolk")[1])
												&& playerType.equals("Druid"))
										{
											if (x==2){
												for (String natives: model.nativeGroup.get("Woodfolk"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Smoke (V)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Patrol") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Patrol")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Patrol")[1])
												&& playerType.equals("Swordsman"))
										{
											if (x==3){
												for (String natives: model.nativeGroup.get("Patrol"))
													model.generatedMonsters.add(natives);

											}
										}
										if (view.soundChit.getText().equals("Stink (V)") 
												&& playerLoc != null
												&& playerType != null
												&& model.nativeLocation.get("Company") != null
												&& playerLoc[0].equals(model.nativeLocation.get("Company")[0]) 
												&& playerLoc[1].equals(model.nativeLocation.get("Company")[1])
												&& playerType.equals("White Knight"))
										{
											if (x==3){
												for (String natives: model.nativeGroup.get("Company"))
													model.generatedMonsters.add(natives);

											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Bones (M)"))
										{
											if (x==4)
												model.generatedMonsters.add("Giant");
											if (x==6)
												model.generatedMonsters.add("Giant Bat");
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Bones (C)"))
										{
											if (x==4){

												model.generatedMonsters.add("Troll T");
												model.generatedMonsters.add("Troll H");

											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Dank (M)"))
										{
											if (x==5){
												model.generatedMonsters.add("Spider H");
												model.generatedMonsters.add("Spider T");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Dank (C)"))
										{
											if (x==2){
												model.generatedMonsters.add("Serpent H");
												model.generatedMonsters.add("Serpent T");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Ruins (M)"))
										{
											if (x==6){
												model.generatedMonsters.add("Giant Bat");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Ruins (C)"))
										{
											if (x==3){
												model.generatedMonsters.add("Goblin Axe");
												model.generatedMonsters.add("Goblin Sword");
												model.generatedMonsters.add("Goblin Spear");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Stink (M)"))
										{
											if (x==4){
												model.generatedMonsters.add("Giant");
											}
											if (x==5){
												model.generatedMonsters.add("Spider H");
												model.generatedMonsters.add("Spider T");

											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Stink (C)"))
										{
											if (x==4){
												model.generatedMonsters.add("Troll H");
												model.generatedMonsters.add("Troll T");

											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && (view.soundChit.getText().equals("Flutter (M)") || view.soundChit.getText().equals("Flutter (C)")))
										{
											if (x==1){
												model.generatedMonsters.add("Flying Dragon H");
												model.generatedMonsters.add("Flying Dragon T");

											}
											if (x==6){
												model.generatedMonsters.add("Giant Bat");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Howl (M)"))
										{
											if (x==6){
												model.generatedMonsters.add("Giant Bat");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Howl (C)"))
										{
											if (x==3){
												model.generatedMonsters.add("Goblin Axe");
												model.generatedMonsters.add("Goblin Sword");
												model.generatedMonsters.add("Goblin Spear");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Patter (M)"))
										{
											if (x==5){
												model.generatedMonsters.add("Spider H");
												model.generatedMonsters.add("Spider T");

											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Patter (C)"))
										{
											if (x==3){
												model.generatedMonsters.add("Goblin Axe");
												model.generatedMonsters.add("Goblin Sword");
												model.generatedMonsters.add("Goblin Spear");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Roar (M)"))
										{
											if (x==3){
												model.generatedMonsters.add("Giant");
											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && view.soundChit.getText().equals("Roar (C)"))
										{
											if (x==1){
												model.generatedMonsters.add("Dragon H");
												model.generatedMonsters.add("Dragon T");
											}
											if (x==4){
												model.generatedMonsters.add("Troll H");
												model.generatedMonsters.add("Troll T");

											}
										}
										if ((site.equals("3 - Lost City") || site.equals("1 - Lost Castle")) && (view.soundChit.getText().equals("Slither (M)") || view.soundChit.getText().equals("Slither (C)")))
										{
											if (x==1){
												model.generatedMonsters.add("Dragon H");
												model.generatedMonsters.add("Dragon T");

											}
											if (x==2){
												model.generatedMonsters.add("Serpent H");
												model.generatedMonsters.add("Serpent T");

											}
										}
										if (site.equals("1 - Altar"))
										{
											if (x==3){
												model.generatedMonsters.add("Ogre");
											}
										}
										if (site.equals("5 - Cairns"))
										{
											if (x==5){
												model.generatedMonsters.add("Spider H");
												model.generatedMonsters.add("Spider T");
											}
										}
										if (site.equals("6 - Hoard"))
										{
											if (x==1){
												model.generatedMonsters.add("Flying Dragon H");
												model.generatedMonsters.add("Flying Dragon T");
											}
										}
										if (site.equals("3 - Lair"))
										{
											if (x==1){
												model.generatedMonsters.add("Dragon H");
												model.generatedMonsters.add("Dragon T");
											}
										}
										if (site.equals("6 - Pool"))
										{
											if (x==3){
												model.generatedMonsters.add("Octopus");
											}
										}
										if (site.equals("4 - Shrine"))
										{
											if (x==2){
												model.generatedMonsters.add("Winged Demon");
											}
										}
										if (site.equals("2 - Statue"))
										{
											if (x==5){
												model.generatedMonsters.add("Imp");
											}
										}
										if (site.equals("3 - Vault"))
										{
											if (x==4){
												model.generatedMonsters.add("Troll H");
												model.generatedMonsters.add("Troll T");
											}
										}
									}
									else if (model.userClassData.get(0).equals("Druid") && !hasPlayersInClearing)
									{
										if (view != null && view.sideframe.isVisible()) view.updateText("You Feel At Peace With Nature");
									}
								}
							}

							//One user sent in their actions
							if (tokens[i+1].contains("actions") && !view.joinframe.isVisible()) {
								String userNameCopy = tokens[i];
								String username = tokens[i];
								String[] temp = new String[tokens.length-2];
								for(int j = 0; j<tokens.length-2; j++)
								{
									temp[j]=tokens[j];
								}

								for (String s : tokens)
								{
									if (s.contains("Move"))
									{
										//Set Hidden back to Normal and Alert back to false
										model.getUserStatusData().put(userNameCopy, "Normal");
										model.userAlertData.put(userNameCopy, false);
										//change user location data.
										String delims2 = "[ ]+";
										String[] tokens1 = s.split(delims2);
										Integer[] tokens2 = new Integer[2];

										for (int t = 1; t<tokens1.length; t++)
										{
											tokens2[t-1] = Integer.parseInt(tokens1[t]);
										}
										model.getPlayerLocationsData().put(userNameCopy, tokens2);
									}

									if (s.contains("Hide"))
									{
										model.getUserStatusData().put(username, "Hidden");
									}
									if (s.contains("Alert"))
									{
										model.userAlertData.put(userNameCopy, true);
									}
								}

								if (view != null && view.sideframe!=null && view.sideframe.isVisible() && username != null) 
									view.updateText(username + " Has Played");
							}	
							if (tokens[i+1].contains("disconnected"))
							{
								if (model.getUserClassData().contains(tokens[i]))
								{
									model.getUserClassData().remove(tokens[i]);
									view.userModel.removeElement(tokens[i]);
									if (view != null 
											&& view.selectedLoc != null
											&& view.selectedLoc.getText() != null 
											&& view.selectedLoc.getText() != "")
									{
										view.updateMap(model);	
										if (view != null && view.sideframe.isVisible()) view.updateText(tokens[i] + " Has Disconnected");
									}
								}
							}
						}


					}
				}
				catch(IOException e) {
					display("Server has close the connection: " + e);
					if(view != null) {
						if (view != null && view.sideframe.isVisible())view.updateText("Connection To The Server Has Failed");
					}

					break;
				}
				// can't happen with a String object but need the catch anyhow
				catch(ClassNotFoundException e2) {
				}
			}
		}
	}
}
