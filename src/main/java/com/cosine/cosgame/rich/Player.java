package com.cosine.cosgame.rich;

import java.util.List;

import com.cosine.cosgame.rich.basicplaces.Estate;

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
	
	protected List<Card> hand;
	protected List<Card> deck;
	protected List<Card> discard;
	
	protected Place place;
	
	protected Board board;
	protected List<Estate> estates;
	
	public void move(int n) {
		place.removePlayer(this);
		Place np = place;
		for (int i=0;i<n;i++) {
			np = np.getNext();
			np.bypass(this);
		}
		board.putPlayerOnPlace(this, np);
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
	public List<Estate> getEstates() {
		return estates;
	}
	public void setEstates(List<Estate> estates) {
		this.estates = estates;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
