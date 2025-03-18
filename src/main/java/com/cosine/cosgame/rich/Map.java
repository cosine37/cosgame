package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

import com.cosine.cosgame.rich.entity.MapEntity;
import com.cosine.cosgame.rich.entity.PlaceEntity;

public class Map {
	int width;
	int height;
	List<Place> places;
	List<Integer> fateIds;
	List<Player> jailPlayers;
	List<String> areaColors;
	List<String> areaNames;
	int jailIndex;
	int jailZone;
	int bailCost;
	int numDice;
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("width", width);
		doc.append("height", height);
		doc.append("fateIds", fateIds);
		doc.append("jailIndex", jailIndex);
		doc.append("jailZone", jailZone);
		doc.append("bailCost", bailCost);
		doc.append("numDice", numDice);
		doc.append("areaColors", areaColors);
		doc.append("areaNames", areaNames);
		List<Integer> jailPlayerIndexes = new ArrayList<>();
		for (i=0;i<jailPlayers.size();i++) {
			jailPlayerIndexes.add(jailPlayers.get(i).getIndex());
		}
		doc.append("jailPlayerIndexes", jailPlayerIndexes);
		List<Document> placesDocList = new ArrayList<>();
		for (i=0;i<places.size();i++){
			placesDocList.add(places.get(i).toDocument());
		}
		doc.append("places",placesDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		height = doc.getInteger("height", 0);
		width = doc.getInteger("width", 0);
		fateIds = (List<Integer>) doc.get("fateIds");
		jailIndex = doc.getInteger("jailIndex", -1);
		jailZone = doc.getInteger("jailZone", -1);
		bailCost = doc.getInteger("bailCost", 0);
		numDice = doc.getInteger("numDice", 1);
		areaColors = (List<String>) doc.get("areaColors");
		areaNames = (List<String>) doc.get("areaNames");
		List<Document> placesDocList = (List<Document>)doc.get("places");
		places = new ArrayList<>();
		for (i=0;i<placesDocList.size();i++){
			Place e = Factory.genPlace(placesDocList.get(i), board);
			e.setId(i);
			places.add(e);
		}
		List<Integer> jailPlayerIndexes = (List<Integer>) doc.get("jailPlayerIndexes");
		jailPlayers = new ArrayList<>();
		for (i=0;i<jailPlayerIndexes.size();i++) {
			jailPlayers.add(board.getPlayers().get(jailPlayerIndexes.get(i)));
		}
	}
	public MapEntity toMapEntity() {
		int i;
		MapEntity entity = new MapEntity();
		List<PlaceEntity> pes = new ArrayList<>();
		for (i=0;i<places.size();i++) {
			pes.add(places.get(i).toPlaceEntity());
		}
		entity.setPlaces(pes);
		entity.setHeight(height);
		entity.setWidth(width);
		entity.setJailZone(jailZone);
		entity.setBailCost(bailCost);
		entity.setNumDice(numDice);
		List<Integer> jailPlayerIndexes = new ArrayList<>();
		for (i=0;i<jailPlayers.size();i++) {
			jailPlayerIndexes.add(jailPlayers.get(i).getIndex());
		}
		entity.setJailPlayersIndex(jailPlayerIndexes);
		return entity;
	}
	
	public Map() {
		places = new ArrayList<>();
		fateIds = new ArrayList<>();
		jailPlayers = new ArrayList<>();
	}
	
	public void genMap(int x) {
		if (x == 0) {
			places = AllRes.genTestMap();
		}
	}
	
	public void addPlace(Place place) {
		places.add(place);
	}
	
	public Place getPlace(int x) {
		if (x>=0 && x<places.size()) {
			return places.get(x);
		} else return null;
	}
	
	public Place getPlaceAfter(int x, int k) {
		if (places.size() == 0) return null;
		int t = x;
		for (int i=0;i<k;i++) {
			t = (t+1)%places.size();
		}
		return places.get(t); 
	}
	
	public void printMap() {
		int i,j;
		for (i=0;i<places.size();i++) {
			String line = places.get(i).getName();
			for (j=0;j<places.get(i).getPlayersOn().size();j++) {
				line = line + " " + places.get(i).getPlayersOn().get(j).getName();
			}
			System.out.println(line);
		}
	}
	public int genRandomFateId() {
		int n = fateIds.size();
		if (n == 0) {
			return -1;
		} else {
			Random rand = new Random();
			int x = rand.nextInt(n*1000);
			x = x%n;
			return fateIds.get(x);
		}
		
	}
	
	public boolean escapedFromJail() {
		if (numDice == 1) {
			if (board.getLastRolled() == 6) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	public Place getJail() {
		if (jailIndex>=0 && jailIndex<places.size()) {
			return places.get(jailIndex); 
		} else {
			return null;
		}
	}
	public void addToJail(Player p) {
		jailPlayers.add(p);
	}
	public void removeFromJail(Player p) {
		for (int i=0;i<jailPlayers.size();i++) {
			if (jailPlayers.get(i).getName().contentEquals(p.getName())) {
				jailPlayers.remove(i);
			}
		}
	}
	public int mapSize() {
		return places.size();
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Integer> getFateIds() {
		return fateIds;
	}
	public void setFateIds(List<Integer> fateIds) {
		this.fateIds = fateIds;
	}
	public List<Player> getJailPlayers() {
		return jailPlayers;
	}
	public void setJailPlayers(List<Player> jailPlayers) {
		this.jailPlayers = jailPlayers;
	}
	public int getJailIndex() {
		return jailIndex;
	}
	public void setJailIndex(int jailIndex) {
		this.jailIndex = jailIndex;
	}
	public int getJailZone() {
		return jailZone;
	}
	public void setJailZone(int jailZone) {
		this.jailZone = jailZone;
	}
	public int getBailCost() {
		return bailCost;
	}
	public void setBailCost(int bailCost) {
		this.bailCost = bailCost;
	}
	public int getNumDice() {
		return numDice;
	}
	public void setNumDice(int numDice) {
		this.numDice = numDice;
	}
	public List<String> getAreaColors() {
		return areaColors;
	}
	public void setAreaColors(List<String> areaColors) {
		this.areaColors = areaColors;
	}
	public List<String> getAreaNames() {
		return areaNames;
	}
	public void setAreaNames(List<String> areaNames) {
		this.areaNames = areaNames;
	}
}
