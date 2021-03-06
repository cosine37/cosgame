package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.roles.*;

public class AllRes {
	List<Role> allRoles;
	
	public AllRes() {
		genAllRoles();
	}
	
	public void genAllRoles() {
		allRoles = new ArrayList<>();
		Role r;
		int i;
		
		r = new AlphaWolf();
		allRoles.add(r);
		r = new Lycanroc();
		allRoles.add(r);
		r = new Diseased();
		allRoles.add(r);
		r = new Cupid();
		allRoles.add(r);
		r = new Instigator();
		allRoles.add(r);
		r = new Priest();
		allRoles.add(r);
		r = new Assassin();
		allRoles.add(r);
		r = new Sentinel();
		allRoles.add(r);
		
		for (i=0;i<2;i++) {
			r = new Werewolf();
			allRoles.add(r);
		}
		r = new MysticWolf();
		allRoles.add(r);
		r = new Timberwolf();
		allRoles.add(r);
		r = new Weremeleon();
		allRoles.add(r);
		r = new BigBadWolf();
		allRoles.add(r);
		r = new WolfGodFather();
		allRoles.add(r);
		r = new Minion();
		allRoles.add(r);
		r = new Pope();
		allRoles.add(r);
		r = new Redhat();
		allRoles.add(r);
		for (i=0;i<2;i++) {
			r = new Mason();
			allRoles.add(r);
		}
		r = new BearTrainer();
		allRoles.add(r);
		r = new Beggar();
		allRoles.add(r);
		r = new Seer();
		allRoles.add(r);
		r = new ApprenticeSeer();
		allRoles.add(r);
		r = new Sorcerer();
		allRoles.add(r);
		r = new Investigator();
		allRoles.add(r);
		r = new CombatMedic();
		allRoles.add(r);
		r = new Butterfly();
		allRoles.add(r);
		r = new Masquerader();
		allRoles.add(r);
		r = new Thief();
		allRoles.add(r);
		r = new Magician();
		allRoles.add(r);
		r = new Witch();
		allRoles.add(r);
		r = new Changeling();
		allRoles.add(r);
		r = new Actor();
		allRoles.add(r);
		r = new YoungWitch();
		allRoles.add(r);
		r = new Warlock();
		allRoles.add(r);
		r = new LittleGirl();
		allRoles.add(r);
		r = new Urchin();
		allRoles.add(r);
		r = new Idiot();
		allRoles.add(r);
		r = new Gremlin();
		allRoles.add(r);
		r = new Drunk();
		allRoles.add(r);
		r = new Nun();
		allRoles.add(r);
		r = new Insomniac();
		allRoles.add(r);
		r = new OldMan();
		allRoles.add(r);
		r = new Gravekeeper();
		allRoles.add(r);
		r = new Scapegoat();
		allRoles.add(r);
		r = new Watcher();
		allRoles.add(r);
		r = new Bishop();
		allRoles.add(r);
		r = new VillageHead();
		allRoles.add(r);
		r = new Shepherd();
		allRoles.add(r);
		r = new Fox();
		allRoles.add(r);
		r = new Agent();
		allRoles.add(r);
		r = new Detective();
		allRoles.add(r);
		r = new SleepingWolf();
		allRoles.add(r);
		r = new Wolfdog();
		allRoles.add(r);
		r = new Tanner();
		allRoles.add(r);
		r = new Hunter();
		allRoles.add(r);
		r = new Guard();
		allRoles.add(r);
		r = new Prince();
		allRoles.add(r);
		r = new Sheriff();
		allRoles.add(r);
		r = new Monk();
		allRoles.add(r);
		r = new WolfChild();
		allRoles.add(r);
		r = new Pagan();
		allRoles.add(r);
		r = new Baker();
		allRoles.add(r);
		r = new Link();
		allRoles.add(r);
		r = new StutteringJudge();
		allRoles.add(r);
		for (i=0;i<3;i++) {
			r = new Villager();
			allRoles.add(r);
		}
		
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}
	
}
