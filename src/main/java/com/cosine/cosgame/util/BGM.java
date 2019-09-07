package com.cosine.cosgame.util;

import org.bson.Document;

public class BGM {
	String title;
	String url;
	
	public BGM() {}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("title", title);
		doc.append("url", url);
		return doc;
	}
	
	public void setBGMFromDoc(Document doc) {
		title = doc.getString("title");
		url = doc.getString("url");
	}
	
}
