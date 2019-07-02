package com.cosine.cosgame.mafia;

public class Role {
	public static int INNOCENT = 0;
	public static int MAFIA = 1;
	public static int POLICE = 2;
	public static int BUTTERFLY = 3;
	public static int FATTY = 4;
	public static int SNIPER = 5;
	public static int IDIOT = 6;
	public static int TERRORIST = 7;
	public static int FORENSIC = 8;
	public static int TWOLADIES = 9;
	public static int THREEGUYS = 10;
	
	public static String getNameEN(int id) {
		String names[] = {"Innocent", "Mafia", "Police", "Butterfly", "Fatty", "Sniper", "Idiot", "Terrorist", "Forensic", "One of the Two Ladies", "One of the Three Guys"};
		return names[id];
	}
	
	public static String getNameCN(int id) {
		String names[] = {"平民", "杀手", "警察", "花蝴蝶", "死胖子", "狙击手", "白痴", "恐怖分子", "法医", "两姐妹之一", "三兄弟之一"};
		return names[id];
	}
}
