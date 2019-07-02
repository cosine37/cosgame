package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
	String name;
	
	Trash trashMat;
	
	List<Card> discard, hand, deck, play, revealed;
	
	public static final String[] phases = {"Start", "Action", "Treasure", "Buy", "Night", "Clean Up", "Offturn"};
	public static final String ACTION_PHASE = "Action";
	public static final String TREASURE_PHASE = "Treasure";
	int phase;
	
	int coin, action, buy;
	
	int coffer, villager;
	
	public Player() {
		this.name = "tempname";
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		play = new ArrayList<Card>();
	}
	public Player(String name) {
		this();
		this.name = name;
	}
	
	public void resetValues() {
		coin = 0;
		action = 1;
		buy = 1;
	}
	
	public void play(Card card) {
		
	}
	
	public void shuffle() {
		Random rand = new Random();
		while (discard.size()>0) {
			int size = discard.size();
			deck.add(discard.remove(rand.nextInt(size)));
		}
	}
	
	public void draw(int x) {
		int i;
		for (i=0;i<x;i++) {
			if (deck.size() == 0) {
				shuffle();
			}
			if (deck.size() == 0) {
				// do nothing
			} else {
				hand.add(deck.remove(0));
			}
		}
	}
	
	public void setup() {

	}
	
	public void trash(List<String> cards, String from) {
		int i, j;
		for (i=0;i<=cards.size();i++) {
			j = 0;
			while (1>0) {
				if (from == "hand") {
					if (j == hand.size()) {
						break;
					}
					if (hand.get(j).getName().equals(cards.get(i))) {
						trashMat.add(hand.remove(j));
						break;
					}
				} else if (from == "revealed") {
					
				} else if (from == "deck") {
					
				}
			}
			
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getPhase() {
		return phase;
	}
	public String getPhaseAsString() {
		return phases[phase];
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<Card> getDiscard(){
		return discard;
	}
	public List<Card> getDeck(){
		return deck;
	}
	public List<Card> getPlay(){
		return play;
	}
	public List<Card> getHand(){
		return hand;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public void addCoin(int x) {
		this.coin = this.coin + x;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public void addAction(int x) {
		this.action = this.action + x;
	}
	public int getBuy() {
		return buy;
	}
	public void setBuy(int buy) {
		this.buy = buy;
	}
	public void addBuy(int x) {
		this.buy = this.buy + x;
	}
}
