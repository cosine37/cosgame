package com.cosine.cosgame.architect.entity;

import java.util.List;

public class BuildingEntity {
	List<String> price;
	String name;
	String img;
	String score;
	String canBuild;
	
	public List<String> getPrice() {
		return price;
	}
	public void setPrice(List<String> price) {
		this.price = price;
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getCanBuild() {
		return canBuild;
	}
	public void setCanBuild(String canBuild) {
		this.canBuild = canBuild;
	}
	
}
