package com.cosine.cosgame.gravepsycho.event;

import java.util.List;

import com.cosine.cosgame.gravepsycho.Card;
import com.cosine.cosgame.gravepsycho.Consts;
import com.cosine.cosgame.gravepsycho.Event;
import com.cosine.cosgame.gravepsycho.Player;

public class Dodge extends Event{
	public Dodge() {
		super();
		this.num = 22;
		this.img = "dodge";
		this.name = "闪躲翻滚";
		this.desc = "灾难发生的回合若你选择了偷盗（无论成功与否），你不会失去本轮获得的钱币。";
	}
	
	public boolean disasterHandle(Player p) {
		if (p.getDecisionLastTurn() >= Consts.THIEFDECISIONS) {
			board.getLogger().log("埋伏在归途的"+p.getName()+"身手敏捷地躲过了这一场灾难");
			return true;
		} else {
			return false;
		}
	}
}
