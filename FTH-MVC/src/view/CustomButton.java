package view;

import javax.swing.JButton;

public class CustomButton extends JButton {
	private String btnID;

	public CustomButton(String id, String label) {
		this.btnID = id;
		setText(label);
		setFocusable(false);
	}

	public String getBtnID() {
		return btnID;
	}
}
