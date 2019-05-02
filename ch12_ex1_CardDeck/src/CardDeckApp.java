import java.util.ArrayList;

public class CardDeckApp {

    public static void main(String[] args) {
        System.out.println("DECK");
        ArrayList<String> deck = getDeck();
        displayCards(deck);

        System.out.println("SHUFFLED DECK");
        shuffleDeck(deck);
        displayCards(deck);

        int count = 2;
        System.out.println("HAND OF " + count + " CARDS");
        //String[] hand = dealCards(deck, count);
        ArrayList<String> hand = dealCards(deck, count);
        displayCards(hand);
    }

    private static ArrayList<String> getDeck() {
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", 
            "8", "9", "10", "Jack", "Queen", "King"};        

        ArrayList<String> deck = new ArrayList<String>(52);
        //String[] deck = new String[52];
        int i = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
            	deck.add(rank + " of " + suit);
                //deck[i] = rank + " of " + suit;
                i++;
            }
        }
        return deck;
    }

    private static void displayCards(ArrayList<String> cards) {
        System.out.print("|");        
        for (String card : cards) {
            System.out.print(card + "|");
        }
        System.out.println();        
    }

    private static void shuffleDeck(ArrayList<String> deck) {
        for (int i = 0; i < deck.size(); i++) {
            String savedCard = deck.get(i);
            int randomIndex = (int) (Math.random() * deck.size()-1);
            deck.set(i, deck.get(randomIndex));
            //deck[i] = deck[randomIndex];
            deck.set(randomIndex, savedCard);
            //deck[randomIndex] = savedCard;
        }
    }

    private static ArrayList<String> dealCards(ArrayList<String> deck, int count) {
    	ArrayList<String> hand = new ArrayList<String>(count);
    	for (int i = 0; i < count; i ++) {
    		hand.add(deck.get(count));
    		deck.remove(count);
    	}
        //String[] hand = Arrays.copyOfRange(deck, 0, count);
        return hand;
    }
}