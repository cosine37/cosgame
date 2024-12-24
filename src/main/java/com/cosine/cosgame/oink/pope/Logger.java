package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;

public class Logger {
	List<String> logs;
	
	public Logger() {
		logs = new ArrayList<>();
	}
	
	public Logger(List<String> logs) {
		this();
		for (int i=0;i<logs.size();i++) {
			this.logs.add(logs.get(i));
		}
	}
	
	public List<String> getLogs(){
		return logs;
	}
	
	public void log(String s) {
		logs.add(s);
	}
	
	public void logPlayCard(PopePlayer p, Card c) {
		String s = p.getName() + "打出了" + c.getName();	
		log(s);
	}
	
	public void logPlayCard(PopePlayer p, PopePlayer tp, Card c) {
		String s = p.getName() + "对" + tp.getName() + "打出了" + c.getName();
		if (p.getName().contentEquals(tp.getName())) {
			s = p.getName() + "对自己打出了" + c.getName();
		}
		log(s);
	}
	
	public void logInactive(PopePlayer p) {
		String s = p.getName() + "，OUT！";
		log(s);
	}
	
	public void logProtect(PopePlayer p) {
		String s = "但是，对" + p.getName() + "好像没有效果，这就尴尬了。。。";
		log(s);
	}
	
	public void logDiscard(PopePlayer p, Card c) {
		String s = p.getName() + "弃置了" + c.getName();
		log(s);
	}
	
	public void logDraw(PopePlayer p) {
		String s = p.getName() + "抽了一张牌";
		log(s);
	}
	
	public void logRoundStart(int x) {
		String s = "第 " + Integer.toString(x) +" 局 开始";
		log(s);
	}
	
	public void logRoundEnd(int x) {
		log("----------");
		String s = "第 " + Integer.toString(x) +" 局 结束";
		log(s);
	}	
	
	public void logRoundEndDivider() {
		log("==========");
	}
	
	public void logStartTurn(PopePlayer p) {
		log("----------");
		String s = p.getName() + "开始了回合并抽了一张牌";
		log(s);
	}
	
	public void logEndTurn(PopePlayer p) {
		String s = p.getName() + "结束了回合";
		log(s);
	}
	
}
