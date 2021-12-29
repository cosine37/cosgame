package com.cosine.cosgame.propnight;

import java.util.List;

public class Player {
	String name;
	int role; // human or ghost
	int index;
	int hp;
	List<Integer> visitedPlaces;
	List<Integer> availablePlaces;
	int placeThisTurn;
	
	Board board;
	
	public void endTurn() {
		if (role == Consts.HUMAN) {
			visitedPlaces.add(placeThisTurn);
			placeThisTurn = -1;
		} else if (role == Consts.GHOST) {
			placeThisTurn = -1;
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
	
	public void rest() {
		hp = Consts.MAXHP;
		while (visitedPlaces.size()>0) {
			int x = visitedPlaces.remove(0);
			availablePlaces.add(x);
		}
		board.moveGhostMark(1);
	}
	
	public void choosePlace(int x) {
		if (isAvailable(x)) {
			if (role == Consts.HUMAN) {
				for (int i=0;i<availablePlaces.size();i++) {
					if (x == availablePlaces.get(i)) {
						availablePlaces.remove(i);
						placeThisTurn = x;
						break;
					}
				}
			} else if (role == Consts.GHOST) {
				placeThisTurn = x;
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
	public int getPlaceThisTurn() {
		return placeThisTurn;
	}
	public void setPlaceThisTurn(int placeThisTurn) {
		this.placeThisTurn = placeThisTurn;
	}
}
