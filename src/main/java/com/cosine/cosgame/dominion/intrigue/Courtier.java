package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.CardFactory;

public class Courtier extends Card{
	public Courtier() {
		super();
		this.name = "Courtier";
		this.image = "/image/Dominion/cards/Intrigue/Courtier.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.HANDCHOOSE);
		ask.setUpper(1);
		ask.setLower(1);
		ask.setMsg("Choose a card");
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask;
		List<String> choices = new ArrayList<String>();
		choices.add("+1 Action");
		choices.add("+1 Buy");
		choices.add("+ $3");
		choices.add("Gain a Gold");
		if (a.getResLevel() == 0) {
			String cardname = a.getSelectedCards().get(0);
			CardFactory factory = new CardFactory();
			Card card = factory.createCard(cardname);
			int numTypes = card.numTypes();
			
			String s = player.getName() + " reveals " + cardname + " (with "+ numTypes;
			if (numTypes == 1) {
				s = s+" type)";
			} else {
				s = s+" types)";
			}
			log(player.getName() + " reveals " + cardname + " (with "+ numTypes + " types)",1);
			ask = new Ask();
			ask.setCardName(name);
			ask.setType(Ask.OPTION);
			ask.setMsg("Choose one:");
			ask.setAns(-1);
			ask.setResLevel(1);
			if (numTypes == 1) {
				ask.setOptions(choices);
			} else if (numTypes == 2) {
				int i,j;
				List<String> options = new ArrayList<String>();
				for (i=0;i<choices.size();i++) {
					for (j=i+1;j<choices.size();j++) {
						options.add(choices.get(i)+","+choices.get(j));
					}
				}
				ask.setOptions(options);
			} else if (numTypes == 3) {
				int i,j,k;
				List<String> options = new ArrayList<String>();
				for (i=0;i<choices.size();i++) {
					for (j=i+1;j<choices.size();j++) {
						for (k=j+1;k<choices.size();k++) {
							options.add(choices.get(i)+","+choices.get(j)+","+choices.get(k));
						}	
					}
				}
				ask.setOptions(options);
			} else if (numTypes >= 4) {
				player.addAction(1);
				player.addBuy(1);
				player.addCoin(1);
				board.gainToPlayerFromPile(player, board.getPileByTop("Gold"));
				log(player.getName() + " gets +1 Action, +1 Buy, + $3",1);
				log(player.getName() + " gains a Gold",1);
				ask = new Ask();
			} else {
				ask = new Ask();
			}
		} else if (a.getResLevel() == 1) {
			List<String> bonuses = Arrays.asList(a.getOptions().get(a.getAns()).split(","));
			boolean flag = false; 
			String s = player.getName() + " gets";
			for (int i=0;i<bonuses.size();i++) {
				if (bonuses.get(i).equals(choices.get(0))) {
					player.addAction(1);
					s = s + " +1 Action";
					flag = true;
				} else if (bonuses.get(i).equals(choices.get(1))) {
					player.addBuy(1);
					if (flag) s = s+",";
					s = s + " +1 Buy";
					flag = true;
				} else if (bonuses.get(i).equals(choices.get(2))) {
					player.addCoin(3);
					if (flag) s = s+",";
					s = s + " + $3";
					flag = true;
				} else if (bonuses.get(i).equals(choices.get(3))) {
					board.gainToPlayerFromPile(player, board.getPileByTop("Gold"));
					if (flag) {
						log(s,1);
						flag = false;
					}
					log(player.getName() + " gains a Gold",1);
				}
			} 
			if (flag) {
				log(s,1);
			}
			ask = new Ask();
		} else {
			ask = new Ask();
		}
		return ask;
		
	}
}
