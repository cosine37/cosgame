package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cosine.cosgame.rich.entity.CardEntity;

public class Card {
	protected int id;
	protected String name;
	protected String desc;
	protected List<Boolean> types;
	protected Player player;
	protected Board board;
	protected int level;
	protected boolean exhaust;
	
	public Card() {
		level = 0;
		types = new ArrayList<>();
		exhaust = true;
	}
	
	public void play(int rawOptions) {
		
	}
	
	public CardEntity toCardEntity(){
		CardEntity entity = new CardEntity();
		entity.setId(id);
		entity.setLevel(level);
		entity.setName(name);
		entity.setDesc(desc);
		entity.setTypes(types);
		HashMap<String, String> imgStyle = new HashMap<>();
		imgStyle.put("background-image", "url(/image/Rich/card/" + id + ".png)");
		imgStyle.put("background-size", "cover");
		entity.setImgStyle(imgStyle);
		entity.setPlayable(playable());
		return entity;
	}
	
	public boolean playable() {
		return false;
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
	public List<Boolean> getTypes() {
		return types;
	}
	public void setTypes(List<Boolean> types) {
		this.types = types;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isExhaust() {
		return exhaust;
	}
	public void setExhaust(boolean exhaust) {
		this.exhaust = exhaust;
	}
}
