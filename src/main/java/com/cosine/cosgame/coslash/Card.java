package com.cosine.cosgame.coslash;

import org.bson.Document;

public class Card {
	public static final int BASIC = 0;
	public static final int ACTION = 1;
	public static final int EQUIP = 2;
	public static final int DELAYED = 3;
	protected int type;
	protected String name;
	protected String text;
	protected String image;
	
	protected int suit;
	public static final int SPADE = 0;
	public static final int HEART = 1;
	public static final int CLUB = 2;
	public static final int DIAMOND = 3;
	
	protected int rank; // aka point
	
	public Card() {
		
	}
	
	public Card(String name, int suit, int rank) {
		this.name = name;
		this.suit = suit;
		this.rank = rank;
	}
	
	// 使用
	public void play(Player p, Table t) { 
		
	}
	
	// 打出
	public void respond(Player p, Table t) {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("suit", suit);
		doc.append("rank", rank);
		return doc;
	}
	
}
