package com.cosine.cosgame.marshbros;

public class Card {
	String name;
	String img;
	int atk;
	int hp;
	int iAtk;
	int iHp;
	int where; // where is the card
	int owner;
	int tempOwner;
	int type;
	int choice; // not choosed, taked actions, etc.
	
	Board board;
	Player player;
	
	boolean taunt;
	boolean canAttack;
	
	public Card() {
		taunt = false;
		canAttack = true;
	}
	
	public void activate() {
		
	}
	
	public void recruitment() {
		
	}
	
	public void lastWish() {
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getiAtk() {
		return iAtk;
	}
	public void setiAtk(int iAtk) {
		this.iAtk = iAtk;
	}
	public int getiHp() {
		return iHp;
	}
	public void setiHp(int iHp) {
		this.iHp = iHp;
	}
	public int getWhere() {
		return where;
	}
	public void setWhere(int where) {
		this.where = where;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getChoice() {
		return choice;
	}
	public void setChoice(int choice) {
		this.choice = choice;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isTaunt() {
		return taunt;
	}
	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}
	public boolean isCanAttack() {
		return canAttack;
	}
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
	
	
	
}
