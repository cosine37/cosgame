package com.cosine.cosgame.minigame.xutangbo;

public class Move {
	
	// standard moves
	public static final int UNDEFINED = -2;
	public static final int BAOSI = -1;
	public static final int XU = 0;
	public static final int BI = 1;
	public static final int TANG = 2;
	public static final int BO = 3;
	public static final int DATANG = 4;
	public static final int ZHONGBO = 5;
	public static final int QIANGLIETANG = 6;
	public static final int DABO = 7;
	public static final int BOBA = 8;
	public static final int JIUJIBO = 9;
	
	
	// extra moves
	
	public static final String[] MOVENAMES = {"xu", "bi", "tang", "bo", "datang", "zhongbo", "qianglietang", "dabo", "boba", "jiujibo"};
	public static final String[] MOVENAMESCN = {"xu", "bi", "tang", "bo", "datang", "zhongbo", "qianglietang", "dabo", "boba", "jiujibo"};
	public static final String B = "baosi";
	public static final String U = "undefined";
	
	public static final int[] ENERGY = {-1, 0, 0, 1, 1, 2, 2, 3, 4, 5};
	public static final int[] POWER = {0, 1, 0, 2, 0, 4, 0, 6, 8, 10};
	public static final int[] DEFENCE = {0, 1, 5, 2, 7, 4, 9, 6, 8, 10};
	
	int moveid;
	
	
	public Move() {
		moveid = UNDEFINED;
	}
	
	public Move(int moveid) {
		this.moveid = moveid;
	}
	
	public void setMoveid(int moveid) {
		this.moveid = moveid;
	}
	
	public int getMoveid() {
		return moveid;
	}
	
	public String getMoveName() {
		if (moveid == UNDEFINED) return U;
		if (moveid == BAOSI) return B;
		return MOVENAMES[moveid];
	}
	
	public String getMoveNameCN() {
		if (moveid == UNDEFINED) return U;
		if (moveid == BAOSI) return B;
		return MOVENAMESCN[moveid];
	}
	
	public int getEnergy() {
		if (moveid<0) return 0;
		return ENERGY[moveid];
	}
	
	public int getPower() {
		if (moveid<0) return 0;
		return POWER[moveid];
	}
	
	public int getDefence() {
		if (moveid<0) return 0;
		return DEFENCE[moveid];
	}
	
}
