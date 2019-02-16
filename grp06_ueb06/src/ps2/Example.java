/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps2;

import ps2.network.Connection;
import ps2.network.connections.*;
import ps2.network.vehicles.*;
import ps2.network.Network;
import ps2.network.Location;
import ps2.network.Connection;
import ps2.network.connections.Street;

/**
 *
 * @author inf102691
 */
public class Example {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Location hamburg = new Location("HAM", "Hamburg");
        Location rom = new Location("RO", "Rom");
        Location moskau = new Location("MOS", "Moskau");
        Location london = new Location("LON", "London");

        
        
        Street hamZuRom = new Street(1700, 100);
        Street romZuMos = new Street(300, 90);
        Street lonZuHam = new Street (900, 90);
        Street lonZuRom = new Street(1800, 100);
        
        
        
        
        
       

        
        
        
        
       
        
        
        Network myNetwork = new Network();
       
        
        
        
        
        myNetwork.addLocation(rom);
        myNetwork.addLocation(london);
       
   
        
        myNetwork.addConnection(lonZuRom, "LON", "RO");
       
        myNetwork.rmvLocation(london);
        
        
      
        
        
        
        
    }

}
