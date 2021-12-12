package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Player {
	String name;
	int index;
	
	ID id;
	List<Card> hand;
	List<Card> play;
	List<Card> jail;
	
	Board board;
	
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
	
	public void putInJail(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			jail.add(c);
		}
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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("id", id.toDocument());
		int i;
		List<Document> doh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			doh.add(hand.get(i).toDocument());
		}
		doc.append("hand", doh);
		List<Document> dop = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			doh.add(play.get(i).toDocument());
		}
		doc.append("play", dop);
		List<Document> doj = new ArrayList<>();
		for (i=0;i<jail.size();i++) {
			doh.add(jail.get(i).toDocument());
		}
		doc.append("jail", doj);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		Document idDoc = (Document) doc.get("id");
		id = new ID();
		id.setFromDoc(idDoc);
		int i;
		hand = new ArrayList<>();
		List<Document> doh = (List<Document>) doc.get("hand");
		for (i=0;i<doh.size();i++) {
			Card c = CardFactory.makeCard(doh.get(i));
			c.setWhere(Consts.HAND);
			hand.add(c);
		}
		play = new ArrayList<>();
		List<Document> dop = (List<Document>) doc.get("play");
		for (i=0;i<dop.size();i++) {
			Card c = CardFactory.makeCard(dop.get(i));
			c.setWhere(Consts.PLAY);
			play.add(c);
		}
		jail = new ArrayList<>();
		List<Document> doj = (List<Document>) doc.get("jail");
		for (i=0;i<doj.size();i++) {
			Card c = CardFactory.makeCard(doj.get(i));
			c.setWhere(Consts.JAIL);
			play.add(c);
		}
	}
	
}
