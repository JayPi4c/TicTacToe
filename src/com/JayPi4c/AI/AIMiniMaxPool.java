package com.JayPi4c.AI;

import java.util.ArrayList;

import com.JayPi4c.game.Field;
import com.JayPi4c.game.Spot;

public class AIMiniMaxPool extends AIMiniMax {
	@Override
	public void doMove(Field field) {
		double bestScore = -9999;
		// HashMap<Number, Spot> ratings = new HashMap<>();
		ArrayList<Spot> targetSpots = new ArrayList<Spot>();

		for (Spot s : field.getFreeSpots()) {
			Field f = field.copy();
			f.setSpot(s.getI(), s.getJ(), Spot.STATE.O);
			double score = minimax(f, 10, false);

			if (score >= bestScore) {
				if (score == bestScore)
					targetSpots.add(s);
				else {
					bestScore = score;
					targetSpots = new ArrayList<Spot>();
					targetSpots.add(s);
				}
			}
		}
		targetSpots.get((int) (targetSpots.size() * Math.random())).setState(Spot.STATE.O);
	}

}
