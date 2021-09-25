package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.architect.entity.CardEntity;

public class Card {
	int type;
	List<Integer> needRes;
	List<Integer> provideRes;
	List<Integer> resOn;
	int numUpgrade;
	String img;
	String name;
	List<String> clickQuote;
	List<String> resolveQuote;
	
	Player player;
	Board board;
	
	public Card() {
		needRes = new ArrayList<>();
		provideRes = new ArrayList<>();
		resOn = new ArrayList<>();
		clickQuote = new ArrayList<>();
		resolveQuote = new ArrayList<>();
		for (int i=0;i<4;i++) {
			needRes.add(0);
			provideRes.add(0);
		}
	}
	
	public void quoteCategory(int x) {
		if (x == Consts.WORKER) {
			String[] clickArr = {"worker01","worker02","worker03","worker04","worker05"};
			String[] resolveArr = {"worker06","worker07","worker08"};
			setQuotes(clickArr, resolveArr);
		} else if (x == Consts.MAGICIAN) {
			String[] clickArr = {"mage01", "mage02"};
			String[] resolveArr = {"mage03", "mage04"};
			setQuotes(clickArr, resolveArr);
		} else if (x == Consts.TRADER) {
			String[] clickArr = {"trader01", "trader02"};
			String[] resolveArr = {"trader03", "trader04"};
			setQuotes(clickArr, resolveArr);
		} else if (x == Consts.TRADERALT01) {
			String[] clickArr = {"trader51", "trader52"};
			String[] resolveArr = {"trader53", "trader54"};
			setQuotes(clickArr, resolveArr);
		} else if (x == Consts.TRADERALT02) {
			String[] clickArr = {"trader11", "trader12"};
			String[] resolveArr = {"trader13", "trader14"};
			setQuotes(clickArr, resolveArr);
		}
		
	}
	
	public void setQuotes(String c[], String r[]) {
		clickQuote = new ArrayList<>();
		resolveQuote = new ArrayList<>();
		int i;
		for (i=0;i<c.length;i++) {
			clickQuote.add(c[i]);
		}
		for (i=0;i<r.length;i++) {
			resolveQuote.add(r[i]);
		}
	}
	
	public void setTypeWithQuote(int x) {
		type = x%10;
		quoteCategory(x);
	}
	
	public void addNeedRes(int res, int n) {
		int x = needRes.get(res);
		x = x+n;
		needRes.set(res, x);
	}
	
	public void addProvideRes(int res, int n) {
		int x = provideRes.get(res);
		x = x+n;
		provideRes.set(res, x);
	}
	
	public int maxPlayNum() {
		int ans = 0;
		int i;
		if (player==null) return 0;
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
	
	public void play(List<Integer> targets) {
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
				if (x>=Consts.WOOD && x<Consts.GOLD) {
					int y = x+1;
					int rx = player.getWarehouse().get(x)-1;
					int ry = player.getWarehouse().get(y)+1;
					player.getWarehouse().set(x, rx);
					player.getWarehouse().set(y, ry);
				}
			}
		} else if (type == Consts.TRADER) {
			if (targets.size()<1) return;
			int times = targets.get(0);
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
	public List<String> getClickQuote() {
		return clickQuote;
	}
	public void setClickQuote(List<String> clickQuote) {
		this.clickQuote = clickQuote;
	}
	public List<String> getResolveQuote() {
		return resolveQuote;
	}
	public void setResolveQuote(List<String> resolveQuote) {
		this.resolveQuote = resolveQuote;
	}

	public CardEntity toCardEntity() {
		CardEntity entity = new CardEntity();
		entity.setImg(img);
		entity.setName(name);
		entity.setType(Integer.toString(type));
		entity.setNumUpgrade(Integer.toString(numUpgrade));
		entity.setClickQuote(clickQuote);
		entity.setResolveQuote(resolveQuote);
		entity.setMaxPlayNum(Integer.toString(maxPlayNum()));
		int i;
		List<String> lnr = new ArrayList<>();
		for (i=0;i<needRes.size();i++) {
			lnr.add(Integer.toString(needRes.get(i)));
		}
		entity.setNeedRes(lnr);
		List<String> lpr = new ArrayList<>();
		for (i=0;i<provideRes.size();i++) {
			lpr.add(Integer.toString(provideRes.get(i)));
		}
		entity.setProvideRes(lpr);
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		doc.append("needRes", needRes);
		doc.append("provideRes", provideRes);
		doc.append("resOn", resOn);
		doc.append("numUpgrade", numUpgrade);
		doc.append("img", img);
		doc.append("name", name);
		doc.append("clickQuote", clickQuote);
		doc.append("resolveQuote", resolveQuote);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		type = doc.getInteger("type", -1);
		needRes = (List<Integer>) doc.get("needRes");
		provideRes = (List<Integer>) doc.get("provideRes");
		resOn = (List<Integer>) doc.get("resOn");
		numUpgrade = doc.getInteger("numUpgrade", -1);
		img = doc.getString("img");
		name = doc.getString("name");
		clickQuote = (List<String>) doc.get("clickQuote");
		resolveQuote = (List<String>) doc.get("resolveQuote");
	}
	
}
