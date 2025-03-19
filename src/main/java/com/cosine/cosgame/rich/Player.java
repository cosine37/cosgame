package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.account.Account;
import com.cosine.cosgame.rich.basicplaces.Estate;
import com.cosine.cosgame.rich.entity.AvatarEntity;
import com.cosine.cosgame.rich.entity.PlayerEntity;

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
	
	protected List<Card> hand;
	protected List<Card> deck;
	protected List<Card> discard;
	protected List<Integer> owned;
	
	protected int placeIndex;
	
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
		doc.append("turnEnd", turnEnd);
		
		List<Integer> handDocList = new ArrayList<>();
		for (i=0;i<hand.size();i++){
			handDocList.add(hand.get(i).getId());
		}
		doc.append("hand",handDocList);
		List<Integer> deckDocList = new ArrayList<>();
		for (i=0;i<deck.size();i++){
			deckDocList.add(deck.get(i).getId());
		}
		doc.append("deck",deckDocList);
		List<Integer> discardDocList = new ArrayList<>();
		for (i=0;i<discard.size();i++){
			discardDocList.add(discard.get(i).getId());
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
		turnEnd = doc.getBoolean("turnEnd", turnEnd);
		int avatarId = doc.getInteger("avatar", -1);
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
		
		Account account = new Account();
		account.getFromDB(name);
		Avatar avatar = Factory.genAvatar(account.getChosenAvatar());
		if (avatar != null) {
			AvatarEntity avatarEntity = avatar.toAvatarEntity();
			int n = -1;
			if (inJail) {
				n = board.getMap().getJailPlayers().size();
			} else if (board.getMap().getPlace(placeIndex) != null) {
				n = board.getMap().getPlace(placeIndex).getPlayersOn().size();
			}
			if (n == 1) {
				avatarEntity.getAvatarStyle().put("margin-left", "40px");
			} else if (n == 2) {
				avatarEntity.getAvatarStyle().put("margin-left", "13px");
			} else if (n == 3) {
				avatarEntity.getAvatarStyle().put("margin-left", "0px");
			} else if (n == 4) {
				if (index == board.getMap().getPlace(placeIndex).getPlayersOn().get(0).getIndex()) {
					avatarEntity.getAvatarStyle().put("margin-left", "0px");
				} else {
					avatarEntity.getAvatarStyle().put("margin-left", "-14px");
				}
			}
			entity.setAvatar(avatarEntity);
		}
		entity.setAvatarOrigin(avatar.toAvatarEntity());
		return entity;
	}
	
	public Player() {
		hand = new ArrayList<>();
		deck = new ArrayList<>();
		discard = new ArrayList<>();
	}
	
	public void addMoney(int x) {
		money = money+x;
	}
	
	public void loseMoney(int x) {
		money = money-x;
	}
	
	public void addSalary() {
		money = money+salary;
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
		Place place = board.getMap().getPlaceAfter(placeIndex,rollDisplay);
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
		
	}
	
	public void outOfJail() {
		// Step 1: remove from the jail and add to jail index
		board.getMap().removeFromJail(this);
		board.getMap().getJail().addPlayerOn(this);
		
		// Step 2: set related status
		inJail = false;
	}
	
	public List<String> getOptions(){
		List<String> ans = new ArrayList<>();
		if (phase == Consts.PHASE_ROLL) {
			if (inJail) {
				ans.add("越狱");
				if (money>=board.getMap().getBailCost()) {
					ans.add("保释");
				}
			} else {
				ans.add("掷骰");
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
				board.roll();
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				if (board.getMap().escapedFromJail()) {
					board.getLogger().logEscapeSuccess(this);
					phase = Consts.PHASE_ESCAPE;
					outOfJail();
				} else {
					board.getLogger().logEscapeFail(this);
					phase = Consts.PHASE_ESCAPE;
				}
			} else { // regular roll and move
				board.roll();
				phase = Consts.PHASE_MOVE;
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
			}
			
		} else if (option == 1) {
			if (inJail) {// bail option
				if (money>=board.getMap().getBailCost()) {
					loseMoney(board.getMap().getBailCost());
					outOfJail();
					board.getLogger().logBait(this, board.getMap().getBailCost());
				}
			} else {
				
			}
		}
		
		else if (option>100000) {
			int x = option-100000;
			if (inJail) {
				board.setLastRolled(x);
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
				if (board.getMap().escapedFromJail()) {
					board.getLogger().logEscapeSuccess(this);
					phase = Consts.PHASE_ESCAPE;
					outOfJail();
				} else {
					board.getLogger().logEscapeFail(this);
					phase = Consts.PHASE_ESCAPE;
				}
			} else { // regular roll and move
				board.setLastRolled(x);
				phase = Consts.PHASE_MOVE;
				rollDisplay = board.getLastRolled();
				board.getLogger().logPlayerRoll(this);
			}
			
			
		}
	}
	
	public void phaseMove(int option) {
		if (phase != Consts.PHASE_MOVE) return;
		if (option == 0) {
			board.getMap().getPlace(placeIndex).removePlayer(this);
			int t = placeIndex;
			for (int i=rollDisplay-1;i>=0;i--) {
				t = (t+1)%board.getMap().mapSize();
				if (i!=0) board.getMap().getPlace(t).bypass(this);
			}
			phase = Consts.PHASE_RESOLVE;
			
			moveToPlace(t);
			board.getMap().getPlace(t).preStepOn(this);
			board.getLogger().logPlayerArrive(this);
			
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
				loseMoney(board.getMap().getBailCost());
				outOfJail();
				phase = Consts.PHASE_ROLL;
				board.getLogger().logBait(this, board.getMap().getBailCost());
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
			board.roll();
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
}
