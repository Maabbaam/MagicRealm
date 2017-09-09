package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicArrowButton;

import models.Dice;
import models.MagicRealmVariables;

public class MagicRealmGUI extends JFrame
{

	private static final long serialVersionUID = -3634301913546867067L;
	JViewport viewport,viewport2;
	public MagicRealmVariables m;
	JFrame gameOverFrame, infoframe, searchframe, charframe, victorypointframe, combatframe, joinframe, restframe, nativesframe, sideframe, dwellingframe, tradeframe,followFrame, followSearchFrame, tradeSearchFrame, weaponinfoframe;
	JPanel mapPanel;
	JLabel locations,treasures,spells,fame,notoriety,goldPoints,siteChit,soundChit, theOffer,theirOffer,yourOffer,selectedChar,charTitle, mapLabel, chitLabel, chitLabel2, chitLabel3, chitLabel4, selectedLoc;
	JTextArea username,textBox, chatBox;
	JList<String> restWoundedList, restFatiguedList, tradePlayers, nativesList, followPlayers, tradeList, userList, searchList, actionList, inventoryList, inactiveInventoryList, fatiguedInventoryList, woundedInventoryList, combatPlayerList;
	JButton rest, selectVictoryConditions, barter, reject, choose, cancel, okButton, runButton, selectLoc,inn,chapel,house,guard,ghost,hide,follow,search,alert,trade,ok,joinGame,chat,mingle,swing,thrust,smash,dodge,duck,charge,selectDwelling,lootButton,peerButton,locateButton;
	JScrollPane combatInventoryListScrollPanel, tradeScrollPanel, tradePlayerScrollPanel, mapScrollPanel,tempScrollPanel, searchListScrollPanel, inventoryListScrollPanel, inactiveInventoryListScrollPanel, fatiguedInventoryListScrollPanel, woundedInventoryListScrollPanel, playerListScrollPanel;
	DefaultListModel<String> actionModel = new DefaultListModel<String>();
	DefaultListModel<String> userModel = new DefaultListModel<String>();
	DefaultListModel<String> searchModel = new DefaultListModel<String>();
	DefaultListModel<String> inventoryModel = new DefaultListModel<String>();
	DefaultListModel<String> combatInventoryModel = new DefaultListModel<String>();
	DefaultListModel<String> inactiveInventoryModel = new DefaultListModel<String>();
	DefaultListModel<String> fatiguedInventoryModel = new DefaultListModel<String>();
	DefaultListModel<String> woundedInventoryModel = new DefaultListModel<String>();
	DefaultListModel<String> combatPlayerModel = new DefaultListModel<String>();
	DefaultListModel<String> tradePlayerModel = new DefaultListModel<String>();
	DefaultListModel<String> nativesModel = new DefaultListModel<String>();
	DefaultListModel<String> followPlayerModel = new DefaultListModel<String>();
	DefaultListModel<String> tradeListModel = new DefaultListModel<String>();
	DefaultListModel<String> restFatiguedModel = new DefaultListModel<String>();
	DefaultListModel<String> restWoundedModel = new DefaultListModel<String>();
	DefaultListModel<String> woundFatiguedModel = new DefaultListModel<String>();
	DefaultListModel<String> woundActiveModel = new DefaultListModel<String>();
	DefaultListModel<String> woundInactiveModel =  new DefaultListModel<String>();
	BasicArrowButton upGold,downGold,upNotoriety,downNotoriety,upFame,downFame,upSpell,downSpell,upTres,downTres,upLoc, downLoc;
	int locationsFound = 0;
	int woundcount = 0;
	int treasuresFound = 0;
	int spellsFound = 0;
	int fameFound = 0;
	int notorietyFound = 0;
	int goldFound = 0;
	int pointsSpent = 0;
	int numActions = 0;
	boolean inCave = false;
	boolean inSecretPassage = false;
	boolean usingTelescope = false;
	MapImage newMap = new MapImage();
	Integer[] oldLoc = new Integer[2];
	BufferedImage map;
	boolean isMoving = false;
	boolean running = false;
	boolean fatigued = false;
	boolean wounded = false;
	boolean fatigued2 = false;
	boolean wounded2 = false;
	boolean poultice = false;
	boolean shieldDodge = false;
	boolean shieldDuck = false;
	boolean shieldCharge = false;
	boolean foundActive = false;
	boolean foundInactive = false;
	boolean foundFatigued = false;
	String activeItem = "";
	Icon mapIcon, icon;
	public String activeTWT,partner, selectedChit2, selectedChit, followTarget, chatMessage, attackType, maneuverType, targetPlayer, activeWeapon, message, searchTarget, tradeOffer, acceptedItem, offeredItem;

	boolean initialzed = false;
	boolean usedSpectacles = false;
	boolean usedRegent = false;
	boolean usedSceptre = false;
	boolean usedCloak = false;
	boolean usedLantern = false;
	boolean usedBerserkWhite = false;
	boolean usedElusiveness = false;


	JFrame mainframe = new JFrame("Magic Realms");
	public void removeMinMaxClose(Component comp)
	{
		if(comp instanceof AbstractButton)
		{
			comp.getParent().remove(comp);
		}
		if (comp instanceof Container)
		{
			Component[] comps = ((Container)comp).getComponents();
			for(int x = 0, y = comps.length; x < y; x++)
			{
				removeMinMaxClose(comps[x]);
			}
		}
	}

	public JButton getMove(){
		return follow;
	}
	public JScrollPane getMapScrollPanel(){
		return mapScrollPanel;
	}
	public void setMapScrollPanel(JScrollPane panel){
		mapScrollPanel = panel;
	}
	public MagicRealmGUI(MagicRealmVariables m)
	{
		this.m = m;
		MagicRealmGUIJoinScreen(m);
	}
	public void sendChatMessage(String msg)
	{
		chatMessage = msg + "," + m.getUserClassData().get(0);
		m.setChat(1);
		chatBox.setText("");
	}
	public void setScrollPanel(JScrollPane pane)
	{
		mapScrollPanel = pane;
	}

	public void updateMap(MagicRealmVariables m){

		System.out.println("\t---\tUPDATE THE MAP");
		paintMap(m);

	}
	public void updateText(String msg)
	{
		textBox.setText(textBox.getText() + msg + "\n");
	}
	public void MagicRealmGUILootScreen(MagicRealmVariables m)
	{
		JFrame lootframe = new JFrame("Loot");
		lootframe.setSize(635, 382);
		lootframe.setLocation(400,300);

		lootframe.setResizable(false);
		try {
			lootframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("loot.jpg")))));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		searchModel.removeAllElements();
		//add all elements in the clearing, eg. items and treasures.
		for(Integer[] s : m.getClearingInventoryData().keySet())
		{
			if ((s[0].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[0]))
					&&(s[1].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[1])))
			{
				for (String findings : m.getClearingInventoryData().get(s))
					searchModel.addElement(findings);

			}
		}
		for(String s : m.siteChitLocation.keySet())
		{
			//if there is a sitechit at your location
			if ((m.siteChitLocation.get(s)[0].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[0]))
					&&(m.siteChitLocation.get(s)[1].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[1])))
			{
				if(m.siteChitData.get(s)!=null
						&& !(m.siteChitData.get(s).length==0))
				{
					if (m.playerSitesFound.get(m.getUserClassData().get(0)).get(m.siteChits.indexOf(s)) == 1)
						//if this is a sitechit that is discovered
						//if sitechit contains treasures, find it
					{
						if (m.siteChitData.get(s)!=null 
								&& m.siteChitData.get(s)[0]!=""
								&& m.siteChitData.get(s).length!=0)
						{
							searchModel.addElement(s);	
							break;
						}
					}
				}
			}
		}
		searchList = new JList<String>(searchModel);
		JScrollPane searchListScrollPanel = new JScrollPane(searchList);
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{

				if (e.getClickCount() == 2)
				{
					int index = searchList.locationToIndex(e.getPoint());
					if (SwingUtilities.isRightMouseButton(e) || SwingUtilities.isLeftMouseButton(e))
					{
						searchList.setSelectedIndex(index);

						//Check if user clicked on weapon
						for (String z : m.weaponData.keySet()) {
							if (searchList.getSelectedValue() != null 
									&& !searchList.getSelectedValue().equals("")
									&& searchList.getSelectedValue().equals(z))
								MagicRealmGUIWeaponInfoScreen(m, searchList.getSelectedValue());
						}
						//check if user clicked on treasure
						for (String z : m.treasureProp.keySet()) {
							if (searchList.getSelectedValue() != null 
									&& !searchList.getSelectedValue().equals("")
									&& (searchList.getSelectedValue().equals(z)))
								MagicRealmGUITreasureInfoScreen(m, searchList.getSelectedValue());
						}
					}
				}
			}
		};
		searchList.addMouseListener(mouseListener);
		searchListScrollPanel.setSize(350, 210);
		searchListScrollPanel.setLocation(125, 5);
		lootframe.add(searchListScrollPanel);

		//For search frame

		JButton loot = new JButton("Loot");
		loot.setLocation(268,250);
		loot.setSize(80,70);

		loot.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//SAY YOU LOOTED SOMETHING , THIS FUNCTION ADDS THE ITEM TO INACTIVE INVENTORY ASWELL.
				if (searchList.getSelectedValue() != null)
				{
					message = searchList.getSelectedValue() + "," + m.getPlayerLocationsData().get(m.getUserClassData().get(0))[0]
							+ "," + (m.getPlayerLocationsData().get(m.getUserClassData().get(0))[1]);
					m.setSearch(1);

					lootframe.setVisible(false);
					if (usingTelescope)
					{
						usingTelescope = false;
						hide.setEnabled(true);
						trade.setEnabled(true);
						alert.setEnabled(true);
						follow.setEnabled(true);
						hide.setEnabled(true);
						trade.setEnabled(true);
						alert.setEnabled(true);
						follow.setEnabled(true);
						mingle.setEnabled(false);
						for (Integer[] nativeLoc : m.nativeLocation.values())
							if (nativeLoc[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
									&& nativeLoc[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
							{
								mingle.setEnabled(true);
								break;
							}
						search.setEnabled(true);
						rest.setEnabled(true);
						inventoryList.setEnabled(true);
						inactiveInventoryList.setEnabled(true);
						fatiguedInventoryList.setEnabled(true);
						woundedInventoryList.setEnabled(true);
						ok.setEnabled(true);
						m.playerLocationsData.put(m.userClassData.get(0), oldLoc);
					}
				}



			}
		});
		lootframe.add(loot);
		lootframe.setVisible(true);
		lootframe.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	if (usingTelescope)
				{
					
		    		usingTelescope = false;
					hide.setEnabled(true);
					trade.setEnabled(true);
					alert.setEnabled(true);
					follow.setEnabled(true);
					hide.setEnabled(true);
					trade.setEnabled(true);
					alert.setEnabled(true);
					follow.setEnabled(true);
					mingle.setEnabled(false);
					for (Integer[] nativeLoc : m.nativeLocation.values())
						if (nativeLoc[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
								&& nativeLoc[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
						{
							mingle.setEnabled(true);
							break;
						}
					search.setEnabled(true);
					rest.setEnabled(true);
					inventoryList.setEnabled(true);
					inactiveInventoryList.setEnabled(true);
					fatiguedInventoryList.setEnabled(true);
					woundedInventoryList.setEnabled(true);
					ok.setEnabled(true);
					m.playerLocationsData.put(m.userClassData.get(0), oldLoc);
				}
		    }
		});


	}
	public void MagicRealmGUIGameOver()
	{

		gameOverFrame = new JFrame("Game Over");

		gameOverFrame.setLocationRelativeTo(null);
		gameOverFrame.setLocation(gameOverFrame.getLocation().x-150,gameOverFrame.getLocation().y-100);
		gameOverFrame.getContentPane().setBackground(Color.black);
		gameOverFrame.setSize(305, 200);
		JButton cntinue =  new JButton("Continue");
		cntinue.setSize(90,25);
		cntinue.setLocation(10, 130);
		cntinue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gameOverFrame.setVisible(false);
				charframe.setVisible(true);
			}
		});
		gameOverFrame.add(cntinue);
		JButton quit =  new JButton("Quit");
		quit.setSize(90,25);
		quit.setLocation(190, 130);
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				quit.setVisible(false);
				System.exit(0);
			}
		});
		gameOverFrame.add(quit);
		JLabel gameOverLabel = new JLabel("Game Over", JLabel.CENTER);
		gameOverLabel.setSize(300,50);
		gameOverLabel.setLocation(0,20);
		gameOverLabel.setFont(new Font("Garamond", Font.BOLD, 50));
		gameOverLabel.setForeground(Color.cyan);
		gameOverLabel.setBackground(Color.black);
		gameOverLabel.setOpaque(true);
		gameOverFrame.add(gameOverLabel);

		JLabel MaxHanna = new JLabel("");
		gameOverFrame.add(MaxHanna);
		gameOverFrame.setVisible(true);
	}
	public void MagicRealmGUINatives(MagicRealmVariables m)
	{
		nativesframe = new JFrame("Mingle with Natives");
		nativesframe.setLocation(500,300);
		nativesframe.getContentPane().setBackground(Color.black);
		nativesframe.setSize(300, 275);

		JButton hire =  new JButton("Hire");
		hire.setSize(90,25);
		hire.setLocation(50, 140);
		hire.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (nativesList.getSelectedValue() == null || nativesList.getSelectedValue().equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Select a Native to hire", "Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else
				{
					//you just hired a native, this takes care of substracting data from inventory.
					for (int k = 0; k < m.getInactiveInventoryData().get(m.userClassData.get(0)).length; k++) 
					{
						if(m.getInactiveInventoryData().get(m.userClassData.get(0))[k] != null)
						{
							if(m.getInactiveInventoryData().get(m.userClassData.get(0))[k].contains("gold"))
							{
								int currentGold = 0;
								for (int i = 0; i < inactiveInventoryModel.size(); i++)
									if (inactiveInventoryModel.getElementAt(i) != null
									&& inactiveInventoryModel.getElementAt(i).contains("gold"))
									{
										String add1 = inactiveInventoryModel.getElementAt(i);
										String addDelims = "[ ]+";
										String[] goldTokens = add1.split(addDelims);
										currentGold += Integer.parseInt(goldTokens[0]);
									}
								int add2 = m.nativePrice.get(nativesList.getSelectedValue());
								int newValue= currentGold - add2;
								String newVal = newValue + " gold";

								m.getInactiveInventoryData().get(m.userClassData.get(0))[k] = newVal;
								for (int i = 0; i < inactiveInventoryModel.size(); i++)
									if (inactiveInventoryModel.getElementAt(i) != null
									&& inactiveInventoryModel.getElementAt(i).contains("gold"))
									{
										inactiveInventoryModel.removeElementAt(i);
										inactiveInventoryModel.addElement(newVal);
									}
							}
						}
					}


					nativesframe.setVisible(false);

					for (String spec : m.nativeGroup.get(nativesList.getSelectedValue()))
						m.nativesFollowing.add(spec);
					actionModel.addElement("Hired "+nativesList.getSelectedValue());
					setButtons();

				}
			}
		});
		nativesframe.add(hire);

		if (m.nativesFollowing.size() != 0)
		{
			JLabel following = new JLabel("Currently Following You: ",JLabel.CENTER);
			following.setSize(200,25);
			following.setLocation(50,170);
			following.setFont(new Font("Serif", Font.BOLD, 16));
			following.setForeground(Color.cyan);
			following.setBackground(Color.black);
			following.setOpaque(true);
			nativesframe.add(following);
			JLabel hiredx;

			int starty = 170;
			int framex = 300;
			int framey = 275;
			for(String hired : m.nativesFollowing)
			{
				framey = framey + 25;
				starty = starty + 25;
				hiredx = new JLabel(hired, JLabel.CENTER);
				hiredx.setLocation(50,starty);
				hiredx.setSize(200,25);

				hiredx.setFont(new Font("Serif", Font.PLAIN, 12));
				hiredx.setForeground(Color.cyan);
				hiredx.setBackground(Color.black);
				hiredx.setOpaque(true);
				nativesframe.add(hiredx);
				nativesframe.setSize(framex, framey);

			}
		}

		JButton cancel =  new JButton("Cancel");
		cancel.setSize(90,25);
		cancel.setLocation(160, 140);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				nativesframe.setVisible(false);
			}
		});
		nativesframe.add(cancel);

		nativesModel.removeAllElements();
		for(String natives : m.nativeLocation.keySet())
		{
			if (!m.nativesFollowing.contains(natives))
			{
				if (m.nativeLocation.get(natives) != null
						&& m.nativeLocation.get(natives)[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
						&& m.nativeLocation.get(natives)[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
				{
					int yourCurrentGold = 0;
					for (int i = 0; i < inactiveInventoryModel.size(); i++)
						if (inactiveInventoryModel.getElementAt(i) != null
						&& inactiveInventoryModel.getElementAt(i).contains("gold"))
						{
							String add1 = inactiveInventoryModel.getElementAt(i);
							String addDelims = "[ ]+";
							String[] goldTokens = add1.split(addDelims);
							yourCurrentGold += Integer.parseInt(goldTokens[0]);
						}

					if (m.nativePrice.get(natives) != null
							&& m.nativePrice.get(natives) < yourCurrentGold)
					{
						boolean found = false;
						for (String nativeG : m.nativeGroup.get(natives))
						{
							if (m.nativesFollowing.contains(nativeG))
								found = true;
						}
						if (!found)
						{
							nativesModel.addElement(natives);
						}
					}
				}
			}
		}
		JLabel mingleLabel = new JLabel("Buy Natives A Drink? : ", JLabel.CENTER);
		mingleLabel.setSize(250,25);
		mingleLabel.setLocation(25,20);
		mingleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		mingleLabel.setForeground(Color.cyan);
		mingleLabel.setBackground(Color.black);
		mingleLabel.setOpaque(true);
		nativesframe.add(mingleLabel);
		nativesList = new JList<String>(nativesModel);
		//tradePlayers.setLocation(0,0);
		nativesList.setSize(200,80);
		MouseAdapter mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{

				if (e.getClickCount() == 1)
				{
					//If the user selects the weapon, then presses the right click button twice....
					mingleLabel.setText("Buy Natives A Drink? : " + m.nativePrice.get(nativesList.getSelectedValue()) + " Gold");

				}
			}
		};
		nativesList.addMouseListener(mouseListener);

		JScrollPane nativesScrollPanel = new JScrollPane(nativesList);
		nativesScrollPanel.setSize(200,80);
		nativesScrollPanel.setLocation(50,50);

		nativesframe.add(nativesScrollPanel);
		JLabel ProfJeanPierreCorriveau = new JLabel("");
		nativesframe.add(ProfJeanPierreCorriveau);
		nativesframe.setVisible(true);
	}

	public void MagicRealmGUIFollow(MagicRealmVariables m)
	{
		followFrame = new JFrame("Follow");
		followFrame.setLocation(500,300);
		followFrame.getContentPane().setBackground(Color.black);
		followFrame.setSize(300, 275);

		JButton choose =  new JButton("choose");
		choose.setSize(90,25);
		choose.setLocation(50, 140);
		choose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (followPlayers.getSelectedValue() == null || followPlayers.getSelectedValue().equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Select a Character to Follow", "Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else
				{
					followFrame.setVisible(false);
					followTarget = followPlayers.getSelectedValue();
					m.follow = 1;
					actionModel.addElement("Following "+followTarget);
					setButtons();

				}
			}
		});
		followFrame.add(choose);

		if (followTarget != null && followTarget != "")
		{
			JLabel following = new JLabel("Currently Following: " + followTarget,JLabel.CENTER);
			following.setSize(200,25);
			following.setLocation(50,170);
			following.setFont(new Font("Serif", Font.PLAIN, 12));
			following.setForeground(Color.cyan);
			following.setBackground(Color.black);
			following.setOpaque(true);
			followFrame.add(following);
			JButton cancelFollow =  new JButton("Stop");
			cancelFollow.setSize(90,25);
			cancelFollow.setLocation(110, 195);
			cancelFollow.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					m.follow = 0;
					followFrame.setVisible(false);
					actionModel.addElement("Unfollowing " + followTarget);
					followTarget="";
					setButtons();

				}
			});
			followFrame.add(cancelFollow);
		}

		JButton cancel =  new JButton("Cancel");
		cancel.setSize(90,25);
		cancel.setLocation(160, 140);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				followFrame.setVisible(false);
			}
		});
		followFrame.add(cancel);

		followPlayerModel.removeAllElements();
		for(String s : m.getUserClassData())
		{
			if (!s.equals(m.getUserClassData().get(0)))
			{
				if (m.getPlayerLocationsData().get(s) != null
						&& m.getPlayerLocationsData().get(s)[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
						&& m.getPlayerLocationsData().get(s)[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
				{
					if (!followPlayerModel.contains(s))
						followPlayerModel.addElement(s);
				}
			}
		}
		JLabel followLabel = new JLabel("Follow", JLabel.CENTER);
		followLabel.setSize(200,25);
		followLabel.setLocation(50,20);
		followLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		followLabel.setForeground(Color.cyan);
		followLabel.setBackground(Color.black);
		followLabel.setOpaque(true);
		followFrame.add(followLabel);
		followPlayers = new JList<String>(followPlayerModel);
		//tradePlayers.setLocation(0,0);
		followPlayers.setSize(200,80);

		JScrollPane followPlayerScrollPanel = new JScrollPane(followPlayers);
		followPlayerScrollPanel.setSize(200,80);
		followPlayerScrollPanel.setLocation(50,50);

		followFrame.add(followPlayerScrollPanel);
		JLabel MaxHanna = new JLabel("");
		followFrame.add(MaxHanna);
		followFrame.setVisible(true);
	}

	public void MagicRealmGUIWoundingScreen(MagicRealmVariables m)
	{
		woundcount++;
		JFrame woundframe = new JFrame(m.userClassData.get(0) + " : Wound " + woundcount);
		woundframe.getContentPane().setBackground(Color.black);
		woundframe.setResizable(false);
		woundframe.setUndecorated(true);
		woundframe.setSize(612, 520);

		//woundframe.setUndecorated(true);
		//woundframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton accept =  new JButton("Accept");
		accept.setSize(90,25);
		accept.setLocation(500, 450);
		accept.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (selectedChit == null || selectedChit.equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Select a Chit to Wound", "Rest Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else
				{
					woundframe.dispose();
					woundcount--;
					//add it to woundedInventory
					//ITEM SPECIAL ABILITY : VIAL OF HEALING
					if (inventoryModel.contains("Vial of Healing"))
					{
						activeItem = selectedChit;
						m.addFatigued = 1;
						updateText("The Vial of Healing Alleviates Your Wounds");
					}
					else {
						activeItem = selectedChit;
						m.addWounded = 1;
					}
					if(foundActive){
						//remove it from inventory
						activeItem = selectedChit;
						m.removeActive = 1;
						woundActiveModel.removeElement(selectedChit);
					}else if(foundInactive){
						//remove it from inactiveInventory
						activeItem = selectedChit;
						m.removeInactive = 1;
						woundInactiveModel.removeElement(selectedChit);

					}
					else if(foundFatigued){
						//remove it from fatiguedInventory

						activeItem = selectedChit;
						m.removeFatigued = 1;
						woundFatiguedModel.removeElement(selectedChit);
						
					}
					selectedChit = "";
				}					
			}
		});
		woundframe.add(accept);
		JLabel select =  new JLabel("Select A Chit" , JLabel.CENTER);
		select.setSize(300,25);
		select.setLocation(150, 30);
		select.setFont(new Font("Serif", Font.PLAIN, 18));
		select.setForeground(Color.cyan);
		select.setBackground(Color.black);
		select.setOpaque(true);
		woundframe.add(select);

		JLabel activeInvLabel =  new JLabel("Active Inventory" , JLabel.CENTER);
		activeInvLabel.setSize(300,23);
		activeInvLabel.setLocation(150, 55);
		activeInvLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		activeInvLabel.setForeground(Color.cyan);
		activeInvLabel.setBackground(Color.black);
		activeInvLabel.setOpaque(true);
		woundframe.add(activeInvLabel);

		JLabel inactiveInvLabel =  new JLabel("Inactive Inventory" , JLabel.CENTER);
		inactiveInvLabel.setSize(300,23);
		inactiveInvLabel.setLocation(150, 205);
		inactiveInvLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		inactiveInvLabel.setForeground(Color.cyan);
		inactiveInvLabel.setBackground(Color.black);
		inactiveInvLabel.setOpaque(true);
		woundframe.add(inactiveInvLabel);

		JLabel fatiguedLabel =  new JLabel("Fatigued Inventory" , JLabel.CENTER);
		fatiguedLabel.setSize(300,23);
		fatiguedLabel.setLocation(150, 330);
		fatiguedLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		fatiguedLabel.setForeground(Color.cyan);
		fatiguedLabel.setBackground(Color.black);
		fatiguedLabel.setOpaque(true);
		woundframe.add(fatiguedLabel);

		JLabel selectedLabel =  new JLabel("" , JLabel.CENTER);
		selectedLabel.setSize(300,23);
		selectedLabel.setLocation(150, 450);
		selectedLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		selectedLabel.setForeground(Color.cyan);
		selectedLabel.setBackground(Color.black);
		selectedLabel.setOpaque(true);
		woundframe.add(selectedLabel);



		//Wound Active Inventory MODEL + LIST + SCROLLPANE
		woundActiveModel.removeAllElements();
		for (int s = 0; s < inventoryModel.getSize(); s++)
		{
			if (inventoryModel.getElementAt(s).contains("MOVE") || inventoryModel.getElementAt(s).contains("FIGHT"))
			{
				woundActiveModel.addElement(inventoryModel.getElementAt(s));
			}
		}
		JList<String> woundActiveList = new JList<String>(woundActiveModel);
		woundActiveList.setSize(300,100);
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{

				if (sideframe != null && !sideframe.isVisible())
					woundframe.setVisible(false);
				if (e.getClickCount() == 1)
				{
					selectedChit = woundActiveList.getSelectedValue();
					selectedLabel.setText(selectedChit);
					foundFatigued = false;
					foundActive = true;
					foundInactive = false;
				}
			}
		};
		woundActiveList.addMouseListener(mouseListener);

		JScrollPane activeInvScrollPanel = new JScrollPane(woundActiveList);
		activeInvScrollPanel.setSize(300,100);
		activeInvScrollPanel.setLocation(150, 75);
		woundframe.add(activeInvScrollPanel);

		//Wound Inactive Inventory MODEL + LIST + SCROLLPANE
		woundInactiveModel.removeAllElements();
		for (int s = 0; s < inactiveInventoryModel.getSize(); s++)
		{
			if (inactiveInventoryModel.getElementAt(s).contains("MOVE") || inactiveInventoryModel.getElementAt(s).contains("FIGHT"))
			{
				woundInactiveModel.addElement(inactiveInventoryModel.getElementAt(s));

			}
		}
		JList<String> woundInactiveList = new JList<String>(woundInactiveModel);
		woundInactiveList.setSize(300,100);
		MouseListener mouseListener2 = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (sideframe != null && !sideframe.isVisible())
					woundframe.setVisible(false);
				if (e.getClickCount() == 1)
				{
					selectedChit = woundInactiveList.getSelectedValue();
					selectedLabel.setText(selectedChit);
					foundFatigued = false;
					foundActive = false;
					foundInactive = true;
				}
			}
		};
		woundInactiveList.addMouseListener(mouseListener2);

		JScrollPane inactiveInvScrollPanel = new JScrollPane(woundInactiveList);
		inactiveInvScrollPanel.setSize(300,100);
		inactiveInvScrollPanel.setLocation(150, 225);
		woundframe.add(inactiveInvScrollPanel);

		//Wound FATIGUED MODEL + LIST + SCROLLPANE
		woundFatiguedModel.removeAllElements();
		for (int s = 0; s < fatiguedInventoryModel.getSize(); s++)
		{
			if (fatiguedInventoryModel.getElementAt(s).contains("MOVE") || fatiguedInventoryModel.getElementAt(s).contains("FIGHT"))
			{
				woundFatiguedModel.addElement(fatiguedInventoryModel.getElementAt(s));
			}
		}
		JList<String> woundFatiguedList = new JList<String>(woundFatiguedModel);
		woundFatiguedList.setSize(300,100);
		MouseListener mouseListener3 = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (sideframe != null && !sideframe.isVisible())
					woundframe.setVisible(false);
				if (e.getClickCount() == 1)
				{

					selectedChit = woundFatiguedList.getSelectedValue();
					selectedLabel.setText(selectedChit);

					foundFatigued = true;
					foundActive = false;
					foundInactive = false;

				}
			}
		};
		woundFatiguedList.addMouseListener(mouseListener3);

		JScrollPane fatiguedScrollPanel = new JScrollPane(woundFatiguedList);
		fatiguedScrollPanel.setSize(300,100);
		fatiguedScrollPanel.setLocation(150, 350);
		woundframe.add(fatiguedScrollPanel);

		//if there are no longer any chits to wound, then you are dead.
		if (woundFatiguedModel.isEmpty() && woundActiveModel.isEmpty() && woundInactiveModel.isEmpty())
		{
			m.dead=1;
		}
		JLabel Howard = new JLabel ("");
		woundframe.add(Howard);
		woundframe.setVisible(true);
		if (sideframe != null && !sideframe.isVisible())
			woundframe.setVisible(false);
	}


	public void MagicRealmGUIRestFrame(MagicRealmVariables m)
	{
		restframe = new JFrame(m.userClassData.get(0) + " : Resting");
		restframe.getContentPane().setBackground(Color.black);

		for (int i = 0; i< inventoryModel.size(); i++)
		{
			if (inventoryModel.getElementAt(i)!=null && inventoryModel.getElementAt(i).equals("Poultice of Health"))
			{
				poultice = true;
			}
		}
		restframe.setResizable(false);
		restframe.setSize(612, 520);
		JButton accept =  new JButton("Accept");
		accept.setSize(90,25);
		accept.setLocation(500, 450);
		accept.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (selectedChit == null || selectedChit.equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Select a Chit to Rest", "Rest Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else
				{

					if (wounded)
					{
						//add it to fatiguedInventory
						activeItem = selectedChit;
						m.addFatigued = 1;

						//remove it from woundedInventory

						activeItem = selectedChit;
						m.removeWounded = 1;
					}
					else if (fatigued)
					{
						//add it to inventory
						activeItem = selectedChit;
						m.addActive = 1;
						//remove it from fatiguedInventory
						activeItem = selectedChit;
						m.removeFatigued = 1;
					}
					if (wounded2)
					{
						//add it to fatiguedInventory
						activeItem = selectedChit2;
						m.addFatigued = 1;

						//remove it from woundedInventory
						activeItem = selectedChit2;
						m.removeWounded = 1;
					}
					else if(fatigued2)
					{
						//add it to inventoryModel
						inventoryModel.addElement(selectedChit2);
						//add it to m.inventoryData
						String[] tmpInv = new String[m.inventoryData.get(m.userClassData.get(0)).length + 1];
						System.arraycopy(m.inventoryData.get(m.userClassData.get(0)), 0, tmpInv, 0,
								Math.min(m.inventoryData.get(m.userClassData.get(0)).length, m.inventoryData.get(m.userClassData.get(0)).length + 1));
						tmpInv[tmpInv.length-1] = selectedChit2;
						m.inventoryData.put(m.userClassData.get(0), tmpInv);

						//remove it from fatiguedInventoryModel
						for (int i=0; i<fatiguedInventoryModel.size(); i++)
							if (fatiguedInventoryModel.get(i).equals(selectedChit2))
							{
								fatiguedInventoryModel.remove(i);
								break;
							}
						//remove it from m.fatiguedInventoryData
						final String[] n2 = new String[m.fatiguedInventoryData.get(m.userClassData.get(0)).length]; 
						System.arraycopy(m.fatiguedInventoryData.get(m.userClassData.get(0)), 0, n2, 0, n2.length);
						for (int i = 0, j = 0; i < m.fatiguedInventoryData.get(m.userClassData.get(0)).length; i++)
						{
							if (!m.fatiguedInventoryData.get(m.userClassData.get(0))[i].equals(selectedChit2))
							{
								n2[j] = m.fatiguedInventoryData.get(m.userClassData.get(0))[i];
								j++;
							}      
						}
						m.fatiguedInventoryData.put(m.userClassData.get(0), n2);
					}
					restframe.setVisible(false);
				}
			}
		});
		restframe.add(accept);


		JLabel select =  new JLabel("Select A Chit" , JLabel.CENTER);
		select.setSize(300,25);
		select.setLocation(150, 30);
		select.setFont(new Font("Serif", Font.PLAIN, 18));
		select.setForeground(Color.cyan);
		select.setBackground(Color.black);
		select.setOpaque(true);
		restframe.add(select);
		JLabel fatiguedLabel =  new JLabel("Fatigued Inventory" , JLabel.CENTER);
		fatiguedLabel.setSize(300,23);
		fatiguedLabel.setLocation(150, 55);
		fatiguedLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		fatiguedLabel.setForeground(Color.cyan);
		fatiguedLabel.setBackground(Color.black);
		fatiguedLabel.setOpaque(true);
		restframe.add(fatiguedLabel);

		JLabel woundedLabel =  new JLabel("Wounded Inventory" , JLabel.CENTER);
		woundedLabel.setSize(300,23);
		woundedLabel.setLocation(150, 225);
		woundedLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		woundedLabel.setForeground(Color.cyan);
		woundedLabel.setBackground(Color.black);
		woundedLabel.setOpaque(true);
		restframe.add(woundedLabel);

		JLabel selectedLabel =  new JLabel("" , JLabel.CENTER);
		selectedLabel.setSize(300,23);
		selectedLabel.setLocation(150, 405);
		selectedLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		selectedLabel.setForeground(Color.cyan);
		selectedLabel.setBackground(Color.black);
		selectedLabel.setOpaque(true);
		restframe.add(selectedLabel);

		JLabel selectedLabel2 =  new JLabel("" , JLabel.CENTER);
		if (poultice)
		{
			JLabel poulticeLabel =  new JLabel("Poultice of Health Active" , JLabel.CENTER);
			poulticeLabel.setSize(300,23);
			poulticeLabel.setLocation(150, 430);
			poulticeLabel.setFont(new Font("Serif", Font.PLAIN, 14));
			poulticeLabel.setForeground(Color.cyan);
			poulticeLabel.setBackground(Color.black);
			poulticeLabel.setOpaque(true);
			restframe.add(poulticeLabel);


			selectedLabel2.setSize(300,23);
			selectedLabel2.setLocation(150, 455);
			selectedLabel2.setFont(new Font("Serif", Font.PLAIN, 14));
			selectedLabel2.setForeground(Color.cyan);
			selectedLabel2.setBackground(Color.black);
			selectedLabel2.setOpaque(true);
			restframe.add(selectedLabel2);
		}



		//REST FATIGUED MODEL + LIST + SCROLLPANE
		restFatiguedModel.removeAllElements();
		for (int s = 0; s < fatiguedInventoryModel.getSize(); s++)
		{
			restFatiguedModel.addElement(fatiguedInventoryModel.getElementAt(s));
		}
		restFatiguedList = new JList<String>(restFatiguedModel);
		restFatiguedList.setSize(300,150);
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					if (restFatiguedList.getSelectedValue().contains("MOVE") || restFatiguedList.getSelectedValue().contains("FIGHT"))
					{
						if (poultice && (wounded||fatigued))
						{
							selectedChit2 = restFatiguedList.getSelectedValue();
							selectedLabel2.setText(selectedChit2);
							fatigued2 = true;
							wounded2 = false;
						}
						else{
							selectedChit = restFatiguedList.getSelectedValue();
							selectedLabel.setText(selectedChit);
							fatigued = true;
							wounded = false;
						}
					}

				}
			}
		};
		restFatiguedList.addMouseListener(mouseListener);

		JScrollPane fatiguedScrollPanel = new JScrollPane(restFatiguedList);
		fatiguedScrollPanel.setSize(300,150);
		fatiguedScrollPanel.setLocation(150, 75);
		restframe.add(fatiguedScrollPanel);

		//REST WOUNDED MODEL + LIST + SCROLLPANE
		restWoundedModel.removeAllElements();
		for (int s = 0; s < woundedInventoryModel.getSize(); s++)
		{
			restWoundedModel.addElement(woundedInventoryModel.getElementAt(s));
		}
		restWoundedList = new JList<String>(restWoundedModel);
		restWoundedList.setSize(300,150);
		MouseListener mouseListener2 = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					if (restWoundedList.getSelectedValue().contains("MOVE") || restWoundedList.getSelectedValue().contains("FIGHT"))
					{
						if (poultice && (wounded||fatigued))
						{
							selectedChit2 = restWoundedList.getSelectedValue();
							selectedLabel2.setText(selectedChit2);
							fatigued2 = false;
							wounded2 = true;
						}
						else{
							selectedChit = restWoundedList.getSelectedValue();
							selectedLabel.setText(selectedChit);
							fatigued = false;
							wounded = true;
						}
					}
				}
			}
		};
		restWoundedList.addMouseListener(mouseListener2);

		JScrollPane woundedScrollPanel = new JScrollPane(restWoundedList);
		woundedScrollPanel.setSize(300,150);
		woundedScrollPanel.setLocation(150, 250);
		restframe.add(woundedScrollPanel);

		JLabel fakeBrandon = new JLabel("");
		restframe.add(fakeBrandon);
		restframe.setVisible(true);
	}
	public void MagicRealmGUITradeSearch(MagicRealmVariables m)
	{
		tradeSearchFrame = new JFrame("Trade");
		tradeSearchFrame.setLocation(500,300);
		tradeSearchFrame.setSize(300, 275);
		tradeSearchFrame.setResizable(false);

		tradeSearchFrame.getContentPane().setBackground(Color.black);

		JLabel tradeLabel = new JLabel("Trade", JLabel.CENTER);
		tradeLabel.setSize(100,25);
		tradeLabel.setLocation(100,20);
		tradeLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		tradeLabel.setForeground(Color.cyan);
		tradeLabel.setBackground(Color.black);
		tradeLabel.setOpaque(true);
		tradeSearchFrame.add(tradeLabel);

		JButton choose =  new JButton("choose");
		choose.setSize(90,25);
		choose.setLocation(50, 140);
		choose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (tradePlayers.getSelectedValue() == null || tradePlayers.getSelectedValue().equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Select a Character to Trade With", "Trade Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else
				{
					tradeSearchFrame.setVisible(false);
					partner = tradePlayers.getSelectedValue();
					m.tradeSearch = 1;
					MagicRealmGUITradeFrame(m);

				}
			}
		});
		tradeSearchFrame.add(choose);


		JButton cancel =  new JButton("Cancel");
		cancel.setSize(90,25);
		cancel.setLocation(160, 140);
		cancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tradeSearchFrame.setVisible(false);
			}
		});
		tradeSearchFrame.add(cancel);

		tradePlayerModel.removeAllElements();
		for(String s : m.getUserClassData())
		{
			if (!s.equals(m.getUserClassData().get(0)))
			{
				if (m.getPlayerLocationsData().get(s) != null
						&& m.getPlayerLocationsData().get(s)[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
						&& m.getPlayerLocationsData().get(s)[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
				{
					if (!tradePlayerModel.contains(s))
						tradePlayerModel.addElement(s);
				}
			}
		}
		for (String natives : m.nativeLocation.keySet())
			if (m.nativeLocation.get(natives)[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
					&& m.nativeLocation.get(natives)[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
				tradePlayerModel.addElement(natives);
		tradePlayers = new JList<String>(tradePlayerModel);
		//tradePlayers.setLocation(0,0);
		tradePlayers.setSize(200,80);

		tradePlayerScrollPanel = new JScrollPane(tradePlayers);
		tradePlayerScrollPanel.setSize(200,80);
		tradePlayerScrollPanel.setLocation(50,50);

		tradeSearchFrame.add(tradePlayerScrollPanel);
		JLabel fakeMaab = new JLabel("");
		tradeSearchFrame.add(fakeMaab);
		tradeSearchFrame.setVisible(true);
	}
	public void MagicRealmGUITradeFrame(MagicRealmVariables m)
	{
		tradeframe = new JFrame("Magic Realms : Trade");

		tradeframe.setResizable(false);
		tradeframe.setSize(612, 520);
		try {
			tradeframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("trade.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		barter =  new JButton("Accept");
		barter.setSize(90,25);
		barter.setLocation(500, 450);
		barter.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (tradeList.getSelectedValue() == null || tradeList.getSelectedValue().equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Select an Item to Trade", "Trade Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else if (theOffer.getText()==null || theOffer.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "You Must Wait For An Offer", "Trade Notice",
							JOptionPane.INFORMATION_MESSAGE);    
				}
				else
				{

					if ( partner.contains("Patrol")
							|| partner.contains("Bashkars")
							|| partner.contains("Company")
							|| partner.contains("Guard")
							|| partner.contains("Order")
							|| partner.contains("Rogues")
							|| partner.contains("Soldiers")
							|| partner.contains("Woodfolk")
							)
					{
						tradeList.setEnabled(false);
						reject.setEnabled(false);
						barter.setEnabled(false);
						tradeOffer = tradeList.getSelectedValue();
						targetPlayer = partner;
						acceptedItem = theOffer.getText();
						offeredItem = tradeList.getSelectedValue();
						m.nativeTrade = 1;
						m.tradeAccept = 1;
					}
					else
					{
						tradeList.setEnabled(false);
						reject.setEnabled(false);
						barter.setEnabled(false);
						tradeOffer = tradeList.getSelectedValue();
						targetPlayer = partner;
						acceptedItem = theOffer.getText();
						offeredItem = tradeList.getSelectedValue();
						m.tradeAccept = 1;
					}
				}
			}
		});
		tradeframe.add(barter);

		reject =  new JButton("Reject");
		reject.setSize(90,25);
		reject.setLocation(400, 450);
		reject.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tradeList.setEnabled(false);
				reject.setEnabled(false);
				barter.setEnabled(false);
				tradeOffer = tradeList.getSelectedValue();
				targetPlayer = partner;
				acceptedItem = "Rejected";
				offeredItem = tradeList.getSelectedValue();
				m.tradeAccept = 1;
				tradeframe.setVisible(false);
			}
		});
		tradeframe.add(reject);

		yourOffer =  new JLabel("Your Offer" , JLabel.CENTER);
		yourOffer.setSize(300,25);
		yourOffer.setLocation(150, 30);
		yourOffer.setFont(new Font("Serif", Font.PLAIN, 18));
		yourOffer.setForeground(Color.cyan);
		yourOffer.setBackground(Color.black);
		yourOffer.setOpaque(true);
		tradeframe.add(yourOffer);

		theirOffer =  new JLabel(partner+"'s Offer" , JLabel.CENTER);
		theirOffer.setSize(300,25);
		theirOffer.setLocation(150, 225);
		theirOffer.setFont(new Font("Serif", Font.PLAIN, 18));
		theirOffer.setForeground(Color.cyan);
		theirOffer.setBackground(Color.black);
		theirOffer.setOpaque(true);
		tradeframe.add(theirOffer);


		theOffer =  new JLabel("" , JLabel.CENTER);
		theOffer.setSize(300,25);
		theOffer.setLocation(150, 250);
		theOffer.setFont(new Font("Serif", Font.PLAIN, 18));
		theOffer.setForeground(Color.cyan);
		theOffer.setBackground(Color.black);
		theOffer.setOpaque(true);
		tradeframe.add(theOffer);

		tradeListModel.removeAllElements();
		for (int s = 0; s < inventoryModel.getSize(); s++)
		{
			tradeListModel.addElement(inventoryModel.getElementAt(s));
		}
		for (int s = 0; s < inactiveInventoryModel.getSize(); s++)
		{
			tradeListModel.addElement(inactiveInventoryModel.getElementAt(s));
		}
		tradeList = new JList<String>(tradeListModel);
		tradeList.setSize(300,150);
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{

					if ( partner.contains("Patrol")
							|| partner.contains("Bashkars")
							|| partner.contains("Company")
							|| partner.contains("Guard")
							|| partner.contains("Order")
							|| partner.contains("Rogues")
							|| partner.contains("Soldiers")
							|| partner.contains("Woodfolk")
							)
					{
						if (m.itemPrice.get(tradeList.getSelectedValue())!=null)
							theOffer.setText(m.itemPrice.get(tradeList.getSelectedValue()) + " gold");
						else
							theOffer.setText("0 gold");
					}
					else{
						tradeOffer = tradeList.getSelectedValue();
						targetPlayer = partner;
						m.tradeOffer = 1;
					}
				}
			}
		};
		tradeList.addMouseListener(mouseListener);

		tradeScrollPanel = new JScrollPane(tradeList);
		tradeScrollPanel.setSize(300,150);
		tradeScrollPanel.setLocation(150, 75);
		tradeframe.add(tradeScrollPanel);

		JLabel fakeBrandon = new JLabel("");
		tradeframe.add(fakeBrandon);
		tradeframe.setVisible(true);
	}
	public void MagicRealmGUIPlayerInfoScreen(MagicRealmVariables m, String user)
	{
		infoframe = new JFrame("Player Information");
		infoframe.setSize(550, 600);
		infoframe.setLocation(300,100);
		infoframe.getContentPane().setBackground(Color.black);
		//For a character name without spaces, lowercase the first char.
		String testString = user;
		String firstLetter = testString.substring(0,1).toLowerCase();
		String restLetters = testString.substring(1);
		String resultString = firstLetter + restLetters;

		//If its a character name with a space, ie Woods Girl, format accordingly: woods_girl
		String resultString2 = resultString;
		for (int c = 0; c < resultString.toCharArray().length; c++) {
			if (Character.isWhitespace(resultString.charAt(c))) {
				resultString2 = resultString.substring(0,c)+'_';
				String firstLetter2 = resultString.substring(c+1,c+2).toLowerCase();
				String restLetters2 = resultString.substring(c+2);
				resultString2 = resultString2 + firstLetter2 + restLetters2;
			}
		}
		Icon icon = new ImageIcon("characterdetail/"+resultString2+".jpg");
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		thumb.setSize(370, 600);
		thumb.setLocation(0, 0);
		infoframe.add(thumb);

		JLabel locations = new JLabel("Locations Entered : " + String.valueOf(m.getUserVictoryPoints().get(user)[5]));
		locations.setSize(180,25);
		locations.setLocation(370,175);
		locations.setForeground(Color.cyan);
		infoframe.add(locations);

		JLabel treasures = new JLabel("Great Treasures : " + String.valueOf(m.getUserVictoryPoints().get(user)[0]));
		treasures.setSize(180,25);
		treasures.setLocation(370,200);
		treasures.setForeground(Color.cyan);
		infoframe.add(treasures);

		JLabel spells = new JLabel("Spells Learned : " + String.valueOf(m.getUserVictoryPoints().get(user)[1]));
		spells.setSize(180,25);
		spells.setLocation(370,225);
		spells.setForeground(Color.cyan);
		infoframe.add(spells);

		JLabel fame = new JLabel("Fame : " + String.valueOf(m.getUserVictoryPoints().get(user)[2]));
		fame.setSize(180,25);
		fame.setLocation(370,250);
		fame.setForeground(Color.cyan);
		infoframe.add(fame);

		JLabel notoriety = new JLabel("Notoriety : " + String.valueOf(m.getUserVictoryPoints().get(user)[3]));
		notoriety.setSize(180,25);
		notoriety.setLocation(370,275);
		notoriety.setForeground(Color.cyan);
		infoframe.add(notoriety);

		JLabel goldPoints = new JLabel("Gold : " + String.valueOf(m.getUserVictoryPoints().get(user)[4]));
		goldPoints.setSize(180,25);
		goldPoints.setLocation(370,300);
		goldPoints.setForeground(Color.cyan);
		infoframe.add(goldPoints);

		if(m.getUserClassData().get(0).equals(user)){
			JLabel locationsGoal = new JLabel("Locations Goal : " + String.valueOf(m.userVictoryConditions.get(user)[5]));
			locationsGoal.setSize(180,25);
			locationsGoal.setLocation(370,350);
			locationsGoal.setForeground(Color.cyan);
			infoframe.add(locationsGoal);

			JLabel treasuresGoal = new JLabel("Great Treasures Goal : " + String.valueOf(m.userVictoryConditions.get(user)[0]));
			treasuresGoal.setSize(180,25);
			treasuresGoal.setLocation(370,375);
			treasuresGoal.setForeground(Color.cyan);
			infoframe.add(treasuresGoal);

			JLabel spellsGoal = new JLabel("Spells Learned Goal : " + String.valueOf(m.userVictoryConditions.get(user)[1]));
			spellsGoal.setSize(180,25);
			spellsGoal.setLocation(370,400);
			spellsGoal.setForeground(Color.cyan);
			infoframe.add(spellsGoal);

			JLabel fameGoal = new JLabel("Fame Goal : " + String.valueOf(m.userVictoryConditions.get(user)[2]));
			fameGoal.setSize(180,25);
			fameGoal.setLocation(370,425);
			fameGoal.setForeground(Color.cyan);
			infoframe.add(fameGoal);

			JLabel notorietyGoal = new JLabel("Notoriety Goal : " + String.valueOf(m.userVictoryConditions.get(user)[3]));
			notorietyGoal.setSize(180,25);
			notorietyGoal.setLocation(370,450);
			notorietyGoal.setForeground(Color.cyan);
			infoframe.add(notorietyGoal);

			JLabel goldPointsGoal = new JLabel("Gold Goal : " + String.valueOf(m.userVictoryConditions.get(user)[4]));
			goldPointsGoal.setSize(180,25);
			goldPointsGoal.setLocation(370,475);
			goldPointsGoal.setForeground(Color.cyan);
			infoframe.add(goldPointsGoal);
		}

		JButton done = new JButton("Done");
		done.setSize(180, 25);
		done.setLocation(370, 500);
		done.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				infoframe.setVisible(false);
			}
		});

		infoframe.add(done);

		JLabel fakeMaxHanna = new JLabel("");
		infoframe.add(fakeMaxHanna);
		infoframe.setVisible(true);

	}
	public void MagicRealmGUIVictoryPointScreen(MagicRealmVariables m, String user)
	{
		victorypointframe = new JFrame("Set Your Victory Conditions");
		pointsSpent=0;
		locationsFound=0;
		spellsFound=0;
		notorietyFound=0;
		goldFound=0;
		fameFound=0;
		treasuresFound=0;
		victorypointframe.setSize(418, 600);
		victorypointframe.setLocation(100,50);
		//victorypointframe.getContentPane().setBackground(Color.black);
		victorypointframe.setResizable(false);
		try {
			victorypointframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("victory.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JButton done = new JButton("Done");
		done.setEnabled(false);
		done.setSize(100, 25);
		done.setLocation(169, 515);
		done.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				Integer[] temp = new Integer[6];
				temp[0]=treasuresFound;
				temp[1]=spellsFound;
				temp[2]=fameFound;
				temp[3]=notorietyFound;
				temp[4]=goldFound;
				temp[5]=locationsFound;
				m.userVictoryConditions.put(user, temp);
				victorypointframe.setVisible(false);
				joinGame.setEnabled(true);
				joinframe.setVisible(true);
			} 
		});



		victorypointframe.add(done);
		locations = new JLabel("Locations Entered: " + locationsFound);
		locations.setSize(215,45);
		locations.setLocation(15,30);
		locations.setFont(new Font("Serif", Font.PLAIN, 18));
		victorypointframe.add(locations);

		downLoc = new BasicArrowButton(BasicArrowButton.SOUTH);
		downLoc.setSize(25, 25);
		downLoc.setLocation(353, 30);
		downLoc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (pointsSpent > 0)
				{

					upGold.setEnabled(true);
					upNotoriety.setEnabled(true);
					upFame.setEnabled(true);
					upSpell.setEnabled(true);
					upTres.setEnabled(true);
					upLoc.setEnabled(true);
					locationsFound--;
					if (locationsFound == 0)
					{
						downLoc.setEnabled(false);
					}
					done.setEnabled(false);
					pointsSpent--;
					locations.setText("Locations Entered: " + locationsFound);
				}
			} 
		});
		victorypointframe.add(downLoc);


		upLoc = new BasicArrowButton(BasicArrowButton.NORTH);
		upLoc.setSize(25, 25);
		upLoc.setLocation(378, 30);
		upLoc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    	
				downLoc.setEnabled(true);

				if (pointsSpent < 5)
				{
					locationsFound++;
					pointsSpent++;
					if (pointsSpent == 5)
					{
						done.setEnabled(true);
						upGold.setEnabled(false);
						upNotoriety.setEnabled(false);
						upFame.setEnabled(false);
						upSpell.setEnabled(false);
						upTres.setEnabled(false);
						upLoc.setEnabled(false);
						if (goldFound > 0)
							downGold.setEnabled(true);
						if (notorietyFound > 0)
							downNotoriety.setEnabled(true);
						if (fameFound > 0)
							downFame.setEnabled(true);
						if (spellsFound > 0)
							downSpell.setEnabled(true);
						if (treasuresFound > 0)
							downTres.setEnabled(true);
						if (locationsFound > 0)
							downLoc.setEnabled(true);
					}
					locations.setText("Locations Entered: " + locationsFound);
				}
			} 
		});
		victorypointframe.add(upLoc);

		treasures = new JLabel("Great Treasures Found: " + treasuresFound);
		treasures.setSize(215,45);
		treasures.setLocation(15,75);
		treasures.setFont(new Font("Serif", Font.PLAIN, 18));
		victorypointframe.add(treasures);

		downTres = new BasicArrowButton(BasicArrowButton.SOUTH);
		downTres.setSize(25, 25);
		downTres.setLocation(353, 75);
		downTres.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (pointsSpent > 0)
				{
					upGold.setEnabled(true);
					upNotoriety.setEnabled(true);
					upFame.setEnabled(true);
					upSpell.setEnabled(true);
					upTres.setEnabled(true);
					upLoc.setEnabled(true);
					done.setEnabled(false);
					treasuresFound--;
					if (treasuresFound == 0)
					{

						downTres.setEnabled(false);
					}
					pointsSpent--;
					treasures.setText("Great Treasures Found: " + treasuresFound);
				}
			} 
		});
		victorypointframe.add(downTres);

		upTres = new BasicArrowButton(BasicArrowButton.NORTH);
		upTres.setSize(25, 25);
		upTres.setLocation(378, 75);
		upTres.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    	
				downTres.setEnabled(true);
				if (pointsSpent < 5)
				{

					treasuresFound++;
					pointsSpent++;
					if (pointsSpent == 5)
					{
						done.setEnabled(true);
						upGold.setEnabled(false);
						upNotoriety.setEnabled(false);
						upFame.setEnabled(false);
						upSpell.setEnabled(false);
						upTres.setEnabled(false);
						upLoc.setEnabled(false);
						if (goldFound > 0)
							downGold.setEnabled(true);
						if (notorietyFound > 0)
							downNotoriety.setEnabled(true);
						if (fameFound > 0)
							downFame.setEnabled(true);
						if (spellsFound > 0)
							downSpell.setEnabled(true);
						if (treasuresFound > 0)
							downTres.setEnabled(true);
						if (locationsFound > 0)
							downLoc.setEnabled(true);
					}
					treasures.setText("Great Treasures Found: " + treasuresFound);
				}
			} 
		});
		victorypointframe.add(upTres);

		spells = new JLabel("Spells Learned: " + spellsFound);
		spells.setSize(215,45);
		spells.setLocation(15,120);
		spells.setFont(new Font("Serif", Font.PLAIN, 18));
		victorypointframe.add(spells);

		downSpell = new BasicArrowButton(BasicArrowButton.SOUTH);
		downSpell.setSize(25, 25);
		downSpell.setLocation(353, 120);
		downSpell.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (pointsSpent > 0)
				{

					upGold.setEnabled(true);
					upNotoriety.setEnabled(true);
					upFame.setEnabled(true);
					upSpell.setEnabled(true);
					upTres.setEnabled(true);
					upLoc.setEnabled(true);
					done.setEnabled(false);
					spellsFound = spellsFound - 2;
					if (spellsFound == 0)
					{
						downSpell.setEnabled(false);
					}
					pointsSpent--;
					spells.setText("Spells Learned: " + spellsFound);
				}
			} 
		});
		victorypointframe.add(downSpell);

		upSpell = new BasicArrowButton(BasicArrowButton.NORTH);
		upSpell.setSize(25, 25);
		upSpell.setLocation(378, 120);
		upSpell.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    	
				downSpell.setEnabled(true);
				if (pointsSpent < 5)
				{
					spellsFound = spellsFound + 2;
					pointsSpent++;	
					if (pointsSpent == 5)
					{
						done.setEnabled(true);
						upGold.setEnabled(false);
						upNotoriety.setEnabled(false);
						upFame.setEnabled(false);
						upSpell.setEnabled(false);
						upTres.setEnabled(false);
						upLoc.setEnabled(false);
						if (goldFound > 0)
							downGold.setEnabled(true);
						if (notorietyFound > 0)
							downNotoriety.setEnabled(true);
						if (fameFound > 0)
							downFame.setEnabled(true);
						if (spellsFound > 0)
							downSpell.setEnabled(true);
						if (treasuresFound > 0)
							downTres.setEnabled(true);
						if (locationsFound > 0)
							downLoc.setEnabled(true);
					}
					spells.setText("Spells Learned: " + spellsFound);
				}
			} 
		});
		victorypointframe.add(upSpell);

		fame = new JLabel("Fame: " + fameFound);
		fame.setSize(215,45);
		fame.setLocation(15,165);
		fame.setFont(new Font("Serif", Font.PLAIN, 18));
		victorypointframe.add(fame);

		downFame = new BasicArrowButton(BasicArrowButton.SOUTH);
		downFame.setSize(25, 25);
		downFame.setLocation(353, 165);
		downFame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (pointsSpent > 0)
				{
					upGold.setEnabled(true);
					upNotoriety.setEnabled(true);
					upFame.setEnabled(true);
					upSpell.setEnabled(true);
					upTres.setEnabled(true);
					upLoc.setEnabled(true);
					done.setEnabled(false);
					fameFound = fameFound - 10;
					if (fameFound == 0)
					{
						downFame.setEnabled(false);
					}
					pointsSpent--;
					fame.setText("Fame: " + fameFound);
				}
			} 
		});
		victorypointframe.add(downFame);
		upFame = new BasicArrowButton(BasicArrowButton.NORTH);
		upFame.setSize(25, 25);
		upFame.setLocation(378, 165);
		upFame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    	
				downFame.setEnabled(true);
				if (pointsSpent < 5)
				{
					fameFound = fameFound + 10;
					pointsSpent++;
					if (pointsSpent == 5)
					{
						done.setEnabled(true);
						upGold.setEnabled(false);
						upNotoriety.setEnabled(false);
						upFame.setEnabled(false);
						upSpell.setEnabled(false);
						upTres.setEnabled(false);
						upLoc.setEnabled(false);
						if (goldFound > 0)
							downGold.setEnabled(true);
						if (notorietyFound > 0)
							downNotoriety.setEnabled(true);
						if (fameFound > 0)
							downFame.setEnabled(true);
						if (spellsFound > 0)
							downSpell.setEnabled(true);
						if (treasuresFound > 0)
							downTres.setEnabled(true);
						if (locationsFound > 0)
							downLoc.setEnabled(true);
					}
					fame.setText("Fame: " + fameFound);
				}
			} 
		});
		victorypointframe.add(upFame);

		notoriety = new JLabel("Notoriety: " + notorietyFound);
		notoriety.setSize(215,45);
		notoriety.setLocation(15,210);
		notoriety.setFont(new Font("Serif", Font.PLAIN, 18));
		victorypointframe.add(notoriety);

		downNotoriety = new BasicArrowButton(BasicArrowButton.SOUTH);
		downNotoriety.setSize(25, 25);
		downNotoriety.setLocation(353, 210);
		downNotoriety.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (pointsSpent > 0)
				{
					upGold.setEnabled(true);
					upNotoriety.setEnabled(true);
					upFame.setEnabled(true);
					upSpell.setEnabled(true);
					upTres.setEnabled(true);
					upLoc.setEnabled(true);
					done.setEnabled(false);
					notorietyFound = notorietyFound - 20;
					if (notorietyFound == 0)
					{
						downNotoriety.setEnabled(false);
					}
					pointsSpent--;
					notoriety.setText("Notoriety: " + notorietyFound);
				}
			} 
		});
		victorypointframe.add(downNotoriety);

		upNotoriety = new BasicArrowButton(BasicArrowButton.NORTH);
		upNotoriety.setSize(25, 25);
		upNotoriety.setLocation(378, 210);
		upNotoriety.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    	
				downNotoriety.setEnabled(true);
				if (pointsSpent < 5)
				{
					notorietyFound = notorietyFound + 20;
					pointsSpent++;
					if (pointsSpent == 5)
					{
						done.setEnabled(true);
						upGold.setEnabled(false);
						upNotoriety.setEnabled(false);
						upFame.setEnabled(false);
						upSpell.setEnabled(false);
						upTres.setEnabled(false);
						upLoc.setEnabled(false);
						if (goldFound > 0)
							downGold.setEnabled(true);
						if (notorietyFound > 0)
							downNotoriety.setEnabled(true);
						if (fameFound > 0)
							downFame.setEnabled(true);
						if (spellsFound > 0)
							downSpell.setEnabled(true);
						if (treasuresFound > 0)
							downTres.setEnabled(true);
						if (locationsFound > 0)
							downLoc.setEnabled(true);
					}
					notoriety.setText("Notoriety: " + notorietyFound);
				}
			} 
		});
		victorypointframe.add(upNotoriety);


		goldPoints = new JLabel("Gold: " + goldFound);
		goldPoints.setSize(215,45);
		goldPoints.setLocation(15,255);
		goldPoints.setFont(new Font("Serif", Font.PLAIN, 18));
		victorypointframe.add(goldPoints);

		downGold = new BasicArrowButton(BasicArrowButton.SOUTH);
		downGold.setSize(25, 25);
		downGold.setLocation(353, 255);
		downGold.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (pointsSpent > 0)
				{
					upGold.setEnabled(true);
					upNotoriety.setEnabled(true);
					upFame.setEnabled(true);
					upSpell.setEnabled(true);
					upTres.setEnabled(true);
					upLoc.setEnabled(true);
					goldFound = goldFound - 30;
					if (goldFound == 0)
					{
						downGold.setEnabled(false);
					}
					pointsSpent--;
					goldPoints.setText("Gold: " + goldFound);
				}
			} 
		});
		victorypointframe.add(downGold);

		upGold = new BasicArrowButton(BasicArrowButton.NORTH);
		upGold.setSize(25, 25);
		upGold.setLocation(378, 255);
		upGold.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    	
				downGold.setEnabled(true);
				if (pointsSpent < 5)
				{
					goldFound = goldFound + 30;
					pointsSpent++;
					if (pointsSpent == 5)
					{
						done.setEnabled(true);
						upGold.setEnabled(false);
						upNotoriety.setEnabled(false);
						upFame.setEnabled(false);
						upSpell.setEnabled(false);
						upTres.setEnabled(false);
						upLoc.setEnabled(false);
						if (goldFound > 0)
							downGold.setEnabled(true);
						if (notorietyFound > 0)
							downNotoriety.setEnabled(true);
						if (fameFound > 0)
							downFame.setEnabled(true);
						if (spellsFound > 0)
							downSpell.setEnabled(true);
						if (treasuresFound > 0)
							downTres.setEnabled(true);
						if (locationsFound > 0)
							downLoc.setEnabled(true);
					}
					goldPoints.setText("Gold: " + goldFound);
				}
			} 
		});
		victorypointframe.add(upGold);

		downLoc.setEnabled(false);
		downGold.setEnabled(false);
		downFame.setEnabled(false);
		downSpell.setEnabled(false);
		downTres.setEnabled(false);
		downNotoriety.setEnabled(false);

		JLabel fakeShakerSaba = new JLabel("");
		victorypointframe.add(fakeShakerSaba);
		victorypointframe.setVisible(true);

	}

	public void MagicRealmGUIWeaponInfoScreen(MagicRealmVariables m, String weapon)
	{
		//System.out.println(weapon);
		weaponinfoframe = new JFrame("Weapon Information");
		weaponinfoframe.getContentPane().setBackground(Color.white);
		weaponinfoframe.setSize(300, 230);
		weaponinfoframe.setLocation(300,100);
		//weaponinfoframe.getContentPane().setBackground(Color.black);

		String testString = weapon;
		String firstLetter = testString.substring(0,1).toLowerCase();
		String restLetters = testString.substring(1);
		String resultString = firstLetter + restLetters;

		//If its a weapon name with a space, ie Thrusting Sword, format accordingly: thrusting_sword
		String resultString2 = resultString;
		if (weapon.equals("Alchemist's Mixture"))
		{
			resultString2 = "alchemistsmixture";
		}
		else {
			for (int c = 0; c < resultString.toCharArray().length; c++) {
				if (Character.isWhitespace(resultString.charAt(c))) {
					resultString2 = resultString.substring(0,c)+'_';
					String firstLetter2 = resultString.substring(c+1,c+2).toLowerCase();
					String restLetters2 = resultString.substring(c+2);
					resultString2 = resultString2 + firstLetter2 + restLetters2;
				}
			}
		}


		//System.out.println(resultString2);
		Icon icon = new ImageIcon("weapons/"+resultString2+".gif");
		JLabel thumb = new JLabel("", JLabel.CENTER);
		thumb.setIcon(icon);
		thumb.setSize(160, 100);
		thumb.setLocation(65, 0);
		thumb.setBackground(Color.white);
		thumb.setOpaque(true);
		weaponinfoframe.add(thumb);
		//SPEED, WEIGHT(0=negligible 1=light, 2=medium 3=tremendous), LENGTH, SHARPNESS, ALERTED SHARPNESS, ALERTED SPEED

		JLabel speed = new JLabel("Speed : "+ m.weaponData.get(weapon)[0]);
		speed.setSize(150,15);
		speed.setLocation(25,100);
		speed.setFont(new Font("Serif", Font.PLAIN, 12));
		speed.setForeground(Color.black);
		speed.setBackground(Color.white);
		speed.setOpaque(true);
		weaponinfoframe.add(speed);

		String wepweight = "";
		if (m.weaponData.get(weapon)[1] == 0)
			wepweight = "Neglegible";
		else if (m.weaponData.get(weapon)[1] == 1)
			wepweight = "Light";
		else if (m.weaponData.get(weapon)[1] == 2)
			wepweight = "Medium";
		else if (m.weaponData.get(weapon)[1] == 3)
			wepweight = "Heavy";
		else if (m.weaponData.get(weapon)[1] == 4)
			wepweight = "Tremendous";
		else if (m.weaponData.get(weapon)[1] == 5)
			wepweight = "Super";

		JLabel weight = new JLabel("Weight : "+ wepweight);
		weight.setSize(150,15);
		weight.setLocation(25,115);
		weight.setFont(new Font("Serif", Font.PLAIN, 12));
		weight.setForeground(Color.black);
		weight.setBackground(Color.white);
		weight.setOpaque(true);
		weaponinfoframe.add(weight);


		JLabel length = new JLabel("Length : "+ m.weaponData.get(weapon)[2]);
		length.setSize(150,15);
		length.setLocation(25,130);
		length.setFont(new Font("Serif", Font.PLAIN, 12));
		length.setForeground(Color.black);
		length.setBackground(Color.white);
		length.setOpaque(true);
		weaponinfoframe.add(length);

		JLabel sharpness = new JLabel("Sharpness : "+ m.weaponData.get(weapon)[3]);
		sharpness.setSize(150,15);
		sharpness.setLocation(25,145);
		sharpness.setFont(new Font("Serif", Font.PLAIN, 12));
		sharpness.setForeground(Color.black);
		sharpness.setBackground(Color.white);
		sharpness.setOpaque(true);
		weaponinfoframe.add(sharpness);


		JLabel alertsharpness = new JLabel("Alert Sharpness : "+ m.weaponData.get(weapon)[4]);
		alertsharpness.setSize(150,15);
		alertsharpness.setLocation(150,100);
		alertsharpness.setFont(new Font("Serif", Font.PLAIN, 12));
		alertsharpness.setForeground(Color.black);
		alertsharpness.setBackground(Color.white);
		alertsharpness.setOpaque(true);
		weaponinfoframe.add(alertsharpness);


		JLabel alertspeed = new JLabel("Alert Speed : "+ m.weaponData.get(weapon)[5]);
		alertspeed.setSize(150,15);
		alertspeed.setLocation(150,115);
		alertspeed.setFont(new Font("Serif", Font.PLAIN, 12));
		alertspeed.setForeground(Color.black);
		alertspeed.setBackground(Color.white);
		alertspeed.setOpaque(true);
		weaponinfoframe.add(alertspeed);

		JButton done = new JButton("Done");
		done.setSize(100, 25);
		done.setLocation(100, 165);
		done.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				weaponinfoframe.setVisible(false);
			}
		});

		weaponinfoframe.add(done);


		JLabel fakeMaabAhmed = new JLabel("");
		weaponinfoframe.add(fakeMaabAhmed);
		weaponinfoframe.setVisible(true);

	}


	public void MagicRealmGUITreasureInfoScreen(MagicRealmVariables m, String treasure)
	{
		JFrame treasureinfoframe = new JFrame("Treasure Information");
		treasureinfoframe.getContentPane().setBackground(Color.white);
		treasureinfoframe.setSize(330, 350);
		treasureinfoframe.setLocation(500,150);
		treasureinfoframe.getContentPane().setBackground(Color.black);

		JLabel description = new JLabel("Item Description:", JLabel.CENTER);
		description.setSize(330, 20);
		description.setLocation(0,10);
		description.setFont(new Font("Serif", Font.BOLD, 18));
		description.setForeground(Color.yellow);
		description.setBackground(Color.black);
		treasureinfoframe.add(description);

		JLabel treasuretype = new JLabel(treasure, JLabel.CENTER);
		treasuretype.setSize(330, 20);
		treasuretype.setLocation(0,30);
		treasuretype.setFont(new Font("Serif", Font.ITALIC, 18));
		treasuretype.setForeground(Color.yellow);
		treasuretype.setBackground(Color.black);
		treasureinfoframe.add(treasuretype);

		JTextArea multi = new JTextArea(m.treasureProp.get(treasure));
		multi.setWrapStyleWord(true);
		multi.setLineWrap(true);
		multi.setEditable(false);
		multi.setSize(300,200);
		multi.setLocation(0,58);
		multi.setFont(new Font("Serif", Font.ITALIC, 14));
		multi.setForeground(Color.yellow);
		multi.setBackground(Color.black);
		multi.setOpaque(true);
		treasureinfoframe.add(multi);

		JButton done = new JButton("Done");
		done.setSize(100, 25);
		done.setLocation(115, 275);
		done.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				treasureinfoframe.setVisible(false);
			}
		});
		treasureinfoframe.add(done);
		if ((treasure.equals("Chest") && inventoryModel.contains("Chest"))
				|| (treasure.equals("Ancient Telescope") && inventoryModel.contains("Ancient Telescope"))
				|| (treasure.equals("Crypt of the Knight") && inventoryModel.contains("Crypt of the Knight"))
				|| (treasure.equals("Toadstool Circle") && inventoryModel.contains("Toadstool Circle"))
				|| (treasure.equals("Remains of Thief") && inventoryModel.contains("Remains of Thief"))
				|| (treasure.equals("Enchanted Meadow") && inventoryModel.contains("Enchanted Meadow"))
				|| (treasure.equals("Mouldy Skeleton") && inventoryModel.contains("Mouldy Skeleton")))
		{
			treasureinfoframe.setSize(treasureinfoframe.getWidth(), treasureinfoframe.getHeight()+30);
			JButton use = new JButton("Use");
			use.setSize(100, 25);
			use.setLocation(115, 310);
			use.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{    
					treasureinfoframe.setVisible(false);
					if (treasure.equals("Chest") && inventoryModel.contains("Lost Keys"))
					{
						updateText("The Chest Was Unlocked Using Lost Keys!");
						activeItem = "Chest";
						m.itemDestroyed = 1;
						activeItem = "Lost Keys";
						m.itemDestroyed = 1;
						activeItem = "50";
						m.addGold = 1;
					}
					else if (treasure.equals("Ancient Telescope"))
					{
						updateText("The Ancient Telescope Enables You To Loot Anywhere!");
						JOptionPane.showMessageDialog(null, "The Ancient Telescope Enables You To Loot Anywhere! Select A Clearing To Loot From.", "Ancient Telescope",
								JOptionPane.INFORMATION_MESSAGE);    
						activeItem = "Ancient Telescope";
						m.itemDestroyed = 1;
						usingTelescope=true;
						hide.setEnabled(false);
						trade.setEnabled(false);
						alert.setEnabled(false);
						follow.setEnabled(false);
						hide.setEnabled(false);
						trade.setEnabled(false);
						alert.setEnabled(false);
						follow.setEnabled(false);
						mingle.setEnabled(false);
						search.setEnabled(false);
						rest.setEnabled(false);
						inventoryList.setEnabled(false);
						inactiveInventoryList.setEnabled(false);
						fatiguedInventoryList.setEnabled(false);
						woundedInventoryList.setEnabled(false);
						ok.setEnabled(false);
					}
					//ITS FOUND IN TREASURES WITHIN TREASURES
					else
					{
						Random rand = new Random();
						int count = 0;
						int rando = rand.nextInt(m.TWTdata.get(treasure).length);
						for (String item: m.TWTdata.get(treasure))
						{
							if (count == rando)
							{
								activeItem = item;
								m.addInactive = 1;		
								
								while (!inactiveInventoryModel.contains(activeItem))
								{
									
								}
								activeTWT = treasure;	
								m.takeTWT = 1;
								break;
							}
							count++;
						}
					}
				}
				
			});
			treasureinfoframe.add(use);

		}
		JLabel fakeBrandonHurley = new JLabel("");
		treasureinfoframe.add(fakeBrandonHurley);
		treasureinfoframe.setVisible(true);

	}

	public void MagicRealmGUISearchScreen(MagicRealmVariables m)
	{
		searchframe = new JFrame("Search");
		searchframe.setSize(500, 200);
		searchframe.setLocation(400,300);
		searchframe.setResizable(false);
		try {
			searchframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("search.jpg")))));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		ImageIcon peerIcon = new ImageIcon("actions/peer.gif");
		peerButton = new JButton(peerIcon);
		peerButton.setLocation(70,50);
		peerButton.setSize(50,50);
		JLabel peerLabel = new JLabel("Peer", JLabel.CENTER);
		peerLabel.setSize(80,25);
		peerLabel.setLocation(70,130);
		peerLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		peerLabel.setForeground(Color.cyan);
		peerLabel.setBackground(Color.black);
		peerLabel.setOpaque(true);
		searchframe.add(peerLabel);

		ImageIcon locateIcon = new ImageIcon("actions/cache.gif");
		locateButton = new JButton(locateIcon);
		locateButton.setLocation(210,50);
		locateButton.setSize(50,50);
		JLabel locateLabel = new JLabel("Locate", JLabel.CENTER);
		locateLabel.setSize(80,25);
		locateLabel.setLocation(210,130);
		locateLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		locateLabel.setForeground(Color.cyan);
		locateLabel.setBackground(Color.black);
		locateLabel.setOpaque(true);
		searchframe.add(locateLabel);

		ImageIcon lootIcon = new ImageIcon("actions/trade.gif");
		lootButton = new JButton(lootIcon);
		lootButton.setLocation(350,50);
		lootButton.setSize(50,50);
		JLabel lootLabel = new JLabel("Loot", JLabel.CENTER);
		lootLabel.setSize(80,25);
		lootLabel.setLocation(350,130);
		lootLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		lootLabel.setForeground(Color.cyan);
		lootLabel.setBackground(Color.black);
		lootLabel.setOpaque(true);
		searchframe.add(lootLabel);
		searchframe.add(peerButton);
		searchframe.add(locateButton);
		searchframe.add(lootButton);

		locateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//DWARF SPECIAL ABILITY:CAVE KNOWLEDGE and WOODS GIRL SPECIAL ABILITY: TRACKING SKILLS
				//ITEM SPECIAL ABILITY : LUCKY CHARM
				Dice die = new Dice();
				int x,i;
				if ((m.userClassData.get(0).equals("Dwarf") && inCave) || inventoryModel.contains("Lucky Charm") || (m.userClassData.get(0).equals("Woods Girl") && soundChit.getText() != null 
						&& soundChit.getText()!= "" && soundChit.getText().contains("(W)")))
				{
					x = die.roll();
					if (inventoryModel.contains("Lucky Charm"))
						updateText("The Lucky Charm Aids Your Search");
					if (m.userClassData.get(0).equals("Dwarf") && inCave)
						updateText("Cave Knowledge Aids Your Search");
					if (m.userClassData.get(0).equals("Woods Girl") && soundChit.getText() != null 
							&& soundChit.getText()!= "" && soundChit.getText().contains("(W)"))
						updateText("Tracking Skills Aid Your Search");
				}
				else {
					i = die.roll();
					x = die.roll();
					if(i>x)
						x = i;
				}
				//ITEM SPECIAL ABILITY : MAP OF RUINS
				if (inventoryModel.contains("Map of Ruins") && soundChit.getText().contains("Ruins"))
				{
					if (x>0)
						x--;
					updateText("The Map of Ruins Aids Your Search");
				}
				//ITEM SPECIAL ABILITY : MAP OF Lost City
				if (inventoryModel.contains("Map of Lost City") && siteChit.getText().contains("Lost City"))
				{
					if (x>0)
						x--;
					updateText("The Map of Lost City Aids Your Search");
				}
				//ITEM SPECIAL ABILITY : MAP OF Lost Castle
				if (inventoryModel.contains("Map of Lost Castle") && siteChit.getText().contains("Lost Castle"))
				{
					if (x>0)
						x--;
					updateText("The Map of Lost Castle Aids Your Search");
				}
				//roll 2 or 4, find a site chit
				//roll 3 or 4 find a cave entrance
				if (m.cheatMode)
				{
					Object[] diceSheet = {"1", "2", "3", "4", "5", "6"};
					String temp = (String) JOptionPane.showInputDialog(sideframe, 
							"Select the die value to be used for Locate",
							"Cheat Mode: Locate",
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
				Integer playerLoc[] = m.getPlayerLocationsData().get(m.getUserClassData().get(0));
				boolean found = false;
				if(x==2||x==4){
					for(String str: m.siteChitLocation.keySet()){
						for(Integer[] loc: m.siteChitNeighborhood.get(str)){
							if(playerLoc[0].equals(loc[0]) && playerLoc[1].equals(loc[1])){
								int u = m.siteChits.indexOf(str);
								if (m.siteChits.get(u) != null && m.playerSitesFound.get(m.userClassData.get(0)).get(u)!=1)
								{
									//		System.out.println(u);
									m.playerSitesFound.get(m.getUserClassData().get(0)).set(u, 1);
									JOptionPane.showMessageDialog(null,"Chit Found!\n"+m.siteChits.get(u));
									//		System.out.println("Chit Found");
									siteChit.setText(m.siteChits.get(u));
									searchframe.setVisible(false);
									found = true;
									break;
								}
							}
						}
					}
					if(!found)
						JOptionPane.showMessageDialog(null,"No Sites Found" );
					searchframe.setVisible(false);
				}
				found = false;
				//3 or 2
				if(x==3||x==2){
					//for each adjacent clearing, check if it is in m.cavesEntrances.
					//if one of them are, add the caves' coordinates to the caveFound arrayList
					for (Integer[] caves : m.cavesEntrances)
					{
						for (Integer[] adj : m.adjacentClearings.keySet())
						{
							if (adj[0].equals(playerLoc[0]) && adj[1].equals(playerLoc[1]))
								for (Integer[] adj2 : m.adjacentClearings.get(adj))
								{
									if (caves[0].equals(adj2[0]) && caves[1].equals(adj2[1]))
									{
										if (!m.cavesFound.contains(caves))
										{
											JOptionPane.showMessageDialog(null,"Discovered A Cave Entrance");
											m.cavesFound.add(caves);
											m.locationEntered=1;
											found = true;
										}
									}
								}
						}
					}
					if(!found)
						JOptionPane.showMessageDialog(null,"No Passages Found" );
					searchframe.setVisible(false);
				}
				//if nothing
				if(x!=3&&x!=2&&x!=4){
					JOptionPane.showMessageDialog(null,"Nothing Found" );
				}
				searchframe.setVisible(false);
			}
		});

		peerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//DWARF SPECIAL ABILITY:CAVE KNOWLEDGE and WOODS GIRL SPECIAL ABILITY: TRACKING SKILLS
				//ITEM SPECIAL ABILITY : LUCKY CHARM
				Dice die = new Dice();
				int x,z;
				if ((m.userClassData.get(0).equals("Dwarf") && inCave) || inventoryModel.contains("Lucky Charm") || (m.userClassData.get(0).equals("Woods Girl") && soundChit.getText() != null 
						&& soundChit.getText()!= "" && soundChit.getText().contains("(W)")))
				{
					x = die.roll();
					if (inventoryModel.contains("Lucky Charm"))
						updateText("The Lucky Charm Aids Your Search");
					if (m.userClassData.get(0).equals("Dwarf") && inCave)
						updateText("Cave Knowledge Aids Your Search");
					if (m.userClassData.get(0).equals("Woods Girl") && soundChit.getText() != null 
							&& soundChit.getText()!= "" && soundChit.getText().contains("(W)"))
						updateText("Tracking Skills Aid Your Search");
				}
				else {
					z = die.roll();
					x = die.roll();
					if(z>x)
						x = z;
				}

				if (m.cheatMode)
				{
					Object[] diceSheet = {"1", "2", "3", "4", "5", "6"};
					String temp = (String) JOptionPane.showInputDialog(sideframe, 
							"Select the die value to be used for Peer",
							"Cheat Mode: Peer",
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
				//roll 3 or 4, find a player
				//roll 2 or 3 find a secret path
				boolean othersFound = false;
				for(String s : m.getUserClassData())
				{
					if (!s.equals(m.getUserClassData().get(0)))
					{
						if (m.getPlayerLocationsData().get(s) != null
								&& m.getPlayerLocationsData().get(s)[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
								&& m.getPlayerLocationsData().get(s)[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
						{
							othersFound = true;
						}
					}
				}
				boolean found = false;

				if((x==3||x==4) && othersFound){
					for(String s : m.getUserClassData())
					{
						if (!s.equals(m.getUserClassData().get(0)))
						{
							if (m.getPlayerLocationsData().get(s) != null
									&& m.getPlayerLocationsData().get(s)[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
									&& m.getPlayerLocationsData().get(s)[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1])
									&& m.userStatusData.get(s).equals("Hidden"))
							{
								m.getUserStatusData().put(s, "Normal");
								JOptionPane.showMessageDialog(null,"Discovered "+s );

								found = true;
								updateMap(m);
							}
						}
					}
					if(!found)
					{
						JOptionPane.showMessageDialog(null,"No Players Here" );
					}
					searchframe.setVisible(false);
				}
				found = false;
				//3 or 2
				if(x==3||x==2){
					//for each adjacent clearing, check if it is in m.cavesEntrances.
					//if one of them are, add the caves' coordinates to the caveFound arrayList
					Integer[] playerLoc = m.playerLocationsData.get(m.userClassData.get(0));

					for (Integer[] entrance : m.secretPathsEntrances)
					{
						if(playerLoc[0].equals(entrance[0]) && playerLoc[1].equals(entrance[1])){
							found = true;
							m.secretPathsFound.add(playerLoc);
							Integer[] arr = {entrance[2],entrance[3]};
							m.secretPathsFound.add(arr);
							JOptionPane.showMessageDialog(null,"Discovered a Secret Path");
						}
					}
					if(!found)
					{
						JOptionPane.showMessageDialog(null,"No Paths Found" );
					}
					searchframe.setVisible(false);
				}
				//if nothing
				if(x!=3&&x!=2&&x!=4){
					JOptionPane.showMessageDialog(null,"Nothing Found" );

				}
				searchframe.setVisible(false);
			}
		});

		lootButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				searchframe.setVisible(false);
				MagicRealmGUILootScreen(m);
			}
		});

		JLabel fakelabel = new JLabel("");
		searchframe.add(fakelabel);
		searchframe.setVisible(true);   
	}

	public void MagicRealmGUIJoinScreen(MagicRealmVariables m)
	{
		//m.event = new JLabel("Day 1: Birdsong");
		siteChit = new JLabel("");
		soundChit = new JLabel("");
		m.start = 1;
		joinframe = new JFrame("Create A Profile");
		joinframe.setSize(500, 628);
		joinframe.setLocation(200,0);
		joinframe.setDefaultCloseOperation(3);

		joinframe.setResizable(false);
		try {
			joinframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("join.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}


		selectedChar = new JLabel("No Selected Char", JLabel.CENTER);
		selectedChar.setSize(200, 20);
		selectedChar.setFont(new Font("Serif", Font.PLAIN, 18));
		selectedChar.setForeground(Color.cyan);
		selectedChar.setBackground(Color.black);
		selectedChar.setOpaque(true);
		selectedChar.setLocation(150, 480);
		joinframe.add(selectedChar);

		JButton selectChar = new JButton("Select A Character");
		selectChar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//joinframe.setVisible(false);
				MagicRealmGUICharSelect(m);
			}
		});
		selectChar.setLocation(150, 500);
		selectChar.setSize(200, 25);
		joinframe.add(selectChar);

		selectDwelling = new JButton("Select Dwelling");
		if(selectedChar.getText().equals("No Selected Char"))
			selectDwelling.setEnabled(false);
		selectDwelling.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//joinframe.setVisible(false);
				MagicRealmGUIDwellingSelect(m);
			}
		});
		selectDwelling.setSize(200, 25);
		selectDwelling.setLocation(150, 525);
		joinframe.add(selectDwelling);

		selectVictoryConditions = new JButton("Select Victory Conditions");
		if(selectedChar.getText().equals("No Selected Char"))
			selectVictoryConditions.setEnabled(false);
		selectVictoryConditions.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//joinframe.setVisible(false);
				MagicRealmGUIVictoryPointScreen(m,selectedChar.getText());
				selectVictoryConditions.setEnabled(false);
			}
		});
		selectVictoryConditions.setSize(200, 25);
		selectVictoryConditions.setLocation(150, 550);
		joinframe.add(selectVictoryConditions);

		joinGame = new JButton("Join Game");
		if(selectedChar.getText().equals("No Selected Char"))
			joinGame.setEnabled(false);
		joinGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String newCharType = selectedChar.getText();

				if(m.userClassData.size()!=0){
					m.userClassData.add(0,newCharType);
				}else{
					m.userClassData.add(newCharType);
				}
				//INIT INVENTORY MODELS
				for (String s : m.getInventoryData().get(m.getUserClassData().get(0)))
					inventoryModel.addElement(s);

				for (String s : m.getInactiveInventoryData().get(m.getUserClassData().get(0)))
					inactiveInventoryModel.addElement(s);

				for (String s : m.getWoundedInventoryData().get(m.getUserClassData().get(0)))
					woundedInventoryModel.addElement(s);

				for (String s : m.getFatiguedInventoryData().get(m.getUserClassData().get(0)))
					fatiguedInventoryModel.addElement(s);

				userModel.addElement(m.getUserClassData().get(0));
				joinframe.setVisible(false);
				//SEND INFO TO SERVER
				m.setPlay(1);
				//START THE MAP
				MagicRealmGUIMainScreenMap(m);
				//PLACE THE (CORRECT) SOUND CHIT FOR THE LOCATION THE PLAYER IS IN.
				Integer[] playerLoc = m.playerLocationsData.get(m.getUserClassData().get(0));
				for(String str: m.soundChitNeighborhood.keySet()){
					for(Integer[] loc: m.soundChitNeighborhood.get(str)){
						if(playerLoc[0].equals(loc[0]) && playerLoc[1].equals(loc[1])){
							int u = m.soundChits.indexOf(str);
							soundChit.setText(m.soundChits.get(u));
							break;
						}
					}
				}

			}
		});
		joinGame.setSize(200, 25);
		joinGame.setLocation(150, 575);
		joinframe.add(joinGame);

		JLabel fakes = new JLabel("");
		joinframe.add(fakes);
		joinframe.setVisible(true);
	}

	public void paintMap (MagicRealmVariables m) {

		System.out.println("\t---\tPaint Map");

		newMap=new MapImage();
		map = newMap.setMapImage(m);

		// newMap.writeImage(map, "test", "gif");
		mapIcon = new ImageIcon(map);
		mapLabel = new JLabel(mapIcon);
		mapLabel.addMouseListener(new MouseAdapter() {
			@Override //I override only one method for presentation
			public void mousePressed(MouseEvent e) {
				//System.out.println("Y'all clicked at: "+e.getX() + ", " + e.getY()+" in the image.");
				//ITEM SPECIAL ABILITY : ANCIENT TELESCOPE
				if (usingTelescope)
				{
					oldLoc[0] = m.playerLocationsData.get(m.userClassData.get(0))[0];
					oldLoc[1] = m.playerLocationsData.get(m.userClassData.get(0))[1];
					for (Integer[] val : m.adjacentClearings.keySet())
					{
						//upper right corner
						if ((e.getX() >= (val[0] - 40)) && (e.getX() <=(val[0])) && (e.getY() >= (val[1] - 40)) && (e.getY() <= val[1]))
						{
							m.playerLocationsData.put(m.userClassData.get(0), val);
							MagicRealmGUILootScreen(m);
							
						}
						//bottom left corner
						else if ((e.getX() >= (val[0])) && (e.getX() <= (val[0] +40)) && (e.getY() >= (val[1] - 40)) && (e.getY() <= val[1]))
						{
							m.playerLocationsData.put(m.userClassData.get(0), val);
							MagicRealmGUILootScreen(m);
						}
						//bottom right corner
						else if ( (e.getX() >= (val[0] -40)) && (e.getX() <=val[0]) && (e.getY() >= val[1])  && (e.getY() <= (val[1] + 40)))
						{
							m.playerLocationsData.put(m.userClassData.get(0), val);
							MagicRealmGUILootScreen(m);
						}
						else if ((e.getX() >= (val[0])) && (e.getX() <= (val[0] +40)) &&  (e.getY() >= val[1])  && (e.getY() <= (val[1] + 40)))
						{
							m.playerLocationsData.put(m.userClassData.get(0), val);
							MagicRealmGUILootScreen(m);
						}
					}
				}
				else
				{
					int maxActions = 0;
					if (m.userClassData.get(0).equals("Amazon"))
					{
						maxActions = 5;
					}
					else 
						maxActions = 4;
					if ((ok.isEnabled()) && (numActions < maxActions))
					{
						for (Integer[] key : m.getAdjacentClearings().keySet())
						{
							// as we iterate through the clearing where the player(key) is...
							// check if mouse was pressed in range of an adjacent clearing
							if ((key[0].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[0]))
									&& (key[1].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[1])))
							{
								//System.out.println(m.adjacentClearings.get(key));
								//check to see if we clicked around an adjacent clearing(val)
								for (Integer[] val : m.getAdjacentClearings().get(key))
								{
	
									//upper right corner
									if ((e.getX() >= (val[0] - 40)) && (e.getX() <=(val[0])) && (e.getY() >= (val[1] - 40)) && (e.getY() <= val[1]))
									{
										moveLogic(val);
									}
									//bottom left corner
									else if ((e.getX() >= (val[0])) && (e.getX() <= (val[0] +40)) && (e.getY() >= (val[1] - 40)) && (e.getY() <= val[1]))
									{
										moveLogic(val);
									}
									//bottom right corner
									else if ( (e.getX() >= (val[0] -40)) && (e.getX() <=val[0]) && (e.getY() >= val[1])  && (e.getY() <= (val[1] + 40)))
									{
										moveLogic(val);
									}
									else if ((e.getX() >= (val[0])) && (e.getX() <= (val[0] +40)) &&  (e.getY() >= val[1])  && (e.getY() <= (val[1] + 40)))
									{
										moveLogic(val);
									}	
	
	
								}    
							}
						}
					}
				}
			}
		});
		mapPanel = new JPanel();
		mapLabel.setIcon(mapIcon);
		mapLabel.setSize(800,1100);
		mapLabel.setLocation(0, 0);

		mapPanel = new JPanel();
		mapPanel.add(mapLabel);

		JLabel fake = new JLabel("");
		fake.setLocation(400,400);
		mapPanel.add(fake);
		mapPanel.setSize(800,1100);

		mapScrollPanel.add(mapPanel);
		mapScrollPanel.setLocation(205, 115);
		mapScrollPanel.setSize(550, 450);
		mapScrollPanel.setViewportView(mapLabel);
		mapScrollPanel.getViewport().setViewPosition(new Point(m.playerLocationsData.get(m.userClassData.get(0))[0]-400,m.playerLocationsData.get(m.userClassData.get(0))[1]-300));

		mainframe.add(mapScrollPanel);
		mainframe.setVisible(true);

	}

	public void MagicRealmGUIMainScreenMap(MagicRealmVariables m)
	{
		System.out.println("\t---\tInitialzation");
		mainframe = new JFrame(m.userClassData.get(0) + "'s Map");
		mainframe.setSize(800, 600);
		mainframe.setLocation(500,80);
		mainframe.setDefaultCloseOperation(3);
		mapScrollPanel = new JScrollPane();


		//if (!initialzed){

		paintMap(m);
		MagicRealmGUIMainScreenSidebar(m);
		//}

		initialzed = true;

	}


	public void MagicRealmGUIMainScreenSidebar(MagicRealmVariables m){
		sideframe = new JFrame(m.userClassData.get(0)  + "'s Sidepanel");
		sideframe.setSize(620, 675);
		sideframe.setDefaultCloseOperation(3);

		JButton cheat = new JButton((m.cheatMode) ? "God" : "Cheat?");
		cheat.setSize(90,25);
		cheat.setLocation(510,50);
		cheat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (!m.cheatMode)
				{
					cheat.setText("God");
					m.cheatMode=true;
				}
				else
				{
					m.cheatMode=false;
					cheat.setText("Cheat?");
				}
			}
		});
		sideframe.add(cheat);


		charTitle = new JLabel(m.getUserClassData().get(0));
		charTitle.setSize(200,30);
		charTitle.setLocation(200,0);
		charTitle.setFont(new Font("Serif", Font.PLAIN, 18));
		sideframe.add(charTitle);


		m.event.setSize(200,30);
		m.event.setLocation(200,16);
		m.event.setFont(new Font("Serif", Font.PLAIN, 18));
		sideframe.add(m.event);

		soundChit.setSize(200,70);
		soundChit.setLocation(200,46);
		soundChit.setFont(new Font("Serif", Font.PLAIN, 18));
		sideframe.add(soundChit);
		siteChit.setSize(200,30);
		siteChit.setLocation(200,46);
		siteChit.setFont(new Font("Serif", Font.PLAIN, 18));
		sideframe.add(siteChit);

		userModel.clear();
		for (String string : m.userClassData) {
			userModel.addElement(string);
		}

		userList = new JList<String>(userModel);
		JScrollPane listScrollPane = new JScrollPane(userList);
		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					if (m.userClassData.contains(userList.getSelectedValue())
							&& userList.getSelectedValue()!=null
							&& !userList.getSelectedValue().equals(""))
						MagicRealmGUIPlayerInfoScreen(m, userList.getSelectedValue());
				}
			}
		};
		userList.addMouseListener(mouseListener);
		listScrollPane.setSize(150, 100);
		listScrollPane.setLocation(5, 5);
		sideframe.add(listScrollPane);

		//list of user's actions appearing on main gui
		actionList = new JList<String>(actionModel);
		JScrollPane actionListScrollPane = new JScrollPane(actionList);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					//int index = userList.locationToIndex(e.getPoint());
					//System.out.println("Double clicked on Item " + index);
				}
			}
		};
		actionList.addMouseListener(mouseListener);
		actionListScrollPane.setSize(200, 400);
		actionListScrollPane.setLocation(5, 115);
		sideframe.add(actionListScrollPane);




		chat = new JButton("Chat");
		chat.setSize(80, 30);
		chat.setLocation(105, 600);
		chat.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				sendChatMessage(chatBox.getText());
			}
		});
		sideframe.add(chat);
		chatBox = new JTextArea();
		chatBox.setFont(new Font("Serif", Font.PLAIN, 18));
		JScrollPane chatBoxScrollPane = new JScrollPane(chatBox);
		chatBoxScrollPane.setSize(260, 30);
		chatBoxScrollPane.setLocation(205, 600);
		sideframe.add(chatBoxScrollPane);


		//This is where the user is informed of what's happening in the game
		textBox = new JTextArea();
		updateText("Team Lazer Cobra's Magic Realm");
		JScrollPane textBoxScrollPane = new JScrollPane(textBox);
		textBoxScrollPane.setSize(185, 400);
		textBoxScrollPane.setLocation(210, 115);
		sideframe.add(textBoxScrollPane);


		//list of user's inventory appearing on main gui
		inventoryList = new JList<String>(inventoryModel);
		JScrollPane inventoryListScrollPane = new JScrollPane(inventoryList);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{

				int index = inventoryList.locationToIndex(e.getPoint());
				if (SwingUtilities.isRightMouseButton(e))
				{
					inventoryList.setSelectedIndex(index);

					//Check if user clicked on weapon
					for (String z : m.weaponData.keySet()) {
						if (inventoryList.getSelectedValue() != null 
								&& !inventoryList.getSelectedValue().equals("")
								&& (inventoryList.getSelectedValue().equals(z)))
							MagicRealmGUIWeaponInfoScreen(m, inventoryList.getSelectedValue());
					}

					for (String z : m.treasureProp.keySet()) {
						if (inventoryList.getSelectedValue() != null 
								&& !inventoryList.getSelectedValue().equals("")
								&& (inventoryList.getSelectedValue().equals(z)))
							MagicRealmGUITreasureInfoScreen(m, inventoryList.getSelectedValue());
					}
				}
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2)
				{

					m.moveActiveToInactive = 1;
				}
			}
		};
		inventoryList.addMouseListener(mouseListener);
		inventoryListScrollPane.setSize(200, 100);
		inventoryListScrollPane.setLocation(400, 115);
		sideframe.add(inventoryListScrollPane);

		inactiveInventoryList = new JList<String>(inactiveInventoryModel);
		JScrollPane inactiveInventoryListScrollPane = new JScrollPane(inactiveInventoryList);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				int index = inactiveInventoryList.locationToIndex(e.getPoint());
				if (SwingUtilities.isRightMouseButton(e))
				{
					inactiveInventoryList.setSelectedIndex(index);

					//Check if user clicked on weapon
					for (String z : m.weaponData.keySet()) {
						if (inactiveInventoryList.getSelectedValue() != null 
								&& !inactiveInventoryList.getSelectedValue().equals("")
								&& inactiveInventoryList.getSelectedValue().equals(z))
							MagicRealmGUIWeaponInfoScreen(m, inactiveInventoryList.getSelectedValue());
					}
					for (String z : m.treasureProp.keySet()) {
						if (inactiveInventoryList.getSelectedValue() != null 
								&& !inactiveInventoryList.getSelectedValue().equals("")
								&& (inactiveInventoryList.getSelectedValue().equals(z)))
							MagicRealmGUITreasureInfoScreen(m, inactiveInventoryList.getSelectedValue());
					}
				}
				if (e.getClickCount() == 2)
				{
					boolean isArmour=false;
					for (int i = 0; i < inventoryModel.size(); i++) {
						if(inactiveInventoryList.getSelectedValue()!=null
								&& (!inactiveInventoryList.getSelectedValue().equals(""))
								&& inventoryModel.get(i)!=null
								&& inventoryModel.get(i).equals(inactiveInventoryList.getSelectedValue())){
							String temp=inactiveInventoryList.getSelectedValue();
							if(temp.equals("Breastplate") || temp.equals("Suit of Armour") || temp.equals("Shield")
									|| temp.equals("Helmet")){
								isArmour=true;
								JOptionPane.showMessageDialog(null, "Can't carry more than one "+temp, "Armour notice",
										JOptionPane.INFORMATION_MESSAGE);
							}
							for (String string : m.weaponData.keySet()) {
								if(string.equals(temp)){
									isArmour=true;
									JOptionPane.showMessageDialog(null, "Can't carry more than one weapon", "Weapon notice",
											JOptionPane.INFORMATION_MESSAGE);
								}
							}
						}
					}
					if(!isArmour){
						String value = inactiveInventoryList.getSelectedValue();
						if (value != null && !value.contains("gold"))
						{
							m.moveInactiveToActive = 1;

						}
					}
				}
			}
		};
		inactiveInventoryList.addMouseListener(mouseListener);
		inactiveInventoryListScrollPane.setSize(200, 100);
		inactiveInventoryListScrollPane.setLocation(400, 215);
		sideframe.add(inactiveInventoryListScrollPane);


		fatiguedInventoryList = new JList<String>(fatiguedInventoryModel);
		JScrollPane fatiguedInventoryListScrollPane = new JScrollPane(fatiguedInventoryList);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					//int index = fatiguedInventoryList.locationToIndex(e.getPoint());
					//System.out.println("Double clicked on Item " + index);
				}
			}
		};
		fatiguedInventoryList.addMouseListener(mouseListener);
		fatiguedInventoryListScrollPane.setSize(200, 100);
		fatiguedInventoryListScrollPane.setLocation(400, 315);
		sideframe.add(fatiguedInventoryListScrollPane);


		//list of user's combat chits appearing on main gui
		woundedInventoryList = new JList<String>(woundedInventoryModel);
		JScrollPane woundedInventoryListScrollPane = new JScrollPane(woundedInventoryList);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					//int index = woundedInventoryList.locationToIndex(e.getPoint());
				}
			}
		};
		woundedInventoryList.addMouseListener(mouseListener);
		woundedInventoryListScrollPane.setSize(200, 100);
		woundedInventoryListScrollPane.setLocation(400, 415);
		sideframe.add(woundedInventoryListScrollPane);

		//add the hide,trade,follow,etc labels
		JLabel followLabel = new JLabel("Follow", JLabel.CENTER);
		followLabel.setSize(50, 30);
		followLabel.setLocation(110, 520);
		sideframe.add(followLabel);
		JLabel searchLabel = new JLabel("Search", JLabel.CENTER);
		searchLabel.setSize(50, 30);
		searchLabel.setLocation(160, 520);
		sideframe.add(searchLabel);
		JLabel alertLabel = new JLabel("Alert", JLabel.CENTER);
		alertLabel.setSize(50, 30);
		alertLabel.setLocation(210, 520);
		sideframe.add(alertLabel);
		JLabel tradeLabel = new JLabel("Trade", JLabel.CENTER);
		tradeLabel.setSize(50, 30);
		tradeLabel.setLocation(260, 520);
		sideframe.add(tradeLabel);
		JLabel hideLabel = new JLabel("Hide", JLabel.CENTER);
		hideLabel.setSize(50, 30);
		hideLabel.setLocation(310, 520);
		sideframe.add(hideLabel);
		JLabel mingleLabel = new JLabel("Hire", JLabel.CENTER);
		mingleLabel.setSize(50, 30);
		mingleLabel.setLocation(360, 520);
		sideframe.add(mingleLabel);
		JLabel restLabel = new JLabel("Rest", JLabel.CENTER);
		restLabel.setSize(50, 30);
		restLabel.setLocation(410, 520);
		sideframe.add(restLabel);
		JLabel doneLabel = new JLabel("Done", JLabel.CENTER);
		doneLabel.setSize(50, 30);
		doneLabel.setLocation(460, 520);
		sideframe.add(doneLabel);


		//add the hide,trade,follow,etc buttons...

		ImageIcon followIcon = new ImageIcon("actions/move.gif");

		follow = new JButton(followIcon);
		follow.setSize(50, 50);
		follow.setLocation(110, 540);
		follow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MagicRealmGUIFollow(m);

			}
		});
		sideframe.add(follow);


		ImageIcon searchIcon = new ImageIcon("actions/search.gif");

		search = new JButton(searchIcon);
		search.setSize(50,50);
		search.setLocation(160, 540);
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//  the Search icon on the main gui does this
				MagicRealmGUISearchScreen(m);
				actionModel.addElement("Search");
				setButtons();
			}
		});

		sideframe.add(search);

		ImageIcon alertIcon = new ImageIcon("actions/alert.gif");
		alert = new JButton(alertIcon);
		alert.setSize(50, 50);
		alert.setLocation(210, 540);
		alert.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//  the Alert icon on the main gui does this
				actionModel.addElement("Alert");
				setButtons();
			}
		});
		sideframe.add(alert);

		ImageIcon tradeIcon = new ImageIcon("actions/trade.gif");
		trade = new JButton(tradeIcon);
		trade.setSize(50, 50);
		trade.setLocation(260, 540);
		trade.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//  the Trade icon on the main gui does this
				actionModel.addElement("Trade");
				MagicRealmGUITradeSearch(m);

				setButtons();
			}
		});
		sideframe.add(trade);

		ImageIcon hideIcon = new ImageIcon("actions/hide.gif");
		hide = new JButton(hideIcon);
		hide.setSize(50, 50);
		hide.setLocation(310, 540);
		hide.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if ( m.userStatusData.get(m.userClassData.get(0)).equals("Normal") )
				{

					/*	DRUID SPECIAL ABILITY:CONCEALMENT and DWARF SPECIAL ABILITY:CAVE KNOWLEDGE 
					 *	and WOODS GIRL SPECIAL ABILITY : TRACKING SKILLS 						
					 *	ITEM SPECIAL ABILITY : LUCKY CHARM 											*/
					if (m.userClassData.get(0).equals("Druid")
							|| inventoryModel.contains("Lucky Charm")
							|| (m.userClassData.get(0).equals("Dwarf") && inCave)
							|| (m.userClassData.get(0).equals("Woods Girl") && soundChit.getText() != null 
							&& soundChit.getText()!= "" && soundChit.getText().contains("(W)")))
					{
						Dice d1;
						d1 = new Dice();
						int roll1 = d1.roll();
						if (inventoryModel.contains("Lucky Charm"))
							updateText("The Lucky Charm Aids Your Concealment");
						if (m.userClassData.get(0).equals("Dwarf") && inCave)
							updateText("Cave Knowledge Aids Your Concealment");
						if (m.userClassData.get(0).equals("Woods Girl") && soundChit.getText() != null 
								&& soundChit.getText()!= "" && soundChit.getText().contains("(W)"))
							updateText("Tracking Skills Aid Your Concealment");
						if (m.cheatMode)
						{
							Object[] diceSheet = {"1", "2", "3", "4", "5", "6"};
							String temp = (String) JOptionPane.showInputDialog(sideframe, 
									"Select the die value to be used for Hide",
									"Cheat Mode: Hide",
									JOptionPane.INFORMATION_MESSAGE,
									null,
									diceSheet,
									1);
							if(temp!=null){
								roll1 = Integer.parseInt(temp);
							}else{
								roll1 = d1.roll();
							}
						}
						if (roll1 != 6 ){
							//  the hide icon on the main gui does this
							actionModel.addElement("Hide");
							m.userStatusData.put(m.userClassData.get(0), "Hidden");
							setButtons();
						}
						else{
							JOptionPane.showMessageDialog(null, "Unable to Hide", "Unlucky",
									JOptionPane.INFORMATION_MESSAGE);

							actionModel.addElement("Hide Failed");
							setButtons();
						}
					}
					//else : Player has no special ability
					else{

						Dice d1,d2;
						d1 = new Dice();
						d2 = new Dice();
						int roll1 = d1.roll();
						int roll2 = d2.roll();
						if (roll1 < roll2) 
							roll1 = roll2;
						if (m.cheatMode)
						{
							Object[] diceSheet = {"1", "2", "3", "4", "5", "6"};
							String temp = (String) JOptionPane.showInputDialog(sideframe, 
									"Select the die value to be used for Hide",
									"Cheat Mode: Hide",
									JOptionPane.INFORMATION_MESSAGE,
									null,
									diceSheet,
									1);
							if(temp!=null){
								roll1 = Integer.parseInt(temp);
							}else{
								roll1 = d1.roll();
							}
						}
						if (roll1 != 6)
						{
							//  the hide icon on the main gui does this
							actionModel.addElement("Hide");
							m.userStatusData.put(m.userClassData.get(0), "Hidden");
							setButtons();
						}


						else{
							JOptionPane.showMessageDialog(null, "Unable to Hide", "Unlucky",
									JOptionPane.INFORMATION_MESSAGE);
							actionModel.addElement("Hide Failed");
							setButtons();
						}
					}

				}
				else{
					JOptionPane.showMessageDialog(null, "Player is already hidden", "Cant hide twice ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		sideframe.add(hide);

		ImageIcon hireIcon = new ImageIcon("actions/hire.gif");
		//Mingle button
		mingle = new JButton(hireIcon);
		mingle.setSize(50, 50);
		mingle.setLocation(360, 540);
		mingle.setEnabled(false);
		for (Integer[] nativeLoc : m.nativeLocation.values())
			if (nativeLoc[0].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[0])
					&& nativeLoc[1].equals(m.getPlayerLocationsData().get(m.userClassData.get(0))[1]))
			{
				mingle.setEnabled(true);
				break;
			}
		mingle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MagicRealmGUINatives(m);
			}
		});
		sideframe.add(mingle);

		ImageIcon restIcon = new ImageIcon("actions/rest.gif");
		//rest button
		rest = new JButton(restIcon);
		rest.setSize(50, 50);
		rest.setLocation(410, 540);
		rest.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//  the rest icon on the main gui does this
				MagicRealmGUIRestFrame(m);
				actionModel.addElement("Rest");
				setButtons();
			}
		});
		sideframe.add(rest);

		ImageIcon okIcon = new ImageIcon("actions/greencheck.gif");
		ok = new JButton(okIcon);
		ok.setSize(50, 50);
		ok.setLocation(460, 540);
		ok.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				hide.setEnabled(false);
				trade.setEnabled(false);
				alert.setEnabled(false);
				follow.setEnabled(false);
				hide.setEnabled(false);
				trade.setEnabled(false);
				alert.setEnabled(false);
				follow.setEnabled(false);
				mingle.setEnabled(false);
				search.setEnabled(false);
				rest.setEnabled(false);
				inventoryList.setEnabled(false);
				inactiveInventoryList.setEnabled(false);
				fatiguedInventoryList.setEnabled(false);
				woundedInventoryList.setEnabled(false);
				ok.setEnabled(false);
				shieldDodge = false;
				shieldCharge = false;
				shieldDuck = false;
				usedSpectacles = false;
				usedRegent = false;
				usedSceptre = false;
				usedCloak = false;
				usedLantern = false;
				usedBerserkWhite = false;
				usedElusiveness = false;
				//  the ok icon on the main gui does this
				if (m.getWaitingMove()==0)
					m.setWaitingMove(1);

			}
		});
		sideframe.add(ok);


		JLabel fakes = new JLabel("");
		sideframe.add(fakes);
		//mainframe.add(mapScrollPanel);

		sideframe.setVisible(true);
	}

	// COMBAT MODE
	public void MagicRealmGUICombatScreen(MagicRealmVariables m)
	{

		// SKIP TO MIDNIGHT
		if (m.getWaitingMove()==0)
			m.setWaitingMove(1);
		combatframe = new JFrame(m.userClassData.get(0) + ": Combat");
		combatframe.setSize(400, 750);
		try {
			combatframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("combat.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//adding players to combat list based on player locations
		combatPlayerModel.removeAllElements();
		for (String s : m.getUserClassData())
		{
			if (m.getPlayerLocationsData().get(s)!=null
					&& m.getPlayerLocationsData().get(m.getUserClassData().get(0))!=null
					&& m.getUserClassData().get(0) != s
					&& !combatPlayerModel.contains(s)
					&& (m.getPlayerLocationsData().get(s)[0].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[0]))
					&& (m.getPlayerLocationsData().get(s)[1].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[1])))
			{
				if (m.getUserStatusData().get(s) != "Hidden")
					combatPlayerModel.addElement(s);
			}
		}    

		//adding monsters to combat list based on sound chits

		for(String spawnedMonster : m.generatedMonsters)
			combatPlayerModel.addElement(spawnedMonster);

		//System.out.println(combatPlayerModel.toArray().toString());
		combatPlayerList = new JList<String>(combatPlayerModel);
		combatPlayerModel.removeElement(m.getUserClassData().get(0));
		playerListScrollPanel = new JScrollPane(combatPlayerList);
		MouseAdapter mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					//we need a monster info frame

					//player info frame
					for (String s : m.userClassData)
						if (combatPlayerList.getSelectedValue().equals(s))
							MagicRealmGUIPlayerInfoScreen(m,s);
				}
			}
		};
		combatPlayerList.addMouseListener(mouseListener);
		playerListScrollPanel.setSize(400, 50);
		playerListScrollPanel.setLocation(0, 0);
		combatframe.add(playerListScrollPanel);

		JLabel attackLabel = new JLabel("Attack : ");
		attackLabel.setLocation(20,65);
		attackLabel.setSize(60,20);
		attackLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		attackLabel.setForeground(Color.black);
		attackLabel.setBackground(Color.white);
		attackLabel.setOpaque(true);
		combatframe.add(attackLabel);

		swing = new JButton("Swing");
		swing.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				swing.setEnabled(false);
				thrust.setEnabled(false);
				smash.setEnabled(false);
				attackType="Swing";
			}
		});
		swing.setLocation(100,65);
		swing.setSize(75,75);
		combatframe.add(swing);

		thrust = new JButton("Thrust");
		thrust.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				swing.setEnabled(false);
				thrust.setEnabled(false);
				smash.setEnabled(false);
				attackType="Thrust";
			}
		});
		thrust.setLocation(185,65);
		thrust.setSize(75,75);
		combatframe.add(thrust);

		smash = new JButton("Smash");
		smash.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				swing.setEnabled(false);
				thrust.setEnabled(false);
				smash.setEnabled(false);
				attackType="Smash";
			}
		});
		smash.setLocation(265,65);
		smash.setSize(75,75);
		combatframe.add(smash);


		JLabel weaponLabel = new JLabel("Weapon : ");
		weaponLabel.setLocation(20,175);
		weaponLabel.setSize(60,20);
		weaponLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		weaponLabel.setForeground(Color.black);
		weaponLabel.setBackground(Color.white);
		weaponLabel.setOpaque(true);
		combatframe.add(weaponLabel);


		JLabel thumb = new JLabel();
		thumb.setSize(75, 75);
		thumb.setLocation(70, 175);
		thumb.setBackground(Color.white);
		thumb.setOpaque(true);
		combatframe.add(thumb);

		chitLabel4 = new JLabel("Chit: ");
		chitLabel4.setLocation(155,175);
		chitLabel4.setSize(80,20);
		chitLabel4.setFont(new Font("Serif", Font.PLAIN, 18));
		chitLabel4.setForeground(Color.black);
		chitLabel4.setBackground(Color.white);
		chitLabel4.setOpaque(true);
		combatframe.add(chitLabel4);

		chitLabel = new JLabel();
		chitLabel.setLocation(190,175);
		chitLabel.setSize(100,20);
		chitLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		chitLabel.setForeground(Color.black);
		chitLabel.setBackground(Color.white);
		chitLabel.setOpaque(true);
		combatframe.add(chitLabel);



		JLabel maneuverLabel = new JLabel("Maneuver : ");
		maneuverLabel.setLocation(20,275);
		maneuverLabel.setSize(60,20);
		maneuverLabel.setFont(new Font("Serif", Font.PLAIN, 12));
		maneuverLabel.setForeground(Color.black);
		maneuverLabel.setBackground(Color.white);
		maneuverLabel.setOpaque(true);
		combatframe.add(maneuverLabel);

		ImageIcon shieldIcon = new ImageIcon("combat/shield.gif");
		JButton shielddodge = new JButton(shieldIcon);
		JButton shieldcharge = new JButton(shieldIcon);
		JButton shieldduck = new JButton(shieldIcon);
		if (inventoryModel.contains("Shield"))
		{
			JLabel shieldLabel = new JLabel("Shield : ");
			shieldLabel.setLocation(20,410);
			shieldLabel.setSize(60,20);
			shieldLabel.setFont(new Font("Serif", Font.PLAIN, 12));
			shieldLabel.setForeground(Color.black);
			shieldLabel.setBackground(Color.white);
			shieldLabel.setOpaque(true);
			combatframe.add(shieldLabel);
			shielddodge.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					shieldDodge = true;
					shieldDuck = false;
					shieldCharge = false;
					shieldcharge.setEnabled(false);
					shieldduck.setEnabled(false);
					shielddodge.setEnabled(false);
					dodge.setEnabled(false);
				}
			});
			shielddodge.setLocation(100,410);
			shielddodge.setSize(38,38);
			combatframe.add(shielddodge);
			shieldcharge.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					shieldDodge = false;
					shieldDuck = false;
					shieldCharge = true;
					shieldcharge.setEnabled(false);
					shieldduck.setEnabled(false);
					shielddodge.setEnabled(false);
					charge.setEnabled(false);
				}
			});
			shieldcharge.setLocation(200,410);
			shieldcharge.setSize(38,38);
			combatframe.add(shieldcharge);
			shieldduck.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					shieldDodge = false;
					shieldDuck = true;
					shieldCharge = false;
					shieldcharge.setEnabled(false);
					shieldduck.setEnabled(false);
					shielddodge.setEnabled(false);
					duck.setEnabled(false);
				}
			});
			shieldduck.setLocation(300,410);
			shieldduck.setSize(38,38);
			combatframe.add(shieldduck);
		}

		dodge = new JButton("Dodge");
		dodge.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				charge.setEnabled(false);
				duck.setEnabled(false);
				dodge.setEnabled(false);
				maneuverType="Dodge";
				shielddodge.setEnabled(false);
			}
		});
		dodge.setLocation(100,275);
		dodge.setSize(75,75);
		combatframe.add(dodge);

		charge = new JButton("Charge");
		charge.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				charge.setEnabled(false);
				duck.setEnabled(false);
				dodge.setEnabled(false);
				maneuverType="Charge";
				shieldcharge.setEnabled(false);
			}
		});
		charge.setLocation(185,275);
		charge.setSize(75,75);
		combatframe.add(charge);

		duck = new JButton("Duck");
		duck.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				charge.setEnabled(false);
				duck.setEnabled(false);
				dodge.setEnabled(false);
				maneuverType="Duck";
				shieldduck.setEnabled(false);
			}
		});
		duck.setLocation(265,275);
		duck.setSize(75,75);
		combatframe.add(duck);

		chitLabel3 = new JLabel("Chit: ");
		chitLabel3.setLocation(155,375);
		chitLabel3.setSize(80,20);
		chitLabel3.setFont(new Font("Serif", Font.PLAIN, 18));
		chitLabel3.setForeground(Color.black);
		chitLabel3.setBackground(Color.white);
		chitLabel3.setOpaque(true);
		combatframe.add(chitLabel3);

		chitLabel2 = new JLabel();
		chitLabel2.setLocation(190,375);
		chitLabel2.setSize(100,20);
		chitLabel2.setFont(new Font("Serif", Font.PLAIN, 18));
		chitLabel2.setForeground(Color.black);
		chitLabel2.setBackground(Color.white);
		chitLabel2.setOpaque(true);
		combatframe.add(chitLabel2);

		JButton sendNatives = new JButton("Send Natives");
		sendNatives.setLocation(40,475);
		sendNatives.setSize(120,30);
		//	combatframe.add(sendNatives);


		runButton = new JButton("Run");
		runButton.setLocation(200,475);
		runButton.setSize(80,30);

		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//No movement chit selected, running away impossible; Return user to combat so they may provide one.
				if (chitLabel2.getText().equals(""))
					JOptionPane.showMessageDialog(null, "To Run Away From Combat, You Must Choose A Movement Chit!", "How to : Run Away",
							JOptionPane.INFORMATION_MESSAGE);
				else
				{
					//user sent partial attack variables, treat this as running away.
					//user can no longer do anything until all combat actions are entered
					running = true;
					inventoryList.setEnabled(false);
					inactiveInventoryList.setEnabled(false);
					fatiguedInventoryList.setEnabled(false);
					woundedInventoryList.setEnabled(false);
					okButton.setEnabled(false);
					runButton.setEnabled(false);
					//  the ok icon on the combat gui does this
					if (m.getWaitingCombatMove()==0)
						m.setWaitingCombatMove(1);
				}    

			}
		});
		combatframe.add(runButton);


		okButton = new JButton("OK");
		okButton.setLocation(280,475);
		okButton.setSize(80,30);

		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//Check if something is missing from the attack.
				if (chitLabel2.getText().equals("") || chitLabel.getText() == "" || combatPlayerList.getSelectedValue() == null
						|| smash.isEnabled() == true || duck.isEnabled() == true)
				{
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Choose a Fight Chit, Action Chit or Target Player First?","Warning",dialogButton);
					if(dialogResult == JOptionPane.YES_OPTION){
						//return to combat , do nothing.
					}
					else
					{
						//user sent partial attack variables, treat this as running away.
						//user can no longer do anything until all combat actions are entered
						inventoryList.setEnabled(false);
						inactiveInventoryList.setEnabled(false);
						fatiguedInventoryList.setEnabled(false);
						woundedInventoryList.setEnabled(false);
						okButton.setEnabled(false);
						//  the ok icon on the combat gui does this
						if (m.getWaitingCombatMove()==0)
							m.setWaitingCombatMove(1);
					}    

				}
				//Nothing is missing from the attack
				//Send a combat message to the users on network.
				else
				{
					if(chitLabel.getText().contains("**") || chitLabel2.getText().contains("**")){
						//For fatigue (pure sharpness chits)
						if(chitLabel.getText().contains("**")){
							boolean found=false;
							for (int i = 0; i < fatiguedInventoryModel.size(); i++) {
								if(fatiguedInventoryModel.get(i).contains("*")){
									inventoryModel.addElement(fatiguedInventoryModel.get(i));
									fatiguedInventoryModel.removeElement(fatiguedInventoryModel.get(i));
									found=true;
									break;
								}

							}

							if(!found){
								inventoryModel.addElement(fatiguedInventoryModel.get(0));
								fatiguedInventoryModel.removeElement(fatiguedInventoryModel.get(0));
							}
							inventoryModel.removeElement(chitLabel.getText());
							fatiguedInventoryModel.addElement(chitLabel.getText());
						}else if(chitLabel2.getText().contains("**")){
							inventoryModel.removeElement(chitLabel2.getText());
							fatiguedInventoryModel.addElement(chitLabel2.getText());
						}
					}else{
						int count=0;
						if(chitLabel.getText().contains("*")&&chitLabel2.getText().contains("*")){
							//Random rand=new Random();
							//count=rand.nextInt(2)+1;
							//if (m.cheatMode)
							//{
							Object[] chitSheet = {chitLabel.getText(), chitLabel2.getText()};
							String temp = (String) JOptionPane.showInputDialog(sideframe, 
									"Select the chit to be fatigued",
									"Fatiguing",
									JOptionPane.INFORMATION_MESSAGE,
									null,
									chitSheet,
									chitLabel.getText());
							if(temp!=null && temp.equals(chitLabel.getText())){
								count = 1;
							}else if(temp!=null && temp.equals(chitLabel2.getText())){
								count = 2;
							}else{
								count = 0;
							}
							//}
							switch (count) {
							case 1:
								inventoryModel.removeElement(chitLabel.getText());
								fatiguedInventoryModel.addElement(chitLabel.getText());
								break;
							case 2:
								inventoryModel.removeElement(chitLabel2.getText());
								fatiguedInventoryModel.addElement(chitLabel2.getText());
								break;

							default:
								break;
							}
						}
					}

					//user can no longer do anything until all combat actions are entered
					inventoryList.setEnabled(false);
					inactiveInventoryList.setEnabled(false);
					fatiguedInventoryList.setEnabled(false);
					woundedInventoryList.setEnabled(false);
					okButton.setEnabled(false);
					//  the ok icon on the combat gui does this
					if (m.getWaitingCombatMove()==0)
						m.setWaitingCombatMove(1);
				}

			}
		});
		combatframe.add(okButton);

		inventoryList.setEnabled(false);
		inactiveInventoryList.setEnabled(false);
		fatiguedInventoryList.setEnabled(false);
		woundedInventoryList.setEnabled(false);
		ok.setEnabled(false);
		combatInventoryModel.removeAllElements();
		for (int s = 0; s < inventoryModel.getSize(); s++)
		{
			if (inventoryModel.getElementAt(s)!=null)
			{	
				if (inventoryModel.getElementAt(s).equals("Deft Gloves")) 
					combatInventoryModel.addElement("FIGHTL2");
	
				else if (inventoryModel.getElementAt(s).equals("Handy Gloves")) 
					combatInventoryModel.addElement("FIGHTM3");
	
				else if (inventoryModel.getElementAt(s).equals("Shoes of Stealth"))
					combatInventoryModel.addElement("MOVEL3");
	
				else if (inventoryModel.getElementAt(s).equals("Scroll of Nature"))
					combatInventoryModel.addElement("MAGICII");
	
				else if (inventoryModel.getElementAt(s).equals("Scroll of Alchemy"))
					combatInventoryModel.addElement("MAGICVI");
	
				else if (inventoryModel.getElementAt(s).equals("Sacred Statue"))
					combatInventoryModel.addElement("MAGICI");
	
				else if (inventoryModel.getElementAt(s).equals("Power Gauntlets"))
					combatInventoryModel.addElement("FIGHTH4");
	
				else if (inventoryModel.getElementAt(s).equals("Power Boots"))
					combatInventoryModel.addElement("MOVEH4");
	
				else if (inventoryModel.getElementAt(s).equals("Hidden Ring"))
					combatInventoryModel.addElement("MAGICVI");
	
				else if (inventoryModel.getElementAt(s).equals("DragonFang Necklace"))
					combatInventoryModel.addElement("MAGICV");
	
				else if (inventoryModel.getElementAt(s).equals("Good Book"))
					combatInventoryModel.addElement("MAGICI");
	
				else if (inventoryModel.getElementAt(s).equals("Glowing Gem"))
					combatInventoryModel.addElement("MAGICVII");
	
				else if (inventoryModel.getElementAt(s).equals("Gloves of Strength"))
					combatInventoryModel.addElement("FIGHTT5");
	
				else if (inventoryModel.getElementAt(s).equals("Glimmering Ring"))
					combatInventoryModel.addElement("MAGICIII");
	
				else if (inventoryModel.getElementAt(s).equals("Eye of the Idol"))
					combatInventoryModel.addElement("MAGICII");
	
				else if (inventoryModel.getElementAt(s).equals("Enchanter's Skull"))
					combatInventoryModel.addElement("MAGICVI");
	
				else if (inventoryModel.getElementAt(s).equals("Elven Slippers"))
					combatInventoryModel.addElement("MOVEL2");
	
				else if (inventoryModel.getElementAt(s).equals("Book of Lore"))
					combatInventoryModel.addElement("MAGICIV");
	
				else if (inventoryModel.getElementAt(s).equals("Blasted Jewel"))
					combatInventoryModel.addElement("MAGICV");
	
				else if (inventoryModel.getElementAt(s).equals("Black Book"))
					combatInventoryModel.addElement("MAGICV");
	
				else if (inventoryModel.getElementAt(s).equals("Beast Pipes"))
					combatInventoryModel.addElement("MAGICVIII");
	
				else if (inventoryModel.getElementAt(s).equals("7-League Boots"))
					combatInventoryModel.addElement("MOVET5");
	
				else if (inventoryModel.getElementAt(s).equals("Quick Boots"))
					combatInventoryModel.addElement("MOVEM3");
	
				else 
					combatInventoryModel.addElement(inventoryModel.getElementAt(s));
			}
		}
		JList<String> combatInventoryList = new JList<String>(combatInventoryModel);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{
					if (combatInventoryList.getSelectedValue() != null)
					{
						//ITEM SPECIAL ABILITY : GIRTLE OF ENERGY
						if (inventoryModel.contains("Girtle of Energy"))
						{
							if (!(chitLabel2.getText().contains("**") && chitLabel.getText().contains("**")))
							{
								updateText("The Girtle of Energy Envigorates You");

								if (combatInventoryList.getSelectedValue().contains("DUCK") || combatInventoryList.getSelectedValue().contains("MOVE"))
								{
									chitLabel2.setText((String)combatInventoryList.getSelectedValue());
									if (combatInventoryList.getSelectedValue().contains("DUCK") )
										updateText("Ducking Comes Naturally To You");
								}
								else if (combatInventoryList.getSelectedValue().contains("BERSERK"))
								{
									if (combatInventoryList.getSelectedValue().contains("**"))
										chitLabel.setText("FIGHTT6**");
									else if (combatInventoryList.getSelectedValue().contains("*"))
										chitLabel.setText("FIGHTT6*");
									else 
										chitLabel.setText("FIGHTT6");
									updateText("Berserk!");
								}
								else if (combatInventoryList.getSelectedValue().contains("FIGHT"))
									chitLabel.setText((String)combatInventoryList.getSelectedValue());

							}
						}
						//ITEM SPECIAL ABILITY : POTION OF ENERGY
						else if (inventoryModel.contains("Potion of Energy"))
						{
							updateText("The Potion of Energy Envigorates You");
							if (combatInventoryList.getSelectedValue().contains("DUCK") || combatInventoryList.getSelectedValue().contains("MOVE"))
							{
								chitLabel2.setText((String)combatInventoryList.getSelectedValue());
								if (combatInventoryList.getSelectedValue().contains("DUCK") )
									updateText("Ducking Comes Naturally To You");
							}
							else if (combatInventoryList.getSelectedValue().contains("BERSERK"))
							{
								if (combatInventoryList.getSelectedValue().contains("**"))
									chitLabel.setText("FIGHTT6**");
								else if (combatInventoryList.getSelectedValue().contains("*"))
									chitLabel.setText("FIGHTT6*");
								else 
									chitLabel.setText("FIGHTT6");
								updateText("Berserk!");
							}
							else if (combatInventoryList.getSelectedValue().contains("FIGHT"))
								chitLabel.setText((String)combatInventoryList.getSelectedValue());						
						}
						else
						{
							if (((String)combatInventoryList.getSelectedValue()).contains("MOVE"))
							{
								//calculating the effort of each chit, makes sure the max effort you can use is 2 * worth, spaghetti code
								if ((chitLabel.getText().length()==9&&((String)combatInventoryList.getSelectedValue()).length()==6) ||
										(chitLabel.getText().length()==8&&((String)combatInventoryList.getSelectedValue()).length()<=7) ||
										(chitLabel.getText().length()<=7&&((String)combatInventoryList.getSelectedValue()).length()<=8) )
									chitLabel2.setText((String)combatInventoryList.getSelectedValue());
							}
							//DWARF SPECIAL ABILITY: DUCK
							if (((String)combatInventoryList.getSelectedValue()).contains("DUCK"))
							{
								if ((chitLabel.getText().length()==9&&((String)combatInventoryList.getSelectedValue()).length()==6) ||
										(chitLabel.getText().length()==8&&((String)combatInventoryList.getSelectedValue()).length()<=7) ||
										(chitLabel.getText().length()<=7&&((String)combatInventoryList.getSelectedValue()).length()<=8) )
								{
									chitLabel2.setText((String)combatInventoryList.getSelectedValue());
									updateText("Ducking Comes Naturally To You");
								}
							}

							if (((String)combatInventoryList.getSelectedValue()).contains("FIGHT"))
							{
								if ((chitLabel2.getText().length()==8&&((String)combatInventoryList.getSelectedValue()).length()==7) ||
										(chitLabel2.getText().length()==7&&((String)combatInventoryList.getSelectedValue()).length()<=8) ||
										(chitLabel2.getText().length()<=6&&((String)combatInventoryList.getSelectedValue()).length()<=9) )
									chitLabel.setText((String)combatInventoryList.getSelectedValue());
							}
							//BESERKER SPECIAL ABILITY : BERSERK
							if (((String)combatInventoryList.getSelectedValue()).contains("BERSERK"))
							{
								if (!(chitLabel2.getText().contains("**")&&combatInventoryList.getSelectedValue().contains("*")) &&
										!(chitLabel2.getText().contains("*")&&combatInventoryList.getSelectedValue().contains("**")))
								{
									if (combatInventoryList.getSelectedValue().contains("**"))
										chitLabel.setText("FIGHTT"+combatInventoryList.getSelectedValue().charAt(8)+"**");

									else if (combatInventoryList.getSelectedValue().contains("*"))
										chitLabel.setText("FIGHTT"+combatInventoryList.getSelectedValue().charAt(8)+"*");

									else chitLabel.setText("FIGHTT"+combatInventoryList.getSelectedValue().charAt(8));
									updateText("Berserk!");
								}
							}

						}



						/*if (((String)combatInventoryList.getSelectedValue()).contains("MAGIC"))
                  	{
                      	chitLabel.setText((String)combatInventoryList.getSelectedValue());
                  	}*/

						for (String string : m.weaponData.keySet()) {
							if ((combatInventoryList.getSelectedValue().equals(string))){
								String testString = combatInventoryList.getSelectedValue();
								String firstLetter = testString.substring(0,1).toLowerCase();
								String restLetters = testString.substring(1);
								String resultString = firstLetter + restLetters;

								//If its a weapon with a space in the name, ie Thrusting Sword, format accordingly: thrusting_sword
								String resultString2 = resultString;
								//if its a special weapon, give the right image as resultstring2.
								if (combatInventoryList.getSelectedValue().contains("Alchemist") && combatInventoryList.getSelectedValue().contains("Mixture"))
								{
									resultString2 = "alchemistsmixture";
								}
								else if (combatInventoryList.getSelectedValue().contains("Bane Sword"))
								{
									resultString2 ="great_sword";
								}
								else if (combatInventoryList.getSelectedValue().contains("Truesteel Sword")
										|| combatInventoryList.getSelectedValue().contains("Devil Broadsword"))
								{
									resultString2 ="broadsword";
								}
								else if (combatInventoryList.getSelectedValue().contains("Living Thrusting Sword"))
								{
									resultString2 ="thrusting_sword";
								}
								else {
									for (int c = 0; c < resultString.toCharArray().length; c++) {
										if (Character.isWhitespace(resultString.charAt(c))) {
											resultString2 = resultString.substring(0,c)+'_';
											String firstLetter2 = resultString.substring(c+1,c+2).toLowerCase();
											String restLetters2 = resultString.substring(c+2);
											resultString2 = resultString2 + firstLetter2 + restLetters2;
										}
									}
								}
								icon = new ImageIcon("weapons/"+ resultString2 + ".gif");
								thumb.setIcon(icon);
								activeWeapon = combatInventoryList.getSelectedValue();
							}
						}
					}
				}
			}
		};
		combatInventoryList.addMouseListener(mouseListener);
		combatInventoryListScrollPanel = new JScrollPane(combatInventoryList);
		combatInventoryListScrollPanel.setSize(130, 190);
		combatInventoryListScrollPanel.setLocation(0, 520);
		combatframe.add(combatInventoryListScrollPanel);

		fatiguedInventoryList = new JList<String>(fatiguedInventoryModel);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{

				}
			}
		};
		fatiguedInventoryList.addMouseListener(mouseListener);
		fatiguedInventoryListScrollPanel = new JScrollPane(fatiguedInventoryList);
		fatiguedInventoryListScrollPanel.setSize(130, 190);
		fatiguedInventoryListScrollPanel.setLocation(130, 520);
		combatframe.add(fatiguedInventoryListScrollPanel);

		woundedInventoryList = new JList<String>(woundedInventoryModel);
		mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 1)
				{

				}
			}
		};
		woundedInventoryList.addMouseListener(mouseListener);
		woundedInventoryListScrollPanel = new JScrollPane(woundedInventoryList);
		woundedInventoryListScrollPanel.setSize(130, 190);
		woundedInventoryListScrollPanel.setLocation(260, 520);
		combatframe.add(woundedInventoryListScrollPanel);

		JLabel ShakerSaba = new JLabel("");
		combatframe.add(ShakerSaba);
		combatframe.setVisible(true);
	}
	public void MagicRealmGUIDwellingSelect(MagicRealmVariables m)
	{
		dwellingframe = new JFrame("Magic Realms: Dwelling Selection");
		dwellingframe.setSize(535,325);
		dwellingframe.setLocation(200,200);


		try {
			dwellingframe.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("dwellings.jpg")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon innIcon = new ImageIcon("dwellings/inn.gif");
		inn = new JButton(innIcon);
		inn.setSize(75, 75);
		inn.setLocation(25, 25);
		inn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedLoc.setText("Inn");
			}
		});
		dwellingframe.add(inn);


		ImageIcon chapelIcon = new ImageIcon("dwellings/chapel.gif");
		chapel = new JButton(chapelIcon);
		chapel.setSize(75, 75);
		chapel.setLocation(125, 25);
		chapel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedLoc.setText("Chapel");
			}
		});
		dwellingframe.add(chapel);


		ImageIcon houseIcon = new ImageIcon("dwellings/house.gif");
		house = new JButton(houseIcon);
		house.setSize(75,75);
		house.setLocation(225, 25);
		house.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedLoc.setText("House");
			}
		});
		dwellingframe.add(house);

		ImageIcon ghostIcon = new ImageIcon("dwellings/ghost.gif");
		ghost = new JButton(ghostIcon);
		ghost.setSize(75, 75);
		ghost.setLocation(325, 25);
		ghost.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedLoc.setText("Ghost");
			}
		});
		dwellingframe.add(ghost);

		ImageIcon guardIcon = new ImageIcon("dwellings/guard.gif");
		guard = new JButton(guardIcon);
		guard.setSize(75, 75);
		guard.setLocation(425, 25);
		guard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				selectedLoc.setText("Guard");
			}
		});
		dwellingframe.add(guard);


		selectedLoc = new JLabel("", JLabel.CENTER);
		selectedLoc.setFont(new Font("Serif", Font.PLAIN, 18));
		selectedLoc.setForeground(Color.cyan);
		selectedLoc.setBackground(Color.black);
		selectedLoc.setOpaque(true);
		selectedLoc.setSize(200,25);
		selectedLoc.setLocation(150,200);
		dwellingframe.add(selectedLoc);


		selectLoc = new JButton("Select");
		selectLoc.setSize(200,25);
		selectLoc.setLocation(150,250);
		selectLoc.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (selectedLoc.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "You Must Select A Starting Location!", "Select a Dwelling",
							JOptionPane.INFORMATION_MESSAGE);    
				}else{
					m.playerLocationsData.get(selectedChar.getText())[0] = m.dwellingLocationsData.get(selectedLoc.getText())[0];
					m.playerLocationsData.get(selectedChar.getText())[1] = m.dwellingLocationsData.get(selectedLoc.getText())[1];
					selectVictoryConditions.setEnabled(true);
					joinframe.setVisible(true);
					dwellingframe.setVisible(false);
				}
			}
		});
		dwellingframe.add(selectLoc);

		inn.setEnabled(false);
		house.setEnabled(false);
		chapel.setEnabled(false);
		guard.setEnabled(false);
		ghost.setEnabled(false);
		for(String dwell : m.startingLocations.get(selectedChar.getText()))
		{
			if (dwell.equals("Inn"))
			{
				inn.setEnabled(true);
			}
			if (dwell.equals("House"))
			{
				house.setEnabled(true);
			}
			if (dwell.equals("Guard"))
			{
				guard.setEnabled(true);
			}
			if (dwell.equals("Ghost"))
			{
				ghost.setEnabled(true);
			}
			if (dwell.equals("Chapel"))
			{
				chapel.setEnabled(true);
			}
		}
		JLabel MaxHanna = new JLabel("");
		dwellingframe.add(MaxHanna);
		dwellingframe.setVisible(true);

	}
	// CHARACTER SELECTION
	public void MagicRealmGUICharSelect(MagicRealmVariables m)
	{
		charframe = new JFrame("Magic Realms: Character Selection");
		selectDwelling.setEnabled(false);
		joinGame.setEnabled(false);
		selectVictoryConditions.setEnabled(false);
		if (dwellingframe != null)
			dwellingframe.setVisible(false);
		if (victorypointframe != null)
			victorypointframe.setVisible(false);

		JScrollPane scrollPane = new JScrollPane();
		charframe.setPreferredSize(new Dimension(550, 600));
		charframe.add(scrollPane);
		charframe.setLayout(new BorderLayout());
		charframe.setSize(550, 600);
		charframe.getContentPane().setBackground(Color.black);
		Icon icon = new ImageIcon("characterdetail/amazon.jpg");
		JLabel thumb = new JLabel();
		thumb.setIcon(icon);
		thumb.setSize(370, 600);
		thumb.setLocation(0, 0);
		charframe.add(thumb);

		JButton amazonButton = new JButton("Amazon");
		amazonButton.setSize(180, 25);
		amazonButton.setLocation(370, 25);
		amazonButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Amazon"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);       	 
				ImageIcon icon2 = new ImageIcon("characterdetail/amazon.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Amazon");
			}
		});
		charframe.add(amazonButton);
		JButton berserkerButton = new JButton("Berserker");
		berserkerButton.setSize(180, 25);
		berserkerButton.setLocation(370, 50);
		berserkerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Berserker"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/berserker.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Berserker");
			}
		});
		charframe.add(berserkerButton);
		JButton black_knightButton = new JButton("Black Knight");
		black_knightButton.setSize(180, 25);
		black_knightButton.setLocation(370, 75);
		black_knightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Black Knight"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/black_knight.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Black Knight");
			}
		});
		charframe.add(black_knightButton);
		JButton captainButton = new JButton("Captain");
		captainButton.setSize(180, 25);
		captainButton.setLocation(370, 100);
		captainButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Captain"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/captain.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Captain");
			}
		});
		charframe.add(captainButton);
		JButton druidButton = new JButton("Druid");
		druidButton.setSize(180, 25);
		druidButton.setLocation(370, 125);
		druidButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Druid"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/druid.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Druid");
			}
		});
		charframe.add(druidButton);
		JButton dwarfButton = new JButton("Dwarf");
		dwarfButton.setSize(180, 25);
		dwarfButton.setLocation(370, 150);
		dwarfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Dwarf"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/dwarf.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Dwarf");
			}
		});
		charframe.add(dwarfButton);
		JButton elfButton = new JButton("Elf");
		elfButton.setSize(180, 25);
		elfButton.setLocation(370, 175);
		elfButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Elf"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/elf.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Elf");
			}
		});
		charframe.add(elfButton);
		JButton magicianButton = new JButton("Magician");
		magicianButton.setSize(180, 25);
		magicianButton.setLocation(370, 200);
		magicianButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Magician"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/magician.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Magician");
			}
		});
		charframe.add(magicianButton);
		JButton pilgrimButton = new JButton("Pilgrim");
		pilgrimButton.setSize(180, 25);
		pilgrimButton.setLocation(370, 225);
		pilgrimButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Pilgrim"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/pilgrim.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Pilgrim");
			}
		});
		charframe.add(pilgrimButton);
		JButton sorcerorButton = new JButton("Sorceror");
		sorcerorButton.setSize(180, 25);
		sorcerorButton.setLocation(370, 250);
		sorcerorButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Sorceror"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/sorceror.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Sorceror");
			}
		});
		charframe.add(sorcerorButton);
		JButton swordsmanButton = new JButton("Swordsman");
		swordsmanButton.setSize(180, 25);
		swordsmanButton.setLocation(370, 275);
		swordsmanButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Swordsman"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/swordsman.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Swordsman");
			}
		});
		charframe.add(swordsmanButton);
		JButton white_knightButton = new JButton("White Knight");
		white_knightButton.setSize(180, 25);
		white_knightButton.setLocation(370, 300);
		white_knightButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("White Knight"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/white_knight.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("White Knight");
			}
		});
		charframe.add(white_knightButton);
		JButton witchButton = new JButton("Witch");
		witchButton.setSize(180, 25);
		witchButton.setLocation(370, 325);
		witchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Witch"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/witch.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Witch");
			}
		});
		charframe.add(witchButton);
		JButton witch_kingButton = new JButton("Witch King");
		witch_kingButton.setSize(180, 25);
		witch_kingButton.setLocation(370, 350);
		witch_kingButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Witch King"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/witch_king.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Witch King");
			}
		});
		charframe.add(witch_kingButton);
		JButton wizardButton = new JButton("Wizard");
		wizardButton.setSize(180, 25);
		wizardButton.setLocation(370, 375);
		wizardButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Wizard"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/wizard.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Wizard");
			}
		});
		charframe.add(wizardButton);
		JButton woodsgirlButton = new JButton("Woods Girl");
		woodsgirlButton.setSize(180, 25);
		woodsgirlButton.setLocation(370, 400);
		woodsgirlButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (m.getUserClassData().contains("Woods Girl"))
					JOptionPane.showMessageDialog(null, "Player Already Chosen!", "Cannot Select Character",
							JOptionPane.INFORMATION_MESSAGE);    
				ImageIcon icon2 = new ImageIcon("characterdetail/woods_girl.jpg");
				thumb.setIcon(icon2);
				selectedChar.setText("Woods Girl");
			}
		});
		charframe.add(woodsgirlButton);

		JButton select = new JButton("Select");
		select.setSize(180, 25);
		select.setLocation(370, 500);
		select.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{    
				if (m.userClassData.contains(selectedChar.getText()))
				{
					selectedChar.setText("Character In Use");
					joinGame.setEnabled(false);
					joinframe.setVisible(true);
					charframe.setVisible(false);
					selectDwelling.setEnabled(false);
				}
				else
				{
					if ((selectedChar.getText().equals("No Selected Char")) || (selectedChar.getText().equals("Character In Use")))
					{
						selectDwelling.setEnabled(false);
						joinGame.setEnabled(false);
						charframe.setVisible(false);
						joinframe.setVisible(true);
					}
					else
					{
						selectDwelling.setEnabled(true);
						joinGame.setEnabled(false);
						charframe.setVisible(false);
						joinframe.setVisible(true);
					}
				}
			}
		});

		charframe.add(select);

		JLabel BrandonHurley = new JLabel("");
		charframe.add(BrandonHurley);

		charframe.setVisible(true);
	}

	//Method for all the GUI buttons (excluding "OK")
	public void setButtons(){
		numActions++;
		boolean hasSpectacles = false;
		boolean hasRegent = false;
		boolean hasSceptre = false;
		boolean hasCloak = false;
		boolean hasLantern = false;
		boolean hasElusiveness = false;
		boolean isBerserkWhite = false;


		//checking for special abilities allows for
		//more then 4 numActions (player actions)
		if (m.userClassData.get(0).equals("Berserker")
				|| m.userClassData.get(0).equals("White Knight"))
		{
			isBerserkWhite = true;
		}

		if (m.userClassData.get(0).equals("Elf"))
		{
			hasElusiveness = true;
		}


		for (int i = 0; i < inventoryModel.size(); i++) {

			if (inventoryModel.get(i) != null){
				if(inventoryModel.get(i).equals("Magic Spectacles")){
					hasSpectacles=true;
				}
				if(inventoryModel.get(i).equals("Regent of Jewels")){
					hasRegent=true;
				}
				if(inventoryModel.get(i).equals("Royal Sceptre")){
					hasSceptre=true;
				}
				if(inventoryModel.get(i).equals("Cloak of Mist")){
					hasCloak=true;
				}
				if(inventoryModel.get(i).equals("Shielded Lantern")){
					hasLantern=true;
				}
			}

		}

		if (numActions >= 4)
		{
			if(hasLantern && !usedLantern){
				hide.setEnabled(true);
				trade.setEnabled(true);
				mingle.setEnabled(true);
				search.setEnabled(true);
				usedLantern=true;

				alert.setEnabled(false);
				follow.setEnabled(false);
				if(isBerserkWhite && !usedBerserkWhite){
					rest.setEnabled(true);
					usedBerserkWhite=true;
					updateText("Robust Health Aids You");
				} else{
					rest.setEnabled(false);
				}
				updateText("The Shielded Lantern Empowers You");
			}else{
				hide.setEnabled(false);
				trade.setEnabled(false);
				mingle.setEnabled(false);
				search.setEnabled(false);
				if(hasCloak && !usedCloak){
					hide.setEnabled(true);
					usedCloak=true;
					updateText("The Cloak of Mist Empowers You");
				}else{
					hide.setEnabled(false);
					if(hasElusiveness && !usedElusiveness){
						hide.setEnabled(true);
						usedElusiveness=true;
						updateText("Elusiveness Aids You");
					}else{
						hide.setEnabled(false);
					}
				}

				

				if(hasRegent && !usedRegent){
					trade.setEnabled(true);
					usedRegent=true;
					updateText("The Regent of Jewels Empowers You");
				}else{
					trade.setEnabled(false);
				}
				alert.setEnabled(false);
				follow.setEnabled(false);
				if(hasSceptre && !usedSceptre){
					mingle.setEnabled(true);
					usedSceptre=true;
					updateText("The Royal Scepter Empowers You");
				}else{
					mingle.setEnabled(false);
				}

				if(hasSpectacles && !usedSpectacles){
					search.setEnabled(true);
					usedSpectacles=true;
					updateText("The Magic Spectacles Empower You");
				} else{
					search.setEnabled(false);
				}

				if(isBerserkWhite && !usedBerserkWhite){
					rest.setEnabled(true);
					usedBerserkWhite=true;
					updateText("Robust Health Aids You");
				} else{
					rest.setEnabled(false);
				}
			}

			

		} 
	}

	public void moveLogic(Integer[] hyperLoc){
		boolean canPass = true;

		//For secret passages
		if (!inSecretPassage){
			boolean found = false;
			for (Integer[] entrance : m.secretPathsEntrances) {
				if (entrance[0].equals(hyperLoc[0]) && entrance[1].equals(hyperLoc[1]))
				{
					found = true;
					break;
				}
			}
			if(found)
				inSecretPassage = true;
			else
				inSecretPassage = false;
			canPass = true;
		}else{
			boolean found = false;
			for (Integer[] entrance : m.secretPathsEntrances) {
				if(entrance[0].equals(hyperLoc[0]) && entrance[1].equals(hyperLoc[1])){
					found = true;
					boolean foundPath = false; 
					for(Integer[] entr : m.secretPathsFound){
						if (entr[0].equals(hyperLoc[0]) && entr[1].equals(hyperLoc[1]))
						{
							canPass = true;
							foundPath = true;
							break;
						}
					}
					if(!foundPath){
						if(!entrance[2].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[0]) || 
								!entrance[3].equals(m.getPlayerLocationsData().get(m.getUserClassData().get(0))[1]))
						{
							canPass = true;
							break;
						}
						else
						{
							canPass = false;
							break;
						}
					}
					else{
						break;
					}
				}
			}
			if(!found){
				canPass = true;
				inSecretPassage = false;
			}
		}

		if (!inCave)
		{
			for (int i = 0; i < m.cavesEntrances.size(); i++) {
				if (m.cavesEntrances.get(i)[0].equals(hyperLoc[0]) && m.cavesEntrances.get(i)[1].equals(hyperLoc[1]))
				{
					canPass = false;
					//player is trying to step into a cave... check if they discovered it first
					for (Integer[] foundCave : m.cavesFound)
					{
						if (foundCave[0].equals(hyperLoc[0])
								&& foundCave[1].equals(hyperLoc[1]))
						{
							canPass = true;
							inCave = true;
							break;
						}														
					}
					if (!canPass)
						updateText("This Cave is Hidden");
				}
			}
		}
		else
		{
			// if the player finds another cave entrance while in a cave,
			// add it to the cavesFound arrayList
			for (int i = 0; i < m.cavesEntrances.size(); i++) {
				if (m.cavesEntrances.get(i)[0].equals(hyperLoc[0]) && m.cavesEntrances.get(i)[1].equals(hyperLoc[1]))
				{
					if (!m.cavesFound.contains(hyperLoc))
					{
						m.cavesFound.add(hyperLoc);
					}
				}
			}
		}

		boolean tooTired = false;
		if (numActions == 3 
				&& !m.userClassData.get(0).equals("Amazon"))
		{
			for (int i = 0; i < m.mountainClearings.size(); i++) {
				if (m.mountainClearings.get(i)[0].equals(hyperLoc[0]) && m.mountainClearings.get(i)[1].equals(hyperLoc[1]))
				{
					updateText("You are too tired to climb");
					tooTired = true;
					break;
				}
			}	
		}
		if (numActions == 4 
				&& m.userClassData.get(0).equals("Amazon"))
		{
			for (int i = 0; i < m.mountainClearings.size(); i++) {
				if (m.mountainClearings.get(i)[0].equals(hyperLoc[0]) && m.mountainClearings.get(i)[1].equals(hyperLoc[1]))
				{
					updateText("You are too tired to climb");
					tooTired = true;
					break;
				}
			}	
		}
		if (!tooTired && canPass)
		{
			actionModel.addElement("Move " + hyperLoc[0] + " " + hyperLoc[1]);
			for (int i = 0; i < m.mountainClearings.size(); i++) {
				if (m.mountainClearings.get(i)[0].equals(hyperLoc[0]) && m.mountainClearings.get(i)[1].equals(hyperLoc[1]))
				{
					numActions++;
					updateText("Climbing takes its toll");
					break;
				}
			}

			//setButtons();
			m.getUserStatusData().put(m.getUserClassData().get(0), "Normal");
			m.getPlayerLocationsData().put(m.getUserClassData().get(0), hyperLoc);
			m.move=1;
			//System.out.println("Valid Move Entered!");
		}
	}
}



