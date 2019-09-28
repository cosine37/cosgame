package com.cosine.cosgame.dominion.dominion;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class CouncilRoom extends Card{
	public CouncilRoom() {
		super();
		this.name = "Council Room";
		this.image = "/image/Dominion/cards/Dominion/CouncilRoom.png";
		this.types[INDEX_ACTION] = true;
		this.price = 5;
		this.card = 4;
		this.buy = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		for (int i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).getName().equals(player.getName())) {
				
			} else {
				board.getPlayers().get(i).draw(1);
				log("Each other player draws a card", 1);
			}
		}
		return ask;
	}
}
