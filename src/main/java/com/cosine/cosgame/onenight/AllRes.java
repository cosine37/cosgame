package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.questions.*;
import com.cosine.cosgame.onenight.roles.*;

public class AllRes {
	List<Role> allRoles;
	List<Question> allQuestions;
	
	public AllRes() {
		genAllRoles();
		genAllQuestions();
	}
	
	public void genAllQuestions() {
		allQuestions = new ArrayList<>();
		Question q;
		q = new AnotherGypsy();
		allQuestions.add(q);
		q = new HasSadako();
		allQuestions.add(q);
		q = new HasPope();
		allQuestions.add(q);
		q = new HasWeremeleon();
		allQuestions.add(q);
		q = new HasWolfCenter();
		allQuestions.add(q);
		q = new LeftPlayerRole();
		allQuestions.add(q);
		q = new RightPlayerRole();
		allQuestions.add(q);
	}
	
	public void genAllRoles() {
		allRoles = new ArrayList<>();
		Role r;
		int i;
		
		r = new MushroomFarmer();
		allRoles.add(r);
		r = new WolfCook();
		allRoles.add(r);
		r = new Pharmacist();
		allRoles.add(r);
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
		r = new GreyWolf();
		allRoles.add(r);
		r = new RedWolf();
		allRoles.add(r);
		r = new BladeWolf();
		allRoles.add(r);
		r = new LangLang();
		allRoles.add(r);
		r = new Sadako();
		allRoles.add(r);
		r = new Minion();
		allRoles.add(r);
		r = new PlagueDoctor();
		allRoles.add(r);
		r = new Pope();
		allRoles.add(r);
		r = new Redhat();
		allRoles.add(r);
		for (i=0;i<2;i++) {
			r = new Mason();
			allRoles.add(r);
		}
		r = new Washerwoman();
		allRoles.add(r);
		r = new Waiter();
		allRoles.add(r);
		r = new BearTrainer();
		allRoles.add(r);
		r = new Beggar();
		allRoles.add(r);
		for (i=0;i<2;i++) {
			r = new Gypsy();
			allRoles.add(r);
		}
		r = new Seer();
		allRoles.add(r);
		r = new ApprenticeSeer();
		allRoles.add(r);
		r = new Shaman();
		allRoles.add(r);
		r = new Sorcerer();
		allRoles.add(r);
		r = new PoliceDetect();
		allRoles.add(r);
		r = new Investigator();
		allRoles.add(r);
		r = new CombatMedic();
		allRoles.add(r);
		r = new Jester();
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
		r = new RepentWolf();
		allRoles.add(r);
		r = new Drunk();
		allRoles.add(r);
		r = new Abba();
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
		r = new Parent();
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
		r = new RustyKnight();
		allRoles.add(r);
		r = new MadDog();
		allRoles.add(r);
		r = new WolfHunter();
		allRoles.add(r);
		
		for (i=0;i<3;i++) {
			r = new Villager();
			allRoles.add(r);
		}
		r = new Delusional();
		allRoles.add(r);
		
	}

	public List<Role> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(List<Role> allRoles) {
		this.allRoles = allRoles;
	}

	public List<Question> getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}
	
}
