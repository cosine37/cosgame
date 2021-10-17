package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.List;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.common.Card.CardRank;
import com.cosgame.sfsj.common.Card.CardSuit;
import com.cosgame.sfsj.play.Game;
import com.cosgame.sfsj.util.CardUtils;

public class GameUtil {
	Board board;
	Game game;
	
	public GameUtil() {
		game = new Game(null, 0);
	}
	
	public void newGame() {
		game = new Game(null, 0);
		List<List<Card>> playerCards = game.getPlayerCards();
		int i;
		for (i=0;i<playerCards.size();i++) {
			playerCards.get(i).sort(CardUtils.cardComparator(CardRank.TWO, CardSuit.SPADE));
		}
		game.setPlayerCards(playerCards);
	}
	
	public void buildCards(List<String> rawCards) {
		int i,j;
		List<List<Card>> playerCards = new ArrayList<>();
		for (i=0;i<rawCards.size();i++) {
			List<Card> singlePlayerCards = new ArrayList<>();
			j = 0;
			while (j<rawCards.get(i).length()) {
				String rawCard = rawCards.get(i).substring(j,j+2);
				Card card = CardUtils.deSerializeCard(rawCard);
				singlePlayerCards.add(card);
				j = j+2;
			}
			playerCards.add(singlePlayerCards);
		}
		game.setPlayerCards(playerCards);
	}
	
	public List<String> toRawCards() {
		List<String> rawCards = new ArrayList<>();
		int i,j;
		for (i=0;i<game.getPlayerCards().size();i++) {
			String singlePlayerCards = "";
			for (j=0;j<game.getPlayerCards().get(i).size();j++) {
				Card c = game.getPlayerCards().get(i).get(j);
				singlePlayerCards = singlePlayerCards + CardUtils.serializeCard(c);
			}
			rawCards.add(singlePlayerCards);
		}
		return rawCards;
	}
	
	public String playerRawCards(int x) {
		return toRawCards().get(x);
	}

	public Game getGame() {
		return game;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
}
