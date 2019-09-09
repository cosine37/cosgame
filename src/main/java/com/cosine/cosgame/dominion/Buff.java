package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class Buff {
	public static final int TOTALBUFF = 200;
	List<Integer> buffs;
	
	/**
	 * 
	 * 
	 * Start from 100: Buffs in Oriental
	 * 
	 * 
	 */
	public static final int UNAFFACTEDBYATTACK = 1;
	
	public static final int MEMORIALBUYVICTORY = 100;
	public static final int PLUSONEOTHERSTART = 101;
	public static final int GAINCURSETOCOPY = 102;
	
	public Buff() {
		buffs = new ArrayList<>();
		for (int i=0;i<TOTALBUFF; i++) {
			buffs.add(0);
		}
	}
	
	public int getBuff(int index) {
		if (index < TOTALBUFF && index >= 0) {
			return buffs.get(index);
		} else {
			return 0;
		}
	}
	
	public void addBuff(int index) {
		if (index < TOTALBUFF && index >= 0) {
			int ans = buffs.get(index) + 1;
			buffs.set(index, ans);
		}
	}
	
	public void setBuffs(Board board, Player player) {
		List<Player> players = board.getPlayers();
		for (int i=0;i<players.size();i++) {
			List<Card> play = players.get(i).getPlay();
			for (int j=0;j<play.size();j++) {
				if (players.get(i).getName().equals(player.getName())) {
					addBuff(play.get(j).inPlay());
				} else {
					addBuff(play.get(j).inPlayOther());
				}
			}
		}
	}
}
