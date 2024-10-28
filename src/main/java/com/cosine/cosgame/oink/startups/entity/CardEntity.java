package com.cosine.cosgame.oink.startups.entity;

import java.util.Map;

public class CardEntity {
	String name;
	int num;
	int coinOn;
	String color;
	int played;
	Map<String, String> barColor;
	Map<String, String> iconStyle;
	Map<String, String> cstyle;
	
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
	public Map<String, String> getCstyle() {
		return cstyle;
	}
	public void setCstyle(Map<String, String> cstyle) {
		this.cstyle = cstyle;
	}
	public int getPlayed() {
		return played;
	}
	public void setPlayed(int played) {
		this.played = played;
	}
	
}
