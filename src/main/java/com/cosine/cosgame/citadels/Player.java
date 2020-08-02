package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name;
	Board board;
	List<Card> hand;
	List<Card> built;
	int coin;
	boolean firstFinished;
	boolean finished;
	boolean allColors;
	boolean killed;
	int role;
	
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
		built = new ArrayList<>();
		coin = 0;
		firstFinished = false;
		finished = false;
		killed = false;
	}

	public void takeCoins(int n) {
		int c = board.takeCoins(n);
		coin = coin+c;
	}
	
	public void addToHand(Card c) {
		hand.add(c);
	}
	
	public void exchangeHand(Player p) {
		List<Card> temp = p.hand;
		p.hand = hand;
		hand = temp;
	}
	
	public void build(int x) {
		if (hand.size() > x) {
			Card c = hand.get(x);
			int cost = c.getCost();
			if (cost <= coin) {
				hand.remove(x);
				built.add(c);
				coin = coin-cost;
				if (built.size() == board.getFinishCount()) {
					finished = true;
					if (board.isFirstFinished()) {
						firstFinished = true;
					}
				}
			}
		}
	}
	
	public int missingColor() {
		int[] colors = {0,0,0,0,0};
		for (int i=0;i<built.size();i++) {
			int cc = built.get(i).getColor();
			colors[cc]++;
		}
		int ans = -2;
		for (int i=0;i<5;i++) {
			if (colors[i] == 0) {
				if (ans == -2) {
					ans = i;
				} else {
					ans = -1;
					break;
				}
			}
		}
		return ans;
	}
	
	public int calcScore() {
		int ans = 0;
		int i;
		for (i=0;i<built.size();i++) {
			ans = ans + built.get(i).getScore();
		}
		if (finished) {
			if (firstFinished) {
				ans = ans+4;
			} else {
				ans = ans+2;
			}
		}
		int[] colors = {0,0,0,0,0};
		for (i=0;i<built.size();i++) {
			int cc = built.get(i).getColorForScore();
			colors[cc]++;
		}
		allColors = true;
		for (i=0;i<5;i++) {
			if (colors[i] == 0) {
				allColors = false;
				break;
			}
		}
		if (allColors) {
			ans = ans+3;
		}
		return ans;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getBuilt() {
		return built;
	}
	public void setBuilt(List<Card> built) {
		this.built = built;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isFirstFinished() {
		return firstFinished;
	}
	public void setFirstFinished(boolean firstFinished) {
		this.firstFinished = firstFinished;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public boolean isAllColors() {
		return allColors;
	}
	public void setAllColors(boolean allColors) {
		this.allColors = allColors;
	}
	public boolean isKilled() {
		return killed;
	}
	public void setKilled(boolean killed) {
		this.killed = killed;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
}
