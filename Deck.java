import java.lang.Math;
public class Deck {
	public Card[] cards = new Card[52];
	
	public Deck() {
		//make cards for deck
		Character[] suits = new Character[] {'s', 'c', 'd', 'h'};
		int s = 0;
		int count = 2;
		for (int k = 0; k < cards.length; k++) {
			cards[k] = new Card(count, suits[s]);
			count++;
			if (count == 15) {
				count = 2;
				s++;
			}
		}
	}
	
	/**
	 * Method called to shuffle the cards comes up with an array of 52 ints 
	 * When you deal from the deck it would be in shuffled order.
	 * Shuffled using the Fisher-Yates Algorithim
	 */
	public void shuffle() {
		//starting with 10 to make sure I get it right
		//int[] order = new int[52];
		for (int k = 51; k > 0; k--) {
			int v = (int) (Math.random() * k);
			//System.out.println(v);
			Card tmp = cards[v];
			cards[v] = cards[k];
			cards[k] = tmp;
		}
	}
	
	public void print() {
		for (int k = 0; k < 52; k++) {
			cards[k].printCard();
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
