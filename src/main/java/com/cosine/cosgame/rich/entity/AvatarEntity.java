package com.cosine.cosgame.rich.entity;

import java.util.Map;

public class AvatarEntity {
	String name;
	Map<String, String> avatarBlockStyle;
	Map<String, String> avatarStyle;
	Map<String, String> vehicleStyle;
	
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
	public Map<String, String> getAvatarBlockStyle() {
		return avatarBlockStyle;
	}
	public void setAvatarBlockStyle(Map<String, String> avatarBlockStyle) {
		this.avatarBlockStyle = avatarBlockStyle;
	}
	public Map<String, String> getVehicleStyle() {
		return vehicleStyle;
	}
	public void setVehicleStyle(Map<String, String> vehicleStyle) {
		this.vehicleStyle = vehicleStyle;
	}

}
