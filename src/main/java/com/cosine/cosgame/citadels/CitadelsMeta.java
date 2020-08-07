package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.StringEntity;

public class CitadelsMeta {
	List<Board> boards;
	List<String> boardIds;
	MongoDBUtil dbutil;
	
	public CitadelsMeta() {
		String dbname = "citadels";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		
		boardIds = dbutil.getValues("id");
	}
	
	public StringEntity getBoardIdsAsStringEntity() {
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		for (int i=0;i<boardIds.size();i++) {
			value.add(boardIds.get(i));
		}
		entity.setValue(value);
		return entity;
	}
}
