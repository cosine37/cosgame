package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class CardEntity {
	int id;
	int level;
	int rarity;
	int playStyle;
	String name;
	String desc;
	List<Boolean> types;
	Map<String, String> imgStyle;
	Map<String, String> descStyle;
	boolean playable;
	List<String> options;
	
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
	public int getPlayStyle() {
		return playStyle;
	}
	public void setPlayStyle(int playStyle) {
		this.playStyle = playStyle;
	}
	public Map<String, String> getDescStyle() {
		return descStyle;
	}
	public void setDescStyle(Map<String, String> descStyle) {
		this.descStyle = descStyle;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
}
