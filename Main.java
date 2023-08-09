import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represents the main flashcard game where players are presented with
 * spell cards containing terms pulled from a flashcard set. Players need to choose the correct
 * spell card to match a randomly generated enemy definition. The goal is to increase the
 * familiarity level of each spell card to 5 by choosing the correct card.
 */
public class Main {
    static Scanner s = new Scanner(System.in);

    /**
     * The main method that starts the flashcard game.
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("""
                                                                                     
 _____ _         _      _____           _    _____     _   _         
 |   __| |___ ___| |_   |     |___ ___ _| |  |   __|___| |_| |___ ___ 
 |   __| | .'|_ -|   |  |   --| .'|  _| . |  |   __| .'| . | | -_|_ -|
 |__|  |_|__,|___|_|_|  |_____|__,|_| |___|  |__|  |__,|___|_|___|___|
                                                                      
                """);
        System.out.print("Would you like to read the rules (y/n): ");
        if (s.nextLine().equals("y")) {
            printRules();
        }
        Player p1 = new Player();
        playCmdGame(p1);
    }

    /**
     * This method plays the command-line version of the flashcard game with the given player.
     * @param player The player object representing the game player
     */
    public static void playCmdGame(Player player) {
        Random r = new Random();
        ArrayList<Card> spell_cards = new ArrayList<Card>(5);
        ArrayList<Card> card_pool = player.getCards();
        String enemy;


        while (true) {
            if (player.numCards() == 0) {
                break;
            }
            else if (player.numCards() < 5) {
                spell_cards = card_pool;
            } else {
                for (int i = 0; i < 5; i++) {
                    Card curr_card = card_pool.get(r.nextInt(card_pool.size()));
                    spell_cards.add(i, curr_card);
                    card_pool.remove(curr_card);
                }
            }
            enemy = spell_cards.get(r.nextInt(spell_cards.size())).getDef();
    
            System.out.println("SPELL CARDS:\n\n");
            int i = 1;
            for (Card c : spell_cards) {
                System.out.printf("%d: %s\n", i, c.getTerm());
                i++;
            }
            System.out.printf("\nENEMY:\n%s\n\n", enemy);
    
            System.out.print("Enter your spell card number: ");

            int answer = 0;

            try {
                answer = Integer.parseInt(s.nextLine()) - 1;
            } catch (Exception e) {
                System.err.println("""
                    \nError: 
                    Input must be of integer type
                        """);
                continue;
            }

            if (answer < 0 || answer >= spell_cards.size()) {
                System.err.println("""
                    \nError: 
                    You must choose between the valid spell cards on screen
                        """);
                continue;
            }
    
            if (spell_cards.get(answer).getDef().equals(enemy)) {
                System.out.printf("\nYou chose correctly! +1 famililarity to %s", spell_cards.get(answer).getTerm());
                if (player.getCards().get(answer).incFamiliarity()) {
                    player.removeCard(spell_cards.get(answer));
                }
            } else {
                System.out.printf("\nYou chose incorrectly... -1 famililarity to %s", spell_cards.get(answer).getTerm());
                player.getCards().get(answer).decFamiliarity();
            }
            System.out.println("\n");
        }
        System.out.println("Congratulations! You have mastered all the terms!");
    }

    /**
     * This method prints the rules of the flashcard game.
     */
    public static void printRules() {
        System.out.println("""
Rules:
        To start the game, you must enter the path file for the txt file containing your flashcards

        Each flashcard should be on a single line with the term and definition being one tab apart

        Each round you are given a set of five or less spell cards. These cards contain a term
        pulled from the flashcard set. You will need to defeat an enemy, which is marked with a random
        definition, by choosing the spell card that matches the definiton. 

        Each time the correct spell card is chosen, the \'famililarity\' level of that card goes up by 1,
        and down by 1 for each time you get it wrong.

        The goal of the game is to get a familiarity level of 5 for each card.
                """);
    }
}
