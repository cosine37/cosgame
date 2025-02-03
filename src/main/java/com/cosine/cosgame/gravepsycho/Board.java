package com.cosine.cosgame.gravepsycho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<Card> deck;
	List<Card> revealed;
	List<Card> treasures;
	List<Card> removed;
	int status;
	int round;
	int leftover;
	String id;
	String lord;
	boolean useEvent;
	AllRes allRes;
	Event event;
	Logger logger;
	MongoDBUtil dbutil;
	
	public Board() {
		players = new ArrayList<>();
		deck = new ArrayList<>();
		revealed = new ArrayList<>();
		treasures = new ArrayList<>();
		removed = new ArrayList<>();
		allRes = new AllRes();
		event = new Event();
		logger = new Logger();
		
		String dbname = "gravepsycho";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void newBoard() {
		status = Consts.CREATEGAME;
	}
	
	public void startGame(int ue) {
		round = 0;
		leftover = 0;
		deck = allRes.getDeck();
		treasures = allRes.getTreasures();
		useEvent = false;
		if (ue == 1) {
			useEvent = true;
		}
		newRoundHandle();
	}
	
	public boolean disaster() {
		boolean ans = false;
		int[] a = {0,0,0,0,0};
		for (int i=0;i<revealed.size();i++) {
			if (revealed.get(i).getType() == Consts.DISASTER) {
				int x = revealed.get(i).getNum();
				a[x]++;
				if (a[x] >= 2) {
					ans = true;
					break;
				}
			}
		}
		if (useEvent && event != null) {
			ans = event.disaster(ans);
		}
		return ans;
	}
	
	public void disasterHandle() {
		List<String> pnames = new ArrayList<>();
		for (int i=0;i<players.size();i++) {
			Player p = players.get(i);
			p.setDecision(Consts.UNDECIDED);
			if (p.isStillIn()) {
				p.setMoneyThisTurn(0);
				pnames.add(p.getName());
			}
		}
		logger.logDisaster(pnames);
		status = Consts.DISASTERROUND;
	}
	
	public void goBackHandle(List<Player> backPlayers) {
		int n = backPlayers.size();
		if (n==0) {
			return;
		}
		int i;
		
		logger.logBack(backPlayers);
		
		if (backPlayers.size() == 1) {
			Player p = backPlayers.get(0);
			if (useEvent && event != null) {
				boolean finished = event.singleBackHandle(p);
				if (finished) return;
			}
			leftover = 0;
			for (i=0;i<backPlayers.size();i++) {
				backPlayers.get(i).setStillIn(false);
				backPlayers.get(i).addMoney(leftover);
				if (useEvent && event != null) {
					boolean finished = event.backHandle(backPlayers.get(i));
					if (finished) return;
				}
			}
			for (i=revealed.size()-1;i>=0;i--) {
				if (revealed.get(i).getType() == Consts.TREASURE) {
					Card c = revealed.remove(i);
					removed.add(c);
					p.addMoney(c.getNum());
					
					logger.logTreasure(p.getName(), c.getTreasureName());
				}
			}
		} else {
			int x = leftover / n;
			int y = leftover % n;
			leftover = y;
			for (i=0;i<backPlayers.size();i++) {
				backPlayers.get(i).setStillIn(false);
				backPlayers.get(i).addMoney(x);
				if (useEvent && event != null) {
					boolean finished = event.backHandle(backPlayers.get(i));
					if (finished) return;
				}
			}
			
		}
		
		
	}
	
	public void allBackHandle() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setDecision(Consts.UNDECIDED);
		}
		status = Consts.ENDROUND;
	}
	
	public void endGameHandle() {
		status = Consts.ENDGAME;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).secureMoney();
		}
	}
	
	public void newRoundHandle() {
		int i;
		if (status == Consts.DISASTERROUND) {
			int x = revealed.size()-1;
			while (x>0) {
				if (revealed.get(x).getType() == Consts.DISASTER) {
					Card c = revealed.remove(x);
					removed.add(c);
					break;
				}
				x--;
			}
		}
		while (revealed.size() > 0) {
			Card c = revealed.remove(0);
			if (c.getType() != Consts.TREASURE) {
				deck.add(c);
			} else {
				removed.add(c);
			}
		}
		if (treasures.size()>0) {
			Card t = treasures.remove(0);
			deck.add(t);
		}
		for (i=0;i<players.size();i++) {
			players.get(i).setStillIn(true);
			players.get(i).secureMoney();
			players.get(i).setMoneyThisTurn(0);
			players.get(i).setDecisionLastTurn(Consts.NA);
			players.get(i).setDecision(Consts.UNDECIDED);
		}
		shuffle();
		status = Consts.PENDING;
		leftover = 0;
		round++;
		logger.logRoundStart(round);
		if (useEvent) {
			AllRes allRes = new AllRes();
			event = allRes.getRandomEvent();
			event.setBoard(this);
			event.newRound();
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
	
	public void distributeCoins(int numCoins) {
		if (status == Consts.DISASTERROUND) return;
		
		if (useEvent && event != null) {
			boolean finished = event.distributeCoins(numCoins);
			if (finished) return;
		}
		
		int i;
		int n=0;
		
		for (i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				n++;
			}
		}
		
		if (n>0) {
			List<String> pnames = new ArrayList<>();
			int x = numCoins/n;
			int y = numCoins%n;
			leftover = leftover+y;
			for (i=0;i<players.size();i++) {
				if (players.get(i).isStillIn()) {
					players.get(i).addMoney(x);
					pnames.add(players.get(i).getName());
				}
			}
			logger.logDistributeCoins(pnames, x);
		}
	}
	
	public void distributeCoins(Card c) {
		if (c.getType() == Consts.COIN) {
			distributeCoins(c.getNum());
		}
	}
	
	public void revealACard() {
		Card c = deck.remove(0);
		revealed.add(c);
		
		if (useEvent && event != null) {
			event.onReveal(c);
		}
		
		if (useEvent && event != null) {
			boolean override = event.overrideLogReveal(c);
			if (override) {
				
			} else {
				logger.logRevealCard(c);
			}
		} else {
			logger.logRevealCard(c);
		}
		
		if (c.getType() == Consts.DISASTER) {
			if (disaster()) {
				disasterHandle();
			}
		} else if (c.getType() == Consts.COIN) {
			distributeCoins(c);
		}
	}
	
	public void revealNextCard() {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				players.get(i).setDecisionLastTurn(players.get(i).getDecision());
				players.get(i).setDecision(Consts.UNDECIDED);
			}
		}
		
		if (useEvent && event != null) {
			boolean finished = event.revealNext();
			if (finished) return;
		}
		
		revealACard();
	}
	
	public boolean allBack() {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isStillIn()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean allDecided() {
		if (status == Consts.PENDING) {
			for (int i=0;i<players.size();i++) {
				if (players.get(i).isStillIn()) {
					if (players.get(i).getDecision() == Consts.UNDECIDED) {
						return false;
					}
				}
			}
		} else if (status == Consts.ENDROUND || status == Consts.DISASTERROUND) {
			for (int i=0;i<players.size();i++) {
				if (players.get(i).getDecision() == Consts.UNDECIDED) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public void playerDecide(String name, int x) {
		Player p = getPlayerByName(name);
		if (p!=null) {
			p.setDecision(x);
			if (allDecided()) {
				if (status == Consts.PENDING) {
					List<Player> backPlayers = new ArrayList<>();
					for (int i=0;i<players.size();i++) {
						if (players.get(i).isStillIn()) {
							if (players.get(i).getDecision() == Consts.BACK) {
								backPlayers.add(players.get(i));
							}
						}
					}
					goBackHandle(backPlayers);
					if (allBack()) {
						allBackHandle();
					} else {
						revealNextCard();
					}
				} else if (status == Consts.ENDROUND || status == Consts.DISASTERROUND) {
					if (round >=5) {
						endGameHandle();
					} else {
						newRoundHandle();
					}
					
				}
			}
		}
	}
	
	public Player getPlayerByName(String name) {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
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
	public List<Card> getRevealed() {
		return revealed;
	}
	public void setRevealed(List<Card> revealed) {
		this.revealed = revealed;
	}
	public List<Card> getTreasures() {
		return treasures;
	}
	public void setTreasures(List<Card> treasures) {
		this.treasures = treasures;
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
	public int getLeftover() {
		return leftover;
	}
	public void setLeftover(int leftover) {
		this.leftover = leftover;
	}
	public List<Card> getRemoved() {
		return removed;
	}
	public void setRemoved(List<Card> removed) {
		this.removed = removed;
	}
	public boolean isUseEvent() {
		return useEvent;
	}
	public void setUseEvent(boolean useEvent) {
		this.useEvent = useEvent;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
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
	
	public void storeToDB() {
		Document doc = toDocument();
		dbutil.insert(doc);
	}
	
	public void getFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updateDeck() {
		int i;
		List<Document> lod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).toDocument());
		}
		dbutil.update("id", id, "deck", lod);
	}
	
	public void updateRevealed() {
		int i;
		List<Document> lor = new ArrayList<>();
		for (i=0;i<revealed.size();i++) {
			lor.add(revealed.get(i).toDocument());
		}
		dbutil.update("id", id, "revealed", lor);
	}
	
	public void updateTreasures() {
		int i;
		List<Document> lot = new ArrayList<>();
		for (i=0;i<treasures.size();i++) {
			lot.add(treasures.get(i).toDocument());
		}
		dbutil.update("id", id, "treasures", lot);
	}
	
	public void updateRemoved() {
		int i;
		List<Document> lot = new ArrayList<>();
		for (i=0;i<removed.size();i++) {
			lot.add(removed.get(i).toDocument());
		}
		dbutil.update("id", id, "removed", lot);
	}
	
	public void updateLogs() {
		dbutil.update("id", id, "logs", logger.getLogs());
	}
	
	public void dismiss() {
		dbutil.delete("id", id);
	}
	
	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		List<String> playerNames = new ArrayList<>();
		List<String> lor = new ArrayList<>();
		List<String> lov = new ArrayList<>();
		List<String> lod = new ArrayList<>();
		List<String> los = new ArrayList<>();
		List<String> money = new ArrayList<>();
		List<String> moneyThisTurn = new ArrayList<>();
		List<String> avatar = new ArrayList<>();
		String myIndex = "";
		String myDecision = "";
		String myMoney = "";
		int i;
		for (i=0;i<revealed.size();i++) {
			String img = revealed.get(i).getImage();
			if (useEvent && event != null) {
				img = event.cardImg(revealed.get(i));
			}
			lor.add(img);
		}
		for (i=0;i<removed.size();i++) {
			lov.add(removed.get(i).getImage());
		}
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			moneyThisTurn.add(Integer.toString(players.get(i).getMoneyThisTurn()));
			lod.add(Integer.toString(players.get(i).getDecisionLastTurn()));
			avatar.add(players.get(i).getAvatar());
			if (players.get(i).isStillIn()) {
				los.add("y");
			} else {
				los.add("n");
			}
			if (status == Consts.ENDGAME) {
				money.add(Integer.toString(players.get(i).getMoney()));
			} else {
				if (players.get(i).getName().contentEquals(name)) {
					money.add(Integer.toString(players.get(i).getMoney()));
				} else {
					money.add("-");
				}
			}
			if (players.get(i).getName().contentEquals(name)) {
				myIndex = Integer.toString(i);
				myDecision = Integer.toString(players.get(i).getDecision());
				myMoney = Integer.toString(players.get(i).getMoney());
			}
		}
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(Integer.toString(status));
		entity.setRound(Integer.toString(round));
		entity.setLeftover(Integer.toString(leftover));
		entity.setPlayerNames(playerNames);
		entity.setRevealed(lor);
		entity.setRemoved(lov);
		entity.setDecisions(lod);
		entity.setStillIn(los);
		entity.setMoney(money);
		entity.setMoneyThisTurn(moneyThisTurn);
		entity.setMyIndex(myIndex);
		entity.setMyDecision(myDecision);
		entity.setMyMoney(myMoney);
		entity.setAvatar(avatar);
		entity.setUseEvent(useEvent);
		entity.setEvent(event.toEventEntity());
		entity.setLogs(logger.getLogs());
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		List<Document> lod = new ArrayList<>();
		List<Document> lor = new ArrayList<>();
		List<Document> lot = new ArrayList<>();
		List<Document> lov = new ArrayList<>();
		List<String> playerNames = new ArrayList<>();
		int i;
		
		doc.append("playerNames", playerNames);
		for (i=0;i<deck.size();i++) {
			lod.add(deck.get(i).toDocument());
		}
		doc.append("deck", lod);
		for (i=0;i<revealed.size();i++) {
			lor.add(revealed.get(i).toDocument());
		}
		doc.append("revealed", lor);
		for (i=0;i<treasures.size();i++) {
			lot.add(treasures.get(i).toDocument());
		}
		doc.append("treasures", lot);
		for (i=0;i<removed.size();i++) {
			lov.add(removed.get(i).toDocument());
		}
		doc.append("removed", lov);
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("leftover", leftover);
		doc.append("event", event.getNum());
		doc.append("useEvent", useEvent);
		doc.append("logs", logger.getLogs());
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		leftover = doc.getInteger("leftover", 0);
		useEvent = doc.getBoolean("useEvent", false);
		List<Document> lod = (List<Document>) doc.get("deck");
		List<Document> lor = (List<Document>) doc.get("revealed");
		List<Document> lot = (List<Document>) doc.get("treasures");
		List<Document> lov = (List<Document>) doc.get("removed");
		int i;
		deck = new ArrayList<>();
		for (i=0;i<lod.size();i++) {
			Card c = new Card();
			c.setFromDoc(lod.get(i));
			deck.add(c);
		}
		revealed = new ArrayList<>();
		for (i=0;i<lor.size();i++) {
			Card c = new Card();
			c.setFromDoc(lor.get(i));
			revealed.add(c);
		}
		treasures = new ArrayList<>();
		for (i=0;i<lot.size();i++) {
			Card c = new Card();
			c.setFromDoc(lot.get(i));
			treasures.add(c);
		}
		removed = new ArrayList<>();
		for (i=0;i<lov.size();i++) {
			Card c = new Card();
			c.setFromDoc(lov.get(i));
			removed.add(c);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setFromDoc(dop);
			players.add(p);
		}
		int eid = doc.getInteger("event", 0);
		event = EventFactory.makeEvent(eid);
		List<String> logs = (List<String>) doc.get("logs");
		logger = new Logger(logs);
		event.setBoard(this);
	}
}
