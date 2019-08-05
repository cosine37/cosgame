package com.cosine.cosgame.dominion.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class Moneylender extends Card{
	public Moneylender() {
		super();
		this.name = "Moneylender";
		this.image = "/image/Dominion/cards/Dominion/Moneylender.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.has("Copper", "hand")) {
			ask.setType(Ask.OPTION);
			String msg = "You may trash a Copper from your hand";
			List<String> options = new ArrayList<String>();
			options.add("Trash Copper");
			options.add("Don't Trash");
			int ans = -1;
			ask.setMsg(msg);
			ask.setOptions(options);
			ask.setAns(ans);
		} else {
			
		}
		//System.out.println("ask.type=" + ask.getType());
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (a.getAns() == 0) {
			List<String> ls = new ArrayList<String>();
			ls.add("Copper");
			player.trash(ls, "hand");
			player.addCoin(3);
		} else {
			
		}
		ask.setType(Ask.NONE);
		return ask;
	}
}
