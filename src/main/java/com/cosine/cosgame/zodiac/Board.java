package com.cosine.cosgame.zodiac;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	List<Player> players;
	List<Zodiac> zodiacs;
	List<Role> roles;
	
	int round;
	int phase;
	int status;
	
	public void initializeZodiacs() {
		int i;
		String[] names = {"鼠","牛","虎","兔","龙","蛇","马","羊","猴","鸡","苟","猪"};
		zodiacs = new ArrayList<>();
		for (i=0;i<12;i++) {
			Zodiac z = new Zodiac();
			z.setName(names[i]);
			z.setNum(i+1);
			zodiacs.add(z);
		}
	}
	
	public void setZodiacQualities() {
		int i;
		List<Boolean> bs = new ArrayList<>();
		for (i=0;i<6;i++) {
			bs.add(true);
			bs.add(false);
		}
		i = 0;
		while (bs.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(bs.size());
			Boolean f = bs.remove(x);
			zodiacs.get(i).setReal(f);
			i++;
		}
	}
	
	public void randomizeZodiacs() {
		int i;
		List<Zodiac> tempZ = new ArrayList<>();
		for (i=0;i<zodiacs.size();i++) {
			tempZ.add(zodiacs.get(i));
		}
		zodiacs = new ArrayList<>();
		while (tempZ.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(tempZ.size());
			Zodiac z = tempZ.remove(x);
			zodiacs.add(z);
		}
	}
	
	public void keepAndReveal(int roundNum) {
		int i,j;
		List<Zodiac> tempZ = new ArrayList<>();
		for (i=0;i<4;i++) {
			int x = roundNum*4+i;
			tempZ.add(zodiacs.get(x));
		}
		for (i=0;i<4;i++) {
			for (j=i+1;j<4;j++) {
				Zodiac zi = tempZ.get(i);
				Zodiac zj = tempZ.get(j);
				boolean exchange = false;
				if (zi.getVotes() < zj.getVotes()) {
					exchange = true;
				} else if (zi.getVotes() == zj.getVotes()) {
					if (zj.getNum() < zi.getNum()) {
						exchange = true;
					}
				}
				if (exchange) {
					tempZ.set(i, zj);
					tempZ.set(j, zi);
				}
			}
		}
		Zodiac z0 = tempZ.get(0);
		Zodiac z1 = tempZ.get(1);
		z0.setKeep(true);
		z1.setKeep(true);
		if (z0.getVotes() > z1.getVotes()) {
			z1.setReveal(true);
		} else if (z0.getVotes() < z1.getVotes()) {
			z0.setReveal(true);
		} else {
			if (z1.getNum() < z0.getNum()) {
				z1.setReveal(true);
			} else {
				z0.setReveal(true);
			}
		}
	}

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Zodiac> getZodiacs() {
		return zodiacs;
	}
	public void setZodiacs(List<Zodiac> zodiacs) {
		this.zodiacs = zodiacs;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
