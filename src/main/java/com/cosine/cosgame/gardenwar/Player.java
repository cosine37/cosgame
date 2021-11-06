package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.gardenwar.base.PuffShroom;
import com.cosine.cosgame.gardenwar.base.SunShroom;
import com.cosine.cosgame.gardenwar.entity.PlayerEntity;

public class Player {
	String name;
	int hp;
	int phase;
	int sun;
	int atk;
	
	List<Card> hand;
	List<Card> deck;
	List<Card> play;
	List<Card> discard;
	List<Card> equip;
	
	Board board;
	
	public Player() {
		hand = new ArrayList<>();
		deck = new ArrayList<>();
		play = new ArrayList<>();
		discard = new ArrayList<>();
		equip = new ArrayList<>();
	}
	
	public void initialize() {
		int i;
		for (i=0;i<2;i++) {
			Card puffShroom = new PuffShroom();
			discard.add(puffShroom);
		}
		for (i=0;i<8;i++) {
			Card sunShroom = new SunShroom();
			discard.add(sunShroom);
		}
		draw(5);
	}
	
	public void shuffle() {
		while (discard.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(discard.size());
			Card c = discard.remove(x);
			deck.add(c);
		}
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
	}
	
	public void startTurn() {
		sun = 0;
		atk = 0;
	}
	
	public void playCard(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			play.add(c);
			c.play();
		}
	}
	
	public void autoplay() {
		while (hand.size()>0) {
			Card c = hand.remove(0);
			play.add(c);
			c.play();
		}
	}
	
	public void addSun(int x) {
		sun = sun+x;
	}
	public void addAtk(int x) {
		atk = atk+x;
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
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		return entity;
	}
	
}
