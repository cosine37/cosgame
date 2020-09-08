package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;

import org.bson.Document;

import com.cosine.cosgame.citadels.specialcards.*;
import com.cosine.cosgame.citadels.sc2016.*;
import com.cosine.cosgame.citadels.scdarkcity.*;
import com.cosine.cosgame.citadels.sckx.*;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	AllRes allRes;
	List<Player> players;
	List<Card> deck;
	List<Role> roles;
	List<Integer> trackCrownPlayers;
	List<String> tempRevealedTop;
	String lord;
	boolean firstFinished;
	boolean crownMoved;
	boolean regicide;
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
	int no9;
	String id;
	MongoDBUtil dbutil;
	Logger logger;
	
	public Board() {
		allRes = new AllRes();
		allRes.setBoard(this);
		players = new ArrayList<>();
		deck = new ArrayList<>();
		roles = new ArrayList<>();
		firstFinished = true;
		finishCount = 7;
		coins = 30;
		killedRole = -1;
		stealedRole = -1;
		no9 = -1;
		
		status = CitadelsConsts.PREGAME;
		
		String dbname = "citadels";
		String col = "board";
		
		logger = new Logger();
		
		trackCrownPlayers = new ArrayList<>();
		tempRevealedTop = new ArrayList<>();
		
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
		if (players.size() == 8) {
			if (no9 == -1) {
				no9 = 0;
			}
		}
	}
	
	public void genDeck() {
		deck = allRes.genDeck();
	}
	
	public void genRoles() {
		roles = allRes.genRoles(players.size());
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
		//TODO: test special cards addition here
		Card c = new QingfengPark();
		deck.add(0,c);
		c = new Villa();
		deck.add(0,c);
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
	
	public Player getPlayerByRoleImg(String img) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getRole().getImg().contentEquals(img)) {
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
			if (roles.size() == 9) {
				numReveal = 3;
			}
			numHidden = 1;
		} else if (numPlayers == 5) {
			numReveal = 1;
			if (roles.size() == 9) {
				numReveal = 2;
			}
			numHidden = 1;
		} else if (numPlayers == 6) {
			numReveal = 0;
			if (roles.size() == 9) {
				numReveal = 1;
			}
			numHidden = 1;
		} else if (numPlayers == 7) {
			numReveal = 0;
			numHidden = 1;
		} else if (numPlayers == 8) {
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
			int x;
			int index;
			while (true) {
				x = rand.nextInt(tempNum.size());
				index = tempNum.get(x);
				if (roles.get(index).getNum() != 4) {
					index = tempNum.remove(x);
					break;
				}
				index = tempNum.remove(x);
			}
			
			roles.get(index).setOwner(CitadelsConsts.NOTUSEDREVEALED);
			log("本回合没有" + Integer.toString(roles.get(index).getNum()) + " 号"+roles.get(index).getName()+"。");
		}
		for (i=0;i<numHidden;i++) {
			int x = rand.nextInt(tempNum.size());
			int index = tempNum.remove(x);
			roles.get(index).setOwner(CitadelsConsts.NOTUSEDHIDDEN);
		}
		
	}
	
	public void newRound() {
		status = CitadelsConsts.CHOOSEROLE;
		int i;
		for (i=0;i<roles.size();i++) {
			roles.get(i).setOwner(CitadelsConsts.SELECTABLE);
		}
		for (i=0;i<players.size();i++) {
			players.get(i).setRole(null);
			players.get(i).setRoleNum(-1);
		}
		roundCount = roundCount+1;
		log("回合 " + Integer.toString(roundCount)+" 开始");
		discardRoles();
		killedRole = -1;
		stealedRole = -1;
		curPlayer = crown;
		players.get(crown).setPhase(CitadelsConsts.CHOOSEROLE);
	}
	
	public void nextPlayerChooseRole() {
		curPlayer = curPlayer + 1;
		if (curPlayer >= players.size()) {
			curPlayer = 0;
		}
		int numPlayers = players.size();
		if (numPlayers+1 == roles.size()) {
			int selectableCount = 0;
			for (int i=0;i<roles.size();i++) {
				if (roles.get(i).getOwner() == CitadelsConsts.SELECTABLE) {
					selectableCount++;
				}
			}
			if (selectableCount == 1) {
				for (int i=0;i<roles.size();i++) {
					if (roles.get(i).getOwner() == CitadelsConsts.NOTUSEDHIDDEN) {
						roles.get(i).setOwner(CitadelsConsts.SELECTABLE);
					}
				}
			}
		}
		players.get(curPlayer).setPhase(CitadelsConsts.CHOOSEROLE);
		updateStatus();
	}
	
	public void nextRole() {
		tempRevealedTop = new ArrayList<>();
		curRoleNum = curRoleNum+1;
		if (curRoleNum>roles.size()) {
			log("所有角色行动完毕。");
			updateStatus();
		}
		int i;
		for (i=0;i<roles.size();i++) {
			Role r = roles.get(i);
			if (r.getNum() == curRoleNum) {
				log("轮到选择"+Integer.toString(curRoleNum)+"号 "+r.getName()+"的玩家行动了。");
				if (curRoleNum == killedRole) {
					log(Integer.toString(curRoleNum)+"号 "+r.getName()+"被1号 送葬者带走了。");
					if (r.getOwner() < 0) {
						log("然而，没有玩家选择"+Integer.toString(curRoleNum)+"号 "+r.getName()+"，这就尴尬了。");
					} else {
						String playerName = players.get(r.getOwner()).getName();
						log(playerName + "失去所有角色技能并跳过回合。");
						if (r.getImg().contentEquals("004")) {
							Player assassinPlayer = this.getPlayerByRole(1);
							if (assassinPlayer.getRole().getImg().contentEquals("001") && regicide) {
								int x = this.getPlayerIndex(assassinPlayer);
								this.moveCrownTo(x);
							}
						}
					}
					nextRole();
					return;
				} else {
					if (curRoleNum == stealedRole) {
						log(Integer.toString(curRoleNum)+"号 "+r.getName()+"被2号 盗贼盯上了。");
					}
					if (r.getOwner() < 0) { // Not selected
						if (curRoleNum == stealedRole) {
							log("然而，没有玩家选择"+Integer.toString(curRoleNum)+"号 "+r.getName()+"，这就尴尬了。");
						} else {
							log("没有玩家选择"+Integer.toString(curRoleNum)+"号 "+r.getName()+"。");
						}
						
						nextRole();
						return;
					} else {
						curPlayer = r.getOwner();
						String playerName = players.get(curPlayer).getName();
						log(playerName + "开始回合。");
						players.get(curPlayer).getRole().whenReveal();
						players.get(curPlayer).setPhase(CitadelsConsts.TAKEACTION);
						break;
					}
				}
				
			}
		}
	}
	
	public void moveCrownTo(int x) {
		trackCrownPlayers = new ArrayList<>();
		if (crown == x) {
			crownMoved = false;
		} else {
			crown = x;
			log(players.get(x).getName() + "获得了市长标记。");
			int i,j;
			boolean flag;
			for (i=0;i<players.size();i++) {
				flag = false;
				for (j=0;j<players.get(i).getBuilt().size();j++) {
					if (players.get(i).getBuilt().get(j).isTrackCrown()) {
						flag = true;
						players.get(i).getBuilt().get(j).crownMovement();
					}
				}
				if (flag) {
					trackCrownPlayers.add(i);
				}
			}
			crownMoved = true;
		}
	}
	
	public void updateStatus() {
		if (status == CitadelsConsts.CHOOSEROLE) {
			int i;
			int count = 0;
			for (i=0;i<roles.size();i++) {
				if (roles.get(i).getOwner() >= 0) {
					count++;
				}
			}
			if (count == players.size()) {
				status = CitadelsConsts.TAKETURNS;
				log("所有玩家都选择了角色。");
				curRoleNum = 0;
				nextRole();
			}
		} else if (status == CitadelsConsts.TAKETURNS) {
			if (curRoleNum>roles.size()) {
				log("回合 " + Integer.toString(roundCount) + " 结束。");
				if (killedRole == 4 && regicide == false) {
					Player p = this.getPlayerByRoleImg("004");
					if (p != null) {
						int x = this.getPlayerIndex(p);
						this.moveCrownTo(x);
					}
				}
				if (firstFinished) {
					newRound();
				} else {
					log("游戏结束.");
					status = CitadelsConsts.ENDGAME;
				}
				
			}
		}
	}
	
	public void botChooseRole() {
		Player p = players.get(curPlayer);
		if (p.isBot() && p.getPhase() == CitadelsConsts.CHOOSEROLE) {
			int i;
			List<Integer> choices = new ArrayList<>();
			for (i=0;i<roles.size();i++) {
				choices.add(i);
			}
			int x;
			p.setRoleNum(-1);
			while (p.getRoleNum() == -1) {
				Random rand = new Random();
				x = choices.remove(rand.nextInt(choices.size()));
				if (roles.get(x).getOwner() == CitadelsConsts.SELECTABLE) {
					p.chooseRole(x);
					break;
				}
			}
		}
	}
	
	public void botNextMove() {
		Player p = players.get(curPlayer);
		if (p.isBot() && p.getPhase() != CitadelsConsts.OFFTURN && status == CitadelsConsts.TAKETURNS) {
			p.botNextMove();
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
		if (players.size() == 8) {
			if (no9 == -1) {
				no9 = 0;
			}
		}
		addPlayerToDB(botName);
	}
	public boolean isLord(String name) {
		return name.contentEquals(this.lord);
	}
	public void log(String s) {
		if (status == CitadelsConsts.CHOOSEROLE || status == CitadelsConsts.TAKETURNS) {
			logger.log(s);
		}
		
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
	public Logger getLogger() {
		return logger;
	}
	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	public boolean isCrownMoved() {
		return crownMoved;
	}
	public void setCrownMoved(boolean crownMoved) {
		this.crownMoved = crownMoved;
	}
	public boolean isRegicide() {
		return regicide;
	}
	public void setRegicide(boolean regicide) {
		this.regicide = regicide;
	}
	public List<String> getTempRevealedTop() {
		return tempRevealedTop;
	}
	public void setTempRevealedTop(List<String> tempRevealedTop) {
		this.tempRevealedTop = tempRevealedTop;
	}
	public int getNo9() {
		return no9;
	}
	public void setNo9(int no9) {
		this.no9 = no9;
	}

	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		
		if (id.contentEquals("NE")) {
			entity.setId(id);
			return entity;
		}
		int i,j;
		Player p = this.getPlayerByName(name);
		int playerIndex = this.getPlayerIndex(name);
		List<String> playerNames = new ArrayList<>();
		List<String> coins = new ArrayList<>();
		List<String> handSizes = new ArrayList<>();
		List<List<String>> built = new ArrayList<>();
		List<List<String>> beautifyLevel = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playerNames.add(players.get(i).getName());
			coins.add(Integer.toString(players.get(i).getCoin()));
			handSizes.add(Integer.toString(players.get(i).getHand().size()));
			List<String> singleBuilt = new ArrayList<>();
			List<String> singleBeautifyLevel = new ArrayList<>();
			for (j=0;j<players.get(i).getBuilt().size();j++) {
				singleBuilt.add(players.get(i).getBuilt().get(j).getImg());
				singleBeautifyLevel.add(Integer.toString(players.get(i).getBuilt().get(j).getBeautifyLevel()));
			}
			built.add(singleBuilt);
			beautifyLevel.add(singleBeautifyLevel);
		}
		List<String> hand = new ArrayList<>();
		List<String> buildable = new ArrayList<>();
		List<String> revealedCards = new ArrayList<>();
		
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
		List<String> roleImgs = new ArrayList<>();
		for (i=0;i<roles.size();i++) {
			roleNums.add(Integer.toString(roles.get(i).getNum()));
			if (roles.get(i).getOwner() == CitadelsConsts.SELECTABLE) {
				roleOwners.add("-1");
			} else if (roles.get(i).getOwner() == CitadelsConsts.NOTUSEDREVEALED) {
				roleOwners.add("-2");
			} else {
				roleOwners.add("-3");
			}
			roleImgs.add(roles.get(i).getImg());
		}
		String isLord = "n";
		if (name.contentEquals(lord)) {
			isLord = "y";
		}
		String lastRound = "y";
		if (firstFinished) {
			lastRound = "n";
		}
		List<String> roleRevealed = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			if (this.status == CitadelsConsts.TAKETURNS) {
				if (players.get(i).getRoleNum() <= curRoleNum) {
					roleRevealed.add(Integer.toString(players.get(i).getRoleNum()));
				} else {
					roleRevealed.add("-1");
				}
			} else {
				roleRevealed.add("-1");
			}
		}
		List<String> skillButtons = new ArrayList<>();
		if (p.getRole() != null) {
			skillButtons = p.getRole().getButtonNames();
		}
		List<String> askLs = new ArrayList<>();
		List<List<String>> askBuiltInfo = new ArrayList<>();
		if (p.getAsk() != null) {
			askLs = p.getAsk().getLs();
			askBuiltInfo = p.getAsk().getBuiltInfo();
		}
		List<String> scores = new ArrayList<>();
		List<String> netScores = new ArrayList<>();
		List<String> extraScores = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			int score = players.get(i).calcScore();
			scores.add(Integer.toString(score));
			int netScore = players.get(i).netScore();
			netScores.add(Integer.toString(netScore));
			int extraScore = players.get(i).extraScore();
			extraScores.add(Integer.toString(extraScore));
		}
		String yourRole;
		if (p.getRole() == null) {
			yourRole = "未选择";
		} else if (this.status == CitadelsConsts.CHOOSEROLE) {
			if (this.crown < playerIndex && curPlayer > playerIndex) {
				yourRole = Integer.toString(p.getRole().getNum()) + "号 " + p.getRole().getName();
			} else if (this.crown > playerIndex && curPlayer > playerIndex && this.crown > curPlayer) {
				yourRole = Integer.toString(p.getRole().getNum()) + "号 " + p.getRole().getName();
			} else if (this.crown < playerIndex && curPlayer < playerIndex && this.crown > curPlayer) {
				yourRole = Integer.toString(p.getRole().getNum()) + "号 " + p.getRole().getName();
			} else if (this.crown == playerIndex && curPlayer != playerIndex){
				yourRole = Integer.toString(p.getRole().getNum()) + "号 " + p.getRole().getName();
			} else {
				yourRole = "未选择";
			}
		} else {
			yourRole = Integer.toString(p.getRole().getNum()) + "号 " + p.getRole().getName();
		}
		
		String chooseOrDiscard;
		if (p.getNumChoose() == 2 && p.getNumReveal() ==3) {
			chooseOrDiscard = "discard";
		} else {
			chooseOrDiscard = "choose";
		}
		String regicide;
		if (this.regicide) {
			regicide = "y";
		} else {
			regicide = "n";
		}
		List<List<String>> specialHands = new ArrayList<>();
		if (this.status == CitadelsConsts.ENDGAME) {
			for (i=0;i<players.size();i++) {
				List<String> singleSpecialHand = new ArrayList<>();
				for (j=0;j<players.get(i).getHand().size();j++) {
					Card c = players.get(i).getHand().get(j);
					if (c.getSecretScore() != 0) {
						singleSpecialHand.add(c.getImg());
					}
				}
				specialHands.add(singleSpecialHand);
			}
		}
			
		entity.setDeckSize(Integer.toString(this.deck.size()));
		entity.setBank(Integer.toString(this.coins));
		entity.setPlayerNames(playerNames);
		entity.setBuilt(built);
		entity.setBeautifyLevel(beautifyLevel);
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
		entity.setRoleImgs(roleImgs);
		entity.setCrown(Integer.toString(crown));
		entity.setLord(lord);
		entity.setIsLord(isLord);
		entity.setId(id);
		entity.setLastRound(lastRound);
		entity.setRoleRevealed(roleRevealed);
		entity.setLogs(logger.getLogs());
		entity.setAskId(Integer.toString(p.getAsk().getAskId()));
		entity.setAskType(Integer.toString(p.getAsk().getAskType()));
		entity.setAskBuiltIndex(Integer.toString(p.getAsk().getAskBuiltIndex()));
		entity.setAskLimit(Integer.toString(p.getAsk().getUpperLimit()));
		entity.setSkillButtons(skillButtons);
		entity.setAskMsg(p.getAsk().getMsg());
		entity.setAskLs(askLs);
		entity.setAskBuiltInfo(askBuiltInfo);
		entity.setCanUseRoleSkill(p.getCanUseRoleSkill());
		entity.setCanUseCardSkill(p.getCanUseCardSkill());
		entity.setKilledRole(Integer.toString(killedRole));
		entity.setStealedRole(Integer.toString(stealedRole));
		entity.setScores(scores);
		entity.setNetScores(netScores);
		entity.setExtraScores(extraScores);
		entity.setYourRole(yourRole);
		entity.setChooseOrDiscard(chooseOrDiscard);
		entity.setFinishCount(Integer.toString(finishCount));
		entity.setRegicide(regicide);
		entity.setTempRevealedTop(tempRevealedTop);
		entity.setSpecialHands(specialHands);
		entity.setNo9(Integer.toString(no9));
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
		doc.append("regicide", regicide);
		doc.append("tempRevealedTop", tempRevealedTop);
		doc.append("no9", no9);
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
		doc.append("logs", logger.getLogs());
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
		regicide = doc.getBoolean("regicide", false);
		no9 = doc.getInteger("no9", -1);
		tempRevealedTop = (List<String>) doc.get("tempRevealedTop");
		int i;
		List<Document> dor = (List<Document>) doc.get("roles");
		roles = new ArrayList<>();
		for (i=0;i<dor.size();i++) {
			Role r = RoleFactory.createRole(dor.get(i));
			r.setBoard(this);
			roles.add(r);
		}
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
		logger.setLogs((List<String>) doc.get("logs"));
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updateLogs() {
		dbutil.update("id", id, "logs", logger.getLogs());
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
	
	public void updatePlayer(int index) {
		if (index < players.size() && index>=0) {
			Player p = players.get(index);
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updateThief() {
		Player p = this.getPlayerByRole(2);
		if (p != null) {
			String name = p.getName();
			Document dop = p.toDocument();
			String playerName = "player-" + name;
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updateTrackCrownPlayers() {
		for (int i=0;i<trackCrownPlayers.size();i++) {
			updatePlayer(i);
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
	
	public void removePlayerFromDB(int index) {
		String playerName = "player-" + players.get(index).getName();
		players.remove(index);
		dbutil.removeKey("id", id, playerName);
		List<String> playerNames = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			playerName = players.get(i).getName();
			playerNames.add(players.get(i).getName());
		}
		dbutil.update("id", id, "playerNames", playerNames);
	}
	
	public void removePlayerFromDB(String name) {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				removePlayerFromDB(i);
				break;
			}
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
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
}
