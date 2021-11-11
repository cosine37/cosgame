package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;

public class Logger {
	List<String> logs;
	
	public Logger() {
		logs = new ArrayList<>();
	}
	public void log(String s) {
		logs.add(s);
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
}
