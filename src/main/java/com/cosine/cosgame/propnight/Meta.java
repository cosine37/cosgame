package com.cosine.cosgame.propnight;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.StringEntity;

public class Meta {
	List<Board> boards;
	List<String> boardIds;
	List<Integer> boardStatuses;
	List<List<String>> playerNames;
	List<String> lords;
	MongoDBUtil dbutil;
	
	public Meta() {
		String dbname = "propnight";
		String col = "board";
		boards = new ArrayList<>();
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		
		boardIds = dbutil.getValues("id");
		boardStatuses = dbutil.getIntValues("status");
		playerNames = dbutil.getListValues("playerNames");
		lords = dbutil.getValues("lord");
	}
	
	public StringEntity getBoardIdsAsStringEntity(String name) {
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		for (int i=0;i<boardIds.size();i++) {
			value.add(boardIds.get(i));
			value.add(lords.get(i));
			value.add(Integer.toString(boardStatuses.get(i)));
			String canBack="n";
			for (int j=0;j<playerNames.get(i).size();j++) {
				
				if (playerNames.get(i).get(j) != null && playerNames.get(i).get(j).contentEquals(name)) {
					canBack="y";
				}
			}
			value.add(canBack);
		}
		entity.setValue(value);
		return entity;
	}
}
