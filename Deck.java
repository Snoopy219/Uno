import java.util.*;

class Deck{
	/*Numbered cards are the same
  1 for red, 2 for blue, 3 for green, and 4 for yellow
	10 is skip, 11 is reverse, 12 is draw, 100000 is wild and 200000 is wild draw
 */
	
	List<Card> deck = new ArrayList<Card>();
	public Deck(){
		for(int i = 0; i < 10; i++){
			for(int j = 1; j<5; j++){
				deck.add(new Card(i,j));
			}
		}
		for(int i = 1; i < 13; i++){
			for(int j = 1; j<5; j++){
				deck.add(new Card(i,j));
			}
			if(i>9){
				for(int j = 0; j < 2; j++){
					for(int k = 1; k<5; j++){
					deck.add(new Card(i,k));
					}
				}
			}
		}
		for(int i=0; i<4; i++){
			deck.add(new Card(0, 5));
			deck.add(new Card(0, 6));
		}
	}

	public String toString(){
		String str = "";
		for(int i = 0; i < deck.size(); i++){
			str+= deck.get(i).toString();
		}
		return str;
	}

	public void shuffle(){
		Random rand = new Random();
		for(int i = 0; i < deck.size(); i++){
			
		}
	}
}