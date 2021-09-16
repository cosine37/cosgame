package com.cosine.cosgame.architect;

import java.util.List;

public class Board {
	List<Card> cardDeck;
	List<Card> revealedCards;
	List<Building> buildingDeck;
	List<Building> revealedBuildings;
	List<Player> players;
	int num3vp;
	int num1vp;
	int roundCount;
	int firstPlayerIndex;
	int curPlayerIndex;
	int status;
	int numBuildingFinish;
	
	public void playerBuild(Player p, int x) {
		if (x<0 || x>=revealedBuildings.size()) {
			return;
		} else {
			if (revealedBuildings.get(x).canBuy(p)) {
				Building b = revealedBuildings.remove(x);
				p.pay(b);
				if (x == 0 && num3vp>0) {
					num3vp--;
					p.add3vp();
				} else if (x == 1 && num1vp>0) {
					num1vp--;
					p.add1vp();
				}
				if (p.getBuildings().size() >= numBuildingFinish) {
					status = Consts.LASTROUND;
				}
			}
		}
	}
	
	public void playerHire(Player p, int x, List<Integer> resources) {
		if (x == resources.size() && x>=0 && x<revealedCards.size()) {
			int i,j;
			for (i=0;i<x;i++) {
				Card c = revealedCards.get(i);
				for (j=0;j<resources.size();j++) {
					int y = resources.get(j);
					int res = p.getWarehouse().get(y);
					c.addResOn(res);
				}
			}
			Card c = revealedCards.get(x);
			for (i=0;i<c.getResOn().size();i++) {
				p.addRes(c.getResOn().get(i));
			}
		}
	}
	
	public void endGame() {
		status = Consts.ENDGAME;
	}
	
	public void nextPlayer() {
		Player p1 = players.get(curPlayerIndex);
		p1.setPhase(Consts.OFFTURN);
		curPlayerIndex++;
		if (curPlayerIndex>players.size()) {
			curPlayerIndex = curPlayerIndex % players.size();
		}
		if ((curPlayerIndex == firstPlayerIndex) && (status == Consts.LASTROUND)) {
			endGame();
		} else {
			Player p2 = players.get(curPlayerIndex);
			p2.setPhase(Consts.INTURN);
		}
	}
	
	public List<Card> getCardDeck() {
		return cardDeck;
	}
	public void setCardDeck(List<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}
	public List<Card> getRevealedCards() {
		return revealedCards;
	}
	public void setRevealedCards(List<Card> revealedCards) {
		this.revealedCards = revealedCards;
	}
	public List<Building> getBuildingDeck() {
		return buildingDeck;
	}
	public void setBuildingDeck(List<Building> buildingDeck) {
		this.buildingDeck = buildingDeck;
	}
	public List<Building> getRevealedBuildings() {
		return revealedBuildings;
	}
	public void setRevealedBuildings(List<Building> revealedBuildings) {
		this.revealedBuildings = revealedBuildings;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getNum3vp() {
		return num3vp;
	}
	public void setNum3vp(int num3vp) {
		this.num3vp = num3vp;
	}
	public int getNum1vp() {
		return num1vp;
	}
	public void setNum1vp(int num1vp) {
		this.num1vp = num1vp;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	public int getFirstPlayerIndex() {
		return firstPlayerIndex;
	}
	public void setFirstPlayerIndex(int firstPlayerIndex) {
		this.firstPlayerIndex = firstPlayerIndex;
	}
	public int getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(int curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNumBuildingFinish() {
		return numBuildingFinish;
	}
	public void setNumBuildingFinish(int numBuildingFinish) {
		this.numBuildingFinish = numBuildingFinish;
	}
}
