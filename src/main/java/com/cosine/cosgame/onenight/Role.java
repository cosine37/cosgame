package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.List;

public class Role {
	protected int roleNum;
	protected int side;
	protected int sequence;
	protected String name;
	protected String img;
	protected List<String> duskMsg;
	protected List<String> confirmedDuskMsg;
	protected List<String> nightMsg;
	protected List<String> dayMsg;
	protected List<String> confirmedMsg;
	protected List<String> votedMsg;
	
	protected int choosePlayerNum;
	protected int chooseCenterNum;
	protected int chooseStatusNum;
	protected boolean canChooseBoth;
	protected boolean hasNight;
	protected boolean mandatory;
	protected boolean hasDusk;
	protected boolean useStatus;
	
	protected Player player;
	protected Board board;
	
	public Role() {
		choosePlayerNum = 0;
		chooseCenterNum = 0;
		chooseStatusNum = 0;
		canChooseBoth = false;
		hasNight = false;
		hasDusk = false;
		useStatus = false;
		mandatory = false;
		name = "";
		img = "";
		duskMsg = new ArrayList<>();
		confirmedDuskMsg = new ArrayList<>();
		nightMsg = new ArrayList<>();
		dayMsg = new ArrayList<>();
		confirmedMsg = new ArrayList<>();
		votedMsg = new ArrayList<>();
	}
	
	public void vision() {
		
	}
	
	public void executeDuskSkill() {
		
	}
	
	public void useSkill(int t1) {
		
	}
	
	public void useSkill(int t1, int t2) {
		
	}
	
	public void useSkill(List<Integer> targets) {
		if (targets.size() == 1) {
			useSkill(targets.get(0));
		} else if (targets.size() == 2) {
			useSkill(targets.get(0), targets.get(1));
		}
	}
	
	public void executeSkill() {
		
	}
	
	public void onDawnSkill() {
		
	}
	
	public void receiveVote(int x) {
		player.receiveVote(x);
	}

	public int voteValue() {
		return 1;
	}
	
	public void voteHandle() {
		int v = voteValue();
		int x = player.getVoteIndex();
		if (x>=0 && x<=board.getPlayers().size()) {
			Player p = board.getPlayers().get(x);
			p.getCurrentRole().receiveVote(v);
			p.getCurrentRole().votedReaction(player);
		}
	}
	
	public void votedReaction(Player p) {
		
	}
	
	public void afterVoteCountHandle() {
		
	}
	
	public boolean win(boolean f) {
		return f;
	}
	
	public boolean votedThis(boolean f) {
		return f;
	}
	
	public boolean canConfirm(List<Integer> targets) {
		return true;
	}
	
	public boolean exchangable() {
		return true;
	}
	
	public void dayHandle() {
		
	}
	
	public void onView(Player viewer) {
		
	}
	
	public int getRoleNumToShow() {
		return roleNum;
	}
	
	public int getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
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
	public String getFinalImg() {
		return getImg();
	}
	public String getDisplayImg() {
		return getImg();
	}
	public String getDBStorageImg() {
		return getImg();
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	public int getChoosePlayerNum() {
		return choosePlayerNum;
	}
	public void setChoosePlayerNum(int choosePlayerNum) {
		this.choosePlayerNum = choosePlayerNum;
	}
	public int getChooseCenterNum() {
		return chooseCenterNum;
	}
	public void setChooseCenterNum(int chooseCenterNum) {
		this.chooseCenterNum = chooseCenterNum;
	}
	public boolean isCanChooseBoth() {
		return canChooseBoth;
	}
	public void setCanChooseBoth(boolean canChooseBoth) {
		this.canChooseBoth = canChooseBoth;
	}
	public boolean isHasNight() {
		return hasNight;
	}
	public void setHasNight(boolean hasNight) {
		this.hasNight = hasNight;
	}
	public boolean isMandatory() {
		return mandatory;
	}
	public void setMandatory(boolean mandatory) {
		this.mandatory = mandatory;
	}

	public List<String> getNightMsg() {
		return nightMsg;
	}

	public void setNightMsg(List<String> nightMsg) {
		this.nightMsg = nightMsg;
	}

	public List<String> getDayMsg() {
		return dayMsg;
	}

	public void setDayMsg(List<String> dayMsg) {
		this.dayMsg = dayMsg;
	}

	public List<String> getConfirmedMsg() {
		return confirmedMsg;
	}

	public void setConfirmedMsg(List<String> confirmedMsg) {
		this.confirmedMsg = confirmedMsg;
	}

	public List<String> getVotedMsg() {
		return votedMsg;
	}

	public void setVotedMsg(List<String> votedMsg) {
		this.votedMsg = votedMsg;
	}
	public boolean isHasDusk() {
		return hasDusk;
	}
	public void setHasDusk(boolean hasDusk) {
		this.hasDusk = hasDusk;
	}
	public int getChooseStatusNum() {
		return chooseStatusNum;
	}
	public void setChooseStatusNum(int chooseStatusNum) {
		this.chooseStatusNum = chooseStatusNum;
	}
	public boolean isUseStatus() {
		return useStatus;
	}
	public void setUseStatus(boolean useStatus) {
		this.useStatus = useStatus;
	}

	public List<String> getDuskMsg() {
		if (duskMsg.size() == 0) {
			duskMsg.add("你的身份是 " + name + "。");
			duskMsg.add("点击确认结束你的黄昏阶段。");
		}
		return duskMsg;
	}
	public void setDuskMsg(List<String> duskMsg) {
		this.duskMsg = duskMsg;
	}
	public List<String> getConfirmedDuskMsg() {
		if (confirmedDuskMsg.size() == 0) {
			confirmedDuskMsg.add("你的身份是 " + name + "。");
			confirmedDuskMsg.add("正等待其他玩家确认。");
		}
		return confirmedDuskMsg;
	}
	public void setConfirmedDuskMsg(List<String> confirmedDuskMsg) {
		this.confirmedDuskMsg = confirmedDuskMsg;
	}
	
}
