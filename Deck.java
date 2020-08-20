import java.lang.Math;
import java.util.*;
public class Deck {
	//public Card[] cards = new Card[52];
	public ArrayList<Card> cards = new ArrayList<Card>();
	public int numCards = 52;
	
	public Deck() {
		//make cards for deck
		Character[] suits = new Character[] {'s', 'c', 'd', 'h'};
		int s = 0; //keeps track of what suit the card is
		int count = 2; //value of the card
		for (int k = 0; k < 52; k++) {
			cards.add(new Card(count, suits[s]));
			count++;
			if (count == 15) {
				count = 2;
				s++;
			}
		}
		numCards = 52;
	}
	
	public Card Deal() {
		Card c = cards.get(0);
		cards.remove(0);
		numCards--;
		return c;
	}
	public void remove(Card c) {
		cards.remove(c);
		numCards--;
	}
	public void reset() {
		int s = 0;
		int count = 2;
		cards.clear();
		Character[] suits = new Character[] {'s', 'c', 'd', 'h'};
		for (int k = 0; k < 52; k++) {
			cards.add(new Card(count, suits[s]));
			count++;
			if (count == 15) {
				count = 2;
				s++;
			}
		}
		numCards = 52;
	}
	
	/**
	 * Method called to shuffle the cards comes up with an array of 52 ints 
	 * When you deal from the deck it would be in shuffled order.
	 * Shuffled using the Fisher-Yates Algorithim
	 */
	public void shuffle() {
		//starting with 10 to make sure I get it right
		//int[] order = new int[52];
		for (int k = numCards - 1; k > 0; k--) {
			int v = (int) (Math.random() * k);
			//System.out.println(v);
			Card tmp = cards.get(v);
			cards.set(v, cards.get(k));
			cards.set(k, tmp);
		}
	}
	
	public void print() {
		for (int k = 0; k < numCards; k++) {
			cards.get(k).printCard();
		}
	}
	
	public static void main(String[] args) {
		Deck d = new Deck();
		d.print();
		d.shuffle();
		System.out.println("\n \n \n \n");
		d.print();
	}

}
