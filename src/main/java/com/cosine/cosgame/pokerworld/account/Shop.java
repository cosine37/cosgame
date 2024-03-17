package com.cosine.cosgame.pokerworld.account;

import java.util.ArrayList;
import java.util.List;

public class Shop {
	public static final int FIRSTREWARD = 500;
	public static final int DAILYREWARD = 100;
	public static final int WINREWARD = 50;
	public static final int DIGPRICE = 88;
	public static final int OPENCHESTPRICE = 1;
	
	public Shop() {
		
	}
	
	public List<Transaction> winReward(Account a){
		List<Transaction> ts = new ArrayList<>();
		if (a.getWinStrike() == 0) {
			
		} else if (a.getWinStrike() == 1) {
			Transaction t = new Transaction(Transaction.MONEY, WINREWARD, "胜利奖励");
			ts.add(t);
		} else {
			int x = WINREWARD;
			for (int i=0;i<a.getWinStrike();i++) {
				x = x + i*10;
			}
			Transaction t = new Transaction(Transaction.MONEY, x, a.getWinStrike() + "连胜");
			ts.add(t);
		}
		return ts;
	}
	
	public List<Transaction> loseReward(Account a){
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, 10, "参与奖励");
		ts.add(t);
		return ts;
	}
	
	public List<Transaction> entryReward() {
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, FIRSTREWARD, "首次参与游戏");
		ts.add(t);
		return ts;
	}
	
	public List<Transaction> dailyReward() {
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, DAILYREWARD, "每日奖励");
		//Transaction t = new Transaction(Transaction.KEY, 1, "每日奖励");
		ts.add(t);
		return ts;
	}
	
	
	public List<Transaction> numGameReward(Account a){
		List<Transaction> ts = new ArrayList<>();
		int x = a.getNumGames();
		if (x%10 == 0) {
			Transaction t = new Transaction(Transaction.MONEY, 300, x + "场游戏");
			ts.add(t);
		} else if (x%5 == 0) {
			Transaction t = new Transaction(Transaction.MONEY, 100, x + "场游戏");
			ts.add(t);
		} else if (x == 37) {
			Transaction t = new Transaction(Transaction.MONEY, 1000, x + "场游戏");
			ts.add(t);
		}
		return ts;
	}

}
