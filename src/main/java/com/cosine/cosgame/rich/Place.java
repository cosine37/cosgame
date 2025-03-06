package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.entity.PlaceEntity;

public abstract class Place {
	protected int id;
	protected int type;
	
	protected String name;
	protected Place prev;
	protected Place next;
	
	protected Board board;
	
	protected List<Player> playersOn;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("id",id);
		doc.append("type",type);
		doc.append("name",name);
		List<Integer> playersOnDocList = new ArrayList<>();
		for (i=0;i<playersOn.size();i++){
			playersOnDocList.add(playersOn.get(i).getIndex());
		}
		doc.append("playersOn",playersOnDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		id = doc.getInteger("id",0);
		type = doc.getInteger("type",0);
		name = doc.getString("name");
		List<Integer> playersOnDocList = (List<Integer>)doc.get("playersOn");
		playersOn = new ArrayList<>();
		for (i=0;i<playersOnDocList.size();i++){
			int index = playersOnDocList.get(i);
			Player e = board.getPlayers().get(index);
			playersOn.add(e);
			e.setPlace(this);
		}
	}
	public PlaceEntity toPlaceEntity() {
		PlaceEntity entity = new PlaceEntity();
		entity.setName(name);
		int i;
		List<Integer> pis = new ArrayList<>();
		for (i=0;i<playersOn.size();i++) {
			pis.add(playersOn.get(i).getIndex());
		}
		entity.setPlayersOn(pis);
		return entity;
	}
	
	public Place(int id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
		
		playersOn = new ArrayList<>();
	}
	
	public Place(Document doc, Board board) {
		this.board = board;
		setFromDoc(doc);
	}
	
	public void stepOn(Player p) {
		addPlayerOn(p);
	}
	
	public void bypass(Player p) {}

	public boolean hasPlayer(int index) {
		boolean f = false;
		for (int i=0;i<playersOn.size();i++) {
			if (playersOn.get(i).getIndex() == index) {
				f = true;
				break;
			}
		}
		return f;
	}
	
	public boolean hasPlayer(Player p) {
		return hasPlayer(p.getIndex());
	}
	
	public void removePlayer(Player p) {
		for (int i=0;i<playersOn.size();i++) {
			if (playersOn.get(i).getIndex() == p.getIndex()) {
				playersOn.remove(i);
				break;
			}
		}
	}
	
	public void addPlayerOn(Player p) {
		if (hasPlayer(p) == false) {
			playersOn.add(p);
			p.setPlace(this);
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Place getPrev() {
		return prev;
	}
	public void setPrev(Place prev) {
		this.prev = prev;
	}
	public Place getNext() {
		return next;
	}
	public void setNext(Place next) {
		this.next = next;
	}
	public List<Player> getPlayersOn() {
		return playersOn;
	}
	public void setPlayersOn(List<Player> playersOn) {
		this.playersOn = playersOn;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	

}
