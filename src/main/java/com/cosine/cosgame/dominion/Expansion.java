package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.base.Copper;
import com.cosine.cosgame.dominion.base.Curse;
import com.cosine.cosgame.dominion.base.Duchy;
import com.cosine.cosgame.dominion.base.Estate;
import com.cosine.cosgame.dominion.base.Gold;
import com.cosine.cosgame.dominion.base.Province;
import com.cosine.cosgame.dominion.base.Silver;

public class Expansion {
	protected List<Pile> piles;
	
	public Expansion() {
		piles = new ArrayList<Pile>();
		
	}

	public List<Pile> getPiles() {
		return piles;
	}
	
	public void sort(int x) {
		
	}
	
	public void addExpansion(Expansion e) {
		List<Pile> p = e.getPiles();
		for (int i=0;i<p.size();i++) {
			piles.add(p.get(i));
		}
	}
	
	public List<Pile> genKindomPile(){
		List<Pile> kindom = new ArrayList<Pile>();
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
