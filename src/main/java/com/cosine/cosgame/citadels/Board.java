package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	List<Player> players;
	List<Card> deck;
	boolean firstFinished;
	int finishCount;
	int coins;
	int killedRole;
	int stealedRole;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		firstFinished = true;
		finishCount = 8;
		coins = 30;
		killedRole = 0;
		stealedRole = 0;
	}
	
	public int takeCoins(int n) {
		int ans;
		if (coins < n) {
			ans = coins;
			coins = 0;
		} else {
			ans = n;
			coins = coins-n;
		}
		return ans;
	}
	
	public void addPlayer(String name) {
		Player p = new Player(name);
		p.setBoard(this);
		players.add(p);
	}
	
	public void setDeck() {
		int i;
		Card c;
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("路边摊", CitadelConsts.GREEN, 1, "g1");
			deck.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("快餐店", CitadelConsts.GREEN, 2, "g21");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("菜场", CitadelConsts.GREEN, 2, "g22");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("超市", CitadelConsts.GREEN, 3, "g3");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("商场", CitadelConsts.GREEN, 4, "g4");
			deck.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("商业步行街", CitadelConsts.GREEN, 5, "g5");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("幼儿园", CitadelConsts.BLUE, 1, "b1");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("小学", CitadelConsts.BLUE, 2, "b2");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("初中", CitadelConsts.BLUE, 3, "b3");
			deck.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("高中", CitadelConsts.BLUE, 5, "b5");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("岗亭", CitadelConsts.RED, 1, "r1");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("监狱", CitadelConsts.RED, 2, "r2");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("警察局", CitadelConsts.RED, 3, "r3");
			deck.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("军区", CitadelConsts.RED, 5, "r5");
			deck.add(c);
		}
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("法院", CitadelConsts.YELLOW, 3, "y3");
			deck.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("电视台", CitadelConsts.YELLOW, 4, "y4");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("市政府", CitadelConsts.YELLOW, 5, "y5");
			deck.add(c);
		}
		
	}
	
	public void shuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int size = deck.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	public void deal() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).draw(4);
			players.get(i).addCoin(2);
		}
	}
	
	public void gameSetup() {
		setDeck();
		shuffle();
		deal();
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public boolean isFirstFinished() {
		return firstFinished;
	}
	public void setFirstFinished(boolean firstFinished) {
		this.firstFinished = firstFinished;
	}
	public int getFinishCount() {
		return finishCount;
	}
	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public BoardEntity toBoardEntity() {
		BoardEntity entity = new BoardEntity();
		int i,j;
		List<String> playerNames = new ArrayList<>();
		List<String> coins = new ArrayList<>();
		List<List<String>> built = new ArrayList<>();
		List<List<String>> hand = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			coins.add(Integer.toString(players.get(i).getCoin()));
			List<String> singleBuilt = new ArrayList<>();
			List<String> singleHand = new ArrayList<>();
			for (j=0;j<players.get(i).getBuilt().size();j++) {
				singleBuilt.add(players.get(i).getBuilt().get(j).getImg());
			}
			built.add(singleBuilt);
			for (j=0;j<players.get(i).getHand().size();j++) {
				singleHand.add(players.get(i).getHand().get(j).getImg());
			}
			hand.add(singleHand);
		}
		entity.setBank(Integer.toString(this.coins));
		entity.setPlayerNames(playerNames);
		entity.setBuilt(built);
		entity.setHand(hand);
		entity.setCoins(coins);
		return entity;
	}

}
