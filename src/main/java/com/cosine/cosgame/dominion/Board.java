package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.dominion.base.Base;
import com.cosine.cosgame.dominion.base.Empty;
import com.cosine.cosgame.dominion.base.Estate;
import com.cosine.cosgame.dominion.dominion.Dominion;
import com.cosine.cosgame.dominion.intrigue.Intrigue;
import com.cosine.cosgame.dominion.oriental.Oriental;
import com.cosine.cosgame.dominion.seaside.Seaside;
import com.cosine.cosgame.dominion.Player;
import com.cosine.cosgame.util.MongoDBUtil;
import com.cosine.cosgame.util.TextGenerator;

public class Board {
	String boardId;
	String lord;
	int numPlayers;
	
	Expansion base;
	Expansion dominion;
	Expansion intrigue;
	Expansion seaside;
	Expansion prosperity;
	Expansion oriental;
	Expansion entertainments;
	Trash trash;
	List<Player> players;
	List<Pile> kindom;
	List<Pile> basePile;
	
	CardList cardList;
	
	int status;
	public static final int BEFORE = 0;
	public static final int FIRSTCARDS = 1;
	public static final int INGAME = 2;
	public static final int ENDGAME = 3;
	
	String endPlayer;
	String endType;
	
	int startPlayer;
	int currentPlayer;
	
	MongoDBUtil dbutil;
	
	Logger logger;
	
	public Board() {
		base = new Base();
		trash = new Trash();
		startPlayer = 0;
		currentPlayer = 0;
		endType = "";
		endPlayer = "";
		logger = new Logger();
		
		cardList = new CardList();
		
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
	public void createBoard(String lord, int numPlayers) {
		this.lord = lord;
		Date date= new Date();
		String id = Long.toString(date.getTime());
		initialize(id, numPlayers);
		status = BEFORE;
	}
	
	public void initialize(String boardId, int numPlayers) {
		this.boardId = boardId;
		this.numPlayers = numPlayers;
		cardList = new CardList(numPlayers);
		players = new ArrayList<Player>();
		Player lp = new Player(this.lord);
		players.add(lp);
		kindom = new ArrayList<Pile>();
		basePile = new ArrayList<Pile>();
		
		status = BEFORE;
	}
	
	public void ready(String name) {
		int i;
		int readyCount = 0;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(name)) {
				players.get(i).ready();
			}
			if (players.get(i).getIsReady()){
				readyCount = readyCount + 1;
			}
		}
		if (readyCount == numPlayers) {
			setup();
		}
	}
	
	public void setup() {
		baseSetup();
		kindomSetup();
		endType = "";
		endPlayer = "";
		boolean useShelters = false;
		boolean hasHeirloom = false;
		int i,j;
		for (i=0;i<kindom.size();i++) {
			kindom.get(i).getCards().get(0).setup();
		}
		
		int copperIndex = 5;
		int estateIndex = 4;
		
		// clean trash
		trash = new Trash();
		
		// clean logs
		logger = new Logger();
		
		// clean player cards in case
		for (i=0;i<players.size();i++) {
			players.get(i).cleanCards();
		}
		
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
		startPlayer = rand.nextInt(players.size());
		currentPlayer = startPlayer;
		for (i=0;i<players.size();i++) {
			players.get(i).setIsGoodToGo(false);
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
		currentPlayer = startPlayer;
		currentPlayer = currentPlayer-1;
		if (currentPlayer<0) {
			currentPlayer = currentPlayer + players.size();
		}
		for (i=0;i<players.size();i++) {
			players.get(i).cleanUp();
			players.get(i).setPhase(Player.OFFTURN);
			//updatePlayerDB(players.get(i).getName());
		}
		status = INGAME;
		nextPlayer();
	}
	
	public void nextPlayer() {
		players.get(currentPlayer).setPhase(Player.OFFTURN);
		players.get(currentPlayer).resetPlayed();
		players.get(currentPlayer).setAction(0);
		players.get(currentPlayer).setBuy(0);
		players.get(currentPlayer).setCoin(0);
		currentPlayer = (currentPlayer+1)%players.size();
		while (players.get(currentPlayer).getIsBot()) {
			players.get(currentPlayer).nextPhase();
			players.get(currentPlayer).goWithAI(this);
			players.get(currentPlayer).setPhase(Player.OFFTURN);
			updatePlayerDB(players.get(currentPlayer).getName());
			updateSupply();
			updateLogsDB();
			currentPlayer = (currentPlayer+1)%players.size();
		}
		players.get(currentPlayer).nextPhase();
	}
	
	public Player prevPlayer(Player p) {
		int index;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().equals(p.getName())) {
				index = i-1;
				if (index == -1) {
					index = index+players.size();
				}
				return players.get(index);
			}
		}
		return null;
	}
	
	public List<String> getCardnamesWithPrice(int price, int reducer){
		List<String> cns = new ArrayList<>();
		int i;
		for (i=0;i<basePile.size();i++) {
			if (basePile.get(i).getCards().size() == 0) {
				continue;
			}
			if (basePile.get(i).getTop().getPrice(reducer) == price) {
				cns.add(basePile.get(i).getTop().getName());
			}
		}
		for (i=0;i<kindom.size();i++) {
			if (kindom.get(i).getCards().size() == 0) {
				continue;
			}
			if (kindom.get(i).getTop().getPrice(reducer) == price) {
				cns.add(kindom.get(i).getTop().getName());
			}
		}
		return cns;
	}
	
	public Pile getPileByTop(String name) {
		int i;
		for (i=0;i<basePile.size();i++) {
			if (basePile.get(i).getCards().size() == 0) {
				continue;
			}
			if (basePile.get(i).getTop().getName().equals(name)) {
				return basePile.get(i);
			}
		}
		for (i=0;i<kindom.size();i++) {
			if (kindom.get(i).getCards().size() == 0) {
				continue;
			}
			if (kindom.get(i).getTop().getName().equals(name)) {
				return kindom.get(i);
			}
		}
		return null;
	}
	
	public void playerBuy(Player p, Pile pile) {
		Card card = pile.getTop();
		card.setBoard(this);
		p.buy = p.buy - 1;
		p.coin = p.coin - card.getPrice(p.getPriceReduce());
		if (pile.getEmbargo() > 0) {
			int i;
			for (i=0;i<pile.getEmbargo();i++) {
				if (getPileByTop("Curse") == null) {
					break;
				}
				logger.add(p.getName() + " gains a Curse (from Embargo token)", 1);
				gainToPlayerFromPile(p, getPileByTop("Curse"));
			}
		}
		card.onBuy(p);
		
	}
	
	public void gainToPlayer(Player p, Card card) {
		card.setBoard(this);
		p.putOnDiscard(card);
		card.onGain(p);
	}
	
	public void gainToPlayerFromPile(Player p, Pile pile) {
		Card card = pile.removeTop();
		card.setBoard(this);
		p.putOnDiscard(card);
		card.onGain(p);
	}
	
	public void gainToPlayerFromPileToHand(Player p, Pile pile) {
		Card card = pile.removeTop();
		card.setBoard(this);
		p.putOnHand(card);
		card.onGain(p);
	}
	
	public void gainToPlayerFromPileToTopdeck(Player p, Pile pile) {
		Card card = pile.removeTop();
		card.setBoard(this);
		p.deck.add(0, card);
		card.onGain(p);
	}
	
	public void gainToPlayerFromPileToSeclusion(Player p, Pile pile) {
		Card card = pile.removeTop();
		card.setBoard(this);
		p.getSeclusion().add(card);
		card.onGain(p);
	}
	
	public void trashFromPile(Player p, Pile pile) {
		Card card = pile.removeTop();
		card.setBoard(this);
		trash.add(card);
		card.onTrash(p);
	}
	
	public void resign(String name) {
		this.status = ENDGAME;
		this.endPlayer = name;
		this.endType = "resign";
		updateDB("status", status);
		updateDB("endType", endType);
		updateDB("endPlayer", endPlayer);
	}
	
	public void baseSetup() {
		base = new Base(players.size());
		basePile = base.getPiles();
	}
	
	public void kindomSetup() {
		// resize victory cards if numPlayers is 2
		if (numPlayers>2) return;
		for (int i=0;i<kindom.size();i++) {
			if (kindom.get(i).getTop().isVictory()) {
				if (kindom.get(i).getTop().isKinght()) {
					continue;
				}
				if (kindom.get(i).getTop().isCastle()) {
					
					continue;
				}
				kindom.get(i).resizeCards(8);
				
			}
		}
	}
	
	public void randomize() {
		kindom = cardList.genKindomPiles();
	}
	
	public String getBoardId() {
		return boardId;
	}
	
	public String getLord() {
		return lord;
	}
	
	public void resetStatus() {
		status = 0;
		for (int i=0;i<players.size();i++) {
			if (!players.get(i).getIsBot()) {
				players.get(i).setIsReady(false);
			}
		}
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getEndPlayer() {
		return endPlayer;
	}
	
	public String getEndType() {
		return endType;
	}
	
	public String getStatusAsString() {
		return Integer.toString(status);
	}
	
	public String getStatusString() {
		String ans = "";
		if (status == 0) ans = "Pending";
		if (status == 1) ans = "In Game";
		if (status == 2) ans = "In Game";
		if (status == 3) ans = "End Game";
		return ans;
	}
	
	public void setNumPlayers(int numPlayers) {
		this.numPlayers = numPlayers;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public String getPlayersInfo() {
		String ans = players.size() + "/" + numPlayers;
		return ans;
	}
	
	public String enterable() {
		if (players.size() < numPlayers) {
			return "1";
		} else {
			return "0";
		}
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
	
	public Trash getTrash() {
		return trash;
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
		return players.get(currentPlayer).getName();
	}
	
	public Logger getLogger() {
		return logger;
	}
	
	public CardList getCardList() {
		return cardList;
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
	
	public void attackHandle(Player player, String cardName) {
		CardFactory factory = new CardFactory();
		Card card = factory.createCard(cardName);
		for (int i=0;i<players.size();i++) {
			Player p = players.get(i);
			if (p.getName().equals(player.getName())){
				continue;
			} else {
				Buff buff = new Buff();
				buff.setBuffs(this, p);
				int x = buff.getBuff(Buff.UNAFFACTEDBYATTACK);
				if (x > 0) {
					
				} else if (p.hasAttackBlock()) {
					if (p.getIsBot()) {
						
					} else {
						Ask ask = new Ask();
						ask.setType(Ask.OPTION);
						String s = "";
						for (int j=0;i<p.getHand().size();i++) {
							if (p.getHand().get(j).isAttackBlock()) {
								s = p.getHand().get(j).getName();
							}
						}
						ask.setMsg("You may reveal " + s + " to block " + cardName);
						List<String> options = new ArrayList<String>();
						options.add("Reveal" + s);
						options.add("Don't reveal");
						p.setAsk(ask);
					}
				} else {
					if (p.getIsBot()) {
						p.setBoard(this);
						p.dealWithAttack(cardName);
					} else {
						card.setPlayer(p);
						card.setBoard(this);
						Ask ask = card.attack();
						p.setAsk(ask);
					}
				}
			}
		}
	}
	
	public void addPlayer(String playerName, boolean storeToDB) {
		if (numPlayers <= players.size()) {
			
		} else {
			Player p = new Player(playerName);
			players.add(p);
		}
		if (storeToDB) {
			List<Document> playerDocs = genPlayerNameDoc();
			updateDB("players", playerDocs);
			Document dob = genPlayerDoc(players.size()-1);
			updateDB(playerName,dob);
		}
	}
	
	public void addBot(boolean storeToDB) {
		if (numPlayers <= players.size()) {
			
		} else {
			TextGenerator gen = new TextGenerator();
			//gen.readName();
			int i;
			String botName = "";
			boolean flag = true;
			while (flag) {
				botName = gen.generateNameEasy() + "(bot)";
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
	
	public int getNumEmptyPile() {
		int emptyPile = 0;
		int i;
		for (i=0;i<basePile.size();i++) {
			if (basePile.get(i).getNumCards() == 0) emptyPile = emptyPile+1;
		}
		for (i=0;i<kindom.size();i++) {
			if (kindom.get(i).getNumCards() == 0) emptyPile = emptyPile+1;
		}
		return emptyPile;
	}
	
	public void gameEndJudge() {
		int emptyPile = getNumEmptyPile();
		int provinceIndex = 0;
		int i;
		if (basePile.get(provinceIndex).getNumCards() == 0 || emptyPile > 2) {
			this.status = ENDGAME;
			setWinner();
			updateDB("status", status);
			updateDB("endType", endType);
			updateDB("endPlayer", endPlayer);
			return;
		}
		
	}
	
	public void setWinner() {
		int i, score, index;
		int highest = -10000;
		endType = "win";
		for (i=0;i<players.size();i++) {
			index = (i+startPlayer)%players.size();
			score = players.get(index).getScore();
			if (score >= highest) {
				highest = score;
				endPlayer = players.get(index).getName();
			}
		}
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("boardId", boardId, key, value);
	}
	
	public void updatePlayerDB(String name) {
		updateDB(name, genPlayerDoc(name));
	}
	
	public void updatePlayersDB() {
		for (int i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			updatePlayerDB(name);
		}
	}
	
	public void updateLogsDB() {
		updateDB("logs", logger.getLoggerAsDocument());
	}
	
	public void updateSupply() {
		updateDB("base", genBaseDocs());
		updateDB("kindom", genKindomDocs());
		updateDB("trash", trash.toDocument());
	}
	
	public void cleanPlayerDBs() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).cleanCards();
			updatePlayerDB(players.get(i).getName());
		}
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
		endType = (String)doc.get("endType");
		endPlayer = (String)doc.get("endPlayer");
		
		List<Document> trashDocs = (List<Document>)doc.get("trash");
		trash.setTrashFromDoc(trashDocs);
		
		Document selectedDoc = (Document)doc.get("cardList");
		cardList.setSelectedFromDoc(selectedDoc);
		
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
			int embargo = (int)kindomDocs.get(i).get("embargo");
			p.setName(name);
			p.setIsSupply(isSupply);
			p.setIsMixed(isMixed);
			p.setIsSplit(isSplit);
			p.setImage(image);
			p.setEmbargo(embargo);
			
			if (isMixed || isSplit) {
				
			} else {
				int n = (int)((Document)kindomDocs.get(i).get("pile")).get("number");
				String cardname = (String)((Document)kindomDocs.get(i).get("pile")).get("name");
				if (n == 0) {
					p.setCards(factory.createCards("Empty", 1));
				} else {
					p.setCards(factory.createCards(cardname, n));
				}
			}
			kindom.add(p);
		}
		for (i=0;i<baseDocs.size();i++) {
			Pile p = new Pile();
			String name = (String)baseDocs.get(i).get("name");
			int n = (int)baseDocs.get(i).get("number");
			String image = (String)baseDocs.get(i).get("image");
			int embargo = (int)baseDocs.get(i).get("embargo");
			p.setName(name);
			if (n == 0) {
				p.setCards(factory.createCards("Empty", 1));
			} else {
				p.setCards(factory.createCards(name, n));
			}
			p.setImage(image);
			p.setEmbargo(embargo);
			basePile.add(p);
		}
		for (i=0;i<playerDocs.size();i++) {
			Player p = new Player();
			p.setBoard(this);
			String name = (String) playerDocs.get(i).get("name");
			p.setName(name);
			Document dop = (Document)doc.get(name);
			p.setIsReady((boolean)dop.getBoolean("isready"));
			p.setIsBot((boolean)dop.getBoolean("isbot"));
			p.setIsGoodToGo((boolean)dop.getBoolean("isgoodtogo"));
			p.setPhase((int)dop.get("phase"));
			p.setAction((int)dop.get("action"));
			p.setCoin((int)dop.get("coin"));
			p.setBuy((int)dop.get("buy"));
			p.setCoffer((int)dop.get("coffer"));
			p.setVillager((int)dop.get("villager"));
			p.setVp((int)dop.get("vp"));
			p.setMemorial((int)dop.get("memorial"));
			p.setNumActionsPlayed((int)dop.getInteger("numActionsPlayed"));
			p.setPriceReduce((int)dop.getInteger("priceReduce"));
			p.resetGained();
			p.setGainedFromDoc((List<Document>)dop.get("gained"));
			p.setBoughtFromDoc((List<Document>)dop.get("bought"));
			
			Ask ask = new Ask();
			ask.setAskFromDocument((Document) dop.get("ask"));
			p.setAsk(ask);
			
			List<Document> startAsksDocs = (List<Document>)dop.get("startAsks");
			List<Document> discardDocs = (List<Document>)dop.get("discard");
			List<Document> deckDocs = (List<Document>)dop.get("deck");
			List<Document> handDocs = (List<Document>)dop.get("hand");
			List<Document> playDocs = (List<Document>)dop.get("play");
			List<Document> revealedDocs = (List<Document>)dop.get("revealed");
			List<Document> seclusionDocs = (List<Document>)dop.get("seclusion");
			List<Document> islandDocs = (List<Document>)dop.get("island");
			List<Document> nativeVillageDocs = (List<Document>)dop.get("nativeVillage");
			List<Document> havenDocs = (List<Document>)dop.get("haven");
			List<Document> playedDocs = (List<Document>)dop.get("played");
			List<Ask> startAsks = new ArrayList<Ask>();
			List<Card> discard = new ArrayList<Card>();
			List<Card> deck = new ArrayList<Card>();
			List<Card> hand = new ArrayList<Card>();
			List<Card> play = new ArrayList<Card>();
			List<Card> revealed = new ArrayList<Card>();
			List<Card> seclusion = new ArrayList<Card>();
			List<Card> island = new ArrayList<Card>();
			List<Card> nativeVillage = new ArrayList<Card>();
			List<Card> haven = new ArrayList<Card>();
			List<String> played = new ArrayList<String>();
		
			int j;
			for (j=0;j<startAsksDocs.size();j++) {
				Ask a = new Ask();
				a.setAskFromDocument((Document)startAsksDocs.get(j).get("ask"));
				startAsks.add(a);
			}
			for (j=0;j<discardDocs.size();j++) {discard.add(factory.createCard((String)discardDocs.get(j).get("name")));}
			for (j=0;j<deckDocs.size();j++) {deck.add(factory.createCard((String)deckDocs.get(j).get("name")));}
			for (j=0;j<handDocs.size();j++) {hand.add(factory.createCard((String)handDocs.get(j).get("name")));}
			for (j=0;j<playDocs.size();j++) {
				Card c = factory.createCard((String)playDocs.get(j).get("name"));
				if (c.isDuration()) {
					c.setNumTurns((int)playDocs.get(j).get("numTurns"));
					if (playDocs.get(j).get("under") != null) {
						List<Document> underDocs = (List<Document>)playDocs.get(j).get("under");
						List<String> under = new ArrayList<>();
						for (int ii=0;ii<underDocs.size();ii++) {
							under.add(underDocs.get(ii).getString("c"));
						}
						c.setUnder(under);
					}
				}
				play.add(c);
			}
			for (j=0;j<revealedDocs.size();j++) {revealed.add(factory.createCard((String)revealedDocs.get(j).get("name")));}
			for (j=0;j<seclusionDocs.size();j++) {seclusion.add(factory.createCard((String)seclusionDocs.get(j).get("name")));}
			for (j=0;j<islandDocs.size();j++) {island.add(factory.createCard((String)islandDocs.get(j).get("name")));}
			for (j=0;j<nativeVillageDocs.size();j++) {nativeVillage.add(factory.createCard((String)nativeVillageDocs.get(j).get("name")));}
			for (j=0;j<havenDocs.size();j++) {haven.add(factory.createCard((String)havenDocs.get(j).get("name")));}
			for (j=0;j<playedDocs.size();j++) {played.add((String)playedDocs.get(j).get("value"));}
			
			p.setStartAsks(startAsks);
			p.setDiscard(discard);
			p.setDeck(deck);
			p.setHand(hand);
			p.setPlay(play);
			p.setRevealed(revealed);
			p.setSeclusion(seclusion);
			p.setIsland(island);
			p.setNativeVillage(nativeVillage);
			p.setHaven(haven);
			p.setPlayedCounter(played);
			players.add(p);
			
		}
		List<Document> logDocs = (List<Document>)doc.get("logs");
		logger.setLoggerFromDocument(logDocs);
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
		dop.append("isready", players.get(i).getIsReady());
		dop.append("isgoodtogo", players.get(i).getIsGoodToGo());
		dop.append("isbot", players.get(i).getIsBot());
		dop.append("phase", players.get(i).getPhase());
		dop.append("action", players.get(i).getAction());
		dop.append("coin", players.get(i).getCoin());
		dop.append("buy", players.get(i).getBuy());
		dop.append("coffer", players.get(i).getCoffer());
		dop.append("villager", players.get(i).getVillager());
		dop.append("vp", players.get(i).getVp());
		dop.append("memorial", players.get(i).getMemorial());
		dop.append("ask", players.get(i).getAsk().toDocument());
		dop.append("numActionsPlayed", players.get(i).getNumActionsPlayed());
		dop.append("priceReduce", players.get(i).getPriceReduce());
		dop.append("gained", players.get(i).getGained().toDocument());
		dop.append("bought", players.get(i).getGained().toDocumentBuy());
		
		int j;
		List<Document> startAsksDocs = new ArrayList<Document>();
		List<Document> discardDocs = new ArrayList<Document>();
		List<Document> deckDocs = new ArrayList<Document>();
		List<Document> handDocs = new ArrayList<Document>();
		List<Document> playDocs = new ArrayList<Document>();
		List<Document> revealedDocs = new ArrayList<Document>();
		List<Document> seclusionDocs = new ArrayList<Document>();
		List<Document> islandDocs = new ArrayList<Document>();
		List<Document> nativeVillageDocs = new ArrayList<Document>();
		List<Document> havenDocs = new ArrayList<Document>();
		List<Document> playedDocs = new ArrayList<Document>();
		
		for (j=0;j<players.get(i).getStartAsks().size();j++) {
			Document d = new Document();
			d.append("ask", players.get(i).getStartAsks().get(j).toDocument());
			startAsksDocs.add(d);
		}
		
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
			if (players.get(i).getPlay().get(j).isDuration()) {
				d.append("numTurns", players.get(i).getPlay().get(j).getNumTurns());
				if (players.get(i).getPlay().get(j).getUnder().size() > 0) {
					int ii;
					List<Document> underDoc = new ArrayList<>();
					for (ii=0;ii<players.get(i).getPlay().get(j).getUnder().size();ii++) {
						Document dd = new Document();
						dd.append("c", players.get(i).getPlay().get(j).getUnder().get(ii));
						underDoc.add(dd);
					}
					d.append("under", underDoc);
				}
			}
			playDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getRevealed().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getRevealed().get(j).getName());
			revealedDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getSeclusion().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getSeclusion().get(j).getName());
			seclusionDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getIsland().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getIsland().get(j).getName());
			islandDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getNativeVillage().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getNativeVillage().get(j).getName());
			nativeVillageDocs.add(d);
		}

		for (j=0;j<players.get(i).getHaven().size();j++) {
			Document d = new Document();
			d.append("name", players.get(i).getHaven().get(j).getName());
			havenDocs.add(d);
		}
		
		for (j=0;j<players.get(i).getPlayedList().size();j++) {
			Document d = new Document();
			d.append("value", players.get(i).getPlayedList().get(j));
			playedDocs.add(d);
		}
		dop.append("startAsks", startAsksDocs);
		dop.append("discard", discardDocs);
		dop.append("deck", deckDocs);
		dop.append("hand", handDocs);
		dop.append("play", playDocs);
		dop.append("played", playedDocs);
		dop.append("revealed", revealedDocs);
		dop.append("seclusion", seclusionDocs);
		dop.append("island", islandDocs);
		dop.append("nativeVillage", nativeVillageDocs);
		dop.append("haven", havenDocs);
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
			dop.append("embargo", basePile.get(i).getEmbargo());
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
			dop.append("embargo", kindom.get(i).getEmbargo());
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
		doc.append("endType", endType);
		doc.append("endPlayer", endPlayer);
		doc.append("cardList", cardList.getSelectedDoc());
		
		List<Document> dopn = genPlayerNameDoc();
		doc.append("players", dopn);
		
		List<Document> baseDocs = genBaseDocs();
		List<Document> kindomDocs = genKindomDocs();
		doc.append("base", baseDocs);
		doc.append("kindom", kindomDocs);
		
		List<Document> trashDocs = trash.toDocument();
		doc.append("trash", trashDocs);
		
		int i;
		for (i=0;i<players.size();i++) {
			Document dop = genPlayerDoc(i);
			doc.append(players.get(i).getName(), dop);
		}
		List<Document> logDocs = logger.getLoggerAsDocument();
		doc.append("logs", logDocs);
		dbutil.insert(doc);
		System.out.println("Board with id " + boardId + " is stored in db");
	}
}
