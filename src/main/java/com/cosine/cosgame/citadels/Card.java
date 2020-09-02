package com.cosine.cosgame.citadels;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Card {
	protected String name;
	protected int cost;
	protected String img;
	protected int color;
	protected int builtRound;
	protected Player player;
	protected Board board;
	protected int numSkills;
	protected List<String> skillNames;
	protected int expansion;
	
	public Card() {
		builtRound = -1;
		numSkills = 0;
		skillNames = new ArrayList<>();
		expansion = 0;
	}
	
	public Card(String name) {
		this();
		this.name = name;
	}
	
	public void alterPlayerAbility() {
		
	}
	
	public void endTurnEffect() {
		
	}
	
	public boolean destroyable() {
		return true;
	}
	
	public boolean omniColor() {
		return false;
	}
	
	public String canUseSkill() {
		return "n";
	}
	
	public String canUseSkillSameRound() {
		return "n";
	}
	
	public Ask chooseSkill(int index, int x) {
		Ask ask = new Ask();
		return ask;
	}
	
	public Ask useSkill(int index, int x) {
		Ask ask = new Ask();
		
		if (player != null) {
			player.useCardSkill(index);
		}
		
		return ask;
	}
	
	public int getColorForScore() {
		return color;
	}
	public int getScore() {
		return cost;
	}
	public int getExtraScore() {
		return 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getBuiltRound() {
		return builtRound;
	}
	public void setBuiltRound(int builtRound) {
		this.builtRound = builtRound;
	}
	public int getNumSkills() {
		return numSkills;
	}
	public void setNumSkills(int numSkills) {
		this.numSkills = numSkills;
	}
	public List<String> getSkillNames() {
		return skillNames;
	}
	public void setSkillNames(List<String> skillNames) {
		this.skillNames = skillNames;
	}
	public int getExpansion() {
		return expansion;
	}
	public void setExpansion(int expansion) {
		this.expansion = expansion;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("cost", cost);
		doc.append("color", color);
		doc.append("img", img);
		doc.append("builtRound", builtRound);
		return doc;
	}
}
