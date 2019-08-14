package com.cosine.cosgame.dominion.list;

import java.util.List;

public class SingleCard {
	String name;
	String nameCN;
	String image;
	String desc;
	String descCN;
	String faq;
	String faqCN;
	public SingleCard(){
		
	}
	
	public void set(List<String> info) {
		name = info.get(0);
		nameCN = info.get(1);
		image = info.get(2);
		desc = info.get(3);
		descCN = info.get(4);
		faq = info.get(5);
		faqCN = info.get(6);
	}

	public String getName() {
		return name;
	}

	public String getNameCN() {
		return nameCN;
	}

	public String getImage() {
		return image;
	}

	public String getDesc() {
		return desc;
	}

	public String getDescCN() {
		return descCN;
	}

	public String getFaq() {
		return faq;
	}

	public String getFaqCN() {
		return faqCN;
	}
	
	
}
