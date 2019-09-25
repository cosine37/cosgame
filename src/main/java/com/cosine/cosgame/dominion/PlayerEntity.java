package com.cosine.cosgame.dominion;

import java.util.List;

public class PlayerEntity {
	List<Pile> play;
	List<Pile> hand;
	Ask ask;
	List<Ask> startAsks;
	Card discard;
	int deck;
	String phase;
	int goodToGo;
	List<List<Pile>> mats;
	List<Integer> tokens;
	int action;
	int buy;
	int coin;
	int reducer;
	
	
	int vp;
	
	public PlayerEntity(Player player) {
		play = player.getPlayAsPiles();
		hand = player.getHandAsPiles();
		ask = player.getAsk();
		startAsks = player.getStartAsks();
		if (player.getDiscard().size() == 0) {
			discard = null;
		} else {
			discard = player.getDiscard().get(player.getDiscard().size() - 1);
		}
		
		deck = player.getDeck().size();
		phase = player.getPhaseAsString();
		if (player.getIsGoodToGo()) {
			goodToGo = 1;
		} else {
			goodToGo = 0;
		}
		mats = player.getMatsAsPiles();
		tokens = player.getTokens();
		action = player.getAction();
		buy = player.getBuy();
		coin = player.getCoin();
		reducer = player.getPriceReduce();
		vp = player.getVp();
	}
	
	public String getPhase() {
		return phase;
	}
	
	public void setPhase(String phase) {
		this.phase = phase;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getBuy() {
		return buy;
	}

	public void setBuy(int buy) {
		this.buy = buy;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public List<Pile> getPlay() {
		return play;
	}

	public void setPlay(List<Pile> play) {
		this.play = play;
	}

	public List<Pile> getHand() {
		return hand;
	}

	public void setHand(List<Pile> hand) {
		this.hand = hand;
	}

	public Ask getAsk() {
		return ask;
	}

	public void setAsk(Ask ask) {
		this.ask = ask;
	}

	public List<Ask> getStartAsks() {
		return startAsks;
	}

	public void setStartAsks(List<Ask> startAsks) {
		this.startAsks = startAsks;
	}

	public Card getDiscard() {
		return discard;
	}

	public void setDiscard(Card discard) {
		this.discard = discard;
	}

	public int getDeck() {
		return deck;
	}

	public void setDeck(int deck) {
		this.deck = deck;
	}

	public List<List<Pile>> getMats() {
		return mats;
	}

	public void setMats(List<List<Pile>> mats) {
		this.mats = mats;
	}

	public List<Integer> getTokens() {
		return tokens;
	}

	public void setTokens(List<Integer> tokens) {
		this.tokens = tokens;
	}

	public int getReducer() {
		return reducer;
	}

	public void setReducer(int reducer) {
		this.reducer = reducer;
	}

	public int getVp() {
		return vp;
	}

	public void setVp(int vp) {
		this.vp = vp;
	}

	public int getGoodToGo() {
		return goodToGo;
	}

	public void setGoodToGo(int goodToGo) {
		this.goodToGo = goodToGo;
	}
}
