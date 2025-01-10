package com.cosine.cosgame.gravepsycho;

import org.bson.Document;

public class Card {
	String name;
	String img;
	int type;
	int num;
	
	public Card() {
		
	}
	
	public Card(String img, int type, int num) {
		this();
		this.img = img;
		this.type = type;
		this.num = num;
	}
	
	public String getTreasureName() {
		String ans = "";
		if (type == Consts.TREASURE) {
			if (num == 5) {
				ans = "如意";
			} else if (num == 7) {
				ans = "陶瓷";
			} else if (num == 8) {
				ans = "宝剑";
			} else if (num == 10) {
				ans = "铜鼎";
			} else if (num == 12) {
				ans = "玉玺";
			}
		}
		return ans;
	}
	
	public String getDisasterName() {
		String ans = "";
		if (type == Consts.DISASTER) {
			if (num == 0) {
				ans = "陷阱";
			} else if (num == 1) {
				ans = "崩塌";
			} else if (num == 2) {
				ans = "毒蛇";
			} else if (num == 3) {
				ans = "鬼火";
			} else if (num == 4) {
				ans = "僵尸";
			}
		}
		return ans;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return img;
	}
	public void setImage(String img) {
		this.img = img;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("img", img);
		doc.append("type", type);
		doc.append("num", num);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		img = doc.getString("img");
		type = doc.getInteger("type", 0);
		num = doc.getInteger("num", 0);
	}
}
