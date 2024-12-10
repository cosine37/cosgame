package com.cosine.cosgame.oink.grove;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.oink.account.Account;
import com.cosine.cosgame.oink.grove.entity.GrovePlayerEntity;
import com.cosine.cosgame.oink.grove.entity.RoleEntity;

public class GrovePlayer {
	String name;
	int phase;
	int accused;
	int index;
	List<Integer> liars;
	Role leftOutsider;
	Role rightOutsider;
	
	List<Integer> viewed;
	
	Grove grove;
	
	public Document toDocument(){
		Document doc = new Document();
		doc.append("name",name);
		doc.append("phase",phase);
		doc.append("accused",accused);
		doc.append("index",index);
		doc.append("liars",liars);
		doc.append("leftOutsider",leftOutsider.toDocument());
		doc.append("rightOutsider",rightOutsider.toDocument());
		doc.append("viewed", viewed);
		return doc;
	}
	public void setFromDoc(Document doc){
		name = doc.getString("name");
		phase = doc.getInteger("phase",0);
		accused = doc.getInteger("accused",0);
		index = doc.getInteger("index",0);
		liars = (List<Integer>)doc.get("liars");
		leftOutsider = new Role();
		rightOutsider = new Role();
		leftOutsider.setFromDoc((Document) doc.get("leftOutsider"));
		rightOutsider.setFromDoc((Document) doc.get("rightOutsider"));
		viewed = (List<Integer>) doc.get("viewed");
	}
	
	public GrovePlayerEntity toGrovePlayerEntity() {
		GrovePlayerEntity entity = new GrovePlayerEntity();
		entity.setIndex(index);
		entity.setName(name);
		entity.setLiars(liars);
		
		Account account = new Account();
		account.getFromDB(name);
		entity.setAccount(account.toAccountEntity());
		return entity;
	}
	
	public GrovePlayer() {
		liars = new ArrayList<>();
		viewed = new ArrayList<>();
		
		leftOutsider = new Role();
		rightOutsider = new Role();
		
	}
	
	public GrovePlayer(String name) {
		this();
		this.name = name;
	}
	
	public void newGame() {
		
	}
	
	public void newRound() {
		accused = -1;
		leftOutsider = null;
		rightOutsider = null;
		viewed = new ArrayList<>();
		for (int i=0;i<3;i++) {
			viewed.add(0);
		}
	}
	
	public void addLiar(int x) {
		liars.add(x);
	}
	
	public int numLiars() {
		return liars.size();
	}
	
	public int numMyLiars() {
		int ans = 0;
		for (int i=0;i<liars.size();i++) {
			if (liars.get(i) == index) {
				ans++;
			}
		}
		return ans;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhase() {
		return phase;
	}
	public void setPhase(int phase) {
		this.phase = phase;
	}
	public int getAccused() {
		return accused;
	}
	public void setAccused(int accused) {
		this.accused = accused;
	}
	public List<Integer> getViewed() {
		return viewed;
	}
	public void setViewed(List<Integer> viewed) {
		this.viewed = viewed;
	}
	public Role getLeftOutsider() {
		return leftOutsider;
	}
	public void setLeftOutsider(Role leftOutsider) {
		this.leftOutsider = leftOutsider;
	}
	public Role getRightOutsider() {
		return rightOutsider;
	}
	public void setRightOutsider(Role rightOutsider) {
		this.rightOutsider = rightOutsider;
	}
	public Grove getGrove() {
		return grove;
	}
	public void setGrove(Grove grove) {
		this.grove = grove;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Integer> getLiars() {
		return liars;
	}
	public void setLiars(List<Integer> liars) {
		this.liars = liars;
	}
	
	
}
