package com.cosine.cosgame.splendor;

import java.util.ArrayList;
import java.util.List;

public class Player {
	List<List<Card>> built;
	List<Card> hand;
	List<Integer> resources;
	List<Noble> nobles;
	Board board;
	int phase;
	
	public Player() {
		built = new ArrayList<>();
		for (int i=0;i<5;i++) {
			List<Card> singleBuilt = new ArrayList<>();
			built.add(singleBuilt);
		}
		hand = new ArrayList<>();
		resources = new ArrayList<>();
		nobles = new ArrayList<>();
	}
	
	public int getScore() {
		int ans,i,j;
		ans = 0;
		for (i=0;i<built.size();i++) {
			for (j=0;j<built.get(i).size();j++) {
				ans = ans+built.get(i).get(j).getScore();
			}
		}
		return ans;
	}
	
	public boolean canBuild(Card card) {
		int i,x,y;
		x = 0;
		for (i=0;i<5;i++) {
			y = card.getCost().get(i) - built.get(i).size() - resources.get(i);
			if (y>0) x = x+y;
		}
		if (x>resources.get(SplendorConsts.ALLPURPOSE)) {
			return false;
		} else {
			return true;
		}
	}
	
	public void build(Card card) {
		int i,y,x,z;
		z=0;
		if (canBuild(card)) {
			for (i=0;i<5;i++) {
				y = card.getCost().get(i) - built.get(i).size() - resources.get(i);
				if (y>0) {
					x = resources.get(i);
					if (x<y) {
						x = 0;
						z = z+y-x;
					} else {
						x = x-y;
					}
					resources.set(i, x);
				}
			}
			x = resources.get(SplendorConsts.ALLPURPOSE);
			x = x-z;
			resources.set(SplendorConsts.ALLPURPOSE, x);
			built.get(card.benefit).add(card);
		}
	}
	
	public int numRes() {
		int ans = 0;
		for (int i=0; i<6; i++) {
			ans = ans+resources.get(i);
		}
		return ans;
	}
	
	public void putCardInHand(Card card) {
		int x;
		if (hand.size() < 3) {
			hand.add(card);
			if (board.getResources().get(SplendorConsts.ALLPURPOSE) > 0 && numRes()<10) {
				x = board.getResources().get(SplendorConsts.ALLPURPOSE)-1;
				board.getResources().set(SplendorConsts.ALLPURPOSE, x);
				x = resources.get(SplendorConsts.ALLPURPOSE)+1;
				resources.set(SplendorConsts.ALLPURPOSE, x);
			}
		}
	}
	
	public void takeCard(int x) { // draw random from deck
		if (x>=0 && x < 3) {
			Card card = board.drawOne(x);
			if (card != null) {
				putCardInHand(card);
			}
			
		}
	}
	
	public void takeSupply(int x, int y) { // take a card from supply
		if (x>=0 && x<3 && y>=0 && y<4) {
			Card card = board.takeFromSupply(x, y);
			if (card != null) {
				putCardInHand(card);
			}
		}
	}
	
	public void takeTwo(int x) {
		int y;
		if (board.getResources().get(x) >= 4 && numRes() <= 8) {
			y = board.getResources().get(x);
			y = y-2;
			board.getResources().set(x, y);
			y = resources.get(x);
			y = y+2;
			resources.set(x, y);
		}
	}
	
	public void take(List<Integer> a) {
		int y;
		if (numRes() + a.size() <= 10) {
			for (int i=0;i<a.size();i++) {
				y = board.getResources().get(a.get(i));
				if (y>0) {
					y = y-1;
					board.getResources().set(a.get(i), y);
				}
				y = resources.get(a.get(i));
				y = y+1;
				resources.set(a.get(i), y);
			}
		}
	}
	
	public void take(int x1, int x2, int x3) {
		List<Integer> a = new ArrayList<>();
		a.add(x1);
		a.add(x2);
		a.add(x3);
		take(a);
	}
	
	public void take(int x1, int x2) {
		List<Integer> a = new ArrayList<>();
		a.add(x1);
		a.add(x2);
		take(a);
	}
	
	public void take(int x1) {
		List<Integer> a = new ArrayList<>();
		a.add(x1);
		take(a);
	}
	
	public List<List<Card>> getBuilt() {
		return built;
	}
	public void setBuilt(List<List<Card>> built) {
		this.built = built;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Integer> getResources() {
		return resources;
	}
	public void setResources(List<Integer> resources) {
		this.resources = resources;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Noble> getNobles() {
		return nobles;
	}
	public void setNobles(List<Noble> nobles) {
		this.nobles = nobles;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	
}
