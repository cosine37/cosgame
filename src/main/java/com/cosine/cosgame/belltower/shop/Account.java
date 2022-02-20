package com.cosine.cosgame.belltower.shop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.belltower.entity.AccountEntity;
import com.cosine.cosgame.belltower.shop.Shop;
import com.cosine.cosgame.belltower.shop.Transaction;
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
	
	List<Integer> characters;
	List<Integer> frames;
	List<Transaction> transactions;
	
	MongoDBUtil dbutil;
	
	public Account() {
		name = "";
		money = 0;
		diamond = 0;
		key = 0;
		goldenKey = 0;
		numGames = 0;
		winStrike = 0;
		lastLoginDate = "";
		transactions = new ArrayList<>();
		characters = new ArrayList<>();
		frames = new ArrayList<>();
		
		String dbname = "admin";
		String col = "users";
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public List<Transaction> receiveWinReward(String name) {
		Shop shop = new Shop();
		numGames++;
		List<Transaction> ts = getGeneralReward();
		winStrike++;
		ts.addAll(shop.winReward(this));
		addNewTransactions(ts);
		updateAccountDB(name);
		return ts;
	}
	
	public List<Transaction> receiveLoseReward(String name) {
		Shop shop = new Shop();
		numGames++;
		List<Transaction> ts = getGeneralReward();
		winStrike=0;
		ts.addAll(shop.loseReward(this));
		addNewTransactions(ts);
		updateAccountDB(name);
		return ts;
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
	public void addCharacter(int x) {
		characters.add(x);
	}
	public boolean hasCharacter(int x) {
		int i;
		for (i=0;i<characters.size();i++) {
			if (characters.get(i) == x) {
				return true;
			}
		}
		return false;
	}
	public void cleanAccount() {
		money = 0;
		diamond = 0;
		key = 0;
		lastLoginDate = "";
		transactions = new ArrayList<>();
		characters = new ArrayList<>();
		frames = new ArrayList<>();
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
	public List<Integer> getCharacters() {
		return characters;
	}
	public void setCharacters(List<Integer> characters) {
		this.characters = characters;
	}
	public List<Integer> getFrames() {
		return frames;
	}
	public void setFrames(List<Integer> frames) {
		this.frames = frames;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public MongoDBUtil getDbutil() {
		return dbutil;
	}
	public void setDbutil(MongoDBUtil dbutil) {
		this.dbutil = dbutil;
	}
	public void getFromDB(String username) {
		Document userDoc = dbutil.read("username", username);
		if (userDoc.get("belltower") == null) {
			name = username;
			Document doc = toDocument();
			dbutil.update("username", username, "belltower", doc);
		} else {
			Document doc = (Document) userDoc.get("belltower");
			setFromDoc(doc);
		}
	}
	public void updateAccountDB(String username) {
		Document doc = toDocument();
		dbutil.update("username", username, "belltower", doc);
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
		doc.append("characters", characters);
		doc.append("frames", frames);
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
		characters = (List<Integer>) doc.get("characters");
		frames = (List<Integer>) doc.get("frames");
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
		return entity;
	}
}
