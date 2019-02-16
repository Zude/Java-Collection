/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grp06_ueb02;

import java.util.Scanner;

/**
 *
 * @author Alexander Löffler / André Kloodt
 */




public class Grp06_ueb02 {
    
    private static void outGive(){
        System.out.printf("%n%n");
        System.out.printf("1. wähle Testfall aus Daten %n");
        System.out.printf("2. zeige Standardroute %n");
        System.out.printf("3. zeige aktuelle Route %n");
        System.out.printf("4. erzeuge Route aus allen Orten von eingelesenem Startpunkt aus %n");
        System.out.printf("5. entferne Routenpunkte naheliegender Orte %n%n");
        System.out.printf("Bitte wähle einen Menüpunkt aus: ");
        
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        
        Scanner scan = new Scanner(System.in);    
        int selection =1;
        int testSelection = 0;
        double[][] mySpots = Data.getSpots((byte) testSelection);
        int[] myRoute = ProcessData.createDefaultRoute((byte )mySpots.length);
        int startWith;
        int minDiz;
        
        
        
        
        
        while((selection > 0) && (selection < 6)){
         Grp06_ueb02.outGive();
         selection = scan.nextInt();
         System.out.printf("%n%n");
         
         
        switch (selection) {
            case 1: {
                System.out.printf("Gebe bitte deinen Testfall an: ");
                testSelection = scan.nextInt();
                mySpots = Data.getSpots((byte) testSelection);
                myRoute = ProcessData.createDefaultRoute((byte) mySpots.length);
                break;
            }
            
            case 2: {    
                System.out.printf("%n%n");
                System.out.printf("Die Default Route: %n");
                System.out.printf("%n");
                ProcessData.printRouteWithDistances(Data.getSpots((byte) testSelection), ProcessData.createDefaultRoute((byte) (Data.getSpots((byte) testSelection)).length), Data.getSpotNames((byte) testSelection));
            break;
            }
            case 3: {
                System.out.printf("%n%n");
                System.out.printf("Aktuelle Route: %n");
                System.out.printf("%n");
                ProcessData.printRouteWithDistances(mySpots, myRoute, Data.getSpotNames((byte) testSelection));
            break;
            }
            
            case 4: {
                System.out.printf("Bitte den Startpunkt der Route angeben: ");
                startWith = scan.nextInt();
                myRoute = ProcessData.buildRoute(mySpots, startWith);
                
            break;
            }
            
            case 5: {
               System.out.printf("Bitte gebe die Mindestdistanz zwischen 2 Punkten in Kilometern an: ");
               minDiz = scan.nextInt();
               
               myRoute=ProcessData.removeSimilarFromRoute(mySpots, myRoute, minDiz);
                
            break;
            }
            
        }
        
        }
       
    }
    
}
