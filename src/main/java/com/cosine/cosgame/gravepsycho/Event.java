package com.cosine.cosgame.gravepsycho;

public class Event {
	protected int num;
	protected String name;
	protected String desc;
	protected String img;
	protected Board board;
	
	public Event() {
		
	}
	public EventEntity toEventEntity() {
		EventEntity entity = new EventEntity();
		entity.setNum(num);
		entity.setName(name);
		entity.setImg(img);
		entity.setDesc(desc);
		return entity;
	}
	public boolean disaster(boolean f) {return f;}
	public boolean distributeCoins(int x) {return false;}
	public boolean backHandle(Player p) {return false;}
	public boolean singleBackHandle(Player p) {return false;}
	public boolean revealNext() {return false;}
	public void onReveal(Card c) {}
	public String cardImg(Card c) {return c.getImage();}
	public boolean overrideLogReveal(Card c) {return false;}
	public void newRound() {}
	public void stealSuccessful(Player p) {}
	public boolean disasterHandle(Player p) {return false;}
	public void singleStealHandle(Player p) {}
	public void preBackStealHandle() {}

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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
