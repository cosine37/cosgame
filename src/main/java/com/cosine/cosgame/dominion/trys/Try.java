package com.cosine.cosgame.dominion.trys;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.login.User;

public class Try {
	public static void main(String args[]) {
		/*
		Board board = new Board();
		board.createBoard("cosine", 2);
		board.setup();
		board.storeBoardToDB();
		*/
		User user = new User("aaaa");
		user.getEncrypted();
		user.verifyEncryptedAsStringEntity("49100103");
		/*
		TextGenerator g = new TextGenerator();
		g.readName();
		int i;
		for (i=0; i<20; i++) {
			System.out.println(g.generateName());
		}
		*/
		//String s = "中文";
		
		//Pile pile = new Pile();
		//pile.add(Copper.class, 60);
		
		//System.out.println(pile.getTop().getName());
	}
}
