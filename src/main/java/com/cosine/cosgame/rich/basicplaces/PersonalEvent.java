package com.cosine.cosgame.rich.basicplaces;

import java.util.Random;

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
		} else if (x == 10006) {
			ans = 910006;
		} else if (x == 10009) {
			ans = 910009;
		} else if (x == 10010) {
			ans = 910010;
		} else if (x == 10020) {
			ans = 910020;
		}
		return ans;
	}
	
	public void preStepOn(Player p) {
		int x = board.getMap().genRandomFateId();
		// NEW dominant fate related
		if (board.getNewsBuff().getDominantFate() != -1) {
			Random rand = new Random();
			int y = rand.nextInt(3);
			if (y>0) x = board.getNewsBuff().getDominantFate();
		}
		
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
		board.setSesPlayer(Consts.SES_ALLPLAYERS);
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
