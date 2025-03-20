package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Factory;
import com.cosine.cosgame.rich.Fate;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class PersonalEvent extends Place {
	public PersonalEvent(int id, String name) {
		super(id, name, Consts.PLACE_FATE);
	}
	
	public PersonalEvent(Document doc, Board board) {
		super(doc,board);
	}

	@Override
	public void stepOn(Player p) {
		super.stepOn(p);
		Fate fate = Factory.genFate(board.getLastFateId());
		
		if (fate != null) {
			fate.apply(p);
			
			board.getLogger().logFate(p, fate.getConversation());
		}
		
	}
	
	public void preStepOn(Player p) {
		int x = board.getMap().genRandomFateId();
		board.setLastFateId(x);
		
		// broadcast fate
		Fate fate = Factory.genFate(x);
		board.setBroadcastImg("fate/" + x);
		String broadcastMsg = p.getName() + "的见闻：" + fate.getContent();
		board.setBroadcastMsg(broadcastMsg);
	}
	
	public String getLandMsg(Player player) {
		String s = "";
		Fate fate = Factory.genFate(board.getLastFateId());
		if (fate != null) {
			for (int i=0;i<fate.getConversation().length();i++) {
				if (fate.getConversation().charAt(i) == 'p') {
					s = s+"你";
				} else {
					s = s+fate.getConversation().charAt(i);
				}
			}
		}
		return s;
	}


}
