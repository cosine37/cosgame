package com.cosine.cosgame.belltower;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.belltower.entity.BoardEntity;
import com.cosine.cosgame.belltower.entity.PlayerEntity;
import com.cosine.cosgame.belltower.roles.troublebrewing.*;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	String id;
	String lord;
	String morningMsg;
	String executionMsg;
	int status;
	int phase;
	int numDay;
	
	List<Integer> groupCounts;
	List<Player> players;
	List<Integer> killedIndexes;
	Script script;
	
	MongoDBUtil dbutil;
	
	int sequence; // clockwise or counterclockwise
	int firstNominator;
	int curNominator;
	int curVoter;
	int nominated;
	int voteCount;
	List<Integer> voteResults;
	
	public Board() {
		groupCounts = new ArrayList<>();
		players = new ArrayList<>();
		
		script = new Script();
		
		String dbname = "belltower";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void startGame() {
		status = Consts.ASSIGNROLE;
		
		// TODO: customize script choice here
		script = ScriptFactory.makeScript(1);
		
	}
	
	public void assignRoles() {
		List<Role> roles = script.getRoles(groupCounts);
		Random rand = new Random();
		for (int i=0;i<players.size();i++) {			
			if (roles.size() == 0) break;
			int x = rand.nextInt(roles.size());
			Role r = roles.remove(x);
			players.get(i).gameStart(r);
		}
		
		// TODO: Assign roles here
		/*
		Role r1 = new Imp();
		players.get(0).setRole(r1);
		Role r2 = new Poisoner();
		players.get(1).setRole(r2);
		*/
	}
	
	public void startFirstNight() {
		assignRoles();
		numDay = 0;
		status = Consts.INGAME;
		startNight();
	}
	
	public void startNight() {
		phase = Consts.NIGHT;
		numDay++;
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).startNight();
		}
		killedIndexes = new ArrayList<>();
	}
	
	public void execSkills() {
		int i,j;
		List<Player> tplayers = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			tplayers.add(players.get(i));
		}
		for (i=0;i<tplayers.size();i++) {
			for (j=i+1;j<tplayers.size();j++) {
				Player p1 = tplayers.get(i);
				Player p2 = tplayers.get(j);
				if (p1.getRole().getSequence() > p2.getRole().getSequence()) {
					tplayers.set(i, p2);
					tplayers.set(j, p1);
				}
			}
		}
		for (i=0;i<tplayers.size();i++) {
			Role r = tplayers.get(i).getRole();
			if (numDay == 1) {
				if (r.isHasFirstNight()) {
					r.execSkill();
				}
			} else {
				if (r.isHasRestNights()) {
					r.execSkill();
				}
			}
		}
	}
	
	public void endNight() {
		// TODO: Deal with sequences
		int i;
		execSkills();
		if (killedIndexes.size() == 0) {
			morningMsg = "昨晚平安夜。";
		} else {
			morningMsg = "";
			Random rand = new Random();
			int temp = rand.nextInt(4);
			if (temp == 0 && killedIndexes.size()==1) {
				String name = players.get(killedIndexes.get(0)).getDisplayName();
				for (i=0;i<name.length();i++) {
					morningMsg = morningMsg + name.charAt(i) + "——";
				}
			} else {
				List<String> names = new ArrayList<>();
				for (i=0;i<killedIndexes.size();i++) {
					String name = players.get(killedIndexes.get(i)).getDisplayName();
					names.add(name);
				}
				morningMsg = "昨晚，";
				while (names.size()>0) {
					int x = rand.nextInt(names.size());
					String name = names.remove(x);
					if (names.size()==0) {
						morningMsg = morningMsg + name;
					} else if (names.size()==1) {
						morningMsg = morningMsg + name + "和";
					} else {
						morningMsg = morningMsg + name + "、";
					}
				}
				String[] killedMsg = {"跪了。", "圆寂了。", "浪费了。", "死了啦，都你害的啦，拜托。"};
				int x = rand.nextInt(killedMsg.length);
				morningMsg = morningMsg + killedMsg[x];
			}
		}
		phase = Consts.DAY;
	}
	
	public void startDay() {
		Random rand = new Random();
		//firstNominator = rand.nextInt(players.size());
		int i;
		List<Integer> a = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			players.get(i).startDay();
			if (players.get(i).isAlive()) {
				a.add(i);
			}
		}
		int x = rand.nextInt(a.size());
		firstNominator = a.get(x);
		curNominator = firstNominator;
		curVoter = -1;
		nominated = -1;
		voteResults = new ArrayList<>();
		boolean f = rand.nextBoolean();
		if (f) {
			sequence = 1;
		} else {
			sequence = -1;
		}
	}
	
	public void endDay() {
		numDay = numDay+1;
		phase = Consts.NIGHT;
	}
	
	public void nominate(int x) {
		if (x == -1) {
			nextPlayerNominate();
		} else if (players.get(x).isNominated() == false){
			nominated = x;
			voteCount = 0;
			curVoter = curNominator;
			players.get(x).receiveNomination();
		}
	}
	public void vote(boolean f) {
		if (curVoter<0 || curVoter>=players.size()) {
			return;
		}
		Player p = players.get(curVoter);
		if (p.isCanVote() == false) {
			f = false;
		}
		if (f) {
			voteCount++;
			if (p.isAlive() == false) {
				p.setCanVote(false);
			}
		}
		
		curVoter = curVoter + sequence;
		if (curVoter >= players.size()) {
			curVoter = curVoter - players.size();
		}
		if (curVoter < 0) {
			curVoter = curVoter + players.size();
		}
		
		if (curVoter == curNominator) {
			if (voteCount*2 >= numAlivePlayers()) {
				voteResults.add(nominated*100+voteCount);
			}
			nextPlayerNominate();
		}
	}
	public void nextPlayerNominate() {
		curVoter = -1;
		nominated = -1;
		curNominator = curNominator + sequence;
		System.out.println("CurNominator:" + curNominator);
		if (curNominator >= players.size()) {
			curNominator = curNominator - players.size();
		}
		if (curNominator < 0) {
			curNominator = curNominator + players.size();
		}
		if (players.get(curNominator).isAlive() == false) {
			nextPlayerNominate();
		} else if (curNominator == firstNominator) {
			executionCheck();
		}
	}
	public void executionCheck() {
		int i;
		int maxVotes = 0;
		int executedIndex = -1;
		for (i=0;i<voteResults.size();i++) {
			int numVotes = voteResults.get(i)%100;
			int index = voteResults.get(i)/100;
			if (numVotes > maxVotes) {
				maxVotes = numVotes;
				executedIndex = index;
			} else if (numVotes == maxVotes) {
				executedIndex = -1;
			}
		}
		
		phase = Consts.EXECUTION;
		if (executedIndex != -1) {
			execute(executedIndex);
		} else {
			executionMsg = "没有人被处决。";
			broadcast(executionMsg);
		}
	}
	public void execute(int executedIndex) {
		if (executedIndex>=0 && executedIndex<players.size()) {
			Player p = players.get(executedIndex);
			p.executed();
			
			Random rand = new Random();
			int x = rand.nextInt(2);
			if (x == 0) {
				executionMsg = p.getDisplayName() + "被处决了。";
				p.addLog("你被处决了。");
			} else if (x == 1) {
				executionMsg = p.getDisplayName() + "被选票淹死了。";
				p.addLog("你被选票淹死了。");
			}
			broadcastExcept(executionMsg, executedIndex);
		}
	}
	public void broadcastExcept(String log, int exception) {
		if (log == null || log.contentEquals("")) return;
		int i;
		for (i=0;i<players.size();i++) {
			if (exception == i) continue;
			players.get(i).addLog(log);
		}
	}
	public void broadcast(String log) {
		broadcastExcept(log, -1);
	}
	
	public boolean allConfirmedNight() {
		boolean ans = true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isConfirmedNight() == false) {
				ans = false;
				break;
			}
		}
		return ans;
	}
	
	public boolean allConfirmedDay() {
		boolean ans = true;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isConfirmedDay() == false) {
				ans = false;
				break;
			}
		}
		return ans;
	}
	
	public int numAlivePlayers() {
		int ans = 0;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).isAlive()) {
				ans++;
			}
		}
		return ans;
	}
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	public boolean isLord(String username) {
		if (username.contentEquals(lord)) {
			return true;
		} else {
			return false;
		}
	}
	public void addKilledIndex(int x) {
		killedIndexes.add(x);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getNumDay() {
		return numDay;
	}
	public void setNumDay(int numDay) {
		this.numDay = numDay;
	}
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public Script getScript() {
		return script;
	}
	public void setScript(Script script) {
		this.script = script;
	}
	public List<Integer> getGroupCounts() {
		return groupCounts;
	}
	public void setGroupCounts(List<Integer> groupCounts) {
		this.groupCounts = groupCounts;
	}
	public List<Integer> getKilledIndexes() {
		return killedIndexes;
	}
	public void setKilledIndexes(List<Integer> killedIndexes) {
		this.killedIndexes = killedIndexes;
	}
	public String getMorningMsg() {
		return morningMsg;
	}
	public void setMorningMsg(String morningMsg) {
		this.morningMsg = morningMsg;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getFirstNominator() {
		return firstNominator;
	}
	public void setFirstNominator(int firstNominator) {
		this.firstNominator = firstNominator;
	}
	public int getCurNominator() {
		return curNominator;
	}
	public void setCurNominator(int curNominator) {
		this.curNominator = curNominator;
	}
	public int getCurVoter() {
		return curVoter;
	}
	public void setCurVoter(int curVoter) {
		this.curVoter = curVoter;
	}
	public int getNominated() {
		return nominated;
	}
	public void setNominated(int nominated) {
		this.nominated = nominated;
	}
	public int getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(int voteCount) {
		this.voteCount = voteCount;
	}

	public List<Integer> getVoteResults() {
		return voteResults;
	}

	public void setVoteResults(List<Integer> voteResults) {
		this.voteResults = voteResults;
	}

	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		p.setDisplayName(name);
		players.add(p);
	}
	
	public Player getPlayerByName(String name) {
		Player p = null;
		for (int i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				p = players.get(i);
				break;
			}
		}
		return p;
	}
	
	public Player getPlayerByIndex(int index) {
		if (index<0 || index>=players.size()) {
			return null;
		}
		return players.get(index);
	}
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(i);
		}
	}
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
	}
	
	public void storeToDB() {
		Document doc = toDocument();
		dbutil.insert(doc);
	}
	
	public void getFromDB(String id) {
		Document doc = dbutil.read("id", id);
		setFromDoc(doc);
	}
	
	public void updateDB(String key, Object value) {
		dbutil.update("id", id, key, value);
	}
	
	public void updateBasicDB() {
		updateDB("phase", phase);
		updateDB("status", status);
		updateDB("numDay", numDay);
		updateDB("killedIndexes", killedIndexes);
		updateDB("morningMsg", morningMsg);
		updateDB("sequence", sequence);
		updateDB("curNominator", curNominator);
		updateDB("curVoter", curVoter);
		updateDB("firstNominator", firstNominator);
		updateDB("nominated", nominated);
		updateDB("voteCount", voteCount);
		updateDB("voteResults", voteResults);
		updateDB("executionMsg", executionMsg);
	}
	
	public void removePlayerFromDB(int index) {
		String playerName = "player-" + players.get(index).getName();
		players.remove(index);
		dbutil.removeKey("id", id, playerName);
		List<String> playerNames = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			playerName = players.get(i).getName();
			playerNames.add(players.get(i).getName());
		}
		dbutil.update("id", id, "playerNames", playerNames);
	}
	
	public void removePlayerFromDB(String name) {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getName().contentEquals(name)) {
				removePlayerFromDB(i);
				break;
			}
		}
	}
	
	public void dismiss() {
		dbutil.delete("id", id);
	}
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("lord", lord);
		doc.append("status", status);
		doc.append("phase", phase);
		doc.append("script", script.getId());
		doc.append("groupCounts", groupCounts);
		doc.append("numDay", numDay);
		doc.append("killedIndexes", killedIndexes);
		doc.append("morningMsg", morningMsg);
		doc.append("sequence", sequence);
		doc.append("firstNominator", firstNominator);
		doc.append("curNominator", curNominator);
		doc.append("curVoter", curVoter);
		doc.append("nominated", nominated);
		doc.append("voteCount", voteCount);
		doc.append("voteResults", voteResults);
		doc.append("executionMsg", executionMsg);
		int i;
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String name = players.get(i).getName();
			playerNames.add(name);
			name = "player-" + name;
			doc.append(name, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		lord = doc.getString("lord");
		status = doc.getInteger("status", -1);
		phase = doc.getInteger("phase", -1);
		numDay = doc.getInteger("numDay", 0);
		morningMsg = doc.getString("morningMsg");
		sequence = doc.getInteger("sequence", 1);
		firstNominator = doc.getInteger("firstNominator", -1);
		curNominator = doc.getInteger("curNominator", -1);
		curVoter = doc.getInteger("curVoter", -1);
		nominated = doc.getInteger("nominated", -1);
		voteCount = doc.getInteger("voteCount", 0);
		voteResults = (List<Integer>) doc.get("voteResults");
		executionMsg = doc.getString("executionMsg");
		int scriptId = doc.getInteger("script", -1);
		script = ScriptFactory.makeScript(scriptId);
		List<String> playerNames = (List<String>) doc.get("playerNames");
		groupCounts = (List<Integer>) doc.get("groupCounts");
		killedIndexes = (List<Integer>) doc.get("killedIndexes");
		int i;
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String name = playerNames.get(i);
			name = "player-" + name;
			Document dop = (Document) doc.get(name);
			Player p = new Player();
			p.setBoard(this);
			p.setIndex(i);
			p.setFromDoc(dop);
			players.add(p);
		}
	}
	
	public BoardEntity toBoardEntity(String username) {
		BoardEntity entity = new BoardEntity();
		entity.setId(id);
		entity.setLord(lord);
		entity.setNumDay(numDay);
		entity.setPhase(phase);
		entity.setStatus(status);
		entity.setMorningMsg(morningMsg);
		entity.setSequence(sequence);
		entity.setFirstNominator(firstNominator);
		entity.setCurNominator(curNominator);
		entity.setCurVoter(curVoter);
		entity.setNominated(nominated);
		entity.setExecutionMsg(executionMsg);
		entity.setVoteResults(voteResults);
		List<PlayerEntity> playerEntities = new ArrayList<>();
		int i;
		for (i=0;i<players.size();i++) {
			playerEntities.add(players.get(i).toPlayerEntity());
			if (players.get(i).getName().contentEquals(username)) {
				Player p = players.get(i);
				entity.setMyRole(p.getRole().toRoleEntity());
				entity.setMyLogs(p.getLogs());
				entity.setMyLastNightMsg(p.getLastNightMsg());
				entity.setMyDisplayName(p.getDisplayName());
				entity.setMyIndex(i);
				entity.setMyAvailableCharacters(p.getAvailableCharacters());
				entity.setConfirmedDay(p.isConfirmedDay());
				entity.setConfirmedNight(p.isConfirmedNight());
			}
		}
		entity.setPlayers(playerEntities);
		return entity;
	}
}
