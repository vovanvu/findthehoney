package model;

import java.util.ArrayList;

public class QuestionLibrary {
	private int questionIndex;
	private ArrayList<Question> questions;

	public QuestionLibrary() {
		initListQuestion();
	}

	private void initListQuestion() {
		questionIndex = 0;
		questions = new ArrayList<Question>();
		Question question1 = new Question("Viet Nam co bao nhieu tinh thanh?", "62", "63", "64", "65", "b");
		Question question2 = new Question("Thanh pho cua tinh Phu Yen", "Nha Trang", "Quy Nhon", "Tuy Hoa",
				"Mu Cang Chai", "c");
		Question question3 = new Question("JAV la viet tat cua tu gi?", "Japanese Adult Video", "Japan and Vietnam",
				"Japan Anti Virus", "Tat ca deu sai", "d");
		Question question4 = new Question("Chi pheo sinh ra o dau?", "Lo gach cu", "Lo gach moi", "Lo ton",
				"Lo banh mi", "a");
		Question question5 = new Question("Bien phap tranh thai nao an toan, it gay hai cho con nguoi?",
				"Thuoc tranh thai khan cap", "That ong dan tinh", "Cat ong dan trung", "Bao cao su", "d");
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);
		questions.add(question5);
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);
		questions.add(question5);
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);
		questions.add(question5);
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);
		questions.add(question5);

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
