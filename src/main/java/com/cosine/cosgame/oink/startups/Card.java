package com.cosine.cosgame.oink.startups;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.cosine.cosgame.oink.startups.entity.CardEntity;

public class Card {
	int num;
	int coinOn;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("num", num);
		doc.append("coinOn", coinOn);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		num = doc.getInteger("num", 0);
		coinOn = doc.getInteger("coinOn", 0);
	}
	
	public CardEntity toCardEntity() {
		CardEntity entity = new CardEntity();
		entity.setNum(num);
		entity.setCoinOn(coinOn);
		entity.setName(getName());
		entity.setColor(getColor());
		
		Map<String, String> barColor = new HashMap<>();
		Map<String, String> iconStyle = new HashMap<>();
		barColor.put("background-color", getColor());
		String backgroundImage = "url(/image/Oink/Startups/" + Integer.toString(num) + ".png)";
		iconStyle.put("background-image", backgroundImage);
		iconStyle.put("background-size", "cover");
		entity.setBarColor(barColor);
		entity.setIconStyle(iconStyle);
		
		return entity;
	}
	
	public Card(int num) {
		this.num = num;
		this.coinOn = 0;
	}
	
	public Card(Document doc) {
		setFromDoc(doc);
	}
	
	public void receiveCoin() {
		this.coinOn++;
	}
	
	public void clearCoin() {
		this.coinOn = 0;
	}
	
	public String getName() {
		String ans = "";
		if (num == 5) {
			ans = "五福铁门";
		} else if (num == 6) {
			ans = "六道轮胎";
		} else if (num == 7) {
			ans = "七窍香烟";
		} else if (num == 8) {
			ans = "八哥软件";
		} else if (num == 9) {
			ans = "九转香肠";
		} else if (num == 10) {
			ans = "十年纱窗";
		}
		return ans;
	}
	
	public String getColor() {
		String ans = "";
		if (num == 5) {
			ans = "rgb(255,165,0)";
		} else if (num == 6) {
			ans = "rgb(0,0,128)";
		} else if (num == 7) {
			ans = "rgb(255,105,180)";
		} else if (num == 8) {
			ans = "rgb(150,75,0)";
		} else if (num == 9) {
			ans = "rgb(0,128,0)";
		} else if (num == 10) {
			ans = "rgb(210,43,43)";
		}
		return ans;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCoinOn() {
		return coinOn;
	}
	public void setCoinOn(int coinOn) {
		this.coinOn = coinOn;
	}
}
