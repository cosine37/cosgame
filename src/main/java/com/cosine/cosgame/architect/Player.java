package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;

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
	
	
	public void pay(Building b) {
		if (b.canBuy(this)) {
			int i,j;
			for (i=0;i<b.price.size();i++) {
				int x = b.price.get(i);
				if (x == 0) continue;
				removeRes(i,x);
			}
		}
	}
	
	public void rest() {
		while (play.size()>0) {
			Card c = play.remove(0);
			hand.add(c);
		}
	}
	
	public void playCard(int x, int y, List<Integer> targets) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			c.play(y, targets);
			play.add(c);
		}
	}
	
	public void playCard(int x) {
		List<Integer> targets = new ArrayList<>();
		playCard(x,1,targets);
	}
	
	public void playCard(int x, List<Integer> targets) {
		playCard(x,1,targets);
	}
	
	public void build(int x) {
		board.playerBuild(this, x);
	}
	
	public void hire(int x, List<Integer> targets) {
		board.playerHire(this, x, targets);
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
		int count = 0;
		if (numRes <= count) return;
		for (int i=warehouse.size()-1;i>=0;i--) {
			if (res == warehouse.get(i)) {
				warehouse.remove(i);
				count++;
				if (numRes == count) return;
			}
		}
	}
	public int numRes(int res) {
		int ans = 0;
		for (int i=0;i<warehouse.size();i++) {
			if (warehouse.get(i) == res) {
				ans++;
			}
		}
		return ans;
	}
	public void addRes(int res, int numRes) {
		for (int i=0;i<numRes;i++) {
			warehouse.add(res);
		}	
	}
	public void addRes(int res) {
		warehouse.add(res);
	}
	public void add1vp() {
		num1vp++;
	}
	public void add3vp() {
		num3vp++;
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
	
}
