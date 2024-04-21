package com.cosine.cosgame.pokerworld;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosgame.sfsj.common.Card;
import com.cosgame.sfsj.play.Hand;
import com.cosine.cosgame.pokerworld.account.Account;
import com.cosine.cosgame.pokerworld.entity.PlayerEntity;
import com.mongodb.util.JSON;

public class Player {
	String name;
	int phase;
	int innerId;
	int index;
	boolean confirmedClaim;
	boolean confirmedNextTurn;
	Board board;
	
	List<Integer> playedIndex;
	String playedCardsStr;
	
	List<PokerCard> hand;
	List<Integer> scores;
	List<Integer> bids;
	List<Integer> bonuses;
	List<Integer> actuals;
	List<PokerCard> played;
	List<String> endGameRewards;
	
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
		bonuses = new ArrayList<>();
		played = new ArrayList<>();
		endGameRewards = new ArrayList<>();
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
		if (board.getCurPlayer() != index) {
			return;
		}
		if (board.gameModeIs(Consts.SFSJ)) {
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
		} else if (board.gameModeIs(Consts.WIZARD)) {
			if (board.getStatus() == Consts.PLAYCARDS || board.getStatus() == Consts.CONFIRMROUNDTURN) {
				board.potentialNewTurnHandle();
				if (playedIndex.size() == 1) {
					int x = playedIndex.get(0);
					PokerCard c = hand.remove(x);
					played = new ArrayList<>();
					played.add(c);
					board.currentSuitHandle(this);
				}
				board.nextPlayerPlay();
			}
		}
		
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
		if (bids.size() == board.getRound()-1 && (board.getStatus() == Consts.SCORING || board.getStatus() == Consts.BIDTRICKS)) {
			board.potentialNewSetHandle();
			bids.add(x);
			actuals.add(0);
			bonuses.add(0); // 5-10 bonus handles
			board.nextPlayerBid();
		}
	}
	
	public int getBidThisRound() {
		int ans = -1;
		if (bids.size() == board.getRound()) {
			ans = bids.get(bids.size()-1);
		}
		return ans;
	}
	
	public int getActualThisRound() {
		int ans = -1;
		if (actuals.size() == board.getRound()) {
			ans = actuals.get(actuals.size()-1);
		}
		return ans;
	}
	
	public int getBonusThisRound() {
		int ans = 0;
		if (bonuses.size() == board.getRound()) {
			ans = bonuses.get(bonuses.size()-1);
		}
		System.out.println(board.getRound());
		System.out.println(bonuses);
		System.out.println(ans);
		return ans;
	}
	
	public int getLatestScore() {
		int x = scores.size()-1;
		return scores.get(x);
	}
	
	public void winTrick() {
		int i = board.getRound() - 1;
		int x = actuals.get(i)+1;
		actuals.set(i, x);
	}
	
	public void receiveBonus(int x) {
		int i = board.getRound() - 1;
		int y = bonuses.get(i) + x;
		bonuses.set(i, y);
	}
	
	public void updateScore() {
		int x = 0;
		if (scores.size()>0) {
			x = scores.get(scores.size()-1);
		}
		int b = getBidThisRound();
		int a = getActualThisRound();
		if (b == a) { // guessed right
			x = x+a*10+20;
			if (board.isFiveTenBonus()) {
				x = x + getBonusThisRound();
			}
		} else {
			int t = a-b;
			if (t<0) t = 0-t;
			x = x-t*10;
		}
		
		scores.add(x);
	}
	
	public void confirmNextTurn() {
		this.confirmedNextTurn = true;
	}
	
	public List<Integer> getPlayable(){
		List<Integer> ans = new ArrayList<>();
		boolean flag = true;
		int i;
		if (board.getStatus() != Consts.PLAYCARDS) {
			for (i=0;i<hand.size();i++) {
				ans.add(Consts.PLAYABLE);
			}
			return ans;
		}
		
		
		if (board.getCurPlayer() != index) {
			for (i=0;i<hand.size();i++) {
				ans.add(Consts.UNPLAYABLE);
			}
			return ans;
		}
		
		for (i=0;i<hand.size();i++) {
			if (board.getCurrentSuit().toUpperCase().contentEquals("WZ")) {
				break;
			}
			if (board.getCurrentSuit().toUpperCase().contentEquals("JE")) {
				break;
			}
			if (board.getCurrentSuit().toUpperCase().contentEquals("BM")) {
				break;
			}
			if (board.getCurrentSuit().toUpperCase().contentEquals("DR")) {
				break;
			}
			if (board.getCurrentSuit().toUpperCase().contentEquals("FR")) {
				break;
			}
			if (board.getCurrentSuit().contentEquals(hand.get(i).getSuit())) {
				flag = false;
				break;
			}
		}
		
		if (flag) {
			for (i=0;i<hand.size();i++) {
				ans.add(Consts.PLAYABLE);
			}
		} else {
			for (i=0;i<hand.size();i++) {
				if (hand.get(i).getSuit().toUpperCase().contentEquals("WZ")) {
					ans.add(Consts.PLAYABLE);
				} else if (hand.get(i).getSuit().toUpperCase().contentEquals("JE")) {
					ans.add(Consts.PLAYABLE);
				} else if (hand.get(i).getSuit().toUpperCase().contentEquals("DR")) {
					ans.add(Consts.PLAYABLE);
				} else if (hand.get(i).getSuit().toUpperCase().contentEquals("FR")) {
					ans.add(Consts.PLAYABLE);
				} else if (hand.get(i).getSuit().toUpperCase().contentEquals("BM")) {
					ans.add(Consts.PLAYABLE);
				}
				
				else if (hand.get(i).getSuit().contentEquals(board.getCurrentSuit())) {
					ans.add(Consts.PLAYABLE);
				} else {
					ans.add(Consts.UNPLAYABLE);
				}
			}
		}
		
		return ans;
	}
	
	public void emptyPlayedIndex() {
		playedIndex = new ArrayList<>();
	}
	
	public void emptyHand() {
		hand = new ArrayList<>();
	}
	
	public void emptyPlayed() {
		played = new ArrayList<>();
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
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	public List<Integer> getBonuses() {
		return bonuses;
	}
	public void setBonuses(List<Integer> bonuses) {
		this.bonuses = bonuses;
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
		doc.append("endGameRewards", endGameRewards);
		doc.append("bonuses", bonuses);
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
		endGameRewards = (List<String>) doc.get("endGameRewards");
		bonuses = (List<Integer>) doc.get("bonuses");
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
		entity.setBonuses(bonuses);
		//entity.setRewards(endGameRewards);
		Account account = new Account();
		account.getFromDB(name);
		//System.out.println(account.getName());
		entity.setChosenSkins(account.getChosenSkins());
		
		return entity;
	}
	
}
