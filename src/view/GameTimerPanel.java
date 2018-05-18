package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameTimerPanel extends JPanel {
	private int hour, minute, second;
	private JLabel lHour, lMinute, lSecond;
	private Timer timer;

	public GameTimerPanel() {
		setBorder(BorderFactory.createTitledBorder("Time"));
		setPreferredSize(new Dimension(360, 50));
		JLabel lSpace1 = new JLabel(":");
		JLabel lSpace2 = new JLabel(":");
		lHour = new JLabel("00");
		lMinute = new JLabel("00");
		lSecond = new JLabel("00");
		add(lHour);
		add(lSpace1);
		add(lMinute);
		add(lSpace2);
		add(lSecond);
	}

	public void runClock() {
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				second++;
				if (second > 59) {
					second = 0;
					minute++;
				}
				if (minute > 59) {
					minute = 0;
					second = 0;
					hour++;
				}
				lSecond.setText("" + second);
				lMinute.setText("" + minute);
				lHour.setText("" + hour);

			}

		});
		timer.start();
	}

	public void stopClock() {
		timer.stop();
	}

	public void resumeClock() {
		timer.start();
	}

	public void resetClock() {
		if (timer.isRunning()) {
			timer.stop();
			hour = 0;
			minute = 0;
			second = 0;
			lSecond.setText("" + second);
			lMinute.setText("" + minute);
			lHour.setText("" + hour);
			timer.start();
		} else if (!timer.isRunning()) {
			hour = 0;
			minute = 0;
			second = 0;
			lSecond.setText("" + second);
			lMinute.setText("" + minute);
			lHour.setText("" + hour);
			timer.start();
		}
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

}
