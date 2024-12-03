package com.cosine.cosgame.oink.grove;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Role {
	int num;
	int height;
	int age;
	String name;
	String description;
	String gender;
	String avatar;
	
	List<Integer> predicted;
	
	public Document toDocument(){
		Document doc = new Document();
		doc.append("num",num);
		doc.append("height",height);
		doc.append("age",age);
		doc.append("name",name);
		doc.append("description",description);
		doc.append("gender",gender);
		doc.append("avatar",avatar);
		doc.append("predicted",predicted);
		return doc;
	}
	
	public void setFromDoc(Document doc){
		num = doc.getInteger("num",0);
		height = doc.getInteger("height",0);
		age = doc.getInteger("age",0);
		name = doc.getString("name");
		description = doc.getString("description");
		gender = doc.getString("gender");
		avatar = doc.getString("avatar");
		predicted = (List<Integer>)doc.get("predicted");
	}
	
	public Role() {
		predicted = new ArrayList<>();
	}
	
	public Role(int num, int height, int age, String name, String description, String gender, String avatar) {
		this();
		this.num = num;
		this.height = height;
		this.age = age;
		this.name = name;
		this.description = description;
		this.gender = gender;
		this.avatar = avatar;
	}
	
	public int lastPredicted() {
		if (predicted.size() == 0) return -1;
		return predicted.get(predicted.size()-1);
	}
	public void addPredicted(int x) {
		predicted.add(x);
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public List<Integer> getPredicted() {
		return predicted;
	}
	public void setPredicted(List<Integer> predicted) {
		this.predicted = predicted;
	}
}
