package model;

import java.awt.*;

import javax.swing.*;

public class GameMap {
	private String[] MAP = { "rrrrrrrr", "rggqggqr", "rqrgrrgr", "rgrgrggr", "rgqgrqrr", "rgrrrggr", "rggqgqhr",
			"rrrrrrrr" };

	public String[] updateMap(int x, int y, String updateComponent) {
		String arrTemp = MAP[y];
		String stringTemp = "";
		stringTemp += arrTemp.substring(0, x) + updateComponent + arrTemp.substring(x + 1);
		MAP[y] = stringTemp;
		return MAP;
	}

	public String getMap(int x, int y) {
		String index = MAP[y].substring(x, x + 1);
		return index;
	}

	public void resetMap() {
		MAP[0] = "rrrrrrrr";
		MAP[1] = "rggqggqr";
		MAP[2] = "rqrgrrgr";
		MAP[3] = "rgrgrggr";
		MAP[4] = "rgqgrqrr";
		MAP[5] = "rgrrrggr";
		MAP[6] = "rggqgqhr";
		MAP[7] = "rrrrrrrr";
	}
}
