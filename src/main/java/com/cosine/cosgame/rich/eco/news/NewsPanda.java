package com.cosine.cosgame.rich.eco.news;

import java.util.Random;

import com.cosine.cosgame.rich.Card;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.eco.News;
import com.cosine.cosgame.rich.gta.cards.CardPanda;

public class NewsPanda extends News {
	public NewsPanda() {
		super();
		id = 9;
		desc = "听众朋友们大家好，马后炮电台为您播报昨天的旧闻，昨天下午国宝在我市展览时被盗，所有玩家增加2点通缉值。本台专家表示他是冤枉的，因为他昨天上午没有去偷，希望盗贼早日自首以证明本台专家的清白，谢谢收听！";
		logDesc = "昨天下午国宝在我市展览时被盗，所有玩家增加2点通缉值";
	}
	
	public void effect() {
		super.effect();
		int i;
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			p.addStar(2);
		}
		
		Random rand = new Random();
		Card c = new CardPanda();
		int x = rand.nextInt(board.getPlayers().size());
		for (i=0;i<board.getPlayers().size();i++) {
			int y = x+i;
			if (y>=board.getPlayers().size()) y = y-board.getPlayers().size();
			if (!board.getPlayers().get(y).fullHand()) {
				board.getPlayers().get(y).addCard(c);
				break;
			}
		}
		
	}
}
