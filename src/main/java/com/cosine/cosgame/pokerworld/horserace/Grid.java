package com.cosine.cosgame.pokerworld.horserace;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.pokerworld.PokerCard;

public class Grid {
	int index;
	List<GridItem> gis;
	PokerCard reverseCard;
	boolean revealReversed;
	
	public Grid(int index, PokerCard reverseCard, boolean revealReversed) {
		this.index = index;
		gis = new ArrayList<>();
		this.reverseCard = reverseCard;
		this.revealReversed = revealReversed;
	}
	
	public Grid(int index, PokerCard reverseCard) {
		this(index, reverseCard, false);
	}
	
	public void horseAfter(Horse h) {
		gis.add(h);
	}
	
	public void horseEnter(Horse h) {
		gis.add(0, h);
	}
	
	public void removeHorse(String suit) {
		for (int i=0;i<gis.size();i++) {
			if (gis.get(i) instanceof Horse) {
				Horse h = (Horse) gis.get(i);
				if (h.getSuit().contentEquals(suit)) {
					gis.remove(i);
					break;
				}
			}
		}
	}
	
	public void printGrid() {
		int i;
		String line = "[";
		for (i=0;i<gis.size();i++) {
			if (gis.get(i) instanceof Horse) {
				Horse h = (Horse) gis.get(i);
				line = line + h.getSuit();
			}
		}
		line = line + "]";
		System.out.print(line);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<GridItem> getGis() {
		return gis;
	}
	public void setGis(List<GridItem> gis) {
		this.gis = gis;
	}
	public PokerCard getReverseCard() {
		return reverseCard;
	}
	public void setReverseCard(PokerCard reverseCard) {
		this.reverseCard = reverseCard;
	}
	public boolean isRevealReversed() {
		return revealReversed;
	}
	public void setRevealReversed(boolean revealReversed) {
		this.revealReversed = revealReversed;
	}
}
