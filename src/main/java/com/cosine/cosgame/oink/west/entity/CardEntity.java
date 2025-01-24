package com.cosine.cosgame.oink.west.entity;

import java.util.Map;

public class CardEntity {
	int num;
	String name;
	String desc;
	String img;
	
	Map<String, String> avatarStyle;
	Map<String, String> winStyle;
	Map<String, String> loseStyle;
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Map<String, String> getAvatarStyle() {
		return avatarStyle;
	}
	public void setAvatarStyle(Map<String, String> avatarStyle) {
		this.avatarStyle = avatarStyle;
	}
	public Map<String, String> getWinStyle() {
		return winStyle;
	}
	public void setWinStyle(Map<String, String> winStyle) {
		this.winStyle = winStyle;
	}
	public Map<String, String> getLoseStyle() {
		return loseStyle;
	}
	public void setLoseStyle(Map<String, String> loseStyle) {
		this.loseStyle = loseStyle;
	}
}
