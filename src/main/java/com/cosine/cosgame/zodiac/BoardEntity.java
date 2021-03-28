package com.cosine.cosgame.zodiac;

import java.util.List;

public class BoardEntity {
	List<String> players;
	List<String> zodiacs;
	List<String> zodiacImages;
	
	String status;
	String phase;
	String round;
	String lord;
	
	public List<String> getPlayers() {
		return players;
	}
	public void setPlayers(List<String> players) {
		this.players = players;
	}
	public List<String> getZodiacs() {
		return zodiacs;
	}
	public void setZodiacs(List<String> zodiacs) {
		this.zodiacs = zodiacs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public List<String> getZodiacImages() {
		return zodiacImages;
	}
	public void setZodiacImages(List<String> zodiacImages) {
		this.zodiacImages = zodiacImages;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
}
