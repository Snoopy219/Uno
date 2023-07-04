import java.util.*;
import java.lang.Math;

class Deck{
	/*Numbered cards are the same
  1 for red, 2 for blue, 3 for green, and 4 for yellow
	10 is skip, 11 is reverse, 12 is draw, 100000 is wild and 200000 is wild draw
 */

	//find this
	List<Integer> deck = new ArrayList<Integer>();
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

	public String cardToString(int num){
		String str = "";
		if(num<200){
				str+= "\nRed ";
				str+= cardType(num-100);
			}else if(num<300){
				str+= "\nBlue ";
				str+= cardType(num-200);
			}else if(num<400){
				str+= "\nGreen ";
				str+= cardType(num-300);
			}else if(num<500){
				str+= "\nYellow ";
				str+= cardType(num-400);
			}else if(num==1000){
				str+= "\nWild";
			}else{
				str+= "\nWild Draw 4";
		}
		return str;
	}

		private String cardType(int rawNum){
		if(rawNum<10){
			return Integer.toString(rawNum);
		}else if(rawNum == 10){
			return "Skip";
		}else if(rawNum == 11){
			return "Reverse";
		}else{
			return "Draw 2";
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
		int card = deck.get(0);
		deck.remove(0);
		return card;
	}
}