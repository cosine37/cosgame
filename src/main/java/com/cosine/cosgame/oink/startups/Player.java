package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.startups.entity.CardEntity;
import com.cosine.cosgame.oink.startups.entity.PlayerEntity;

public class Player {
	String name;
	int index;
	int coins;
	int phase;
	int ranking;
	int roundRanking;
	int numTaken;
	int lastPlayed;
	
	boolean confirmNextRound;
	
	List<Integer> coin1s;
	List<Integer> coin3s;
	
	List<Integer> scores;
	
	List<Card> hand;
	List<Card> play;
	
	List<String> endGameRewards;
	
	Startups startups;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("index", index);
		doc.append("coins", coins);
		doc.append("phase", phase);
		doc.append("numTaken", numTaken);
		doc.append("coin1s", coin1s);
		doc.append("coin3s", coin3s);
		doc.append("scores", scores);
		doc.append("confirmNextRound", confirmNextRound);
		doc.append("ranking", ranking);
		doc.append("endGameRewards", endGameRewards);
		doc.append("lastPlayed", lastPlayed);
		
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
		confirmNextRound = doc.getBoolean("confirmNextRound", false);
		coin1s = (List<Integer>)doc.get("coin1s");
		coin3s = (List<Integer>)doc.get("coin3s");
		scores = (List<Integer>) doc.get("scores");
		endGameRewards = (List<String>)doc.get("endGameRewards");
		List<Document> doh = (List<Document>) doc.get("hand");
		List<Document> dop = (List<Document>) doc.get("play");
		ranking = doc.getInteger("ranking", 0);
		lastPlayed = doc.getInteger("lastPlayed", 0);
		
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
		entity.setCoin1s(coin1s);
		entity.setCoin3s(coin3s);
		entity.setPlay(toPlayEntityList());
		entity.setAntiMonopoly(toAntiMonopolyEntityList());
		entity.setRanking(ranking);
		entity.setLastPlayed(lastPlayed);
		
		if (startups.getStatus() == Consts.ROUNDEND) {
			entity.setScoreDisplay(this.getTotalScore(false));
			List<Integer> tss = new ArrayList<>();
			for (int i=0;i<scores.size()-1;i++) {
				tss.add(scores.get(i));
			}
			entity.setScores(tss);
		} else {
			entity.setScoreDisplay(this.getTotalScore());
			entity.setScores(scores);
		}
		Account account = new Account();
		account.getFromDB(name);
		entity.setAccount(account.toAccountEntity());
		return entity;
	}
	
	public List<CardEntity> toPlayEntityList(){
		int i,j;
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
			
			int fromHand = 0;
			if (startups.getStatus() == Consts.ROUNDEND) {
				for (j=0;j<hand.size();j++) {
					if (hand.get(j).getNum() == i) fromHand++;
				}
			}
			c.setFromHand(fromHand);
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
		coin1s = new ArrayList<>();
		coin3s = new ArrayList<>();
		scores = new ArrayList<>();
		endGameRewards = new ArrayList<>();
	}
	
	public Player(String name) {
		this();
		this.name = name;
		this.coins = 0;
	}
	
	public void startRound() {
		hand = new ArrayList<>();
		play = new ArrayList<>();
		takeCard(3);
		coins = 10;
		coin1s = new ArrayList<>();
		coin3s = new ArrayList<>();
		
		
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
			lastPlayed = c.getNum();
			play.add(c);
			startups.getLogger().logPlay(this, c, index);
			
			boolean f = startups.potentialChangeAntiMonopoly(this, c);
			
			if (startups.getStatus() == Consts.INGAME) {
				phase = Consts.OFFTURN;
				
				if (startups.roundEnd()) {
					startups.endRound();
				} else {
					startups.nextPlayer();
				}
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
					
					
					if (startups.roundEnd()) {
						startups.endRound();
					} else {
						startups.nextPlayer();
					}
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
	
	public void subtractCoin1RoundEnd(int x) {
		int coin1RoundEnd = getCoin1RoundEnd() - x;
		coin1s.add(coin1RoundEnd);
	}
	
	public void addCoin3RoundEnd(int x) {
		int coin3RoundEnd = getCoin3RoundEnd() + x;
		coin3s.add(coin3RoundEnd);
	}
	
	public int finalCoins() {
		return getCoin1RoundEnd() + 3*getCoin3RoundEnd();
	}
	public int getCoin1RoundEnd() {
		int coin1RoundEnd = coins;
		if (coin1s.size() > 0) {
			coin1RoundEnd = coin1s.get(coin1s.size()-1);
		}
		return coin1RoundEnd;
	}
	public void setCoin1RoundEnd(int x) {
		coin1s.add(x);
	}
	public int getCoin3RoundEnd() {
		int coin3RoundEnd = 0;
		if (coin3s.size() > 0) {
			coin3RoundEnd = coin3s.get(coin3s.size()-1);
		}
		return coin3RoundEnd;
	}
	public void setCoin3RoundEnd(int x) {
		coin3s.add(x);
	}
	
	public void addScore(int x) {
		scores.add(x);
	}
	public int getLastScore() {
		int ans = 0;
		if (scores.size() > 0) {
			ans = scores.get(scores.size()-1);
		}
		return ans;
	}
	public int getTotalScore(boolean includeLast) {
		int ans = 0;
		int n = scores.size();
		if (includeLast) {
			
		} else {
			n = n-1;
		}
		for (int i=0;i<n;i++) ans = ans+scores.get(i);
		
		return ans;
	}
	public int getTotalScore() {
		return getTotalScore(true);
	}
	public int numScores(int x) {
		int ans = 0;
		for (int i=0;i<scores.size();i++) {
			if (scores.get(i) == x) ans++;
		}
		return ans;
	}
	
	public String getNameDisplay() {
		int i;
		String nameDisplay = "";
		if (name.length() < 14) {
			nameDisplay = name;
		} else {
			for (i=0;i<12;i++) {
				nameDisplay = nameDisplay + name.charAt(i);
			}
			nameDisplay = nameDisplay + "...";
		}
		return nameDisplay;
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
	public List<Integer> getCoin1s() {
		return coin1s;
	}
	public void setCoin1s(List<Integer> coin1s) {
		this.coin1s = coin1s;
	}
	public List<Integer> getCoin3s() {
		return coin3s;
	}
	public void setCoin3s(List<Integer> coin3s) {
		this.coin3s = coin3s;
	}
	public boolean isConfirmNextRound() {
		return confirmNextRound;
	}
	public void setConfirmNextRound(boolean confirmNextRound) {
		this.confirmNextRound = confirmNextRound;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public int getRoundRanking() {
		return roundRanking;
	}
	public void setRoundRanking(int roundRanking) {
		this.roundRanking = roundRanking;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	public int getLastPlayed() {
		return lastPlayed;
	}
	public void setLastPlayed(int lastPlayed) {
		this.lastPlayed = lastPlayed;
	}
	
	
}
