package com.cosine.cosgame.onenight.roles;

import com.cosine.cosgame.onenight.Consts;
import com.cosine.cosgame.onenight.Role;

public class Investigator extends Role{
	public Investigator() {
		super();
		roleNum = 25;
		side = Consts.HUMAN;
		img = "r25";
		sequence = 580;
		name = "调查员";
		choosePlayerNum = 1;
		hasNight = true;
		nightMsg.add("你的初始身份是 调查员。");
		nightMsg.add("你可以选择一名其它角色的身份牌查看。");
		nightMsg.add("选择后点击确认结束你的夜晚阶段，之后你可以看到你选择的身份牌。");
		nightMsg.add("若你选择查看，你的阵营和你查看的身份牌阵营相同。");
		confirmedMsg.add("你的初始身份是 调查员。");
		confirmedMsg.add("场上显示的身份牌是你发动你技能时指定的身份牌。");
		confirmedMsg.add("你初始身份右侧的身份显示了你目前的阵营，但你的身份可能已有变化。");
		confirmedMsg.add("正等待其他玩家确认。");
		dayMsg.add("你的初始身份是 调查员，你现在的身份可能已有变化。");
		dayMsg.add("场上显示的身份牌是你发动你技能时所看到的身份牌，可能已有变化。");
		dayMsg.add("你初始身份右侧的身份显示了你目前的阵营，但你的身份可能已有变化。");
		dayMsg.add("你可以选择某位玩家的身份牌并点击确认投票给该玩家。");
		votedMsg.add("你的初始身份是 调查员，你现在的身份可能已有变化。");
		votedMsg.add("你已投票，正等待其他玩家投票。");
	}
	
	public void useSkill(int t1) {
		if (t1 < board.getPlayers().size()) {
			Role r = board.getPlayers().get(t1).getCurrentRole();
			int x = r.getRoleNum();
			player.getPlayerMarks().set(t1, x);
			if (r.getSide() == Consts.HUMAN) {
				this.side = Consts.HUMAN;
			} else if (r.getSide() == Consts.WOLF) {
				this.side = Consts.WOLF;
			} else if (r.getSide() == Consts.TANNER) {
				this.side = Consts.TANNER;
			}
			player.setUpdatedRole(this);
			player.setShowUpdatedRole(true);
			if (r.getRoleNum() == Consts.PAGAN) {
				Role r1 = new QuoteWerewolf();
				player.addRole(r1);
				player.setUpdatedRole(r1);
				player.setShowUpdatedRole(true);
			}
		}
	}
	
	public String getDBStorageImg() {
		if (this.side == Consts.HUMAN) {
			return "r2500";
		} else if (this.side == Consts.WOLF) {
			return "r2501";
		} else if (this.side == Consts.TANNER) {
			return "r2502";
		} else {
			return "r25";
		}
	}
	
	public String getFinalImg() {
		if (board.getStatus() == Consts.AFTERVOTE) {
			if (this.side == Consts.HUMAN) {
				return "r2500";
			} else if (this.side == Consts.WOLF) {
				return "r2501";
			} else if (this.side == Consts.TANNER) {
				return "r2502";
			}
		}
		if (player.getInitialRole().getRoleNum() == Consts.INVESTIGATOR) {
			if (this.side == Consts.HUMAN) {
				return "r2500";
			} else if (this.side == Consts.WOLF) {
				return "r2501";
			} else if (this.side == Consts.TANNER) {
				return "r2502";
			}
		}
		return img;
	}
}
