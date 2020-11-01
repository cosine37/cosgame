package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Logger {
	List<String> logs;
	
	public Logger() {
		logs = new ArrayList<>();
	}
	
	public void log(String s) {
		logs.add(s);
	}
	
	String getAnotherUserName(Player p) {
		Random rand = new Random();
		int n = p.getBoard().getPlayers().size();
		String ans = p.getName();
		while (ans.contentEquals(p.getName())) {
			int x = rand.nextInt(n);
			ans = p.getBoard().getPlayers().get(x).getName();
		}
		return ans;
	}
	
	public void logUse(Player p, Card c) { 
		String s;
		if (c.getNum() == 8) {
			Random rand = new Random();
			int x = rand.nextInt(3);
			if (x == 0) {
				s = p.getName() + "：“我喝口水。”";
				log(s);
			}
		}
		if (c.getNum() == 1 || c.getNum() == 2) {
			Random rand = new Random();
			int x = rand.nextInt(5);
			if (x<1) {
				s = p.getName() + "：“我去吃我" + c.getName() + "！”";
				log(s);
			}
		}
		s = p.getName() + "的" + p.getPm().getName() + "使用了" + c.getName() + "。";
		log(s);
		
	}
	
	public void logUse(Player p, Card c, Player v) {
		String s;
		Random rand = new Random();
		int x = rand.nextInt(5);
		if (x<1) {
			s = p.getName() + "：“我去吃我" + c.getName() + "！”";
			log(s);
		}
		s = p.getName() + "的" + p.getPm().getName() + "对" + v.getName() + "的"
				+ v.getPm().getName() + "使用了" + c.getName() + "。";
		log(s);
		x = rand.nextInt(5);
		if (x<1) {
			s = v.getName() + "：“你能秒我？”";
			log(s);
		}
	}
	
	public void logUse(Player p, Card c, Player v1, Player v2) {
		String s;
		Random rand = new Random();
		int x = rand.nextInt(5);
		if (x<1) {
			s = p.getName() + "：“我去吃我" + c.getName() + "！”";
			log(s);
		}
		s = p.getName() + "的" + p.getPm().getName() + "对" + v1.getName() + "的"
				+ v1.getPm().getName()  + "和" + v2.getName() + "的"
				+ v2.getPm().getName() + "使用了" + c.getName() + "。";
		log(s);
		x = rand.nextInt(5);
		if (x<1) {
			s = v1.getName() + "：“你能秒我？”";
			log(s);
		}
		x = rand.nextInt(5);
		if (x<1) {
			s = v2.getName() + "：“你能秒我？”";
			log(s);
		}
	}
	
	public void logMiss(Player p, Card c, int r) {
		String s = "然而" + p.getName() + "的" + p.getPm().getName() + "并不会" + c.getName() + "。";
		log(s);
		s = p.getName() + "的" + p.getPm().getName() + "受到了" + r + "点反伤。";
		log(s);
		
		boolean flag = true;
		Random rand = new Random();
		int x;
		if (p.getHp()<=2) {
			if (flag) {
				x = rand.nextInt(2);
				if (x<1) {
					s = p.getName() + "：“真是屋漏偏逢连夜雨。”";
					log(s);
					flag = false;
				}
			}
			if (flag) {
				x = rand.nextInt(3);
				if (x<2) {
					s = p.getName() + "：“我已经在考虑下一把了。”";
					log(s);
					flag = false;
				}
			}
		}
		if (p.getMissCount() >= 3) {
			if (flag) {
				x = rand.nextInt(3);
				if (x<1) {
					s = p.getName() + "：“真是气煞我也！”（敲桌）"; 
					log(s);
					flag = false;
				}
			}
			if (flag) {
				x = rand.nextInt(3);
				if (x<2) {
					s = p.getName() + "：“我今天爆炸了！”"; 
					log(s);
					flag = false;
				}
			}
		}
		if (flag) {
			x = rand.nextInt(3);
			if (x<2) {
				s = p.getName() + "：“纳尼？”"; 
				log(s);
				flag = false;
			}
		}
		if (flag) {
			x = rand.nextInt(3);
			if (x<1) {
				s = p.getName() + "：“有没有搞错？”"; 
				log(s);
				flag = false;
			}
		}
	}
	
	public void logOn(Player p, Player v, int h) {
		String s = p.getName() + "的" + p.getPm().getName() + "对" + v.getName() + "的" + v.getPm().getName();
		Random rand = new Random();
		int x;
		if (h == 1) {
			x = rand.nextInt(3);
			if (x<1) {
				s = s + "造成了1点微小的重创。";
			} else {
				s = s + "造成了1点伤害。";
			}
			log(s);
		} else {
			s = s + "造成了" + h + "点伤害。";
			log(s);
			x = rand.nextInt(8);
			if (x==0) {
				s = v.getName() + ":“" + "我去伤害这么高吗？" + "”";
				log(s);
			} else if (x == 1) {
				s = v.getName() + ":“" + "说伤害不高，其实还挺高。" + "”";
				log(s);
			} else if (x<4) {
				s = v.getName() + ":“" + "这十万条命都接不起啊！" + "”";
				log(s);
			}
		}
		if (v.getHp() == 1) {
			x = rand.nextInt(3);
			if (x < 2) {
				s = p.getName() + ":“这" + v.getPm().getName() + "已经是风中残烛。”";
				log(s);
			}
			x = rand.nextInt(3);
			if (x < 1) {
				s = v.getName() + ":“我已经在考虑下一把了。”";
				log(s);
			}
		}
		if (v.getHp() == 0) {
			x = rand.nextInt(4);
			if (x == 0) {
				s = v.getName() + ":“大势已去。”";
				log(s);
			} else if (x == 1) {
				s = v.getName() + ":“有一方选择了投降，那一方就是我。”";
				log(s);
			}
		}
	}
	
	public void logHeal(Player p, int h) {
		String s = p.getName() + "的" + p.getPm().getName() + "回复了" + h + "点生命值。";
		log(s);
		Random rand = new Random();
		if (p.getHp() == 6) {
			int x = rand.nextInt(2);
			if (x<1) {
				s = p.getPm().getName() + "的生命值来到了一万三千点。";
				log(s);
			}
		}
	}
	
	public void logAncient(Player p) {
		String s = p.getName() + "获得了一张原始牌。";
		log(s);
	}
	
	public void logSend(Player p) {
		String s = p.getName() + "派出了" + p.getPm().getName() + "。";
		log(s);
		
		Random rand = new Random();
		int x = rand.nextInt(5);
		if (x<1) {
			s = p.getName() +":“这把要是能输，我当场给你把这个屏幕吃掉。”";
			log(s);
		}
		x = rand.nextInt(4);
		if (x<1) {
			int y = rand.nextInt(6);
			String ts = getAnotherUserName(p);
			if (y <= 1) {
				s = ts +":“" + p.getPm().getName() + "他不得行。”";
			} else if (y <= 3) {
				s = ts +":“" + p.getPm().getName() + "能干什么？" + p.getPm().getName() + "他什么也干不了。”";
			} else {
				s = ts + ":“你这" + p.getPm().getName() + "哪里来的?”";
				log(s);
				s = p.getName() + ":“我朋友送我的,关你什么事。”";
			}
			log(s);
		}
	}

	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	
}
