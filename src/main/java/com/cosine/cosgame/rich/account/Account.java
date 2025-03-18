package com.cosine.cosgame.rich.account;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.bson.Document;

import com.cosine.cosgame.rich.Avatar;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.account.entity.AccountEntity;
import com.cosine.cosgame.rich.entity.AvatarEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Account {
	String name;
	String signature;
	String lastLoginDate;
	String shopTimestamp;
	int chosenAvatar;
	int money;
	int diamond;
	int key;
	int numGames;
	int winStrike;
	String avatars;
	List<Integer> shopAvatars;
	boolean visitedRich;
	Shop shop;
	
	List<Transaction> transactions;
	
	MongoDBUtil dbutil;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("chosenAvatar", chosenAvatar);
		doc.append("avatars", avatars);
		doc.append("shopAvatars", shopAvatars);
		doc.append("visitedRich", visitedRich);
		doc.append("signature", signature);
		doc.append("money", money);
		doc.append("diamond", diamond);
		doc.append("key", key);
		doc.append("numGames", numGames);
		doc.append("winStrike", winStrike);
		doc.append("lastLoginDate", lastLoginDate);
		doc.append("shopTimestamp", shopTimestamp);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		chosenAvatar = doc.getInteger("chosenAvatar", 1000);
		avatars = doc.getString("avatars");
		shopAvatars = (List<Integer>) doc.get("shopAvatars");
		if (shopAvatars == null) shopAvatars = new ArrayList<>();
		visitedRich = doc.getBoolean("visitedRich", false);
		signature = doc.getString("signature");
		money = doc.getInteger("money", 0);
		diamond = doc.getInteger("diamond", 0);
		key = doc.getInteger("key", 0);
		numGames = doc.getInteger("numGames", 0);
		winStrike = doc.getInteger("winStrike", 0);
		lastLoginDate = doc.getString("lastLoginDate");
		if (signature == null) signature = "";
		if (lastLoginDate == null) lastLoginDate = "";
		shopTimestamp = doc.getString("shopTimestamp");
		String tsToday = getTimestampPST();
		
		if (shopTimestamp == null || !(shopTimestamp.contentEquals(tsToday))) {
			generateShopAvatars();
		} else {
			shopAvatars = (List<Integer>) doc.get("shopAvatars");
			if (shopAvatars == null) {
				shopAvatars = new ArrayList<>();
				generateShopAvatars();
			}
		}
	}
	
	public AccountEntity toAccountEntity() {
		AccountEntity entity = new AccountEntity();
		Shop shop = new Shop();
		
		entity.setName(name);
		entity.setChosenAvatar(chosenAvatar);
		entity.setAvatars(avatars);
		entity.setVisitedRich(visitedRich);
		entity.setMoney(money);
		entity.setDiamond(diamond);
		entity.setKey(key);
		entity.setChosenAvatarEntity(Factory.genAvatar(chosenAvatar).toAvatarEntity());
		
		int i;
		List<AvatarEntity> avatarEntities = new ArrayList<>();
		for (i=0;i<Consts.MAXAVATAR;i++) {
			if (avatars.charAt(i) == '1') {
				Avatar avatar = Factory.genAvatar(i);
				avatarEntities.add(avatar.toAvatarEntity());
			} else {
				Avatar avatar = Factory.genAvatar(-1);
				avatarEntities.add(avatar.toAvatarEntity());
			}
			
			
		}
		entity.setAvatarEntities(avatarEntities);
		
		entity.setShopAvatars(shopAvatars);
		List<Map<String, String>> shopAvatarStyles = new ArrayList<>();
		List<String> shopAvatarNames = new ArrayList<>();
		List<Integer> canBuyAvatar = new ArrayList<>();
		/*
		for (i=0;i<shopAvatars.size();i++) {
			avatarStyle = new HashMap<>();
			backgroundImage = "url(/image/Oink/Avatar/" + Integer.toString(shopAvatars.get(i)) + ".png)";
			avatarStyle.put("background-image", backgroundImage);
			avatarStyle.put("background-size", "cover");
			shopAvatarStyles.add(avatarStyle);
			shopAvatarNames.add(shop.getAvatarName(shopAvatars.get(i)));
			if (hasAvatar(shopAvatars.get(i))) {
				canBuyAvatar.add(0);
			} else {
				canBuyAvatar.add(1);
			}
		}
		*/
		entity.setShopAvatarStyles(shopAvatarStyles);
		entity.setShopAvatarNames(shopAvatarNames);
		entity.setCanBuyAvatar(canBuyAvatar);
		
		entity.setSignature(signature);
		String signatureDisplay = "";
		if (signature.length() < 16) {
			signatureDisplay = signature;
		} else {
			for (i=0;i<14;i++) {
				signatureDisplay = signatureDisplay + signature.charAt(i);
			}
			signatureDisplay = signatureDisplay + "...";
		}
		entity.setSignatureDisplay(signatureDisplay);
		String nameDisplay = "";
		if (name.length() < 14) {
			nameDisplay = name;
		} else {
			for (i=0;i<12;i++) {
				nameDisplay = nameDisplay + name.charAt(i);
			}
			nameDisplay = nameDisplay + "...";
		}
		entity.setNameDisplay(nameDisplay);
		
		return entity;
	}
	
	public Account() {
		cleanAccount();
		
		String dbname = "admin";
		String col = "users";
		
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	
	public void cleanAccount() {
		transactions = new ArrayList<>();
		avatars = "";
		shopAvatars = new ArrayList<>();
		int i;
		for (i=0;i<8;i++) {
			avatars = avatars+'1';
		}
		for (i=8;i<999;i++) {
			avatars = avatars+'0';
		}
		chosenAvatar = 0;
		
		signature = "";
		lastLoginDate = "";
		visitedRich = false;
	}
	
	public List<Transaction> endGameReward(int placement) {
		List<Transaction> transactions = new ArrayList<>();
		transactions.addAll(getGeneralReward());
		shop = new Shop();
		if (placement == 1) {
			transactions.addAll(shop.winReward(this));
		} else if (placement == 2) {
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
	
	public String getTimestampPST() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setTimeZone(TimeZone.getTimeZone("PST"));
		
		Calendar cal = Calendar.getInstance();
		String timestamp = formatter.format(cal.getTime());
		return timestamp;
	}
	
	public void generateShopAvatars(){
		String timestamp = getTimestampPST();
		
		String[] arrOfTS = timestamp.split("-");
		int y = Integer.parseInt(arrOfTS[0]);
		int m = Integer.parseInt(arrOfTS[1]);
		int d = Integer.parseInt(arrOfTS[2]);
		
		int seed = y*3137+m*4657+d*7919;
		for (int i=0;i<name.length();i++) {
			int x = Math.abs(name.charAt(i));
			seed = seed + x*7717;
		}
		
		Shop shop = new Shop();
		shop.generateShopAvatar(this, seed);
		shopTimestamp = timestamp;
		updateAccountDB();
	}
	
	public boolean hasAvatar(int x) {
		if (x>=0 && x<avatars.length()) {
			if (avatars.charAt(x) == '1') return true;
		}
		return false;
	}
	
	public void addAvatar(int x) {
		if (x>=0 && x<avatars.length()) {
			avatars = avatars.substring(0,x)+'1'+avatars.substring(x+1);
		}
		
	}
	
	public void chooseAvatar(int x) {
		if (hasAvatar(x)) {
			chosenAvatar = x;
		}
	}
	
	public void updateSignature(String s) {
		this.signature = s;
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
	
	public void changeMoney(int x) {
		money = money+x;
	}
	public void changeDiamond(int x) {
		diamond = diamond+x;
	}
	public void changeKey(int x) {
		key = key+x;
	}
	
	public void getFromDB(String username) {
		Document userDoc = dbutil.read("username", username);
		if (userDoc.get("rich") == null) {
			name = username;
			Document doc = toDocument();
			dbutil.update("username", username, "rich", doc);
		} else {
			Document doc = (Document) userDoc.get("rich");
			setFromDoc(doc);
		}
	}
	public void updateAccountDB(String username) {
		Document doc = toDocument();
		dbutil.update("username", username, "rich", doc);
	}
	public void updateAccountDB() {
		updateAccountDB(name);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getChosenAvatar() {
		return chosenAvatar;
	}
	public void setChosenAvatar(int chosenAvatar) {
		this.chosenAvatar = chosenAvatar;
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
	public String getAvatars() {
		return avatars;
	}
	public void setAvatars(String avatars) {
		this.avatars = avatars;
	}
	public boolean isVisitedRich() {
		return visitedRich;
	}
	public void setVisitedOink(boolean visitedRich) {
		this.visitedRich = visitedRich;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
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
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Integer> getShopAvatars() {
		return shopAvatars;
	}

	public void setShopAvatars(List<Integer> shopAvatars) {
		this.shopAvatars = shopAvatars;
	}
	
	
}
