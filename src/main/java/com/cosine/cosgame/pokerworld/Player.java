package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.play.Hand;
import com.cosine.cosgame.pokerworld.entity.PlayerEntity;

public class Player {
	String name;
	int phase;
	int innerId;
	boolean confirmedClaim;
	boolean confirmedNextTurn;
	Board board;
	
	List<Integer> playedIndex;
	String playedCardsStr;
	
	List<PokerCard> hand;
	List<Integer> scores;
	List<Integer> bids;
	List<Integer> actuals;
	List<PokerCard> played;
	
	public Player() {
		clearAll();
	}
	
	public void clearAll() {
		playedIndex = new ArrayList<>();
		playedCardsStr = "";
		
		hand = new ArrayList<>();
		scores = new ArrayList<>();
		bids = new ArrayList<>();
		actuals = new ArrayList<>();
		played = new ArrayList<>();
	}
	
	public Player(Board board) {
		this();
		this.board = board;
	}
	
	public List<Card> getMyCards(){
		List<Card> cards = board.getGame().getPlayerCards().get(innerId);
		return cards;
	}
	
	public String getHandAsStr() {
		return PokerUtil.cardListToString(hand);
	}
	
	public String getMyRawCards() {
		return board.getGameUtil().playerRawCards(innerId);
	}
	
	public String getMyRawCardsAfterPlay() {
		String myRawCards = board.getGameUtil().playerRawCards(innerId);
		int i,j;
		int n = myRawCards.length() / 2;
		String s = "";
		for (i=0;i<n;i++) {
			boolean f = true;
			for (j=0;j<playedIndex.size();j++) {
				if (playedIndex.get(j) == i) {
					f = false;
					break;
				}
			}
			if (f) {
				s = s+myRawCards.substring(i*2, i*2+2);
			}
		}
		return s;
	}
	
	public void play(List<Integer> playedIndex) {
		int numPlayed = board.getNumPlayed();
		if (numPlayed == -1) {
			setPlayedIndex(playedIndex);
			genPlayedCardsStr();
			board.setNumPlayed(playedIndex.size());
		} else if (numPlayed == playedIndex.size()){
			setPlayedIndex(playedIndex);
			genPlayedCardsStr();
		}
		board.nextPlayerPlay();
	}
	
	public void sortHand() {
		int i,j;
		for (i=0;i<hand.size();i++) {
			for (j=i+1;j<hand.size();j++) {
				PokerCard c1 = hand.get(i);
				PokerCard c2 = hand.get(j);
				if (PokerUtil.bigger(c2, c1, board, true)) {
					hand.set(i, c2);
					hand.set(j, c1);
				}
			}
		}
	}
	
	public void genPlayedCardsStr() {
		playedCardsStr = "";
		String myRawCards = getMyRawCards();
		int i;
		for (i=0;i<playedIndex.size();i++) {
			int x = playedIndex.get(i);
			playedCardsStr = playedCardsStr + myRawCards.substring(x*2, x*2+2);
		}
	}
	
	public void addBid(int x) {
		if (bids.size() == board.getRound()-1) {
			bids.add(x);
		}
	}
	
	public int getBidThisRound() {
		int ans = -1;
		if (bids.size() == board.getRound()) {
			ans = bids.get(bids.size()-1);
		}
		return ans;
	}
	
	public void emptyPlayedIndex() {
		playedIndex = new ArrayList<>();
	}
	
	public void emptyHand() {
		hand = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getInnerId() {
		return innerId;
	}
	public void setInnerId(int innerId) {
		this.innerId = innerId;
	}
	public List<Integer> getPlayedIndex() {
		return playedIndex;
	}
	public void setPlayedIndex(List<Integer> playedIndex) {
		this.playedIndex = playedIndex;
	}
	public boolean isConfirmedClaim() {
		return confirmedClaim;
	}
	public void setConfirmedClaim(boolean confirmedClaim) {
		this.confirmedClaim = confirmedClaim;
	}
	public String getPlayedCardsStr() {
		return playedCardsStr;
	}
	public void setPlayedCardsStr(String playedCardsStr) {
		this.playedCardsStr = playedCardsStr;
	}
	public boolean isConfirmedNextTurn() {
		return confirmedNextTurn;
	}
	public void setConfirmedNextTurn(boolean confirmedNextTurn) {
		this.confirmedNextTurn = confirmedNextTurn;
	}
	public List<PokerCard> getHand() {
		return hand;
	}
	public void setHand(List<PokerCard> hand) {
		this.hand = hand;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public List<Integer> getBids() {
		return bids;
	}
	public void setBids(List<Integer> bids) {
		this.bids = bids;
	}
	public List<Integer> getActuals() {
		return actuals;
	}
	public void setActuals(List<Integer> actuals) {
		this.actuals = actuals;
	}
	public List<PokerCard> getPlayed() {
		return played;
	}
	public void setPlayed(List<PokerCard> played) {
		this.played = played;
	}
	public Document toDocument() {
		int i;
		Document doc = new Document();
		doc.append("name", name);
		doc.append("innerId", innerId);
		doc.append("playedIndex", playedIndex);
		doc.append("confirmedClaim", confirmedClaim);
		doc.append("confirmedNextTurn", confirmedNextTurn);
		doc.append("hand", PokerUtil.cardListToString(hand));
		doc.append("scores", scores);
		doc.append("bids", bids);
		doc.append("actuals", actuals);
		if (board.getGameMode() == Consts.SFSJ) {
			doc.append("playedCardsStr", playedCardsStr);
		} else {
			doc.append("playedCardsStr", PokerUtil.cardListToString(played));
		}
		return doc;
	}
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		innerId = doc.getInteger("innerId", -1);
		playedIndex = (List<Integer>) doc.get("playedIndex");
		confirmedClaim = doc.getBoolean("confirmedClaim", false);
		confirmedNextTurn = doc.getBoolean("confirmedNextTurn", false);
		playedCardsStr = doc.getString("playedCardsStr");
		scores = (List<Integer>) doc.get("scores");
		bids = (List<Integer>) doc.get("bids");
		actuals = (List<Integer>) doc.get("actuals");
		
		if (board.getGameMode() == Consts.SFSJ) {
			playedCardsStr = doc.getString("playedCardsStr");
		} else {
			playedCardsStr = doc.getString("playedCardsStr");
			played = PokerUtil.stringToCardList(playedCardsStr);
			String handStr = doc.getString("hand");
			hand = PokerUtil.stringToCardList(handStr);
		}
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		entity.setConfirmedNextTurn(confirmedNextTurn);
		if (board.getGameMode() == Consts.SFSJ) {
			entity.setPlayedCards(playedCardsStr);
		} else {
			entity.setPlayedCards(PokerUtil.cardListToString(played));
		}
		entity.setPlayedCards(playedCardsStr);
		entity.setScores(scores);
		entity.setBids(bids);
		entity.setActuals(actuals);
		return entity;
	}
	
}
