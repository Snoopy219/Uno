import java.util.Scanner;
class Game{
	Scanner scan = new Scanner(System.in);
	Deck deck;
	Player comp1;
	Player comp2;
	Player comp3;
	Player me;
	int round;
	boolean gameOver = false;
	int currentCard;
	
	public Game(){
		deck = new Deck();
		deck.shuffle();
		me = new Player();
		comp1 = new Player();
		comp2 = new Player();
		comp3 = new Player();
		round = 1;
	}

	public void play(){
		System.out.println("The game will now start. The deck has been shuffled. The first card is: ");
		currentCard = deck.drawCard();
		System.out.println(deck.cardToString(currentCard));
		System.out.println("The first round will now start.");
		while(!gameOver){
			playRound();
		}
	}

	public void playRound(){
		System.out.println("Round: " + round);
		System.out.println("Computer 1 has " + comp1.getNumCards() + " cards.");
		System.out.println("Computer 2 has " + comp2.getNumCards() + " cards.");
		System.out.println("Computer 3 has " + comp3.getNumCards() + " cards.");
		System.out.println("You have " + me.getNumCards() + " cards.");
		for(int i = 0; i < 4; i++){
			playTurn(i);
			if(i != 3) System.out.println("Computer " + (i+1) + " played " + Deck.cardToString(currentCard));
		}
		round++;
	}

	public void playTurn(int player){
		if(player == 0){
			currentCard = comp1.playCard(currentCard);
		}else if(player == 1){
			currentCard = comp2.playCard(currentCard);
		}else if(player == 2){
			currentCard = comp3.playCard(currentCard);
		}else{
			System.out.println("Please type the number of the card you would like to play.");
			System.out.println("Your cards:");
			System.out.print(me.getCardString());
			int choice = scan.nextInt();
			currentCard = me.humanPlay(currentCard, choice-1);
			System.out.println("You played " + currentCard);
		}
	}
}