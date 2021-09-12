package com.cosine.cosgame.onenight;

import org.bson.Document;

import com.cosine.cosgame.onenight.questions.*;

public class QuestionFactory {
	public static Question createQuestion(int id) {
		Question question = new Question();
		if (id == Consts.ANOTHERGYPSY) {
			question = new AnotherGypsy();
		} else if (id == Consts.HASSADAKO) {
			question = new HasSadako();
		} else if (id == Consts.HASPOPE) {
			question = new HasPope();
		} else if (id == Consts.HASWEREMELEON) {
			question = new HasWeremeleon();
		} else if (id == Consts.HASWOLFCENTER) {
			question = new HasWolfCenter();
		} else if (id == Consts.LEFTPLAERROLE) {
			question = new LeftPlayerRole();
		} else if (id == Consts.RIGHTPLAYERROLE) {
			question = new RightPlayerRole();
		}
		return question;
	}
	
	public static Question createQuestion(int id, String questionText, String answerText) {
		Question question = createQuestion(id);
		question.setQuestionText(questionText);
		question.setAnswerText(answerText);
		return question;
	}
	
	public static Question createQuestion(Document doc) {
		int id = doc.getInteger("id");
		String questionText = doc.getString("questionText");
		String answerText = doc.getString("answerText");
		return createQuestion(id, questionText, answerText);
	}
}
