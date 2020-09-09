package com.cosine.cosgame.nothanks;

public class Package {
	Card card;
	int money;
	
	public Package() {
		
	}
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public void addMoney() {
		this.money++;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
