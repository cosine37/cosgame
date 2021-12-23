package com.cosine.cosgame.threechaodoms.entity;

import java.util.List;

public class LoggerEntity {
	List<String> logs;
	List<CardLogEntity> cardLogs;
	
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public List<CardLogEntity> getCardLogs() {
		return cardLogs;
	}
	public void setCardLogs(List<CardLogEntity> cardLogs) {
		this.cardLogs = cardLogs;
	}
}
