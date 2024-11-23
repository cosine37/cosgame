package com.cosine.cosgame.oink.account.entity;

import java.util.List;
import java.util.Map;

public class AccountEntity {
	String name;
	int chosenAvatar;
	List<Integer> avatars;
	boolean visitedOink;
	Map<String, String> chosenAvatarStyle;
	List<Map<String, String>> avatarStyles;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChosenAvatar() {
		return chosenAvatar;
	}
	public void setChosenAvatar(int chosenAvatar) {
		this.chosenAvatar = chosenAvatar;
	}
	public List<Integer> getAvatars() {
		return avatars;
	}
	public void setAvatars(List<Integer> avatars) {
		this.avatars = avatars;
	}
	public boolean isVisitedOink() {
		return visitedOink;
	}
	public void setVisitedOink(boolean visitedOink) {
		this.visitedOink = visitedOink;
	}
	public Map<String, String> getChosenAvatarStyle() {
		return chosenAvatarStyle;
	}
	public void setChosenAvatarStyle(Map<String, String> chosenAvatarStyle) {
		this.chosenAvatarStyle = chosenAvatarStyle;
	}
	public List<Map<String, String>> getAvatarStyles() {
		return avatarStyles;
	}
	public void setAvatarStyles(List<Map<String, String>> avatarStyles) {
		this.avatarStyles = avatarStyles;
	}
	
}
