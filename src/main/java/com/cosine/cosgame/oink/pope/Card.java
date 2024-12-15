package com.cosine.cosgame.oink.pope;

import java.util.List;

import com.cosine.cosgame.oink.pope.entity.CardEntity;

public class Card {
	int num;
	String name;
	String img;
	String desc;
	String color;
	List<String> sound;
	
	PopePlayer player;
	PopeGame game;
	
	public CardEntity toCardEntity(){
		int i;
		CardEntity entity = new CardEntity();
		entity.setNum(num);
		entity.setName(name);
		entity.setImg(img);
		entity.setDesc(desc);
		entity.setColor(color);
		return entity;
	}
	
	public Card() {
		
	}
	
	public void onPlay() {
		
	}
	
	public void onDiscard() {
		
	}
	
	public boolean canPlay() {
		return true;
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
	
}
