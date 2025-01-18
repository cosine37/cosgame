package com.cosine.cosgame.oink.west;

import org.bson.Document;

public class Card {
	int num;
	String name;
	String desc;
	
	public Document toDocument(){
		Document doc = new Document();
		doc.append("num",num);
		doc.append("name",name);
		doc.append("desc",desc);
		return doc;
	}
	public void setFromDoc(Document doc){
		num = doc.getInteger("num",0);
		name = doc.getString("name");
		desc = doc.getString("desc");
	}
	
	public Card() {
		this.num = 0;
		this.name = "";
		this.desc = "";
	}
	
	public Card(int num, String name, String desc) {
		this.num = num;
		this.name = name;
		this.desc = desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
