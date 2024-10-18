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
	
	public void log(String s) {
		logs.add(s);
	}
	
	public void startRound(int round) {
		String s = "第"+Integer.toString(round)+"轮 开始";
		log(s);
	}
	
	public void logDraw(Player p, int cost) {
		String s = "";
		if (cost == 0) {
			s = p.getName() + "摸了一张未知股份";
		} else {
			s = p.getName() + "花费了$" + Integer.toString(cost) + "摸了一张未知股份";
		}
		log(s);
	}
	
	public void logTake(Player p, Card c, int coin) {
		String s = "";
		if (coin == 0) {
			s = p.getName() + "获得了弃置的一股 " + c.getName();
		} else {
			s = p.getName() + "获得了弃置的一股 " + c.getName() + " 并获得了$" + Integer.toString(coin);
		}
		log(s);
	}
	
	public void logDiscard(Player p, Card c, int index) {
		String s = "";
		if (index == 3) {
			s = p.getName() + "摸切了刚获得的一股 " + c.getName();
		} else {
			s = p.getName() + "手切了之前持有的一股 " + c.getName();
		}
		log(s);
	}
	
	public void logPlay(Player p, Card c, int index) {
		String s = "";
		if (index == 3) {
			s = p.getName() + "打出了刚获得的一股 " + c.getName();
		} else {
			s = p.getName() + "打出了之前持有的一股 " + c.getName();
		}
		log(s);
	}
	
	public void logAntiMonopoly(Player p, Card c) {
		String s = p.getName() + " 明面上暂时持有最多股 " + c.getName() +" 因此获得相应的反垄断标记";
		log(s);
	}
	
}
