package com.cosine.cosgame.oink.startups;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.oink.startups.entity.CardEntity;
import com.cosine.cosgame.oink.startups.entity.PlayerEntity;
import com.cosine.cosgame.oink.startups.entity.StartupsEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Startups {
	int round;
	int curPlayer;
	
	List<Card> deck;
	List<Card> discard;
	List<Player> players;
	
	HashMap<Integer, Integer> antiMonopoly;
	HashMap<Integer, Integer> shareholder;
	
	Logger logger;
	Board board;
	
	MongoDBUtil dbutil;
	
	public Document hashMapToDocument(HashMap<Integer, Integer> map) {
		Document doc = new Document();
		for (int key: map.keySet()) {
			doc.append(Integer.toString(key), map.get(key));
		}
		return doc;
	}
	
	public HashMap<Integer, Integer> documentToHashmap(Document doc) {
		HashMap<Integer, Integer> map = new HashMap<>();
		if (doc != null) {
			Set<String> keySet = doc.keySet();
			for (String s : keySet) {
				int k = Integer.parseInt(s);
				int v = doc.getInteger(s, 0);
				map.put(k, v);
			}
		}
		return map;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("round", round);
		doc.append("curPlayer", curPlayer);
		doc.append("antiMonopoly", antiMonopoly);
		doc.append("shareholder", shareholder);
		doc.append("logs", logger.getLogs());
		doc.append("antiMonopoly", hashMapToDocument(antiMonopoly));
		doc.append("shareholder", hashMapToDocument(shareholder));
		
		int i;
		List<Document> dok = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dok.add(deck.get(i).toDocument());
		}
		List<Document> dos = new ArrayList<>();
		for (i=0;i<discard.size();i++) {
			dos.add(discard.get(i).toDocument());
		}
		doc.append("deck", dok);
		doc.append("discard", dos);
		
		for (i=0;i<players.size();i++) {
			String n = players.get(i).getName();
			n = "player-" + n;
			doc.append(n, players.get(i).toDocument());
		}
		
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		round = doc.getInteger("round", -1);
		curPlayer = doc.getInteger("curPlayer", -1);
		antiMonopoly = documentToHashmap((Document) doc.get("antiMonopoly"));
		shareholder = documentToHashmap((Document) doc.get("shareholder"));
		
		
		List<String> logs = (List<String>) doc.get("logs");
		logger = new Logger(logs);
		
		int i;
		List<Document> dok = (List<Document>) doc.get("deck");
		List<Document> dos = (List<Document>) doc.get("discard");
		deck = new ArrayList<>();
		for (i=0;i<dok.size();i++) {
			deck.add(new Card(dok.get(i)));
		}
		discard = new ArrayList<>();
		for (i=0;i<dos.size();i++) {
			discard.add(new Card(dos.get(i)));
		}
		
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String n = playerNames.get(i);
			n = "player-" + n;
			Document dop = (Document) doc.get(n);
			Player p = new Player();
			p.setStartups(this);
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	
	public StartupsEntity toStartupsEntity(String username) {
		StartupsEntity entity = new StartupsEntity();
		entity.setCurPlayer(curPlayer);
		entity.setRound(round);
		entity.setDeckSize(deck.size());
		
		int i,j;
		List<PlayerEntity> lp = new ArrayList<>();
		List<CardEntity> lc = new ArrayList<>();
		List<CardEntity> ls = new ArrayList<>();
		
		for (i=0;i<discard.size();i++) {
			ls.add(discard.get(i).toCardEntity());
		}
		
		boolean canDraw = false;
		int phase = Consts.OFFTURN;
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			lp.add(p.toPlayerEntity());
			if (p.getName().contentEquals(username)) {
				canDraw = p.canDraw();
				phase = p.getPhase();
				for (j=0;j<p.getHand().size();j++) {
					lc.add(p.getHand().get(j).toCardEntity());
				}
			}
		}
		entity.setCanDraw(canDraw);
		entity.setPlayers(lp);
		entity.setMyHand(lc);
		entity.setDiscard(ls);
		entity.setPhase(phase);
		return entity;
	}
	
	public Startups(Board board) {
		this.board = board;
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		logger = new Logger();
		round = 0;
		
		antiMonopoly = new HashMap<>();
		shareholder = new HashMap<>();
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startRound() {
		// Step 1: add all cards
		List<Card> allCards = new ArrayList<>();
		int i,j;
		for (i=5;i<=10;i++) {
			for (j=0;j<i;j++) {
				allCards.add(new Card(i));
			}
		}
		
		// Step 2: set antiMonopoly
		antiMonopoly = new HashMap<>();
		for (i=5;i<=10;i++) {
			antiMonopoly.put(i, -1);
		}
		
		// Step 3: shuffle
		deck = new ArrayList<>();
		Random rand = new Random();
		while (allCards.size()>0) {
			int x = rand.nextInt(allCards.size());
			deck.add(allCards.remove(x));
		}
		
		// Step 4: remove first 5 cards
		for (i=0;i<Consts.REMOVENUM;i++) {
			removeTopCard();
		}
		
		// Step 5: deal cards and coins
		for (i=0;i<players.size();i++) {
			players.get(i).startRound();
		}
		
		// Step 6: set status, round & curPlayer
		board.setStatus(Consts.INGAME);
		round++;
		logger.logRoundStart(round);
		//curPlayer = firstPlayer;
		players.get(curPlayer).startTurn();
		
	}
	
	public Card removeTopCard() {
		if (deck.size() == 0) {
			return null;
		} else {
			return deck.remove(0);
		}
	}
	
	public int drawCost(Player p) {
		int ans = 0;
		int i;
		for (i=0;i<discard.size();i++) {
			int num = discard.get(i).getNum();
			if (antiMonopoly.get(num) != p.getIndex()) {
				ans++;
			}
			
		}
		return ans;
	}
	
	public void placeCoins(Player p) {
		int i;
		for (i=0;i<discard.size();i++) {
			int num = discard.get(i).getNum();
			if (antiMonopoly.get(num) != p.getIndex()) {
				discard.get(i).receiveCoin();
			}
		}
	}
	
	public boolean potentialChangeAntiMonopoly(Player p, Card c) {
		int num = c.getNum();
		int ai = antiMonopoly.get(num);
		boolean f = false;
		if (ai == -1) {
			f = true;
		} else {
			Player p2 = players.get(ai);
			if (p2.numStockPlayed(num) < p.numStockPlayed(num)) {
				f = true;
			}
		}
		if (f) {
			antiMonopoly.put(num, p.getIndex());
		}
		return f;
	}
	
	public void nextPlayer() {
		if (board.getStatus() == Consts.INGAME) {
			curPlayer++;
			if (curPlayer == players.size()) {
				curPlayer = 0;
			}
			players.get(curPlayer).startTurn();
		}
	}
	
	public void endRound() {
		// Step 1: change status
		// status = Consts.ROUNDEND;
		
		// Step 2: get shareholder for each stock
		
		// Step 3: change coins
		
		// Step 4: calc score
	}
	
	// Start actual operations
	// Actual start game operation
	public void startGameUDB() {
		// Step 1: create players
		int i;
		List<String> playerNames = board.getPlayerNames();
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			Player p = new Player(playerNames.get(i));
			p.setStartups(this);
			players.add(p);
		}
		
		// Step 2: set round and curPlayer
		round = 0;
		curPlayer = board.getFirstPlayer();
		startRound();
		
		updatePlayers();
		updateBasicDB();
	}
	

	// Actual draw operation
	public void playerDrawUDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			if (p.canDraw() && p.getIndex() == curPlayer && p.getPhase() == Consts.DRAWORTAKE) {
				p.draw();
				updateBasicDB();
				updatePlayer(name);
			}
		}
	}
	
	// Actual discard operation
	public void playerDiscardUDB(String name, int cardIndex) {
		Player p = getPlayerByName(name);
		if (p != null) {
			p.discard(cardIndex);
			updateBasicDB();
			updatePlayers();
		}
	}
	
	// End actual operations
	
	
	public Player getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	
	public void updateBasicDB() {
		int i;
		List<Document> dok = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dok.add(deck.get(i).toDocument());
		}
		List<Document> dos = new ArrayList<>();
		for (i=0;i<discard.size();i++) {
			dos.add(discard.get(i).toDocument());
		}
		updateDB("deck", dok);
		updateDB("discard", dos);
		
		updateDB("round", round);
		updateDB("curPlayer", curPlayer);
		updateDB("antiMonopoly", hashMapToDocument(antiMonopoly));
		updateDB("shareholder", hashMapToDocument(shareholder));
		updateDB("logs", logger.getLogs());
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", board.getId(), key, value);
	}
	
	public boolean hasPlayer(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return true;
			}
		}
		return false;
	}

	public String getId() {
		return board.getId();
	}
	public void setId(String id) {
		board.setId(id);
	}
	public int getStatus() {
		return board.getStatus();
	}
	public void setStatus(int status) {
		board.setStatus(status);
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
	public int getFirstPlayer() {
		return board.getFirstPlayer();
	}
	public void setFirstPlayer(int firstPlayer) {
		board.setFirstPlayer(firstPlayer);
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public HashMap<Integer, Integer> getAntiMonopoly() {
		return antiMonopoly;
	}
	public void setAntiMonopoly(HashMap<Integer, Integer> antiMonopoly) {
		this.antiMonopoly = antiMonopoly;
	}
	public HashMap<Integer, Integer> getShareholder() {
		return shareholder;
	}
	public void setShareholder(HashMap<Integer, Integer> shareholder) {
		this.shareholder = shareholder;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
}
