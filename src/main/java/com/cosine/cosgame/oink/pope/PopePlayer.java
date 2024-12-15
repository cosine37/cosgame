package com.cosine.cosgame.oink.pope;

import java.util.List;

import org.bson.Document;

public class PopePlayer {
	int index;
	int phase;
	int numKey;
	boolean protect;
	boolean active;
	boolean playedThief;
	String name;
	
	List<Card> hand;
	Card play;
	PopeGame game;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("index",index);
		doc.append("phase",phase);
		doc.append("numKey",numKey);
		doc.append("protect",protect);
		doc.append("active",active);
		doc.append("playedThief",playedThief);
		doc.append("name",name);
		if (play != null) {
			doc.append("play", play.getNum());
		} else {
			doc.append("play", -1);
		}
		return doc;
	}
	
	public void setFromDoc(Document doc){
		int i;
		index = doc.getInteger("index",0);
		phase = doc.getInteger("phase",0);
		numKey = doc.getInteger("numKey",0);
		protect = doc.getBoolean("protect",false);
		active = doc.getBoolean("active",false);
		playedThief = doc.getBoolean("playedThief",false);
		name = doc.getString("name");
		int playId = doc.getInteger("play");
		play = CardFactory.makeCard(playId);
	}
	
	public PopePlayer() {
		
	}
	
	public void draw() {
		Card c = game.removeFromDeck();
		if (c != null) {
			hand.add(c);
		}
	}
	
	public void startRound() {
		active = true;
		phase = Consts.OFFTURN;
		draw();
	}
	
	public void startTurn() {
		// Step 1: change phase, remove protect, and draw
		protect = false;
		phase = Consts.PLAYCARD;
		draw();
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumKey() {
		return numKey;
	}
	public void setNumKey(int numKey) {
		this.numKey = numKey;
	}
	public boolean isProtect() {
		return protect;
	}
	public void setProtect(boolean protect) {
		this.protect = protect;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public Card getPlay() {
		return play;
	}
	public void setPlay(Card play) {
		this.play = play;
	}
	public PopeGame getGame() {
		return game;
	}
	public void setGame(PopeGame game) {
		this.game = game;
	}
	public boolean isPlayedThief() {
		return playedThief;
	}
	public void setPlayedThief(boolean playedThief) {
		this.playedThief = playedThief;
	}
	
}
