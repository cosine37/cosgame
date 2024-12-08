package com.cosine.cosgame.oink.grove.entity;

import java.util.List;

import com.cosine.cosgame.oink.account.entity.AccountEntity;

public class GrovePlayerEntity {
	String name;
	int index;
	List<Integer> liars;
	AccountEntity account;
	RoleEntity leftOutsider;
	RoleEntity rightOutsider;
	
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
	public List<Integer> getLiars() {
		return liars;
	}
	public void setLiars(List<Integer> liars) {
		this.liars = liars;
	}
	public AccountEntity getAccount() {
		return account;
	}
	public void setAccount(AccountEntity account) {
		this.account = account;
	}
	public RoleEntity getLeftOutsider() {
		return leftOutsider;
	}
	public void setLeftOutsider(RoleEntity leftOutsider) {
		this.leftOutsider = leftOutsider;
	}
	public RoleEntity getRightOutsider() {
		return rightOutsider;
	}
	public void setRightOutsider(RoleEntity rightOutsider) {
		this.rightOutsider = rightOutsider;
	}
}
