package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.pope.entity.CardEntity;
import com.cosine.cosgame.oink.pope.entity.PopePlayerEntity;

public class PopePlayer {
	int index;
	int phase;
	int numKey;
	int ranking;
	boolean protect;
	boolean active;
	boolean playedThief;
	boolean confirmed;
	String name;
	
	List<Card> hand;
	List<String> endGameRewards;
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
		doc.append("confirmed",confirmed);
		doc.append("ranking", ranking);
		doc.append("endGameRewards", endGameRewards);
		if (play != null) {
			doc.append("play", play.getNum());
		} else {
			doc.append("play", -1);
		}
		List<Integer> hi = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			hi.add(hand.get(i).getNum());
		}
		doc.append("hand", hi);
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
		confirmed = doc.getBoolean("confirmed", false);
		ranking = doc.getInteger("ranking", 0);
		endGameRewards = (List<String>) doc.get("endGameRewards");
		int playId = doc.getInteger("play");
		play = CardFactory.makeCard(playId);
		List<Integer> hi = (List<Integer>) doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<hi.size();i++) {
			Card c = CardFactory.makeCard(hi.get(i));
			c.setPlayer(this);
			c.setGame(game);
			hand.add(c);
		}
	}
	
	public PopePlayerEntity toPopePlayerEntity(){
		int i;
		PopePlayerEntity entity = new PopePlayerEntity();
		entity.setIndex(index);
		entity.setPhase(phase);
		entity.setNumKey(numKey);
		entity.setProtect(protect);
		entity.setActive(active);
		entity.setPlayedThief(playedThief);
		entity.setName(name);
		entity.setConfirmed(confirmed);
		entity.setRanking(ranking);
		if (play == null) {
			entity.setPlay(new ArrayList<>());
		} else {
			List<CardEntity> lp = new ArrayList<>();
			lp.add(play.toCardEntity());
			entity.setPlay(lp);
		}
		if (game.getStatus() == Consts.ROUNDEND) {
			List<CardEntity> lh = new ArrayList<>();
			lh.add(hand.get(0).toCardEntity());
			entity.setHandRevealed(lh);
		} else {
			entity.setHandRevealed(new ArrayList<>());
		}
		return entity;
	}
	
	public PopePlayer() {
		hand = new ArrayList<>();
	}
	public PopePlayer(String name) {
		this();
		this.name = name;
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
		hand = new ArrayList<>();
		draw();
	}
	
	public void startTurn() {
		// Step 1: change phase, remove protect, and draw
		protect = false;
		phase = Consts.PLAYCARD;
		
		draw();
	}
	
	public void playCard(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			c.onPlay();
			play = c;
		}
	}
	
	public void addKey() {
		numKey = numKey+1;
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
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	
}
