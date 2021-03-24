package com.cosine.cosgame.zodiac;

import com.cosine.cosgame.zodiac.roles.*;

public class RoleFactory {
	public static Role createRole(int num) {
		Role role = new Role();
		if (num == Consts.SWINDLER) {
			role = new Swindler();
		} else if (num == Consts.KIDNAPPER) {
			role = new Kidnapper();
		} else if (num == Consts.CURATOR) {
			role = new Curator();
		} else if (num == Consts.DETECTIVE) {
			role = new Detective();
		} else if (num == Consts.APPRENTICE1) {
			role = new Apprentice1();
		} else if (num == Consts.APPRENTICE2) {
			role = new Apprentice2();
		} else if (num == Consts.THIEF) {
			role = new Thief();
		} else if (num == Consts.GENIUS) {
			role = new Genius();
		}
		return role;
	}
	
	public static Role createRole(String img) {
		Role role = new Role();
		if (img.contentEquals(Consts.SWINDLERIMG)) {
			role = new Swindler();
		} else if (img.contentEquals(Consts.KIDNAPPERIMG)) {
			role = new Kidnapper();
		} else if (img.contentEquals(Consts.CURATORIMG)) {
			role = new Curator();
		} else if (img.contentEquals(Consts.DETECTIVEIMG)) {
			role = new Detective();
		} else if (img.contentEquals(Consts.APPRENTICE1IMG)) {
			role = new Apprentice1();
		} else if (img.contentEquals(Consts.APPRENTICE2IMG)) {
			role = new Apprentice2();
		} else if (img.contentEquals(Consts.THIEFIMG)) {
			role = new Thief();
		} else if (img.contentEquals(Consts.GENIUSIMG)) {
			role = new Genius();
		}
		return role;
	}

}
