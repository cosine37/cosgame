package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class PlaceEntity {
	String name;
	String desc;
	String ownerName;
	int type;
	List<Integer> playersOn;
	
	// estate related
	int level;
	int maxLevel;
	int area;
	int ownerId;
	int cost;
	int upgradeCost;
	List<Integer> rents;
	Map<String, String> imgStyle;
	Map<String, String> fontStyle;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Integer> getPlayersOn() {
		return playersOn;
	}
	public void setPlayersOn(List<Integer> playersOn) {
		this.playersOn = playersOn;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getUpgradeCost() {
		return upgradeCost;
	}
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}
	public List<Integer> getRents() {
		return rents;
	}
	public void setRents(List<Integer> rents) {
		this.rents = rents;
	}
	public int getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public Map<String, String> getImgStyle() {
		return imgStyle;
	}
	public void setImgStyle(Map<String, String> imgStyle) {
		this.imgStyle = imgStyle;
	}
	public Map<String, String> getFontStyle() {
		return fontStyle;
	}
	public void setFontStyle(Map<String, String> fontStyle) {
		this.fontStyle = fontStyle;
	}
	
}
