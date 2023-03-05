package com.cosine.cosgame.propnighttry;

public class Board extends com.cosine.boardgame.Board{
	public Board() {
		super("propnight","board");
	}
	
	public void startGame() {
		this.setStatus(Consts.INGAME);
		int i;
		for (i=0;i<players.size();i++) {
			Player p = (Player) players.get(i);
			p.resetHP();
		}
	}
}
