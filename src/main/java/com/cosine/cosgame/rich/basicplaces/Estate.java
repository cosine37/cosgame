package com.cosine.cosgame.rich.basicplaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;
import com.cosine.cosgame.rich.Place;
import com.cosine.cosgame.rich.Player;
import com.cosine.cosgame.rich.entity.PlaceEntity;

public class Estate extends Place{
	int area; // can be viewed as color
	int cost;
	int upgradeCost;
	int level;
	int maxLevel;
	
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
	public PlaceEntity toPlaceEntity(String username) {
		PlaceEntity entity = super.toPlaceEntity(username);
		entity.setArea(area);
		entity.setCost(cost);
		entity.setTotalCost(totalCost());
		entity.setUpgradeCost(upgradeCost);
		entity.setLevel(level);
		entity.setMaxLevel(maxLevel);
		entity.setOwnerId(ownerId);
		entity.setRents(rents);
		if (area == Consts.AREA_UTILITY) {
			entity.setRent(getRentUtilityDisplay());
		} else {
			entity.setRent(getRent());
		}
		
		if (ownerId == -1) {
			entity.setOwnerName("");
		} else if (ownerId < board.getPlayers().size()){
			entity.setOwnerName(board.getPlayers().get(ownerId).getName());
		}
		
		String areaName = "";
		if (area == Consts.AREA_UTILITY) {
			areaName = board.getMap().getUtilityName();
		} else if (area == Consts.AREA_STATION) {
			areaName = board.getMap().getStationName();
		} else {
			areaName = board.getMap().getAreaNames().get(area);
		}
		entity.setAreaName(areaName);
		
		HashMap<String, String> areaStyle = new HashMap<>();
		if (area < board.getMap().getAreaColors().size()) {
			areaStyle.put("background-color", board.getMap().getAreaColors().get(area));
		}
		entity.setAreaStyle(areaStyle);
		
		if (placeBuff.noBuff()) {
			HashMap<String, String> estateBackground = new HashMap<>();
			if (ownerId == -1) {
				estateBackground.put("background-color", "white");
			} else {
				Player owner = board.getPlayers().get(ownerId);
				if (owner.getName().contentEquals(username)) {
					estateBackground.put("background-color", "LightGreen");
				} else {
					estateBackground.put("background-color", "LightPink");
				}
			}
			entity.setEstateBackground(estateBackground);
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
	}
	
	public boolean monopoly() {
		for (int i=0;i<board.getMap().mapSize();i++) {
			Place p = board.getMap().getPlace(i);
			if (p.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) p;
				if (e.getArea() == area && e.getOwnerId() != ownerId) {
					return false;
				}
			}
			
		}
		return true;
	}
	
	public int numOccupiedInArea() {
		int ans = 0;
		for (int i=0;i<board.getMap().mapSize();i++) {
			Place p = board.getMap().getPlace(i);
			if (p.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) p;
				if (e.getArea() == area && e.getOwnerId() == ownerId) {
					ans++;
				}
			}
		}
		return ans;
	}
	
	public List<Place> otherStations(){
		List<Place> ans = new ArrayList<>();
		for (int i=0;i<board.getMap().mapSize();i++) {
			Place p = board.getMap().getPlace(i);
			if (p.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) p;
				if (e.getArea() == Consts.AREA_STATION && e.getId() != id) {
					ans.add(e);
				}
			}
		}
		return ans;
	}
	public int getRentUtilityDisplay() {
		int ans = rents.get(level);
		int x = numOccupiedInArea();
		if (x>0) x = x-1;
		ans = rents.get(x);
		return ans;
	}
	public int getRent() {
		if (level<rents.size()) {
			int ans = rents.get(level);
			if (area == Consts.AREA_UTILITY) {
				int x = numOccupiedInArea();
				if (x>0) x = x-1;
				ans = rents.get(x);
				
				ans = ans*board.getLastRolled();
			} else if (area == Consts.AREA_STATION) {
				int x = numOccupiedInArea();
				if (x>0) x = x-1;
				ans = rents.get(x);
			} else {
				if (monopoly()) {
					ans = ans*2;
				}
				
			}
			return ans;
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
			return "你可以花费$" + totalCost() + "购买该地块";
		} else if (player.getIndex() != ownerId) {
			// NEW related, place buffs
			if (placeBuff.getDisable() > -1) {
				return "该地被污染，正在整改，所以你不需要支付租金";
			}
			
			// GTA related: no need to pay for rent
			Player owner = board.getPlayers().get(ownerId);
			if (board.getSettings().getUseGTA() == 1) {
				if (owner.isInJail()) {
					return owner.getName() + "在监狱，所以你不需要支付租金";
				} else if (owner.isInWard()) {
					return owner.getName() + "在住院，所以你不需要支付租金";
				}
			}
			// refuse card related
			if (player.getBuff().getFreeRound()>0) {
				return "你拥有免租效果，所以你不需要支付租金";
			}
			
			
			
			if (area == Consts.AREA_UTILITY) {
				return "你掷了一个" + board.getLastRolled() + "，需要支付$" + getRent() + "给" + owner.getName();
			} else {
				return "你需要支付$" + getRent() + "给" + owner.getName();
			}
		} else if (level < maxLevel){
			return "你可以花费$" + upgradeCost + "升级该地块";
		} else {
			return "这是你的地块";
		}
	}
	@Override
	public List<String> getResolveOptions(Player player){
		List<String> ans = new ArrayList<>();
		if (isUnoccupied()) {
			ans.add("不购买");
			if (player.getMoney()>=totalCost()) {
				ans.add("购买地块");
			}
		} else if (player.getIndex() != ownerId) {
			Player owner = board.getPlayers().get(ownerId);
			boolean flag = true;
			// NEW related, place buffs
			if (placeBuff.getDisable() > -1) {
				ans.add("好臭啊（恼）");
				flag = false;
			}
			// GTA related: no need to pay for rent
			if (board.getSettings().getUseGTA() == 1) {
				if (owner.isInJail()) {
					ans.add("活该！");
					flag = false;
				} else if (owner.isInWard()) {
					ans.add("真遗憾呐（笑）");
					flag = false;
				}
			}
			// refuse card related
			if (player.getBuff().getFreeRound()>0) {
				ans.add("漂亮！");
				flag = false;
			}
			if (flag) {
				ans.add("支付租金");
			}
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
	public void preStepOn(Player p) {
		if (area == Consts.AREA_UTILITY && isUnoccupied() == false) {
			p.setPhase(Consts.PHASE_UTILITY);
		}
	}
	public void stepOn(Player p, int option) {
		stepOn(p);
		if (isUnoccupied()) {
			if (option == 0) {
				board.getLogger().log(p.getName() + " 没有购买 " + name);
			} else if (option == 1) {
				if (p.getMoney()>=totalCost()) {
					ownerId = p.getIndex();
					board.getLogger().log(p.getName() + " 花费了$" + totalCost() + "购买了 " + name);
					
					board.setBroadcastImg("avatar/head_"+p.getAvatarId());
					board.setBroadcastMsg(p.getName() + "购买了" + name);
					p.loseMoney(totalCost());
				}
			}
		} else if (p.getIndex() != ownerId) {
			
			Player owner = board.getPlayers().get(ownerId);
			boolean flag = true;
			// NEW related, place buffs
			if (placeBuff.getDisable() > -1) {
				flag = false;
				board.getLogger().log(name + " 被污染，正在整改，所以" + p.getName() + "无需支付租金");
					
				board.setBroadcastImg("avatar/head_"+p.getAvatarId());
				board.setBroadcastMsg(name + "被污染，正在整改，所以" + p.getName() + "无需支付租金。");
			}
			// GTA related: no need to pay for rent
			if (board.getSettings().getUseGTA() == 1) {
				if (owner.isInJail()) {
					flag = false;
					board.getLogger().log(owner.getName() + " 在监狱，所以 " + p.getName() + " 无需支付租金");
					
					board.setBroadcastImg("avatar/head_"+p.getAvatarId());
					board.setBroadcastMsg(owner.getName() + "在监狱，所以" + p.getName() + "无需支付租金。");
				} else if (owner.isInWard()) {
					flag = false;
					board.getLogger().log(owner.getName() + " 在住院，所以 " + p.getName() + " 无需支付租金");
					
					board.setBroadcastImg("avatar/head_"+p.getAvatarId());
					board.setBroadcastMsg(owner.getName() + "在住院，所以" + p.getName() + "无需支付租金。");
				}
			}
			// refuse card related
			if (p.getBuff().getFreeRound()>0) {
				board.getLogger().log(p.getName() + " 拥有免租效果，所以无需支付租金");
					
				board.setBroadcastImg("avatar/head_"+p.getAvatarId());
				board.setBroadcastMsg(p.getName() + "拥有免租效果，所以无需支付租金。");
				flag = false;
			}
			if (flag) {
				int paidRent = getRent();
				if (area == Consts.AREA_UTILITY) {
					board.getLogger().logPlayerRoll(p);
				}
				board.getLogger().log(p.getName() + " 向 " + owner.getName() + " 支付了租金$" + paidRent);
				
				board.setBroadcastImg("avatar/head_"+p.getAvatarId());
				board.setBroadcastMsg(p.getName() + "向" + owner.getName() + "支付了$" + paidRent + "。");
				
				owner.addMoney(paidRent);
				p.loseMoney(paidRent);
			}
			
		} else if (level < maxLevel){
			if (option == 0) {
				board.getLogger().log(p.getName() + " 没有加盖 " + name);
			} else if (option == 1) {
				if (p.getMoney()>=upgradeCost && level<maxLevel) {
					level++;
					board.getLogger().log(p.getName() + " 花费了$" + upgradeCost + "加盖了 " + name + " （当前等级：" + level + "级）");
					
					board.setBroadcastImg("avatar/head_"+p.getAvatarId());
					board.setBroadcastMsg(p.getName() + "加盖了" + name + "。");
					
					p.loseMoney(upgradeCost);
				}
			}
		} else {

		}
		
		if (area == Consts.AREA_STATION) {
			p.setTurnEnd(false);
			p.setPhase(Consts.PHASE_STATION);
		}
		
	}
	
	public List<String> getStationOptions(){
		List<String> ans = new ArrayList<>();
		List<Place> oss = otherStations();
		ans.add("不移动");
		if (board.getNewsBuff().getNoStationMove() == 1) {
			
		} else {
			for (int i=0;i<oss.size();i++) {
				ans.add("至" + oss.get(i).getName());
			}
		}
		
		return ans;
	}
	public void resolveStation(Player p, int option) {
		List<Place> oss = otherStations();
		if (option > 0) {
			int x = option-1;
			removePlayer(p);
			
			int t = id;
			int newId = oss.get(x).getId();
			while (t!=newId) {
				t++;
				if (t == board.getMap().mapSize()) {
					t = 0;
				}
				board.getMap().getPlace(t).bypass(p);
			}
			
			p.moveToPlace(newId);
			board.getLogger().log(p.getName() + " 移动到了 " + oss.get(x).getName());
			
			board.setBroadcastImg(oss.get(x).getImg());
			board.setBroadcastMsg(p.getName() + "移动到了" + oss.get(x).getName());
			
			// add se
			String input = oss.get(x).getImg(); 
			int slashIndex = input.indexOf('/');
			String mapName = (slashIndex != -1) ? input.substring(0, slashIndex) : input;
			String seSrc = "/sound/Rich/" + mapName + "/" + newId + ".mp3";
			board.addSes(seSrc);
			board.setSesPlayer(p.getIndex());
		}
	}
	
	// Begin gta related
	public void destroyHouse() {
		level = 0;
	}
	
	public void downgradeHouse() {
		level--;
	}
	public int totalCost() {
		int ans = cost;
		if (level>0) {
			ans = ans+level*upgradeCost;
		}
		return ans;
	}
	// End gta related
	// Begin new related
	public void removeOwner() {
		ownerId = -1;
	}
	// End new related
	
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public List<Integer> getRents() {
		return rents;
	}

	public void setRents(List<Integer> rents) {
		this.rents = rents;
	}
}
