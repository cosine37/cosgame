package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Player {
	String name;
	Board board;
	Role role;
	List<Card> hand;
	List<Card> built;
	List<Card> forChoose;
	int numReveal;
	int numChoose;
	int coin;
	boolean firstFinished;
	boolean finished;
	boolean allColors;
	boolean killed;
	int roleNum;
	int phase;
	int buildLimit;
	int numBuilt;
	int identicalLimit;
	boolean bot;
	
	public Player(String name) {
		this.name = name;
		hand = new ArrayList<>();
		built = new ArrayList<>();
		forChoose = new ArrayList<>();
		coin = 0;
		firstFinished = false;
		finished = false;
		killed = false;
		numReveal = 2;
		numChoose = 1;
		buildLimit = 1;
		identicalLimit = 0;
		bot = false;
		phase = CitadelsConsts.OFFTURN;
	}
	
	public void chooseRole(int index) {
		if (board.getRoles().get(index).getOwner() == CitadelsConsts.SELECTABLE) {
			role = board.getRoles().get(index);
			roleNum = role.getNum();
			role.setOwner(board.getPlayerIndex(this));
			board.log(name + " chooses a role.");
		}
		phase = CitadelsConsts.OFFTURN;
		board.nextPlayerChooseRole();
		
	}
	
	public void startTurn() {
		if (phase == CitadelsConsts.OFFTURN) {
			phase = CitadelsConsts.TAKEACTION;
			board.log(name + " starts the turn.");
		} else {
			// for debug purposes
			//phase = CitadelsConsts.TAKEACTION;
		}
	}
	
	public void endTurn() {
		if (phase == CitadelsConsts.BUILDDISTRICT) {
			phase = CitadelsConsts.OFFTURN;
			board.log(name + " finishes the turn.");
			board.nextRole();
		}
	}
	
	public void takeActionOption(int option) {
		if (phase == CitadelsConsts.TAKEACTION) {
			if (option == 1) {
				if (numReveal == numChoose) {
					draw(numChoose);
					board.log(name + " draws " + Integer.toString(numChoose) + " cards.");
					startBuildPhase();
				} else {
					forChoose = board.firstCards(numReveal);
					board.log(name + " looks at " + Integer.toString(numChoose) + " cards.");
					phase = CitadelsConsts.CHOOSECARD;
				}
			} else if (option == 2) {
				addCoin(2);
				board.log(name + " takes 2 coins.");
				startBuildPhase();
			} else {
				
			}
		}
	}
	
	public void chooseCard(int index) {
		if (numChoose == 1) {
			hand.add(forChoose.remove(index));
			board.bottomDeck(forChoose);
			board.log(name + " chooses a card.");
			startBuildPhase();
		}
		
	}
	
	public void startBuildPhase(){
		phase = CitadelsConsts.BUILDDISTRICT;
		numBuilt = 0;
	}
	
	public void addCoin(int n) {
		int c = board.takeCoins(n);
		coin = coin+c;
	}
	
	public void draw(int x) {
		int i;
		for (i=0;i<x;i++) {
			if (board.getDeck().size() > 0) {
				hand.add(board.getDeck().remove(0));
			} else {
				break;
			}
		}
	}
	
	public void addToHand(Card c) {
		hand.add(c);
	}
	
	public void exchangeHand(Player p) {
		List<Card> temp = p.hand;
		p.hand = hand;
		hand = temp;
	}
	
	public boolean canBuild(int x) {
		if (hand.size()>x) {
			if (numBuilt >= buildLimit) {
				return false;
			}
			Card c = hand.get(x);
			int cost = c.getCost();
			if (cost > coin) {
				return false;
			}
			int identicalCount = 0;
			int i;
			for (i=0;i<built.size();i++) {
				if (built.get(i).getImg().contentEquals(c.getImg())) {
					identicalCount++;
				}
			}
			if (identicalCount > identicalLimit) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public void build(int x) {
		if (hand.size() > x) {
			Card c = hand.get(x);
			int cost = c.getCost();
			if (canBuild(x)) {
				board.log(name + " builds " + c.getName());
				numBuilt++;
				hand.remove(x);
				built.add(c);
				coin = coin-cost;
				board.addCoin(cost);
				if (built.size() == board.getFinishCount()) {
					finished = true;
					if (board.isFirstFinished()) {
						firstFinished = true;
						board.setFirstFinished(false);
						board.log(name + " finishes the city. Game will end after this round");
					}
				}
			}
		}
	}
	
	public void botNextMove() { // for bot to take action & build
		if (phase == CitadelsConsts.TAKEACTION) {
			if (hand.size() > 1) {
				takeActionOption(2);
			} else {
				takeActionOption(1);
			}
		} else if (phase == CitadelsConsts.CHOOSECARD) {
			chooseCard(0);
		} else if (phase == CitadelsConsts.BUILDDISTRICT) {
			int i;
			for (i=0;i<hand.size();i++) {
				if (canBuild(i)) {
					build(i);
					break;
				}
			}
			endTurn();
		}
	}
	
	public int missingColor() {
		int[] colors = {0,0,0,0,0};
		for (int i=0;i<built.size();i++) {
			int cc = built.get(i).getColor();
			colors[cc]++;
		}
		int ans = -2;
		for (int i=0;i<5;i++) {
			if (colors[i] == 0) {
				if (ans == -2) {
					ans = i;
				} else {
					ans = -1;
					break;
				}
			}
		}
		return ans;
	}
	
	public int calcScore() {
		int ans = 0;
		int i;
		for (i=0;i<built.size();i++) {
			ans = ans + built.get(i).getScore();
		}
		if (finished) {
			if (firstFinished) {
				ans = ans+4;
			} else {
				ans = ans+2;
			}
		}
		int[] colors = {0,0,0,0,0};
		for (i=0;i<built.size();i++) {
			int cc = built.get(i).getColorForScore();
			colors[cc]++;
		}
		allColors = true;
		for (i=0;i<5;i++) {
			if (colors[i] == 0) {
				allColors = false;
				break;
			}
		}
		if (allColors) {
			ans = ans+3;
		}
		return ans;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getBuilt() {
		return built;
	}
	public void setBuilt(List<Card> built) {
		this.built = built;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isFirstFinished() {
		return firstFinished;
	}
	public void setFirstFinished(boolean firstFinished) {
		this.firstFinished = firstFinished;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public boolean isAllColors() {
		return allColors;
	}
	public void setAllColors(boolean allColors) {
		this.allColors = allColors;
	}
	public boolean isKilled() {
		return killed;
	}
	public void setKilled(boolean killed) {
		this.killed = killed;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getRoleNum() {
		return roleNum;
	}
	public void setRoleNum(int roleNum) {
		this.roleNum = roleNum;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<Card> getForChoose() {
		return forChoose;
	}
	public void setForChoose(List<Card> forChoose) {
		this.forChoose = forChoose;
	}
	public int getNumReveal() {
		return numReveal;
	}
	public void setNumReveal(int numReveal) {
		this.numReveal = numReveal;
	}
	public int getNumChoose() {
		return numChoose;
	}
	public void setNumChoose(int numChoose) {
		this.numChoose = numChoose;
	}
	public int getNumBuilt() {
		return numBuilt;
	}
	public void setNumBuilt(int numBuilt) {
		this.numBuilt = numBuilt;
	}
	public int getBuildLimit() {
		return buildLimit;
	}
	public void setBuildLimit(int buildLimit) {
		this.buildLimit = buildLimit;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("coin", coin);
		doc.append("role", roleNum);
		doc.append("phase", phase);
		doc.append("firstFinished", firstFinished);
		doc.append("numBuilt", numBuilt);
		doc.append("bot", bot);
		int i;
		List<Document> doh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			doh.add(hand.get(i).toDocument());
		}
		List<Document> dob = new ArrayList<>();
		for (i=0;i<built.size();i++) {
			dob.add(built.get(i).toDocument());
		}
		List<Document> dof = new ArrayList<>();
		for (i=0;i<forChoose.size();i++) {
			dof.add(forChoose.get(i).toDocument());
		}
		doc.append("hand", doh);
		doc.append("built", dob);
		doc.append("forChoose", dof);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		coin = doc.getInteger("coin", 0);
		roleNum = doc.getInteger("role", -1);
		phase = doc.getInteger("phase", -1);
		firstFinished = doc.getBoolean("firstFinished", false);
		numBuilt = doc.getInteger("numBuilt", 0);
		bot = doc.getBoolean("bot", false);
		role = board.getRoleByNum(roleNum);
		
		int i;
		
		List<Document> handDocList = (List<Document>) doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<handDocList.size();i++) {
			Card c = CardFactory.createCard(handDocList.get(i));
			c.setPlayer(this);
			c.setBoard(this.board);
			hand.add(c);
		}
			
		List<Document> builtDocList = (List<Document>) doc.get("built");
		built = new ArrayList<>();
		for (i=0;i<builtDocList.size();i++) {
			Card c = CardFactory.createCard(builtDocList.get(i));
			c.setPlayer(this);
			c.setBoard(this.board);
			built.add(c);
		}
		
		List<Document> forChooseDocList = (List<Document>) doc.get("forChoose");
		for (i=0;i<forChooseDocList.size();i++) {
			Card c = CardFactory.createCard(forChooseDocList.get(i));
			c.setPlayer(this);
			c.setBoard(this.board);
			forChoose.add(c);
		}
	}
	
}
