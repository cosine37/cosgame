package com.cosine.cosgame.pokewhat;

import java.util.ArrayList;
import java.util.List;

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
	
	public Player() {
		hand = new ArrayList<>();
		ancient = new ArrayList<>();
		pm = new Pm();
	}
	
	public void startTurn() {
		board.nextTurn();
		phase = PokewhatConsts.USEMOVE;
		lastMove = 0;
		board.setCurPlayer(index);
	}
	
	public void endTurn() {
		phase = PokewhatConsts.OFFTURN;
		drawHands();
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
		int index = cardIndex(c);
		if (cardIndex(c) != -1) {
			lastMove = c.getNum();
			c.cardEffect();
			Card removed = hand.remove(index);
			board.addToPlayedCards(removed);
			if (hand.size() == 0) {
				board.eternabeam(this.index);
			}
			if (board.isRoundEnd()) {
				board.endRound();
			}
		} else {
			c.penalty();
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
		//TODO: add end turn & calc score handlers
	}
	
	public void recover(int x) {
		hp = hp+x;
		if (hp>PokewhatConsts.MAXHP) {
			hp=PokewhatConsts.MAXHP;
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
	}
	
}
