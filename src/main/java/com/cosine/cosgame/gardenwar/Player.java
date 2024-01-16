package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.gardenwar.basic.*;
import com.cosine.cosgame.gardenwar.entity.PlayerEntity;
import com.cosine.cosgame.gardenwar.generic.DaggerAxe;

public class Player {
	String name;
	int hp;
	int phase;
	int sun;
	int atk;
	int index;
	
	List<Card> hand;
	List<Card> deck;
	List<Card> play;
	List<Card> discard;
	List<Card> equip;
	
	List<Boolean> canBuy;
	
	Board board;

	// ask
	int askType;
	int askSubType;
	List<Integer> askExtraBits;
	String askMsg;
	int askPlace;
	int askIndex;
	
	public Player() {
		hand = new ArrayList<>();
		deck = new ArrayList<>();
		play = new ArrayList<>();
		discard = new ArrayList<>();
		equip = new ArrayList<>();
		canBuy = new ArrayList<>();
		
		emptyAsk();
	}
	
	public void initialize() {
		int i;
		/*
		for (i=0;i<2;i++) {
			Card puffShroom = new PuffShroom();
			discard.add(puffShroom);
		}
		for (i=0;i<8;i++) {
			Card sunShroom = new SunShroom();
			discard.add(sunShroom);
		}
		*/
		for (i=0;i<7;i++) {
			Card coin1 = new Coin1();
			discard.add(coin1);
		}
		Card wineCup = new WineCup();
		discard.add(wineCup);
		Card rod = new Rod();
		discard.add(rod);
		Card bronzeSword = new BronzeSword();
		discard.add(bronzeSword);
		draw(5);
	}
	
	public void shuffle() {
		while (discard.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(discard.size());
			Card c = discard.remove(x);
			deck.add(c);
		}
		board.log(name + "将弃牌堆洗为摸牌堆。");
	}
	
	public void draw(int n) {
		int i;
		for (i=0;i<n;i++) {
			if (deck.size() == 0) {
				shuffle();
			}
			if (deck.size() == 0) {
				break;
			} else {
				Card c = deck.remove(0);
				hand.add(c);
			}
		}
		board.log(name + "摸了" + n + "张牌。");
	}
	
	public void trash(int x) {
		if (x>=0 && x<hand.size()) {
			if (hand.get(x).isTrashable()) {
				Card c = hand.remove(x);
				board.log(name + "消耗了 " + c.getName() + "。");
				board.getTrash().add(c);
			}
		}
	}
	
	public void startTurn() {
		sun = 0;
		atk = 0;
		canBuy = new ArrayList<>();
		int i;
		for (i=0;i<4;i++) {
			canBuy.add(true);
		}
		for (i=0;i<equip.size();i++) {
			if (equip.get(i).isHasStartTurn()) {
				board.log(name + "的 " + equip.get(i).getName() + "效果触发。");
				equip.get(i).startTurn();
			}
			equip.get(i).setActivated(true);
			if (equip.get(i).isActivatable()) {
				equip.get(i).setActivated(false);
			}
		}
	}
	
	public void buyBasic(int x) {
		Card c = board.getBasic(x);
		if (c != null) {
			if (canBuy.get(x) == true) {
				if (sun >= c.getCost()) {
					if (c.getCost() == 0) {
						canBuy.set(x, false);
					}
					sun = sun - c.getCost();
					board.log(name + "购买了 " + c.getName() + "。");
					gain(c);
				}
			}
		}
	}
	
	public void buy(int x) {
		if (x>=0 && x<board.getSupply().size()) {
			Card c = board.getSupply().get(x);
			if (sun >= c.getCost()) {
				board.popSupply(x);
				sun = sun-c.getCost();
				board.log(name + "购买了 " + c.getName() + "。");
				gain(c);
			}
		}
	}
	
	public void gain(Card c) {
		discard.add(c);
	}
	
	public void playCard(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			if (c.getType() == Consts.CARD) {
				board.log(name + "打出了" + c.getName() + "。");
				play.add(c);
				c.setPlace(Consts.PLAY);
				c.setIndex(play.size()-1);
				c.play();
			} else if (c.getType() == Consts.EQUIP) {
				board.log(name + "放置了" + c.getName() + "。");
				c.setHp(c.getShield());
				c.activated = true;
				if (c.isActivatable()) {
					c.activated = false;
				}
				equip.add(c);
			}
		}
		if (hand.size() == 0) {
			nextPhase();
		}
	}
	
	public void activateEquip(int x) {
		if (x>=0 && x<equip.size()) {
			Card c = equip.get(x);
			if (c.isActivatable()) {
				c.activate();
			}
		}
	}
	
	public void removeFromEquip(Card c) {
		int index = c.getIndex();
		if (index>=0 && index<equip.size()) {
			Card e = equip.remove(index);
			discard.add(e);
		}
	}
	
	public void setAskTargets(Card c) {
		askPlace = c.getPlace();
		askIndex = c.getIndex();
	}
	
	public void resolveCard(List<Integer> targets) {
		if (askPlace == Consts.PLAY) {
			if (askIndex>=0 && askIndex<play.size()) {
				Card card = play.get(askIndex);
				card.resolve(targets);
			}
		} else if (askPlace == Consts.EQUIP) {
			if (askIndex>=0 && askIndex<equip.size()) {
				Card card = equip.get(askIndex);
				card.resolve(targets);
			}
		}
	}

	public void autoplay() {
		while (hand.size()>0) {
			Card c = hand.remove(0);
			board.log(name + "打出了" + c.getName() + "。");
			play.add(c);
			c.play();
		}
		if (hand.size() == 0) {
			nextPhase();
		}
	}
	
	public void cleanUp() {
		while (play.size() > 0) {
			Card c = play.remove(0);
			discard.add(c);
		}
		while (hand.size() > 0) {
			Card c = hand.remove(0);
			discard.add(c);
		}
		board.log(name + "弃置了所有手牌和出牌区的牌。");
		draw(5);
	}
	
	public void nextPhase() {
		if (phase == Consts.OFFTURN) {
			startTurn();
			board.log(name + "开始了回合。");
			// TODO: Statuses
			//phase = Consts.STATUSES;
			phase = Consts.PLAY;
		} else if (phase == Consts.STATUSES) {
			phase = Consts.PLAY;
		} else if (phase == Consts.PLAY) {
			if (atk > 0) {
				phase = Consts.ATTACK;
			} else {
				phase = Consts.BUY;
			}
		} else if (phase == Consts.ATTACK) {
			phase = Consts.BUY;
		} else if (phase == Consts.BUY) {
			cleanUp();
			board.log(name + "结束了回合。");
			phase = Consts.OFFTURN;
			board.nextPlayer();
		}
	}
	
	public int numCards() {
		int ans = 0;
		ans = ans + hand.size();
		ans = ans + deck.size();
		ans = ans + discard.size();
		ans = ans + play.size();
		ans = ans + equip.size();
		return ans;
	}
	
	public Player nextPlayer() {
		int x = index+1;
		if (x >= board.getPlayers().size()) {
			x = x - board.getPlayers().size();
		}
		return board.getPlayerByIndex(x);
	}
	
	public void emptyAsk() {
		askType = Consts.NONE;
		askSubType = Consts.NONE;
		askExtraBits = new ArrayList<>();
		askMsg = "";
		askPlace = Consts.NONE;
		askIndex = -1;
	}
	public void addSun(int x) {
		sun = sun+x;
		if (x!=0) {
			board.log(name + "获得了s" + x + "。");
		}
		
	}
	public void addAtk(int x) {
		atk = atk+x;
		if (x!=0) {
			board.log(name + "获得了p" + x + "。");
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getSun() {
		return sun;
	}
	public void setSun(int sun) {
		this.sun = sun;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
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
	public List<Card> getEquip() {
		return equip;
	}
	public void setEquip(List<Card> equip) {
		this.equip = equip;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Boolean> getCanBuy() {
		return canBuy;
	}
	public void setCanBuy(List<Boolean> canBuy) {
		this.canBuy = canBuy;
	}
	public int getAskType() {
		return askType;
	}
	public void setAskType(int askType) {
		this.askType = askType;
	}
	public int getAskSubType() {
		return askSubType;
	}
	public void setAskSubType(int askSubType) {
		this.askSubType = askSubType;
	}
	public List<Integer> getAskExtraBits() {
		return askExtraBits;
	}
	public void setAskExtraBits(List<Integer> askExtraBits) {
		this.askExtraBits = askExtraBits;
	}
	public String getAskMsg() {
		return askMsg;
	}
	public void setAskMsg(String askMsg) {
		this.askMsg = askMsg;
	}
	public int getAskPlace() {
		return askPlace;
	}
	public void setAskPlace(int askPlace) {
		this.askPlace = askPlace;
	}
	public int getAskIndex() {
		return askIndex;
	}
	public void setAskIndex(int askIndex) {
		this.askIndex = askIndex;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	List<Document> toCardDocumentList(List<Card> cards){
		List<Document> docs = new ArrayList<>();
		for (int i=0;i<cards.size();i++) {
			docs.add(cards.get(i).toDocument());
		}
		return docs;
	}
	List<Card> toCardList(List<Document> docs){
		List<Card> cards = new ArrayList<>();
		for (int i=0;i<docs.size();i++) {
			Card c = CardFactory.makeCard(docs.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			cards.add(c);
		}
		return cards;
	}
	void setCardsIndexPlace() {
		int i;
		for (i=0;i<equip.size();i++) {
			Card c = equip.get(i);
			c.setPlace(Consts.EQUIP);
			c.setIndex(i);
		}
		for (i=0;i<play.size();i++) {
			Card c = play.get(i);
			c.setPlace(Consts.PLAY);
			c.setIndex(i);
		}
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("hp", hp);
		doc.append("phase", phase);
		doc.append("sun", sun);
		doc.append("atk", atk);
		doc.append("hand", toCardDocumentList(hand));
		doc.append("deck", toCardDocumentList(deck));
		doc.append("play", toCardDocumentList(play));
		doc.append("discard", toCardDocumentList(discard));
		doc.append("equip", toCardDocumentList(equip));
		doc.append("canBuy", canBuy);
		doc.append("askType", askType);
		doc.append("askSubType", askSubType);
		doc.append("askExtraBits", askExtraBits);
		doc.append("askMsg", askMsg);
		doc.append("askPlace", askPlace);
		doc.append("askIndex", askIndex);
		return doc;
	}
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		hp = doc.getInteger("hp", 0);
		phase = doc.getInteger("phase", Consts.OFFTURN);
		sun = doc.getInteger("sun", 0);
		atk = doc.getInteger("atk", 0);
		hand = toCardList((List<Document>) doc.get("hand"));
		deck = toCardList((List<Document>) doc.get("deck"));
		play = toCardList((List<Document>) doc.get("play"));
		discard = toCardList((List<Document>) doc.get("discard"));
		equip = toCardList((List<Document>) doc.get("equip"));
		canBuy = (List<Boolean>) doc.get("canBuy");
		askType = doc.getInteger("askType", Consts.NONE);
		askSubType = doc.getInteger("astSubType", Consts.NONE);
		askExtraBits = (List<Integer>) doc.get("askExtraBits");
		askMsg = doc.getString("askMsg");
		askPlace = doc.getInteger("askPlace", 0);
		askIndex = doc.getInteger("askIndex", -1);
		setCardsIndexPlace();
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		return entity;
	}
	
}
