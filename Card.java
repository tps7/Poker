public class Card {
	/**
	 * Card Values
	 * 2:2 3:3...ect
	 * 11:Jack, 12:Queen, 13:K, 14:Ace
	 */
	public int value;
	
	/**
	 * Card suits
	 * s : spades
	 * c : clubs
	 * d : diamonds
	 * h : hearts
	 */
	public Character suit;
	
	/**
	 * Card Constructor
	 * @param v The Card value;
	 * @param s The Card suit;
	 */
	public Card(int v, Character s) {
		if (v < 2 || v > 14) {
			throw new ExceptionInInitializerError("Cards cannot have that value try again");
		}
		if (s != 's' && s != 'c' && s != 'd' && s != 'h') {
			throw new ExceptionInInitializerError("Cards cannot have that suit try again");
		}
		value = v;
		suit = s;
	}
	
	public int getValue() {return value;}
	public Character getSuit() {return suit;};
	
	public void printCard() {
		String s = "";
		if (value > 10) {
			if (value == 11) {
				s += "Jack";
			} else if (value == 12) {
				s += "Queen";
			} else if (value == 13) {
				s += "King";
			} else {
				s += "Ace";
			}
		} else {
			s += Integer.toString(value);
		}
		s += " of ";
		if (suit == 's') {
			s += "Spades";
		} else if (suit == 'c') {
			s += "Clubs";
		} else if (suit == 'd') {
			s += "Diamonds";
		} else {
			s += "Hearts";
		}
		System.out.println(s);
	}
}
