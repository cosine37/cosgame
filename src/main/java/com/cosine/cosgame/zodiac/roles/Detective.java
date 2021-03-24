package com.cosine.cosgame.zodiac.roles;

import java.util.List;

import com.cosine.cosgame.zodiac.Consts;
import com.cosine.cosgame.zodiac.Manipulations;
import com.cosine.cosgame.zodiac.Player;
import com.cosine.cosgame.zodiac.Role;

public class Detective extends Role{
	public Detective() {
		super();
		num = Consts.DETECTIVE;
		img = Consts.DETECTIVEIMG;
		name = "侦探";
		numPlayerChoose = 1;
		numZodiacChoose = 0;
		numHigherChoose = 0;
		side = Consts.GOOD;
	}
	
	public void disable() {
		super.disable();
		List<Player> players = board.getPlayers();
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getRole().getNum() == Consts.CURATOR) {
				players.get(i).getRole().disable();
			}
		}
	}
	
	public void useSkill() {
		if (player.isDisabled()) {
			
		} else {
			Manipulations.checkRole(this);
		}
	}
	
}
