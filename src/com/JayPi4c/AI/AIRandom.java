package com.JayPi4c.AI;

import java.util.ArrayList;

import com.JayPi4c.game.Field;
import com.JayPi4c.game.Spot;

public class AIRandom implements AIPlayer {

	@Override
	public void doMove(Field field) {
		ArrayList<Spot> free = field.getFreeSpots();
		free.get((int) (Math.random() * free.size())).setState(Spot.STATE.O);
	}

}
