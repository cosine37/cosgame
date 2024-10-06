package com.cosine.cosgame.pokerworld;

public class Consts {
	// settings
	public static final int GAMEMODEINDEX = 0;
	public static final int BIGGESTRANKINDEX = 1;
	public static final int FIRSTPLAYERINDEX = 2;
	public static final int TOTALROUNDSINDEX = 3;
	public static final int EXTRACARDSINDEX = 4;
	public static final int FIVETENBONUSINDEX = 5;
	
	// game modes
	public static final int SFSJ = 0;
	public static final int WIZARD = 1;
	public static final int CAMELUP = 2;
	
	// game statuses
	public static final int PREGAME = 0;
	public static final int DISTRIBUTECARDS = 1;
	public static final int DRAWHIDDEN = 2;
	public static final int PLAYCARDS = 3;
	public static final int SCORING = 4;
	
	public static final int PREENDGAME = 9;
	public static final int ENDGAME = 10;
	
	public static final int BIDTRICKS = 1;
	public static final int CONFIRMROUNDTURN = 5;
	
	public static final int STATIONCHOOSE = 21;
	public static final int CIRCUSPASS = 22;
	
	// suits
	public static final String SPADE = "s";
	public static final String HEART = "h";
	public static final String DIAMOND = "d";
	public static final String CLUB = "c";
	
	public static final String UNDETERMINED = "u";
	
	// ranks
	public static final char TEN = 'T';
	public static final char NINEMORE = 'N';
	public static final char SEVENMORE = 'S';
	
	// card playability
	public static final int PLAYABLE = 1;
	public static final int UNPLAYABLE = 0;
	
	// card options
	public static final int NOTSELECTED = -1;
	
	// round related rules
	public static final int MAXROUNDS = 20;
	public static final int FASTGAMEROUNDS = 6;
	public static final int TORNAMENTROUNDS = 10;
	public static final int[][] FASTGAMECARDS = {
		{0,2,5,8,11,14,17},
		{0,2,5,8,11,14,17},
		{0,2,5,8,11,14,17},
		{0,2,5,8,11,14,17},
		{0,2,5,8,10,12,14},
		{0,2,4,6,8,10,11},
		{0,2,4,6,7,8,9}
	};
	
	public static final int[][] TORNAMENTCARDS = {
		{0,2,4,6,8,10,12,14,16,18,20},
		{0,2,4,6,8,10,12,14,16,18,20},
		{0,2,4,6,8,10,12,14,16,18,20},
		{0,2,4,6,8,10,12,14,16,18,20},
		{0,1,3,5,7,9,11,12,13,14,15},
		{0,2,4,5,6,7,8,9,10,11,12},
		{0,1,2,3,4,5,6,7,8,9,10}
	};
	
	
	// horserace related
	public static final int HORSERACEINITIALCOIN = 10;
	
	
	
	// skin types
	//public static final int WIZARD = 1;
	public static final int JESTER = 2;
	public static final int BOMB = 3;
	public static final int DRAGON = 4;
	public static final int FAIRY = 5;
	public static final int MERLIN = 6;
	
	// skin levels
	public static final int COMMON = 0;
	public static final int RARE = 1;
	public static final int EPIC = 2;
	
	// skin totals
	public static final int[] SKINTOTALS = {0, 30, 30, 12, 12, 12, 7};
	
	// other skin related
	public static final int NOTCHOSEN = -1;
	public static final int MAXCHOSENSKINS = 12;
	
	// buy options
	public static final int BUY1HCOINS = 8001;
	public static final int BUY5HCOINS = 8005;
	public static final int BUY1KCOINS = 8010;
	public static final int BUYCHEST = 8100;
	public static final int BUYSKIN1 = 8201;
	public static final int BUYSKIN2 = 8202;
	public static final int BUYSKIN3 = 8203;
	

}
