package com.cosine.cosgame.zodiac.roles;

import java.util.Random;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Role;

public class Kidnapper extends Role{
	public Kidnapper() {
		super();
		num = Consts.KIDNAPPER;
		img = Consts.KIDNAPPERIMG;
		name = "绑匪";
		numPlayerChoose = 1;
		numZodiacChoose = 1;
		numHigherChoose = 0;
		side = Consts.BAD;
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else {
			Manipulations.inspect(this);
			Manipulations.disablePlayer(this);
		}
	}
}
