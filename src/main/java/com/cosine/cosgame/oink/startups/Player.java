package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.startups.entity.CardEntity;
import com.cosine.cosgame.oink.startups.entity.PlayerEntity;

public class Player {
	String name;
	int index;
	int coins;
	int phase;
	
	int numTaken;
	
	int coin1RoundEnd;
	int coin3RoundEnd;
	
	List<Integer> scores;
	
	List<Card> hand;
	List<Card> play;
	
	Startups startups;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("index", index);
		doc.append("coins", coins);
		doc.append("phase", phase);
		doc.append("numTaken", numTaken);
		doc.append("coin1RoundEnd", coin1RoundEnd);
		doc.append("coin3RoundEnd", coin3RoundEnd);
		doc.append("scores", scores);
		
		int i;
		List<Document> doh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			doh.add(hand.get(i).toDocument());
		}
		doc.append("hand", doh);
		
		List<Document> dop = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			dop.add(play.get(i).toDocument());
		}
		doc.append("play", dop);
		
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		index = doc.getInteger("index", 0);
		coins = doc.getInteger("coins", 0);
		phase = doc.getInteger("phase", 0);
		numTaken = doc.getInteger("numTaken", 0);
		coin1RoundEnd = doc.getInteger("coin1RoundEnd", 0);
		coin3RoundEnd = doc.getInteger("coin3RoundEnd", 0);
		scores = (List<Integer>) doc.get("scores");
		List<Document> doh = (List<Document>) doc.get("hand");
		List<Document> dop = (List<Document>) doc.get("play");
		
		int i;
		hand = new ArrayList<>();
		play = new ArrayList<>();
		for (i=0;i<doh.size();i++) {
			hand.add(new Card(doh.get(i)));
		}
		for (i=0;i<dop.size();i++) {
			play.add(new Card(dop.get(i)));
		}
		
	}
	
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		entity.setIndex(index);
		entity.setCoins(coins);
		entity.setPhase(phase);
		entity.setNumTaken(numTaken);
		entity.setCoin1RoundEnd(coin1RoundEnd);
		entity.setCoin3RoundEnd(coin3RoundEnd);
		entity.setScores(scores);
		entity.setPlay(toPlayEntityList());
		entity.setAntiMonopoly(toAntiMonopolyEntityList());
		return entity;
	}
	
	public List<CardEntity> toPlayEntityList(){
		int i;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (i=5;i<=10;i++) {
			map.put(i, 0);
		}
		
		for (i=0;i<play.size();i++) {
			int x = map.get(play.get(i).getNum())+1;
			map.put(play.get(i).getNum(), x);
		}
		
		List<CardEntity> lp = new ArrayList<>();
		for (i=5;i<=10;i++) {
			CardEntity c = new Card(i).toCardEntity();
			c.setPlayed(map.get(i));
			lp.add(c);
		}
		
		return lp;
	}
	
	public List<CardEntity> toAntiMonopolyEntityList(){
		int i;
		List<CardEntity> la = new ArrayList<>();
		for (i=5;i<=10;i++) {
			if (startups.getAntiMonopoly().get(i) == index) {
				CardEntity c = new Card(i).toCardEntity();
				la.add(c);
			}
		}
		return la;
	}
	
	public Player() {
		scores = new ArrayList<>();
		hand = new ArrayList<>();
		play = new ArrayList<>();
	}
	
	public Player(String name) {
		this();
		this.name = name;
		this.coins = 0;
	}
	
	public void startRound() {
		takeCard(3);
		coins = 10;
	}
	
	public void startTurn() {
		numTaken = -1;
		phase = Consts.DRAWORTAKE;
	}
	
	public void takeCard(int x) {
		for (int i=0;i<x;i++) {
			Card c = startups.removeTopCard();
			if (c != null) hand.add(c);
		}
	}
	
	public void draw() {
		if (canDraw()) {
			// Step 1: pay coins
			int cost = startups.drawCost(this);
			coins = coins-cost;
			startups.placeCoins(this);
			
			// Step 2: set num taken (to be -1)
			this.numTaken = -1;
			
			// Step 3: put the card in hand
			Card c = startups.removeTopCard();
			if (c != null) hand.add(c);
			
			// Step 3: next phase
			if (startups.getStatus() == Consts.INGAME && phase == Consts.DRAWORTAKE) {
				startups.getLogger().logDraw(this, cost);
				
				phase = Consts.PLAYORDISCARD;
			}
		}
	}
	/*
	public void draw(int x) {
		for (int i=0;i<x;i++) draw();
	}
	*/
	public boolean canDraw() {
		if (phase != Consts.DRAWORTAKE) return false;
		int cost = startups.drawCost(this);
		if (cost>coins) {
			return false;
		} else {
			return true;
		}
	}
	
	public void take(int index) {
		if (canTake(index)) {
			// Step 1: receive coins
			int cost = startups.getDiscard().get(index).getCoinOn();
			coins = coins+cost;
			
			// Step 2: set num taken
			this.numTaken = startups.getDiscard().get(index).getNum();
			
			// Step 3: put the card in hand
			Card c = startups.getDiscard().remove(index);
			c.clearCoin();
			if (c != null) hand.add(c);

			// Step 4: next phase
			if (startups.getStatus() == Consts.INGAME) {
				startups.getLogger().logTake(this, c, cost);
				
				phase = Consts.PLAYORDISCARD;
			}
		}
	}
	
	public boolean canTake(int index) {
		if (index < startups.getDiscard().size()) {
			Card c = startups.getDiscard().get(index);
			if (startups.getAntiMonopoly().get(c.getNum()) == this.index) { // if player has antimonopoly token
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public void play(int index) { // index is where from hand
		if (index < hand.size()) {
			int i;
			boolean flag = true;
			Card c = hand.remove(index);
			play.add(c);
			startups.getLogger().logPlay(this, c, index);
			
			boolean f = startups.potentialChangeAntiMonopoly(this, c);
			
			if (startups.getStatus() == Consts.INGAME) {
				phase = Consts.OFFTURN;
				
				
				// TODO: add end round handles here
				startups.nextPlayer();
			}
		}
	}
	
	public boolean canDiscard(int index) {
		Card c = hand.get(index);
		if (c.getNum() == this.numTaken) {
			return false;
		} else {
			return true;
		}
	}
	
	public void discard(int index) { // index is where from hand
		if (index < hand.size()) {
			if (canDiscard(index)) {
				Card c = hand.remove(index);
				startups.getDiscard().add(c);
				startups.getLogger().logDiscard(this, c, index);
				
				if (startups.getStatus() == Consts.INGAME) {
					phase = Consts.OFFTURN;
					
					// TODO: add end round handles here
					startups.nextPlayer();
				}
			}
		}
	}
	
	public void emptyHandPlay() {
		hand = new ArrayList<>();
		play = new ArrayList<>();
	}
	
	public int numStockPlayed(int stock) {
		int ans = 0;
		int i;
		for (i=0;i<play.size();i++) {
			if (play.get(i).getNum() == stock) {
				ans++;
			}
		}
		return ans;
	}
	
	public int numStock(int stock) {
		int ans = 0;
		int i;
		for (i=0;i<play.size();i++) {
			if (play.get(i).getNum() == stock) {
				ans++;
			}
		}
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() == stock) {
				ans++;
			}
		}
		return ans;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getCoin1RoundEnd() {
		return coin1RoundEnd;
	}
	public void setCoin1RoundEnd(int coin1RoundEnd) {
		this.coin1RoundEnd = coin1RoundEnd;
	}
	public int getCoin3RoundEnd() {
		return coin3RoundEnd;
	}
	public void setCoin3RoundEnd(int coin3RoundEnd) {
		this.coin3RoundEnd = coin3RoundEnd;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public int getNumTaken() {
		return numTaken;
	}
	public void setNumTaken(int numTaken) {
		this.numTaken = numTaken;
	}
	public Startups getStartups() {
		return startups;
	}
	public void setStartups(Startups startups) {
		this.startups = startups;
	}
	
	
}
