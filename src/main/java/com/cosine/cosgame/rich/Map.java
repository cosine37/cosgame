package com.cosine.cosgame.rich;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Map {
	List<Place> places;
	
	public Document toDocument(){
		int i;
		Document doc = new Document();
		List<Document> placesDocList = new ArrayList<>();
		for (i=0;i<places.size();i++){
			placesDocList.add(places.get(i).toDocument());
		}
		doc.append("places",placesDocList);
		return doc;
	}
	public void setFromDoc(Document doc){
		int i;
		List<Document> placesDocList = (List<Document>)doc.get("places");
		places = new ArrayList<>();
		for (i=0;i<placesDocList.size();i++){
			Place e = Factory.genPlace(placesDocList.get(i));
			places.add(e);
		}
	}
	
	public Map() {
		
	}
	
	public void genMap(int x) {
		if (x == 0) {
			places = AllRes.genTestMap();
		}
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
}
