package com.cosine.cosgame.oink.grove;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.oink.grove.GrovePlayer;

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
	
	public void logView(GrovePlayer p, List<Integer> viewed) {
		String s = p.getName() + " 调查了 ";
		boolean flag = false;
		for (int i=0;i<viewed.size();i++) {
			if (viewed.get(i) == 1) {
				if (flag) {
					s = s+" 和 ";
				}
				char ch = (char) ('A' + i);
				s = s+"嫌疑人"+ch;
				flag = true;
			}
		}
		log(s);
	}
	
	public void logAccuse(GrovePlayer p, int index) {
		String s = p.getName() + " 指控了 嫌疑人";
		char ch = (char) ('A' + index);
		s = s + ch;
		log(s);
	}
	
	public void logCorrect(GrovePlayer p, Role r) {
		String s = p.getName() + " 成功指控了罪犯 " + r.getName();
		log(s);
	}
	
	public void logWrong(GrovePlayer p, Role r) {
		String s = p.getName() + " 指控了无辜的 " + r.getName();
		log(s);
	}
	
	public void logDoge(GrovePlayer p, Role r, int x) {
		String s =  "无辜的 " + r.getName() + " 最终被 " + p.getName() + " 愿望，因此" + p.getName() + "获得了" + Integer.toString(x) + "个狗头标记";
		log(s);
	}
	
	public void logMurderer(Role r) {
		String s ="凶手就是你，" + r.getName() + "！";
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
	
	public void logStartTurn(GrovePlayer p) {
		log("----------");
		String s = p.getName() + " 开始了回合";
		log(s);
	}
}
