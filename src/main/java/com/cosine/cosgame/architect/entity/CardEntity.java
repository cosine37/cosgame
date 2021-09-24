package com.cosine.cosgame.architect.entity;

import java.util.List;

public class CardEntity {
	String name;
	String img;
	String type;
	String numUpgrade;
	List<String> provideRes;
	List<String> needRes;
	
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
	
	
}
