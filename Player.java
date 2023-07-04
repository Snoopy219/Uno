import java.util.*;
class Player extends Deck{
	List<Integer> cards=new ArrayList<Integer>();

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

	public int[] playCard(int currentCard){
		List<Integer> playableCards = new ArrayList<Integer>();
		int counter = 0;
		for(int i = 0; i < cards.size(); i++){
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard/100)) || ((currentCard%100)==(cards.get(i)%100))){
				playableCards.add(i);
			}
		}
		while(playableCards.size()==0){
			addCard();
			counter++;
			for(int i = 0; i < cards.size(); i++){
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard)/100) || ((currentCard%100)==(cards.get(i)%100))){
				playableCards.add(i);
			}
		}
		}
		
		int cardLoc = (int)(Math.random()*playableCards.size());
		int card = cards.get(playableCards.get(cardLoc));
		cards.remove(cardLoc);
		System.out.println(cards.size());
		int[] arr = new int[]{card, counter};
		return arr;
	}
		

	public int humanPlay(int currentCard, int choice){
		List<Integer> playableCards = new ArrayList<Integer>();
		if(choice == -1){
			addCard();
			return currentCard; 
		}
		for(int i = 0; i < cards.size(); i++){
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard/100)) || ((currentCard%100)==(cards.get(i)%100))){
				playableCards.add(i);
			}
		}
		if(playableCards.indexOf(choice) == -1){
			System.out.println(choice);
			System.out.println("That card is not playable. Try again.");
			Game.playTurn(3);
			return currentCard;
		}else{
			int card = cards.get(choice);
			cards.remove(choice);
			return card;
		}
	}

	public void addCard(){
		cards.add(drawCard());
	}
}