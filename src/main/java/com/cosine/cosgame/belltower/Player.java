package com.cosine.cosgame.belltower;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.belltower.entity.PlayerEntity;

public class Player {
	String name;
	Role role;
	Board board;
	List<Integer> extraBits;
	List<Integer> targets;
	List<String> logs;
	boolean confirmedNight;
	boolean poisoned;
	boolean alive;
	boolean canVote;
	
	public Player() {
		role = new Role();
	}
	
	public void gameStart(Role r) {
		alive = true;
		poisoned = false;
		canVote = true;
		role = r;
	}
	
	public void startNight() {
		confirmedNight = false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<String> getLogs() {
		return logs;
	}
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	public boolean isPoisoned() {
		return poisoned;
	}
	public void setPoisoned(boolean poisoned) {
		this.poisoned = poisoned;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public List<Integer> getExtraBits() {
		return extraBits;
	}
	public void setExtraBits(List<Integer> extraBits) {
		this.extraBits = extraBits;
	}
	public boolean isCanVote() {
		return canVote;
	}
	public void setCanVote(boolean canVote) {
		this.canVote = canVote;
	}
	public List<Integer> getTargets() {
		return targets;
	}
	public void setTargets(List<Integer> targets) {
		this.targets = targets;
	}
	public boolean isConfirmedNight() {
		return confirmedNight;
	}
	public void setConfirmedNight(boolean confirmedNight) {
		this.confirmedNight = confirmedNight;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("role", role.getId());
		doc.append("poisoned", poisoned);
		doc.append("alive", alive);
		doc.append("logs", logs);
		doc.append("extraBits", extraBits);
		doc.append("canVote", canVote);
		doc.append("targets", targets);
		doc.append("confirmedNight", confirmedNight);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		poisoned = doc.getBoolean("poisoned", false);
		alive = doc.getBoolean("alive", true);
		logs = (List<String>) doc.get("logs");
		extraBits = (List<Integer>) doc.get("extraBits");
		int roleId = doc.getInteger("role", -1);
		role = RoleFactory.makeRole(roleId);
		canVote = doc.getBoolean("canVote", true);
		targets = (List<Integer>) doc.get("targets");
		confirmedNight = doc.getBoolean("confirmedNight", false);
	}
	
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setAlive(alive);
		entity.setCanVote(canVote);
		entity.setLogs(logs);
		entity.setName(name);
		return entity;
	}
}
