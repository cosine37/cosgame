package com.cosine.cosgame.dominion;

import java.util.List;

public class BoardEntity {
	int status;
	List<Pile> base;
	List<Pile> kindom;
	List<Pile> trash;
	List<String> logs;
	
	public BoardEntity(Board board) {
		status = board.getStatus();
		base = board.getBase();
		kindom = board.getKindom();
		trash = board.getTrash().getTrashedCardsAsPiles();
		logs = board.getLogger().getLogsAsList();
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Pile> getBase() {
		return base;
	}

	public void setBase(List<Pile> base) {
		this.base = base;
	}

	public List<Pile> getKindom() {
		return kindom;
	}

	public void setKindom(List<Pile> kindom) {
		this.kindom = kindom;
	}

	public List<Pile> getTrash() {
		return trash;
	}

	public void setTrash(List<Pile> trash) {
		this.trash = trash;
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}
	
}
