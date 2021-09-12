package com.cosine.cosgame.onenight;

import org.bson.Document;

public class Question {
	protected String questionText;
	protected String answerText;
	protected Player player;
	protected Board board;
	protected Player targetPlayer;
	
	protected int id;
	protected int index;
	
	public Question() {
		questionText = "";
		answerText = "";
	}
	
	public void genAnswer() {
		
	}
	
	public boolean shouldContain() {
		return true;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
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

	public Player getTargetPlayer() {
		return targetPlayer;
	}

	public void setTargetPlayer(Player targetPlayer) {
		this.targetPlayer = targetPlayer;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Document toDocument() {
		Document doc = new Document();
		doc.append("id", id);
		doc.append("questionText", questionText);
		doc.append("answerText", answerText);
		return doc;
	}
}
