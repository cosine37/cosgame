package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class StartPoint extends Place {

	public StartPoint(int id, String name) {
		super(id, name, Consts.PLACE_STARTPOINT);
	}
	
	public StartPoint(Document doc, Board board) {
		super(doc,board);
	}

	public void stepOn(Player p) {
		super.stepOn(p);
		p.addSalary();
	}
	
	public void bypass(Player p) {
		super.bypass(p);
		p.addSalary();
	}

}
