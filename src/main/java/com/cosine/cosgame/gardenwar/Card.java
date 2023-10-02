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
	protected int level;
	protected int hp;
	protected int index;
	protected int place;
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
	protected boolean trashable;
	protected boolean hasStartTurn;
	protected boolean activatable;
	protected boolean activated;
	
	public Card() {
		taunt = false;
		autoplay = false;
		trashable = true;
		hasStartTurn = false;
		activatable = false;
		activated = true;
		type = Consts.CARD;
		clan = new ArrayList<>();
		extraBits = new ArrayList<>();
		int i;
		for (i=0;i<Consts.NUMCLANS;i++) {
			clan.add(0);
		}
		sun = 0;
		atk = 0;
		shield = 0;
		cost = 0;
		level = 0;
		desc = "";
	}
	
	public void play() {
		player.addSun(getSun());
		player.addAtk(getAtk());
	}
	public void resolve(List<Integer> targets) {
		
	}
	public void resolve(int x) {
		List<Integer> targets = new ArrayList<>();
		targets.add(x);
		resolve(targets);
	}
	public void activate() {
		
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
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isTrashable() {
		return trashable;
	}
	public void setTrashable(boolean trashable) {
		this.trashable = trashable;
	}
	public boolean isHasStartTurn() {
		return hasStartTurn;
	}
	public void setHasStartTurn(boolean hasStartTurn) {
		this.hasStartTurn = hasStartTurn;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public boolean isActivatable() {
		return activatable;
	}
	public void setActivatable(boolean activatable) {
		this.activatable = activatable;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int place) {
		this.place = place;
	}
	public String getClanDisplay() {
		boolean flag = true;
		String ans = "";
		int i;
		//final String[] clanNames = {"蘑菇", "豌豆", "花朵", "树叶","果实","投手","土间","种子"};
		final String[] clanNames = {"物品", "豌豆", "花朵", "树叶","果实","投手","土间","种子"};
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
		String typeDisplay = getTypeDisplay();
		if (ans.length() == 0) ans = typeDisplay; else ans = typeDisplay + "，" + ans;
		return ans;
	}
	public String getTypeDisplay() {
		String ans = "";
		if (type == Consts.CARD) {
			ans = "行动";
		} else if (type == Consts.EQUIP) {
			ans = "名流";
		}
		return ans;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("img", img);
		doc.append("hp", hp);
		doc.append("activated", activated);
		doc.append("extraBits", extraBits);
		return doc;
	}
	public CardEntity toCardEntity() {
		CardEntity entity = new CardEntity();
		entity.setName(name);
		//entity.setImg(img);
		entity.setImg(getImg());
		entity.setDesc(getDesc());
		entity.setClan(getClanDisplay());
		entity.setCost(cost);
		entity.setShield(shield);
		entity.setType(getTypeDisplay());
		//entity.setSun(sun);
		entity.setSun(getSun());
		entity.setPea(atk);
		entity.setTaunt(taunt);
		entity.setLevel(level);
		entity.setHp(hp);
		entity.setActivated(activated);
		return entity;
	}
	
	
}
