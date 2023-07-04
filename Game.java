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
	static boolean forward;
	static int lastPlay;
	static boolean skip;
	
	public Game(){
		deck = new Deck();
		deck.shuffle();
		me = new Player();
		comp1 = new Player();
		comp2 = new Player();
		comp3 = new Player();
		round = 1;
		lastPlay = -1;
		forward = true;
		skip = false;
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
		System.out.println("You have " + me.getNumCards() + " cards.\n");
		if(forward){
			for(int i = 0; i < 4; i++){
			if(forward){
				if(i==lastPlay)i++;
				if(skip){
					skip = false;
				}else{
				playTurn(i);
				lastPlay = i;
				if(i != 3) System.out.println("Computer " + (i+1) + " played " + Deck.cardToString(currentCard));}
			}else{
				for(int j = i; j>-1; j--){
					if(skip){
						skip = false;
					}else{
					playTurn(j);
					lastPlay = j;
					if(j != 3) System.out.println("Computer " + (j+1) + " played " + Deck.cardToString(currentCard));}
				}
			}
			}
		}else{
			for(int i=3; i>-1; i--){
				if(!forward){
					if(i==lastPlay)i--;
					if(skip){
						skip = false;
					}else{
					playTurn(i);
					lastPlay = i;
				if(i != 3) System.out.println("Computer " + (i+1) + " played " + Deck.cardToString(currentCard));}
				}else{
					for(int j = i; j<4; j++){
						if(skip){
							skip = false;
						}else{
					playTurn(j);
						lastPlay = j;
					if(j != 3) System.out.println("Computer " + (j+1) + " played " + Deck.cardToString(currentCard));}
				}
				}
			}
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
			if(results[2] == 1){forward = !forward;}
			if(results[2]==2)skip=true;
		}else if(player == 1){
			results = comp2.playCard(currentCard);
			currentCard = results[0];
			if(results[1]!=0){
				System.out.println("Computer " + (player+1) + " drew " + results[1] + " cards.");
			}
			if(results[2] == 1){forward = !forward;}
			if(results[2]==2)skip=true;
		}else if(player == 2){
			results = comp3.playCard(currentCard);
			currentCard = results[0];
			if(results[1]!=0){
				System.out.println("Computer " + (player+1) + " drew " + results[1] + " cards.");
			}
			if(results[2] == 1){forward = !forward;}
			if(results[2]==2)skip=true;
		}else{
			int choice = 0;
			int[] results1 = new int[3];
			while(choice == 0){
				System.out.println("Please type the number of the card you would like to play or 0 to draw.");
				System.out.println("Your cards:");
				System.out.print(me.getCardString());
				choice = scan.nextInt();
				results1 = me.humanPlay(currentCard, choice-1);
				currentCard = results1[0];
				if(choice == 0){
					System.out.println("You drew 1 card.");
				}
			}
			if(results1[1]!=0)System.out.println("You played " + Deck.cardToString(currentCard));
			if(results1[2] == 1)forward = !forward;
			if(results1[2]==2)skip=true;
		}
	}
}