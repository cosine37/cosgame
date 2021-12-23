package com.cosine.cosgame.threechaodoms.logs;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.CardFactory;
import com.cosine.cosgame.threechaodoms.entity.CardLogEntity;

public class CardLog {
	Card card;
	String info;
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("card", card.toDocument());
		doc.append("info", info);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		Document dc = (Document) doc.get("card");
		card = CardFactory.makeCard(dc);
		info = doc.getString("info");
	}
	
	public CardLogEntity toCardLogEntity() {
		CardLogEntity entity = new CardLogEntity();
		entity.setCard(card.toCardEntity());
		entity.setInfo(info);
		return entity;
	}
	
}
