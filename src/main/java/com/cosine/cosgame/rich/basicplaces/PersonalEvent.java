package com.cosine.cosgame.rich.basicplaces;

import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class PersonalEvent extends Place {
	int type;

	public PersonalEvent(int id, String name, int type) {
		super(id, name, type);
	}

	@Override
	public void stepOn(Player p) {
		
	}

	@Override
	public void bypass(Player p) {
		
	}

}
