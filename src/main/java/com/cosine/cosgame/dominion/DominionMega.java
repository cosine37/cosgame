package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.base.Base;
import com.cosine.cosgame.dominion.dominion.Dominion;
import com.cosine.cosgame.dominion.intrigue.Intrigue;
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
		List<String> value = new ArrayList<String>();
		for (int i=0;i<boardIds.size();i++) {
			value.add(boards.get(i).getBoardId());
			value.add(boards.get(i).getStatusString());
			value.add(boards.get(i).getLord());
			value.add(boards.get(i).getPlayersInfo());
			value.add(boards.get(i).enterable());
		}
		entity.setValue(value);
		return entity;
	}
	
	public StringEntity getCardsAsAtringEntity(String exp) {
		StringEntity entity = new StringEntity();
		List<String> value = new ArrayList<String>();
		if (exp.equals("Base")) {
			
		} else if (exp.equals("Intrigue")) {
			
		} else if (exp.equals("Oriental")) {
			
		}
		return entity;
	}
}
