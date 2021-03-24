package com.cosine.cosgame.zodiac.roles;

import java.util.Random;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Role;

public class Swindler extends Role{
	public Swindler() {
		super();
		num = Consts.SWINDLER;
		img = Consts.SWINDLERIMG;
		name = "骗子";
		numPlayerChoose = 0;
		numZodiacChoose = 1;
		numHigherChoose = 0;
		side = Consts.BAD;
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else {
			Manipulations.inspect(this);
			Manipulations.flipInspectResults(this);
		}
	}
}
