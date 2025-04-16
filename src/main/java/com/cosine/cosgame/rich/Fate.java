package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cosine.cosgame.rich.basicplaces.Estate;

public class Fate {
	int id;
	int type;
	int value;
	String content;
	String conversation;
	
	public Fate(int id, int type, int value, String content, String conversation) {
		this.id = id;
		this.type = type;
		this.value = value;
		this.content = content;
		this.conversation = conversation;
	}
	
	public int houseRepairCost(Player p) {
		Map map = p.getBoard().getMap();
		int ans = 0;
		for (int i=0;i<map.getPlaces().size();i++) {
			if (map.getPlaces().get(i).getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) map.getPlaces().get(i);
				if (e.getOwnerId() == p.getIndex()) {
					ans = ans+e.getLevel();
				}
			}
		}
		ans = ans*value;
		return ans;
	}
	
	public int propertyTaxCost(Player p) {
		Map map = p.getBoard().getMap();
		int ans = 0;
		for (int i=0;i<map.getPlaces().size();i++) {
			if (map.getPlaces().get(i).getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) map.getPlaces().get(i);
				if (e.getOwnerId() == p.getIndex()) {
					ans++;
				}
			}
		}
		ans = ans*value;
		return ans;
	}
	
	public void hpStarHandle(Player p, int raw) {
		int x = raw%10;
		int f = raw/10;
		if (f == 1) { // add hp
			p.addHp(x);
		} else if (f == 2) { // lose hp
			p.loseHp(x);
		} else if (f == 3) { // add star
			p.addStar(x);
		} else if (f == 4) { // lose star
			p.loseStar(x);
		}
	}
	
	public void apply(Player p) {
		int i;
		if (type == Consts.FATE_ADD) {
			int x = value%10000;
			int raw = value/10000;
			p.addMoney(x);
			if (raw>9) {
				hpStarHandle(p,raw);
			}
		} else if (type == Consts.FATE_LOSE) {
			int x = value%10000;
			int raw = value/10000;
			p.loseMoney(x);
			if (raw>9) {
				hpStarHandle(p,raw);
			}
		} else if (type == Consts.FATE_GOTOJAIL) {
			p.goToJail();
		} else if (type == Consts.FATE_HOUSEREPAIR) {
			p.loseMoney(houseRepairCost(p));
		}  else if (type == Consts.FATE_PROPERTYTAX) {
			p.loseMoney(propertyTaxCost(p));
		} else if (type == Consts.FATE_RECEIVEEVERY) {
			int x = value*(p.getBoard().getPlayers().size()-1);
			for (i=0;i<p.getBoard().getPlayers().size();i++) {
				Player tp = p.getBoard().getPlayers().get(i);
				if (tp.getIndex() == p.getIndex()) {
					tp.addMoney(x);
				} else {
					tp.loseMoney(value);
				}
			}
		} else if (type == Consts.FATE_GIVEEVERY) {
			int x = value*(p.getBoard().getPlayers().size()-1);
			for (i=0;i<p.getBoard().getPlayers().size();i++) {
				Player tp = p.getBoard().getPlayers().get(i);
				if (tp.getIndex() == p.getIndex()) {
					tp.loseMoney(x);
				} else {
					tp.addMoney(value);
				}
			}
		} else if (type == Consts.FATE_MOVE) {
			if (value < 100) {
				p.getPlace().removePlayer(p);
				p.moveToPlace(value);
				p.setTurnEnd(false);
			} else if (value<200) {
				int x = p.getPlaceIndex();
				for (i=0;i<p.getBoard().getMap().getPlaces().size();i++){
					if (p.getBoard().getMap().getPlaces().get(x).getType() == Consts.PLACE_ESTATE) {
						Estate e = (Estate) p.getBoard().getMap().getPlaces().get(x);
						if (e.getArea() == value) {
							p.getPlace().removePlayer(p);
							p.moveToPlace(x);
							p.setTurnEnd(false);
							
							p.getBoard().getLogger().logPlayerArrive(p);
							p.getBoard().setBroadcastImg(p.getBoard().getMap().getPlace(x).getImg());
							p.getBoard().setBroadcastMsg(p.getName() + "来到了" + p.getBoard().getMap().getPlace(x).getName() + "。");
							break;
						}
					}
					
					x++;
					if (x == p.getBoard().getMap().getPlaces().size()) {
						x=0;
					}
					p.getBoard().getMap().getPlace(x).bypass(p);
				}
			}
		} else if (type == Consts.FATE_CARD) {
			Card c = Factory.genNewCard(value);
			p.addCard(c);
		} else if (type == Consts.FATE_HPSTAR) {
			if (value<100) {
				hpStarHandle(p, value);
			} else if (value>999 && value<10000) {
				int raw1 = value/100;
				int raw2 = value%100;
				hpStarHandle(p, raw1);
				hpStarHandle(p, raw2);
			}
			
		} else if (type == Consts.FATE_CARDLOSE) {
			int cardId = value/10000;
			int loseAmount = value%10000;
			Card c = Factory.genNewCard(cardId);
			p.addCard(c);
			p.loseMoney(loseAmount);
		} else if (type == Consts.FATE_CARDHPSTAR) {
			int cardId = value/100;
			int raw = value%100;
			hpStarHandle(p,raw);
			Card c = Factory.genNewCard(cardId);
			p.addCard(c);
		} else if (type == Consts.FATE_VEHICLEHPSTAR) {
			int x = value/10000;
			int y = value%10000;
			if (x == 1) {
				Vehicle v = Factory.genRandomVehicle(p.getBoard().getMap().getVehicleIds());
				p.receiveVehicle(v);
				p.getBoard().getLogger().log(p.getName() + " 获得了载具 " + v.getName());
			} else if (x == 2) {
				p.loseVehicle();
			} else {
				Vehicle v = Factory.genVehicle(x);
				p.receiveVehicle(v);
				p.getBoard().getLogger().log(p.getName() + " 获得了载具 " + v.getName());
			}
			if (y<100) {
				hpStarHandle(p, y);
			} else if (y>999 && y<10000) {
				int raw1 = y/100;
				int raw2 = y%100;
				hpStarHandle(p, raw1);
				hpStarHandle(p, raw2);
			}
		} else if (type == Consts.FATE_RECEIVEEVERYCARD) {
			List<Card> cards = new ArrayList<>();
			for (i=0;i<p.getBoard().getPlayers().size();i++) {
				Player tp = p.getBoard().getPlayers().get(i);
				if (tp.getIndex() != p.getIndex()) {
					Card c = tp.sendRandom();
					if (c != null) {
						p.getBoard().getLogger().log(tp.getName() + " 送给 " + p.getName() + " 一张 " + c.getName());
						cards.add(c);
					}
				}
			}
			for (i=0;i<cards.size();i++) {
				p.addCard(cards.get(i));
			}
		} else if (type == Consts.FATE_LOSECARDHPSTAR) {
			int raw = value%10000;
			int x = value/10000;
			hpStarHandle(p,raw);
			
			for (i=0;i<x;i++) {
				Card c = p.sendRandom();
				if (c != null) {
					p.getBoard().getLogger().log(p.getName() + " 在慌乱中失去了 " + c.getName());
				}
			}
		} else if (type == Consts.FATE_LOSEHAND) {
			p.setHand(new ArrayList<>());
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getConversation() {
		return conversation;
	}
	public String getConversation(Player p) {
		if (type == Consts.FATE_HOUSEREPAIR) {
			return conversation + "（共支付$" + houseRepairCost(p) + "）";
		} else if (type == Consts.FATE_PROPERTYTAX) {
			return conversation + "（共支付$" + propertyTaxCost(p) + "）";
		} else {
			return conversation;
		}
	}
	public void setConversation(String conversation) {
		this.conversation = conversation;
	}
	
	
}
