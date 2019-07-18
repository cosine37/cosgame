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
		Player lp = new Player(this.lord);
		players.add(lp);
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
		status = BEFORE;
	}
	
	public void resign() {
		this.status = ENDGAME;
		updateDB("status", status);
	}
	
	public void randomize() {
		basePile = base.getPiles();
		kindom = dominion.getPiles();
	}
	
	public String getBoardId() {
		return boardId;
	}
	
	public List<Pile> getBase(){
		return basePile;
	}
	
	public List<Pile> getKindom(){
		return kindom;
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public List<String> getPlayerNames(){
		List<String> ans = new ArrayList<String>();
		int i;
		for (i=0;i<players.size();i++) {
			ans.add(players.get(i).getName());
		}
		return ans;
	}
	
	public void addBot(boolean storeToDB) {
		if (numPlayers <= players.size()) {
			
		} else {
			Player bot = new Player();
			bot.bot();
			players.add(bot);
			if (storeToDB) {
				List<Document> playerDocs = genPlayerDocs();
				//dbutil.update("boardId", boardId, "players", playerDocs);
				updateDB("players", playerDocs);
			}
		}
		
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("boardId", boardId, key, value);
	}
	
	public void removeSelfFromDB() {
		dbutil.delete("boardId", boardId);
	}
	
	public void getBoardFromDB(String id) {
		CardFactory factory = new CardFactory();
		
		Document doc = dbutil.read("boardId", id);
		status = (int)doc.get("status");
		boardId = (String)doc.get("boardId");
		numPlayers = (int)doc.get("numPlayers");
		
		List<Document> baseDocs = (List<Document>)doc.get("base");
		List<Document> kindomDocs = (List<Document>)doc.get("kindom");
		List<Document> playerDocs = (List<Document>)doc.get("players");
		int i;
		
		kindom = new ArrayList<Pile>();
		basePile = new ArrayList<Pile>();
		players = new ArrayList<Player>();
		for (i=0;i<kindomDocs.size();i++) {
			Pile p = new Pile();
			String name = (String)kindomDocs.get(i).get("name");
			boolean isSupply = (boolean)kindomDocs.get(i).get("isSupply");
			boolean isMixed = (boolean)kindomDocs.get(i).get("isMixed");
			boolean isSplit = (boolean)kindomDocs.get(i).get("isSplit");
			p.setName(name);
			p.setIsSupply(isSupply);
			p.setIsMixed(isMixed);
			p.setIsSplit(isSplit);
			
			if (isMixed || isSplit) {
				
			} else {
				int n = (int)((Document)kindomDocs.get(i).get("pile")).get("number");
				String cardname = (String)((Document)kindomDocs.get(i).get("pile")).get("name");
				p.setCards(factory.createCards(cardname, n));
			}
			kindom.add(p);
		}
		for (i=0;i<baseDocs.size();i++) {
			Pile p = new Pile();
			String name = (String)baseDocs.get(i).get("name");
			int n = (int)baseDocs.get(i).get("number");
			p.setName(name);
			p.setCards(factory.createCards(name, n));
			basePile.add(p);
		}
		for (i=0;i<playerDocs.size();i++) {
			Player p = new Player();
			p.setName((String)playerDocs.get(i).get("name"));
			p.setPhase((int)playerDocs.get(i).get("phase"));
			p.setAction((int)playerDocs.get(i).get("action"));
			p.setCoin((int)playerDocs.get(i).get("coin"));
			p.setBuy((int)playerDocs.get(i).get("buy"));
			p.setCoffer((int)playerDocs.get(i).get("coffer"));
			p.setVillager((int)playerDocs.get(i).get("villager"));
			p.setVp((int)playerDocs.get(i).get("vp"));
			
			List<Document> discardDocs = (List<Document>)playerDocs.get(i).get("discard");
			List<Document> deckDocs = (List<Document>)playerDocs.get(i).get("deck");
			List<Document> handDocs = (List<Document>)playerDocs.get(i).get("hand");
			List<Document> playDocs = (List<Document>)playerDocs.get(i).get("play");
			List<Card> discard = new ArrayList<Card>();
			List<Card> deck = new ArrayList<Card>();
			List<Card> hand = new ArrayList<Card>();
			List<Card> play = new ArrayList<Card>();
		
			int j;
			for (j=0;j<discardDocs.size();j++) {discard.add(factory.createCard((String)discardDocs.get(j).get("name")));}
			for (j=0;j<deckDocs.size();j++) {deck.add(factory.createCard((String)deckDocs.get(j).get("name")));}
			for (j=0;j<handDocs.size();j++) {hand.add(factory.createCard((String)handDocs.get(j).get("name")));}
			for (j=0;j<playDocs.size();j++) {play.add(factory.createCard((String)playDocs.get(j).get("name")));}
			p.setDiscard(discard);
			p.setDeck(deck);
			p.setHand(hand);
			p.setPlay(play);
			players.add(p);
		}
		
		
	}
	
	public List<Document> genPlayerDocs(){
		List<Document> playerDocs = new ArrayList<Document>();
		int i;
		for (i=0;i<players.size();i++) {
			Document dop = new Document();
			dop.append("name", players.get(i).getName());
			dop.append("phase", players.get(i).getPhase());
			dop.append("action", players.get(i).getAction());
			dop.append("coin", players.get(i).getCoin());
			dop.append("buy", players.get(i).getBuy());
			dop.append("coffer", players.get(i).getCoffer());
			dop.append("villager", players.get(i).getVillager());
			dop.append("vp", players.get(i).getVp());
			
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
		return playerDocs;
	}
	
	public void storeBoardToDB() {
		Document doc = new Document();
		
		doc.append("status", status);
		doc.append("boardId", boardId);
		doc.append("numPlayers", numPlayers);
		
		List<Document> baseDocs = new ArrayList<Document>();
		List<Document> kindomDocs = new ArrayList<Document>();
		int i;
		for (i=0;i<kindom.size();i++) {
			Document dop = new Document();
			dop.append("name", kindom.get(i).getName());
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
		
		List<Document> playerDocs = genPlayerDocs();
		
		doc.append("players", playerDocs);
		dbutil.insert(doc);
		System.out.println("Board with id " + boardId + " is stored in db");
	}
}
