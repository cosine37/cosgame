package com.cosine.cosgame.architect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.architect.entity.*;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Card> cardDeck;
	List<Card> revealedCards;
	List<Building> buildingDeck;
	List<Building> revealedBuildings;
	List<Player> players;
	int num3vp;
	int num1vp;
	int roundCount;
	int firstPlayerIndex;
	int curPlayerIndex;
	int status;
	int numBuildingFinish;
	String lord;
	String id;
	
	MongoDBUtil dbutil;
	
	public Board() {
		cardDeck = new ArrayList<>();
		revealedCards = new ArrayList<>();
		buildingDeck = new ArrayList<>();
		revealedBuildings = new ArrayList<>();
		players = new ArrayList<>();
		
		String dbname = "architect";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startGame() {
		int i;
		AllRes allRes = new AllRes();
		Random rand = new Random();
		firstPlayerIndex = rand.nextInt() % players.size();
		curPlayerIndex = firstPlayerIndex;
		int x = curPlayerIndex;
		for (i=0;i<players.size();i++) {
			Player p = players.get(x);
			p.clearAll();
			Card woodCutter = allRes.getWoodCutter();
			woodCutter.setPlayer(p);
			woodCutter.setBoard(this);
			Card mage = allRes.getMage();
			mage.setPlayer(p);
			mage.setBoard(this);
			p.addHand(woodCutter);
			p.addHand(mage);
			if (i == 0) {
				p.addRes(Consts.WOOD, 3);
			} else if (i==1 || i==2) {
				p.addRes(Consts.WOOD, 4);
			} else if (i==3 || i==4) {
				p.addRes(Consts.WOOD, 3);
				p.addRes(Consts.STONE, 1);
			}
			x++;
			x = x%players.size();
		}
		cardDeck = allRes.getCardDeck();
		buildingDeck = allRes.getBuildingDeck();
		revealedCards = new ArrayList<>();
		for (i=0;i<Consts.NUMCARDREVEAL;i++) {
			Card c = cardDeck.remove(0);
			revealedCards.add(c);
		}
		
		revealedBuildings = new ArrayList<>();
		for (i=0;i<Consts.NUMBUILDINGREVEAL;i++) {
			Building b = buildingDeck.remove(0);
			revealedBuildings.add(b);
		}
		num3vp = players.size()*2;
		num1vp = players.size()*2;
		status = Consts.INGAME;
		
	}

	public void playerBuild(Player p, int x) {
		if (x<0 || x>=revealedBuildings.size()) {
			return;
		} else {
			if (revealedBuildings.get(x).canBuy(p)) {
				Building b = revealedBuildings.remove(x);
				p.payAndBuild(b);
				if (x == 0 && num3vp>0) {
					num3vp--;
					p.add3vp();
				} else if (x == 1 && num1vp>0) {
					num1vp--;
					p.add1vp();
				}
				if (p.getBuildings().size() >= numBuildingFinish) {
					status = Consts.LASTROUND;
				}
				if (buildingDeck.size()>0) {
					Building nb = buildingDeck.remove(0);
					revealedBuildings.add(nb);
				}
			}
		}
	}
	
	public void playerHire(Player p, int x, List<Integer> resources) {
		if (x <= resources.size() && x>=0 && x<revealedCards.size()) {
			int i,j;
			for (i=0;i<x;i++) {
				Card c = revealedCards.get(i);
				for (j=0;j<resources.size();j++) {
					int y = resources.get(j);
					if (y>=Consts.WOOD && y<=Consts.GOLD) {
						c.addResOn(y);
					}
				}
			}
			Card c = revealedCards.remove(x);
			for (i=0;i<c.getResOn().size();i++) {
				p.addRes(c.getResOn().get(i));
			}
			p.getHand().add(c);
			if (cardDeck.size()>0) {
				Card nc = cardDeck.remove(0);
				revealedCards.add(nc);
			}
		}
	}
	
	public void endGame() {
		status = Consts.ENDGAME;
	}
	
	public void nextPlayer() {
		Player p1 = players.get(curPlayerIndex);
		p1.setPhase(Consts.OFFTURN);
		curPlayerIndex++;
		if (curPlayerIndex>players.size()) {
			curPlayerIndex = curPlayerIndex % players.size();
		}
		if ((curPlayerIndex == firstPlayerIndex) && (status == Consts.LASTROUND)) {
			endGame();
		} else {
			Player p2 = players.get(curPlayerIndex);
			p2.setPhase(Consts.INTURN);
		}
	}
	
	public List<Card> getCardDeck() {
		return cardDeck;
	}
	public void setCardDeck(List<Card> cardDeck) {
		this.cardDeck = cardDeck;
	}
	public List<Card> getRevealedCards() {
		return revealedCards;
	}
	public void setRevealedCards(List<Card> revealedCards) {
		this.revealedCards = revealedCards;
	}
	public List<Building> getBuildingDeck() {
		return buildingDeck;
	}
	public void setBuildingDeck(List<Building> buildingDeck) {
		this.buildingDeck = buildingDeck;
	}
	public List<Building> getRevealedBuildings() {
		return revealedBuildings;
	}
	public void setRevealedBuildings(List<Building> revealedBuildings) {
		this.revealedBuildings = revealedBuildings;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getNum3vp() {
		return num3vp;
	}
	public void setNum3vp(int num3vp) {
		this.num3vp = num3vp;
	}
	public int getNum1vp() {
		return num1vp;
	}
	public void setNum1vp(int num1vp) {
		this.num1vp = num1vp;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	public int getFirstPlayerIndex() {
		return firstPlayerIndex;
	}
	public void setFirstPlayerIndex(int firstPlayerIndex) {
		this.firstPlayerIndex = firstPlayerIndex;
	}
	public int getCurPlayerIndex() {
		return curPlayerIndex;
	}
	public void setCurPlayerIndex(int curPlayerIndex) {
		this.curPlayerIndex = curPlayerIndex;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getNumBuildingFinish() {
		return numBuildingFinish;
	}
	public void setNumBuildingFinish(int numBuildingFinish) {
		this.numBuildingFinish = numBuildingFinish;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		players.add(p);
	}
	
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(Integer.toString(status));
		int i;
		List<String> playerNames = new ArrayList<>();
		List<PlayerEntity> lp = new ArrayList<>();
		List<CardEntity> lrc = new ArrayList<>();
		List<BuildingEntity> lrb = new ArrayList<>();
		String myIndex = "-1";
		Player p = null;
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			lp.add(players.get(i).toPlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				myIndex = Integer.toString(i);
				p = players.get(i);
			}
		}
		entity.setPlayerNames(playerNames);
		entity.setPlayers(lp);
		entity.setMyIndex(myIndex);
		for (i=0;i<revealedCards.size();i++) {
			lrc.add(revealedCards.get(i).toCardEntity());
		}
		for (i=0;i<revealedBuildings.size();i++) {
			BuildingEntity be = revealedBuildings.get(i).toBuildingEntity();
			if (p != null) {
				if (revealedBuildings.get(i).canBuy(p)) {
					be.setCanBuild("y");
				} else {
					be.setCanBuild("n");
				}
			}
			lrb.add(be);
		}
		entity.setRevealedCards(lrc);
		entity.setRevealedBuildings(lrb);
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("num3vp", num3vp);
		doc.append("num1vp", num1vp);
		doc.append("roundCount", roundCount);
		doc.append("firstPlayerIndex", firstPlayerIndex);
		doc.append("curPlayerIndex", curPlayerIndex);
		doc.append("status", status);
		doc.append("numBuildingFinish", numBuildingFinish);
		int i;
		List<Document> docd = new ArrayList<>();
		for (i=0;i<cardDeck.size();i++) {
			docd.add(cardDeck.get(i).toDocument());
		}
		doc.append("cardDeck", cardDeck);
		List<Document> dorc = new ArrayList<>();
		for (i=0;i<revealedCards.size();i++) {
			dorc.add(revealedCards.get(i).toDocument());
		}
		doc.append("revealedCards", dorc);
		List<Document> dobd = new ArrayList<>();
		for (i=0;i<buildingDeck.size();i++) {
			dobd.add(buildingDeck.get(i).toDocument());
		}
		doc.append("buildingDeck", dobd);
		List<Document> dorb = new ArrayList<>();
		for (i=0;i<revealedBuildings.size();i++) {
			dorb.add(revealedBuildings.get(i).toDocument());
		}
		doc.append("revealedBuildings", dorb);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		num3vp = doc.getInteger("num3vp", 0);
		num1vp = doc.getInteger("num1vp", 0);
		roundCount = doc.getInteger("roundCount", 0);
		firstPlayerIndex = doc.getInteger("firstPlayerIndex", -1);
		curPlayerIndex = doc.getInteger("curPlayerIndex", -1);
		status = doc.getInteger("status", -1);
		numBuildingFinish = doc.getInteger("numBuildingFinish",numBuildingFinish);
		int i;
		cardDeck = new ArrayList<>();
		List<Document> docd = (List<Document>) doc.get("cardDeck");
		for (i=0;i<docd.size();i++) {
			Card c = new Card();
			c.setBoard(this);
			c.setFromDoc(docd.get(i));
			cardDeck.add(c);
		}
		revealedCards = new ArrayList<>();
		List<Document> dorc = (List<Document>) doc.get("revealedCards");
		for (i=0;i<dorc.size();i++) {
			Card c = new Card();
			c.setBoard(this);
			c.setFromDoc(dorc.get(i));
			revealedCards.add(c);
		}
		buildingDeck = new ArrayList<>();
		List<Document> dobd = (List<Document>) doc.get("buildingDeck");
		for (i=0;i<dobd.size();i++) {
			Building b = new Building();
			b.setFromDoc(dobd.get(i));
			buildingDeck.add(b);
		}
		revealedBuildings = new ArrayList<>();
		List<Document> dorb = (List<Document>) doc.get("revealedBuildings");
		for (i=0;i<dorb.size();i++) {
			Building b = new Building();
			b.setFromDoc(dorb.get(i));
			revealedBuildings.add(b);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			name = "player-" + name;
			Document dop = (Document) doc.get(name);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	
	public Player getPlayerByName(String name) {
		Player p = null;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				p = players.get(i);
				break;
			}
		}
		return p;
	}
	
	public Player getPlayerByIndex(int index) {
		if (index<0 || index>=players.size()) {
			return null;
		}
		return players.get(index);
	}
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
	}
	
	public void updateAllCards() {
		int i;
		List<Document> docd = new ArrayList<>();
		for (i=0;i<cardDeck.size();i++) {
			docd.add(cardDeck.get(i).toDocument());
		}
		dbutil.update("id", id, "cardDeck", docd);
		List<Document> dorc = new ArrayList<>();
		for (i=0;i<revealedCards.size();i++) {
			dorc.add(revealedCards.get(i).toDocument());
		}
		dbutil.update("id", id, "revealedCards", dorc);
		List<Document> dobd = new ArrayList<>();
		for (i=0;i<buildingDeck.size();i++) {
			dobd.add(buildingDeck.get(i).toDocument());
		}
		dbutil.update("id", id, "buildingDeck", dobd);
		List<Document> dorb = new ArrayList<>();
		for (i=0;i<revealedBuildings.size();i++) {
			dorb.add(revealedBuildings.get(i).toDocument());
		}
		dbutil.update("id", id, "revealedBuildings", dorb);
	}
	
	public void updateBasicDB() {
		dbutil.update("id", id, "status", status);
		dbutil.update("id", id, "num1vp", num1vp);
		dbutil.update("id", id, "num3vp", num3vp);
		dbutil.update("id", id, "curPlayerIndex", curPlayerIndex);
		dbutil.update("id", id, "firstPlayerIndex", firstPlayerIndex);
		dbutil.update("id", id, "roundCount", roundCount);
		updateAllCards();
	}
	
	public void storeToDB() {
		Document doc = toDocument();
		dbutil.insert(doc);
	}
	
	public void getFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
}
