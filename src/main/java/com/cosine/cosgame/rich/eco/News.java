package com.cosine.cosgame.rich.eco;

import com.cosine.cosgame.rich.Board;
import com.cosine.cosgame.rich.Consts;

public class News {
	protected int id;
	protected String desc;
	protected String logDesc;
	protected Board board;
	
	public News() {
		
	}
	
	public void effect() {
		// logs and broadcast
		if (logDesc != null && logDesc.length()>0) board.getLogger().log(logDesc);
		board.setBroadcastImg("news/"+id);
		board.setBroadcastMsg(desc);
		
		// add se
		String seSrc = "/sound/Rich/news/" + id + ".mp3";
		board.addSes(seSrc);
		board.setSesPlayer(Consts.SES_ALLPLAYERS);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
