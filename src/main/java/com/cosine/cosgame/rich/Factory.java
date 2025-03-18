package com.cosine.cosgame.rich;

import org.bson.Document;

import com.cosine.cosgame.rich.basicplaces.*;

public class Factory {
	public static Card genCard(int id) {
		Card c = new Card();
		
		return c;
	}
	
	public static Place genPlace(Document doc, Board board) {
		Place p = null;
		int type = doc.getInteger("type", 0);
		if (type == Consts.PLACE_EMPTY) {
			p = new Empty(doc, board);
		} else if (type == Consts.PLACE_STARTPOINT) {
			p = new StartPoint(doc, board);
		} else if (type == Consts.PLACE_TAX) {
			p = new Tax(doc, board);
		} else if (type == Consts.PLACE_ESTATE) {
			p = new Estate(doc, board);
		} else if (type == Consts.PLACE_FATE) {
			p = new PersonalEvent(doc, board);
		} else if (type == Consts.PLACE_JAIL) {
			p = new Jail(doc, board);
		} else if (type == Consts.PLACE_GOTOJAIL) {
			p = new GoToJail(doc, board);
		}
		return p;
	}
	
	public static Fate genFate(int id) {
		Fate fate = null;
		if (id == 1) {
			return new Fate(1,Consts.FATE_ADD,100,"在路边捡到$100。","p在路边捡到了$100");
		} else if (id == 2) {
			return new Fate(2,Consts.FATE_LOSE,200,"在景区内随地吐痰，罚款$200。","p因为在景区内随地吐痰，被罚款$200");
		} else if (id == 3) {
			return new Fate(2,Consts.FATE_GOTOJAIL,0,"酒后大闹景区，立即入狱。","p因为喝酒闹事，进入监狱");
		}
		return fate;
	}
	
	public static Avatar genAvatar(int id) {
		Avatar avatar = null;
		if (id == -1) {
			avatar = new Avatar(9999,"未解锁");
		} else if (id == 0) {
			avatar = new Avatar(0,"蓝猫");
		} else if (id == 1) {
			avatar = new Avatar(1,"淘气");
		} else if (id == 2) {
			avatar = new Avatar(2,"菲菲");
		} else if (id == 3) {
			avatar = new Avatar(3,"咖喱");
		} else if (id == 4) {
			avatar = new Avatar(4,"肥仔");
		} else if (id == 5) {
			avatar = new Avatar(5,"鸡大婶");
		} else if (id == 6) {
			avatar = new Avatar(6,"甜妞");
		} else if (id == 7) {
			avatar = new Avatar(7,"啦啦");
		}
		
		return avatar;
	}
}
