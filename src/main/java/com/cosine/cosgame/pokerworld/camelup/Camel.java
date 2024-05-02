package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.List;

public class Camel extends Grid {
	int color;
	
	boolean crazy;
	
	Camel camelOn;
	Camel camelUnder;
	Camel topCamel;
	Camel bottomCamel;
	
	public Camel(int color) {
		super();
		this.color = color;
		
		crazy = false;
		
		camelOn = null;
		camelUnder = null;
		topCamel = this;
		bottomCamel = this;
	}
	
	public Camel(int color, boolean crazy) {
		this(color);
		this.crazy = crazy;
	}
	
	public void updatePos(int newPos) {
		pos = newPos;
		if (camelOn != null) {
			camelOn.updatePos(newPos);
		}
	}
	
	public void updateBottomCamel(Camel c) {
		bottomCamel = c;
		if (camelOn != null) {
			camelOn.updateBottomCamel(c);
		}
	}
	
	public void updateTopCamel(Camel c) {
		topCamel = c;
		if (camelOn != null) {
			camelOn.updateTopCamel(c);
		}
	}
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Camel getCamelOn() {
		return camelOn;
	}
	public void setCamelOn(Camel camelOn) {
		this.camelOn = camelOn;
	}
	public Camel getCamelUnder() {
		return camelUnder;
	}
	public void setCamelUnder(Camel camelUnder) {
		this.camelUnder = camelUnder;
	}
	public Camel getTopCamel() {
		return topCamel;
	}
	public void setTopCamel(Camel topCamel) {
		this.topCamel = topCamel;
	}
	public int getType() {
		return Consts.CAMEL;
	}
	public Camel getBottomCamel() {
		return bottomCamel;
	}
	public void setBottomCamel(Camel bottomCamel) {
		this.bottomCamel = bottomCamel;
	}
	public boolean isCrazy() {
		return crazy;
	}
	public void setCrazy(boolean crazy) {
		this.crazy = crazy;
	}

	public List<Integer> toList(){
		List<Integer> ans = new ArrayList<>();
		ans.add(color);
		if (camelOn != null) {
			ans.addAll(camelOn.toList());
		}
		return ans;
	}
	
	public void printInfo() {
		int bottomColor = 0;
		if (bottomCamel != null) {
			bottomColor = bottomCamel.color;
		}
		int topColor = 0;
		if (topCamel != null) {
			topColor = topCamel.color;
		}
		
		int onColor = 0;
		if (camelOn != null) {
			onColor = camelOn.color;
		}
		int underColor = 0;
		if (camelUnder != null) {
			underColor = camelUnder.color;
		}
		System.out.println("Color: " +  color + " topColor: " + topColor + " bottomColor: " + bottomColor + " onColor: " + onColor + " underColor: " + underColor);
	}
}
