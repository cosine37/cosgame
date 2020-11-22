package com.cosine.cosgame.gravepsycho;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Board {
	List<Player> players;
	List<Card> deck;
	List<Card> revealed;
	List<Card> treasures;
	int status;
	int round;
	int leftover;
	AllRes allRes;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		revealed = new ArrayList<>();
		treasures = new ArrayList<>();
		allRes = new AllRes();
	}
	
	public void startGame() {
		status = 0;
		round = 0;
		leftover = 0;
		deck = allRes.getDeck();
		treasures = allRes.getTreasures();
	}
	
	public boolean disaster() {
		boolean ans = false;
		int[] a = {0,0,0,0,0};
		for (int i=0;i<revealed.size();i++) {
			if (revealed.get(i).getType() == Consts.DISASTER) {
				int x = revealed.get(i).getNum();
				a[x]++;
				if (a[x] >= 2) {
					ans = true;
					break;
				}
			}
		}
		return ans;
	}
	
	public void disasterHandle() {
		for (int i=0;i<players.size();i++) {
			Player p = players.get(i);
			if (p.isStillIn()) {
				p.setMoneyThisTurn(0);
			}
		}
		status = Consts.DISASTERROUND;
	}
	
	public void goBackHandle(List<Player> backPlayers) {
		int n = backPlayers.size();
		int x = leftover / n;
		int y = leftover % n;
		int i;
		leftover = y;
		for (i=0;i<backPlayers.size();i++) {
			backPlayers.get(i).setStillIn(false);
			backPlayers.get(i).addMoney(x);
		}
		if (backPlayers.size() == 1) {
			Player p = backPlayers.get(0);
			for (i=revealed.size()-1;i>=0;i--) {
				if (revealed.get(i).getType() == Consts.TREASURE) {
					Card c = revealed.remove(i);
					p.addMoney(c.getNum());
				}
			}
		}
	}
	
	public void newRoundHandle() {
		int i;
		if (status == Consts.DISASTERROUND) {
			int x = revealed.size()-1;
			revealed.remove(x);
		}
		while (revealed.size() > 0) {
			Card c = revealed.remove(0);
			if (c.getType() != Consts.TREASURE) {
				deck.add(c);
			}
		}
		if (treasures.size()>0) {
			Card t = treasures.remove(0);
			deck.add(t);
		}
		for (i=0;i<players.size();i++) {
			players.get(i).setStillIn(true);
			players.get(i).setDecision(Consts.UNDECIDED);
		}
		shuffle();
		status = Consts.INROUND;
		round++;
	}
	
	public void shuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int size = deck.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	public void distributeCoins(Card c) {
		if (c.getType() == Consts.TREASURE) {
			int i;
			int n=0;
			for (i=0;i<players.size();i++) {
				if (players.get(i).isStillIn()) {
					n++;
				}
			}
			if (n>0) {
				int x = c.getNum()/n;
				int y = c.getNum()%n;
				leftover = leftover+y;
				for (i=0;i<players.size();i++) {
					if (players.get(i).isStillIn()) {
						players.get(i).addMoney(x);
					}
				}
			}
		}
	}
	
	public void revealNextCard() {
		Card c = deck.remove(0);
		revealed.add(c);
		if (c.getType() == Consts.DISASTER) {
			if (disaster()) {
				disasterHandle();
			}
		} else if (c.getType() == Consts.COIN) {
			distributeCoins(c);
		}
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				players.get(i).setDecision(Consts.UNDECIDED);
			}
		}
	}
	
	public boolean allBack() {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean allDecided() {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				if (players.get(i).getDecision() == Consts.UNDECIDED) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void playerDecide(String name, int x) {
		Player p = getPlayerByName(name);
		if (p!=null) {
			p.setDecision(x);
			if (allDecided()) {
				if (status == Consts.INROUND) {
					List<Player> backPlayers = new ArrayList<>();
					for (int i=0;i<players.size();i++) {
						if (players.get(i).isStillIn()) {
							if (players.get(i).getDecision() == Consts.BACK) {
								backPlayers.add(players.get(i));
							}
						}
					}
					goBackHandle(backPlayers);
					if (allBack()) {
						status = Consts.ENDROUND;
					}
				} else if (status == Consts.ENDROUND || status == Consts.DISASTERROUND) {
					if (round >=5) {
						status = Consts.ENDGAME;
					} else {
						newRoundHandle();
					}
					
				}
			}
		}
	}
	
	public Player getPlayerByName(String name) {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getRevealed() {
		return revealed;
	}
	public void setRevealed(List<Card> revealed) {
		this.revealed = revealed;
	}
	public List<Card> getTreasures() {
		return treasures;
	}
	public void setTreasures(List<Card> treasures) {
		this.treasures = treasures;
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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("status", status);
		doc.append("round", round);
		doc.append("leftover", leftover);
		List<Document> lod = new ArrayList<>();
		List<Document> lor = new ArrayList<>();
		List<Document> lot = new ArrayList<>();
		List<String> playerNames = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playersNames", playerNames);
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).toDocument());
		}
		doc.append("deck", lod);
		for (i=0;i<revealed.size();i++) {
			lor.add(revealed.get(i).toDocument());
		}
		doc.append("revealed", lor);
		for (i=0;i<treasures.size();i++) {
			lot.add(treasures.get(i).toDocument());
		}
		doc.append("treasures", lot);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		leftover = doc.getInteger("leftover", 0);
		List<Document> lod = (List<Document>) doc.get("deck");
		List<Document> lor = (List<Document>) doc.get("revealed");
		List<Document> lot = (List<Document>) doc.get("treasures");
		int i;
		deck = new ArrayList<>();
		for (i=0;i<lod.size();i++) {
			Card c = new Card();
			c.setFromDoc(lod.get(i));
			deck.add(c);
		}
		revealed = new ArrayList<>();
		for (i=0;i<lor.size();i++) {
			Card c = new Card();
			c.setFromDoc(lor.get(i));
			revealed.add(c);
		}
		treasures = new ArrayList<>();
		for (i=0;i<lot.size();i++) {
			Card c = new Card();
			c.setFromDoc(lot.get(i));
			treasures.add(c);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setFromDoc(dop);
			players.add(p);
		}
	}
}
