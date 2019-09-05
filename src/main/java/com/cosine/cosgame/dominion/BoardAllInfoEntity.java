package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class BoardAllInfoEntity {
	boolean isLord;
	int status;
	List<Pile> kindom;
	List<String> names;
	List<String> ready;
	
	public BoardAllInfoEntity(Board board) {
		isLord = false;
		status = board.getStatus();
		kindom = board.getKindom();
		names = board.getPlayerNames();
		ready = new ArrayList<>();
		for (int i=0;i<board.getPlayers().size();i++) {
			if (board.getPlayers().get(i).getIsBot()) {
				ready.add("Bot Ready");
			} else if (board.getPlayers().get(i).getIsReady()) {
				ready.add("Ready");
			} else {
				ready.add("Not Ready");
			}
		}
	}

	public boolean getLord() {
		return isLord;
	}

	public void setLord(boolean isLord) {
		this.isLord = isLord;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Pile> getKindom() {
		return kindom;
	}

	public void setKindom(List<Pile> kindom) {
		this.kindom = kindom;
	}

	public List<String> getNames() {
		return names;
	}

	public void setNames(List<String> names) {
		this.names = names;
	}

	public List<String> getReady() {
		return ready;
	}

	public void setReady(List<String> ready) {
		this.ready = ready;
	}
	
	
	
}
