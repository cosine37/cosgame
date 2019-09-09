package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class GainedCard {
	List<String> cardnames;
	List<String> cardnamesRaw;
	List<String> buycardnamesRaw;
	public GainedCard() {
		cardnames = new ArrayList<>();
		cardnamesRaw = new ArrayList<>();
		buycardnamesRaw = new ArrayList<>();
	}
	
	public void add(String cardname) {
		cardnamesRaw.add(cardname);
		for (int i=0;i<cardnames.size();i++) {
			if (cardnames.get(i).equals(cardname)) {
				return;
			}
		}
		cardnames.add(cardname);
	}
	
	public void addBuy(String cardname) {
		buycardnamesRaw.add(cardname);
	}
	
	public boolean hasVictory() {
		CardFactory factory = new CardFactory();
		for (int i=0;i<cardnames.size();i++) {
			Card card = factory.createCard(cardnames.get(i));
			if (card!=null) {
				if (card.isVictory()) return true;
			}
		}
		return false;
	}
	
	public boolean hasBoughtVictory() {
		CardFactory factory = new CardFactory();
		for (int i=0;i<buycardnamesRaw.size();i++) {
			Card card = factory.createCard(cardnames.get(i));
			if (card!=null) {
				if (card.isVictory()) return true;
			}
		}
		return false;
	}
	
	public List<String> getCardNames(){
		return cardnames;
	}
	
	public List<Document> toDocument() {
		List<Document> lst = new ArrayList<>();
		for (int i=0;i<cardnamesRaw.size();i++) {
			Document d = new Document();
			d.append("c", cardnamesRaw.get(i));
			lst.add(d);
		}
		return lst;
	}
	
	public List<Document> toDocumentBuy(){
		List<Document> lst = new ArrayList<>();
		for (int i=0;i<buycardnamesRaw.size();i++) {
			Document d = new Document();
			d.append("c", buycardnamesRaw.get(i));
			lst.add(d);
		}
		return lst;
	}
	
	public void setFromDoc(List<Document> lst) {
		cardnames = new ArrayList<>();
		cardnamesRaw = new ArrayList<>();
		for (int i=0;i<lst.size();i++) {
			String cardname = lst.get(i).getString("c");
			add(cardname);
		}
	}
	
	public void setBuyFromDoc(List<Document> lst) {
		buycardnamesRaw = new ArrayList<>();
		for (int i=0;i<lst.size();i++) {
			String cardname = lst.get(i).getString("c");
			buycardnamesRaw.add(cardname);
		}
	}
}
