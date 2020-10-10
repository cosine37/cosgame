package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;

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
	
}
