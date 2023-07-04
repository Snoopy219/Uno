class Card{
	/*Numbered cards are the same
  1 for red, 2 for blue, 3 for green, and 4 for yellow
	10 is skip, 11 is reverse, 12 is draw, 100000 is wild and 200000 is wild draw*/
	int number;
	int color;

	public Card(int num, int col){
		this.number = num;
		this.color = col;
	}

	public int getNum(){
		return this.number;
	}

	public int getColor(){
		return this.color;
	}

	public String toString(){
		String str = "";
		if(color==1){
				str+= "\nRed ";
				str+= cardType(this.number);
			}else if(color==2){
				str+= "\nBlue ";
				str+= cardType(this.number);
			}else if(color==3){
				str+= "\nGreen ";
				str+= cardType(this.number);
			}else if(color==4){
				str+= "\nYellow ";
				str+= cardType(this.number);
			}else if(color==5){
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
}