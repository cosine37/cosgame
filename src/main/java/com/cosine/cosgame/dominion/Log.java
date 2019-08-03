package com.cosine.cosgame.dominion;

import org.bson.Document;

public class Log {
	String msg;
	int level;
	
	public Log() {
		
	}
	
	public boolean startWithVowel(String name) {
		if (name.startsWith("A")) return true;
		if (name.startsWith("E")) return true;
		if (name.startsWith("I")) return true;
		if (name.startsWith("O")) return true;
		if (name.startsWith("U")) return true;
		return false;
	}
	
	public Log(String msg, int level) {
		this.msg = msg;
		this.level = level;
	}
	
	public void play(String name, String cardName) {
		msg = name + " plays " + cardName;
		level = 0;
	}
	
	public void buy(String name, String cardName) {
		msg = name + " buys " + cardName;
		level = 0;
	}
	
	public void gain(String name, String cardName) {
		msg = name + " gains " + cardName;
		level = 0;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("msg", msg);
		doc.append("level", level);
		return doc;
	}
	
	public void setValue(String msg, int level) {
		this.msg = msg;
		this.level = level;
	}
	
	public void setValue(Document doc) {
		msg = (String)doc.get("msg");
		level = (int)doc.get("level");
	}
	
	public String getMsg() {
		return msg;
	}
	public int getLevel() {
		return level;
	}

}
