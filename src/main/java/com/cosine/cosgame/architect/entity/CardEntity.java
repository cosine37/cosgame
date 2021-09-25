package com.cosine.cosgame.architect.entity;

import java.util.List;

public class CardEntity {
	String name;
	String img;
	String type;
	String numUpgrade;
	String maxPlayNum;
	List<String> provideRes;
	List<String> needRes;
	List<String> clickQuote;
	List<String> resolveQuote;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getProvideRes() {
		return provideRes;
	}
	public void setProvideRes(List<String> provideRes) {
		this.provideRes = provideRes;
	}
	public List<String> getNeedRes() {
		return needRes;
	}
	public void setNeedRes(List<String> needRes) {
		this.needRes = needRes;
	}
	public String getNumUpgrade() {
		return numUpgrade;
	}
	public void setNumUpgrade(String numUpgrade) {
		this.numUpgrade = numUpgrade;
	}
	public List<String> getClickQuote() {
		return clickQuote;
	}
	public void setClickQuote(List<String> clickQuote) {
		this.clickQuote = clickQuote;
	}
	public List<String> getResolveQuote() {
		return resolveQuote;
	}
	public void setResolveQuote(List<String> resolveQuote) {
		this.resolveQuote = resolveQuote;
	}
	public String getMaxPlayNum() {
		return maxPlayNum;
	}
	public void setMaxPlayNum(String maxPlayNum) {
		this.maxPlayNum = maxPlayNum;
	}
	
	
}
