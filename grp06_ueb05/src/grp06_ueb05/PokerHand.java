package grp06_ueb05;

import static grp06_ueb05.Rank.*;
import static grp06_ueb05.Card.*;

/**
 * contains five Cards.
 * This corresponds to the cards that are helt in hand in a draw-poker-game.
 *
 * @author Gerit
 */
public class PokerHand extends Pack {

    /**
     * a pack containing all rankCards of this hand
     */
    private Pack rankCards;
    /**
     * a pack containing all kickerCards of this hand
     */
    private Pack kickerCards;

    /**
     * gets a pack of rankCards
     * @return the rankCards
     */
    private Pack getRankCards() {
        return rankCards;
    }

    /**
     * gets a pack of kickerCards
     * @return the kickerCards
     */
    private Pack getKickerCards() {
        return kickerCards;
    }

    /**
     * the rank of this hand
     */
    private Rank rank;

    /**
     * creates a pokerhand with the given cards
     * @param cards to add to the hand
     */
    public PokerHand(Card[] cards) {
        super(cards);
        assert 5 == cards.length;                                               
        analyzeCards();
        assert 5 == rankCards.getNoOfCards() + kickerCards.getNoOfCards() 
               : "die Karten sollten auf rankCards und kickerCards aufgeteilt werden";
    }

    /**
     * checks whether the hand has the given rank
     * @param rank to check
     * @return true, if this hand has the given rank
     */
    public boolean hasRankOf(Rank rank) {
        return rank == getRank();
    }

    /**
     * gets the rank of this hand
     * @return the rank of this hand
     */
    public Rank getRank() {
        return rank;
    }

    /**
     * analyzes the cards of the hand and sets the attributes rankCards,
     * kickerCards and rank
     */
    private void analyzeCards() {
        Rank thisRank = null;
        Pack thisRankCards = new Pack();
        /*
         Alle Karten sind zunächst wertlos und gehören somit zu den kickerCards.
         Diese werden im Folgenden untersucht.
         */
        Pack thisKickerCards = this.copy();   
        
        /*
         Zuerst werden die Karten gleicher Werte gesucht, also 
         Vierling, Drilling, Paar und die Kombinationen zwei Paare und FullHouse
         */
        Card cardWithMaxSameValues = thisKickerCards.getCardWithMaxSameValues();
        int maxSameValues = thisKickerCards.getMaxNoOfSameValues();

        if (maxSameValues == 4) {                           //vier gleiche Karten
            thisRank = FOUR_OF_A_KIND;
            thisRankCards = thisKickerCards.pickCardsWithSameValue(cardWithMaxSameValues);
        } else if (maxSameValues == 3) {                   //drei gleiche Karten
            thisRank = THREE_OF_A_KIND;
            thisRankCards = thisKickerCards.pickCardsWithSameValue(cardWithMaxSameValues);
            cardWithMaxSameValues = thisKickerCards.getCardWithMaxSameValues();
            maxSameValues = thisKickerCards.getMaxNoOfSameValues();
        }
        /*
         habe ich ein Paar gefunden, so kann dies 
         das einzige Paar unter wertlosen Karten sein oder
         das zweite zu einem bereits gefundenen Paar sein oder
         nach einem gefundenen Drilling ein FullHouse bilden
         */

        while (maxSameValues == 2) {                  // zwei gleiche Karten
            if (thisRank == null) {                   // zum ersten Mal gefunden
                thisRank = ONE_PAIR;
            } else if (thisRank == ONE_PAIR) {        // zum zweiten Mal gefunden
                thisRank = TWO_PAIR;
            } else if (thisRank == THREE_OF_A_KIND) { // bereits Dreier gefunden
                thisRank = FULL_HOUSE;
            }
            Pack sameValuesHand = thisKickerCards.pickCardsWithSameValue(cardWithMaxSameValues);
            thisRankCards.addCards(sameValuesHand); 
            cardWithMaxSameValues = thisKickerCards.getCardWithMaxSameValues();
            maxSameValues = thisKickerCards.getMaxNoOfSameValues();
        }

        /*
         habe ich nicht einmal zwei gleichwertige Karten, suche ich nach
         Straßen
         */
        if (thisRank == null && maxSameValues == 1) {
            /*
             .. muss ich prüfen, ob ich eine Straße habe
             oder nur eine simple HIGH_CARD vorliegt
             */
            Card firstCard = thisKickerCards.getCard(0);
            if (thisKickerCards.getNoOfCards() == thisKickerCards.countOfSameSuit(firstCard)) {
                // FLUSH = Kartensequenz mit Lücken aus einer Farbe (Bsp: KB,K9,K7,K4,K3)
                thisRank = FLUSH;
                thisRankCards = thisKickerCards;
                thisKickerCards = new Pack();
            } else {
                /*
                 nicht implementiert:
                 // STRAIGHT = Kartensequenz ohne Lücken, Farbe egal (Bsp: K10,H9,K8,P7,H6)
                 // STRAIGHT_FLUSH = direkt aufeinanderfolgende Kartensequenz aus einer Farbe (Bsp: HK,HD,HB,H10,H9)
                 */

                // HIGH_CARD = ohne Zusammenhang (Bsp: KK,KD,P7,H4,P3) 
                thisRank = HIGH_CARD;
                thisRankCards.addCard(thisKickerCards.pickHighestCard());
            }
        }
        this.kickerCards = thisKickerCards;
        this.rankCards = thisRankCards;
        this.rank = thisRank;
    }
    
    /**
     * checks if this hand is superior than the other
     * @param other  the ohter hand
     * @return true, if this hand is superior (or the other is null); 
     *         false otherwise
     */
    public boolean isSuperiorThan(PokerHand other) {
        // dieser Rang ist höher als nichts
        if (other == null) {
            return true;
        }
        // Vergleich der Ränge (ONE_PAIR < FULL_HOUSE)
        int compare;
        compare = this.rank.compareTo(other.rank);
        
        // wenn Rang gleich ist, Rangkarten vergleichen (PIK_ZEHN, HERZ_ZEHN > PIK_NEUN, KARO_NEUN)
        if (compare == 0){
            // Dieser Vergleich ist nicht ganz korrekt für FULL_HOUSE, da 
            // hierfür eigentlich zunächst die Drillinge verglichen werden 
            // müssen, hier aber nur die höchsten Rangkarten verglichen werden 
            // (das kann auch das Paar sein).
            compare = this.rankCards.compareTo(other.rankCards);
        }
        
        // wenn Rangkarten gleich sind, Kickerkarten vergleichen (PIK_ZEHN > PIK_NEUN)
        // bei FULL_HOUSE gibt es keine Kickerkarten
        if (compare == 0 && kickerCards != null) {
            compare = this.kickerCards.compareTo(other.kickerCards);
        }
        
        // wenn auch die gleich sind, wird doch die Farbe der höchsten Rangkarte zu Rate gezogen
        if (compare == 0) {
            compare = this.rankCards.getCard(0).getSuit().compareTo(other.rankCards.getCard(0).getSuit());
        }
        return compare > 0;
    }
    

//<editor-fold defaultstate="collapsed" desc="tests">
    
    /**
     * test-output is given for every test if this is true;
     * test-output is printed just for failed tests if this is false
     */
    final static boolean SHOW_SUCCESSFULL_TEST = true;
    
    /**
     * prints the testResult and compares to expected rank and rankCards
     * @param expRank      the expected rank
     * @param cards        cards to hold in the hand
     * @param expRankCards cards expected in the rankCards
     */
    private static void testHand(Rank expRank, Card[] cards, Card[] expRankCards) {
        PokerHand hand = new PokerHand(cards);
        Pack expRankHand = new Pack(expRankCards);
        if (SHOW_SUCCESSFULL_TEST || expRank != hand.rank
            || !expRankHand.equals(hand.rankCards)) {
            System.out.println(hand.rank
                               +(expRank == hand.rank
                                 ? ""
                                 : " *** expected: " + expRank + " ***"));
            System.out.printf("Hand  : %s %n"
                    + "Rank  : %s %s%n"
                    + "Kicker: %s %n%n",
                    hand, hand.getRankCards(),
                    expRankHand.equals(hand.getRankCards())
                    ? ""
                    : " *** expected : " + expRankHand + " ***",
                    hand.getKickerCards());
        }
    }
    
    /**
     * tests the analysis of the cards
     */
    public static void testAnalyze() {
        testHand(HIGH_CARD,
                new Card[]{HERZ_ASS, PIK_KOENIG, KREUZ_SIEBEN, KARO_FUENF,
                    KREUZ_ZWEI},
                new Card[]{HERZ_ASS});
        
        testHand(ONE_PAIR,
                new Card[]{HERZ_KOENIG, PIK_KOENIG, KREUZ_SIEBEN, KARO_FUENF,
                    KREUZ_ZWEI},
                new Card[]{HERZ_KOENIG, PIK_KOENIG});
        
        testHand(ONE_PAIR,
                new Card[]{HERZ_ASS, PIK_KOENIG, KREUZ_FUENF, KARO_FUENF,
                    KREUZ_ZWEI},
                new Card[]{KREUZ_FUENF, KARO_FUENF});
        
        testHand(TWO_PAIR,
                new Card[]{HERZ_KOENIG, PIK_KOENIG, KREUZ_FUENF, KARO_FUENF,
                    KREUZ_ZWEI},
                new Card[]{HERZ_KOENIG, PIK_KOENIG, KREUZ_FUENF, KARO_FUENF});
        
        testHand(THREE_OF_A_KIND,
                new Card[]{HERZ_ASS, PIK_KOENIG, PIK_NEUN, KARO_NEUN, KREUZ_NEUN},
                new Card[]{PIK_NEUN, KARO_NEUN, KREUZ_NEUN});
        
        testHand(FOUR_OF_A_KIND,
                new Card[]{HERZ_ASS, PIK_ASS, KREUZ_ASS, KARO_ASS, KREUZ_DAME},
                new Card[]{HERZ_ASS, PIK_ASS, KREUZ_ASS, KARO_ASS});
        
        testHand(FULL_HOUSE,
                new Card[]{HERZ_ASS, PIK_ASS, KARO_ZWEI, PIK_ZWEI, KREUZ_ZWEI},
                new Card[]{HERZ_ASS, PIK_ASS, KARO_ZWEI, PIK_ZWEI, KREUZ_ZWEI});
        
        testHand(FLUSH,
                new Card[]{PIK_ASS, PIK_KOENIG, PIK_ZEHN, PIK_ACHT, PIK_ZWEI},
                new Card[]{PIK_ASS, PIK_KOENIG, PIK_ZEHN, PIK_ACHT, PIK_ZWEI});
    }
    
    /**
     * Ausgabe für den Test von isSuperiorThan
     * @param cardsOne        Karten des ersten Spielers
     * @param cardsOther      Karten des zweiten Spielers
     * @param expWinnerIsOne  erwarteter Sieger
     */
    private static void testCompare(String testName,
            Card[] cardsOne, Card[] cardsOther, boolean expWinnerIsOne) {
        PokerHand hOne   = new PokerHand(cardsOne);
        PokerHand hOther = new PokerHand(cardsOther);
        boolean oneWins = hOne.isSuperiorThan(hOther);
        if (SHOW_SUCCESSFULL_TEST || oneWins != expWinnerIsOne) {
            System.out.println(testName + (oneWins == expWinnerIsOne ? "" : " - failed ****") );
            System.out.println("1: " + hOne.toString()   + " Rang: " + hOne.rank);
            System.out.println("2: " + hOther.toString() + " Rang: " + hOther.rank);
            System.out.println("");
        }
    }
    
    /**
     * testet die Methode isSuperiorThan()
     */
    private static void testIsSuperiorThan() {
        testCompare( "HighCard HerzKönig bei 1 gewinnt vor Herz10",
                new Card[]{HERZ_KOENIG, PIK_BUBE,
                    KREUZ_SIEBEN, KARO_FUENF,KREUZ_ZWEI},
                new Card[]{HERZ_ZEHN, PIK_SECHS,
                    KREUZ_FUENF, KARO_DREI, KREUZ_ZWEI},
                true);
        testCompare( "OnePair bei 2 gewinnt vor HerzKönig",
                new Card[]{HERZ_KOENIG, PIK_BUBE,
                    KREUZ_SIEBEN, KARO_FUENF,KREUZ_ZWEI},
                new Card[]{HERZ_ZEHN, PIK_SECHS,
                    KREUZ_FUENF, KARO_FUENF, KREUZ_ZWEI},
                false);
        testCompare( "OnePair, Könige bei 1 gewinnen vor fünfen",
                new Card[]{HERZ_KOENIG, PIK_KOENIG,
                    KREUZ_SIEBEN, KARO_FUENF,KREUZ_ZWEI},
                new Card[]{HERZ_ASS, PIK_KOENIG,
                    KREUZ_FUENF, KARO_FUENF, KREUZ_ZWEI},
                true);
        testCompare( "Flush bei 1 gewinnt vor Drilling",
                new Card[]{PIK_KOENIG, PIK_DAME,
                    PIK_SIEBEN, PIK_FUENF,PIK_ZWEI},
                new Card[]{HERZ_ASS, PIK_ASS, KREUZ_ASS,
                    KARO_FUENF, KREUZ_ZWEI},
                true);
    }
    
    
    /**
     * tests the method of this class
     * @param args
     */
    public static void main(String[] args) {
        testAnalyze();
        testIsSuperiorThan();
    }
    
//</editor-fold>

}
