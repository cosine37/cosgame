package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.gardenwar.entity.CardEntity;

public class Card {
	// card basic info
	protected int id;
	protected String name;
	protected String img;
	protected String desc;
	protected int cost;
	protected int type;
	protected int shield;
	protected List<Integer> clan;
	protected List<Integer> extraBits;
	
	// card bonuses
	protected int sun;
	protected int atk;
	protected int def;
	protected int draw;
	
	protected Player player;
	protected Board board;
	
	protected boolean taunt;
	protected boolean autoplay;
	
	public Card() {
		taunt = false;
		autoplay = true;
		type = Consts.CARD;
		clan = new ArrayList<>();
		int i;
		for (i=0;i<Consts.NUMCLANS;i++) {
			clan.add(0);
		}
		sun = 0;
		atk = 0;
		shield = 0;
		cost = 0;
		desc = "";
	}
	
	public void play() {
		
	}
	public void startTurn() {
		
	}
	public void addClan(int x) {
		if (x>=0 && x<Consts.NUMCLANS) {
			clan.set(x, 1);
		}
	}
	public boolean isClan(int x) {
		if (x>=0 && x<Consts.NUMCLANS) {
			if (clan.get(x) == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List<Integer> getClan() {
		return clan;
	}
	public void setClan(List<Integer> clan) {
		this.clan = clan;
	}
	public int getSun() {
		return sun;
	}
	public void setSun(int sun) {
		this.sun = sun;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getDef() {
		return def;
	}
	public void setDef(int def) {
		this.def = def;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isTaunt() {
		return taunt;
	}
	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}
	public boolean isAutoplay() {
		return autoplay;
	}
	public void setAutoplay(boolean autoplay) {
		this.autoplay = autoplay;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Integer> getExtraBits() {
		return extraBits;
	}
	public void setExtraBits(List<Integer> extraBits) {
		this.extraBits = extraBits;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getClanDisplay() {
		boolean flag = true;
		String ans = "";
		int i;
		final String[] clanNames = {"蘑菇", "豌豆", "花朵"};
		for (i=0;i<clan.size();i++) {
			if (clan.get(i) == 1 && i<clanNames.length) {
				String s = clanNames[i];
				if (flag) {
					ans = s;
					flag = false;
				} else {
					ans = ans + "，" + s;
				}
			}
		}
		return ans;
	}
	public String getTypeDisplay() {
		String ans = "";
		if (type == Consts.CARD) {
			ans = "基本";
		} else if (type == Consts.EQUIP) {
			ans = "放置";
		}
		return ans;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("img", img);
		doc.append("extraBits", extraBits);
		return doc;
	}
	public CardEntity toCardEntity() {
		CardEntity entity = new CardEntity();
		entity.setName(name);
		entity.setImg(img);
		entity.setDesc(desc);
		entity.setClan(getClanDisplay());
		entity.setCost(cost);
		entity.setShield(shield);
		entity.setType(getTypeDisplay());
		entity.setSun(sun);
		entity.setPea(atk);
		entity.setTaunt(taunt);
		return entity;
	}
	
	
}
