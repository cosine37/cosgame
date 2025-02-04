package com.cosine.cosgame.rich.basicplaces;

import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;

public class StartPoint extends Place {

	public StartPoint(int id, String name, int type) {
		super(id, name, type);
	}

	@Override
	public void stepOn(Player p) {
		p.addSalary();
	}

	@Override
	public void bypass(Player p) {
		p.addSalary();
	}

}
