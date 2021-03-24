package com.cosine.cosgame.zodiac.roles;

import java.util.Random;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Role;

public class Thief extends Role{
	public Thief() {
		super();
		num = Consts.THIEF;
		img = Consts.THIEFIMG;
		name = "绑匪";
		numPlayerChoose = 0;
		numZodiacChoose = 1;
		numHigherChoose = 1;
		side = Consts.BAD;
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else {
			Manipulations.inspect(this);
			Manipulations.steal(this);
		}
	}
}
