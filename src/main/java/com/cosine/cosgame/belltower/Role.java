package com.cosine.cosgame.belltower;

public class Role {
	int id;
	String name;
	String img;
	String desc;
	int faction;
	int group;
	boolean drunk;
	
	Player player;
	Board board;
	
	public Role() {
		
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public boolean isDrunk() {
		return drunk;
	}
	public void setDrunk(boolean drunk) {
		this.drunk = drunk;
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
}
