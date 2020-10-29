package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Player {
	List<Card> hand;
	List<Card> ancient;
	String name;
	int score;
	int hp;
	int scoreLastRound;
	int phase;
	int index;
	int minNum;
	boolean kill;
	Pm pm;
	Board board;
	
	public Player() {
		hand = new ArrayList<>();
		ancient = new ArrayList<>();
		pm = new Pm();
	}
	
	public int cardIndex(Card c) {
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).getImg().contentEquals(c.getImg())) {
				return i;
			}
		}
		return -1;
	}
	
	public void useCard(Card c) {
		c.setPlayer(this);
		int index = cardIndex(c);
		if (cardIndex(c) != -1) {
			c.cardEffect();
			Card removed = hand.remove(index);
			board.addToPlayedCards(removed);
		} else {
			c.penalty();
		}
	}
	
	public void hurt(int x) {
		hp = hp-x;
		if (hp<0) hp=0;
		//TODO: add end turn & calc score handlers
	}
	
	public void recover(int x) {
		hp = hp+x;
		if (hp>PokewhatConsts.MAXHP) {
			hp=PokewhatConsts.MAXHP;
		}
	}
	
	public int getRoundScore() {
		if (hp == 0) {
			return 0;
		} else {
			if (kill) {
				return 3;
			} else {
				return 1;
			}
		}
	}
	
	public Player prevPlayer() {
		int x = index-1;
		if (x<0) {
			x = x + board.getPlayers().size();
		}
		Player p = board.getPlayers().get(x);
		return p;
	}
	
	public Player nextPlayer() {
		int x = index+1;
		if (x == board.getPlayers().size()) {
			x = 0;
		}
		Player p = board.getPlayers().get(x);
		return p;
	}

	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getAncient() {
		return ancient;
	}
	public void setAncient(List<Card> ancient) {
		this.ancient = ancient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void addHp(int x) {
		hp = hp+x;
		if (hp>PokewhatConsts.MAXHP) hp=PokewhatConsts.MAXHP;
	}
	public int getScoreLastRound() {
		return scoreLastRound;
	}
	public void setScoreLastRound(int scoreLastRound) {
		this.scoreLastRound = scoreLastRound;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Pm getPm() {
		return pm;
	}
	public void setPm(Pm pm) {
		this.pm = pm;
	}
	public boolean isKill() {
		return kill;
	}
	public void setKill(boolean kill) {
		this.kill = kill;
	}
	public int getMinNum() {
		return minNum;
	}
	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("score", score);
		doc.append("hp", hp);
		doc.append("scoreLastRound", scoreLastRound);
		doc.append("phase", phase);
		doc.append("minNum", minNum);
		doc.append("kill", kill);
		doc.append("pm",pm.toDocument());
		int i;
		List<String> loh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			loh.add(hand.get(i).getImg());
		}
		doc.append("hand", loh);
		List<String> loa = new ArrayList<>();
		for (i=0;i<ancient.size();i++) {
			loh.add(ancient.get(i).getImg());
		}
		doc.append("ancient", loa);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		score = doc.getInteger("score", 0);
		hp = doc.getInteger("hp", 0);
		scoreLastRound = doc.getInteger("scoreLastRound", 0);
		phase = doc.getInteger("phase", 0);
		minNum = doc.getInteger("minNum", 0);
		kill = doc.getBoolean("kill", false);
		Document dopm = (Document) doc.get("pm");
		pm.setFromDoc(dopm);
		int i;
		hand = new ArrayList<>();
		List<String> loh = (List<String>) doc.get("hand");
		for (i=0;i<loh.size();i++) {
			hand.add(CardFactory.createCard(loh.get(i)));
		}
		ancient = new ArrayList<>();
		List<String> loa = (List<String>) doc.get("ancient");
		for (i=0;i<loa.size();i++) {
			ancient.add(CardFactory.createCard(loa.get(i)));
		}
	}
	
}
