package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.rich.account.Account;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.entity.AvatarEntity;
import com.cosine.cosgame.rich.entity.PlayerEntity;
import com.cosine.cosgame.rich.gta.cards.*;

public class Player {
	protected String name;
	protected int hp;
	protected int energy;
	protected int san;
	protected int money;
	protected int star;
	protected int salary;
	protected int phase;
	protected int index;
	protected int rollDisplay;
	protected boolean confirmed;
	protected boolean inJail;
	protected boolean turnEnd;
	protected int jailRound;
	protected int avatarId;
	protected List<Card> hand;
	protected List<Card> deck;
	protected List<Card> discard;
	protected List<Integer> owned;
	protected int placeIndex;
	
	// GTA related
	protected boolean inWard;
	protected Buff buff;
	protected Vehicle vehicle;
	protected CardGenerator cardGenerator;
	
	protected Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("name",name);
		doc.append("hp",hp);
		doc.append("energy",energy);
		doc.append("san",san);
		doc.append("money",money);
		doc.append("star",star);
		doc.append("salary",salary);
		doc.append("phase",phase);
		doc.append("index",index);
		doc.append("confirmed",confirmed);
		doc.append("owned", owned);
		doc.append("rollDisplay", rollDisplay);
		doc.append("inJail", inJail);
		doc.append("jailRound", jailRound);
		doc.append("inWard", inWard);
		doc.append("turnEnd", turnEnd);
		doc.append("buffs", buff.getBuffs());
		doc.append("vehicle", vehicle.getId());
		doc.append("cardGenerator", cardGenerator.toDocument());
		Account account = new Account();
		account.getFromDB(name);
		Avatar avatar = Factory.genAvatar(account.getChosenAvatar());
		doc.append("avatarId", avatar.getId());
		
		List<Integer> handDocList = new ArrayList<>();
		for (i=0;i<hand.size();i++){
			handDocList.add(hand.get(i).getId()*100+hand.get(i).getLevel());
		}
		doc.append("hand",handDocList);
		List<Integer> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).getId()*100+deck.get(i).getLevel());
		}
		doc.append("deck",deckDocList);
		List<Integer> discardDocList = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			discardDocList.add(discard.get(i).getId()*100+discard.get(i).getLevel());
		}
		doc.append("discard",discardDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		name = doc.getString("name");
		hp = doc.getInteger("hp",0);
		energy = doc.getInteger("energy",0);
		san = doc.getInteger("san",0);
		money = doc.getInteger("money",0);
		star = doc.getInteger("star",0);
		salary = doc.getInteger("salary",0);
		phase = doc.getInteger("phase",0);
		index = doc.getInteger("index",0);
		confirmed = doc.getBoolean("confirmed",false);
		owned = (List<Integer>) doc.get("owned");
		rollDisplay = doc.getInteger("rollDisplay", 0);
		inJail = doc.getBoolean("inJail", false);
		jailRound = doc.getInteger("jailRound", 0);
		inWard = doc.getBoolean("inWard", false);
		turnEnd = doc.getBoolean("turnEnd", turnEnd);
		avatarId = doc.getInteger("avatarId", -1);
		List<Integer> buffs = (List<Integer>) doc.get("buffs");
		buff = new Buff(buffs);
		int vehicleId = doc.getInteger("vehicle", -1);
		vehicle = Factory.genVehicle(vehicleId);
		if (avatarId == -1) {
			Account account = new Account();
			account.getFromDB(name);
			Avatar avatar = Factory.genAvatar(account.getChosenAvatar());
			if (avatar != null) {
				avatarId = avatar.getId();
			}
		}
		List<Integer> handDocList = (List<Integer>)doc.get("hand");
		hand = new ArrayList<>();
		for (i=0;i<handDocList.size();i++){
			Card c = Factory.genCard(handDocList.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			hand.add(c);
		}
		List<Integer> deckDocList = (List<Integer>)doc.get("deck");
		deck = new ArrayList<>();
		for (i=0;i<deckDocList.size();i++){
			Card c = Factory.genCard(deckDocList.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			deck.add(c);
		}
		List<Integer> discardDocList = (List<Integer>)doc.get("discard");
		discard = new ArrayList<>();
		for (i=0;i<discardDocList.size();i++){
			Card c = Factory.genCard(discardDocList.get(i));
			c.setPlayer(this);
			c.setBoard(board);
			discard.add(c);
		}
		Document cardGeneratorDoc = (Document) doc.get("cardGenerator");
		cardGenerator = new CardGenerator();
		cardGenerator.setPlayer(this);
		cardGenerator.setFromDoc(cardGeneratorDoc);
	}
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setIndex(index);
		entity.setMoney(money);
		entity.setName(name);
		entity.setConfirmed(confirmed);
		entity.setPhase(phase);
		entity.setSalary(salary);
		entity.setOwned(owned);
		entity.setInJail(inJail);
		entity.setJailRound(jailRound);
		entity.setHp(hp);
		entity.setStar(star);
		entity.setBuffs(buff.getBuffs());
		entity.setHandSize(hand.size());
		entity.setTotalMoney(board.getBank().getTotal(this));
		entity.setSaving(board.getBank().getSaving(this));
		if (vehicle == null || vehicle.getId() == -1) {
			entity.setVehicleId(-1);
			entity.setVehicleName("没有");
		} else {
			entity.setVehicleId(vehicle.getId());
			entity.setVehicleName(vehicle.getName());
		}
		
		
		Account account = new Account();
		account.getFromDB(name);
		Avatar avatar = Factory.genAvatar(account.getChosenAvatar());
		if (avatar != null) {
			AvatarEntity avatarEntity = avatar.toAvatarEntity(vehicle);
			int n = -1;
			if (inJail) {
				n = board.getMap().getJailPlayers().size();
			} else if (inWard) {
				n = board.getMap().getWardPlayers().size();
			} 	
			else if (board.getMap().getPlace(placeIndex) != null) {
				n = board.getMap().getPlace(placeIndex).getPlayersOn().size();
			}
			if (n == 1) {
				avatarEntity.getAvatarBlockStyle().put("margin-left", "40px");
			} else if (n == 2) {
				avatarEntity.getAvatarBlockStyle().put("margin-left", "13px");
			} else if (n == 3) {
				avatarEntity.getAvatarBlockStyle().put("margin-left", "0px");
			} else if (n == 4) {
				if (index == board.getMap().getPlace(placeIndex).getPlayersOn().get(0).getIndex()) {
					avatarEntity.getAvatarBlockStyle().put("margin-left", "0px");
				} else {
					avatarEntity.getAvatarBlockStyle().put("margin-left", "-14px");
				}
			} else if (n == 5) {
				if (index == board.getMap().getPlace(placeIndex).getPlayersOn().get(0).getIndex()) {
					avatarEntity.getAvatarBlockStyle().put("margin-left", "0px");
				} else {
					avatarEntity.getAvatarBlockStyle().put("margin-left", "-21px");
				}
			}
			entity.setAvatar(avatarEntity);
			entity.setAvatarOrigin(avatar.toAvatarEntity(null));
		}
		return entity;
	}
	
	public Player() {
		hand = new ArrayList<>();
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		buff = new Buff();
		vehicle = new Vehicle();
		cardGenerator = new CardGenerator();
	}
	
	public void addMoney(int x) {
		money = money+x;
	}
	
	public void loseMoney(int x) {
		int cash = this.getMoney();
		if (cash < x) {
			// If cash is not enough to pay the amount, deduct money 
			// from savings account at bank.
			int savingDeduction = x - cash;
			board.getBank().withdraw(this, savingDeduction);
		}
		money = money-x;
		
		if (hand != null) {
			for (int i=0;i<hand.size();i++) {
				if (hand.get(i).isPassive()) {
					hand.get(i).onLoseMoney(x);
				}
			}
		}
	}
	
	public void addSalary() {
		money = money+salary;
	}
	
	public void addSalary(int x) {
		salary = salary+x;
	}
	
	public void fullRestore() {
		hp = getMaxHp();
	}
	
	public int getMaxHp() {
		return Consts.GTA_MAXHP;
	}
	
	public void addHp(int x) {
		hp = hp+x;
		// TODO: may need to update here
		if (hp>getMaxHp()) hp = getMaxHp();
		
		board.getLogger().logRecover(this, x);
	}
	
	public void loseHp(int x) {
		int value = x;
		// GTA related, restrict lose value
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).capHurt() && value>1) {
				value=1;
				board.getLogger().log("因为" + hand.get(i).getName() + "的效果，失去的生命值改为 1 点");
			}
		}
		hp = hp-value;
		for (int i=0;i<hand.size();i++) {
			if (value>0) {
				hand.get(i).onLoseHp(value);
			}
		}
		if (hp<0) hp = 0;
	}
	
	public void addStar(int x) {
		star = star+x;
		// TODO: may need to update here
		if (star>Consts.GTA_MAXSTAR) star = Consts.GTA_MAXSTAR;
	}
	
	public void loseStar(int x) {
		star = star-x;
		if (star<0) star = 0;
	}
	
	public void receiveVehicle(Vehicle v) {
		setVehicle(v);
	}
	
	public void loseVehicle() {
		vehicle = new Vehicle();
	}
	
	public boolean hasVehicle() {
		if (vehicle.getId()>0) return true; else return false;
	}
	
	public void hurt(Player victim, int x) {
		victim.loseHp(x);
	
		// GTA related, passive effects
		for (int i=0;i<hand.size();i++) {
			hand.get(i).onHurt(x);
		}
	}
	
	public void moveToPlace(int x) {
		placeIndex = x;
		board.getMap().getPlace(x).addPlayerOn(this);
	}
	
	public void startGame() {
		money = board.getSettings().getStartMoney();
		salary = board.getSettings().getStartSalary();
		moveToPlace(0);
		phase = Consts.PHASE_OFFTURN;
		inJail = false;
		
		// below are expansion settings
		if (board.getSettings().getUseGTA()>0) {
			hp = Consts.GTA_MAXHP;
			star = 0;
			for (int i=0;i<index;i++) {
				addRandomCard();
			}
			// TODO: test cards here
			//addRandomCard();
			//addRandomCard();
			//hand.add(new CardDual());
			//hand.add(new CardFrenzyBone());
		}
	}
	
	public void startTurn() {
		// Step 1: basic phase and log updates
		phase = Consts.PHASE_ROLL;
		board.getLogger().logStartTurn(this);
		
		// Step 2: jail related
		if (inJail) {
			jailRound++;
			board.getLogger().logJailRound(this, jailRound);
		}
	}
	
	public String myNextPlaceName() {
		int temp = rollDisplay;
		int totalSteps = 0;
		while (temp>0) {
			totalSteps = totalSteps+temp%10;
			temp = temp/10;
		}
		
		// GTA related, add steps
		if (buff.getRollAdd() > 0) {
			totalSteps = totalSteps+buff.getRollAdd();
		}
		
		Place place = board.getMap().getPlaceAfter(placeIndex,totalSteps);
		if (buff.getTeleport() != -1) {
			place = board.getMap().getPlace(buff.getTeleport());
		}
		
		if (place == null) return ""; else return place.getName();
	}
	
	public boolean isGoingToJail() {
		if (board.getSettings().getUseGTA() != 1) return false;
		if (buff.getTeleport() != -1) return false;
		int temp = rollDisplay;
		int totalSteps = 0;
		while (temp>0) {
			totalSteps = totalSteps+temp%10;
			temp = temp/10;
		}
		if (buff.getRollAdd() > 0) {
			totalSteps = totalSteps+buff.getRollAdd();
		}
		if (totalSteps <= star && star>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public String myCurrentPlaceName() {
		Place place = board.getMap().getPlace(placeIndex);
		if (place == null) return ""; else return place.getName();
	}
	
	public String myLandMsg() {
		Place place = board.getMap().getPlace(placeIndex);
		if (place == null) return ""; else return place.getLandMsg(this);
	}
	
	public void goToJail() {
		// Step 0: GTA related, in case user have substitude;
		int i;
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).clearJail()) {
				Card c = hand.get(i);
				board.getLogger().log(name + " 消耗了一张 " + c.getName() + " ，逃过了入狱并且清空了通缉值");
				board.setBroadcastImg("card/"+c.getId());
				board.setBroadcastMsg(name + "消耗了一张" + c.getName() + "，逃过了入狱并且清空了通缉值。谢谢你，" + c.getName() + "！");
				hand.remove(i);
				star = 0;
				return;
			}
		}
		
		// Step 1: remove from the current place and add player in jail
		board.getMap().getPlace(placeIndex).removePlayer(this);
		board.getMap().addToJail(this);
		
		// Step 2: set related status
		inJail = true;
		jailRound = 0;
		
		// Step 3: GTA related
		if (board.getSettings().getUseGTA() == 1) {
			board.getLogger().logGoToJailGTA(this);
			board.setBroadcastImg("avatar/head_"+avatarId);
			if (star<=2) {
				board.setBroadcastMsg(name + "因轻罪入狱。");
			} else if (star<=4) {
				board.setBroadcastMsg(name + "因中罪入狱，失去载具和除出狱卡外的随机2张手牌。");
				this.discardRandom(2,10);
				loseVehicle();
			} else if (star<=6) {
				board.setBroadcastMsg(name + "因重罪入狱，失去载具和所有手牌且不得提前保释。");
				hand = new ArrayList<>();
				loseVehicle();
			}
		}
		
	}
	
	public void outOfJail() {
		// Step 1: remove from the jail and add to jail index
		board.getMap().removeFromJail(this);
		board.getMap().getJail().addPlayerOn(this);
		
		// Step 2: set related status
		inJail = false;
	}
	
	public void goToWard() {
		// Step 0: GTA related, in case user have Mipha;
		int i;
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).clearWard()) {
				Card c = hand.get(i);
				board.getLogger().log(name + " 消耗了一张 " + c.getName() + " ，回复了所有生命值");
				board.setBroadcastImg("card/"+c.getId());
				board.setBroadcastMsg(name + "消耗了一张" + c.getName() + "，回复了所有生命值。谢谢你，" + c.getName() + "！");
				hand.remove(i);
				fullRestore();
				return;
			}
		}
		
		// Step 1: remove from the current place and add player in ward
		board.getLogger().logGoToWard(this);
		board.setBroadcastImg("avatar/head_"+avatarId);
		board.setBroadcastMsg(name + "眼前一黑，被送进ICU。");
		
		board.getMap().getPlace(placeIndex).removePlayer(this);
		board.getMap().addToWard(this);
		
		// Step 2: set related status
		inWard = true;
		
		// Step 3: GTA related, exhaust cards onward
		for (i=hand.size()-1;i>=0;i--) {
			Card c = hand.get(i);
			if (c.exhaustOnWard()) {
				hand.remove(i);
				board.getLogger().log(name + " 的 " + c.getName() + " 被消耗了");
			}
		}
		
	}
	
	public void outOfWard() {
		// Step 1: remove from the ward and add to ward index
		board.getMap().removeFromWard(this);
		board.getMap().getHospital().addPlayerOn(this);
		
		// Step 2: set related status
		inWard = false;
		
		// Step 3: pay treatment fee
		int fee = Consts.GTA_TREATMENTFEE*hp;
		
		int deduction = 0;
		for (int i=0;i<hand.size();i++) {
			if (hand.get(i).isPassive()) {
				deduction = deduction+hand.get(i).wardFeeDeduction();
			}
		}
		if (deduction > 0) {
			board.getLogger().log(name + " 的 医保卡 共减免医疗费 $" +deduction);
		}
		fee = fee-deduction;
		
		if (money<1) fee = 0; else if (money<fee) fee = money-1;
		
		
		// Step 4: logs
		board.getLogger().logOutOfWard(this, fee);
		
		board.setBroadcastImg("avatar/head_"+avatarId);
		if (deduction > 0) {
			board.setBroadcastMsg(name + "出院了，住院期间花费$" + fee + "，医保卡为其减免了$" + deduction + "。");
		} else {
			board.setBroadcastMsg(name + "出院了，住院期间花费$" + fee + "。");
		}
		
		
		loseMoney(fee);
	}
	
	public void playCard(int x, int rawOptions) {
		if (x>=0 && x<hand.size()) {
			Card c = hand.remove(x);
			if (rawOptions == Consts.CARD_OPTION_THROW) {
				board.getLogger().logThrowCard(this,c);
				
				board.setBroadcastImg("card/"+c.getId());
				board.setBroadcastMsg(name + "丢弃了" + c.getName() + "。");
				
				c.onThrow();
			} else {
				board.getLogger().logPlayCard(this,c);
				c.play(rawOptions);
				
				// TODO: put the card in discard
				if (c.isExhaust() == false) {
					if (c.returnHand()) {
						if (fullHand()) {
							board.getLogger().log(name + " 手牌已满，无法将 " + c.getName() + " 返回手牌。");
						} else {
							addCard(c);
							board.getLogger().log(name + " 将 " + c.getName() + " 返回了手牌。");
						}
					}
				}
			}
			
		}
	}
	
	public void playCardRaw(int option) {
		if (option>=10000 && option<20000) {
			int x = option/100;
			x = x%100;
			int rawOptions = option%100;
			playCard(x, rawOptions);
		}
	}
	
	public void discardRandom(int num, int exception) {
		int i,x;
		List<Card> setAside = new ArrayList<>();
		for (i=0;i<hand.size();i++) {
			if (hand.get(i).getId() == exception) {
				setAside.add(hand.remove(i));
			}
		}
		
		Random rand = new Random();
		for (i=0;i<num;i++) {
			if (hand.size() == 0) break;
			x = rand.nextInt(hand.size());
			hand.remove(x);
		}
		
		for (i=0;i<setAside.size();i++) {
			hand.add(setAside.get(i));
		}
	}
	
	public void discardRandom(int num) {
		discardRandom(num,-1);
	}
	
	public Card sendRandom() {
		if (hand.size()>0) {
			Random rand = new Random();
			int x = rand.nextInt(hand.size());
			Card c = hand.remove(x);
			return c;
		} else {
			return null;
		}
	}
	
	public int handRarity() {
		int ans = 0;
		int i;
		int maxRarity = 0;
		int count = 0;
		for (i=0;i<hand.size();i++) {
			 if (hand.get(i).getRarity() > maxRarity) {
				 count = 1;
				 maxRarity = hand.get(i).getRarity();
			 } else if (hand.get(i).getRarity() == maxRarity) {
				 count++;
			 }
		}
		ans = maxRarity*10+count;
		return ans;
	}
	
	public String handRarityStr() {
		String s = "";
		int i;
		int maxRarity = 0;
		int count = 0;
		for (i=0;i<hand.size();i++) {
			 if (hand.get(i).getRarity() > maxRarity) {
				 count = 1;
				 maxRarity = hand.get(i).getRarity();
			 } else if (hand.get(i).getRarity() == maxRarity) {
				 count++;
			 }
		}
		if (count == 0) {
			s = "没有手牌";
		} else {
			if (maxRarity == 0) {
				s = "常见";
			} else if (maxRarity == 1) {
				s = "罕见";
			} else if (maxRarity == 2) {
				s = "稀有";
			} else if (maxRarity == 3) {
				s = "史诗";
			}
			s = "" + count + "张" + s + "手牌";
		}
		return s;
	}
	
	public List<String> getOptions(){
		List<String> ans = new ArrayList<>();
		if (phase == Consts.PHASE_ROLL) {
			if (inJail) {
				ans.add("越狱");
				if (money>=board.getMap().getBailCost()) {
					if (board.getSettings().getUseGTA() == 1) {
						if (star>4 && jailRound<3) {
							ans.add("等待");
						} else {
							ans.add("保释");
						}
					} else {
						ans.add("保释");
					}
					
				}
			} else if (inWard) {
				if (hp < Consts.GTA_MAXHP) {
					ans.add("治疗");
				}
				if (hp>0) {
					ans.add("出院！");
				}
				
			}
			else {
				if (vehicle != null && vehicle.getId()>-1) {
					ans.add("掷一个骰子");
					ans.add("掷两个骰子");
				} else {
					ans.add("掷骰");
				}
			}
			
		} else if (phase == Consts.PHASE_MOVE) {
			ans.add("移动");
		} else if (phase == Consts.PHASE_RESOLVE) {
			Place p = board.getMap().getPlace(placeIndex);
			ans = p.getResolveOptions(this);
		} else if (phase == Consts.PHASE_ESCAPE) {
			ans.add("确定");
		} else if (phase == Consts.PHASE_UTILITY) {
			ans.add("确定");
		} else if (phase == Consts.PHASE_STATION) {
			Place p = board.getMap().getPlace(placeIndex);
			if (p.getType() == Consts.PLACE_ESTATE) {
				Estate e = (Estate) p;
				if (e.getArea() == Consts.AREA_STATION) {
					ans = e.getStationOptions();
				} else {
					ans.add("不移动");
				}
			} else {
				ans.add("不移动");
			}
		}
		return ans;
	}
	
	public void phaseRoll(int option) {
		if (phase != Consts.PHASE_ROLL) return;
		if (option == 0) {
			if (inJail) {
				board.roll(this);
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				if (board.getMap().escapedFromJail()) {
					if (board.getSettings().getUseGTA() == 1) {
						addStar(1);
						board.getLogger().logEscapeSuccessGTA(this);
						board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，成功越狱，但是增加1点通缉值！");
					} else {
						board.getLogger().logEscapeSuccess(this);
						board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，成功越狱！");
					}

					phase = Consts.PHASE_ESCAPE;
					outOfJail();

					board.setBroadcastImg("dice/"+rollDisplay);
					
				} else {
					board.getLogger().logEscapeFail(this);
					phase = Consts.PHASE_ESCAPE;
					
					board.setBroadcastImg("dice/"+rollDisplay);
					board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，越狱失败！");
				}
			} else if (inWard) {
				if (hp<Consts.GTA_MAXHP) {
					hp++;
					
					board.getLogger().logWardTreatment(this);
					board.setBroadcastImg("avatar/head_"+avatarId);
					board.setBroadcastMsg(name + "正在病房接受治疗。");
					
					board.getLogger().logEndTurn(this);
					board.nextPlayer();
				} else {
					outOfWard();
				}
			}
			
			else { // regular roll and move
				if (board.getSettings().getUseGTA() == 1) { // GTA ward handles
					board.wardCheck();
					if (inWard) {
						board.getLogger().logEndTurn(this);
						board.nextPlayer();
						return;
					}
				}
				
				board.roll(this);
				phase = Consts.PHASE_MOVE;
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				
				
				String broadcastMsg = name + "掷了一个" + rollDisplay;
				if (buff.getTeleport() > -1) {
					broadcastMsg = name + "将会传送到" + myNextPlaceName();
					board.setBroadcastImg("card/40");
				} else {
					board.setBroadcastImg("dice/"+rollDisplay);
					if (buff.getRollAdd() > 0) {
						broadcastMsg = broadcastMsg + "，还会额外移动" + buff.getRollAdd() + "步";
					}
					if (isGoingToJail()) {
						broadcastMsg = broadcastMsg + "，将会被抓进监狱";
					} else {
						broadcastMsg = broadcastMsg + "，将会移动到" + myNextPlaceName();
					}
					
				}
				broadcastMsg = broadcastMsg + "。";
				board.setBroadcastMsg(broadcastMsg);
			}
			
		} else if (option == 1) {
			if (inJail) {// bail option
				int bailCost = board.getMap().getBailCost();
				if (board.getSettings().getUseGTA() == 1) {
					if (star>4 && jailRound<3) { // cannot bail if star>4
						board.getLogger().logJailWaitGTA(this);
						board.setBroadcastImg("avatar/head_"+avatarId);
						board.setBroadcastMsg(name + "不得提前保释，正在猛啃窝窝头。");
						
						board.getLogger().logEndTurn(this);
						board.nextPlayer();
						return;
					}
					if (star > 0) bailCost = bailCost*star;
				}
				
				if (money>=bailCost) {
					if (board.getSettings().getUseGTA() == 1) {
						star = 0;
						board.getLogger().logBaitGTA(this, bailCost);
						board.setBroadcastMsg(name + "花费了$" + bailCost + "把自己保释了，通缉值清零。");
					} else {
						board.getLogger().logBait(this, bailCost);
						board.setBroadcastMsg(name + "花费了$" + bailCost + "把自己保释了。");
					}
					outOfJail();
					loseMoney(bailCost);

					board.setBroadcastImg("avatar/head_"+avatarId);
					
				}
			} else if (inWard) {
				if (hp<Consts.GTA_MAXHP && hp>0) {
					outOfWard();
				} 
			}
			
			else if (vehicle != null && vehicle.getId() > -1){
				if (board.getSettings().getUseGTA() == 1) { // GTA ward handles
					board.wardCheck();
					if (inWard) {
						board.getLogger().logEndTurn(this);
						board.nextPlayer();
						return;
					}
				}
				
				board.roll(this,2);
				phase = Consts.PHASE_MOVE;
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				
				int r1 = rollDisplay/10;
				int r2 = rollDisplay%10;
				
				
				String broadcastMsg = "";
				if (buff.getTeleport()>-1) {
					broadcastMsg = name + "将会传送到" + myNextPlaceName();
					board.setBroadcastImg("card/40");
				} else {
					board.setBroadcastImg("dice/"+rollDisplay);
					if (r1 == 0) {
						broadcastMsg = name + "掷了一个" + rollDisplay;
					} else {
						broadcastMsg = name + "掷了" + r1 + "和" + r2;
					}
					if (buff.getRollAdd() > 0) {
						broadcastMsg = broadcastMsg + "，还会额外移动" + buff.getRollAdd() + "步";
					}
					if (isGoingToJail()) {
						broadcastMsg = broadcastMsg + "，将会被抓进监狱";
					} else {
						broadcastMsg = broadcastMsg + "，将会移动到" + myNextPlaceName();
					}
				}
				broadcastMsg = broadcastMsg + "。";
				board.setBroadcastMsg(broadcastMsg);
			}
		} else if (option>=10000 && option<20000) { // play cards
			playCardRaw(option);
		}
		
		else if (option>100000) {
			int x = option-100000;
			if (inJail) {
				board.setLastRolled(x);
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				if (board.getMap().escapedFromJail()) {
					if (board.getSettings().getUseGTA() == 1) {
						addStar(1);
						board.getLogger().logEscapeSuccessGTA(this);
						board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，成功越狱，但是增加1点通缉值！");
					} else {
						board.getLogger().logEscapeSuccess(this);
						board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，成功越狱！");
					}
					phase = Consts.PHASE_ESCAPE;
					outOfJail();
					
					board.setBroadcastImg("dice/"+rollDisplay);
				} else {
					board.getLogger().logEscapeFail(this);
					phase = Consts.PHASE_ESCAPE;
					
					board.setBroadcastImg("dice/"+rollDisplay);
					board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，越狱失败！");
				}
			} else { // regular roll and move
				board.setLastRolled(x);
				phase = Consts.PHASE_MOVE;
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				
				board.setBroadcastImg("dice/"+rollDisplay);
				board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，将会来到" + myNextPlaceName());
			}
			
			
		}
	}
	
	public void phaseMove(int option) {
		if (phase != Consts.PHASE_MOVE) return;
		if (option == 0) {
			int temp = rollDisplay;
			int totalSteps = 0;
			while (temp>0) {
				totalSteps = totalSteps+temp%10;
				temp = temp/10;
			}
			if (buff.getRollAdd() > 0) {
				totalSteps = totalSteps+buff.getRollAdd();
				//buff.clearRollAdd();
			}
			
			if (board.getSettings().getUseGTA() == 1) { // GTA add steps and go to jail handles
				
				if (isGoingToJail()) {
					goToJail();
					
					if (inJail) {
						board.getLogger().logEndTurn(this);
						board.nextPlayer();
						return;
					}
					
				}
			}
			
			board.getMap().getPlace(placeIndex).removePlayer(this);
			
			int t = placeIndex;
			if (buff.getTeleport() > -1) { // teleport
				t = buff.getTeleport();
				buff.clearTeleport();
			} else { // regular move
				for (int i=totalSteps-1;i>=0;i--) {
					t = (t+1)%board.getMap().mapSize();
					if (i!=0) board.getMap().getPlace(t).bypass(this);
				}
			}
			phase = Consts.PHASE_RESOLVE;
			moveToPlace(t);
			
			
			if (buff.getRollAdd() > 0) {
				buff.clearRollAdd();
			}
			
			if (board.getMap().getPlace(t).getType() == Consts.PLACE_STARTPOINT || board.getMap().getPlace(t).getType() == Consts.PLACE_CARDGAINER) {
				board.setBroadcastImg(board.getMap().getPlace(t).getDetail().getImg());
			} else {
				board.setBroadcastImg(board.getMap().getPlace(t).getImg());
			}
			
			board.setBroadcastMsg(name + "来到了" + board.getMap().getPlace(t).getName() + "。");
			
			board.getLogger().logPlayerArrive(this);
			
			board.getMap().getPlace(t).preStepOn(this);
			//board.setLastRolled(0);
			rollDisplay = 0;
		} else if (option>=10000 && option<20000) { // play cards
			playCardRaw(option);
		}
	}
	
	public void phaseResolve(int option) {
		if (phase != Consts.PHASE_RESOLVE) return;
		if (option>=10000 && option<20000) { // play cards
			playCardRaw(option);
		} else {
			turnEnd = true;
			board.getMap().getPlace(placeIndex).stepOn(this, option);
			if (turnEnd) {
				board.getLogger().logEndTurn(this);
				board.nextPlayer();
			}
		}
	}
	
	public void phaseEscape(int option) {
		if (phase != Consts.PHASE_ESCAPE) return;
		if (inJail) {
			// TODO: custom here
			if (jailRound == 3) {
				int bailCost = board.getMap().getBailCost();
				if (board.getSettings().getUseGTA() == 1) {
					if (star > 0) bailCost = bailCost*star;
				}
				
				
				phase = Consts.PHASE_ROLL;
				if (board.getSettings().getUseGTA() == 1) {
					star = 0;
					board.getLogger().logBaitGTA(this, bailCost);
					board.setBroadcastMsg(name + "花费了$" + bailCost + "强制保释了自己，通缉值清零。");
				} else {
					board.getLogger().logBait(this, bailCost);
					board.setBroadcastMsg(name + "花费了$" + bailCost + "强制保释了自己。");
				}
				
				board.setBroadcastImg("avatar/head_"+avatarId);
				
				loseMoney(bailCost);
				outOfJail();
			} else {
				board.getLogger().logEndTurn(this);
				board.nextPlayer();
			}
		} else {
			phase = Consts.PHASE_ROLL;			
		}
	}
	
	public void phaseUtility(int option) {
		if (phase != Consts.PHASE_UTILITY) return;
		if (option == 0) {
			board.roll(this);
			rollDisplay = board.getLastRolled();
			phase = Consts.PHASE_RESOLVE;
		}
	}
	
	public void phaseStation(int option) {
		if (phase != Consts.PHASE_STATION) return;
		Place p = board.getMap().getPlace(placeIndex);
		if (p.getType() == Consts.PLACE_ESTATE) {
			Estate e = (Estate) p;
			if (e.getArea() == Consts.AREA_STATION) {
				e.resolveStation(this, option);
			}
		}
		board.getLogger().logEndTurn(this);
		board.nextPlayer();
		
	}
	
	// begin card related
	public void addCard(Card c) {
		if (fullHand() == false) {
			hand.add(c);
		}
	}
	
	public boolean fullHand() {
		if (hand.size()<5) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean addRandomCard() {
		if (fullHand() == false) {
			Card c = cardGenerator.generateACard();
			if (c != null) {
				hand.add(c);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void missHandle() {
		
	}
	// end card related
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getSan() {
		return san;
	}
	public void setSan(int san) {
		this.san = san;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public List<Card> getHand() {
		return hand;
	}
	public void setHand(List<Card> hand) {
		this.hand = hand;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public void setDeck(List<Card> deck) {
		this.deck = deck;
	}
	public List<Card> getDiscard() {
		return discard;
	}
	public void setDiscard(List<Card> discard) {
		this.discard = discard;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public Place getPlace() {
		return board.getMap().getPlace(placeIndex);
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getRollDisplay() {
		return rollDisplay;
	}
	public void setRollDisplay(int rollDisplay) {
		this.rollDisplay = rollDisplay;
	}
	public List<Integer> getOwned() {
		return owned;
	}
	public void setOwned(List<Integer> owned) {
		this.owned = owned;
	}
	public int getPlaceIndex() {
		return placeIndex;
	}
	public void setPlaceIndex(int placeIndex) {
		this.placeIndex = placeIndex;
	}
	public boolean isInJail() {
		return inJail;
	}
	public void setInJail(boolean inJail) {
		this.inJail = inJail;
	}
	public int getJailRound() {
		return jailRound;
	}
	public void setJailRound(int jailRound) {
		this.jailRound = jailRound;
	}
	public boolean isTurnEnd() {
		return turnEnd;
	}
	public void setTurnEnd(boolean turnEnd) {
		this.turnEnd = turnEnd;
	}
	public int getAvatarId() {
		return avatarId;
	}
	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}
	public boolean isInWard() {
		return inWard;
	}
	public void setInWard(boolean inWard) {
		this.inWard = inWard;
	}
	public Buff getBuff() {
		return buff;
	}
	public void setBuff(Buff buff) {
		this.buff = buff;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
