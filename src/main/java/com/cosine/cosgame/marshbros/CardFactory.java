package com.cosine.cosgame.marshbros;

import com.cosine.cosgame.marshbros.official.*;

public class CardFactory {
	public static Card createCard(String img) {
		Card c = new Card();
		
		if (img.contentEquals("XieZhen")) {
			c = new XieZhen();
		} else if (img.contentEquals("XieBao")) {
			c = new XieBao();
		} else if (img.contentEquals("GuanSheng")) {
			c = new GuanSheng();
		} else if (img.contentEquals("LiTianRun")) {
			c = new LiTianRun();
		} else if (img.contentEquals("WangLun")) {
			c = new WangLun();
		} else if (img.contentEquals("ChaoGai")) {
			c = new ChaoGai();
		} else if (img.contentEquals("Tiger")) {
			c = new Tiger();
		}
		return c;
	}
}
