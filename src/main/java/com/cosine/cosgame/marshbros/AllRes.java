package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.marshbros.official.*;

public class AllRes {
	List<Card> officialCards;
	
	public AllRes() {
		setOfficialCards();
	}
	
	public List<Card> genDeck() {
		return officialCards;
	}
	
	public void setOfficialCards() {
		officialCards = new ArrayList<>();
		for (int i=0;i<3;i++) {
			officialCards.add(new XieZhen());
			officialCards.add(new XieBao());
			officialCards.add(new GuanSheng());
			officialCards.add(new LiTianRun());
			officialCards.add(new WangLun());
			officialCards.add(new ChaoGai());
			officialCards.add(new Tiger());
			officialCards.add(new Tiger());
			officialCards.add(new MuHong());
			officialCards.add(new MuChun());
			officialCards.add(new LiKui());
			officialCards.add(new HuYanZhuo());
			officialCards.add(new ZhuTong());
			officialCards.add(new LeiHeng());
		}
	}
}
