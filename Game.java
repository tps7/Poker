import java.util.ArrayList;
/**
 * 
 * @author timothysullivan
 * TODO: 
 * Add comments to Code it is very confusing right now
 * Simplify and delete methods to make code more understandable
 * Understand algorithim to get all combinations better.
 * Write methods for a game.
 * Add table class. This class will have a game and a number of players playing.
 * Write somthing for wild cards (Do this after Holdem works?)
 */
public class Game {
	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Deck deck = new Deck();
	public int numPlayers;
	public Game() {
		players.add(new Player("Bob"));
		//System.out.println(Player);
		players.add(new Player("Joe"));
		players.add(new Player("Bob Joe"));
		players.add(new Player("Joe Bob"));
		deck.shuffle();
	}
	
	public Game(ArrayList<Player> p) {
		players = p;
	}
	
	/**
	 * This function takes in two hands of Cards and sees which hand is better.
	 * @param h1 First hand you are Comparing max 5 cards
	 * @param h2 Second hand you are comparing max 5 cards
	 * @return 0 if the first hand is greater than the second. 1 if the second hand greater than the first
	 * 2 if the hands are equal.
	 */
	public static int compareHands(ArrayList<Card> h1, ArrayList<Card> h2) {
		ArrayList<Integer> one = getHand(h1);
		ArrayList<Integer> two = getHand(h2);
		if (one.get(0) > two.get(0)) {
			return 0;
		} else if(two.get(0) > one.get(0)) {
			return 1;
		} else {
			for (int k = 1; k < 5; k++) {
				if (one.get(k) > two.get(k)) {
					return 0;
				} else if(two.get(k) > one.get(k)) {
					return 1;
				} else {
					continue;
				}
			}
		}
		return 2;
	}
	/**
	 * This function compares two hands in their integer forms. I may not need to use this method ever.
	 * @param one first hand
	 * @param two second had
	 * @return 0 if first hand bigger 1 if second hand bigger 2 if tie.
	 */
	public static int compareIntHands(ArrayList<Integer> one, ArrayList<Integer> two) {
		if (one.get(0) > two.get(0)) {
			return 0;
		} else if(two.get(0) > one.get(0)) {
			return 1;
		} else {
			for (int k = 1; k < 5; k++) {
				if (one.get(k) > two.get(k)) {
					return 0;
				} else if(two.get(k) > one.get(k)) {
					return 1;
				} else {
					continue;
				}
			}
		}
		return 2;
	}
	
	/**
	 * Given a 5 cards this lists finds the best hand that you have and returns it with a int value.
	 * @param Hand The cards in the hand. Only works with 5.
	 * @return returns an array List that represents the srength of the hand. The number is 100 for a pair,
	 * 200 for a 2 pair 300 for 3 of a kind.. up to 700 for a straight flush. 
	 * The next numbers in the list are the cards listed in order of strength (Ace king queen...)
	 */
	public static ArrayList<Integer> getHand(ArrayList<Card> hand) {
		//return a list of integers to handle edge cases.
		for (Card a : hand) {
			a.printCard();
		}
		System.out.println("\n \n \n \n");
		int handValue = 0;
		//Sort array highest card to lowest card
		for (int k = 1; k < hand.size(); k++) {
			Card key = hand.get(k);
			int j = k - 1;
			//Move elements that are greater than key to one position ahead of there current postion
			while (j >= 0 && (hand.get(j).getValue() < key.getValue())) {
				hand.set(j + 1, hand.get(j));
				j = j - 1;
			}
			hand.set(j + 1, key);
		}
		int pairs = 0;
		int streak = 1;
		/* index values: 0 = pair, 1 = two pair, 2 = three of a kind, 3 = straight, 4 = flush
		 * 5 = fullhouse, 6 = four of a kind, 7 = Straight Flush*/
		boolean[] hands = new boolean[8];
		Card highCard = hand.get(hand.size() - 1);
		int pairValue = 0;
		//set straight to true
		hands[3] = true;
		//set flush to true
		hands[4] = true;
		for (int k = 1; k < hand.size(); k++) {
			int v1 = hand.get(k - 1).getValue();
			int v2 = hand.get(k).getValue();
			//At least a pair
			if (v1 == v2) {
				streak++;
				if (streak == 2) {
					pairs++;
					pairValue = hand.get(k).value;
					hands[0] = true;
					//Check for two pair
					if (pairs == 2) {
						hands[1] = true;
						if (hand.get(k).getValue() > pairValue) {
							pairValue = hand.get(k).value;
						}
					}
				}
				//Check for 3 of a kind
				if (streak == 3) {
					hands[2] = true;
					pairValue = hand.get(k).value;
					//three_of_a_kind = true;
					pairs--;
					if (k > 1) {
						//System.out.println();
						hands[0] = false;
					}
				}
				//Check for 4 of a kind
				if (streak == 4) {
					hands[6] = true;
					pairValue = hand.get(k).value;
					//four_of_a_kind = true;
				}
			} else {
				streak = 1;
			}
			//Check for straight
			if (v1 + 1 != v2) {
				hands[3] = false;
			}
			//Check for flush
			if (hand.get(k - 1).getSuit() != hand.get(k).getSuit()) {
				hands[4] = false;
			}
		}
		//Check for straight flush
		if (hands[3] == true && hands[4] == true) {
			hands[7] = true;
		}
		//check for fullhouse
		//System.out.println(hands[0]);
		if (hands[2] == true && hands[0] == true) {
			hands[5] = true;
		}
		for (int k = hands.length - 1; k >= 0; k--) {
			if(hands[k]) {
				handValue = k * 100;
				break;
			}
		}
		//Dont need to adjust if you have a straight/flush you can't have a pair so handvalue would be zero;
//		if (pairValue != 0) {
//			handValue += pairValue;
//		} else {
//			handValue += highCard.getValue();
//		}
		ArrayList<Integer> rtrn = new ArrayList<Integer>();
		for (Card a : hand) {
			a.printCard();
			rtrn.add(a.value);
		}
		rtrn.add(0, handValue);
		return rtrn;
		
		//System.out.println(hand.toString());
	}
	

	private static int t = 0;
	
	/**
	 * Given an array and size of a sub array this function gets all permutations of the given array of size r.
	 * @param arr The given array 
	 * @param r The size of the sub arrays
	 * @return a 2d array with each array being one combination.
	 */
    public static ArrayList<ArrayList<Integer>> getCombos(ArrayList<Integer> arr, int r) {
    	//int data[]=new int[r]; 
        ArrayList<Integer> data = new ArrayList();
  
        // Print all combination using temprary array 'data[]'
        ArrayList<ArrayList<Integer>> rtrn = new ArrayList<ArrayList<Integer>>();
        rtrn.add(comboUtil(arr, data, 0, arr.size()-1, 0, r));
        return rtrn;
        //return null;
    }
    public static ArrayList<Integer> comboUtil(ArrayList<Integer> arr, ArrayList<Integer> data, int start, int end, 
    		int index, int r) 
    {
    	ArrayList<Integer> rtrn = new ArrayList<Integer>();
        // Current combination is ready to be printed, print it 
        if (index == r) 
        { 
            for (int j=0; j<r; j++) {
            	rtrn.add(data.get(j));
            }
                //System.out.print(data.get(j) + " "); 
            //System.out.println("");
            t++;
            return rtrn;
        } 
  
        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i=start; i<=end && end-i+1 >= r-index; i++) 
        {
        	data.add(index, arr.get(i));
            //data.get(index) = arr.get(i);
        	// Since the elements are sorted, all occurrences of an element
            // must be together 
            comboUtil(arr, data, i+1, end, index+1, r); 
            
//            while (arr.get(i) == arr.get(i+1)) {
//            	i++;
//            }
        }
		return rtrn;
    } 
    /**
     * This functions goes through all diffrent permuatations of a hand and picks out the best poker hand.
     * To do this the function goes through each permutation and calls compare hands with this permatation and 
     * the best poker hand so far. Thus by the end it returns the best hand. 
     * @param hand The poker hand you want find the best hand of. This hand must be bigger than 5 cards
     * @param data Not entirly sure.
     * @param best The best poker hand so far. Starts out as an empty Array.
     * @param start Start of the array for the next permutation.
     * @param end End of the array for the next permutation.
     * @param index Not entirly sure.
     * @param r Number of cards you want to go through this number should always be 5.
     * @return The best 5 card poker hand of the list of cards given.
     */
    public static ArrayList<Card> comboUtilC(ArrayList<Card> hand, ArrayList<Card> data, ArrayList<Card> best,
    int start, int end, int index, int r) 
    {
    	ArrayList<Card> rtrn = new ArrayList<Card>();
        // Current combination is ready to be printed, print it 
        if (index == r) 
        { 
            for (int j=0; j<r; j++) {
            	rtrn.add(data.get(j));
//            	data.get(j).printCard();
            	//System.out.println(r); 
            }
            
            if (best.isEmpty()) {
            	best = rtrn;
            } else if (compareHands(rtrn, best) == 0) {
            	best = rtrn;
            }
            //System.out.println("");
            
            t++;
            return best;
        } 
  
        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i=start; i<=end && end-i+1 >= r-index; i++) 
        {
        	data.add(index, hand.get(i));
            //data.get(index) = arr.get(i);
        	// Since the elements are sorted, all occurrences of an element
            // must be together 
            best = comboUtilC(hand, data, best, i+1, end, index+1, r); 
            
//            while (arr.get(i) == arr.get(i+1)) {
//            	i++;
//            }
        }
        
		return best;
    }
    /**
     * This function is the function that actually gets the best hand. It is basically
     * a more simplified version of ComboutilC and it just calls CombosUtilC and returns what 
     * CombosutilC returns.
     * @param arr The array of cards you want to find the best hand for. This should be greater than 5
     * @param r The size of the subarray for permutations. This should always be 5.
     * @return The best poker hand of the Cards given.
     */
    public static ArrayList<Card> getCombosC(ArrayList<Card> arr, int r) {
    	//int data[]=new int[r]; 
        ArrayList<Card> data = new ArrayList();
        ArrayList<Card> best = new ArrayList();
        // Print all combination using temprary array 'data[]'
        ArrayList<Card> rtrn = new ArrayList<Card>();
        rtrn = comboUtilC(arr, data, best, 0, arr.size()-1, 0, r);
        
        return rtrn;
        //return null;
    }
//    public static ArrayList<Integer> comboUtil(ArrayList<Card> hand, ArrayList<Integer> data, int start, int end, 
//    		int index, int r) 
//    {
//    	ArrayList<Integer> arr = new ArrayList<Integer>();
//		for (int k = 1; k < hand.size(); k++) {
//			Card key = hand.get(k);
//			int j = k - 1;
//			//Move elements that are greater than key to one position ahead of there current postion
//			while (j >= 0 && (hand.get(j).getValue() < key.getValue())) {
//				hand.set(j + 1, hand.get(j));
//				j = j - 1;
//			}
//			hand.set(j + 1, key);
//		}
//		for (int k = 0; k < hand.size(); k++) {
//			arr.add(hand.get(k).value);
//		}
//		
//    	ArrayList<Integer> rtrn = new ArrayList<Integer>();
//        // Current combination is ready to be printed, print it 
//        if (index == r) 
//        { 
//            for (int j=0; j<r; j++) {
//            	rtrn.add(data.get(j));
//            }
//                //System.out.print(data.get(j) + " "); 
//            //System.out.println("");
//            t++;
//            return rtrn;
//        } 
//  
//        // replace index with all possible elements. The condition 
//        // "end-i+1 >= r-index" makes sure that including one element 
//        // at index will make a combination with remaining elements 
//        // at remaining positions 
//        for (int i=start; i<=end && end-i+1 >= r-index; i++) 
//        {
//        	data.add(index, arr.get(i));
//            //data.get(index) = arr.get(i);
//        	// Since the elements are sorted, all occurrences of an element
//            // must be together 
//            comboUtil(arr, data, i+1, end, index+1, r); 
//            
////            while (arr.get(i) == arr.get(i+1)) {
////            	i++;
////            }
//        }
//		return rtrn;
//    }
    
//    public int getBestHand(ArrayList<Card> hand) {
//    	ArrayList<Integer> arr = new ArrayList<Integer>();
//		for (int k = 1; k < hand.size(); k++) {
//			Card key = hand.get(k);
//			int j = k - 1;
//			//Move elements that are greater than key to one position ahead of there current postion
//			while (j >= 0 && (hand.get(j).getValue() < key.getValue())) {
//				hand.set(j + 1, hand.get(j));
//				j = j - 1;
//			}
//			hand.set(j + 1, key);
//		}
//		for (int k = 0; k < hand.size(); k++) {
//			arr.add(hand.get(k).value);
//		}
//    	ArrayList<ArrayList<Integer>> hands = getCombos(arr, 5); //new ArrayList<ArrayList<Integer>>();
//    	//ArrayList<Integer> maxHand = getHand(hand);
//    	//ArrayList<Integer>  = getHand(c);
//    	return 0;
//    	
//    }
    
    public ArrayList<Integer> intGetBestHand(ArrayList<Card> c) {
    	return null;
    }
    
	//Winner function to be completed once player function is fixed.
//	public Player winner() {
//		Player win = players.get(0);
//		for (int k = 1; k < players.size(); k++) {
//			compareHands(win.getHand(), players.get(k))
//		}
//		return win;
//	}
	
	public static void main(String[] args) {
		Game g = new Game();
		for (int k = 0; k < 5; k++) {
//			System.out.println(players.size());
//			System.out.println(deck.cards.length);
			players.get(0).addCard(deck.cards[k]);
		}
		ArrayList<Card> c = new ArrayList<Card>();
		c.add(new Card(2, 'h'));
		c.add(new Card(2, 'h'));
		c.add(new Card(3, 'h'));
		c.add(new Card(8, 'h'));
		c.add(new Card(7, 'h'));
		ArrayList<Card> c2 = new ArrayList<Card>();
		c2.add(new Card(2, 'h'));
		c2.add(new Card(9, 'c'));
		c2.add(new Card(9, 'h'));
		c2.add(new Card(8, 'h'));
		c2.add(new Card(8, 'h'));
		c2.add(new Card(9, 's'));
		c2.add(new Card(13, 'c'));
		
		//System.out.println(getHand(c));
		//System.out.println(compareHands(c, c2));
		ArrayList<Integer> rtrn = new ArrayList<Integer>();
		rtrn.add(2); rtrn.add(3); rtrn.add(4); rtrn.add(5); rtrn.add(6); rtrn.add(7); rtrn.add(8);
		//Input list must be sorted
		//printCombination(rtrn, 5);
		ArrayList<Card> q = new ArrayList<Card>();
		q = getCombosC(c2, 5);
		
		
//		for (Card a : c) {
//			a.printCard();
//		}
		System.out.println(t);
		int e = 5;
		//System.out.println(q.size());
		for (int k = 0; k < q.size(); k++) {
			q.get(k).printCard();
		}
	}
}

/* arr[]  ---> Input Array 
data[] ---> Temporary array to store current combination 
start & end ---> Staring and Ending indexes in arr[] 
index  ---> Current index in data[] 
r ---> Size of a combination to be printed */
//public static void combinationUtil(ArrayList<Integer> arr, ArrayList<Integer> data, int start, int end, int index, int r) 
//{ 
//    // Current combination is ready to be printed, print it 
//    if (index == r) 
//    { 
//        for (int j=0; j<r; j++) 
//            System.out.print(data.get(j) + " "); 
//        System.out.println("");
//        t++;
//        return; 
//    } 
//
//    // replace index with all possible elements. The condition 
//    // "end-i+1 >= r-index" makes sure that including one element 
//    // at index will make a combination with remaining elements 
//    // at remaining positions 
//    for (int i=start; i<=end && end-i+1 >= r-index; i++) 
//    {
//    	data.add(index, arr.get(i));
//        //data.get(index) = arr.get(i);
//    	// Since the elements are sorted, all occurrences of an element
//        // must be together 
//        combinationUtil(arr, data, i+1, end, index+1, r); 
//        
////        while (arr.get(i) == arr.get(i+1)) {
////        	i++;
////        }
//    }
//} 
//static void printCombination(ArrayList<Integer> arr, int r) 
//{ 
//    // A temporary array to store all combination one by one 
//    //int data[]=new int[r]; 
//    ArrayList<Integer> data = new ArrayList();
//
//    // Print all combination using temprary array 'data[]' 
//    combinationUtil(arr, data, 0, arr.size()-1, 0, r); 
//}

//public ArrayList<Integer> getBestHand(ArrayList<Card> hand) {
//if (hand.size() <= 5) {
//	return getHand(hand);
//}
//int index = 0;
//int counter = 0;
//int cards = hand.size() - 1;
//ArrayList<Integer> maxHand = getHand(new ArrayList<Card>(hand.subList(0, 5)));
//for (int k = 1; k < cards; k++) {
//	ArrayList<Integer> test = getHand(new ArrayList<Card>(hand.subList(index, index + 5)));
//	if (compareIntHands(test, maxHand) == 0) {
//		maxHand = test;
//	}
//}
//while(index > 0) {
//	ArrayList<Integer> test = getHand(new ArrayList<Card>(hand.subList(index, index + 5)));
//	if (compareIntHands(test, maxHand) == 0) {
//		maxHand = test;
//	}
//	index--;
//}
////while()
//while(index <= hand.size() - 6) {
////for (int k = 0; k < hand.size(); k++) {
//	ArrayList<Integer> test = getHand(new ArrayList<Card>(hand.subList(index, index + 5)));
//	for (int j = index; j < index + 5; j++) {
//		
//	}
//	
//}
//return maxHand;
//}
