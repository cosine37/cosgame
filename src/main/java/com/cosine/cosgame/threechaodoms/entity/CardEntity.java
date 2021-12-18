package com.cosine.cosgame.threechaodoms.entity;

public class CardEntity {
	String name;
	String courtesy;
	String img;
	String title;
	String desc;
	int faction;
	boolean blankSpace;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCourtesy() {
		return courtesy;
	}
	public void setCourtesy(String courtesy) {
		this.courtesy = courtesy;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}
	public boolean isBlankSpace() {
		return blankSpace;
	}
	public void setBlankSpace(boolean blankSpace) {
		this.blankSpace = blankSpace;
	}
}
