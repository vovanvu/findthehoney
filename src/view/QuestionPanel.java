package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.QuestionLibrary;

public class QuestionPanel extends JPanel {
	private JTextArea taQuestion;
	private CustomButton btA, btB, btC, btD;
	private QuestionLibrary library;

	private JPanel questionPanel, answerPanel;

	public QuestionPanel() {
		setPreferredSize(new Dimension(360, 200));
		setBorder(BorderFactory.createTitledBorder("Question"));
		// init components
		taQuestion = new JTextArea(10, 30);
		taQuestion.setMargin(new Insets(10, 10, 10, 10));
		taQuestion.setLineWrap(true);
		taQuestion.setWrapStyleWord(true);
		taQuestion.setEditable(false);
		taQuestion.setText("Question Placeholder");
		btA = new CustomButton("a", "Answer Placeholder");
		btB = new CustomButton("b", "Answer Placeholder");
		btC = new CustomButton("c", "Answer Placeholder");
		btD = new CustomButton("d", "Answer Placeholder");
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		questionPanel = new JPanel();
		add(questionPanel);
		questionPanel.add(taQuestion);
		answerPanel = new JPanel();
		add(answerPanel);
		GridLayout layout = new GridLayout(0, 2);
		layout.setHgap(5);
		layout.setVgap(5);
		answerPanel.setLayout(layout);
		answerPanel.add(btA);
		answerPanel.add(btB);
		answerPanel.add(btC);
		answerPanel.add(btD);
	}

	public void updateQuestion(int currentQuestionIndex) {
		String content = library.getQuestions().get(currentQuestionIndex).getContent();
		String a = library.getQuestions().get(currentQuestionIndex).getAnswerA();
		String b = library.getQuestions().get(currentQuestionIndex).getAnswerB();
		String c = library.getQuestions().get(currentQuestionIndex).getAnswerC();
		String d = library.getQuestions().get(currentQuestionIndex).getAnswerD();
		taQuestion.setText(content);
		btA.setText(a);
		btB.setText(b);
		btC.setText(c);
		btD.setText(d);
	}

	public CustomButton getBtA() {
		return btA;
	}

	public CustomButton getBtB() {
		return btB;
	}

	public CustomButton getBtC() {
		return btC;
	}

	public CustomButton getBtD() {
		return btD;
	}

	public void setLibrary(QuestionLibrary library) {
		this.library = library;
	}
}
