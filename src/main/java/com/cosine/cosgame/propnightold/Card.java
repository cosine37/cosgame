package com.cosine.cosgame.propnightold;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Card {
	int id;
	String name;
	List<Integer> extraBits;
	
	public Card() {
		extraBits = new ArrayList<>();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Integer> getExtraBits() {
		return extraBits;
	}
	public void setExtraBits(List<Integer> extraBits) {
		this.extraBits = extraBits;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("extraBits", extraBits);
		return doc;
	}
	
}
