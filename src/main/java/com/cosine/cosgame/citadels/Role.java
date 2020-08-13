package com.cosine.cosgame.citadels;

import org.bson.Document;

public class Role {
	int num;
	String name;
	String img;
	int owner;
	int numSkills;
	boolean destroyable;
	Board board;
	Player player;
	
	public Role() {
		destroyable = true;
	}
	
	public void whenReveal() {
		if (num == board.getStealedRole()) {
			Player p = board.getPlayerByRole(CitadelsConsts.THIEF);
			if (p != null) {
				p.addCoin(player.getCoin());
				player.setCoin(0);
			}
		}
	}
	
	public void useSkill(int x, int p1, int p2, int p3, int p4) {
		
	}
	
	public void useSkill(int x, int p1, int p2, int p3) {
		useSkill(x, p1, p2, p3, 0);
	}
	
	public void useSkill(int x, int p1, int p2) {
		useSkill(x, p1, p2, 0, 0);
	}
	
	public void useSkill(int x, int p1) {
		useSkill(x, p1, 0, 0, 0);
	}
	
	public void useSkill(int x) {
		useSkill(x, 0, 0, 0, 0);
	}
	
	
	// for No.6, No.7
	public void afterTakeAction() {
		
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public int getNumSkills() {
		return numSkills;
	}
	public void setNumSkills(int numSkills) {
		this.numSkills = numSkills;
	}
	public boolean isDestroyable() {
		return destroyable;
	}
	public void setDestroyable(boolean destroyable) {
		this.destroyable = destroyable;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	public int getOwner() {
		return owner;
	}
	public void setOwner(int owner) {
		this.owner = owner;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("num", num);
		doc.append("img", img);
		doc.append("name", name);
		doc.append("owner", owner);
		return doc;
	}
	
}
