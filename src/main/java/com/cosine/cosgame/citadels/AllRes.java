package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.roles.*;

public class AllRes {
	List<Card> baseCards;
	List<Card> specialCards;
	List<Role> allRoles;
	
	public AllRes() {
		baseCards = new ArrayList<>();
		specialCards = new ArrayList<>();
		allRoles = new ArrayList<>();
		setAllRoles();
		setBaseCards();
		setSpecialCards();
	}
	
	void setAllRoles() {
		Role r;
		r = new Assassin();
		allRoles.add(r);
		r = new Thief();
		allRoles.add(r);
		r = new Magician();
		allRoles.add(r);
		r = new King();
		allRoles.add(r);
		r = new Bishop();
		allRoles.add(r);
		r = new Merchant();
		allRoles.add(r);
		r = new Architect();
		allRoles.add(r);
		r = new Warlord();
		allRoles.add(r);
	}
	
	public void setSpecialCards() {
		
	}
	
	public void setBaseCards() {
		int i;
		Card c;
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("路边摊", CitadelsConsts.GREEN, 1, "g1");
			baseCards.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("快餐店", CitadelsConsts.GREEN, 2, "g21");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("菜场", CitadelsConsts.GREEN, 2, "g22");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("超市", CitadelsConsts.GREEN, 3, "g3");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("商场", CitadelsConsts.GREEN, 4, "g4");
			baseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("商业步行街", CitadelsConsts.GREEN, 5, "g5");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("幼儿园", CitadelsConsts.BLUE, 1, "b1");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("小学", CitadelsConsts.BLUE, 2, "b2");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("初中", CitadelsConsts.BLUE, 3, "b3");
			baseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("高中", CitadelsConsts.BLUE, 5, "b5");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("岗亭", CitadelsConsts.RED, 1, "r1");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("监狱", CitadelsConsts.RED, 2, "r2");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("警察局", CitadelsConsts.RED, 3, "r3");
			baseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("军区", CitadelsConsts.RED, 5, "r5");
			baseCards.add(c);
		}
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("居委会", CitadelsConsts.YELLOW, 3, "y3");
			baseCards.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("电视台", CitadelsConsts.YELLOW, 4, "y4");
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("市政府", CitadelsConsts.YELLOW, 5, "y5");
			baseCards.add(c);
		}
	}
	
	public List<Role> genRoles(){
		return allRoles;
	}
	
	public List<Card> genDeck(){
		return baseCards;
	}
	
}
