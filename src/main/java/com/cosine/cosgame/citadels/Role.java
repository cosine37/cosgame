package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Role {
	protected int num;
	protected String name;
	protected String img;
	protected int owner;
	protected int numSkills;
	protected boolean destroyable;
	protected Board board;
	protected Player player;
	protected List<String> buttonNames;
	
	public Role() {
		destroyable = true;
		buttonNames = new ArrayList<>();
	}
	
	public void whenReveal() {
		if (num == board.getStealedRole()) {
			Player p = board.getPlayerByRoleImg("002"); // Thief handle
			if (p != null) {
				board.log(player.getName() + "'s coin has been stealed");
				p.addCoin(player.getCoin());
				player.setCoin(0);
			}
		}
	}
	
	public Ask chooseSkill(int x) {
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3, int p4) {
		Ask ask = new Ask();
		
		if (player != null) {
			player.useRoleSkill(x);
		}
		
		return ask;
	}
	
	public Ask useSkill(int x, int p1, int p2, int p3) {
		return useSkill(x, p1, p2, p3, 0);
	}
	
	public Ask useSkill(int x, int p1, int p2) {
		return useSkill(x, p1, p2, 0, 0);
	}
	
	public Ask useSkill(int x, int p1) {
		return useSkill(x, p1, 0, 0, 0);
	}
	
	public Ask useSkill(int x) {
		return useSkill(x, 0, 0, 0, 0);
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
	public List<String> getButtonNames() {
		return buttonNames;
	}
	public void setButtonNames(List<String> buttonNames) {
		this.buttonNames = buttonNames;
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
