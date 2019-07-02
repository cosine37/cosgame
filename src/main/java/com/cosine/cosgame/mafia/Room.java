package com.cosine.cosgame.mafia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.TextGenerator;

public class Room {
	public String roomId;
	public int numPlayers;
	public List<Player> players;
	public String phase; // day or night;
	public int day; // aka round, start from night;
	public List<String> logs;
	
	public String lord;

	MongoDBUtil dbutil;
	
	public Room() {
		String dbname = "mafia";
		String col = "room";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
		players = new ArrayList<Player>();
		logs = new ArrayList<String>();
	}

	public void setup(String roomId, int numPlayers) {
		this.roomId = roomId;
		this.numPlayers = numPlayers;
		int i;
		players = new ArrayList<Player>();
		for (i=0;i<numPlayers;i++) {
			Player tp = new Player();
			players.add(tp);
		}
	}
	
	public void createRoom(String lord, int numPlayers) {
		this.lord = lord;
		Date date= new Date();
		String id = Long.toString(date.getTime());
		setup(id, numPlayers);
	}
	
	public void storeRoomToDB() {
		Document doc = new Document();
		doc.append("roomId", roomId);
		doc.append("numPlayers", numPlayers);
		
		List<Document> playerDocs = new ArrayList<Document>();
		int i;
		for (i=0;i<numPlayers;i++) {
			Document dop = new Document();
			dop.append("name", players.get(i).getName());
			dop.append("role", players.get(i).getRole());
			dop.append("isAlive", players.get(i).isAlive());
			dop.append("isBot", players.get(i).isBot());
			dop.append("emptySlot", players.get(i).isEmpty());
			playerDocs.add(dop);
		}
		doc.append("players", playerDocs);
		dbutil.insert(doc);
		System.out.println("room with id " + roomId + " is stored in db");
	}
	
	public void getRoomFromDB(String id) {
		Document doc = dbutil.read("roomId", id);
		roomId = (String)doc.get("roomId");
		numPlayers = (int)doc.get("numPlayers");
		
		List<Document> playerDocs = (List<Document>)doc.get("players");
		
		int i;
		int n = players.size();
		while (n<numPlayers) {
			Player tp = new Player();
			players.add(tp);
			n++;
		}
		for (i=0;i<numPlayers;i++) {
			players.get(i).setName(playerDocs.get(i).getString("name"));
			players.get(i).setRole(playerDocs.get(i).getInteger("role"));
			players.get(i).setAlive(playerDocs.get(i).getBoolean("isAlive"));
			players.get(i).setBot(playerDocs.get(i).getBoolean("isBot"));
			players.get(i).setEmpty(playerDocs.get(i).getBoolean("emptySlot"));
		}
	}
	
	public void deleteRoomFromDB(String id) {
		dbutil.delete("roomId", id);
	}
	
	public void deleteSelfFromDB() {
		deleteRoomFromDB(this.roomId);
	}
	
	public void autoFill() { // fill all empty slots to bots;
		int i,j;
		TextGenerator gen = new TextGenerator();
		gen.readName();
		for (i=0;i<numPlayers;i++) {
			if (players.get(i).isEmpty()) {
				boolean flag;
				String name;
				while (true) {
					flag = true;
					name = gen.generateName();
					for (j=0;j<i;j++) {
						if (name == players.get(j).getName()) {
							flag=false;
							break;
						}
					}
					if (flag) break;
				}
				players.get(i).assignBot(name);
			}
		}
		
	}
	
	public void updateDB() {
		deleteSelfFromDB();
		storeRoomToDB();
	}
	
	public void applyUpdates(Updates updates) {
		
	}
	
	public List<String> getPlayersAsStringList(){
		List<String> ans = new ArrayList<String>();
		int i;
		for (i=0;i<numPlayers;i++) {
			ans.add(players.get(i).getName());
			ans.add(Integer.toString(players.get(i).getRole()));
			ans.add(Boolean.toString(players.get(i).isAlive()));
			ans.add(Boolean.toString(players.get(i).isBot()));
		}
		return ans;
	}
}
