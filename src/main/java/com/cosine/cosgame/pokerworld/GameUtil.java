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
	List<List<Integer>> sequences;
	
	public GameUtil() {
		game = new Game(null, 0);
	}
	
	public void newGame() {
		game = new Game(null, 0);
	}
	
	public void sortCards() {
		List<List<Card>> playerCards = game.getPlayerCards();
		List<String> rawCardsBefore = toRawCards();
		int i,j,k;
		for (i=0;i<playerCards.size();i++) {
			playerCards.get(i).sort(CardUtils.cardComparator(CardRank.TWO, CardSuit.SPADE));
		}
		List<String> rawCardsAfter = toRawCards();
		sequences = new ArrayList<>();
		for (i=0;i<playerCards.size();i++) {
			List<Boolean> used = new ArrayList<>();
			List<Integer> singleSequence = new ArrayList<>();
			String rb = rawCardsBefore.get(i);
			String ra = rawCardsAfter.get(i);
			int n = rawCardsBefore.get(i).length() / 2;
			for (j=0;j<n;j++) {
				used.add(false);
			}
			for (j=0;j<n;j++) {
				String ca = "" + ra.charAt(j*2) + ra.charAt(j*2+1);
				for (k=0;k<n;k++) {
					if (used.get(k)) {
						continue;
					}
					String cb = "" + rb.charAt(k*2) + rb.charAt(k*2+1);
					if (ca.contentEquals(cb)) {
						singleSequence.add(k);
						used.set(k, true);
						break;
					}
				}
			}
			sequences.add(singleSequence);
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
	/*
	public void buildTreasures(String rawTreasures) {
		List<Card> treasureCards = new ArrayList<>();
		int i=0;
		while (i<rawTreasures.length()) {
			String rawCard = rawTreasures.substring(i,i+2);
			Card card = CardUtils.deSerializeCard(rawCard);
			treasureCards.add(card);
		}
		game.setTreasureCards(treasureCards);
	}
	
	public String toRawTreasures() {
		String rawTreasures = "";
		int i;
		for (i=0;i<game.getTreasureCards().size();i++) {
			Card c = game.getTreasureCards().get(i);
			rawTreasures = rawTreasures + CardUtils.serializeCard(c);
		}
		return rawTreasures;
	}
	*/
	public List<Card> buildHidden(String rawHidden) {
		List<Card> hiddenCards = new ArrayList<>();
		int i=0;
		while (i<rawHidden.length()) {
			String rawCard = rawHidden.substring(i,i+2);
			Card card = CardUtils.deSerializeCard(rawCard);
			hiddenCards.add(card);
			i = i+2;
		}
		//game.setTreasureCards(treasureCards);
		//game.getGameDeck().
		return hiddenCards;
	}
	
	public String toRawHidden() {
		String rawHidden = "";
		int i;
		for (i=0;i<game.getGameDeck().getHiddenCards().size();i++) {
			Card c = game.getGameDeck().getHiddenCards().get(i);
			rawHidden = rawHidden + CardUtils.serializeCard(c);
		}
		return rawHidden;
	}
	
	public String playerRawCards(int x) {
		return toRawCards().get(x);
	}
	
	public String toRaw(List<Card> cards) {
		int i;
		String ans = "";
		for (i=0;i<cards.size();i++) {
			ans = ans + CardUtils.serializeCard(cards.get(i));
		}
		return ans;
	}
	
	Card.CardSuit toCardSuit(String suitRaw){
		Card.CardSuit suit = Card.CardSuit.RED_JOKER;
		if (suitRaw.contentEquals("s")) {
			suit = Card.CardSuit.SPADE;
		} else if (suitRaw.contentEquals("h")) {
			suit = Card.CardSuit.HEART;
		} else if (suitRaw.contentEquals("c")) {
			suit = Card.CardSuit.CLUB;
		} else if (suitRaw.contentEquals("d")) {
			suit = Card.CardSuit.DIAMOND;
		}
		
		return suit;
	}
	
	public void claimDominantSuit(String suitRaw, int index) {
		Card.CardSuit suit = toCardSuit(suitRaw);
		game.claimDominantSuit(suit, index);
	}
	
	public void addHiddenCardsToHand(int x, String rawHidden) {
		List<Card> hand = game.getPlayerCards().get(x);
		List<Card> hidden = buildHidden(rawHidden);
		hand.addAll(hidden);
		game.getPlayerCards().set(x, hand);
		this.sortCards();
	}
	
	public String removeHiddenCardsFromHand(int x, List<Integer> playedIndexes) {
		List<Card> hand = game.getPlayerCards().get(x);
		List<Card> hidden = new ArrayList<>();
		int i;
		for (i=playedIndexes.size()-1;i>=0;i--) {
			int y = playedIndexes.get(i);
			Card c = hand.remove(y);
			hidden.add(c);
		}
		game.getPlayerCards().set(x, hand);
		return toRaw(hidden);
	}

	public Game getGame() {
		return game;
	}
	public List<List<Integer>> getSequences() {
		return sequences;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
