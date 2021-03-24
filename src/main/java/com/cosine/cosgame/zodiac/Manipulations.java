package com.cosine.cosgame.zodiac;

import java.util.List;

import com.cosine.cosgame.zodiac.roles.Thief;

public class Manipulations {
	static void flip(Player p, int x) {
		if (p.getCheckHistory().get(x) == Consts.REAL) {
			p.getCheckHistory().set(x, Consts.FAKE);
		} else if (p.getCheckHistory().get(x) == Consts.FAKE) {
			p.getCheckHistory().set(x, Consts.REAL);
		}
	}
	
	public static void trueInspect(Role r) {
		Player p = r.getPlayer();
		Board b = r.getBoard();
		int round = b.getRound();
		int i;
		for (i=0;i<4;i++) {
			int x = round*4+i;
			if (p.getZodiacTargets().get(x) == Consts.CHOOSED) {
				if (b.getZodiacs().get(x).isStolen()) {
					p.getCheckHistory().set(x, Consts.STOLEN);
				} else {
					if (b.getZodiacs().get(x).isReal()) {
						p.getCheckHistory().set(x, Consts.REAL);
					} else {
						p.getCheckHistory().set(x, Consts.FAKE);
					}
				}
			}
		}
	}
	
	public static void inspect(Role r) {
		trueInspect(r);
		Player p = r.getPlayer();
		Board b = r.getBoard();
		
		if (!b.isFlipped()) {
			return;
		}
		if (r.getSide() == Consts.BAD) {
			return;
		}
		
		int round = b.getRound();
		int i;
		for (i=0;i<4;i++) {
			int x = round*4+i;
			if (p.getZodiacTargets().get(x) == Consts.CHOOSED) {
				flip(p,x);
			}
		}
	}
	
	public static void checkRole(Role r) {
		Player p = r.getPlayer();
		List<Player> players = r.getBoard().getPlayers();
		for (int i=0;i<p.getPlayerTargets().size();i++) {
			if (p.getPlayerTargets().get(i) == Consts.CHOOSED) {
				p.getSideHistory().set(i, players.get(i).getRole().getSide());
			}
		}
	}

	public static void flipInspectResults(Role r) {
		Player p = r.getPlayer();
		Board b = r.getBoard();
		if (p.getOption() == Consts.FLIP) {
			b.setFlipped(true);
		}
	}

	public static void disablePlayer(Role r) {
		Player p = r.getPlayer();
		List<Player> players = r.getBoard().getPlayers();
		for (int i=0;i<p.getPlayerTargets().size();i++) {
			if (p.getPlayerTargets().get(i) == Consts.CHOOSED) {
				players.get(i).getRole().disable();
			}
		}
	}

	public static void steal(Role r) {
		Player p = r.getPlayer();
		Board b = r.getBoard();
		int round = b.getRound();
		int i;
		for (i=0;i<4;i++) {
			int x = round*4+i;
			if (p.getZodiacTargets().get(x) == Consts.HIGHERCHOOSED) {
				b.getZodiacs().get(x).setStolen(true);
			}
		}
	}
}
