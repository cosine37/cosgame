package com.cosine.cosgame.splendor;

import java.util.List;

public class Card {
	int score;
	List<Integer> cost;
	int benefit;
	String img;
	Player player;
	Board board;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public List<Integer> getCost() {
		return cost;
	}
	public void setCost(List<Integer> cost) {
		this.cost = cost;
	}
	public int getBenefit() {
		return benefit;
	}
	public void setBenefit(int benefit) {
		this.benefit = benefit;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
