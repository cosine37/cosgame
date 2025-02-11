package com.cosine.cosgame.rich;

import org.bson.Document;

public class Factory {
	public static Card genCard(int id) {
		Card c = new Card();
		
		return c;
	}
	
	public static Place genPlace(Document doc) {
		return null;
	}
}
