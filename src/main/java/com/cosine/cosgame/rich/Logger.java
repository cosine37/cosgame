package com.cosine.cosgame.rich;

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
	
	public void logRoundStart(int x) {
		String s = "第 " + Integer.toString(x) +" 轮 开始";
		log(s);
	}
	
	public void logRoundEndDivider() {
		log("==========");
	}
	
	public void logStartTurn(Player p) {
		log("----------");
		String s = p.getName() + " 开始了回合";
		log(s);
	}
	
	public void logEndTurn(Player p) {
		String s = p.getName() + " 结束了回合";
		log(s);
	}
	
	public void logPlayerRoll(Player p) {
		String s = p.getName() + " 掷出了 " + p.getRollDisplay();
		log(s);
	}
	
	public void logPlayerArrive(Player p) {
		String s = p.getName() + " 来到了 " + p.myCurrentPlaceName();
		log(s);
	}
	
	public void logPlayerReceiveMoney(Player p, int amount) {
		String s = p.getName() + " 获得了 $" + amount;
		log(s);
	}
	
	public void logPlayerLoseMoney(Player p, int amount) {
		String s = p.getName() + " 向银行支付了 $" + amount;
		log(s);
	}
	
	public void logBypass(Player p, Place place) {
		String s = p.getName() + " 经过了 " + place.getName();
		log(s);
	}
}
