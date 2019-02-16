package ps2.routing;

import ps2.network.Connection;
import ps2.network.Vehicle;

/**
 * For Routing you may want to get
 * <ul>
 *   <li>the first connection</li>
 *   <li>the rest routing</li>
 *   <li>the arrival </li>
 *   <li>whether it is empty</li>
 *   <li>the count of steps</li>
 *   <li>addToFront</li>
 * </ul>
 * @author uhl
 */
public interface Routing {
   
   /** 
    * gets the first connection of the routing.
    * @return the first connection of the routing
    */
   public Connection head();

   /**
    * gets the tail of the routing.
    * @return  the tail of the routing
    */
   public Routing tail();
   
    /**
     * constructs a new connection to the routing.
     * @param c connection to add
     * @return the new routing with the connection added
     */
   public Routing addToFront(Connection c);
   
   /**
    * gets the count of steps of the routing.
    * @return the count of steps of the routing
    */
   public int steps();
   
   /**
    * gets the moment of arrival of the vehicle with the given start.
    * If the vehicle can't pass the street or is too slow (e.g. for a highway), 
    * null is returned.
    * @param v      the vehicle that uses the routing
    * @param start  the start-time 
    * @return the moment of arrival or null, when v can't pass the street
    */
   public Integer arrival(Vehicle v, int start);
   
   /**
    * gets if the routing is empty.
    * @return true, if it's empty
    */
   public boolean isempty();
   
}
