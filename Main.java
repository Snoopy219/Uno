import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to UNO!");
		System.out.println("This is a four player game.");
		Scanner scan = new Scanner(System.in);
		System.out.println("Please type \'play\' to begin.");
		String response = scan.nextLine();
		if(response.equals("play")){
			Game game = new Game();
			game.play();
		}
		
  }

	public static void wait(int ms)
	{
    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
	}
}