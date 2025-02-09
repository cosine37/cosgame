package com.cosine.cosgame.rich.game.bcct;

import com.cosine.cosgame.rich.AllRes;
import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;

public class BcctBoard extends Board {
	public BcctBoard() {
		super();
	}
	
	public void startGame() {
		super.startGame();
		map.genMap(0);
		
		
		addPlayer("p1");
		putPlayerOnPlace(players.get(0), Consts.STARTPOINT_INDEX);
		
		players.get(0).setMoney(0);
		players.get(0).setSalary(100);
		
		players.get(0).move(4);
		map.printMap();
		System.out.println(players.get(0).getMoney());
		
		players.get(0).move(8);
		map.printMap();
		System.out.println(players.get(0).getMoney());
		
		players.get(0).move(3);
		map.printMap();
		System.out.println(players.get(0).getMoney());
		
	}
	
	public static void main(String args[]) {
		BcctBoard bcct = new BcctBoard();
		bcct.startGame();
	}
}
