package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;

public class Jail extends Place{

	public Jail(int id, String name) {
		super(id, name, Consts.PLACE_JAIL);
	}
	
	public Jail(Document doc, Board board) {
		super(doc, board);
	}

}
