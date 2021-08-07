package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Ask {
	int type;
	int subType;
	boolean single;
	int roleIndex;
	String msg;
	
	Board board;
	Player player;
	List<Player> players;
	
	public Ask() {
		players = new ArrayList<>();
	}
	
	public Ask(int type) {
		this();
		this.type = type;
	}
	
	public Ask(int type, int subType, boolean single) {
		this(type);
		this.type = type;
		this.subType = subType;
		this.single = single;
	}
	
	public void resolveAutomatic() {
		if (type == Consts.AUTOMATIC) {
			if (subType == Consts.NEXTPHASE) {
				player.nextPhase();
			} else if (subType == Consts.MOVETOTOMB) {
				player.moveToTomb(roleIndex);
			} else if (subType == Consts.RESOLVEACTION) {
				player.getArea().get(roleIndex).setChoice(Consts.ACTION);
			}
		}
	}
	
	public void resolve() {
		if (type == Consts.AUTOMATIC) {
			resolveAutomatic();
		}
	}

	public boolean isAutomatic() {
		if (type == Consts.AUTOMATIC) {
			return true;
		} else {
			return false;
		}
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public boolean isSingle() {
		return single;
	}
	public void setSingle(boolean single) {
		this.single = single;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getRoleIndex() {
		return roleIndex;
	}
	public void setRoleIndex(int roleIndex) {
		this.roleIndex = roleIndex;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public AskEntity toEntity() {
		AskEntity entity = new AskEntity();
		entity.setType(Integer.toString(type));
		entity.setSubType(Integer.toString(subType));
		entity.setMsg(msg);
		return entity;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("type", type);
		doc.append("subType", subType);
		doc.append("single", single);
		doc.append("roleIndex", roleIndex);
		doc.append("msg", msg);
		
		int playerIndex = -1;
		if (player != null) {
			playerIndex = player.getIndex();
		}
		doc.append("playerIndex", playerIndex);
		int i;
		List<Integer> playerIndexes = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerIndexes.add(players.get(i).getIndex());
		}
		doc.append("playerIndexes", playerIndexes);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		type = doc.getInteger("type", 0);
		subType = doc.getInteger("subType", 0);
		single = doc.getBoolean("single", true);
		roleIndex = doc.getInteger("roleIndex", -1);
		msg = doc.getString("msg");
		int playerIndex = doc.getInteger("playerIndex", -1);
		player = board.getPlayerByIndex(playerIndex);
		int i;
		List<Integer> playerIndexes = (List<Integer>) doc.get("playerIndexes");
		players = new ArrayList<>();
		for (i=0;i<playerIndexes.size();i++) {
			Player p = board.getPlayerByIndex(playerIndexes.get(i));
			players.add(p);
		}
	}
	
}
