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
			board.getLogger().logFate(p, fate.getConversation(p));
			
			fate.apply(p);
		}
		
	}
	
	public int vehicleTweak(int x) {
		int ans = x;
		if (x == 10001) {
			ans = 910001;
		}
		return ans;
	}
	
	public void preStepOn(Player p) {
		int x = board.getMap().genRandomFateId();
		if (p.getVehicle() != null && p.getVehicle().getId() > -1) {
			x = vehicleTweak(x);
		}
		
		board.setLastFateId(x);
		
		// broadcast fate
		Fate fate = Factory.genFate(x);
		board.setBroadcastImg("fate/" + x);
		String broadcastMsg = p.getName() + "的" + name + "：" + fate.getContent();
		board.setBroadcastMsg(broadcastMsg);
		
		// add se
		String seSrc = "/sound/Rich/fate/" + x + ".mp3";
		board.addSes(seSrc);
	}
	
	public String getLandMsg(Player player) {
		String s = "";
		Fate fate = Factory.genFate(board.getLastFateId());
		if (fate != null) {
			String c = fate.getConversation(player);
			for (int i=0;i<c.length();i++) {
				if (c.charAt(i) == 'p') {
					s = s+"你";
				} else {
					s = s+c.charAt(i);
				}
			}
		}
		return s;
	}


}
