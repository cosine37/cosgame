package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.HashMap;
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
	List<String> cornerNames;
	List<String> bgms;
	int jailIndex;
	int jailZone;
	int bailCost;
	int numDice;
	String utilityName;
	String stationName;
	String name;
	String nameFont;
	String mapZoom;
	String centerZoom;
	String centerWidth;
	String centerHeight;
	String logHeight;
	
	// GTA related
	int hospitalIndex;
	int wardZone;
	List<Player> wardPlayers;
	List<Integer> vehicleIds;
	List<Integer> commonCardIds;
	List<Integer> uncommonCardIds;
	List<Integer> rareCardIds;
	List<Integer> epicCardIds;
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		List<Document> placesDocList = new ArrayList<>();
		for (i=0;i<places.size();i++){
			placesDocList.add(places.get(i).toDocument());
		}
		doc.append("places",placesDocList);
		doc.append("width", width);
		doc.append("height", height);
		doc.append("fateIds", fateIds);
		doc.append("vehicleIds", vehicleIds);
		doc.append("jailIndex", jailIndex);
		doc.append("jailZone", jailZone);
		doc.append("hospitalIndex", hospitalIndex);
		doc.append("wardZone", wardZone);
		doc.append("bailCost", bailCost);
		doc.append("numDice", numDice);
		doc.append("areaColors", areaColors);
		doc.append("areaNames", areaNames);
		doc.append("utilityName", utilityName);
		doc.append("stationName", stationName);
		doc.append("cornerNames", cornerNames);
		doc.append("name", name);
		doc.append("nameFont", nameFont);
		doc.append("bgms", bgms);
		doc.append("mapZoom", mapZoom);
		doc.append("centerZoom", centerZoom);
		doc.append("centerWidth", centerWidth);
		doc.append("centerHeight", centerHeight);
		doc.append("logHeight", logHeight);
		List<Integer> jailPlayerIndexes = new ArrayList<>();
		for (i=0;i<jailPlayers.size();i++) {
			jailPlayerIndexes.add(jailPlayers.get(i).getIndex());
		}
		doc.append("jailPlayerIndexes", jailPlayerIndexes);
		List<Integer> wardPlayerIndexes = new ArrayList<>();
		for (i=0;i<wardPlayers.size();i++) {
			wardPlayerIndexes.add(wardPlayers.get(i).getIndex());
		}
		doc.append("wardPlayerIndexes", wardPlayerIndexes);
		doc.append("commonCardIds", commonCardIds);
		doc.append("uncommonCardIds", uncommonCardIds);
		doc.append("rareCardIds", rareCardIds);
		doc.append("epicCardIds", epicCardIds);
		
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		height = doc.getInteger("height", 0);
		width = doc.getInteger("width", 0);
		fateIds = (List<Integer>) doc.get("fateIds");
		vehicleIds = (List<Integer>) doc.get("vehicleIds");
		jailIndex = doc.getInteger("jailIndex", -1);
		jailZone = doc.getInteger("jailZone", -1);
		hospitalIndex = doc.getInteger("hospitalIndex", -1);
		wardZone = doc.getInteger("wardZone", -1);
		bailCost = doc.getInteger("bailCost", 0);
		numDice = doc.getInteger("numDice", 1);
		areaColors = (List<String>) doc.get("areaColors");
		areaNames = (List<String>) doc.get("areaNames");
		utilityName = doc.getString("utilityName");
		stationName = doc.getString("stationName");
		cornerNames = (List<String>) doc.get("cornerNames");
		name = doc.getString("name");
		nameFont = doc.getString("nameFont");
		bgms = (List<String>) doc.get("bgms");
		mapZoom = doc.getString("mapZoom");
		centerZoom = doc.getString("centerZoom");
		centerWidth = doc.getString("centerWidth");
		centerHeight = doc.getString("centerHeight");
		logHeight = doc.getString("logHeight");
		commonCardIds = (List<Integer>) doc.get("commonCardIds");
		uncommonCardIds = (List<Integer>) doc.get("uncommonCardIds");
		rareCardIds = (List<Integer>) doc.get("rareCardIds");
		epicCardIds = (List<Integer>) doc.get("epicCardIds");
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
		List<Integer> wardPlayerIndexes = (List<Integer>) doc.get("wardPlayerIndexes");
		wardPlayers = new ArrayList<>();
		for (i=0;i<wardPlayerIndexes.size();i++) {
			wardPlayers.add(board.getPlayers().get(wardPlayerIndexes.get(i)));
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
		entity.setStationName(stationName);
		entity.setUtilityName(utilityName);
		entity.setAreaNames(areaNames);
		entity.setCornerNames(cornerNames);
		entity.setName(name);
		entity.setBgms(bgms);
		List<Integer> jailPlayerIndexes = new ArrayList<>();
		for (i=0;i<jailPlayers.size();i++) {
			jailPlayerIndexes.add(jailPlayers.get(i).getIndex());
		}
		entity.setJailPlayersIndex(jailPlayerIndexes);
		List<Integer> wardPlayerIndexes = new ArrayList<>();
		for (i=0;i<wardPlayers.size();i++) {
			wardPlayerIndexes.add(wardPlayers.get(i).getIndex());
		}
		entity.setWardPlayersIndex(wardPlayerIndexes);
		HashMap<String, String> fontStyle = new HashMap<>();
		fontStyle.put("font-family", nameFont);
		entity.setNameStyle(fontStyle);
		HashMap<String, String> mapStyle = new HashMap<>();
		mapStyle.put("zoom", mapZoom);
		HashMap<String, String> centerStyle = new HashMap<>();
		centerStyle.put("zoom", centerZoom);
		centerStyle.put("height", centerHeight);
		centerStyle.put("width", centerWidth);
		HashMap<String, String> logStyle = new HashMap<>();
		logStyle.put("height", logHeight);
		entity.setMapStyle(mapStyle);
		entity.setCenterStyle(centerStyle);
		entity.setLogStyle(logStyle);
		return entity;
	}
	
	public Map() {
		places = new ArrayList<>();
		fateIds = new ArrayList<>();
		jailPlayers = new ArrayList<>();
		wardPlayers = new ArrayList<>();
		commonCardIds = new ArrayList<>();
		uncommonCardIds = new ArrayList<>();
		rareCardIds = new ArrayList<>();
		epicCardIds = new ArrayList<>();
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
	
	// jail related
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
	
	// hospital/ward related
	public Place getHospital() {
		if (hospitalIndex>=0 && hospitalIndex<places.size()) {
			return places.get(hospitalIndex); 
		} else {
			return null;
		}
	}
	public void addToWard(Player p) {
		wardPlayers.add(p);
	}
	public void removeFromWard(Player p) {
		for (int i=0;i<wardPlayers.size();i++) {
			if (wardPlayers.get(i).getName().contentEquals(p.getName())) {
				wardPlayers.remove(i);
			}
		}
	}
	// end of hospital/ward related
	
	// card related
	public void sortCardRarity(List<Integer> ids) {
		int i;
		for (i=0;i<ids.size();i++) {
			Card c = Factory.genNewCard(ids.get(i));
			if (c.getRarity() == 0) {
				commonCardIds.add(ids.get(i));
			} else if (c.getRarity() == 1) {
				uncommonCardIds.add(ids.get(i));
			} else if (c.getRarity() == 2) {
				rareCardIds.add(ids.get(i));
			} else if (c.getRarity() == 3) {
				epicCardIds.add(ids.get(i));
			}
		}
	}
	// end of card related
	
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
	public String getUtilityName() {
		return utilityName;
	}
	public void setUtilityName(String utilityName) {
		this.utilityName = utilityName;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public List<String> getCornerNames() {
		return cornerNames;
	}
	public void setCornerNames(List<String> cornerNames) {
		this.cornerNames = cornerNames;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameFont() {
		return nameFont;
	}
	public void setNameFont(String nameFont) {
		this.nameFont = nameFont;
	}
	public List<String> getBgms() {
		return bgms;
	}
	public void setBgms(List<String> bgms) {
		this.bgms = bgms;
	}
	public String getMapZoom() {
		return mapZoom;
	}
	public void setMapZoom(String mapZoom) {
		this.mapZoom = mapZoom;
	}
	public String getCenterZoom() {
		return centerZoom;
	}
	public void setCenterZoom(String centerZoom) {
		this.centerZoom = centerZoom;
	}
	public String getCenterWidth() {
		return centerWidth;
	}
	public void setCenterWidth(String centerWidth) {
		this.centerWidth = centerWidth;
	}
	public String getCenterHeight() {
		return centerHeight;
	}
	public void setCenterHeight(String centerHeight) {
		this.centerHeight = centerHeight;
	}
	public String getLogHeight() {
		return logHeight;
	}
	public void setLogHeight(String logHeight) {
		this.logHeight = logHeight;
	}
	public int getHospitalIndex() {
		return hospitalIndex;
	}
	public void setHospitalIndex(int hospitalIndex) {
		this.hospitalIndex = hospitalIndex;
	}
	public int getWardZone() {
		return wardZone;
	}
	public void setWardZone(int wardZone) {
		this.wardZone = wardZone;
	}
	public List<Player> getWardPlayers() {
		return wardPlayers;
	}
	public void setWardPlayers(List<Player> wardPlayers) {
		this.wardPlayers = wardPlayers;
	}
	public List<Integer> getVehicleIds() {
		return vehicleIds;
	}
	public void setVehicleIds(List<Integer> vehicleIds) {
		this.vehicleIds = vehicleIds;
	}
	public List<Integer> getCommonCardIds() {
		return commonCardIds;
	}
	public void setCommonCardIds(List<Integer> commonCardIds) {
		this.commonCardIds = commonCardIds;
	}
	public List<Integer> getUncommonCardIds() {
		return uncommonCardIds;
	}
	public void setUncommonCardIds(List<Integer> uncommonCardIds) {
		this.uncommonCardIds = uncommonCardIds;
	}
	public List<Integer> getRareCardIds() {
		return rareCardIds;
	}
	public void setRareCardIds(List<Integer> rareCardIds) {
		this.rareCardIds = rareCardIds;
	}
	public List<Integer> getEpicCardIds() {
		return epicCardIds;
	}
	public void setEpicCardIds(List<Integer> epicCardIds) {
		this.epicCardIds = epicCardIds;
	}
}
