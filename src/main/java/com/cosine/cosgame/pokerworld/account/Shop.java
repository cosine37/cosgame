package com.cosine.cosgame.pokerworld.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.cosine.cosgame.pokerworld.Consts;

public class Shop {
	public static final int FIRSTREWARD = 500;
	public static final int DAILYREWARD = 100;
	public static final int WINREWARD = 50;
	public static final int DIGPRICE = 88;
	public static final int OPENCHESTPRICE = 1;
	
	List<Skin> epicSkins;
	List<Skin> rareSkins;
	List<Skin> commonSkins;
	
	List<Skin> allSkins;
	Map<Integer, Skin> skinDict;
	
	public Shop() {
		epicSkins = new ArrayList<>();
		rareSkins = new ArrayList<>();
		commonSkins = new ArrayList<>();
		allSkins = new ArrayList<>();
		skinDict = new HashMap<>();
		
		allSkins();
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
	
	public String dig(Account a) {
		if (a.getMoney() < DIGPRICE) return "";
		String rewardMsg = "";
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
		
		a.updateAccountDB();
		return rewardMsg;
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
			if (a.hasSkin(s.getId())){
				s = null;
			} else {
				break;
			}
		}
		return s;
	}
	
	
	
	
	
	public void allSkins() {
		commonSkins.add(new Skin(1001,Consts.WIZARD, Consts.COMMON,"黄金巫师"));
		commonSkins.add(new Skin(1002,Consts.WIZARD, Consts.COMMON,"炫彩巫师"));
		commonSkins.add(new Skin(1003,Consts.WIZARD, Consts.COMMON,"女巫"));
		commonSkins.add(new Skin(1004,Consts.WIZARD, Consts.COMMON,"巫婆"));
		commonSkins.add(new Skin(1005,Consts.WIZARD, Consts.COMMON,"老巫师"));
		commonSkins.add(new Skin(1006,Consts.WIZARD, Consts.COMMON,"男巫"));
		commonSkins.add(new Skin(1007,Consts.WIZARD, Consts.COMMON,"雪人"));
		commonSkins.add(new Skin(1008,Consts.WIZARD, Consts.COMMON,"灯泡"));
		commonSkins.add(new Skin(1009,Consts.WIZARD, Consts.COMMON,"鼠鼠"));
		commonSkins.add(new Skin(1010,Consts.WIZARD, Consts.COMMON,"甜筒"));
		commonSkins.add(new Skin(1011,Consts.WIZARD, Consts.COMMON,"鹿巫"));
		commonSkins.add(new Skin(1012,Consts.WIZARD, Consts.COMMON,"茶壶"));
		
		commonSkins.add(new Skin(2001,Consts.JESTER, Consts.COMMON,"男孩"));
		commonSkins.add(new Skin(2002,Consts.JESTER, Consts.COMMON,"微笑"));
		commonSkins.add(new Skin(2003,Consts.JESTER, Consts.COMMON,"大笑"));
		commonSkins.add(new Skin(2004,Consts.JESTER, Consts.COMMON,"小丑女"));
		commonSkins.add(new Skin(2005,Consts.JESTER, Consts.COMMON,"AI"));
		commonSkins.add(new Skin(2006,Consts.JESTER, Consts.COMMON,"小女孩"));
		commonSkins.add(new Skin(2007,Consts.JESTER, Consts.COMMON,"花盆"));
		commonSkins.add(new Skin(2008,Consts.JESTER, Consts.COMMON,"粑粑"));
		commonSkins.add(new Skin(2009,Consts.JESTER, Consts.COMMON,"汤锅"));
		commonSkins.add(new Skin(2010,Consts.JESTER, Consts.COMMON,"青蛙"));
		commonSkins.add(new Skin(2011,Consts.JESTER, Consts.COMMON,"蜥蜴"));
		commonSkins.add(new Skin(2012,Consts.JESTER, Consts.COMMON,"傻驴"));
		
		rareSkins.add(new Skin(1013,Consts.WIZARD, Consts.RARE,"狐狸"));
		rareSkins.add(new Skin(1014,Consts.WIZARD, Consts.RARE,"鲨鱼"));
		rareSkins.add(new Skin(1015,Consts.WIZARD, Consts.RARE,"熊猫"));
		rareSkins.add(new Skin(1016,Consts.WIZARD, Consts.RARE,"雄狮"));
		rareSkins.add(new Skin(1017,Consts.WIZARD, Consts.RARE,"乌龟"));
		rareSkins.add(new Skin(1018,Consts.WIZARD, Consts.RARE,"火龙果"));
		
		rareSkins.add(new Skin(2013,Consts.JESTER, Consts.RARE,"河马"));
		rareSkins.add(new Skin(2014,Consts.JESTER, Consts.RARE,"呆鸭"));
		rareSkins.add(new Skin(2015,Consts.JESTER, Consts.RARE,"二哈"));
		rareSkins.add(new Skin(2016,Consts.JESTER, Consts.RARE,"蠢猪"));
		rareSkins.add(new Skin(2017,Consts.JESTER, Consts.RARE,"菠萝"));
		rareSkins.add(new Skin(2018,Consts.JESTER, Consts.RARE,"壶铃"));
		
		rareSkins.add(new Skin(3001,Consts.BOMB, Consts.RARE,"气球"));
		rareSkins.add(new Skin(3002,Consts.BOMB, Consts.RARE,"刺豚"));
		rareSkins.add(new Skin(3003,Consts.BOMB, Consts.RARE,"樱桃炸弹"));
		
		rareSkins.add(new Skin(4001,Consts.DRAGON, Consts.RARE,"龙龙岩"));
		rareSkins.add(new Skin(4002,Consts.DRAGON, Consts.RARE,"椰树龙"));
		rareSkins.add(new Skin(4003,Consts.DRAGON, Consts.RARE,"苹果龙"));
		
		rareSkins.add(new Skin(5001,Consts.FAIRY, Consts.RARE,"洋葱仙"));
		rareSkins.add(new Skin(5002,Consts.FAIRY, Consts.RARE,"腊肠狗"));
		rareSkins.add(new Skin(5003,Consts.FAIRY, Consts.RARE,"小魔怪"));
		
		epicSkins.add(new Skin(1019,Consts.WIZARD, Consts.EPIC,"独角兽"));
		epicSkins.add(new Skin(1020,Consts.WIZARD, Consts.EPIC,"纸杯蛋糕"));
		
		epicSkins.add(new Skin(2019,Consts.JESTER, Consts.EPIC,"薯条"));
		epicSkins.add(new Skin(2020,Consts.JESTER, Consts.EPIC,"牛头"));
		
		epicSkins.add(new Skin(3004,Consts.BOMB, Consts.EPIC,"拳石"));
		epicSkins.add(new Skin(4004,Consts.DRAGON, Consts.EPIC,"绿毛龙"));
		epicSkins.add(new Skin(5004,Consts.FAIRY, Consts.EPIC,"仙奶"));
		
		allSkins.addAll(commonSkins);
		allSkins.addAll(rareSkins);
		allSkins.addAll(epicSkins);
		
		for (int i=0;i<allSkins.size();i++) {
			skinDict.put(allSkins.get(i).getId(), allSkins.get(i));
		}
	}

}
