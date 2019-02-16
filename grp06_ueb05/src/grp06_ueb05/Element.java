package grp06_ueb05;

import java.util.Objects;

/**
 * a list element containing a card and a reference to the next. More
 * package-private methods may be added!
 *
 * @author Gerit
 */
public class Element {

    /**
     * card
     */
    private final Card card;
    /**
     * reference to the next element
     */
    private Element next;

    /**
     * creates an element with this card
     *
     * @param card to be contained
     */
    public Element(Card card) {
        assert card != null : "card for creating Element is null";
        this.card = card;
        next = null;
    }

    private Element() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * gets this card
     *
     * @return
     */
    Card getCard() {
        return card;
    }

    /**
     * gets the reference to the next element
     *
     * @return
     */
    Element getNext() {
        return next;
    }

    /**
     * appends an element.
     *
     * @param card card to append
     * @return the list with the appended card
     */
    Element appendElement(Card card) {
        if (this.getNext() == null) {
            this.next = new Element(card);
        } else {
            this.next = this.next.appendElement(card);
        }
        return this;
    }

    /**
     * inserts an element with the given card in front of the element with a
     * card of a lower ordinality. Highest card(ordinality) leads the list.
     *
     * @param card the card to insert
     * @return the list with the element inserted
     */
    Element insertElement(Card card) {

        if (this.next == null && (card.compareTo(this.card) < 0)) {
            Element cardElement = new Element(card);
            this.next = cardElement;
            return this;
        } else if ((card.compareTo(this.card) > 0)
                || (card.compareTo(this.card) == 0)) {

            Element cardElement = new Element(card);
            cardElement.next = this;
            return cardElement;

        } else if (card.compareTo(this.card) < 0) {
            this.next = this.next.insertElement(card);
            return this;
        } else {
            return this;
        }

    }

    /**
     * gets a string representing this and the next elements, separating the
     * values by comma and blank (", ") and giving each cardname a width of 12
     *
     * @return a string representing this and the next elements
     */
    @Override
    public String toString() {

        if (this.next == null) {
            return String.format("%12s", this.card);
        } else {

            return (String.format("%12s", this.card) + ", " + this.next.toString());
        }

    }
    
    /**
     * liefert einen vergleich von zwei decks
     *
     * @param other das zu vergleichende deck
     * @return true, wenn es gleich ist
     */
    boolean ourEquals(Element other) {

        if (((this.next == null) && (other.next == null))
                && (this.card.compareTo(other.card) == 0)) {
            return true;
        } else if ((this.card.compareTo(other.card) == 0)
                && ((this.next != null) && (other.next != null))) {
            return this.next.ourEquals(other.next);
        } else {
            return false;
        }
    }

    /**
     * the list is equal, if the cards are the same and the length is equal
     *
     * @param obj the other list to compare with
     * @return true, if the lengths are equal and the cards are same of this and
     * the next elements
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Element)) {
            return false;
        } else {
            Element meinElement = (Element) obj;
            return this.ourEquals(meinElement);
        }

    }

    /**
     * entfernt ein Element aus der Liste 
     *
     * @param index gibt das zu enterfende Element an
     * @return die Liste ohne das Element
     */
    Element deleteAt(int index) {
        if (index == 0) {
            return this.next;

        } else {
            this.next = this.next.deleteAt(--index);
            return this;
        }
    }

    /**
     * nimmt eine Gewünschte Karte aus einem Deck
     *
     * @param index gibt den Platz des Elementes an
     * @return das gewünschte Element
     */
    Card pickCardAt(int index) {

        if (index == 0) {
            return this.card;
        } else {
           Element pickCard = new Element(this.next.pickCardAt(--index));

            return pickCard.card;
        }

    }
    
    /**
     * zählt die Anzahl der Karten
     *
     * @return die Anzahl der Karten
     */
    int cardsCount() {
        if (this.next == null) {
            return 1;
        } else {
            return (1 + this.next.cardsCount());
        }
    }

    /**
     * zeigt die gewünschte Karte der Liste
     *
     * @param index gibt an welche Karte
     * @return die Karte
     */
    Card getCardAt(int index) {

        if (index == 0) {
            return this.card;
        } else if (index != 0 && this.next != null) {
            return this.next.getCardAt(--index);
        } else {
            return null;
        }
    }

    /**
     * vergleicht einen Wert mit dem Wert der aktuellen karte
     *
     * @param value der zu vergleichende Wert
     * @return true, wenn die Werte gelch sind 
     */
    boolean sameValue(int value) {

        return (value == this.card.getValue());

    }

    /**
     * gibt an wie oft der Wert der Karte vorhanden ist
     *
     * @param card liefert den zu überprüfenden Wert
     * @return liefert die Häufigkeit des Wertes
     */
    int countOfSameValue(Card card) {
        int zaehler = 0;

        for (int index = 0; index < this.cardsCount(); index++) {
            if (this.getCardAt(index).hasSameValue(card)) {
                zaehler += 1;
            }
        }
        return zaehler;
    }

//<editor-fold defaultstate="collapsed" desc="Testroutinen">
    /**
     * create list with elements of the given cards.
     *
     * @param cards array of cards to fill the list
     * @return list elements of the given cards
     */
    private static Element createTestList(Card[] cards) {
        if (cards == null || cards.length == 0) {
            return null;
        } else {
            Element head = new Element(cards[0]);
            Element list = head;
            for (int i = 1; i < cards.length; i++) {
                list.next = new Element(cards[i]);
                list = list.next;
            }
            return head;
        }
    }

    private static void testInsertAndToString() {
        Element list = new Element(Card.KARO_ACHT);

        list = list.insertElement(Card.PIK_ASS);
        assert list.toString().equals("     PIK_ASS,    KARO_ACHT") : list.toString();

        list = list.insertElement(Card.HERZ_ZWEI);
        assert list.toString().equals("     PIK_ASS,    KARO_ACHT,    HERZ_ZWEI") : list.toString();

        list = list.insertElement(Card.HERZ_DREI);
        assert list.toString().equals("     PIK_ASS,    KARO_ACHT,    HERZ_DREI,    HERZ_ZWEI") : list.toString();
    }

    private static void testEquals() {
        Element listNull = null;
        Element listP8 = createTestList(new Card[]{Card.PIK_ACHT});
        Element listP8copy = createTestList(new Card[]{Card.PIK_ACHT});
        Element listP8H9 = createTestList(new Card[]{Card.PIK_ACHT, Card.HERZ_NEUN});
        Element listP8H9K4 = createTestList(new Card[]{Card.PIK_ACHT, Card.HERZ_NEUN, Card.KARO_VIER});
        Element listP4H9K4 = createTestList(new Card[]{Card.PIK_VIER, Card.HERZ_NEUN, Card.KARO_VIER});

        assert listP8.equals(listP8) : "Liste = selbe Liste: " + listP8 + " eq " + listP8;
        assert listP8.equals(listP8copy) : "Liste = gleiche Liste: " + listP8 + " eq " + listP8copy;
        assert !listP8.equals(listNull) : "Liste sollte ungleich null sein: " + listP8 + " !eq null";
        assert !listP8.equals(listP8H9) : "zweite Liste länger: " + listP8 + " !eq " + listP8H9;
        assert !listP8H9.equals(listP8) : "erste Liste länger: " + listP8H9 + " !eq " + listP8;

        assert listP8H9K4.equals(listP8H9K4) : "selbe Liste: " + listP8H9K4 + " eq " + listP8H9K4;
        assert !listP8H9.equals(listP8H9K4) : "zweite Liste länger: " + listP8 + " !eq " + listP8H9;
        assert !listP8H9K4.equals(listP8H9) : "erste Liste länger: " + listP8H9 + " !eq " + listP8;

        assert !listP8H9K4.equals(listP4H9K4) : "erste Karte unterschiedlich: " + listP8H9K4 + " !eq " + listP4H9K4;
    }

    /**
     * tests for the list of elements
     *
     * @param args
     */
    public static void main(String[] args) {
        testInsertAndToString();
        testEquals();

        assert false : "*** Ende (Assertions sind an) ***";
    }

}
