package com.cosine.cosgame.pokerworld.horserace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cosine.cosgame.pokerworld.Consts;
import com.cosine.cosgame.pokerworld.PokerCard;
import com.cosine.cosgame.pokerworld.PokerUtil;

public class GameMap {
	int raceLength;
	int curGambler;
	
	List<Grid> grids;
	Map<String, Horse> horses;
	List<PokerCard> revealedCards;
	List<PokerCard> deck;
	List<Gambler> gamblers;
	
	public GameMap() {
		grids = new ArrayList<>();
		horses = new HashMap<>();
		revealedCards = new ArrayList<>();
		deck = new ArrayList<>();
		gamblers = new ArrayList<>();
	}
	
	public void initializeMap(int raceLength) {
		this.raceLength = raceLength;
		deck = PokerUtil.shuffle(PokerUtil.getStandardDeck());
		
		int i;
		for (i=0;i<=raceLength;i++) {
			grids.add(new Grid(i, deck.remove(0)));
		}
		
		Horse dhorse = new Horse(Consts.DIAMOND);
		Horse chorse = new Horse(Consts.CLUB);
		Horse hhorse = new Horse(Consts.HEART);
		Horse shorse = new Horse(Consts.SPADE);
		
		horses.put(Consts.DIAMOND, dhorse);
		horses.put(Consts.CLUB, chorse);
		horses.put(Consts.HEART, hhorse);
		horses.put(Consts.SPADE, shorse);
		
		grids.get(0).horseEnter(dhorse);
		grids.get(0).horseEnter(chorse);
		grids.get(0).horseEnter(hhorse);
		grids.get(0).horseEnter(shorse);
	}
	
	public void moveHorse(String suit, int steps) {
		if (horses.containsKey(suit)) {
			Horse h = horses.get(suit);
			int x = h.getIndex() + steps;
			if (x>raceLength) x = raceLength;
			grids.get(h.getIndex()).removeHorse(suit);
			h.setIndex(x);
			if (steps>0) {
				grids.get(x).horseEnter(h);
			} else {
				grids.get(x).horseAfter(h);
			}
		}
	}
	
	public List<String> currentRank(){
		int i,j;
		List<String> ranks = new ArrayList<>();
		for (i=raceLength;i>=0;i--) {
			for (j=0;j<grids.get(i).getGis().size();j++) {
				if (grids.get(i).getGis().get(j) instanceof Horse) {
					Horse h = (Horse) grids.get(i).getGis().get(j);
					ranks.add(h.getSuit());
				}
			}
		}
		return ranks;
	}
	
	public void nextGambler() {
		curGambler++;
		if (curGambler>=gamblers.size()) {
			curGambler = curGambler % gamblers.size();
		}
	}
	
	public void printMap() {
		int i,j;
		for (i=0;i<=raceLength;i++) {
			grids.get(i).printGrid();
		}
		System.out.println();
	}
	
	public static void main(String args[]) {
		GameMap gm = new GameMap();
		gm.initializeMap(5);
		gm.moveHorse(Consts.SPADE, 2);
		gm.printMap();
		System.out.println(gm.currentRank());
		gm.moveHorse(Consts.CLUB, 2);
		gm.printMap();
		System.out.println(gm.currentRank());
		gm.moveHorse(Consts.DIAMOND, 1);
		gm.printMap();
		System.out.println(gm.currentRank());
		gm.moveHorse(Consts.SPADE, -1);
		gm.printMap();
		System.out.println(gm.currentRank());
	}
}
