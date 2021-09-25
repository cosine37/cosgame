package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.architect.entity.BuildingEntity;

public class Building {
	List<Integer> price;
	int score;
	String img;
	String name;
	
	public Building() {
		price = new ArrayList<>();
		for (int i=0;i<4;i++) {
			price.add(0);
		}
	}
	
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
	
	public void setPrice(int w, int s, int i, int g) {
		price.set(Consts.WOOD, w);
		price.set(Consts.STONE, s);
		price.set(Consts.IRON, i);
		price.set(Consts.GOLD, g);
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
	
	public BuildingEntity toBuildingEntity() {
		BuildingEntity entity = new BuildingEntity();
		entity.setName(name);
		entity.setImg(img);
		List<String> lp = new ArrayList<>();
		for (int i=0;i<price.size();i++) {
			lp.add(Integer.toString(price.get(i)));
		}
		entity.setPrice(lp);
		entity.setScore(Integer.toString(score));
		return entity;
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
