import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("""
                                                                                     
 _____ _         _      _____           _    _____     _   _         
 |   __| |___ ___| |_   |     |___ ___ _| |  |   __|___| |_| |___ ___ 
 |   __| | .'|_ -|   |  |   --| .'|  _| . |  |   __| .'| . | | -_|_ -|
 |__|  |_|__,|___|_|_|  |_____|__,|_| |___|  |__|  |__,|___|_|___|___|
                                                                      
                """);
        System.out.print("Would you like to read the rules (y/n): ");
        if (s.nextLine().equals("y")) {
            print_rules();
        }
        Player p1 = new Player();
        play_cmd_game(p1);
    }

    public static void play_cmd_game(Player player) {
        Random r = new Random();
        ArrayList<Card> spell_cards = new ArrayList<Card>(5);
        ArrayList<Card> card_pool = player.get_cards();
        String enemy;


        while (true) {
            if (player.num_cards() == 0) {
                break;
            }
            else if (player.num_cards() < 5) {
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
                if (player.get_cards().get(answer).incFamiliarity()) {
                    player.remove_card(spell_cards.get(answer));
                }
            } else {
                System.out.printf("\nYou chose incorrectly... -1 famililarity to %s", spell_cards.get(answer).getTerm());
                player.get_cards().get(answer).decFamiliarity();
            }
            System.out.println("\n");
        }
        System.out.println("Congratulations! You have mastered all the terms!");
    }

    public static void print_rules() {
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
