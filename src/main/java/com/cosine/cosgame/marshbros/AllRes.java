package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.marshbros.official.*;

public class AllRes {
	List<Card> officialCards;
	
	public AllRes() {
		setOfficialCards();
	}
	
	public List<Card> genDeck() {
		return officialCards;
	}
	
	public void setOfficialCards() {
		officialCards = new ArrayList<>();
		//officialCards.add(new XieZhen());
		for (int i=0;i<100;i++) {
			officialCards.add(new XieZhen());
		}
	}
}
