package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Player {
	List<Card> hand;
	List<Card> ancient;
	String name;
	String avatar;
	int score;
	int hp;
	int scoreLastRound;
	int phase;
	int index;
	int minNum;
	int missCount;
	int lastMove;
	boolean kill;
	boolean bot;
	Pm pm;
	Board board;
	List<Pm> pmPool;
	
	public Player() {
		hand = new ArrayList<>();
		ancient = new ArrayList<>();
		pmPool = new ArrayList<>();
		pm = new Pm();
	}
	
	public void startTurn() {
		board.nextTurn();
		phase = PokewhatConsts.USEMOVE;
		lastMove = -1;
		board.setCurPlayer(index);
		board.getLogger().logStartTurn(this);
	}
	
	public void activelyEndTurn() {
		board.getLogger().logEndTurn(this);
		endTurn();
	}
	
	public void endTurn() {
		phase = PokewhatConsts.OFFTURN;
		drawHands();
		changePmImgOnEndTurn();
		Player p = nextPlayer();
		p.startTurn();
	}
	
	public int cardIndex(Card c) {
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).getImg().contentEquals(c.getImg())) {
				return i;
			}
		}
		return -1;
	}
	
	public void useMove(int x) {
		Card c = CardFactory.createCard(x);
		c.setPlayer(this);
		c.setBoard(board);
		if (x == 0 && hand.size() == 0) {
			board.getLogger().logEternabeam(this);
			c.cardEffect();
			board.changeAnimation(c.getMoveAnimation());
			board.endRound();
			return;
		}
		board.getLogger().logUse(this, c);
		int index = cardIndex(c);
		if (cardIndex(c) != -1) {
			lastMove = c.getNum();
			c.cardEffect();
			board.changeAnimation(c.getMoveAnimation());
			Card removed = hand.remove(index);
			board.addToPlayedCards(removed);
			if (board.isRoundEnd()) {
				board.endRound();
			}
		} else {
			this.missCount++;
			c.penalty();
			board.changeAnimation(c.getMissAnimation());
			if (hp>0) {
				endTurn();
			} else {
				board.endRound();
			}
		}
	}
	
	public void hurt(int x) {
		hp = hp-x;
		if (hp<0) hp=0;
		changePmImgOnHurt();
	}
	
	public void recover(int x) {
		hp = hp+x;
		if (hp>PokewhatConsts.MAXHP) {
			hp=PokewhatConsts.MAXHP;
		}
		changePmImgOnRecover();
	}
	
	public void changePmImgOnHurt() {
		final int XS = 50;
		final int S = 80;
		final int M = 120;
		final int L = 180;
		final int XL = 250;
		if (this.getPm().getImg().contentEquals("071")) {
			this.getPm().setImg("071_1");
			return;
		}
		if (this.getPm().getImg().contentEquals("099")) {
			this.getPm().setImg("099_1");
			return;
		}
		if (this.getPm().getImg().contentEquals("185")) {
			if (this.getHp() < 2) {
				this.getPm().setImg("185_1");
				this.getPm().setSize(S);
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("187")) {
			if (this.getHp() <= 3) {
				this.getPm().setImg("187_1");
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("188_1") 
				|| this.getPm().getImg().contentEquals("188_2")) {
			this.getPm().setImg("188");
			return;
		}
		if (this.getPm().getImg().contentEquals("921")) {
			if (this.getHp() == 0) {
				this.getPm().setImg("921_1");
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("922")) {
			if (this.getHp() == 0) {
				this.getPm().setImg("922_1");
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("923")) {
			if (this.getHp() == 0) {
				this.getPm().setImg("923_1");
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("924")) {
			if (this.getHp() == 0) {
				this.getPm().setImg("924_1");
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("925")) {
			if (this.getHp() == 0) {
				this.getPm().setImg("925_1");
			}
			return;
		}
	}
	
	public void resetImgOnNewRound() {
		final int XS = 50;
		final int S = 80;
		final int M = 120;
		final int L = 180;
		final int XL = 250;
		if (this.getPm().getName().contentEquals("百变怪")) {
			this.getPm().setImg("083");
			this.getPm().setSize(S);
			return;
		}
		if (this.getPm().getImg().contentEquals("071_1")) {
			this.getPm().setImg("071");
			return;
		}
		if (this.getPm().getImg().contentEquals("099_1")) {
			this.getPm().setImg("099");
			return;
		}
		if (this.getPm().getImg().contentEquals("185_1")) {
			this.getPm().setImg("185");
			this.getPm().setSize(XL);
			return;
		}
		if (this.getPm().getImg().contentEquals("187_1")) {
			this.getPm().setImg("187");
			return;
		}
		if (this.getPm().getImg().contentEquals("188_1") 
				|| this.getPm().getImg().contentEquals("188_2")) {
			this.getPm().setImg("188");
			return;
		}
		if (this.getPm().getImg().contentEquals("921_1")) {
			this.getPm().setImg("921");
			return;
		}
		if (this.getPm().getImg().contentEquals("922_1")) {
			this.getPm().setImg("922");
			return;
		}
		if (this.getPm().getImg().contentEquals("923_1")) {
			this.getPm().setImg("923");
			return;
		}
		if (this.getPm().getImg().contentEquals("924_1")) {
			this.getPm().setImg("924");
			return;
		}
		if (this.getPm().getImg().contentEquals("925_1")) {
			this.getPm().setImg("925");
			return;
		}
		if (this.getPm().getImg().contentEquals("283") && board.getRound() > 1) {
			this.getPm().setImg("283_1");
			return;
		}
		
	}
	
	public void changePmImgOnRecover() {
		final int XS = 50;
		final int S = 80;
		final int M = 120;
		final int L = 180;
		final int XL = 250;
		if (this.getPm().getImg().contentEquals("185_1")) {
			if (this.getHp() > 1) {
				this.getPm().setImg("185");
				this.getPm().setSize(XL);
			}
			return;
		}
		if (this.getPm().getImg().contentEquals("187_1")) {
			if (this.getHp() > 3) {
				this.getPm().setImg("187");
			}
			return;
		}
	}
	
	public void changePmImgOnEndTurn() {
		if (this.getPm().getImg().contentEquals("125")) {
			this.getPm().setImg("125_1");
			return;
		}
		if (this.getPm().getImg().contentEquals("125_1")) {
			this.getPm().setImg("125");
			return;
		}
		if (this.getPm().getImg().contentEquals("188")) {
			if (this.getHp() > 3) {
				this.getPm().setImg("188_1");
			} else {
				this.getPm().setImg("188_2");
			}
			return;
		}
		if (this.getPm().getName().contentEquals("百变怪")) {
			Random rand = new Random();
			int x = rand.nextInt(board.getPlayers().size()-1);
			if (x>=index) x = x+1;
			this.pm.setImg(board.getPlayers().get(x).getPm().getImg());
			this.pm.setSize(board.getPlayers().get(x).getPm().getSize());
			return;
		}
	}
	
	public void drawHands() {
		while (hand.size() < PokewhatConsts.HANDSIZE) {
			if (board.getDeck().size() == 0) {
				break;
			}
			Card c = board.getDeck().remove(0);
			hand.add(c);
		}
	}
	
	public int prevPlayerIndex() {
		int x = index-1;
		if (x<0) {
			x = x + board.getPlayers().size();
		}
		return x;
	}
	
	public int nextPlayerIndex() {
		int x = index+1;
		if (x == board.getPlayers().size()) {
			x = 0;
		}
		return x;
	}
	
	public Player prevPlayer() {
		int x = index-1;
		if (x<0) {
			x = x + board.getPlayers().size();
		}
		Player p = board.getPlayers().get(x);
		return p;
	}
	
	public Player nextPlayer() {
		int x = index+1;
		if (x == board.getPlayers().size()) {
			x = 0;
		}
		Player p = board.getPlayers().get(x);
		return p;
	}

	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getAncient() {
		return ancient;
	}
	public void setAncient(List<Card> ancient) {
		this.ancient = ancient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void addScore(int x) {
		score = score+x;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public void addHp(int x) {
		hp = hp+x;
		if (hp>PokewhatConsts.MAXHP) hp=PokewhatConsts.MAXHP;
	}
	public int getScoreLastRound() {
		return scoreLastRound;
	}
	public void setScoreLastRound(int scoreLastRound) {
		this.scoreLastRound = scoreLastRound;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Pm getPm() {
		return pm;
	}
	public void setPm(Pm pm) {
		this.pm = pm;
	}
	public boolean isKill() {
		return kill;
	}
	public void setKill(boolean kill) {
		this.kill = kill;
	}
	public int getMinNum() {
		return minNum;
	}
	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}
	public int getMissCount() {
		return missCount;
	}
	public void setMissCount(int missCount) {
		this.missCount = missCount;
	}
	public int getLastMove() {
		return lastMove;
	}
	public void setLastMove(int lastMove) {
		this.lastMove = lastMove;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Pm> getPmPool() {
		return pmPool;
	}
	public void setPmPool(List<Pm> pmPool) {
		this.pmPool = pmPool;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("score", score);
		doc.append("hp", hp);
		doc.append("scoreLastRound", scoreLastRound);
		doc.append("phase", phase);
		doc.append("minNum", minNum);
		doc.append("kill", kill);
		doc.append("missCount", missCount);
		doc.append("bot", bot);
		doc.append("avatar", avatar);
		doc.append("pm",pm.toDocument());
		doc.append("lastMove", lastMove);
		int i;
		List<String> loh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			loh.add(hand.get(i).getImg());
		}
		doc.append("hand", loh);
		List<String> loa = new ArrayList<>();
		for (i=0;i<ancient.size();i++) {
			loa.add(ancient.get(i).getImg());
		}
		doc.append("ancient", loa);
		List<Document> lop = new ArrayList<>();
		for (i=0;i<pmPool.size();i++) {
			lop.add(pmPool.get(i).toDocument());
		}
		doc.append("pmPool", lop);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		score = doc.getInteger("score", 0);
		hp = doc.getInteger("hp", 0);
		scoreLastRound = doc.getInteger("scoreLastRound", 0);
		phase = doc.getInteger("phase", 0);
		minNum = doc.getInteger("minNum", 0);
		kill = doc.getBoolean("kill", false);
		missCount = doc.getInteger("missCount", 0);
		bot = doc.getBoolean("bot", false);
		lastMove = doc.getInteger("lastMove", 0);
		avatar = doc.getString("avatar");
		Document dopm = (Document) doc.get("pm");
		pm.setFromDoc(dopm);
		int i;
		hand = new ArrayList<>();
		List<String> loh = (List<String>) doc.get("hand");
		for (i=0;i<loh.size();i++) {
			Card c = CardFactory.createCard(loh.get(i));
			c.setBoard(board);
			hand.add(c);
		}
		ancient = new ArrayList<>();
		List<String> loa = (List<String>) doc.get("ancient");
		for (i=0;i<loa.size();i++) {
			Card c = CardFactory.createCard(loa.get(i));
			c.setBoard(board);
			ancient.add(c);
		}
		pmPool = new ArrayList<>();
		List<Document> lop = (List<Document>) doc.get("pmPool");
		for (i=0;i<lop.size();i++) {
			Pm pm = new Pm();
			pm.setFromDoc(lop.get(i));
			pmPool.add(pm);
		}
	}
	
}
