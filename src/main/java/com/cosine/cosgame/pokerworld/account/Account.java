package com.cosine.cosgame.pokerworld.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.pokerworld.Consts;
import com.cosine.cosgame.pokerworld.entity.AccountEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Account {
	String name;
	int money;
	int diamond;
	int key;
	int goldenKey;
	int numGames;
	int winStrike;
	String lastLoginDate;
	boolean visitedPokerworld;
	
	List<Transaction> transactions;
	List<Integer> skins;
	List<Integer> chosenSkins;
	
	Shop shop;
	
	MongoDBUtil dbutil;
	
	public Account() {
		cleanAccount();
		
		String dbname = "admin";
		String col = "users";
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void cleanAccount() {
		money = 0;
		diamond = 0;
		key = 0;
		goldenKey = 0;
		winStrike = 0;
		numGames = 0;
		lastLoginDate = "";
		
		transactions = new ArrayList<>();
		skins = new ArrayList<>();
		chosenSkins = new ArrayList<>();
		for (int j=0;j<Consts.MAXCHOSENSKINS;j++) {
			chosenSkins.add(Consts.NOTCHOSEN);
		}
		visitedPokerworld = false;
	}
	
	public List<Transaction> endGameReward(int placement) {
		List<Transaction> transactions = new ArrayList<>();
		transactions.addAll(getGeneralReward());
		shop = new Shop();
		System.out.println("名次：" + placement);
		if (placement == 0) {
			transactions.addAll(shop.winReward(this));
		} else if (placement == 1) {
			transactions.addAll(shop.secondReward(this));
		} else {
			transactions.addAll(shop.loseReward(this));
		}
		
		return transactions;
	}
	
	List<Transaction> getGeneralReward() {
		Shop shop = new Shop();
		List<Transaction> ts = new ArrayList<>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH);
		int date = Calendar.getInstance().get(Calendar.DATE);
		String today = "" + year + month + date;
		if (lastLoginDate.contentEquals("")) {
			ts.addAll(shop.entryReward());
		}
		if (today.contentEquals(lastLoginDate)) {
			
		} else {
			ts.addAll(shop.dailyReward());
			lastLoginDate = today;
		}
		ts.addAll(shop.numGameReward(this));
		return ts;
	}
	
	public void addNewTransaction(Transaction t) {
		t.setAccount(this);
		t.execOnAccount();
		transactions.add(t);
	}
	public void addNewTransactions(List<Transaction> ts) {
		for (int i=0;i<ts.size();i++) {
			addNewTransaction(ts.get(i));
		}
	}
	public boolean hasSkin(int x) {
		for (int i=0;i<skins.size();i++) {
			if (skins.get(i) == x) return true;
		}
		return false;
	}
	public void addSkin(int x) {
		skins.add(x);
	}
	
	public void chooseSkin(int skinId) {
		int skinType = skinId/1000;
		int i;
		if (skinType == Consts.WIZARD) {
			for (i=0;i<4;i++) {
				if (chosenSkins.get(i) == Consts.NOTCHOSEN) {
					chosenSkins.set(i, skinId);
					break;
				}
			}
		} else if (skinType == Consts.JESTER) {
			for (i=4;i<8;i++) {
				if (chosenSkins.get(i) == Consts.NOTCHOSEN) {
					chosenSkins.set(i, skinId);
					break;
				}
			}
		} else if (skinType == Consts.BOMB) {
			if (chosenSkins.get(8) == Consts.NOTCHOSEN) {
				chosenSkins.set(8, skinId);
			}
		} else if (skinType == Consts.DRAGON) {
			if (chosenSkins.get(9) == Consts.NOTCHOSEN) {
				chosenSkins.set(9, skinId);
			}
		} else if (skinType == Consts.FAIRY) {
			if (chosenSkins.get(10) == Consts.NOTCHOSEN) {
				chosenSkins.set(10, skinId);
			}
		}
	}
	
	public void cancelChooseSkin(int skinId) {
		int i;
		for (i=0;i<Consts.MAXCHOSENSKINS;i++) {
			if (chosenSkins.get(i) == skinId) {
				chosenSkins.set(i, Consts.NOTCHOSEN);
			}
		}
	}
	
	
	public List<List<String>> allSkinImgs(){
		List<List<String>> ans = new ArrayList<>();
		int i,j;
		for (i=1;i<=5;i++) {
			List<String> ls = new ArrayList<>();
			for (j=1;j<=Consts.SKINTOTALS[i];j++) {
				int x = i*1000+j;
				if (hasSkin(x)) {
					ls.add(Integer.toString(x));
				} else {
					ls.add("qs");
				}
			}
			ans.add(ls);
		}
		return ans;
	}
	
	public List<List<String>> allSkinNames(){
		shop = new Shop();
		List<List<String>> ans = new ArrayList<>();
		int i,j;
		for (i=1;i<=5;i++) {
			List<String> ls = new ArrayList<>();
			for (j=1;j<=Consts.SKINTOTALS[i];j++) {
				int x = i*1000+j;
				if (hasSkin(x)) {
					ls.add(shop.getSkinName(x));
				} else {
					ls.add("");
				}
			}
			ans.add(ls);
		}
		return ans;
	}
	
	public List<String> allChosenSkins(){
		List<String> ans = new ArrayList<>();
		int i;
		for (i=0;i<chosenSkins.size();i++) {
			if (chosenSkins.get(i) == Consts.NOTCHOSEN) {
				ans.add("");
			} else {
				ans.add(Integer.toString(chosenSkins.get(i)));
			}
		}
		return ans;
	}
	
	
	public void changeMoney(int x) {
		money = money+x;
	}
	public void changeDiamond(int x) {
		diamond = diamond+x;
	}
	public void changeKey(int x) {
		key = key+x;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getDiamond() {
		return diamond;
	}
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public int getGoldenKey() {
		return goldenKey;
	}
	public void setGoldenKey(int goldenKey) {
		this.goldenKey = goldenKey;
	}
	public int getNumGames() {
		return numGames;
	}
	public void setNumGames(int numGames) {
		this.numGames = numGames;
	}
	public int getWinStrike() {
		return winStrike;
	}
	public void setWinStrike(int winStrike) {
		this.winStrike = winStrike;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public boolean isVisitedPokerworld() {
		return visitedPokerworld;
	}
	public void setVisitedPokerworld(boolean visitedPokerworld) {
		this.visitedPokerworld = visitedPokerworld;
	}
	public void updateAccountDB(String username) {
		Document doc = toDocument();
		dbutil.update("username", username, "pokerworld", doc);
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Integer> getSkins() {
		return skins;
	}
	public void setSkins(List<Integer> skins) {
		this.skins = skins;
	}
	public List<Integer> getChosenSkins() {
		return chosenSkins;
	}
	public void setChosenSkins(List<Integer> chosenSkins) {
		this.chosenSkins = chosenSkins;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public void getFromDB(String username) {
		Document userDoc = dbutil.read("username", username);
		if (userDoc.get("pokerworld") == null) {
			name = username;
			Document doc = toDocument();
			dbutil.update("username", username, "pokerworld", doc);
		} else {
			Document doc = (Document) userDoc.get("pokerworld");
			setFromDoc(doc);
		}
	}
	public void updateAccountDB() {
		updateAccountDB(name);
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("money", money);
		doc.append("diamond", diamond);
		doc.append("key", key);
		doc.append("lastLoginDate", lastLoginDate);
		doc.append("goldenKey", goldenKey);
		doc.append("numGames", numGames);
		doc.append("winStrike", winStrike);
		doc.append("visitedPokerworld", visitedPokerworld);
		doc.append("skins", skins);
		doc.append("chosenSkins", chosenSkins);
		int i;
		List<Document> dot = new ArrayList<>();
		for (i=0;i<transactions.size();i++) {
			dot.add(transactions.get(i).toDocument());
		}
		doc.append("transactions", dot);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		money = doc.getInteger("money", 0);
		diamond = doc.getInteger("diamond", 0);
		key = doc.getInteger("key", 0);
		lastLoginDate = doc.getString("lastLoginDate");
		goldenKey = doc.getInteger("goldenKey", 0);
		numGames = doc.getInteger("numGames", 0);
		winStrike = doc.getInteger("winStrike", 0);
		visitedPokerworld = doc.getBoolean("visitedPokerworld", false);
		skins = (List<Integer>) doc.get("skins");
		if (skins == null) skins = new ArrayList<>();
		chosenSkins = (List<Integer>) doc.get("chosenSkins");
		if (chosenSkins == null) {
			chosenSkins = new ArrayList<>();
			for (int j=0;j<Consts.MAXCHOSENSKINS;j++) {
				chosenSkins.add(Consts.NOTCHOSEN);
			}
		}
		int i;
		List<Document> dot = (List<Document>) doc.get("transactions");
		transactions = new ArrayList<>();
		for (i=0;i<dot.size();i++) {
			Transaction t = new Transaction();
			t.setAccount(this);
			t.setFromDoc(dot.get(i));
			transactions.add(t);
		}
	}
	
	public AccountEntity toAccountEntity() {
		AccountEntity entity = new AccountEntity();
		entity.setName(name);
		entity.setMoney(money);
		entity.setDiamond(diamond);
		entity.setKey(key);
		entity.setGoldenKey(goldenKey);
		entity.setAllSkinImgs(allSkinImgs());
		entity.setAllSkinNames(allSkinNames());
		entity.setChosenSkins(allChosenSkins());
		return entity;
	}
	
}
