import java.util.Scanner;

class Main {
  public static void main(String[] args) {
		Game game = new Game();
    System.out.println("Welcome to UNO!");
		System.out.println("This is a four player game.");
		Scanner scan = new Scanner(System.in);
		System.out.println("Please type \'play\' to begin.");
		String response = scan.nextLine();
		if(response.equals("play")){
			game.play();
		}
		System.out.println("Would you like to play again?");
		response = scan.nextLine();
		while(response.equals("yes")){
			game = new Game();
			game.play();
			System.out.println("Would you like to play again? (type \'yes\' to play again or anything else to quit)");
			response = scan.nextLine();
		}
  }
}