package com.cosine.cosgame.coslash;

import java.util.List;

public class Player {
	public static final int INACTIVE = 0;
	public static final int ALIVE = 1;
	public static final int DYING = 2;
	public static final int DEAD = 3;
	
	public static final int PHASE_OUTER = 100;
	public static final int PHASE_START = 101;
	public static final int PHASE_JUDGE = 102;
	public static final int PHASE_DRAW = 103;
	public static final int PHASE_ACTION = 104;
	public static final int PHASE_DISCARD = 105;
	public static final int PHASE_END = 106;
	  
	int status;
	int phase;
	String name;
	Role role;
	List<Card> hand;
	Card weapen;
	Card shield;
	Card atkhorse;
	Card defhorse;
	int maxHP;
	int hp;
	List<Ask> asks;
	
	boolean hasJail; // 乐不思蜀
	boolean hasBomb; // 闪电
	boolean hasFoodless; // 兵粮寸断
	
	public Player() {
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public Card getWeapen() {
		return weapen;
	}

	public void setWeapen(Card weapen) {
		this.weapen = weapen;
	}

	public Card getShield() {
		return shield;
	}

	public void setShield(Card shield) {
		this.shield = shield;
	}

	public Card getAtkhorse() {
		return atkhorse;
	}

	public void setAtkhorse(Card atkhorse) {
		this.atkhorse = atkhorse;
	}

	public Card getDefhorse() {
		return defhorse;
	}

	public void setDefhorse(Card defhorse) {
		this.defhorse = defhorse;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public List<Ask> getAsks() {
		return asks;
	}

	public void setAsks(List<Ask> asks) {
		this.asks = asks;
	}
	
	
	
}
