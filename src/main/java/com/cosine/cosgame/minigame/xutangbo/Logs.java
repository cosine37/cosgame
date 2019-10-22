package com.cosine.cosgame.minigame.xutangbo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Logs {
	List<Log> logs;
	
	public Logs() {
		logs = new ArrayList<>();
	}
	
	public List<String> getLogsAsStrings() {
		List<String> ls = new ArrayList<>();
		for (int i=0;i<logs.size();i++) {
			ls.add(logs.get(i).toString());
		}
		return ls;
	}
	
	public void logCount(int round, int step) {
		Log log = new Log();
		log.setType(Log.COUNTER);
		log.setRound(round);
		log.setStep(step);
		logs.add(log);
	}
	
	public void logMove(String name, int moveId) {
		Log log = new Log();
		log.setType(Log.USEMOVE);
		log.setName(name);
		log.setMoveId(moveId);
		logs.add(log);
	}
	
	public void logAfterEffect(String name, int moveId) {
		Log log = new Log();
		log.setType(Log.AFTEREFFECT);
		log.setName(name);
		if (moveId == Move.BI) {
			log.setSubType(Log.USEBI);
			logs.add(log);
		} else if (moveId == Move.XU) {
			log.setSubType(Log.GAINENERGY);
			logs.add(log);
		} else {
			Move move = new Move(moveId);
			if (move.getEnergy() > 0) {
				log.setSubType(Log.USEENERGY);
				log.setNum(move.getEnergy());
				logs.add(log);
			}
		}
		
	}
	
	public void logFaint(String name) {
		Log log = new Log();
		log.setType(Log.AFTEREFFECT);
		log.setSubType(Log.FAINTED);
		log.setName(name);
		logs.add(log);
	}
	
	public void logBaosi(String name) {
		Log log = new Log();
		log.setType(Log.AFTEREFFECT);
		log.setSubType(Log.BAOSI);
		log.setName(name);
		logs.add(log);
	}
	
	public void addLog(Log log) {
		logs.add(log);
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
	
	public List<Document> toDocument() {
		List<Document> dol = new ArrayList<>();
		for (int i=0;i<logs.size();i++) {
			Document d = logs.get(i).toDocument();
			dol.add(d);
		}
		return dol;
	}
	
	public void setFromDoc(List<Document> dol) {
		logs = new ArrayList<>();
		for (int i=0;i<dol.size();i++) {
			Log log = new Log();
			log.setFromDoc(dol.get(i));
			logs.add(log);
		}
	}
	
}
