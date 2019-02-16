package ps2;

import ps2.network.vehicles.Car;
import ps2.network.Location;
import ps2.network.Network;
import ps2.network.connections.Street;
import ps2.routing.DijkstraRoutingFinder;
import ps2.routing.Routing;
import ps2.routing.RoutingFinder;

public class Example {

   public static void main(String[] args) {
       //creating a network with 6 locations and 8 connections
      Network n = new Network();
      Location rom = new Location("ROM", "Rom");
      Location ham = new Location("HAM", "Hamburg");
      Location lon = new Location("LON", "London");
      Location mos = new Location("MOS", "Moskau");
      
      n.addLocation(rom);
      n.addLocation(ham);
      n.addLocation(lon);
      n.addLocation(mos);
           
            
      n.addConnection(new Street( 900,  90), lon, ham);
      n.addConnection(new Street(1800, 100), lon, rom);
      n.addConnection(new Street(1700, 100), ham, rom);
      n.addConnection(new Street(3000,  90), rom, mos);
      
      //using the DijkstraRoutingFinder

      RoutingFinder rf = new DijkstraRoutingFinder(n);
      
      //find the best way from London to Rome
      Routing r = rf.findBest(new Car(160), lon, rom);
      System.out.println("LON to ROM by car(160): " + r + " Ankunft: " + r.arrival(new Car(160), 0));
      
      //find the best way from London to Moskau
      r = rf.findBest(new Car(160), lon, mos);
      System.out.println("LON to MOS by car(160): " + r + " Ankunft: " + r.arrival(new Car(160), 0));

      //find the best way from Rome to Hamburg (there is no way)(Dijkstra has to perform again because of new start)
      r = rf.findBest(new Car(160), rom, ham);
      System.out.println("ROM to HAM by car(160): " + r + " Ankunft: " + r.arrival(new Car(160), 0));
      
      //find the best way from Rome to Moskau with a slower car (Dijkstra has to perform again because of slower car)
      r = rf.findBest(new Car(90), rom, mos);
      System.out.println("ROM to MOS by car(90): " + r + " Ankunft: " + r.arrival(new Car(90), 0));
      

      
      //add two connections to the network
      System.out.println("\nadd LON->MOS and HAM->MOS to network");
      n.addConnection(new Street(3000,  90), lon, mos);
      n.addConnection(new Street(2100, 100), ham, mos);
      rf = new DijkstraRoutingFinder(n);
      
      //find the best way from London to Moskau
      r = rf.findBest(new Car(160), lon, mos);
      System.out.println("LON to MOS by car(160): " + r + " Ankunft: " + r.arrival(new Car(160), 0));

   }

}
