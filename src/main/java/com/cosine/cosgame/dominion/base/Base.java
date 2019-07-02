package com.cosine.cosgame.dominion.base;

import java.util.ArrayList;

import com.cosine.cosgame.dominion.Expansion;
import com.cosine.cosgame.dominion.Pile;

public class Base extends Expansion{
	Pile copperPile;
	Pile silverPile;
	Pile goldPile;
	Pile estatePile;
	Pile duchyPile;
	Pile provincePile;
	Pile cursePile;
	
	public Base() {
		super();

		copperPile = new Pile(Copper.class, 60);
		silverPile = new Pile(Silver.class, 40);
		goldPile = new Pile(Gold.class, 30);
		estatePile = new Pile(Estate.class, 24);
		duchyPile = new Pile(Duchy.class, 12);
		provincePile = new Pile(Province.class, 12);
		cursePile = new Pile(Curse.class, 30);
		
		piles.add(provincePile);
		piles.add(goldPile);
		piles.add(duchyPile);
		piles.add(silverPile);
		piles.add(estatePile);
		piles.add(copperPile);
		piles.add(cursePile);
		
		numPiles = 7;
	}
	
	public void sort(int x){
		if (x==0) {
			return;
		}
		piles = new ArrayList<Pile>();
		
	}
}
