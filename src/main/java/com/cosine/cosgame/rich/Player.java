package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.entity.PlayerEntity;

public class Player {
	protected String name;
	protected int hp;
	protected int energy;
	protected int san;
	protected int money;
	protected int star;
	protected int salary;
	protected int phase;
	protected int index;
	protected int rollDisplay;
	protected boolean confirmed;
	
	protected List<Card> hand;
	protected List<Card> deck;
	protected List<Card> discard;
	protected List<Integer> owned;
	
	protected int placeIndex;
	
	protected Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("name",name);
		doc.append("hp",hp);
		doc.append("energy",energy);
		doc.append("san",san);
		doc.append("money",money);
		doc.append("star",star);
		doc.append("salary",salary);
		doc.append("phase",phase);
		doc.append("index",index);
		doc.append("confirmed",confirmed);
		doc.append("owned", owned);
		doc.append("rollDisplay", rollDisplay);
		List<Integer> handDocList = new ArrayList<>();
		for (i=0;i<hand.size();i++){
			handDocList.add(hand.get(i).getId());
		}
		doc.append("hand",handDocList);
		List<Integer> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).getId());
		}
		doc.append("deck",deckDocList);
		List<Integer> discardDocList = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			discardDocList.add(discard.get(i).getId());
		}
		doc.append("discard",discardDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		name = doc.getString("name");
		hp = doc.getInteger("hp",0);
		energy = doc.getInteger("energy",0);
		san = doc.getInteger("san",0);
		money = doc.getInteger("money",0);
		star = doc.getInteger("star",0);
		salary = doc.getInteger("salary",0);
		phase = doc.getInteger("phase",0);
		index = doc.getInteger("index",0);
		confirmed = doc.getBoolean("confirmed",false);
		owned = (List<Integer>) doc.get("owned");
		rollDisplay = doc.getInteger("rollDisplay", 0);
		List<Integer> handDocList = (List<Integer>)doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<handDocList.size();i++){
			Card c = Factory.genCard(handDocList.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			hand.add(c);
		}
		List<Integer> deckDocList = (List<Integer>)doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<deckDocList.size();i++){
			Card c = Factory.genCard(deckDocList.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			deck.add(c);
		}
		List<Integer> discardDocList = (List<Integer>)doc.get("discard");
		discard = new ArrayList<>();
		for (i=0;i<discardDocList.size();i++){
			Card c = Factory.genCard(discardDocList.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			discard.add(c);
		}
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setIndex(index);
		entity.setMoney(money);
		entity.setName(name);
		entity.setConfirmed(confirmed);
		entity.setPhase(phase);
		entity.setSalary(salary);
		entity.setOwned(owned);
		return entity;
	}
	
	public Player() {
		hand = new ArrayList<>();
		deck = new ArrayList<>();
		discard = new ArrayList<>();
	}
	
	public void addMoney(int x) {
		money = money+x;
	}
	
	public void loseMoney(int x) {
		money = money-x;
	}
	
	public void addSalary() {
		money = money+salary;
	}
	
	public void moveToPlace(int x) {
		placeIndex = x;
		board.getMap().getPlace(x).addPlayerOn(this);
	}
	
	public void startGame() {
		money = board.getSettings().getStartMoney();
		salary = board.getSettings().getStartSalary();
		moveToPlace(0);
		phase = Consts.PHASE_OFFTURN;
	}
	
	public void startTurn() {
		phase = Consts.PHASE_ROLL;
		board.getLogger().logStartTurn(this);
	}
	
	public List<String> getOptions(){
		List<String> ans = new ArrayList<>();
		if (phase == Consts.PHASE_ROLL) {
			ans.add("掷骰");
		} else if (phase == Consts.PHASE_MOVE) {
			ans.add("移动");
		} else if (phase == Consts.PHASE_RESOLVE) {
			Place p = board.getMap().getPlace(placeIndex);
			ans = p.getResolveOptions(this);
		}
		return ans;
	}
	
	public String myNextPlaceName() {
		Place place = board.getMap().getPlaceAfter(placeIndex,rollDisplay);
		if (place == null) return ""; else return place.getName();
	}
	
	public String myCurrentPlaceName() {
		Place place = board.getMap().getPlace(placeIndex);
		if (place == null) return ""; else return place.getName();
	}
	
	public String myLandMsg() {
		Place place = board.getMap().getPlace(placeIndex);
		if (place == null) return ""; else return place.getLandMsg(this);
	}
	
	public void phaseRoll(int option) {
		if (phase != Consts.PHASE_ROLL) return;
		if (option == 0) {
			board.roll();
			phase = Consts.PHASE_MOVE;
			rollDisplay = board.getLastRolled();
			
			board.getLogger().logPlayerRoll(this);
		} else if (option>100000) {
			int x = option-100000;
			board.setLastRolled(x);
			phase = Consts.PHASE_MOVE;
			rollDisplay = board.getLastRolled();
			
			board.getLogger().logPlayerRoll(this);
		}
	}
	
	public void phaseMove(int option) {
		if (phase != Consts.PHASE_MOVE) return;
		if (option == 0) {
			board.getMap().getPlace(placeIndex).removePlayer(this);
			int t = placeIndex;
			for (int i=rollDisplay-1;i>=0;i--) {
				t = (t+1)%board.getMap().mapSize();
				if (i!=0) board.getMap().getPlace(t).bypass(this);
			}
			moveToPlace(t);
			board.getMap().getPlace(t).preStepOn(this);
			board.getLogger().logPlayerArrive(this);
			
			phase = Consts.PHASE_RESOLVE;
			rollDisplay = 0;
		}
	}
	
	public void phaseResolve(int option) {
		if (phase != Consts.PHASE_RESOLVE) return;
		board.getMap().getPlace(placeIndex).stepOn(this, option);
		board.getLogger().logEndTurn(this);
		board.nextPlayer();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getSan() {
		return san;
	}
	public void setSan(int san) {
		this.san = san;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Place getPlace() {
		return board.getMap().getPlace(placeIndex);
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getRollDisplay() {
		return rollDisplay;
	}
	public void setRollDisplay(int rollDisplay) {
		this.rollDisplay = rollDisplay;
	}
	public List<Integer> getOwned() {
		return owned;
	}
	public void setOwned(List<Integer> owned) {
		this.owned = owned;
	}
	public int getPlaceIndex() {
		return placeIndex;
	}
	public void setPlaceIndex(int placeIndex) {
		this.placeIndex = placeIndex;
	}
}
