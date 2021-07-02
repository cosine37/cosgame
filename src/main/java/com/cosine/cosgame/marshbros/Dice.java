package com.cosine.cosgame.marshbros;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bson.Document;

public class Dice {
	int resultId;
	List<Integer> results;
	
	public Dice() {
		results = new ArrayList<>();
	}
	
	public List<Integer> roll(int n){
		results = new ArrayList<>();
		int i;
		for (i=0;i<n;i++) {
			Random rand = new Random();
			int x = rand.nextInt(60000);
			x = x % 6+1;
			results.add(x);
		}
		resultId = resultId+1;
		return results;
	}
	
	public int calcResult(int target) {
		int ans = 0;
		for (int i=0;i<results.size();i++) {
			if (results.get(i)<=target) {
				ans++;
			}
		}
		return ans;
	}
	
	public int calcResult(List<Integer> results, int target) {
		int ans = 0;
		for (int i=0;i<results.size();i++) {
			if (results.get(i)<=target) {
				ans++;
			}
		}
		return ans;
	}

	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public List<Integer> getResults() {
		return results;
	}
	public void setResults(List<Integer> results) {
		this.results = results;
	}
	
	public Document toDocument() {
		Document doc = new Document();
		doc.append("resultId", resultId);
		doc.append("results", results);
		return doc;
	}
	
	public void setFromDoc(Document doc) {
		resultId = doc.getInteger("resultId", -1);
		results = (List<Integer>) doc.get("results");
	}
	
}
