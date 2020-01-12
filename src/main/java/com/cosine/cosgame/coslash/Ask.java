package com.cosine.cosgame.coslash;

import org.bson.Document;

public class Ask {
	int type;
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		return doc;
	}
	
	public void setAskFromDoc(Document doc) {
		type = doc.getInteger("type");
	}
}
