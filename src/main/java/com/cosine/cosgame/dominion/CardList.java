package com.cosine.cosgame.dominion;

import java.util.ArrayList;
import java.util.List;

import com.cosine.cosgame.dominion.dominion.Dominion;
import com.cosine.cosgame.dominion.intrigue.Intrigue;
import com.cosine.cosgame.dominion.oriental.Oriental;
import com.cosine.cosgame.dominion.seaside.Seaside;

import org.bson.Document;

public class CardList {
	public static final int EXCLUDE = 1;
	public static final int FORRANDOMIZE = 0;
	public static final int INCLUDE = 2;
	
	int numKindom;
	
	List<Expansion> expansions = new ArrayList<>();
	
	List<List<Integer>> selected;
	
	public CardList() {
		numKindom = 10;
		
		Expansion dominion = new Dominion();
		Expansion intrigue = new Intrigue();
		Expansion seaside = new Seaside();
		Expansion oriental = new Oriental();
		dominion.sortPiles();
		intrigue.sortPiles();
		seaside.sortPiles();
		oriental.sortPiles();
		expansions.add(dominion);
		expansions.add(intrigue);
		expansions.add(seaside);
		expansions.add(oriental);
		
		selected = new ArrayList<>();
		List<Integer> selectedInExpansion;
		for (int i=0;i<expansions.size();i++) {
			selectedInExpansion = new ArrayList<>();
			for (int j=0;j<expansions.get(i).getPiles().size();j++) {
				selectedInExpansion.add(0);
			}
			selected.add(selectedInExpansion);
		}
		
	}
	
	public List<Expansion> getExpansions(){
		return expansions;
	}
	
	public List<List<Integer>> getSelected(){
		return selected;
	}
	
	public void setSelected(List<List<Integer>> selected) {
		this.selected = selected;
	}
	
	public void setSelectedFromDoc(Document selectedDoc) {
		selected = new ArrayList<>();
		List<Document> expNames = (List<Document>)selectedDoc.get("expansions");
		for (int i=0;i<expNames.size();i++) {
			String expName = expNames.get(i).getString("name");
			List<Document> selectionsInExp = (List<Document>)selectedDoc.get(expName);
			List<Integer> selectedInExpansion = new ArrayList<>();
			for (int j=0;j<selectionsInExp.size();j++) {
				int x = selectionsInExp.get(j).getInteger("s");
				selectedInExpansion.add(x);
			}
			selected.add(selectedInExpansion);
		}
	}
	
	public Document getSelectedDoc() {
		Document selectedDoc = new Document();
		List<Document> expNames = new ArrayList<>();
		for (int i=0;i<expansions.size();i++) {
			Document doc = new Document();
			doc.append("name", expansions.get(i).getName());
			expNames.add(doc);
		}
		selectedDoc.append("expansions", expNames);
		for (int i=0;i<selected.size();i++) {
			List<Document> selectionsInExp = new ArrayList<>();
			for (int j=0;j<selected.get(i).size();j++) {
				Document doc = new Document();
				doc.append("s", selected.get(i).get(j));
				selectionsInExp.add(doc);
			}
			selectedDoc.append(expansions.get(i).getName(), selectionsInExp);
		}
		return selectedDoc;
	}
	
	public void include(int x, int y) {
		int i,j;
		int numIncluded = 0;
		for (i=0;i<selected.size();i++) {
			for (j=0;j<selected.get(i).size();j++) {
				if (selected.get(i).get(j) == INCLUDE) {
					numIncluded = numIncluded + 1;
				}
			}
		}
		if (numIncluded < 10) {
			selected.get(x).set(y, INCLUDE);
		}
	}
	
	public List<Pile> getIncluded(){
		List<Pile> kindom = new ArrayList<>();
		int i,j;
		for (i=0;i<selected.size();i++) {
			for (j=0;j<selected.get(i).size();j++) {
				if (selected.get(i).get(j) == INCLUDE) {
					kindom.add(expansions.get(i).getPiles().get(j));
				}
			}
		}
		return kindom;
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
	
	public List<Pile> genKindomPiles() {
		int i,j;
		List<Pile> kindom = new ArrayList<>();
		List<Pile> buffer = new ArrayList<>();
		for (i=0;i<selected.size();i++) {
			for (j=0;j<selected.get(i).size();j++) {
				if (selected.get(i).get(j) == INCLUDE) {
					kindom.add(expansions.get(i).getPiles().get(j));
				} else if (selected.get(i).get(j) == FORRANDOMIZE) {
					buffer.add(expansions.get(i).getPiles().get(j));
				}
			}
		}
		boolean flag;
		int x;
		Pile p;
		while (kindom.size()<numKindom) {
			x = (int)(Math.random() * buffer.size());
			p = buffer.remove(x);
			kindom.add(p);
		}
		
		
		for (i=0;i<numKindom;i++) {
			for (j=i+1;j<numKindom;j++) {
				if (shouldSwap(kindom, i, j)) {
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
