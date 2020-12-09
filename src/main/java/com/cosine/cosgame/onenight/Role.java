package com.cosine.cosgame.onenight;

public class Role {
	int roleNum;
	int side;
	String name;
	String img;
	
	Player player;
	Board board;
	
	public void useSkill(int t1) {
		
	}
	
	public void useSkill(int t1, int t2) {
		
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
	
	
}
