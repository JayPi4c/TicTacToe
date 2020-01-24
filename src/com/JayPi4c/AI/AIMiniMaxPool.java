package com.JayPi4c.AI;

import java.util.ArrayList;

import com.JayPi4c.game.Field;
import com.JayPi4c.game.Spot;

public class AIMiniMaxPool implements AIPlayer {
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
