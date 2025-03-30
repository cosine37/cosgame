package com.cosine.cosgame.rich;

import java.util.HashMap;

import com.cosine.cosgame.rich.entity.AvatarEntity;

public class Avatar {
	int id;
	String name;
	
	public Avatar(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public AvatarEntity toAvatarEntity(Vehicle vehicle) {
		AvatarEntity entity = new AvatarEntity();
		entity.setName(name);
		HashMap<String, String> avatarStyle = new HashMap<>();
		avatarStyle.put("background-image", "url(/image/Rich/avatar/" + getHead() + ".png)");
		avatarStyle.put("background-size", "cover");
		entity.setAvatarStyle(avatarStyle);
		HashMap<String, String> vehicleStyle = new HashMap<>();
		if (vehicle != null) {
			vehicleStyle.put("background-image", "url(/image/Rich/vehicle/" + vehicle.getId() + ".png)");
			vehicleStyle.put("background-size", "cover");
		}
		entity.setVehicleStyle(vehicleStyle);
		entity.setAvatarBlockStyle(new HashMap<>());
		return entity;
	}
	
	public String getHead() {
		return "head_" + id;
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
