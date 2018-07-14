package model;

import java.util.ArrayList;

public class QuestionLibrary {
	private int questionIndex;
	private ArrayList<Question> questions;
	private QuestionList questionList;

	public QuestionLibrary() {
		questionList = new SocialQuestionList();
		initListQuestion();
	}

	private void initListQuestion() {
		questionIndex = 0;
		questions = questionList.createQuestionList();
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public int getQuestionIndex() {
		return questionIndex;
	}

	public void updateQuestionIndex() {
		questionIndex = (questionIndex + 1) % questions.size();
	}

}
