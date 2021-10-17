package com.cosine.cosgame.pokerworld;

import java.util.List;

import org.bson.Document;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.play.Hand;
import com.cosine.cosgame.pokerworld.entity.PlayerEntity;

public class Player {
	String name;
	int phase;
	int innerId;
	Board board;
	
	public Player() {
		
	}
	
	public List<Card> getMyCards(){
		List<Card> cards = board.getGame().getPlayerCards().get(innerId);
		return cards;
	}
	
	public String getMyRawCards() {
		return board.getGameUtil().playerRawCards(innerId);
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
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("innerId", innerId);
		return doc;
	}
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		innerId = doc.getInteger("innerId", -1);
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		return entity;
	}
	
}
