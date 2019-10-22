package com.cosine.cosgame.minigame.xutangbo;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {
	List<PlayerEntity> players;
	String id;
	String lord;
	int status;
	int round;
	int step;
	List<String> logs;
	boolean disableMove;
	public GameEntity() {
		players = new ArrayList<>();
		disableMove = false;
	}
	public GameEntity(Game game) {
		players = new ArrayList<>();
		for (int i=0;i<game.getPlayers().size();i++) {
			Player p = game.getPlayers().get(i);
			PlayerEntity pe = new PlayerEntity(p);
			players.add(pe);
		}
		id = game.getId();
		lord = game.getLord();
		status = game.getStatus();
		round = game.getRound();
		step = game.getStep();
		logs = game.getLogs().getLogsAsStrings();
	}
	public void shouldDisableMove(String name) {
		disableMove = true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				if (players.get(i).getStatus() == Player.ALIVE) {
					disableMove = false;
				} else {
					disableMove = true;
				}
			}
		}
		if (status == Game.ENDGAME) disableMove = true;
	}
	public List<PlayerEntity> getPlayers() {
		return players;
	}
	public void setPlayers(List<PlayerEntity> players) {
		this.players = players;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public boolean isDisableMove() {
		return disableMove;
	}
	public void setDisableMove(boolean disableMove) {
		this.disableMove = disableMove;
	}
	
	
}
