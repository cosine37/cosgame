package com.cosine.cosgame.architect;

import java.util.List;

import org.bson.Document;

public class Building {
	List<Integer> price;
	int score;
	String img;
	String name;
	
	public boolean canBuy(Player p) {
		boolean ans = true;
		for (int i=0;i<price.size();i++) {
			int x = p.numRes(i);
			if (x<price.get(i)) {
				ans = false;
				break;
			}
		}
		return ans;
	}

	public List<Integer> getPrice() {
		return price;
	}
	public void setPrice(List<Integer> price) {
		this.price = price;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("price", price);
		doc.append("score", score);
		doc.append("img", img);
		doc.append("name", name);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		price = (List<Integer>) doc.get("price");
		score = doc.getInteger("score", 0);
		img = doc.getString("img");
		name = doc.getString("name");
	}
	
}
