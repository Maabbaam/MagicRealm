package models;
import java.io.Serializable;

public class ChatMessage implements Serializable
{
	protected static final long serialVersionUID = 1112122200L;
	public static final int WHOISIN = 0;
	public static final int MESSAGE = 1;
	public static final int LOGOUT = 2;
	public static final int VICTORY = 3;
	public static final int CONNECT = 4;
	public static final int MOVE = 5;
	public static final int CHAT = 6;
	public static final int COMBATMOVE = 7;
	public static final int DEATH = 8;
	public static final int SEARCH = 9;
	public static final int TRADESEARCH = 10;
	public static final int TRADEACCEPT = 11;
	public static final int TRADEOFFER = 12;
	public static final int QUICKMOVE = 13;
	public static final int UPDATE = 14;
	public static final int HIRED = 15;
	public static final int MONSTERCOMBAT = 16;
	public static final int NATIVECOMBAT = 17;
	public static final int FAME = 18;
	public static final int NOTORIETY = 19;
	public static final int GOLD = 20;
	public static final int SPELL = 21;
	public static final int LOCATION = 22;
	public static final int TREASURE = 23;
	public static final int INVENTORY = 24;
	public static final int NULL = -1;
	public int type;
	public Object message;
	public String userClass;
	
	public ChatMessage(int type, Object message, String userClass)
	{
		this.type = type;
		this.message = message;
		this.userClass = userClass;
	}
	public String toString()
	{
		return userClass + "'s message";
	}
	public int getType()
	{
		return this.type;
	}
	
	public Object getMessage()
	{
		return this.message;
	}	
	public String getUserClass()
	{
		return this.userClass;
	}

	public static int getWhoisin() {
		return WHOISIN;
	}
	
	public static int getLogout() {
		return LOGOUT;
	}

	public static int getVictory() {
		return VICTORY;
	}

	public static int getConnect() {
		return CONNECT;
	}

	public static int getMove() {
		return MOVE;
	}

	public static int getChat() {
		return CHAT;
	}

	public static int getCombatmove() {
		return COMBATMOVE;
	}

	public static int getDeath() {
		return DEATH;
	}

	public static int getSearch() {
		return SEARCH;
	}
}
