package com.cosine.cosgame.gravepsycho;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gravepsycho.event.*;

public class AllRes {
	List<Card> deck;
	List<Card> treasures;
	List<Event> events;
	
	public AllRes() {
		deck = new ArrayList<>();
		treasures = new ArrayList<>();
		genDeck();
		genTreasures();
	}
	
	public void genDeck() {
		Card c;
		c = new Card("c01", Consts.COIN, 1);
		deck.add(c);
		c = new Card("c02", Consts.COIN, 2);
		deck.add(c);
		c = new Card("c03", Consts.COIN, 3);
		deck.add(c);
		c = new Card("c04", Consts.COIN, 4);
		deck.add(c);
		c = new Card("c05", Consts.COIN, 5);
		deck.add(c);
		c = new Card("c05", Consts.COIN, 5);
		deck.add(c);
		c = new Card("c07", Consts.COIN, 7);
		deck.add(c);
		c = new Card("c07", Consts.COIN, 7);
		deck.add(c);
		c = new Card("c09", Consts.COIN, 9);
		deck.add(c);
		c = new Card("c11", Consts.COIN, 11);
		deck.add(c);
		c = new Card("c11", Consts.COIN, 11);
		deck.add(c);
		c = new Card("c13", Consts.COIN, 13);
		deck.add(c);
		c = new Card("c14", Consts.COIN, 14);
		deck.add(c);
		c = new Card("c15", Consts.COIN, 15);
		deck.add(c);
		c = new Card("c17", Consts.COIN, 17);
		deck.add(c);
		int i,j;
		for (i=0;i<5;i++) {
			for (j=0;j<3;j++) {
				String img = "d0"+i;
				c = new Card(img, Consts.DISASTER, i);
				deck.add(c);
			}
		}
		
	}
	
	public void genTreasures() {
		Card c;
		c = new Card("t05", Consts.TREASURE, 5);
		treasures.add(c);
		c = new Card("t07", Consts.TREASURE, 7);
		treasures.add(c);
		c = new Card("t08", Consts.TREASURE, 8);
		treasures.add(c);
		c = new Card("t10", Consts.TREASURE, 10);
		treasures.add(c);
		c = new Card("t12", Consts.TREASURE, 12);
		treasures.add(c);
	}
	
	public void genEvents() {
		Event e;
		e = new Classic();
		events.add(e);
		e = new Windfall();
		events.add(e);
		e = new Relic();
		events.add(e);
		e = new Danger();
		events.add(e);
		e = new Disappear();
		events.add(e);
		e = new Pride();
		events.add(e);
		e = new Panic();
		events.add(e);
		e = new Focus();
		events.add(e);
		e = new WealOrWoe();
		events.add(e);
		e = new Search();
		events.add(e);
		e = new Rain();
		events.add(e);
		e = new Impatient();
		events.add(e);
		e = new Echo();
		events.add(e);
		e = new Guard();
		events.add(e);
		e = new Valuable();
		events.add(e);
		e = new Certify();
		events.add(e);
	}
	
	public void genThiefEvents() {
		Event e;
		e = new Raider();
		events.add(e);
		e = new Dodge();
		events.add(e);
		e = new Pilfer();
		events.add(e);
		e = new Greed();
		events.add(e);
	}
	
	public List<EventEntity> genEventsAsEntity(){
		events = new ArrayList<>();
		genEvents();
		List<EventEntity> ees = new ArrayList<>();
		for (int i=0;i<events.size();i++) {
			ees.add(events.get(i).toEventEntity());
		}
		return ees;
	}
	
	public List<EventEntity> genThiefEventsAsEntity(){
		events = new ArrayList<>();
		genThiefEvents();
		List<EventEntity> ees = new ArrayList<>();
		for (int i=0;i<events.size();i++) {
			ees.add(events.get(i).toEventEntity());
		}
		return ees;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public List<Card> getTreasures() {
		return treasures;
	}
	
	public Event getRandomEvent(boolean useThief) {
		events = new ArrayList<>();
		genEvents();
		if (useThief) {
			genThiefEvents();
		}
		
		int n = events.size()*1000;
		
		Random rand = new Random();
		int x = rand.nextInt(n) % events.size();
		Event e = events.get(x);
		// TODO: test events here
		//e = new Pilfer();
		return e;
	}
	
}
