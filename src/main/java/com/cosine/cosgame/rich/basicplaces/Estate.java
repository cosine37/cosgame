package com.cosine.cosgame.rich.basicplaces;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.entity.PlaceEntity;

public class Estate extends Place{
	int area; // can be view as color
	int cost;
	int upgradeCost;
	int level;
	int maxLevel;
	
	String img;
	
	int ownerId;
	
	List<Integer> rents;
	
	public Document toDocument() {
		Document doc = super.toDocument();
		doc.append("area", area);
		doc.append("cost", cost);
		doc.append("upgradeCost", upgradeCost);
		doc.append("level", level);
		doc.append("maxLevel", maxLevel);
		doc.append("ownerId", ownerId);
		doc.append("rents", rents);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		super.setFromDoc(doc);
		area = doc.getInteger("area", 0);
		cost = doc.getInteger("cost", 0);
		upgradeCost = doc.getInteger("upgradeCost", 0);
		level = doc.getInteger("level", 0);
		maxLevel = doc.getInteger("maxLevel", 0);
		ownerId = doc.getInteger("ownerId", -1);
		rents = (List<Integer>) doc.get("rents");
	}
	public PlaceEntity toPlaceEntity() {
		PlaceEntity entity = super.toPlaceEntity();
		entity.setArea(area);
		entity.setCost(cost);
		entity.setUpgradeCost(upgradeCost);
		entity.setLevel(level);
		entity.setMaxLevel(maxLevel);
		entity.setOwnerId(ownerId);
		entity.setRents(rents);
		if (ownerId == -1) {
			entity.setOwnerName("");
		} else if (ownerId < board.getPlayers().size()){
			entity.setOwnerName(board.getPlayers().get(ownerId).getName());
		}
		return entity;
	}
	
	public Estate(int id, String name) {
		super(id, name, Consts.PLACE_ESTATE);
		initializeEstate();
	}
	
	public Estate(Document doc, Board board) {
		super(doc,board);
	}
	
	public Estate(int id, String name, int area, int cost, int upgradeCost, int maxLevel, List<Integer> rents) {
		this(id,name);
		setEstateDetails(area, cost, upgradeCost, maxLevel, rents);
	}
	
	public void initializeEstate() {
		ownerId = -1;
		level = 0;
		rents = new ArrayList<>();
	}
	
	public boolean isUnoccupied() {
		return ownerId == -1;
	}
	
	public boolean isOwner(Player p) {
		if (ownerId == -1) {
			return false;
		} else {
			return ownerId == p.getIndex();
		}
		
	}
	
	public void stepOn(Player p) {
		super.stepOn(p);
		if (isUnoccupied()) {
			
		} else {
			
		}
	}
	
	public int getRent() {
		if (level<rents.size()) {
			return rents.get(level);
		} else {
			return 0;
		}
	}
	
	/**
	 * Set basic but important info for estate
	 * 
	 * @param area
	 * @param cost
	 * @param upgradeCost
	 * @param rents
	 */
	public void setEstateDetails(int area, int cost, int upgradeCost, int maxLevel, List<Integer> rents) {
		this.area = area;
		this.cost = cost;
		this.upgradeCost = upgradeCost;
		this.maxLevel = maxLevel;
		this.rents = rents;
	}
	public String getLandMsg(Player player) {
		if (isUnoccupied()) {
			return "你可以花费$" + cost + "购买该地块";
		} else if (player.getIndex() != ownerId) {
			return "你需要支付$" + getRent() + "给" + board.getPlayers().get(ownerId).getName();
		} else if (level < maxLevel){
			return "你可以升级该地块";
		} else {
			return "这是你的地块";
		}
	}
	@Override
	public List<String> getResolveOptions(Player player){
		System.out.println("in estate");
		System.out.println(ownerId);
		List<String> ans = new ArrayList<>();
		if (isUnoccupied()) {
			ans.add("不购买");
			if (player.getMoney()>=cost) {
				ans.add("购买地块");
			}
		} else if (player.getIndex() != ownerId) {
			ans.add("支付租金");
		} else if (level < maxLevel){
			ans.add("不升级");
			if (player.getMoney()>=upgradeCost) {
				ans.add("升级地块");
			}
		} else {
			ans.add("确定");
		}
		return ans;
	}
	
	public void stepOn(Player p, int option) {
		stepOn(p);
		if (isUnoccupied()) {
			if (option == 0) {
				board.getLogger().log(p.getName() + " 没有购买 " + name);
			} else if (option == 1) {
				if (p.getMoney()>=cost) {
					ownerId = p.getIndex();
					p.loseMoney(cost);
					board.getLogger().log(p.getName() + " 花费了$" + cost + "购买了 " + name);
				}
			}
		} else if (p.getIndex() != ownerId) {
			int paidRent = getRent();
			Player owner = board.getPlayers().get(ownerId);
			owner.addMoney(paidRent);
			p.loseMoney(paidRent);
			board.getLogger().log(p.getName() + " 向 " + owner.getName() + " 支付了租金$" + paidRent);
		} else if (level < maxLevel){
			if (option == 0) {
				board.getLogger().log(p.getName() + " 没有加盖 " + name);
			} else if (option == 1) {
				if (p.getMoney()>=upgradeCost && level<maxLevel) {
					p.loseMoney(upgradeCost);
					level++;
					board.getLogger().log(p.getName() + " 花费了$" + cost + "加盖了 " + name + " （当前等级：" + level + "级）");
				}
			}
		} else {

		}
	}

}
