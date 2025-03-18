package com.cosine.cosgame.rich.account.entity;

import java.util.List;
import java.util.Map;

import com.cosine.cosgame.rich.entity.AvatarEntity;

public class AccountEntity {
	String name;
	String signature;
	String signatureDisplay;
	String nameDisplay;
	int chosenAvatar;
	int money;
	int diamond;
	int key;
	String avatars;
	boolean visitedRich;
	AvatarEntity chosenAvatarEntity;
	List<AvatarEntity> avatarEntities;
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
	public String getAvatars() {
		return avatars;
	}
	public void setAvatars(String avatars) {
		this.avatars = avatars;
	}
	public boolean isVisitedRich() {
		return visitedRich;
	}
	public void setVisitedRich(boolean visitedRich) {
		this.visitedRich = visitedRich;
	}
	public AvatarEntity getChosenAvatarEntity() {
		return chosenAvatarEntity;
	}
	public void setChosenAvatarEntity(AvatarEntity chosenAvatarEntity) {
		this.chosenAvatarEntity = chosenAvatarEntity;
	}
	public List<AvatarEntity> getAvatarEntities() {
		return avatarEntities;
	}
	public void setAvatarEntities(List<AvatarEntity> avatarEntities) {
		this.avatarEntities = avatarEntities;
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
