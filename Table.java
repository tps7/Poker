import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.*;

public class Table implements ActionListener {
	private JFrame table = new JFrame();
	private JButton playGame = new JButton();
	private JButton calcOdds = new JButton();
	private JButton deal = new JButton();
	private static TexasHoldEm game = new TexasHoldEm();
	private JLabel numPlayers = new JLabel();
	private JLabel yourCards = new JLabel();
	public Table() {
		table.setSize(800, 500);
		table.setVisible(true);
		/*
		 * Have first screen have play game button, a dropdown menu with list of games, and number of players.
		 */
//		playGame.addActionListener(this);
//		playGame.setBounds(50, 60, 125, 75);
//		playGame.setText("Play Game");
//		table.add(playGame);
		
		/*
		 * Second screen will have a screen showing your cards, and a A calc odds button.
		 * There will also need to be a button that progresses the deal (goes from flop to other part)
		 * Also has to be a button that makes other players fold.
		 * Maybe add a button that shows rules for that game?
		 */
		calcOdds.addActionListener(this);
		calcOdds.setBounds(50, 60, 125, 75);
		calcOdds.setText("Calculate Odds");
		table.add(calcOdds);
		
		deal.addActionListener(this);
		deal.setBounds(450, 42, 75, 75);
		deal.setText("Deal");
		table.add(deal);
		
		numPlayers.setText(Integer.toString(game.numPlayers));
		numPlayers.setBounds(200, 200, 50, 50);
		table.add(numPlayers);
		
		yourCards.setText(game.players.get(0).getStringHand());
		yourCards.setBounds(400, 200, 150, 150);
		table.add(yourCards);
	}

	public static void main(String[] args) {
		Table t = new Table();
		game.players.get(0).printHand();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == calcOdds) {
			System.out.println(game.calcOdds(game.players.get(1)));
		}
		if (e.getSource() == deal) {
			game.play();
			yourCards.setText(game.players.get(0).getStringHand());
		}
		
	}

}
