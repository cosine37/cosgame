package com.cosine.cosgame.zodiac;

public class Role {
	protected int num;
	protected String img;
	protected int side;
	protected String name;
	protected Player player;
	protected Board board;
	protected int numPlayerChoose;
	protected int numZodiacChoose;
	protected int numHigherChoose;
	
	public void initialize() {
		
	}
	
	public void vision() {
		
	}
	
	public void disable() {
		player.setDisabled(true);
	}
	
	public void useSkill() {
		
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
	public int getNumPlayerChoose() {
		return numPlayerChoose;
	}
	public void setNumPlayerChoose(int numPlayerChoose) {
		this.numPlayerChoose = numPlayerChoose;
	}
	public int getNumZodiacChoose() {
		return numZodiacChoose;
	}
	public void setNumZodiacChoose(int numZodiacChoose) {
		this.numZodiacChoose = numZodiacChoose;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getNumHigherChoose() {
		return numHigherChoose;
	}
	public void setNumHigherChoose(int numHigherChoose) {
		this.numHigherChoose = numHigherChoose;
	}
}
