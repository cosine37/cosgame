package com.cosine.cosgame.rich.entity;

import java.util.List;
import java.util.Map;

public class MapEntity {
	int width;
	int height;
	List<PlaceEntity> places;
	List<Integer> jailPlayersIndex;
	int jailZone;
	int bailCost;
	int numDice;
	String utilityName;
	String stationName;
	List<String> areaNames;
	List<String> cornerNames;
	Map<String, String> nameStyle;
	String name;
	
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
	public List<PlaceEntity> getPlaces() {
		return places;
	}
	public void setPlaces(List<PlaceEntity> places) {
		this.places = places;
	}
	public List<Integer> getJailPlayersIndex() {
		return jailPlayersIndex;
	}
	public void setJailPlayersIndex(List<Integer> jailPlayersIndex) {
		this.jailPlayersIndex = jailPlayersIndex;
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
	public List<String> getAreaNames() {
		return areaNames;
	}
	public void setAreaNames(List<String> areaNames) {
		this.areaNames = areaNames;
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
	public Map<String, String> getNameStyle() {
		return nameStyle;
	}
	public void setNameStyle(Map<String, String> nameStyle) {
		this.nameStyle = nameStyle;
	}
	
}
