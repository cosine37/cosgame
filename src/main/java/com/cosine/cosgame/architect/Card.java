package com.cosine.cosgame.architect;

import java.util.List;

public class Card {
	int type;
	List<Integer> needRes;
	List<Integer> provideRes;
	List<Integer> resOn;
	int numUpgrade;
	String img;
	String name;
	
	Player player;
	Board board;
	
	public int maxPlayNum() {
		int ans = 0;
		int i;
		if (type == Consts.WORKER) {
			ans = 1;
		} else if (type == Consts.MAGICIAN) {
			int count = 0;
			for (i=0;i<player.getWarehouse().size();i++) {
				if (player.getWarehouse().get(i) != Consts.GOLD) {
					count++;
				}
			}
			if (count>=numUpgrade) {
				ans = 1;
			} else {
				ans = 0;
			}
		} else if (type == Consts.TRADER) {
			ans = 999;
			for (i=0;i<needRes.size();i++) {
				int x = needRes.get(i);
				if (x==0) {
					continue;
				} else {
					int y = player.numRes(i) / x;
					if (y<ans) ans = y;
				}
			}
			if (ans == 999) ans = 0;
		}
		return ans;
	}
	
	public void play(int times, List<Integer> targets) {
		int i,j;
		if (type == Consts.WORKER) {
			for (i=0;i<provideRes.size();i++) {
				int x = provideRes.get(i);
				for (j=0;j<x;j++) {
					player.addRes(i);
				}
			}
		} else if (type == Consts.MAGICIAN) {
			for (i=0;i<targets.size();i++) {
				int x = targets.get(i);
				if (player.getWarehouse().get(x) == Consts.WOOD) {
					player.getWarehouse().set(x, Consts.STONE);
				} else if (player.getWarehouse().get(x) == Consts.STONE) {
					player.getWarehouse().set(x, Consts.BRICK);
				} else if (player.getWarehouse().get(x) == Consts.BRICK) {
					player.getWarehouse().set(x, Consts.GOLD);
				}
			}
		} else if (type == Consts.TRADER) {
			for (i=0;i<needRes.size();i++) {
				int x = needRes.get(i)*times;
				if (x>0) {
					player.removeRes(i, x);
				}
			}
			for (i=0;i<provideRes.size();i++) {
				int x = provideRes.get(i)*times;
				if (x>0) {
					player.addRes(i,x);
				}
			}
		}
	}
	
	public void addResOn(int res) {
		resOn.add(res);
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Integer> getNeedRes() {
		return needRes;
	}
	public void setNeedRes(List<Integer> needRes) {
		this.needRes = needRes;
	}
	public List<Integer> getProvideRes() {
		return provideRes;
	}
	public void setProvideRes(List<Integer> provideRes) {
		this.provideRes = provideRes;
	}
	public int getNumUpgrade() {
		return numUpgrade;
	}
	public void setNumUpgrade(int numUpgrade) {
		this.numUpgrade = numUpgrade;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Integer> getResOn() {
		return resOn;
	}
	public void setResOn(List<Integer> resOn) {
		this.resOn = resOn;
	}
	
}
