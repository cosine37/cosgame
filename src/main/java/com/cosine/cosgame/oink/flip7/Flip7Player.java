package com.cosine.cosgame.oink.flip7;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.flip7.entity.CardEntity;
import com.cosine.cosgame.oink.flip7.entity.Flip7PlayerEntity;

public class Flip7Player {
	int index;
	int score;
	int phase;
	
	String name;
	List<Card> numCards;
	List<Card> addonCards;
	List<Card> specialCards;
	boolean active;
	boolean confirmed;
	
	Flip7 flip7;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("index",index);
		doc.append("score",score);
		doc.append("phase",phase);
		doc.append("name",name);
		List<Document> numCardsDocList = new ArrayList<>();
		for (i=0;i<numCards.size();i++){
			numCardsDocList.add(numCards.get(i).toDocument());
		}
		doc.append("numCards",numCardsDocList);
		List<Document> addonCardsDocList = new ArrayList<>();
		for (i=0;i<addonCards.size();i++){
			addonCardsDocList.add(addonCards.get(i).toDocument());
		}
		doc.append("addonCards",addonCardsDocList);
		List<Document> specialCardsDocList = new ArrayList<>();
		for (i=0;i<specialCards.size();i++){
			specialCardsDocList.add(specialCards.get(i).toDocument());
		}
		doc.append("specialCards",specialCardsDocList);
		doc.append("active",active);
		doc.append("confirmed",confirmed);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		index = doc.getInteger("index",0);
		score = doc.getInteger("score",0);
		phase = doc.getInteger("phase",0);
		name = doc.getString("name");
		List<Document> numCardsDocList = (List<Document>)doc.get("numCards");
		numCards = new ArrayList<>();
		for (i=0;i<numCardsDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(numCardsDocList.get(i));
			numCards.add(e);
		}
		List<Document> addonCardsDocList = (List<Document>)doc.get("addonCards");
		addonCards = new ArrayList<>();
		for (i=0;i<addonCardsDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(addonCardsDocList.get(i));
			addonCards.add(e);
		}
		List<Document> specialCardsDocList = (List<Document>)doc.get("specialCards");
		specialCards = new ArrayList<>();
		for (i=0;i<specialCardsDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(specialCardsDocList.get(i));
			specialCards.add(e);
		}
		active = doc.getBoolean("active",false);
		confirmed = doc.getBoolean("confirmed",false);
	}
	
	public Flip7PlayerEntity toFlip7PlayerEntity(String username){
		int i,j;
		Flip7PlayerEntity entity = new Flip7PlayerEntity();
		entity.setIndex(index);
		entity.setScore(score);
		entity.setName(name);
		List<CardEntity> listOfNumCards = new ArrayList<>();
		for (i=0;i<numCards.size();i++){
			listOfNumCards.add(numCards.get(i).toCardEntity());
		}
		entity.setNumCards(listOfNumCards);
		List<CardEntity> listOfAddonCards = new ArrayList<>();
		for (i=0;i<addonCards.size();i++){
			listOfAddonCards.add(addonCards.get(i).toCardEntity());
		}
		entity.setAddonCards(listOfAddonCards);
		List<CardEntity> listOfSpecialCards = new ArrayList<>();
		for (i=0;i<specialCards.size();i++){
			listOfSpecialCards.add(specialCards.get(i).toCardEntity());
		}
		entity.setSpecialCards(listOfSpecialCards);
		entity.setActive(active);
		entity.setConfirmed(confirmed);
		return entity;
	}
	
	public Flip7Player() {
		numCards = new ArrayList<>();
		addonCards = new ArrayList<>();
		specialCards = new ArrayList<>();
	}
	
	public void startRound() {
		active = true;
		
		discardAll();
		updateScore();
	}
	
	public void endRound() {
		
	}
	
	public boolean explodes() {
		int i,j;
		for (i=0;i<numCards.size();i++) {
			for (j=i+1;j<numCards.size();j++) {
				if (numCards.get(i).getNum() == numCards.get(j).getNum()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int calcCurScore() {
		if (explodes()) {
			return 0;
		}
		
		int ans = 0;
		int i;
		for (i=0;i<numCards.size();i++) {
			ans = ans+numCards.get(i).getNum();
		}
		
		for (i=0;i<addonCards.size();i++) {
			if (addonCards.get(i).getNum() > 190) {
				int x = addonCards.get(i).getNum() - 190;
				ans = ans*x;
			}
		}
		
		for (i=0;i<addonCards.size();i++) {
			if (addonCards.get(i).getNum() < 150) {
				int x = addonCards.get(i).getNum() - 100;
				ans = ans+x;
			}
		}
		
		if (numCards.size() == 7) {
			ans = ans+15;
		}
		
		return ans;
	}
	
	List<String> getOptions(){
		List<String> options = new ArrayList<>();
		boolean f = explodes();
		if (f) {
			options.add("结束");
		} else {
			options.add("结束");
			options.add("继续");
		}
		return options;
	}
	
	public void getACard() {
		Card c = flip7.deal();
		if (c.getNum()<20) {
			numCards.add(c);
		} else if (c.getNum()<200) {
			addonCards.add(c);
		} else if (c.getNum()>200) {
			specialCards.add(c);
		}
	}
	
	public void discardACard(Card c) {
		flip7.getDiscard().add(c);
	}
	
	public void discardAll() {
		while (numCards.size()>0) {
			Card c = numCards.remove(0);
			discardACard(c);
		}
		while (addonCards.size()>0) {
			Card c = addonCards.remove(0);
			discardACard(c);
		}
		while (specialCards.size()>0) {
			Card c = specialCards.remove(0);
			discardACard(c);
		}
	}
	
	public void updateScore() {
		score = score+calcCurScore();
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getNumCards() {
		return numCards;
	}
	public void setNumCards(List<Card> numCards) {
		this.numCards = numCards;
	}
	public List<Card> getAddonCards() {
		return addonCards;
	}
	public void setAddonCards(List<Card> addonCards) {
		this.addonCards = addonCards;
	}
	public List<Card> getSpecialCards() {
		return specialCards;
	}
	public void setSpecialCards(List<Card> specialCards) {
		this.specialCards = specialCards;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Flip7 getFlip7() {
		return flip7;
	}
	public void setFlip7(Flip7 flip7) {
		this.flip7 = flip7;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
}
