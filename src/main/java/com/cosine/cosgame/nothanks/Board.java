package com.cosine.cosgame.nothanks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<Card> deck;
	int status;
	int curPlayer;
	int numGoldInDeck;
	int initialRevealedMoney;
	AllRes allRes;
	MongoDBUtil dbutil;
	String id;
	String lord;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		allRes = new AllRes();
		
		String dbname = "nothanks";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public Package genPack() {
		Package pack;
		if (deck.size() != 0) {
			Card c = deck.remove(0);
			pack = new Package();
			pack.setCard(c);
			pack.setMoney(0);
		} else {
			pack = new Package();
		}
		return pack;
	}
	
	public void startGame() {
		status = 1;
		numGoldInDeck = 12 - players.size()*initialRevealedMoney;
		deck = allRes.genDeck(players.size(), numGoldInDeck);
		for (int i=0;i<players.size();i++) {
			players.get(i).setRevealedMoney(initialRevealedMoney);
			players.get(i).setPhase(-1);
		}
		shuffle();
		players.get(curPlayer).setPhase(0);
		players.get(curPlayer).draw();
	}
	
	public void shuffle() {
		List<Card> shuffled = new ArrayList<>();
		Random rand = new Random();
		while (deck.size()>0) {
			int size = deck.size();
			shuffled.add(deck.remove(rand.nextInt(size)));
		}
		for (int i=0;i<shuffled.size();i++) {
			deck.add(shuffled.get(i));
		}
	}
	
	public void endGame() {
		status = 2;
	}
	
	public void sendPack(int x, Package pack) {
		players.get(x).setPack(pack);
		players.get(x).setPhase(0);
		curPlayer = x;
	}
	
	public void updateInitialRevealedMoney() {
		initialRevealedMoney = 12/players.size();
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
	
	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		p.setBoard(this);
		players.add(p);
		updateInitialRevealedMoney();
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Card> getDeck() {
		return deck;
	}

	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getNumGoldInDeck() {
		return numGoldInDeck;
	}
	public void setNumGoldInDeck(int numGoldInDeck) {
		this.numGoldInDeck = numGoldInDeck;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public int getInitialRevealedMoney() {
		return initialRevealedMoney;
	}
	public void setInitialRevealedMoney(int initialRevealedMoney) {
		this.initialRevealedMoney = initialRevealedMoney;
	}

	public void addBot() {
		String botName = "P" + Integer.toString(players.size());
		Player bot = new Player();
		bot.setName(botName);
		bot.setBot(true);
		players.add(bot);
		addPlayerToDB(botName);
		updateInitialRevealedMoney();
	}

	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setStatus(Integer.toString(status));
		entity.setDeckSize(Integer.toString(deck.size()));
		entity.setIntialRevealedMoney(Integer.toString(initialRevealedMoney));
		int i,j;
		List<String> handSizes = new ArrayList<>();
		List<String> revealedMoney = new ArrayList<>();
		List<String> hasPack = new ArrayList<>();
		List<String> playerNames = new ArrayList<>();
		List<String> hand = new ArrayList<>();
		List<String> scores = new ArrayList<>();
		List<List<String>> allHands = new ArrayList<>();
		String phase = "";
		String packCardImg = "";
		String packMoney = "";
		String trueMoney = "";
		for (i=0;i<players.size();i++) {
			handSizes.add(Integer.toString(players.get(i).getHand().size()));
			revealedMoney.add(Integer.toString(players.get(i).getRevealedMoney()));
			playerNames.add(players.get(i).getName());
			if (players.get(i).getName().contentEquals(name)) {
				phase = Integer.toString(players.get(i).getPhase());
				if (players.get(i).getPack() != null) {
					packCardImg = players.get(i).getPack().getCard().getImg();
				}
				trueMoney = Integer.toString(players.get(i).getTrueMoney());
				for (j=0;j<players.get(i).getHand().size();j++) {
					hand.add(players.get(i).getHand().get(j).getImg());
				}
			}
			if (players.get(i).getPack() != null) {
				packMoney = Integer.toString(players.get(i).getPack().getMoney());
				hasPack.add("y");
			} else {
				hasPack.add("n");
			}
			if (status == 2) {
				List<String> singleHand = new ArrayList<>();
				for (j=0;j<players.get(i).getHand().size();j++) {
					singleHand.add(players.get(i).getHand().get(j).getImg());
				}
				allHands.add(singleHand);
				scores.add(Integer.toString(players.get(i).calcScore()));
			}
		}
		entity.setCurPlayer(Integer.toString(curPlayer));
		entity.setLord(lord);
		entity.setPhase(phase);
		entity.setTrueMoney(trueMoney);
		entity.setPackCardImg(packCardImg);
		entity.setPackMoney(packMoney);
		entity.setHasPack(hasPack);
		entity.setHandSizes(handSizes);
		entity.setRevealedMoney(revealedMoney);
		entity.setPlayerNames(playerNames);
		entity.setHand(hand);
		entity.setAllHands(allHands);
		entity.setScores(scores);
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("status", status);
		doc.append("curPlayer", curPlayer);
		doc.append("numGoldInDeck", numGoldInDeck);
		doc.append("lord", lord);
		doc.append("initialRevealedMoney", initialRevealedMoney);
		List<String> playerNames = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
		}
		doc.append("playerNames", playerNames);
		List<Integer> lod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getNum());
		}
		doc.append("deck", lod);
		for (i=0;i<players.size();i++) {
			String key = "player-" + players.get(i).getName();
			Document dop = players.get(i).toDocument();
			doc.append(key, dop);
		}
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		status = doc.getInteger("status", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		numGoldInDeck = doc.getInteger("numGoldInDeck", -1);
		lord = doc.getString("lord");
		initialRevealedMoney = doc.getInteger("initialRevealedMoney", 0);
		int i;
		
		players = new ArrayList<>();
		List<String> playerNames = (List<String>) doc.get("playerNames");
		for (i=0;i<playerNames.size();i++) {
			String key = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(key);
			Player p = new Player();
			p.setFromDoc(dop);
			p.setBoard(this);
			p.setIndex(i);
			players.add(p);
		}
		
		deck = new ArrayList<>();
		List<Integer> lod = (List<Integer>) doc.get("deck");
		for (i=0;i<lod.size();i++) {
			Card c = CardFactory.createCard(lod.get(i));
			deck.add(c);
		}
	}
	
	public void dismiss() {
		dbutil.delete("id", id);
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
	
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updateDeck() {
		List<Integer> lod = new ArrayList<>();
		for (int i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getNum());
		}
		dbutil.update("id", id, "deck", lod);
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(players.get(i).getName());
		}
	}
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
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
