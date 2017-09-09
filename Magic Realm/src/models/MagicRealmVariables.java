package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JLabel;

public class MagicRealmVariables implements Serializable {
	protected static final long serialVersionUID = 1112122330L;
	public boolean cheatMode = false;
	public int follow = 0;
	public int start = 0;
	public int play = 0;
	public int chat = 0;
	public int addGold = 0;
	public int itemDestroyed = 0;
	public int nativeTrade = 0;
	public int waitingMove = 0;
	public int waitingCombatMove = 0;
	public int takeTWT = 0;
	public int search = 0;
	public int tradeSearch = 0;
	public int tradeAccept = 0;
	public int tradeOffer = 0;
	public int move = 0;
	public int dead = 0;
	public int moveActiveToInactive = 0;
	public int moveInactiveToActive = 0;
	public int locationEntered = 0;
	public int removeActive = 0;
	public int addActive = 0;
	public int addInactive = 0;
	public int removeInactive = 0;
	public int addFatigued = 0;
	public int removeFatigued = 0;
	public int addWounded = 0;
	public int removeWounded = 0;
	public String itemFound = "";
	public ArrayList<String> userClassData = new ArrayList<String>();
	public ArrayList<String> damagedArmour = new ArrayList<String>();
	public ArrayList<String> generatedMonsters = new ArrayList<String>();
	public HashMap<String, String> userStatusData = new HashMap<String, String>();
	public HashMap<String, Boolean> userAlertData = new HashMap<String, Boolean>();
	public HashMap<String, Integer[]> userVictoryPoints = new HashMap<String, Integer[]>();
	public HashMap<String, Integer> userClassVulnerability = new HashMap<String, Integer>();
	public HashMap<String, String[]> inventoryData = new HashMap<String, String[]>();
	public HashMap<String, String[]> inactiveInventoryData = new HashMap<String, String[]>();
	public HashMap<String, String[]> woundedInventoryData = new HashMap<String, String[]>();
	public HashMap<String, String[]> fatiguedInventoryData = new HashMap<String, String[]>();
	public HashMap<String, String[]> playerMoves = new HashMap<String, String[]>();
	public HashMap<Integer[], String[]> clearingInventoryData = new HashMap<Integer[], String[]>();
	public HashMap<Integer[], String> monsterLocations = new HashMap<Integer[], String>();
	public HashMap<String, Integer[]> playerLocationsData = new HashMap<String, Integer[]>();
	public HashMap<String, Integer[]> dwellingLocationsData = new HashMap<String, Integer[]>();
	public HashMap<Integer[], Integer[][]> adjacentClearings = new HashMap<Integer[], Integer[][]>();
	public HashMap<String, String[]> userCombatActions = new HashMap<String, String[]>();
	public HashMap<String, Integer[]> weaponData = new HashMap<String, Integer[]>();
	public HashMap<String, Integer[]> armourData = new HashMap<String, Integer[]>();
	public HashMap<String, Integer[]> monsterData = new HashMap<String, Integer[]>();
	public HashMap<String, String[]> startingLocations = new HashMap<String, String[]>();
	public HashMap<String, String[]> tradeAccepts = new HashMap<String, String[]>();
	public HashMap<String, Integer[]> userVictoryConditions = new HashMap<String, Integer[]>();
	public HashMap<String, String[]> followers = new HashMap<String, String[]>();
	public HashMap<String, Integer> itemPrice = new HashMap<String, Integer>();

	public HashMap<String, String[]> siteChitData = new HashMap<String, String[]>(); // chit
																						// name
																						// and
																						// corresponding
																						// treasures
	public HashMap<String, String[]> soundChitData = new HashMap<String, String[]>(); // chit
																						// name
																						// and
																						// corresponding
																						// monsters
	public ArrayList<String> siteChits = new ArrayList<String>(); // arraylist
																	// of chits
	public ArrayList<String> soundChits = new ArrayList<String>(); // arraylist
																	// of chits
	public HashMap<String, Integer[]> siteChitLocation = new HashMap<String, Integer[]>(); // stores
																							// location
																							// of
																							// each
																							// chit,
																							// assigned
																							// randomly
																							// each
																							// session
	public HashMap<Integer[], String> rSiteChitLocation = new HashMap<Integer[], String>(); // reverse
																							// map
																							// for
																							// when
																							// we
																							// have
																							// coordinates
																							// and
																							// need
																							// to
																							// find
																							// the
																							// treasure
	public HashMap<String, Integer[]> soundChitLocation = new HashMap<String, Integer[]>(); // stores
																							// location
																							// of
																							// each
																							// chit,
																							// assigned
																							// randomly
																							// each
																							// session
	public HashMap<Integer[], String> rSoundChitLocation = new HashMap<Integer[], String>(); // reverse
																								// map
																								// for
																								// when
																								// we
																								// have
																								// coordinates
																								// and
																								// need
																								// to
																								// find
																								// the
																								// treasure
	public HashMap<String, ArrayList<Integer>> playerSitesFound = new HashMap<String, ArrayList<Integer>>(); // stores
																												// data
																												// on
																												// players
																												// and
																												// their
																												// discovery
																												// of
																												// chits

	public HashMap<String, ArrayList<Integer[]>> soundChitNeighborhood = new HashMap<String, ArrayList<Integer[]>>();
	public HashMap<String, ArrayList<Integer[]>> siteChitNeighborhood = new HashMap<String, ArrayList<Integer[]>>();
	public HashMap<String, String[]> treasure = new HashMap<String, String[]>();
	public ArrayList<Integer[]> cavesFound = new ArrayList<Integer[]>();
	public ArrayList<Integer[]> secretPathsFound = new ArrayList<Integer[]>();

	public ArrayList<Integer[]> mountainClearings = new ArrayList<Integer[]>();
	public ArrayList<Integer[]> cavesEntrances = new ArrayList<Integer[]>();
	public ArrayList<Integer[]> caves = new ArrayList<Integer[]>();
	public ArrayList<Integer[]> secretPathsEntrances = new ArrayList<Integer[]>();
	public ArrayList<Integer[]> secretPaths = new ArrayList<Integer[]>();

	public HashMap<String, Integer[]> nativeLocation = new HashMap<String, Integer[]>(); // stores
																							// location
																							// of
																							// each
																							// native
	public HashMap<String, Integer> nativePrice = new HashMap<String, Integer>(); // stores
																					// location
																					// of
																					// each
																					// native
	public HashMap<String, String[]> nativeGroup = new HashMap<String, String[]>(); // stores
																					// location
																					// of
																					// each
																					// native
	public HashMap<String, Integer[]> nativeData = new HashMap<String, Integer[]>(); // stores
																						// location
																						// of
																						// each
																						// native

	public ArrayList<String> nativesFollowing = new ArrayList<String>(); // stores
																			// location
																			// of
																			// each
																			// native

	public HashMap<String, String> treasureProp = new HashMap<String, String>();

	public ArrayList<String> TWT = new ArrayList<String>();
	public ArrayList<String> largeTreasures = new ArrayList<String>();
	public ArrayList<String> smallTreasures = new ArrayList<String>();

	public ArrayList<String> largeTWT = new ArrayList<String>();
	public HashMap<String, String[]> TWTdata = new HashMap<String, String[]>();

	public ArrayList<String> updatedPlayers = new ArrayList<String>();
	public JLabel event;
	public int phase;
	public int day;

	public MagicRealmVariables() {

		event = new JLabel("Day 1: Birdsong");
		phase = 1;
		day = 1;

		// placing natives at correct dwelling....
		nativeLocation.put("Patrol", new Integer[] { 730, 1867 }); // Chapel
		nativeLocation.put("Lancers", new Integer[] { 730, 1867 }); // Chapel
		nativeLocation.put("Patrol", new Integer[] { 1627, 1657 }); // Guard
		nativeLocation.put("Patrol", new Integer[] { 1481, 1999 }); // House
		nativeLocation.put("Woodfolk", new Integer[] { 1481, 1999 }); // House
		nativeLocation.put("Patrol", new Integer[] { 1106, 796 }); // Inn
		nativeLocation.put("Company", new Integer[] { 1106, 796 }); // Inn

		nativePrice.put("Bashkars", 12);
		nativePrice.put("Company", 16);
		nativePrice.put("Guard", 12);
		nativePrice.put("Lancers", 8);
		nativePrice.put("Order", 1);
		nativePrice.put("Patrol", 6);
		nativePrice.put("Rogues", 1);
		nativePrice.put("Soldiers", 10);
		nativePrice.put("Woodfolk", 6);

		nativeGroup.put("Bashkars", new String[] { "BHQ", "B1", "B2", "B3",
				"B4", "B5" });
		nativeGroup.put("Company", new String[] { "CHQ", "C1", "C2", "C3",
				"C4", "C5", "C6" });
		nativeGroup.put("Guard", new String[] { "GHQ", "G1", "G2" });
		nativeGroup.put("Lancers", new String[] { "LHQ", "L1", "L2", "L3" });
		nativeGroup.put("Order", new String[] { "OHQ", "O1", "O2", "O3" });
		nativeGroup.put("Patrol", new String[] { "PHQ", "P1", "P2" });
		nativeGroup.put("Rogues", new String[] { "RHQ", "R1", "R2", "R3", "R4",
				"R5", "R6", "R7" });
		nativeGroup.put("Soldiers", new String[] { "SHQ", "S1", "S2", "S3" });
		nativeGroup.put("Woodfolk", new String[] { "WHQ", "W1", "W2" });

		/*
		 * NATIVE DATA
		 * 
		 * 0 int vulnerability 1 int notoriety; 2 int goldValue; 3 int alert 4
		 * int attackDamage //1=light 2=medium 3=heavy 4=tremendous 5 int
		 * moveSpeed 6 int attackSpeed 7 int sharpness 8 int alertAttackDamage;
		 * //attackDamage when alerted 9 int alertMovementSpeed; //attackSpeed
		 * when alerted 10 int alertAttackSpeed; 11 int alertWeaponSharp;
		 * //sharpness when alerted
		 */

		// Refer to 3rd edition list and Tables document pg. 9-10

		// Bashkars (group of Raiders)
		nativeData.put("BHQ", new Integer[] { 1, 4, 2, 0, 1, 2, 4, 1, 2, 3, 4,
				1 });
		nativeData.put("B1",
				new Integer[] { 1, 4, 2, 0, 2, 3, 3, 1, 1, 3, 3, 1 });
		nativeData.put("B2",
				new Integer[] { 1, 4, 2, 0, 2, 2, 4, 1, 1, 3, 3, 1 });
		nativeData.put("B3",
				new Integer[] { 1, 4, 2, 0, 1, 2, 4, 1, 2, 4, 3, 1 });
		nativeData.put("B4",
				new Integer[] { 1, 4, 2, 0, 2, 2, 5, 1, 1, 4, 2, 1 });
		nativeData.put("B5",
				new Integer[] { 1, 4, 2, 0, 1, 4, 2, 1, 1, 4, 2, 1 });
		// Company (group of short swordsmen, pikemen, a Great Swordsman, and a
		// Crossbowman)
		// Short Swordsmen
		nativeData.put("CHQ", new Integer[] { 2, 3, 2, 0, 2, 5, 3, 1, 1, 3, 4,
				1 });
		nativeData.put("C1",
				new Integer[] { 2, 3, 2, 0, 1, 4, 3, 1, 2, 3, 4, 1 });
		// Pikemen
		nativeData.put("C2",
				new Integer[] { 2, 3, 2, 0, 3, 4, 6, 1, 2, 5, 4, 1 });
		nativeData.put("C3",
				new Integer[] { 2, 3, 2, 0, 3, 4, 6, 1, 2, 5, 4, 1 });
		nativeData.put("C4",
				new Integer[] { 2, 3, 2, 0, 3, 4, 6, 1, 2, 5, 4, 1 });
		// Great Swordsman
		nativeData.put("C5",
				new Integer[] { 3, 6, 4, 0, 4, 6, 4, 1, 3, 5, 5, 1 });
		// Crossbowman
		nativeData.put("C6",
				new Integer[] { 2, 4, 2, 0, 3, 5, 6, 0, 3, 4, 1, 0 });
		// Guard (group of Great Swordsmen)
		nativeData.put("GHQ", new Integer[] { 3, 6, 4, 0, 3, 5, 5, 1, 4, 6, 4,
				1 });
		nativeData.put("G1",
				new Integer[] { 3, 6, 4, 0, 3, 5, 5, 1, 4, 6, 4, 1 });
		nativeData.put("G2",
				new Integer[] { 3, 6, 4, 0, 3, 5, 5, 1, 4, 6, 4, 1 });
		// Lancers (group of Lancers)
		nativeData.put("LHQ", new Integer[] { 1, 4, 2, 0, 3, 3, 5, 1, 2, 4, 3,
				1 });
		nativeData.put("L1",
				new Integer[] { 1, 4, 2, 0, 3, 2, 6, 1, 2, 4, 3, 1 });
		nativeData.put("L2",
				new Integer[] { 1, 4, 2, 0, 2, 3, 4, 1, 3, 4, 4, 1 });
		nativeData.put("L3",
				new Integer[] { 1, 4, 2, 0, 3, 5, 4, 1, 2, 4, 4, 1 });
		// Order (group of Knights)
		nativeData.put("OHQ", new Integer[] { 4, 12, 8, 0, 3, 4, 6, 1, 4, 6, 4,
				1 });
		nativeData.put("O1", new Integer[] { 4, 12, 8, 0, 3, 6, 4, 1, 4, 5, 5,
				1 });
		nativeData.put("O2", new Integer[] { 4, 12, 8, 0, 3, 5, 5, 1, 4, 6, 5,
				1 });
		nativeData.put("O3", new Integer[] { 4, 12, 8, 0, 3, 6, 4, 1, 4, 6, 5,
				1 });
		// Patrol (group of Short Swordsmen)
		nativeData.put("PHQ", new Integer[] { 2, 3, 2, 0, 2, 3, 4, 1, 1, 5, 2,
				1 });
		nativeData.put("P1",
				new Integer[] { 2, 3, 2, 0, 3, 3, 4, 1, 2, 4, 4, 1 });
		nativeData.put("P2",
				new Integer[] { 2, 3, 2, 0, 2, 3, 5, 1, 1, 3, 4, 1 });
		// Rogues (group of Assassins, Great Axemen, Swordsmen, a Short
		// Swordsman, and an Archer)
		// Assassin
		nativeData.put("RHQ", new Integer[] { 2, 2, 1, 0, 2, 4, 3, 1, 3, 2, 4,
				1 });
		// Great Axemen
		nativeData.put("R1",
				new Integer[] { 3, 6, 4, 0, 3, 4, 5, 1, 4, 5, 3, 1 });
		nativeData.put("R2",
				new Integer[] { 3, 6, 4, 0, 3, 4, 5, 1, 4, 5, 3, 1 });
		// Short Swordsman
		nativeData.put("R3",
				new Integer[] { 2, 3, 2, 0, 2, 3, 5, 1, 1, 4, 3, 1 });
		// Archer
		nativeData.put("R4",
				new Integer[] { 2, 4, 2, 0, 0, 2, 0, 0, 1, 4, 1, 2 });
		// The other Assassin
		nativeData.put("R5",
				new Integer[] { 2, 2, 1, 0, 2, 4, 3, 1, 3, 2, 4, 1 });
		// Swordsmen
		nativeData.put("R6",
				new Integer[] { 2, 2, 1, 0, 2, 5, 3, 1, 1, 4, 3, 1 });
		nativeData.put("R7",
				new Integer[] { 2, 2, 1, 0, 2, 5, 3, 1, 1, 4, 3, 1 });
		// Soldiers (group of Pikemen, a Great Swordsman, and a Crossbowman)
		// Great Swordsman
		nativeData.put("SHQ", new Integer[] { 3, 6, 4, 0, 4, 6, 4, 1, 3, 5, 5,
				1 });
		// Pikemen
		nativeData.put("S1",
				new Integer[] { 2, 3, 2, 0, 3, 4, 6, 1, 2, 5, 4, 1 });
		nativeData.put("S2",
				new Integer[] { 2, 3, 2, 0, 3, 4, 6, 1, 2, 5, 4, 1 });
		// Crossbowman
		nativeData.put("S3",
				new Integer[] { 2, 4, 2, 0, 3, 5, 6, 0, 3, 4, 1, 0 });
		// Woodfolk (group of Archers)
		// NEED HELP WITH NORMAL ATTACK CHIT
		nativeData.put("WHQ", new Integer[] { 2, 4, 2, 0, 0, 2, 0, 0, 2, 4, 1,
				2 }); // Uses Medium Bow
		nativeData.put("W1",
				new Integer[] { 2, 4, 2, 0, 0, 2, 0, 0, 1, 4, 1, 2 });
		nativeData.put("W2",
				new Integer[] { 2, 4, 2, 0, 0, 2, 0, 0, 1, 4, 1, 2 });

		treasureProp
				.put("7-League Boots",
						"This card allows its owner to record an extra phase to do the Move activity, even if he is riding a horse.");
		treasureProp
				.put("Alchemist's Mixture",
						" This Potion represents pinches of deadlypowder that are thrown at a target. It is a missile weapon, length 11, weightNegligible, impact Medium, with three sharpness stars. Once activated, it canattack one target per round of combat until Midnight, when it expires andreturns to the Warlock. It is not limited to the character who activates it can be transferred while active. ");
		treasureProp
				.put("Amulet",
						"This card can cure one character in its clearing of one Curse,or it can break (or partially break) one Spell whose spellcaster or target is inhis clearing. The card's owner chooses exactly which Curse or Spell is canceled. At Midnight it returns to the Shaman's box.");
		treasureProp
				.put("Ancient Telescope",
						"This card gives its owner an extra phase tha they can use to do the enhanced Peer activity, peering from one mountain clearing to any other mountain clearing on the board. When he records the activity he must record the mountain clearing he will search. When he does the activity he must be in a mountain clearing or the activity is canceled.");
		treasureProp
				.put("Battle Bracelets",
						" If he is attacking a target whose maneuver time is 5 or more, he can shift his attack to any Attack circle he chooses. If his target’s maneuver time is 4 or less, he cannot shift his attack. Note: If he is attacking multiple targets, he can shift his attack only if every target has a maneuver time of 5 or more.");
		treasureProp
				.put("Beast Pipe",
						" This card contains one Type VIII Spell card. It can be used as a MAGlC Vlll chit to cast this spell.");
		treasureProp
				.put("Bejeweled Dwarf Vest",
						"When active, this card intercepts Thrust attacks. It is destroyed when Heavy (or greater) harm is inflicted on it. When  it is destroyed, its owner gets 23 GOLD points to represent the value of its jewels. ");
		treasureProp
				.put("Belt of Strength",
						" This card alters the strength of Its owner's Move, Fight, Duck and Berserk chits (his Magic chits are not affected). The asterisks on each chit define its strength: each chit with two asterisks has Tremendous strength, each chit with one asterisk has Heavy strength, and each chit with no asterisks has Medium strength. ");
		treasureProp
				.put("Black Book",
						" This card contains two Type V Spell cards and two Type VIII Spell cards. Once per day it can be used as a MAGIC V chit to cast one of the Type V spells. It cannot cast the Type VIII spells. ");
		treasureProp
				.put("Blasted Jewel",
						" This card contains one Type V Spell card. It can beused as a MAGlC V chit to cast this spell. ");
		treasureProp
				.put("Book of Lore",
						" This card contains four Type IV Spells. Once per dayit can be used as a MAGIC IV chit to cast one of them. ");
		treasureProp
				.put("Chest",
						" The only way to open the Chest is with the Lost Keys; it staysin play like any other item until it is opened and its card exchanged. A character can open the Chest only if he has the Lost Keys and the Chest active at the same time. When he opens the Chest, he immediately exchanges it for its treasures, and the Chest card is removed from play. He keeps the treasures and adds 50 points to his recorded Gold. Note: The Chest cannot be reused once it is opened.");
		treasureProp
				.put("Cloak of Mist",
						" This card allows its owner to record an extra phase to do the Hide activity. ");
		treasureProp
				.put("Cloven Hoof",
						"Whenever a character rolls on a table while he is in the same clearing with the Cloven Hoof, he adds one to his result. This Enchanted card is also a source of Black color magic to everyone in its clearing.");
		treasureProp
				.put("Withered Claw",
						"When a character activates this card, he immediately rolls for a Wish and a Curse. He uses the same die roll to find his result on both tables (so if his result is 4 he gets the I wish for peace Wish and the Ill Health Curse). At Midnight it returns to the Shaman's box.");
		treasureProp
				.put("Vial of Healing",
						"This card converts its owner’s wounds into fatigue instantaneously, at no cost. While a character has this card active, all of his wounded action chits are instantly converted into fatigued chits, and any new wounds he receives are also converted to fatigued chits. This card does not affect fatigued chits. This Potion is reusable and can be transferred while active, but it expires at Midnight and is returned to the Chapel. ");
		treasureProp
				.put("Toadstool Ring",
						"This card allows its owner to record an extra phase to do the Enchant activity.");
		treasureProp
				.put("Toadstool Circle",
						"This site card allows each character in its clearing to record an extra phase to do the Enchant activity, if he is in that clearing both when he records the phase and when he does it. This card is also an Enchanted card which supplies black color magic to everyone in its clearing, as well as being a Site card which can be looted.");
		treasureProp
				.put("Timeless Jewel",
						"The owner of this card ignores his recorded turn and chooses what to do on each phase of his turn as he does the phase. He gets the same number of phases and types of activities he is normally allowed, except that he can use an extra or enhanced activity caused by a belonging as soon as he activates that belonging, whether it was active during Birdsong or not. Exception: Only one horse per day can give him extra phases. Once he uses an extra phase caused by one horse, for the rest of that day he cannot use extra phases caused by other horses.\n1.  If he activates the Timeless Jewel during his turn, he can choose how to do the remaining phases of his turn as he does them.\n2.  If he inactivates the Timeless Jewel during his turn he is instantly blocked.");
		treasureProp
				.put("Shoes of Stealth",
						"When a character has this card active, he rolls only one die each time he uses the Hide table.");
		treasureProp
				.put("Shielded Lantern",
						"This card allows its owner to record an extra phase that he can use to record any normal activity. He must be in a cave clearing when he starts the phase or the activity is canceled.");
		treasureProp
				.put("Scroll of Nature",
						" This card contains two Type II Spell cards and two Type III Spell cards. Once per day it can be used as a 'MAGIC II' chit to cast one of the Type II spells. It cannot cast the Type III spells.");
		treasureProp
				.put("Scroll of Alchemy",
						"This card contains four Type VI Spell cards. Once per day it can be used as a 'MAGIC VI' chit to cast one of them.");
		treasureProp
				.put("Sacred Statue",
						"This card contains one Type I Spell card. It can be used as a 'MAGIC l' chit to cast this spell.");
		treasureProp.put("Sacred Grail",
				"This card is a source of White magic.");
		treasureProp
				.put("Royal Sceptre",
						"This card allows its owner to record an extra phase to do the Hire activity.");
		treasureProp
				.put("Remains of Thief",
						"When a character draws this card, he must immediately reveal it and roll for a Curse. Then he exchanges the card and keeps its treasures; he also adds 20 points to his recorded Gold.");
		treasureProp
				.put("Regent of Jewels",
						"This card allows its owner to record an extra phase to do the Trade activity. ");
		treasureProp
				.put("Reflecting Grease",
						"When a character has this card active, he cannot be wounded by any attack that strikes armor. If an attack hits him and strikes armor, he does not wound any action chits. His armor can still be damaged and destroyed normally, and attacks that hit him without striking armor can still wound and kill him. This Potion is used up by the character that activates it – it cannot be transferred while active. This card returns to the Crone at Midnight.\nIncreased effort:  Each of these cards allows its owner to play more than two effort asterisks during a round of combat. The number of asterisks he must fatigue is always one less than the number he uses. Example: If he plays three asterisks, then he must fatigue two asterisks.");
		treasureProp.put("Quick Boots", "Medium strength, time number of 3.");
		treasureProp.put("Power Gauntlets",
				"Heavy strength, time number of 4. ");
		treasureProp.put("Power Boots", " Heavy strength, time number of 4. ");
		treasureProp
				.put("Poultice of Health",
						"When a character has this card active, each time he does a Rest activity, he rests two effort asterisks instead of one. Note: When a character activates this card he uses all of the Potion on himself. The card cannot be transferred while active.");
		treasureProp
				.put("Potion of Energy",
						"This card allows its owner to play any number of asterisks in each round of combat. This Potion is used up by the character who activates it – it cannot be transferred while active. Note: If the same character has both the Girtle of Energy and the Potion of Energy active, the Potion takes precedence; there is no limit to the asterisks he can play. This card returns to the Warlock at Midnight.\nChanging directions:  A character who has one of these cards active can use it during the melee step, after attacks and maneuvers are revealed but before they are resolved. If the indicated conditions are met, the card allows him to change the direction of his attack or his maneuver. He cannot change any pieces that he played, but he can move them to a different box. ");
		// treasureProp.put("Phantom Glass","This card causes its owner to use Magic Sight when he searches");
		treasureProp
				.put("Penetrating Grease",
						"This card causes the weapon to ignore armor. It inflicts damage on all monsters and natives as if they were unarmored. When it hits a character it inflicts damage directly on him as if he were not wearing any armor. The weapon does not damage or destroy armor – it just ignores all armor. This card returns to the Crone at Midnight.");
		treasureProp
				.put("Ointment of Steel",
						"When active, this card intercepts all attacks. It is destroyed when Tremendous (or greater) harm is inflicted on it. When it is destroyed, its owner gets nothing. When a character activates this card, he uses all of the Potion on himself. This card returns to the Shaman at Midnight.");
		treasureProp
				.put("Ointment of Bite",
						" This card causes the weapon to hit whenever its attack time undercuts or ties its target’s maneuver time. This card returns to the Crone at Midnight.");
		treasureProp
				.put("Oil of Poison",
						"This card adds one sharpness star to the damage the weapon inflicts when it hits. This card returns to the Crone at Midnight. ");
		treasureProp
				.put("Mouldy Skeleton",
						" When a character draws this card, he must immediately reveal it and roll for a Curse. Then he exchanges the card, but he does not keep its treasures - he must put them in the site box he was searching when he drew the Mouldy Skeleton, and the only way to obtain them is by further looting of that site. He puts the treasures on top of any other treasures in that box, with the Gold helmet on top, the Silver breastplate next and the Jade shield third from the top.");
		treasureProp
				.put("Map of Ruins",
						"This card affects all Locate rolls made in the Ruins tile or in any tile that contains a yellow Ruins Warning chit. If a character has this card active in one of these tiles, then when he rolls on the Locate table he subtracts one from his result. Note: If the Ruins tile contains a Ruins chit, the subtraction is still one - not two. ");
		treasureProp
				.put("Map of Lost City",
						"This card affects all Locate rolls made in the tile that contains the Lost City chit. If a character has this card active when he is in this tile, then when he uses the Locate table he subtracts one from his result.");
		treasureProp
				.put("Map of Lost Castle",
						"This card affects all Locate rolls made in the tile that contains the Lost Castle chit. If a character has this card active when he is in this tile, then when he uses the Locate table he subtracts one from his result.  ");
		treasureProp
				.put("Magic Wand",
						"This card affects only the Spell tables (Wish, Curse, Power of the Pit, Transform, Lost and Violent Storm). It also affects the Missile Table when it is used to resolve an Attack spell. If a character has this card active when he uses one of these tables, he chooses what the red die rolls instead of rolling it. If he must roll two dice he must still roll the white die, after choosing what the red die rolls, and the higher number is his result. If he must roll only one die (as a result of the Lucky Charm, for example), he can use the red die and choose his result. ");
		treasureProp
				.put("Magic Spectacles",
						"This card allows its owner to record an extra phase to do the Search activity.  ");
		treasureProp
				.put("Lucky Charm",
						"When a character has this card active when he uses a table, he rolls only one die (including Curse or Power of the Pit).");
		treasureProp
				.put("Lost Keys",
						"When a character has this card active, he does not have to play a piece with Tremendous strength to loot the Vault or Crypt of the Knight. This card is also used to open the Chest.");
		treasureProp
				.put("Hidden Ring",
						"This card contains one Type VI Spell card. It can be used as a MAGIC VI chit to cast this spell. ");
		treasureProp.put("Handy Gloves", "Medium strength, time number of 3. ");
		treasureProp
				.put("Gripping Dust",
						"This card keeps the weapon alerted side up at all times, even when it hits.  This card returns to the Warlock at Midnight.");
		treasureProp
				.put("Good Book",
						"This card contains two Type I Spell cards and two Type VII Spell cards. Once per day it can be used as a MAGIC I chit to cast one of the Type I spells. It cannot cast the Type VII spells.");
		treasureProp.put("Golden Icon", "This card is a source of Grey magic.");
		treasureProp
				.put("Golden Crown",
						" When active, this card intercepts Smash attacks. It is destroyed when Medium (or greater) harm is inflicted on it. When it is destroyed, its owner gets 48 GOLD points to represent the value of its gold and jewels.");
		treasureProp
				.put("Golden Arm Band",
						"When active, this card intercepts Swing attacks. It is destroyed when Medium (or greater) harm is inflicted on it. When it is destroyed, its owner gets 11 GOLD points to represent the value of the gold. ");
		treasureProp
				.put("Glowing Gem",
						" This card contains one Type VII Spell card. It can be used as a 'MAGlC Vll' chit to cast this spell.");
		treasureProp
				.put("Gloves of Strength",
						"Tremendous strength, time number of 5. This card can be used to open the Vault and Crypt of the Knight.");
		treasureProp
				.put("Glimmering Ring",
						"This card contains one Type Ill Spell card. It can be used as 'MAGlC lll' chit to cast this spell.");
		treasureProp
				.put("Girtle of Energy",
						"This card allows its owner to play up to three effort asterisks (instead of two) during each round of combat.");
		treasureProp
				.put("Garb of Speed",
						" This card alters the time numbers on all of its owner's action chits (including his Magic chits). The asterisks on each chit defines its time number: each chit with two asterisks has a time of '3', each chit with one asterisk has a time of '4', and each chit with no asterisks has a time of '5'. Note: If a character has both the Garb of Speed and Draught of Speed active, the Draught of Speed defines the time numbers on his action chits and the Garb of Speed has no effect.");
		treasureProp
				.put("Flying Carpet",
						"The Flying Carpet is the target of an unbreakable Permanent spell that can be energized by Purple magic. This spell cannot be broken - when a spellbreaking spell is used against it the spell just falls inert until it is resupplied with Purple magic.  When the spell is energized, the Flying Carpet can be used as a Fly chit with Medium strength and a time number of '2'. The owner of the card can play it once per round of combat, using it repeatedly (the spell does not expire after one use). The card's owner can also use it to fly during his turn.");
		treasureProp.put("Flowers of Rest",
				"This card is a source of Gold magic. ");
		treasureProp
				.put("Eye of the Moon",
						"The Eye of the Moon can be played exactly like an Artifact to cast its spell. It can be played only once per Evening, and its spell has a completion time of zero. This instant spell can be cast only if the optional Seasons/Weather rules are being used. This spell requires grey magic to cast, and targets the face-down number chit that will define next week's weather. When the spell goes into effect the spellcaster can either look at this chit secretly or, without looking at it, insist that a new chit be picked using the normal method. If a new chit is picked, he does not look at it. Comment: A character usually must cast this spell several times to get the weather he wants. He must cast it once to look at the chit, a second time to change it, a third time to look at the new chit, and so on. This spell can be broken just like other spells. ");
		treasureProp
				.put("Eye of the Idol",
						" This card contains one Type II Spell card. It can be used as a 'MAGIC ll' chit to cast this spell.  ");
		treasureProp
				.put("Enchanter's Skull",
						"This card contains one Type IV Spell card. It can be used as a 'MAGIC lV' chit to cast this spell. ");
		treasureProp
				.put("Enchanted Meadow",
						"Die Roll: Effect on the searching character:\n1 He takes the pony. If it is gone, he gets nothing.\n2 He takes the Truesteel sword. If it is gone, he gets nothing.\n3 He must roll for a Wish. He is both the spellcaster and target.\n4 All of his fatigued and wounded action chits instantly return to play, rested. Note: This result automatically breaks the Wither curse.\n5 He must roll for a Curse.\n6 He gets nothing. ");
		treasureProp
				.put("Elven Slippers", "Light strength, time number of 2. ");
		treasureProp
				.put("Elusive Cloak",
						" If all the attacks aimed at him have an attack time of 5 or more, he can move his maneuver to any Maneuver square he chooses. If an attack with a time of 4 or less is aimed at him he cannot shift his maneuver. Note: The Dwarf can never shift his Duck chit – it can be played only in the Duck maneuver square.\n* If the Battle Bracelets and Elusive Cloak are used in the same melee step, the attack is shifted first, then the maneuver.  ");
		treasureProp
				.put("Draught of Speed",
						"This card alters the time numbers on all of its owner's action chits (including his Magic chits). The asterisks on each chit defines its time number: each chit with two asterisks has a time of '2', each chit with one asterisk has a time of '3', and each chit with no asterisks has a time of '4'. This Potion is used up by the character who activates it. It cannot be transferred while active.  At Midnight, it returns to the Warlock.");
		treasureProp
				.put("DragonFang Necklace",
						"The Dragonfang Necklace can be played exactly like an Artifact to cast its spell. It can be played only once per Evening, and its spell has a completion time of zero. This spell requires purple magic to cast, and targets one Dragon or Flying Dragon of any size. The character who owns this card controls the Dragon. When no character has the card, the spell is nullified. This Day spell expires at the next Sunset. This spell can be broken just like other spells.");
		treasureProp.put("Dragon Essence",
				" This card is a source of Purple magic.");
		treasureProp
				.put("Deft Gloves",
						"Light strength, time number of 2. This card also allows its owner to roll one die on the Loot table.");
		treasureProp
				.put("Crystal Ball",
						"This card allows its owner to use his regular phases to record the enhanced Peer or remote Enchant activity. When he records the enhanced Peer activity, he can record any clearing on the map, and when he records the remote Enchant activity, he can record any tile on the map. When he does the activity he searches the clearing, or enchants the tile, that he recorded.\nExtra phases:  Each of the following cards allows its owner to record an extra phase. He must record which phase is caused by each card and keep the card active until he does that phase.");
		treasureProp
				.put("Crypt of the Knight",
						"Die Roll: Effect on the searching character:\n1 He takes the warhorse. If it is gone, he gets nothing.\n2 He takes the 'T' suit of armor. If it is gone, he gets nothing.\n3 He takes the Bane sword. If it is gone, he gets nothing.\n4 He takes the treasure card. If it is gone, he gets nothing.\n5 He adds one point to his recorded Gold. These Gold points remain available when all of the treasures are gone.\n6 He must roll for a Curse.");
		treasureProp.put("Imperial Tabard",
				"Inert item. It has no special effect. ");

		// treasure data
		treasure.put("7-League Boots", new String[] { "Birdsong", "", "",
				"MOVET5", "", "", "", "", "", "2", "5" });
		treasure.put("Alchemist's Mixture", new String[] { "Weapon", "", "",
				"", "", "Warlock", "", "", "", "", "4" });
		treasure.put("Amulet", new String[] { "One-Use", "", "", "", "",
				"Shaman", "", "", "", "", "5" });
		treasure.put("Ancient Telescope", new String[] { "Birdsong", "", "",
				"", "", "", "", "", "", "", "5" });
		treasure.put("Battle Bracelets", new String[] { "Combat", "", "1", "",
				"", "", "", "", "2", "4", "12" });
		treasure.put("Beast Pipes", new String[] { "Artifacts", "1", "1",
				"MAGICVIII", "1 VIII", "", "", "", "-5", "5", "8" });
		treasure.put("Bejeweled Dwarf Vest", new String[] { "Armor", "1", "1",
				"", "", "", "2", "10 (Soldiers)", "", "5", "27" });
		treasure.put("Belt of Strength", new String[] { "Altering Chits", "1",
				"1", "", "", "", "", "", "3", "6", "16" });
		treasure.put("Black Book", new String[] { "Spell Books", "", "",
				"MAGICV", "2 V, 2 VIII", "", "1", "", "-15", "15", "10" });
		treasure.put("Blasted Jewel", new String[] { "Artifacts", "1", "",
				"MAGICV", "1 V", "", "3", "", "-15", "15", "30" });
		treasure.put("Book of Lore", new String[] { "Spell Books", "", "1",
				"MAGICIV", "4 IV", "", "1", "", "-5", "10", "10" });
		treasure.put("Chest", new String[] { "TWT", "", "P1", "", "", "", "4",
				"", "", "", "0" });
		treasure.put("Cloak of Mist", new String[] { "Birdsong", "1", "", "",
				"", "", "", "", "", "2", "4" });
		treasure.put("Cloven Hoof", new String[] { "Using Tables", "1", "",
				"Black", "", "", "", "", "-20", "40", "4" });
		treasure.put("Crypt of the knight", new String[] { "TWT", "", "P5", "",
				"", "", "", "", "", "", "0" });
		treasure.put("Crystal Ball", new String[] { "Birdsong", "", "", "", "",
				"", "2", "", "", "5", "20" });
		treasure.put("Deft Gloves", new String[] { "Using Tables", "1", "1",
				"FIGHTL2", "", "", "", "5 (Order)", "", "6", "10" });
		treasure.put("Dragon Essence", new String[] { "DayLight", "", "1",
				"Purple", "", "", "", "", "-15", "15", "30" });
		treasure.put("DragonFang Necklace", new String[] { "Tr. w/Spells", "",
				"1", "MAGICV", "1 V", "", "3", "12 (Lancers)", "", "12", "8" });
		treasure.put("Draught of Speed", new String[] { "Alerting Chits", "",
				"1", "", "", "Warlock", "", "", "", "", "6" });
		treasure.put("Elusive Cloak", new String[] { "Combat", "", "1", "", "",
				"", "", "", "", "2", "10" });
		treasure.put("Elven Slippers", new String[] { "Boots", "", "1",
				"MOVEL2", "", "", "", "", "", "2", "5" });
		treasure.put("Enchanted Meadow", new String[] { "TWT", "", "P6", "",
				"", "", "", "", "", "", "" });
		treasure.put("Enchanter's Skull", new String[] { "Artifacts", "", "1",
				"MAGICIV", "1 IV", "", "1", "", "-10", "10", "17" });
		treasure.put("Eye of the Idol", new String[] { "Artifacts", "", "1",
				"MAGICII", "1 II", "", "3", "", "-5", "10", "34" });
		treasure.put("Eye of the Moon", new String[] { "Tr w/Spells", "1", "1",
				"", "", "", "2", "", "", "13", "13" });
		treasure.put("Flowers of Rest", new String[] { "Daylight", "1", "1",
				"Gold", "", "", "", "", "", "", "2" });
		treasure.put("Flying Carpet", new String[] { "Tr. w/Spells", "", "",
				"", "", "", "2", "", "", "12", "17" });
		treasure.put("Garb of Speed", new String[] { "Altering Chits", "1",
				"1", "", "", "", "", "", "2", "6", "16" });
		treasure.put("Girtle of Energy", new String[] { "Combat", "1", "1", "",
				"", "", "", "", "2", "4", "13" });
		treasure.put("Glimmering Ring", new String[] { "Artifacts", "", "1",
				"MAGICIII", "1 III", "", "", "", "", "10", "15" });
		treasure.put("Gloves of Strength", new String[] { "Gloves", "", "",
				"FIGHTT5", "", "", "", "5 (Order)", "", "6", "8" });
		treasure.put("Glowing Gem", new String[] { "Artifacts", "1", "1",
				"MAGICVII", "1 VII", "", "", "5 (Soldiers)", "", "5", "17" });
		treasure.put("Golden Arm Band", new String[] { "Armor", "", "", "", "",
				"", "1", "3 (Lancers)", "", "", "15" });
		treasure.put("Golden Crown", new String[] { "Armor", "", "", "", "",
				"", "3", "20 (Guard)", "", "15", "50" });
		treasure.put("Golden Icon", new String[] { "Color", "", "", "Grey", "",
				"", "4", "", "-10", "20", "100" });
		treasure.put("Good Book", new String[] { "Spell Books", "", "",
				"MAGICI", "2 I, 2 VII", "", "1", "", "5", "5", "10" });
		treasure.put("Gripping Dust", new String[] { "Alter Weapon", "", "",
				"", "", "Warlock", "", "", "-15", "15", "3" });
		treasure.put("Handy Gloves", new String[] { "Gloves", "", "",
				"FIGHTM3", "", "", "", "", "1", "2", "6" });
		treasure.put("Hidden Ring", new String[] { "Artifacts", "", "",
				"MAGICVI", "1 VI", "", "", "", "-10", "10", "20" });
		treasure.put("Imperial Tabard", new String[] { "", "", "", "", "", "",
				"2", "20 (Guard)", "", "-10", "17" });
		treasure.put("Lost Keys", new String[] { "Using Tables", "", "", "",
				"", "", "", "", "", "", "5" });
		treasure.put("Lucky Charm", new String[] { "Using Tables", "", "", "",
				"", "", "", "", "", "", "14" });
		treasure.put("Magic Spectacles", new String[] { "Birdsong", "", "", "",
				"", "", "", "", "", "2", "6" });
		treasure.put("Magic Wand", new String[] { "Using Tables", "1", "1", "",
				"", "", "", "", "-10", "10", "17" });
		treasure.put("Map of Lost Castle", new String[] { "Using Tables", "1",
				"", "", "", "", "", "", "", "", "3" });
		treasure.put("Map of Lost City", new String[] { "Using Tables", "1",
				"1", "", "", "", "", "", "", "", "3" });
		treasure.put("Map of Ruins", new String[] { "Using Tables", "1", "",
				"", "", "", "", "", "", "", "3" });
		treasure.put("Mouldy Skeleton", new String[] { "TWT", "1", "P2", "",
				"", "Discard", "", "", "", "", "" });
		treasure.put("Oil of Poison", new String[] { "Alter Weap", "", "1", "",
				"", "Crone", "H", "", "", "", "3" });
		treasure.put("Ointment of Bite", new String[] { "Alter Weap", "", "",
				"", "", "Crone", "", "", "", "", "5" });
		treasure.put("Ointment of Steel", new String[] { "Armor", "", "", "",
				"", "Shaman", "", "", "", "", "4" });
		treasure.put("Penetrating Grease", new String[] { "Alter Weap", "", "",
				"", "", "Shaman", "", "", "", "", "4" });
		// treasure.put("Phantom Glass" , new String[] {"Using Tables",
		// "","","","", "1", "", "", "", "2","8"});
		treasure.put("Potion of Energy", new String[] { "Combat", "", "", "",
				"", "Warlock", "", "", "", "", "5" });
		treasure.put("Poultice of Health", new String[] { "Daylight", "", "",
				"", "", "Shaman", "", "", "", "", "2" });
		treasure.put("Power Boots", new String[] { "Boots", "", "", "MOVEH4",
				"", "", "", "", "", "3", "8" });
		treasure.put("Power Gauntlets", new String[] { "Gloves", "", "",
				"FIGHTH4", "", "", "", "", "3", "4", "7" });
		treasure.put("Quick Boots", new String[] { "Boots", "", "", "MOVEM3",
				"", "", "", "", "1", "2", "8" });
		treasure.put("Reflecting Grease", new String[] { "Combat", "", "", "",
				"", "Crone", "", "", "", "", "3" });
		treasure.put("Regent of Jewels", new String[] { "Birdsong", "", "", "",
				"", "", "1", "10 (Soldiers)", "", "10", "67" });
		treasure.put("Remains of Thief", new String[] { "TWT", "", "P3", "",
				"", "Discard", "", "", "", "", "" });
		treasure.put("Royal Sceptre", new String[] { "Birdsong", "", "", "",
				"", "", "1", "20 (Guard)", "", "", "4" });
		treasure.put("Sacred Grail", new String[] { "Color", "", "", "White",
				"", "", "2", "50 (Order)", "", "-25", "12" });
		treasure.put("Sacred Statue", new String[] { "Artifacts", "", "",
				"MAGICI", "1 I", "", "1", "10 (Order)", "", "-5", "10" });
		treasure.put("Scroll of Alchemy", new String[] { "Spell Books", "", "",
				"MAGICVI", "4 VI", "", "1", "", "-10", "15", "10" });
		treasure.put("Scroll of Nature", new String[] { "Spell Books", "", "",
				"MAGICII", "2 II, 2 III", "", "1", "", "", "5", "10" });
		treasure.put("Shielded Lantern", new String[] { "Birdsong", "", "", "",
				"", "", "1", "", "", "", "8" });
		treasure.put("Shoes of Stealth", new String[] { "Using Tables", "", "",
				"MOVEL3", "", "", "", "", "", "2", "7" });
		treasure.put("Timeless Jewel", new String[] { "Daylight", "", "", "",
				"", "", "1", "5 (Soldiers)", "", "7", "34" });
		treasure.put("Toadstool Circle", new String[] { "TWT", "", "P4",
				"Black", "", "", "", "", "", "", "" });
		treasure.put("Toadstool Ring", new String[] { "Birdsong", "", "", "",
				"", "", "", "", "", "3", "9" });
		treasure.put("Vial of Healing", new String[] { "Instant Rest", "", "",
				"", "", "Chapel", "", "", "", "", "2" });
		treasure.put("Withered Claw", new String[] { "One-Use", "", "", "", "",
				"Shaman", "", "", "", "", "3" });

		for (String s : treasure.keySet()) {

			if (treasure.get(s)[0] == "TWT") {

				TWT.add(s);
			}

			else if (treasure.get(s)[2] == "1") {
				largeTreasures.add(s);
			}

			else {

				smallTreasures.add(s);
			}

		}

		largeTreasures.get(0);

		TWTdata.put("Toadstool Circle", new String[] {}); // 1
		TWTdata.put("Chest", new String[] {}); // 2
		TWTdata.put("Crypt of the Knight", new String[] {}); // 1
		TWTdata.put("Enchanted Meadow", new String[] {});
		TWTdata.put("Mouldy Skeleton", new String[] {});
		TWTdata.put("Remains of Thief", new String[] {});// 1

		ArrayList<Integer> ra = new ArrayList<Integer>();

		for (int t = 0; t < largeTreasures.size(); t++) {

			ra.add(t);

		}

		Collections.shuffle(ra);

		TWTdata.replace("Toadstool Circle",
				new String[] { largeTreasures.get(ra.get(0)),
						"Devil Broadsword" });
		TWTdata.replace("Chest", new String[] { largeTreasures.get(ra.get(1)),
				largeTreasures.get(ra.get(2)) });
		TWTdata.replace("Crypt of the Knight",
				new String[] { largeTreasures.get(ra.get(3)), "Bane Sword" });
		TWTdata.replace("Remains of Thief",
				new String[] { largeTreasures.get(ra.get(4)),
						"Living Thrusting Sword" });
		TWTdata.replace("Mouldy Skeleton",
				new String[] { "Jade Shield",
						"Gold Helmet", "Silver Breastplate" });
		TWTdata.replace(
				"Enchanted Meadow",
				new String[] { largeTreasures.get(ra.get(5)), "Truesteel Sword" });

		for (int g = 0; g < 5; g++) {

			largeTreasures.remove(ra.get(g));
		}

		largeTWT.addAll(TWT);
		largeTWT.addAll(largeTreasures);

		Collections.shuffle(largeTWT);

		ArrayList<Integer> e = new ArrayList<Integer>();

		for (int t = 0; t < largeTWT.size(); t++) {

			e.add(t);

		}

		Collections.shuffle(e);

		ArrayList<Integer> r = new ArrayList<Integer>();

		for (int t = 0; t < smallTreasures.size(); t++) {

			r.add(t);

		}

		Collections.shuffle(r);
		Collections.shuffle(smallTreasures);

		// site chits and their contents, randomized.
		siteChitData.put(
				"6 - Hoard",
				new String[] { "30 gold", largeTWT.get(e.get(0)),
						largeTWT.get(e.get(1)), largeTWT.get(e.get(2)),
						largeTWT.get(e.get(3)), largeTWT.get(e.get(4)),
						smallTreasures.get(r.get(0)),
						smallTreasures.get(r.get(1)),
						smallTreasures.get(r.get(2)),
						smallTreasures.get(r.get(3)) });
		siteChitData.put(
				"3 - Lair",
				new String[] { "44 gold", largeTWT.get(e.get(5)),
						largeTWT.get(e.get(6)), largeTWT.get(e.get(7)),
						smallTreasures.get(r.get(4)),
						smallTreasures.get(r.get(5)),
						smallTreasures.get(r.get(6)),
						smallTreasures.get(r.get(7)) });
		siteChitData.put("1 - Altar",
				new String[] { "10 gold", "20 gold", largeTWT.get(e.get(8)),
						largeTWT.get(e.get(9)), largeTWT.get(e.get(10)) });
		siteChitData.put(
				"4 - Shrine",
				new String[] { "20 gold", largeTWT.get(e.get(11)),
						largeTWT.get(e.get(12)), smallTreasures.get(r.get(8)),
						smallTreasures.get(r.get(9)) });
		siteChitData.put(
				"6 - Pool",
				new String[] { "28 gold", largeTWT.get(e.get(13)),
						largeTWT.get(e.get(14)), smallTreasures.get(r.get(10)),
						smallTreasures.get(r.get(11)),
						smallTreasures.get(r.get(12)),
						smallTreasures.get(r.get(13)),
						smallTreasures.get(r.get(14)),
						smallTreasures.get(r.get(15)) });
		siteChitData.put(
				"3 - Vault",
				new String[] { "35 gold", largeTWT.get(e.get(15)),
						largeTWT.get(e.get(16)), largeTWT.get(e.get(17)),
						largeTWT.get(e.get(18)), largeTWT.get(e.get(19)) });
		siteChitData.put(
				"5 - Cairns",
				new String[] { "20 gold", largeTWT.get(e.get(20)),
						smallTreasures.get(r.get(16)),
						smallTreasures.get(r.get(17)),
						smallTreasures.get(r.get(18)),
						smallTreasures.get(r.get(19)),
						smallTreasures.get(r.get(20)),
						smallTreasures.get(r.get(21)) });
		siteChitData.put(
				"2 - Statue",
				new String[] { "15 gold", largeTWT.get(e.get(21)),
						smallTreasures.get(r.get(22)),
						smallTreasures.get(r.get(23)) });
		siteChitData.put("1 - Lost Castle", new String[] { "77 gold" });
		siteChitData
				.put("3 - Lost City", new String[] { "32 gold", "30 gold" });

		// any clearing located on a mountain
		mountainClearings.add(new Integer[] { 714, 592 });
		mountainClearings.add(new Integer[] { 744, 720 });
		mountainClearings.add(new Integer[] { 610, 718 });
		mountainClearings.add(new Integer[] { 542, 642 });
		mountainClearings.add(new Integer[] { 1626, 1356 });
		mountainClearings.add(new Integer[] { 1666, 1250 });
		mountainClearings.add(new Integer[] { 1740, 1380 });
		mountainClearings.add(new Integer[] { 1834, 1334 });
		mountainClearings.add(new Integer[] { 1762, 1194 });
		mountainClearings.add(new Integer[] { 1878, 1234 });
		mountainClearings.add(new Integer[] { 1614, 898 });
		mountainClearings.add(new Integer[] { 1744, 730 });
		mountainClearings.add(new Integer[] { 1994, 584 });
		mountainClearings.add(new Integer[] { 2110, 518 });
		mountainClearings.add(new Integer[] { 2222, 582 });
		mountainClearings.add(new Integer[] { 1368, 342 });
		mountainClearings.add(new Integer[] { 1394, 196 });
		mountainClearings.add(new Integer[] { 1276, 176 });
		mountainClearings.add(new Integer[] { 1362, 80 });

		// only the cave entrances
		cavesEntrances.add(new Integer[] { 506, 1006 });
		cavesEntrances.add(new Integer[] { 734, 1010 });
		cavesEntrances.add(new Integer[] { 734, 1136 });
		cavesEntrances.add(new Integer[] { 1304, 702 });
		cavesEntrances.add(new Integer[] { 1340, 614 });
		cavesEntrances.add(new Integer[] { 1288, 794 });
		cavesEntrances.add(new Integer[] { 994, 568 });
		cavesEntrances.add(new Integer[] { 1112, 494 });
		cavesEntrances.add(new Integer[] { 1104, 364 });
		cavesEntrances.add(new Integer[] { 1258, 278 });
		cavesEntrances.add(new Integer[] { 1480, 280 });
		cavesEntrances.add(new Integer[] { 540, 1540 });

		// inside the caves
		caves.add(new Integer[] { 506, 1006 });
		caves.add(new Integer[] { 734, 1010 });
		caves.add(new Integer[] { 734, 1136 });
		caves.add(new Integer[] { 1304, 702 });
		caves.add(new Integer[] { 1340, 614 });
		caves.add(new Integer[] { 1288, 794 });
		caves.add(new Integer[] { 994, 568 });
		caves.add(new Integer[] { 1112, 494 });
		caves.add(new Integer[] { 1104, 364 });
		caves.add(new Integer[] { 1258, 278 });
		caves.add(new Integer[] { 1480, 280 });
		caves.add(new Integer[] { 554, 1200 });
		caves.add(new Integer[] { 590, 940 });
		caves.add(new Integer[] { 640, 1070 });
		caves.add(new Integer[] { 540, 1540 });
		caves.add(new Integer[] { 540, 1540 });
		caves.add(new Integer[] { 1024, 440 });
		caves.add(new Integer[] { 920, 444 });
		caves.add(new Integer[] { 866, 362 });

		// only the secret path entrances
		secretPathsEntrances.add(new Integer[] { 620, 500, 714, 592 });
		secretPathsEntrances.add(new Integer[] { 714, 592, 620, 500 });
		secretPathsEntrances.add(new Integer[] { 720, 1394, 706, 1574 });
		secretPathsEntrances.add(new Integer[] { 706, 1574, 720, 1394 });
		secretPathsEntrances.add(new Integer[] { 1274, 1620, 1274, 1620 });
		secretPathsEntrances.add(new Integer[] { 1336, 1516, 1336, 1516 });
		secretPathsEntrances.add(new Integer[] { 1280, 1372, 1416, 1380 });
		secretPathsEntrances.add(new Integer[] { 1416, 1380, 1280, 1372 });
		secretPathsEntrances.add(new Integer[] { 1734, 856, 1820, 996 });
		secretPathsEntrances.add(new Integer[] { 1820, 996, 1734, 856 });
		secretPathsEntrances.add(new Integer[] { 1836, 892, 1850, 778 });
		secretPathsEntrances.add(new Integer[] { 1850, 778, 1836, 892 });
		secretPathsEntrances.add(new Integer[] { 1994, 718, 2224, 714 });
		secretPathsEntrances.add(new Integer[] { 2224, 714, 1994, 718 });
		secretPathsEntrances.add(new Integer[] { 2106, 650, 2110, 518 });
		secretPathsEntrances.add(new Integer[] { 2110, 518, 2106, 650 });
		secretPathsEntrances.add(new Integer[] { 1740, 1380, 1626, 1356 });
		secretPathsEntrances.add(new Integer[] { 1626, 1356, 1740, 1380 });
		secretPathsEntrances.add(new Integer[] { 1834, 1334, 1878, 1234 });
		secretPathsEntrances.add(new Integer[] { 1878, 1234, 1834, 1334 });
		secretPathsEntrances.add(new Integer[] { 866, 362, 994, 568 });
		secretPathsEntrances.add(new Integer[] { 994, 568, 866, 362 });
		secretPathsEntrances.add(new Integer[] { 1024, 440, 1104, 364 });
		secretPathsEntrances.add(new Integer[] { 1104, 364, 1024, 440 });
		secretPathsEntrances.add(new Integer[] { 640, 1070, 734, 1010 });
		secretPathsEntrances.add(new Integer[] { 734, 1010, 640, 1070 });

		siteChits.add("1 - Altar");
		siteChits.add("6 - Hoard");
		siteChits.add("3 - Lair");
		siteChits.add("4 - Shrine");
		siteChits.add("2 - Statue");
		siteChits.add("1 - Lost Castle");
		siteChits.add("3 - Lost City");
		siteChits.add("6 - Pool");
		siteChits.add("3 - Vault");
		siteChits.add("5 - Cairns");

		soundChits.add("Bones (W)");
		soundChits.add("Bones (M)");
		soundChits.add("Bones (C)");
		soundChits.add("Bones (V)");
		soundChits.add("Dank (C)");
		soundChits.add("Dank (W)");
		soundChits.add("Dank (M)");
		soundChits.add("Dank (V)");
		soundChits.add("Howl (M)");
		// soundChits.add("Howl (C)");
		// soundChits.add("Flutter (C)");
		soundChits.add("Flutter (M)");
		soundChits.add("Patter (M)");
		soundChits.add("Patter (C)");
		// soundChits.add("Slither (M)");
		soundChits.add("Slither (C)");
		// soundChits.add("Smoke (M)");
		// soundChits.add("Smoke (C)");
		soundChits.add("Smoke (V)");
		soundChits.add("Stink (W)");
		// soundChits.add("Stink (M)");
		soundChits.add("Stink (C)");
		soundChits.add("Stink (V)");
		// soundChits.add("Ruins (C)");
		soundChits.add("Ruins (W)");
		// soundChits.add("Ruins (M)");
		soundChits.add("Ruins (V)");
		soundChits.add("Roar (C)");
		// soundChits.add("Roar (M)");

		soundChitData.put("Bones (W)", new String[] { "Ogre" });
		soundChitData.put("Dank (W)", new String[] { "Viper" });
		soundChitData.put("Stink (W)", new String[] { "Ghost" });
		soundChitData.put("Ruins (W)", new String[] { "Wolf" });
		soundChitData.put("Bones (M)", new String[] { "Giant", "Giant Bat" });
		soundChitData.put("Dank (M)", new String[] { "Spider H", "Spider T" });
		soundChitData.put("Howl (M)", new String[] { "Giant Bat" });
		soundChitData.put("Flutter (M)", new String[] { "Flying Dragon H",
				"Giant Bat" });
		soundChitData
				.put("Patter (M)", new String[] { "Spider H", "Spider T" });
		/*
		 * soundChitData.put("Slither (M)", new String[] {"Dragon H",
		 * "Serpent H"}); soundChitData.put("Smoke (M)", new String[]
		 * {"Flying Dragon H"}); soundChitData.put("Ruins (M)", new String[]
		 * {"Giant Bat"}); soundChitData.put("Roar (M)", new String[]
		 * {"Giant"}); soundChitData.put("Stink (M)", new String[] {"Giant",
		 * "Spider H"});
		 */
		soundChitData.put("Bones (C)", new String[] { "Troll H", "Troll T" });
		soundChitData
				.put("Dank (C)", new String[] { "Serpent H", "Serpent T" });
		soundChitData.put("Stink (C)", new String[] { "Troll H", "Troll T" });
		soundChitData.put("Patter (C)", new String[] { "Goblin Sword" });
		soundChitData.put("Slither (C)", new String[] { "Dragon H", "Dragon T",
				"Serpent H", "Serpent T" });
		soundChitData.put("Roar (C)", new String[] { "Dragon H", "Dragon T",
				"Troll H", "Troll T" });
		/*
		 * soundChitData.put("Howl (C)", new String[] {"Goblin Axe"});
		 * soundChitData.put("Flutter (C)", new String[] {"Flying Dragon H",
		 * "Giant Bat"}); soundChitData.put("Smoke (C)", new String[]
		 * {"Dragon H"}); soundChitData.put("Ruins (C)", new String[]
		 * {"Goblin Axe"});
		 */
		soundChitData.put("Bones (V)", new String[] { "Ghost" });
		soundChitData.put("Dank (V)", new String[] { "Wolf" });
		soundChitData.put("Smoke (V)", new String[] { "Giant" });
		soundChitData.put("Ruins (V)", new String[] { "Ghost" });
		soundChitData.put("Stink (V)", new String[] { "Ghost" });

		// all possible treasure locations set up
		Integer[] cave0[] = { { 994, 568 }, { 1112, 494 }, { 1024, 440 },
				{ 866, 362 }, { 1104, 364 }, { 920, 444 } };
		Integer[] cave1[] = { { 1289, 507 }, { 1500, 632 }, { 1430, 520 },
				{ 1288, 794 }, { 1304, 702 }, { 1340, 614 } };
		Integer[] cave2[] = { { 506, 1006 }, { 734, 1010 }, { 640, 1070 },
				{ 590, 940 }, { 724, 1136 }, { 554, 1200 } };
		Integer[] cave3[] = { { 706, 1574 }, { 544, 1632 }, { 496, 1436 },
				{ 632, 1462 }, { 720, 1394 }, { 540, 1540 } };
		Integer[] cave4[] = { { 1276, 176 }, { 1368, 342 }, { 1258, 278 },
				{ 1395, 196 }, { 1362, 80 }, { 1480, 280 } };
		Integer[] moun0[] = { { 1994, 584 }, { 1994, 718 }, { 2106, 650 },
				{ 2222, 582 }, { 2224, 714 }, { 2110, 518 } };
		Integer[] moun1[] = { { 1836, 892 }, { 1744, 730 }, { 1850, 778 },
				{ 1734, 856 }, { 1614, 898 }, { 1820, 996 } };
		Integer[] moun2[] = { { 542, 602 }, { 494, 722 }, { 610, 718 },
				{ 610, 500 }, { 744, 720 }, { 714, 592 } };
		Integer[] moun3[] = { { 1416, 1380 }, { 1428, 1622 }, { 1274, 1620 },
				{ 1280, 1372 }, { 1222, 1476 }, { 1336, 1516 } };
		Integer[] moun4[] = { { 1878, 1234 }, { 1626, 1356 }, { 1740, 1380 },
				{ 1762, 1194 }, { 1666, 1250 }, { 1834, 1334 } };
		Integer[] vall0[] = { { 926, 750 }, { 1108, 926 }, { 1106, 796 },
				{ 878, 928 } };
		Integer[] vall1[] = { { 1302, 1970 }, { 1364, 1806 }, { 1478, 1874 },
				{ 1481, 1999 } };
		Integer[] vall2[] = { { 504, 1867 }, { 614, 1804 }, { 730, 1867 },
				{ 614, 2010 } };
		Integer[] vall3[] = { { 1625, 364 }, { 1850, 364 }, { 1854, 494 },
				{ 1670, 545 } };
		Integer[] vall4[] = { { 1627, 1657 }, { 1800, 1688 }, { 1622, 1788 },
				{ 1734, 1852 } };
		Integer[] wood0[] = { { 200, 736 }, { 354, 780 }, { 306, 960 } };
		Integer[] wood1[] = { { 195, 1692 }, { 358, 1654 }, { 300, 1810 } };
		Integer[] wood2[] = { { 872, 1672 }, { 930, 1830 }, { 1052, 1692 } };
		Integer[] wood3[] = { { 874, 1230 }, { 926, 1390 }, { 1100, 1290 } };
		Integer[] wood4[] = { { 1364, 1206 }, { 1250, 1074 }, { 1412, 976 } };
		Integer[] wood5[] = { { 1274, 1620 }, { 1428, 1622 }, { 1336, 1516 },
				{ 1280, 1372 }, { 1416, 1380 }, { 1222, 1476 } };

		ArrayList<Integer[][]> cavess = new ArrayList<Integer[][]>();
		cavess.add(cave0);
		cavess.add(cave1);
		cavess.add(cave2);
		cavess.add(cave3);
		cavess.add(cave4);
		Collections.shuffle(cavess);
		ArrayList<Integer[][]> woods = new ArrayList<Integer[][]>();
		woods.add(wood0);
		woods.add(wood1);
		woods.add(wood2);
		woods.add(wood3);
		woods.add(wood4);
		Collections.shuffle(woods);
		ArrayList<Integer[][]> valleys = new ArrayList<Integer[][]>();
		valleys.add(vall0);
		valleys.add(vall1);
		valleys.add(vall2);
		valleys.add(vall3);
		valleys.add(vall4);
		Collections.shuffle(valleys);
		ArrayList<Integer[][]> mounts = new ArrayList<Integer[][]>();
		mounts.add(moun0);
		mounts.add(moun1);
		mounts.add(moun2);
		mounts.add(moun3);
		mounts.add(moun4);
		mounts.add(wood5);
		Collections.shuffle(mounts);

		// random order is decided
		int locations[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		Random rnd = new Random();
		for (int i = 9; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = locations[index];
			locations[index] = locations[i];
			locations[i] = a;
		}
		String s;
		/*
		 * randomly assigning treasures to a tile and the respective clearing as
		 * determined by the treasure
		 */
		for (int x = 0; x < 10; x++) {
			s = siteChits.get(x);
			switch (locations[x]) {
			case 1:
				siteChitLocation.put(s,
						cave0[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						cave0[Integer.parseInt("" + s.charAt(0)) - 1], s);
				
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(cave0[i]);
				}
				break;
			case 2:
				siteChitLocation.put(s,
						cave1[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						cave1[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(cave1[i]);
				}
				break;
			case 3:
				siteChitLocation.put(s,
						cave2[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						cave2[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(cave2[i]);
				}
				break;
			case 4:
				siteChitLocation.put(s,
						cave3[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						cave3[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(cave3[i]);
				}
				break;
			case 5:
				siteChitLocation.put(s,
						cave4[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						cave4[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(cave4[i]);
				}
				break;
			case 6:
				siteChitLocation.put(s,
						moun0[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						moun0[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(moun0[i]);
				}
				break;
			case 7:
				siteChitLocation.put(s,
						moun1[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						moun1[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(moun1[i]);
				}
				break;
			case 8:
				siteChitLocation.put(s,
						moun2[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						moun2[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(moun2[i]);
				}
				break;
			case 9:
				siteChitLocation.put(s,
						moun3[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						moun3[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(moun3[i]);
				}
				break;
			case 0:
				siteChitLocation.put(s,
						moun4[Integer.parseInt("" + s.charAt(0)) - 1]);
				rSiteChitLocation.put(
						moun4[Integer.parseInt("" + s.charAt(0)) - 1], s);
				siteChitNeighborhood.put(s, new ArrayList<Integer[]>());
				for (int i = 0; i < 6; i++) {
					siteChitNeighborhood.get(s).add(moun4[i]);
				}
				break;
			}
		}

		// random order is decided

		String z;
		/*
		 * randomly assigning monsters to a tile and the respective clearing as
		 * determined by the sound chit
		 */
		for (int x = 0; x < 20; x++) {
			z = soundChits.get(x);
			switch (x) {
			case 1:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}

				break;
			case 2:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}

				break;
			case 3:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 4:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 5:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 6:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 7:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 8:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 9:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 10:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 11:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 12:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 13:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 14:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 15:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 16:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 17:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 18:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 19:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 20:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			case 0:
				soundChitNeighborhood.put(z, new ArrayList<Integer[]>());
				if (z.contains("(C)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(cavess.get(0)[i]);
					}
					cavess.remove(cavess.get(0));
				} else if (z.contains("(W)")) {
					for (int i = 0; i < 3; i++) {
						soundChitNeighborhood.get(z).add(woods.get(0)[i]);
					}
					woods.remove(woods.get(0));
				} else if (z.contains("(M)")) {
					for (int i = 0; i < 6; i++) {
						soundChitNeighborhood.get(z).add(mounts.get(0)[i]);
					}
					mounts.remove(mounts.get(0));
				} else if (z.contains("(V)")) {
					for (int i = 0; i < 4; i++) {
						soundChitNeighborhood.get(z).add(valleys.get(0)[i]);
					}
					valleys.remove(valleys.get(0));
				}
				break;
			}
		}

		playerSitesFound.put("Amazon", new ArrayList<Integer>());
		playerSitesFound.put("Berserker", new ArrayList<Integer>());
		playerSitesFound.put("Black Knight", new ArrayList<Integer>());
		playerSitesFound.put("Captain", new ArrayList<Integer>());
		playerSitesFound.put("Druid", new ArrayList<Integer>());
		playerSitesFound.put("Dwarf", new ArrayList<Integer>());
		playerSitesFound.put("Elf", new ArrayList<Integer>());
		playerSitesFound.put("Familiar", new ArrayList<Integer>());
		playerSitesFound.put("Magician", new ArrayList<Integer>());
		playerSitesFound.put("Phantasm", new ArrayList<Integer>());
		playerSitesFound.put("Pilgrim", new ArrayList<Integer>());
		playerSitesFound.put("Sorceror", new ArrayList<Integer>());
		playerSitesFound.put("Swordsman", new ArrayList<Integer>());
		playerSitesFound.put("White Knight", new ArrayList<Integer>());
		playerSitesFound.put("Witch", new ArrayList<Integer>());
		playerSitesFound.put("Witch King", new ArrayList<Integer>());
		playerSitesFound.put("Wizard", new ArrayList<Integer>());
		playerSitesFound.put("Woods Girl", new ArrayList<Integer>());
		for (String str : playerSitesFound.keySet()) {
			for (int i = 0; i < 10; i++)
				playerSitesFound.get(str).add(0);
			// System.out.println(str + playerSitesFound.get(str).size());
		}

		userClassData.add("");
		// DWELLING LOCATIONS
		dwellingLocationsData.put("Inn", new Integer[] { 1106, 796 });
		dwellingLocationsData.put("Ghost", new Integer[] { 1625, 364 });
		dwellingLocationsData.put("House", new Integer[] { 1481, 1999 });
		dwellingLocationsData.put("Guard", new Integer[] { 1627, 1657 });
		dwellingLocationsData.put("Chapel", new Integer[] { 730, 1867 });

		startingLocations.put("Amazon", new String[] { "Inn" });
		startingLocations.put("Berserker", new String[] { "Inn" });
		startingLocations.put("Black Knight", new String[] { "Inn" });
		startingLocations.put("Captain",
				new String[] { "Inn", "House", "Guard" });
		startingLocations.put("Druid", new String[] { "Inn" });
		startingLocations.put("Dwarf", new String[] { "Inn", "Guard" });
		startingLocations.put("Elf", new String[] { "Inn" });
		startingLocations.put("Familiar", new String[] { "Inn" });
		startingLocations.put("Magician", new String[] { "Inn" });
		startingLocations.put("Phantasm", new String[] { "Inn" });
		startingLocations.put("Pilgrim", new String[] { "Inn", "Chapel" });
		startingLocations.put("Sorceror", new String[] { "Inn" });
		startingLocations.put("Swordsman", new String[] { "Inn" });
		startingLocations.put("White Knight", new String[] { "Inn", "Chapel" });
		startingLocations.put("Witch", new String[] { "Inn" });
		startingLocations.put("Witch King", new String[] { "Inn", "Ghost" });
		startingLocations.put("Wizard",
				new String[] { "Inn", "House", "Guard" });
		startingLocations.put("Woods Girl", new String[] { "Inn", "House" });

		followers.put("Amazon", new String[] { "" });
		followers.put("Berserker", new String[] { "" });
		followers.put("Black Knight", new String[] { "" });
		followers.put("Captain", new String[] { "" });
		followers.put("Druid", new String[] { "" });
		followers.put("Dwarf", new String[] { "" });
		followers.put("Elf", new String[] { "" });
		followers.put("Familiar", new String[] { "" });
		followers.put("Magician", new String[] { "" });
		followers.put("Phantasm", new String[] { "" });
		followers.put("Pilgrim", new String[] { "" });
		followers.put("Sorceror", new String[] { "" });
		followers.put("Swordsman", new String[] { "" });
		followers.put("White Knight", new String[] { "" });
		followers.put("Witch", new String[] { "" });
		followers.put("Witch King", new String[] { "" });
		followers.put("Wizard", new String[] { "" });
		followers.put("Woods Girl", new String[] { "" });

		/*
		 * MONSTER DATA
		 * 
		 * 0 int vulnerability 1 int notoriety; 2 int fame; 3 int alert 4 int
		 * attackDamage //3=heavy 4=tremendous 5 int moveSpeed 6 int attackSpeed
		 * 7 int sharpness 8 int alertAttackDamage; //attackDamage when alerted
		 * 9 int alertMovementSpeed; //attackSpeed when alerted 10 int
		 * alertAttackSpeed; 11 int alertWeaponSharp; //sharpness when alerted
		 * 12 int weapon2attackspeed 13 int weapon2moveSpeed 14 int
		 * weapon2attackDamage 15 int alertedWeapon2attackDamage 16 int
		 * alertedWeapon2length 17 int alertedWeapon2attackSpeed 18 int
		 * alertedWeapon2moveSpeed
		 */

		monsterData.put("Dragon T", new Integer[] { 4, 10, 10, 0, 3, 6, 5, 0,
				5, 6, 3, 0, 4, 4, 3, 4, 0, 4, 0 });
		monsterData.put("Wolf", new Integer[] { 2, 1, 0, 0, 1, 3, 4, 0, 2, 4,
				4, 2, 5, 3, 2, 1, 4, 3, 4 });
		monsterData.put("Flying Dragon T", new Integer[] { 4, 12, 12, 0, 2, 4,
				3, 0, 5, 4, 6, 0, 3, 3, 2, 4, 3, 3, 0 });
		monsterData.put("Giant", new Integer[] { 4, 8, 8, 0, 3, 5, 5, 0, 5, 6,
				4, 0, 6, 0, 3, 4, 0, 4, 0 });
		monsterData.put("Ghost", new Integer[] { 2, 2, 0, 0, 3, 4, 4, 0, 1, 2,
				2, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Demon", new Integer[] { 4, 8, 8, 0, 5, 4, 2, 0, 5, 4,
				4, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Flying Dragon H", new Integer[] { 3, 5, 5, 0, 3, 4, 4,
				0, 1, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Giant Bat", new Integer[] { 3, 3, 3, 0, 2, 3, 2, 0, 2,
				2, 3, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Dragon H", new Integer[] { 3, 5, 5, 0, 3, 4, 4, 0, 2,
				4, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Goblin Axe", new Integer[] { 2, 1, 1, 0, 1, 3, 4, 1,
				2, 4, 4, 1, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Goblin Spear", new Integer[] { 2, 1, 1, 0, 0, 3, 0, 0,
				3, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Goblin Sword", new Integer[] { 2, 1, 1, 0, 3, 3, 6, 1,
				4, 5, 5, 1, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Imp", new Integer[] { 2, 1, 2, 0, 4, 2, 2, 0, 2, 3, 4,
				0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Octopus", new Integer[] { 4, 8, 8, 0, 1, 2, 4, 0, 5,
				3, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Ogre", new Integer[] { 2, 2, 0, 0, 4, 5, 5, 0, 3, 4,
				5, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Serpent H", new Integer[] { 3, 4, 4, 0, 2, 3, 4, 0, 3,
				4, 5, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Serpent T", new Integer[] { 4, 7, 7, 0, 1, 4, 4, 0, 5,
				5, 4, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Spider H", new Integer[] { 3, 3, 3, 0, 1, 3, 4, 0, 4,
				4, 6, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Spider T", new Integer[] { 4, 6, 6, 0, 2, 3, 4, 0, 5,
				4, 5, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Troll H", new Integer[] { 3, 5, 5, 0, 2, 4, 4, 0, 3,
				4, 5, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Troll T", new Integer[] { 4, 8, 8, 0, 3, 4, 4, 0, 5,
				6, 2, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Viper ", new Integer[] { 2, 2, 1, 0, 2, 4, 4, 2, 1, 2,
				2, 0, 0, 0, 0, 0, 0, 0, 0 });
		monsterData.put("Winged Demon", new Integer[] { 4, 8, 8, 0, 4, 3, 3, 0,
				5, 2, 5, 0, 0, 0, 0, 0, 0, 0, 0 });

		// WEAPON DATA, SORTED BY : SPEED, WEIGHT(0=negligible 1=light, 2=medium
		// 3=heavy 4=tremendous), LENGTH, SHARPNESS, ALERTED SHARPNESS, ALERTED
		// SPEED
		weaponData.put("Axe", new Integer[] { 5, 2, 2, 1, 1, 0 });
		weaponData.put("Broad Sword", new Integer[] { 5, 2, 7, 1, 1, 0 });
		weaponData.put("Crossbow", new Integer[] { 0, 3, 12, 0, 1, 1 });
		weaponData.put("Great Axe", new Integer[] { 0, 3, 5, 1, 1, 4 });
		weaponData.put("Great Sword", new Integer[] { 6, 3, 8, 1, 1, 0 });
		weaponData.put("Halberd", new Integer[] { 6, 2, 8, 1, 2, 0 });
		weaponData.put("Light Bow", new Integer[] { 0, 1, 14, 0, 2, 1 });
		weaponData.put("Mace", new Integer[] { 0, 2, 1, 0, 0, 3 });
		weaponData.put("Medium Bow", new Integer[] { 0, 2, 16, 0, 2, 1 });
		weaponData.put("Morning Star", new Integer[] { 0, 3, 6, 0, 0, 3 });
		weaponData.put("Short Sword", new Integer[] { 0, 1, 3, 1, 1, 0 });
		weaponData.put("Spear", new Integer[] { 6, 2, 10, 0, 1, 0 });
		weaponData.put("Staff", new Integer[] { 0, 1, 9, 0, 0, 0 });
		weaponData.put("Thrusting Sword", new Integer[] { 4, 1, 4, 1, 1, 0 });
		weaponData.put("Dagger", new Integer[] { 0, 0, 0, 1, 1, 0 });
		// treasure weapons
		weaponData.put("Alchemist's Mixture",
				new Integer[] { 2, 0, 11, 3, 3, 2 });
		weaponData.put("Truesteel Sword", new Integer[] { 0, 2, 7, 2, 2, 0 });
		weaponData.put("Devil Broadsword", new Integer[] { 4, 3, 7, 1, 1, 3 });
		weaponData.put("Bane Sword", new Integer[] { 8, 4, 8, 1, 1, 2 });
		weaponData.put("Living Thrusting Sword", new Integer[] { 3, 1, 4, 1, 1,
				2 });
	
		// WEAPON PRICE
		itemPrice.put("Axe", 4);
		itemPrice.put("Broad Sword", 8);
		itemPrice.put("Crossbow", 10);
		itemPrice.put("Great Axe", 8);
		itemPrice.put("Great Sword", 10);
		itemPrice.put("Halberd", 10);
		itemPrice.put("Light Bow", 6);
		itemPrice.put("Mace", 6);
		itemPrice.put("Medium Bow", 8);
		itemPrice.put("Morning Star", 8);
		itemPrice.put("Short Sword", 4);
		itemPrice.put("Spear", 6);
		itemPrice.put("Staff", 1);
		itemPrice.put("Thrusting Sword", 6);
		itemPrice.put("Dagger", 1);
		itemPrice.put("Alchemist's Mixture", 10);
		itemPrice.put("Helmet", 5);
		itemPrice.put("Suit of Armour", 17);
		itemPrice.put("Shield", 7);
		itemPrice.put("Breastplate", 9);
		itemPrice.put("Beast Pipes", 20);
		itemPrice.put("Glowing Gem", 20);
		itemPrice.put("Hidden Ring", 20);
		itemPrice.put("Blasted Jewel", 20);
		itemPrice.put("Enchanter's Skull", 20);
		itemPrice.put("Glimmering Ring", 20);
		itemPrice.put("Eye of the Idol", 20);
		itemPrice.put("Sacred Statue", 20);
		itemPrice.put("Scroll of Alchemy", 50);
		itemPrice.put("Black Book", 50);
		itemPrice.put("Book of Lore", 50);
		itemPrice.put("Scroll of Nature", 50);
		itemPrice.put("Good Book", 50);
		itemPrice.put("Bane Sword", 20);
		itemPrice.put("Truesteel Sword", 25);
		itemPrice.put("Devil Broadsword", 20);
		itemPrice.put("Living Thrusting Sword", 25);
		itemPrice.put("Gold Helmet", 30);
		itemPrice.put("Silver Breastplate", 25);
		itemPrice.put("Jade Shield", 20);

		// ARMOUR DATA, SORTED BY : WEIGHT(0=negligible 1=light, 2=medium
		// 3=tremendous), Damage (0=normal, 1=damaged, 2=destroyed)
		armourData.put("Helmet", new Integer[] { 2, 0 });
		armourData.put("Suit of Armour", new Integer[] { 3, 0 });
		armourData.put("Shield", new Integer[] { 2, 0 });
		armourData.put("Breastplate", new Integer[] { 2, 0 });
		armourData.put("Gold Helmet", new Integer[] { 2, 0 });
		armourData.put("Silver Breastplate", new Integer[] { 2, 0 });
		armourData.put("Jade Shield", new Integer[] { 2, 0 });


		// CLASS VULNERABILITIES

		userClassVulnerability.put("Amazon", 2);
		userClassVulnerability.put("Berserker", 3);
		userClassVulnerability.put("Black Knight", 2);
		userClassVulnerability.put("Captain", 2);
		userClassVulnerability.put("Druid", 1);
		userClassVulnerability.put("Dwarf", 3);
		userClassVulnerability.put("Elf", 1);
		userClassVulnerability.put("Familiar", 0);
		userClassVulnerability.put("Magician", 1);
		userClassVulnerability.put("Phantasm", 0);
		userClassVulnerability.put("Pilgrim", 2);
		userClassVulnerability.put("Sorceror", 2);
		userClassVulnerability.put("Swordsman", 1);
		userClassVulnerability.put("White Knight", 3);
		userClassVulnerability.put("Witch", 1);
		userClassVulnerability.put("Witch King", 1);
		userClassVulnerability.put("Wizard", 2);
		userClassVulnerability.put("Woods Girl", 1);

		// VICTORY POINTS
		// The Integer Array Represents, in Order: Great Treasures, Spells
		// Learned, Fame, Notoriety, Gold, Locations Entered.
		userVictoryPoints.put("Amazon", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Berserker", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Black Knight",
				new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Captain", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Druid", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Dwarf", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Elf", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Familiar", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Magician", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Phantasm", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Pilgrim", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Sorceror", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Swordsman", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("White Knight",
				new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Witch", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints
				.put("Witch King", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints.put("Wizard", new Integer[] { 0, 0, 0, 0, 10, 0 });
		userVictoryPoints
				.put("Woods Girl", new Integer[] { 0, 0, 0, 0, 10, 0 });

		userAlertData.put("Amazon", false);
		userAlertData.put("Berserker", false);
		userAlertData.put("Black Knight", false);
		userAlertData.put("Captain", false);
		userAlertData.put("Druid", false);
		userAlertData.put("Dwarf", false);
		userAlertData.put("Elf", false);
		userAlertData.put("Familiar", false);
		userAlertData.put("Magician", false);
		userAlertData.put("Phantasm", false);
		userAlertData.put("Pilgrim", false);
		userAlertData.put("Sorceror", false);
		userAlertData.put("Swordsman", false);
		userAlertData.put("White Knight", false);
		userAlertData.put("Witch", false);
		userAlertData.put("Witch King", false);
		userAlertData.put("Wizard", false);
		userAlertData.put("Woods Girl", false);

		// PLAYER LOCATIONS

		playerLocationsData.put("Amazon", new Integer[] { 1106, 796 });
		playerLocationsData.put("Berserker", new Integer[] { 1106, 796 });
		playerLocationsData.put("Black Knight", new Integer[] { 1106, 796 });
		playerLocationsData.put("Captain", new Integer[] { 1106, 796 });
		playerLocationsData.put("Druid", new Integer[] { 1106, 796 });
		playerLocationsData.put("Dwarf", new Integer[] { 1106, 796 });
		playerLocationsData.put("Elf", new Integer[] { 1106, 796 });
		playerLocationsData.put("Familiar", new Integer[] { 1106, 796 });
		playerLocationsData.put("Magician", new Integer[] { 1106, 796 });
		playerLocationsData.put("Phantasm", new Integer[] { 1106, 796 });
		playerLocationsData.put("Pilgrim", new Integer[] { 1106, 796 });
		playerLocationsData.put("Sorceror", new Integer[] { 1106, 796 });
		playerLocationsData.put("Swordsman", new Integer[] { 1106, 796 });
		playerLocationsData.put("White Knight", new Integer[] { 1106, 796 });
		playerLocationsData.put("Witch", new Integer[] { 1106, 796 });
		playerLocationsData.put("Witch King", new Integer[] { 1106, 796 });
		playerLocationsData.put("Wizard", new Integer[] { 1106, 796 });
		playerLocationsData.put("Woods Girl", new Integer[] { 1106, 796 });

		// PLAYER INVENTORY DATA

		inventoryData.put("Amazon", new String[] { "Short Sword", "Helmet",
				"Breastplate", "Shield", "MOVEM4", "MOVEM3*", "FIGHTL4",
				"MOVEM4", "FIGHTM5", "FIGHTM4*", "MOVEM3*", "FIGHTM3**",
				"FIGHTH4**", "FIGHTM4*", "FIGHTM3**", "MOVEM3*" });
		inventoryData.put("Berserker", new String[] { "Helmet", "Great Axe",
				"MOVEH6", "MOVEH5", "FIGHTH4*", "MOVET6*", "MOVEH4**",
				"FIGHTH5", "MOVEH4**", "FIGHTT6*", "FIGHTT4**", "BERSERKT4**",
				"FIGHTT5*", "FIGHTT4**" });
		inventoryData.put("Black Knight", new String[] { "Mace",
				"Suit of Armour", "Shield", "MOVEM5", "MOVEH5*", "FIGHTH5*",
				"MOVEM6", "MOVEM4*", "FIGHTH6", "FIGHTM4*", "FIGHTM4*",
				"FIGHTM5", "MOVEH4**", "FIGHTH4**", "FIGHTM3**" });
		inventoryData.put("Captain", new String[] { "Shield", "Short Sword",
				"Helmet", "Breastplate", "MOVEM4*", "MOVEM5", "FIGHTH5*",
				"FIGHTM5", "FIGHTM3**", "MOVEM3**", "MOVEM4*", "FIGHTH5*",
				"FIGHTM4*", "MOVEM4*", "FIGHTH6*", "FIGHTM4*" });
		inventoryData.put("Druid", new String[] { "MOVEL3*", "MOVEL3*",
				"MOVEL4", "FIGHTL4", "MOVEL2**", "FIGHTL2**", "MAGICII3*",
				"MAGICVIII4*", "MAGICVIII3*", "MAGICII2**", "MAGICII3*",
				"MAGICVIII2**" });
		inventoryData.put("Dwarf", new String[] { "Axe", "Great Axe", "Helmet",
				"DUCKT3*", "MOVEH6", "FIGHTH5*", "MOVET6*", "FIGHTH6",
				"FIGHTH4**", "MOVEH5*", "FIGHTT6*", "FIGHTH4**", "MOVET5**",
				"FIGHTT5**", "FIGHTT5**" });
		inventoryData.put("Elf", new String[] { "Light Bow", "MAGICIII3*",
				"MAGICIII4*", "MAGICVII4*", "MAGICVII3*", "MAGICIII3*",
				"MAGICIII2*", "MOVEL3*", "FIGHTL3*", "MOVEL2*", "MOVEM4",
				"FIGHTM3*", "FIGHTM4" });
		inventoryData.put("Magician", new String[] { "FIGHTL3*", "MOVEL4",
				"MOVEL3*", "FIGHTL4", "MAGICII3*", "MOVEM4*", "MAGICVIII4*",
				"MAGICIII3*", "MAGICVII4**", "MAGICV4**", "MAGICVI4*",
				"MAGICIV3*" });
		inventoryData.put("Pilgrim", new String[] { "Staff", "MOVEM4*",
				"MOVEM5", "FIGHTM3*", "MOVEH5*", "FIGHTM4", "FIGHTM2**",
				"MAGICI6*", "MAGICVII3*", "FIGHTM3*", "MOVEH6", "FIGHTH4*",
				"MAGICI4*" });
		inventoryData.put("Sorceror",
				new String[] { "MOVEM5", "FIGHTL3*", "MOVEM4*", "MAGICIV4*",
						"MAGICIV4*", "MAGICIV4*", "MAGICVI5*", "MAGICVI6*",
						"MAGICVI4*", "MAGICIV3*", "MAGICIV3*", "MAGICIV5*" });
		inventoryData.put("Swordsman", new String[] { "Thrusting Sword",
				"MOVEL4", "MOVEL3*", "FIGHTL3*", "MOVEL3*", "FIGHTL2**",
				"MOVEL2**", "MOVEM4*", "FIGHTM4*", "FIGHTM3**", "FIGHTL4*",
				"FIGHTM5*", "FIGHTL2**" });
		inventoryData.put("White Knight", new String[] { "Great Sword",
				"Suit of Armour", "MOVEH5*", "MOVEH6", "FIGHTH5*", "MOVEH4**",
				"FIGHTH6", "FIGHTH4**", "FIGHTH4**", "FIGHTH5*", "MAGICI5**",
				"MOVET6*", "FIGHTT4**", "FIGHTT5*" });
		inventoryData.put("Witch",
				new String[] { "MOVEL4", "MOVEL3*", "MOVEM4*", "MAGICVIII4*",
						"MAGICV6*", "MAGICII3*", "MAGICV5*", "MAGICII3*",
						"FIGHTL3*", "MAGICV4*", "MAGICII2*", "MAGICVIII3*" });
		inventoryData.put("Witch King", new String[] { "MAGICIV4*", "MAGICV4*",
				"MAGICVI4*", "MAGICIV4*", "MAGICV3*", "MAGICVI3*", "MAGICIV3*",
				"MAGICVI3*", "MAGICIV3*", "MAGICV2*", "MAGICVI2*" });
		inventoryData.put("Wizard", new String[] { "Staff", "MOVEM5",
				"MOVEM4*", "FIGHTM3*", "FIGHTL4", "MOVEM5", "FIGHTM5",
				"MAGICII4*", "MAGICIV4*", "MOVEM5", "MAGICII3*", "MAGICIII3*",
				"MAGICIV3*" });
		inventoryData.put("Woods Girl", new String[] { "Light Bow", "MOVEL3*",
				"MOVEL4", "MOVEL2**", "FIGHTL4", "MOVEL2**", "MAGICVII6**",
				"FIGHTL3*", "FIGHTM5", "FIGHTL4", "MOVEL3*", "FIGHTM4*",
				"FIGHTL3*" });

		// PLAYER INACTIVE INVENTORY DATA

		inactiveInventoryData.put("Amazon", new String[] { "10 gold" });
		inactiveInventoryData.put("Berserker", new String[] { "10 gold" });
		inactiveInventoryData.put("Black Knight", new String[] { "10 gold" });
		inactiveInventoryData.put("Captain", new String[] { "10 gold" });
		inactiveInventoryData.put("Druid", new String[] { "10 gold" });
		inactiveInventoryData.put("Dwarf", new String[] { "10 gold" });
		inactiveInventoryData.put("Elf", new String[] { "10 gold" });
		inactiveInventoryData.put("Familiar", new String[] { "10 gold" });
		inactiveInventoryData.put("Magician", new String[] { "10 gold" });
		inactiveInventoryData.put("Phantasm", new String[] { "10 gold" });
		inactiveInventoryData.put("Pilgrim", new String[] { "10 gold" });
		inactiveInventoryData.put("Sorceror", new String[] { "10 gold" });
		inactiveInventoryData.put("Swordsman", new String[] { "10 gold" });
		inactiveInventoryData.put("White Knight", new String[] { "10 gold" });
		inactiveInventoryData.put("Witch", new String[] { "10 gold" });
		inactiveInventoryData.put("Witch King", new String[] { "10 gold" });
		inactiveInventoryData.put("Wizard", new String[] { "10 gold" });
		inactiveInventoryData.put("Woods Girl", new String[] { "10 gold" });

		// PLAYER WOUNDED INVENTORY DATA

		woundedInventoryData.put("Amazon", new String[] { "" });
		woundedInventoryData.put("Berserker", new String[] { "" });
		woundedInventoryData.put("Black Knight", new String[] { "" });
		woundedInventoryData.put("Captain", new String[] { "" });
		woundedInventoryData.put("Druid", new String[] { "" });
		woundedInventoryData.put("Dwarf", new String[] { "" });
		woundedInventoryData.put("Elf", new String[] { "" });
		woundedInventoryData.put("Familiar", new String[] { "" });
		woundedInventoryData.put("Magician", new String[] { "" });
		woundedInventoryData.put("Phantasm", new String[] { "" });
		woundedInventoryData.put("Pilgrim", new String[] { "" });
		woundedInventoryData.put("Sorceror", new String[] { "" });
		woundedInventoryData.put("Swordsman", new String[] { "" });
		woundedInventoryData.put("White Knight", new String[] { "" });
		woundedInventoryData.put("Witch", new String[] { "" });
		woundedInventoryData.put("Witch King", new String[] { "" });
		woundedInventoryData.put("Wizard", new String[] { "" });
		woundedInventoryData.put("Woods Girl", new String[] { "" });

		// PLAYER FATIGUED INVENTORY DATA

		fatiguedInventoryData.put("Amazon", new String[] { "" });
		fatiguedInventoryData.put("Berserker", new String[] { "" });
		fatiguedInventoryData.put("Black Knight", new String[] { "" });
		fatiguedInventoryData.put("Captain", new String[] { "" });
		fatiguedInventoryData.put("Druid", new String[] { "" });
		fatiguedInventoryData.put("Dwarf", new String[] { "" });
		fatiguedInventoryData.put("Elf", new String[] { "" });
		fatiguedInventoryData.put("Familiar", new String[] { "" });
		fatiguedInventoryData.put("Magician", new String[] { "" });
		fatiguedInventoryData.put("Phantasm", new String[] { "" });
		fatiguedInventoryData.put("Pilgrim", new String[] { "" });
		fatiguedInventoryData.put("Sorceror", new String[] { "" });
		fatiguedInventoryData.put("Swordsman", new String[] { "" });
		fatiguedInventoryData.put("White Knight", new String[] { "" });
		fatiguedInventoryData.put("Witch", new String[] { "" });
		fatiguedInventoryData.put("Witch King", new String[] { "" });
		fatiguedInventoryData.put("Wizard", new String[] { "" });
		fatiguedInventoryData.put("Woods Girl", new String[] { "" });

		// USER STATUS

		userStatusData.put("Amazon", "Normal");
		userStatusData.put("Berserker", "Normal");
		userStatusData.put("Black Knight", "Normal");
		userStatusData.put("Captain", "Normal");
		userStatusData.put("Druid", "Normal");
		userStatusData.put("Dwarf", "Normal");
		userStatusData.put("Elf", "Normal");
		userStatusData.put("Familiar", "Normal");
		userStatusData.put("Magician", "Normal");
		userStatusData.put("Phantasm", "Normal");
		userStatusData.put("Pilgrim", "Normal");
		userStatusData.put("Sorceror", "Normal");
		userStatusData.put("Swordsman", "Normal");
		userStatusData.put("White Knight", "Normal");
		userStatusData.put("Witch", "Normal");
		userStatusData.put("Witch King", "Normal");
		userStatusData.put("Wizard", "Normal");
		userStatusData.put("Woods Girl", "Normal");

		// CLEARING INVENTORY DATA
		Random randomInt = new Random();
		// int random = randomInt.nextInt(max - min + 1) + min

		int random = randomInt.nextInt(10 - 1 + 1) + 1;
		clearingInventoryData.put(
				new Integer[] { 1106, 796 },
				new String[] { "Shielded Lantern","Cloak of Mist",random + " gold", smallTreasures.get(r.get(24)),
						smallTreasures.get(r.get(25)) });
		clearingInventoryData.put(new Integer[] { 1289, 507 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 878, 928 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 734, 1010 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 730, 1867 }, new String[] {
				smallTreasures.get(r.get(32)), smallTreasures.get(r.get(33)) });
		clearingInventoryData.put(new Integer[] { 1627, 1657 }, new String[] {
				smallTreasures.get(r.get(30)), smallTreasures.get(r.get(31)) });
		clearingInventoryData
				.put(new Integer[] { 1734, 1852 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1481, 1999 }, new String[] {
				smallTreasures.get(r.get(28)), smallTreasures.get(r.get(29)) });
		clearingInventoryData.put(new Integer[] { 1625, 364 }, new String[] {
				smallTreasures.get(r.get(26)), smallTreasures.get(r.get(27)) });
		clearingInventoryData.put(new Integer[] { 640, 1070 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 590, 940 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 554, 1200 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 506, 1006 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 306, 960 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 734, 1136 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 874, 1230 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 926, 1390 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1052, 1692 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1302, 1970 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1478, 1874 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1622, 1788 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1800, 1688 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 930, 1830 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 504, 1867 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 300, 1810 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 614, 2010 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 614, 1804 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 544, 1632 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 358, 1654 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 195, 1692 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 706, 1574 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 872, 1672 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 632, 1462 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 540, 1540 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 496, 1436 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 720, 1394 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 200, 736 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 354, 780 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 494, 722 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 620, 500 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 714, 592 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 610, 718 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 542, 602 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 744, 720 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 926, 750 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 994, 568 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1024, 440 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 920, 444 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 866, 362 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1104, 364 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1258, 278 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1480, 280 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1362, 80 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1276, 176 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1394, 196 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1368, 342 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1112, 494 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1304, 702 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1430, 520 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1340, 614 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1288, 794 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1500, 632 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1670, 545 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1850, 364 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1854, 494 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1994, 584 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 2210, 518 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 2222, 582 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 2224, 714 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 2106, 650 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1994, 718 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1850, 778 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1820, 996 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1836, 892 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1734, 856 }, new String[] {});// secret
																				// passage
		clearingInventoryData.put(new Integer[] { 1744, 730 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1614, 898 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1412, 976 }, new String[] {});
		clearingInventoryData.put(new Integer[] { 1108, 926 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1250, 1074 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1100, 1290 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1364, 1206 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1416, 1380 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1626, 1356 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1666, 1250 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1740, 1380 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1834, 1334 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1762, 1194 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1878, 1234 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1336, 1516 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1280, 1372 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1222, 1476 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1274, 1620 }, new String[] {});// secret
																	// passage
		clearingInventoryData
				.put(new Integer[] { 1428, 1622 }, new String[] {});
		clearingInventoryData
				.put(new Integer[] { 1364, 1806 }, new String[] {});

		// ADJACENT CLEARING DATA

		adjacentClearings.put(new Integer[] { 1106, 796 }, new Integer[][] {
				{ 878, 928 }, { 1289, 507 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1289, 507 }, new Integer[][] {
				{ 1106, 796 }, { 1368, 342 }, { 1340, 614 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 878, 928 }, new Integer[][] {
				{ 1106, 796 }, { 734, 1010 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 734, 1010 }, new Integer[][] {
				{ 878, 928 }, { 590, 940 }, { 640, 1070 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 730, 1867 }, new Integer[][] {
				{ 930, 1830 }, { 504, 1867 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1627, 1657 }, new Integer[][] {
				{ 1428, 1622 }, { 1734, 1852 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1734, 1852 }, new Integer[][] {
				{ 1627, 1657 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1481, 1999 }, new Integer[][] {
				{ 1364, 1806 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1625, 364 }, new Integer[][] {
				{ 1480, 280 }, { 1854, 494 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 640, 1070 }, new Integer[][] {
				{ 734, 1010 }, { 734, 1136 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 590, 940 }, new Integer[][] {
				{ 554, 1200 }, { 734, 1010 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 554, 1200 }, new Integer[][] {
				{ 590, 940 }, { 506, 1006 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 506, 1006 }, new Integer[][] {
				{ 554, 1200 }, { 306, 960 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 306, 960 }, new Integer[][] {
				{ 506, 1006 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 734, 1136 }, new Integer[][] {
				{ 874, 1230 }, { 640, 1070 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 874, 1230 }, new Integer[][] {
				{ 734, 1136 }, { 926, 1390 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 926, 1390 }, new Integer[][] {
				{ 874, 1230 }, { 720, 1394 }, { 1052, 1692 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1052, 1692 }, new Integer[][] {
				{ 926, 1390 }, { 1302, 1970 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1302, 1970 }, new Integer[][] {
				{ 1052, 1692 }, { 1478, 1874 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1478, 1874 }, new Integer[][] {
				{ 1302, 1970 }, { 1622, 1788 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1622, 1788 }, new Integer[][] {
				{ 1478, 1874 }, { 1800, 1688 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1800, 1688 }, new Integer[][] {
				{ 1622, 1788 }, { 0 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 930, 1830 }, new Integer[][] {
				{ 730, 1867 }, { 872, 1672 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 504, 1867 }, new Integer[][] {
				{ 730, 1867 }, { 300, 1810 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 300, 1810 }, new Integer[][] {
				{ 504, 1867 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 614, 2010 }, new Integer[][] {
				{ 614, 1804 }, { 0 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 614, 1804 }, new Integer[][] {
				{ 614, 2010 }, { 544, 1632 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 544, 1632 }, new Integer[][] {
				{ 358, 1654 }, { 706, 1574 }, { 614, 1804 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 358, 1654 }, new Integer[][] {
				{ 195, 1692 }, { 544, 1632 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 195, 1692 }, new Integer[][] {
				{ 358, 1654 }, { 0 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 706, 1574 }, new Integer[][] {
				{ 544, 1632 }, { 872, 1672 }, { 632, 1462 }, { 720, 1394 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 872, 1672 }, new Integer[][] {
				{ 706, 1574 }, { 930, 1830 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 632, 1462 }, new Integer[][] {
				{ 540, 1540 }, { 706, 1574 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 540, 1540 }, new Integer[][] {
				{ 496, 1436 }, { 632, 1462 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 496, 1436 }, new Integer[][] {
				{ 540, 1540 }, { 720, 1394 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 720, 1394 }, new Integer[][] {
				{ 496, 1436 }, { 926, 1390 }, { 706, 1574 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 200, 736 }, new Integer[][] {
				{ 354, 780 }, { 0 }, { 0 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 354, 780 }, new Integer[][] {
				{ 200, 736 }, { 494, 722 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 494, 722 }, new Integer[][] {
				{ 620, 500 }, { 744, 720 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 620, 500 }, new Integer[][] {
				{ 494, 722 }, { 714, 592 }, { 0 }, { -1, -1 } });// secret
																	// passage
		adjacentClearings.put(new Integer[] { 714, 592 }, new Integer[][] {
				{ 610, 718 }, { 744, 720 }, { 620, 500 }, { -1, -1 } });// secret
																		// passage
		adjacentClearings.put(new Integer[] { 610, 718 }, new Integer[][] {
				{ 542, 602 }, { 714, 592 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 542, 602 }, new Integer[][] {
				{ 610, 718 }, { -1, -1 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 744, 720 }, new Integer[][] {
				{ 492, 722 }, { 714, 592 }, { 926, 750 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 926, 750 }, new Integer[][] {
				{ 744, 720 }, { 994, 568 }, { 1108, 926 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 994, 568 }, new Integer[][] {
				{ 1024, 440 }, { 866, 362 }, { 926, 750 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1024, 440 }, new Integer[][] {
				{ 920, 444 }, { 994, 568 }, { 1112, 494 }, { 1104, 364 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 920, 444 }, new Integer[][] {
				{ 866, 362 }, { 1024, 440 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 866, 362 }, new Integer[][] {
				{ 1104, 364 }, { 994, 568 }, { 920, 444 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1104, 364 }, new Integer[][] {
				{ 866, 362 }, { 1024, 440 }, { 1258, 278 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1258, 278 }, new Integer[][] {
				{ 1104, 364 }, { 1480, 280 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1480, 280 }, new Integer[][] {
				{ 1258, 278 }, { 1625, 364 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1362, 80 }, new Integer[][] {
				{ 1276, 176 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1276, 176 }, new Integer[][] {
				{ 1362, 80 }, { 1394, 196 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1394, 196 }, new Integer[][] {
				{ 1276, 176 }, { 1368, 342 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1368, 342 }, new Integer[][] {
				{ 1394, 196 }, { 1289, 507 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1112, 494 }, new Integer[][] {
				{ 1024, 440 }, { 1304, 702 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1304, 702 }, new Integer[][] {
				{ 1112, 494 }, { 1430, 520 }, { 1288, 794 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1430, 520 }, new Integer[][] {
				{ 1340, 614 }, { 1304, 702 }, { 1500, 632 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1340, 614 }, new Integer[][] {
				{ 1289, 507 }, { 1430, 520 }, { 1288, 794 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1288, 794 }, new Integer[][] {
				{ 1340, 614 }, { 1734, 856 }, { 1304, 702 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1500, 632 }, new Integer[][] {
				{ 1430, 520 }, { 1670, 545 }, { 1412, 976 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1670, 545 }, new Integer[][] {
				{ 1500, 632 }, { 1744, 730 }, { 1850, 364 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1850, 364 }, new Integer[][] {
				{ 1670, 545 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1854, 494 }, new Integer[][] {
				{ 1625, 364 }, { 1994, 584 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1994, 584 }, new Integer[][] {
				{ 1854, 494 }, { 2110, 518 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 2210, 518 }, new Integer[][] {
				{ 1994, 584 }, { 2222, 582 }, { 2106, 650 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 2222, 582 }, new Integer[][] {
				{ 2110, 518 }, { 0 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 2224, 714 }, new Integer[][] {
				{ 2106, 650 }, { 1994, 718 }, { 0 }, { -1, -1 } });// secret
																	// passage
		adjacentClearings.put(new Integer[] { 2106, 650 }, new Integer[][] {
				{ 1994, 718 }, { 2224, 714 }, { 2110, 518 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1994, 718 }, new Integer[][] {
				{ 1850, 778 }, { 2106, 650 }, { 2224, 714 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1850, 778 }, new Integer[][] {
				{ 1994, 718 }, { 1820, 996 }, { 1836, 892 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1820, 996 }, new Integer[][] {
				{ 1836, 892 }, { 1850, 778 }, { 1734, 856 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1836, 892 }, new Integer[][] {
				{ 1734, 856 }, { 1820, 996 }, { 1850, 778 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1734, 856 }, new Integer[][] {
				{ 1836, 892 }, { 1288, 794 }, { 1820, 996 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1744, 730 }, new Integer[][] {
				{ 1670, 545 }, { 1614, 898 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1614, 898 }, new Integer[][] {
				{ 1412, 976 }, { 1744, 730 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1412, 976 }, new Integer[][] {
				{ 1500, 632 }, { 1614, 898 }, { 1364, 1206 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1108, 926 }, new Integer[][] {
				{ 926, 750 }, { 1250, 1074 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1250, 1074 }, new Integer[][] {
				{ 1108, 926 }, { 1100, 1290 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1100, 1290 }, new Integer[][] {
				{ 1250, 1074 }, { 1222, 1476 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1364, 1206 }, new Integer[][] {
				{ 1412, 976 }, { 1416, 1380 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1416, 1380 },
				new Integer[][] { { 1364, 1206 }, { 1626, 1356 },
						{ 1336, 1516 }, { 1280, 1372 } });// secret passage
		adjacentClearings.put(new Integer[] { 1626, 1356 }, new Integer[][] {
				{ 1416, 1380 }, { 1666, 1250 }, { 1740, 1380 }, { -1, -1 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 1666, 1250 }, new Integer[][] {
				{ 1626, 1356 }, { 1740, 1380 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1740, 1380 }, new Integer[][] {
				{ 1666, 1250 }, { 1834, 1334 }, { 1626, 1356 }, { -1, -1 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 1834, 1334 }, new Integer[][] {
				{ 1740, 1380 }, { 1762, 1194 }, { 1878, 1234 }, { -1, -1 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 1762, 1194 }, new Integer[][] {
				{ 1834, 1334 }, { 1878, 1234 }, { -1, -1 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1878, 1234 }, new Integer[][] {
				{ 1762, 1194 }, { 1834, 1334 }, { -1, -1 }, { -1, -1 } });// secret
																			// passage
		adjacentClearings.put(new Integer[] { 1336, 1516 }, new Integer[][] {
				{ 1416, 1380 }, { 1280, 1372 }, { 1274, 1620 }, { -1, -1 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 1280, 1372 }, new Integer[][] {
				{ 1222, 1476 }, { 1336, 1516 }, { 1416, 1380 }, { -1, -1 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 1222, 1476 }, new Integer[][] {
				{ 1100, 1290 }, { 1280, 1372 }, { 1274, 1620 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1274, 1620 }, new Integer[][] {
				{ 1222, 1476 }, { 1428, 1622 }, { 1336, 1516 }, { -1, -1 } });// secret
																				// passage
		adjacentClearings.put(new Integer[] { 1428, 1622 }, new Integer[][] {
				{ 1274, 1620 }, { 1627, 1657 }, { 1364, 1806 }, { -1, -1 } });
		adjacentClearings.put(new Integer[] { 1364, 1806 }, new Integer[][] {
				{ 1481, 1999 }, { 1428, 1622 }, { -1, -1 }, { -1, -1 } });

	}

	public int getPlay() {
		return play;
	}

	public int getChat() {
		return chat;
	}

	public int getWaitingMove() {
		return waitingMove;
	}

	public int getWaitingCombatMove() {
		return waitingCombatMove;
	}

	public int getSearch() {
		return search;
	}

	public ArrayList<String> getUserClassData() {
		return userClassData;
	}

	public HashMap<String, String> getUserStatusData() {
		return userStatusData;
	}

	public HashMap<String, Integer[]> getUserVictoryPoints() {
		return userVictoryPoints;
	}

	public HashMap<String, Integer> getUserClassVulnerability() {
		return userClassVulnerability;
	}

	public HashMap<String, String[]> getInventoryData() {
		return inventoryData;
	}

	public HashMap<String, String[]> getInactiveInventoryData() {
		return inactiveInventoryData;
	}

	public HashMap<String, String[]> getWoundedInventoryData() {
		return woundedInventoryData;
	}

	public HashMap<String, String[]> getFatiguedInventoryData() {
		return fatiguedInventoryData;
	}

	public HashMap<String, String[]> getPlayerMoves() {
		return playerMoves;
	}

	public HashMap<Integer[], String[]> getClearingInventoryData() {
		return clearingInventoryData;
	}

	public HashMap<Integer[], String> getMonsterLocations() {
		return monsterLocations;
	}

	public HashMap<String, Integer[]> getPlayerLocationsData() {
		return playerLocationsData;
	}

	public HashMap<String, Integer[]> getDwellingLocationsData() {
		return dwellingLocationsData;
	}

	public HashMap<Integer[], Integer[][]> getAdjacentClearings() {
		return adjacentClearings;
	}

	public HashMap<String, String[]> getUserCombatActions() {
		return userCombatActions;
	}

	public HashMap<String, Integer[]> getWeaponData() {
		return weaponData;
	}

	public void setPlay(int play) {
		this.play = play;
	}

	public void setChat(int chat) {
		this.chat = chat;
	}

	public void setWaitingMove(int waitingMove) {
		this.waitingMove = waitingMove;
	}

	public void setWaitingCombatMove(int waitingCombatMove) {
		this.waitingCombatMove = waitingCombatMove;
	}

	public void setSearch(int search) {
		this.search = search;
	}

	public void setUserClassData(ArrayList<String> userClassData) {
		this.userClassData = userClassData;
	}

	public void setUserStatusData(HashMap<String, String> userStatusData) {
		this.userStatusData = userStatusData;
	}

	public void setUserVictoryPoints(
			HashMap<String, Integer[]> userVictoryPoints) {
		this.userVictoryPoints = userVictoryPoints;
	}

	public void setUserClassVulnerability(
			HashMap<String, Integer> userClassVulnerability) {
		this.userClassVulnerability = userClassVulnerability;
	}

	public void setInventoryData(HashMap<String, String[]> inventoryData) {
		this.inventoryData = inventoryData;
	}

	public void setInactiveInventoryData(
			HashMap<String, String[]> inactiveInventoryData) {
		this.inactiveInventoryData = inactiveInventoryData;
	}

	public void setWoundedInventoryData(
			HashMap<String, String[]> woundedInventoryData) {
		this.woundedInventoryData = woundedInventoryData;
	}

	public void setFatiguedInventoryData(
			HashMap<String, String[]> fatiguedInventoryData) {
		this.fatiguedInventoryData = fatiguedInventoryData;
	}

	public void setPlayerMoves(HashMap<String, String[]> playerMoves) {
		this.playerMoves = playerMoves;
	}

	public void setClearingInventoryData(
			HashMap<Integer[], String[]> clearingInventoryData) {
		this.clearingInventoryData = clearingInventoryData;
	}

	public void setMonsterLocations(HashMap<Integer[], String> monsterLocations) {
		this.monsterLocations = monsterLocations;
	}

	public void setPlayerLocationsData(
			HashMap<String, Integer[]> playerLocationsData) {
		this.playerLocationsData = playerLocationsData;
	}

	public void setDwellingLocationsData(
			HashMap<String, Integer[]> dwellingLocationsData) {
		this.dwellingLocationsData = dwellingLocationsData;
	}

	public void setAdjacentClearings(
			HashMap<Integer[], Integer[][]> adjacentClearings) {
		this.adjacentClearings = adjacentClearings;
	}

	public void setUserCombatActions(HashMap<String, String[]> userCombatActions) {
		this.userCombatActions = userCombatActions;
	}

	public void setWeaponData(HashMap<String, Integer[]> weaponData) {
		this.weaponData = weaponData;
	}

	public String toString() {
		return "MagicRealmVariables";
	}

}
