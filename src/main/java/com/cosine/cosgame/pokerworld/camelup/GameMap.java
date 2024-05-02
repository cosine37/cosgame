package com.cosine.cosgame.pokerworld.camelup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
	List<Grid> grids;
	Map<Integer, Camel> camels;
	
	public GameMap() {
		grids = new ArrayList<>();
		camels = new HashMap<>();
	}
	
	public void initialize() {
		int i;
		for (i=Consts.WINNINGREVERSE;i<=Consts.WINNING;i++) {
			grids.add(new Empty());
		}
	}
	
	public void initializeCamel(int color, int pos) {
		Camel c = new Camel(color);
		camels.put(c.getColor(),c);
		moveCamel(c, pos);
	}
	
	public void initializeCrazyCamel(int color, int pos) {
		Camel c = new Camel(color);
		c.setCrazy(true);
		camels.put(c.getColor(),c);
		moveCamel(c, pos);
	}
	
	public void printCamelsInfo() {
		for (int i : camels.keySet()) {
			camels.get(i).printInfo();
		}
	}
	
	public List<List<Integer>> toList(){
		int i;
		List<List<Integer>> ans = new ArrayList<>();
		for (i=Consts.WINNINGREVERSE;i<=Consts.WINNING;i++) {
			ans.add(grids.get(i).toList());
		}
		return ans;
	}
	
	public void rollDice() {
		
	}
	
	// c: the camel(s), x: how many steps
	public void moveCamel(Camel c, int x) {
		if (c.getCamelUnder() != null) {
			c.getCamelUnder().setCamelOn(null);
			c.getBottomCamel().updateTopCamel(c.getCamelUnder());
			c.setCamelUnder(null);
		} else {
			grids.set(c.getPos(), new Empty());
		}
		
		boolean toBottom = false;
		int newPos = c.getPos()+x;
		
		if (grids.get(newPos) instanceof Spectator) {
			Spectator s = (Spectator) grids.get(newPos);
			if (s.getDir() == Consts.CHEER) {
				if (x < 0) {
					newPos = newPos-1;
				} else {
					newPos = newPos+1;
				}
			} else if (s.getDir() == Consts.BOO) {
				toBottom = true;
				if (x < 0) {
					newPos = newPos+1;
				} else {
					newPos = newPos-1;
				}
			}
		}
		
		
		if (toBottom) {
			moveToBottom(c, newPos);
		} else {
			moveToTop(c, newPos);
		}
	}
	
	public void moveCamel(int color, int x) {
		if (camels.containsKey(color)) {
			moveCamel(camels.get(color), x);
		}
	}
	
	// c: the camel(s); pos: new position
	public void moveToTop(Camel c, int newPos) {
		if (grids.get(newPos) instanceof Empty) {
			grids.set(newPos, c);
		} else if (grids.get(newPos) instanceof Camel) {
			Camel tc = c.getTopCamel();
			Camel bc = (Camel) grids.get(newPos);
			
			bc.getTopCamel().setCamelOn(c);
			c.setCamelUnder(bc.getTopCamel());
			
			bc.updateTopCamel(tc);
			bc.updateBottomCamel(bc);
			
		}
		c.updatePos(newPos);
	}
	
	// c: the camel(s); pos: new position
	public void moveToBottom(Camel c, int newPos) {
		if (grids.get(newPos) instanceof Empty) {
			grids.set(newPos, c);
		} else if (grids.get(newPos) instanceof Camel) {
			
			Camel bc = (Camel) grids.get(newPos);
			Camel tc = bc.getTopCamel();
			
			c.getTopCamel().setCamelOn(bc);
			bc.setCamelUnder(c.getTopCamel());
			
			c.updateTopCamel(tc);
			c.updateBottomCamel(c);
			grids.set(newPos, c);
		}
		c.updatePos(newPos);
	}
	
	public boolean canPlaceSpectator(int pos) {
		boolean f = false;
		if (grids.get(pos) instanceof Empty) {
			f = true;
			int left = pos-1;
			int right = pos+1;
			if (left == Consts.WINNINGREVERSE) {
				left = Consts.WINNING-1;
			}
			if (right == Consts.WINNING) {
				right = Consts.WINNINGREVERSE+1;
			}
			if (grids.get(left) instanceof Spectator) {
				f = false;
			} if (grids.get(right) instanceof Spectator) {
				f = false;
			}
			
		} else {
			f = false;
		}
		return f;
	}
	
	public boolean placeSpectator(Gambler g, int dir, int pos) {
		for (int i=Consts.WINNINGREVERSE+1;i<Consts.WINNING;i++) {
			if (grids.get(i) instanceof Spectator) {
				Spectator s = (Spectator) grids.get(i);
				if (s.getGambler().getIndex() == g.getIndex()) {
					grids.set(i, new Empty());
				}
			}
		}
		
		boolean f = canPlaceSpectator(pos);
		if (f) {
			Spectator s = new Spectator(g, dir);
			s.setPos(pos);
			grids.set(pos, s);
		}
		return f;
	}
	
	public static void main(String args[]) {
		GameMap gm = new GameMap();
		Gambler g1 = new Gambler("g1", 1);
		Gambler g2 = new Gambler("g2", 2);
		
		gm.initialize();
		gm.initializeCamel(Consts.RED, 2);
		gm.initializeCamel(Consts.GREEN, 2);
		gm.initializeCamel(Consts.BLUE, 2);
		gm.initializeCamel(Consts.PURPLE, 1);
		gm.initializeCamel(Consts.YELLOW, 2);
		System.out.println(gm.toList());
		
		gm.moveCamel(Consts.BLUE, 2);
		System.out.println(gm.toList());
		gm.printCamelsInfo();
		
		gm.moveCamel(Consts.PURPLE, 3);
		System.out.println(gm.toList());
		gm.printCamelsInfo();
		
		gm.placeSpectator(g1, Consts.BOO, 5);
		System.out.println(gm.toList());
		
		gm.moveCamel(Consts.GREEN, 3);
		System.out.println(gm.toList());
		
		gm.moveCamel(Consts.GREEN, 1);
		System.out.println(gm.toList());
		
		gm.moveCamel(Consts.GREEN, 2);
		System.out.println(gm.toList());
		
		gm.moveCamel(Consts.YELLOW, 2);
		System.out.println(gm.toList());
		
		gm.placeSpectator(g1, Consts.CHEER, 7);
		System.out.println(gm.toList());
		
		gm.moveCamel(Consts.GREEN, 1);
		System.out.println(gm.toList());
		
		gm.placeSpectator(g2, Consts.CHEER, 6);
		System.out.println(gm.toList());
		
		gm.placeSpectator(g1, Consts.CHEER, 6);
		System.out.println(gm.toList());
	}
}
