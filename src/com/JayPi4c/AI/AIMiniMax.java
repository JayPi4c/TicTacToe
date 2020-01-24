package com.JayPi4c.AI;

import com.JayPi4c.game.Field;
import com.JayPi4c.game.Spot;

public class AIMiniMax implements AIPlayer {

	@Override
	public void doMove(Field field) {
		double bestScore = -9999;
		Spot targetSpot = null;
		for (Spot s : field.getFreeSpots()) {
			Field f = field.copy();
			f.setSpot(s.getI(), s.getJ(), Spot.STATE.O);
			double score = minimax(f, 10, false);
			if (score > bestScore) {
				bestScore = score;
				targetSpot = s;
			}
		}
		targetSpot.setState(Spot.STATE.O);
	}

	public double minimax(Field f, int depth, boolean isMaximazing) {
		Spot.STATE winner = f.getWinner();

		if (winner == Spot.STATE.TIE)
			return 0;
		if (winner == Spot.STATE.O)
			return 10;
		if (winner == Spot.STATE.X)
			return -10;

		if (isMaximazing) {
			double bestScore = -9999;
			for (Spot s : f.getFreeSpots()) {
				Field f_ = f.copy();
				f_.setSpot(s.getI(), s.getJ(), Spot.STATE.O);
				double score = minimax(f_, depth - 1, false);
				bestScore = Math.max(score, bestScore);
			}
			return bestScore;
		} else {
			double bestScore = 9999;
			for (Spot s : f.getFreeSpots()) {
				Field f_ = f.copy();
				f_.setSpot(s.getI(), s.getJ(), Spot.STATE.X);
				double score = minimax(f_, depth - 1, true);
				bestScore = Math.min(score, bestScore);
			}
			return bestScore;
		}

	}

}
