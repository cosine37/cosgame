package com.cosine.cosgame.citadels;

import org.bson.Document;

import com.cosine.cosgame.citadels.roles.Assassin;
import com.cosine.cosgame.citadels.roles.Thief;

public class RoleFactory {
	public static Role createRole(int num, String name, String img, int owner) {
		Role role;
		if (img.contentEquals("001")) {
			role = new Assassin();
			role.setOwner(owner);
		} else if (img.contentEquals("002")) {
			role = new Thief();
			role.setOwner(owner);
		} else {
			role = new Role();
			role.setNum(num);
			role.setName(name);
			role.setImg(img);
			role.setOwner(owner);
		}
		
		return role;
	}
	
	public static Role createRole(Document dor) {
		int num = dor.getInteger("num", 0);
		String name = dor.getString("name");
		String img = dor.getString("img");
		int owner = dor.getInteger("owner", -1);
		return createRole(num,name,img, owner);
	}
}
