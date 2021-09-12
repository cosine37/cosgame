package com.cosine.cosgame.onenight.roles;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Player;
import com.cosine.cosgame.onenight.Question;
import com.cosine.cosgame.onenight.Role;

public class Gypsy extends Role{
	public Gypsy() {
		super();
		roleNum = 72;
		side = Consts.HUMAN;
		img = "r72";
		sequence = 440;
		name = "吉普赛娘";
		hasNight = true;
		votedMsg.add("你的初始身份是 吉普赛娘。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		//System.out.println("choice:"+t1);
		if (t1 == 0 || t1 == 1) {
			player.setQuestionChoosed(t1);
		}
		
	}
	
	public void executeSkill() {
		if (player.getQuestionChoosed()==0 || player.getQuestionChoosed()==1) {
			int x = player.getQuestionChoices().get(player.getQuestionChoosed());
			Question q = board.getQuestions().get(x);
			q.setBoard(board);
			q.setPlayer(player);
			q.genAnswer();
		}
	}
	
	public List<String> getNightMsg(){
		nightMsg.add("你的初始身份是 吉普赛娘。");
		if (player.getQuestionChoices().size() >=2) {
			nightMsg.add("请选择一个你要问的问题。");
			int x1 = player.getQuestionChoices().get(0);
			String q1Text = board.getQuestions().get(x1).getQuestionText();
			int x2 = player.getQuestionChoices().get(1);
			String q2Text = board.getQuestions().get(x2).getQuestionText();
			nightMsg.add("A: " + q1Text);
			nightMsg.add("B: " + q2Text);
		}
		return nightMsg;
	}
	
	
	public List<String> getConfirmedMsg() {
		confirmedMsg.add("你的初始身份是 吉普赛娘。");
		if (player.getQuestionChoosed()==0 || player.getQuestionChoosed()==1) {
			String qtext = board.getQuestions().get(player.getQuestionChoosed()).getQuestionText();
			confirmedMsg.add("你提的问题是：" + qtext + " 天亮时你将会看到答案。");
		}
		confirmedMsg.add("正等待其他玩家确认。");
		return confirmedMsg;
	}
	
	public List<String> getDayMsg() {
		dayMsg.add("你的初始身份是 吉普赛娘，你现在的身份可能已有变化。");
		if (player.getQuestionChoosed()==0 || player.getQuestionChoosed()==1) {
			String qtext = board.getQuestions().get(player.getQuestionChoosed()).getQuestionText();
			String atext = board.getQuestions().get(player.getQuestionChoosed()).getAnswerText();
			dayMsg.add("你提的问题是：" + qtext);
			dayMsg.add("该问题的答案是：" + atext);
		}
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		return dayMsg;
	}
	
}
