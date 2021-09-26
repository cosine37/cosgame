package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.architect.entity.CardEntity;
import com.cosine.cosgame.architect.entity.PlayerEntity;

public class Player {
	List<Integer> warehouse;
	List<Card> hand;
	List<Card> play;
	List<Building> buildings;
	int phase;
	int subPhase;
	int num3vp;
	int num1vp;
	
	String name;
	Board board;
	
	public Player() {
		clearAll();
	}
	
	public void clearAll() {
		warehouse = new ArrayList<>();
		hand = new ArrayList<>();
		play = new ArrayList<>();
		buildings = new ArrayList<>();
		for (int i=0;i<4;i++) {
			warehouse.add(0);
		}
	}
	
	public void payAndBuild(Building b) {
		if (b.canBuy(this)) {
			int i,j;
			for (i=0;i<b.price.size();i++) {
				int x = b.price.get(i);
				if (x == 0) continue;
				removeRes(i,x);
			}
			buildings.add(b);
		}
	}
	
	public int warehouseSize() {
		int ans = 0;
		for (int i=0;i<warehouse.size();i++) {
			ans = ans+warehouse.get(i);
		}
		return ans;
	}
	
	public void endOrDiscard() {
		if (phase == Consts.INTURN) {
			if (warehouseSize() > Consts.MAXWAREHOUSESIZE) {
				phase = Consts.DISCARD;
			} else {
				endTurn();
			}
		}
	}
	
	public void discard(List<Integer> targets) {
		int i;
		for (i=0;i<targets.size();i++) {
			int x = targets.get(i);
			int y = warehouse.get(x)-1;
			warehouse.set(x, y);
		}
		endTurn();
	}
	
	public void rest() {
		while (play.size()>0) {
			Card c = play.remove(0);
			hand.add(c);
		}
		endOrDiscard();
	}
	
	public void playCard(int x) {
		List<Integer> targets = new ArrayList<>();
		playCard(x,targets);
	}
	
	public void playCard(int x, List<Integer> targets) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			c.play(targets);
			play.add(c);
		}
		endOrDiscard();
	}
	
	public void build(int x) {
		board.playerBuild(this, x);
		endOrDiscard();
	}
	
	public void hire(int x, List<Integer> targets) {
		board.playerHire(this, x, targets);
		endOrDiscard();
	}
	
	public void endTurn() {
		board.nextPlayer();
	}
	
	public int getScore() {
		int ans = 0;
		for (int i=0;i<buildings.size();i++) {
			ans = ans+buildings.get(i).getScore();
		}
		ans = ans+num1vp+3*num3vp;
		return ans;
	}
	public void removeRes(int res, int numRes) {
		int x = warehouse.get(res);
		if (x>=numRes) {
			x = x-numRes;
			warehouse.set(res, x);
		}
	}
	public void addRes(int res, int numRes) {
		int x = warehouse.get(res);
		x = x+numRes;
		warehouse.set(res, x);
	}
	public void addRes(int res) {
		addRes(res, 1);
	}
	public int numRes(int res) {
		return warehouse.get(res);
	}
	public void add1vp() {
		num1vp++;
	}
	public void add3vp() {
		num3vp++;
	}
	public void addHand(Card c) {
		hand.add(c);
	}
	public List<Integer> getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(List<Integer> warehouse) {
		this.warehouse = warehouse;
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
	public List<Building> getBuildings() {
		return buildings;
	}
	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getSubPhase() {
		return subPhase;
	}
	public void setSubPhase(int subPhase) {
		this.subPhase = subPhase;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getNum3vp() {
		return num3vp;
	}
	public void setNum3vp(int num3vp) {
		this.num3vp = num3vp;
	}
	public int getNum1vp() {
		return num1vp;
	}
	public void setNum1vp(int num1vp) {
		this.num1vp = num1vp;
	}
	
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		entity.setPhase(Integer.toString(phase));
		int i;
		List<String> lw = new ArrayList<>();
		for (i=0;i<warehouse.size();i++) {
			lw.add(Integer.toString(warehouse.get(i)));
		}
		entity.setWarehouse(lw);
		List<CardEntity> lh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			lh.add(hand.get(i).toCardEntity());
		}
		entity.setHand(lh);
		List<CardEntity> lp = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			lp.add(play.get(i).toCardEntity());
		}
		entity.setPlay(lp);
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("warehouse", warehouse);
		doc.append("phase", phase);
		doc.append("subPhase", subPhase);
		doc.append("num1vp", num1vp);
		doc.append("num3vp", num3vp);
		doc.append("name", name);
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
		List<Document> dob = new ArrayList<>();
		for (i=0;i<buildings.size();i++) {
			dob.add(buildings.get(i).toDocument());
		}
		doc.append("buildings", dob);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		warehouse = (List<Integer>) doc.get("warehouse");
		phase = doc.getInteger("phase", Consts.OFFTURN);
		subPhase = doc.getInteger("subPhase", Consts.OFFTURN);
		num1vp = doc.getInteger("num1vp", 0);
		num3vp = doc.getInteger("num3vp", 0);
		name = doc.getString("name");
		int i;
		List<Document> doh = (List<Document>) doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<doh.size();i++) {
			Card c = new Card();
			c.setPlayer(this);
			c.setBoard(board);
			c.setFromDoc(doh.get(i));
			hand.add(c);
		}
		List<Document> dop = (List<Document>) doc.get("play");
		play = new ArrayList<>();
		for (i=0;i<dop.size();i++) {
			Card c = new Card();
			c.setPlayer(this);
			c.setBoard(board);
			c.setFromDoc(dop.get(i));
			play.add(c);
		}
		List<Document> dob = (List<Document>) doc.get("buildings");
		buildings = new ArrayList<>();
		for (i=0;i<dob.size();i++) {
			Building b = new Building();
			b.setFromDoc(dob.get(i));
			buildings.add(b);
		}
	}
	
}
