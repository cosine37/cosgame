package com.cosine.cosgame.belltower.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Shop {
	public static final int FIRSTREWARD = 500;
	public static final int DAILYREWARD = 100;
	public static final int WINREWARD = 50;
	public static final int DIGPRICE = 88;
	
	public Shop() {
		
	}
	
	public String dig(Account a) {
		if (a.getMoney() < DIGPRICE) return "";
		/*
		final int totalSkin = 39;
		int i;
		List<Integer> ls = new ArrayList<>();
		for (i=1;i<=totalSkin;i++) {
			ls.add(i);
		}
		boolean flag = false;
		Skin s = new Skin();
		while (ls.size() > 0) {
			Random rand = new Random();
			int k = rand.nextInt(ls.size());
			int x = ls.remove(k);
			if (a.getSkinIndexById(x) == -1) {
				flag = true;
				s = SkinFactory.makeSkin(x);
				break;
			}
		}
		
		if (flag) {
			Transaction t = new Transaction(Transaction.MONEY, -88, "挖掘");
			a.addSkin(s);
			a.addNewTransaction(t);
			a.updateAccountDB();
			String img = s.getOriginalImg();
			Card c = CardFactory.makeCard(img);
			entity = c.toCardEntity();
			entity.setImg(s.getNewImg());
			entity.setTitle(s.getTitle());
		}
		*/
		String rewardMsg = "";
		return rewardMsg;
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
		ts.add(t);
		return ts;
	}
}
