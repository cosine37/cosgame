package com.cosine.cosgame.oink.flip7;

import java.util.ArrayList;
import java.util.List;

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
