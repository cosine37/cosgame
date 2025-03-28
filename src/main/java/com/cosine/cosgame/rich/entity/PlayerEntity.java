package com.cosine.cosgame.rich.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	int money;
	int salary;
	int phase;
	int index;
	int rollDisplay;
	int jailRound;
	boolean inJail;
	boolean confirmed;
	AvatarEntity avatar;
	AvatarEntity avatarOrigin;
	
	List<Integer> owned;
	
	int hp;
	int star;

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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public List<Integer> getOwned() {
		return owned;
	}
	public void setOwned(List<Integer> owned) {
		this.owned = owned;
	}
	public int getRollDisplay() {
		return rollDisplay;
	}
	public void setRollDisplay(int rollDisplay) {
		this.rollDisplay = rollDisplay;
	}
	public int getJailRound() {
		return jailRound;
	}
	public void setJailRound(int jailRound) {
		this.jailRound = jailRound;
	}
	public boolean isInJail() {
		return inJail;
	}
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public AvatarEntity getAvatar() {
		return avatar;
	}
	public void setAvatar(AvatarEntity avatar) {
		this.avatar = avatar;
	}
	public AvatarEntity getAvatarOrigin() {
		return avatarOrigin;
	}
	public void setAvatarOrigin(AvatarEntity avatarOrigin) {
		this.avatarOrigin = avatarOrigin;
	}
	
}
