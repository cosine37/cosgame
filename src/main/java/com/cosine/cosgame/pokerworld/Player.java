package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.play.Hand;
import com.cosine.cosgame.pokerworld.entity.PlayerEntity;

public class Player {
	String name;
	int phase;
	int innerId;
	boolean confirmedClaim;
	boolean confirmedNextTurn;
	Board board;
	
	List<Integer> playedIndex;
	String playedCardsStr;
	
	public Player() {
		playedIndex = new ArrayList<>();
		playedCardsStr = "";
	}
	
	public List<Card> getMyCards(){
		List<Card> cards = board.getGame().getPlayerCards().get(innerId);
		return cards;
	}
	
	public String getMyRawCards() {
		return board.getGameUtil().playerRawCards(innerId);
	}
	
	public String getMyRawCardsAfterPlay() {
		String myRawCards = board.getGameUtil().playerRawCards(innerId);
		int i,j;
		int n = myRawCards.length() / 2;
		String s = "";
		for (i=0;i<n;i++) {
			boolean f = true;
			for (j=0;j<playedIndex.size();j++) {
				if (playedIndex.get(j) == i) {
					f = false;
					break;
				}
			}
			if (f) {
				s = s+myRawCards.substring(i*2, i*2+2);
			}
		}
		return s;
	}
	
	public void play(List<Integer> playedIndex) {
		int numPlayed = board.getNumPlayed();
		if (numPlayed == -1) {
			setPlayedIndex(playedIndex);
			genPlayedCardsStr();
			board.setNumPlayed(playedIndex.size());
		} else if (numPlayed == playedIndex.size()){
			setPlayedIndex(playedIndex);
			genPlayedCardsStr();
		}
		board.nextPlayerPlay();
	}
	
	public void genPlayedCardsStr() {
		playedCardsStr = "";
		String myRawCards = getMyRawCards();
		int i;
		for (i=0;i<playedIndex.size();i++) {
			int x = playedIndex.get(i);
			playedCardsStr = playedCardsStr + myRawCards.substring(x*2, x*2+2);
		}
	}
	
	public void emptyPlayedIndex() {
		playedIndex = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getInnerId() {
		return innerId;
	}
	public void setInnerId(int innerId) {
		this.innerId = innerId;
	}
	public List<Integer> getPlayedIndex() {
		return playedIndex;
	}
	public void setPlayedIndex(List<Integer> playedIndex) {
		this.playedIndex = playedIndex;
	}
	public boolean isConfirmedClaim() {
		return confirmedClaim;
	}
	public void setConfirmedClaim(boolean confirmedClaim) {
		this.confirmedClaim = confirmedClaim;
	}
	public String getPlayedCardsStr() {
		return playedCardsStr;
	}
	public void setPlayedCardsStr(String playedCardsStr) {
		this.playedCardsStr = playedCardsStr;
	}
	public boolean isConfirmedNextTurn() {
		return confirmedNextTurn;
	}
	public void setConfirmedNextTurn(boolean confirmedNextTurn) {
		this.confirmedNextTurn = confirmedNextTurn;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("innerId", innerId);
		doc.append("playedIndex", playedIndex);
		doc.append("confirmedClaim", confirmedClaim);
		doc.append("confirmedNextTurn", confirmedNextTurn);
		doc.append("playedCardsStr", playedCardsStr);
		return doc;
	}
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		innerId = doc.getInteger("innerId", -1);
		playedIndex = (List<Integer>) doc.get("playedIndex");
		confirmedClaim = doc.getBoolean("confirmedClaim", false);
		confirmedNextTurn = doc.getBoolean("confirmedNextTurn", false);
		playedCardsStr = doc.getString("playedCardsStr");
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		/*
		int i;
		String playedCards = "";
		String myRawCards = getMyRawCards();
		for (i=0;i<playedIndex.size();i++) {
			int x = playedIndex.get(i);
			playedCards = playedCards + myRawCards.substring(x*2, x*2+2);
		}
		entity.setPlayedCards(playedCards);
		*/
		entity.setConfirmedNextTurn(confirmedNextTurn);
		entity.setPlayedCards(playedCardsStr);
		return entity;
	}
	
}
