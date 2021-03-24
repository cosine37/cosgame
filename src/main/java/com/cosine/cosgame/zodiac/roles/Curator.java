package com.cosine.cosgame.zodiac.roles;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Role;

public class Curator extends Role{
	public Curator() {
		super();
		num = Consts.CURATOR;
		img = Consts.CURATORIMG;
		name = "古董家";
		numPlayerChoose = 0;
		numZodiacChoose = 2;
		numHigherChoose = 0;
		side = Consts.GOOD;
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else {
			Manipulations.inspect(this);
		}
	}
	
}
