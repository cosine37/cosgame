package com.cosine.cosgame.rich.gta.places;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class Hospital extends Place{

	public Hospital(int id, String name) {
		super(id, name, Consts.PLACE_HOSPITAL);
	}
	
	public Hospital(Document doc, Board board) {
		super(doc, board);
	}
	
	public String getLandMsg(Player player) {
		if (player.getHp() == Consts.GTA_MAXHP) {
			return "恭喜你，你看起来身体倍儿棒";
		} else if (player.getHp() == 1) {
			return "你看起来不是很舒服，可能需要加大剂量";
		} else {
			return "可以花费$500回复1点生命值";
		} 
	}
	@Override
	public List<String> getResolveOptions(Player player){
		List<String> ans = new ArrayList<>();
		if (player.getHp() == Consts.GTA_MAXHP) {
			ans.add("谢谢夸奖");
		} else if (player.getHp() == 1) {
			ans.add("无需回复");
			if (player.getMoney() >= 500) {
				ans.add("回复1点");
			}
			if (player.getMoney() >= 1000) {
				ans.add("回复2点");
			}
		} else {
			ans.add("无需回复");
			if (player.getMoney() >= 500) {
				ans.add("回复1点");
			}
		} 
		return ans;
	}
	
	public void stepOn(Player p, int option) {
		stepOn(p);
		if (option == 0) {
			board.getLogger().log(p.getName() + " 觉得自己很不戳，决定不接受治疗");
			
			board.setBroadcastImg("avatar/head_"+p.getAvatarId());
			board.setBroadcastMsg(p.getName() + "觉得自己很不戳，决定不接受治疗");
		} else if (option == 1) {
			p.addHp(1);
			p.loseMoney(500);
			
			board.getLogger().log(p.getName() + " 在 " + name + " 花费了 $500 回复了 1 点生命值");
			
			board.setBroadcastImg("avatar/head_"+p.getAvatarId());
			board.setBroadcastMsg(p.getName() + "在" + name + "花费了$500回复了1点生命值。");
		} else if (option == 2 && p.getHp() == 1) {
			p.addHp(2);
			p.loseMoney(1000);
			
			board.getLogger().log(p.getName() + " 在 " + name + " 花费了 $1000 回复了 2 点生命值");
			
			board.setBroadcastImg("avatar/head_"+p.getAvatarId());
			board.setBroadcastMsg(p.getName() + "在" + name + "花费了$1000回复了2点生命值。");
		}
	}

}
