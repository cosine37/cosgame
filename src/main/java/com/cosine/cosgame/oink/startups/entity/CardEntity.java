package com.cosine.cosgame.oink.startups.entity;

import java.util.Map;

public class CardEntity {
	String name;
	int num;
	int coinOn;
	String color;
	Map<String, String> barColor;
	Map<String, String> iconStyle;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCoinOn() {
		return coinOn;
	}
	public void setCoinOn(int coinOn) {
		this.coinOn = coinOn;
	}
	public Map<String, String> getBarColor() {
		return barColor;
	}
	public void setBarColor(Map<String, String> barColor) {
		this.barColor = barColor;
	}
	public Map<String, String> getIconStyle() {
		return iconStyle;
	}
	public void setIconStyle(Map<String, String> iconStyle) {
		this.iconStyle = iconStyle;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
