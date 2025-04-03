package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cosine.cosgame.rich.entity.CardEntity;

public class Card {
	protected int id;
	protected String name;
	protected String desc;
	protected List<Boolean> types;
	protected Player player;
	protected Board board;
	protected int level;
	protected boolean exhaust;
	protected int rarity;
	protected int playStyle;
	
	public CardEntity toCardEntity(){
		CardEntity entity = new CardEntity();
		entity.setId(id);
		entity.setLevel(level);
		entity.setRarity(rarity);
		entity.setName(name);
		entity.setDesc(parseDesc());
		entity.setTypes(types);
		HashMap<String, String> imgStyle = new HashMap<>();
		imgStyle.put("background-image", "url(/image/Rich/card/" + id + ".png)");
		imgStyle.put("background-size", "cover");
		entity.setImgStyle(imgStyle);
		entity.setPlayable(playable());
		entity.setPlayStyle(playStyle);
		return entity;
	}
	
	public Card() {
		level = 0;
		types = new ArrayList<>();
		exhaust = true;
		rarity = 0;
		playStyle = Consts.PLAYSTYLE_DIRECT;
		
		for (int i=0;i<10;i++) types.add(false);
		types.set(0, true);
	}
	
	public void play(int rawOptions) {
		
	}
	
	public boolean playable() {
		return false;
	}
	
	public void passive() {
		types.set(0, false);
		types.set(1, true);
	}
	
	public boolean isPassive() {
		return types.get(1);
	}
	
	public void onLoseMoney(int x) {}
	
	public int wardFeeDeduction() {return 0;}
	
	String smartReplace(String s, String s1, String s2) {
		if (s == null) return null;
		int i=0;
		int n = s.length();
		int x = s1.length();
		String ans = "";
		while (i<n) {
			if (i+x > n) {
				ans = ans+s.charAt(i);
				i++;
				
			} else {
				String ts = s.substring(i,i+x);
				if (ts.contentEquals(s1)){
					ans = ans + s2;
					i = i+x;
				} else {
					ans = ans + s.charAt(i);
					i++;
				}
			}
		}
		return ans;
	}
	
	public String parseDesc() {
		String ans = getDesc();
		
		//ans = smartReplace(ans,"s","<span style='width: 20px; height:20px; background-image=url(/image/Rich/star1.png); background-size:cover'></span>");
		ans = smartReplace(ans,"starP","<img src='/image/Rich/star1.png' style='width: 18px; height:22px;padding-bottom:4px'>");
		ans = smartReplace(ans,"healthP","<img src='/image/Rich/hp1.png' style='width: 20px; height:22px;padding-bottom:4px;padding-left: 2px;'>");
		
		ans = smartReplace(ans,"消耗","<b style='color:darkorange'>消耗</b>");
		ans = smartReplace(ans,"载具","<b style='color:darkblue'>载具</b>");
		ans = smartReplace(ans,"成功率：","<b>成功率：</b>");
		
		return ans;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Boolean> getTypes() {
		return types;
	}
	public void setTypes(List<Boolean> types) {
		this.types = types;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isExhaust() {
		return exhaust;
	}
	public void setExhaust(boolean exhaust) {
		this.exhaust = exhaust;
	}
	public int getRarity() {
		return rarity;
	}
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	public int getPlayStyle() {
		return playStyle;
	}
	public void setPlayStyle(int playStyle) {
		this.playStyle = playStyle;
	}
}
