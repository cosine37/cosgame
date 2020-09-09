package com.cosine.cosgame.nothanks;

import java.util.ArrayList;
import java.util.List;

public class Player {
	String name;
	List<Card> hand;
	int revealedMoney;
	Package pack;
	Board board;
	boolean bot;
	
	public Player() {
		hand = new ArrayList<>();
	}
	
	public void draw() {
		if (pack == null) {
			pack = board.genPack();
		}
	}
	
	public void receive() {
		revealedMoney = revealedMoney+pack.getMoney();
		hand.add(pack.getCard());
		pack = null;
		if (board.getDeck().size() == 0) {
			board.endGame();
		} else {
			draw();
		}
	}
	
	public void send(int x) {
		if (revealedMoney != 0) {
			revealedMoney--;
			pack.addMoney();
			board.sendPack(x, pack);
		} else {
			boolean flag = false;
			for (int i=0;i<hand.size();i++) {
				if (hand.get(i).getNum() == -3) {
					flag = true;
					hand.remove(i);
					pack.addMoney();
				}
			}
			if (flag) {
				board.sendPack(x, pack);
			}
		}
	}
	
	public int calcScore() {
		int i;
		int ans = 0;
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() > 0) {
				if (hasQs()) {
					ans = ans+13-hand.get(i).getNum();
				}
			} else {
				ans = ans + hand.get(i).getNum();
			}
		}
		ans = ans-revealedMoney*3;
		return ans;
	}
	
	public boolean hasQs() {
		boolean ans = false;
		int i;
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() == 0) {
				ans = true;
				break;
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
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public int getRevealedMoney() {
		return revealedMoney;
	}
	public void setRevealedMoney(int revealedMoney) {
		this.revealedMoney = revealedMoney;
	}
	public Package getPack() {
		return pack;
	}
	public void setPack(Package pack) {
		this.pack = pack;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	
}
