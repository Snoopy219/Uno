import java.util.*;
import java.util.Scanner;
class Player extends Deck{
	List<Integer> cards=new ArrayList<Integer>();
	Scanner scan = new Scanner(System.in);

	public Player(){
		for(int i = 0; i < 7; i++){
			cards.add(drawCard());
		}
	}

	public int getNumCards(){
		return cards.size();
	}

	public String getCardString(){
		String str = "";
		for(int i = 0; i < cards.size(); i++){
			str+="\n " + (i+1) + ". " + cardToString(cards.get(i));
		}
		str+="\n";
		return str;
	}

	/*Last indeices are action cards. 1 = reverse*/
	public int[] playCard(int currentCard){
		List<Integer> playableCards = new ArrayList<Integer>();
		int counter = 0;
		int action = 0;
		for(int i = 0; i < cards.size(); i++){
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard/100)) || ((currentCard%100)==(cards.get(i)%100))){
				playableCards.add(i);
			}
		}
		while(playableCards.size()==0){
			addCard();
			counter++;
			if((Math.floor(cards.get(cards.size()-1)/100)==Math.floor(currentCard)/100) || ((currentCard%100)==(cards.get(cards.size()-1)%100))||(cards.get(cards.size()-1)==1000)||(cards.get(cards.size()-1)==2000)){
				playableCards.add(cards.size()-1);
			}
		}
		
		int cardLoc = (int)(Math.random()*playableCards.size());
		int card = cards.get(playableCards.get(cardLoc));
		int cardsLoc = playableCards.get(cardLoc);
		cards.remove(cardsLoc);
		if(cardType(card%100).equals("Reverse")){
			action=1;
		}else if(cardType(card%100).equals("Skip")){
				action=2;
		}else if(card == 1000){
			action = playWildComp();
		}else if(card == 2000){
			action = playWildComp()+100;
		}else if(cardType(card%100).equals("Draw 2")){
				action = 7;
		}
		int[] arr = new int[]{card, counter, action};
		return arr;
	}
		

	public int[] humanPlay(int currentCard, int choice){
		List<Integer> playableCards = new ArrayList<Integer>();
		int[] results = new int[3];
		if(choice == -1){
			addCard();
			results[0] = currentCard;
			results[1] = 1;
			results[2] = 0;
			return results;
		}
		for(int i = 0; i < cards.size(); i++){
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard/100)) || ((currentCard%100)==(cards.get(i)%100))||(cards.get(i)==1000)||(cards.get(i)==2000)){
				playableCards.add(i);
			}
			/*System.out.println(Math.floor(cards.get(i)/100));
			System.out.println(Math.floor(currentCard/100));*/
		}
		if(playableCards.indexOf(choice) == -1){
			System.out.println("That card is not playable. Try again.");
			Game.playTurn(3);
			results[0]= currentCard;
			results[1] = 0;
			results[2] = 0;
			return results;
		}else{
			int card = cards.get(choice);
			cards.remove(choice);
			results[0]= card;
			results[1] = 1;
			/*System.out.println(card);
			System.out.println(cardType(card%100));*/
			if(cardType(card%100).equals("Reverse")){
				results[2]=1;
			}else if(cardType(card%100).equals("Skip")){
				results[2]=2;
			}else if(card == 1000){
				results[2] = playWild();
			}else if(card == 2000){
				results[2] = playWild() + 100;
		}else if(cardType(card%100).equals("Draw 2")){
				results[2] = 7;
		}
			return results;
		}
	}

	public int playWild(){
		System.out.println("What color would you like? Type \'B\' for blue, \'R\' for red, \'G\' for green, or \'Y\' for yellow.");
		String response = scan.nextLine();
		if(response.equals("B")){
			return 3;
		}else if(response.equals("R")){
			return 4;
		}else if(response.equals("G")){
			return 5;
		}else{
			return 6;
		}
	}

	//this needs more strategy
	public int playWildComp(){
		int color = (int)(Math.random()*4);
		if(color==0){
			return 3;
		}else if(color==1){
			return 4;
		}else if(color==2){
			return 5;
		}else{
			return 6;
		}
	}

	public void addCard(){
		cards.add(drawCard());
	}
}