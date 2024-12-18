package com.cosine.cosgame.oink.pope.entity;

import java.util.Map;

public class CardEntity {
	int num;
	String name;
	String img;
	String description;
	String color;
	Map<String, String> avatarStyle;
	Map<String, String> primaryColor;
	Map<String, String> secondaryColor;
	Map<String, String> cstyle;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, String> getAvatarStyle() {
		return avatarStyle;
	}
	public void setAvatarStyle(Map<String, String> avatarStyle) {
		this.avatarStyle = avatarStyle;
	}
	public Map<String, String> getPrimaryColor() {
		return primaryColor;
	}
	public void setPrimaryColor(Map<String, String> primaryColor) {
		this.primaryColor = primaryColor;
	}
	public Map<String, String> getSecondaryColor() {
		return secondaryColor;
	}
	public void setSecondaryColor(Map<String, String> secondaryColor) {
		this.secondaryColor = secondaryColor;
	}
	public Map<String, String> getCstyle() {
		return cstyle;
	}
	public void setCstyle(Map<String, String> cstyle) {
		this.cstyle = cstyle;
	}
}
