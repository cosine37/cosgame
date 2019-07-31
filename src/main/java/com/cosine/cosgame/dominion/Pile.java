package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class Pile {
	String name;
	String image;
	List<Card> cards;
	int numCards;
	int numCardsInit;
	boolean isSupply;
	boolean isSplit;
	boolean isMixed;
	
	public Pile() {
		cards = new ArrayList<Card>();
		isMixed = false;
		isSplit = false;
		isSupply = true;
	}
	
	public Pile(String name) {
		this();
		this.name = name;
	}
	
	public Pile(Class c, int num) {
		this();
		int i;
		for (i=0;i<num;i++) {
			try {
				cards.add((Card) c.newInstance());
			} catch (Exception e) {
				
			}	
		}
		name = cards.get(0).getName();
		image = cards.get(0).getImage();
	}

	public void add(Card card) {
		cards.add(card);
	}
	
	public void set(Class c, int num) {
		cards = new ArrayList<Card>();
		int i;
		for (i=0;i<num;i++) {
			try {
				cards.add((Card) c.newInstance());
			} catch (Exception e) {
				
			}	
		}
	}
	
	public void addTo(Player player) {
		Card card = cards.remove(0);
		card.setPlayer(player);
		
		name = cards.get(0).getName();
		image = cards.get(0).getImage();
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Card getTop() {
		return cards.get(0);
	}
	
	public Card removeTop() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Card> getCards(){
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public int getNumCards() {
		if (cards.size() == 1) {
			if (cards.get(0).getName().equals("Empty")) return 0;
		}
		return cards.size();
	}
	
	public int getNumCardsInit() {
		return numCardsInit;
	}

	public void setNumCardsInit(int numCardsInit) {
		this.numCardsInit = numCardsInit;
	}
	
	public void setIsSupply(boolean isSupply) {
		this.isSupply = isSupply;
	}
	
	public void setIsSplit(boolean isSplit) {
		this.isSplit = isSplit;
	}
	
	public void setIsMixed(boolean isMixed) {
		this.isMixed = isMixed;
	}
	
	public boolean isSupply() {
		return isSupply;
	}
	
	public boolean isSplit() {
		return isSplit;
	}
	
	public boolean isMixed() {
		return isMixed;
	}

	public void setSupply(boolean isSupply) {
		this.isSupply = isSupply;
	}
	
}
