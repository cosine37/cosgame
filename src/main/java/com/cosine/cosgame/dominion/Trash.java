package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Trash {
	
	List<Card> trashedCards;
	
	public Trash() {
		trashedCards = new ArrayList<Card>();
	}
	
	public void add(Card card) {
		trashedCards.add(card);
	}
	
	public List<Card> getTrashedCards(){
		return trashedCards;
	}
	
	public List<Document> toDocument() {
		List<Document> trashDocs = new ArrayList<Document>();
		for (int i=0;i<trashedCards.size();i++) {
			Document doc = new Document();
			doc.append("card", trashedCards.get(i).getName());
			trashDocs.add(doc);
		}
		return trashDocs;
	}
	
	public void setTrashFromDoc(List<Document> trashDocs) {
		trashedCards = new ArrayList<Card>();
		CardFactory factory = new CardFactory();
		for (int i=0;i<trashDocs.size();i++) {
			String cardName = (String)trashDocs.get(i).get("card");
			Card card = factory.createCard(cardName);
			trashedCards.add(card);
		}
	}
}
