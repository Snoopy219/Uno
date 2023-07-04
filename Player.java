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
			for(int i = 0; i < cards.size(); i++){
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard)/100) || ((currentCard%100)==(cards.get(i)%100))){
				playableCards.add(i);
			}
		}
		}
		
		int cardLoc = (int)(Math.random()*playableCards.size());
		int card = cards.get(playableCards.get(cardLoc));
		cards.remove(cardLoc);
		if(cardType(card%100).equals("Reverse"))action=1;
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
			if((Math.floor(cards.get(i)/100)==Math.floor(currentCard/100)) || ((currentCard%100)==(cards.get(i)%100))){
				playableCards.add(i);
			}
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
			System.out.println(card);
			System.out.println(cardType(card%100));
			if(cardType(card%100).equals("Reverse")){
				System.out.println("Reverse called");
				results[2]=1;
			}
			return results;
		}
	}

	public void addCard(){
		cards.add(drawCard());
	}
}