package com.cosine.cosgame.rich.basicplaces;

import org.bson.Document;

import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;

public class Empty extends Place{

	public Empty(int id, String name) {
		super(id, name, Consts.PLACE_EMPTY);
	}
	
	public Empty(Document doc) {
		super(doc);
	}

}
