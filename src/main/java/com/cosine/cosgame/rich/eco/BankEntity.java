package com.cosine.cosgame.rich.eco;

import java.util.List;

public class BankEntity {
	List<Integer> savings;
	List<Integer> bonusRates;
	int rate;
	
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
}
