package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Account {
	String name;
	int money;
	int ingot;
	int key;
	
	List<Transaction> transactions;
	List<Skin> skins;

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
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("money", money);
		doc.append("ingot", ingot);
		doc.append("key", key);
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
		int i;
		List<Document> dot = (List<Document>) doc.get("transactions");
		transactions = new ArrayList<>();
		for (i=0;i<dot.size();i++) {
			Transaction t = new Transaction();
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
}
