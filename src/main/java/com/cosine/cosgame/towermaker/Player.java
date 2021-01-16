package com.cosine.cosgame.towermaker;

public class Player {
	String name;
	int index;
	int life;
	int hp;
	boolean stillIn;
	Monster monster;
	Board board;

	public void draw() {
		Monster m = board.getDeck().remove(0);
		monster = m;
	}
	
	public void quit() {
		stillIn = false;
	}
	
	public void removeEquip(int x) {
		int n = board.getHero().getEquips().size();
		if (x>=0 && x<n) {
			board.getHero().getEquips().remove(x);
		}
	}
	
	public void putInTower() {
		if (monster != null) {
			board.getTower().add(monster);
			monster = null;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
	public boolean isStillIn() {
		return stillIn;
	}
	public void setStillIn(boolean stillIn) {
		this.stillIn = stillIn;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public Monster getMonster() {
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	
}
