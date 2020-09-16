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
	protected int buildCount;
	protected boolean buildable;
	protected boolean trackCrown;
	protected boolean trackOtherAssassin;
	protected int beautifyLevel;
	protected int index;
	protected List<Card> cardsUnder;
	
	public Card() {
		builtRound = -1;
		numSkills = 0;
		skillNames = new ArrayList<>();
		expansion = 0;
		buildCount = 1;
		buildable = true;
		trackCrown = false;
		trackOtherAssassin = false;
		beautifyLevel = 0;
		cardsUnder = new ArrayList<>();
	}
	
	public Card(String name) {
		this();
		this.name = name;
	}
	
	public void alterPlayerAbility() {
		
	}
	
	public void endTurnEffect() {
		
	}
	
	public void onSelectChooseCard() {
		
	}
	
	public void afterTakeActionEffect() {
		
	}
	
	public void insuredEffect() {
		
	}
	
	public void onBuild() {
		
	}
	
	public void onAnotherBuild(Card c) {
		
	}
	
	public void crownMovement() {
		
	}
	
	public void onOtherAssassin() {
		
	}
	
	public int buildCount() {
		return 1;
	}
	
	public boolean canBuildWhen(int x) {
		return true;
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
	
	public void beautify() {
		beautifyLevel++;
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
		return cost + beautifyLevel;
	}
	public int getExtraScore() {
		return 0;
	}
	public int getSecretScore() {
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
	public int getBuildCount() {
		return buildCount;
	}
	public void setBuildCount(int buildCount) {
		this.buildCount = buildCount;
	}
	public boolean isBuildable() {
		return buildable;
	}
	public void setBuildable(boolean buildable) {
		this.buildable = buildable;
	}
	public boolean isTrackCrown() {
		return trackCrown;
	}
	public void setTrackCrown(boolean trackCrown) {
		this.trackCrown = trackCrown;
	}
	public int getBeautifyLevel() {
		return beautifyLevel;
	}
	public void setBeautifyLevel(int beautifyLevel) {
		this.beautifyLevel = beautifyLevel;
	}
	public boolean isTrackOtherAssassin() {
		return trackOtherAssassin;
	}
	public void setTrackOtherAssassin(boolean trackOtherAssassin) {
		this.trackOtherAssassin = trackOtherAssassin;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public List<Card> getCardsUnder() {
		return cardsUnder;
	}
	public void setCardsUnder(List<Card> cardsUnder) {
		this.cardsUnder = cardsUnder;
	}
	public Document toDocument() {
		Document doc = new Document();
		doc.append("name", name);
		doc.append("cost", cost);
		doc.append("color", color);
		doc.append("img", img);
		doc.append("builtRound", builtRound);
		doc.append("beautifyLevel", beautifyLevel);
		List<Document> cu = new ArrayList<>();
		for (int i=0;i<cardsUnder.size();i++) {
			Document d = cardsUnder.get(i).toDocument();
			cu.add(d);
		}
		doc.append("cardsUnder", cu);
		return doc;
	}
}
