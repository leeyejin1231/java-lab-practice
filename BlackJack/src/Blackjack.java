import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		int seed = Integer.parseInt(args[0]);
		int playerNum = Integer.parseInt(args[1]);
		Scanner s = new Scanner(System.in);
		String cmd;
		int hc = 1, p1c = 1;
		int[] pc = new int[5];
		pc[0] = 1;
		pc[1] = 1;
		pc[2] = 1;
		pc[3] = 1;
		boolean hbust = false;
		boolean bust = false;
		boolean stand = false;
		
		if(playerNum > 5)
			System.out.println("error");
				
		Deck deck = new Deck();
		deck.shuffle(seed);
		
		Computer[] player = new Computer[playerNum-1];
		House house = new House();
		Player player1 = new Player();
		for(int i = 0; i < playerNum-1; i++) {
			player[i] = new Computer();
		}
		
		
		
		player1.addCard(deck.dealCard());
		for(int i = 0; i < playerNum-1; i++) {
			player[i].addCard(deck.dealCard());
		}
		house.addCard(deck.dealCard());
		player1.addCard(deck.dealCard());
		for(int i = 0; i < playerNum-1; i++) {
			player[i].addCard(deck.dealCard());
		}
		house.addCard(deck.dealCard());

		
		System.out.println("House: HIDDEN, " + house.getCard(1));
		System.out.println("Player1: " + player1.getCard(0) + ", " + player1.getCard(1) + " (" + player1.Sum() + ")");
		
		for(int i = 0; i < playerNum-1; i++) {
			System.out.println("Player" + (i+2) + ": " + player[i].getCard(0) + ", " + player[i].getCard(1) + " (" + player[i].Sum() + ")");
		}
		
		System.out.println("");
		
		//--------------------------------------------------------------------------------
		
		if(house.Sum() != 21) {
		
			System.out.println("--- Player1 turn ---");
			System.out.println("Player1: " + player1.getCard(0) + ", " + player1.getCard(1) + " (" + player1.Sum() + ")");
			cmd = s.next();
			
			
			while(cmd.equals("Hit")) {
				player1.addCard(deck.dealCard());
				p1c++;
				System.out.print("Player1: ");
				for(int i=0; i <= p1c; i++) {
					System.out.print(player1.getCard(i));
					if(i<p1c)
						System.out.print(", ");
				}
				if(player1.Sum() > 21) {
					System.out.println(" (" + player1.Sum() + ") - Bust!");
					bust = true;
					break;
				} else
					System.out.println(" (" + player1.Sum() + ")");
				cmd = s.next();
			}
			if(bust == false) {
			System.out.print("Player1: ");
			for(int i=0; i <= p1c; i++) {
				System.out.print(player1.getCard(i));
				if(i<p1c)
					System.out.print(", ");
			}
			System.out.println(" (" + player1.Sum() + ")");
			}
			
			System.out.println("");
	//------------------------------------------------------------------------		
			
			for(int i = 0; i < playerNum-1; i++) {
				System.out.println("--- Player" + (i+2) + " turn ---");
				System.out.println("Player" + (i+2) + ": " + player[i].getCard(0) + ", " + player[i].getCard(1) + " (" + player[i].Sum() + ")");
				int sum = player[i].Sum();
				
				while(sum<=17) {
					if(sum < 14 ) {
						System.out.println("Hit");
						player[i].addCard(deck.dealCard());
						pc[i]++;
						System.out.print("Player" + (i+2)+ ": ");
						for(int j=0; j <= pc[i]; j++) {
							System.out.print(player[i].getCard(j));
							if(j<pc[i])
								System.out.print(", ");
						}
						if(player[i].Sum() > 21) {
							System.out.println(" (" + player[i].Sum() + ") - Bust!");
							stand = true;
							break;
						} else
							System.out.println(" (" + player[i].Sum() + ")");
					}else {
						Random random = new Random();
						int is_hit = 0;
						is_hit = (int)(random.nextInt(2));
						if(is_hit == 1) {
							System.out.println("Hit");
							player[i].addCard(deck.dealCard());
							pc[i]++;
							System.out.print("Player" + (i+2)+ ": ");
							for(int j=0; j <= pc[i]; j++) {
								System.out.print(player[i].getCard(j));
								if(j<pc[i])
									System.out.print(", ");
							}
							if(player[i].Sum() > 21) {
								System.out.println(" (" + player[i].Sum() + ") - Bust!");
								stand = true;
								break;
							} else
								System.out.println(" (" + player[i].Sum() + ")");
						}else {   
							System.out.println("Stand");
							System.out.print("Player" + (i+2)+ ": ");
							for(int j=0; j <= pc[i]; j++) {
								System.out.print(player[i].getCard(j));
								if(j<pc[i])
									System.out.print(", ");
							}
							System.out.println(" (" + player[i].Sum() + ")");
							stand = true;
							break;
						}
					}
					sum = player[i].Sum();
				}
				if(stand == false || sum > 21) {
					System.out.println("Stand");
					System.out.print("Player" + (i+2)+ ": ");
					for(int j=0; j <= pc[i]; j++) {
						System.out.print(player[i].getCard(j));
						if(j<pc[i])
							System.out.print(", ");
					}
					System.out.println(" (" + player[i].Sum() + ")");
				}
				System.out.println("");
			}
			System.out.println("");
			System.out.println("");
	//--------------------------------------------------------------------------------
			
			
			System.out.println("--- House turn ---");
			System.out.println("House: " + house.getCard(0) + ", " + house.getCard(1) + " (" + house.Sum() + ")");
			
			int sum = house.Sum();
			
			
			while(sum < 17) {
				System.out.println("Hit");
				house.addCard(deck.dealCard());
				hc++;
				System.out.print("House: ");
				for(int i=0; i <= hc; i++) {
					System.out.print(house.getCard(i));
					if(i<hc)
						System.out.print(", ");
				}
				if(house.Sum() > 21) {
					System.out.println(" (" + house.Sum() + ") - Bust!");
					hbust = true;
					break;
				} else
					System.out.println(" (" + house.Sum() + ")");
				sum = house.Sum();
			}
			if(hbust == false) {
				System.out.println("Stand");
				System.out.print("House: ");
				for(int i=0; i <= hc; i++) {
					System.out.print(house.getCard(i));
					if(i<hc)
						System.out.print(", ");
				}
				System.out.println(" (" + house.Sum() + ")");
			}
			System.out.println("");
		}
//----------------------------------------------------------------------		
		
		System.out.println("--- Game Results ---");
		System.out.print("House: ");
		for(int i=0; i<=hc; i++) {
			System.out.print(house.getCard(i));
			if(i<hc)
				System.out.print(", ");
		}
		if(hbust == true) 
			System.out.println(" (" + house.Sum() + ") - Bust!");

		else
			System.out.println(" (" + house.Sum() + ")");
		
		//--------------------------------------------------
		
		if(bust == true)
			System.out.print("[Lose] Player1: ");
		else if(hbust == true)
			System.out.print("[Win]  Player1: ");
		else if (house.Sum() < player1.Sum())
			System.out.print("[Win]  Player1: ");
		else if (house.Sum() == player1.Sum())
			System.out.print("[Draw] Player1: ");
		else 
			System.out.print("[Lose] Player1: ");
		
		for(int i=0; i<=p1c; i++) {
			System.out.print(player1.getCard(i));
			if(i<p1c)
				System.out.print(", ");
		}
		if(bust == true) 
			System.out.println(" (" + player1.Sum() + ") - Bust!");
		
		else
			System.out.println(" (" + player1.Sum() + ")");
		
		//--------------------------------------------------------
		
		for(int i=0; i<playerNum-1; i++) {
			if(player[i].Sum() > 21)
				System.out.print("[Lose] Player" + (i+2) + ": ");
			else if(hbust == true)
				System.out.print("[Win]  Player" + (i+2) + ": ");
			else if (house.Sum() < player[i].Sum())
				System.out.print("[Win]  Player" + (i+2) + ": ");
			else if (house.Sum() == player[i].Sum())
				System.out.print("[Draw] Player" + (i+2) +": ");
			else 
				System.out.print("[Lose] Player" + (i+2)+ ": ");
			
			for(int j=0; j<=pc[i]; j++) {
				System.out.print(player[i].getCard(j));
				if(j<pc[i])
					System.out.print(", ");
			}
			if(player[i].Sum() > 21) 
				System.out.println(" (" + player[i].Sum() + ") - Bust!");
			
			else
				System.out.println(" (" + player[i].Sum() + ")");
		}
	}
}



class Card{
	
	public int suit;
	public int value;

	
	public Card(int theValue, int theSuit) {
		value = theValue;
		suit = theSuit;
	}
		
	public int getsuit() {
		return suit;
	}
	
	public int getvalue() {
		return value;
	}
	
	public String getsuitString() {
		switch (suit) {
		case 0 : return "c";
		case 1 : return "h";
		case 2 : return "d";
		case 3 : return "s";
		default : return "error";
		}
	}
	
	public String getvalueString() {
		switch (value) {
		case 1 : return "A";
		case 2 : return "2";
		case 3 : return "3";
		case 4 : return "4";
		case 5 : return "5";
		case 6 : return "6";
		case 7 : return "7";
		case 8 : return "8";
		case 9 : return "9";
		case 10 : return "10";
		case 11 : return "J";
		case 12 : return "Q";
		case 13 : return "K";
		default : return "error"; 
		}
	}
	
	public String toString() {
		return getvalueString() + getsuitString();
	}
}

class Deck {

	private int cardsUsed;
	Card[] deck;
	public Deck() {
		
		int count = 0, i, j;
		deck = new Card[52];
		
		for(i=1; i<=13; i++) {
			for(j=0; j<4; j++) {
				deck[count++] = new Card(i, j);
			}
		}
		cardsUsed = 0;
	}
		
	public void shuffle(int seed) {
		Random random = new Random(seed);
		for(int i = deck.length-1; i>0; i--) {
			int rand = (int)(random.nextInt(i+1));
			Card temp = deck[i];
			deck[i] = deck[rand];
			deck[rand] = temp;
			
		}
		cardsUsed = 0;
	}
	
	public Card dealCard() {
		if(cardsUsed == deck.length)
			throw new IllegalStateException("No cards are left in the deck.");
		cardsUsed++;
		return deck[cardsUsed - 1];
	}
}

class Hand{
	
	public ArrayList<Card> hand;
	
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCard (Card c) {
		if(c != null) {
			hand.add(c);
		}
	}
	
	public Card getCard(int position) {
		if(position >= 0 && position < hand.size())
			return (Card)hand.get(position);
		else
			return null;
	}
	
	public int Sum() {
		int sum = 0;
		boolean ace = false;
		int cardCount = hand.size();
		
		for(int i=0; i < cardCount; i++) {
			Card card = getCard(i);
			int value = card.getvalue();
			if(value > 10) {
				value = 10;
			}else if (value == 1) {
				ace = true;
			}
			sum += value;
		}
		if(ace == true && sum + 10 <= 21) {
			sum += 10;
		}
		return sum;
	}
	
}  

class Computer extends Hand{
	Computer(){}
}  //Player automatically participates
class Player extends Hand{
	Player(){}
}  //Player you control
class House extends Hand{
	House(){}
}
