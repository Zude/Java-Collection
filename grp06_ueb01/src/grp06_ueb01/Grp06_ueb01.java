/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp06_ueb01;

/**
 * wir p
 *
 * @author inf102691
 */
public class Grp06_ueb01 {

    /**
     * wir prüfen ob number eine Primzahl ist
     *
     * @param number ist der zu überprüfende parameter
     * @return, wenn = true, dann ist es eine primzahl
     */
    public static boolean istPrime(int number) {
        boolean prime = number > 1;
        for (int i = 2; i * i < number && prime; i++) {
            prime = number % i != 0;
        }
        return prime;
    }
    /**
     * wir suchen die nächst kleinere Primzahl neben der übergebenen Primzahl
     *
     * @param number ist der zu überprüfende parameter 
     * @return hier übergeben wir die kleinere Primzahl
     */
    public static int smallNumber(int number) {
        int kleinezahl = 0;
        for (int i = 2; i < number; i++) {
            if (istPrime(i) == true && i < number) {
                kleinezahl = i;
            }
        }
        return kleinezahl;
    }
    /**
     * wir suchen die nächst größere Primzahl neben der übergebenen Primzahl
     *
     * @param number ist der zu überprüfende parameter
     * @return, wenn = true, dann ist es eine primzahl
     */
    public static int biggerNumber(int number) {
        int großezahl = 0;
        for (int i = number; (großezahl == 0); i++) {
            if ((istPrime(i) == true) && (i > number)) {
                großezahl = i;
            }
        }
        return großezahl;
    }
    /**
     * wir prüfen ob number eine gute Primzahl ist
     *
     * @param number ist der zu überprüfende parameter
     * @return, wenn = true, dann ist es eine gute primzahl
     */
    public static boolean istGut(int number) {
        if ((number * number) > (smallNumber(number) * biggerNumber(number))) {
            return true;
        }
        return false;
    }
    /**
     * wir prüfen ob number eine Teufelszahl ist
     *
     * @param number ist der zu überprüfende parameter
     * @return, wenn = true, dann ist es eine Teufelszahl
     */
    public static boolean istTeufel(int number) {

        while (number != 0) {
            if ((number % 10 == 6) || (number % 10 == -6)) {
                number /= 10;
                if ((number % 10 == 6) || (number % 10 == -6)) {
                    number /= 10;
                    if ((number % 10 == 6) || (number % 10 == -6)) {
                        number /= 10;
                        return true;
                    }
                }
            }

            number /= 10;

        }

        return false;
    }
    /**
     * wir zählen die Anzahl der Ziffer 
     *
     * @param number ist der zu überprüfende parameter
     * @return count,  wir übergeben die Anzahl der Ziffern
     */
    public static int getCountofdigits(int number) {
        int count = 1;
        while (number > 9) {
            count++;
            number /= 10;
        }
        return count;
    }
    /**
     * Diese Methode ersetzt das "Hochrechnen" 
     *
     * @param number ist der zu überprüfende parameter
     * @param anzahl ist die anzahl wie oft number mit sich selbst multipliziert wird
     * @return number, gibt uns den "Hochgerechneten" Wert zurück
     */
    public static int hochRechnung(int number, int anzahl) {

        int zwischenwert = number;

        for (int i = 1; i < anzahl; i++) {
            number *= zwischenwert;
        }

        return number;

    }
    /**
     * wir prüfen ob number eine Narzisstische Zahl ist
     *
     * @param number ist der zu überprüfende parameter
     * @return, wenn = true, dann ist es eine Narzisstische Zahl
     */
    public static boolean istNarzi(int number) {
        int zahl;
        int zz;
        int endzahl = 0;

        zahl = getCountofdigits(number);

        zz = number;

        while (zz > 0) {
            endzahl += hochRechnung((zz % 10), zahl);
            zz /= 10;
        }

        if (number == endzahl) {
            return true;
        }
        return false;

    }

    /** In dem Hauptprogramm überprüfen wir Zahlen eines Zahlenbereichs auf 
     * verschiedene Eigenschaften. (Narzisstische Zahl, Teufelszahl oder gute Primzahl)
     * 
     * @param args the command line arguments
     * @var START ist der Anfang des Bereichs
     * @var END ist das Ende des Bereiches
     * @var MAXDIGITS ist die Anzahl der Ziffern der höchsten Zahl aus unserem Bereich
     * und wird zur Formatierung der auszugebenen Zahlen benutzt
     */
    public static void main(String[] args) {
        final int START = 100;
        final int END = 700;
        final int MAXDIGITS = getCountofdigits(END);                                 
        for (int i = START; i <= END; i++) {
            if ((istPrime(i)) && (istGut(i))) {
                System.out.printf("%" + MAXDIGITS + "d: %s %d %s %d %n", i, (istGut(i) ? "ist eine gute primzahl" : ""), smallNumber(i), "/", biggerNumber(i));
            }
            if (istTeufel(i) == true) {
                System.out.printf("%" + MAXDIGITS + "d: %s %n", i, (istTeufel(i) ? "Ist eine Teufelszahl" : ""));

            }
            if (istNarzi(i) == true) {
                System.out.printf("%" + MAXDIGITS + "d: %s %n", i, (istNarzi(i) ? "Ist eine narzisstische Zahl" : ""));
            }
        }
    }
}
