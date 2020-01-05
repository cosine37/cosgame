package com.cosine.cosgame.dominion.oriental;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.Ask;
import com.cosine.cosgame.dominion.Card;
import com.cosine.cosgame.dominion.Player;

public class Quadrangle extends Card{
	public Quadrangle() {
		super();
		this.name = "Quadrangle";
		this.image = "/image/Dominion/cards/Oriental/Quadrangle.png";
		this.types[INDEX_ACTION] = true;
		this.price = 4;
		this.action = 2;
		this.memorial = 1;
	}
	
}
