package com.cosine.cosgame.oink.startups.entity;

import java.util.List;

public class EndRoundEntity {
	String stockName;
	String icon;
	String colorStyle;
	String shareholder;
	List<String> playerNames;
	List<Integer> coin1Before;
	List<Integer> coin3Before;
	List<Integer> coin1After;
	List<Integer> coin3After;
	int num;
	
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getColorStyle() {
		return colorStyle;
	}
	public void setColorStyle(String colorStyle) {
		this.colorStyle = colorStyle;
	}
	public String getShareholder() {
		return shareholder;
	}
	public void setShareholder(String shareholder) {
		this.shareholder = shareholder;
	}
	public List<String> getPlayerNames() {
		return playerNames;
	}
	public void setPlayerNames(List<String> playerNames) {
		this.playerNames = playerNames;
	}
	public List<Integer> getCoin1Before() {
		return coin1Before;
	}
	public void setCoin1Before(List<Integer> coin1Before) {
		this.coin1Before = coin1Before;
	}
	public List<Integer> getCoin3Before() {
		return coin3Before;
	}
	public void setCoin3Before(List<Integer> coin3Before) {
		this.coin3Before = coin3Before;
	}
	public List<Integer> getCoin1After() {
		return coin1After;
	}
	public void setCoin1After(List<Integer> coin1After) {
		this.coin1After = coin1After;
	}
	public List<Integer> getCoin3After() {
		return coin3After;
	}
	public void setCoin3After(List<Integer> coin3After) {
		this.coin3After = coin3After;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
