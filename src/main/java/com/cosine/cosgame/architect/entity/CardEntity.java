package com.cosine.cosgame.architect.entity;

import java.util.List;

public class CardEntity {
	String name;
	String img;
	String type;
	String subType;
	String numUpgrade;
	String numAwaken;
	String maxPlayNum;
	List<String> provideRes;
	List<String> provideResAlt;
	List<String> needRes;
	List<String> clickQuote;
	List<String> resolveQuote;
	List<String> resOn;
	
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
	public List<String> getResOn() {
		return resOn;
	}
	public void setResOn(List<String> resOn) {
		this.resOn = resOn;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getNumAwaken() {
		return numAwaken;
	}
	public void setNumAwaken(String numAwaken) {
		this.numAwaken = numAwaken;
	}
	public List<String> getProvideResAlt() {
		return provideResAlt;
	}
	public void setProvideResAlt(List<String> provideResAlt) {
		this.provideResAlt = provideResAlt;
	}
	
	
}
