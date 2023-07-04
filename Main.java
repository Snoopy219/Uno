class Main {
  public static void main(String[] args) {
    System.out.println("Welcome to UNO!");
		System.out.println("This is a four player game.");
		System.out.println("Here are your cards: ");
		Deck cards = new Deck();
		System.out.println(cards.toString());
		
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