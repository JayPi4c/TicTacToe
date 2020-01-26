package com.JayPi4c;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.WindowConstants;

import com.JayPi4c.AI.AIMiniMax;
import com.JayPi4c.AI.AIMiniMaxPool;
import com.JayPi4c.AI.AIMiniMaxWithError;
import com.JayPi4c.AI.AIRandom;
import com.JayPi4c.game.Game;

public class Main {
	public static final int WINDOW_WIDTH = 300;
	public static final int WINDOW_HEIGHT = 300;

	public static void main(String args[]) {
		JFrame frame = new JFrame("Tic Tac Toe");
		// frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT + 30);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Game g = new Game();
		frame.add(g);

		JMenuBar menuBar = new JMenuBar();
		JMenu ai = new JMenu("AI");

		ButtonGroup AIBtnGroup = new ButtonGroup();
		JRadioButtonMenuItem human = new JRadioButtonMenuItem("Human");
		human.addActionListener(event -> {
			g.setSinglePlayer(false);
		});
		JRadioButtonMenuItem randomAI = new JRadioButtonMenuItem("random");
		randomAI.addActionListener(event -> {
			g.setNewAI(new AIRandom());
		});
		JRadioButtonMenuItem miniMaxAI = new JRadioButtonMenuItem("MiniMax");
		miniMaxAI.addActionListener(event -> {
			g.setNewAI(new AIMiniMax());
		});

		JRadioButtonMenuItem miniMaxPoolAI = new JRadioButtonMenuItem("MiniMax Pool");
		miniMaxPoolAI.setSelected(true);
		miniMaxPoolAI.addActionListener(event -> {
			g.setNewAI(new AIMiniMaxPool());
		});

		JRadioButtonMenuItem miniMaxPoolWithErrorAI = new JRadioButtonMenuItem("MiniMax with error");
		miniMaxPoolWithErrorAI.addActionListener(event -> {
			g.setNewAI(new AIMiniMaxWithError());
		});

		AIBtnGroup.add(human);
		AIBtnGroup.add(randomAI);
		AIBtnGroup.add(miniMaxAI);
		AIBtnGroup.add(miniMaxPoolAI);
		AIBtnGroup.add(miniMaxPoolWithErrorAI);

		ai.add(human);
		ai.add(randomAI);
		ai.add(miniMaxAI);
		ai.add(miniMaxPoolAI);
		ai.add(miniMaxPoolWithErrorAI);

		menuBar.add(ai);

		frame.setJMenuBar(menuBar);

		frame.pack();

		frame.setVisible(true);
	}
}
