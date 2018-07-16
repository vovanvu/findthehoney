package model;

import java.util.ArrayList;

public class QuestionLibrary {
	private int questionIndex;
	private ArrayList<Question> questions;

	public QuestionLibrary() {
		questionIndex = 0;
		createLibrary();
	}

	private void createLibrary() {
		Library library = LibraryFactory.createLibrary();
		questions = library.getLibrary();
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
