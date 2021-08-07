package com.cosine.cosgame.marshbros;

public class Card {
	protected String name;
	protected String nickname;
	protected String img;
	protected int atk;
	protected int hp;
	protected int type;
	protected int subType;
	
	protected Board board;
	protected Player player;
	protected Role role;
	
	protected boolean taunt;
	protected boolean canAttack;
	protected boolean attackTwice;
	protected boolean hasAction;
	
	protected int actionType;
	protected String actionText;
	protected String actionName;
	
	public Card() {
		taunt = false;
		canAttack = true;
		attackTwice = false;
		hasAction = false;
		type = Consts.ROLE;
	}
	
	public void activate() {
		
	}
	
	public void recruitment() {
		
	}
	
	public void lastWish() {
		
	}
	
	public void resolveAction(int target) {
		
	}

	public boolean isBeast() {
		if (subType == Consts.BEAST) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public boolean isTaunt() {
		return taunt;
	}
	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}
	public boolean isCanAttack() {
		return canAttack;
	}
	public void setCanAttack(boolean canAttack) {
		this.canAttack = canAttack;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public boolean isAttackTwice() {
		return attackTwice;
	}
	public void setAttackTwice(boolean attackTwice) {
		this.attackTwice = attackTwice;
	}
	public boolean isHasAction() {
		return hasAction;
	}
	public void setHasAction(boolean hasAction) {
		this.hasAction = hasAction;
	}
	public String getActionText() {
		return actionText;
	}
	public void setActionText(String actionText) {
		this.actionText = actionText;
	}
	public int getActionType() {
		return actionType;
	}
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	
	
}
