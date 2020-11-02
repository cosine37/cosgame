package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<List<Card>> playedCards;
	List<Card> ancient;
	List<Card> deck;
	int status;
	int round;
	int turn;
	int curPlayer;
	String id;
	String lord;
	AllRes allRes;
	MongoDBUtil dbutil;
	
	public Board(){
		players = new ArrayList<>();
		playedCards = new ArrayList<>();
		ancient = new ArrayList<>();
		deck = new ArrayList<>();
		allRes = new AllRes();
		
		String dbname = "pokewhat";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void genDeck() {
		int i,j;
		final int NUMSPELL = 8;
		for (i=1;i<=NUMSPELL;i++) {
			for (j=1;j<=i;j++) {
				Card c = CardFactory.createCard(i);
				deck.add(c);
			}
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
	
	public void removeCards(int x) {
		int numRemove = 0;
		if (x == 2) {
			numRemove = 12;
		} else if (x == 3) {
			numRemove = 6;
		}
		for (int i=0;i<numRemove;i++) {
			Card c = deck.remove(0);
			int index = c.getNum();
			playedCards.get(index).add(c);
		}
	}
	
	public void genAncient() {
		for (int i=0;i<PokewhatConsts.NUMANCIENT;i++) {
			Card c = deck.remove(0);
			ancient.add(c);
		}
	}
	
	public void deal() {
		int i,j;
		for (i=0;i<players.size();i++) {
			for (j=0;j<PokewhatConsts.HANDSIZE;j++) {
				Card c = deck.remove(0);
				players.get(i).getHand().add(c);
			}
			players.get(i).setHp(PokewhatConsts.MAXHP);
		}
	}
	
	public void startGame() {
		int i;
		for (i=0;i<9;i++) {
			List<Card> ls = new ArrayList<>();
			playedCards.add(ls);
		}
		genDeck();
		shuffle();
		removeCards(players.size());
		genAncient();
		deal();
	}
	
	public boolean isRoundEnd() {
		boolean ans = false;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getHp() == 0) {
				ans = true;
			}
		}
		return ans;
	}
	
	public void nextRound() {
		round++;
	}
	
	public void addToPlayedCards(Card c) {
		int index = c.getNum();
		playedCards.get(index).add(c);
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public void addPlayer(String s) {
		Player p = new Player();
		p.setName(s);
		players.add(p);
	}
	public void addBot() {
		String botName = "P" + Integer.toString(players.size());
		Player bot = new Player();
		bot.setName(botName);
		bot.setBot(true);
		players.add(bot);
		addPlayerToDB(botName);
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
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<List<Card>> getPlayedCards() {
		return playedCards;
	}
	public void setPlayedCards(List<List<Card>> playedCards) {
		this.playedCards = playedCards;
	}
	public List<Card> getAncient() {
		return ancient;
	}
	public void setAncient(List<Card> ancient) {
		this.ancient = ancient;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
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
	
	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		
		List<List<String>> allCards = new ArrayList<>();
		List<List<String>> playedCards = new ArrayList<>();
		List<String> playerNames = new ArrayList<>();
		List<String> pms = new ArrayList<>();
		List<String> hp = new ArrayList<>();
		int i,j;
		for (i=0;i<this.playedCards.size();i++) {
			List<String> sl = new ArrayList<>();
			for (j=0;j<this.playedCards.get(i).size();j++) {
				sl.add(this.playedCards.get(i).get(j).getImg());
			}
			playedCards.add(sl);
		}
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			pms.add(players.get(i).getPm().getImg());
			hp.add(Integer.toString(players.get(i).getHp()));
			
			List<String> sl = new ArrayList<>();
			for (j=0;j<players.get(i).getHand().size();j++) {
				if (players.get(i).getName().contentEquals(name)) {
					sl.add("qs");
				} else {
					sl.add(players.get(i).getHand().get(j).getImg());
				}
			}
			allCards.add(sl);
		}
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setRound(Integer.toString(round));
		entity.setTurn(Integer.toString(turn));
		entity.setStatus(Integer.toString(status));
		entity.setPlayedCards(playedCards);
		entity.setPlayerNames(playerNames);
		entity.setAllCards(allCards);
		entity.setHp(hp);
		entity.setPm(pms);
		return entity;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("id",id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("turn", turn);
		doc.append("curPlayer", curPlayer);
		int i;
		List<String> lod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getImg());
		}
		doc.append("deck", lod);
		List<String> loa = new ArrayList<>();
		for (i=0;i<ancient.size();i++) {
			loa.add(ancient.get(i).getImg());
		}
		doc.append("ancient", loa);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String playerName = players.get(i).getName();
			playerNames.add(playerName);
			String s = "player-" + playerName;
			doc.append(s, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		turn = doc.getInteger("turn", 0);
		curPlayer = doc.getInteger("curPlayer", 0);
		int i;
		List<String> lod = (List<String>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<lod.size();i++) {
			Card c = CardFactory.createCard(lod.get(i));
			c.setBoard(this);
			deck.add(c);
		}
		List<String> loa = (List<String>) doc.get("ancient");
		ancient = new ArrayList<>();
		for (i=0;i<loa.size();i++) {
			Card c = CardFactory.createCard(loa.get(i));
			c.setBoard(this);
			ancient.add(c);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
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
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(players.get(i).getName());
		}
	}
	
	public void updateDeck() {
		List<String> lod = new ArrayList<>();
		for (int i=0;i<deck.size();i++) {
			lod.add(deck.get(i).getImg());
		}
		dbutil.update("id", id, "deck", lod);
	}
	
	public void updateAncient() {
		List<String> loa = new ArrayList<>();
		for (int i=0;i<ancient.size();i++) {
			loa.add(ancient.get(i).getImg());
		}
		dbutil.update("id", id, "ancient", loa);
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
