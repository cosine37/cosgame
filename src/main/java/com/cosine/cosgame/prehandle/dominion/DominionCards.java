package com.cosine.cosgame.prehandle.dominion;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class DominionCards {
	MongoDBUtil dbutil;
	
	public DominionCards() {
		String dbname = "dominion";
		String col = "list";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void change(String s) {
		dbutil.update("id", "1", "value", s);
	}
	
	public void create() {
		Document doc = new Document();
		doc.append("id", "1");
		doc.append("value", "a");
		dbutil.insert(doc);
	}
	
	public String get() {
		String ans = dbutil.getValues("value").get(0);
		return ans;
	}
}
