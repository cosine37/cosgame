package com.cosine.cosgame.rich.gta.places;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class CardGainer extends Place {

	public CardGainer(int id, String name) {
		super(id, name, Consts.PLACE_CARDGAINER);
	}
	
	public CardGainer(Document doc, Board board) {
		super(doc,board);
	}

	public void stepOn(Player p) {
		super.stepOn(p);
		boolean f;
		int x = 0;
		f = p.addRandomCard();
		if (f) x++;
		f = p.addRandomCard();
		if (f) x++;
		
		String bcm = p.getName() + "来到了" + name;
		if (p.getStar() > 0) {
			bcm = bcm + "，失去了1点通缉值";
			p.loseStar(1);
		}
		
		board.getLogger().logGainCard(p, x);
		board.getLogger().logLoseStar(p, 1);
		
		board.setBroadcastImg("avatar/head_"+p.getAvatarId());
		if (x == 0) {
			bcm = bcm + "，但是手牌已满，并没有获得任何牌，这就尴尬了。";
		} else {
			bcm = bcm + "并获得了" + x + "张牌。";
		}
		board.setBroadcastMsg(bcm);
	}
	
	public void bypass(Player p) {
		super.bypass(p);
		if (p.fullHand() == false) {
			board.getLogger().logGainCard(p, 1);
		}
		p.addRandomCard();
	}

}
