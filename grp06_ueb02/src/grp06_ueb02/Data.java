package grp06_ueb02;

import java.util.Arrays;


/**
 * stellt Testdaten mit Koordinaten zur Verf�gung. 0 - "Deutschland" 1 - "kleine
 * Welttour" 2 - "FH-Wedel"
 *
 * @author Gerit
 */
public class Data {

    /**
     * Testdaten mit mehreren Listen von Ortsangaben
     */
    private static final double[][][] SPOTS_LISTS = {
        { // St�dte in Deutschland
            {53.55, 10.00}, //HH
            {53.55, 10.00001}, //HH2
            {53.04, 8.80}, //HB
            {52.33, 9.76}, //H
            {51.30, 12.40}, //L
            {49.44, 11.10}, //N
            {48.74, 9.20}, //S
            {48.10, 11.60} //M
        },
        { // "kleine Welttour"
            {54.433754, 9.650631}, // Tirol
            {53.114639, 13.823194}, // Afrika
            {52.451908, 7.851692}, // Aegypten
            {54.424300, 10.391010}, // Brasilien
            {52.525594, 9.922922}, // Texas
            {54.427205, 10.375500}, // Kalifornien
            {53.821604, 9.583796}, // Groenland
            {54.490936, 8.876520}, // England
            {52.761547, 7.839971}, // Norwegen 
            {53.776255, 9.665465} // Sibirien
        },
        { // FH-Wedel-Umrundung
            {53.57889450, 9.727948300},
            {53.57870340, 9.728028770},
            {53.57867160, 9.728248710},
            {53.57871300, 9.728897800},
            {53.57866200, 9.728924630},
            {53.57866840, 9.729139200},
            {53.57790080, 9.729391330},
            {53.57774161, 9.727883934},
            {53.57881495, 9.727449417}
        }
    };

    /**
     * Liste mit Benennungen der Ortsangaben der Testlisten
     */
    private static final String[][] SPOT_NAMES_LISTS = new String[][]{
        {"Hamburg", "HH2", "Bremen", "Hannover", "Leipzig", "Nuernberg",
            "Stuttgart", "Muenchen"},
        {"Tirol", "Afrika", "Aegypten", "Brasilien", "Texas",
           "Kalifornien", "Groenland", "England", "Norwegen", "Sibirien"},
        {"0", "1", "2", "3", "4", "5", "6", "7", "8"},};

    /**
     * liefert eine Liste von Orten (Koordinaten) des index-ten Testfalls.
     *
     * @param index Index des Testfalls
     * @return eine Liste von Koordinaten; null, wenn der Index ung�ltig ist
     */
    public static double[][] getSpots(byte index) {
        //TODO DONE Tiefenkopie
        int i =0;

        double[][] spotBetween = new double [SPOTS_LISTS[index].length][];
        if ((index <= 2) || (index >= 0)) {
            while(i<SPOTS_LISTS[index].length){
            spotBetween[i] = SPOTS_LISTS[index][i].clone();
            i++;
            }
        } else {
            System.err.printf("Falscher Index übergeben");
            spotBetween = null;
        }
        return spotBetween;
    }

    /**
     * liefert eine Liste von Ortsnamen des index-ten Testfalls.
     *
     * @param index des Testfalls
     * @return eine Liste von Ortsnamen; null, wenn der Index ung�ltig ist
     */
    public static String[] getSpotNames(byte index) {

        String[] namesBetween;
        if ((index <= 2) || (index >= 0)) {
            namesBetween = SPOT_NAMES_LISTS[index].clone();
        } else {
            System.err.printf("Falscher Index übergeben");
            namesBetween = null;
        }
        return namesBetween;
    }
    
     public static void main(String[] args){
        double[][] spots = getSpots((byte)0);
        assert SPOTS_LISTS[0].length == spots.length;
        System.out.println(Arrays.toString(SPOTS_LISTS[0][0]) + " -> " + Arrays.toString(spots[0]));
        spots[0][0] = 99;
        System.out.println(Arrays.toString(SPOTS_LISTS[0][0]) + " -> " + Arrays.toString(spots[0]));
        
        String[] spotsnames = getSpotNames((byte)0);
        System.out.println(SPOT_NAMES_LISTS[0][0] + " -> " + spotsnames[0]);
        spotsnames[0] = "99";
        System.out.println(SPOT_NAMES_LISTS[0][0] + " -> " + spotsnames[0]);
        
    }


}
