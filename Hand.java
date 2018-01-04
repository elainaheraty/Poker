package compSciProj3;

/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

//import java.util.ArrayList;

public class Hand {
	
	private Card[] hand;   // The cards in the hand.
	private int count; 
	/**
	 * Create a hand that is initially empty.
	 */
	public Hand() {
		hand = new Card[5];
		count = 0;
	}

	/**
	 * Remove all cards from the hand, leaving it empty.
	 */
	public void clear() {
		for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
		count = 0;
	}

	/**
	 * Add a card to the hand.  It is added at the end of the current hand.
	 * @param c the non-null card to be added.
	 * @throws NullPointerException if the parameter c is null.
	 */
	public void addCard(Card c) {

		for(int i=0 ; i<hand.length; i++){ 
			if (hand[i] == null){
				hand[i] = c;
				count = count + 1;
				break;
			}
		}


	}

	  /**
	    * Remove a card from the hand, if present.
	    * @param c the card to be removed.  If c is null or if the card is not in 
	    * the hand, then nothing is done.
	    */
	   public void removeCard(Card c) {

	  for(int i=0 ; i<hand.length; i++){ 
	    if (hand[i]!=null && hand[i].equals(c)){
	      hand[i] = null;
	      count = count-1;
	    }
	  }

	   }
	   
	   /**
	    * Remove the card in a specified position from the hand.
	    * @param position the position of the card that is to be removed, where
	    * positions are starting from zero.
	    * @throws IllegalArgumentException if the position does not exist in
	    * the hand, that is if the position is less than 0 or greater than
	    * or equal to the number of cards in the hand.
	    */
	   public void removeCard(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	      hand[position] = null;
	      count --;
	   }

	/**
	 * Returns the number of cards in the hand.
	 */
	public int getCardCount() {
		return count;
	}

	/**
	 * Gets the card in a specified position in the hand.  (Note that this card
	 * is not removed from the hand!)
	 * @param position the position of the card that is to be returned
	 * @throws IllegalArgumentException if position does not exist in the hand
	 */
	public Card getCard(int position) {
		if (position < 0 || position >= hand.length)
			throw new IllegalArgumentException("Position does not exist in hand: "
					+ position);
		return hand[position];
	}

	/**
	 * Sorts the cards in the hand so that cards of the same suit are
	 * grouped together, and within a suit the cards are sorted by value.
	 * Note that aces are considered to have the lowest value, 1.
	 */
	public void sortBySuit() {
		int size = count;
		int nonnull = 0;
		int index = 0;

		Card[] newHand = new Card[5];
		while (size > 0) {
			if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
			int pos = nonnull;  // Position of minimal card.
			Card c = hand[nonnull];  // Minimal card.

			for (int i = nonnull+1; i < hand.length; i++) {
				Card c1 = hand[i];
				if (c1 != null){
					if ( c1.getSuit() < c.getSuit() ||
							(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
						pos = i;
						c = c1;
					}
				}
			}
			hand[pos] = null;
			size = size - 1;
			newHand[index++] = c;
			nonnull = 0;
		}
		hand = newHand;
	}

	/**
	 * Sorts the cards in the hand so that cards of the same value are
	 * grouped together.  Cards with the same value are sorted by suit.
	 * Note that aces are considered to have the lowest value, 1.
	 */
	public void sortByValue() {
		int size = count;
		int nonnull = 0;
		int index = 0;

		Card[] newHand = new Card[5];
		while (size > 0) {
			if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
			int pos = nonnull;  // Position of minimal card.
			Card c = hand[nonnull];  // Minimal card.

			for (int i = nonnull+1; i < hand.length; i++) {

				Card c1 = hand[i];
				if (c1 != null){
					if ( c1.getValue() < c.getValue() ||
							(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
						pos = i;
						c = c1;
					}
				}
			}
			hand[pos] = null;
			size = size - 1;
			newHand[index++] = c;
			nonnull = 0;
		}
		hand = newHand;
	}

	public void printHand(){

		for(int i=0; i<hand.length; i++){
			if (hand[i] != null){
				System.out.println(hand[i]);
			}
		}
		System.out.println();
	}


	/******************************** Implement your methods here ****************************************/
	/*
	public int numPairs() {
		int count=0;
		if(hasFourOfAKind()) {
			return 2;
		}
		else if(hasTriplet()) {
			if(hand[2].getValue()==hand[1].getValue()&&hand[2].getValue()==hand[3].getValue()) {
				return 1;
			}
			else if(hand[2].getValue()==hand[1].getValue()) {
				if(hand[3].getValue()==hand[4].getValue()) {
					return 2;
				}
				return 1;
			}
			else if(hand[0].getValue()==hand[1].getValue()) {
					return 2;
				}

		}
			for(int i=0; i<hand.length-1; i++) {
				if(hand[i].getValue()==hand[i+1].getValue()) {
					count++;
				}
			}
			return count;
	}
	*/
	public int numPairs() {
		int count=0;
		sortByValue();		
		for(int i=0; i<hand.length-1; i++) {
			if(hand[i].getValue()==hand[i+1].getValue()) {
				i++;
				count++;
			}
		}
		return count;
	}


	public boolean hasTriplet() {
		sortByValue();
		for(int i=1; i<hand.length-1; i++) {
			if(hand[i-1].getValue()==hand[i].getValue()&&hand[i].getValue()==hand[i+1].getValue()) {
				return true;
			}
		}
		return false;
	}
		
	public boolean hasFourOfAKind() {
		sortByValue();
		for(int i=1; i<hand.length-2; i++) {
			if(hand[i-1].getValue()==hand[i].getValue()&&hand[i].getValue()==hand[i+1].getValue()) {
				if(hand[i].getValue()==hand[i+2].getValue()) {
					return true;
				}
			}
		}
		return false;
	}



	public boolean hasStraight() {
		sortByValue();
		for(int i=1; i<hand.length-1; i++) {
			if(hand[i].getValue() + 1 != hand[i+1].getValue()) {
				return false;
			}
		}
		if(hand[0].getValue() == 1 ) {
			if(hand[1].getValue() == 10 || hand[1].getValue() == 2) {
				return true;
			}
		}
		else if(hand[0].getValue() + 1 == hand[1].getValue()) {
			return true;
		}
		
		return false;
	}

	
	public boolean hasFullHouse() {
		if(numPairs() == 2 && hasTriplet() == true && hasFourOfAKind() == false) {
			return true;
		}
		return false;
	}

	public boolean hasFlush() {
		sortBySuit();
		int val = hand[0].getSuit();
		for(int i=1;i<hand.length;i++) {
			if(val!=hand[i].getSuit()) {
				return false;
			}
		}
		return true;
	}

	public Card highestValue() {
		sortByValue();
		if(hand[0].getValue()==1) {
			return hand[0];
		}
		return hand[4];	
	}

	public Card highestDuplicate() {
		sortByValue();
		Card highestDuplicate = null;
		for(int i=1; i<hand.length;i++) {	
			if(hand[i-1].getValue()==hand[i].getValue()) {
				if(hand[i].getValue()==1) {
					// found ace stop iterating.
					highestDuplicate = hand[i]; 
					break;
				}
				else if((highestDuplicate == null) || (highestDuplicate.getValue() < hand[i].getValue())) {
					highestDuplicate = hand[i];
				}
			}
		}
		return highestDuplicate;
	}

	
	public int compareTo(Hand h) {
		int max1;
		int max2;
		int secondMax1;
		int secondMax2;
		int thirdMax1;
		int thirdMax2;
		int i;
		
		// ROYAL FLUSH
		// Testing player1 hand for Royal flush
		if(hasFlush()&&hasStraight()&&hand[4].getValue()==13&&hand[0].getValue()==1) {
			// player1 has royal flush
			// testing player2 hand for royal flush hand 
			if(h.hasFlush()&&h.hasStraight()&&h.hand[4].getValue()==13&&h.hand[0].getValue()==1) {
				// player2 also has royal flush
				// no winner
				return 0;
			}
			else {
				// player2 does not have royal flush
				// player 1 wins
				return 1;
			}
		}
		// Testing player2 hand for royal flush
		if(h.hasFlush()&&h.hasStraight()&&h.hand[4].getValue()==13&&h.hand[0].getValue()==1) {
			// player2 has royal flush and wins
			return -1;
		}

		// STRAIGHT FLUSH
		// Testing player1 hand for straight flush
		if(hasFlush()&&hasStraight()) {
			// player1 has straight flush
			// Testing player2 hand for straight flush
			if(h.hasFlush()&&h.hasStraight()) {
				// player2 also has straight flush
				if(hand[4].getValue() > h.hand[4].getValue()) {
					// player1 has higher sequence and wins
					return 1;
				}
				if(hand[4].getValue()<h.hand[4].getValue()) {
					// player2 has higher sequence and wins
					return -1;
				}
				// both player have the same straight flush
				// no winer
				return 0;
			}
			else {
				// player1 has straight flush but player2 does not
				// player1 is the winner
				return 1;
			}
		}
		// Testing player2 hand for straight flush
		if(h.hasFlush()&&h.hasStraight()) {
			// player2 has straight flush and wins
			return -1;
		}
		
		// FOUR OF A KIND
		// Testing player1 hand for 4 of a kind
		if(hasFourOfAKind()) {
			// Testing player2 hand for 4 of a kind
			if(h.hasFourOfAKind()) {
				int player1CardValue = highestDuplicate().getValue();
				int player2CardValue = h.highestDuplicate().getValue();
				if(player1CardValue == 1) {
					// player1 got all the 4 aces and wins
					return 1;
				}
				if(player2CardValue == 1) {
					// player2 got all the 4 aces and wins
					return -1;
				}
			
				if(player1CardValue < player2CardValue) {
					// player2 has higher value 4 of a kind and wins
					return -1;
				}
				else {
					// player1 has higher value 4 of a kind and wins
					return 1;
				}

			}
			else {
				// player1 has 4 of a kind but player2 does not
				// player1 wins
				return 1;
			}
		}
		// Testing player2 hand for 4 of a kind
		if(h.hasFourOfAKind()){ 
			// player2 has 4 of a kind but player1 does not
			// player2 wins			
			return -1;
		}

		// FULL HOUSE (triplet and a pair)
		// Testing player1 hand for full house
		if(hasFullHouse()) {
			// Testing player2 hand for full house
			if(h.hasFullHouse()) {
				// both have full house
				// find player1 triplet card value and double card value
				if(hand[0].getValue()==hand[2].getValue()) {
					max1 = hand[0].getValue();
				}	
				else {
					max1 =hand[4].getValue();
				}
				// find palyer2 triplet card value and double card value
				if(h.hand[0].getValue()==h.hand[2].getValue()) {
					max2 = h.hand[0].getValue();
				}
				else {
					max2 = h.hand[4].getValue();
				}
				if(max2 == 1) {
					// player2 has ace triplet and wins
					return -1;
				}
				if(max1==1) {
					// player1 has ace triplet and wins
					return 1;
				}
				if(max1 >  max2) {
					// player1 has higher triplet and wins
					return 1;
				}
				else {
					// player2 has higher triplet and wins
					return -1;	
				}
			}
			else {
				// player1 has full house and player2 does not 
				// player1 wins
				return 1;
			}
		}
		else if(h.hasFullHouse()) {
			// player2 has full house and player2 does not 
			// player2 wins
			return -1;
		}
		

		// FLUSH
		// Testing player1 hand for flush 		
		if(hasFlush()) {
			// player1 has flush
			if(h.hasFlush()) {
				// player2 has flush
				// hand with the highest card wins
				if(hand[0].getValue() == 1 && h.hand[0].getValue() != 1) {
					// player1 has an ace and wins
					return 1;
				}
				
				if(h.hand[0].getValue() == 1 && hand[0].getValue() != 1) {
					// player2 has an ace and wins
					return -1;
				}
				for(i=4; i>0; i--) {
					if(hand[i].getValue() > h.hand[i].getValue()) {
						//player1 has card of higher value and wins
						return 1;
					}
					if(hand[i].getValue() < h.hand[i].getValue()) {
						//player2 has card of higher value and wins
						return -1;
					}
				}
				// no winner
				return 0;
			}
			else  {
				// player1 has flush and player2 does not
				// player1 wins
				return 1;
			}
		}
		// Testing player2 hand for flush 		
		if (h.hasFlush()) {
			// player2 has flush and player1 does not
			// player2 wins
			return -1;
		}

		// STRAIGHT
		// Testing player1 hand for straight 
		if(hasStraight()) {
			// player1 has straight
			// Testing player1 hand for straight 
			if(h.hasStraight()) {
				// player2 has straight
				max1 = highestValue().getValue();
				max2 = h.highestValue().getValue();
				if(max1 == max2){
					// both players have the same straight hand
					// no winner
					return 0;
				}
				else {
					if(max1 == 1){
						// player1 has a straight hand with higher value cards and wins 
						return 1;
					}
					if(max2 == 1){
						// player2 has a straight hand with higher value cards and wins 
						return -1;
					}
					if(max1 < max2) {
						// player2 has a straight hand with higher value cards and wins 
						return -1;
						
					}
					else {
						// player1 has a straight hand with higher value cards and wins 
						return 1;
					}
				}
			}
			else {
				// player1 has a straight hand but player2 does not
				// player1 wins
				return 1;
			}
		}
		// Testing player2 hand for straight 
		if (h.hasStraight()) {
			// player2 has a straight hand but player1 does not
			// player2 wins
			return -1;
		} 
		
		// TRIPLET
		// Testing player1 hand for triplet 
		if (hasTriplet()) {
			// player1 has triple
			// Testing player1 hand for triplet 
			if(h.hasTriplet()) {
				// player2 also has triplet
				//find player1 triplet card value 
				if(hand[0].getValue()==hand[2].getValue()) {
					max1 = hand[0].getValue();
				}	
				else {
					max1 =hand[3].getValue();
				}
				// find player2 triple card value
				if(h.hand[0].getValue()==h.hand[2].getValue()) {
					max2 = h.hand[0].getValue();
				}
				else {
					max2 = h.hand[3].getValue();
				}
				if(max1 == 1){
					// player1 has a triplet of aces
					return 1;
				}
				if(max2 == 1){
					// player2 has a triplet of aces
					return -1;
				}
				if(max2>max1) {
					// player2 has a triple of higher value cards and wins
					return -1;
				}
				else {
					// player1 has a triple of higher value cards and wins
					return 1;
				}
			}
			else {
				// player1 has a triplet wins			
				return 1;
			}
		}
		// Testing player2 hand for triplet
		if(h.hasTriplet()) {
			// player2 has a triplet wins			
			return -1;
		}

		// TWO PAIRS
		// Testing player1 hand for two pairs 		
		if(numPairs()==2) {
			// player1 has two pairs
			// Testing player2 hand for two pairs 	
			if(h.numPairs()==2) {
				// player2 has two pairs
				// get player1 two pairs and other card values
				if(hand[0].getValue()!=hand[1].getValue()) {
					// player1 first card is not part of a pair
					max1=hand[4].getValue();
					secondMax1=hand[1].getValue();
					thirdMax1=hand[0].getValue();
				}
				else {
					// player1 first card is part of a pair
					if(hand[0].getValue()==1) {
						// player1 has a pair of aces
						max1=hand[0].getValue();
						secondMax1=hand[3].getValue();
						if(hand[2]!=hand[3]) {
							thirdMax1=hand[2].getValue();
						}
						else {
							thirdMax1=hand[4].getValue();
						}
					}
					else{
						// player1 has no ace pair
						max1=hand[3].getValue();
						secondMax1=hand[0].getValue();
						if(hand[2]!=hand[3]) {
							thirdMax1=hand[2].getValue();
						}
						else {
							thirdMax1=hand[4].getValue();
						}
					}
				}
				// get player2 two pairs and other card values
				if(h.hand[0].getValue()!=h.hand[1].getValue()) {
					// player2 first card is not part of a pair
					max2=h.hand[4].getValue();
					secondMax2=h.hand[1].getValue();
					thirdMax2=h.hand[0].getValue();
				} 
				else {
					// player2 first card is part of a pair
					if(h.hand[0].getValue()==1) {
						// player2 has a pair of aces
						max2=h.hand[0].getValue();
						secondMax2=h.hand[3].getValue();
						if(h.hand[2]!=h.hand[3]) {
							thirdMax2=h.hand[2].getValue();
						}
						else {
							thirdMax2=h.hand[4].getValue();
						}
					}
					else{
						// player2 has no ace pair
						max2=h.hand[3].getValue();
						secondMax2=h.hand[0].getValue();
						if(h.hand[2]!=h.hand[3]) {
							thirdMax2=h.hand[2].getValue();
						}
						else {
							thirdMax2=h.hand[4].getValue();
						}
					}
				}
				
				if(max1!=max2) {
					// highest pair or player1 and player2 are not the same
					if(max1==1) {
						// player1 has a pair of access but player2 does not 
						// player1 wins
						return 1;
					}
					if(max2==1) {
						// player2 has a pair of access but player1 does not 
						// player2 wins
						return -1;
					}
					if(max1>max2) {
						// player1 has a higher value pair than player2
						// player1 wins
						return 1;
					}
					else {
						// player2 has a higher value pair than player1
						// plyer2 wins
						return -1;
					}
				}
				else {
					// player1 and player2 highest value pair are the same
					// Check next  pair
					if(secondMax1 != secondMax2) {
						// player1 and player2 do not have the same second pair
						if(secondMax1>secondMax2) {
							// player1 has a higher value second pair than player2
							// player1 wins
							return 1;
						}
						else {
							// player2 has a higher value second pair than player1
							// plyer2 wins
							return -1;
						}
					}
					else {
						// player1 and player2 have the same second pair also
						// check the single card value
						 if(thirdMax1 != thirdMax2) {
							if(thirdMax1==1) {
								// player1 has an ace but player2 does not 
								// player1 wins
								return 1;
							}
							if(thirdMax2==1) {
								// player2 has an ace but player1 does not 
								// player2 wins
								return -1;
							}
						   	if(thirdMax1 > thirdMax2) {
						   		// player1 has a higher value card and wins
						   		return 1;
						   	}
						   	else {
						   		// player2 has a higher value card
						   		return -1;
						   	}
						}
						else {
							// both players have the same value 2 pairs and the same value card
							// no winner
							return 0;
						}  
					}
				}
			}	
			else {	
				// player2 does not have 2 pairs
				return 1;
			}
		}
		if (h.numPairs()==2) {
			// player2 has 2 pairs but player1 does not
			return -1; 
		}
		
		
		// ONE PAIR
		// Testing player1 hand for one pair
		if (numPairs()==1) {
			// player1 has single pair
			if(h.numPairs()==1) {
				// both player has single pair
				// find player1 and player2 pair card value
				int player1PairCardValue = 0;
				int player2PairCardValue = 0;
				for(i=4; i>0; i--) {
					if(hand[i].getValue()==hand[i-1].getValue()) {
						player1PairCardValue = hand[i].getValue();
						break;
					}
				}
				
				for(i=4; i>0; i--) {
					if(h.hand[i].getValue()==h.hand[i-1].getValue()) {
						player2PairCardValue = h.hand[i].getValue();
						break;
					}
				}
				if(player1PairCardValue != player2PairCardValue) {
					// player1 and player2 single pair card value is not the same
					if(player1PairCardValue == 1) {
						// player1 has single pair of aces
						return 1;
					}
					if(player2PairCardValue == 1) {
						// player2 has single pair of aces
						return -1;
					}
					if(player1PairCardValue >  player2PairCardValue) {
						// player1 has higher value pair and wins
						return 1;
					}
					else {
						// player2 has higher value pair and wins
						return -1;
					
					}
				}
				else { 
					// single pair card value is the same
					// check for ace
					if(hand[0].getValue() != h.hand[0].getValue()) {
						if(hand[0].getValue() == 1) {
							// player1 has an ace and wins
							return 1;
						}
						if(h.hand[0].getValue() == 1) {
							// player2 has an ace and wins
							return -1;
						}
					}
					// find highest card of each player.
					for(i=4; i>0; i--) {
						if(hand[i].getValue() > h.hand[i].getValue()) {
							//player1 has card of higher value and wins
							return 1;
						}
						if(hand[i].getValue() < h.hand[i].getValue()) {
							//player2 has card of higher value and wins
							return -1;
						}
					}
					// both have the same hand
					return 0;
				}
			}
			else {
				// only player1 has a single pair and wins
				return 1;
			}
			
		}
		if(h.numPairs()==1) {
			// only player2 has a single pair and wins
			return -1;
		}
	
		
		// HIGHEST CARD
		if(hand[0].getValue() == 1 && h.hand[0].getValue() != 1) {
			// player1 has an ace and wins
			return 1;
		}
		
		if(h.hand[0].getValue() == 1 && hand[0].getValue() != 1) {
			// player2 has an ace and wins
			return -1;
		}
		for(i=4; i>0; i--) {
			if(hand[i].getValue() > h.hand[i].getValue()) {
				//player1 has card of higher value and wins
				return 1;
			}
			if(hand[i].getValue() < h.hand[i].getValue()) {
				//player2 has card of higher value and wins
				return -1;
			}
		}
		// both have the same hand
		return 0;
	}
}
