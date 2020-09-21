package com.cosine.cosgame.citadels.sckx.omnicolor;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.citadels.Card;
import com.cosine.cosgame.citadels.CitadelsConsts;

public class Guesthouse extends Card {
	public Guesthouse() {
		super();
		name = "招待所";
		cost = 1;
		img = "o11p";
		color = CitadelsConsts.PURPLE;
		expansion = 3;
	}
	
	public void beforeTakeActionEffect() {
		Card revealedCard = board.getDeck().remove(0);
		String name = player.getName();
		board.log(name + "发动了建筑 招待所 的特效。");
		List<String> tempRevealedTop = new ArrayList<>();
		tempRevealedTop.add(revealedCard.getImg());
		board.setTempRevealedTop(tempRevealedTop);
		board.log(name + "展示了 " + revealedCard.getName() + " 。");
		int color = revealedCard.getColor();
		if (color == CitadelsConsts.GREEN) {
			this.color = color;
			this.img = "o11g";
			board.log(name + "的 招待所 变成了 商业类型 。");
		} else if (color == CitadelsConsts.BLUE) {
			this.color = color;
			this.img = "o11b";
			board.log(name + "的 招待所 变成了 教育类型 。");
		} else if (color == CitadelsConsts.RED) {
			this.color = color;
			this.img = "o11r";
			board.log(name + "的 招待所 变成了 治安类型 。");
		} else if (color == CitadelsConsts.YELLOW) {
			this.color = color;
			this.img = "o11y";
			board.log(name + "的 招待所 变成了 市政类型 。");
		} else if (color == CitadelsConsts.PURPLE) {
			this.color = color;
			this.img = "o11p";
			board.log(name + "的 招待所 变成了 特殊类型 。");
		}
		board.bottomDeck(revealedCard);
		board.log(name + "将 " + revealedCard.getName() + " 置于牌堆底。");
	}
}
