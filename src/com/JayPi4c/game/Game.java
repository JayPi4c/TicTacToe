package com.JayPi4c.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.JayPi4c.Main;
import com.JayPi4c.AI.AIMiniMaxPool;
import com.JayPi4c.AI.AIPlayer;

public class Game extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	Field f;
	int moveCount;

	boolean singlePlayer = true;
	AIPlayer ai;
	boolean AIStart = true;

	public Game() {
		setSize(new Dimension(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT));
		f = new Field();
		moveCount = 0;
		this.addMouseListener(this);
		ai = new AIMiniMaxPool();
		if (AIStart)
			ai.doMove(f);
	}

	@Override
	public void paint(Graphics g) {
		BufferedImage bImage = new BufferedImage(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) bImage.getGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, bImage.getWidth(), bImage.getHeight());
		g2.setColor(Color.BLACK);
		f.show(g2);

		g.drawImage(bImage, 0, 0, null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();
		int i = p.x / 100;
		int j = p.y / 100;
		if (!singlePlayer) {

			if (f.setSpot(i, j, (moveCount % 2 == 0 ? Spot.STATE.X : Spot.STATE.O))) {
				moveCount++;
				repaint();
				Spot.STATE Winner = f.getWinner();
				String winMsg = "";
				if (Winner == Spot.STATE.FREE)
					return;
				else if (Winner == Spot.STATE.TIE)
					winMsg = "Es ist unentschieden!";
				else
					winMsg = Winner + " hat gewonnen!";
				int choice = JOptionPane.showConfirmDialog(null, winMsg + "\nNeues Spiel?", "Spielende",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.NO_OPTION)
					System.exit(0);
				f = new Field();
				moveCount = 0;
				repaint();
			}
		} else {
			if (f.setSpot(i, j, Spot.STATE.X)) {
				Spot.STATE Winner = f.getWinner();
				String winMsg = "";
				repaint();
				if (Winner == Spot.STATE.FREE)
					ai.doMove(f);
				Winner = f.getWinner();
				if (Winner == Spot.STATE.FREE)
					return;
				else if (Winner == Spot.STATE.TIE)
					winMsg = "Es ist unentschieden!";
				else
					winMsg = Winner + " hat gewonnen!";
				int choice = JOptionPane.showConfirmDialog(null, winMsg + "\nNeues Spiel?", "Spielende",
						JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.NO_OPTION)
					System.exit(0);
				f = new Field();
				if (AIStart)
					ai.doMove(f);
				repaint();

			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
