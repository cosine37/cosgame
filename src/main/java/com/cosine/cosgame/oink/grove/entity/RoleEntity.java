package com.cosine.cosgame.oink.grove.entity;

import java.util.List;
import java.util.Map;

public class RoleEntity {
	int num;
	int height;
	int age;
	String name;
	String description;
	String gender;
	String avatar;
	
	List<Integer> predicted;
	Map<String, String> avatarStyle;

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Integer> getPredicted() {
		return predicted;
	}
	public void setPredicted(List<Integer> predicted) {
		this.predicted = predicted;
	}
	public Map<String, String> getAvatarStyle() {
		return avatarStyle;
	}
	public void setAvatarStyle(Map<String, String> avatarStyle) {
		this.avatarStyle = avatarStyle;
	}
	
	
}
