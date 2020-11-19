package com.cosine.cosgame.pokewhat;

import org.bson.Document;

public class Pm {
	String name;
	String nickname;
	String img;
	Player player;
	Board board;
	int size;
	
	public Pm() {
		
	}
	
	public Pm(String img, String name, int size) {
		this.img = img;
		this.name = name;
		this.size = size;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("nickname", nickname);
		doc.append("img", img);
		doc.append("size", size);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		nickname = doc.getString("nickname");
		img = doc.getString("img");
		size = doc.getInteger("size", 0);
	}
}
