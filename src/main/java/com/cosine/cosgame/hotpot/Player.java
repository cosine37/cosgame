package com.cosine.cosgame.hotpot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
	String name;
	int index;
	int moneyThisTurn;
	int scoreThisTurn;
	int ruby;
	int score;
	int pos;
	int startingPos;
	int threshold;
	boolean drawing;
	boolean potion;
	boolean exploded;
	
	List<Chip> bag;
	List<Chip> pot;
	
	List<Integer> hash;
	
	Board board;
	
	public Player() {
		bag = new ArrayList<>();
		pot = new ArrayList<>();
		hash = new ArrayList<>();
	}
	
	public void endRound() {
		while (pot.size()>0) {
			bag.add(pot.remove(0));
		}
		int i;
		for (i=0;i<hash.size();i++) {
			hash.add(0);
		}
		moneyThisTurn = 0;
		scoreThisTurn = 0;
		exploded = false;
		drawing = true;
	}
	
	public Chip draw() {
		Chip c = null;
		if (bag.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt();
			c = bag.remove(x);
		}
		return c;
	}
	
	public void putInPot(Chip c) {
		c.prePut();
		if (exploded == false) {
			c.setPotIndex(pot.size());
			pot.add(c);
			c.afterPut();
			for (int i=0;i<pot.size()-1;i++) {
				pot.get(i).afterPutChip(c);
			}
		}
	}
	
	public void stopDrawing() {
		drawing = false;
		for (int i=0;i<pot.size();i++) {
			pot.get(i).afterDrawing();
		}
	}
	
	public void addToBag(Chip c) {
		if (moneyThisTurn>=c.getPrice()) {
			bag.add(c);
		}
	}
	
	public void addToBag(Chip c1, Chip c2) {
		if (moneyThisTurn>=c1.getPrice() + c2.getPrice()) {
			bag.add(c1);
			bag.add(c2);
		}
	}
	
	public void buy(int x) {
		if (board.getSupply().size()>x) {
			if (board.getSupply().get(x).size()>0) {
				if (moneyThisTurn>=board.getSupply().get(x).get(0).getPrice()) addToBag(board.getSupply().get(x).remove(0));
			}
		}
	}
	
	public void buy(int x1, int x2) {
		if (board.getSupply().size()>x1 && board.getSupply().size()>x2) {
			if (board.getSupply().get(x1).size()>0 && board.getSupply().get(x2).size()>0) {
				if (moneyThisTurn>=board.getSupply().get(x1).get(0).getPrice() + board.getSupply().get(x2).get(0).getPrice()) 
					addToBag(board.getSupply().get(x1).remove(0), board.getSupply().get(x2).remove(0));
			}
		}
	}
	
	public void buy(List<Integer> xs) {
		if (xs.size() == 1) {
			buy(xs.get(0));
		} else if (xs.size() == 2) {
			buy(xs.get(0),xs.get(1));
		}
	}
	
	public void addPos(int x) {
		pos = pos+x;
		if (pos>Consts.MAXPOS) pos = Consts.MAXPOS;
	}
	
	public int numChipsInPot(Chip c) {
		int ans = 0;
		int i;
		for (i=0;i<pot.size();i++) {
			if (pot.get(i).getId() == c.getId()) {
				ans++;
			}
		}
		return ans;
	}
	
	public int numChipsInPot(int x) {
		int ans = 0;
		int i;
		for (i=0;i<pot.size();i++) {
			if (pot.get(i).getId() == x) {
				ans++;
			}
		}
		return ans;
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
	public int getMoneyThisTurn() {
		return moneyThisTurn;
	}
	public void setMoneyThisTurn(int moneyThisTurn) {
		this.moneyThisTurn = moneyThisTurn;
	}
	public int getScoreThisTurn() {
		return scoreThisTurn;
	}
	public void setScoreThisTurn(int scoreThisTurn) {
		this.scoreThisTurn = scoreThisTurn;
	}
	public int getRuby() {
		return ruby;
	}
	public void setRuby(int ruby) {
		this.ruby = ruby;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public int getStartingPos() {
		return startingPos;
	}
	public void setStartingPos(int startingPos) {
		this.startingPos = startingPos;
	}
	public boolean isPotion() {
		return potion;
	}
	public void setPotion(boolean potion) {
		this.potion = potion;
	}
	public boolean isExploded() {
		return exploded;
	}
	public void setExploded(boolean exploded) {
		this.exploded = exploded;
	}
	public List<Chip> getBag() {
		return bag;
	}
	public void setBag(List<Chip> bag) {
		this.bag = bag;
	}
	public List<Chip> getPot() {
		return pot;
	}
	public void setPot(List<Chip> pot) {
		this.pot = pot;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public boolean isDrawing() {
		return drawing;
	}
	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}
	public List<Integer> getHash() {
		return hash;
	}
	public void setHash(List<Integer> hash) {
		this.hash = hash;
	}
}
