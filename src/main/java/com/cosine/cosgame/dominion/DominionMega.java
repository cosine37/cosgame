package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.StringEntity;

public class DominionMega {
	List<Board> boards;
	List<String> boardIds;
	MongoDBUtil dbutil;
	
	public DominionMega() {
		String dbname = "dominion";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		boards = new ArrayList<Board>();
		boardIds = dbutil.getValues("boardId");
		int i;
		for (i=0;i<boardIds.size();i++) {
			Board board = new Board();
			board.getBoardFromDB(boardIds.get(i));
			boards.add(board);
		}
	}
	
	public StringEntity getBoardIdsAsStringEntity() {
		StringEntity entity = new StringEntity();
		entity.setValue(boardIds);
		return entity;
	}
}
