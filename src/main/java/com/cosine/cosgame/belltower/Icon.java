package com.cosine.cosgame.belltower;

import java.util.List;

import org.bson.Document;

import com.cosine.cosgame.belltower.entity.IconEntity;

public class Icon {
	int character;
	int frame;
	String characterImg;
	List<Integer> availableCharacters;
	List<Integer> availableFrames;
	
	public void assignCharacter(int character) {
		this.character = character;
		setCharacterImg(character);
	}
	
	public void assignIcon(List<Integer> iconArr) {
		if (iconArr.size() == 0) {
			
		} else {
			int c = iconArr.get(0);
			assignCharacter(c);
			if (iconArr.size() == 1) {
				frame = 0;
			} else {
				frame = iconArr.get(1);
			}
		}
	}
	
	public int getCharacter() {
		return character;
	}
	public void setCharacter(int character) {
		this.character = character;
	}
	public int getFrame() {
		return frame;
	}
	public void setFrame(int frame) {
		this.frame = frame;
	}
	public List<Integer> getAvailableCharacters() {
		return availableCharacters;
	}
	public void setAvailableCharacters(List<Integer> availableCharacters) {
		this.availableCharacters = availableCharacters;
	}
	public List<Integer> getAvailableFrames() {
		return availableFrames;
	}
	public void setAvailableFrames(List<Integer> availableFrames) {
		this.availableFrames = availableFrames;
	}
	public String getCharacterImg() {
		return characterImg;
	}
	public void setCharacterImg(String characterImg) {
		this.characterImg = characterImg;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("character", character);
		doc.append("frame", frame);
		return doc;
	}
	public void setFromDoc(Document doc) {
		character = doc.getInteger("character", 0);
		frame = doc.getInteger("frame", 0);
		setCharacterImg(character);
	}
	public IconEntity toIconEntity() {
		IconEntity entity = new IconEntity();
		entity.setCharacter(character);
		entity.setCharacterImg(characterImg);
		entity.setFrame(frame);
		return entity;
	}
	public void setCharacterImg(int character) {
		if (character == -1) {
			
		}
		
		else {
			characterImg = Integer.toString(character);
		}
	}
}
