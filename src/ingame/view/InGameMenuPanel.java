package ingame.view;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class InGameMenuPanel extends JPanel {
	private JButton btnReset, btnPause, btnMute, btnMainMenu;
	boolean b = true;

	public InGameMenuPanel() {
		setBorder(BorderFactory.createTitledBorder("Menu"));
		setPreferredSize(new Dimension(360, 70));
		btnReset = new JButton("Choi lai");
		add(btnReset);
		btnPause = new JButton("Tam dung");
		add(btnPause);
		btnMute = new JButton("Mute");
		add(btnMute);
		btnMainMenu = new JButton("MainMenu");
		add(btnMainMenu);
		setFalseFocusable();
	}

	private void setFalseFocusable() {
		btnReset.setFocusable(false);
		btnPause.setFocusable(false);
		btnMainMenu.setFocusable(false);
		btnMute.setFocusable(false);

	}

	public JButton getBtnReset() {
		return btnReset;
	}

	public JButton getBtnPause() {
		return btnPause;
	}

	public JButton getBtnMute() {
		return btnMute;
	}

	public JButton getBtnMainMenu() {
		return btnMainMenu;
	}

}
