package model;

public class Question {
	private String content;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String trueAnswer;

	public Question(String content, String answerA, String answerB, String answerC, String answerD, String trueAnswer) {
		super();
		this.content = content;
		this.answerA = answerA;
		this.answerB = answerB;
		this.answerC = answerC;
		this.answerD = answerD;
		this.trueAnswer = trueAnswer;
	}

	public String getContent() {
		return content;
	}

	public String getAnswerA() {
		return answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public String getAnswerC() {
		return answerC;
	}

	public String getAnswerD() {
		return answerD;
	}

	public String getTrueAnswer() {
		return trueAnswer;
	}

	public boolean isTrueAnswer(String buttonID) {
		return buttonID.equals(getTrueAnswer());
	}

}
