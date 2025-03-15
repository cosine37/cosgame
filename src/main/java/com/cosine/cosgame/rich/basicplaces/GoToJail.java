package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class GoToJail extends Place{

	public GoToJail(int id, String name) {
		super(id, name, Consts.PLACE_GOTOJAIL);
	}
	
	public GoToJail(Document doc, Board board) {
		super(doc, board);
	}
	
	public void stepOn(Player p) {
		p.goToJail();
		board.getLogger().logGoToJail(p);
	}
	
}
