public class Card {
    private String term, def;
    private int familiarity = 0;

    /**
     * Constructor for the Card class.
     * Creates a new Card object with the specified term and definition.
     *
     * @param word The term or word on the card.
     * @param definition The definition of the term on the card.
     */
    public Card(String word, String definiton) {
        term = word;
        def = definiton;
    }

    /**
     * Get the term or word on the card.
     *
     * @return The term on the card.
     */
    public String getTerm() {
        return term;
    }

    /**
     * Get the definition of the term on the card.
     *
     * @return The definition of the term on the card.
     */
    public String getDef() {
        return def;
    }

    /**
     * Get the familiarity level of the card.
     * The familiarity level indicates how well the user knows the term.
     * It increases with repeated correct responses and decreases with incorrect responses.
     *
     * @return The familiarity level of the card.
     */
    public int getFamiliarity() {
        return familiarity;
    }

    /**
     * Increment the familiarity level of the card.
     * This method is called when the user correctly matches a term to its definition.
     * 
     * If familiarity is 5, then a true boolean will be sent. This is used to remove
     * the card from the deck.
     */
    public Boolean incFamiliarity() {
        familiarity++;
        if (familiarity == 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decrement the familiarity level of the card.
     * This method is called when the user incorrectly matches a term to its definition.
     */
    public void decFamiliarity() {
        if (familiarity > 0) {
            familiarity--;
        }
    }

    /**
     * Check if the card is mastered.
     * A card is considered mastered when its familiarity level is equal to or greater than 5.
     * @return true if the card is mastered, false otherwise.
     */
    public boolean isMastered() {
        return familiarity >= 5;
    }

    /**
     * Override the toString method to provide a custom string representation of the Card object.
     *
     * @return A string representation of the card in the format: "term: definition".
     */
    @Override
    public String toString() {
        return this.term + ": " + this.def + "\n " + this.familiarity;
    }
}
