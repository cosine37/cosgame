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
	protected int memorial;
	
	// basic features
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
	
	// for duration cards
	protected int nt; // feature
	protected int numTurns; // for record
	
	// some special care
	protected int specialCare;
	
	protected boolean[] types = new boolean[100];
	// Primary types
	protected static final int INDEX_ACTION = 0;
	protected static final int INDEX_TREASURE = 1;
	protected static final int INDEX_VICTORY = 2;
	protected static final int INDEX_CURSED = 3;
	protected static final int INDEX_NIGHT = 4;
	protected static final int INDEX_GENERAL = 5;
	
	// Secondary types
	protected static final int INDEX_ATTACK = 10;
	protected static final int INDEX_REACTION = 11;
	protected static final int INDEX_DURATION = 12;
	
	// categories
	protected static final int INDEX_ATTACKBLOCK = 31;
	
	// special cares
	protected static final int SC_NONE = 0;
	protected static final int SC_CLEANUPTOSECLUSION = 1;
	
	// This is used for treasure, default true
	protected boolean autoplay;
	
	// These are used for AI
	protected boolean safe; // AI will play the card if the card is safe
	
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
		
		autoplay = true;
		
		safe = true;
		
		int i;
		for (i=0;i<30;i++) {
			types[i] = false;
		}
		
		specialCare = 0;
		nt = 0;
		numTurns = 0;
	}
	
	public void vanilla() {
		player.draw(card);
		player.addAction(action);
		player.addBuy(buy);
		player.addCoin(coin);
		
		boolean flag = false;
		String s = player.getName() + " gets";
		if (card>0) {
			s = s+" +"+card+" Card";
			flag = true;
		}
		if (action>0) {
			if (flag) {
				s = s+",";
			}
			s = s+" +"+action+" Action";
			flag = true;
		}
		if (buy>0) {
			if (flag) {
				s = s+",";
			}
			s = s+" +"+buy+" Buy";
			flag = true;
		}
		if (coin>0) {
			if (flag) {
				s = s+",";
			}
			s = s+" + $"+coin;
			flag = true;
		}
		if (flag && isActionType()) {
			log(s,1);
		}
		numTurns = nt;
	}
	
	public void setup() {
		
	}
	
	public void log(String s, int level) {
		if (board != null) {
			board.getLogger().add(s, level);
		}
		
	}
	
	public Ask onGain(Player p) {
		Ask ask = new Ask();
		ask.setCardName(name);
		return ask;
	}
	
	public Ask onBuy(Player p) {
		Ask ask = new Ask();
		ask.setCardName(name);
		return ask;
	}
	
	public Ask onDiscard(Player p) {
		Ask ask = new Ask();
		ask.setCardName(name);
		return ask;
	}
	
	public Ask onTrash(Player p) {
		Ask ask = new Ask();
		ask.setCardName(name);
		return ask;
	}
	
	public Ask play() {
		if (player != null) {
			if (player.getPhase() == Player.ACTION) {
				player.addNumActionsPlayed();
				if (board != null) {
					board.getLogger().addPlayCard(player.getName(), name);
				}
			}
		}
		vanilla();
		Ask ask = new Ask();
		ask.setCardName(name);
		if (isAttack()) {
			ask.setSubType(Ask.ATTACK);
		}
		return ask;
	}
	
	public Ask attack() {
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask duration() {
		numTurns = numTurns - 1;
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask response(Ask a) {
		return a;
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

	public int getScore(Player p) {
		return score;
	}
	
	public int getNumTurns() {
		return numTurns;
	}
	
	public void setNumTurns(int numTurns) {
		this.numTurns = numTurns;
	}

	public boolean isActionType() {
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
	
	public boolean isNight() {
		return types[INDEX_NIGHT];
	}
	
	public boolean isGeneral() {
		return types[INDEX_GENERAL];
	}

	public boolean isAttack() {
		return types[INDEX_ATTACK];
	}

	public boolean isReaction() {
		return types[INDEX_REACTION];
	}
	
	public boolean isDuration() {
		return types[INDEX_DURATION];
	}
	
	public boolean isAttackBlock() {
		return types[INDEX_ATTACKBLOCK];
	}
	
	public int numTypes() {
		int i;
		int total = 0;
		for (i=0;i<30;i++) {
			if (types[i]) total++;
		}
		return total;
	}
	
	public boolean playable() {
		boolean ans = false;
		if (player.getPhase() == Player.ACTION) {
			if (isActionType()) {
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
	
	public boolean isAutoplay() {
		return autoplay;
	}
	
	public boolean isSafe() {
		return safe;
	}
	
	public int getSpecialCare() {
		return specialCare;
	}
	
}
