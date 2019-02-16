package grp06_ueb05;

import java.util.Random;

/**
 * a pack contains several cards ordered by value (highest first). Used as a
 * deck (cards are face down) a card must be picked by pickRandomCard(), used as
 * a hand (faces are shown) a card can be picked by index.
 *
 * To <b>get</b> a card gets the card and leaves it in the pack, to <b>pick</b>
 * a card gets the card and removes it from the pack.
 *
 * A pack can be created empty or with cards given in an array. A full pack
 * (that contains all cards) can be created. Cards with same value or same suit
 * can be counted, gotten or picked. The pack can be compared with another pack
 * depending on values of the cards (not suit or ordinality). A copy of the pack
 * can be created. toString() shows the cards in square-brackets. equals() gives
 * true, if the compared packs hold the same cards.
 *
 * More public-methods may be added.
 *
 * @author Gerit
 */
public class Pack {

    /**
     * a list of cards
     */
    private Element myCards;

    /**
     * randomgenerator for dealing cards
     */
    private final Random randomGenerator = new Random();

    /**
     * creates a hand without cards
     */
    Pack() {
        this.myCards = null;
    }

    /**
     * creates a hand with the given cards
     *
     * @param cards cards to add
     */
    Pack(Card myArray[]) {

        for (Card myArray1 : myArray) {
            if (this.myCards != null) {
                this.myCards = this.myCards.insertElement(myArray1);
            } else {
                this.myCards = new Element(myArray1);
            }
        }
    }

    /**
     * creates a new pack full of all cards
     *
     * @return a new pack full of all cards
     */
    private static Pack getAllCards() {
        Pack newDeck = new Pack();

        Card ourEnum[] = Card.values();
        for (Card ourEnum1 : ourEnum) {
            newDeck.insertElement(ourEnum1);
        }
        return newDeck;
    }

    /**
     * Creating a new Pack with all cards using
     *
     * @return a random card of this pack
     */
    Pack createFreshDeck() {

        Pack theDeck = getAllCards();
        return theDeck;

    }

    /**
     * add one card in a sorted order
     *
     * @param card card to add
     */
    final public void addCard(Card card) {
        if (this.myCards != null) {
            this.myCards = this.myCards.insertElement(card);
        } else {
            this.myCards = new Element(card);
        }
    }

    /**
     * add the cards of the other pack to this pack
     *
     * @param other other pack
     */
    public void addCards(Pack other) {

        for (int index = 0; index < other.myCards.cardsCount(); index++) {
            this.addCard(other.myCards.getCardAt(index));
        }

    }

    /**
     * get the card at the index
     *
     * @param index the index of the card in the hand
     * @return the card at the index, null if the index is invalid
     */
    public Card getCard(int index) {

        if (index >= 0 && index < this.getNoOfCards()) {

            return this.myCards.getCardAt(index);

        } else {
            return null;
        }
    }

    /**
     * Deletes a Element at index
     *
     * @param index the index
     * @return the List without the deleted Element
     */
    Element deleteAt(int index) {

        return this.myCards = this.myCards.deleteAt(index);

    }

    /**
     * Picks a Card at index
     *
     * @param index the index
     * @return the Card that got picked
     */
    Card pickCardAt(int index) {
        Element outElement = new Element(this.myCards.pickCardAt(index));
        this.deleteAt(index);
        return outElement.getCard();

    }

    /**
     * picks the card with the highest order of the deck.
     *
     * @return the card with the highest order
     */
    public Card pickHighestCard() {
        Element pickHighest = new Element(this.pickCardAt(0));

        return pickHighest.getCard();

    }

    /**
     * picks a random card. Usually used if this is a pack to deal of.
     *
     * @return a random card of this pack
     */
    public Card pickRandomCard() {

        Element rdmCard = new Element(this.pickCardAt(this.randomGenerator.nextInt(getNoOfCards())));

        return rdmCard.getCard();
    }

    /**
     * picks a number of random cards. Usually used if this is a pack to deal
     * of. If there are less cards left than shall be picked, the rest of cards
     * are given.
     *
     * @param noOfCardsToPick number of cards to pick
     * @return an array of cards picked by random
     */
    public Card[] pickRandomCards(int noOfCardsToPick) {

        Card[] rdmArray = new Card[noOfCardsToPick];

        for (int index = 0; index < noOfCardsToPick; index++) {
            if (this.getCard(index) != null) {
                rdmArray[index] = this.pickRandomCard();
            }

        }
        return rdmArray;

    }

    /**
     * picks all cards of the pack with the same value as card
     *
     * @param card to compare with
     * @return a new pack with all cards of this deck having the same value as
     * card
     */
    public Pack pickCardsWithSameValue(Card card) {
        Pack sameValuePack = new Pack();

        for (int index = 0; index < this.myCards.cardsCount(); index++) {
            if (this.myCards.getCardAt(index).hasSameValue(card)) {
                if (sameValuePack.myCards == null) {
                    sameValuePack.myCards = new Element(this.pickCardAt(index));
                } else {
                    sameValuePack.insertElement(this.pickCardAt(index));
                }
            }
        }
        return sameValuePack;

    }

    /**
     * insert Element
     *
     * @param card it the Card to insert
     * @return returns the List with the new Card included
     */
    void insertElement(Card card) {
        if (this.myCards == null) {
            this.myCards = new Element(card);

        } else {
            this.myCards = this.myCards.insertElement(card);
        }
    }

    /**
     * gets the number of cards in the deck
     *
     * @return the number of cards in the deck
     */
    public int getNoOfCards() {
        if (this.myCards == null) {
            return 0;
        } else {
            return this.myCards.cardsCount();
        }
    }

    /**
     * counts the cards in the deck having the same suit as card
     *
     * @param card the card to compareTo with
     * @return the count of cards having the same suit as card
     */
    public int countOfSameSuit(Card card) {
        int counter = 0;

        for (int index = 0; index < this.myCards.cardsCount(); index++) {
            if (this.myCards.getCardAt(index).hasSameSuit(card)) {
                counter += 1;
            }
        }
        return counter;
    }

    /**
     * gets a card having the highest count of partners (cards with same value)
     * e.g. one of the cards of THREE_OF_A_KIND
     *
     * @return a card having most partners of the same value; null if the deck
     * is empty
     */
    public Card getCardWithMaxSameValues() {
        int theIndex = 0;
        boolean indexValid = false;
        int counter = 0;

        for (int index = 0; index < this.myCards.cardsCount(); index++) {
            if (this.myCards.countOfSameValue(this.myCards.getCardAt(index)) > counter) {
                counter = this.myCards.countOfSameValue(this.myCards.getCardAt(index));
                theIndex = index;
                indexValid = true;
            }

        }
        if (indexValid) {
            return this.getCard(theIndex);

        } else {
            return null;
        }
    }

    /**
     * gets the number of cards having the most partners (cards with the same
     * value)
     *
     * @return the number of cards having the most partners (cards with the same
     * value)
     */
    public int getMaxNoOfSameValues() {

        return this.myCards.countOfSameValue(this.getCardWithMaxSameValues());
    }

    /**
     * gets a copy of the pack
     *
     * @return a new pack holding the same cards
     */
    public Pack copy() {
        Pack packCopy = new Pack();

        for (int index = 0; index < this.myCards.cardsCount(); index++) {
            if (packCopy.myCards == null) {
                packCopy.myCards = new Element(this.getCard(index));
            } else {
                packCopy.insertElement(this.getCard(index));
            }
        }
        return packCopy;
    }

    /**
     * compares the packs card by card by value. The cards are sorted by value
     * and then by suit. The suit is NOT relevant for this comparison.
     *
     * @param other hand to compareTo with
     * @return -1, if these cards have lower values than the others 0, if these
     * cards have higher values than the others +1, if these cards have same
     * values than the others
     */
    public int compareTo(Pack other) {
        int compare = 0;
        for (int index = 0; (index < this.myCards.cardsCount()) && (index < other.myCards.cardsCount())
                && compare == 0; index++) {
            compare = this.getCard(index).compareCardByValue(other.getCard(index));
//                    != 0) {
//                compare = (this.getCard(index).compareCardByValue(other.getCard(index)));
//
//            }

        }
        if (compare != 0) {
            return compare;
        }
        if (this.myCards.cardsCount() == other.myCards.cardsCount()) {
            return 0;

        } else if (this.myCards.cardsCount() > other.myCards.cardsCount()) {
            return 1;

        } else {
            return -1;

        }
    }

    /**
     * a string representing this deck with all cards in box brackets
     *
     * @return a string representing this deck with all cards in box brackets
     */
    @Override
    public String toString() {

        String outPutString;

        outPutString = ("[ ");
        for (int index = 0; index < this.myCards.cardsCount(); index++) {
            outPutString += (this.getCard(index));
            outPutString += (" ");
        }
        outPutString += ("]");
        return outPutString;
    }

    /**
     * a pack is equal if it contains the same cards than the other pack, not
     * more or less.
     *
     * @param obj another pack
     * @return true, if the two packs hold the same cards
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pack)) {
            return false;
        } else {
            Pack meinPack = (Pack) obj;
            return meinPack.myCards.ourEquals(this.myCards);

        }

    }

    //<editor-fold defaultstate="collapsed" desc="Tests">
    private static void testAddCards() {
        Pack pack = new Pack();
        pack.addCard(Card.HERZ_ACHT);
        assert 1 == pack.getNoOfCards() : "Herz8 wurde nicht zugefügt";

        pack.addCard(Card.HERZ_ASS);
        assert 2 == pack.getNoOfCards() : "HerzAss wurde nicht zugefügt";

        pack.addCard(Card.HERZ_BUBE);
        assert 3 == pack.getNoOfCards() : "HerzBube wurde nicht zugefügt";
    }

    private static void testGetCard() {
        Pack pack = new Pack(new Card[]{Card.KARO_NEUN, Card.KARO_FUENF});
        assert 2 == pack.getNoOfCards() : "Konstruktor funktioniert nicht";

        Card card = pack.getCard(0);
        assert 2 == pack.getNoOfCards() : "get() soll das Pack nicht verändern.";
        assert Card.KARO_NEUN == card : "erste Karte sollte Karo9 sein.";

        card = pack.getCard(1);
        assert 2 == pack.getNoOfCards() : "get() soll das Pack nicht verändern.";
        assert Card.KARO_FUENF == card : "zweite Karte sollte Karo5 sein.";
    }

    private static void testPickingCards() {
        Pack pack;
        Card[] cards;
        pack = new Pack(new Card[]{Card.KARO_NEUN, Card.KARO_FUENF, Card.KREUZ_NEUN,
            Card.PIK_SECHS, Card.PIK_DAME});
        assert 5 == pack.getNoOfCards();
        Card card = pack.pickHighestCard();
        assert Card.PIK_DAME == card : "HighestCard sollte Pik-Dame sein";
        assert 4 == pack.getNoOfCards() : "pickHighestCard() hat Karte nicht gezogen";

        pack = new Pack(new Card[]{Card.KARO_NEUN, Card.KARO_FUENF, Card.KREUZ_NEUN,
            Card.PIK_SECHS, Card.PIK_DAME});
        card = pack.pickRandomCard();
        assert 4 == pack.getNoOfCards() : "pickRandomCard() hat Karte nicht gezogen";

        pack = new Pack(new Card[]{Card.KARO_NEUN, Card.KARO_FUENF, Card.KREUZ_NEUN,
            Card.PIK_SECHS, Card.PIK_DAME});
        cards = pack.pickRandomCards(3);
        assert 3 == cards.length : "pickRandomCards(3) hat nicht 3 Karten entfernt, sondern "
                + cards.length;
        assert 2 == pack.getNoOfCards() : "nach pack.pickRandomCards(3) darf pack nur noch 2 enthalten, "
                + "enthält aber " + pack.getNoOfCards();
    }

    /**
     * tests for the pack
     *
     * @param args
     */
    public static void main(String[] args) {

        testAddCards();
        testGetCard();
        testPickingCards();

        assert false : "*** Ende (Assertions sind an) ***";
    }
//</editor-fold>
}
