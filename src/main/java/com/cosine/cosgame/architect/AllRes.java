package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;

public class AllRes {
	List<Card> cardDeck;
	List<Building> buildingDeck;
	
	public AllRes() {
		cardDeck = new ArrayList<>();
		buildingDeck = new ArrayList<>();
		genDeck();
	}
	
	
	public void genDeck() {
		Card card;
		
		card = new Card();
		card.setName("樵夫");
		card.setImg("Hiker");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("樵夫");
		card.setImg("Hiker");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 2);
		card.addProvideRes(Consts.STONE, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("樵夫");
		card.setImg("Hiker");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 1);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("高级伐木工");
		card.setImg("Lumberjack");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 3);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("大象");
		card.setImg("Elephant");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 4);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("矿工");
		card.setImg("Miner");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.GOLD, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("矿工");
		card.setImg("Miner");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.IRON, 1);
		cardDeck.add(card);
		
		card = new Card();
		card.setName("矿工");
		card.setImg("Miner");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.STONE, 2);
		cardDeck.add(card);
	}
	
	public Card getWoodCutter() {
		Card card = new Card();
		card.setName("伐木工");
		card.setImg("WoodCutter");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 2);
		
		return card;
	}
	
	public Card getMage() {
		Card card = new Card();
		card.setName("法师");
		card.setImg("Mage");
		card.setType(Consts.MAGICIAN);
		card.setNumUpgrade(2);
		
		return card;
	}

	public List<Card> getCardDeck() {
		return cardDeck;
	}

	public void setCardDeck(List<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}

	public List<Building> getBuildingDeck() {
		return buildingDeck;
	}

	public void setBuildingDeck(List<Building> buildingDeck) {
		this.buildingDeck = buildingDeck;
	}
	
}
