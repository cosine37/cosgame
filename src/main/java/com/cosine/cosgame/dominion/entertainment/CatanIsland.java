package com.cosine.cosgame.dominion.entertainment;

import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;

public class CatanIsland extends Card {
	public CatanIsland() {
		super();
		this.name = "Catan Island";
		this.image = "/image/Dominion/cards/Entertainments/CatanIsland.png";
		this.types[INDEX_ACTION] = true;
		this.types[INDEX_VICTORY] = true;
		this.price = 4;
		this.score = 1;
	}
	
	public Ask play() {
		Ask ask = super.play();
		Ask a = player.rollDice(1);
		if (a.getType() == Ask.NONE) {
			int result = player.getDiceResults().get(0);
			log(player.getName() + " rolls " + result, 1);
			List<String> cns = board.getCardnamesWithPrice(result, player.getPriceReduce());
			if (cns.size() == 0) {
				
			} else if (cns.size() == 1){
				board.gainToPlayerFromPile(player, board.getPileByTop(cns.get(0)));
				log(player.getName() + " gains a " + cns.get(0), 1);
			} else {
				ask.setType(Ask.GAIN);
				ask.setMsg("You rolled " + result + ", gain a card costing exactly $" + result);
				ask.setUpper(result);
				ask.setLower(result);
			}
		} else {
			
		}
		return ask;
	}
	
	public Ask response(Ask a) {
		Ask ask = super.response(a);
		String gainedCardName = ask.getSelectedCards().get(0);
		log(player.getName() + " gains a " + gainedCardName, 1);
		board.gainToPlayerFromPile(player, board.getPileByTop(gainedCardName));
		ask = new Ask();
		return ask;
	}

}
