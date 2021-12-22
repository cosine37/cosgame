package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.entity.CardEntity;
import com.cosine.cosgame.threechaodoms.entity.PlayerEntity;

public class Player {
	String name;
	int index;
	int phase;
	
	ID id;
	List<Card> hand;
	List<Card> play;
	List<Card> jail;
	
	Board board;
	
	public Player() {
		id = new ID();
		hand = new ArrayList<>();
		play = new ArrayList<>();
		jail = new ArrayList<>();
	}
	
	public void setupHand(int jailIndex, int exileIndex) {
		if (jailIndex > exileIndex) {
			putInJail(jailIndex);
			exile(exileIndex);
		} else {
			exile(exileIndex);
			putInJail(jailIndex);
		}
		endTurn();
	}
	
	
	public void playCard(int x, List<Integer> targets) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			c.play(targets);
			play.add(c);
			
			//TODO: May need fix here
			if (hand.size()>=4) {
				phase = Consts.DISCARD;
			} else {
				phase = Consts.RECRUIT;
			}
			
		}
	}
	
	public void exileCards(List<Integer> targets) {
		int i,j;
		for (i=0;i<targets.size();i++) {
			for (j=i+1;j<targets.size();j++) {
				if (targets.get(i)>targets.get(j)) {
					int x = targets.get(i);
					targets.set(i, targets.get(j));
					targets.set(j, x);
				}
			}
		}
		for (i=targets.size()-1;i>=1;i--) {
			exile(targets.get(i));
		}
		if (hand.size()>=4) {
			phase = Consts.DISCARD;
		} else {
			phase = Consts.RECRUIT;
		}
	}
	
	public void exile(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			board.addToExile(c);
		}
	}
	
	public void draw(int x) {
		int i;
		for (i=0;i<x;i++) {
			Card c = board.popTopDeck();
			if (c == null) return;
			hand.add(c);
		}
	}
	
	public void draw() {
		draw(1);
	}
	
	public void takeFromTavern(int x) {
		Card c = board.takeFromTavern(x);
		if (c != null) {
			hand.add(c);
		}
	}
	public void recruit(int x) {
		if (x == -1) {
			return;
		} else if (x>=0 && x<3) {
			takeFromTavern(x);
		} else {
			draw();
		}
		if (hand.size() == 4) {
			phase = Consts.DISCARD;
			board.refillTavern();
		}
	}
	public void discard(int x) {
		exile(x);
		if (hand.size() <= 3) {
			endTurn();
		}
	}
	/*
	public void takeFromTavern(List<Integer> targets) {
		for (int i=0;i<targets.size();i++) {
			takeFromTavern(targets.get(i));
		}
	}
	
	public void refillHand() {
		while (hand.size() < Consts.REFILLSIZE) {
			draw(1);
		}
	}
	*/
	public void putInJail(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			jail.add(c);
		}
	}
	
	public void putInJail(Card c) {
		jail.add(c);
	}
	
	public void endTurn() {
		phase = Consts.OFFTURN;
		Player p = nextPlayer();
		if (board.getStatus() == Consts.SETUP){
			board.setCurPlayer(p.getIndex());
			if (board.getCurPlayer() == board.getFirstPlayer()) {
				board.setStatus(Consts.INGAME);
				p.setPhase(Consts.PLAYCARD);
			} else {
				p.setPhase(Consts.MAKEHAND);
			}
			
		} else if (board.getStatus() == Consts.INGAME) {
			p.setPhase(Consts.PLAYCARD);
		}
	}
	
	public Player nextPlayer() {
		int n = board.getPlayers().size();
		int x = index+1;
		if (x>=n) x=x-n;
		
		Player p = board.getPlayers().get(x);
		return p;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public List<Card> getJail() {
		return jail;
	}
	public void setJail(List<Card> jail) {
		this.jail = jail;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("id", id.toDocument());
		doc.append("phase", phase);
		int i;
		List<Document> doh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			doh.add(hand.get(i).toDocument());
		}
		doc.append("hand", doh);
		List<Document> dop = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			dop.add(play.get(i).toDocument());
		}
		doc.append("play", dop);
		List<Document> doj = new ArrayList<>();
		for (i=0;i<jail.size();i++) {
			doj.add(jail.get(i).toDocument());
		}
		doc.append("jail", doj);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		phase = doc.getInteger("phase", Consts.OFFTURN);
		Document idDoc = (Document) doc.get("id");
		id = new ID();
		id.setFromDoc(idDoc);
		int i;
		hand = new ArrayList<>();
		List<Document> doh = (List<Document>) doc.get("hand");
		for (i=0;i<doh.size();i++) {
			Card c = CardFactory.makeCard(doh.get(i));
			c.setWhere(Consts.HAND);
			c.setBoard(board);
			c.setPlayer(this);
			hand.add(c);
		}
		play = new ArrayList<>();
		List<Document> dop = (List<Document>) doc.get("play");
		for (i=0;i<dop.size();i++) {
			Card c = CardFactory.makeCard(dop.get(i));
			c.setWhere(Consts.PLAY);
			c.setBoard(board);
			c.setPlayer(this);
			play.add(c);
		}
		jail = new ArrayList<>();
		List<Document> doj = (List<Document>) doc.get("jail");
		for (i=0;i<doj.size();i++) {
			Card c = CardFactory.makeCard(doj.get(i));
			c.setWhere(Consts.JAIL);
			c.setBoard(board);
			c.setPlayer(this);
			jail.add(c);
		}
	}
	
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		int i;
		List<CardEntity> playEntity = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			playEntity.add(play.get(i).toCardEntity());
		}
		entity.setPlay(playEntity);
		return entity;
	}
	
}
