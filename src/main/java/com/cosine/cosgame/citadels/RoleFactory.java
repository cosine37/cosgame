package com.cosine.cosgame.citadels;

import org.bson.Document;

import com.cosine.cosgame.citadels.roles.Assassin;
import com.cosine.cosgame.citadels.roles.Bishop;
import com.cosine.cosgame.citadels.roles.Merchant;
import com.cosine.cosgame.citadels.roles.Thief;

public class RoleFactory {
	public static Role createRole(int num, String name, String img, int owner) {
		Role role;
		if (img.contentEquals("001")) {
			role = new Assassin();
		} else if (img.contentEquals("002")) {
			role = new Thief();
		} else if (img.contentEquals("005")) {
			role = new Bishop();
		} else if (img.contentEquals("006")) {
			role = new Merchant();
		} 
		
		else {
			role = new Role();
			role.setNum(num);
			role.setName(name);
			role.setImg(img);
		}
		role.setOwner(owner);
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
