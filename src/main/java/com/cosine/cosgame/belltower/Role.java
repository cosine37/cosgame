package com.cosine.cosgame.belltower;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cosine.cosgame.belltower.entity.RoleEntity;

public class Role {
	protected int id;
	protected String name;
	protected String img;
	protected String desc;
	protected int faction;
	protected int group;
	protected int sequence;
	protected int numPlayerChoose;
	protected boolean drunk;
	protected boolean hasFirstNight;
	protected boolean hasRestNights;
	
	protected Player player;
	protected Board board;
	
	public Role() {
		numPlayerChoose = 0;
		hasFirstNight = false;
		hasRestNights = false;
	}
	
	public int getNumPlayerChoose() {
		int numDay = board.getNumDay();
		if (numDay == 1) {
			if (hasFirstNight) {
				return numPlayerChoose;
			} else {
				return 0;
			}
		} else {
			if (hasRestNights) {
				return numPlayerChoose;
			} else {
				return 0;
			}
		}
	}
	
	public void execSkill() {
		if (player.isPoisoned()) {
			execSkillPoisoned();
		} else {
			execSkillNormal();
		}
	}
	
	public void execSkillNormal() {
		
	}
	
	public void execSkillPoisoned() {
		
	}
	
	public void onKilled() {
		
	}
	
	public List<Player> twoPlayersFromGroupFake(int group){
		List<Player> ans = new ArrayList<>();
		int i;
		List<Player> tempPlayers = new ArrayList<>();
		List<Player> eligiblePlayers = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				continue;
			}
			tempPlayers.add(p);
			if (p.getRole().getGroup() == group) {
				eligiblePlayers.add(p);
			}
		}
		if (eligiblePlayers.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(eligiblePlayers.size());
			Player faker = eligiblePlayers.get(x);
			Player p1 = null;
			Player p2 = null;
			if (tempPlayers.size() > 0) {
				int y = rand.nextInt(tempPlayers.size());
				p1 = tempPlayers.remove(y);
			}
			while (tempPlayers.size() > 0) {
				int y = rand.nextInt(tempPlayers.size());
				p2 = tempPlayers.remove(y);
				if (p2.getName().contentEquals(p1.getName())) {
					continue;
				}
				break;
			}
			if (p1 != null && p2 != null && faker != null) {
				ans.add(p1);
				ans.add(p2);
				ans.add(faker);
			}
		}
		return ans;
	}
	
	public List<Player> twoPlayersFromGroup(int group){
		List<Player> ans = new ArrayList<>();
		int i;
		List<Player> tempPlayers = new ArrayList<>();
		List<Player> eligiblePlayers = new ArrayList<>();
		for (i=0;i<board.getPlayers().size();i++) {
			Player p = board.getPlayers().get(i);
			if (p.getName().contentEquals(player.getName())) {
				continue;
			}
			tempPlayers.add(p);
			if (p.getRole().getGroup() == group) {
				eligiblePlayers.add(p);
			}
		}
		if (eligiblePlayers.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(eligiblePlayers.size());
			Player p1 = eligiblePlayers.remove(x);
			Player p2 = null;
			while (tempPlayers.size() > 0) {
				int y = rand.nextInt(tempPlayers.size());
				p2 = tempPlayers.remove(y);
				if (p2.getName().contentEquals(p1.getName())) {
					continue;
				}
				break;
			}
			if (p1 != null && p2 != null) {
				ans.add(p1);
				ans.add(p2);
			}
		}
		return ans;
	}
	
	public String getMessageForTwoPlayersFromGroup(List<Player> players, String roleName) {
		String ans = "";
		if (players.size() > 1) {
			Player p1 = players.get(0);
			Player p2 = players.get(1);
			Random rand = new Random();
			boolean swap = rand.nextBoolean();
			if (swap) {
				ans = p2.getDisplayName() + "和" + p1.getDisplayName();
			} else {
				ans = p1.getDisplayName() + "和" + p2.getDisplayName();
			}
			ans = ans + "中有一名玩家的身份是 " + roleName + "。";
		}
		return ans;
	}
	
	public String getMessageForTwoPlayersFromGroup(List<Player> players) {
		String ans = "";
		if (players.size() > 1) {
			String roleName = players.get(0).getRole().getName();
			ans = getMessageForTwoPlayersFromGroup(players, roleName);
		}
		return ans;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getFaction() {
		return faction;
	}
	public void setFaction(int faction) {
		this.faction = faction;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public boolean isDrunk() {
		return drunk;
	}
	public void setDrunk(boolean drunk) {
		this.drunk = drunk;
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
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public boolean isHasFirstNight() {
		return hasFirstNight;
	}
	public void setHasFirstNight(boolean hasFirstNight) {
		this.hasFirstNight = hasFirstNight;
	}
	public boolean isHasRestNights() {
		return hasRestNights;
	}
	public void setHasRestNights(boolean hasRestNights) {
		this.hasRestNights = hasRestNights;
	}
	public void setNumPlayerChoose(int numPlayerChoose) {
		this.numPlayerChoose = numPlayerChoose;
	}

	public List<String> getInstructions() {
		String s =  "你的身份是 " + name;
		List<String> instruction = new ArrayList<>();
		instruction.add(s);
		return instruction;
	}

	public RoleEntity toRoleEntity() {
		RoleEntity entity = new RoleEntity();
		entity.setId(id);
		entity.setName(name);
		entity.setDesc(desc);
		entity.setImg(img);
		entity.setNumPlayerChoose(getNumPlayerChoose());
		entity.setInstructions(getInstructions());
		return entity;
	}
}
