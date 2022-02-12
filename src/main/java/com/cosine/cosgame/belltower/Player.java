package com.cosine.cosgame.belltower;

import java.util.List;
import java.util.ArrayList;

import org.bson.Document;

import com.cosine.cosgame.belltower.entity.PlayerEntity;
import com.cosine.cosgame.belltower.shop.Account;

public class Player {
	String name;
	String displayName;
	protected String lastNightMsg;
	Icon icon;
	Role role;
	Board board;
	List<Integer> extraBits;
	List<Integer> targets;
	List<String> logs;
	List<String> availableCharacters;
	int index;
	boolean confirmedNight;
	boolean confirmedDay;
	boolean poisoned;
	boolean alive;
	boolean canVote;
	boolean unaffectedByDemon;
	boolean nominated;
	
	Account account;
	
	public Player() {
		role = new Role();
		extraBits = new ArrayList<>();
		targets = new ArrayList<>();
		logs = new ArrayList<>();
		icon = new Icon();
	}
	
	public void gameStart(Role r) {
		alive = true;
		poisoned = false;
		canVote = true;
		role = r;
	}
	
	public void assignIcon(List<Integer> iconArr) {
		icon.assignIcon(iconArr);
	}
	
	public void startNight() {
		confirmedNight = false;
		unaffectedByDemon = false;
	}
	
	public void confirmNight() {
		targets = new ArrayList<>();
		confirmedNight = true;
	}
	
	public void confirmNight(List<Integer> targets) {
		this.targets = targets;
		confirmedNight = true;
	}
	
	public void confirmDay() {
		confirmedDay = true;
	}
	
	public void receiveNomination() {
		nominated = true;
		//TODO: role related
	}
	
	public void executed() {
		alive = false;
		//TODO: role related
	}
	/*
	public List<String> getAvailableCharacters(){
		List<String> ans = new ArrayList<>();
		int i;
		for (i=0;i<Consts.NUMDEFAULTCHARACTER; i++) {
			ans.add(Integer.toString(i));
		}
		//TODO: apply accounts
		return ans;
	}
	*/
	
	public void addLog(String log) {
		logs.add(log);
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
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getLastNightMsg() {
		return lastNightMsg;
	}
	public void setLastNightMsg(String lastNightMsg) {
		this.lastNightMsg = lastNightMsg;
	}
	public boolean isUnaffectedByDemon() {
		return unaffectedByDemon;
	}
	public void setUnaffectedByDemon(boolean unaffectedByDemon) {
		this.unaffectedByDemon = unaffectedByDemon;
	}
	public boolean isConfirmedDay() {
		return confirmedDay;
	}
	public void setConfirmedDay(boolean confirmedDay) {
		this.confirmedDay = confirmedDay;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isNominated() {
		return nominated;
	}
	public void setNominated(boolean nominated) {
		this.nominated = nominated;
	}
	public Icon getIcon() {
		return icon;
	}
	public void setIcon(Icon icon) {
		this.icon = icon;
	}
	public List<String> getAvailableCharacters() {
		return availableCharacters;
	}
	public void setAvailableCharacters(List<String> availableCharacters) {
		this.availableCharacters = availableCharacters;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public void setupFromAccount() {
		int i;
		availableCharacters = new ArrayList<>();
		for (i=0;i<Consts.NUMDEFAULTCHARACTER;i++) {
			availableCharacters.add(Integer.toString(i));
		}
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
		doc.append("confirmedDay", confirmedDay);
		doc.append("displayName", displayName);
		doc.append("lastNightMsg", lastNightMsg);
		doc.append("unaffectedByDemon", unaffectedByDemon);
		doc.append("nominated", nominated);
		doc.append("icon", icon.toDocument());
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
		role.setPlayer(this);
		role.setBoard(board);
		Document doi = (Document) doc.get("icon");
		icon = new Icon();
		icon.setFromDoc(doi);
		canVote = doc.getBoolean("canVote", true);
		targets = (List<Integer>) doc.get("targets");
		confirmedNight = doc.getBoolean("confirmedNight", false);
		confirmedDay = doc.getBoolean("confirmedDay", false);
		displayName = doc.getString("displayName");
		lastNightMsg = doc.getString("lastNightMsg");
		unaffectedByDemon = doc.getBoolean("unaffectedByDemon", false);
		nominated = doc.getBoolean("nominated", false);
		setupFromAccount();
	}

	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setAlive(alive);
		entity.setCanVote(canVote);
		entity.setLogs(logs);
		entity.setName(name);
		entity.setDisplayName(displayName);
		entity.setNominated(nominated);
		entity.setIcon(icon.toIconEntity());
		
		return entity;
	}
}
