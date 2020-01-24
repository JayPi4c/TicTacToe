package com.JayPi4c;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.JayPi4c.game.Game;

public class Main {
	public static final int WINDOW_WIDTH = 300;
	public static final int WINDOW_HEIGHT = 300;

	public static void main(String args[]) {
		JFrame frame = new JFrame("Tic Tac Toe");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 30);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(new Game());
		frame.setVisible(true);
	}
}
