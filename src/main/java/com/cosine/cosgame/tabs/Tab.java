package com.cosine.cosgame.tabs;

public class Tab {
	String name;
	String color;
	String path;
	String backgroundColor;
	
	public Tab(String name, String path, String color, String backgroundColor) {
		this.name = name;
		this.color = color;
		this.path = path;
		this.backgroundColor = backgroundColor;
	}
	
	public Tab(String name, String path, String color) {
		this(name, path, color, "white");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
