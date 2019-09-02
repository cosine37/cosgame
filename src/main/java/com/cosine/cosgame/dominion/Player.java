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
	List<Card> discard, hand, deck, play, revealed, seclusion, island, nativeVillage;
	String cleanUpOptions;
	String startOptions;
	List<Ask> startAsks;
	
	int priceReduce;
	List<Integer> diceResults;
	
	public static final String[] phases = {"Start", "Action", "Treasure", "Buy", "Night", "Clean Up", "Offturn"};
	int phase;
	public static final int START = 0; // this turn deals with duration cards
	public static final int ACTION = 1;
	public static final int TREASURE = 2;
	public static final int BUY = 3;
	public static final int NIGHT = 4;
	public static final int CLEANUP = 5;
	public static final int OFFTURN = 6;
	
	int subPhase; // this is linked to Ask type
	
	int coin, action, buy;
	int numActionsPlayed;
	int coffer, villager, vp, memorial;
	boolean isBot, isGoodToGo;
	
	Ask ask;
	
	public Player() {
		this.name = "tempname";
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		play = new ArrayList<Card>();
		revealed = new ArrayList<Card>();
		seclusion = new ArrayList<Card>();
		island = new ArrayList<Card>();
		nativeVillage = new ArrayList<Card>();
		diceResults = new ArrayList<Integer>();
		isBot = false;
		isGoodToGo = false;
		cleanUpOptions = "";
		startOptions = "";
		counter = new PlayedCounter();
		sk = new ScoreKeeper(this);
		ask = new Ask();
		numActionsPlayed = 0;
		priceReduce = 0;
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
		memorial = 0;
		counter = new PlayedCounter();
		discard = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		deck = new ArrayList<Card>();
		play = new ArrayList<Card>();
		seclusion = new ArrayList<Card>();
		island = new ArrayList<Card>();
		nativeVillage = new ArrayList<Card>();
		diceResults = new ArrayList<Integer>();
		cleanUpOptions = "";
		startOptions = "";
		ask = new Ask();
		priceReduce = 0;
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
	public Board getBoard() {
		return board;
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
	
	public void putOnHand(Card card) {
		card.setPlayer(this);
		this.hand.add(card);
	}
	
	public void topDeck(String cardName) {
		for (int i=hand.size()-1;i>=0;i--) {
			if (hand.get(i).getName().equals(cardName)) {
				Card card = hand.remove(i);
				deck.add(0, card);
				break;
			}
		}
	}
	
	public void topDeck(Card card) {
		deck.add(0,card);
	}
	
	public void topDeckRevealed(List<Integer> lsi) {
		if (revealed.size() == 0) return;
		for (int i=0;i<lsi.size();i++) {
			int index = lsi.get(i);
			Card card = revealed.get(index);
			topDeck(card);
		}
		while (revealed.size()>0) {
			revealed.remove(0);
		}
	}
	
	public Ask play(Card card) {
		Ask ask = new Ask();
		return ask;
	}
	
	public void moveToPlay(String cardName) {
		Card c;
		for (int i=hand.size()-1;i>=0;i--) {
			if (hand.get(i).getName().equals(cardName)) {
				c = hand.get(i);
				hand.remove(i);
				play.add(c);
				break;
			}
		}
	}
	
	public Ask play(String cardName) {
		int i;
		Card c;
		Ask ask = new Ask();
		for (i=hand.size()-1;i>=0;i--) {
			if (hand.get(i).getName().equals(cardName)) {
				c = hand.get(i);
				hand.remove(i);
				c.setPlayer(this);
				c.setBoard(board);
				if (phase == ACTION) {
					action = action - 1;
				}
				play.add(c);
				ask = c.play();
				break;
			}
		}
		setAsk(ask);
		return ask;
	}
	
	public List<Pile> getAllCards(){
		PileGen pileGen = new PileGen();
		pileGen.add(discard);
		pileGen.add(hand);
		pileGen.add(deck);
		pileGen.add(play);
		pileGen.add(seclusion);
		pileGen.add(island);
		pileGen.add(nativeVillage);
		List<Pile> piles = pileGen.getPiles();
		return piles;
		
	}
	
	public List<Card> getAllCardsAsCards(){
		List<Card> allCards = new ArrayList<Card>();
		allCards.addAll(discard);
		allCards.addAll(hand);
		allCards.addAll(deck);
		allCards.addAll(play);
		allCards.addAll(seclusion);
		allCards.addAll(island);
		allCards.addAll(nativeVillage);
		return allCards;
	}
	
	public void shuffle() {
		Random rand = new Random();
		while (discard.size()>0) {
			int size = discard.size();
			deck.add(discard.remove(rand.nextInt(size)));
		}
		while (seclusion.size()>0) {
			Card card = seclusion.remove(0);
			discard.add(card);
		}
	}
	
	public void draw(int x) {
		int i;
		for (i=0;i<x;i++) {
			if (deck.size() == 0) {
				shuffle();
			}
			if (deck.size() == 0) {
				if (discard.size() > 0) {
					shuffle();
				}
			}
			if (deck.size() == 0) {

			} else {
				hand.add(deck.remove(0));
			}
		}
	}
	
	public void discardHand() {
		Card card;
		while (hand.size()>0) {
			card = hand.remove(0);
			card.onDiscard(this);
			discard.add(card);
		}
	}
	
	public Card discardTop() {
		if (deck.size() == 0) {
			shuffle();
		}
		if (deck.size() == 0) {
			if (discard.size() > 0) {
				shuffle();
			}
		}
		if (deck.size() == 0) {
			return null;
		}
		Card card = deck.remove(0);
		card.onDiscard(this);
		discard.add(card);
		return card;
	}
	
	public Card getTop() {
		if (deck.size() == 0) {
			shuffle();
		}
		if (deck.size() == 0) {
			if (discard.size() > 0) {
				shuffle();
			}
		}
		if (deck.size() == 0) {
			return null;
		}
		Card card = deck.get(0);
		return card;
	}
	
	public Card removeTop() {
		Card card = getTop();
		if (card != null) {
			deck.remove(0);
		}
		return card;
	}
	
	public Card discard(int index) {
		if (hand.size()>index) {
			Card card = hand.remove(index);
			card.onDiscard(this);
			discard.add(card);
			return card;
		} else {
			return null;
		}
	}
	
	public Card discard(String cardName) {
		for (int i=hand.size()-1;i>=0;i--) {
			if (hand.get(i).getName().equals(cardName)) {
				Card card = hand.remove(i);
				card.onDiscard(this);
				discard.add(card);
				return card;
			}
		} 
		return null;
	}
	
	public void cleanUp() {
		while (hand.size()>0) {
			discard.add(hand.remove(0));
		}
		int i=0;
		while (i<play.size()) {
			Card card = play.get(i);
			if (card.getNumTurns() == 0) {
				play.remove(i);
				if (card.getSpecialCare() == Card.SC_NONE) {
					discard.add(card);
				} else if (card.getSpecialCare() == Card.SC_CLEANUPTOSECLUSION) {
					seclusion.add(card);
				}
			} else { // for duration cards
				i = i+1;
			}
		}
		draw(5);
	}
	
	public void setup() {

	}
	
	public Card trash(int index) {
		if (hand.size()>index) {
			Card card = hand.remove(index);
			board.getTrash().add(card);
			card.onTrash(this);
			return card;
		} else {
			return null;
		}
	}
	
	public void trash(List<String> cards, String from) {
		int i, j;
		Card card;
		if (from.equals("hand")) {
			for (i=0;i<cards.size();i++) {
				for (j=hand.size()-1;j>=0;j--) {
					if (hand.get(j).getName().equals(cards.get(i))) {
						card = hand.remove(j);
						board.getTrash().add(card);
						card.onTrash(this);
						break;
					}
				}
			}
		} else if (from == "deck") {
			
		}
	}
	
	public void trash(String cardname, String from) {
		List<String> lsc = new ArrayList<String>();
		lsc.add(cardname);
		trash(lsc, from);
	}
	
	public void exile(List<String> cards, String from) {
		int i, j;
		Card card;
		if (from.equals("hand")) {
			for (i=0;i<cards.size();i++) {
				for (j=hand.size()-1;j>=0;j--) {
					if (hand.get(j).getName().equals(cards.get(i))) {
						card = hand.remove(j);
						seclusion.add(card);
						break;
					}
				}
			}
		} else if (from == "deck") {
			
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
	
	public boolean hasAttackBlock() {
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).isAttackBlock()) {
				return true;
			}
		}
		return false;
	}
	
	public void botAttackHandle(Ask ask) {
		
	}
	
	public boolean hasDiceModify() {
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).isDiceModify()) {
				return true;
			}
		}
		return false;
	}
	
	public Ask rollDice(int n) {
		int i;
		diceResults = new ArrayList<Integer>();
		Random rand = new Random();
		int x;
		for (i=0;i<n;i++) {
			x = rand.nextInt(6) + 1;
			diceResults.add(x);
		}
		Ask ask = new Ask();
		if (hasDiceModify()) {
			
		} else {
			
		}
		return ask;
	}
	
	
	public void nextPhase() {
		phase = (phase+1)%7;
		if (phase == START) {
			action = 1;
			buy = 1;
			coin = 0;
			numActionsPlayed = 0;
			priceReduce = 0;
			resetPlayed();
			startAsks = new ArrayList<>();
			for (int i=0;i<play.size();i++) {
				Card card = play.get(i);
				card.setPlayer(this);
				card.setBoard(board);
				Ask ask = card.duration();
				if (ask.getType() != Ask.NONE) {
					startAsks.add(ask);
				}
			}
			if (startAsks.size() == 0) {
				phase++;
			}
		}
		if (phase == ACTION) {
			if (noCardType("action") && memorial == 0) {
				phase++;
			}
		}
		if (phase == TREASURE) {
			ask.setType(Ask.NONE);
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
	
	public void dealWithAttack(String cardName) {
		ai = new AI(this, board);
		ai.dealWithAttack(cardName);
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
		int total = 0;
		int i=0;
		while (i<hand.size()) {
			if (hand.get(i).isTreasure() && hand.get(i).isAutoplay()) {
				Card c = hand.get(i);
				hand.remove(i);
				total = total + c.getCoin();
				c.setPlayer(this);
				c.play();
				play.add(c);
			} else {
				i++;
			}
		}
		board.getLogger().add(name + " gets + $"+total, 0);
	}
	
	public boolean has(String cardName, String where) {
		boolean ans = false;
		if (where.equals("hand")) {
			for (int i=0;i<hand.size();i++) {
				if (hand.get(i).getName().equals(cardName)) {
					ans = true;
					break;
				}
			}
		}
		
		return ans;
	}
	
	public boolean hasType(String type) {
		boolean ans = false;
		for (int i=0;i<hand.size();i++) {
			if (type == "Action" && hand.get(i).isActionType()) {
				ans = true;
				break;
			}
			if (type == "Treasure" && hand.get(i).isTreasure()) {
				ans = true;
				break;
			}
		}
		return ans;
	}
	
	public void revealTop(int x) {
		while (revealed.size()<x) {
			if (deck.size() == 0) {
				shuffle();
			}
			if (deck.size() == 0) {
				break;
			}
			Card card = deck.remove(0);
			revealed.add(card);
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
	public void autoSetPhase() {
		if (phase == ACTION) {
			if (ask.getType() == Ask.NONE) {
				if (memorial == 0) {
					if (action == 0 && villager == 0) {
						phase = TREASURE;
					} else if (noCardType("action")) {
						phase = TREASURE;
					} 
				}
			}
		}
		if (phase == TREASURE) {
			if (ask.getType() == Ask.NONE) {
				if (noCardType("treasure")) {
					phase = BUY;
				}
			}
		}
		if (phase == BUY) {
			if (buy == 0) {
				nextPhase();
			}
		}
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
	
	public List<Pile> getPlayAsPiles(){
		PileGen pileGen = new PileGen();
		pileGen.add(play);
		List<Pile> piles = pileGen.getPiles();
		return piles;
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
	public List<Card> getRevealed(){
		return revealed;
	}
	public List<Pile> getRevealedAsPiles(){
		PileGen pileGen = new PileGen();
		pileGen.add(revealed);
		List<Pile> piles = pileGen.getPiles();
		return piles;
	}
	public List<Card> getSeclusion(){
		return seclusion;
	}
	public List<Card> getIsland(){
		return island;
	}
	public List<Card> getNativeVillage(){
		return nativeVillage;
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
	public void setRevealed(List<Card> revealed) {
		this.revealed = revealed;
	}
	public void setSeclusion(List<Card> seclusion) {
		this.seclusion = seclusion;
	}
	public void setIsland(List<Card> island) {
		this.island = island;
	}
	public void setNativeVillage(List<Card> nativeVillage) {
		this.nativeVillage = nativeVillage;
	}
	public List<Integer> getDiceResults(){
		return diceResults;
	}
	public void setDiceResults(List<Integer> diceResults) {
		this.diceResults = diceResults;
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
	public int getPriceReduce() {
		return priceReduce;
	}
	public void setPriceReduce(int priceReduce) {
		this.priceReduce = priceReduce;
	}
	public void addPriceReduce(int x) {
		priceReduce = priceReduce + x;
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
	public int getMemorial() {
		return memorial;
	}
	public void setMemorial(int memorial) {
		this.memorial = memorial;
	}
	public void addMemorial(int x) {
		this.memorial = this.memorial + x;
	}
	public void useMemorial() {
		this.memorial = this.memorial - 1;
		board.getLogger().add(name + " uses a Memorial token to draw a card", 0);
		draw(1);
	}
	public List<Integer> getTokens(){
		List<Integer> ans = new ArrayList<>();
		ans.add(vp);
		ans.add(coffer);
		ans.add(villager);
		ans.add(memorial);
		return ans;
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
	public void setAsk(Ask ask) {
		this.ask = ask;
	}
	public Ask getAsk() {
		return ask;
	}
	public void setPlayedCounter(List<String> pc) {
		int i;
		counter = new PlayedCounter();
		for (i=0;i<pc.size();i++) {
			counter.add(pc.get(i));
		}
	}
	public void setNumActionsPlayed(int numActionsPlayed) {
		this.numActionsPlayed = numActionsPlayed;
	}
	public int getNumActionsPlayed() {
		return numActionsPlayed;
	}
	public void addNumActionsPlayed() {
		numActionsPlayed = numActionsPlayed + 1;
	}
	public List<List<Pile>> getMatsAsPiles() {
		PileGen pileGen;
		pileGen = new PileGen();
		pileGen.add(seclusion);
		List<Pile> seclusionPiles = pileGen.getPiles();
		pileGen = new PileGen();
		pileGen.add(island);
		List<Pile> islandPiles = pileGen.getPiles();
		pileGen = new PileGen();
		pileGen.add(nativeVillage);
		List<Pile> nativeVillagePiles = pileGen.getPiles();
		List<List<Pile>> ans = new ArrayList<>();
		ans.add(seclusionPiles);
		ans.add(islandPiles);
		ans.add(nativeVillagePiles);
		return ans;
	}
}
