package com.cosine.cosgame.dominion.trys;

import java.util.List;

import com.cosine.cosgame.dominion.Board;
import com.cosine.cosgame.dominion.Pile;
import com.cosine.cosgame.login.User;
import com.cosine.cosgame.minigame.xutangbo.Game;
import com.cosine.cosgame.util.CsvReader;
import com.cosine.cosgame.util.MongoDBUtil;

public class Try {
	public static void main(String args[]) {
		CsvReader reader = new CsvReader();
		String fileName = "files/dominionCards.csv";
		reader.setPath(fileName);
		reader.read();
		reader.printContent();
		
	}
}
