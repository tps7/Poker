import java.util.ArrayList;
public class Player {
	public ArrayList<Card> hand = new ArrayList<Card>();
	//public int cards = hand.size();
	public String name;
	public int money;
	public Player() {
		name = "default1";
		money = 500;
	}
	public Player(String n) {
		name = n;
		money = 500;
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	public int getNumCards() {
		return hand.size();
	}
	public String getName() {
		return name;
	}
	public void setName(String s) {
		name = s;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int v) {
		money = v;
	}
	public void addCard(Card c) {
		hand.add(c);
	}
	public void newHand() {
		hand.clear();
	}
	public void printHand() {
		if (hand.size() == 0) {
			System.out.println("No Cards");
			return;
		}
		for (int k = 0; k < hand.size(); k++) {
			hand.get(k).printCard();
		}
	}
	public String getStringHand() {
		String rtrn = "";
		if (hand.size() == 0) {
			rtrn = "No Cards";
			return rtrn;
		}
		for (int k = 0; k < hand.size(); k++) {
			rtrn += hand.get(k).getStringCard();
			rtrn += ", ";
		}
		return rtrn;
	}

}
