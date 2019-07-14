package com.cosine.cosgame.mongo;

import org.bson.Document;

import com.cosine.cosgame.login.User;
import com.cosine.cosgame.mafia.Mafia;
import com.cosine.cosgame.mafia.Room;
import com.cosine.cosgame.util.MongoDBUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoTry {
	public static void main( String args[] ){
		/*
		MongoDBUtil util = new MongoDBUtil("dominion");
		util.setCol("rooms");
		Document doc = new Document("title","testdoc");
		util.insert(doc);
		*/
		User user = new User("aaaa");
		user.getEncrypted();
		
		/*
		try {
			
			Room room = new Room();
			room.createRoom("xxx", 10);
			room.autoFill();
			room.storeRoomToDB();
			
			Mafia mafia = new Mafia();
			
		} catch(Exception e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
		*/
	}
}
