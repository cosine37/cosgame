package com.cosine.cosgame.pokewhat;

import java.util.List;

import org.bson.Document;

public class Frame {
	List<Integer> targets;
	int type;
	int time;
	String img;
	
	public List<Integer> getTargets() {
		return targets;
	}
	public void setTargets(List<Integer> targets) {
		this.targets = targets;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		doc.append("time", time);
		doc.append("img", img);
		doc.append("targets", targets);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		type = doc.getInteger("type", 0);
		time = doc.getInteger("time", 0);
		img = doc.getString("img");
		targets = (List<Integer>) doc.get("targets");
	}
}
