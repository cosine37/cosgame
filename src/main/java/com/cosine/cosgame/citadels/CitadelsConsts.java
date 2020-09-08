package com.cosine.cosgame.citadels;

public class CitadelsConsts {
	public static final int GREEN = 0;
	public static final int BLUE = 1;
	public static final int RED = 2;
	public static final int YELLOW = 3;
	public static final int PURPLE = 4;
	public static final String[] COLORNAME = {"商业", "教育", "治安", "市政", "特殊"};
	public static final int GREENBLUE = 10;
	public static final int GREENRED = 20;
	public static final int GREENYELLOW = 30;
	public static final int BLUERED = 21;
	public static final int BLUEYELLOW = 31;
	public static final int REDYELLOW = 32;
	
	// role number
	public static final int ASSASSIN = 1;
	public static final int THIEF = 2;
	public static final int MAGICIAN = 3;
	public static final int KING = 4;
	public static final int BISHOP = 5;
	public static final int MERCHANT = 6;
	public static final int ARCHITECT = 7;
	public static final int WARLORD = 8;
	
	// player phase
	public static final int OFFTURN = -1;
	public static final int TAKEACTION = 0;
	public static final int CHOOSECARD = 1;
	public static final int BUILDDISTRICT = 2;
	
	// board status
	public static final int PREGAME = 0;
	public static final int CHOOSEROLE = 1;
	public static final int TAKETURNS = 2;
	public static final int ENDGAME = 3;
	
	// role status, >=0 is the owner
	public static final int SELECTABLE = -1;
	public static final int NOTUSEDREVEALED = -2;
	public static final int NOTUSEDHIDDEN = -3;
	
	// ask types
	public static final int NOASK = 0;
	//public static final int CHOOSEROLE = 1;
	public static final int CHOOSEBUILT = 2;
	public static final int CHOOSEPLAYER = 3;
	public static final int CHOOSECARDS = 4;
	public static final int CHOOSEONECARDFROMHAND = 5;
	public static final int CHOOSETWOBUILTS = 6;
	
}
