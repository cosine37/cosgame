package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.gardenwar.baseold.*;
import com.cosine.cosgame.gardenwar.basic.*;
import com.cosine.cosgame.gardenwar.generic.*;

public class AllRes {
	public List<Card> baseCards;
	public List<Card> cardsPre650;
	
	public AllRes() {
		//buildBaseCards();
		build650Cards();
	}
	
	public static Card getBasic(int x) {
		Card c = null;
		if (x == 2) {
			c = new PuffShroom();
		} else if (x == 3) {
			c = new SunShroom();
		} else if (x == 0) {
			c = new PeaShooter();
		} else if (x == 1) {
			c = new Sunflower();
		}
		return c;
	}
	
	public void build650Cards() {
		int i;
		cardsPre650 = new ArrayList<>();
		for (i=0;i<3;i++) {
			cardsPre650.add(new Chime());
		}
		for (i=0;i<3;i++) {
			cardsPre650.add(new DaggerAxe());
		}
		for (i=0;i<3;i++) {
			cardsPre650.add(new Sacrifice());
		}
	}
	
	public void buildBaseCards() {
		int i;
		baseCards = new ArrayList<>();
		for (i=0;i<3;i++) {
			baseCards.add(new Repeater());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new TwinSunflower());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new MagnetShroom());
		}
		for (i=0;i<4;i++) {
			baseCards.add(new Cactus());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new Starfruit());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new FumeShroom());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new GloomShroom());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new GoldMagnet());
		}
		for (i=0;i<4;i++) {
			baseCards.add(new CabbagePult());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new MelonPult());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new Gravebuster());
		}
		for (i=0;i<4;i++) {
			baseCards.add(new Spikeweed());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new Spikerock());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new Marigold());
		}
		for (i=0;i<5;i++) {
			baseCards.add(new ScaredyShroom());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new Threepeater());
		}
		for (i=0;i<4;i++) {
			baseCards.add(new FlowerPot());
		}
		for (i=0;i<2;i++) {
			baseCards.add(new GatlingPea());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new Garlic());
		}
		for (i=0;i<3;i++) {
			baseCards.add(new CoffeeBean());
		}
	}
	
	public List<Card> getSupplyDeck() {
		int i;
		List<Card> supplyDeck = new ArrayList<>();
		List<Card> tempCards = new ArrayList<>();
		
		for (i=0;i<cardsPre650.size();i++) {
			tempCards.add(cardsPre650.get(i));
		}
		
		while(tempCards.size() > 0) {
			Random rand = new Random();
			int x = rand.nextInt(tempCards.size());
			Card c = tempCards.remove(x);
			supplyDeck.add(c);
		}
		
		return supplyDeck;
	}
}
