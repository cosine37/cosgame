package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Player {
	String name;
	int phase;
	int resource;
	int index;
	
	Board board;
	
	List<Card> hand;
	List<Role> area;
	
	public Player() {
		hand = new ArrayList<>();
		area = new ArrayList<>();
	}
	
	public void draw() {
		Card c = board.takeTopCard();
		hand.add(c);
	}
	
	public void draw(int x) {
		List<Card> cards = board.takeTopCards(x);
		for (int i=0;i<cards.size();i++) {
			Card c = cards.get(i);
			hand.add(c);
		}
		
	}
	
	public void moveToTomb(int x) {
		if (x>=0 && x<area.size()) {
			Card c = area.remove(x).getCard();
			board.getTomb().add(c);
		}
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
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getResource() {
		return resource;
	}
	public void setResource(int resource) {
		this.resource = resource;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Role> getArea() {
		return area;
	}
	public void setArea(List<Role> area) {
		this.area = area;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("phase", phase);
		doc.append("resource", resource);
		doc.append("index", index);
		
		int i;
		List<String> hands = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			hands.add(hand.get(i).getImg());
		}
		List<Document> doa = new ArrayList<>();
		for (i=0;i<area.size();i++) {
			doa.add(area.get(i).toDocument());
		}
		doc.append("hand", hands);
		doc.append("area", doa);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		phase = doc.getInteger("phase", Consts.OFFTURN);
		resource = doc.getInteger("resource", 0);
		index = doc.getInteger("index", -1);
		
		int i;
		List<String> hands = (List<String>) doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<hands.size();i++) {
			Card c = CardFactory.createCard(hands.get(i));
			c.setBoard(board);
			c.setPlayer(this);
			hand.add(c);
		}
		List<Document> doa = (List<Document>) doc.get("area");
		area = new ArrayList<>();
		for (i=0;i<doa.size();i++) {
			Role r = new Role();
			r.setFromDoc(doa.get(i));
			r.setBoard(board);
			r.setPlayer(this);
			area.add(r);
		}
	}

	
}
