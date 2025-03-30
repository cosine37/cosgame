package com.cosine.cosgame.rich;

public class Vehicle {
	int id;
	String name;
	
	public Vehicle() {
		id = -1;
	}
	
	public Vehicle(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
}
