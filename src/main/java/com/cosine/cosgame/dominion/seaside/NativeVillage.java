package com.cosine.cosgame.dominion.seaside;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class NativeVillage extends Card{
	public NativeVillage() {
		super();
		this.name = "Native Village";
		this.image = "/image/Dominion/cards/Seaside/NativeVillage.png";
		this.types[INDEX_ACTION] = true;
		this.action =2;
		this.price = 2;
	}
	
	public Ask play() {
		Ask ask = super.play();
		ask.setType(Ask.OPTION);
		ask.setMsg("Choose one:");
		List<String> options = new ArrayList<>();
		options.add("Set aside");
		options.add("Pick up");
		ask.setOptions(options);
		ask.setAns(-1);
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			Card c = player.removeTop();
			if (c != null) {
				player.getNativeVillage().add(c);
				log(player.getName()+" puts a " + c.getName() + " to Native Village mat", 1);
			}
		} else if (ask.getAns() == 1) {
			while (player.getNativeVillage().size()>0) {
				Card c = player.getNativeVillage().remove(0);
				player.getHand().add(c);
			}
			log(player.getName()+" puts all cards from Native Village mat to hand", 1);
		}
		
		ask = new Ask();
		return ask;
	}
	
}
