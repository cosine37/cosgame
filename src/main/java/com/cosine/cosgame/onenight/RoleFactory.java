package com.cosine.cosgame.onenight;

import com.cosine.cosgame.onenight.roles.*;

public class RoleFactory {
	public static Role createRole(String img) {
		Role role;
		if (img.contentEquals("r00")) {
			role = new Villager();
		} else if (img.contentEquals("r01")) {
			role = new Werewolf();
		} else if (img.contentEquals("r02")) {
			role = new Seer();
		} else if (img.contentEquals("r03")) {
			role = new Thief();
		} else if (img.contentEquals("r04")) {
			role = new Urchin();
		} else if (img.contentEquals("r05")) {
			role = new Minion();
		} else if (img.contentEquals("r06")) {
			role = new Insomniac();
		} else if (img.contentEquals("r07")) {
			role = new Drunk();
		} else if (img.contentEquals("r08")) {
			role = new Mason();
		} else if (img.contentEquals("r09")) {
			role = new Hunter();
		} else if (img.contentEquals("r10")) {
			role = new Tanner();
		} else if (img.contentEquals("r11")) {
			role = new SleepingWolf();
		} else if (img.contentEquals("r12")) {
			role = new ApprenticeSeer();
		} else if (img.contentEquals("r13")) {
			role = new Detective();
		} else if (img.contentEquals("r14")) {
			role = new Witch();
		} else if (img.contentEquals("r15")) {
			role = new MysticWolf();
		} else if (img.contentEquals("r16")) {
			role = new Guard();
		} else if (img.contentEquals("r17")) {
			role = new Idiot();
		} else if (img.contentEquals("r18")) {
			role = new Watcher();
		} else if (img.contentEquals("r19")) {
			role = new Prince();
		} else if (img.contentEquals("r20")) {
			role = new Sheriff();
		} else if (img.contentEquals("r21")) {
			role = new YoungWitch();
		} else if (img.contentEquals("r22")) {
			role = new Agent();
		} else if (img.contentEquals("r23")) {
			role = new Magician();
		} else if (img.contentEquals("r24")) {
			role = new WolfChild();
		} else if (img.contentEquals("r25")) {
			role = new Investigator();
		} else if (img.contentEquals("r2500")) {
			role = new Investigator();
			role.setSide(Consts.HUMAN);
		} else if (img.contentEquals("r2501")) {
			role = new Investigator();
			role.setSide(Consts.WOLF);
		} else if (img.contentEquals("r2502")) {
			role = new Investigator();
			role.setSide(Consts.TANNER);
		} else if (img.contentEquals("r26")) {
			role = new Timberwolf();
		} else if (img.contentEquals("r27")) {
			role = new BearTrainer();
		}
		
		else {
			role = new Role();
			role.setImg(img);
		}
		
		return role;
	}
	
	public static Role createRole(int num) {
		Role role;
		if (num == 0) {
			role = new Villager();
		} else if (num == 1) {
			role = new Werewolf();
		} else if (num == 2) {
			role = new Seer();
		} else if (num == 3) {
			role = new Thief();
		} else if (num == 4) {
			role = new Urchin();
		} else if (num == 5) {
			role = new Minion();
		} else if (num == 6) {
			role = new Insomniac();
		} else if (num == 7) {
			role = new Drunk();
		} else if (num == 8) {
			role = new Mason();
		} else if (num == 9) {
			role = new Hunter();
		} else if (num == 10) {
			role = new Tanner();
		} else if (num == 11) {
			role = new SleepingWolf();
		} else if (num == 12) {
			role = new ApprenticeSeer();
		} else if (num == 13) {
			role = new Detective();
		} else if (num == 14) {
			role = new Witch();
		} else if (num == 15) {
			role = new MysticWolf();
		} else if (num == 16) {
			role = new Guard();
		} else if (num == 17) {
			role = new Idiot();
		} else if (num == 18) {
			role = new Watcher();
		} else if (num == 19) {
			role = new Prince();
		} else if (num == 20) {
			role = new Sheriff();
		} else if (num == 21) {
			role = new YoungWitch();
		} else if (num == 22) {
			role = new Agent();
		} else if (num == 23) {
			role = new Magician();
		} else if (num == 24) {
			role = new WolfChild();
		} else if (num == 25) {
			role = new Investigator();
		} else if (num == 26) {
			role = new Timberwolf();
		} else if (num == 27) {
			role = new BearTrainer();
		}
		
		else {
			role = new Role();
			role.setRoleNum(num);
		}
		
		return role;
	}
}
