package com.cosine.cosgame.zodiac.roles;

import java.util.Random;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Role;

public class Apprentice1 extends Role{
	public Apprentice1() {
		super();
		num = Consts.APPRENTICE1;
		img = Consts.APPRENTICE1IMG;
		name = "学徒";
		numPlayerChoose = 0;
		numZodiacChoose = 1;
		numHigherChoose = 0;
		side = Consts.GOOD;
	}
	
	public void initialize() {
		super.initialize();
		Random rand = new Random();
		int x = rand.nextInt(Consts.NUMROUNDS);
		player.setDisabledTurn(x);
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else {
			Manipulations.inspect(this);
		}
	}
}
