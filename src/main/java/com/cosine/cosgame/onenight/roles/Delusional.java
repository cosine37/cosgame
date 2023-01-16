package com.cosine.cosgame.onenight.roles;

import java.util.List;

import com.cosine.cosgame.onenight.Board;
import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Role;

public class Delusional extends Role{
	public Delusional() {
		super();
		roleNum = 85;
		side = Consts.HUMAN;
		img = "r85";
		sequence = 0;
		name = "妄想者";
		nightMsg.add("你的初始身份是 妄想者。");
		nightMsg.add("点击确认结束你的夜晚阶段。");
		confirmedMsg.add("你的初始身份是 妄想者。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 妄想者，你现在的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 妄想者，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public Role retrieveDelusionalRole() {
		Role r = board.getDelusionalRole();
		r.setBoard(board);
		r.setPlayer(player);
		return r;
	}
	
	public void vision() {
		Role r = retrieveDelusionalRole();
		r.visionPoisoned();
	}
	
	public void visionPoisoned() {
		Role r = retrieveDelusionalRole();
		r.visionPoisoned();
	}
	
	public void executeDuskSkill() {
		Role r = retrieveDelusionalRole();
		r.executeDuskSkillPoisoned();
	}
	
	public void executeDuskSkillPoisoned() {
		Role r = retrieveDelusionalRole();
		r.executeDuskSkillPoisoned();
	}
	
	public void useSkill(int t1) {
		Role r = retrieveDelusionalRole();
		r.useSkill(t1);
	}
	
	public void useSkill(int t1, int t2) {
		Role r = retrieveDelusionalRole();
		r.useSkill(t1, t2);
	}
	
	public void executeSkill() {
		Role r = retrieveDelusionalRole();
		r.executeSkillPoisoned();
	}
	
	public void executeSkillPoisoned() {
		Role r = retrieveDelusionalRole();
		r.executeSkillPoisoned();
	}
	
	public void onDawnSkill() {
		Role r = retrieveDelusionalRole();
		r.onDawnSkillPoisoned();
	}
	
	public void onDawnSkillPoisoned() {
		Role r = retrieveDelusionalRole();
		r.onDawnSkillPoisoned();
	}
	
	public int getChoosePlayerNum() {
		Role r = retrieveDelusionalRole();
		return r.getChoosePlayerNum();
	}
	public int getChooseCenterNum() {
		Role r = retrieveDelusionalRole();
		return r.getChooseCenterNum();
	}
	public boolean isCanChooseBoth() {
		Role r = retrieveDelusionalRole();
		return r.isCanChooseBoth();
	}
	public boolean isHasNight() {
		Role r = retrieveDelusionalRole();
		return r.isHasNight();
	}
	public boolean isMandatory() {
		Role r = retrieveDelusionalRole();
		return r.isMandatory();
	}
	public List<String> getNightMsg() {
		Role r = retrieveDelusionalRole();
		return r.getNightMsg();
	}

	public List<String> getDayMsg() {
		Role r = retrieveDelusionalRole();
		return r.getDayMsg();
	}

	public List<String> getConfirmedMsg() {
		Role r = retrieveDelusionalRole();
		return r.getConfirmedMsg();
	}

	public List<String> getVotedMsg() {
		Role r = retrieveDelusionalRole();
		return r.getVotedMsg();
	}
	
	public boolean isHasDusk() {
		Role r = retrieveDelusionalRole();
		return r.isHasDusk();
	}
	public int getChooseStatusNum() {
		Role r = retrieveDelusionalRole();
		return r.getChooseStatusNum();
	}
	public boolean isUseStatus() {
		Role r = retrieveDelusionalRole();
		return r.isUseStatus();
	}
	public boolean isCanChooseSelfStatus() {
		Role r = retrieveDelusionalRole();
		return r.isCanChooseSelfStatus();
	}

	public List<String> getDuskMsg() {
		Role r = retrieveDelusionalRole();
		return r.getDuskMsg();
	}
	public List<String> getConfirmedDuskMsg() {
		Role r = retrieveDelusionalRole();
		return r.getConfirmedDuskMsg();
	}
}
