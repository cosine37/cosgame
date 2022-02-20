package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class PlayerEntity {
	String name;
	
	boolean alive;
	boolean canVote;
	boolean nominated;
	String displayName;
	String roleText;
	String initialRoleText;
	IconEntity icon;
	boolean win;
	List<String> receives;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isCanVote() {
		return canVote;
	}
	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isNominated() {
		return nominated;
	}
	public void setNominated(boolean nominated) {
		this.nominated = nominated;
	}
	public IconEntity getIcon() {
		return icon;
	}
	public void setIcon(IconEntity icon) {
		this.icon = icon;
	}
	public String getInitialRoleText() {
		return initialRoleText;
	}
	public void setInitialRoleText(String initialRoleText) {
		this.initialRoleText = initialRoleText;
	}
	public String getRoleText() {
		return roleText;
	}
	public void setRoleText(String roleText) {
		this.roleText = roleText;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	public List<String> getReceives() {
		return receives;
	}
	public void setReceives(List<String> receives) {
		this.receives = receives;
	}
	
}
