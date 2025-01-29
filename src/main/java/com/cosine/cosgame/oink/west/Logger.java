package com.cosine.cosgame.oink.west;

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
		String s = "第 " + Integer.toString(x) +" 局 开始";
		log(s);
		log("----------");
	}
	
	public void logRoundEnd(int x) {
		log("----------");
		String s = "第 " + Integer.toString(x) +" 局 结束";
		log(s);
	}	
	
	public void logRoundEndDivider() {
		log("==========");
	}
	
	public void logSmallDivider() {
		log("----------");
	}
	
	public void logExchange(Player p, Card c) {
		String s = p.getName() + "将救兵置换成了" + c.getName() + "（战力：" + c.getNum() + "）";
		log(s);
	}
	
	public void logDraw(Player p) {
		String s = p.getName() + "抽了一张牌";
		log(s);
	}
	
	public void logDiscard(Player p, Card c) {
		String s = p.getName() + "弃置了" + c.getName() + "（战力：" + c.getNum() + "）";
		log(s);
	}
	
	public void logBid(Player p, int x) {
		String s = p.getName() + "加入了吃瓜，将" + Integer.toString(x) + "枚仙丹加入池中";
		log(s);
	}
	
	public void logRetreat(Player p) {
		String s = p.getName() + "退出了吃瓜队列";
		log(s);
	}
	
	public void logWin(Player p) {
		String s = p.getName() + "获胜";
		log(s);
	}
	
	public void logReceivePool(Player p, int pool) {
		String s = p.getName() + "获得了池中的" + pool + "枚仙丹";
		log(s);
	}
	
	public void logInitialAssist(Card c) {
		String s = "本轮初始救兵为" + c.getName() + "（战力：" + c.getNum() + "）";
		log(s);
	}
}
