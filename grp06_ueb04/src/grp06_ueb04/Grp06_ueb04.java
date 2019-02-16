package grp06_ueb04;

import java.util.Arrays;

/**
 * @author Andre und Alex Gruppe 06
 */
public class Grp06_ueb04 {

    private static final String SPLIT_CHAR = "/";

    /**
     * wir lesen übergebene Parameter in die Mengen ein
     *
     * @param set1 ist unsere erste Menge vom Typ Set
     * @param set2 ist unsere zweite Menge vom Typ Set
     * @param args ist der Array mit den übergebenen Parametern
     */
    public static void getSetsfromArgs(Set set1, Set set2, String[] args) {
        Set currentSet = set1;

        for (String arg : args) {
            if (arg.equals(SPLIT_CHAR)) {
                currentSet = set2;
            } else {
                currentSet.addElement(Integer.valueOf(arg));
            }
        }

    }

    /**
     * wir Testen unsere Mengen und Funktionen und geben die Ergebnisse aus
     *
     * @param set1 ist unsere erste Menge vom Typ Set
     * @param set2 ist unsere zweite Menge vom Typ Set
     */
    public static void testClassSet(Set set1, Set set2) {

        System.out.println("Menge 1:          " + set1.showValues());
        System.out.println("Menge 2:          " + set2.showValues());
        System.out.println("Vereinigung:      " + (set1.union(set2)).showValues());
        System.out.println("Schnittmenge:     " + (set1.intersection(set2)).showValues());
        System.out.println("Differenzmenge:   " + (set1.diff(set2)).showValues());
        System.out.println("echte Teilmenge:  " + (set2.isProperSubSet(set1)));
    }

    /**
     * Das Hauptprogramm, wo die Funktionen von oben aufgerufen werden
     *
     * @param args Commandozeilenparameter
     */
    public static void main(String[] args) {

        Set mySet1 = new Set();
        Set mySet2 = new Set();

        getSetsfromArgs(mySet1, mySet2, args);
        System.out.println("Test Klasse Set");
        System.out.println("Argumente:        " + Arrays.toString(args));
        testClassSet(mySet1, mySet2);

    }

}
