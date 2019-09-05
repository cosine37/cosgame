package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

public class Expansion {
	protected String name;
	protected List<Pile> piles;
	protected List<Pile> kindom;
	protected String expCardImage;

	public Expansion() {
		piles = new ArrayList<Pile>();
		
	}

	public List<Pile> getPiles() {
		return piles;
	}
	
	boolean shouldSwap(List<Pile> piles, int i, int j) {
		if (piles.get(i).getTop().getPrice() > piles.get(j).getTop().getPrice()) {
			return true;
		} else if (piles.get(i).getTop().getPrice() == piles.get(j).getTop().getPrice()) {
			String si = piles.get(i).getTop().getName().toUpperCase();
			String sj = piles.get(j).getTop().getName().toUpperCase();
			int x = 0;
			int ai, aj;
			while (x<si.length()) {
				if (x>=sj.length()) {
					return false;
				}
				ai = si.charAt(x);
				aj = sj.charAt(x);
				if (ai>aj) return true;
				if (ai<aj) return false;
				x = x+1;
			}
			return true;
		}
		return false;
	}
	
	public List<Pile> getPilesSorted(){
		List<Pile> sortedPiles = new ArrayList<Pile>();
		Pile p;
		int i,j;
		for (i=0;i<piles.size();i++) {
			sortedPiles.add(piles.get(i));
		}
		for (i=0;i<sortedPiles.size();i++) {
			for (j=i+1;j<sortedPiles.size();j++) {
				if (shouldSwap(sortedPiles, i,j)) {
					p = sortedPiles.get(i);
					sortedPiles.set(i, sortedPiles.get(j));
					sortedPiles.set(j, p);
				}
			}
		}
		return sortedPiles;
	}
	
	public void sortPiles() {
		piles = getPilesSorted();
	}
	
	public void resetPiles() {
		piles = new ArrayList<Pile>();
	}
	
	public void resetKindom() {
		kindom = new ArrayList<Pile>();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getExpCardImage() {
		return expCardImage;
	}
	
	public void addKindom(Pile p) {
		boolean flag = true;
		for (int i=0;i<kindom.size();i++) {
			if (kindom.get(i).getName().equals(p.getName())) {
				flag = false;
			}
		}
		if (flag) {
			kindom.add(p);
		}
	}
	
	public void addPile(Pile p) {
		boolean flag = true;
		for (int i=0;i<piles.size();i++) {
			if (piles.get(i).getName().equals(p.getName())) {
				flag = false;
			}
		}
		if (flag) {
			piles.add(p);
		}
	}
	
	public void addExpansion(Expansion e) {
		List<Pile> p = e.getPiles();
		for (int i=0;i<p.size();i++) {
			piles.add(p.get(i));
		}
	}
	
	public List<Pile> genKindomPile(){
		int numKindom = 10;
		// TODO: add potential extra pile afterwards
		int i,j,x;
		boolean flag;
		Pile p = new Pile();
		for (i=0;i<numKindom;i++) {
			flag = true;
			while (flag) {
				flag = false;
				x = (int)(Math.random() * piles.size());
				p = piles.get(x);
				for (j=0;j<i;j++) {
					if (kindom.get(j).getName().equals(p.getName())) {
						flag = true;
						break;
					}
				}
			}
			kindom.add(p);
		}
		
		for (i=0;i<numKindom;i++) {
			for (j=i+1;j<numKindom;j++) {
				if (kindom.get(i).getTop().getPrice() > kindom.get(j).getTop().getPrice()) {
					p = kindom.get(i);
					kindom.set(i, kindom.get(j));
					kindom.set(j, p);
				}
			}
		}
		
		if (numKindom == 10) {
			for (i=0;i<5;i++) {
				p = kindom.get(i);
				kindom.set(i, kindom.get(i+5));
				kindom.set(i+5, p);
			}
		}
		
		return kindom;
	}

}
