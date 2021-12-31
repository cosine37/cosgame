package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.entity.CardEntity;

public class Card {
	protected String name;
	protected String courtesy;
	protected String img;
	protected String desc;
	protected String title;
	protected int han;
	protected int wei;
	protected boolean blankSpace;
	protected List<Integer> extraBits;
	protected int where;
	protected int faction;
	protected boolean emulator;
	protected int emulateType;
	protected Player player;
	protected Board board;
	
	protected int playType;
	protected int playSubType;
	protected List<String> options;
	protected List<String> options2;
	protected String instruction;
	
	public Card() {
		han = 0;
		wei = 0;
		blankSpace = false;
		playType = 0;
		playSubType = 0;
		emulator = false;
		extraBits = new ArrayList<>();
		options = new ArrayList<>();
		options2 = new ArrayList<>();
	}
	
	public void play(List<Integer> targets) {
		board.moveHan(han);
		board.moveWei(wei);
	}
	public String getTitleDisplay(Player p) {
		if (p == null) {
			return title;
		}
		if (p.getAccount() == null) {
			return title;
		}
		Skin s = p.getAccount().findSkinByImg(img);
		if (s == null) {
			return title;
		} else {
			return s.getTitle();
		}
	}
	public String getImgDisplay(Player p) {
		if (p == null) {
			return img;
		} else if (p.getAccount() == null) {
			return img;
		}
		
		Skin s = p.getAccount().findSkinByImg(img);
		if (s == null) {
			return img;
		} else {
			return s.getNewImg();
		}
	}
	public boolean isFaction(int x) {
		if (x == faction) {
			return true;
		} else {
			return false;
		}
	}
	public String getDescDisplay() {
		return desc;
	}
	public List<String> getOptionsDisplay() {
		return options;
	}
	public List<String> getOptions2Display() {
		return options2;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getHan() {
		return han;
	}
	public void setHan(int han) {
		this.han = han;
	}
	public int getWei() {
		return wei;
	}
	public void setWei(int wei) {
		this.wei = wei;
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
	public boolean isBlankSpace() {
		return blankSpace;
	}
	public void setBlankSpace(boolean blankSpace) {
		this.blankSpace = blankSpace;
	}
	public List<Integer> getExtraBits() {
		return extraBits;
	}
	public void setExtraBits(List<Integer> extraBits) {
		this.extraBits = extraBits;
	}
	public int getWhere() {
		return where;
	}
	public void setWhere(int where) {
		this.where = where;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCourtesy() {
		return courtesy;
	}
	public void setCourtesy(String courtesy) {
		this.courtesy = courtesy;
	}
	public int getPlayType() {
		return playType;
	}
	public void setPlayType(int playType) {
		this.playType = playType;
	}
	public int getPlaySubType() {
		return playSubType;
	}
	public void setPlaySubType(int playSubType) {
		this.playSubType = playSubType;
	}
	public List<String> getOptions() {
		return options;
	}
	public void setOptions(List<String> options) {
		this.options = options;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public List<String> getOptions2() {
		return options2;
	}
	public void setOptions2(List<String> options2) {
		this.options2 = options2;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("img", img);
		//doc.append("where", where);
		doc.append("extraBits", extraBits);
		return doc;
	}
	
	public CardEntity toCardEntity(Player p) {
		CardEntity entity = new CardEntity();
		entity.setName(name);
		//entity.setImg(img);
		entity.setImg(getImgDisplay(p));
		entity.setCourtesy(courtesy);
		//entity.setTitle(title);
		entity.setTitle(getTitleDisplay(p));
		entity.setFaction(faction);
		entity.setDesc(desc);
		entity.setOptions(options);
		entity.setOptions2(options2);
		entity.setBlankSpace(blankSpace);
		entity.setPlayType(playType);
		entity.setPlaySubType(playSubType);
		entity.setEmulator(emulator);
		entity.setEmulateType(emulateType);
		if (where == Consts.HAND) {
			entity.setDesc(getDescDisplay());
			entity.setOptions(getOptionsDisplay());
			entity.setOptions2(getOptions2Display());
		}
		entity.setInstruction(instruction);
		return entity;
	}
	
	public CardEntity toCardEntity() {
		return toCardEntity(null);
	}
	
}
