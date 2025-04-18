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
	
	
	// basic places 
	public static final int PLACE_EMPTY = 0;
	public static final int PLACE_ESTATE = 1;
	public static final int PLACE_SPECIAL = 2;
	public static final int PLACE_STARTPOINT = 3;
	public static final int PLACE_TAX = 4;
	public static final int PLACE_FATE = 5;
	public static final int PLACE_JAIL = 6;
	public static final int PLACE_GOTOJAIL = 7;
	
	public static final int STARTPOINT_INDEX = 0;
	
	// GTA places
	public static final int PLACE_HOSPITAL = 11;
	public static final int PLACE_CARDGAINER = 12;
	
	
	
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
	public static final int FATE_HOUSEREPAIR = 4;
	public static final int FATE_PROPERTYTAX = 5;
	public static final int FATE_RECEIVEEVERY = 6;
	public static final int FATE_GIVEEVERY = 7;
	public static final int FATE_MOVE = 8;
	public static final int FATE_CARD = 9;
	public static final int FATE_HPSTAR = 10;
	public static final int FATE_CARDLOSE = 11;
	public static final int FATE_CARDHPSTAR = 12;
	public static final int FATE_VEHICLEHPSTAR = 13;
	public static final int FATE_RECEIVEEVERYCARD = 14;
	public static final int FATE_LOSECARDHPSTAR = 15;
	public static final int FATE_LOSEHAND = 16;
	
	// ses
	public static final int SES_ALLPLAYERS = 999;
	
	
	// settings related
	public static final int SETTINGS_MODE = 0;
	public static final int SETTINGS_START_MONEY = 1;
	public static final int SETTINGS_START_SALARY = 2;
	public static final int SETTINGS_END_CONDITION = 3;
	public static final int SETTINGS_FIRST_PLAYER = 4;
	public static final int SETTINGS_MAPID = 5;
	public static final int SETTINGS_USEGTA = 6;
	public static final int SETTINGS_USENEW = 7;
	
	// avatar related
	public static final int MAXAVATAR = 8;
	
	// GTA card related
	public static final int CARD_OPTION_THROW = 99;
	
	public static final int PLAYSTYLE_DIRECT = 0;
	public static final int PLAYSTYLE_CHOOSEPLAYER = 1;
	public static final int PLAYSTYLE_CHOOSEGRID = 2;
	public static final int PLAYSTYLE_CHOOSEOPTION = 3;
	
	public static final int GUARANTEE_UNCOMMON = 5;
	public static final int GUARANTEE_RARE = 10;
	public static final int GUARANTEE_EPIC = 20;
	
	// GTA player related
	public static final int GTA_MAXHP = 5;
	public static final int GTA_MAXSTAR = 6;
	public static final int GTA_TREATMENTFEE = 500;
}
