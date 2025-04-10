package com.cosine.cosgame.threechaodoms.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.CardFactory;
import com.cosine.cosgame.threechaodoms.Skin;
import com.cosine.cosgame.threechaodoms.SkinFactory;
import com.cosine.cosgame.threechaodoms.entity.CardEntity;
import com.cosine.cosgame.threechaodoms.entity.ShopEntity;

public class Shop {
	List<Skin> skins;
	
	public Shop() {
		
	}
	
	public CardEntity dig(Account a) {
		CardEntity entity = new CardEntity();
		if (a.getMoney() < 88) return entity;
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
		return entity;
	}
	
	public List<Transaction> entryReward() {
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, 500, "首次参与游戏");
		ts.add(t);
		return ts;
	}
	
	public List<Transaction> dailyReward() {
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, 100, "每日奖励");
		ts.add(t);
		return ts;
	}
	
	public List<Transaction> winReward(Account a){
		List<Transaction> ts = new ArrayList<>();
		if (a.getWinStrike() == 0) {
			
		} else if (a.getWinStrike() == 1) {
			Transaction t = new Transaction(Transaction.MONEY, 50, "胜利奖励");
			ts.add(t);
		} else {
			int x = 50;
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
	
	public List<Skin> getDailySkins(){
		skins = new ArrayList<>();
		
		return skins;
	}
	
	public Skin genSkin(int id) {
		Skin s = SkinFactory.makeSkin(id);
		return s;
	}
	
	public void drawSkin(Account a) {
		
	}
	
	public ShopEntity toShopEntity() {
		ShopEntity entity = new ShopEntity();
		return entity;
	}
}
