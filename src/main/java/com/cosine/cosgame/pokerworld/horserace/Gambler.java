package com.cosine.cosgame.pokerworld.horserace;

import com.cosine.cosgame.pokerworld.Consts;
import com.cosine.cosgame.pokerworld.Player;

public class Gambler {
	Player player;
	GameMap gameMap;
	int coins;
	
	public Gambler(Player player, GameMap gameMap) {
		this.player = player;
		this.gameMap = gameMap;
	}
	
	public void initialize() {
		coins = Consts.HORSERACEINITIALCOIN;
	}
	
	public void skip() {
		
	}
	
}
