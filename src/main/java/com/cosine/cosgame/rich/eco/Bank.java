package com.cosine.cosgame.rich.eco;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Player;

public class Bank {
	List<Integer> savings;
	List<Integer> bonusRates;
	int rate;
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("savings",savings);
		doc.append("bonusRates",bonusRates);
		doc.append("rate",rate);
		return doc;
	}
	public void setFromDoc(Document doc){
		savings = (List<Integer>)doc.get("savings");
		bonusRates = (List<Integer>)doc.get("bonusRates");
		rate = doc.getInteger("rate",0);
	}
	public BankEntity toBankEntity(String username){
		int i,j;
		BankEntity entity = new BankEntity();
		entity.setSavings(savings);
		entity.setBonusRates(bonusRates);
		entity.setRate(rate);
		return entity;
	}
	
	public Bank() {
		savings = new ArrayList<>();
		bonusRates = new ArrayList<>();
	}
	
	public void gameStart() {
		for (int i=0;i<board.getPlayers().size();i++) {
			savings.add(0);
			bonusRates.add(0);
		}
	}
	
	public int getSaving(Player p) {
		if (p.getIndex()<savings.size()) {
			return savings.get(p.getIndex());
		} else {
			return 0;
		}
		
	}
	
	public int getTotal(Player p) {
		int ans = p.getMoney();
		ans = ans+savings.get(p.getIndex());
		return ans;
	}
	
	public void deposit(Player p, int x) {
		if (p.getMoney()>=x) {
			p.loseMoney(x);
			int y = savings.get(p.getIndex())+x;
			savings.set(p.getIndex(), y);
		}
	}
	
	public void withdraw(Player p, int x) {
		if (savings.get(p.getIndex())>=x) {
			p.addMoney(x);
			int y = savings.get(p.getIndex())-x;
			savings.set(p.getIndex(), y);
		}
	}
	
	public void distributeInterest() {
		for (int i=0;i<board.getPlayers().size();i++) {
			int x = savings.get(i);
			x = x * (100+rate)/100;
			savings.set(i, x);
		}
	}
	public List<Integer> getSavings() {
		return savings;
	}
	public void setSavings(List<Integer> savings) {
		this.savings = savings;
	}
	public List<Integer> getBonusRates() {
		return bonusRates;
	}
	public void setBonusRates(List<Integer> bonusRates) {
		this.bonusRates = bonusRates;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
