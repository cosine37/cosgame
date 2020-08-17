package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;

import org.bson.Document;

import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	AllRes allRes;
	List<Player> players;
	List<Card> deck;
	List<Role> roles;
	String lord;
	boolean firstFinished;
	int finishCount;
	int coins;
	int killedRole;
	int stealedRole;
	int roundCount;
	int phase;
	int status;
	int curPlayer;
	int curRoleNum;
	int crown;
	String id;
	MongoDBUtil dbutil;
	
	public Board() {
		allRes = new AllRes();
		players = new ArrayList<>();
		deck = new ArrayList<>();
		roles = new ArrayList<>();
		firstFinished = true;
		finishCount = 8;
		coins = 30;
		killedRole = -1;
		stealedRole = -1;
		
		status = CitadelsConsts.PREGAME;
		
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
	
	public void genDeck() {
		deck = allRes.genDeck();
	}
	
	public void genRoles() {
		roles = allRes.genRoles();
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
	
	public List<Card> firstCards(int x) {
		List<Card> ans = new ArrayList<>();
		int i;
		for (i=0;i<x;i++) {
			if (deck.size()>0) {
				ans.add(deck.remove(0));
			} else {
				break;
			}
		}
		return ans;
	}
	
	public void bottomDeck(List<Card> cards) {
		while (cards.size()>0) {
			deck.add(cards.remove(0));
		}
	}
	
	public void bottomDeck(Card card) {
		deck.add(card);
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public void gameSetup() {
		genRoles();
		genDeck();
		shuffle();
		deal();
		curPlayer = crown;
		newRound();
		
	}
	
	public Role getRoleByNum(int num) {
		for (int i=0;i<roles.size();i++) {
			if (roles.get(i).getNum() == num) {
				return roles.get(i);
			}
		}
		return null;
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
	
	public int getPlayerIndex(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getPlayerIndex(Player p) {
		return getPlayerIndex(p.getName());
	}
	
	public void discardRoles() {
		int numPlayers = players.size();
		int numReveal;
		int numHidden;
		if (numPlayers == 4) {
			numReveal = 2;
			numHidden = 1;
		} else if (numPlayers == 5) {
			numReveal = 1;
			numHidden = 1;
		} else if (numPlayers == 6) {
			numReveal = 0;
			numHidden = 1;
		} else if (numPlayers == 7) {
			numReveal = 0;
			numHidden = 1;
		} else {
			numReveal = 0;
			numHidden = 0;
		}
		List<Integer> tempNum = new ArrayList<>();
		int i;
		for (i=0;i<roles.size();i++) {
			tempNum.add(i);
		}
		Random rand = new Random();
		for (i=0;i<numReveal;i++) {
			int x = rand.nextInt(tempNum.size());
			int index = tempNum.remove(x);
			roles.get(index).setOwner(CitadelsConsts.NOTUSEDREVEALED);
		}
		for (i=0;i<numHidden;i++) {
			int x = rand.nextInt(tempNum.size());
			int index = tempNum.remove(x);
			roles.get(index).setOwner(CitadelsConsts.NOTUSEDHIDDEN);
		}
		
	}
	
	public void newRound() {
		int i;
		for (i=0;i<roles.size();i++) {
			roles.get(i).setOwner(-1);
		}
		for (i=0;i<players.size();i++) {
			players.get(i).setRole(null);
			players.get(i).setRoleNum(-1);
		}
		discardRoles();
		status = CitadelsConsts.CHOOSEROLE;
		killedRole = -1;
		stealedRole = -1;
	}
	
	public void nextPlayerChooseRole() {
		curPlayer = curPlayer + 1;
		if (curPlayer >= players.size()) {
			curPlayer = 0;
		}
		players.get(curPlayer).setPhase(CitadelsConsts.CHOOSEROLE);
		updateStatus();
	}
	
	public void nextRole() {
		curRoleNum = curRoleNum+1;
		if (curRoleNum>roles.size()) {
			updateStatus();
		}
		if (curRoleNum == killedRole) {
			nextRole();
		}
		int i;
		for (i=0;i<roles.size();i++) {
			Role r = roles.get(i);
			if (r.getNum() == curRoleNum) {
				if (r.getOwner() < 0) { // Not selected
					nextRole();
				} else {
					curPlayer = r.getOwner();
				}
			}
		}
	}
	
	public void updateStatus() {
		if (status == CitadelsConsts.CHOOSEROLE) {
			int i;
			boolean flag = true;
			for (i=0;i<players.size();i++) {
				if (players.get(i).getRoleNum() == -1) {
					flag = false;
					break;
				}
			}
			if (flag) {
				status = CitadelsConsts.TAKETURNS;
				curRoleNum = 0;
				nextRole();
			}
		} else if (status == CitadelsConsts.TAKETURNS) {
			if (curRoleNum>roles.size()) {
				newRound();
			}
		}
	}
	
	public void botChooseRole() {
		Player p = players.get(curPlayer);
		//System.out.println(p.getName());
		if (p.isBot()) {
			int i;
			List<Integer> choices = new ArrayList<>();
			for (i=0;i<roles.size();i++) {
				choices.add(i);
			}
			int x;
			while (p.getRoleNum() == -1) {
				Random rand = new Random();
				x = choices.remove(rand.nextInt(choices.size()));
				if (roles.get(x).getOwner() == CitadelsConsts.SELECTABLE) {
					p.chooseRole(x);
					//System.out.println(p.getName()+" chooses "+x);
					break;
				}
			}
		}
	}
	
	public void addCoin(int x) {
		this.coins = this.coins+x;
	}
	public void addBot() {
		String botName = "P" + Integer.toString(players.size());
		Player bot = new Player(botName);
		bot.setBot(true);
		players.add(bot);
		addPlayerToDB(botName);
	}
	public boolean isLord(String name) {
		return name.contentEquals(this.lord);
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public int getCurRoleNum() {
		return curRoleNum;
	}

	public void setCurRoleNum(int curRoleNum) {
		this.curRoleNum = curRoleNum;
	}

	public int getCrown() {
		return crown;
	}

	public void setCrown(int crown) {
		this.crown = crown;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		int i,j;
		List<String> playerNames = new ArrayList<>();
		List<String> coins = new ArrayList<>();
		List<String> handSizes = new ArrayList<>();
		List<List<String>> built = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			coins.add(Integer.toString(players.get(i).getCoin()));
			handSizes.add(Integer.toString(players.get(i).getHand().size()));
			List<String> singleBuilt = new ArrayList<>();
			for (j=0;j<players.get(i).getBuilt().size();j++) {
				singleBuilt.add(players.get(i).getBuilt().get(j).getImg());
			}
			built.add(singleBuilt);
		}
		List<String> hand = new ArrayList<>();
		List<String> buildable = new ArrayList<>();
		List<String> revealedCards = new ArrayList<>();
		Player p = this.getPlayerByName(name);
		String status = Integer.toString(this.status);
		String phase = "-1";
		if (p!=null) {
			phase = Integer.toString(p.getPhase());
			for (i=0;i<p.getHand().size();i++) {
				Card c = p.getHand().get(i);
				hand.add(c.getImg());
				String f;
				if (p.canBuild(i)) {
					f = "y";
				} else {
					f = "n";
				}
				buildable.add(f);
			}
			for (i=0;i<p.getForChoose().size();i++) {
				Card c = p.getForChoose().get(i);
				revealedCards.add(c.getImg());
			}
		}
		List<String> roleNums = new ArrayList<>();
		List<String> roleOwners = new ArrayList<>();
		for (i=0;i<roles.size();i++) {
			roleNums.add(Integer.toString(roles.get(i).getNum()));
			if (roles.get(i).getOwner() == CitadelsConsts.SELECTABLE) {
				roleOwners.add("-1");
			} else if (roles.get(i).getOwner() == CitadelsConsts.NOTUSEDREVEALED) {
				roleOwners.add("-2");
			} else {
				roleOwners.add("-3");
			}
		}
		entity.setDeckSize(Integer.toString(this.deck.size()));
		entity.setBank(Integer.toString(this.coins));
		entity.setPlayerNames(playerNames);
		entity.setBuilt(built);
		entity.setHand(hand);
		entity.setBuildable(buildable);
		entity.setRevealedCards(revealedCards);
		entity.setCoins(coins);
		entity.setHandSizes(handSizes);
		entity.setPhase(phase);
		entity.setStatus(status);
		entity.setCurPlayer(Integer.toString(curPlayer));
		entity.setCurRole(Integer.toString(curRoleNum));
		entity.setRoundCount(Integer.toString(roundCount));
		entity.setRoleNums(roleNums);
		entity.setRoleOwners(roleOwners);
		entity.setCrown(Integer.toString(crown));
		entity.setLord(lord);
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
		doc.append("status", status);
		doc.append("curPlayer", curPlayer);
		doc.append("curRoleNum", curRoleNum);
		doc.append("crown", crown);
		doc.append("lord", lord);
		int i;
		List<Document> dod = new ArrayList<>();
		for (i=0;i<deck.size();i++) {
			dod.add(deck.get(i).toDocument());
		}
		List<Document> dor = new ArrayList<>();
		for (i=0;i<roles.size();i++) {
			dor.add(roles.get(i).toDocument());
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
		doc.append("roles", dor);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		firstFinished = doc.getBoolean("firstFinished", false);
		finishCount = doc.getInteger("finishCount", 0);
		coins = doc.getInteger("coins", coins);
		killedRole = doc.getInteger("killedRole", 0);
		stealedRole = doc.getInteger("stealedRole", 0);
		roundCount = doc.getInteger("roundCount", 0);
		phase = doc.getInteger("phase", 0);
		status = doc.getInteger("status", 0);
		curPlayer = doc.getInteger("curPlayer", -1);
		curRoleNum = doc.getInteger("curRoleNum", -1);
		crown = doc.getInteger("crown", -1);
		lord = doc.getString("lord");
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
		List<Document> dod = (List<Document>) doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<dod.size();i++) {
			Card c = CardFactory.createCard(dod.get(i));
			c.setBoard(this);
			deck.add(c);
		}
		List<Document> dor = (List<Document>) doc.get("roles");
		roles = new ArrayList<>();
		for (i=0;i<dor.size();i++) {
			Role r = RoleFactory.createRole(dor.get(i));
			r.setBoard(this);
			roles.add(r);
		}
		
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updateDeck() {
		List<Document> dod = new ArrayList<>();
		int i;
		for (i=0;i<deck.size();i++) {
			dod.add(deck.get(i).toDocument());
		}
		dbutil.update("id", id, "deck", dod);
	}
	
	public void updateRoles() {
		List<Document> dor = new ArrayList<>();
		int i;
		for (i=0;i<roles.size();i++) {
			dor.add(roles.get(i).toDocument());
		}
		dbutil.update("id", id, "roles", dor);
	}
	
	public void updatePlayers() {
		int i;
		for (i=0;i<players.size();i++) {
			updatePlayer(players.get(i).getName());
		}
	}
	
	public void updatePlayer(String name) {
		Player p = this.getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + name;
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updateCurPlayer() {
		String name = players.get(curPlayer).getName();
		Player p = players.get(curPlayer);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + name;
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void addPlayerToDB(String name) {
		Player p = this.getPlayerByName(name);
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
}
