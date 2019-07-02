package com.cosine.cosgame.mafia;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.StringEntity;

public class Mafia {
	List<Room> rooms;
	List<String> roomIds;
	MongoDBUtil dbutil;	
	
	public Mafia() {
		String dbname = "mafia";
		String col = "room";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		rooms = new ArrayList<Room>();
		roomIds = dbutil.getValues("roomId");
		int i;
		for (i=0;i<roomIds.size();i++) {
			Room room = new Room();
			room.getRoomFromDB(roomIds.get(i));
			rooms.add(room);
		}
	}
	
	public StringEntity getRoomIdsAsStringEntity() {
		StringEntity entity = new StringEntity();
		entity.setValue(roomIds);
		return entity;
	}
	
	public StringEntity getPlayersAsStringEntity(String roomId) {
		StringEntity entity = new StringEntity();
		Room room = new Room();
		room.getRoomFromDB(roomId);
		entity.setValue(room.getPlayersAsStringList());
		return entity;
	}
	
	public StringEntity createRoom(String lord, int numPlayers) {
		StringEntity entity = new StringEntity();
		Room room = new Room();
		room.createRoom(lord, numPlayers);
		return entity;
	}
}
