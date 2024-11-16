package com.cosine.cosgame.oink.startups.entity;

import java.util.HashMap;
import java.util.List;

public class PlayerEntity {
	String name;
	int index;
	int coins;
	int phase;
	int numTaken;
	
	List<Integer> coin1s;
	List<Integer> coin3s;
	
	List<Integer> scores;
	List<CardEntity> play;
	List<CardEntity> antiMonopoly;

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
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumTaken() {
		return numTaken;
	}
	public void setNumTaken(int numTaken) {
		this.numTaken = numTaken;
	}
	public List<Integer> getCoin1s() {
		return coin1s;
	}
	public void setCoin1s(List<Integer> coin1s) {
		this.coin1s = coin1s;
	}
	public List<Integer> getCoin3s() {
		return coin3s;
	}
	public void setCoin3s(List<Integer> coin3s) {
		this.coin3s = coin3s;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public List<CardEntity> getPlay() {
		return play;
	}
	public void setPlay(List<CardEntity> play) {
		this.play = play;
	}
	public List<CardEntity> getAntiMonopoly() {
		return antiMonopoly;
	}
	public void setAntiMonopoly(List<CardEntity> antiMonopoly) {
		this.antiMonopoly = antiMonopoly;
	}
	
	

}
