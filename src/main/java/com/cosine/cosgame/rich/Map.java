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
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("width", width);
		doc.append("height", height);
		doc.append("fateIds", fateIds);
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
		List<Document> placesDocList = (List<Document>)doc.get("places");
		places = new ArrayList<>();
		for (i=0;i<placesDocList.size();i++){
			Place e = Factory.genPlace(placesDocList.get(i), board);
			e.setId(i);
			places.add(e);
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
		return entity;
	}
	
	public Map() {
		places = new ArrayList<>();
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
}
