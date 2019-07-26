package com.cosine.cosgame.dominion;

public class Card {
	// vanilla bonuses 
	protected int action;
	protected int coin;
	protected int card;
	protected int buy;
	
	protected int vp;
	protected int coffer;
	protected int villager;
	
	protected String name;
	protected String image;
	protected Player player;
	protected Board board;
	
	// price of the card
	protected int price;
	protected int pricePotion;
	protected int priceDebt;
	
	// for treasure
	protected int produce;
	protected int producePotion;
	
	// for Victory & Curse
	protected int score;
	
	protected boolean[] types = new boolean[100];
	// Primary types
	protected static final int INDEX_ACTION = 0;
	protected static final int INDEX_TREASURE = 1;
	protected static final int INDEX_VICTORY = 2;
	protected static final int INDEX_CURSED = 3;
	protected static final int INDEX_NIGHT = 4;
	
	// Secondary types
	protected static final int INDEX_ATTACK = 10;
	protected static final int INDEX_REACTION = 11;
	protected static final int INDEX_DURATION = 12;
	
	String where;
	
	public Card() {
		action = 0;
		coin = 0;
		card = 0;
		buy = 0;
		
		vp = 0;
		coffer = 0;
		villager = 0;
		
		score = 0;
		
		price = 0;
		pricePotion = 0;
		priceDebt = 0;
		
		int i;
		for (i=0;i<100;i++) {
			types[i] = false;
		}
	}
	
	public void vanilla() {
		player.draw(card);
		player.addAction(action);
		player.addBuy(buy);
		player.addCoin(coin);
	}
	
	public void setup() {
		
	}
	
	public void onGain(Player p) {
		
	}
	
	public Ask play() {
		vanilla();
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask afterChoice(String msg) {
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask response(String msg) {
		Ask ask = new Ask();
		return ask;
	}

	public int getAction() {
		return action;
	}

	public int getCoin() {
		return coin;
	}

	public int getCard() {
		return card;
	}

	public int getBuy() {
		return buy;
	}

	public int getVp() {
		return vp;
	}

	public int getCoffer() {
		return coffer;
	}

	public int getVillager() {
		return villager;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public Player getPlayer() {
		return player;
	}

	public int getPrice() {
		return price;
	}

	public int getPricePotion() {
		return pricePotion;
	}

	public int getPriceDebt() {
		return priceDebt;
	}

	public int getProduce() {
		return produce;
	}

	public int getProducePotion() {
		return producePotion;
	}

	public int getScore() {
		return score;
	}

	public boolean isAction() {
		return types[INDEX_ACTION];
	}

	public boolean isVictory() {
		return types[INDEX_VICTORY];
	}

	public boolean isTreasure() {
		return types[INDEX_TREASURE];
	}

	public boolean isCursed() {
		return types[INDEX_CURSED];
	}

	public boolean isAttack() {
		return types[INDEX_ATTACK];
	}

	public boolean isReaction() {
		return types[INDEX_REACTION];
	}
	
	public int numTypes() {
		int i;
		int total = 0;
		for (i=0;i<100;i++) {
			if (types[i]) total++;
		}
		return total;
	}
	
	public boolean playable() {
		boolean ans = false;
		if (player.getPhase() == Player.ACTION) {
			if (isAction()) {
				ans = true;
			}
		}
		
		if (player.getPhase() == Player.TREASURE) {
			if (isTreasure()) {
				ans = true;
			}
		}
		return ans;
	}
	
	public String getWhere() {
		return where;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
}
