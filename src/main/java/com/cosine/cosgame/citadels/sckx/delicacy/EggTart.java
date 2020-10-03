package com.cosine.cosgame.citadels.sckx.delicacy;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Ask;
import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.Delicacy;
import com.cosine.cosgame.citadels.Player;

public class EggTart extends Delicacy{
	public EggTart() {
		name = "葡式蛋挞";
		img = "d301";
		cost = 3;
	}
	
	public boolean canBuy(Player p) {
		if (p.getCanBuyDelicacy().get(index).contentEquals("n")) {
			return false;
		}
		if (p.getCoin() >= 3) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Ask onBuy(Player p) {
		Ask ask = super.onBuy(p);
		if (p.getCanBuyDelicacy().get(index).contentEquals("y")) {
			if (p.getCoin() >= 3) {
				p.getCanBuyDelicacy().set(index, "n");
				board.log(p.getName() + "花费了3￥购买了葡式蛋挞。");
				Card revealedCard = board.getDeck().remove(0);
				List<String> tempRevealedTop = new ArrayList<>();
				tempRevealedTop.add(revealedCard.getImg());
				board.setTempRevealedTop(tempRevealedTop);
				board.log(p.getName() + "展示了 " + revealedCard.getName() + " 。");
				int c = p.getCoin()-3+revealedCard.getCost();
				p.setCoin(c);
				board.log(p.getName() + "获得了" + Integer.toString(revealedCard.getCost()) + "￥。");
				board.bottomDeck(revealedCard);
				board.log(p.getName() + "将 " + revealedCard.getName() + " 置于牌堆底。");
			}
		}
		
		return ask;
	}
}
