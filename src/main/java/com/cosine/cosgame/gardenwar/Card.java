package com.cosine.cosgame.gardenwar;

import java.util.ArrayList;
import java.util.List;

public class Card {
	// card basic info
	int id;
	String name;
	String img;
	int cost;
	int type;
	List<Integer> clan;
	
	// card bonuses
	int sun;
	int atk;
	int def;
	int draw;
	
	Player player;
	Board board;
	
	boolean taunt;
	boolean autoplay;
	
	public Card() {
		taunt = false;
		autoplay = true;
		type = Consts.CARD;
		clan = new ArrayList<>();
		int i;
		for (i=0;i<Consts.NUMCLANS;i++) {
			clan.add(0);
		}
	}
	
	public void play() {
		
	}
	public void startTurn() {
		
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
	
	
	
}
