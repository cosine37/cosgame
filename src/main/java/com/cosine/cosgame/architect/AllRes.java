package com.cosine.cosgame.architect;

import java.util.List;

public class AllRes {
	List<Card> cardDeck;
	List<Building> buildingDeck;
	
	public AllRes() {
		
	}
	
	
	public void genDeck() {
		
	}
	
	public Card getWoodCutter() {
		Card card = new Card();
		card.setName("伐木工");
		card.setType(Consts.WORKER);
		card.addProvideRes(Consts.WOOD, 2);
		
		return card;
	}
	
	public Card getMage() {
		Card card = new Card();
		card.setName("法师");
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
