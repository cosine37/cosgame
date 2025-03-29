package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class CardEntity {
	int id;
	int level;
	int rarity;
	String name;
	String desc;
	List<Boolean> types;
	Map<String, String> imgStyle;
	boolean playable;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<Boolean> getTypes() {
		return types;
	}
	public void setTypes(List<Boolean> types) {
		this.types = types;
	}
	public Map<String, String> getImgStyle() {
		return imgStyle;
	}
	public void setImgStyle(Map<String, String> imgStyle) {
		this.imgStyle = imgStyle;
	}
	public boolean isPlayable() {
		return playable;
	}
	public void setPlayable(boolean playable) {
		this.playable = playable;
	}
	public int getRarity() {
		return rarity;
	}
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	
}
