package com.JayPi4c.AI;

import java.util.ArrayList;

import com.JayPi4c.game.Field;
import com.JayPi4c.game.Spot;

public class AIMiniMaxWithError extends AIMiniMax {
	@Override
	public void doMove(Field field) {
		double bestScore = -9999;
		// HashMap<Number, Spot> ratings = new HashMap<>();
		ArrayList<Spot> errorSpots = new ArrayList<Spot>();
		ArrayList<Spot> goodSpots = new ArrayList<Spot>();

		for (Spot s : field.getFreeSpots()) {
			Field f = field.copy();
			f.setSpot(s.getI(), s.getJ(), Spot.STATE.O);
			double score = minimax(f, 10, false);

			if (score >= bestScore) {
				if (score == bestScore)
					goodSpots.add(s);
				else {
					bestScore = score;
					errorSpots.addAll(goodSpots);
					goodSpots = new ArrayList<Spot>();
					goodSpots.add(s);
				}
			} else {
				errorSpots.add(s);
			}
		}
		double rand = Math.random();
		if (rand < 0.25 && errorSpots.size() > 0)
			errorSpots.get((int) (errorSpots.size() * Math.random())).setState(Spot.STATE.O);
		else
			goodSpots.get((int) (goodSpots.size() * Math.random())).setState(Spot.STATE.O);
	}

}
