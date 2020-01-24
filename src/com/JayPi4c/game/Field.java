package com.JayPi4c.game;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class Field {

	Spot[][] field;

	public Field() {
		field = new Spot[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				field[i][j] = new Spot(i, j);
	}

	public Field copy() {
		Field f = new Field();
		for (int i = 0; i < field.length; i++)
			for (int j = 0; j < field[i].length; j++)
				f.setSpot(i, j, field[i][j].getState());
		return f;
	}

	public Spot getSpot(int i, int j) {
		return field[i][j];
	}

	public boolean freeSpot() {
		for (Spot spots[] : field)
			for (Spot spot : spots)
				if (spot.getState() == Spot.STATE.FREE)
					return true;
		return false;
	}

	public ArrayList<Spot> getFreeSpots() {
		ArrayList<Spot> output = new ArrayList<Spot>();
		for (Spot[] spots : field)
			for (Spot spot : spots)
				if (spot.getState() == Spot.STATE.FREE)
					output.add(spot);
		return output;
	}

	public boolean setSpot(int i, int j, Spot.STATE state) {
		Spot s = field[i][j];
		if (s.getState() == Spot.STATE.FREE) {
			s.setState(state);
			return true;
		} else
			return false;
	}

	void show(Graphics2D g) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g.drawRect(i * 100, j * 100, 99, 99);
				if (field[i][j].getState() == Spot.STATE.X) {
					g.drawLine(i * 100, j * 100, i * 100 + 100, j * 100 + 100);
					g.drawLine(i * 100, j * 100 + 100, i * 100 + 100, j * 100);
				} else if (field[i][j].getState() == Spot.STATE.O) {
					g.drawOval(i * 100 + 1, j * 100 + 1, 98, 98);
				}
			}
		}
	}

	/**
	 * 
	 * @return gibt entweder den State des Gewinners, FREE (wenn das spiel noch
	 *         nicht zuenede ist) oder null (bei Unentschieden) zurück
	 */
	public Spot.STATE getWinner() {

		// checke die Felder senkrecht
		for (int i = 0; i < 3; i++) {
			int XSum = 0, OSum = 0;
			for (int j = 0; j < 3; j++) {
				Spot.STATE s = field[i][j].getState();
				if (s == Spot.STATE.X)
					XSum++;
				else if (s == Spot.STATE.O)
					OSum++;
			}
			if (XSum == 3)
				return Spot.STATE.X;
			if (OSum == 3)
				return Spot.STATE.O;
		}

		// checke die Felder waagerecht
		for (int j = 0; j < 3; j++) {
			int XSum = 0, OSum = 0;
			for (int i = 0; i < 3; i++) {
				Spot.STATE s = field[i][j].getState();
				if (s == Spot.STATE.X)
					XSum++;
				else if (s == Spot.STATE.O)
					OSum++;
			}
			if (XSum == 3)
				return Spot.STATE.X;
			if (OSum == 3)
				return Spot.STATE.O;
		}

		// checke die Felder diagonal
		int sumDownX = 0, sumDownO = 0;
		int sumUpX = 0, sumUpO = 0;
		for (int i = 0; i < 3; i++) {
			if (field[i][i].getState() == Spot.STATE.X)
				sumDownX++;
			else if (field[i][i].getState() == Spot.STATE.O)
				sumDownO++;
			if (field[i][2 - i].getState() == Spot.STATE.X)
				sumUpX++;
			else if (field[i][2 - i].getState() == Spot.STATE.O)
				sumUpO++;
		}
		if (sumDownX == 3)
			return Spot.STATE.X;
		if (sumDownO == 3)
			return Spot.STATE.O;
		if (sumUpX == 3)
			return Spot.STATE.X;
		if (sumUpO == 3)
			return Spot.STATE.O;

		// checke, ob noch Felder frei sind
		for (Spot[] spots : field)
			for (Spot spot : spots)
				if (spot.getState() == Spot.STATE.FREE)
					return Spot.STATE.FREE;

		return Spot.STATE.TIE;
	}

}
