package com.cosine.cosgame.oink.account.entity;

import java.util.List;
import java.util.Map;

public class AccountEntity {
	String name;
	String signature;
	String signatureDisplay;
	String nameDisplay;
	int chosenAvatar;
	int money;
	int diamond;
	int key;
	List<Integer> avatars;
	boolean visitedOink;
	Map<String, String> chosenAvatarStyle;
	List<Map<String, String>> avatarStyles;
	List<Integer> shopAvatars;
	List<String> shopAvatarNames;
	List<Map<String, String>> shopAvatarStyles;
	List<Integer> canBuyAvatar;
	
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
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSignatureDisplay() {
		return signatureDisplay;
	}
	public void setSignatureDisplay(String signatureDisplay) {
		this.signatureDisplay = signatureDisplay;
	}
	public String getNameDisplay() {
		return nameDisplay;
	}
	public void setNameDisplay(String nameDisplay) {
		this.nameDisplay = nameDisplay;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getDiamond() {
		return diamond;
	}
	public void setDiamond(int diamond) {
		this.diamond = diamond;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public List<Integer> getShopAvatars() {
		return shopAvatars;
	}
	public void setShopAvatars(List<Integer> shopAvatars) {
		this.shopAvatars = shopAvatars;
	}
	public List<Map<String, String>> getShopAvatarStyles() {
		return shopAvatarStyles;
	}
	public void setShopAvatarStyles(List<Map<String, String>> shopAvatarStyles) {
		this.shopAvatarStyles = shopAvatarStyles;
	}
	public List<String> getShopAvatarNames() {
		return shopAvatarNames;
	}
	public void setShopAvatarNames(List<String> shopAvatarNames) {
		this.shopAvatarNames = shopAvatarNames;
	}
	public List<Integer> getCanBuyAvatar() {
		return canBuyAvatar;
	}
	public void setCanBuyAvatar(List<Integer> canBuyAvatar) {
		this.canBuyAvatar = canBuyAvatar;
	}
	
}
