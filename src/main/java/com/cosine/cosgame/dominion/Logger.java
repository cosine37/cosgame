package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.util.StringEntity;

public class Logger {
	List<Log> logs;
	
	public Logger() {
		logs = new ArrayList<Log>();
	}
	
	public StringEntity getLogsAsStringEntity() {
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		for (int i=0;i<logs.size();i++) {
			value.add(logs.get(i).getMsg());
		}
		entity.setValue(value);
		return entity;
	} 
	
	public void setLoggerFromDocument(List<Document> docs) {
		logs = new ArrayList<Log>();
		int i;
		for (i=0;i<docs.size();i++) {
			Log log = new Log();
			log.setValue(docs.get(i));
			logs.add(log);
		}
	}
	
	public List<Document> getLoggerAsDocument() {
		List<Document> docs = new ArrayList<Document>();
		int i;
		for (i=0;i<logs.size();i++) {
			Document doc = logs.get(i).toDocument();
			docs.add(doc);
		}
		return docs;
	}
	
	public void add(String msg, int level) {
		Log log = new Log(msg, level);
		logs.add(log);
	}
	
	public void addBuyCard(String playerName, String cardName) {
		Log log = new Log();
		log.buy(playerName, cardName);
		logs.add(log);
	}
	
	public void addGainCard(String playerName, String cardName) {
		Log log = new Log();
		log.gain(playerName, cardName);
		logs.add(log);
	}
	
	public void addPlayCard(String playerName, String cardName) {
		Log log = new Log();
		log.play(playerName, cardName);
		logs.add(log);
	}
	
	public void addAutoplayTreasure(Player p) {
		Log log = new Log(p.getName() + " autoplays treasure", 0);
		logs.add(log);
	}
}
