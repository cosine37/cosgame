package com.cosine.cosgame.belltower.shop;

import org.bson.Document;

public class Transaction {
	int type;
	int amount;
	String info;
	Account account;
	
	public static final int MONEY = 1;
	public static final int DIAMOND = 2;
	public static final int KEY = 3;
	
	public Transaction() {
		
	}
	
	public Transaction(int type, int amount, String info) {
		this.type = type;
		this.amount = amount;
		this.info = info;
	}
	
	public Transaction(int type, int amount, int character) {
		this.type = type;
		this.amount = amount;
		//this.info = "购买" + s.getSkinName() + "-" + s.getTitle();
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		doc.append("amount", amount);
		doc.append("info", info);
		return doc;
	}
	public void setFromDoc(Document doc) {
		type = doc.getInteger("type", 0);
		amount = doc.getInteger("amount", 0);
		info = doc.getString("info");
	}
}
