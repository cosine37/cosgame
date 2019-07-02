package com.cosine.cosgame.mafia;

public class Player {
	private String name;
	private int role;
	private int side;
	private int number;
	private int target;
	private int	x;
	private boolean alive;
	private boolean lastword;
	private boolean bot;
	private boolean emptySlot;
	
	public Player() {
		this.alive = true;
		this.emptySlot = true;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void assignBot(String name) {
		this.name = name;
		this.bot = true;
		this.emptySlot = false;
	}
	
	public void assignPlayer(String name) {
		this.name = name;
		this.bot = false;
		this.emptySlot = false;
	}
	
	public void setRole(int role) {
		this.role = role;
		if (role == Role.FATTY) x=1; //not killed yet
		if (role == Role.SNIPER) x=1; //not used bullet yet
		
	}
	
	public void setRole(int role, int x) {
		this.role = role;
		this.x = x;
	}
	
	public int getRole() {
		return this.role;
	}
	
	public boolean isAlive() {
		return this.alive;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public boolean isBot() {
		return this.bot;
	}
	
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	
	public boolean isEmpty() {
		return this.emptySlot;
	}
	
	public void setEmpty(boolean emptySlot) {
		this.emptySlot = emptySlot;
	}
	
	public int killed() {
		if (role == Role.FATTY && x == 1) {
			x = 0;
			return 0;
		}
		if (role == Role.SNIPER && x == 1) {
			return 0;
		}
		lastword = true;
		alive = false;
		if (role == Role.BUTTERFLY) {
			return target;
		}
		return 0;
	}
	
	public int shot() {
		lastword = true;
		alive = false;
		if (role == Role.BUTTERFLY) {
			return target;
		}
		return 0;
	}
	
	public void voted() {
		lastword = true;
		alive = false;
	}
	
}
