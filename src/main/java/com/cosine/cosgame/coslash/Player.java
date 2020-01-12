package com.cosine.cosgame.coslash;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Player {
	public static final int INACTIVE = 0;
	public static final int ALIVE = 1;
	public static final int DYING = 2;
	public static final int DEAD = 3;
	
	public static final int PHASE_OUTER = 100;
	public static final int PHASE_START = 101;
	public static final int PHASE_JUDGE = 102;
	public static final int PHASE_DRAW = 103;
	public static final int PHASE_ACTION = 104;
	public static final int PHASE_DISCARD = 105;
	public static final int PHASE_END = 106;
	  
	int status;
	int phase;
	int identity;
	String name;
	Role role;
	List<Card> hand;
	Card weapon;
	Card shield;
	Card atkhorse;
	Card defhorse;
	int maxHP;
	int hp;
	List<Ask> asks;
	
	Card jail; // 乐不思蜀
	Card bomb; // 闪电
	Card foodless; // 兵粮寸断
	
	public Player() {
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Card> getHand() {
		return hand;
	}

	public void setHand(List<Card> hand) {
		this.hand = hand;
	}

	public Card getWeapon() {
		return weapon;
	}

	public void setWeapon(Card weapon) {
		this.weapon = weapon;
	}

	public Card getShield() {
		return shield;
	}

	public void setShield(Card shield) {
		this.shield = shield;
	}

	public Card getAtkhorse() {
		return atkhorse;
	}

	public void setAtkhorse(Card atkhorse) {
		this.atkhorse = atkhorse;
	}

	public Card getDefhorse() {
		return defhorse;
	}

	public void setDefhorse(Card defhorse) {
		this.defhorse = defhorse;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public List<Ask> getAsks() {
		return asks;
	}

	public void setAsks(List<Ask> asks) {
		this.asks = asks;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("identity", identity);
		doc.append("role", role.getName());
		doc.append("status", status);
		doc.append("phase", phase);
		doc.append("maxHP", maxHP);
		doc.append("hp", hp);
		List<Document> doh = new ArrayList<>();
		for (int i=0;i<hand.size();i++) {
			Document dc = new Document();
			dc.append("card", hand.get(i).toDocument());
			doh.add(dc);
		}
		doc.append("hand", doh);
		
		List<Document> doa = new ArrayList<>();
		for (int i=0;i<asks.size();i++) {
			Document da = asks.get(i).toDocument();
			doa.add(da);
		}
		doc.append("asks", doa);
		
		if (weapon == null) {
			doc.append("weapon", null);
		} else {
			doc.append("weapon", weapon.toDocument());
		}
		if (shield == null) {
			doc.append("shield", null);
		} else {
			doc.append("shield", shield.toDocument());
		}
		if (atkhorse == null) {
			doc.append("atkhorse", null);
		} else {
			doc.append("atkhorse", atkhorse.toDocument());
		}
		if (defhorse == null) {
			doc.append("defhorse", null);
		} else {
			doc.append("defhorse", defhorse.toDocument());
		}
		
		if (jail == null) {
			doc.append("jail", null);
		} else {
			doc.append("jail", jail.toDocument());
		}
		if (bomb == null) {
			doc.append("bomb", null);
		} else {
			doc.append("bomb", bomb.toDocument());
		}
		if (foodless == null) {
			doc.append("foodless", null);
		} else {
			doc.append("foodless", foodless.toDocument());
		}
		
		return doc;
	}
	
	public void setPlayerFromDoc(Document doc) {
		name = doc.getString("name");
		status = doc.getInteger("status");
		identity = doc.getInteger("identity");
		phase = doc.getInteger("phase");
		role = RoleFactory.createRole(doc.getString("role"));
		
		List<Document> doh = (List<Document>)doc.get("hand");
		hand = new ArrayList<>();
		for (int i=0;i<doh.size();i++) {
			Card c = CardFactory.createCard(doh.get(i));
			hand.add(c);
		}
		
		List<Document> doa = (List<Document>)doc.get("asks");
		asks = new ArrayList<>();
		for (int i=0;i<doa.size();i++) {
			Ask a = new Ask();
			a.setAskFromDoc(doa.get(i));
			asks.add(a);
		}
		
		if (doc.get("weapon") == null) {
			weapon = null;
		} else {
			weapon = CardFactory.createCard((Document)doc.get("weapon"));
		}
		if (doc.get("shield") == null) {
			shield = null;
		} else {
			shield = CardFactory.createCard((Document)doc.get("shield"));
		}
		if (doc.get("atkhorse") == null) {
			atkhorse = null;
		} else {
			atkhorse = CardFactory.createCard((Document)doc.get("atkhorse"));
		}
		if (doc.get("defhorse") == null) {
			defhorse = null;
		} else {
			defhorse = CardFactory.createCard((Document)doc.get("defhorse"));
		}
		
		if (doc.get("jail") == null) {
			jail = null;
		} else {
			jail = CardFactory.createCard((Document)doc.get("jail"));
		}
		if (doc.get("bomb") == null) {
			bomb = null;
		} else {
			bomb = CardFactory.createCard((Document)doc.get("bomb"));
		}
		if (doc.get("foodless") == null) {
			foodless = null;
		} else {
			foodless = CardFactory.createCard((Document)doc.get("foodless"));
		}
	}
	
}
