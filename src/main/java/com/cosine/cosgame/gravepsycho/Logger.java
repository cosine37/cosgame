package com.cosine.cosgame.gravepsycho;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		log("==========");
		String s = "第 " + Integer.toString(x) + " 轮开始";
		log(s);
	}
	
	public void logRevealCard(Card c) {
		log("----------");
		String s = "";
		if (c.getType() == Consts.COIN) {
			s = "发现了 " + c.getNum() + " 枚钱币";
		} else if (c.getType() == Consts.TREASURE) {
			s = "发现宝物： " + c.getTreasureName();
		} else if (c.getType() == Consts.DISASTER) {
			s = "灾难接近： " + c.getDisasterName();
		}
		log(s);
	}
	
	public void logDistributeCoins(List<String> pnames, int x) {
		if (pnames.size() == 1) {
			log(pnames.get(0) + "独得 " + x + " 枚钱币");
		} else {
			int i;
			String s = "";
			for (i=0;i<pnames.size()-1;i++) {
				if (i == 0) {
					s = s+pnames.get(i);
				} else {
					s = s+"、"+pnames.get(i);
				}
			}
			s = s+"和"+pnames.get(pnames.size()-1)+"各获得了 " + x + " 枚钱币";
			log(s);
		}
	}
	
	public void logDisasterHappen() {
		log("灾难发生！");
	}
	
	public void logDisaster(List<String> pnames) {
		String s = "";
		if (pnames.size() == 1) {
			s = s+ pnames.get(0);
		} else {
			int i;
			for (i=0;i<pnames.size()-1;i++) {
				if (i == 0) {
					s = s+pnames.get(i);
				} else {
					s = s+"、"+pnames.get(i);
				}
			}
			s = s+"和"+pnames.get(pnames.size()-1);
		}
		s = s + "失去了本轮获得的所有钱币";
		log(s);
	}
	
	public void logTreasure(String pname, String tname) {
		log(pname + "带走了宝物：" + tname);
	}
	
	public void logBack(List<Player> backPlayers) {
		String s = "";
		if (backPlayers.size() == 1) {
			s = s+backPlayers.get(0).getName();
		} else {
			int i;
			for (i=0;i<backPlayers.size()-1;i++) {
				if (i == 0) {
					s = s+backPlayers.get(i).getName();
				} else {
					s = s+"、"+backPlayers.get(i).getName();
				}
				s = s+"和"+backPlayers.get(backPlayers.size()-1).getName();
			}
		}
		Random rand = new Random();
		int x = rand.nextInt(2);
		if (x == 0) {
			s = s+"见好就收，选择了返回营地";
		} else {
			s = s+"怂了，决定返回营地";
		}
		log(s);
	}
	
	public void logSteal(String pname, String tname) {
		String s = pname + "准备在归途伏击" + tname;
		log(s);
	}
	
	public void logStealFail(String pname, String tname) {
		String s = "但是" + tname + "并没有返回营地，所以" + pname + "什么都没有偷到，这就尴尬了";
		log(s);
	}
	
	public void logStealDuplicate(String pname, String tname) {
		String s = "但是有其他玩家也选择伏击" + tname + "，隐蔽失败，所以" + pname + "什么都没有偷到，这就尴尬了";
		log(s);
	}
	
	public void logStealSuccess(String pname, String tname, int x) {
		Random rand = new Random();
		int y = rand.nextInt(2);
		String s = "";
		if (y == 0) {
			s = tname + "在返回营地的路上被伏击的" + pname + "偷走了" + x + "枚钱币，" + pname + "真是脏的飞起";
		} else if (y == 1) {
			s = tname + "在返回营地的路上被伏击的" + pname + "偷走了" + x + "枚钱币，" + pname + "实在是太素质了";
		} else {
			
		}
		
		log(s);
	}

}
