package com.cosine.cosgame.rich;

import java.util.HashMap;

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
	
	public void apply(Player p) {
		int i;
		if (type == Consts.FATE_ADD) {
			p.addMoney(value);
		} else if (type == Consts.FATE_LOSE) {
			p.loseMoney(value);
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
