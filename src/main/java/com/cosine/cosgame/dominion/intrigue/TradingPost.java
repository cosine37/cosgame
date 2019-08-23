package com.cosine.cosgame.dominion.intrigue;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class TradingPost extends Card{
	public TradingPost() {
		super();
		this.name = "TradingPost";
		this.image = "/image/Dominion/cards/Intrigue/TradingPost.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
	}
	
	public Ask play() {
		Ask ask = super.play();
		if (player.getHand().size() > 2) {
			ask.setType(Ask.HANDCHOOSE);
			ask.setUpper(2);
			ask.setLower(2);
			ask.setMsg("Trash 2 cards");
			return ask;
		} else {
			int x = player.getHand().size();
			while (player.getHand().size()>0) {
				player.trash(0);
			}
			if (x == 2) {
				board.gainToPlayerFromPileToHand(player, board.getPileByTop("Silver"));
				log(player.getName() + " gains a Silver to hand", 1);
			}
			ask = new Ask();
			return ask;
		}
		
	}
	
	public Ask response(Ask a) {
		player.setBoard(board);
		player.trash(a.getSelectedCards(), "hand");
		log(player.getName() + " trashes 2 cards", 1);
		board.gainToPlayerFromPileToHand(player, board.getPileByTop("Silver"));
		log(player.getName() + " gains a Silver to hand", 1);
		Ask ask = new Ask();
		return ask;
	}
}
