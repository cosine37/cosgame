package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllRes {
	
	public static List<Card> allBaseCards() {
		int i;
		List<Card> cards = new ArrayList<>();
		List<Card> shuffled = new ArrayList<>();
		
		for (i=0;i<2;i++) cards.add(CardFactory.makeCard(4));
		for (i=0;i<2;i++) cards.add(CardFactory.makeCard(8));
		for (i=0;i<1;i++) cards.add(CardFactory.makeCard(9));
		for (i=0;i<3;i++) cards.add(CardFactory.makeCard(2));
		for (i=0;i<3;i++) cards.add(CardFactory.makeCard(7));
		for (i=0;i<3;i++) cards.add(CardFactory.makeCard(5));
		for (i=0;i<3;i++) cards.add(CardFactory.makeCard(3));
		
		Random rand = new Random();
		while (cards.size()>0) {
			int x = rand.nextInt(cards.size());
			shuffled.add(cards.remove(x));
		}
		
		return shuffled;
	}
	
	public static List<Card> allDeluxeCards(){
		List<Card> cards = allBaseCards();
		List<Card> shuffled = new ArrayList<>();
		
		
		Random rand = new Random();
		while (cards.size()>0) {
			int x = rand.nextInt(cards.size());
			shuffled.add(cards.remove(x));
		}
		
		return shuffled;
	}
}
