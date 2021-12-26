package com.cosine.cosgame.threechaodoms.logs;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.Card;
import com.cosine.cosgame.threechaodoms.entity.CardLogEntity;
import com.cosine.cosgame.threechaodoms.entity.LoggerEntity;

public class Logger {
	List<String> logs;
	List<CardLog> cardLogs;
	
	public Logger() {
		logs = new ArrayList<>();
		cardLogs = new ArrayList<>();
	}
	
	public void log(String s) {
		logs.add(s);
	}
	
	public void log(Card c, String i) {
		CardLog cardLog = new CardLog();
		cardLog.setCard(c);
		cardLog.setInfo(i);
		cardLogs.add(cardLog);
	}
	
	public void logPlayCard(String name, Card c) {
		String log = name + "派出了" + c.getName() + "。";
		String info = name + "打出";
		log(log);
		log(c,info);
	}
	
	public void logRecruitTavern(String name, Card c) {
		String log = name + "招募了" + c.getName() + "。";
		String info = name + "招募";
		log(log);
		log(c,info);
	}
	
	public void logRecruitDeck(String name) {
		String log = name + "招募了一名未知武将。";
		log(log);
	}
	
	public void logMove(int hw, int x, int oldPos, int newPos) {
		String log = "";
		if (hw == 0) {
			log = "王道";
		} else if (hw == 1) {
			log = "霸道";
		}
		if (hw==0 || hw==1) {
			if (x<0) {
				log = log + "向后移动";
				x = 0-x;
			} else if (x>0) {
				log = log + "向前移动";
			}
			if (x!=0) {
				log = log+x+"（" + oldPos + "->" + newPos + "）。";
				log(log);
			}
		}
		
	}
	
	public void log(String s, Card c, String i) {
		log(s);
		log(c,i);
	}
	
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public List<CardLog> getCardLogs() {
		return cardLogs;
	}
	public void setCardLogs(List<CardLog> cardLogs) {
		this.cardLogs = cardLogs;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("logs", logs);
		List<Document> loc = new ArrayList<>();
		for (int i=0;i<cardLogs.size();i++) {
			loc.add(cardLogs.get(i).toDocument());
		}
		doc.append("cardLogs", loc);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		logs = (List<String>) doc.get("logs");
		List<Document> loc = (List<Document>) doc.get("cardLogs");
		cardLogs = new ArrayList<>();
		for (int i=0;i<loc.size();i++) {
			CardLog cl = new CardLog();
			cl.setFromDoc(loc.get(i));
			cardLogs.add(cl);
		}
	}
	
	public LoggerEntity toLoggerEntity() {
		LoggerEntity entity = new LoggerEntity();
		entity.setLogs(logs);
		List<CardLogEntity> cardLogEntities = new ArrayList<>();
		for (int i=0;i<cardLogs.size();i++) {
			cardLogEntities.add(cardLogs.get(i).toCardLogEntity());
		}
		entity.setCardLogs(cardLogEntities);
		return entity;
	}
	
}
