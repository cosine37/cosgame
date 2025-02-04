package com.cosine.cosgame.rich;

import java.util.List;

import com.cosine.cosgame.rich.basicplaces.Estate;

public class Player {
	String name;
	int hp;
	int energy;
	int san;
	int money;
	int star;
	int salary;
	int phase;
	
	List<Card> hand;
	List<Card> deck;
	List<Card> discard;
	
	List<Estate> estates;
	
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
}
