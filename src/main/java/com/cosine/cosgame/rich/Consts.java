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
	public static final int PHASE_ESCAPE = 99999;
	public static final int PHASE_UTILITY = 99998;
	public static final int PHASE_STATION = 99997;
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
	public static final int PLACE_TAX = 4;
	public static final int PLACE_FATE = 5;
	public static final int PLACE_JAIL = 6;
	public static final int PLACE_GOTOJAIL = 7;
	
	public static final int STARTPOINT_INDEX = 0;
	
	// areas
	public static final int AREA_UTILITY = 101;
	public static final int AREA_STATION = 102;
	
	// events
	public static final int FATE = 1;
	public static final int CHANCE = 2;
	
	// fate types
	public static final int FATE_ADD = 1;
	public static final int FATE_LOSE = 2;
	public static final int FATE_GOTOJAIL = 3;
	
	
	// settings related
	public static final int SETTINGS_MODE = 0;
	public static final int SETTINGS_START_MONEY = 1;
	public static final int SETTINGS_START_SALARY = 2;
	public static final int SETTINGS_END_CONDITION = 3;
	public static final int SETTINGS_FIRST_PLAYER = 4;
	
	// avatar related
	public static final int MAXAVATAR = 8;
}
