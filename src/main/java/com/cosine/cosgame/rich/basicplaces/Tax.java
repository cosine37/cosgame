package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class Tax extends Place{
	int rate;
	
	public Document toDocument() {
		Document doc = super.toDocument();
		doc.append("rate", rate);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		super.setFromDoc(doc);
		rate = doc.getInteger("rate", 0);
	}
	
	public Tax(int id, String name, int rate) {
		super(id, name, Consts.PLACE_TAX);
		this.rate = rate;
		initializeMsg();
	}
	
	public Tax(Document doc, Board board) {
		super(doc, board);
		initializeMsg();
	}
	
	public void initializeMsg() {
		this.desc = "支付$" + rate;
		this.landMsg = "请支付$" + rate + "给银行";
	}
	
	public void stepOn(Player p) {
		p.loseMoney(rate);
		
		board.getLogger().logPlayerLoseMoney(p, rate);
		
		board.setBroadcastImg("avatar/head_"+p.getAvatarId());
		board.setBroadcastMsg(p.getName() + "支付了" + name + "$" + rate + "。");
	}

	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}

}
