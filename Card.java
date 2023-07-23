public class Card {
    private String term, def;
    private int familiarity = 0;

    public Card(String word, String definiton) {
        term = word;
        def = definiton;
    }

    public String getTerm() {
        return term;
    }

    public String getDef() {
        return def;
    }

    public int getFamiliarity() {
        return familiarity;
    }

    public void incFamiliarity() {
        familiarity++;
    }

    public void decFamiliarity() {
        familiarity--;
    }

    public boolean isMastered() {
        return familiarity >= 5;
    }
}
