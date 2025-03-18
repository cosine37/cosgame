package com.cosine.cosgame.rich.account;

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
	List<Integer> commonAvatar;
	List<Integer> rareAvatar;
	List<Integer> epicAvatar;
	/*
	List<Skin> epicSkins;
	List<Skin> rareSkins;
	List<Skin> commonSkins;
	
	List<Skin> allSkins;
	Map<Integer, Skin> skinDict;
	*/
	public Shop() {
		avatarDict = new HashMap<>();
		commonAvatar = new ArrayList<>();
		rareAvatar = new ArrayList<>();
		epicAvatar = new ArrayList<>();
		
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
	*/
	public String buy(Account a, int option) {
		String buyMsg = "";
		
		if (option<100) {
			Transaction t1;
			if (option<5) {
				t1 = new Transaction(Transaction.MONEY, -88, "购买皮肤支付");
				a.addNewTransaction(t1);
				a.addAvatar(a.getShopAvatars().get(option));
			} else if (option<7) {
				t1 = new Transaction(Transaction.MONEY, -588, "购买皮肤支付");
				a.addNewTransaction(t1);
				a.addAvatar(a.getShopAvatars().get(option));
			} else if (option == 7) {
				t1 = new Transaction(Transaction.MONEY, -1388, "购买皮肤支付");
				a.addNewTransaction(t1);
				a.addAvatar(a.getShopAvatars().get(option));
			}
			
		}
		
		/*
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
		*/
		a.updateAccountDB();
		return buyMsg;
	}
	
	public void generateShopAvatar(Account a, int seed){
		int i,j,x;
		List<Integer> ans = new ArrayList<>();
		List<Integer> ta = new ArrayList<>();
		for (i=0;i<commonAvatar.size();i++) {
			if (a.hasAvatar(commonAvatar.get(i))) {
				
			} else {
				ta.add(commonAvatar.get(i));
			}
		}
		if (ta.size()<5) return;
		for (i=0;i<5;i++) {
			x = seed % ta.size();
			ans.add(ta.remove(x));
		}
		ta = new ArrayList<>();
		for (i=0;i<rareAvatar.size();i++) {
			if (a.hasAvatar(rareAvatar.get(i))) {
				
			} else {
				ta.add(rareAvatar.get(i));
			}
		}
		if (ta.size() == 0) return;
		x = seed % ta.size();
		ans.add(ta.remove(x));
		x = seed % ta.size();
		ans.add(ta.remove(x));
		ta = new ArrayList<>();
		for (i=0;i<epicAvatar.size();i++) {
			if (a.hasAvatar(epicAvatar.get(i))) {
				
			} else {
				ta.add(epicAvatar.get(i));
			}
		}
		x = seed % ta.size();
		ans.add(ta.remove(x));
		a.setShopAvatars(ans);
	}
	
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
		avatarDict.put(1006, "黄发男");
		avatarDict.put(1007, "风般男子");
		avatarDict.put(1008, "商务女");
		avatarDict.put(1009, "小女生");
		avatarDict.put(1010, "羞涩男");
		avatarDict.put(1011, "酷男");
		avatarDict.put(1012, "宅男");
		avatarDict.put(1013, "红发女郎");
		avatarDict.put(1014, "蓝发女郎");
		avatarDict.put(1015, "红发女生");
		avatarDict.put(1016, "小白兔");
		avatarDict.put(1017, "小只因");
		avatarDict.put(1018, "小猫");
		avatarDict.put(1019, "粉耳兔");
		avatarDict.put(1020, "青恐龙");
		avatarDict.put(1021, "调侃兔");
		avatarDict.put(1022, "小三毛");
		avatarDict.put(1023, "熟男");
		avatarDict.put(1024, "白领男");
		avatarDict.put(1025, "墨镜酷男");
		avatarDict.put(1026, "小男孩");
		avatarDict.put(1027, "眼镜呆子");
		avatarDict.put(1028, "护士");
		avatarDict.put(1029, "红发女");
		avatarDict.put(1030, "白领女");
		avatarDict.put(1031, "熊猫");
		avatarDict.put(1032, "老鸭");
		avatarDict.put(1033, "醒目兔");
		avatarDict.put(1034, "紫色大狗");
		avatarDict.put(1035, "老虎");
		avatarDict.put(1036, "大黄狗");
		avatarDict.put(1037, "大叔");
		avatarDict.put(1038, "络腮大叔");
		avatarDict.put(1039, "商务男");
		avatarDict.put(1040, "QQ男");
		avatarDict.put(1041, "大熊");
		avatarDict.put(1042, "大眼男");
		avatarDict.put(1043, "街舞装扮");
		avatarDict.put(1044, "小朋友");
		avatarDict.put(1045, "黄发女孩");
		avatarDict.put(1046, "羞涩女");
		avatarDict.put(1047, "短发女生");
		avatarDict.put(1048, "绿辫女生");
		avatarDict.put(1049, "可爱猫");
		avatarDict.put(1050, "大象");
		avatarDict.put(1051, "绿鸭");
		avatarDict.put(1052, "BB熊");
		avatarDict.put(1053, "海象");
		avatarDict.put(1054, "海狮");
		
		avatarDict.put(2000, "悟空");
		avatarDict.put(2001, "海王子");
		avatarDict.put(2002, "春丽");
		avatarDict.put(2003, "包子头");
		avatarDict.put(2004, "酷比");
		avatarDict.put(2005, "好运莲莲");
		avatarDict.put(2006, "玛卡巴卡");
		avatarDict.put(2007, "村民猫");
		avatarDict.put(2008, "村民男");
		avatarDict.put(2009, "村民女");
		avatarDict.put(2010, "村民象");
		avatarDict.put(2011, "村民鹦鹉");
		avatarDict.put(2012, "旺仔");
		avatarDict.put(2013, "花开富贵");
		avatarDict.put(2014, "杀马特红");
		avatarDict.put(2015, "杀马特绿");
		avatarDict.put(2016, "岁月静好");
		avatarDict.put(2017, "友谊干杯");
		avatarDict.put(2018, "古月哥欠");
		
		avatarDict.put(3000, "姚明");
		avatarDict.put(3001, "奥尼尔");
		avatarDict.put(3002, "库里");
		avatarDict.put(3003, "登哥");
		avatarDict.put(3004, "梅东");
		avatarDict.put(3005, "姆巴佩");
		avatarDict.put(3006, "哈兰德");
		avatarDict.put(3007, "锦鲤");
		avatarDict.put(3008, "挺懂球啊");
		
		int i;
		for (i=0;i<55;i++) {
			commonAvatar.add(1000+i);
		}
		for (i=0;i<19;i++) {
			rareAvatar.add(2000+i);
		}
		for (i=0;i<9;i++) {
			epicAvatar.add(3000+i);
		}
		
	}

}
