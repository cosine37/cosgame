package com.cosine.cosgame.marshbros;

import java.util.List;

import org.bson.Document;

public class Role {
	Card card;
	int hp;
	int atk;
	int owner;
	int tempOwner;
	int choice; // attack, raid, action, etc.
	List<Integer> extraInfo;
	
	Player player;
	Board board;
	
	public Role() {
		card = new Card();
	}
	
	public Role(Card c) {
		card = c;
		card.setRole(this);
		hp = card.getHp();
		atk = card.getAtk();
	}
	
	public void raid() {
		int x = board.diceFinalResult(atk, hp);
		player.addResource(x);
	}
	
	public void attack(int playerIndex, int roleIndex) {
		int x = board.diceFinalResult(atk, hp);
		Player tp = board.getPlayerByIndex(playerIndex);
		Role tr = tp.getArea().get(roleIndex);
		tr.loseHp(x);
	}
	
	public void addHp(int x) {
		hp = hp+x;
	}
	
	public void loseHp(int x) {
		hp = hp-x;
	}
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}
	public int getTempOwner() {
		return tempOwner;
	}
	public void setTempOwner(int tempOwner) {
		this.tempOwner = tempOwner;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public List<Integer> getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(List<Integer> extraInfo) {
		this.extraInfo = extraInfo;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("card", card.getImg());
		doc.append("hp", hp);
		doc.append("atk", atk);
		doc.append("owner", owner);
		doc.append("tempOwner", tempOwner);
		doc.append("choice", choice);
		doc.append("extraInfo", extraInfo);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		String cardName = doc.getString("card");
		card = CardFactory.createCard(cardName);
		hp = doc.getInteger("hp", 0);
		atk = doc.getInteger("atk", 0);
		owner = doc.getInteger("owner", -1);
		tempOwner = doc.getInteger("tempOwner", -1);
		choice = doc.getInteger("choice", -1);
		extraInfo = (List<Integer>) doc.get("extraInfo");
		card.setRole(this);
	}
	
}
