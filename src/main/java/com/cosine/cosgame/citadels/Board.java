package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<Card> deck;
	List<Role> roles;
	boolean firstFinished;
	int finishCount;
	int coins;
	int killedRole;
	int stealedRole;
	int roundCount;
	int phase;
	int curPlayer;
	String id;
	MongoDBUtil dbutil;
	
	public Board() {
		id = "1";
		players = new ArrayList<>();
		deck = new ArrayList<>();
		roles = new ArrayList<>();
		firstFinished = true;
		finishCount = 8;
		coins = 30;
		killedRole = 0;
		stealedRole = 0;
		
		String dbname = "citadels";
		String col = "board";
		
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public int takeCoins(int n) {
		int ans;
		if (coins < n) {
			ans = coins;
			coins = 0;
		} else {
			ans = n;
			coins = coins-n;
		}
		return ans;
	}
	
	public void addPlayer(String name) {
		Player p = new Player(name);
		p.setBoard(this);
		players.add(p);
	}
	
	public void setDeck() {
		int i;
		Card c;
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("路边摊", CitadelConsts.GREEN, 1, "g1");
			deck.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("快餐店", CitadelConsts.GREEN, 2, "g21");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("菜场", CitadelConsts.GREEN, 2, "g22");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("超市", CitadelConsts.GREEN, 3, "g3");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("商场", CitadelConsts.GREEN, 4, "g4");
			deck.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("商业步行街", CitadelConsts.GREEN, 5, "g5");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("幼儿园", CitadelConsts.BLUE, 1, "b1");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("小学", CitadelConsts.BLUE, 2, "b2");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("初中", CitadelConsts.BLUE, 3, "b3");
			deck.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("高中", CitadelConsts.BLUE, 5, "b5");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("岗亭", CitadelConsts.RED, 1, "r1");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("监狱", CitadelConsts.RED, 2, "r2");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("警察局", CitadelConsts.RED, 3, "r3");
			deck.add(c);
		}
		for (i=0;i<2;i++) {
			c = CardFactory.createCard("军区", CitadelConsts.RED, 5, "r5");
			deck.add(c);
		}
		for (i=0;i<5;i++) {
			c = CardFactory.createCard("法院", CitadelConsts.YELLOW, 3, "y3");
			deck.add(c);
		}
		for (i=0;i<4;i++) {
			c = CardFactory.createCard("电视台", CitadelConsts.YELLOW, 4, "y4");
			deck.add(c);
		}
		for (i=0;i<3;i++) {
			c = CardFactory.createCard("市政府", CitadelConsts.YELLOW, 5, "y5");
			deck.add(c);
		}
		
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
	
	public void deal() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).draw(4);
			players.get(i).addCoin(2);
		}
	}
	
	public void gameSetup() {
		setDeck();
		shuffle();
		deal();
	}
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public Player getPlayerByRole(int roleNum) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getRoleNum() == roleNum) {
				return players.get(i);
			}
		}
		return null;
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
	public boolean isFirstFinished() {
		return firstFinished;
	}
	public void setFirstFinished(boolean firstFinished) {
		this.firstFinished = firstFinished;
	}
	public int getFinishCount() {
		return finishCount;
	}
	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public int getKilledRole() {
		return killedRole;
	}
	public void setKilledRole(int killedRole) {
		this.killedRole = killedRole;
	}
	public int getStealedRole() {
		return stealedRole;
	}
	public void setStealedRole(int stealedRole) {
		this.stealedRole = stealedRole;
	}
	public int getRoundCount() {
		return roundCount;
	}
	public void setRoundCount(int roundCount) {
		this.roundCount = roundCount;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public BoardEntity toBoardEntity() {
		BoardEntity entity = new BoardEntity();
		int i,j;
		List<String> playerNames = new ArrayList<>();
		List<String> coins = new ArrayList<>();
		List<List<String>> built = new ArrayList<>();
		List<List<String>> hand = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			coins.add(Integer.toString(players.get(i).getCoin()));
			List<String> singleBuilt = new ArrayList<>();
			List<String> singleHand = new ArrayList<>();
			for (j=0;j<players.get(i).getBuilt().size();j++) {
				singleBuilt.add(players.get(i).getBuilt().get(j).getImg());
			}
			built.add(singleBuilt);
			for (j=0;j<players.get(i).getHand().size();j++) {
				singleHand.add(players.get(i).getHand().get(j).getImg());
			}
			hand.add(singleHand);
		}
		entity.setBank(Integer.toString(this.coins));
		entity.setPlayerNames(playerNames);
		entity.setBuilt(built);
		entity.setHand(hand);
		entity.setCoins(coins);
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("firstFinished", firstFinished);
		doc.append("finishCount", finishCount);
		doc.append("coins", coins);
		doc.append("killedRole", killedRole);
		doc.append("stealedRole", stealedRole);
		doc.append("roundCount", roundCount);
		doc.append("phase", phase);
		doc.append("curPlayer", curPlayer);
		int i;
		/*
		List<Document> dop = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			dop.add(players.get(i).toDocument());
		}
		*/
		List<Document> dod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dod.add(deck.get(i).toDocument());
		}
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String playerName = players.get(i).getName();
			playerNames.add(players.get(i).getName());
			playerName = "player-" + playerName;
			doc.append(playerName, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		doc.append("deck", dod);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		firstFinished = doc.getBoolean("firstFinished", false);
		finishCount = doc.getInteger("finishCount", 0);
		coins = doc.getInteger("coins", coins);
		killedRole = doc.getInteger("killedRole", 0);
		stealedRole = doc.getInteger("stealedRole", 0);
		roundCount = doc.getInteger("roundCount", 0);
		phase = doc.getInteger("phase", 0);
		curPlayer = doc.getInteger("curPlayer", 0);
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		for (i=0;i<playerNames.size();i++) {
			String playerName = playerNames.get(i);
			Player p = new Player(playerName);
			p.setBoard(this);
			playerName = "player-" + playerName;
			Document dop = (Document) doc.get(playerName);
			if (dop != null) {
				p.setFromDoc(dop);
			}
			players.add(p);
		}
		/*
		List<Document> dop = (List<Document>) doc.get("players");
		players = new ArrayList<>();
		for (i=0;i<dop.size();i++) {
			Player p = new Player("name");
			p.setBoard(this);
			p.setFromDoc(dop.get(i));
			players.add(p);
		}
		*/
		List<Document> dod = (List<Document>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<dod.size();i++) {
			Card c = CardFactory.createCard(dod.get(i));
			c.setBoard(this);
			deck.add(c);
		}
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updatePlayer(String name) {
		Player p = this.getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + name;
			dbutil.update("id", id, playerName, dop);
		}
	}

	public void storeToDB() {
		Document doc = toDocument();
		dbutil.insert(doc);
	}
	
	public void getFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
}
