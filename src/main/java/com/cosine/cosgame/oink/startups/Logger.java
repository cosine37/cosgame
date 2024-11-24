package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

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
	
	public void logDraw(Player p, int cost) {
		String s = "";
		if (cost == 0) {
			s = p.getName() + " 从牌堆获得了一张未知股份";
		} else {
			s = p.getName() + " 花费了$" + Integer.toString(cost) + "从牌堆获得一张未知股份";
		}
		log(s);
	}
	
	public void logTake(Player p, Card c, int coin) {
		String s = "";
		if (coin == 0) {
			s = p.getName() + " 获得了弃置的一股 " + c.getName();
		} else {
			s = p.getName() + " 获得了弃置的一股 " + c.getName() + " 并获得了$" + Integer.toString(coin);
		}
		log(s);
	}
	
	public void logDiscard(Player p, Card c, int index) {
		String s = "";
		if (index == 3) {
			s = p.getName() + " 摸切了刚获得的一股 " + c.getName();
		} else {
			s = p.getName() + " 手切了之前持有的一股 " + c.getName();
		}
		log(s);
	}
	
	public void logPlay(Player p, Card c, int index) {
		String s = "";
		if (index == 3) {
			s = p.getName() + " 打出了刚获得的一股 " + c.getName();
		} else {
			s = p.getName() + " 打出了之前持有的一股 " + c.getName();
		}
		log(s);
	}
	
	public void logAntiMonopoly(Player p, Card c, Player p2) {
		String s = p.getName() + " 获得了 " + c.getName() +" 的反垄断标记";
		if (p2 != null) {
			s = p.getName() + " 从 " + p2.getName() + " 处获得了 " + c.getName() +" 的反垄断标记";
		}
		
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
		log("==========");
	}
	
	public void logStartTurn(Player p) {
		log("----------");
		String s = p.getName() + " 开始了回合";
		log(s);
	}
	
}
