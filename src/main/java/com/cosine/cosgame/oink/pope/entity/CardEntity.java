package com.cosine.cosgame.oink.pope.entity;

import java.util.Map;

public class CardEntity {
	int num;
	String name;
	String img;
	String desc;
	String color;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Map<String, String> getCstyle() {
		return cstyle;
	}
	public void setCstyle(Map<String, String> cstyle) {
		this.cstyle = cstyle;
	}
}
