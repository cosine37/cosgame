package com.cosine.cosgame.oink.pope;

public class Consts {
	public static final int REVERSENUM = 5;
	
	// Statuses
	public static final int PREGAME = 0;
	public static final int INGAME = 1;
	public static final int ROUNDEND = 2;
	public static final int ENDGAME = 3;
	
	// Phases
	public static final int PLAYCARD = 1;
	public static final int CHOOSEOPTION = 2;
	public static final int WAITING = 8;
	public static final int TARGETED = 9;
	public static final int OFFTURN = -1;
	public static final int OUT = -2;
	
	// Card Types
	public static final int CTYPE_NONE = 0;
	public static final int CTYPE_TARGET = 1;
	public static final int CTYPE_TARGETSELF = 2;
}
