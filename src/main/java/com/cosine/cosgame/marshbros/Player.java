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
	
	public void startTurn() {
		phase = Consts.REC1;
	}
	
	public void endTurn() {
		phase = Consts.OFFTURN;
		Player p = nextPlayer();
		//System.out.println(p.getName());
		p.startTurn();
	}
	
	public void nextPhase() {
		if (phase == Consts.REC1) {
			if (area.size()==0) {
				phase = Consts.REC2;
			} else {
				phase = Consts.ACTION;
			}
		} else if (phase == Consts.ACTION) {
			for (int i=0;i<area.size();i++) {
				area.get(i).setChoice(Consts.NOTCHOOSED);
			}
			/*
			for (int i=0;i<area.size();i++) {
				System.out.print(area.get(i).getChoice());
				System.out.println();
			}
			*/
			phase = Consts.REC2;
		} else if (phase == Consts.REC2) {
			if (area.size()>3) {
				phase = Consts.SACRIFICE;
			} else {
				endTurn();
			}
		} else if (phase == Consts.SACRIFICE) {
			endTurn();
		}
		//System.out.println("Curplayer phase:" + phase);
	}
	
	public void draw() {
		Card c = board.takeTopCard();
		hand.add(c);
		board.getLogs().logDraw(this, 1);
	}
	
	public void draw(int x) {
		List<Card> cards = board.takeTopCards(x);
		for (int i=0;i<cards.size();i++) {
			Card c = cards.get(i);
			hand.add(c);
		}
		board.getLogs().logDraw(this, x);
	}
	
	public void appoint(int x) {
		Card c = hand.remove(x);
		Role r = new Role(c);
		area.add(r);
		board.getLogs().logAppoint(this, r);
	}
	
	public void sacrifice(int roleIndex) {
		Role r = area.get(roleIndex);
		r.setHp(0);
		board.getLogs().logSacrifice(this, r);
		board.addMoveToTombAsk(this, roleIndex);
		r.getCard().lastWish();
		board.resolveAutoAsks();
	}
	
	public void moveToTomb(int x) {
		if (x>=0 && x<area.size()) {
			Card c = area.remove(x).getCard();
			board.getTomb().add(c);
		}
	}
	
	public void addResource(int x) {
		resource = resource + x;
	}
	
	public boolean hasTaunt() {
		boolean ans = false;
		for (int i=0;i<area.size();i++) {
			if (area.get(i).getCard().isTaunt()) {
				ans = true;
				break;
			}
		}
		return ans;
	}
	
	public List<String> getAttackTargets(){
		List<String> attackTargets = new ArrayList<>();
		for (int i=0;i<area.size();i++) {
			attackTargets.add("1");
		}
		return attackTargets;
	}
	
	public boolean allActioned(int index) {
		for (int i=0;i<area.size();i++) {
			if (i==index) {
				continue;
			}
			if (area.get(i).getChoice() == Consts.NOTCHOOSED || area.get(i).getChoice() == Consts.ANOTHERATTACK) {
				return false;
			}
		}
		return true;
	}
	
	public Player nextPlayer() {
		int x = index+1;
		if (x==board.getPlayers().size()) {
			x = 0;
		}
		return board.getPlayers().get(x);
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
			r.setBoard(board);
			r.setPlayer(this);
			r.setFromDoc(doa.get(i));
			r.setIndex(i);
			area.add(r);
		}
	}

	
}
