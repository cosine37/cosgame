package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.entity.PlaceEntity;

public abstract class Place {
	protected int id;
	protected int type;
	
	protected String name;
	protected Place prev;
	protected Place next;
	protected String desc;
	protected String landMsg;
	protected String img;
	protected String fontFamily;
	protected int fontSize;
	protected Detail detail;
	
	protected Board board;
	
	protected List<Player> playersOn;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("id",id);
		doc.append("type",type);
		doc.append("name",name);
		doc.append("desc",desc);
		doc.append("landMsg", landMsg);
		doc.append("img", img);
		doc.append("fontFamily", fontFamily);
		doc.append("fontSize", fontSize);
		List<Integer> playersOnDocList = new ArrayList<>();
		for (i=0;i<playersOn.size();i++){
			playersOnDocList.add(playersOn.get(i).getIndex());
		}
		doc.append("playersOn",playersOnDocList);
		doc.append("detail", detail.toDocument());
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		id = doc.getInteger("id",0);
		type = doc.getInteger("type",0);
		name = doc.getString("name");
		desc = doc.getString("desc");
		landMsg = doc.getString("landMsg");
		img = doc.getString("img");
		fontFamily = doc.getString("fontFamily");
		fontSize = doc.getInteger("fontSize", 16);
		List<Integer> playersOnDocList = (List<Integer>)doc.get("playersOn");
		playersOn = new ArrayList<>();
		for (i=0;i<playersOnDocList.size();i++){
			int index = playersOnDocList.get(i);
			Player e = board.getPlayers().get(index);
			playersOn.add(e);
			e.setPlaceIndex(this.id);
		}
		Document detailDoc = (Document) doc.get("detail");
		detail = new Detail(this);
		detail.setFromDoc(detailDoc);
	}
	public PlaceEntity toPlaceEntity(String username) {
		PlaceEntity entity = new PlaceEntity();
		entity.setId(id);
		entity.setType(type);
		entity.setName(name);
		entity.setDesc(this.getDesc());
		int i;
		List<Integer> pis = new ArrayList<>();
		for (i=0;i<playersOn.size();i++) {
			pis.add(playersOn.get(i).getIndex());
		}
		entity.setPlayersOn(pis);
		HashMap<String, String> imgStyle = new HashMap<>();
		imgStyle.put("background-image", "url(/image/Rich/" + img + ".png)");
		entity.setImgStyle(imgStyle);
		HashMap<String, String> fontStyle = new HashMap<>();
		fontStyle.put("font-family", fontFamily);
		fontStyle.put("font-size", Integer.toString(fontSize)+"px");
		entity.setFontStyle(fontStyle);
		if (detail != null) {
			entity.setDetail(detail.toDetailEntity());
		}
		return entity;
	}
	
	public Place(int id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
		
		desc = "";
		playersOn = new ArrayList<>();
		detail = new Detail(this);
	}
	
	public Place(Document doc, Board board) {
		this.board = board;
		setFromDoc(doc);
	}
	
	public void stepOn(Player p) {
		addPlayerOn(p);
	}
	
	public void stepOn(Player p, int option) {
		stepOn(p);
	}
	
	public void bypass(Player p) {}
	public void preStepOn(Player p) {}

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
			p.setPlaceIndex(this.id);
		}
	}
	public List<String> getResolveOptions(Player player){
		List<String> ans = new ArrayList<>();
		ans.add("确定");
		return ans;
	}
	public void setFont(String fontFamily, int fontSize) {
		this.fontFamily = fontFamily;
		this.fontSize = fontSize;
	}
	public void createDetail() {
		detail = new Detail(this);
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLandMsg(Player player) {
		return landMsg;
	}
	public void setLandMsg(String landMsg) {
		this.landMsg = landMsg;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getFontFamily() {
		return fontFamily;
	}
	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	

}
