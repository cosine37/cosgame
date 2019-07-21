package com.cosine.cosgame.dominion.trys;

import java.util.List;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.login.User;
import com.cosine.cosgame.util.MongoDBUtil;

public class Try {
	public static void main(String args[]) {
		
		/*
		MongoDBUtil util = new MongoDBUtil("dominion");
		util.setCol("board");
		util.update("boardId", "1563353674770", "status", 1);
		*/
		
		Board board = new Board();
		board.getBoardFromDB("1563715273810");
		System.out.println(board.getPlayerNames());
		List<Pile> piles = board.getAllCards("aaaa");
		System.out.println(piles.size());
	}
}
