package grp06_ueb05;

/**
 * Play Five Card Draw Poker without betting.
 *
 * @author Gerit
 */
public class Grp06_ueb05 {

    /**
     * Gives us the Number of the winning Player
     *
     * @param playerOne is a Player
     * @param playerTwo is a Player
     * @param playerThree is a Player
     * @param playerFour is a Player
     *
     * @return the result of the Pokergame
     */
    static int result(PokerHand playerOne, PokerHand playerTwo, PokerHand playerThree, PokerHand playerFour) {

        if (playerOne.isSuperiorThan(playerTwo)
                && playerOne.isSuperiorThan(playerThree)
                && playerOne.isSuperiorThan(playerFour)) {
            return 1;
        } else if (playerTwo.isSuperiorThan(playerOne)
                && playerTwo.isSuperiorThan(playerThree)
                && playerTwo.isSuperiorThan(playerFour)) {
            return 2;
        } else if (playerThree.isSuperiorThan(playerTwo)
                && playerThree.isSuperiorThan(playerOne)
                && playerThree.isSuperiorThan(playerFour)) {
            return 3;
        } else {
            return 4;
        }
    }

    /**
     * The Output for the Pokergame. Marks the Player who has won
     *
     *
     *
     *
     */
    static void winnerAndOutput() {
        Pack ourDeck = new Pack();
        ourDeck = ourDeck.createFreshDeck();

        // deal cards
        PokerHand playerOne = new PokerHand(ourDeck.pickRandomCards(5));
        PokerHand playerTwo = new PokerHand(ourDeck.pickRandomCards(5));
        PokerHand playerThree = new PokerHand(ourDeck.pickRandomCards(5));
        PokerHand playerFour = new PokerHand(ourDeck.pickRandomCards(5));

        int result = (result(playerOne, playerTwo, playerThree, playerFour));
        System.out.printf("1: %64s Rank: %10s " + (result == 1 ? " ***Gewinner***" : "") + "%n", playerOne.toString(), playerOne.getRank());
        System.out.printf("2: %64s Rank: %10s " + (result == 2 ? " ***Gewinner***" : "") + "%n", playerTwo.toString(), playerTwo.getRank());
        System.out.printf("3: %64s Rank: %10s " + (result == 3 ? " ***Gewinner***" : "") + "%n", playerThree.toString(), playerThree.getRank());
        System.out.printf("4: %64s Rank: %10s " + (result == 4 ? " ***Gewinner***" : "") + "%n", playerFour.toString(), playerFour.getRank());

        if (result > 4 || result < 1) {
            System.out.println(" Es gitb keinen Gewinner ");
        }

    }

    /**
     * Play Five Card Draw Poker without betting. Each of the 4 players gets 5
     * cards. The hands are analysed and the winner ist shown.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        winnerAndOutput();

    }

}
