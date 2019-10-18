package com.cosine.cosgame.dominion.trys;

import java.util.List;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.login.User;
import com.cosine.cosgame.minigame.xutangbo.Game;
import com.cosine.cosgame.util.MongoDBUtil;

public class Try {
	public static void main(String args[]) {
		Game game = new Game();
		game.getGameFromDB("1571436817242");
		System.out.println("lord:" + game.getLord());
		
	}
}
