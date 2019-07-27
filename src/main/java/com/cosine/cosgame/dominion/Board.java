package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.dominion.base.Base;
import com.cosine.cosgame.dominion.base.Estate;
import com.cosine.cosgame.dominion.dominion.Dominion;
import com.cosine.cosgame.dominion.Player;
import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.TextGenerator;

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
	public static final int FIRSTCARDS = 1;
	public static final int INGAME = 2;
	public static final int ENDGAME = 3;
	
	int startPlayer;
	int currentPlayer;
	
	MongoDBUtil dbutil;
	
	public Board() {
		base = new Base();
		dominion = new Dominion();
		trash = new Trash();
		startPlayer = 0;
		currentPlayer = 0;
	
		String dbname = "dominion";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void kick(String kickedName, boolean storeToDB) {
		if (kickedName.equals(lord)) {
			
		} else {
			dbutil.removeKey("boardId", boardId, kickedName);
			int i;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getName().equals(kickedName)) {
					players.remove(i);
					break;
				}
			}
			if (storeToDB) {
				updateDB("players",genPlayerNameDoc());
			}
		}
		
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
		
		status = BEFORE;
	}
	
	public void setup() {
		boolean useShelters = false;
		boolean hasHeirloom = false;
		randomize();
		int i,j;
		for (i=0;i<kindom.size();i++) {
			kindom.get(i).getCards().get(0).setup();
		}
		
		int copperIndex = 5;
		int estateIndex = 4;
		
		// give players start pile
		if (useShelters) {
			
		} else if (hasHeirloom) {
			
		} else {
			for (i=0;i<players.size();i++) {
				for (j=0;j<7;j++) {
					gainToPlayerFromPile(players.get(i), basePile.get(copperIndex));
				}
				for (j=0;j<3;j++) {
					Card estate = new Estate();
					gainToPlayer(players.get(i), estate);
				}
			}
		}
		
		// Decide which player goes first
		Random rand = new Random();
		System.out.println("players.size()="+players.size());
		startPlayer = rand.nextInt(players.size());
		currentPlayer = startPlayer;
		System.out.println("startPlayer="+startPlayer);
		for (i=0;i<players.size();i++) {
			if (players.get(i).getIsBot()) {
				players.get(i).goodToGo();
			}
		}
		
		status = FIRSTCARDS;
	}
	
	public void playerGoodToGo(String name) {
		int i;
		int goodToGo = 0;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				players.get(i).goodToGo();
			}
			if (players.get(i).getIsGoodToGo()){
				goodToGo = goodToGo + 1;
			}
		}
		if (goodToGo == numPlayers) {
			start();
		}
	}
	
	public void start() {
		int i;
		currentPlayer = currentPlayer-1;
		if (currentPlayer<0) {
			currentPlayer = currentPlayer + players.size();
		}
		for (i=0;i<players.size();i++) {
			players.get(i).cleanUp();
			players.get(i).setPhase(Player.OFFTURN);
		}
		status = INGAME;
		nextPlayer();
	}
	
	public void nextPlayer() {
		players.get(currentPlayer).setPhase(Player.OFFTURN);
		currentPlayer = (currentPlayer+1)%players.size();
		while (players.get(currentPlayer).getIsBot()) {
			players.get(currentPlayer).goWithAI();
			players.get(currentPlayer).setPhase(Player.OFFTURN);
			currentPlayer = (currentPlayer+1)%players.size();
		}
		players.get(currentPlayer).nextPhase();
	}
	
	public void gainToPlayer(Player p, Card card) {
		p.putOnDiscard(card);
		card.onGain(p);
	}
	
	public void gainToPlayerFromPile(Player p, Pile pile) {
		Card card = pile.removeTop();
		p.putOnDiscard(card);
		card.onGain(p);
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
	
	public String getLord() {
		return lord;
	}
	
	public int getStatus() {
		return status;
	}
	
	public int getStartPlayer() {
		return startPlayer;
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
	
	public Player getPlayerByName(String name) {
		Player player = new Player();
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				player = players.get(i);
			}
		}
		return player;
	}
	
	public List<String> getPlayerNames(){
		List<String> ans = new ArrayList<String>();
		int i;
		for (i=0;i<players.size();i++) {
			ans.add(players.get(i).getName());
		}
		return ans;
	}
	
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public Player getCurrentPlayerAsPlayer() {
		return players.get(currentPlayer);
	}
	
	public String getCurrentPlayerName() {
		System.out.println("players.size()="+players.size());
		System.out.println("currentPlayer="+currentPlayer);
		return players.get(currentPlayer).getName();
	}
	
	public List<Pile> getAllCards(String name){
		List<Pile> piles = new ArrayList<Pile>();
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				piles = players.get(i).getAllCards();
			}
		}
		return piles;
	}
	
	public void addBot(boolean storeToDB) {
		if (numPlayers <= players.size()) {
			
		} else {
			TextGenerator gen = new TextGenerator();
			gen.readName();
			int i;
			String botName = "";
			boolean flag = true;
			while (flag) {
				botName = gen.generateName() + "(bot)";
				flag = false;
				for (i=0;i<players.size();i++) {
					if (players.get(i).getName().equals(botName)) {
						flag = true;
						break;
					}
				}
			}
			Player bot = new Player(botName);
			bot.bot();
			players.add(bot);
			if (storeToDB) {
				List<Document> playerDocs = genPlayerNameDoc();
				updateDB("players", playerDocs);
				Document dob = genPlayerDoc(players.size()-1);
				updateDB(botName,dob);
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
		lord = (String)doc.get("lord");
		startPlayer = (int)doc.get("startPlayer");
		currentPlayer = (int)doc.get("currentPlayer");
		
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
			String image = (String)kindomDocs.get(i).get("image");
			p.setName(name);
			p.setIsSupply(isSupply);
			p.setIsMixed(isMixed);
			p.setIsSplit(isSplit);
			p.setImage(image);
			
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
			String image = (String)baseDocs.get(i).get("image");
			p.setName(name);
			p.setCards(factory.createCards(name, n));
			p.setImage(image);
			basePile.add(p);
		}
		for (i=0;i<playerDocs.size();i++) {
			Player p = new Player();
			p.setBoard(this);
			String name = (String) playerDocs.get(i).get("name");
			p.setName(name);
			Document dop = (Document)doc.get(name);
			p.setIsBot((boolean)dop.getBoolean("isbot"));
			p.setIsGoodToGo((boolean)dop.getBoolean("isgoodtogo"));
			p.setPhase((int)dop.get("phase"));
			p.setAction((int)dop.get("action"));
			p.setCoin((int)dop.get("coin"));
			p.setBuy((int)dop.get("buy"));
			p.setCoffer((int)dop.get("coffer"));
			p.setVillager((int)dop.get("villager"));
			p.setVp((int)dop.get("vp"));
			
			List<Document> discardDocs = (List<Document>)dop.get("discard");
			List<Document> deckDocs = (List<Document>)dop.get("deck");
			List<Document> handDocs = (List<Document>)dop.get("hand");
			List<Document> playDocs = (List<Document>)dop.get("play");
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
	
	public List<Document> genPlayerNameDoc() {
		int i;
		List<Document> dopn = new ArrayList<Document>();
		for (i=0;i<players.size();i++) {
			Document d = new Document();
			d.append("name", players.get(i).getName());
			dopn.add(d);
		}
		return dopn;
	}
	
	public Document genPlayerDoc(int i) {
		Document dop = new Document();
		dop.append("isgoodtogo", players.get(i).getIsGoodToGo());
		dop.append("isbot", players.get(i).getIsBot());
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
			d.append("name", players.get(i).getDiscard().get(j).getName());
			discardDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getDeck().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getDeck().get(j).getName());
			deckDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getHand().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getHand().get(j).getName());
			handDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getPlay().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getPlay().get(j).getName());
			playDocs.add(d);
		}
		dop.append("discard", discardDocs);
		dop.append("deck", deckDocs);
		dop.append("hand", handDocs);
		dop.append("play", playDocs);
		
		return dop;
	}
	
	public Document genPlayerDoc(String name) {
		Document doc = new Document();
		int i;
		for (i=0;i<numPlayers;i++) {
			if (players.get(i).getName().equals(name)) {
				doc = genPlayerDoc(i);
			}
		}
		return doc;
	}
	
	public List<Document> genBaseDocs(){
		List<Document> baseDocs = new ArrayList<Document>();
		int i;
		for (i=0;i<basePile.size();i++) {
			Document dop = new Document();
			dop.append("name",basePile.get(i).getName());
			dop.append("number", basePile.get(i).getNumCards());
			dop.append("image", basePile.get(i).getImage());
			baseDocs.add(dop);
		}
		return baseDocs;
	}
	
	public List<Document> genKindomDocs(){
		List<Document> kindomDocs = new ArrayList<Document>();
		int i;
		for (i=0;i<kindom.size();i++) {
			Document dop = new Document();
			dop.append("name", kindom.get(i).getName());
			dop.append("isSupply", kindom.get(i).isSupply());
			dop.append("isMixed", kindom.get(i).isMixed());
			dop.append("isSplit", kindom.get(i).isSplit());
			dop.append("image", kindom.get(i).getImage());
			if (kindom.get(i).isMixed() || kindom.get(i).isSplit()) {
				
			} else {
				Document dok = new Document();
				dok.append("name",kindom.get(i).getName());
				dok.append("number", kindom.get(i).getNumCards());
				dop.append("pile", dok);
			}
			kindomDocs.add(dop);
		}
		return kindomDocs;
	}
	
	public void storeBoardToDB() {
		Document doc = new Document();
		
		doc.append("status", status);
		doc.append("boardId", boardId);
		doc.append("numPlayers", numPlayers);
		doc.append("lord", lord);
		doc.append("startPlayer", startPlayer);
		doc.append("currentPlayer", currentPlayer);
		
		List<Document> dopn = genPlayerNameDoc();
		doc.append("players", dopn);
		
		List<Document> baseDocs = genBaseDocs();
		List<Document> kindomDocs = genKindomDocs();
		doc.append("base", baseDocs);
		doc.append("kindom", kindomDocs);
		
		int i;
		for (i=0;i<players.size();i++) {
			Document dop = genPlayerDoc(i);
			doc.append(players.get(i).getName(), dop);
		}
		dbutil.insert(doc);
		System.out.println("Board with id " + boardId + " is stored in db");
	}
}
