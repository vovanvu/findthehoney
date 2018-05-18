package view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GameImage {
	private Image grass, rock, question, honey, bear;

	public GameImage() {
		ImageIcon img = new ImageIcon("resources/Grass.png");
		grass = img.getImage();
		img = new ImageIcon("resources/Bear.png");
		bear = img.getImage();
		img = new ImageIcon("resources/Question.png");
		question = img.getImage();
		img = new ImageIcon("resources/Rock.png");
		rock = img.getImage();
		img = new ImageIcon("resources/Honey.png");
		honey = img.getImage();
	}

	public Image getGrass() {
		return grass;
	}

	public Image getRock() {
		return rock;
	}

	public Image getQuestion() {
		return question;
	}

	public Image getHoney() {
		return honey;
	}

	public Image getBear() {
		return bear;
	}
	
}
