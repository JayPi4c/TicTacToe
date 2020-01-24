package com.JayPi4c.game;

public class Spot {

	public static enum STATE {
		X, O, FREE, TIE
	};

	private int i, j;

	private STATE state;

	public Spot(int i, int j) {
		this.state = STATE.FREE;
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public STATE getState() {
		return this.state;
	}

}
