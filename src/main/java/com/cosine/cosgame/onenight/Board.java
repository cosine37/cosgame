package com.cosine.cosgame.onenight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.onenight.roles.*;
import com.cosine.cosgame.util.MongoDBUtil;

public class Board {
	List<Player> players;
	List<Role> rolesThisGame;
	List<List<Role>> centerRoles;
	
	String id;
	int status;
	int round;
	int totalRounds;
	int winSide;
	int firstPlayerIndex;
	String lord;
	boolean canNight;
	boolean tannerWin;
	int detectiveIndex;
	int weremeleonIndex;
	String detectiveRoleImg;
	boolean soleWolf;
	int restrictedIndex;
	
	List<String> confirmed;
	
	MongoDBUtil dbutil;
	
	public Board() {
		players = new ArrayList<>();
		rolesThisGame = new ArrayList<>();
		centerRoles = new ArrayList<>();
		canNight = false;
		detectiveIndex = -1;
		detectiveRoleImg = "";
		soleWolf = false;
		restrictedIndex = -1;
		firstPlayerIndex = -1;
		
		String dbname = "onenight";
		String col = "board";
		dbutil = new MongoDBUtil(dbname);
		dbutil.setCol(col);
	}
	
	public void parseSettings(List<Integer> settings) {
		int minLength = 1;
		if (settings.size()<minLength) {
			return;
		} else {
			if (settings.get(0) == 1) {
				soleWolf = true;
			} else {
				soleWolf = false;
			}
		}
	}
	
	public void setWeremeleonIndex() {
		List<Integer> ris = new ArrayList<>();
		for (int i=0;i<rolesThisGame.size();i++) {
			ris.add(i);
		}
		while (ris.size() > 0) {
			Random rand = new Random();
			int x = rand.nextInt(ris.size());
			int y = ris.remove(x);
			Role r = rolesThisGame.get(y);
			if (r.getSide() == Consts.HUMAN) {
				if (r.getRoleNum() != Consts.PAGAN && r.getRoleNum() != Consts.BAKER && r.getRoleNum() != Consts.MONK) {
					weremeleonIndex = y;
				}
			}
		}
	}
	
	public void startGame() {
		int n = players.size();
		int i;
		for (i=0;i<n;i++) {
			players.get(i).initializeMarks(n);
			players.get(i).setShowUpdatedRole(false);
			players.get(i).clearRole();
			players.get(i).setVotedOut(false);
			players.get(i).setNumVotes(0);
		}
		detectiveIndex = -1;
		detectiveRoleImg = "";
		canNight = false;
		status = Consts.SETUP;
		winSide = -1;
		restrictedIndex = -1;
		firstPlayerIndex = -1;
		weremeleonIndex = -1;
	}
	
	public void restart() {
		startGame();
		canNight = true;
	}
	
	public void setRolesThisGameByInt(List<Integer> ls) {
		int i;
		AllRes allRes = new AllRes();
		List<Role> allRoles = allRes.getAllRoles();
		rolesThisGame = new ArrayList<>();
		for (i=0;i<ls.size();i++) {
			if (ls.get(i) == 1) {
				Role r = allRoles.get(i);
				rolesThisGame.add(r);
			}
		}
		canNight = true;
	}
	
	public void distributeRoles() {
		setWeremeleonIndex();
		List<Role> tls = new ArrayList<>();
		int i;
		for (i=0;i<rolesThisGame.size();i++) {
			tls.add(rolesThisGame.get(i));
		}
		
		for (i=0;i<players.size();i++) {
			Random rand = new Random();
			int size = tls.size();
			Role r = tls.remove(rand.nextInt(size));
			r.setPlayer(players.get(i));
			players.get(i).addRole(r);
		}
		centerRoles = new ArrayList<>();
		for (i=0;i<3;i++) {
			List<Role> singleRole = new ArrayList<>();
			singleRole.add(tls.remove(0));
			centerRoles.add(singleRole);
		}
		
		// TODO: test roles here
		
		Role r = new Minion();
		r.setPlayer(players.get(0));
		r.setBoard(this);
		players.get(0).getRoles().set(0, r);
		/*
		r = new Actor();
		r.setPlayer(players.get(1));
		r.setBoard(this);
		players.get(1).getRoles().set(0, r);
		
		r = new Monk();
		r.setPlayer(players.get(2));
		r.setBoard(this);
		players.get(2).getRoles().set(0, r);
		
		r = new Seer();
		r.setPlayer(players.get(3));
		r.setBoard(this);
		players.get(3).getRoles().set(0, r);
		
		r = new Villager();
		r.setPlayer(players.get(4));
		r.setBoard(this);
		players.get(4).getRoles().set(0, r);
		
		r = new Wolfdog();
		List<Role> rs = new ArrayList<>();
		rs.add(r);
		centerRoles.set(0, rs);
		*/
		/*
		r = new Pope();
		r.setPlayer(players.get(2));
		r.setBoard(this);
		players.get(2).getRoles().set(0, r);
		
		r = new Thief();
		r.setPlayer(players.get(3));
		r.setBoard(this);
		players.get(3).getRoles().set(0, r);
		*/
		
		for (i=0;i<players.size();i++) {
			players.get(i).getInitialRole().vision();
		}
		confirmed = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			if (players.get(i).getInitialRole().isHasNight()) {
				confirmed.add("n");
			} else {
				confirmed.add("y");
			}
		}
		status = Consts.NIGHT;
		
		
	}
	
	public Role getCurCenterRole(int x) {
		if (x>2 || x<0) {
			return null;
		} else {
			int n = centerRoles.get(x).size()-1;
			Role role = centerRoles.get(x).get(n);
			return role;
		}
	}
	
	public void addCenterRole(int x, Role r) {
		if (x>2 || x<0) {
			return;
		} else {
			centerRoles.get(x).add(r);
		}
	}
	
	public void executeAllSkills() {
		List<Player> tps = new ArrayList<>();
		int i,j;
		for (i=0;i<players.size();i++) {
			tps.add(players.get(i));
		}
		for (i=0;i<tps.size();i++) {
			for (j=i+1;j<tps.size();j++) {
				int xi = tps.get(i).getInitialRole().getSequence();
				int xj = tps.get(j).getInitialRole().getSequence();
				if (xi > xj) {
					Player tp = tps.get(i);
					tps.set(i, tps.get(j));
					tps.set(j, tp);
				}
			}
		}
		
		// night skills
		for (i=0;i<tps.size();i++) {
			int x = tps.get(i).getInitialRole().getSequence();
			if (x > 0 && x < Consts.DAWNSPLITER) {
				tps.get(i).getInitialRole().executeSkill();
			}
		}
		// on dawn skills
		for (i=0;i<tps.size();i++) {
			tps.get(i).getInitialRole().onDawnSkill();
		}
		// dawn skills
		for (i=0;i<tps.size();i++) {
			int x = tps.get(i).getInitialRole().getSequence();
			if (x > Consts.DAWNSPLITER) {
				tps.get(i).getInitialRole().executeSkill();
			}
		}
	}
	
	public boolean allConfirmed() {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isBot()) {
				continue;
			}
			if (!players.get(i).isConfirmed()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean allVoted() {
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).isBot()) {
				continue;
			}
			if (!players.get(i).isVoted()) {
				return false;
			}
		}
		return true;
	}
	
	public void botMoves() {
		 
	}
	
	public void clearConfirmed() {
		int i;
		for (i=0;i<confirmed.size();i++) {
			confirmed.set(i, "n");
		}
		for (i=0;i<players.size();i++) {
			players.get(i).setConfirmed(false);
		}
	}
	
	public void clearVoted() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setVoted(false);
			players.get(i).setNumVotes(0);
			players.get(i).setVoteIndex(-1);
		}
	}
	
	public void dayHandle() {
		int i,j;
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			Role r = p.getCurrentRole();
			if (r.getRoleNum() == Consts.BAKER) {
				for (j=0;j<players.size();j++) {
					players.get(j).getPlayerMarks().set(i, Consts.BAKER);
				}
				p.setUpdatedRole(r);
				p.setShowUpdatedRole(true);
			}
		}
		
		for (i=0;i<centerRoles.size();i++) {
			Role r = getCurCenterRole(i);
			if (r.getRoleNum() == Consts.BAKER) {
				for (j=0;j<players.size();j++) {
					players.get(j).getCenterMarks().set(i, Consts.BAKER);
				}
			}
		}
	}
	
	public void decideFirstPlayer() {
		if (firstPlayerIndex == -1) {
			Random rand = new Random();
			int size = players.size();
			firstPlayerIndex = rand.nextInt(size);
		}
	}
	
	public void confirm(int x) {
		confirmed.set(x, "y");
		players.get(x).setConfirmed(true);
		if (allConfirmed()) {
			botMoves();
			executeAllSkills();
			clearConfirmed();
			clearVoted();
			dayHandle();
			decideFirstPlayer();
			status = Consts.DAY;
		}
	}
	
	public void unconfirm(int x) {
		confirmed.set(x, "n");
	}
	
	public void vote(int x, int y) {
		players.get(x).setVoteIndex(y);
		players.get(x).setVoted(true);
		/*
		if (y>=0 && y<players.size()) {
			if (players.get(x).getCurrentRole().getRoleNum() == Consts.PRINCE) {
				
			} else {
				players.get(y).receiveVote();
				if (players.get(x).getCurrentRole().getRoleNum() == Consts.SHERIFF) {
					players.get(y).receiveVote();
				}
			}
		}
		*/
		if (allVoted()) {
			status = Consts.AFTERVOTE;
			countVote();
			decideWinSide();
			showAllRoles();
		}
	}
	
	public void countVote() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).setNumVotes(0);
		}
		for (i=0;i<players.size();i++) {
			Role r = players.get(i).getCurrentRole();
			int v = r.voteValue();
			int x = players.get(i).getVoteIndex();
			if (x>=0 && x<=players.size()) {
				Player p = players.get(x);
				p.getCurrentRole().receiveVote(v);
			}
		}
		for (i=0;i<players.size();i++) {
			players.get(i).getCurrentRole().afterVoteCountHandle();
		}
	}
	
	public void showAllRoles() {
		int i;
		for (i=0;i<players.size();i++) {
			players.get(i).showAllRoles();
		}
	}
	
	public void decideWinSide() {
		if (status != Consts.AFTERVOTE) {
			return;
		}
		int mostVote = 2;
		int i;
		boolean killedPope = true;
		boolean hasWerewolf = false;
		for (i=0;i<players.size();i++) {
			/*
			if (players.get(i).getCurrentRole().getRoleNum() == Consts.GUARD) {
				int x = players.get(i).getVoteIndex();
				if (x>=0 && x<players.size()) {
					players.get(x).setNumVotes(0);
				}
				
			}
			if (players.get(i).getCurrentRole().getRoleNum() == Consts.PRINCE) {
				players.get(i).setNumVotes(0);
			}
			*/
			if (players.get(i).getCurrentRole().getSide() == Consts.WOLF) {
				hasWerewolf = true;
				int x = players.get(i).getVoteIndex();
				if (x>=0 && x<players.size()) {
					if (players.get(x).getCurrentRole().getRoleNum() == Consts.WOLFCHILD) {
						players.get(x).getCurrentRole().setSide(Consts.WOLF);
					}
					if (players.get(x).getCurrentRole().getRoleNum() != Consts.POPE) {
						killedPope = false;
					}
				} else {
					killedPope = false;
				}
			}
		}
		if (hasWerewolf == false) {
			killedPope = false;
		}
		for (i=0;i<players.size();i++) {
			if (players.get(i).getNumVotes() > mostVote) {
				mostVote = players.get(i).getNumVotes();
			}
		}
		boolean votedWerewolf = false;
		boolean votedMinion = false;
		boolean votedTanner = false;
		boolean missedWerewolf = false;
		boolean votedSomeone = false;
		for (i=0;i<players.size();i++) {
			Role r = players.get(i).getCurrentRole();
			if (players.get(i).getNumVotes() == mostVote) {
				players.get(i).setVotedOut(true);
				if (r.getRoleNum() == Consts.HUNTER) {
					int x = players.get(i).getVoteIndex();
					if (x>=0 && x<players.size()) {
						players.get(x).setVotedOut(true);
					}
				}
			}
		}
		for (i=0;i<players.size();i++) {
			Role r = players.get(i).getCurrentRole();
			if (players.get(i).isVotedOut()) {
				votedSomeone = true;
				if (r.getSide() == Consts.WOLF) {
					if (r.getRoleNum() == Consts.MINION) {
						votedMinion = true;
					} else {
						votedWerewolf = true;
					}
				} else if (r.getSide() == Consts.TANNER) {
					votedTanner = true;
				}
			} else {
				if (r.getSide() == Consts.WOLF) {
					missedWerewolf = true;
				}
			}
		}
		
		if (votedTanner) {
			tannerWin = true;
		}
		
		if (votedWerewolf) {
			if (killedPope) {
				winSide = Consts.WOLF;
			} else {
				winSide = Consts.HUMAN;
			}
		} else if (votedMinion){
			if (missedWerewolf) {
				winSide = Consts.WOLF;
			} else {
				if (killedPope) {
					winSide = Consts.WOLF;
				} else {
					winSide = Consts.HUMAN;
				}
			}
		} else if (missedWerewolf == false) {
			if (votedTanner) {
				winSide = Consts.OTHER;
			} else {
				if (votedSomeone) {
					winSide = Consts.OTHER;
				} else {
					winSide = Consts.HUMAN;
				}
				
			}
		} else {
			winSide = Consts.WOLF;
		}
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
	
	public void genBoardId() {
		Date date = new Date();
		id = Long.toString(date.getTime());
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	public List<Role> getRolesThisGame() {
		return rolesThisGame;
	}
	public void setRolesThisGame(List<Role> rolesThisGame) {
		this.rolesThisGame = rolesThisGame;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public List<List<Role>> getCenterRoles() {
		return centerRoles;
	}
	public void setCenterRoles(List<List<Role>> centerRoles) {
		this.centerRoles = centerRoles;
	}
	public List<String> getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(List<String> confirmed) {
		this.confirmed = confirmed;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}
	public int getWinSide() {
		return winSide;
	}
	public void setWinSide(int winSide) {
		this.winSide = winSide;
	}
	public String getLord() {
		return lord;
	}
	public void setLord(String lord) {
		this.lord = lord;
	}
	public boolean isCanNight() {
		return canNight;
	}
	public void setCanNight(boolean canNight) {
		this.canNight = canNight;
	}
	public int getDetectiveIndex() {
		return detectiveIndex;
	}
	public void setDetectiveIndex(int detectiveIndex) {
		this.detectiveIndex = detectiveIndex;
	}
	public String getDetectiveRoleImg() {
		return detectiveRoleImg;
	}
	public void setDetectiveRoleImg(String detectiveRoleImg) {
		this.detectiveRoleImg = detectiveRoleImg;
	}
	public boolean isSoleWolf() {
		return soleWolf;
	}
	public void setSoleWolf(boolean soleWolf) {
		this.soleWolf = soleWolf;
	}
	public int getFirstPlayerIndex() {
		return firstPlayerIndex;
	}
	public void setFirstPlayerIndex(int firstPlayerIndex) {
		this.firstPlayerIndex = firstPlayerIndex;
	}
	public int getRestrictedIndex() {
		return restrictedIndex;
	}
	public void setRestrictedIndex(int restrictedIndex) {
		this.restrictedIndex = restrictedIndex;
	}
	public int getWeremeleonIndex() {
		return weremeleonIndex;
	}
	public void setWeremeleonIndex(int weremeleonIndex) {
		this.weremeleonIndex = weremeleonIndex;
	}
	public String getWeremeleonImg() {
		if (weremeleonIndex != -1) {
			Role r = rolesThisGame.get(weremeleonIndex);
			return r.getImg();
		} else {
			return "";
		}
	}
	public Role getWeremeleonRole() {
		if (weremeleonIndex != -1) {
			Role r = rolesThisGame.get(weremeleonIndex);
			return r;
		} else {
			return null;
		}
	}

	public List<String> getWinPlayers(){
		List<String> ans = new ArrayList<>();
		if (status != Consts.AFTERVOTE) return ans;
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getCurrentRole().getSide() == Consts.TANNER) {
				if (tannerWin) {
					ans.add(players.get(i).getName());
					continue;
				}
			}
			if (players.get(i).getCurrentRole().getSide() == winSide) {
				ans.add(players.get(i).getName());
			}
		}
		return ans;
	}
	
	public List<String> getLosePlayers(){
		List<String> ans = new ArrayList<>();
		if (status != Consts.AFTERVOTE) return ans;
		int i;
		for (i=0;i<players.size();i++) {
			if (players.get(i).getCurrentRole().getSide() == Consts.TANNER) {
				if (!tannerWin) {
					ans.add(players.get(i).getName());
					continue;
				}
			}
			if (players.get(i).getCurrentRole().getSide() != winSide) {
				ans.add(players.get(i).getName());
			}
		}
		return ans;
	}

	public BoardEntity toBoardEntity(String name) {
		BoardEntity entity = new BoardEntity();
		String initialRole = "-1";
		String initialRoleName = "";
		String lastSeenRole = "-1";
		String choosePlayerNum = "0";
		String chooseCenterNum = "0";
		String myIndex = "-1";
		String canChooseBoth = "n";
		String mandatory = "n";
		String hasSkill = "n";
		String showUpdatedRole = "n";
		String updatedRole = "";
		String confirmed = "n";
		String voted = "n";
		String beggarIndex = "-1";
		List<String> centerMsg = new ArrayList<>();
		List<String> playerMarks = new ArrayList<>();
		List<String> centerMarks = new ArrayList<>();
		List<String> playerNames = new ArrayList<>();
		List<String> playerDisplayNames = new ArrayList<>();
		List<String> numVotes = new ArrayList<>();
		List<String> playerVotes = new ArrayList<>();
		List<String> votedOut = new ArrayList<>();
		List<String> finalRoles = new ArrayList<>();
		int i,j;
		
		decideWinSide();
		
		for (i=0;i<players.size();i++) {
			Player p = players.get(i);
			playerNames.add(players.get(i).getName());
			playerDisplayNames.add(players.get(i).getDisplayName());
			if (status == Consts.AFTERVOTE) {
				numVotes.add(Integer.toString(p.getNumVotes()));
				playerVotes.add(Integer.toString(p.getVoteIndex()));
				finalRoles.add(p.getCurrentRole().getFinalImg());
				if (p.isVotedOut()) {
					votedOut.add("y");
				} else {
					votedOut.add("n");
				}
			}
			if (players.get(i).getName().contentEquals(name)) {
				beggarIndex = Integer.toString(players.get(i).getBeggarIndex());
				myIndex = Integer.toString(i);
				if (p.getInitialRole() == null) {
					initialRole = "-1";
				} else {
					initialRole = p.getInitialRole().getImg();
					initialRoleName = p.getInitialRole().getName();
					choosePlayerNum = Integer.toString(p.getInitialRole().getChoosePlayerNum());
					chooseCenterNum = Integer.toString(p.getInitialRole().getChooseCenterNum());
					if (p.getInitialRole().isCanChooseBoth()) {
						canChooseBoth = "y";
					}
					if (p.getInitialRole().isMandatory()) {
						mandatory = "y";
					}
					if (p.getInitialRole().isHasNight()) {
						hasSkill = "y";
					}
					if (p.isShowUpdatedRole()) {
						showUpdatedRole = "y";
						updatedRole = p.getUpdatedRole().getFinalImg();
					}
					if (status == Consts.NIGHT) {
						if (p.isConfirmed()) {
							centerMsg = p.getInitialRole().getConfirmedMsg();
						} else {
							centerMsg = p.getInitialRole().getNightMsg();
						}
					} else if (status == Consts.DAY) {
						if (p.isVoted()) {
							centerMsg = p.getInitialRole().getVotedMsg();
						} else {
							centerMsg = p.getInitialRole().getDayMsg();
						}
					}
					if (p.isConfirmed()) {
						confirmed = "y";
					}
					if (p.isVoted()) {
						voted = "y";
					}
					
				}
				for (j=0;j<p.getPlayerMarks().size();j++) {
					int x = p.getPlayerMarks().get(j);
					String s;
					if (x<0) {
						s = Integer.toString(x);
					} else {
						Role r = RoleFactory.createRole(x);
						s = r.getDisplayImg();
					}
					playerMarks.add(s);
					if (j == p.getIndex()) {
						lastSeenRole = s;
					}
				}
				for (j=0;j<p.getCenterMarks().size();j++) {
					int x = p.getCenterMarks().get(j);
					String s;
					if (x<0) {
						s = Integer.toString(x);
					} else {
						Role r = RoleFactory.createRole(x);
						s = r.getDisplayImg();
					}
					centerMarks.add(s);
				}
			}
		}
		
		List<String> lor = new ArrayList<>();
		for (i=0;i<rolesThisGame.size();i++) {
			lor.add(rolesThisGame.get(i).getImg());
		}
		if (canNight) {
			entity.setCanNight("y");
		} else {
			entity.setCanNight("n");
		}
		if (soleWolf) {
			entity.setSoleWolf("y");
		} else {
			entity.setSoleWolf("n");
		}
		
		String restrictedPlayer = "";
		if (restrictedIndex != -1) {
			restrictedPlayer = players.get(restrictedIndex).getName();
		}
		
		List<String> rolesChoose = new ArrayList<>();
		AllRes allRes = new AllRes();
		List<Role> allRoles = allRes.getAllRoles();
		for (i=0;i<allRoles.size();i++) {
			rolesChoose.add(allRoles.get(i).getImg());
		}
		
		String firstPlayer = "";
		if (firstPlayerIndex != -1) {
			firstPlayer = players.get(firstPlayerIndex).getDisplayName();
		}
		
		entity.setId(id);
		entity.setLord(lord);
		entity.setStatus(Integer.toString(status));
		entity.setRound(Integer.toString(round));
		entity.setTotalRounds(Integer.toString(totalRounds));
		entity.setPlayerNames(playerNames);
		entity.setPlayerDisplayNames(playerDisplayNames);
		entity.setInitialRole(initialRole);
		entity.setInitialRoleName(initialRoleName);
		entity.setLastSeenRole(lastSeenRole);
		entity.setPlayerMarks(playerMarks);
		entity.setCenterMarks(centerMarks);
		entity.setRolesThisGame(lor);
		entity.setChooseCenterNum(chooseCenterNum);
		entity.setChoosePlayerNum(choosePlayerNum);
		entity.setMyIndex(myIndex);
		entity.setCanChooseBoth(canChooseBoth);
		entity.setMandatory(mandatory);
		entity.setHasSkill(hasSkill);
		entity.setUpdatedRole(updatedRole);
		entity.setShowUpdatedRole(showUpdatedRole);
		entity.setCenterMsg(centerMsg);
		entity.setConfirmed(confirmed);
		entity.setVoted(voted);
		entity.setRolesChoose(rolesChoose);
		entity.setWinPlayers(getWinPlayers());
		entity.setLosePlayers(getLosePlayers());
		entity.setNumVotes(numVotes);
		entity.setPlayerVotes(playerVotes);
		entity.setVotedOut(votedOut);
		entity.setFinalRoles(finalRoles);
		entity.setDetectiveIndex(Integer.toString(detectiveIndex));
		entity.setDetectiveRoleImg(detectiveRoleImg);
		entity.setFirstPlayer(firstPlayer);
		entity.setRestrictedIndex(Integer.toString(restrictedIndex));
		entity.setRestrictedPlayer(restrictedPlayer);
		entity.setBeggarIndex(beggarIndex);
		return entity;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("status", status);
		doc.append("round", round);
		doc.append("totalRounds", totalRounds);
		doc.append("confirmed", confirmed);
		doc.append("lord", lord);
		doc.append("canNight", canNight);
		doc.append("detectiveIndex", detectiveIndex);
		doc.append("detectiveRoleImg", detectiveRoleImg);
		doc.append("soleWolf", soleWolf);
		doc.append("firstPlayerIndex", firstPlayerIndex);
		doc.append("restrictedIndex", restrictedIndex);
		doc.append("weremeleonIndex", weremeleonIndex);
		int i,j;
		List<String> lor = new ArrayList<>();
		for (i=0;i<rolesThisGame.size();i++) {
			lor.add(rolesThisGame.get(i).getImg());
		}
		doc.append("rolesThisGame", lor);
		List<List<String>> loc = new ArrayList<>();
		for (i=0;i<centerRoles.size();i++) {
			List<String> sloc = new ArrayList<>();
			for (j=0;j<centerRoles.get(i).size();j++) {
				sloc.add(centerRoles.get(i).get(j).getDBStorageImg());
			}
			loc.add(sloc);
		}
		doc.append("centerRoles", loc);
		List<String> playerNames = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			String playerName = players.get(i).getName();
			playerNames.add(playerName);
			String s = "player-" + playerName;
			doc.append(s, players.get(i).toDocument());
		}
		doc.append("playerNames", playerNames);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		id = doc.getString("id");
		status = doc.getInteger("status", 0);
		round = doc.getInteger("round", 0);
		totalRounds = doc.getInteger("totalRounds", totalRounds);
		confirmed = (List<String>) doc.get("confirmed");
		lord = doc.getString("lord");
		canNight = doc.getBoolean("canNight", false);
		detectiveIndex = doc.getInteger("detectiveIndex", -1);
		detectiveRoleImg = doc.getString("detectiveRoleImg");
		soleWolf = doc.getBoolean("soleWolf", false);
		firstPlayerIndex = doc.getInteger("firstPlayerIndex", 0);
		restrictedIndex = doc.getInteger("restrictedIndex", -1);
		weremeleonIndex = doc.getInteger("weremeleonIndex", -1);
		int i,j;
		List<String> lor = (List<String>) doc.get("rolesThisGame");
		rolesThisGame = new ArrayList<>();
		for (i=0;i<lor.size();i++) {
			Role r = RoleFactory.createRole(lor.get(i));
			r.setBoard(this);
			rolesThisGame.add(r);
		}
		List<List<String>> loc = (List<List<String>>) doc.get("centerRoles");
		centerRoles = new ArrayList<>();
		for (i=0;i<loc.size();i++) {
			List<Role> singleCenterRoles = new ArrayList<>();
			for (j=0;j<loc.get(i).size();j++) {
				Role r = RoleFactory.createRole(loc.get(i).get(j));
				r.setBoard(this);
				singleCenterRoles.add(r);
			}
			centerRoles.add(singleCenterRoles);
		}
		List<String> playerNames = (List<String>) doc.get("playerNames");
		players = new ArrayList<>();
		for (i=0;i<playerNames.size();i++) {
			String playerName = "player-" + playerNames.get(i);
			Document dop = (Document) doc.get(playerName);
			Player p = new Player();
			p.setBoard(this);
			p.setFromDoc(dop);
			p.setIndex(i);
			players.add(p);
		}
	}
	
	public void dismiss() {
		dbutil.delete("id", id);
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
	
	public void updatePlayer(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayer(int index) {
		Player p = players.get(index);
		if (p != null) {
			Document dop = p.toDocument();
			String playerName = "player-" + p.getName();
			dbutil.update("id", id, playerName, dop);
		}
	}
	
	public void updatePlayers() {
		for (int i=0;i<players.size();i++) {
			updatePlayer(players.get(i).getName());
		}
	}
	
	public void updateRolesThisGame() {
		int i;
		List<String> lor = new ArrayList<>();
		for (i=0;i<rolesThisGame.size();i++) {
			lor.add(rolesThisGame.get(i).getImg());
		}
		dbutil.update("id", id, "rolesThisGame", lor);
	}
	
	public void updateCenterRoles() {
		int i,j;
		List<List<String>> loc = new ArrayList<>();
		for (i=0;i<centerRoles.size();i++) {
			List<String> sloc = new ArrayList<>();
			for (j=0;j<centerRoles.get(i).size();j++) {
				sloc.add(centerRoles.get(i).get(j).getDBStorageImg());
			}
			loc.add(sloc);
		}
		dbutil.update("id", id, "centerRoles", loc);
	}
	
	public void addPlayerToDB(String name) {
		Player p = getPlayerByName(name);
		if (p != null) {
			dbutil.push("id", id, "playerNames", name);
			updatePlayer(name);
		}
	}
	
	public void addPlayer(String name) {
		Player p = new Player();
		p.setName(name);
		p.setDisplayName(name);
		players.add(p);
	}
	
	public void addBot() {
		String botName = "P" + Integer.toString(players.size());
		Player bot = new Player();
		bot.setName(botName);
		bot.setDisplayName(botName);
		bot.setBot(true);
		players.add(bot);
		addPlayerToDB(botName);
	}
	
	public boolean exists(String id) {
		Document doc = dbutil.read("id", id);
		if (doc == null) {
			return false;
		} else {
			return true;
		}
	}
}
