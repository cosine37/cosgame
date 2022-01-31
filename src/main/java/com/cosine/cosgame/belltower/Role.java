package com.cosine.cosgame.belltower;

import java.util.List;

import com.cosine.cosgame.belltower.entity.RoleEntity;

public class Role {
	protected int id;
	protected String name;
	protected String img;
	protected String desc;
	protected int faction;
	protected int group;
	protected boolean drunk;
	
	protected Player player;
	protected Board board;
	
	public Role() {
		
	}
	
	public void execSkill() {
		
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
	
	public RoleEntity toRoleEntity() {
		RoleEntity entity = new RoleEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setDesc(desc);
		entity.setImg(img);
		return entity;
	}
}
