package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;

public class Board {
	List<Player> players;
	List<List<Card>> playedCards;
	List<Card> ancient;
	List<Card> deck;
	int status;
	int round;
	int turn;
	int curPlayer;
	AllRes allRes;
	
	public Board(){
		players = new ArrayList<>();
		playedCards = new ArrayList<>();
		allRes = new AllRes();
	}
	
	public void genDeck() {
		
	}
	
	public void removeCards(int x) {
		if (x == 2) {
			
		} else if (x == 3) {
			
		}
	}
	
	public void genAncient() {
		for (int i=0;i<PokewhatConsts.NUMANCIENT;i++) {
			Card c = deck.remove(0);
			ancient.add(c);
		}
	}
	
	public void startGame() {
		int i;
		for (i=0;i<9;i++) {
			List<Card> ls = new ArrayList<>();
			playedCards.add(ls);
		}
		genDeck();
		removeCards(players.size());
		genAncient();
	}
	
	public boolean isRoundEnd() {
		boolean ans = false;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getHp() == 0) {
				ans = true;
			}
		}
		return ans;
	}
	
	public void nextRound() {
		round++;
	}
	
	public void addToPlayedCards(Card c) {
		int index = c.getNum();
		playedCards.get(index).add(c);
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<List<Card>> getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(List<List<Card>> playedCards) {
		this.playedCards = playedCards;
	}
	public List<Card> getAncient() {
		return ancient;
	}
	public void setAncient(List<Card> ancient) {
		this.ancient = ancient;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
}
