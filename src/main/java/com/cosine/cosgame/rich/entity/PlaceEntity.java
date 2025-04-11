package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class PlaceEntity {
	String name;
	String desc;
	String ownerName;
	String areaName;
	int type;
	int id;
	List<Integer> playersOn;
	DetailEntity detail;
	
	// estate related
	int level;
	int maxLevel;
	int area;
	int ownerId;
	int cost;
	int upgradeCost;
	int rent;
	List<Integer> rents;
	Map<String, String> imgStyle;
	Map<String, String> fontStyle;
	Map<String, String> areaStyle;
	Map<String, String> estateBackground;

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
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public Map<String, String> getAreaStyle() {
		return areaStyle;
	}
	public void setAreaStyle(Map<String, String> areaStyle) {
		this.areaStyle = areaStyle;
	}
	public DetailEntity getDetail() {
		return detail;
	}
	public void setDetail(DetailEntity detail) {
		this.detail = detail;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Map<String, String> getEstateBackground() {
		return estateBackground;
	}
	public void setEstateBackground(Map<String, String> estateBackground) {
		this.estateBackground = estateBackground;
	}
	
}
