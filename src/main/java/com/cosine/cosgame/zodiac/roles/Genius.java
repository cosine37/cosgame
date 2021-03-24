package com.cosine.cosgame.zodiac.roles;

import java.util.List;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Player;
import com.cosine.cosgame.zodiac.Role;

public class Genius extends Role{
	public Genius() {
		super();
		num = Consts.GENIUS;
		img = Consts.GENIUSIMG;
		name = "天才";
		numPlayerChoose = 0;
		numZodiacChoose = 1;
		numHigherChoose = 0;
		side = Consts.GOOD;
	}
	
	public void disable() {
		super.disable();
		player.setOption(Consts.DEAD);
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else if (player.getOption() == Consts.DEAD) {
			
		} else {
			Manipulations.trueInspect(this);
		}
	}
	
}
