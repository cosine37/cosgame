package com.cosine.cosgame.pokewhat;

import com.cosine.cosgame.pokewhat.cards.*;

public class CardFactory {
	public static Card createCard(String img) {
		Card card;
		if (img.contentEquals("1")) {
			card = new DracoMeteor();
		} else if (img.contentEquals("2")) {
			card = new OblivionWing();
		} else if (img.contentEquals("3")) {
			card = new Moonlight();
		} else if (img.contentEquals("4")) {
			card = new AncientPower();
		} else if (img.contentEquals("5")) {
			card = new Thundervolt();
		} else if (img.contentEquals("6")) {
			card = new IceBeam();
		} else if (img.contentEquals("7")) {
			card = new Flamethrowner();
		} else if (img.contentEquals("8")) {
			card = new LifeDew();
		} else if (img.contentEquals("0")) {
			card = new Eternalbeam();
		}
		
		else {
			card = new Card();
			card.setImg(img);
			card.setNum(0);
		}
		
		return card;
	}
	
	public static Card createCard(int x) {
		Card card;
		if (x == 1) {
			card = new DracoMeteor();
		} else if (x == 2) {
			card = new OblivionWing();
		} else if (x == 3) {
			card = new Moonlight();
		} else if (x == 4) {
			card = new AncientPower();
		} else if (x == 5) {
			card = new Thundervolt();
		} else if (x == 6) {
			card = new IceBeam();
		} else if (x == 7) {
			card = new Flamethrowner();
		} else if (x == 8) {
			card = new LifeDew();
		} else if (x == 0) {
			card = new Eternalbeam();
		}
		
		else {
			String img = Integer.toString(x);
			card = new Card();
			card.setImg(img);
			card.setNum(x);
		}
		
		return card;
	}
}
