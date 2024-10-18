package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;

public class Startups {
	int round;
	int curPlayer;
	int firstPlayer;
	
	List<Card> deck;
	List<Card> discard;
	List<Player> players;
	
	HashMap<Integer, Integer> antiMonopoly;
	HashMap<Integer, Integer> shareholder;
	
	Logger logger;
	Board board;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("round", round);
		doc.append("curPlayer", curPlayer);
		doc.append("firstPlayer", firstPlayer);
		doc.append("antiMonopoly", antiMonopoly);
		doc.append("shareholder", shareholder);
		
		int i;
		List<Document> dok = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dok.add(deck.get(i).toDocument());
		}
		List<Document> dos = new ArrayList<>();
		for (i=0;i<discard.size();i++) {
			dos.add(discard.get(i).toDocument());
		}
		doc.append("deck", dok);
		doc.append("discard", dos);
		
		for (i=0;i<players.size();i++) {
			String n = players.get(i).getName();
			n = "player-" + n;
			doc.append(n, players.get(i).toDocument());
		}
		
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		round = doc.getInteger("round", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		firstPlayer = doc.getInteger("firstPlayer", -1);
		antiMonopoly = (HashMap<Integer, Integer>) doc.get("antiMonopoly");
		shareholder = (HashMap<Integer, Integer>) doc.get("shareholder");
		
		int i;
		List<Document> dok = (List<Document>) doc.get("deck");
		List<Document> dos = (List<Document>) doc.get("discard");
		deck = new ArrayList<>();
		for (i=0;i<dok.size();i++) {
			deck.add(new Card(dok.get(i)));
		}
		discard = new ArrayList<>();
		for (i=0;i<dos.size();i++) {
			discard.add(new Card(dos.get(i)));
		}
		
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String n = playerNames.get(i);
			n = "player-" + n;
			Document dop = (Document) doc.get(n);
			Player p = new Player();
			p.setStartups(this);
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	
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
		board.setStatus(Consts.INGAME);
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
		if (board.getStatus() == Consts.INGAME) {
			curPlayer++;
			if (curPlayer == players.size()) {
				curPlayer = 0;
			}
			players.get(curPlayer).startRound();
		}
	}
	
	public void endRound() {
		// Step 1: change status
		// status = Consts.ROUNDEND;
		
		// Step 2: get shareholder for each stock
		
		// Step 3: change coins
		
		// Step 4: calc score
	}
	

	public String getId() {
		return board.getId();
	}
	public void setId(String id) {
		board.setId(id);
	}
	public int getStatus() {
		return board.getStatus();
	}
	public void setStatus(int status) {
		board.setStatus(status);
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
