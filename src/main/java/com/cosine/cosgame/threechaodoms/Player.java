package com.cosine.cosgame.threechaodoms;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.threechaodoms.entity.CardEntity;
import com.cosine.cosgame.threechaodoms.entity.PlayerEntity;
import com.cosine.cosgame.threechaodoms.shop.Account;
import com.cosine.cosgame.threechaodoms.shop.Transaction;

public class Player {
	String name;
	int index;
	int phase;
	
	ID id;
	List<Card> hand;
	List<Card> play;
	List<Card> jail;
	
	List<Integer> knownJails;
	List<String> receives;
	
	Board board;
	Account account;
	
	public Player() {
		id = new ID();
		hand = new ArrayList<>();
		play = new ArrayList<>();
		jail = new ArrayList<>();
		knownJails = new ArrayList<>();
		receives = new ArrayList<>();
		account = new Account();
	}
	
	public void setupHand(int jailIndex, int exileIndex) {
		if (jailIndex > exileIndex) {
			putInJail(jailIndex);
			exile(exileIndex);
		} else {
			exile(exileIndex);
			putInJail(jailIndex);
		}
		board.log(name + "初始化了手牌。");
		endTurn();
	}
	
	
	public void playCard(int x, List<Integer> targets) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			board.log(name, c);
			c.play(targets);
			play.add(c);
			
			//TODO: May need fix here
			if (hand.size()>=4) {
				phase = Consts.DISCARD;
			} else {
				phase = Consts.RECRUIT;
			}
			
		}
	}
	
	public void exileCards(List<Integer> targets) {
		int i,j;
		for (i=0;i<targets.size();i++) {
			for (j=i+1;j<targets.size();j++) {
				if (targets.get(i)>targets.get(j)) {
					int x = targets.get(i);
					targets.set(i, targets.get(j));
					targets.set(j, x);
				}
			}
		}
		for (i=targets.size()-1;i>=0;i--) {
			exile(targets.get(i));
		}
		if (hand.size()>=4) {
			phase = Consts.DISCARD;
		} else {
			phase = Consts.RECRUIT;
		}
	}
	
	public void exile(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			board.addToExile(c);
		}
	}
	
	public void draw(int x) {
		int i;
		for (i=0;i<x;i++) {
			Card c = board.popTopDeck();
			if (c == null) return;
			hand.add(c);
		}
	}
	
	public void draw() {
		draw(1);
	}
	
	public void takeFromTavern(int x) {
		Card c = board.takeFromTavern(x);
		if (c != null) {
			hand.add(c);
		}
	}
	public void recruit(int x) {
		if (x == -1) {
			return;
		} else if (x>=0 && x<3) {
			Card c = board.getTavern().get(x);
			takeFromTavern(x);
			board.getLogger().logRecruitTavern(name, c);
		} else {
			draw();
			board.getLogger().logRecruitDeck(name);
		}
		if (hand.size() == 4) {
			board.log(name + "招募结束。");
			phase = Consts.DISCARD;
			board.refillTavern();
		}
	}
	public void discard(int x) {
		exile(x);
		board.log(name + "驱逐了一名武将。");
		if (hand.size() <= 3) {
			endTurn();
		}
	}
	
	public void putInJail(int x) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			jail.add(c);
		}
	}
	
	public void putInJail(Card c) {
		jail.add(c);
	}
	
	public void endTurn() {
		phase = Consts.OFFTURN;
		Player p = nextPlayer();
		if (board.getStatus() == Consts.SETUP){
			board.setCurPlayer(p.getIndex());
			if (board.getCurPlayer() == board.getFirstPlayer()) {
				board.setStatus(Consts.INGAME);
				p.setPhase(Consts.PLAYCARD);
			} else {
				p.setPhase(Consts.MAKEHAND);
			}
			
		} else if (board.getStatus() == Consts.INGAME) {
			knownJails = new ArrayList<>();
			board.log(name + "结束了回合。");
			board.log(p.getName() + "开始了回合。");
			p.setPhase(Consts.PLAYCARD);
			if (board.gameEnds()) {
				board.endGame();
			}
		}
	}
	
	public Player nextPlayer() {
		int n = board.getPlayers().size();
		int x = index+1;
		if (x>=n) x=x-n;
		
		Player p = board.getPlayers().get(x);
		return p;
	}
	
	public void findAccount() {
		Account account = new Account();
		account.getFromDB(name);
		this.account = account;
	}
	
	public int numFaction(int x) {
		int ans = 0;
		int i;
		for (i=0;i<play.size();i++) {
			if (play.get(i).getFaction() == x) {
				ans++;
			}
		}
		for (i=0;i<jail.size();i++) {
			if (jail.get(i).getFaction() == x) {
				ans++;
			}
		}
		return ans;
	}
	
	public int totalCards() {
		return play.size() + jail.size();
	}
	
	public void setReceivesFromTransaction(List<Transaction> ts) {
		receives = new ArrayList<>();
		for (int i=0;i<ts.size();i++) {
			String s = ts.get(i).getInfo() + "：" + ts.get(i).getAmount();
			if (ts.get(i).getType() == ts.get(i).MONEY) {
				s = s+"枚铜钱";
			} else if (ts.get(i).getType() == ts.get(i).INGOT) {
				s = s+"个元宝";
			}
			receives.add(s);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getPlay() {
		return play;
	}
	public void setPlay(List<Card> play) {
		this.play = play;
	}
	public List<Card> getJail() {
		return jail;
	}
	public void setJail(List<Card> jail) {
		this.jail = jail;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<Integer> getKnownJails() {
		return knownJails;
	}
	public void setKnownJails(List<Integer> knownJails) {
		this.knownJails = knownJails;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("id", id.toDocument());
		doc.append("phase", phase);
		doc.append("knownJails", knownJails);
		doc.append("receives", receives);
		int i;
		List<Document> doh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			doh.add(hand.get(i).toDocument());
		}
		doc.append("hand", doh);
		List<Document> dop = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			dop.add(play.get(i).toDocument());
		}
		doc.append("play", dop);
		List<Document> doj = new ArrayList<>();
		for (i=0;i<jail.size();i++) {
			doj.add(jail.get(i).toDocument());
		}
		doc.append("jail", doj);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		phase = doc.getInteger("phase", Consts.OFFTURN);
		knownJails = (List<Integer>) doc.get("knownJails");
		receives = (List<String>) doc.get("receives");
		Document idDoc = (Document) doc.get("id");
		id = new ID();
		id.setFromDoc(idDoc);
		int i;
		hand = new ArrayList<>();
		List<Document> doh = (List<Document>) doc.get("hand");
		for (i=0;i<doh.size();i++) {
			Card c = CardFactory.makeCard(doh.get(i));
			c.setWhere(Consts.HAND);
			c.setBoard(board);
			c.setPlayer(this);
			hand.add(c);
		}
		play = new ArrayList<>();
		List<Document> dop = (List<Document>) doc.get("play");
		for (i=0;i<dop.size();i++) {
			Card c = CardFactory.makeCard(dop.get(i));
			c.setWhere(Consts.PLAY);
			c.setBoard(board);
			c.setPlayer(this);
			play.add(c);
		}
		jail = new ArrayList<>();
		List<Document> doj = (List<Document>) doc.get("jail");
		for (i=0;i<doj.size();i++) {
			Card c = CardFactory.makeCard(doj.get(i));
			c.setWhere(Consts.JAIL);
			c.setBoard(board);
			c.setPlayer(this);
			jail.add(c);
		}
		findAccount();
	}
	
	public PlayerEntity toPlayerEntity(Player p) {
		PlayerEntity entity = new PlayerEntity();
		entity.setName(name);
		entity.setReceives(receives);
		if (board.getStatus() == Consts.ENDGAME) {
			entity.setId(id.getFactions());
		} else {
			List<Integer> empty = new ArrayList<>();
			entity.setId(empty);
		}
		int i;
		List<CardEntity> playEntity = new ArrayList<>();
		for (i=0;i<play.size();i++) {
			playEntity.add(play.get(i).toCardEntity(this));
		}
		entity.setPlay(playEntity);
		List<CardEntity> jailEntity = new ArrayList<>();
		for (i=0;i<jail.size();i++) {
			Card blank = new BlankSpaceCard();
			if (p == null) {
				jailEntity.add(blank.toCardEntity());
			} else if (p.getIndex() == index) {
				jailEntity.add(jail.get(i).toCardEntity(p));
			} else if (board.getStatus() == Consts.ENDGAME) {
				jailEntity.add(jail.get(i).toCardEntity(p));
			} else {
				List<Integer> knownJails = p.getKnownJails();
				boolean flag = true;
				int j;
				for (j=0;j<knownJails.size();j++) {
					int x = knownJails.get(j) / 10;
					int y = knownJails.get(j) % 10;
					if (x == index && y == i) {
						jailEntity.add(jail.get(i).toCardEntity(p));
					}
					flag = false;
				}
				if (flag) {
					jailEntity.add(blank.toCardEntity());
				}
			}
		}
		entity.setJail(jailEntity);
		return entity;
	}
	
	public PlayerEntity toPlayerEntity() {
		return toPlayerEntity(null);
	}
	
}
