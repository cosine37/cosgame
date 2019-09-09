package com.cosine.cosgame.dominion.oriental;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Buff;
import com.cosine.cosgame.dominion.Card;

public class PiscesJade extends Card{
	public PiscesJade() {
		super();
		this.name = "PiscesJade";
		this.image = "/image/Dominion/cards/Oriental/PiscesJade.png";
		this.types[INDEX_TREASURE] = true;
		this.coin = 2;
		this.price = 5;
		this.autoplay = false;
	}
	
	public int inPlay() {
		return Buff.GAINCURSETOCOPY;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		if (ask.getAns() == 0) {
			log(player.getName() + " opens Pisces Jade", 1);
			log(player.getName() + " gains a Curse", 1);
			board.gainToPlayerFromPile(player, board.getPileByTop("Curse"));
			String ts = "You may gain a Curse to gain another copy of ";
			String cardname = ask.getMsg().substring(ts.length());
			log(player.getName() + " gains a " + cardname, 1);
			board.gainToPlayerFromPile(player, board.getPileByTop(cardname));
		}
		ask = new Ask();
		return ask;
	}
}
