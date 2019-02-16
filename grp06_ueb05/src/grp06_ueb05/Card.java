package grp06_ueb05;

import static grp06_ueb05.Suit.*;

/**
 * cards of the 4 suits from two to ace, lowest value and suit first
 *
 * @author Gerit
 */
public enum Card {

    KREUZ_ZWEI(KREUZ, 2), KARO_ZWEI(KARO, 2), HERZ_ZWEI(HERZ, 2), PIK_ZWEI(PIK, 2),
    KREUZ_DREI(KREUZ, 3), KARO_DREI(KARO, 3), HERZ_DREI(HERZ, 3), PIK_DREI(PIK, 3),
    KREUZ_VIER(KREUZ, 4), KARO_VIER(KARO, 4), HERZ_VIER(HERZ, 4), PIK_VIER(PIK, 4),
    KREUZ_FUENF(KREUZ, 5), KARO_FUENF(KARO, 5), HERZ_FUENF(HERZ, 5), PIK_FUENF(PIK, 5),
    KREUZ_SECHS(KREUZ, 6), KARO_SECHS(KARO, 6), HERZ_SECHS(HERZ, 6), PIK_SECHS(PIK, 6),
    KREUZ_SIEBEN(KREUZ, 7), KARO_SIEBEN(KARO, 7), HERZ_SIEBEN(HERZ, 7), PIK_SIEBEN(PIK, 7),
    KREUZ_ACHT(KREUZ, 8), KARO_ACHT(KARO, 8), HERZ_ACHT(HERZ, 8), PIK_ACHT(PIK, 8),
    KREUZ_NEUN(KREUZ, 9), KARO_NEUN(KARO, 9), HERZ_NEUN(HERZ, 9), PIK_NEUN(PIK, 9),
    KREUZ_ZEHN(KREUZ, 10), KARO_ZEHN(KARO, 10), HERZ_ZEHN(HERZ, 10), PIK_ZEHN(PIK, 10),
    KREUZ_BUBE(KREUZ, 11), KARO_BUBE(KARO, 11), HERZ_BUBE(HERZ, 11), PIK_BUBE(PIK, 11),
    KREUZ_DAME(KREUZ, 12), KARO_DAME(KARO, 12), HERZ_DAME(HERZ, 12), PIK_DAME(PIK, 12),
    KREUZ_KOENIG(KREUZ, 13), KARO_KOENIG(KARO, 13), HERZ_KOENIG(HERZ, 13), PIK_KOENIG(PIK, 13),
    KREUZ_ASS(KREUZ, 14), KARO_ASS(KARO, 14), HERZ_ASS(HERZ, 14), PIK_ASS(PIK, 14);

    /**
     * the suit of the card
     */
    private final Suit suit;
    /**
     * the value of the card
     */
    private final int value;

    /**
     * construct a card with a value and a suit
     *
     * @param suit suit of the card
     * @param value value of the card
     */
    Card(Suit suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * gets the suit of the card
     *
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * gets the value of the card
     *
     * @return the value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * checks whether the other card has the same suit
     *
     * @param other card to compare with
     * @return true, if the other card has the same suit, false otherwise
     */
    public boolean hasSameSuit(Card other) {
        return this.suit == other.suit;

    }

    /**
     * checks whether the other card has the same value
     *
     * @param other card to compare with
     * @return true, if the other card has the same value, false otherwise
     */
    public boolean hasSameValue(Card other) {
        return this.value == other.value;

    }

    /**
     * compares the cards by value. The cards are sorted by value and then by
     * suit. The suit is not relevant for this comparison.
     *
     * @param other card to compare with
     * @return -1, if this value is lower than the others value 0, if this value
     * is the same as the others value +1, if this value is higher than the
     * others value
     */
    public int compareCardByValue(Card other) {
        int valueBetween;

        valueBetween = (this.value - other.value);
        
        if (valueBetween == 0){
         return 0;   
        }
        else if (valueBetween < 0){
            return -1;
        }
        else {
            return 1;
        }       

    }
   
}
