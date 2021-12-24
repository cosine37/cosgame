package com.cosine.cosgame.threechaodoms;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.entity.SkinEntity;
import com.cosine.cosgame.threechaodoms.shop.Transaction;

public class Skin {
	int id;
	int index;
	String title;
	String skinName;
	String originalImg;
	String newImg;
	List<String> quotes;
	boolean inUse;
	
	Transaction price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOriginalImg() {
		return originalImg;
	}
	public void setOriginalImg(String originalImg) {
		this.originalImg = originalImg;
	}
	public String getNewImg() {
		return newImg;
	}
	public void setNewImg(String newImg) {
		this.newImg = newImg;
	}
	public List<String> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<String> quotes) {
		this.quotes = quotes;
	}
	public boolean isInUse() {
		return inUse;
	}
	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSkinName() {
		return skinName;
	}
	public void setSkinName(String skinName) {
		this.skinName = skinName;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Transaction getPrice() {
		return price;
	}
	public void setPrice(Transaction price) {
		this.price = price;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("inUse", inUse);
		return doc;
	}
	
	public SkinEntity toSkinEntity() {
		SkinEntity entity = new SkinEntity();
		entity.setId(id);
		entity.setInUse(inUse);
		return entity;
	}
}
