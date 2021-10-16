package com.cosine.cosgame.pokerworld;

import java.util.List;

import org.bson.Document;

import com.cosgame.sfsj.play.Hand;

public class Player {
	String name;
	int phase;
	Hand hand;
	Hand playArea;
	Board board;
	
	public Player() {
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public Hand getPlayArea() {
		return playArea;
	}
	public void setPlayArea(Hand playArea) {
		this.playArea = playArea;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		return doc;
	}
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
	}
	
}
