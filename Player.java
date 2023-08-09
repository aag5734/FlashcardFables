import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private ArrayList<Card> cards;

    /**
     * Constructor for the Player class.
     * Prompts the user to enter the path of a text file containing card data,
     * and then initializes the cards ArrayList by calling makeCards method.
     */
    public Player () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter txt file path: ");
        cards = makeCards(scanner.nextLine());
        System.out.println("\n");
    }

    /**
     * Reads card data from a given text file and creates Card objects.
     *
     * @param filename The path of the text file containing card data.
     * @return An ArrayList of Card objects created from the data in the text file.
     */
    public ArrayList<Card> makeCards(String filename) {
        cards = new ArrayList<>();
        try {
            BufferedReader cardCSV = new BufferedReader(new FileReader(filename));
            String line = cardCSV.readLine();
            while (line != null) {
                String[] card_args = line.split("\t");
                this.cards.add(new Card(card_args[0], card_args[1]));
                line = cardCSV.readLine();
            }
            cardCSV.close();
        } catch (IOException e) {
            System.err.println("Path not found.");
            return this.cards;
        }
        return this.cards;
    }

    /**
     * Prints the details of all the cards in the player's cards ArrayList.
     */
    public void printCards() {
        for (Card c : this.cards) {
            System.out.println(c.toString());
        }
    }

    /**
     * Returns the number of cards remaining in the set
     * @return the number of cards within the deck
     */
    public int num_cards() {
        return this.cards.size();
    }

    /**
     * Returns the array list of cards
     * @return an array list containing all the current cards
     */
    public ArrayList<Card> get_cards() {
        return this.cards;
    }
    
    /**
     * Removes a given card from the player's cards
     * @param card the card to be removed
     */
    public void remove_card(Card card) {
        this.cards.remove(card);
    }
}
