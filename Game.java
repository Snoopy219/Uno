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
	static int currentPlay;
	static boolean skip;
	static boolean draw2;
	static boolean draw4;
	
	public Game(){
		deck = new Deck();
		deck.shuffle();
		me = new Player();
		comp1 = new Player();
		comp2 = new Player();
		comp3 = new Player();
		round = 1;
		currentPlay = -1;
		forward = true;
		skip = false;
		draw2 = false;
		draw4 = false;
	}

	public void play(){
		System.out.println("\n\nThe game will now start. The deck has been shuffled. The first card is: ");
		currentCard = deck.drawCard();
		while(!Deck.cardType(currentCard).equals("NA")){
			currentCard = deck.drawCard();
		}
		System.out.println(deck.cardToString(currentCard));
		System.out.println("The first round will now start.");
		while((comp1.getNumCards()!=0)&&(comp2.getNumCards()!=0)&&(comp3.getNumCards()!=0)&&(me.getNumCards()!=0)){
			playRound();
		}
		if(currentPlay != 3){
			System.out.println("Game Over. Computer " + (currentPlay+1) + " won");
		}else{
			System.out.println("Game Over. You won!");
		}
	}

	public static void playRound(){
		System.out.println("\n\nRound: " + round);
		System.out.println("Computer 1 has " + comp1.getNumCards() + " cards.");
		System.out.println("Computer 2 has " + comp2.getNumCards() + " cards.");
		System.out.println("Computer 3 has " + comp3.getNumCards() + " cards.");
		System.out.println("You have " + me.getNumCards() + " cards.\n");

		for(int i = 0; i < 4; i++){
			if(!((comp1.getNumCards()!=0)&&(comp2.getNumCards()!=0)&&(comp3.getNumCards()!=0)&&(me.getNumCards()!=0))){
				break;
			}
			//System.out.println(forward + " " + currentPlay);
			if(forward){
				currentPlay++;
				if(currentPlay >= 4)currentPlay = 0;
				if(currentPlay < 0)currentPlay = 0;
			}else{
				currentPlay--;
				if(currentPlay < 0)currentPlay = 4;
				if(currentPlay >= 4)currentPlay = 3;
			}
			//System.out.println(forward + " " + currentPlay);
			if(skip){
				skip = false;
				/*System.out.println(draw2);
				System.out.println(draw4);*/
				if(draw2){
					for(int j = 0; j<2; j++){
						if(i == 0){
							comp1.addCard();
						}else if(i == 1){
							comp2.addCard();
						}else if(i == 2){
							comp3.addCard();
						}else{
							me.addCard();
						}
					}
					if(currentPlay != 3){
						System.out.println("Computer " + (currentPlay+1) + " drew two cards.");
					}else{
						System.out.println("You drew two cards.");
					}
				}else if(draw4){
					for(int j = 0; j < 4; j++){
						if(i == 0){
							comp1.addCard();
						}else if(i == 1){
							comp2.addCard();
						}else if(i == 2){
							comp3.addCard();
						}else{
							me.addCard();
						}
					}
					if(currentPlay != 3){
						System.out.println("Computer " + (currentPlay+1) + " drew four cards.");
					}else{
						System.out.println("You drew four cards.");
					}
				}
				draw2 = false;
				draw4 = false;
			}else{
				playTurn(currentPlay);
				if((currentPlay != 3) && (currentCard%100 != 20)){
					System.out.println("Computer " + (currentPlay+1) + " played " + Deck.cardToString(currentCard));
				}else if((currentPlay != 3) && (currentCard%100 == 20)){
					String color = Deck.cardToString(currentCard);
					int loc = color.indexOf(" ");
					color = color.substring(0, loc);
					if(draw4){
						System.out.println("Computer " + (currentPlay+1) + " played Wild Draw 4 making it " + color);
					}else{
						System.out.println("Computer " + (currentPlay+1) + " played Wild making it " + color);
					}
				}
			}
		}
		round++;
	}

	public static void playTurn(int player){
		int[] results = new int[3];
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
				results = me.humanPlay(currentCard, choice-1);
				currentCard = results[0];
				if(choice == 0){
					System.out.println("You drew 1 card.");
				}
			}
			if(results[1]!=0)System.out.println("You played " + Deck.cardToString(currentCard));
		}
		checkAction(results);
	}

	public static void checkAction(int[] results1){
		if(results1[2] == 1){
			forward = !forward;
			System.out.println("reverse");
		}
		if((results1[2]==2)||(results1[2]>100)||(results1[2]==7))skip=true;
		if((results1[2]==3)||((results1[2]-100)==3)){
				currentCard = 220;
			}else if((results1[2]==4)||((results1[2]-100)==4)){
				currentCard = 120;
			}else if((results1[2]==5)||((results1[2]-100)==5)){
				currentCard = 320;
			}else if((results1[2]==6)||((results1[2]-100)==6)){
				currentCard = 420;
			}
		if(results1[2]==7){
			draw2 = true;
		}else if(results1[2]>100){
			draw4 = true;
		}
	}
	public static void setCurrentCard(int num){
		currentCard = num;
	}
}