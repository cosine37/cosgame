package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Startups {
	String id;
	int status;
	int round;
	int curPlayer;
	int firstPlayer;
	
	List<Card> deck;
	List<Card> discard;
	List<Player> players;
	
	HashMap<Integer, Integer> antiMonopoly;
	HashMap<Integer, Integer> shareholder;
	
	Logger logger;
	
	public Startups() {
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		logger = new Logger();
		round = 0;
	}
	
	public void startRound() {
		// Step 1: add all cards
		List<Card> allCards = new ArrayList<>();
		int i,j;
		for (i=5;i<=10;i++) {
			for (j=0;j<i;j++) {
				allCards.add(new Card(i));
			}
		}
		
		// Step 2: set antiMonopoly
		antiMonopoly = new HashMap<>();
		for (i=5;i<=10;i++) {
			antiMonopoly.put(i, -1);
		}
		
		// Step 3: shuffle
		deck = new ArrayList<>();
		Random rand = new Random();
		while (allCards.size()>0) {
			int x = rand.nextInt(allCards.size());
			deck.add(allCards.remove(x));
		}
		
		// Step 4: remove first 5 cards
		for (i=0;i<Consts.REMOVENUM;i++) {
			removeTopCard();
		}
		
		// Step 5: deal cards
		for (i=0;i<players.size();i++) {
			players.get(i).setPhase(Consts.OFFTURN);
			players.get(i).emptyHandPlay();
			for (j=0;j<Consts.HANDSIZE;j++) {
				players.get(i).draw();
			}
		}
		
		// Step 6: set status, round & curPlayer
		status = Consts.INGAME;
		round++;
		curPlayer = firstPlayer;
		players.get(curPlayer).startRound();
		
		
		
	}
	
	public Card removeTopCard() {
		if (deck.size() == 0) {
			return null;
		} else {
			return deck.remove(0);
		}
	}
	
	public int drawCost(Player p) {
		int ans = 0;
		int i;
		for (i=0;i<discard.size();i++) {
			int num = discard.get(i).getNum();
			if (antiMonopoly.get(num) != p.getIndex()) {
				ans++;
			}
		}
		return ans;
	}
	
	public void placeCoins(Player p) {
		int i;
		for (i=0;i<discard.size();i++) {
			int num = discard.get(i).getNum();
			if (antiMonopoly.get(num) != p.getIndex()) {
				discard.get(i).receiveCoin();
			}
		}
	}
	
	public boolean potentialChangeAntiMonopoly(Player p, Card c) {
		int num = c.getNum();
		int ai = antiMonopoly.get(num);
		boolean f = false;
		if (ai == -1) {
			f = true;
		} else {
			Player p2 = players.get(ai);
			if (p2.numStockPlayed(num) < p.numStockPlayed(num)) {
				f = true;
			}
		}
		if (f) {
			antiMonopoly.put(num, p.getIndex());
		}
		return f;
	}
	
	public void nextPlayer() {
		if (status == Consts.INGAME) {
			curPlayer++;
			if (curPlayer == players.size()) {
				curPlayer = 0;
			}
			players.get(curPlayer).startRound();
		}
	}
	
	public void endRound() {
		// Step 1: change status
		status = Consts.ROUNDEND;
		
		// Step 2: get shareholder for each stock
		
		// Step 3: change coins
		
		// Step 4: calc score
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public HashMap<Integer, Integer> getAntiMonopoly() {
		return antiMonopoly;
	}
	public void setAntiMonopoly(HashMap<Integer, Integer> antiMonopoly) {
		this.antiMonopoly = antiMonopoly;
	}
	public HashMap<Integer, Integer> getShareholder() {
		return shareholder;
	}
	public void setShareholder(HashMap<Integer, Integer> shareholder) {
		this.shareholder = shareholder;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
}
