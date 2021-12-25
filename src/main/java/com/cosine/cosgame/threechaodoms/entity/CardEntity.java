package com.cosine.cosgame.threechaodoms.entity;

import java.util.List;

public class CardEntity {
	String name;
	String courtesy;
	String img;
	String title;
	String desc;
	int faction;
	boolean blankSpace;
	int playType;
	int playSubType;
	List<String> options;
	List<String> options2;
	String instruction;
	
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
	public int getPlayType() {
		return playType;
	}
	public void setPlayType(int playType) {
		this.playType = playType;
	}
	public int getPlaySubType() {
		return playSubType;
	}
	public void setPlaySubType(int playSubType) {
		this.playSubType = playSubType;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public List<String> getOptions2() {
		return options2;
	}
	public void setOptions2(List<String> options2) {
		this.options2 = options2;
	}
}
