package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.rich.entity.MapEntity;
import com.cosine.cosgame.rich.entity.PlaceEntity;

public class Map {
	int width;
	int height;
	List<Place> places;
	
	Board board;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		doc.append("width", width);
		doc.append("height", height);
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
		List<Document> placesDocList = (List<Document>)doc.get("places");
		places = new ArrayList<>();
		for (i=0;i<placesDocList.size();i++){
			Place e = Factory.genPlace(placesDocList.get(i), board);
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
		int i;
		for (i=0;i<places.size();i++) {
			if (places.get(i).getId() == x) {
				return places.get(i);
			}
		}
		return null;
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
}
