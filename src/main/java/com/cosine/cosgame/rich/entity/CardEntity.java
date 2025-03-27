package com.cosine.cosgame.rich.entity;

import java.util.List;

public class CardEntity {
	int id;
	int level;
	String name;
	String desc;
	String img;
	List<Boolean> types;
	
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public List<Boolean> getTypes() {
		return types;
	}
	public void setTypes(List<Boolean> types) {
		this.types = types;
	}
	
}
