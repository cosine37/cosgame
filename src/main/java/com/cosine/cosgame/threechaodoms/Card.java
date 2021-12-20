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
	protected Player player;
	protected Board board;
	
	protected int playType;
	protected int playSubType;
	protected List<String> options;
	protected String instruction;
	
	public Card() {
		han = 0;
		wei = 0;
		blankSpace = false;
		playType = 0;
		playSubType = 0;
		extraBits = new ArrayList<>();
		options = new ArrayList<>();
	}
	
	public void play(List<Integer> targets) {
		board.moveHan(han);
		board.moveWei(wei);
	}
	public String getDescDisplay() {
		return desc;
	}
	public List<String> getOptionsDisplay() {
		return options;
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

	public Document toDocument() {
		Document doc = new Document();
		doc.append("img", img);
		//doc.append("where", where);
		doc.append("extraBits", extraBits);
		return doc;
	}
	
	public CardEntity toCardEntity() {
		CardEntity entity = new CardEntity();
		entity.setName(name);
		entity.setImg(img);
		entity.setCourtesy(courtesy);
		entity.setTitle(title);
		entity.setFaction(faction);
		entity.setDesc(desc);
		entity.setBlankSpace(blankSpace);
		entity.setPlayType(playType);
		entity.setPlaySubType(playSubType);
		if (where == Consts.HAND) {
			entity.setDesc(getDescDisplay());
		}
		entity.setOptions(getOptionsDisplay());
		entity.setInstruction(instruction);
		return entity;
	}
	
}
