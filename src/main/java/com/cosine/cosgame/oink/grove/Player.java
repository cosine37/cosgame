package com.cosine.cosgame.oink.grove;

import java.util.List;

public class Player {
	String name;
	int phase;
	int accused;
	int index;
	int liar;
	Role leftOutsider;
	Role rightOutsider;
	
	List<Integer> viewed;
	
	Grove grove;
	
	public Player() {
		
	}
	
	public void addLiar(int x) {
		liar = liar+x;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getAccused() {
		return accused;
	}
	public void setAccused(int accused) {
		this.accused = accused;
	}
	public List<Integer> getViewed() {
		return viewed;
	}
	public void setViewed(List<Integer> viewed) {
		this.viewed = viewed;
	}

	public Role getLeftOutsider() {
		return leftOutsider;
	}
	public void setLeftOutsider(Role leftOutsider) {
		this.leftOutsider = leftOutsider;
	}
	public Role getRightOutsider() {
		return rightOutsider;
	}
	public void setRightOutsider(Role rightOutsider) {
		this.rightOutsider = rightOutsider;
	}
	public Grove getGrove() {
		return grove;
	}
	public void setGrove(Grove grove) {
		this.grove = grove;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLiar() {
		return liar;
	}
	public void setLiar(int liar) {
		this.liar = liar;
	}
	
	
}
