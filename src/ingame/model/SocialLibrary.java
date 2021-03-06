package ingame.model;

import java.util.ArrayList;

public class SocialLibrary implements Library {
	public ArrayList<Question> getLibrary() {
		ArrayList<Question> questions = new ArrayList<Question>();
		Question question1 = new Question("Viet Nam co bao nhieu tinh thanh?", "62", "63", "64", "65", "b");
		Question question2 = new Question("Thanh pho cua tinh Phu Yen", "Nha Trang", "Quy Nhon", "Tuy Hoa",
				"Mu Cang Chai", "c");
		Question question3 = new Question("2018 la nam con gi?", "Cho", "Ga", "Bo", "Khi", "a");
		Question question4 = new Question("1+1=", "3", "4", "2", "Tat ca deu sai", "c");
		Question question5 = new Question("Ten game ban dang choi la gi?", "Find The Dog", "Find The Fish",
				"Find The Honey", "Honey The Find", "c");
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);
		questions.add(question5);
		return questions;
	}
}
