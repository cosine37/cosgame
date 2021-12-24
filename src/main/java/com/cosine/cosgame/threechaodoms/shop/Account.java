package com.cosine.cosgame.threechaodoms.shop;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.Skin;
import com.cosine.cosgame.threechaodoms.SkinFactory;
import com.cosine.cosgame.threechaodoms.entity.AccountEntity;
import com.cosine.cosgame.threechaodoms.entity.SkinEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Account {
	String name;
	int money;
	int ingot;
	int key;
	String lastLoginDate;
	
	List<Transaction> transactions;
	List<Skin> skins;
	
	MongoDBUtil dbutil;
	
	public Account() {
		name = "";
		money = 0;
		ingot = 0;
		key = 0;
		lastLoginDate = "";
		transactions = new ArrayList<>();
		skins = new ArrayList<>();
		
		String dbname = "admin";
		String col = "users";
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
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
	
	public int getSkinIndexById(int id) {
		for (int i=0;i<skins.size();i++) {
			if (skins.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean canAffordSkin(Skin skin) {
		if (skin.getPrice().getType() == Transaction.MONEY) {
			if (money>= skin.getPrice().getAmount()) {
				return true;
			} else {
				return false;
			}
		} else if (skin.getPrice().getType() == Transaction.INGOT) {
			if (ingot>= skin.getPrice().getAmount()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void buySkin(Skin skin) {
		if (canAffordSkin(skin)) {
			addNewTransaction(skin.getPrice());
			skins.add(skin);
		}
	}
	
	public void useSkin(int id) {
		int x = getSkinIndexById(id);
		if (x>=0 && x<skins.size()) {
			Skin s = skins.get(x);
			String originalImg = s.getOriginalImg();
			for (int i=0;i<skins.size();i++) {
				if (skins.get(i).getOriginalImg().contentEquals(originalImg)) {
					skins.get(i).setInUse(false);
				}
			}
			skins.get(x).setInUse(true);
		}
	}
	
	public Skin findSkinByImg(String img) {
		int i;
		for (i=0;i<skins.size();i++) {
			if (skins.get(i).getOriginalImg().contentEquals(img) && skins.get(i).isInUse()) {
				return skins.get(i);
			}
		}
		return null;
	}
	
	public void cleanAccount() {
		money = 0;
		ingot = 0;
		key = 0;
		lastLoginDate = "";
		transactions = new ArrayList<>();
		skins = new ArrayList<>();
	}
	
	public void changeMoney(int x) {
		money = money+x;
	}
	public void changeIngot(int x) {
		ingot = ingot+x;
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
	public int getIngot() {
		return ingot;
	}
	public void setIngot(int ingot) {
		this.ingot = ingot;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Skin> getSkins() {
		return skins;
	}
	public void setSkins(List<Skin> skins) {
		this.skins = skins;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public void getFromDB(String username) {
		Document userDoc = dbutil.read("username", username);
		if (userDoc.get("threechaodoms") == null) {
			name = username;
			Document doc = toDocument();
			dbutil.update("username", username, "threechaodoms", doc);
		} else {
			Document doc = (Document) userDoc.get("threechaodoms");
			setFromDoc(doc);
		}
	}
	
	public void updateAcountDB(String username) {
		Document doc = toDocument();
		dbutil.update("username", username, "threechaodoms", doc);
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("money", money);
		doc.append("ingot", ingot);
		doc.append("key", key);
		doc.append("lastLoginDate", lastLoginDate);
		int i;
		List<Document> dot = new ArrayList<>();
		for (i=0;i<transactions.size();i++) {
			dot.add(transactions.get(i).toDocument());
		}
		doc.append("transactions", dot);
		List<Document> ls = new ArrayList<>();
		for (i=0;i<skins.size();i++) {
			ls.add(skins.get(i).toDocument());
		}
		doc.append("skins", ls);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		money = doc.getInteger("money", 0);
		ingot = doc.getInteger("ingot", 0);
		key = doc.getInteger("key", 0);
		lastLoginDate = doc.getString("lastLoginDate");
		int i;
		List<Document> dot = (List<Document>) doc.get("transactions");
		transactions = new ArrayList<>();
		for (i=0;i<dot.size();i++) {
			Transaction t = new Transaction();
			t.setAccount(this);
			t.setFromDoc(dot.get(i));
			transactions.add(t);
		}
		List<Document> dos = (List<Document>) doc.get("skins");
		skins = new ArrayList<>();
		for (i=0;i<dos.size();i++) {
			Skin s = SkinFactory.makeSkin(dos.get(i));
			skins.add(s);
		}
	}
	
	public AccountEntity toAccountEntity() {
		AccountEntity entity = new AccountEntity();
		entity.setMoney(money);
		entity.setIngot(ingot);
		entity.setKey(key);
		int i;
		List<SkinEntity> skinEntities = new ArrayList<>();
		for (i=0;i<skins.size();i++) {
			skinEntities.add(skins.get(i).toSkinEntity());
		}
		entity.setSkins(skinEntities);
		return entity;
	}
}
