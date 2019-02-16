package ps2.routing;

import ps2.network.Connection;
import ps2.network.Location;
import ps2.network.Network;

/**
 * A DijkstraNetwork is a network holding Dijkstra-Locations.
 * @author uhl
 */
public class DijkstraNetwork extends Network {

    /**
     * constructs a DijkstraNetwork by copying the locations and 
     * outbound-connections of the given network.
     * @param n network to copy
     */
    public DijkstraNetwork(Network n) {
        // copy locations
//        System.out.println("create new DijkstraNetwork");
        for (int i = 0; i < n.getLocationCount(); i++) {
            Location l = n.getLocation(i);
            this.addLocation(new DijkstraLocation(l.getKey(), l.getCaption()));
//            System.out.println("add location " + l.getKey());
        }

        // copy getOutbound-connections
        for (int a = 0; a < n.getLocationCount(); a++) {
            Connection[] out = n.getLocation(a).getOutbound();
            for (int b = 0; b < out.length; b++) {
                out[b].copy(this);
//                System.out.println("add connection " + out[b]);
            }
        }
    }

    /**
     * gets the location with the key.
     * Assertion, if the location isn't a DijkstaLocation.
     * @param key of the location
     * @return the location with the key
     */
    @Override
    public DijkstraLocation getLocation(String key) {
        Location result = super.getLocation(key);

        assert result == null || result instanceof DijkstraLocation            
            : "sollte null oder DijkstraLocation sein";
			
        return (DijkstraLocation) result;
    }

    /**
     * adds the location as a DijkstraLocation.
     * @param l location to add
     * @return this DijkstraNetwork with the location added
     */
    @Override
    public DijkstraNetwork addLocation(Location l) {
        return (DijkstraNetwork) super.addLocation(l);
    }

    /**
     * removes the location.
     * @param l location to remove
     * @return this DijkstraNetwork without the location 
     */    
    @Override
    public DijkstraNetwork rmvLocation(Location l) {
        return (DijkstraNetwork) super.rmvLocation(l);
    }

}
