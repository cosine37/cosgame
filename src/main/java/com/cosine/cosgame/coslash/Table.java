package com.cosine.cosgame.coslash;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Table {
	List<Player> players;
	List<Card> deck;
	
	int flag;
	public static final int CHOOSEGENERAL = 1;
	public static final int INGAME = 2;
	public static final int ENDGAME = 3;
	
	public Table() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
	}
	
	public boolean checkGameEnd() {
		boolean ans = false;
		return ans;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("flag", flag);
		List<Document> dod = new ArrayList<>();
		for (int i=0; i<deck.size();i++) {
			Document dc = deck.get(i).toDocument();
			dod.add(dc);
		}
		doc.append("deck", dod);
		List<Document> dop = new ArrayList<>();
		for (int i=0; i<players.size();i++) {
			Document dp = players.get(i).toDocument();
			dop.add(dp);
		}
		doc.append("players", dop);
		return doc;
	}
	
	public void setTableFromDoc(Document doc) {
		flag = doc.getInteger("flag");
		
		deck = new ArrayList<>();
		List<Document> dod = (List<Document>)doc.get("deck");
		for (int i=0; i<dod.size();i++) {
			Card c = CardFactory.createCard(dod.get(i));
			deck.add(c);
		}
		
		players = new ArrayList<>();
		List<Document> dop = (List<Document>)doc.get("players");
		for (int i=0;i<dop.size();i++) {
			Player p = new Player();
			p.setPlayerFromDoc(dop.get(i));
			players.add(p);
		}
	}
	
}
