package com.cosine.cosgame.rich.entity;

import java.util.Map;

public class AvatarEntity {
	String name;
	Map<String, String> avatarStyle;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getAvatarStyle() {
		return avatarStyle;
	}
	public void setAvatarStyle(Map<String, String> avatarStyle) {
		this.avatarStyle = avatarStyle;
	}

}
