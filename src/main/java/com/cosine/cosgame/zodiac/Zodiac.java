package com.cosine.cosgame.zodiac;

import org.bson.Document;

public class Zodiac {
	int num;
	String name;
	String img;
	Board board;
	boolean isReal;
	boolean reveal;
	boolean keep;
	boolean stolen;
	int votes;
	
	public Zodiac() {
		votes = 0;
		isReal = true;
		reveal = false;
		keep = false;
		stolen = false;
	}
	
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isReal() {
		return isReal;
	}
	public void setReal(boolean isReal) {
		this.isReal = isReal;
	}
	public boolean isReveal() {
		return reveal;
	}
	public void setReveal(boolean reveal) {
		this.reveal = reveal;
	}
	public boolean isKeep() {
		return keep;
	}
	public void setKeep(boolean keep) {
		this.keep = keep;
	}
	public boolean isStolen() {
		return stolen;
	}
	public void setStolen(boolean stolen) {
		this.stolen = stolen;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("num", num);
		doc.append("name", name);
		doc.append("img", img);
		doc.append("isReal", isReal);
		doc.append("reveal", reveal);
		doc.append("keep", keep);
		doc.append("stolen", stolen);
		doc.append("votes", votes);
		return doc;
	}
	public void setFromDoc(Document doc) {
		num = doc.getInteger("num", -1);
		name = doc.getString("name");
		img = doc.getString("img");
		isReal = doc.getBoolean("isReal", true);
		reveal = doc.getBoolean("reveal", false);
		keep = doc.getBoolean("keep", false);
		stolen = doc.getBoolean("stolen", false);
		votes = doc.getInteger("votes", 0);
	}
}
