import java.util.ArrayList;
public class Player {
	public ArrayList<Card> hand = new ArrayList<Card>();
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

}
