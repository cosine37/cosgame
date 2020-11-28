package com.cosine.cosgame.pokewhat.cards;

import java.util.Random;

import com.cosine.cosgame.pokewhat.Card;
import com.cosine.cosgame.pokewhat.Player;

public class AncientPower extends Card{
	public AncientPower() {
		num = 4;
		img = "4";
		name = "原始之力";
	}
	
	public void cardEffect() {
		Card c = board.getAncient().remove(0);
		player.getAncient().add(c);
		board.getLogger().logAncient(player);
	}
	
	
}
