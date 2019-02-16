/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp06_ueb02;

import java.util.Arrays;
import java.util.Locale;

/**
 *
 * @author Alexander Löffler / André Kloodt
 */
public class ProcessData {
    
    /**
     * liefert true, wenn der übergebene Index für die übergebene Arraylänge gültig ist.
     *
     * @param index Index für das Array
     * @param length Gibt die Länge des Arrays an
     * @return ein true oder false, falls der index zu groß ist
     */
    
    public static Boolean isValidIdx(byte index, byte length) {
        return (index >= 0) && (index < length);
    }

    /**
     * liefert eine Route der übergebenen Länge, welches mit Zahlen entsprechend des Indexes gefüllt ist.
     *
     * @param length Gibt die Länge des Arrays an bzw. die Anzhal der Elemente
     * @return ein Array von int-Werten
     */
    
    public static int[] createDefaultRoute(byte length) {
        int[] arrayBetween;
        arrayBetween = new int[length];
        int i = 0;
        while (i < length) {
            arrayBetween[i] = i;
            i++;
        }
        return arrayBetween;
    }

    /**
     * Berechnet die Entfernung zwischen zwei übergebenen Orten in Kilometern.
     *
     * @param arrayOne gibt den ersten Ort an
     * @param arrayTwo gibt den zweiten Ort an
     * @return ein double Wert für die Entfernung
     */
    
    public static double getDistanceOfSpots(double[] arrayOne, double[] arrayTwo) {

        double lengthX = 0;
        double endValue = 0;
        double lengthY = 0;
        double lati = 0;

        lati = (arrayOne[0] + arrayTwo[0]) / 2 * C180_DURCH_PI;
        lengthX = DISTANCE_OF_LONG * Math.cos(lati) * (arrayTwo[1] - arrayOne[1]);
        if (lengthX < 0) {
            lengthX *= -1;
        }
        lengthY = DISTANCE_OF_LONG * (arrayTwo[0] - arrayOne[0]);
        if (lengthY < 0) {
            lengthY *= -1;
        }

        endValue = Math.sqrt(lengthX * lengthX + lengthY * lengthY);

        return endValue;
    }
    private static final double C180_DURCH_PI = 0.01745;
    private static final double DISTANCE_OF_LONG = 111.3;

    /**
     * Gibt die Orte einer Route mit ihren Distanzen zum vorherigen Ort und den Ortsnamen aus.
     *
     * @param spots übergibt die Liste mit den Orte
     * @param route übergibt die Reihenfolge der Orte
     * @param spotnames gibt den Namen der Orte an
     */
    
    public static void printRouteWithDistances(double[][] spots, int[] route, String[] spotnames) {

        int i = 0;
        double allValue = 0;
        if (route == null) {
            route = createDefaultRoute((byte) spots.length);
            
        }

        if ((spots != null) && (spotnames != null) && (spotnames.length == spots.length)) {
            System.out.println(Arrays.toString(route));
            System.out.println("\n");

            System.out.printf(Locale.US,"%12s        Start (%9f, %9f) %n", spotnames[route[i]], spots[route[i]][0], spots[route[i]][1]);
            i++;

            while (i < route.length) {
                System.out.printf(Locale.US,"%12s %10.2fkm (%9f, %9f) %n", spotnames[route[i]], (getDistanceOfSpots(spots[route[i]], spots[route[i - 1]])), spots[route[i]][0], spots[route[i]][1]);
                allValue += (getDistanceOfSpots(spots[route[i]], spots[route[i - 1]]));
                i++;

            }
            System.out.printf("%n");
            System.out.printf(Locale.US,"Gesammtdistanz %10.2f Kilometer %n", allValue);
        } else if ((spots == null) || (spotnames == null)) {
            System.err.printf("Spots/Spotnames enthalten 'Null' oder sind unterschiedlich groß");
        } else if (spots.length == 0) {
            System.out.printf("Spots hat keine Werte");
        }
    }
    
    /**
     *  liefert den Index des Ortes, der die geringste Entfernung zu dem gewählten Ort aufweist.
     *
     * @param spots übergibt die Liste mit den Orte
     * @param visited übergibt die bereits gesehenen Orte
     * @param thisIdx übergibt den Ort für die Distanzberechnung
     * @return einen Integer-Wert für den Index des Ortes
     */
    
    public static int getIdxOfClosestPoint(double[][] spots, boolean[] visited, int thisIdx) {
        int i = 0;
        double valueBetween = 0;
        double endValue = 1000;
        int endIndex = 0;

        if ((spots.length == visited.length) && (spots != null) && (ProcessData.isValidIdx((byte) thisIdx, (byte) spots.length))) {

            visited[thisIdx] = true;

            while (i < spots.length) {
                if (!visited[i] ) {
                    valueBetween = getDistanceOfSpots(spots[thisIdx], spots[i]);
                    if (valueBetween <= endValue) {
                        endValue = valueBetween;
                        endIndex = i;
                    }
                    if (endValue <= 0) {
                        endValue = valueBetween;
                    }
                }
                i++;
            }
            visited[endIndex] = true;
        } else {
            endIndex = thisIdx;
        }

        return endIndex;
    }
    
    /**
     *  liefert eine Route durch alle Orte, die ausgehend vom übergebenen Startindex jeweils den Index des nächstgelegenen Ortes enthält.
     *
     * @param spots übergibt die Liste mit den Orte
     * @param startIdx übergibt den ertsen Index
     * @return eine Liste von Int-Werten, bei einem Fehler 'null'
     */
    
    public static int[] buildRoute(double[][] spots, int startIdx) {
        int[] route = new int[spots.length];
        int i = 0;
        boolean[] visited = new boolean[spots.length];

        if ((ProcessData.isValidIdx((byte) startIdx, (byte) spots.length)) && (spots != null)) {
            route[i] = startIdx;
            visited[startIdx] = true;
            i++;

            while (i < route.length) {

                startIdx = getIdxOfClosestPoint(spots, visited, startIdx);
                route[i] = startIdx;
                visited[startIdx] = true;

                i++;
            }
        } else {
            route = null;
        }
        return route;
    }
    
    /**
     *  liefert eine verkleinerte Kopie von srcArray, die den Wert an index nicht mehr enthält.
     *
     * @param srcArray übergibt eine Liste von Indizees
     * @param index den zu entfernenden Index
     * @return eine Liste von Indizees, bei Fehler den Quellarray
     */
    
    public static int[] removeIntAt(int[] srcArray, int index) {
        int i = 0;
        int k = 0;
        int[] srcCop = new int[(srcArray.length) - 1];
        int[] returnArray;

        if ((ProcessData.isValidIdx((byte) index, (byte) srcArray.length))) {
            while (i < srcArray.length) {
                if (i != index) {
                    srcCop[k] = srcArray[i];
                    k++;
                }
                i++;
            }
            returnArray = srcCop;
        } else {
            returnArray = srcArray;
        }
        return returnArray;
    }
    
    /**
     *  entfernt aus der übergebenen Route jeden Indexwert, dessen Ort eine kürzere Distanz 
     *  als minDistance zum Ort des vorherigen Indexwertes aufweist und liefert diese verkürzte Route zurück.
     *
     * @param spots übergibt die Liste mit den Orte
     * @param route übergibt eine Index Liste der zu gehenden Orte
     * @param minDistance übergibt die zu überbrückende Mindestdistanz
     * @return eine Liste von Indizees, bei Fehler den Quellarray
     */
    
    static public int[] removeSimilarFromRoute(double[][] spots, int[] route, double minDistance) {
        int i = 1;
        //TODO DONE Testfall 1 mit Entfernungen < 100km funktioniert nicht 
    //    minDistance /= 1000;

        if ((spots != null) && (spots.length >= 2)) {
            if (route == null) {
                route = createDefaultRoute((byte) spots.length);
            }
            while (i < route.length) {
                if (getDistanceOfSpots(spots[route[i]], spots[route[i - 1]]) <= minDistance) {
                     route = removeIntAt(route, i);
                     i--;
                }
                i++;
            }

        }

        return route;
    }
}
