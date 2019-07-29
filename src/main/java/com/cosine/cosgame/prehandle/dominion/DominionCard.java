package com.cosine.cosgame.prehandle.dominion;

import java.util.List;

public class DominionCard {
	String name;
	String image;
	String descEN;
	String descCN;
	String faqEN;
	String faqCN;
	
	public DominionCard() {
		
	}
	
	public void set(List<String> s) {
		if (s.size() < 6) return;
		name = s.get(0);
		image = s.get(1);
		descEN = s.get(2);
		descCN = s.get(3);
		faqEN = s.get(4);
		faqCN = s.get(5);
	}
	
	public String get(int x) {
		switch (x) {
			case 0:
				return name;
			case 1:
				return image;
			case 2:
				return descEN;
			case 3:
				return descCN;
			case 4:
				return faqEN;
			case 5:
				return faqCN;
			default:
				return "";	
		}
	}
	
}
