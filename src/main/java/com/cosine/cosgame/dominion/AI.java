package com.cosine.cosgame.dominion;

import java.util.List;

public class AI {
	Player player;
	Board board;
	public AI() {
		
	}
	
	public AI(Player player, Board board) {
		this.player = player;
		this.board = board;
	}
	
	public void startPhase() {
		// TODO
		if (player.getPhase() == Player.START) {
			player.nextPhase();
		}
	}
	
	public void actionPhase() {
		int c1;
		int c2;
		if (player.getPhase() == Player.ACTION) {
			while (true) {
				c1 = playAllNonTerminal();
				c2 = playAllTerminal();
				if (c1 == 0 && c2 == 0) break;
			}
			player.nextPhase();
		}
	}
	
	public void treasurePhase() {
		if (player.getPhase() == Player.TREASURE) {
			board.getLogger().addAutoplayTreasure(player);
			player.autoplayTreasure();
			player.nextPhase();
		}
	}
	
	public void buyPhase() {
		if (player.getPhase() == Player.BUY) {
			naiveBuy();
			player.nextPhase();
		}
	}
	
	public void nightPhase() {
		// TODO
		if (player.getPhase() == Player.NIGHT) {
			player.nextPhase();
		}
	}
	
	public void cleanupPhase() {
		// TODO
		if (player.getPhase() == Player.CLEANUP) {
			player.nextPhase();
		}
	}
	
	public void offturnPhase() {
		// TODO
		
	}
	
	void naiveBuy() {
		if (player.getCoin() >= 8) {
			buy("Province");
		} else if (player.getCoin() >= 6) {
			if (board.getPileByTop("Province").getNumCards() <= 2) {
				buy("Duchy");
			} else {
				buy("Gold");
			}
		} else if (player.getCoin() >= 5) {
			if (board.getPileByTop("Province").getNumCards() <= 2) {
				buy("Duchy");
			} else {
				buy("Silver");
			}
		} else if (player.getCoin() >= 3) {
			if (board.getPileByTop("Province").getNumCards() <= 2) {
				buy("Estate");
			} else {
				buy("Silver");
			}
		} else if (player.getCoin() >= 2) {
			if (board.getPileByTop("Province").getNumCards() <= 2) {
				buy("Estate");
			}
			if (player.getCoin() == 2) {
				buy("Moat");
			}
		}
	}
	
	void buy(String cardName) {
		if (board.getPileByTop(cardName) == null) {
			return;
		}
		board.playerBuy(player, board.getPileByTop(cardName));
		board.getLogger().addBuyCard(player.getName(), cardName);
		board.gainToPlayerFromPile(player, board.getPileByTop(cardName));
		board.getLogger().addGainCard(player.getName(), cardName);
		board.gameEndJudge();
	}
	
	int playAllNonTerminal() {
		int count = 0;
		boolean flag = true;
		while (flag && player.getAction()>0) {
			flag = false;
			int i;
			List<Card> hand = player.getHand();
			for (i=0;i<hand.size();i++) {
				if (hand.get(i).getAction()>0 && hand.get(i).isSafe()) {
					player.play(hand.get(i).getName());
					board.getLogger().addPlayCard(player.getName(), hand.get(i).getName());
					count = count + 1;
					flag = true;
					break;
				}
			}
		}
		return count;
	}
	
	int playAllTerminal() {
		int count = 0;
		boolean flag = true;
		while (flag && player.getAction()>0) {
			flag = false;
			int i;
			List<Card> hand = player.getHand();
			for (i=0;i<hand.size();i++) {
				if (hand.get(i).getAction()==0 && hand.get(i).isSafe()) {
					player.play(hand.get(i).getName());
					board.getLogger().addPlayCard(player.getName(), hand.get(i).getName());
					count = count + 1;
					flag = true;
					break;
				}
			}
			
		}
		return count;
	}
	
	public void dealWithAttack(String cardName) {
		if (cardName.equals("Bureaucrat")) {
			for (int i=0;i<player.getHand().size();i++) {
				if (player.getHand().get(i).isVictory()){
					String s = player.getHand().get(i).getName();
					player.topDeck(s);
					return;
				}
			}
		} else if (cardName.equals("Militia")) {
			int i = 0;
			while (i<player.getHand().size()) {
				Card c = player.getHand().get(i);
				if ((!c.isActionType()) && (!c.isTreasure()) && (!c.isNight())) {
					player.discard(i);
					if (player.getHand().size() == 3) return;
				} else {
					i++;
				}
			}
			i=0;
			while (i<player.getHand().size()) {
				Card c = player.getHand().get(i);
				if (c.getName().equals("Copper")) {
					player.discard(i);
					if (player.getHand().size() == 3) return;
				} else {
					i++;
				}
			}
			while (player.getHand().size() > 3) {
				player.discard(0);
			}
		}
	}
}
