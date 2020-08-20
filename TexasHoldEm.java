import java.util.*;
import java.lang.Math;
public class TexasHoldEm extends Game {
	/**
	 * @TODO:
	 * Odd calculator only works for one situation (player has 2 cards before flop). 
	 * Need to do Odds calcltor for after flop.
	 * Do somthing with community cards to make everything easier.
	 * 
	 */
	public TexasHoldEm() {
//		players.add(new Player("Bob"));
//		//System.out.println(Player);
//		players.add(new Player("Joe"));
//		players.add(new Player("Bob Joe"));
//		players.add(new Player("Joe Bob"));
		numPlayers = players.size();
		deck.shuffle();
	}
	
	public TexasHoldEm(ArrayList<Player> p) {
		players = p;
	}
	public void play() {
		//do blinds
		deck.shuffle();
		deck.shuffle();
		deck.shuffle();
		deal();
		//bet
		dealFlop();
		/*
		 * calculate odds set one players cards and play the game 1000ish times and see how many times that player wins.
		 * Do this every time you want to calculate odds. This means if someone folds you have to calculate odds again.
		 */
		
	}
	public void newGame() {
		deck.reset();
		for (Player k : players) {
			k.newHand();
		}
		deck.shuffle();
		deck.shuffle();
	}
	public void newGame(int index, ArrayList<Card> currHand) {
		deck.reset();
		for (Player k : players) {
			k.newHand();
		}
		deck.shuffle();
		deck.shuffle();
		//System.out.println(currHand.size());
		for (int j = 0; j < currHand.size(); j++) {
			//System.out.println("lkjdfa");
			deck.remove(currHand.get(j));
			players.get(index).addCard(currHand.get(j));
		}
	}
	
	public double calcOdds(Player p) {
		double odds = 0.0;
		if (p.getNumCards() == 0) {
			odds = 1.0 / numPlayers;
			return odds;
		}
		ArrayList<Card> currHand = (ArrayList<Card>) p.getHand().clone();
//		for (int k = 0; k < p.getNumCards(); k++) {
//			deck.remove(p.hand.get(k));
//		}
		ArrayList<Double> winPercentages = new ArrayList<Double>();
		int playerWins = 0;
		int totalPlays = 1000;
		for(int j = 0; j < 100; j++) {
			playerWins = 0;
			totalPlays = 1000;
			for (int k = 0; k < totalPlays; k++) {
				//p.printHand();
				//System.out.println(k);
				for (int i = 0; i < p.getNumCards(); i++) {
					deck.remove(p.hand.get(i));
				}
				play();
				if (getWinner() == p) {
					playerWins++;
				}
				
				newGame(0, currHand);
			}
			//System.out.println(playerWins);
			//System.out.println(totalPlays);
			odds = (playerWins * 1.0) / (totalPlays * 1.0);
			winPercentages.add(odds);
		}
//		for (int k = 0; k < totalPlays; k++) {
//			//p.printHand();
//			//System.out.println(k);
//			
//			for (int j = 0; j < p.getNumCards(); j++) {
//				deck.remove(p.hand.get(j));
//			}
//			play();
//			if (getWinner() == p) {
//				playerWins++;
//			}
//			
//			newGame(0, currHand);
//		}
//		System.out.println(playerWins);
//		System.out.println(totalPlays);
//		odds = (playerWins * 1.0) / (totalPlays * 1.0);
		double sum = 0;
		for (int k = 0; k < winPercentages.size(); k++) {
			sum += winPercentages.get(k);
		}
		odds = sum / 100.0;
		return odds;
	}
	
	public void deal() {
		int index = 0;
		//deal the first card
		for (int k = 1; k < players.size(); k++) {
			players.get(k).addCard(deck.Deal());
		}
		//deal the second card
		for (int k = 1; k < players.size(); k++) {
			players.get(k).addCard(deck.Deal());
		}
	}
	public void dealFlop() {
		for (int k = 0; k < 5; k++) {
			Card c = deck.Deal();
			for (int j = 0; j < players.size(); j++) {
				players.get(j).addCard(c);
			}
		}
	}
	
	public static void main(String[] args) {
		TexasHoldEm g = new TexasHoldEm();
		Player p = g.players.get(0);
		//deck.reset();
		p.addCard(deck.Deal());
		p.addCard(deck.Deal());
		p.printHand();
		double odds = g.calcOdds(p);
//		for(int j = 0; j < 100; j++) {
//			System.out.println(g.calcOdds(p));
//		}
		System.out.println(odds);
		
	}
}
