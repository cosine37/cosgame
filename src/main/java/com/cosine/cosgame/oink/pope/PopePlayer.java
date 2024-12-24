package com.cosine.cosgame.oink.pope;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.pope.entity.CardEntity;
import com.cosine.cosgame.oink.pope.entity.PopePlayerEntity;

public class PopePlayer {
	int index;
	int phase;
	int numKey;
	int ranking;
	int target;
	boolean protect;
	boolean active;
	boolean playedThief;
	boolean confirmed;
	String name;
	
	String targetedMsg;
	
	List<Card> hand;
	List<String> endGameRewards;
	List<Card> play;
	List<Card> resolveCards;
	List<String> resolveMsgs;
	Card justPlayed;
	PopeGame game;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("index",index);
		doc.append("phase",phase);
		doc.append("numKey",numKey);
		doc.append("protect",protect);
		doc.append("active",active);
		doc.append("playedThief",playedThief);
		doc.append("name",name);
		doc.append("confirmed",confirmed);
		doc.append("ranking", ranking);
		doc.append("endGameRewards", endGameRewards);
		doc.append("target", target);
		doc.append("targetedMsg", targetedMsg);
		doc.append("resolveMsgs", resolveMsgs);
		List<Integer> hi = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			hi.add(hand.get(i).getNum());
		}
		List<Integer> pi = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			pi.add(play.get(i).getNum());
		}
		List<Integer> ri = new ArrayList<>();
		for (i=0;i<resolveCards.size();i++) {
			ri.add(resolveCards.get(i).getNum());
		}
		doc.append("hand", hi);
		doc.append("play", pi);
		doc.append("resolveCards", ri);
		if (justPlayed == null) {
			doc.append("justPlayed", -1);
		} else {
			doc.append("justPlayed", justPlayed.getNum());
		}
		
		return doc;
	}
	
	public void setFromDoc(Document doc){
		int i;
		index = doc.getInteger("index",0);
		phase = doc.getInteger("phase",0);
		numKey = doc.getInteger("numKey",0);
		protect = doc.getBoolean("protect",false);
		active = doc.getBoolean("active",false);
		playedThief = doc.getBoolean("playedThief",false);
		name = doc.getString("name");
		confirmed = doc.getBoolean("confirmed", false);
		ranking = doc.getInteger("ranking", 0);
		endGameRewards = (List<String>) doc.get("endGameRewards");
		target = doc.getInteger("target", -1);
		targetedMsg = doc.getString("targetedMsg");
		resolveMsgs = (List<String>) doc.get("resolveMsgs");
		List<Integer> hi = (List<Integer>) doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<hi.size();i++) {
			Card c = CardFactory.makeCard(hi.get(i));
			c.setPlayer(this);
			c.setGame(game);
			hand.add(c);
		}
		List<Integer> pi = (List<Integer>) doc.get("play");
		play = new ArrayList<>();
		for (i=0;i<pi.size();i++) {
			Card c = CardFactory.makeCard(pi.get(i));
			c.setPlayer(this);
			c.setGame(game);
			play.add(c);
		}
		List<Integer> ri = (List<Integer>) doc.get("resolveCards");
		resolveCards = new ArrayList<>();
		for (i=0;i<ri.size();i++) {
			Card c = CardFactory.makeCard(ri.get(i));
			c.setPlayer(this);
			c.setGame(game);
			resolveCards.add(c);
		}
		int ji = doc.getInteger("justPlayed", -1);
		if (ji == -1) {
			justPlayed = null;
		} else {
			justPlayed = CardFactory.makeCard(ji);
			justPlayed.setPlayer(this);
		}
		
	}
	
	public PopePlayerEntity toPopePlayerEntity(){
		int i;
		PopePlayerEntity entity = new PopePlayerEntity();
		entity.setIndex(index);
		entity.setPhase(phase);
		entity.setNumKey(numKey);
		entity.setProtect(protect);
		entity.setActive(active);
		entity.setPlayedThief(playedThief);
		entity.setName(name);
		entity.setConfirmed(confirmed);
		entity.setRanking(ranking);
		entity.setTarget(target);
		if (play == null) {
			entity.setPlay(new ArrayList<>());
		} else {
			List<CardEntity> lp = new ArrayList<>();
			for (i=0;i<play.size();i++) {
				lp.add(play.get(i).toCardEntity());
			}
			entity.setPlay(lp);
		}
		if (game.getStatus() == Consts.ROUNDEND || active == false) {
			List<CardEntity> lh = new ArrayList<>();
			lh.add(hand.get(0).toCardEntity());
			entity.setHandRevealed(lh);
		} else if (game.getStatus() == Consts.INGAME && justPlayed != null){
			List<CardEntity> lh = new ArrayList<>();
			lh.add(justPlayed.toCardEntity());
			entity.setHandRevealed(lh);
		} else {
			entity.setHandRevealed(new ArrayList<>());
		}
		Account account = new Account();
		account.getFromDB(name);
		entity.setAccount(account.toAccountEntity());
		return entity;
	}
	
	public PopePlayer() {
		hand = new ArrayList<>();
		play = new ArrayList<>();
		resolveCards = new ArrayList<>();
		resolveMsgs = new ArrayList<>();
	}
	public PopePlayer(String name) {
		this();
		this.name = name;
	}
	
	public void draw() {
		Card c = game.removeFromDeck();
		if (c != null) {
			hand.add(c);
		}
	}
	
	public void discard() {
		if (hand.size() == 1) {
			Card c = hand.remove(0);
			play.add(c);
			game.getLogger().log(this.getName() + "弃置了" + c.getName());
			c.onDiscard();
		}
	}
	
	public void startRound() {
		active = true;
		phase = Consts.OFFTURN;
		hand = new ArrayList<>();
		play = new ArrayList<>();
		justPlayed = null;
		draw();
	}
	
	public void startTurn() {
		// Step 1: empty resolveMsgs and resolveCards
		resolveMsgs = new ArrayList<>();
		resolveCards = new ArrayList<>();
		
		// Step 2: change phase, remove protect, and draw
		protect = false;
		phase = Consts.PLAYCARD;
		justPlayed = null;
		target = -1;
		draw();
		
		// Step 3: log
		game.getLogger().logStartTurn(this);
	}
	
	public void inactivate() {
		this.active = false;
		this.phase = Consts.OUT;
		game.getLogger().logInactive(this);
	}
	
	public void playCard(int x, int target) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			
			justPlayed = c;
			play.add(c);
			c.onPlay(target);	
		}
	}
	
	public void addKey() {
		numKey = numKey+1;
	}
	public void cleanResolve() {
		resolveMsgs = new ArrayList<>();
		resolveCards = new ArrayList<>();
	}
	public void addResolve(String s, Card c) {
		addResolveMsg(s);
		addResolveCard(c);
	}
	public void addResolveMsg(String s) {
		resolveMsgs.add(s);
	}
	public void addResolveCard(Card c) {
		resolveCards.add(c);
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumKey() {
		return numKey;
	}
	public void setNumKey(int numKey) {
		this.numKey = numKey;
	}
	public boolean isProtect() {
		return protect;
	}
	public void setProtect(boolean protect) {
		this.protect = protect;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public PopeGame getGame() {
		return game;
	}
	public void setGame(PopeGame game) {
		this.game = game;
	}
	public boolean isPlayedThief() {
		return playedThief;
	}
	public void setPlayedThief(boolean playedThief) {
		this.playedThief = playedThief;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	public Card getJustPlayed() {
		return justPlayed;
	}
	public void setJustPlayed(Card justPlayed) {
		this.justPlayed = justPlayed;
	}
	public int getTarget() {
		return target;
	}
	public void setTarget(int target) {
		this.target = target;
	}
	public String getTargetedMsg() {
		return targetedMsg;
	}
	public void setTargetedMsg(String targetedMsg) {
		this.targetedMsg = targetedMsg;
	}
	public List<Card> getResolveCards() {
		return resolveCards;
	}
	public void setResolveCards(List<Card> resolveCards) {
		this.resolveCards = resolveCards;
	}
	public List<String> getResolveMsgs() {
		return resolveMsgs;
	}
	public void setResolveMsgs(List<String> resolveMsgs) {
		this.resolveMsgs = resolveMsgs;
	}
	
}
