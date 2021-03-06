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
	int numTakeCoin;
	int coin;
	boolean firstFinished;
	boolean finished;
	boolean allColors;
	boolean killed;
	boolean beautify5Up;
	boolean beautifyUpTo2;
	boolean beautifyPurple;
	boolean insured;
	boolean protect;
	int roleNum;
	int phase;
	int buildLimit;
	int numBuilt;
	int identicalLimit;
	int greatWallIndex;
	boolean bot;
	Ask ask;
	List<String> canUseRoleSkill;
	List<String> canUseCardSkill;
	List<String> canBuyDelicacy;
	List<Integer> costReducers;
	
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
		numTakeCoin = 2;
		buildLimit = 1;
		identicalLimit = 0;
		greatWallIndex = -1;
		bot = false;
		phase = CitadelsConsts.OFFTURN;
		ask = new Ask();
		canUseRoleSkill = new ArrayList<>();
		canUseCardSkill = new ArrayList<>();
		canBuyDelicacy = new ArrayList<>();
		beautify5Up = false;
		beautifyPurple = false;
		insured = false;
		protect = false;
		costReducers = new ArrayList<>();
		for (int i=0;i<5;i++) {
			costReducers.add(0);
		}
	}
	
	public void chooseRole(int index) {
		if (board.getRoles().get(index).getOwner() == CitadelsConsts.SELECTABLE) {
			role = board.getRoles().get(index);
			roleNum = role.getNum();
			role.setOwner(board.getPlayerIndex(this));
			role.setPlayer(this);
			canUseRoleSkill = new ArrayList<>();
			for (int i=0;i<role.getNumSkills();i++) {
				canUseRoleSkill.add("y");
			}
			board.log(name + "选择了一个角色。");
		}
		phase = CitadelsConsts.OFFTURN;
		board.nextPlayerChooseRole();
		
	}
	
	public void resetDelicacies() {
		canBuyDelicacy = new ArrayList<>();
		for (int i=0;i<board.getDelicacies().size();i++) {
			canBuyDelicacy.add("y");
		}
	}
	
	public void useRoleSkill(int index) {
		if (index < canUseRoleSkill.size()) {
			canUseRoleSkill.set(index, "n");
		}
	}
	
	public void useCardSkill(int index) {
		if (index < canUseCardSkill.size()) {
			canUseCardSkill.set(index, "n");
		}
	}
	
	public void cancelSkill() {
		ask = new Ask();
	}
	
	public void startTurn() {
		if (phase == CitadelsConsts.OFFTURN) {
			phase = CitadelsConsts.TAKEACTION;
			board.log(name + "开始了回合。");
		} else {
			// for debug purposes
			//phase = CitadelsConsts.TAKEACTION;
		}
	}
	
	public void endTurn() {
		if (phase == CitadelsConsts.BUILDDISTRICT) {
			phase = CitadelsConsts.OFFTURN;
			board.log(name + "结束了回合。");
			for (int i=0;i<built.size();i++) {
				built.get(i).endTurnEffect();
			}
			board.nextRole();
		}
	}
	
	public void takeActionOption(int option) {
		if (phase == CitadelsConsts.TAKEACTION) {
			if (option == 1) {
				for (int i=0;i<built.size();i++) {
					built.get(i).onSelectChooseCard();
				}
				if (numReveal == numChoose) {
					draw(numChoose);
					board.log(name + "摸" + Integer.toString(numChoose) + "张牌。");
					startBuildPhase();
				} else {
					forChoose = board.firstCards(numReveal);
					board.log(name + "观看" + Integer.toString(numReveal) + "张牌");
					phase = CitadelsConsts.CHOOSECARD;
				}
			} else if (option == 2) {
				addCoin(numTakeCoin);
				board.log(name + "获得" + Integer.toString(numTakeCoin) + "￥。");
				startBuildPhase();
			} else {
				
			}
		}
	}
	
	public void chooseCard(int index) {
		if (numChoose == 1) {
			hand.add(forChoose.remove(index));
			board.bottomDeck(forChoose);
			board.log(name + "选择了1张牌加入手牌。");
			startBuildPhase();
		} else if (numChoose == 2 && numReveal == 3) {
			for (int i=forChoose.size()-1;i>=0;i--) {
				if (i==index) continue;
				hand.add(forChoose.remove(i));
			}
			board.bottomDeck(forChoose);
			board.log(name + "选择了2张牌加入手牌。");
			startBuildPhase();
		}
		
	}
	
	public void buildCanBuyDelicacy() {
		canBuyDelicacy = new ArrayList<>();
		for (int i=0;i<board.getDelicacies().size();i++) {
			canBuyDelicacy.add("y");
		}
	}
	
	public void buildCanUseCardSkill() {
		canUseCardSkill = new ArrayList<>();
		for (int i=0;i<built.size();i++) {
			Card c = built.get(i);
			canUseCardSkill.add(c.canUseSkill());
		}
	}
	
	public void startBuildPhase(){ // equiv. to aftertakeaction
		// TODO: some extra handle for witch & bewitched players
		role.afterTakeAction();
		for (int i=0;i<built.size();i++) {
			Card c = built.get(i);
			c.afterTakeActionEffect();
		}
		buildCanUseCardSkill();
		buildCanBuyDelicacy();
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
	
	public boolean notIdentical(Card c) {
		int identicalCount = 0;
		int i;
		for (i=0;i<built.size();i++) {
			if (built.get(i).getImg().contentEquals(c.getImg())) {
				identicalCount++;
			}
		}
		if (identicalCount > identicalLimit) {
			return false;
		} else {
			return true;
		}
	}
	
	public int builtSize() {
		int builtSize = 0;
		for (int i=0;i<built.size();i++) {
			builtSize = builtSize + built.get(i).buildCount();
		}
		return builtSize;
	}
	
	public boolean canBuildFreeNoLimit(int x) {
		if (hand.size()>x) {
			Card c = hand.get(x);
			if (!c.isBuildable()) {
				return false;
			}
			if (notIdentical(c) == false) {
				return false;
			}
			int builtSize = builtSize();
			return hand.get(x).canBuildWhen(builtSize);
		} else {
			return false;
		}
	}
	
	public boolean canBuildFree(int x) {
		if (canBuildFreeNoLimit(x)) {
			Card c = hand.get(x);
			if (numBuilt + c.getBuildCount() > buildLimit) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	public boolean canBuild(int x) {
		if (canBuildFree(x)) {
			Card c = hand.get(x);
			int color = c.getColor();
			int costReduce;
			if (color>9) {
				int c1 = color/10;
				int c2 = color%10;
				costReduce = costReducers.get(c1) + costReducers.get(c2);
			} else {
				costReduce = costReducers.get(color);
			}
			int cost = c.getBuildCost() - costReduce;
			if (cost<0) cost = 0;
			if (cost > coin) {
				return false;
			} else {
				return true;
			}
			
		} else {
			return false;
		}
	}
	
	public void freeBuild(int x) {
		if (hand.size() > x) {
			Card c = hand.get(x);
			numBuilt = numBuilt + c.getBuildCount();
			c.setBuiltRound(board.getRoundCount());
			c.setIndex(built.size());
			hand.remove(x);
			for (int i=0;i<built.size();i++) {
				built.get(i).onAnotherBuild(c);
			}
			built.add(c);
			c.onBuild();
			if (beautify5Up && c.getBeautifyLevel() < 3 && c.getCost() >= 5) {
				x = board.takeCoins(1);
				if (x>0) {
					c.setBeautifyLevel(x);
					board.log("因为 别墅区 的效果，" + c.getName() + " 升值了。");
				}
			}
			if (beautifyUpTo2 && c.getBeautifyLevel() < 3 && c.getCost() <= 2) {
				x = board.takeCoins(1);
				if (x>0) {
					c.setBeautifyLevel(x);
					board.log("因为 福利院 的效果，" + c.getName() + " 升值了。");
				}
			}
			canUseCardSkill.add(c.canUseSkillSameRound());
			int builtSize = builtSize();
			if (builtSize >= board.getFinishCount()) {
				finished = true;
				if (board.isFirstFinished()) {
					firstFinished = true;
					board.setFirstFinished(false);
					board.log(name + "达成了建筑数量目标，游戏将在本回合结束后结束。");
				}
			}
		}
		
	}
	
	public void build(int x) {
		if (canBuild(x)) {
			Card c = hand.get(x);
			int color = c.getColor();
			int costReduce;
			if (color>9) {
				int c1 = color/10;
				int c2 = color%10;
				costReduce = costReducers.get(c1) + costReducers.get(c2);
			} else {
				costReduce = costReducers.get(color);
			}
			int cost = c.getBuildCost() - costReduce;
			if (cost<0) cost = 0;
			coin = coin-cost;
			board.addCoin(cost);
			board.log(name + "花费了" + Integer.toString(cost) + "￥建造了 " + c.getName()+"。");
			if (canBuildFree(x)) {
				freeBuild(x);
			}
		}
	}
	
	public void beautify(int index) {
		if (index < 0) return;
		if (index >= built.size()) return;
		if (built.get(index).getBeautifyLevel() == 0) {
			built.get(index).beautify();
		}
	}
	
	public boolean hasCrown() {
		if (board.getCrown() < 0 || board.getCrown() >= board.getPlayers().size()) {
			return false;
		}
		if (board.getPlayers().get(board.getCrown()).getName().contentEquals(name)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<String> canExchangeList(Player dp, Card c) { // a helper for diplomat
		List<String> ans = new ArrayList<>();
		boolean flag;
		if (dp.getName().contentEquals(name)) {
			flag = false;
		} else if (this.builtSize() >= board.getFinishCount()) {
			flag = false;
		} else if (role.isDestroyable() || role.getNum() == board.getKilledRole()) {
			if (notIdentical(c)) {
				flag = true;
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}
		
		for (int i=0;i<built.size();i++) {
			if (flag) {
				Card b = built.get(i);
				int x = b.getCost() + b.getBeautifyLevel() - c.getCost() - c.getBeautifyLevel();
				if (greatWallIndex >= 0 && i != greatWallIndex) {
					x = x+1;
				}
				if (x<0) x = 0;
				if (x>dp.getCoin()) {
					ans.add("-1");
				} else {
					ans.add(Integer.toString(x));
				}
			} else {
				ans.add("-1");
			}
		}
		return ans;
	}
	
	public boolean canReceive(Player dp, Card c) { // a helper for diplomat
		List<String> ls = canExchangeList(dp, c);
		boolean f = false;
		for (int i=0;i<ls.size();i++) {
			if (ls.get(i).contentEquals("-1")) {
				
			} else {
				f = true;
			}
		}
		return f;
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
			if (cc>9) {
				int c1 = cc/10;
				int c2 = cc%10;
				colors[c1]++;
				colors[c2]++;
			} else {
				colors[cc]++;
			}
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
	
	public int netScore() {
		int ans = 0;
		int i;
		for (i=0;i<built.size();i++) {
			ans = ans + built.get(i).getScore();
		}
		if (board.getStatus() == CitadelsConsts.ENDGAME) {
			ans = ans + extraScore() + secretScore();
		}
		return ans;
	}
	
	public int calcScore() {
		int i;
		int ans = netScore();
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
			if (cc>9) {
				int c1 = cc/10;
				int c2 = cc%10;
				colors[c1]++;
				colors[c2]++;
			} else {
				colors[cc]++;
			}
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
	
	public int extraScore() {
		int ans = 0;
		for (int i=0;i<built.size();i++) {
			ans = ans+built.get(i).getExtraScore();
		}
		return ans;
	}
	
	public int secretScore() {
		int ans = 0;
		for (int i=0;i<hand.size();i++) {
			ans = ans+hand.get(i).getSecretScore();
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
	public int getNumTakeCoin() {
		return numTakeCoin;
	}
	public void setNumTakeCoin(int numTakeCoin) {
		this.numTakeCoin = numTakeCoin;
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
	public Ask getAsk() {
		return ask;
	}
	public void setAsk(Ask ask) {
		this.ask = ask;
	}
	public List<String> getCanUseRoleSkill() {
		return canUseRoleSkill;
	}
	public void setCanUseRoleSkill(List<String> canUseRoleSkill) {
		this.canUseRoleSkill = canUseRoleSkill;
	}
	public List<String> getCanUseCardSkill() {
		return canUseCardSkill;
	}
	public void setCanUseCardSkill(List<String> canUseCardSkill) {
		this.canUseCardSkill = canUseCardSkill;
	}
	public int getGreatWallIndex() {
		return greatWallIndex;
	}
	public void setGreatWallIndex(int greatWallIndex) {
		this.greatWallIndex = greatWallIndex;
	}
	public List<Integer> getCostReducers() {
		return costReducers;
	}
	public void setCostReducers(List<Integer> costReducers) {
		this.costReducers = costReducers;
	}
	public int getIdenticalLimit() {
		return identicalLimit;
	}
	public void setIdenticalLimit(int identicalLimit) {
		this.identicalLimit = identicalLimit;
	}
	public boolean isBeautify5Up() {
		return beautify5Up;
	}
	public void setBeautify5Up(boolean beautify5Up) {
		this.beautify5Up = beautify5Up;
	}
	public boolean isBeautifyPurple() {
		return beautifyPurple;
	}
	public void setBeautifyPurple(boolean beautifyPurple) {
		this.beautifyPurple = beautifyPurple;
	}
	public boolean isBeautifyUpTo2() {
		return beautifyUpTo2;
	}
	public void setBeautifyUpTo2(boolean beautifyUpTo2) {
		this.beautifyUpTo2 = beautifyUpTo2;
	}
	public boolean isInsured() {
		return insured;
	}
	public void setInsured(boolean insured) {
		this.insured = insured;
	}
	public List<String> getCanBuyDelicacy() {
		return canBuyDelicacy;
	}
	public void setCanBuyDelicacy(List<String> canBuyDelicacy) {
		this.canBuyDelicacy = canBuyDelicacy;
	}
	public boolean isProtect() {
		return protect;
	}
	public void setProtect(boolean protect) {
		this.protect = protect;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("coin", coin);
		doc.append("role", roleNum);
		doc.append("phase", phase);
		doc.append("finished", finished);
		doc.append("firstFinished", firstFinished);
		doc.append("numBuilt", numBuilt);
		doc.append("bot", bot);
		doc.append("canUseRoleSkill", canUseRoleSkill);
		doc.append("canUseCardSkill", canUseCardSkill);
		doc.append("canBuyDelicacy", canBuyDelicacy);
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
		Document doa = ask.toDocument();
		doc.append("ask", doa);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		coin = doc.getInteger("coin", 0);
		roleNum = doc.getInteger("role", -1);
		phase = doc.getInteger("phase", -1);
		finished = doc.getBoolean("finished", false);
		firstFinished = doc.getBoolean("firstFinished", false);
		numBuilt = doc.getInteger("numBuilt", 0);
		bot = doc.getBoolean("bot", false);
		role = board.getRoleByNum(roleNum);
		if (role != null) {
			role.setPlayer(this);
			role.alterPlayerAbility();
		}
		canUseRoleSkill = (List<String>) doc.get("canUseRoleSkill");
		canUseCardSkill = (List<String>) doc.get("canUseCardSkill");
		canBuyDelicacy = (List<String>) doc.get("canBuyDelicacy");
		
		int i;
		
		List<Document> handDocList = (List<Document>) doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<handDocList.size();i++) {
			Card c = CardFactory.createCard(handDocList.get(i));
			c.setPlayer(this);
			c.setBoard(this.board);
			c.setIndex(i);
			hand.add(c);
		}
			
		List<Document> builtDocList = (List<Document>) doc.get("built");
		built = new ArrayList<>();
		for (i=0;i<builtDocList.size();i++) {
			Card c = CardFactory.createCard(builtDocList.get(i));
			c.setPlayer(this);
			c.setBoard(this.board);
			c.setIndex(i);
			built.add(c);
			c.alterPlayerAbility();
		}
		
		List<Document> forChooseDocList = (List<Document>) doc.get("forChoose");
		for (i=0;i<forChooseDocList.size();i++) {
			Card c = CardFactory.createCard(forChooseDocList.get(i));
			c.setPlayer(this);
			c.setBoard(this.board);
			forChoose.add(c);
		}
		Document doa = (Document) doc.get("ask");
		ask = new Ask();
		ask.setFromDoc(doa);
	}
	
}
