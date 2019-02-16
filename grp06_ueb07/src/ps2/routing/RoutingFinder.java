package ps2.routing;

import ps2.network.Location;
import ps2.network.Vehicle;

/**
 * An interface for a RoutingFinder.
 * @author uhl
 */
public interface RoutingFinder {
    
    /**
     * gets the routing with the fastest way.
     * @param v     the vehicle that uses the routing
     * @param from  the location to start from
     * @param to    the location to travel to
     * @return the routing with the fastest way
     */

   public Routing findBest(Vehicle v, Location from, Location to);

}
