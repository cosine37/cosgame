package com.cosine.cosgame.minigame.xutangbo;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.StringEntity;

public class Mega {
	List<Game> games;
	List<String> ids;
	MongoDBUtil dbutil;
	
	public Mega() {
		dbutil = new MongoDBUtil();
		String dbname = "xtb";
		String col = "game";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		ids = dbutil.getValues("id");
		games = new ArrayList<>();
		for (int i=0;i<ids.size();i++) {
			Game game = new Game();
			game.getGameFromDB(ids.get(i));
			games.add(game);
		}
	}
	
	public StringEntity getGamesAsStringEntity() {
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		for (int i=0;i<games.size();i++) {
			value.add(games.get(i).getId());
			value.add(games.get(i).getLord());
			value.add(Integer.toString(games.get(i).getStatus()));
		}
		entity.setValue(value);
		return entity;
	}
}
