package com.cosine.cosgame.nothanks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Player {
	String name;
	List<Card> hand;
	int revealedMoney;
	Package pack;
	Board board;
	int phase;
	int index;
	boolean bot;
	
	public Player() {
		hand = new ArrayList<>();
		phase = -1; //offturn
	}
	
	public void draw() {
		phase = 0; // in turn;
		if (pack == null) {
			pack = board.genPack();
		}
	}
	
	public void receive() {
		revealedMoney = revealedMoney+pack.getMoney();
		hand.add(pack.getCard());
		pack = null;
		if (board.getDeck().size() == 0) {
			board.endGame();
		} else {
			if (board.getDeck().size()>0) {
				draw();
			} else {
				board.endGame();
			}
			
		}
	}
	
	public void send(int x) {
		if (revealedMoney != 0) {
			revealedMoney--;
			pack.addMoney();
			board.sendPack(x, pack);
			phase = -1;
			pack = null;
		} else {
			boolean flag = false;
			for (int i=0;i<hand.size();i++) {
				if (hand.get(i).getNum() == -3) {
					flag = true;
					hand.remove(i);
					pack.addMoney();
				}
			}
			if (flag) {
				board.sendPack(x, pack);
				phase = -1;
			}
		}
	}
	
	public int calcScore() {
		int i;
		int ans = 0;
		boolean f = hasQs();
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() > 0) {
				if (f) {
					ans = ans+13-hand.get(i).getNum();
				} else {
					ans = ans + hand.get(i).getNum();
				}
			} else {
				ans = ans + hand.get(i).getNum();
			}
		}
		
		ans = ans-revealedMoney*3;
		return ans;
	}
	
	public boolean hasQs() {
		boolean ans = false;
		int i;
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() == 0) {
				ans = true;
				break;
			}
		}
		return ans;
	}
	
	public int botNextMove() {
		if (bot && phase == 0) {
			if (getTrueMoney() == 0 || getReceivedScore() - calcScore() < 3) {
				receive();
				return -1;
			} else {
				int n = board.getPlayers().size()-1;
				Random rand = new Random();
				int x = rand.nextInt(n);
				if (x>=index) {
					x = x+1;
				}
				send(x);
				return x;
			}
		} else {
			return -1;
		}
	}
	
	public int getReceivedScore() {
		int i;
		int ans = 0;
		if (pack.getCard().getNum() == 0) {
			for (i=0;i<hand.size();i++) {
				ans = ans+13-hand.get(i).getNum();
			}
		} else {
			ans = calcScore();
			if (hasQs()) {
				ans = ans + 13-pack.getCard().getNum();
			} else {
				ans = ans + pack.getCard().getNum();
			}
		}
		ans = ans-pack.getMoney()*3;
		return ans;
	}
	
	public int getTrueMoney() {
		int ans = revealedMoney;
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).getNum() == -3) {
				ans++;
			}
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
	public int getRevealedMoney() {
		return revealedMoney;
	}
	public void setRevealedMoney(int revealedMoney) {
		this.revealedMoney = revealedMoney;
	}
	public Package getPack() {
		return pack;
	}
	public void setPack(Package pack) {
		this.pack = pack;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isBot() {
		return bot;
	}
	public void setBot(boolean bot) {
		this.bot = bot;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("revealedMoney", revealedMoney);
		doc.append("phase", phase);
		doc.append("bot", bot);
		if (pack != null) {
			doc.append("packCard", pack.getCard().getNum());
			doc.append("packMoney", pack.getMoney());
		} else {
			doc.append("packCard", -1);
			doc.append("packMoney", -1);
		}
		int i;
		List<Integer> loh = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			loh.add(hand.get(i).getNum());
		}
		doc.append("hand", loh);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		revealedMoney = doc.getInteger("revealedMoney", 0);
		phase = doc.getInteger("phase", -1);
		bot = doc.getBoolean("bot", false);
		int packCard = doc.getInteger("packCard", -1);
		int packMoney = doc.getInteger("packMoney", -1);
		if (packCard == -1) {
			pack = null;
		} else {
			pack = new Package();
			Card c = CardFactory.createCard(packCard);
			pack.setCard(c);
			pack.setMoney(packMoney);
		}
		int i;
		hand = new ArrayList<>();
		List<Integer> loh = (List<Integer>) doc.get("hand");
		for (i=0;i<loh.size();i++) {
			Card c = CardFactory.createCard(loh.get(i));
			hand.add(c);
		}
	}
	
	
	
}
