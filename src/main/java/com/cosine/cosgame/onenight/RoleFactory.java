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
		} else if (img.contentEquals("r28")) {
			role = new Beggar();
		} else if (img.contentEquals("r29")) {
			role = new VillageHead();
		} else if (img.contentEquals("r30")) {
			role = new Pope();
		} else if (img.contentEquals("r31")) {
			role = new Bishop();
		} else if (img.contentEquals("r32")) {
			role = new Monk();
		} else if (img.contentEquals("r33")) {
			role = new Actor();
		} else if (img.contentEquals("r34")) {
			role = new Pagan();
		} else if (img.contentEquals("r3401")) {
			role = new QuoteWerewolf();
		} else if (img.contentEquals("r35")) {
			role = new Shepherd();
		} else if (img.contentEquals("r36")) {
			role = new AlphaWolf();
		} else if (img.contentEquals("r3601")) {
			role = new QuoteWerewolf2();
		} else if (img.contentEquals("r37")) {
			role = new LittleGirl();
		} else if (img.contentEquals("r38")) {
			role = new Fox();
		} else if (img.contentEquals("r39")) {
			role = new Weremeleon();
		} else if (img.contentEquals("r40")) {
			role = new Baker();
		} else if (img.contentEquals("r41")) {
			role = new Masquerader();
		} else if (img.contentEquals("r42")) {
			role = new Sorcerer();
		} else if (img.contentEquals("r43")) {
			role = new Scapegoat();
		} else if (img.contentEquals("r44")) {
			role = new Wolfdog();
		} else if (img.contentEquals("r4400")) {
			role = new Wolfdog();
			role.setSide(Consts.HUMAN);
		} else if (img.contentEquals("r4401")) {
			role = new Wolfdog();
			role.setSide(Consts.WOLF);
		} else if (img.contentEquals("r45")) {
			role = new BigBadWolf();
		} else if (img.contentEquals("r46")) {
			role = new Butterfly();
		} else if (img.contentEquals("r47")) {
			role = new Gravekeeper();
		} else if (img.contentEquals("r48")) {
			role = new Changeling();
		} else if (img.contentEquals("r49")) {
			role = new OldMan();
		} else if (img.contentEquals("r50")) {
			role = new WolfGodFather();
		} else if (img.contentEquals("r51")) {
			role = new Nun();
		} else if (img.contentEquals("r52")) {
			role = new Link();
		} else if (img.contentEquals("r53")) {
			role = new Redhat();
		} else if (img.contentEquals("r54")) {
			role = new StutteringJudge();
		} else if (img.contentEquals("r55")) {
			role = new Sentinel();
		} else if (img.contentEquals("r56")) {
			role = new Cupid();
		} else if (img.contentEquals("r57")) {
			role = new Priest();
		} else if (img.contentEquals("r58")) {
			role = new Lycanroc();
		} else if (img.contentEquals("r59")) {
			role = new Instigator();
		} else if (img.contentEquals("r60")) {
			role = new Diseased();
		} else if (img.contentEquals("r61")) {
			role = new Assassin();
		} else if (img.contentEquals("r62")) {
			role = new CombatMedic();
		} else if (img.contentEquals("r63")) {
			role = new Warlock();
		} else if (img.contentEquals("r64")) {
			role = new Gremlin();
		} else if (img.contentEquals("r65")) {
			role = new RepentWolf();
		} else if (img.contentEquals("r66")) {
			role = new RustyKnight();
		} else if (img.contentEquals("r67")) {
			role = new MadDog();
		} else if (img.contentEquals("r6700")) {
			role = new MadDog();
			role.setSide(Consts.HUMAN);
		} else if (img.contentEquals("r6701")) {
			role = new MadDog();
			role.setSide(Consts.WOLF);
		} else if (img.contentEquals("r68")) {
			role = new WolfHunter();
		} else if (img.contentEquals("r69")) {
			role = new Sadako();
		} else if (img.contentEquals("r70")) {
			role = new GreyWolf();
		} else if (img.contentEquals("r71")) {
			role = new RedWolf();
		} else if (img.contentEquals("r72")) {
			role = new Gypsy();
		} else if (img.contentEquals("r73")) {
			role = new Parent();
		} else if (img.contentEquals("r74")) {
			role = new BladeWolf();
		} else if (img.contentEquals("r75")) {
			role = new PoliceDetect();
		} else if (img.contentEquals("r76")) {
			role = new Abba();
		} else if (img.contentEquals("r77")) {
			role = new Jester();
		} else if (img.contentEquals("r78")) {
			role = new Waiter();
		} else if (img.contentEquals("r79")) {
			role = new Washerwoman();
		} else if (img.contentEquals("r80")) {
			role = new WolfCook();
		} else if (img.contentEquals("r81")) {
			role = new MushroomFarmer();
		} else if (img.contentEquals("r82")) {
			role = new Pharmacist();
		} else if (img.contentEquals("r83")) {
			role = new Shaman();
		} else if (img.contentEquals("r84")) {
			role = new LangLang();
		} else if (img.contentEquals("r85")) {
			role = new Delusional();
		} else if (img.contentEquals("r86")) {
			role = new PlagueDoctor();
		} else if (img.contentEquals("r87")) {
			role = new Bellman();
		} else if (img.contentEquals("r88")) {
			role = new BacktrackWolf();
		} else if (img.contentEquals("r89")) {
			role = new WildChild();
		} else if (img.contentEquals("r90")) {
			role = new SnakeCharmer();
		} else if (img.contentEquals("r91")) {
			role = new Knight();
		} else if (img.contentEquals("r92")) {
			role = new Whitewolf();
		} else if (img.contentEquals("r93")) {
			role = new SheepWolf();
		} else if (img.contentEquals("r94")) {
			role = new Bartender();
		} else if (img.contentEquals("r95")) {
			role = new StarMover();
		} else if (img.contentEquals("r96")) {
			role = new PoisonWolf();
		} else if (img.contentEquals("r97")) {
			role = new JackOfAllTrades();
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
		} else if (num == 28) {
			role = new Beggar();
		} else if (num == 29) {
			role = new VillageHead();
		} else if (num == 30) {
			role = new Pope();
		} else if (num == 31) {
			role = new Bishop();
		} else if (num == 32) {
			role = new Monk();
		} else if (num == 33) {
			role = new Actor();
		} else if (num == 34) {
			role = new Pagan();
		} else if (num == 3401) {
			role = new QuoteWerewolf();
		} else if (num == 35) {
			role = new Shepherd();
		} else if (num == 36) {
			role = new AlphaWolf();
		} else if (num == 3601) {
			role = new QuoteWerewolf2();
		} else if (num == 37) {
			role = new LittleGirl();
		} else if (num == 38) {
			role = new Fox();
		} else if (num == 39) {
			role = new Weremeleon();
		} else if (num == 40) {
			role = new Baker();
		} else if (num == 41) {
			role = new Masquerader();
		} else if (num == 42) {
			role = new Sorcerer();
		} else if (num == 43) {
			role = new Scapegoat();
		} else if (num == 44) {
			role = new Wolfdog();
		} else if (num == 4400) {
			role = new Wolfdog();
			role.setSide(Consts.HUMAN);
		} else if (num == 4401) {
			role = new Wolfdog();
			role.setSide(Consts.WOLF);
		} else if (num == 45) {
			role = new BigBadWolf();
		} else if (num == 46) {
			role = new Butterfly();
		} else if (num == 47) {
			role = new Gravekeeper();
		} else if (num == 48) {
			role = new Changeling();
		} else if (num == 49) {
			role = new OldMan();
		} else if (num == 50) {
			role = new WolfGodFather();
		} else if (num == 51) {
			role = new Nun();
		} else if (num == 52) {
			role = new Link();
		} else if (num == 53) {
			role = new Redhat();
		} else if (num == 54) {
			role = new StutteringJudge();
		} else if (num == 55) {
			role = new Sentinel();
		} else if (num == 56) {
			role = new Cupid();
		} else if (num == 57) {
			role = new Priest();
		} else if (num == 58) {
			role = new Lycanroc();
		} else if (num == 59) {
			role = new Instigator();
		} else if (num == 60) {
			role = new Diseased();
		} else if (num == 61) {
			role = new Assassin();
		} else if (num == 62) {
			role = new CombatMedic();
		} else if (num == 63) {
			role = new Warlock();
		} else if (num == 64) {
			role = new Gremlin();
		} else if (num == 65) {
			role = new RepentWolf();
		} else if (num == 66) {
			role = new RustyKnight();
		} else if (num == 67) {
			role = new MadDog();
		} else if (num == 6700) {
			role = new MadDog();
			role.setSide(Consts.HUMAN);
		} else if (num == 6701) {
			role = new MadDog();
			role.setSide(Consts.WOLF);
		} else if (num == 68) {
			role = new WolfHunter();
		} else if (num == 69) {
			role = new Sadako();
		} else if (num == 70) {
			role = new GreyWolf();
		} else if (num == 71) {
			role = new RedWolf();
		} else if (num == 72) {
			role = new Gypsy();
		} else if (num == 73) {
			role = new Parent();
		} else if (num == 74) {
			role = new BladeWolf();
		} else if (num == 75) {
			role = new PoliceDetect();
		} else if (num == 76) {
			role = new Abba();
		} else if (num == 77) {
			role = new Jester();
		} else if (num == 78) {
			role = new Waiter();
		} else if (num == 79) {
			role = new Washerwoman();
		} else if (num == 80) {
			role = new WolfCook();
		} else if (num == 81) {
			role = new MushroomFarmer();
		} else if (num == 82) {
			role = new Pharmacist();
		} else if (num == 83) {
			role = new Shaman();
		} else if (num == 84) {
			role = new LangLang();
		} else if (num == 85) {
			role = new Delusional();
		} else if (num == 86) {
			role = new PlagueDoctor();
		} else if (num == 87) {
			role = new Bellman();
		} else if (num == 88) {
			role = new BacktrackWolf();
		} else if (num == 89) {
			role = new WildChild();
		} else if (num == 90) {
			role = new SnakeCharmer();
		} else if (num == 91) {
			role = new Knight();
		} else if (num == 92) {
			role = new Whitewolf();
		} else if (num == 93) {
			role = new SheepWolf();
		} else if (num == 94) {
			role = new Bartender();
		} else if (num == 95) {
			role = new StarMover();
		} else if (num == 96) {
			role = new PoisonWolf();
		} else if (num == 97) {
			role = new JackOfAllTrades();
		}
		
		else {
			role = new Role();
			role.setRoleNum(num);
		}
		
		return role;
	}
}
