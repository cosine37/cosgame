package com.cosine.cosgame.oink.grove;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.oink.Board;
import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.account.Transaction;
import com.cosine.cosgame.oink.grove.entity.GroveEntity;
import com.cosine.cosgame.oink.grove.entity.GrovePlayerEntity;
import com.cosine.cosgame.oink.grove.entity.RoleEntity;
import com.cosine.cosgame.util.MongoDBUtil;

public class Grove {
	int curPlayer;
	int lastAccused;
	int predefinedRoles;
	int round;
	int firstPlayer;
	List<GrovePlayer> players;
	List<Role> suspects;
	List<Role> victims;
	
	MongoDBUtil dbutil;
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("curPlayer",curPlayer);
		doc.append("lastAccused",lastAccused);
		doc.append("predefinedRoles",predefinedRoles);
		doc.append("round",round);
		doc.append("firstPlayer",firstPlayer);
		for (i=0;i<players.size();i++) {
			players.get(i).setIndex(i);
			String n = players.get(i).getName();
			n = "player-" + n;
			doc.append(n, players.get(i).toDocument());
		}
		List<Document> suspectsDocList = new ArrayList<>();
		for (i=0;i<suspects.size();i++){
			suspectsDocList.add(suspects.get(i).toDocument());
		}
		doc.append("suspects",suspectsDocList);
		List<Document> victimsDocList = new ArrayList<>();
		for (i=0;i<victims.size();i++){
			victimsDocList.add(victims.get(i).toDocument());
		}
		doc.append("victims",victimsDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		curPlayer = doc.getInteger("curPlayer",0);
		lastAccused = doc.getInteger("lastAccused",0);
		predefinedRoles = doc.getInteger("predefinedRoles",0);
		round = doc.getInteger("round",0);
		firstPlayer = doc.getInteger("firstPlayer",0);
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String n = playerNames.get(i);
			n = "player-" + n;
			Document dop = (Document) doc.get(n);
			GrovePlayer p = new GrovePlayer();
			p.setGrove(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
		List<Document> suspectsDocList = (List<Document>)doc.get("suspects");
		suspects = new ArrayList<>();
		for (i=0;i<suspectsDocList.size();i++){
			Role e = new Role();
			e.setFromDoc(suspectsDocList.get(i));
			suspects.add(e);
		}
		List<Document> victimsDocList = (List<Document>)doc.get("victims");
		victims = new ArrayList<>();
		for (i=0;i<victimsDocList.size();i++){
			Role e = new Role();
			e.setFromDoc(victimsDocList.get(i));
			victims.add(e);
		}
	}
	public GroveEntity toGroveEntity(String username){
		int i,j;
		GroveEntity entity = new GroveEntity();
		entity.setStatus(board.getStatus());
		entity.setRound(round);
		entity.setCurPlayer(curPlayer);
		entity.setFirstPlayer(firstPlayer);
		List<Integer> viewed = new ArrayList<>();
		for (i=0;i<suspects.size();i++) {
			viewed.add(0);
		}
		List<GrovePlayerEntity> listOfPlayers = new ArrayList<>();
		for (i=0;i<players.size();i++){
			listOfPlayers.add(players.get(i).toGrovePlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				GrovePlayer p = players.get(i);
				List<RoleEntity> outsiders = new ArrayList<>();
				outsiders.add(p.getLeftOutsider().toRoleEntity());
				outsiders.add(p.getRightOutsider().toRoleEntity());
				entity.setMyOutsiders(outsiders);
				entity.setPhase(p.getPhase());
				entity.setConfirmed(p.isConfirmed());
				for (j=0;j<p.getViewed().size();j++) {
					viewed.set(j, p.getViewed().get(j));
				}
				if (board.getStatus() == Consts.ENDGAME) {
					entity.setEndGameRewards(p.getEndGameRewards());
				} else {
					entity.setEndGameRewards(new ArrayList<>());
				}
			}
		}
		entity.setPlayers(listOfPlayers);
		List<RoleEntity> listOfSuspects = new ArrayList<>();
		for (i=0;i<suspects.size();i++){
			if (board.getStatus() == Consts.ROUNDEND) {
				listOfSuspects.add(suspects.get(i).toRoleEntity());
			} else {
				if (viewed.get(i) == 1) {
					listOfSuspects.add(suspects.get(i).toRoleEntity());
				} else {
					Role r = new Role(999);
					r.setPredicted(suspects.get(i).getPredicted());
					listOfSuspects.add(r.toRoleEntity());
				}
			}
			
		}
		entity.setSuspects(listOfSuspects);
		
		if (board.getStatus() == Consts.ROUNDEND) {
			entity.setMurIndex(murdererId());
		} else {
			entity.setMurIndex(-1);
		}
		
		if (board.getStatus() == Consts.ENDGAME) {
			List<Integer> rankIndex = new ArrayList<>();
			for (i=0;i<players.size();i++) {
				for (j=0;j<players.size();j++) {
					if (players.get(j).getRanking() == i) {
						rankIndex.add(j);
					}
				}
			}
			entity.setRankIndex(rankIndex);
		} else {
			entity.setRankIndex(new ArrayList<>());
		}
		
		return entity;
	}
	
	public Grove(Board board) {
		this.board = board;
		players = new ArrayList<>();
		suspects = new ArrayList<>();
		victims = new ArrayList<>();
		
		String dbname = "oink";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public int murdererId() {
		int i;
		boolean reverse = false;
		for (i=0;i<suspects.size();i++) {
			if (suspects.get(i).getNum() == Consts.REVERSENUM) {
				reverse = true;
			}
		}
		
		int ans = suspects.get(0).getNum();
		for (i=1;i<suspects.size();i++) {
			if (reverse) {
				if (suspects.get(i).getNum()<ans) {
					ans = suspects.get(i).getNum();
				}
			} else {
				if (suspects.get(i).getNum()>ans) {
					ans = suspects.get(i).getNum();
				}
			}
		}
		return ans;
	}
	
	public void distributeLiar() {
		int ans = murdererId();
		for (int i=0;i<suspects.size();i++) {
			if (suspects.get(i).getNum() != ans && suspects.get(i).lastPredicted() != -1) {
				GrovePlayer p = players.get(suspects.get(i).lastPredicted());
				int x = suspects.get(i).getPredicted().size();
				p.addLiar(1);
				for (int j=0;j<x-1;j++) p.addLiar(2);
			}
		}
	}
	
	public void playerView(String name, List<Integer> viewed) {
		GrovePlayer p = getPlayerByName(name);
		if (p != null && p.getPhase() == Consts.VIEWCARDS) {
			p.setViewed(viewed);
			p.setPhase(Consts.ACCUSECARD);
		}
	}
	
	
	public void playerAccuse(String name, int index) {
		GrovePlayer p = getPlayerByName(name);
		if (p != null && index>=0 && index<=Consts.NUMSUSPECTS && p.getPhase() == Consts.ACCUSECARD) {
			suspects.get(index).addPredicted(p.getIndex());
			p.setAccused(index);
			lastAccused = index;
			p.setPhase(Consts.OFFTURN);
			
			// change next player status
			curPlayer = (curPlayer+1) % players.size();
			if (curPlayer == firstPlayer) {
				endRound();
			} else {
				players.get(curPlayer).setPhase(Consts.ACCUSECARD);
				
				// auto view for next player
				List<Integer> viewed = new ArrayList<>();
				for (int i=0;i<3;i++) {
					if (i == lastAccused) {
						viewed.add(0);
					} else {
						viewed.add(1);
					}
				}
				players.get(curPlayer).setViewed(viewed);
			}
		}
	}
	
	public void endRound() {
		board.setStatus(Consts.ROUNDEND);
		distributeLiar();
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setConfirmed(false);
		}
	}
	
	public void startRound() {
		int i,x,i2;
		
		// Step 1: initial players and phase
		curPlayer = firstPlayer;
		for (i=0;i<players.size();i++) {
			players.get(i).newRound();
			players.get(i).setPhase(Consts.OFFTURN);
		}
		players.get(curPlayer).setPhase(Consts.VIEWCARDS);
		
		// Step 2: distribute roles
		List<Role> allRoles = AllRes.genDefaultRoles(players.size());
		suspects = new ArrayList<>();
		Random rand = new Random();
		for (i=0;i<3;i++) {
			x = rand.nextInt(allRoles.size());
			suspects.add(allRoles.remove(x));
		}
		x = rand.nextInt(allRoles.size());
		victims.add(allRoles.remove(x));
		
		List<Role> trs = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			x = rand.nextInt(allRoles.size());
			trs.add(allRoles.remove(x));
		}
		for (i=0;i<players.size();i++) {
			i2 = (i+1)%players.size();
			players.get(i).setLeftOutsider(trs.get(i));
			players.get(i).setRightOutsider(trs.get(i2));
		}
		
		// Step 3: round count and status
		round++;
		board.setStatus(Consts.INGAME);
	}
	
	public boolean gameEnd() {
		if (round == 8) return true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).numLiars() >= 5) return true;
		}
		return false;
	}
	
	public void endGame() {
		// Step 1: change status
		board.setStatus(Consts.ENDGAME);
		
		// Step 2: sort players
		int i,j;
		List<GrovePlayer> tp = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			tp.add(players.get(i));
		}
		for (i=0;i<tp.size();i++) {
			for (j=i+1;j<tp.size();j++) {
				boolean shouldSwap = false;
				if (tp.get(i).numLiars() > tp.get(j).numLiars()) { // one with less liars ranks higher
					shouldSwap = true;
				} else if (tp.get(i).numLiars() == tp.get(j).numLiars()) {
					if (tp.get(i).numMyLiars() > tp.get(j).numMyLiars()) { // one with less my liars ranks higher
						shouldSwap = true;
					} else if (tp.get(i).numMyLiars() == tp.get(j).numMyLiars()) {
						int rsi = tp.get(i).getIndex();
						int rsj = tp.get(j).getIndex();
						if (rsi < firstPlayer) rsi=rsi+players.size();
						if (rsj < firstPlayer) rsj=rsj+players.size();
						if (rsi > rsj) { // one goes first ranks higher
							shouldSwap = true;
						}
					}
				}
				
				if (shouldSwap) {
					GrovePlayer t = tp.get(i);
					tp.set(i, tp.get(j));
					tp.set(j, t);
				}
			}
		}
		
		for (i=0;i<tp.size();i++) {
			tp.get(i).setRanking(i);
		}
		
		// Step 3: deal with awards
		for (i=0;i<players.size();i++) {
			Account a = new Account();
			GrovePlayer p = players.get(i);
			a.getFromDB(p.getName());
			List<Transaction> rewards = a.endGameReward(p.getRanking()+1);
			List<String> endGameRewards = new ArrayList<>();
			for (j=0;j<rewards.size();j++) {
				a.addNewTransaction(rewards.get(j));
				endGameRewards.add(rewards.get(j).toString());
			}
			p.setEndGameRewards(endGameRewards);
			a.updateAccountDB();
			
		}
	}
	
	
	// Start actual operations
	// Actual start game operation
	public void startGameUDB() {
		// Step 1: create players
		int i;
		List<String> playerNames = board.getPlayerNames();
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			GrovePlayer p = new GrovePlayer(playerNames.get(i));
			p.setGrove(this);
			players.add(p);
		}
		
		// Step 2: set round and curPlayer
		round = 0;
		curPlayer = board.getFirstPlayer();
		firstPlayer = board.getFirstPlayer();
		startRound();
		
		updatePlayers();
		updateBasicDB();
	}
	
	public void playerViewUDB(String username, int cardIndex) {
		List<Integer> viewed = new ArrayList<>();
		viewed.add(0);
		viewed.add(0);
		viewed.add(0);
		int t = cardIndex;
		viewed.set(2, t%10);
		t = t/10;
		viewed.set(1, t%10);
		t = t/10;
		viewed.set(0, t);
		playerView(username,viewed);
		
		updatePlayer(username);
		updateBasicDB();
	}
	
	public void playerAccuseUDB(String username, int cardIndex) {
		playerAccuse(username, cardIndex);
		
		updatePlayers();
		updateBasicDB();
	}
	
	public void playerConfirmUDB(String username) {
		GrovePlayer p = getPlayerByName(username);
		p.setConfirmed(true);
		boolean flag = true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isConfirmed() == false) {
				flag = false;
				break;
			}
		}
		if (flag) {
			if (gameEnd()) {
				endGame();
			} else {
				startRound();
			}
			
		}
		updatePlayers();
		updateBasicDB();
	}
	
	
	// End actual operations
	
	public GrovePlayer getPlayerByName(String name) {
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				return players.get(i);
			}
		}
		return null;
	}
	
	public void updatePlayer(int index) {
		GrovePlayer p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayer(String name) {
		GrovePlayer p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", board.getId(), playerName, dop);
		}
	}
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	
	public void updateBasicDB() {
		updateDB("round", round);
		updateDB("status", board.getStatus());
		updateDB("curPlayer", curPlayer);
		updateDB("firstPlayer", firstPlayer);
		updateDB("lastAccused", lastAccused);
		int i;
		List<Document> suspectsDocList = new ArrayList<>();
		for (i=0;i<suspects.size();i++){
			suspectsDocList.add(suspects.get(i).toDocument());
		}
		updateDB("suspects",suspectsDocList);
		List<Document> victimsDocList = new ArrayList<>();
		for (i=0;i<victims.size();i++){
			victimsDocList.add(victims.get(i).toDocument());
		}
		updateDB("victims",victimsDocList);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", board.getId(), key, value);
	}
	
	public int getStatus() {
		return board.getStatus();
	}
	public void setStatus(int status) {
		board.setStatus(status);
	}
	public int getCurPlayer() {
		return curPlayer;
	}
	public void setCurPlayer(int curPlayer) {
		this.curPlayer = curPlayer;
	}
	public int getLastAccused() {
		return lastAccused;
	}
	public void setLastAccused(int lastAccused) {
		this.lastAccused = lastAccused;
	}
	public int getPredefinedRoles() {
		return predefinedRoles;
	}
	public void setPredefinedRoles(int predefinedRoles) {
		this.predefinedRoles = predefinedRoles;
	}
	public List<GrovePlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<GrovePlayer> players) {
		this.players = players;
	}
	public List<Role> getSuspects() {
		return suspects;
	}
	public void setSuspects(List<Role> suspects) {
		this.suspects = suspects;
	}
	public List<Role> getVictims() {
		return victims;
	}
	public void setVictims(List<Role> victims) {
		this.victims = victims;
	}
	
}
