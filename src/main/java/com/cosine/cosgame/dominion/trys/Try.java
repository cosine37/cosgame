package com.cosine.cosgame.dominion.trys;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.login.User;
import com.cosine.cosgame.util.MongoDBUtil;

public class Try {
	public static void main(String args[]) {
		MongoDBUtil util = new MongoDBUtil("dominion");
		util.setCol("board");
		util.update("boardId", "1563353674770", "status", 1);
	}
}
