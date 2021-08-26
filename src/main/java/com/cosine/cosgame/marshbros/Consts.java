package com.cosine.cosgame.marshbros;

public class Consts {
	//statuses
	public static final int PREGAME = 0;
	public static final int INGAME = 1;

	//phases
	public static final int OFFTURN = -1;
	public static final int REC1 = 1;
	public static final int ACTION = 2;
	public static final int REC2 = 3;
	public static final int SACRIFICE = 4;
	
	//choices
	public static final int ANOTHERATTACK = -2;
	public static final int NOTCHOOSED = -1;
	public static final int NOTHING = 0;
	public static final int RAID = 1;
	public static final int ATTACK = 3;
	//public static final int ACTION = 2;
	
	// ask types
	public static final int NOASK = -1;
	public static final int AUTOMATIC = 0;
	public static final int CHOOSEROLE = 1;
	public static final int CHOOSERICHEST = 2;
	
	// ask subtypes
	public static final int NEXTPHASE = 0;
	public static final int MOVETOTOMB = 1;
	public static final int RESOLVEACTION = 2;
	
	
	// card types
	public static final int ROLE = 1;
	public static final int BEAST = 2;
}

