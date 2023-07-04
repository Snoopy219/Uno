import java.util.Scanner;
class Game{
	static Scanner scan = new Scanner(System.in);
	static Deck deck;
	static Player comp1;
	static Player comp2;
	static Player comp3;
	static Player me;
	static int round;
	static int currentCard;
	
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
		while((comp1.getNumCards()!=0)&&(comp2.getNumCards()!=0)&&(comp3.getNumCards()!=0)&&(me.getNumCards()!=0)){
			playRound();
		}
	}

	public static void playRound(){
		System.out.println("\n\nRound: " + round);
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

	public static void playTurn(int player){
		int[] results = new int[2];
		if(player == 0){
			results = comp1.playCard(currentCard);
			currentCard = results[0];
			if(results[1]!=0){
				System.out.println("Computer " + (player+1) + " drew " + results[1] + " cards.");
			}
		}else if(player == 1){
			results = comp2.playCard(currentCard);
			currentCard = results[0];
			if(results[1]!=0){
				System.out.println("Computer " + (player+1) + " drew " + results[1] + " cards.");
			}
		}else if(player == 2){
			results = comp3.playCard(currentCard);
			currentCard = results[0];
			if(results[1]!=0){
				System.out.println("Computer " + (player+1) + " drew " + results[1] + " cards.");
			}
		}else{
			int choice = 0;
			while(choice == 0){
				System.out.println("Please type the number of the card you would like to play or 0 to draw.");
				System.out.println("Your cards:");
				System.out.print(me.getCardString());
				choice = scan.nextInt();
				currentCard = me.humanPlay(currentCard, choice-1);
				if(choice == 0){
					System.out.println("You drew 1 card.");
				}
			}
			System.out.println("You played " + Deck.cardToString(currentCard));
		}
	}
}