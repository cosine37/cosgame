package com.cosine.cosgame.dominion.list;

import java.util.List;

public class SingleCard {
	String name;
	String text;
	String price;
	String type;
	String image;
	String faq;
	public SingleCard(){
		
	}
	
	public void set(List<String> info) {
		name = info.get(0);
		text = info.get(1);
		price = info.get(2);
		type = info.get(3);
		image = info.get(4);
		if (info.size() == 6) {
			faq = info.get(5);
		}
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getPrice() {
		return price;
	}
	
	public String getType() {
		return type;
	}

	public String getImage() {
		return image;
	}

	public String getFaq() {
		return faq;
	}
	
	
	
}
