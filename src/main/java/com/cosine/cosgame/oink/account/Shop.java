package com.cosine.cosgame.oink.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Shop {
	public static final int FIRSTREWARD = 500;
	public static final int DAILYREWARD = 100;
	public static final int WINREWARD = 66;
	public static final int SECONDREWARD = 36;
	public static final int DIGPRICE = 88;
	public static final int OPENCHESTPRICE = 1;
	
	public static final int NUMCOMMONAVATAR = 10;
	
	Map<Integer, String> avatarDict;
	/*
	List<Skin> epicSkins;
	List<Skin> rareSkins;
	List<Skin> commonSkins;
	
	List<Skin> allSkins;
	Map<Integer, Skin> skinDict;
	*/
	public Shop() {
		avatarDict = new HashMap<>();
		
		allAvatars();
	}
	
	public List<Transaction> winReward(Account a){
		List<Transaction> ts = new ArrayList<>();
		int ttt = a.getWinStrike()+1;
		a.setWinStrike(ttt);
		if (a.getWinStrike() == 0) {
			
		} else if (a.getWinStrike() == 1) {
			Transaction t = new Transaction(Transaction.MONEY, WINREWARD, "胜利奖励");
			ts.add(t);
		} else {
			int x = WINREWARD;
			for (int i=0;i<a.getWinStrike();i++) {
				x = x + i*30;
			}
			Transaction t = new Transaction(Transaction.MONEY, x, a.getWinStrike() + "连胜");
			ts.add(t);
		}
		
		return ts;
	}
	
	public List<Transaction> secondReward(Account a){
		a.setWinStrike(0);
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, SECONDREWARD, "第二名奖励");
		ts.add(t);
		return ts;
	}
	
	public List<Transaction> loseReward(Account a){
		a.setWinStrike(0);
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
	
	public List<Transaction> numGameReward(Account a){
		List<Transaction> ts = new ArrayList<>();
		int x = a.getNumGames()+1;
		a.setNumGames(x);
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
	
	public String dig(Account a) {
		
		if (a.getMoney() < DIGPRICE) return "";
		String rewardMsg = "";
		/*
		int randTop = 256;
		int epicRate = 1;
		int diamond10Rate = 2;
		int diamond5Rate = 4;
		int diamond2Rate = 8;
		int keyRate = 16;
		int diamond1Rate = 32;
		int rareRate = 64;
		Random rand = new Random();
		int x = rand.nextInt(randTop);
		Transaction t1 = new Transaction(Transaction.MONEY, -88, "挖掘");
		a.addNewTransaction(t1);
		if (x<epicRate) {
			Skin s = receiveSkin(Consts.EPIC, a);
			if (s == null) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 10, "挖掘获得");
				a.addNewTransaction(t2);
				rewardMsg = "d10";
			} else {
				a.addSkin(s.getId());
				rewardMsg = "s3" + Integer.toString(s.getId()) + s.getName();
			}
		} else if (x<diamond10Rate) {
			Transaction t2 = new Transaction(Transaction.DIAMOND, 10, "挖掘获得");
			a.addNewTransaction(t2);
			rewardMsg = "d10";
		} else if (x<diamond5Rate) {
			Transaction t2 = new Transaction(Transaction.DIAMOND, 5, "挖掘获得");
			a.addNewTransaction(t2);
			rewardMsg = "d5";
		} else if (x<diamond2Rate) {
			Transaction t2 = new Transaction(Transaction.DIAMOND, 2, "挖掘获得");
			a.addNewTransaction(t2);
			rewardMsg = "d2";
		} else if (x<keyRate) {
			Transaction t2 = new Transaction(Transaction.KEY, 1, "挖掘获得");
			a.addNewTransaction(t2);
			rewardMsg = "k1";
		} else if (x<diamond1Rate) {
			Transaction t2 = new Transaction(Transaction.DIAMOND, 1, "挖掘获得");
			a.addNewTransaction(t2);
			rewardMsg = "d1";
		} else if (x<rareRate) {
			Skin s = receiveSkin(Consts.RARE, a);
			if (s == null) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 3, "挖掘获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			} else {
				a.addSkin(s.getId());
				rewardMsg = "s2" + Integer.toString(s.getId()) + s.getName();
			}
		} else {
			Skin s = receiveSkin(Consts.COMMON, a);
			if (s == null) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 1, "挖掘获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			} else {
				a.addSkin(s.getId());
				rewardMsg = "s1" + Integer.toString(s.getId()) + s.getName();
			}
		}
		*/
		a.updateAccountDB();
		return rewardMsg;
	}
	/*
	public List<String> openChest(Account a) {
		List<String> ls = new ArrayList<>();
		if (a.getMoney() < OPENCHESTPRICE) return ls;
		if (a.getKey() < 1) return ls;
		Transaction t1 = new Transaction(Transaction.MONEY, 0-OPENCHESTPRICE, "打开宝箱");
		a.addNewTransaction(t1);
		t1 = new Transaction(Transaction.KEY, -1, "打开宝箱");
		a.addNewTransaction(t1);
		int randTop = 8;
		int epicRate = 1;
		Random rand = new Random();
		int x = rand.nextInt(randTop);
		boolean receivedEpic = false;
		String rewardMsg;
		if (x<epicRate) {
			receivedEpic = true;
			Skin s = receiveSkin(Consts.EPIC, a);
			if (s == null) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 10, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d10";
			} else {
				a.addSkin(s.getId());
				rewardMsg = "s3" + Integer.toString(s.getId()) + s.getName();
			}
		} else {
			Skin s = receiveSkin(Consts.RARE, a);
			if (s == null) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 3, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d3";
			} else {
				a.addSkin(s.getId());
				rewardMsg = "s2" + Integer.toString(s.getId()) + s.getName();
			}
		}
		ls.add(rewardMsg);
		int moreDiamondsRate = 2;
		boolean receivedMoreDiamonds = false;
		x = rand.nextInt(randTop);
		if (x<moreDiamondsRate) {
			receivedMoreDiamonds = true;
		}
		if (receivedEpic) {
			if (receivedMoreDiamonds) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 2, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d2";
			} else {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 1, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			}
		} else {
			if (receivedMoreDiamonds) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 5, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d2";
			} else {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 3, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			}
		}
		ls.add(rewardMsg);
		int receivedCoins = rand.nextInt(50) + 1;
		if (receivedEpic) {
			if (receivedMoreDiamonds) {
				receivedCoins = rand.nextInt(5) + 1;
			} else {
				
			}
			Transaction t2 = new Transaction(Transaction.MONEY, receivedCoins, "宝箱获得");
			a.addNewTransaction(t2);
			rewardMsg = "m"+receivedCoins;
			ls.add(rewardMsg);
		} else {
			if (receivedMoreDiamonds) {
				
			} else {
				receivedCoins = receivedCoins + 100;
			}
			Transaction t2 = new Transaction(Transaction.MONEY, receivedCoins, "宝箱获得");
			a.addNewTransaction(t2);
			rewardMsg = "m"+receivedCoins;
			ls.add(rewardMsg);
		}
		
		a.updateAccountDB();
		return ls;
	}
	
	
	
	public Skin receiveSkin(int level, Account a) {
		a.setShop(this);
		Skin s = null;
		List<Skin> skins = new ArrayList<>();
		if (level == Consts.COMMON) {
			skins = commonSkins;
		} else if (level == Consts.RARE) {
			skins = rareSkins;
		} else if (level == Consts.EPIC) {
			skins = epicSkins;
		}
		Random rand = new Random();
		while (skins.size()>0) {
			int x = rand.nextInt(skins.size());
			s = skins.remove(x);
			if (a.hasSkin(s.getId()) || a.hasShopSkin(x)){
				s = null;
			} else {
				break;
			}
		}
		return s;
	}
	
	public String buy(Account a, int option) {
		String buyMsg = "";
		if (option == Consts.BUY1HCOINS) {
			Transaction t1 = new Transaction(Transaction.DIAMOND, -1, "购买金币支付");
			Transaction t2 = new Transaction(Transaction.MONEY, 100, "购买金币");
			a.addNewTransaction(t1);
			a.addNewTransaction(t2);
		} else if (option == Consts.BUY5HCOINS) {
			Transaction t1 = new Transaction(Transaction.DIAMOND, -5, "购买金币支付");
			Transaction t2 = new Transaction(Transaction.MONEY, 550, "购买金币");
			a.addNewTransaction(t1);
			a.addNewTransaction(t2);
		} else if (option == Consts.BUY1KCOINS) {
			Transaction t1 = new Transaction(Transaction.DIAMOND, -10, "购买金币支付");
			Transaction t2 = new Transaction(Transaction.MONEY, 1200, "购买金币");
			a.addNewTransaction(t1);
			a.addNewTransaction(t2);
		} else if (option == Consts.BUYCHEST) {
			Transaction t1 = new Transaction(Transaction.DIAMOND, -10, "购买宝箱支付");
			Transaction t2 = new Transaction(Transaction.KEY, 1, "购买宝箱");
			a.addNewTransaction(t1);
			a.addNewTransaction(t2);
		} else if (option == Consts.BUYSKIN1) {
			Transaction t1 = new Transaction(Transaction.MONEY, -233, "购买皮肤支付");
			a.addNewTransaction(t1);
			a.addSkin(a.getShopSkins().get(0));
		} else if (option == Consts.BUYSKIN2) {
			Transaction t1 = new Transaction(Transaction.DIAMOND, -3, "购买皮肤支付");
			a.addNewTransaction(t1);
			a.addSkin(a.getShopSkins().get(1));
		} else if (option == Consts.BUYSKIN3) {
			Transaction t1 = new Transaction(Transaction.DIAMOND, -17, "购买皮肤支付");
			a.addNewTransaction(t1);
			a.addSkin(a.getShopSkins().get(2));
		}
		a.updateAccountDB();
		return buyMsg;
	}
	
	public void generateShopSkins(Account a, int seed){
		int i;
		List<Integer> ans = new ArrayList<>();
		List<Integer> ts = new ArrayList<>();
		for (i=0;i<rareSkins.size();i++) {
			if (a.hasSkin(rareSkins.get(i).getId())) {
				
			} else {
				ts.add(rareSkins.get(i).getId());
			}
		}
		if (ts.size() == 0) return;
		int x = seed % ts.size();
		ans.add(ts.remove(x));
		x = seed % ts.size();
		ans.add(ts.remove(x));
		ts = new ArrayList<>();
		for (i=0;i<epicSkins.size();i++) {
			if (a.hasSkin(epicSkins.get(i).getId())) {
				
			} else {
				ts.add(epicSkins.get(i).getId());
			}
		}
		x = seed % ts.size();
		ans.add(ts.remove(x));
		a.setShopSkins(ans);
	}
	*/
	public String getAvatarName(int avatarId) {
		String ans = "";
		if (avatarDict.containsKey(avatarId)) {
			ans = avatarDict.get(avatarId);
		}
		return ans;
	}
	
	public void allAvatars() {
		avatarDict.put(1000, "企鹅");
		avatarDict.put(1001, "企鹅男");
		avatarDict.put(1002, "企鹅女");
		avatarDict.put(1003, "冲浪企鹅");
		avatarDict.put(1004, "型男");
		avatarDict.put(1005, "发夹女生");
	}

}
