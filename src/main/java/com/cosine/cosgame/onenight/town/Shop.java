package com.cosine.cosgame.onenight.town;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Shop {
	public static final int FIRSTREWARD = 500;
	public static final int DAILYREWARD = 100;
	public static final int WINREWARD = 50;
	public static final int DIGPRICE = 88;
	public static final int OPENCHESTPRICE = 1;
	
	public static final String[] commonNames = {"村民","村长","法师","少爷","弓手","伐木工","法师","高级木工","高级法师","觉醒法师",
			"觉醒矿工","觉醒木工","矿工","樵夫","金币商人","商人","商人","森林法师","流浪法师","千金",
			"勾魂眼","大嘴娃","捣蛋小妖","马加木","明耀","小照","阿芒","大臣","门卫","民兵",
			"民兵","放债人","见习骑士","农民","蜡烛商人","骗徒","海魔女","小贩","算命者","还价者",
			"占卜者","猪先生","奶牛头","猫将军","狗头军师"};
	public static final String[] rareNames = {"勇士","门徒","酒商","大领主","魔术师","教徒","驱魔人","幽灵","吸血鬼","恐惧",
			"剑客","红衣主教","拉苯博士","星月","银仁","望罗","阿米","菊伊"};
	public static final String[] epicNames = {"火夏","刚石","珠贝","斗士","贤者","蒙娜丽龙","珍珠耳环猫","教宗"};
	
	public Shop() {
		
	}
	
	public List<String> openChest(Account a) {
		List<String> ls = new ArrayList<>();
		if (a.getMoney() < OPENCHESTPRICE) return ls;
		if (a.getKey() < 1) return ls;
		Transaction t1 = new Transaction(Transaction.MONEY, -1, "打开宝箱");
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
			int y = receiveCharacter(3, a);
			if (y == -1) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 10, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d10";
			} else {
				a.addCharacter(y);
				rewardMsg = "i" + y + epicNames[y%100];
			}
		} else {
			int y = receiveCharacter(2, a);
			if (y == -1) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 3, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			} else {
				a.addCharacter(y);
				rewardMsg = "i" + y + rareNames[y%100];
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
		int receivedCoins = rand.nextInt(50);
		if (receivedEpic) {
			if (receivedMoreDiamonds) {
				
			} else {
				Transaction t2 = new Transaction(Transaction.MONEY, receivedCoins, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "m"+receivedCoins;
				ls.add(rewardMsg);
			}
		} else {
			if (receivedMoreDiamonds) {
				
			} else {
				receivedCoins = receivedCoins + 100;
				Transaction t2 = new Transaction(Transaction.MONEY, receivedCoins, "宝箱获得");
				a.addNewTransaction(t2);
				rewardMsg = "m"+receivedCoins;
				ls.add(rewardMsg);
			}
		}
		
		a.updateAccountDB();
		return ls;
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
			int y = receiveCharacter(3, a);
			if (y == -1) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 10, "挖掘获得");
				a.addNewTransaction(t2);
				rewardMsg = "d10";
			} else {
				a.addCharacter(y);
				rewardMsg = "i" + y + epicNames[y%100];
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
			int y = receiveCharacter(2, a);
			if (y == -1) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 3, "挖掘获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			} else {
				a.addCharacter(y);
				rewardMsg = "i" + y + rareNames[y%100];
			}
		} else {
			int y = receiveCharacter(1, a);
			if (y == -1) {
				Transaction t2 = new Transaction(Transaction.DIAMOND, 1, "挖掘获得");
				a.addNewTransaction(t2);
				rewardMsg = "d1";
			} else {
				a.addCharacter(y);
				rewardMsg = "i" + y + commonNames[y%100];
			}
		}
		
		a.updateAccountDB();
		return rewardMsg;
	}
	
	public int receiveCharacter(int level, Account a) {
		int ans = -1;
		int i;
		List<Integer> chars = new ArrayList<>();
		if (level == 1) {
			for (i=0;i<commonNames.length;i++) {
				chars.add(100+i);
			}
		} else if (level == 2) {
			for (i=0;i<rareNames.length;i++) {
				chars.add(200+i);
			}
		} else if (level == 3) {
			for (i=0;i<epicNames.length;i++) {
				chars.add(300+i);
			}
		}
		while (chars.size()>0) {
			int n = chars.size();
			Random rand = new Random();
			int x = rand.nextInt(n);
			int y = chars.remove(x);
			if (a.hasCharacter(y) == false) {
				ans = y;
				break;
			}
		}
		return ans;
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
	
	public List<Transaction> belltowerEvent(int amount, String msg){
		List<Transaction> ts = new ArrayList<>();
		Transaction t = new Transaction(Transaction.MONEY, amount, msg);
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
