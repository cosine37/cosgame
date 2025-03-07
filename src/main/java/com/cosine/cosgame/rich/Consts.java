package com.cosine.cosgame.rich;

public class Consts {
	
	// game statuses
	public static final int PREGAME = 0;
	public static final int INGAME = 1;
	public static final int ENDGAME = 2;
	
	// game modes
	public static final int CLASSIC = 0;
	
	// player phases
	public static final int PHASE_ROLL = 100;
	public static final int PHASE_MOVE = 200;
	public static final int PHASE_RESOLVE = 300;
	public static final int PHASE_OFFTURN = -1;
	
	// card type indexes
	public static final int TYPE_ACTION = 0;
	public static final int TYPE_ITEM = 1;
	public static final int TYPE_EQUIP = 2;
	public static final int TYPE_REACTION = 3;
	
	public static final int TOTALTYPES = 4;
	
	
	// places 
	public static final int PLACE_EMPTY = 0;
	public static final int PLACE_ESTATE = 1;
	public static final int PLACE_SPECIAL = 2;
	public static final int PLACE_STARTPOINT = 3;
	
	public static final int STARTPOINT_INDEX = 0;
	
	// events
	public static final int FATE = 1;
	public static final int CHANCE = 2;
	
	
	// settings related
	public static final int SETTINGS_MODE = 0;
	public static final int SETTINGS_START_MONEY = 1;
	public static final int SETTINGS_START_SALARY = 2;
	public static final int SETTINGS_END_CONDITION = 3;
	public static final int SETTINGS_FIRST_PLAYER = 4;
}
