package com.cosine.cosgame.dominion;

public class AI {
	Player player;
	public AI() {
		
	}
	
	public AI(Player player) {
		this.player = player;
	}
	
	public void startPhase() {
		// TODO
		if (player.getPhase() == Player.START) {
			player.nextPhase();
		}
	}
	
	public void actionPhase() {
		// TODO
		if (player.getPhase() == Player.ACTION) {
			player.nextPhase();
		}
	}
	
	public void treasurePhase() {
		// TODO
		if (player.getPhase() == Player.TREASURE) {
			player.nextPhase();
		}
	}
	
	public void buyPhase() {
		// TODO
		if (player.getPhase() == Player.BUY) {
			player.nextPhase();
		}
	}
	
	public void nightPhase() {
		// TODO
		if (player.getPhase() == Player.NIGHT) {
			player.nextPhase();
		}
	}
	
	public void cleanupPhase() {
		// TODO
		if (player.getPhase() == Player.CLEANUP) {
			player.nextPhase();
		}
	}
	
	public void offturnPhase() {
		// TODO
		
	}
	
}
