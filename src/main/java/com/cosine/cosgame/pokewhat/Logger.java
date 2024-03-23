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
	
	public void logStartTurn(Player p) {
		String s;
		s = p.getName() + "开始了回合。";
		log(s);
		Random rand = new Random();
		int x = rand.nextInt(3);
		if (x == 0) {
			Player prev = p.prevPlayer();
			Player next = p.nextPlayer();
			x = rand.nextInt(2);
			if (x == 0) {
				s = prev.getName() + "：“你能秒我？”";
				log(s);
			} else {
				s = next.getName() + "：“你能秒我？”";
				log(s);
			}
			
		}
		
	}
	
	public void logEndTurn(Player p) {
		String s;
		Random rand = new Random();
		int x = rand.nextInt(3);
		if (x == 0) {
			s = p.getName() + "：“我先怂一下。”";
			log(s);
		}
		s = p.getName() + "结束了回合。";
		log(s);
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
		if (c.getNum() != 3 && c.getNum() != 4 && c.getNum() != 8) {
			Random rand = new Random();
			int x = rand.nextInt(5);
			if (x<1) {
				s = p.getName() + "：“我去吃我" + c.getName() + "！”";
				log(s);
			}
		}
		s = p.getName() + "的" + p.getPm().getName() + "使用了" + c.getName() + "。";
		log(s);
		Player prev = p.prevPlayer();
		if (c.getNum() == prev.getLastMove()) {
			Random rand = new Random();
			int x = rand.nextInt(2);
			if (x == 0) {
				s = prev.getName() + "：“你还"+c.getName()+"我还"+c.getName()+"你还"+c.getName()+"。”";
				log(s);
			}
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
		if (c.getNum() == 4) {
			x = rand.nextInt(3);
			if (x<1) {
				s = this.getAnotherUserName(p) + ":“贪贪贪，贪的飞起。”";
				log(s);
			}
		}
		if (p.getHp()<=2 && p.getHp()>0) {
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
		if (p.getHp() == 0) {
			if (flag) {
				x = rand.nextInt(2);
				if (x<1) {
					s = p.getName() + "：“大势已去。”";
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
			x = rand.nextInt(4);
			if (x<1) {
				s = v.getName() + ":“" + "这伤害说不高，其实还挺高。" + "”";
				log(s);
			}
		} else {
			s = s + "造成了" + h + "点伤害。";
			log(s);
			x = rand.nextInt(6);
			if (x<2) {
				s = v.getName() + ":“" + "我去伤害这么高吗？" + "”";
				log(s);
			} else if (x<3) {
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
	
	public void logEternabeam(Player p) {
		String s = p.getName() + "的" + p.getPm().getName() + "使用完全部技能， 发动无！极！光！束！";
		log(s);
		s = "所有其它玩家的宝可梦都被击倒了。";
		log(s);
	}
	
	public void logOverHeal(Player p) {
		if (p.getHp() == PokewhatConsts.MAXHP) {
			String s = "然而，" + p.getName() + "的" + p.getPm().getName() + "的生命值是满的，所以生命值不变。";
			log(s);
		}
	}
	
	public void logHeal(Player p, int h) {
		String s = p.getName() + "的" + p.getPm().getName() + "回复了" + h + "点生命值。";
		log(s);
		Random rand = new Random();
		if (p.getHp() >= 5) {
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
		Random rand = new Random();
		int x = rand.nextInt(3);
		if (x<1) {
			s = p.getName() + ":“胜利的方程式已经写好。”";
			log(s);
		}
	}
	
	public void logSend(Player p) {
		Random rand = new Random();
		String s;
		int x = rand.nextInt(8);
		if (x<1) {
			s = p.getName() + ":“各位同学大家好，我是" + p.getName() + "。”";
			log(s);
		}
		
		s = p.getName() + "派出了" + p.getPm().getName() + "。";
		log(s);
		
		if (p.getPm().getImg().contentEquals("174")){
			s = p.getName() + "的雷吉奇卡斯无法拿出平时的水平！";
			log(s);
		}
		
		x = rand.nextInt(6);
		if (x<1) {
			s = p.getName() + ":“这把要是能输，我当场给你把这个屏幕吃掉。”";
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
	
	public void logStartRound(int x) {
		String s = "第" + x + "轮开始。";
		log(s);
	}
	
	public void logEndRound(int x) {
		String s = "第" + x + "轮结束。";
		log(s);
	}
	
	public void logScore(Player p, int x) {
		String s = p.getName() + "本轮获得" + x + "分。";
		log(s);
	}
	
	public void logGameEnd() {
		String s = "游戏结束。";
		log(s);
	}

	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	
}
