package com.cosine.cosgame.threechaodoms;

public class Consts {
	// Factions
	public static final int WEI = 0;
	public static final int HAN = 1;
	public static final int WU = 2;
	public static final int QUN = 3;
	
	// Rule related
	public static final int STARTPOS = 4;
	public static final int CHAOSPOS = 9;
	public static final int MINPOS = 0;
	public static final int MAXPOS = 12;
	public static final int TAVERNSIZE = 3;
	public static final int REFILLSIZE = 4;
	public static final int DISCARDTOSIZE = 3;
	
	// Card where
	public static final int DECK = 0;
	public static final int TAVERN = 1;
	public static final int EXILE = 2;
	public static final int TOMB = 3;
	public static final int HAND = 4;
	public static final int PLAY = 5;
	public static final int JAIL = 6;
	
	// Board statuses
	public static final int PREGAME = 0;
	public static final int SETUP = 1;
	public static final int INGAME = 2;
	public static final int ENDGAME = 3;
	
	// Phases
	public static final int OFFTURN = -1;
	public static final int MAKEHAND = 0;
	public static final int PLAYCARD = 1;
	public static final int RECRUIT = 2;
	public static final int DISCARD = 3;
	
	// Play Types
	public static final int DIRECTPLAY = 0;
	public static final int CHOOSEONE = 1;
	public static final int CHOOSEHAND = 2;
	public static final int CHOOSEPLAYER = 3;
	public static final int CHOOSEPLAY = 4;
	public static final int CHOOSEPLAYOPTION = 5;
	
	// Play SubTypes
	public static final int INJAIL = 101;
	public static final int OTHERPLAYER = 102;
	public static final int CHOOSEPLAYWEI = 200;
	public static final int CHOOSEPLAYHAN = 201;
	public static final int CHOOSEPLAYWU = 202;
	public static final int CHOOSEPLAYQUN = 203;
}
