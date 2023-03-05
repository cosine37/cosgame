package com.cosine.cosgame.propnight;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.propnight.entity.PlayerEntity;

public class Player {
	String name;
	int role; // human or ghost
	int index;
	int hp;
	int numPlaceNextTurn;
	List<Integer> visitedPlaces;
	List<Integer> availablePlaces;
	List<Card> cards;
	List<Integer> placeThisTurn;
	
	Board board;
	
	public Player() {
		visitedPlaces = new ArrayList<>();
		availablePlaces = new ArrayList<>();
		cards = new ArrayList<>();
		placeThisTurn = new ArrayList<>();
	}
	
	public void initialize() {
		if (role == Consts.HUMAN) {
			hp = Consts.MAXHP;
			visitedPlaces = new ArrayList<>();
			availablePlaces = new ArrayList<>();
			for (int i=1;i<=5;i++) {
				availablePlaces.add(i);
			}
			numPlaceNextTurn = 1;
		} else if (role == Consts.GHOST) {
			
		}
	}
	
	public void endTurn() {
		if (role == Consts.HUMAN) {
			visitedPlaces.addAll(placeThisTurn);
			placeThisTurn = new ArrayList<>();
		} else if (role == Consts.GHOST) {
			placeThisTurn = new ArrayList<>();
		}
	}
	
	public void rush(int x1, int x2) {
		if (hp == 0) return;
		if (visited(x1) && visited(x2)) {
			int i;
			for (i=0;i<visitedPlaces.size();i++) {
				if (visitedPlaces.get(i) == x1) {
					int x = visitedPlaces.remove(i);
					availablePlaces.add(x);
					break;
				}
			}
			for (i=0;i<visitedPlaces.size();i++) {
				if (visitedPlaces.get(i) == x2) {
					int x = visitedPlaces.remove(i);
					availablePlaces.add(x);
					break;
				}
			}
			hp--;
			sortAvailablePlaces();
		}
	}
	
	public void rush(List<Integer> targets) {
		if (targets.size() == 2) {
			rush (targets.get(0), targets.get(1));
		} else if (targets.size() == 4) {
			rush (targets.get(0), targets.get(1));
			rush (targets.get(2), targets.get(3));
		}
	}
	
	public void rest() {
		hp = Consts.MAXHP;
		while (visitedPlaces.size()>0) {
			int x = visitedPlaces.remove(0);
			availablePlaces.add(x);
		}
		board.moveGhostMark(1);
	}
	
	public void choosePlace(List<Integer> places) {
		int j;
		placeThisTurn = new ArrayList<>();
		for (j=0;j<places.size();j++) {
			int x;
			if (places.get(j) == 1) {
				x = j;
			} else {
				continue;
			}
			if (isAvailable(x)) {
				if (role == Consts.HUMAN) {
					for (int i=0;i<availablePlaces.size();i++) {
						if (x == availablePlaces.get(i)) {
							availablePlaces.remove(i);
							placeThisTurn.add(x);
							break;
						}
					}
				} else if (role == Consts.GHOST) {
					placeThisTurn.add(x);
				}
			}
		}
		
	}
	
	public void sortAvailablePlaces() {
		int i,j;
		for (i=0;i<availablePlaces.size();i++) {
			for (j=i+1;j<availablePlaces.size();j++) {
				int t1 = availablePlaces.get(i);
				int t2 = availablePlaces.get(j);
				if (t1>t2) {
					availablePlaces.set(j, t1);
					availablePlaces.set(i, t2);
				}
			}
		}
	}
	
	public boolean isAvailable(int x) {
		if (x>0 && x<=10) {
			if (role == Consts.HUMAN) {
				for (int i=0;i<availablePlaces.size();i++) {
					if (x == availablePlaces.get(i)) return true;
				}
				return false;
			} else if (role == Consts.GHOST) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean visited(int x) {
		int i;
		for (i=0;i<visitedPlaces.size();i++) {
			if (visitedPlaces.get(i) == x) return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public List<Integer> getVisitedPlaces() {
		return visitedPlaces;
	}
	public void setVisitedPlaces(List<Integer> visitedPlaces) {
		this.visitedPlaces = visitedPlaces;
	}
	public List<Integer> getAvailablePlaces() {
		return availablePlaces;
	}
	public void setAvailablePlaces(List<Integer> availablePlaces) {
		this.availablePlaces = availablePlaces;
	}
	public List<Integer> getPlaceThisTurn() {
		return placeThisTurn;
	}
	public void setPlaceThisTurn(List<Integer> placeThisTurn) {
		this.placeThisTurn = placeThisTurn;
	}
	public List<Card> getCards() {
		return cards;
	}
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getNumPlaceNextTurn() {
		return numPlaceNextTurn;
	}
	public void setNumPlaceNextTurn(int numPlaceNextTurn) {
		this.numPlaceNextTurn = numPlaceNextTurn;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("role", role);
		doc.append("index", index);
		doc.append("hp", hp);
		doc.append("placeThisTurn", placeThisTurn);
		doc.append("visitedPlaces", visitedPlaces);
		doc.append("availablePlaces", availablePlaces);
		doc.append("numPlaceNextTurn", numPlaceNextTurn);
		int i;
		List<Document> loc = new ArrayList<>();
		for (i=0;i<cards.size();i++) {
			loc.add(cards.get(i).toDocument());
		}
		doc.append("cards", loc);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		name = doc.getString("name");
		role = doc.getInteger("role", -1);
		index = doc.getInteger("index", -1);
		hp = doc.getInteger("hp", 0);
		placeThisTurn = (List<Integer>) doc.get("placeThisTurn");
		visitedPlaces = (List<Integer>) doc.get("visitedPlaces");
		availablePlaces = (List<Integer>) doc.get("availablePlaces");
		numPlaceNextTurn = doc.getInteger("numPlaceThisTurn", 1);
		int i;
		List<Document> loc = (List<Document>) doc.get("cards");
		cards = new ArrayList<>();
		for (i=0;i<loc.size();i++) {
			Card c = CardFactory.makeCard(loc.get(i));
			cards.add(c);
		}
	}
	
	public PlayerEntity toPlayerEntity() {
		PlayerEntity entity = new PlayerEntity();
		entity.setRole(role);
		entity.setName(name);
		entity.setHp(hp);
		entity.setVisitedPlaces(visitedPlaces);		
		return entity;
	}
}
