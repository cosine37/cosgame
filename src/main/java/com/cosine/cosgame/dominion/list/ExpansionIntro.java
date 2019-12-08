package com.cosine.cosgame.dominion.list;

import java.util.ArrayList;
import java.util.List;

public class ExpansionIntro {
	String name;
	String intro;
	List<SingleCard> cards;
	public ExpansionIntro() {
		cards = new ArrayList<SingleCard>();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public List<SingleCard> getCards() {
		return cards;
	}
	public void setCards(List<SingleCard> cards) {
		this.cards = cards;
	}
	public void add(List<String> info) {
		SingleCard singleCard = new SingleCard();
		singleCard.set(info);
		cards.add(singleCard);
	}
	
	
}
