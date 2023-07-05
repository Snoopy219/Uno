import java.util.*;
import java.lang.Math;

class Deck{
	/*Numbered cards are the same
  1 for red, 2 for blue, 3 for green, and 4 for yellow
	10 is skip, 11 is reverse, 12 is draw, 100000 is wild and 200000 is wild draw
 */

	//find this
	static List<Integer> deck = new ArrayList<Integer>();
	public Deck(){
		for(int i = 0; i < 10; i++){
			for(int j = 1; j<5; j++){
				deck.add(j*100+i);
			}
		}
		for(int i = 1; i < 10; i++){
			for(int j = 1; j<5; j++){
				deck.add(j*100+i);
			}
		}
		for(int i = 10; i < 13; i++){
				for(int k = 1; k<5; k++){
				deck.add(k*100+i);
				}
		}
		for(int i = 10; i < 13; i++){
				for(int k = 1; k<5; k++){
				deck.add(k*100+i);
				}
		}
		for(int i=0; i<4; i++){
			deck.add(1000);
			deck.add(2000);
		}
	}

	public String toString(){
		String str = "";
		for(int i = 0; i < deck.size(); i++){
			str+= cardToString(deck.get(i));
		}
		return str;
	}

	public static String cardToString(int num){
		String str = "";
		if(num<200){
				str+= "Red ";
				str+= cardType(num-100);
			}else if(num<300){
				str+= "Blue ";
				str+= cardType(num-200);
			}else if(num<400){
				str+= "Green ";
				str+= cardType(num-300);
			}else if(num<500){
				str+= "Yellow ";
				str+= cardType(num-400);
			}else if(num==1000){
				str+= "Wild";
			}else{
				str+= "Wild Draw 4";
		}
		return str;
	}

		public static String cardType(int rawNum){
		if(rawNum<10){
			return Integer.toString(rawNum);
		}else if(rawNum == 10){
			return "Skip";
		}else if(rawNum == 11){
			return "Reverse";
		}else if(rawNum == 12){
			return "Draw 2";
		}else{
			return "NA";
		}
	}

	public void shuffle(){
		int oldLoc;
		int newLoc;
		int old;
		for(int i = 0; i < deck.size()/4; i++){
			oldLoc = (int)(Math.random()*deck.size());
			newLoc = (int)(Math.random()*deck.size());
			old = deck.get(oldLoc);
			deck.set(oldLoc, deck.get(newLoc));
			deck.set(newLoc, old);
		}
	}

	public int drawCard(){
		if(deck.size()==0){
			resetCards();
			System.out.println("Reshuffling...");
		}
		int card = deck.get(0);
		deck.remove(0);
		return card;
	}

	public void resetCards(){
		for(int i = 0; i < 10; i++){
			for(int j = 1; j<5; j++){
				deck.add(j*100+i);
			}
		}
		for(int i = 1; i < 10; i++){
			for(int j = 1; j<5; j++){
				deck.add(j*100+i);
			}
		}
		for(int i = 10; i < 13; i++){
				for(int k = 1; k<5; k++){
				deck.add(k*100+i);
				}
		}
		for(int i = 10; i < 13; i++){
				for(int k = 1; k<5; k++){
				deck.add(k*100+i);
				}
		}
		for(int i=0; i<4; i++){
			deck.add(1000);
			deck.add(2000);
		}
	}
}