package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.west.entity.CardEntity;
import com.cosine.cosgame.oink.west.entity.PlayerEntity;

public class Player {
	String name;
	int index;
	int phase;
	int coins;
	int rank;
	boolean stillIn;
	boolean alive;
	boolean confirmed;
	
	List<Card> hand;
	List<Card> discard;
	List<String> endGameRewards;
	
	West west;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("name",name);
		doc.append("index",index);
		doc.append("phase",phase);
		doc.append("coins",coins);
		doc.append("stillIn",stillIn);
		doc.append("alive",alive);
		doc.append("rank", rank);
		doc.append("confirmed",confirmed);
		doc.append("endGameRewards", endGameRewards);
		List<Document> handDocList = new ArrayList<>();
		for (i=0;i<hand.size();i++){
			handDocList.add(hand.get(i).toDocument());
		}
		doc.append("hand",handDocList);
		List<Document> discardDocList = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			discardDocList.add(discard.get(i).toDocument());
		}
		doc.append("discard",discardDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		name = doc.getString("name");
		index = doc.getInteger("index",0);
		phase = doc.getInteger("phase",0);
		coins = doc.getInteger("coins",0);
		stillIn = doc.getBoolean("stillIn",false);
		alive = doc.getBoolean("alive",false);
		rank = doc.getInteger("rank", 0);
		confirmed = doc.getBoolean("confirmed",false);
		endGameRewards = (List<String>) doc.get("endGameRewards");
		List<Document> handDocList = (List<Document>)doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<handDocList.size();i++){
			Card e = new Card();
			e.setWest(west);
			e.setFromDoc(handDocList.get(i));
			hand.add(e);
		}
		List<Document> discardDocList = (List<Document>)doc.get("discard");
		discard = new ArrayList<>();
		for (i=0;i<discardDocList.size();i++){
			Card e = new Card();
			e.setWest(west);
			e.setFromDoc(discardDocList.get(i));
			discard.add(e);
		}
	}
	public PlayerEntity toPlayerEntity(String username){
		int i,j;
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		entity.setIndex(index);
		entity.setCoins(coins);
		entity.setStillIn(stillIn);
		entity.setAlive(alive);
		entity.setRank(rank);
		entity.setConfirmed(confirmed);
		List<CardEntity> listOfDiscard = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			listOfDiscard.add(discard.get(i).toCardEntity(username, 0));
		}
		entity.setDiscard(listOfDiscard);
		
		List<CardEntity> lr = new ArrayList<>();
		if (west.getStatus() == Consts.RESULT && stillIn) {
			int x = west.getWinner();
			int flag;
			if (x == index) {
				flag = 1;
			} else {
				flag = -1;
			}
			lr.add(hand.get(0).toCardEntity(username, flag));
		}
		entity.setRevealed(lr);
		
		
		Account a = new Account();
		a.getFromDB(name);
		entity.setAccount(a.toAccountEntity());
		return entity;
	}
	
	public Player() {
		
	}
	
	public Player(String name) {
		this.name = name;
	}
	
	public void newGame() {
		alive = true;
		coins = Consts.STARTCOINS;
		//newRound();
	}
	
	public void newRound() {
		hand = new ArrayList<>();
		discard = new ArrayList<>();
		stillIn = false;
		phase = Consts.OFFTURN;
		confirmed = false;
		draw();
	}
	
	public void draw() {
		Card c = west.removeTop();
		if (c != null) {
			hand.add(c);
		}
	}
	
	public void discard(int x) {
		if (hand.size()>x) {
			Card c = hand.remove(x);
			discard.add(c);
		}
	}
	
	public void bid(int x) {
		coins = coins-x;
		west.addPool(x);
		stillIn = true;
	}
	
	public void addCoins(int x) {
		coins = coins+x;
	}
	
	public void retreat() {
		stillIn = false;
	}
	
	public int getHandNum() {
		return hand.get(0).getNum();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public boolean isStillIn() {
		return stillIn;
	}
	public void setStillIn(boolean stillIn) {
		this.stillIn = stillIn;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public West getWest() {
		return west;
	}
	public void setWest(West west) {
		this.west = west;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public List<String> getEndGameRewards() {
		return endGameRewards;
	}
	public void setEndGameRewards(List<String> endGameRewards) {
		this.endGameRewards = endGameRewards;
	}
	
}
