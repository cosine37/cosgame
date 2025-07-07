package com.cosine.cosgame.oink.flip7;

import org.bson.Document;

import com.cosine.cosgame.oink.flip7.entity.CardEntity;

public class Card {
	int num;
	String name;
	String desc;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("num", num);
		doc.append("name", name);
		doc.append("desc", desc);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		num = doc.getInteger("num", 0);
		name = doc.getString("name");
		desc = doc.getString("desc");
	}
	
	public CardEntity toCardEntity() {
		CardEntity entity = new CardEntity();
		entity.setNum(num);
		entity.setName(name);
		entity.setDesc(desc);
		return entity;
	}
	
	
	public Card() {
		num = -1;
		desc = "";
	}
	
	public Card(int num, String name) {
		this();
		this.num = num;
		this.name = name;
	}
	
	public Card(int num, String name, String desc) {
		this.num = num;
		this.desc = desc;
		this.name = name;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
