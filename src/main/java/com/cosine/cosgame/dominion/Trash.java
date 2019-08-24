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
	
	public void gainToPlayer(Player p, String cardName) {
		for (int i=trashedCards.size()-1; i>=0; i--) {
			if (trashedCards.get(i).getName().equals(cardName)) {
				Card card = trashedCards.remove(i);
				card.setBoard(p.getBoard());
				p.putOnDiscard(card);
				card.onGain(p);
				break;
			}
		}
	}
	
	public void gainToPlayerHand(Player p, String cardName) {
		for (int i=trashedCards.size()-1; i>=0; i--) {
			if (trashedCards.get(i).getName().equals(cardName)) {
				Card card = trashedCards.remove(i);
				card.setBoard(p.getBoard());
				p.getHand().add(card);
				card.onGain(p);
				break;
			}
		}
	}
	
	public List<Card> getTrashedCards(){
		return trashedCards;
	}
	
	public List<Pile> getTrashedCardsAsPiles(){
		PileGen pileGen = new PileGen();
		pileGen.add(trashedCards);
		List<Pile> trashedPiles = pileGen.getPiles();
		return trashedPiles;
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
