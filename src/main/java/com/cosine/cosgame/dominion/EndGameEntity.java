package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class EndGameEntity {
	public static final String[] SCORECARDS = {"Province", "Duchy", "Estate", "Gardens",
			"Mill", "Duke", "Harem", "Nobles",
			"Island",
			"Quadrangle",
			"CatanIsland",
			"Curse",
			"CorruptedOfficial"};
	String msg;
	String winner;
	String type;
	List<String> playernames;
	List<Integer> scores;
	List<List<List<String>>> records;
	
	public List<List<String>> getRecord(Player player){
		List<List<String>> record = new ArrayList<>();
		List<String> single = new ArrayList<>();
		int i,j,score,numCards, tscore;
		String desc;
		Card c;
		if (player.getVp()!=0) {
			single.add("VP token");
			single.add(Integer.toString(player.getVp()));
			single.add(Integer.toString(player.getVp()));
			record.add(single);
		}
		for (i=0;i<SCORECARDS.length;i++) {
			for (j=0;j<player.getAllCards().size();j++) {
				c = player.getAllCards().get(j).getTop();
				if (SCORECARDS[i].equals(c.getName())) {
					single = new ArrayList<>();
					single.add(c.name);
					numCards = player.getAllCards().get(j).getNumCards();
					single.add(Integer.toString(numCards));
					score = c.getScore(player);
					tscore = score * numCards;
					single.add(Integer.toString(tscore));
					
					if (i == 3) {
						desc = Integer.toString(player.getAllCardsAsCards().size()) + " cards";
					} else if (i == 5) {
						if (score == 0) {
							desc = "No Duchy";
						} else if (score == 1) {
							desc = "A Duchy";
						} else {
							desc = Integer.toString(score) + " Duchies";
						}	
					} else {
						desc = "";
					}
					single.add(desc);
					
					record.add(single);
				}
			}
		}
		return record;
	}
	
	public EndGameEntity(Board board) {
		List<Player> players = board.getPlayers();
		int i;
		winner = board.getEndPlayer();
		type= board.getEndType();
		playernames = new ArrayList<>();
		scores = new ArrayList<>();
		records = new ArrayList<>();
		for (i=0;i<players.size();i++) {
			playernames.add(players.get(i).getName());
			scores.add(players.get(i).getScore());
			records.add(getRecord(players.get(i)));
		}
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public List<String> getPlayernames() {
		return playernames;
	}

	public void setPlayernames(List<String> playernames) {
		this.playernames = playernames;
	}

	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public List<List<List<String>>> getRecords() {
		return records;
	}

	public void setRecords(List<List<List<String>>> records) {
		this.records = records;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
