package com.cosine.cosgame.oink.west;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.oink.west.entity.CardEntity;
import com.cosine.cosgame.oink.west.entity.PlayerEntity;
import com.cosine.cosgame.oink.west.entity.WestEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class West {
	List<Player> players;
	int round;
	int pool;
	int winner;
	int firstPlayer;
	int curPlayer;
	
	List<Card> deck;
	List<Card> assist;
	
	Board board;
	Logger logger;
	
	MongoDBUtil dbutil;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		for (i=0;i<players.size();i++){
			players.get(i).setIndex(i);
			String n = "player-" + players.get(i).getName();
			doc.append(n, players.get(i).toDocument());
		}
		doc.append("round",round);
		doc.append("pool",pool);
		doc.append("winner",winner);
		doc.append("firstPlayer",firstPlayer);
		doc.append("curPlayer",curPlayer);
		List<Document> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).toDocument());
		}
		doc.append("deck",deckDocList);
		List<Document> assistDocList = new ArrayList<>();
		for (i=0;i<assist.size();i++){
			assistDocList.add(assist.get(i).toDocument());
		}
		doc.append("assist",assistDocList);
		doc.append("logs", logger.getLogs());
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++){
			String n = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(n);
			Player p = new Player();
			p.setWest(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
		round = doc.getInteger("round",0);
		pool = doc.getInteger("pool",0);
		winner = doc.getInteger("winner",0);
		firstPlayer = doc.getInteger("firstPlayer",0);
		curPlayer = doc.getInteger("curPlayer",0);
		List<Document> deckDocList = (List<Document>)doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<deckDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(deckDocList.get(i));
			deck.add(e);
		}
		List<Document> assistDocList = (List<Document>)doc.get("assist");
		assist = new ArrayList<>();
		for (i=0;i<assistDocList.size();i++){
			Card e = new Card();
			e.setFromDoc(assistDocList.get(i));
			assist.add(e);
		}
		List<String> logs = (List<String>) doc.get("logs");
		logger = new Logger(logs);
	}
	public WestEntity toWestEntity(String username){
		int i,j;
		WestEntity entity = new WestEntity();
		List<PlayerEntity> listOfPlayers = new ArrayList<>();
		for (i=0;i<players.size();i++){
			listOfPlayers.add(players.get(i).toPlayerEntity(username));
			if (players.get(i).getName().contentEquals(username)){
				Player p = players.get(i);
				List<CardEntity> myHand = new ArrayList<>();
				for (j=0;j<p.getHand().size();j++) {
					myHand.add(p.getHand().get(j).toCardEntity(username));
				}
				entity.setMyHand(myHand);
				entity.setPhase(p.getPhase());
			}
		}
		entity.setPlayers(listOfPlayers);
		entity.setStatus(board.getStatus());
		entity.setRound(round);
		entity.setPool(pool);
		entity.setWinner(winner);
		entity.setFirstPlayer(firstPlayer);
		entity.setCurPlayer(curPlayer);
		List<CardEntity> listOfAssistEntity = new ArrayList<>();
		for (i=0;i<assist.size();i++){
			listOfAssistEntity.add(assist.get(i).toCardEntity(username));
		}
		entity.setAssist(listOfAssistEntity);
		entity.setLogs(logger.getLogs());
		return entity;
	}
	
	public West(Board board) {
		this.board = board;
		
		logger = new Logger();
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void newRound() {
		// Step 1: update deck, assist and pool
		round++;
		deck = AllRes.genShuffledDeck();
		assist = new ArrayList<>();
		updateAssist();
		// TODO: may need to update this
		if (round == 7) {
			pool = 2;
		} else {
			pool = 1;
		}
		
		// Step 2: update all players
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).newRound();
		}
		
		// Step 3: update first player phase
		players.get(firstPlayer).setPhase(Consts.DRAWORREPLACE);
		
		// Step 4: log new round
		logger.logRoundStart(round);
	}
	
	public Card removeTop() {
		if (deck.size() == 0) {
			return null;
		} else {
			Card c = deck.remove(0);
			return c;
		}
	}
	
	public void addPool(int x) {
		pool = pool+x;
	}
	
	public int bidCost() {
		int ans;
		if (round == 7) {
			ans = 2;
		} else {
			ans = 1;
		}
		return ans;
	}
	
	public void updateAssist() {
		Card c = removeTop();
		if (c != null) {
			assist.add(0,c);
		}
		
	}
	
	public Card getCurAssist() {
		if (assist.size() == 0) {
			return null;
		} else {
			return assist.get(0);
		}
	}
	
	public void calcWinner() {
		winner = -1;
		int minIndex = -1;
		int min = 99;
		int max = 0;
		int i;
		
		// Step 1: Calc min player
		for (i=0;i<players.size();i++) {
			if (players.get(i).getHandNum() < min) {
				min = players.get(i).getHandNum();
				minIndex = i;
			}
		}
		
		// Step 2: Calc max and winner
		int t,x;
		for (i=0;i<players.size();i++) {
			t = (firstPlayer+i) % players.size(); // closer to first player wins
			if (players.get(t).isStillIn()) {
				x = players.get(t).getHandNum();
				if (t == minIndex) {
					x = x+getCurAssist().getNum();
				}
				if (x>max) {
					max = x;
					winner = t;
				}
			}
		}
	}
	
	public boolean oneStillIn() {
		int i;
		int x = 0;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				x++;
			}
		}
		return x == 1;
	}
	
	public void nextPlayer() {
		// Step 1: offturn current player
		players.get(curPlayer).setPhase(Consts.OFFTURN);
		
		// Step 2: find next active player
		int x = (curPlayer+1) % players.size();
		while (players.get(x).isAlive() == false && x != firstPlayer) {
			x = (x+1) % players.size();
		}
		curPlayer = x;
		
		// Step 3: define next player's status
		if (board.getStatus() == Consts.INGAME) {
			if (curPlayer == firstPlayer) {
				board.setStatus(Consts.BID);
				players.get(curPlayer).setPhase(Consts.BIDORRETREAT);
			} else {
				players.get(curPlayer).setPhase(Consts.DRAWORREPLACE);
			}
		} else if (board.getStatus() == Consts.BID) {
			if (curPlayer == firstPlayer) {
				board.setStatus(Consts.RESULT);
			} else {
				players.get(curPlayer).setPhase(Consts.BIDORRETREAT);
			}
		}
		
		//System.out.println(status);
		
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
			p.setWest(this);
			p.newGame();
			players.add(p);
		}
		
		// Step 2: set round and curPlayer
		round = 0;
		curPlayer = board.getFirstPlayer();
		firstPlayer = board.getFirstPlayer();
		newRound();
		
		updatePlayers();
		updateBasicDB();
	}
	
	// Actual exchange assist operation
	public void exchangeUDB(String username) {
		Player p = getPlayerByName(username);
		if (p != null && p.getPhase() == Consts.DRAWORREPLACE) {
			updateAssist();
			nextPlayer();
			logger.logExchange(p, getCurAssist());
			
			updatePlayers();
			updateBasicDB();
		}
		
	}
	
	// Actual draw operation
	public void playerDrawUDB(String username) {
		Player p = getPlayerByName(username);
		if (p != null && p.getPhase() == Consts.DRAWORREPLACE) {
			p.draw();
			p.setPhase(Consts.DISCARD);
			logger.logDraw(p);
			
			updatePlayers();
			updateBasicDB();
		}
	}
	
	// Actual discard operation
	public void playerDiscardUDB(String username, int index) {
		Player p = getPlayerByName(username);
		if (p != null && p.getPhase() == Consts.DISCARD) {
			p.discard(index);
			nextPlayer();
			logger.logDiscard(p, p.getDiscard().get(0));
			
			updatePlayers();
			updateBasicDB();
		}
	}
	
	// Actual bid operation
	public void playerBidUDB(String username) {
		Player p = getPlayerByName(username);
		if (p != null && p.getPhase() == Consts.BIDORRETREAT) {
			p.bid(bidCost());
			nextPlayer();
			logger.logBid(p, bidCost());
			
			updatePlayers();
			updateBasicDB();
		}
	}
	
	// Actual retreat operation
	public void playerRetreatUDB(String username) {
		Player p = getPlayerByName(username);
		if (p != null && p.getPhase() == Consts.BIDORRETREAT) {
			p.retreat();
			nextPlayer();
			logger.logRetreat(p);
			
			updatePlayers();
			updateBasicDB();
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
		updateDB("deck", dok);
		List<Document> doa = new ArrayList<>();
		for (i=0;i<assist.size();i++) {
			doa.add(assist.get(i).toDocument());
		}
		updateDB("assist", doa);
		
		updateDB("round", round);
		updateDB("status", board.getStatus());
		updateDB("curPlayer", curPlayer);
		updateDB("firstPlayer", firstPlayer);
		updateDB("winner", winner);
		updateDB("pool", pool);
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

	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getPool() {
		return pool;
	}
	public void setPool(int pool) {
		this.pool = pool;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getFirstPlayer() {
		return firstPlayer;
	}
	public void setFirstPlayer(int firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getAssist() {
		return assist;
	}
	public void setAssist(List<Card> assist) {
		this.assist = assist;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
}
