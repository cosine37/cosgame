package com.cosine.cosgame.gravepsycho;

import org.bson.Document;

public class Player {
	String name;
	String avatar;
	int money;
	int moneyThisTurn;
	int decision;
	int decisionLastTurn;
	boolean canUseThief;
	boolean stillIn;
	
	public Player() {
		name = "";
		avatar = "/image/Gravepsycho/Avatar/1.png";
		money = 0;
		moneyThisTurn = 0;
		decision = 0;
		stillIn = true;
	}
	
	public Player(String name) {
		this();
		this.name = name;
	}
	
	public void addMoney(int x) {
		moneyThisTurn = moneyThisTurn+x;
	}
	
	public void secureMoney() {
		money = money+moneyThisTurn;
		moneyThisTurn = 0;
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
	public int getMoneyThisTurn() {
		return moneyThisTurn;
	}
	public void setMoneyThisTurn(int moneyThisTurn) {
		this.moneyThisTurn = moneyThisTurn;
	}
	public int getDecision() {
		return decision;
	}
	public void setDecision(int decision) {
		this.decision = decision;
	}
	public boolean isStillIn() {
		return stillIn;
	}
	public void setStillIn(boolean stillIn) {
		this.stillIn = stillIn;
	}
	public int getDecisionLastTurn() {
		return decisionLastTurn;
	}
	public void setDecisionLastTurn(int decisionLastTurn) {
		this.decisionLastTurn = decisionLastTurn;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean isCanUseThief() {
		return canUseThief;
	}
	public void setCanUseThief(boolean canUseThief) {
		this.canUseThief = canUseThief;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("money", money);
		doc.append("moneyThisTurn", moneyThisTurn);
		doc.append("decision", decision);
		doc.append("decisionLastTurn", decisionLastTurn);
		doc.append("stillIn", stillIn);
		doc.append("avatar", avatar);
		doc.append("canUseThief", canUseThief);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		money = doc.getInteger("money", 0);
		moneyThisTurn = doc.getInteger("moneyThisTurn", 0);
		decision = doc.getInteger("decision", 0);
		decisionLastTurn = doc.getInteger("decisionLastTurn", 0);
		stillIn = doc.getBoolean("stillIn", false);
		avatar = doc.getString("avatar");
		canUseThief = doc.getBoolean("canUseThief", false);
	}
	
}
