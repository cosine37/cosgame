package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;

public class Logs {
	List<String> logs;
	
	public Logs() {
		logs = new ArrayList<>();
	}
	
	public void addLog(String s) {
		logs.add(s);
	}
	
	String diceInfo(List<Integer> results) {
		String s;
		s = "掷出了：";
		s = s + Integer.toString(results.get(0));
		for (int i=1;i<results.size();i++) {
			s = s + "，" + Integer.toString(results.get(i));
		}
		return s;
	}
	
	void logRaidResult(Player p, Role r, int result) {
		String s;
		if (result == 0) {
			s = r.getCard().getName() + "没有劫掠到任何资源，这就尴尬了。";
		} else {
			s = r.getCard().getName() + "共劫掠了" + Integer.toString(result) + "点资源。";
		}
		addLog(s);
	}
	
	void logAttackResult(Player p, Role r, Player tp, Role tr, int result) {
		String s;
		if (result == 0) {
			s = r.getCard().getName() + "没有造成任何伤害，这就尴尬了。";
		} else {
			s = r.getCard().getName() + "对" + tr.getCard().getName() + "造成了" + Integer.toString(result) + "点伤害。";
		}
		addLog(s);
	}
	
	public void logRaid(Player p, Role r, List<Integer> results, int result) {
		String s;
		s = p.getName() + "寨中的" + r.getCard().getName() + "发动了劫掠。";
		s = s + diceInfo(results);
		addLog(s);
		logRaidResult(p,r,result);
	}
	
	public void logAction(Player p, Role r, String actionName) {
		String s;
		s = p.getName() + "寨中的" + r.getCard().getName() + "发动了特技：" + actionName + "。";
		addLog(s);
	}
	
	public void logAttack(Player p, Role r, Player tp, Role tr, List<Integer> results, int result) {
		String s;
		s = p.getName() + "寨中的" + r.getCard().getName() + "对" + tp.getName() + "帐下的" + tr.getCard().getName() + "发动了攻击。";
		s = s + diceInfo(results);
		addLog(s);
		logAttackResult(p,r,tp,tr,result);
	}
	
	public void logDraw(Player p, int x) {
		String s;
		if (x == 1) {
			s = p.getName() + "摸了1张牌。";
		} else {
			s = p.getName() + "摸了" + Integer.toString(x) + "张牌。";
		}
		addLog(s);
	}
	
	public void logAppoint(Player p, Role r) {
		String s;
		s = p.getName() + "派出了" + r.getCard().name + "。";
		addLog(s);
	}
	
	public void logSacrifice(Player p, Role r) {
		String s;
		s = p.getName() + "献祭了" + r.getCard().name + "。";
		addLog(s);
	}
	
	public void logKnockdown(Player p, Role r) {
		String s;
		s = p.getName() + "帐下的" + r.getCard().name + "被击倒了。";
		addLog(s);
	}
	
	public void logAddHp(Player p, Role r, int x) {
		String s;
		s = p.getName() + "帐下的" + r.getCard().name + "增加了" + Integer.toString(x) + "点体力。";
		addLog(s);
	}
	
	public void logSteal(Player p, Role r, Player v) {
		String s;
		s = p.getName() + "帐下的" + r.getCard().name + "从" + v.getName() + "处窃取了1点资源。";
		addLog(s);
	}
	
	public void logEndTurn(Player p) {
		String s;
		s = p.getName() + "结束了回合。";
		addLog(s);
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	
	
}
