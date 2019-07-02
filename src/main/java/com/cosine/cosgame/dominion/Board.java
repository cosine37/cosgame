package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.dominion.base.Base;
import com.cosine.cosgame.dominion.dominion.Dominion;
import com.cosine.cosgame.dominion.Player;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String boardId;
	String lord;
	int numPlayers;
	
	Expansion base;
	Expansion dominion;
	Trash trash;
	List<Player> players;
	List<Pile> kindom;
	List<Pile> basePile;
	
	int status;
	public static final int BEFORE = 0;
	public static final int INGAME = 1;
	public static final int ENDGAME = 3;
	
	MongoDBUtil dbutil;
	
	public Board() {
		base = new Base();
		dominion = new Dominion();
		trash = new Trash();
	
		String dbname = "dominion";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	//createBoard => initialize => setup => start
	
	public void initialize(String boardId, int numPlayers) {
		this.boardId = boardId;
		this.numPlayers = numPlayers;
		int i;
		players = new ArrayList<Player>();
		for (i=0;i<numPlayers;i++) {
			Player tp = new Player();
			players.add(tp);
		}
		kindom = new ArrayList<Pile>();
		basePile = new ArrayList<Pile>();
	}
	
	public void setup() {
		randomize();
		int i;
		for (i=0;i<kindom.size();i++) {
			kindom.get(i).getCards().get(0).setup();
		}
	}
	
	public void createBoard(String lord, int numPlayers) {
		this.lord = lord;
		Date date= new Date();
		String id = Long.toString(date.getTime());
		initialize(id, numPlayers);
	}
	
	public void randomize() {
		basePile = base.getPiles();
		kindom = dominion.getPiles();
	}
	
	public List<Pile> getBase(){
		return basePile;
	}
	
	public List<Pile> getKindom(){
		return kindom;
	}
	
	public void storeBoardToDB() {
		Document doc = new Document();
		
		doc.append("boardId", boardId);
		doc.append("numPlayers", numPlayers);
		
		List<Document> baseDocs = new ArrayList<Document>();
		List<Document> kindomDocs = new ArrayList<Document>();
		int i;
		for (i=0;i<kindom.size();i++) {
			Document dop = new Document();
			dop.append("isSupply", kindom.get(i).isSupply());
			dop.append("isMixed", kindom.get(i).isMixed());
			dop.append("isSplit", kindom.get(i).isSplit());
			if (kindom.get(i).isMixed() || kindom.get(i).isSplit()) {
				
			} else {
				Document dok = new Document();
				dok.append("name",kindom.get(i).getName());
				dok.append("number", kindom.get(i).getNumCards());
				dop.append("pile", dok);
			}
			kindomDocs.add(dop);
		}
		for (i=0;i<basePile.size();i++) {
			Document dop = new Document();
			dop.append("name",basePile.get(i).getName());
			dop.append("number", basePile.get(i).getNumCards());
			baseDocs.add(dop);
		}
		doc.append("base", baseDocs);
		doc.append("kindom", kindomDocs);
		
		List<Document> playerDocs = new ArrayList<Document>();
		for (i=0;i<players.size();i++) {
			Document dop = new Document();
			dop.append("name", players.get(i).getName());
			dop.append("phase", players.get(i).getPhase());
			
			int j;
			List<Document> discardDocs = new ArrayList<Document>();
			List<Document> deckDocs = new ArrayList<Document>();
			List<Document> handDocs = new ArrayList<Document>();
			List<Document> playDocs = new ArrayList<Document>();
			
			for (j=0;j<players.get(i).getDiscard().size();j++) {
				Document d = new Document();
				d.append("name", players.get(i).getDiscard().get(j));
				discardDocs.add(d);
			}
			
			for (j=0;j<players.get(i).getDeck().size();j++) {
				Document d = new Document();
				d.append("name", players.get(i).getDeck().get(j));
				deckDocs.add(d);
			}
			
			for (j=0;j<players.get(i).getHand().size();j++) {
				Document d = new Document();
				d.append("name", players.get(i).getHand().get(j));
				handDocs.add(d);
			}
			
			for (j=0;j<players.get(i).getPlay().size();j++) {
				Document d = new Document();
				d.append("name", players.get(i).getPlay().get(j));
				playDocs.add(d);
			}
			dop.append("discard", discardDocs);
			dop.append("deck", deckDocs);
			dop.append("hand", handDocs);
			dop.append("play", playDocs);
			
			playerDocs.add(dop);
		}
		doc.append("players", playerDocs);
		dbutil.insert(doc);
		System.out.println("Board with id " + boardId + " is stored in db");
	}
}
