package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.citadels.rdarkcity.*;
import com.cosine.cosgame.citadels.roles.*;
import com.cosine.cosgame.citadels.sc2016.*;
import com.cosine.cosgame.citadels.scdarkcity.*;
import com.cosine.cosgame.citadels.sckx.*;
import com.cosine.cosgame.citadels.sckx.delicacy.*;
import com.cosine.cosgame.citadels.specialcards.*;

public class AllRes {
	List<Card> baseCards;
	List<Card> specialCards;
	List<Card> duoColorCards;
	List<Card> omniColorBaseCards;
	List<Card> omniColorCards;
	List<Role> allRoles;
	List<Delicacy> delicacies;
	Board board;
	
	public AllRes() {
		baseCards = new ArrayList<>();
		specialCards = new ArrayList<>();
		duoColorCards = new ArrayList<>();
		omniColorBaseCards = new ArrayList<>();
		omniColorCards = new ArrayList<>();
		allRoles = new ArrayList<>();
		delicacies = new ArrayList<>();
		setAllRoles();
		setBaseCards();
		setDuoColorCards();
		setOmniColorCards();
		setSpecialCards();
		setDelicacies();
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
		r = new Journalist();
		allRoles.add(r);
		r = new FurnitureDealer();
		allRoles.add(r);
		r = new Diplomat();
		allRoles.add(r);
	}
	
	public void setSpecialCards() {
		Card c;
		c = new DinosaurPark();
		specialCards.add(c);
		c = new SECenter();
		specialCards.add(c);
		c = new PlanningHall();
		specialCards.add(c);
		c = new FormerResidence();
		specialCards.add(c);
		c = new FormerResidence();
		specialCards.add(c);
		c = new Library();
		specialCards.add(c);
		c = new GlobalHarbor();
		specialCards.add(c);
		c = new Inn();
		specialCards.add(c);
		c = new GreatWall();
		specialCards.add(c);
		c = new Subway();
		specialCards.add(c);
		c = new SouthStreet();
		specialCards.add(c);
		
		c = new BaolinTemple();
		specialCards.add(c);
		c = new CRHStation();
		specialCards.add(c);
		c = new DrownedCity();
		specialCards.add(c);
		c = new HongmeiPark();
		specialCards.add(c);
		c = new QingguoLane();
		specialCards.add(c);
		c = new Canal();
		specialCards.add(c);
		c = new DevZone();
		specialCards.add(c);
		
		c = new Statue();
		specialCards.add(c);
		c = new Condo();
		specialCards.add(c);
		c = new MtMao();
		specialCards.add(c);
		c = new TianningTemple();
		specialCards.add(c);
		c = new PeoplesPark();
		specialCards.add(c);
		c = new Bank();
		specialCards.add(c);
		c = new Comb();
		specialCards.add(c);
		
		c = new CulturePalace();
		specialCards.add(c);
		c = new CommodityMarket();
		specialCards.add(c);
		c = new GreatA3Factory();
		specialCards.add(c);
		c = new Hotel();
		specialCards.add(c);
		c = new ZijingPark();
		specialCards.add(c);
		c = new MacawTown();
		specialCards.add(c);
		c = new TianmuLake();
		specialCards.add(c);
		c = new Dragon9Hill();
		specialCards.add(c);
		c = new ZhonglianBuilding();
		specialCards.add(c);
		c = new GoldStore();
		specialCards.add(c);
		c = new QingfengPark();
		specialCards.add(c);
		c = new Villa();
		specialCards.add(c);
		c = new CharityHouse();
		specialCards.add(c);
		c = new HuPalace();
		specialCards.add(c);
		c = new MelonField();
		specialCards.add(c);
		c = new Insurance();
		specialCards.add(c);
		
		c = new Museum();
		specialCards.add(c);
		c = new Village();
		specialCards.add(c);
		c = new Framework();
		specialCards.add(c);
		c = new ChemicalPlant();
		specialCards.add(c);
		
		c = new Newsstand();
		specialCards.add(c);
		c = new CanalNo5();
		specialCards.add(c);
		c = new Fort();
		specialCards.add(c);
		c = new DongpoPark();
		specialCards.add(c);
		c = new StupidSun();
		specialCards.add(c);
		c = new ShootMoonBay();
		specialCards.add(c);
		c = new XinhuaBookstore();
		specialCards.add(c);
		
		c = new ForebackNorthBay();
		specialCards.add(c);
		c = new NearGarden();
		specialCards.add(c);
		c = new EastStorage();
		specialCards.add(c);
		c = new WaterStreet();
		specialCards.add(c);
		c = new LightCity();
		specialCards.add(c);
		c = new JinchuanPark();
		specialCards.add(c);
		
	}
	
	public void setBaseCards() {
		int i;
		Card c;
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("路边摊", CitadelsConsts.GREEN, 1, "g1", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("小吃店", CitadelsConsts.GREEN, 2, "g21", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("菜场", CitadelsConsts.GREEN, 2, "g22", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("超市", CitadelsConsts.GREEN, 3, "g3", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("商场", CitadelsConsts.GREEN, 4, "g4", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("商业步行街", CitadelsConsts.GREEN, 5, "g5", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("幼儿园", CitadelsConsts.BLUE, 1, "b1", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("小学", CitadelsConsts.BLUE, 2, "b2", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("初中", CitadelsConsts.BLUE, 3, "b3", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("高中", CitadelsConsts.BLUE, 5, "b5", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("岗亭", CitadelsConsts.RED, 1, "r1", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("监狱", CitadelsConsts.RED, 2, "r2", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("公安局", CitadelsConsts.RED, 3, "r3", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("军区", CitadelsConsts.RED, 5, "r5", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("居委会", CitadelsConsts.YELLOW, 3, "y3", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("电视台", CitadelsConsts.YELLOW, 4, "y4", -1, 0);
			baseCards.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("市政府", CitadelsConsts.YELLOW, 5, "y5", -1, 0);
			baseCards.add(c);
		}
	}
	
	public void setDuoColorCards() {
		Card c;
		c = CardFactory.createCard("少年宫", CitadelsConsts.GREENBLUE, 4, "gb4", -1, 0);
		duoColorCards.add(c);
		c = CardFactory.createCard("保安公司", CitadelsConsts.GREENRED, 4, "gr4", -1, 0);
		duoColorCards.add(c);
		c = CardFactory.createCard("世贸中心", CitadelsConsts.GREENYELLOW, 4, "gy4", -1, 0);
		duoColorCards.add(c);
		c = CardFactory.createCard("警校", CitadelsConsts.BLUERED, 4, "br4", -1, 0);
		duoColorCards.add(c);
		c = CardFactory.createCard("市委党校", CitadelsConsts.BLUEYELLOW, 4, "by4", -1, 0);
		duoColorCards.add(c);
		c = CardFactory.createCard("江南指挥部", CitadelsConsts.REDYELLOW, 4, "ry4", -1, 0);
		duoColorCards.add(c);
	}
	
	public void setOmniColorCards() {
		Card c;
		c = CardFactory.createCard("招待所", CitadelsConsts.PURPLE, 1, "o11p", -1, 0);
		omniColorCards.add(c);
		c = CardFactory.createCard("办事处", CitadelsConsts.PURPLE, 2, "o21p", -1, 0);
		omniColorCards.add(c);
		c = CardFactory.createCard("商务宾馆", CitadelsConsts.PURPLE, 4, "o42p", -1, 0);
		omniColorCards.add(c);
		c = CardFactory.createCard("写字楼", CitadelsConsts.PURPLE, 5, "o51p", -1, 0);
		omniColorCards.add(c);
		
		int i;
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("奶茶店", CitadelsConsts.GREEN, 2, "g23", -1, 0);
			omniColorBaseCards.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("技校", CitadelsConsts.BLUE, 4, "o41b", -1, 0);
			omniColorBaseCards.add(c);
		}
		c = CardFactory.createCard("路障", CitadelsConsts.RED, 0, "o01r", -1, 0);
		omniColorBaseCards.add(c);
		c = CardFactory.createCard("新闻大厦", CitadelsConsts.YELLOW, 4, "o43y", -1, 0);
		omniColorBaseCards.add(c);
	}
	
	public void setDelicacies() {
		Delicacy d;
		d = new Peach();
		delicacies.add(d);
		d = new SesameCake();
		delicacies.add(d);
		d = new Layers();
		delicacies.add(d);
		d = new EggTart();
		delicacies.add(d);
		d = new Fish();
		delicacies.add(d);
		d = new Wine();
		delicacies.add(d);
		d = new Tea();
		delicacies.add(d);
		d = new DriedRaddish();
		delicacies.add(d);
	}
	
	public List<Role> genRoles(int playerNum){
		List<Role> ans = new ArrayList<>();
		for (int i=0;i<7;i++) {
			ans.add(allRoles.get(i));
		}
		if (board.getNo8() == 0) {
			ans.add(allRoles.get(7));
		} else if (board.getNo8() == 1) {
			ans.add(allRoles.get(10));
		}
		if (board.getNo9() == 0) {
			ans.add(allRoles.get(8));
		} else if (board.getNo9() == 1) {
			ans.add(allRoles.get(9));
		}
		
		return ans;
	}
	
	public List<Delicacy> genDelicacy(int numDelicacy){
		List<Delicacy> ans = new ArrayList<>();
		int x = numDelicacy;
		if (x > delicacies.size()) {
			x = delicacies.size();
		}
		for (int i=0;i<x;i++) {
			Random rand = new Random();
			int size = delicacies.size();
			ans.add(delicacies.remove(rand.nextInt(size)));
		}
		return ans;
	}
	
	public List<Card> genDeck(){
		List<Card> deck = new ArrayList<>();
		int i;
		for (i=0;i<baseCards.size();i++) {
			deck.add(baseCards.get(i));
		}
		int specialCount = 18;
		int duoColorCount = 0;
		int omniColorCount = 0;
		if (board.isUseDuoColor()) {
			duoColorCount = 3;
			Card c = new AsiaCinema();
			deck.add(c);
			for (i=0;i<duoColorCount;i++) {
				Random rand = new Random();
				int size = duoColorCards.size();
				deck.add(duoColorCards.remove(rand.nextInt(size)));
			}
		}
		
		if (board.isUseOmniColor()) {
			omniColorCount = 2;
			for (i=0;i<omniColorCount;i++) {
				Random rand = new Random();
				int size = omniColorCards.size();
				deck.add(omniColorCards.remove(rand.nextInt(size)));
			}
			for (i=0;i<omniColorBaseCards.size();i++) {
				deck.add(omniColorBaseCards.get(i));
			}
		}
		
		for (i=0;i<specialCount;i++) {
			Random rand = new Random();
			int size = specialCards.size();
			deck.add(specialCards.remove(rand.nextInt(size)));
		}
		return deck;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
}
