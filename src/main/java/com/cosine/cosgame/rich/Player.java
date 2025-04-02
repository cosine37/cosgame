package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.account.Account;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.entity.AvatarEntity;
import com.cosine.cosgame.rich.entity.CardEntity;
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
		if (vehicle == null) {
			entity.setVehicleId(-1);
		} else {
			entity.setVehicleId(vehicle.getId());
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
	}
	
	public void addMoney(int x) {
		money = money+x;
	}
	
	public void loseMoney(int x) {
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
	
	public void addHp(int x) {
		hp = hp+x;
		// TODO: may need to update here
		if (hp>Consts.GTA_MAXHP) hp = Consts.GTA_MAXHP;
		
		board.getLogger().logRecover(this, x);
	}
	
	public void loseHp(int x) {
		hp = hp-x;
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
			
			// TODO: test cards here
			/*
			hand.add(new Card1());
			hand.add(new CardSalmonBite());
			hand.add(new CardPoutine());
			hand.add(new CardNugget());
			*/
			//hand.add(new CardRelease());
			/*
			hand.add(new CardVehicleCoupon());
			hand.add(new CardRumor());
			hand.add(new CardLittleEssay());
			hand.add(new CardCurlingStone());
			hand.add(new CardPoutine());
			*/
			hand.add(new CardVehicleCoupon());
			hand.add(new CardDinosaur());
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
		
		Place place = board.getMap().getPlaceAfter(placeIndex,totalSteps);
		if (place == null) return ""; else return place.getName();
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
				loseVehicle();
				List<Card> newHand = new ArrayList<>();
				for (int i=0;i<hand.size();i++) {
					if (hand.get(i).getId() == 10) {
						newHand.add(hand.get(i));
					}
				}
				hand = newHand;
				board.setBroadcastMsg(name + "因中罪入狱，失去载具和除出狱卡外的所有手牌。");
			} else if (star<=6) {
				loseVehicle();
				hand = new ArrayList<>();
				board.setBroadcastMsg(name + "因重罪入狱，失去载具和所有手牌且不得提前保释。");
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
		// Step 1: remove from the current place and add player in ward
		board.getMap().getPlace(placeIndex).removePlayer(this);
		board.getMap().addToWard(this);
		
		// Step 2: set related status
		inWard = true;
		
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
				
				board.setBroadcastImg("avatar/head_"+avatarId);
				board.setBroadcastMsg(name + "丢弃了" + c.getName() + "。");
			} else {
				board.getLogger().logPlayCard(this,c);
				c.play(rawOptions);
				
				// TODO: put the card in discard
				if (c.isExhaust() == false) {
					
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
				
				board.setBroadcastImg("dice/"+rollDisplay);
				board.setBroadcastMsg(name + "掷了一个" + rollDisplay + "，将会来到" + myNextPlaceName());
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
				
				board.setBroadcastImg("dice/"+rollDisplay);
				board.setBroadcastMsg(name + "掷了" + r1 + "和" + r2 + "，将会来到" + myNextPlaceName());
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
			
			if (board.getSettings().getUseGTA() == 1) { // GTA go to jail handles
				if (totalSteps <= star) {
					goToJail();
					
					board.getLogger().logEndTurn(this);
					board.nextPlayer();
					return;
				}
			}
			
			board.getMap().getPlace(placeIndex).removePlayer(this);
			
			int t = placeIndex;
			for (int i=totalSteps-1;i>=0;i--) {
				t = (t+1)%board.getMap().mapSize();
				if (i!=0) board.getMap().getPlace(t).bypass(this);
			}
			phase = Consts.PHASE_RESOLVE;
			
			moveToPlace(t);
			
			board.setBroadcastImg(board.getMap().getPlace(t).getImg());
			board.setBroadcastMsg(name + "来到了" + board.getMap().getPlace(t).getName() + "。");
			
			board.getLogger().logPlayerArrive(this);
			
			board.getMap().getPlace(t).preStepOn(this);
			//board.setLastRolled(0);
			rollDisplay = 0;
		}
	}
	
	public void phaseResolve(int option) {
		if (phase != Consts.PHASE_RESOLVE) return;
		
		turnEnd = true;
		board.getMap().getPlace(placeIndex).stepOn(this, option);
		if (turnEnd) {
			board.getLogger().logEndTurn(this);
			board.nextPlayer();
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
	
	public void addCard(Card c) {
		// TODO: change this later
		if (hand.size()<5) {
			hand.add(c);
		}
	}
	
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
