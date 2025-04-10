package com.cosine.cosgame.oink.west;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.cosine.cosgame.oink.west.entity.CardEntity;

public class Card {
	int num;
	String name;
	String desc;
	
	West west;
	
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
	public CardEntity toCardEntity(String username, int flag){
		int i,j;
		CardEntity entity = new CardEntity();
		entity.setNum(num);
		entity.setName(name);
		entity.setDesc(desc);
		entity.setImg(getImg(flag));
		
		Map<String, String> avatarStyle = new HashMap<>();
		avatarStyle.put("background-image", "url(/image/Oink/West/" + getImg(flag) + ".png)");
		avatarStyle.put("background-size", "cover");
		
		Map<String, String> winStyle = new HashMap<>();
		winStyle.put("background-image", "url(/image/Oink/West/" + getImg(flag) + "w.png)");
		winStyle.put("background-size", "cover");
		
		Map<String, String> loseStyle = new HashMap<>();
		loseStyle.put("background-image", "url(/image/Oink/West/" + getImg(flag) + "l.png)");
		loseStyle.put("background-size", "cover");
		
		entity.setAvatarStyle(avatarStyle);
		entity.setWinStyle(winStyle);
		entity.setLoseStyle(loseStyle);
		
		return entity;
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
	
	public String getImg(int flag) {
		String ans = Integer.toString(num);
		if (flag == -1) {
			ans = ans+"l";
		} else if (flag == 1) {
			ans = ans+"w";
		}
		return ans;
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
	public West getWest() {
		return west;
	}
	public void setWest(West west) {
		this.west = west;
	}
	
	
}
