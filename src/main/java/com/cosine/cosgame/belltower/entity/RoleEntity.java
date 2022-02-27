package com.cosine.cosgame.belltower.entity;

import java.util.List;

public class RoleEntity {
	int id;
	String name;
	String img;
	String desc;
	List<String> instructions;
	int numPlayerChoose;
	boolean hasFirstNight;
	boolean hasRestNights;
	int sequence;
	int faction;
	int group;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public List<String> getInstructions() {
		return instructions;
	}
	public void setInstructions(List<String> instructions) {
		this.instructions = instructions;
	}
	public int getNumPlayerChoose() {
		return numPlayerChoose;
	}
	public void setNumPlayerChoose(int numPlayerChoose) {
		this.numPlayerChoose = numPlayerChoose;
	}
	public boolean isHasFirstNight() {
		return hasFirstNight;
	}
	public void setHasFirstNight(boolean hasFirstNight) {
		this.hasFirstNight = hasFirstNight;
	}
	public boolean isHasRestNights() {
		return hasRestNights;
	}
	public void setHasRestNights(boolean hasRestNights) {
		this.hasRestNights = hasRestNights;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	
}
