public class Player {

	private Hand hand;
	private double balance;
	public Player(double balance){
		this.balance = balance;
		hand = new Hand();
	}


	public void deal(Card c){
		hand.addCard(c);
	}


	public Card[] discard(){
		System.out.println("This is AI's hand:");
		hand.printHand();
		int numDiscard = (int)Math.random()*6;
		System.out.println("AI would like to discard "+numDiscard+" many cards");
		Card[] discardedCards = new Card[numDiscard];
		int nIndex = 0;
		for(int i = 0; i < numDiscard; i++) {
			nIndex =(int) Math.random()*6;
			System.out.println("The index of the card AI would like to discard is " +nIndex);
			discardedCards[i] = hand.getCard(nIndex);
			hand.removeCard(nIndex);
		}
		return discardedCards;	
	}




	public double wager(double min){

		if ( balance < min) {
			return -1.0;
		}
		System.out.println("AI would like to wager ");
		double  wagerAmount  = (int)Math.random()*balance;
		while(wagerAmount < min) {
			System.out.println("The minimum AI can bet is " + min);
			System.out.println("AI would like to wager ");
			wagerAmount  = (int)Math.random()*balance;
		}
		balance -= wagerAmount; 
		return wagerAmount;
	}



	public Hand showHand(){

		return hand;
	}



	public double getBalance(){

		return balance;
	}


	public void winnings(double amount){
		balance += amount;
		hand.clear();

	}
}


