package com.cosine.cosgame.onenight;

import java.util.List;

public class Role {
	protected int roleNum;
	protected int side;
	protected int sequence;
	protected String name;
	protected String img;
	
	protected int choosePlayerNum;
	protected int chooseCenterNum;
	protected boolean canChooseBoth;
	protected boolean hasNight;
	protected boolean mandatory;
	
	protected Player player;
	protected Board board;
	
	public Role() {
		choosePlayerNum = 0;
		chooseCenterNum = 0;
		canChooseBoth = false;
		hasNight = false;
		mandatory = false;
	}
	
	public void vision() {
		
	}
	
	public void useSkill(int t1) {
		
	}
	
	public void useSkill(int t1, int t2) {
		
	}
	
	public void useSkill(List<Integer> targets) {
		if (targets.size() == 1) {
			useSkill(targets.get(0));
		} else if (targets.size() == 2) {
			useSkill(targets.get(0), targets.get(1));
		}
	}
	
	public void executeSkill() {
		
	}
	
	public int getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	public int getChoosePlayerNum() {
		return choosePlayerNum;
	}
	public void setChoosePlayerNum(int choosePlayerNum) {
		this.choosePlayerNum = choosePlayerNum;
	}
	public int getChooseCenterNum() {
		return chooseCenterNum;
	}
	public void setChooseCenterNum(int chooseCenterNum) {
		this.chooseCenterNum = chooseCenterNum;
	}
	public boolean isCanChooseBoth() {
		return canChooseBoth;
	}
	public void setCanChooseBoth(boolean canChooseBoth) {
		this.canChooseBoth = canChooseBoth;
	}
	public boolean isHasNight() {
		return hasNight;
	}
	public void setHasNight(boolean hasNight) {
		this.hasNight = hasNight;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}
	
	
}
