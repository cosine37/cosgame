package com.cosine.cosgame.rich;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.*;

public class Factory {
	public static Card genCard(int id) {
		Card c = new Card();
		
		return c;
	}
	
	public static Place genPlace(Document doc, Board board) {
		Place p = null;
		int type = doc.getInteger("type", 0);
		if (type == Consts.PLACE_EMPTY) {
			p = new Empty(doc, board);
		} else if (type == Consts.PLACE_STARTPOINT) {
			p = new StartPoint(doc, board);
		} else if (type == Consts.PLACE_TAX) {
			p = new Tax(doc, board);
		}
		return p;
	}
}
