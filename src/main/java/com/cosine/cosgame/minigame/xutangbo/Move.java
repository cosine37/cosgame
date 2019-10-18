package com.cosine.cosgame.minigame.xutangbo;

public class Move {
	
	// standard moves
	public final int UNDEFINED = -2;
	public final int BAOSI = -1;
	public final int XU = 0;
	public final int BI = 1;
	public final int TANG = 2;
	public final int BO = 3;
	public final int DATANG = 4;
	public final int ZHONGBO = 5;
	public final int QIANGLIETANG = 6;
	public final int DABO = 7;
	public final int BOBA = 8;
	public final int JIUJIBO = 9;
	
	
	// extra moves
	
	public static final String[] MOVENAMES = {"xu", "bi", "tang", "bo", "datang", "zhongbo", "qianglietang", "dabo", "boba", "jiujibo"};
	public static final String[] MOVENAMESCN = {"xu", "bi", "tang", "bo", "datang", "zhongbo", "qianglietang", "dabo", "boba", "jiujibo"};
	public static final String B = "baosi";
	public static final String U = "undefined";
	
	public static final int[] ENERGY = {0, 0, 0, 1, 1, 2, 2, 3, 4, 5};
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
		return ENERGY[moveid];
	}
	
	public int getPower() {
		return POWER[moveid];
	}
	
	public int getDefence() {
		return DEFENCE[moveid];
	}
	
}
