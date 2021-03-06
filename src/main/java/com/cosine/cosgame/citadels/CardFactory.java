package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.citadels.sc2016.*;
import com.cosine.cosgame.citadels.scdarkcity.*;
import com.cosine.cosgame.citadels.sckx.*;
import com.cosine.cosgame.citadels.sckx.omnicolor.*;
import com.cosine.cosgame.citadels.specialcards.*;

public class CardFactory {
	public static Card createCard(String name, int color, int cost, String img, int builtRound, int beautifyLevel) {
		Card card;
		if (img.contentEquals("p601")) {
			card = new DinosaurPark();
		} else if (img.contentEquals("p602")) {
			card = new SECenter();
		} else if (img.contentEquals("p401")) {
			card = new PlanningHall();
		} else if (img.contentEquals("p301")) {
			card = new FormerResidence();
		} else if (img.contentEquals("p603")) {
			card = new Library();
		} else if (img.contentEquals("p604")) {
			card = new GlobalHarbor();
		} else if (img.contentEquals("p201")) {
			card = new Inn();
		} else if (img.contentEquals("p605")) {
			card = new GreatWall();
		} else if (img.contentEquals("p501")) {
			card = new Subway();
		} else if (img.contentEquals("p502")) {
			card = new SouthStreet();
		} else if (img.contentEquals("p503")) {
			card = new BaolinTemple();
		} else if (img.contentEquals("p504")) {
			card = new CRHStation();
		} else if (img.contentEquals("p505")) {
			card = new DrownedCity();
		} else if (img.contentEquals("p606")) {
			card = new HongmeiPark();
		} else if (img.contentEquals("p402")) {
			card = new QingguoLane();
		} else if (img.contentEquals("p607")) {
			card = new Canal();
		} else if (img.contentEquals("p506")) {
			card = new DevZone();
		} else if (img.contentEquals("p302")) {
			card = new Statue();
		} else if (img.contentEquals("p507")) {
			card = new Condo();
		} else if (img.contentEquals("p508")) {
			card = new MtMao();
		} else if (img.contentEquals("p403")) {
			card = new TianningTemple();
		} else if (img.contentEquals("p202")) {
			card = new PeoplesPark();
		} else if (img.contentEquals("p608")) {
			card = new Bank();
		} else if (img.contentEquals("p001")) {
			card = new Comb();
		} else if (img.contentEquals("p609")) {
			card = new CulturePalace();
		} else if (img.contentEquals("p404")) {
			card = new CommodityMarket();
		} else if (img.contentEquals("p610")) {
			card = new GreatA3Factory();
		} else if (img.contentEquals("p611")) {
			card = new Hotel();
		} else if (img.contentEquals("p405")) {
			card = new ZijingPark();
		} else if (img.contentEquals("p303")) {
			card = new MacawTown();
		} else if (img.contentEquals("p509")) {
			card = new TianmuLake();
		} else if (img.contentEquals("p612")) {
			card = new Dragon9Hill();
		} else if (img.contentEquals("p406")) {
			card = new ZhonglianBuilding();
		} else if (img.contentEquals("p304")) {
			card = new GoldStore();
		} else if (img.contentEquals("p510")) {
			card = new QingfengPark();
		} else if (img.contentEquals("p407")) {
			card = new Villa();
		} else if (img.contentEquals("p613")) {
			card = new CharityHouse();
		} else if (img.contentEquals("p408")) {
			card = new HuPalace();
		} else if (img.contentEquals("p512")) {
			card = new AsiaCinema();
		} else if (img.contentEquals("p513")) {
			card = new MelonField();
		} else if (img.contentEquals("p614")) {
			card = new Insurance();
		} else if (img.contentEquals("p305")) {
			card = new Framework();
		} else if (img.contentEquals("p409")) {
			card = new Museum();
		} else if (img.contentEquals("p410")) {
			card = new Village();
		} else if (img.contentEquals("o41b") 
				|| img.contentEquals("o41g")
				|| img.contentEquals("o41r")
				|| img.contentEquals("o41y")) {
			card = new TechSchool();
			card.setColor(color);
			card.setImg(img);
		} else if (img.contentEquals("p306")) {
			card = new ChemicalPlant();
		} else if (img.contentEquals("p203")) {
			card = new Newsstand();
		} else if (img.contentEquals("p511")) {
			card = new CanalNo5();
		} else if (img.contentEquals("p615")) {
			card = new Fort();
		} else if (img.contentEquals("p514")) {
			card = new DongpoPark();
		} else if (img.contentEquals("p204")) {
			card = new StupidSun();
		} else if (img.contentEquals("o42b") 
				|| img.contentEquals("o42g")
				|| img.contentEquals("o42r")
				|| img.contentEquals("o42y")
				|| img.contentEquals("o42p")) {
			card = new Motel();
			card.setColor(color);
			card.setImg(img);
		} else if (img.contentEquals("p515")) {
			card = new ShootMoonBay();
		} else if (img.contentEquals("o21b") 
				|| img.contentEquals("o21g")
				|| img.contentEquals("o21r")
				|| img.contentEquals("o21y")
				|| img.contentEquals("o21p")) {
			card = new Agency();
			card.setColor(color);
			card.setImg(img);
		} else if (img.contentEquals("o01r")) {
			card = new Roadblock();
		} else if (img.contentEquals("o43y")) {
			card = new NewsCenter();
		} else if (img.contentEquals("o51b") 
				|| img.contentEquals("o51g")
				|| img.contentEquals("o51r")
				|| img.contentEquals("o51y")
				|| img.contentEquals("o51p")) {
			card = new Office();
			card.setColor(color);
			card.setImg(img);
		} else if (img.contentEquals("o11b") 
				|| img.contentEquals("o11g")
				|| img.contentEquals("o11r")
				|| img.contentEquals("o11y")
				|| img.contentEquals("o11p")) {
			card = new Guesthouse();
			card.setColor(color);
			card.setImg(img);
		} else if (img.contentEquals("p307")) {
			card = new XinhuaBookstore();
		} else if (img.contentEquals("p516")) {
			card = new CitizenSquare();
		} else if (img.contentEquals("p517")) {
			card = new Landmark();
		} else if (img.contentEquals("p701")) {
			card = new DinoWaterTown();
		} else if (img.contentEquals("p411")) {
			card = new Stadium();
		} else if (img.contentEquals("p308")) {
			card = new ForebackNorthBay();
		} else if (img.contentEquals("p205")) {
			card = new NearGarden();
		} else if (img.contentEquals("p412")) {
			card = new EastStorage();
		} else if (img.contentEquals("p518")) {
			card = new WaterStreet();
		} else if (img.contentEquals("p616")) {
			card = new LightCity();
		} else if (img.contentEquals("p309")) {
			card = new JinchuanPark();
		}
		
		else {
			if (color>9) {
				card = new DuoColorCard(name);
			} else {
				card = new Card(name);
			}
			card.setCost(cost);
			card.setColor(color);
			card.setImg(img);
		}
		
		card.setBeautifyLevel(beautifyLevel);
		card.setBuiltRound(builtRound);
		return card;
	}
	
	public static Card createCard(Document doc) {
		String name = doc.getString("name");
		int color = doc.getInteger("color", 0);
		int cost = doc.getInteger("cost", 0);
		String img = doc.getString("img");
		int builtRound = doc.getInteger("builtRound", -1);
		int beautifyLevel = doc.getInteger("beautifyLevel", 0);
		List<Document> cu = (List<Document>) doc.get("cardsUnder");
		List<Card> cardsUnder = new ArrayList<>();
		for (int i=0;i<cu.size();i++){
			Card c = createCard(cu.get(i));
			cardsUnder.add(c);
		}
		Card card = createCard(name, color, cost, img, builtRound, beautifyLevel);
		card.setCardsUnder(cardsUnder);
		return card;
		
	}

}
