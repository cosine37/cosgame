package com.cosine.cosgame.oink.pope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cosine.cosgame.oink.pope.entity.CardEntity;

public class Card {
	protected int num;
	protected int type;
	protected String name;
	protected String img;
	protected String desc;
	protected String color;
	protected List<String> sound;
	
	protected PopePlayer player;
	protected PopeGame game;
	
	public CardEntity toCardEntity(){
		int i;
		CardEntity entity = new CardEntity();
		entity.setNum(num);
		entity.setName(name);
		entity.setType(type);
		entity.setImg(img);
		entity.setDescription(desc);
		entity.setColor(color);
		entity.setCanPlay(canPlay());
		
		Map<String, String> avatarStyle = new HashMap<>();
		Map<String, String> primaryColor = new HashMap<>();
		Map<String, String> secondaryColor = new HashMap<>();
		
		avatarStyle.put("background-image", "url(/image/Oink/Pope/" + img + ".png)");
		avatarStyle.put("background-size", "cover");
		primaryColor.put("background-color", color);
		secondaryColor.put("background-color", getSecondaryColor());
		
		entity.setAvatarStyle(avatarStyle);
		entity.setPrimaryColor(primaryColor);
		entity.setSecondaryColor(secondaryColor);
		entity.setCstyle(new HashMap<>());
		
		return entity;
	}
	
	public Card() {
		color = "rgb(0,150,255)";
		type = Consts.CTYPE_NONE;
	}
	
	public void onPlay(int target) {
		if (player.getPhase() == Consts.PLAYCARD) {
			player.setTarget(target);
		}
	}
	
	public void onDiscard() {
		
	}
	
	public void onTargetConfirm() {
		
	}
	
	public void onResolve(int val) {
		
	}
	
	public boolean canPlay() {
		return true;
	}
	
	public String getSecondaryColor() {
		String ans = "";
		if (color.contentEquals("rgb(0,150,255)")) {
			ans = "PaleTurquoise";
		} else if (color.contentEquals("rgb(126,33,18)")) {
			ans = "Wheat";
		} else if (color.contentEquals("DarkGreen")) {
			ans = "PaleGreen";
		} else if (color.contentEquals("goldenrod")) {
			ans = "PaleGoldenRod";
		}
		return ans;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<String> getSound() {
		return sound;
	}
	public void setSound(List<String> sound) {
		this.sound = sound;
	}
	public PopePlayer getPlayer() {
		return player;
	}
	public void setPlayer(PopePlayer player) {
		this.player = player;
	}
	public PopeGame getGame() {
		return game;
	}
	public void setGame(PopeGame game) {
		this.game = game;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
