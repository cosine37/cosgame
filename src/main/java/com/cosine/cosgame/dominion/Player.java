package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
	String name;
	Board board;
	AI ai;
	
	Trash trashMat;
	
	PlayedCounter counter;
	ScoreKeeper sk;
	List<Card> discard, hand, deck, play, revealed;
	String cleanUpOptions;
	String startOptions;
	
	public static final String[] phases = {"Start", "Action", "Treasure", "Buy", "Night", "Clean Up", "Offturn"};
	int phase;
	public static final int START = 0; // this turn deals with duration cards
	public static final int ACTION = 1;
	public static final int TREASURE = 2;
	public static final int BUY = 3;
	public static final int NIGHT = 4;
	public static final int CLEANUP = 5;
	public static final int OFFTURN = 6;
	
	int coin, action, buy;
	int coffer, villager, vp;
	boolean isBot, isGoodToGo;
	
	public Player() {
		this.name = "tempname";
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		play = new ArrayList<Card>();
		isBot = false;
		isGoodToGo = false;
		cleanUpOptions = "";
		startOptions = "";
		counter = new PlayedCounter();
		sk = new ScoreKeeper(this);
	}
	public Player(String name) {
		this();
		this.name = name;
	}
	public void cleanCards() {
		action = 1;
		buy = 1;
		coin = 0;
		vp = 0;
		coffer = 0;
		villager = 0;
		counter = new PlayedCounter();
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		play = new ArrayList<Card>();
		cleanUpOptions = "";
		startOptions = "";
	}
	public void bot() {
		isBot = true;
	}
	public boolean getIsBot() {
		return isBot;
	}
	public void setIsBot(boolean isBot) {
		this.isBot = isBot;
	}
	public void goodToGo() {
		isGoodToGo = true;
	}
	public boolean getIsGoodToGo() {
		return isGoodToGo;
	}
	public void setIsGoodToGo(boolean isGoodToGo) {
		this.isGoodToGo = isGoodToGo;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void resetValues() {
		coin = 0;
		action = 1;
		buy = 1;
	}
	
	public void putOnDiscard(Card card) {
		card.setPlayer(this);
		this.discard.add(card);
	}
	
	public Ask play(Card card) {
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask play(String cardName) {
		int i;
		Card c;
		Ask ask = new Ask();
		if (phase == ACTION) {
			if (action == 0) {
				return ask;
			}
		}
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getName().equals(cardName)) {
				c = hand.get(i);
				hand.remove(i);
				c.setPlayer(this);
				if (phase == ACTION) {
					action = action - 1;
				}
				c.play();
				play.add(c);
				break;
			}
		}
		
		return ask;
	}
	
	public List<Pile> getAllCards(){
		PileGen pileGen = new PileGen();
		pileGen.add(discard);
		pileGen.add(hand);
		pileGen.add(deck);
		pileGen.add(play);
		List<Pile> piles = pileGen.getPiles();
		return piles;
		
	}
	
	public List<Card> getAllCardsAsCards(){
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(discard);
		allCards.addAll(hand);
		allCards.addAll(deck);
		allCards.addAll(play);
		return allCards;
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
	
	public void discardHand() {
		while (hand.size()>0) {
			discard.add(hand.remove(0));
		}
	}
	
	public void cleanUp() {
		while (hand.size()>0) {
			discard.add(hand.remove(0));
		}
		while (play.size()>0) {
			discard.add(play.remove(0));
		}
		draw(5);
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
	
	public void goWithAI(Board board) {
		ai = new AI(this, board);
		ai.startPhase();
		ai.actionPhase();
		ai.treasurePhase();
		ai.buyPhase();
		ai.nightPhase();
		ai.cleanupPhase();
		//cleanUp();
	}
	
	public void nextPhase() {
		phase = (phase+1)%7;
		if (phase == START) {
			action = 1;
			buy = 1;
			coin = 0;
			if (startOptions=="") {
				phase++;
			}
		}
		if (phase == ACTION) {
			if (noCardType("action")) {
				phase++;
			}
		}
		if (phase == TREASURE) {
			if (noCardType("treasure")) {
				phase++;
			}
		}
		if (phase == BUY) {
			if (buy == 0) {
				phase++;
			}
		}
		if (phase == NIGHT) {
			if (noCardType("night")) {
				phase++;
			}
		}
		if (phase == CLEANUP) {
			if (cleanUpOptions=="") {
				phase++;
			}
		}
		if (phase == OFFTURN) {
			cleanUp();
		}
	}
	
	public boolean noCardType(String type) {
		int i;
		for (i=0;i<hand.size();i++) {
			if (type.equals("action")) {
				if (hand.get(i).isActionType()) {
					return false;
				}
			} else if (type.equals("treasure")) {
				if (hand.get(i).isTreasure()) {
					return false;
				}
			} else if (type.equals("night")) {
				if (hand.get(i).isNight()) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public void autoplayTreasure() {
		int i=0;
		while (i<hand.size()) {
			if (hand.get(i).isTreasure() && hand.get(i).isAutoplay()) {
				Card c = hand.get(i);
				hand.remove(i);
				c.setPlayer(this);
				c.play();
				play.add(c);
			} else {
				i++;
			}
		}
	}
	
	public int getScore() {
		sk = new ScoreKeeper(this);
		return sk.getScore();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<Pile> getHandAsPiles(){
		PileGen pileGen = new PileGen();
		pileGen.add(hand);
		List<Pile> piles = pileGen.getPiles();
		return piles;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
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
	public int getCoffer() {
		return coffer;
	}
	public void setCoffer(int coffer) {
		this.coffer = coffer;
	}
	public void addCoffer(int x) {
		this.coffer = this.coffer + x;
	}
	public int getVillager() {
		return villager;
	}
	public void setVillager(int villager) {
		this.villager = villager;
	}
	public void addVillager(int x) {
		this.villager = this.villager + x;
	}
	public int getVp() {
		return vp;
	}
	public void setVp(int vp) {
		this.vp = vp;
	}
	public void addVp(int x) {
		this.vp = this.vp + x;
	}
	public void addPlayed(String s) {
		counter.add(s);
	}
	public int getPlayed(String s) {
		return counter.numPlayed(s);
	}
	public void resetPlayed() {
		counter = new PlayedCounter();
	}
	public List<String> getPlayedList(){
		return counter.getPlayedList();
	}
	public void setPlayedCounter(List<String> pc) {
		int i;
		counter = new PlayedCounter();
		for (i=0;i<pc.size();i++) {
			counter.add(pc.get(i));
		}
	}
}
