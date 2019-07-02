package com.cosine.cosgame.mafia;

public class Action {
	public static int KILL = 1;
	public static int SHOOT = 2;
	public static int VOTED = 3;
	public static int INSPECT = 4;
	public static int TALK = 5;
	public static int LASTWORD = 6;
	
	public int actionType;
	public int targetId;
	public String content;
	
	public Action() {}
	public Action(int actionType, int targetId) {
		this.actionType = actionType;
		this.targetId = targetId;
	}
	
	public Action(int actionType, String content) {
		this.actionType = actionType;
		this.content = content;
	}
	
}
