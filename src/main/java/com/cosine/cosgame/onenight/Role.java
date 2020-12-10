package com.cosine.cosgame.onenight;

public class Role {
	protected int roleNum;
	protected int side;
	protected int sequence;
	protected String name;
	protected String img;
	
	protected Player player;
	protected Board board;
	
	public Role() {
		
	}
	
	public void vision() {
		
	}
	
	public void useSkill(int t1) {
		
	}
	
	public void useSkill(int t1, int t2) {
		
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
	
	
}
